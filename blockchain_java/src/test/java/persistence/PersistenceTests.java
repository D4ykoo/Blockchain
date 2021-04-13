package persistence;

import data_model.Block;
import data_model.Chain;
import data_model.Transaction;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersistenceTests {
    @Test
    public void testWriteChainMultipleTimes_WithTwoBlocks_FirstBlockOnlyWrittenOnce( )
    {
        Transaction t = new Transaction( "sender".getBytes(StandardCharsets.UTF_8), "receiver".getBytes(StandardCharsets.UTF_8), 1.5, 0, 0.0000005, 0.000001 );
        Transaction t1 = new Transaction( "fdjkngliuabglbaubf".getBytes(StandardCharsets.UTF_8), "fahgibgafadbvbahvbab".getBytes(StandardCharsets.UTF_8), 1.5, 0, 0.0000005,
                0.000001 );
        Transaction t2 = new Transaction( "sender".getBytes(StandardCharsets.UTF_8), "aslkjdfhalbu fviueabuhbiuvbfvbav".getBytes(StandardCharsets.UTF_8), 1.5, 0, 0.0000005,
                0.000001 );

        List<Transaction> transactionList = new ArrayList<>( );
        transactionList.add( t );
        transactionList.add( t1 );
        transactionList.add( t2 );

        Block block1 = new Block( transactionList, new byte[0] );

        Chain chain = new Chain( 1 );
        chain.add( block1 );

        Persistence persistence = new Persistence( );
        persistence.writeChain( chain );

        Block block2 = new Block( transactionList, block1.getBlockHash( ) );
        chain.add( block2 );
        persistence.writeChain( chain );
    }
}
