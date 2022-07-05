package edu.yu;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.yu.da.MultiMerge;

public class MergeTest {

    @Test
    public void basicTest() {
        int[][] arr = {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
        MultiMerge multimerge = new MultiMerge();
        int[] result = multimerge.merge(arr);
        for (int i=0; i<result.length; i++) {
            assertEquals(result[i], i+1);
        }
        assertEquals(3, multimerge.getNCombinedMerges());
    }
    @Test
    public void basicTest2() {
        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        MultiMerge multimerge = new MultiMerge();
        int[] result = multimerge.merge(arr);
        for (int i=0; i<result.length; i++) {
            assertEquals(result[i], i+1);
        }
        assertEquals(3, multimerge.getNCombinedMerges());
    }
    @Test
    public void oddTest() {
        int[][] arr = {{1,4,7,11,24},{2,53,75,105,106},{48,89,104,127,1027}};
        MultiMerge multimerge = new MultiMerge();
        int[] result = multimerge.merge(arr);
        assertEquals(15, result.length);
        for (int i=0; i<result.length-1; i++) {
            assertTrue(result[i+1]>result[i]);
        }
        assertEquals(2, multimerge.getNCombinedMerges());
    }
    @Test
    public void sixTest() {
        int[][] arr = {{1,4,7,11,24},{2,53,75,105,106},{48,89,104,127,1027}, {1,3,5,7,9}, {2,4,6,8,10}, {3,6,100,101,10000}};
        MultiMerge multimerge = new MultiMerge();
        int[] result = multimerge.merge(arr);
        assertEquals(arr.length*arr[0].length, result.length);
        for (int i=0; i<result.length-1; i++) {
            assertTrue(result[i+1]>=result[i]);
        }
        assertEquals(5, multimerge.getNCombinedMerges());
    }
    @Test
    public void oneDArray() {
        int[][] arr = {{1,2,3,4,5}};
        MultiMerge multimerge = new MultiMerge();
        int[] result = multimerge.merge(arr);
        assertEquals(5, result.length);
        for (int i=0; i<result.length-1; i++) {
            assertEquals(i+1, result[i]);
        }
        assertEquals(0, multimerge.getNCombinedMerges());
    }
}
