package pl.rzarajczyk.utils.collections;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class ArrayUtilsTest {
    
    @Test
    public void minShouldFindMinimumValue() {
        Assert.assertEquals(-4, ArrayUtils.min(1, 4, 0, -4, -2));
        Assert.assertEquals(-4, ArrayUtils.min(-4, -2));
        Assert.assertEquals(0, ArrayUtils.min(1, 4, 0));
    }
    
    @Test
    public void maxShouldFindMaximumValue() {
        Assert.assertEquals(4, ArrayUtils.max(1, 4, 0, -4, -2));
        Assert.assertEquals(-2, ArrayUtils.max(-4, -2));
        Assert.assertEquals(4, ArrayUtils.max(1, 4, 0));
    }
    
    @Test
    public void minPositiveShouldFindMinimumPositiveValue() {
        Assert.assertEquals(0, ArrayUtils.minPositive(1, 4, 0, -4, -2));
        Assert.assertEquals(0, ArrayUtils.minPositive(1, 4, 0));
    }
    
    @Test
    public void maxPositiveShouldFindMaximumPositiveValue() {
        Assert.assertEquals(4, ArrayUtils.maxPositive(1, 4, 0, -4, -2));
        Assert.assertEquals(4, ArrayUtils.maxPositive(1, 4, 0));
    }
    
    @Test
    public void minNegativeShouldFindMinimumNegativeValue() {
        Assert.assertEquals(-4, ArrayUtils.minNegative(1, 4, 0, -4, -2));
        Assert.assertEquals(-4, ArrayUtils.minNegative(-4, -2));
        Assert.assertEquals(0, ArrayUtils.minNegative(1, 4, 0));
    }
    
    @Test
    public void maxNegativeShouldFindMaximumNegativeValue() {
        Assert.assertEquals(0, ArrayUtils.maxNegative(1, 4, 0, -4, -2));
        Assert.assertEquals(-2, ArrayUtils.maxNegative(-4, -2));
        Assert.assertEquals(0, ArrayUtils.maxNegative(1, 4, 0));
    }

    
    // =========================================================================
    
    @Test(expected=IllegalArgumentException.class)
    public void minShouldThrowOnEmptyArray() {
        ArrayUtils.min();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void maxShouldThrowOnEmptyArray() {
        ArrayUtils.max();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void maxPositiveShouldThrowOnEmptyArray() {
        ArrayUtils.maxPositive();
    }
    @Test(expected=IllegalArgumentException.class)
    public void maxNegativeShouldThrowOnEmptyArray() {
        ArrayUtils.maxNegative();
    }
    @Test(expected=IllegalArgumentException.class)
    public void minPositiveShouldThrowOnEmptyArray() {
        ArrayUtils.minPositive();
    }
    @Test(expected=IllegalArgumentException.class)
    public void minNegativeShouldThrowOnEmptyArray() {
        ArrayUtils.minNegative();
    }
    
}
