package net.nitrado.api.order;

/**
 *
 */
public class PartRentalOption {
    private int hours;
    private Price[] prices;

    public int getHours() {
        return hours;
    }

    public Price[] getPrices() {
        return prices;
    }
}
