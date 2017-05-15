package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.DimensionPricing;

public class Gameserver extends DimensionPricing {
    public Gameserver(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "gameserver";
        additionals.put("game", "sevendtd");
    }

    /**
     *
     * @param game folder short
     */
    public void setGame(String game) {
        // TODO: additionals
        additionals.put("game", game);
    }
}
