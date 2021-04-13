/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */
package threads;

import data_model.Block;

public interface MinerListener {
    void notifyNewBlock(Block block);
}
