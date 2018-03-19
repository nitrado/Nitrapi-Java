package net.nitrado.api.services.gameservers.ddoshistory;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a DDoSAttack.
 */
public class DDoSAttack {

    private Integer id;
    @SerializedName("started_at")
    private GregorianCalendar startedAt;
    @SerializedName("ended_at")
    private GregorianCalendar endedAt;
    @SerializedName("attack_type")
    private String attackType;
    private String ip;
    private String server;
    private Integer pps;
    private Integer bandwidth;
    private Integer duration;
    private DDoSStat[] data;

    /**
     * Returns the id of this DDoS-attack.
     *
     * @return the id of this DDoS-attack
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    /**
     * Returns the start date of this DDoS-attack.
     *
     * @return the start date of this DDoS-attack
     */
    @Nullable
    public GregorianCalendar getStartedAt() {
        return startedAt;
    }

    /**
     * Returns the end date of this DDoS-attack.
     *
     * @return the end date of this DDoS-attack
     */
    @Nullable
    public GregorianCalendar getEndedAt() {
        return endedAt;
    }

    /**
     * Returns the attack type  of this DDoS-attack.
     *
     * @return the attack type  of this DDoS-attack
     */
    @Nullable
    public String getAttackType() {
        return attackType;
    }

    /**
     * Returns the ip that was attacked.
     *
     * @return the ip that was attacked
     */
    @Nullable
    public String getIp() {
        return ip;
    }

    /**
     * Returns the name of the server that was attacked.
     *
     * @return the name of the server that was attacked
     */
    @Nullable
    public String getServer() {
        return server;
    }

    /**
     * Returns the average packets-per-second of this DDoS-attack.
     *
     * @return the average packets-per-second of this DDoS-attack
     */
    @Nullable
    public Integer getPps() {
        return pps;
    }

    /**
     * Returns the average bandwidth of this DDoS-attack.
     *
     * @return the average bandwidth of this DDoS-attack
     */
    @Nullable
    public Integer getBandwidth() {
        return bandwidth;
    }

    /**
     * Returns the duration of this DDoS-attack.
     *
     * @return the duration of this DDoS-attack
     */
    @Nullable
    public Integer getDuration() {
        return duration;
    }

    /**
     * Returns datailed statistics of this DDoS-attack.
     *
     * @return datailed statistics of this DDoS-attack
     */
    @Nullable
    public DDoSStat[] getData() {
        return data;
    }
}
