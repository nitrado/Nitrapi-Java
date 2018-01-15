package net.nitrado.api.services.voiceservers;

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
 * This class represents a Voiceserver.
 */
public class Voiceserver extends Service {

    public static class Type extends Value {
        public Type(String value) {
            super(value);
        }
        public static Type TEAMSPEAK3 = new Type("teamspeak3");
        public static Type MUMBLE = new Type("mumble");
        public static Type VENTRILO = new Type("ventrilo");
    }

    private class VoiceserverData {
        private Type type;
        private String ip;
        private Integer port;
        private Integer slots;
        private Boolean started;
    }

    private VoiceserverData data;

    /**
     * Returns type.
     *
     * @return type
     */
    @Nullable
    public Type getType() {
        return data.type;
    }

    /**
     * Returns ip.
     *
     * @return ip
     */
    @Nullable
    public String getIp() {
        return data.ip;
    }

    /**
     * Returns port.
     *
     * @return port
     */
    @Nullable
    public Integer getPort() {
        return data.port;
    }

    /**
     * Returns slots.
     *
     * @return slots
     */
    @Nullable
    public Integer getSlots() {
        return data.slots;
    }

    /**
     * Returns started.
     *
     * @return started
     */
    @Nullable
    public Boolean isStarted() {
        return data.started;
    }

    /**
     * Restarts the voiceserver.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void doRestart() throws NitrapiException {
        api.dataPost("services/" + getId() + "/voiceservers/restart", null);
    }

    /**
     * Stopps the voiceserver. Notice: Not supported by Mumble.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void doStop() throws NitrapiException {
        api.dataPost("services/" + getId() + "/voiceservers/stop", null);
    }

    /**
     * Reinstalls the voiceserver.
     *
     * @permission ROLE_WEBINTERFACE_GENERAL_CONTROL
     */
    public void reinstall() throws NitrapiException {
        api.dataPost("services/" + getId() + "/voiceservers/reinstall", null);
    }

    @Override
    public void refresh() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/voiceservers", null);
        VoiceserverData datas = api.fromJson(data.get("voiceserver"), VoiceserverData.class);
        this.data = datas;
    }
}
