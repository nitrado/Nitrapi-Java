package net.nitrado.api.services.gameservers.minecraft;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.Value;

/**
 * Settings of BungeeCord.
 */
public class BungeeCord {
    private boolean enabled;
    private boolean only;

    public static class FirewallStatus extends Value {
        public FirewallStatus(String value) {
            super(value);
        }
        /**
         * Firewall is deactivated.
         */
        public static final FirewallStatus OFF = new FirewallStatus("off");
        /**
         * Firewall only allows its ip address.
         */
        public static final FirewallStatus ON_SELF = new FirewallStatus("on_self");
        /**
         * Firewall only allows the ip address returned by getFirewallIp()
         */
        public static final FirewallStatus ON_IP= new FirewallStatus("on_ip");
    }

    private FirewallStatus firewall;
    @SerializedName("firewall_ip")
    private String firewallIp;

    /**
     * Returns true if BungeeCord is enabled.
     *
     * @return true if BungeeCord is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns true if only BungeeCord and not the gameserver itself is started.
     *
     * @return true if only BungeeCord and not the gameserver itself is started
     */
    public boolean isOnly() {
        return only;
    }

    /**
     * Returns the type of firewall.
     *
     * @return the type of the firewall
     */
    public FirewallStatus getFirewall() {
        return firewall;
    }

    /**
     * Returns the ip allowed by the firewall.
     *
     * @return the ip
     */
    public String getFirewallIp() {
        return firewallIp;
    }
}
