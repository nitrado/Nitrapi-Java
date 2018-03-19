package net.nitrado.api.services.gameservers.ddoshistory;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a datapoint of a DDoS-attack statistic.
 */
public class DDoSStat {

    private GregorianCalendar datetime;
    private Integer pps;
    private Integer bandwidth;

    /**
     * Returns the date of this datapoint.
     *
     * @return the date of this datapoint
     */
    @Nullable
    public GregorianCalendar getDatetime() {
        return datetime;
    }

    /**
     * Returns the packets-per-second at this datapoint.
     *
     * @return the packets-per-second at this datapoint
     */
    @Nullable
    public Integer getPps() {
        return pps;
    }

    /**
     * Returns the bandwidth at this datapoint.
     *
     * @return the bandwidth at this datapoint
     */
    @Nullable
    public Integer getBandwidth() {
        return bandwidth;
    }
}
