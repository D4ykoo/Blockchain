/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */
package logic;

public class DependencyManager {
    private static PendingTransactions pendingTransactions;

    public static PendingTransactions getPendingTransactions() {
        if (pendingTransactions == null) {
            pendingTransactions = new PendingTransactions();
        }

        return pendingTransactions;
    }

    public static void injectPendingTransactions(PendingTransactions pendingTransactions) {
        DependencyManager.pendingTransactions = pendingTransactions;
    }

    private static Blockchain blockchain;

    public static Blockchain getBlockchain() {
        if (blockchain == null) {
            blockchain = new Blockchain();
        }

        return blockchain;
    }

    public static void injectBlockchain(Blockchain blockchain) {
        DependencyManager.blockchain = blockchain;
    }
}