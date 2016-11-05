package net.nitrado.api.order;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DimensionPricing extends Pricing {

    public DimensionPricing(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
    }

    HashMap<String, String> dimensions = new HashMap<String, String>();

    public void addDimension(String dimension, String value) {
        dimensions.put(dimension, value);
    }
    public String getSelectedDimension(String dimension) {
        return dimensions.get(dimension);
    }
    public void removeDimension(String dimension) { dimensions.remove(dimension); }

    public void reset(){
        dimensions.clear();
    }

    @Override
    public int getPrice(int service, int rentalTime) {
        PriceList information = getPrices(service);
        Dimension.DimensionValues prices = information.getDimensionPrices();

        String[] dims = new String[dimensions.size() + 1];
    /*    int i = 0;

        for (String key:dimensions.keySet()) { // TODO: make sure they are ordered as in the original response
            dims[i] = dimensions.get(key);
            i++;
        }*/
        Dimension[] realDims = information.getDimensions(); // order them as in the original response
        int j = 0;
        for (int i = 0; i< realDims.length; i++) {
            if (dimensions.containsKey(realDims[i].getId())) {
                dims[j] = dimensions.get(realDims[i].getId());
                j++;
            }

        }
        // last dimension is rental_time
        dims[j] = rentalTime+"";

        if (!prices.containsKey(Dimension.path(dims))) {
            throw new NitrapiErrorException("Can't find price with dimensions " + Dimension.path(dims));
        }

        Dimension.DimensionValue price = prices.get(Dimension.path(dims));
        if (!(price instanceof Dimension.PriceDimensionValue)) {
            throw new NitrapiErrorException("Misformated json for dimension " + Dimension.path(dims));
        }
        int cost = ((Dimension.PriceDimensionValue)price).getValue();
        int advice = information.getAdvice();
        if (advice > cost){
            advice -= (int)((advice - cost) * (50.f/100.f));
        }
        cost -= advice;
        return cost;
    }

    @Override
    public void orderService(int rentalTime) {
        int ADD_PARAMS = 3;
        Parameter[] parameters = new Parameter[dimensions.size() + additionals.size()+ ADD_PARAMS];
        parameters[0] = new Parameter("price", getPrice(rentalTime));
        parameters[1] = new Parameter("rental_time", rentalTime);
        parameters[2] = new Parameter("location", locationId);
        int j = ADD_PARAMS;
        for (Map.Entry<String, String> entry: dimensions.entrySet()) {
            parameters[j] = new Parameter("dimensions["+entry.getKey()+"]", entry.getValue());
            j++;
        }
        for (Map.Entry<String, String> entry : additionals.entrySet()) {
            parameters[j] = new Parameter("additionals[" + entry.getKey() + "]", entry.getValue());
            j++;
        }

        nitrapi.dataPost("order/order/"+product, parameters);
    }

    @Override
    public void switchService(int service, int rentalTime) {
        int ADD_PARAMS = 5;
        Parameter[] parameters = new Parameter[dimensions.size() + additionals.size()+ ADD_PARAMS];
        parameters[0] = new Parameter("price", getSwitchPrice(service, rentalTime));
        parameters[1] = new Parameter("rental_time", rentalTime);
        parameters[2] = new Parameter("location", locationId);
        parameters[3] = new Parameter("method", "switch");
        parameters[4] = new Parameter("service_id", service);
        int j = ADD_PARAMS;
        for (Map.Entry<String, String> entry: dimensions.entrySet()) {
            parameters[j] = new Parameter("dimensions["+entry.getKey()+"]", entry.getValue());
            j++;
        }
        for (Map.Entry<String, String> entry : additionals.entrySet()) {
            parameters[j] = new Parameter("additionals[" + entry.getKey() + "]", entry.getValue());
            j++;
        }

        nitrapi.dataPost("order/order/"+product, parameters);
    }
}
