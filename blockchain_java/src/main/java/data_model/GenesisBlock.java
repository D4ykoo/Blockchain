/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package data_model;

public class GenesisBlock extends Block{
    public static byte[] ZERO_HASH = new byte[32];

    // therefore two constructors in Block.java
    public GenesisBlock(){
        super(ZERO_HASH);
    }
}
