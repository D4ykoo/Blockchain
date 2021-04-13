/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */
package threads;

import data_model.Block;
import data_model.Transaction;
import helpers.SHA3Helper;
import logic.DependencyManager;
import logic.PendingTransactions;
import org.junit.Before;
import org.junit.Test;

public class MinerTests implements MinerListener {
    // generate pending transactions
    @Before
    public void setUp() {
        PendingTransactions transactions = DependencyManager.getPendingTransactions();

        for (int i = 0; i < 100; i++) {
            String sender = "sender" + i;
            String receiver = "receiver" + i;
            double amount = i * 1.1;
            int nonce = i;
            double transactionFee = 0.00001 * i;
            double transactionFeeLimit = 10.0;

            transactions.addPendingTransaction(
                    new Transaction(sender.getBytes(),
                            receiver.getBytes(),
                            amount,
                            nonce,
                            transactionFee,
                            transactionFeeLimit));
        }
    }

    @Test
    public void testMiner() {
        Miner miner = new Miner();
        miner.registerListener(this);

        Thread thread = new Thread(miner);
        thread.start();

        while (DependencyManager.getPendingTransactions().pendingTransactionsAvailable()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        miner.stopMining();

        System.out.println("Länge der Blockchain: " + DependencyManager.getBlockchain().size());
    }

    @Override
    public void notifyNewBlock(Block block) {
        System.out.println("new block mined");

        System.out.println(SHA3Helper.digestToHex(block.getBlockHash()));
    }
}
