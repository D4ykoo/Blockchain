/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package data_model;

import helpers.SHA3Helper;

import java.io.Serializable;

public class BlockHeader implements Serializable {

    // Blockheader -> 80 Byte
    private int version; // 4 Byte
    private long timeStamp; // 8 Byte
    private byte[] previousHash; // 32 Byte
    private byte[] transactionList; // 32 Byte
    private int nonce = 0; // 4 Byte

    public BlockHeader(){

    }

    public BlockHeader(long timeStamp, byte[] previousHash, byte[] transactionList) {
        this.version = 1;
        this.timeStamp = timeStamp;
        this.previousHash = previousHash;
        this.transactionList = transactionList;
    }

    public byte[] asHash() {
        return SHA3Helper.hash256(this);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public byte[] getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(byte[] previousHash) {
        this.previousHash = previousHash;
    }

    public byte[] getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(byte[] transactionList) {
        this.transactionList = transactionList;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public void incrementNonce() throws Exception {
        if (this.nonce == Integer.MAX_VALUE) {
            throw new Exception("Maximum of nonce reached");
        }
        this.nonce++;
    }

}
