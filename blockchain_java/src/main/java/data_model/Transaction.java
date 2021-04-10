package models;

import helpers.SHA3Helper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

// The hash is generated with streams, so serializable is needed
public class Transaction implements Serializable {
    private byte[] sender, receiver;
    private byte[] txId;

    private double amount;
    private double transactionFeeBasePrice;
    private double transactionFeeLimit;

    private int nonce;

    // transient: not included in hash
    private transient double transactionFee;
    private transient long timeStamp;
    private transient int sizeInByte;
    private transient byte[] blockId;

    public Transaction(byte[] sender, byte[] receiver, double amount, int nonce, double transactionFeeBasePrice, double transactionFeeLimit) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.nonce = nonce;
        this.transactionFeeBasePrice = transactionFeeBasePrice;
        this.transactionFeeLimit = transactionFeeLimit;
        this.txId = SHA3Helper.hash256(this);
    }

    // Just some getter and setter

    public void setTxId(byte[] txId) {
        this.txId = txId;
    }

    public byte[] getSender() {
        return sender;
    }

    public void setSender(byte[] sender) {
        this.sender = sender;
    }

    public byte[] getReceiver() {
        return receiver;
    }

    public void setReceiver(byte[] receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public long getReceivedTimeStamp() {
        return timeStamp;
    }

    public void setReceivedTimeStamp(long receivedTimeStamp) {
        this.timeStamp = receivedTimeStamp;
    }

    public byte[] getBlockId() {
        return blockId;
    }

    public void setBlockId(byte[] blockId) {
        this.blockId = blockId;
    }

    public double getTransactionFeeBasePrice() {
        return transactionFeeBasePrice;
    }

    public void setTransactionFeeBasePrice(double transactionFeeBasePrice) {
        this.transactionFeeBasePrice = transactionFeeBasePrice;
    }

    public double getTransactionFeeLimit() {
        return transactionFeeLimit;
    }

    public void setTransactionFeeLimit(double transactionFeeLimit) {
        this.transactionFeeLimit = transactionFeeLimit;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public int getSizeInByte() {
        return sizeInByte;
    }

    public void setSizeInByte(int sizeInByte) {
        this.sizeInByte = sizeInByte;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", nonce=" + nonce +
                ", transactionFeeBasePrice=" + transactionFeeBasePrice +
                ", transactionFeeLimit=" + transactionFeeLimit +
                '}';
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                nonce == that.nonce &&
                Double.compare(that.transactionFeeBasePrice, transactionFeeBasePrice) == 0 &&
                Double.compare(that.transactionFeeLimit, transactionFeeLimit) == 0 &&
                Arrays.equals(txId, that.txId) &&
                Arrays.equals(sender, that.sender) &&
                Arrays.equals(receiver, that.receiver);
    }
*/
    /*
    @Override
    public int hashCode() {
        int result = Objects.hash(amount, nonce, transactionFeeBasePrice, transactionFeeLimit);
        result = 31 * result + Arrays.hashCode(txId);
        result = 31 * result + Arrays.hashCode(sender);
        result = 31 * result + Arrays.hashCode(receiver);
        return result;
    }
*/
}
