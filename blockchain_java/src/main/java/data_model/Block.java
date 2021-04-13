/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package data_model;

import helpers.SHA3Helper;
import org.bouncycastle.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int magicNumber = 0xD9B4BEF9;
    private int blockSize;

    private BlockHeader blockHeader;

    private List<Transaction> transactionList;
    private int transactionCount;

    public Block(){

    }

    public Block(byte[] previousHash) {
        this.blockSize = 92; // must be bigger if the transaction count increments
        this.transactionList = new ArrayList<>();
        this.transactionCount = this.transactionList.size();
        this.blockHeader = new BlockHeader(System.currentTimeMillis(), previousHash, getTransactionHash());
    }

    public Block(List<Transaction> transactions, byte[] previousHash) {
        this.transactionList = transactions;
        this.transactionCount = transactions.size();
        this.blockSize = 92; // TODO: should be calculated later
        this.blockHeader = new BlockHeader(System.currentTimeMillis(), previousHash, getTransactionHash());
    }

    private byte[] getTransactionHash() {
        byte[] transactionsInBytes = new byte[0];

        for (Transaction transaction : transactionList) {
            transactionsInBytes = Arrays.concatenate(transactionsInBytes, transaction.getTxId());
        }

        return SHA3Helper.hash256(transactionsInBytes);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
        this.transactionCount++;
        this.blockHeader.setTransactionList(getTransactionHash());
        this.blockSize += 128; // must be 128 Byte because one transaction with all the attributes is 128 Bytes big
    }

    public byte[] getBlockHash() {
        return blockHeader.asHash();
    }

    public int getNonce() {
        return this.blockHeader.getNonce();
    }

    public void setNonce(int nonce) {
        this.blockHeader.setNonce(nonce);
    }

    public void incrementNonce() throws Exception {
        this.blockHeader.incrementNonce();
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactionList = transactions;
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

}
