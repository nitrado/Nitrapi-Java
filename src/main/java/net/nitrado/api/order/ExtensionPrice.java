package net.nitrado.api.order;

public class ExtensionPrice {
    int rentalTime;
    int price;

    public ExtensionPrice(int rentalTime, int price) {
        this.rentalTime = rentalTime;
        this.price = price;
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public int getPrice() {
        return price;
    }
}
