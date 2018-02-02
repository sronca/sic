package it.istruzione.cercalatuascuola.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

class Generic<T extends Object>{
	void message(T oggetto){
		System.out.println(oggetto);
	}
}



public class Test {

	// generic method printArray                         
	public static < E > void printArray( E[] inputArray )
	{
		// Display array elements              
		for ( E element : inputArray ){        
			System.out.printf( "%s ", element );
		}
		System.out.println();
	}

	// determines the largest of three Comparable objects
	public static <T extends Comparable<T>> T maximum(T x, T y, T z)
	{                      
		T max = x; // assume x is initially the largest       
		if ( y.compareTo( max ) > 0 ){
			max = y; // y is the largest so far
		}
		if ( z.compareTo( max ) > 0 ){
			max = z; // z is the largest now                 
		}
		return max; // returns the largest object   
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		BigDecimal bd = new BigDecimal("1244.995123").setScale(1, BigDecimal.ROUND_HALF_DOWN); 
		//		DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ITALIAN);
		//		//decimalFormat.applyPattern("#0.##");
		//		//decimalFormat.setMaximumFractionDigits(2);
		//		//decimalFormat.setMinimumFractionDigits(0);
		//		
		//		//String result = decimalFormat.format(bd); 
		//		System.out.println(bd.toString());
		//		System.out.println(Float.valueOf(bd.floatValue()));


		// Generics

		Generic<String> pippo = new Generic<String>();
		Generic<Integer> pippo2 = new Generic<Integer>();

		pippo.message("Sergio");
		pippo2.message(3);

		// Create arrays of Integer, Double and Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

		System.out.println( "Array integerArray contains:" );
		printArray( intArray  ); // pass an Integer array

		System.out.println( "\nArray doubleArray contains:" );
		printArray( doubleArray ); // pass a Double array

		System.out.println( "\nArray characterArray contains:" );
		printArray( charArray ); // pass a Character array

		System.out.printf( "Max of %d, %d and %d is %d\n\n", 
				3, 4, 5, maximum( 3, 4, 5 ) );

		System.out.printf( "Maxm of %.1f,%.1f and %.1f is %.1f\n\n",
				6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ) );

		System.out.printf( "Max of %s, %s and %s is %s\n","pear",
				"apple", "orange", maximum( "pear", "apple", "orange" ) );


	}


}
