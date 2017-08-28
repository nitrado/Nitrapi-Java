package net.nitrado.api.customer;

import com.google.gson.annotations.SerializedName;
import net.nitrado.api.common.Value;

import java.util.GregorianCalendar;

public class AccountOverview {
    private GregorianCalendar from;
    private GregorianCalendar end;
    private Payment[] payments;


    public static class Payment {
        public static class Type extends Value {
            public Type(String value) {
                super(value);
            }

            public static final Type INCREASE = new Type("increase");
            public static final Type DECREASE = new Type("decrease");
        }

        private int id;
        @SerializedName("invoice_id")
        private String invoiceId;
        @SerializedName("service_id")
        private int serviceId;
        @SerializedName("switched_service_id")
        private int switchedServiceId;
        private Donation donation;
        private GregorianCalendar date;
        private String method;
        private int duration;
        private int amount;
        private Type type;
        private String currency;
        private String ip;
        private boolean refundable;
        @SerializedName("provider_fee")
        private int providerFee;
        @SerializedName("last_status")
        private String lastStatus;

        public int getId() {
            return id;
        }

        public String getInvoiceId() {
            return invoiceId;
        }


        public int getServiceId() {
            return serviceId;
        }

        public int getSwitchedServiceId() {
            return switchedServiceId;
        }

        public Donation getDonation() {
            return donation;
        }

        public GregorianCalendar getDate() {
            return date;
        }

        public String getMethod() {
            return method;
        }

        public int getDuration() {
            return duration;
        }

        public int getAmount() {
            return amount;
        }

        public Type getType() {
            return type;
        }

        public String getCurrency() {
            return currency;
        }

        public String getIp() {
            return ip;
        }

        public boolean isRefundable() {
            return refundable;
        }

        public int getProviderFee() {
            return providerFee;
        }

        public String getLastStatus() {
            return lastStatus;
        }
    }

    public class Donation {
        @SerializedName("sender_user_id")
        private int senderUserId;
        @SerializedName("receiver_user_id")
        private int receiverUserId;
        @SerializedName("subject")
        private String subject;

        public int getSenderUserId() {
            return senderUserId;
        }

        public int getReceiverUserId() {
            return receiverUserId;
        }

        public String getSubject() {
            return subject;
        }
    }

    public GregorianCalendar getFrom() {
        return from;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public Payment[] getPayments() {
        return payments;
    }
}
