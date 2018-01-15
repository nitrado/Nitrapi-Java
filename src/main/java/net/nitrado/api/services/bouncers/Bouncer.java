package net.nitrado.api.services.bouncers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.Value;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class represents a Bouncer.
 */
public class Bouncer extends Service {
    private class BouncerData {
        @SerializedName("max_bouncer")
        private int maxBouncer;
        private BouncerInstance[] bouncers;
    }

    private BouncerData data;

    /**
     * Returns maximum amount of bouncers.
     *
     * @return maximum amount of bouncers
     */
    public int getMaxBouncer() {
        return data.maxBouncer;
    }

    /**
     * Returns bouncer instances.
     *
     * @return bouncer instances
     */
    public BouncerInstance[] getBouncers() {
        return data.bouncers;
    }
    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/bouncers", null);
        BouncerData datas = api.fromJson(data.get("bouncer"), BouncerData.class);
        this.data = datas;
    }
}
