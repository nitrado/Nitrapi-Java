package net.nitrado.api.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PriceList {

    private int advice;

    @SerializedName("rental_times")
    private int[] rentalTimes;
    @SerializedName("min_rental_time")
    private int minRentalTime;
    @SerializedName("max_rental_time")
    private int maxRentalTime;
    private boolean dynamicRentalTimes;

    // for part pricing
    private Part[] parts;

    // for dimension pricing
    private Dimension[] dimensions;
    private Dimension.DimensionValues prices;

    public int getAdvice() {
        return advice;
    }

    public int[] getRentalTimes() {
        if (rentalTimes == null) {
            dynamicRentalTimes = true;
            ArrayList<Integer> times = new ArrayList<Integer>();
            for (int i = minRentalTime; i <= maxRentalTime; i += 24) {
                times.add(i);
            }

            rentalTimes = new int[times.size()];
            for (int i = 0; i < times.size(); i++) {
                rentalTimes[i] = times.get(i);
            }
        }
        return rentalTimes;
    }

    public boolean hasDynamicRentalTimes() {
        return dynamicRentalTimes || rentalTimes == null;
    }

    public int getMinRentalTime() {
        return minRentalTime;
    }

    public int getMaxRentalTime() {
        return maxRentalTime;
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
