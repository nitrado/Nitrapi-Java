package net.nitrado.api.services.webspaces;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.common.Value;
import net.nitrado.api.services.Service;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Webspace.
 */
public class Webspace extends Service {
    private class WebspaceData {
        private String name;
        private Quota quota;
        private Domain[] domains;
    }

    private WebspaceData data;

    /**
     * This class represents a quota.
     */
    public class Quota {
        @SerializedName("max_space")
        private Integer maxSpace;
        @SerializedName("max_domains")
        private Integer maxDomains;
        @SerializedName("max_mail_accounts")
        private Integer maxMailAccounts;
        @SerializedName("max_ftp_accounts")
        private Integer maxFtpAccounts;
        @SerializedName("max_databases")
        private Integer maxDatabases;

        /**
         * Returns maxSpace.
         *
         * @return maxSpace
         */
        @Nullable
        public Integer getMaxSpace() {
            return maxSpace;
        }

        /**
         * Returns maxDomains.
         *
         * @return maxDomains
         */
        @Nullable
        public Integer getMaxDomains() {
            return maxDomains;
        }

        /**
         * Returns maxMailAccounts.
         *
         * @return maxMailAccounts
         */
        @Nullable
        public Integer getMaxMailAccounts() {
            return maxMailAccounts;
        }

        /**
         * Returns maxFtpAccounts.
         *
         * @return maxFtpAccounts
         */
        @Nullable
        public Integer getMaxFtpAccounts() {
            return maxFtpAccounts;
        }

        /**
         * Returns maxDatabases.
         *
         * @return maxDatabases
         */
        @Nullable
        public Integer getMaxDatabases() {
            return maxDatabases;
        }
    }

    /**
     * This class represents a domain.
     */
    public class Domain {
        private String domain;
        private String expires;
        @SerializedName("paid_until")
        private String paidUntil;
        private String status;

        /**
         * Returns domain.
         *
         * @return domain
         */
        @Nullable
        public String getDomain() {
            return domain;
        }

        /**
         * Returns expires.
         *
         * @return expires
         */
        @Nullable
        public String getExpires() {
            return expires;
        }

        /**
         * Returns paidUntil.
         *
         * @return paidUntil
         */
        @Nullable
        public String getPaidUntil() {
            return paidUntil;
        }

        /**
         * Returns status.
         *
         * @return status
         */
        @Nullable
        public String getStatus() {
            return status;
        }
    }

    /**
     * Returns name.
     *
     * @return name
     */
    @Nullable
    public String getName() {
        if(data == null) { return null; }
        return data.name;
    }

    /**
     * Returns quota.
     *
     * @return quota
     */
    @Nullable
    public Quota getQuota() {
        if(data == null) { return null; }
        return data.quota;
    }

    /**
     * Returns domains.
     *
     * @return domains
     */
    @Nullable
    public Domain[] getDomains() {
        if(data == null) { return null; }
        return data.domains;
    }
    @Override
    public void refresh() throws NitrapiException {
        JsonObject data = api.dataGet("services/" + getId() + "/webspaces", null);
        WebspaceData datas = api.fromJson(data.get("webspace"), WebspaceData.class);
        this.data = datas;
    }
}
