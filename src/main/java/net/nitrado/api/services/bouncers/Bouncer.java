package net.nitrado.api.services.bouncers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.services.Service;

/**
 * This class represents an irc-bouncer.
 */
public class Bouncer extends Service {

    private BouncerInfo info;

    private class BouncerInfo {
        @SerializedName("max_bouncer")
        private int maxBouncer;

        private BouncerInstance[] bouncers;


    }

    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/bouncers", null);
        BouncerInfo infos = api.fromJson(data.get("bouncer"), BouncerInfo.class);
        this.info = infos;
    }


    public int getMaxBouncer() {
        return info != null ? info.maxBouncer : 0;
    }

    public BouncerInstance[] getBouncers() {
        return info != null ? info.bouncers : new BouncerInstance[0];
    }
}
