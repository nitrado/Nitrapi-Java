package net.nitrado.api.services.cloudservers.firewall;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.Value;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Firewall.
 */
public class Firewall {

    private transient Service service;
    private transient Nitrapi api;

    public static class FirewallProtocol extends Value {
        public FirewallProtocol(String value) {
            super(value);
        }
        public static FirewallProtocol TCP = new FirewallProtocol("tcp");
        public static FirewallProtocol UDP = new FirewallProtocol("udp");
        public static FirewallProtocol ICMP = new FirewallProtocol("icmp");
        public static FirewallProtocol ANY = new FirewallProtocol("any");
    }

    private Boolean enabled;
    private FirewallRule[] rules;

    /**
     * This class represents a FirewallRule.
     */
    public class FirewallRule {
        @SerializedName("target_port")
        private Integer targetPort;
        @SerializedName("target_ip")
        private String targetIp;
        @SerializedName("protocol")
        private FirewallProtocol firewallProtocol;
        private Integer number;
        private String comment;
        @SerializedName("source_ip")
        private String sourceIp;

        /**
         * Returns targetPort.
         *
         * @return targetPort
         */
        @Nullable
        public Integer getTargetPort() {
            return targetPort;
        }

        /**
         * Returns targetIp.
         *
         * @return targetIp
         */
        @Nullable
        public String getTargetIp() {
            return targetIp;
        }

        /**
         * Returns firewallProtocol.
         *
         * @return firewallProtocol
         */
        @Nullable
        public FirewallProtocol getFirewallProtocol() {
            return firewallProtocol;
        }

        /**
         * Returns number.
         *
         * @return number
         */
        @Nullable
        public Integer getNumber() {
            return number;
        }

        /**
         * Returns comment.
         *
         * @return comment
         */
        @Nullable
        public String getComment() {
            return comment;
        }

        /**
         * Returns sourceIp.
         *
         * @return sourceIp
         */
        @Nullable
        public String getSourceIp() {
            return sourceIp;
        }
    }

    /**
     * Used internally.
     */
    public void init(Service service, Nitrapi api) {
        this.service = service;
        this.api = api;
    }

    /**
     * Returns enabled.
     *
     * @return enabled
     */
    @Nullable
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns rules.
     *
     * @return rules
     */
    @Nullable
    public FirewallRule[] getRules() {
        return rules;
    }

    /**
     * Deletes a specific rule by number
     *
     * @param number number
     */
    public void deleteRule(Integer number) throws NitrapiException {
        api.dataDelete("services/" + service.getId() + "/cloud_servers/firewall/remove", new Parameter[] {
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
     * @param sourceIp sourceIp
     * @param targetIp targetIp
     * @param targetPort targetPort
     * @param firewallProtocol firewallProtocol
     * @param comment comment
     */
    public void addRule(String sourceIp, String targetIp, Integer targetPort, FirewallProtocol firewallProtocol, String comment) throws NitrapiException {
        api.dataPost("services/" + service.getId() + "/cloud_servers/firewall/add", new Parameter[] {
            new Parameter("source_ip", sourceIp),
            new Parameter("target_ip", targetIp),
            new Parameter("target_port", targetPort),
            new Parameter("protocol", firewallProtocol),
            new Parameter("comment", comment)
        });
    }
}
