/*
 * Code is in style of the book: Blockchain für Entwickler by Tobias Fertig, 2018
 * It is just a little bit changed for the purposes of demonstrating the blockchain-technology by Dario Köllner, 2021
 * */

package helpers;

import data_model.Transaction;
import java.util.Comparator;

public class TransactionComparatorByFee implements Comparator<Transaction>{
    @Override
    public int compare(Transaction o1, Transaction o2 )
    {
        int result = 0;

        if ( o2.getTransactionFeeBasePrice( ) - o1.getTransactionFeeBasePrice( ) < 0.0 )
        {
            result = -1;
        }
        else if ( o2.getTransactionFeeBasePrice( ) - o1.getTransactionFeeBasePrice( ) > 0.0 )
        {
            result = 1;
        }

        return result;
    }
}
