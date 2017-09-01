package net.nitrado.api.services.cloudservers.firewall;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;

/**
 * This class represents a Firewall.
 */
public class Firewall {

    private transient Service service;
    private transient Nitrapi api;

    /**
     * Used internally.
     */
    public void init(Service service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    public static class Protocol extends Value {
        public Protocol(String value) {
            super(value);
        }

        public static final Protocol TCP = new Protocol("tcp");
        public static final Protocol UDP = new Protocol("udp");
        public static final Protocol ICMP = new Protocol("icmp");
        public static final Protocol ANY = new Protocol("any");

    }

    private boolean enabled;
    private FirewallRule[] rules;

    public class FirewallRule {
        @SerializedName("target_port")
        private int targetPort;
        @SerializedName("target_ip")
        private String targetIp;
        private Protocol protocol;
        private int number;
        private String comment;
        @SerializedName("source_ip")
        private String sourceIp;

        /**
         * Returns targetPort.
         *
         * @return targetPort
         */
        public int getTargetPort() {
            return targetPort;
        }

        /**
         * Returns targetIp.
         *
         * @return targetIp
         */
        public String getTargetIp() {
            return targetIp;
        }

        /**
         * Returns protocol.
         *
         * @return protocol
         */
        public Protocol getProtocol() {
            return protocol;
        }

        /**
         * Returns number.
         *
         * @return number
         */
        public int getNumber() {
            return number;
        }

        /**
         * Returns comment.
         *
         * @return comment
         */
        public String getComment() {
            return comment;
        }

        /**
         * Returns sourceIp.
         *
         * @return sourceIp
         */
        public String getSourceIp() {
            return sourceIp;
        }

    }


    /**
     * Returns enabled.
     *
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns rules.
     *
     * @return rules
     */
    public FirewallRule[] getRules() {
        return rules;
    }


    /**
     * Deletes a specific rule by number
     *
     * @param number
     */
    public void deleteRule(int number) throws NitrapiException {
        api.dataDelete("services/" + service.getId() + "/cloud_servers/firewall/remove", new Parameter[]{
                new Parameter("number", number)
        });
    }

    /**
     * Enables the firewall
     */
    public void enableFirewall() throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/firewall/enable", null);
    }

    /**
     * Disables the firewall
     */
    public void disableFirewall() throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/firewall/disable", null);
    }

    /**
     * Creates a new firewall rule
     *
     * @param sourceIp
     * @param targetIp
     * @param targetPort
     * @param protocol
     * @param comment
     */
    public void addRule(String sourceIp, String targetIp, Integer targetPort, Protocol protocol, String comment) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/firewall/add", new Parameter[]{
                new Parameter("source_ip", sourceIp),
                new Parameter("target_ip", targetIp),
                new Parameter("target_port", targetPort),
                new Parameter("protocol", protocol),
                new Parameter("comment", comment)
        });
    }


}
