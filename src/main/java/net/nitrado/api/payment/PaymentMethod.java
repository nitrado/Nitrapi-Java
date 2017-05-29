package net.nitrado.api.payment;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class PaymentMethod {
    private String id;
    private String name;
    @SerializedName("min_amount")
    private int minAmount;
    @SerializedName("max_amount")
    private int maxAmount;
    private int[] tariffs;
    private String[] countries;
    private HashMap<String, String> banks;

    /*+
     * @return the id of this payment method
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of this payment method.
     * @param id id of this method
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name of this payment method
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the minimum amount payable. Overwritten by tariffs array.
     */
    public int getMinAmount() {
        return minAmount;
    }
    /**
     * @return the maximum amount payable. Overwritten by tariffs array.
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * @return an array of available amounts. Overwrites min and max amount.
     */
    public int[] getTariffs() {
        return tariffs;
    }

    /**
     * @return an array of countries this payment method is available in or null if available for all countries.
     */
    public String[] getCountries() {
        return countries;
    }

    /**
     * @return in case of gc_ideal returns an array of banks to choose one from.
     */
    public HashMap<String, String> getBanks() {
        return banks;
    }
}
