package net.nitrado.api.order;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

import java.util.HashMap;
import java.util.Map;

public class PartPricing extends Pricing {
    Map<String, Integer> parts = new HashMap<String, Integer>();

    public PartPricing(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
    }

    public void addPart(String part, int value) {
        parts.put(part, value);
    }

    public Part[] getParts() {
        return getPrices().getParts();
    }

    @Override
    public int getPrice(Service service, int rentalTime) {
        PriceList prices = getPrices(service);

        double totalPrice = 0;
        int multiply = 1;

        if (prices.hasDynamicRentalTimes()) {
            if (rentalTime % prices.getMinRentalTime() != 0) {
                throw new NitrapiErrorException("Rental time " + rentalTime + " is invalid (Modulo " + prices.getMinRentalTime() + ")");
            }
            multiply = rentalTime / prices.getMinRentalTime();
            rentalTime = prices.getMinRentalTime();
        }

        for (Part part : prices.getParts()) {
            if (!parts.containsKey(part.getType())) {
                throw new NitrapiErrorException("No amount selected for " + part.getType());
            }
            int amount = parts.get(part.getType());
            if (amount < part.getMinCount()) {
                throw new NitrapiErrorException("The amount " + amount + " of type " + part.getType() + " is too small.");
            }
            if (amount > part.getMaxCount()) {
                throw new NitrapiErrorException("The amount " + amount + " of type " + part.getType() + " is too big.");
            }

            double localPrice = -1;
            for (PartRentalOption rentalOption : part.getRentalTimes()) {
                if (rentalOption.getHours() == rentalTime) {
                    for (Price price : rentalOption.getPrices()) {
                        if (price.getCount() == amount) {
                            localPrice = price.getPrice();
                            break;
                        }
                    }
                }
                if (localPrice != -1) {
                    break;
                }
            }

            if (localPrice == -1) {
                throw new NitrapiErrorException("No valid price found for part " + part.getType());
            }
            totalPrice += localPrice;
        }

        totalPrice *= multiply;

        return calcAdvicePrice((int) Math.round(totalPrice), prices.getAdvice(), service);
    }

    @Override
    public void orderService(int rentalTime) {
        int ADD_PARAMS = 3;
        Part[] priceParts = getParts();
        Parameter[] parameters = new Parameter[priceParts.length + additionals.size() + ADD_PARAMS];
        parameters[0] = new Parameter("price", getPrice(rentalTime));
        parameters[1] = new Parameter("rental_time", rentalTime);
        parameters[2] = new Parameter("location", locationId);
        int j = ADD_PARAMS;
        for (int i = 0; i < priceParts.length; i++) {
            Part part = getParts()[i];
            parameters[j] = new Parameter("parts[" + part.getName() + "]", parts.get(part.getName()));
            j++;
        }
        for (Map.Entry<String, String> entry : additionals.entrySet()) {
            parameters[j] = new Parameter("additionals[" + entry.getKey() + "]", entry.getValue());
            j++;
        }

        nitrapi.dataPost("order/order/" + product, parameters);
    }

    @Override
    public void switchService(Service service, int rentalTime) {
        int ADD_PARAMS = 5;
        Part[] priceParts = getParts();
        Parameter[] parameters = new Parameter[priceParts.length + additionals.size() + ADD_PARAMS];
        parameters[0] = new Parameter("price", getSwitchPrice(service, rentalTime));
        parameters[1] = new Parameter("rental_time", rentalTime);
        parameters[2] = new Parameter("location", locationId);
        parameters[3] = new Parameter("method", "switch");
        parameters[4] = new Parameter("service_id", service.getId());
        int j = ADD_PARAMS;
        for (int i = 0; i < priceParts.length; i++) {
            Part part = getParts()[i];
            parameters[j] = new Parameter("parts[" + part.getName() + "]", parts.get(part.getName()));
            j++;
        }
        for (Map.Entry<String, String> entry : additionals.entrySet()) {
            parameters[j] = new Parameter("additionals[" + entry.getKey() + "]", entry.getValue());
            j++;
        }

        nitrapi.dataPost("order/order/" + product, parameters);
    }
}
