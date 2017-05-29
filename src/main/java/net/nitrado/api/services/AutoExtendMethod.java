package net.nitrado.api.services;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class AutoExtendMethod {
    private int id;
    private String name;
    private String description;
    private boolean available;
    private boolean active;
    @SerializedName("payment_method")
    private String paymentMethod;
    @SerializedName("rental_times")
    HashMap<Integer, Integer> rentalTimes;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isActive() {
        return active;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     *
     * @return a map of rental_time to price
     */
    public HashMap<Integer, Integer> getRentalTimes() {
        return rentalTimes;
    }

    @Override
    public String toString() {
        return description;
    }
}
