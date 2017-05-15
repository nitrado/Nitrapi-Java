package net.nitrado.api.services.webspaces;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.nitrado.api.services.Service;

/**
 * This class represents a webspace.
 */
public class Webspace extends Service {

    private WebspaceInfo info;

    class Domain {
        private String domain;
        private String expires;
        @SerializedName("paid_until")
        private String paidUntil;
        private String status; // TODO enum?
        public String toString() {
            return domain;
        }

        public String getDomain() {
            return domain;
        }

        public String getExpires() {
            return expires;
        }

        public String getPaidUntil() {
            return paidUntil;
        }

        public String getStatus() {
            return status;
        }
    }

    class WebspaceInfo {
        private String name;
        private Quota quota;
        private Domain[] domains;




        class Quota {
            @SerializedName("max_space")
            private int maxSpace;
            @SerializedName("max_domains")
            private int maxDomains;
            @SerializedName("max_mail_accounts")
            private int maxMailAccounts;
            @SerializedName("max_ftp_accounts")
            private int maxFtpAccounts;
            @SerializedName("max_databases")
            private int maxDatabases;
        }
    }

    public String getName() {
        return info!=null?info.name:"";
    }

    public int getMaxSpace() {
        if (info == null || info.quota == null) {
            return 0;
        }
        return info.quota.maxSpace;
    }

    public int getMaxDomains() {
        if (info == null || info.quota == null) {
            return 0;
        }
        return info.quota.maxDomains;
    }

    public int getMaxMailAccounts() {
        if (info == null || info.quota == null) {
            return 0;
        }
        return info.quota.maxMailAccounts;
    }

    public int getMaxFtpAccounts() {
        if (info == null || info.quota == null) {
            return 0;
        }
        return info.quota.maxFtpAccounts;
    }

    public int getMaxDatabases() {
        if (info == null || info.quota == null) {
            return 0;
        }
        return info.quota.maxDatabases;
    }

    public Domain[] getDomains() {
        return info!=null?info.domains:new Domain[0];
    }


    @Override
    public void refresh() {
        JsonObject data = api.dataGet("services/" + getId() + "/webspaces", null);
        WebspaceInfo infos = api.fromJson(data.get("webspace"), WebspaceInfo.class);
        this.info = infos;
    }
}
