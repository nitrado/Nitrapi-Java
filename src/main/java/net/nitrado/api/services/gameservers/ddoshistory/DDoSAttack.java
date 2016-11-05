package net.nitrado.api.services.gameservers.ddoshistory;

import com.google.gson.annotations.SerializedName;

import java.util.GregorianCalendar;

/**
 * Statistic of a single DDoS-attack.
 */
public class DDoSAttack {
    private int id;
    @SerializedName("started_at")
    private GregorianCalendar startedAt;
    @SerializedName("ended_at")
    private GregorianCalendar endedAt;
    @SerializedName("attack_type")
    private String attackType;
    private String ip;
    private String server;
    private int pps;
    private int bandwidth;
    private int duration;
    private DDoSStat[] data;

    /**
     * Returns the id of this DDoS-attack.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the start date of this DDoS-attack.
     *
     * @return the start date
     */
    public GregorianCalendar getStartedAt() {
        return startedAt;
    }

    /**
     * Returns the end date of this DDoS-attack.
     *
     * @return the end date
     */
    public GregorianCalendar getEndedAt() {
        return endedAt;
    }

    /**
     * Returns the attack type of this DDoS-attack.
     *
     * @return the attack type
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * Returns the ip that was attacked.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Returns the name of the server that was attacked.
     *
     * @return the name of the server
     */
    public String getServer() {
        return server;
    }

    /**
     * Returns the average packets-per-second of this DDoS-attack.
     *
     * @return the averate packets-per-second
     */
    public int getPps() {
        return pps;
    }

    /**
     * Returns the average bandwidth of this DDoS-attack.
     *
     * @return the average bandwidth
     */
    public int getBandwidth() {
        return bandwidth;
    }

    /**
     * Returns the duration of this DDoS-attack.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns detailed statistics of this DDoS-attack.
     *
     * @return a list of data points
     */
    public DDoSStat[] getData() {
        return data;
    }
}

