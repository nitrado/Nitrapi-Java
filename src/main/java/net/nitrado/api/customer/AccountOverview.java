package net.nitrado.api.customer;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;


/**
 * This class represents an AccountOverview.
 */
public class AccountOverview {

    public static class Type extends Value {
        public Type(String value) {
            super(value);
        }
        public static Type INCREASE = new Type("increase");
        public static Type DECREASE = new Type("decrease");
    }

    private GregorianCalendar from;
    private GregorianCalendar end;
    private Payment[] payments;

    /**
     * This class represents a donation.
     */
    public class Donation {
        @SerializedName("sender_user_id")
        private int senderUserId;
        @SerializedName("receiver_user_id")
        private int receiverUserId;
        private String subject;

        /**
         * Returns senderUserId.
         *
         * @return senderUserId
         */
        public int getSenderUserId() {
            return senderUserId;
        }

        /**
         * Returns receiverUserId.
         *
         * @return receiverUserId
         */
        public int getReceiverUserId() {
            return receiverUserId;
        }

        /**
         * Returns subject.
         *
         * @return subject
         */
        public String getSubject() {
            return subject;
        }
    }

    /**
     * This class represents a Payment.
     */
    public class Payment {
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

        /**
         * Returns id.
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Returns invoiceId.
         *
         * @return invoiceId
         */
        public String getInvoiceId() {
            return invoiceId;
        }

        /**
         * Returns serviceId.
         *
         * @return serviceId
         */
        public int getServiceId() {
            return serviceId;
        }

        /**
         * Returns switchedServiceId.
         *
         * @return switchedServiceId
         */
        public int getSwitchedServiceId() {
            return switchedServiceId;
        }

        /**
         * Returns donation.
         *
         * @return donation
         */
        public Donation getDonation() {
            return donation;
        }

        /**
         * Returns date.
         *
         * @return date
         */
        public GregorianCalendar getDate() {
            return date;
        }

        /**
         * Returns method.
         *
         * @return method
         */
        public String getMethod() {
            return method;
        }

        /**
         * Returns duration.
         *
         * @return duration
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Returns amount.
         *
         * @return amount
         */
        public int getAmount() {
            return amount;
        }

        /**
         * Returns type.
         *
         * @return type
         */
        public Type getType() {
            return type;
        }

        /**
         * Returns currency.
         *
         * @return currency
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Returns ip.
         *
         * @return ip
         */
        public String getIp() {
            return ip;
        }

        /**
         * Returns refundable.
         *
         * @return refundable
         */
        public boolean isRefundable() {
            return refundable;
        }

        /**
         * Returns providerFee.
         *
         * @return providerFee
         */
        public int getProviderFee() {
            return providerFee;
        }

        /**
         * Returns lastStatus.
         *
         * @return lastStatus
         */
        public String getLastStatus() {
            return lastStatus;
        }
    }

    /**
     * Returns from.
     *
     * @return from
     */
    public GregorianCalendar getFrom() {
        return from;
    }

    /**
     * Returns end.
     *
     * @return end
     */
    public GregorianCalendar getEnd() {
        return end;
    }

    /**
     * Returns payments.
     *
     * @return payments
     */
    public Payment[] getPayments() {
        return payments;
    }
}
