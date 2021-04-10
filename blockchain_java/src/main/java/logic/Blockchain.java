/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package logic;

import data_model.Block;
import data_model.Chain;

import java.math.BigInteger;

public class Blockchain {
    public final static int MAX_BLOCK_SIZE_IN_BYTES = 1120;

    public final static int NETWORK_ID = 1;

    private BigInteger difficulty;

    private Chain chain;

    public Blockchain() {
        this.chain = new Chain(NETWORK_ID);
        this.difficulty = new BigInteger("16384");
    }

    public void addBlock(Block block) {
        chain.add(block);
    }

    public boolean fulfillsDifficulty(byte[] digest) {
        BigInteger temp = new BigInteger(digest);

        return temp.compareTo(difficulty) <= 0;
    }

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigInteger difficulty) {
        this.difficulty = difficulty;
    }

    public byte[] getPreviousHash() {
        return chain.getLast().getBlockHash();
    }

    public int size() {
        return chain.size();
    }
}

