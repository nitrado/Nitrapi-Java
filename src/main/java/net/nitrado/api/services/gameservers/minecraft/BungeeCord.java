package net.nitrado.api.services.gameservers.minecraft;

import com.google.gson.annotations.SerializedName;

/**
 * Settings of BungeeCord.
 */
public class BungeeCord {
    private boolean enabled;
    private boolean only;

    public enum FirewallStatus {
        /**
         * Firewall is deactivated.
         */
        @SerializedName("off")
        OFF,
        /**
         * Firewall only allows its ip address.
         */
        @SerializedName("on_self")
        ON_SELF,
        /**
         * Firewall only allows the ip address returned by getFirewallIp()
         */
        @SerializedName("on_ip")
        ON_IP;

        @Override
        public String toString() {
            try {
                return FirewallStatus.class.getDeclaredField(super.toString()).getAnnotation(SerializedName.class).value();
            } catch (NoSuchFieldException e) {
                // should not happen
                return super.toString();
            }
        }
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
