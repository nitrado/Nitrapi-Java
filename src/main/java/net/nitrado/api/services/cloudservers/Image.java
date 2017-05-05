package net.nitrado.api.services.cloudservers;

public class Image {
    private int id;
    private String name;
    private boolean daemon;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasDaemonSupport() {
        return daemon;
    }
}
