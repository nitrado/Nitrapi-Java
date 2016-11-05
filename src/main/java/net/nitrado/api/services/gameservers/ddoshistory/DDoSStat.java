package net.nitrado.api.services.gameservers.ddoshistory;

import java.util.GregorianCalendar;

/**
 * Datapoint of a DDoS-attack statistic.
 */
public class DDoSStat {
    private GregorianCalendar datetime;
    private int pps;
    private int bandwidth;

    /**
     * Returns the date of this datapoint.
     *
     * @return the date
     */
    public GregorianCalendar getDatetime() {
        return datetime;
    }

    /**
     * Returns the packets-per-second at this datapoint.
     *
     * @return the number of packets-per-second
     */
    public int getPps() {
        return pps;
    }

    /**
     * Returns the bandwidth at this datapoint.
     *
     * @return the bandwidth
     */
    public int getBandwidth() {
        return bandwidth;
    }
}
