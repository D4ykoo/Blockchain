/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package data_model;

import java.util.ArrayList;
import java.util.List;

public class Chain {
    private List<Block> chain = new ArrayList<>( );

    private int networkID;

    public Chain( int networkID)
    {
        this.networkID = networkID;
        chain.add( new GenesisBlock( ) );
    }

    public void add( Block block )
    {
        chain.add( block );
    }

    public Block get( int index )
    {
        return chain.get( index );
    }

    public Block getLast( )
    {
        return chain.get( chain.size( ) - 1 );
    }

    public int size( )
    {
        return chain.size( );
    }

    public List<Block> getChain( )
    {
        return chain;
    }

    public int getNetworkID( )
    {
        return networkID;
    }

    public void setNetworkID(int networkID)
    {
        this.networkID = networkID;
    }

    @Override public String toString( )
    {
        return "Chain{" +
                "chain=" + chain +
                ", networkId=" + networkID +
                '}';
    }

}
