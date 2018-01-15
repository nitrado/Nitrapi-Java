package net.nitrado.api.services;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents log entries for a gameserver that are split up in pages. <p> Use Gameserver.getLogs(page) to get a certain page
 */
public class Logs {

    @SerializedName("current_page")
    private Integer currentPage;
    @SerializedName("logs_per_page")
    private Integer logsPerPage;
    @SerializedName("page_count")
    private Integer pageCount;
    @SerializedName("log_count")
    private Integer logCount;
    private LogEntry[] logs;

    /**
     * This class represents an entry in the server log.
     */
    public class LogEntry {
        private String user;
        private String category;
        private String message;
        @SerializedName("created_at")
        private GregorianCalendar createdAt;
        private Boolean admin;
        private String ip;

        /**
         * Returns the user.
         *
         * @return the user
         */
        @Nullable
        public String getUser() {
            return user;
        }

        /**
         * Returns the category.
         *
         * @return the category
         */
        @Nullable
        public String getCategory() {
            return category;
        }

        /**
         * Returns the message.
         *
         * @return the message
         */
        @Nullable
        public String getMessage() {
            return message;
        }

        /**
         * Returns the date this entry was created.
         *
         * @return the date this entry was created
         */
        @Nullable
        public GregorianCalendar getCreatedAt() {
            return createdAt;
        }

        /**
         * Returns admin.
         *
         * @return admin
         */
        @Nullable
        public Boolean isAdmin() {
            return admin;
        }

        /**
         * Returns ip.
         *
         * @return ip
         */
        @Nullable
        public String getIp() {
            return ip;
        }
    }

    /**
     * Returns the number of the current page.
     *
     * @return the number of the current page
     */
    @Nullable
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * Returns the number of logs on a page.
     *
     * @return the number of logs on a page
     */
    @Nullable
    public Integer getLogsPerPage() {
        return logsPerPage;
    }

    /**
     * Returns the number of pages in this logs.
     *
     * @return the number of pages in this logs
     */
    @Nullable
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * Returns the total number of log entries.
     *
     * @return the total number of log entries
     */
    @Nullable
    public Integer getLogCount() {
        return logCount;
    }

    /**
     * Returns the log entries on the current page.
     *
     * @return the log entries on the current page
     */
    @Nullable
    public LogEntry[] getLogs() {
        return logs;
    }
}
