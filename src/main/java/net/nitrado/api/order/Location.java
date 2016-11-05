package net.nitrado.api.order;

import com.google.gson.annotations.SerializedName;

public class Location {
    private int id;
    private String country;
    private String city;
    private Products products;
    class Products {
        private boolean bouncer;
        @SerializedName("cloud_server")
        private boolean cloudServer;
        private boolean gameserver;
        private boolean mumble;
        private boolean musicbot;
        private boolean teamspeak3;
        private boolean ventrilo;
        private boolean webspace;
    }

    public int getId() {
        return id;
    }

    /**
     * @return the two letter country code
     */
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public boolean hasBouncer() {
        return products.bouncer;
    }

    public boolean hasCloudServer() {
        return products.cloudServer;
    }

    public boolean hasGameserver() {
        return products.gameserver;
    }

    public boolean hasMumble() {
        return products.mumble;
    }

    public boolean hasMusicbot() {
        return products.mumble;
    }

    public boolean hasTeamspeak3() {
        return products.teamspeak3;
    }

    public boolean hasVentrilo() {
        return products.ventrilo;
    }

    public boolean hasWebspace() {
        return products.webspace;
    }


    public boolean hasService(String type) {
        if (type.equals("bouncer")) {
            return products.bouncer;
        } else if (type.equals("cloud_server")) {
            return products.cloudServer;
        } else if (type.equals("gameserver")) {
            return products.gameserver;
        } else if (type.equals("mumble")) {
            return products.mumble;
        } else if (type.equals("musicbot")) {
            return products.musicbot;
        } else if (type.equals("teamspeak3")) {
            return products.teamspeak3;
        } else if (type.equals("ventrilo")) {
            return products.ventrilo;
        } else if (type.equals("webspace")) {
            return products.webspace;
        }
        return false;
    }

    @Override
    public String toString() {
        return city + " (" + country + ")";
    }
}
