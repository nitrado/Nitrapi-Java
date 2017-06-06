package net.nitrado.api.services;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.services.gameservers.Gameserver;

import java.util.GregorianCalendar;

/**
 * This class represents log entries for a gameserver that are split up in pages.
 * <p>
 * Use Gameserver.getLogs(page) to get a certain page
 * @see Gameserver#getLogs(int)
 */
public class Logs {
    /**
     * This class represents an entry in the server log.
     */
    public class LogEntry {
        private String user;
        private String category;
        private String message;
        @SerializedName("created_at")
        private GregorianCalendar createdAt;
        private String ip;
        @SerializedName("admin")
        private boolean isAdmin;

        /**
         * Returns the user.
         *
         * @return the user
         */
        public String getUser() {
            return user;
        }

        /**
         * Returns the category.
         *
         * @return the category
         */
        public String getCategory() {
            return category;
        }

        /**
         * Returns the message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Returns the date this entry was created.
         *
         * @return the creation date
         */
        public GregorianCalendar getCreatedAt() {
            return createdAt;
        }


        /**
         * Returns admin.
         *
         * @return admin
         */
        public boolean isAdmin() {
            return isAdmin;
        }

        /**
         * Returns ip.
         *
         * @return ip
         */
        public String getIp() {
            return ip;
        }
    }

    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("logs_per_page")
    private int logsPerPage;
    @SerializedName("page_count")
    private int pageCount;
    @SerializedName("log_count")
    private int logCount;
    private LogEntry[] logs;

    /**
     * Returns the number of the current page.
     *
     * @return the number of the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Returns the number of logs on a page.
     *
     * @return the number of logs
     */
    public int getLogsPerPage() {
        return logsPerPage;
    }

    /**
     * Returns the number of pages in this logs.
     *
     * @return the number of pages
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Returns the total number of log entries.
     *
     * @return the total number of log entries
     */
    public int getLogCount() {
        return logCount;
    }

    /**
     * Returns the log entries on the current page.
     *
     * @return a list of log entries
     */
    public LogEntry[] getLogs() {
        return logs;
    }
}
