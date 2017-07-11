package net.nitrado.api.order;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.cloudservers.CloudServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Pricing {
    Nitrapi nitrapi;
    protected String product;
    int locationId;


    protected Map<String, String> additionals;


    /**
     * Cache of prices by location id
     */
    private Map<String, PriceList> prices;


    public Pricing(Nitrapi nitrapi, int locationId) {
        this.nitrapi = nitrapi;
        this.locationId = locationId;
        this.prices = new HashMap<String, PriceList>();
        this.additionals = new HashMap<String, String>();
    }

    /**
     * @return a list of locations this service is available in.
     */
    public Location[] getLocations() {
        JsonObject data = nitrapi.dataGet("order/order/locations", null);
        Location[] locations = nitrapi.fromJson(data.get("locations"), Location[].class);

        ArrayList<Location> available = new ArrayList<Location>();
        for (Location loc : locations) {
            if (loc.hasService(product)) { // TODO: dependent
                available.add(loc);
            }
        }
        return available.toArray(new Location[available.size()]);
    }

    public void setLocationId(int id) {
        this.locationId = id;
    }

    public PriceList getPrices() {
        return getPrices(null);
    }

    /**
     * Get full price list for a specified product
     *
     * @param service service id or -1
     * @return the price list for this product
     */
    public PriceList getPrices(Service service) {
        String cacheName = "" + locationId;
        if (service != null) {
            cacheName += "/" + service;
        }
        if (prices.containsKey(cacheName)) {  // if we requested this before, return it
            return prices.get(cacheName);
        }

        Parameter[] parameters;
        if (service != null) {
            parameters = new Parameter[]{
                    new Parameter("location", locationId),
                    new Parameter("sale_service", service.getId())
            };
        } else {
            parameters = new Parameter[]{
                    new Parameter("location", locationId)
            };
        }

        JsonObject data = nitrapi.dataGet("order/pricing/" + product, parameters);
        prices.put(cacheName, nitrapi.fromJson(data.get("prices"), PriceList.class));

        return prices.get(cacheName);
    }

    /**
     * Returns the price for extending a specific service.
     *
     * @param service    id of the service
     * @param rentalTime the time you want to extend this service
     * @return the prices for extending this service
     */
    public int getExtendPricesForService(Service service, int rentalTime) {
        return nitrapi.dataGet("order/pricing/" + product, new Parameter[]{
                new Parameter("method", "extend"),
                new Parameter("service_id", service.getId()),
                new Parameter("rental_time", rentalTime)
        }).get("extend").getAsJsonObject().get("prices").getAsJsonObject().get(rentalTime + "").getAsInt();
    }


    private class ExtensionPriceList {
        @SerializedName("rental_times")
        private int[] rentalTimes;
        @SerializedName("min_rental_time")
        private int minRentalTime;
        @SerializedName("max_rental_time")
        private int maxRentalTime;

        private HashMap<Integer, Integer> prices;

        public int[] getRentalTimes() {
            return rentalTimes;
        }

        public int getMinRentalTime() {
            return minRentalTime;
        }

        public int getMaxRentalTime() {
            return maxRentalTime;
        }

        public Map<Integer, Integer> getPrices() {
            return prices;
        }
    }

    /**
     * Extends the specific service about the specific rental time
     *
     * @param service    id of the service
     * @param rentalTime time to rent
     * @param price      price calculated from getExtendPricesForService
     */
    public void doExtendService(Service service, int rentalTime, int price) {
        // int price = getExtendPriceForService(service, rentalTime);
        nitrapi.dataPost("order/order/" + product, new Parameter[]{
                new Parameter("price", price),
                new Parameter("rental_time", rentalTime),
                new Parameter("service_id", service.getId()),
                new Parameter("method", "extend")
        });
    }

    public int calcAdvicePrice(int price, int advice, Service service) {
        // Always return 100% of advice for dynamic cloud servers.
        if (service instanceof CloudServer && ((CloudServer) service).isDynamic()) {
            return price - advice;
        }

        if (advice > price) {
            advice -= Math.round((advice - price) * 0.5f);
        }
        return price - advice;
    }

    public int getPrice(int rentalTime) {
        return getPrice(null, rentalTime);
    }

    public abstract int getPrice(Service service, int rentalTime);

    public abstract void orderService(int rentalTime);

    public int getSwitchPrice(Service service, int rentalTime) {
        return getPrice(service, rentalTime);
    }

    public abstract void switchService(Service service, int rentalTime);


}
