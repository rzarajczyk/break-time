package pl.rzarajczyk.utils.collections;

/**
 *
 * @author rafalz
 */
public class ArrayUtils {
    
    private ArrayUtils() {
        // hide the constructor
    }
    
    public static int max(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        int result = Integer.MIN_VALUE;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] > result )
                result = array[i];
        }
        return result;
    }
    
    public static int min(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        int result = Integer.MAX_VALUE;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] < result )
                result = array[i];
        }
        return result;
    }
    
    public static int minPositive(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        boolean found = false;
        int result = Integer.MAX_VALUE;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] >= 0 && array[i] <= result ) {
                result = array[i];
                found = true;
            }
        }
        if ( ! found )
            throw new IllegalArgumentException("Input array has no positive values");
        return result;
    }
    
    public static int maxPositive(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        int result = 0;
        boolean found = false;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] >= 0 && array[i] >= result ) {
                result = array[i];
                found = true;
            }
        }
        if ( ! found )
            throw new IllegalArgumentException("Input array has no positive values");
        return result;
    }
    
    public static int minNegative(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        boolean found = false;
        int result = 0;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] <= 0 && array[i] <= result ) {
                result = array[i];
                found = true;
            }
        }
        if ( ! found )
            throw new IllegalArgumentException("Input array has no negative values");
        return result;
    }
    
    public static int maxNegative(int... array) {
        if ( array.length == 0 )
            throw new IllegalArgumentException("Input array is empty");
        boolean found = false;
        int result = Integer.MIN_VALUE;
        for ( int i=0; i<array.length; i++ ) {
            if ( array[i] <= 0 && array[i] >= result ) {
                result = array[i];
                found = true;
            }
        }
        if ( ! found )
            throw new IllegalArgumentException("Input array has no negative values");
        return result;
    }
    
}
