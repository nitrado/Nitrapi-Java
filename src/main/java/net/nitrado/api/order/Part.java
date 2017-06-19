package net.nitrado.api.order;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 *
 */
public class Part {
    private String type;
    private String name; // optional
    @SerializedName("min_count")
    private int minCount;
    @SerializedName("max_count")
    private int maxCount;
    private int[] steps;
    @SerializedName("step_names")
    private HashMap<Integer, String> stepNames;
    private boolean optional;
    @SerializedName("rental_times")
    private PartRentalOption[] rentalTimes;

    public String getType() {
        return type;
    }

    public String getName() {
        return name == null ? type : name;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int[] getSteps() {
        return steps;
    }

    /**
     * @return better human readable names for the steps
     */
    public HashMap<Integer, String> getStepNames() {
        return stepNames;
    }

    public String getStepName(int step) {
        if (stepNames == null) {
            return steps[step] + "";
        }
        return stepNames.get(steps[step]);
    }

    public boolean isOptional() {
        return optional;
    }

    public PartRentalOption[] getRentalTimes() {
        return rentalTimes;
    }
}
