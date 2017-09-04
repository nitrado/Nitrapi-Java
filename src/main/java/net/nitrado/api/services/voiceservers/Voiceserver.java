package net.nitrado.api.services.voiceservers;

import com.google.gson.JsonObject;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.services.Service;

/**
 * This class represents a voice server.
 */
public class Voiceserver extends Service {
    private VoiceserverInfo info;

    class VoiceserverInfo {
        private Type type;
        private String ip;
        private int port;
        private int slots;
        private boolean started;
        private JsonObject specific;
    }

    public static class Type extends Value {
        public Type(String value) {
            super(value);
        }

        public static final Type TEAMSPEAK3 = new Type("teamspeak3");
        public static final Type MUMBLE = new Type("mumble");
        public static final Type VENTRILO = new Type("ventrilo");
    }

    public Type getType() {
        return info != null ? info.type : null;
    }

    public String getIp() {
        return info != null ? info.ip : "";
    }

    public int getPort() {
        return info != null ? info.port : 0;
    }

    public int getSlots() {
        return info != null ? info.slots : 0;
    }

    public boolean isStarted() {
        return info != null && info.started;
    }

    @Override
    public void refresh() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/voiceservers", null);
        VoiceserverInfo infos = api.fromJson(data.get("voiceserver"), VoiceserverInfo.class);
        this.info = infos;
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
    public void doReinstall() throws NitrapiException {
        api.dataPost("services/" + getId() + "/voiceservers/reinstall", null);
    }
}
