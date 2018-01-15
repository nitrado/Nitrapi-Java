package net.nitrado.api.customer;

import com.google.gson.annotations.SerializedName;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.nitrado.api.common.Value;
import net.nitrado.api.common.exceptions.NitrapiException;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

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
        private Integer senderUserId;
        @SerializedName("receiver_user_id")
        private Integer receiverUserId;
        private String subject;

        /**
         * Returns senderUserId.
         *
         * @return senderUserId
         */
        @Nullable
        public Integer getSenderUserId() {
            return senderUserId;
        }

        /**
         * Returns receiverUserId.
         *
         * @return receiverUserId
         */
        @Nullable
        public Integer getReceiverUserId() {
            return receiverUserId;
        }

        /**
         * Returns subject.
         *
         * @return subject
         */
        @Nullable
        public String getSubject() {
            return subject;
        }
    }

    /**
     * This class represents a Payment.
     */
    public class Payment {
        private Integer id;
        @SerializedName("invoice_id")
        private String invoiceId;
        @SerializedName("service_id")
        private Integer serviceId;
        @SerializedName("switched_service_id")
        private Integer switchedServiceId;
        private Donation donation;
        private GregorianCalendar date;
        private String method;
        private Integer duration;
        private Integer amount;
        private Type type;
        private String currency;
        private String ip;
        private Boolean refundable;
        @SerializedName("provider_fee")
        private Integer providerFee;
        @SerializedName("last_status")
        private String lastStatus;

        /**
         * Returns id.
         *
         * @return id
         */
        @Nullable
        public Integer getId() {
            return id;
        }

        /**
         * Returns invoiceId.
         *
         * @return invoiceId
         */
        @Nullable
        public String getInvoiceId() {
            return invoiceId;
        }

        /**
         * Returns serviceId.
         *
         * @return serviceId
         */
        @Nullable
        public Integer getServiceId() {
            return serviceId;
        }

        /**
         * Returns switchedServiceId.
         *
         * @return switchedServiceId
         */
        @Nullable
        public Integer getSwitchedServiceId() {
            return switchedServiceId;
        }

        /**
         * Returns donation.
         *
         * @return donation
         */
        @Nullable
        public Donation getDonation() {
            return donation;
        }

        /**
         * Returns date.
         *
         * @return date
         */
        @Nullable
        public GregorianCalendar getDate() {
            return date;
        }

        /**
         * Returns method.
         *
         * @return method
         */
        @Nullable
        public String getMethod() {
            return method;
        }

        /**
         * Returns duration.
         *
         * @return duration
         */
        @Nullable
        public Integer getDuration() {
            return duration;
        }

        /**
         * Returns amount.
         *
         * @return amount
         */
        @Nullable
        public Integer getAmount() {
            return amount;
        }

        /**
         * Returns type.
         *
         * @return type
         */
        @Nullable
        public Type getType() {
            return type;
        }

        /**
         * Returns currency.
         *
         * @return currency
         */
        @Nullable
        public String getCurrency() {
            return currency;
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

        /**
         * Returns refundable.
         *
         * @return refundable
         */
        @Nullable
        public Boolean isRefundable() {
            return refundable;
        }

        /**
         * Returns providerFee.
         *
         * @return providerFee
         */
        @Nullable
        public Integer getProviderFee() {
            return providerFee;
        }

        /**
         * Returns lastStatus.
         *
         * @return lastStatus
         */
        @Nullable
        public String getLastStatus() {
            return lastStatus;
        }
    }

    /**
     * Returns from.
     *
     * @return from
     */
    @Nullable
    public GregorianCalendar getFrom() {
        return from;
    }

    /**
     * Returns end.
     *
     * @return end
     */
    @Nullable
    public GregorianCalendar getEnd() {
        return end;
    }

    /**
     * Returns payments.
     *
     * @return payments
     */
    @Nullable
    public Payment[] getPayments() {
        return payments;
    }
}
