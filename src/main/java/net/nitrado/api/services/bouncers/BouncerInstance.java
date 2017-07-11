package net.nitrado.api.services.bouncers;

public class BouncerInstance {
    private String ident;
    private String password;
    private String comment;
    private BouncerServer server;

    class BouncerServer {
        private String name;
        private int port;
    }


    public String getIdent() {
        return ident;
    }

    public String getPassword() {
        return password;
    }

    public String getComment() {
        return comment;
    }

    public String getServerName() {
        return server.name;
    }

    public int getServerPort() {
        return server.port;
    }
}
