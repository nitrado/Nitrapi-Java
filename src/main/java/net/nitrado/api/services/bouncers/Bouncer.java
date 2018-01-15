package net.nitrado.api.services.bouncers;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.Value;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Bouncer.
 */
public class Bouncer extends Service {
    private class BouncerData {
        @SerializedName("max_bouncer")
        private Integer maxBouncer;
        private BouncerInstance[] bouncers;
    }

    private BouncerData data;

    /**
     * Returns maximum amount of bouncers.
     *
     * @return maximum amount of bouncers
     */
    @Nullable
    public Integer getMaxBouncer() {
        return data.maxBouncer;
    }

    /**
     * Returns bouncer instances.
     *
     * @return bouncer instances
     */
    @Nullable
    public BouncerInstance[] getBouncers() {
        return data.bouncers;
    }
    @Override
    public void refresh() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/bouncers", null);
        BouncerData datas = api.fromJson(data.get("bouncer"), BouncerData.class);
        this.data = datas;
    }
}
