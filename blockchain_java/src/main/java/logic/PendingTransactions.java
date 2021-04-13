/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package logic;

import data_model.Block;
import data_model.Transaction;
import helpers.SizeHelper;
import helpers.TransactionComparatorByFee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PendingTransactions {
    private PriorityQueue<Transaction> pendingTransactions;


    public PendingTransactions() {
        Comparator<Transaction> comparator = new TransactionComparatorByFee();
        pendingTransactions = new PriorityQueue<>(11, comparator);
    }

    public PendingTransactions(Comparator<Transaction> comparator) {
        pendingTransactions = new PriorityQueue<>(11, comparator);
    }


    public void addPendingTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    // list of transactions for the next block
    public List<Transaction> getTransactionsForNextBlock() {
        List<Transaction> nextTransactions = new ArrayList<>();

        int transactionCapacity = SizeHelper.calculateTransactionCapacity();

        PriorityQueue<Transaction> temp = new PriorityQueue<>(pendingTransactions);
        while (transactionCapacity > 0 && !temp.isEmpty()) {
            nextTransactions.add(temp.poll());
            transactionCapacity--;
        }

        return nextTransactions;
    }

    public void clearPendingTransactions(Block block) {
        clearPendingTransactions(block.getTransactions());
    }

    public void clearPendingTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            pendingTransactions.remove(transaction);
        }
    }

    public void clearPendingTransaction(Transaction transaction) {
        pendingTransactions.remove(transaction);
    }

    public boolean pendingTransactionsAvailable() {
        return !pendingTransactions.isEmpty();
    }
}
