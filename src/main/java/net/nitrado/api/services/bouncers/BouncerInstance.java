package net.nitrado.api.services.bouncers;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a BouncerInstance.
 */
public class BouncerInstance {

    private String ident;
    private String password;
    private String comment;
    @SerializedName("server")
    private BouncerServer bouncerServer;

    /**
     * This class represents the server this bouncers is on.
     */
    public class BouncerServer {
        private String name;
        private Integer port;

        /**
         * Returns name.
         *
         * @return name
         */
        @Nullable
        public String getName() {
            return name;
        }

        /**
         * Returns port.
         *
         * @return port
         */
        @Nullable
        public Integer getPort() {
            return port;
        }
    }

    /**
     * Returns ident.
     *
     * @return ident
     */
    @Nullable
    public String getIdent() {
        return ident;
    }

    /**
     * Returns password.
     *
     * @return password
     */
    @Nullable
    public String getPassword() {
        return password;
    }

    /**
     * Returns comment.
     *
     * @return comment
     */
    @Nullable
    public String getComment() {
        return comment;
    }

    /**
     * Returns bouncerServer.
     *
     * @return bouncerServer
     */
    @Nullable
    public BouncerServer getBouncerServer() {
        return bouncerServer;
    }
}
