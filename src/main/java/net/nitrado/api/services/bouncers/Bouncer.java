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
        @SerializedName("type")
        private String bouncerType;
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
     * Returns bouncerType.
     *
     * @return bouncerType
     */
    @Nullable
    public String getBouncerType() {
        return data.bouncerType;
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

    /**
     * Creates a new ident
     *
     * @param ident Set the ident. Allowed /^[A-Za-z0-9]+$/.
     * @param password A password for the ident
     */
    public void addIdent(@NotNull String ident, @NotNull String password) throws NitrapiException {
        api.dataPost("services/" + getId() + "/bouncers/", new Parameter[] {
            new Parameter("ident", ident),
            new Parameter("password", password)
        });
    }

    /**
     * Deletes an ident
     *
     * @param ident Name of the ident which will be deleted
     */
    public void deleteIdent(@NotNull String ident) throws NitrapiException {
        api.dataDelete("services/" + getId() + "/bouncers/", new Parameter[] {
            new Parameter("ident", ident)
        });
    }

    /**
     * Changes the password for an ident
     *
     * @param ident ident
     * @param password a new password
     */
    public void editPassword(@NotNull String ident, @NotNull String password) throws NitrapiException {
        api.dataPut("services/" + getId() + "/bouncers/", new Parameter[] {
            new Parameter("ident", ident),
            new Parameter("password", password)
        });
    }

    @Override
    public void refresh() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/bouncers", null);
        BouncerData datas = api.fromJson(data.get("bouncer"), BouncerData.class);
        this.data = datas;
    }
}
