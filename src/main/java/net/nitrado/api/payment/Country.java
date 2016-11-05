package net.nitrado.api.payment;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("short")
    private String isoCode;
    private String name;


    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
