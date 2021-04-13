/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */
package threads;

import data_model.Block;
import data_model.Transaction;
import logic.Blockchain;
import logic.DependencyManager;
import logic.PendingTransactions;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Miner implements Runnable {
    private final static Logger logger = Logger.getLogger(Miner.class);

    private List<MinerListener> listeners = new ArrayList<>();

    private boolean mining = true;

    private boolean cancelBlock = false;

    private Block block;

    @Override
    public void run() {
        logger.info("Miner started");

        // increments until the difficulty is fulfilled
        while (isMining()) {
            block = getNewBlockForMining();

            while (!cancelBlock && doesNotFulfillDifficulty(block.getBlockHash())) {
                try {
                    logger.info("incrementing nonce");
                    block.incrementNonce();
                } catch (Exception e) {
                    restartMining();
                }
            }

            if (cancelBlock) {
                block = null;
                cancelBlock = false;
            } else {
                blockMined(block);
            }
        }
    }

    private Block getNewBlockForMining() {
        logger.info("retrieving new block");

        PendingTransactions pendingTransactions = DependencyManager.getPendingTransactions();
        Blockchain blockchain = DependencyManager.getBlockchain();
        List<Transaction> transactions = pendingTransactions.getTransactionsForNextBlock();

        return new Block(transactions, blockchain.getPreviousHash());
    }

    private boolean doesNotFulfillDifficulty(byte[] digest) {
        logger.info("does not fulfill difficulty");

        Blockchain blockchain = DependencyManager.getBlockchain();
        return !blockchain.fulfillsDifficulty(digest);
    }

    private void restartMining() {
        PendingTransactions pendingTransactions = DependencyManager.getPendingTransactions();
        List<Transaction> transactions = pendingTransactions.getTransactionsForNextBlock();

        block.setTransactions(transactions);
    }

    private void blockMined(Block block) {
        logger.info("block mined");

        DependencyManager.getBlockchain().addBlock(block);
        DependencyManager.getPendingTransactions().clearPendingTransactions(block);

        for (MinerListener listener : listeners) {
            listener.notifyNewBlock(block);
        }
    }

    public boolean isMining() {
        return mining;
    }

    public void setMining(boolean mining) {
        this.mining = mining;
    }

    public void stopMining() {
        logger.info("stopping mining");

        this.mining = false;
    }

    public void setCancelBlock(boolean cancelBlock) {
        logger.info("canceling block");

        this.cancelBlock = cancelBlock;
    }

    public void registerListener(MinerListener listener) {
        listeners.add(listener);
    }
}