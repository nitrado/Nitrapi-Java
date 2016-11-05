package net.nitrado.api.order;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PriceList {

    private int advice;

    @SerializedName("rental_times")
    private int[] rentalTimes;

    // for part pricing
    private Part[] parts;

    // for dimension pricing
    private Dimension[] dimensions;
    private Dimension.DimensionValues prices;

    public int getAdvice() {
        return advice;
    }

    public int[] getRentalTimes() {
        return rentalTimes;
    }

    public Part[] getParts() {
        return parts;
    }


    public Dimension[] getDimensions() {
        return dimensions;
    }
    public Dimension.DimensionValues getDimensionPrices() {
        return prices;
    }
}
