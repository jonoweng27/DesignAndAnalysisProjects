package edu.yu;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.yu.da.WaitNoMore;
import edu.yu.da.WaitNoMoreI;

public class WaitTest {
    @Test
    public void sortingTest() {
        int[] durations = {5,2,5,2,2};
        int[] weights = {2,1,3,10,2};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(26, result);
    }
    @Test
    public void sortingTest2() {
        int[] durations = {5,2,5,10,1};
        int[] weights = {2,1,3,10,2};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(46, result);
    }
    @Test
    public void LeffTest() {
        int[] durations = {2,4};
        int[] weights = {1,1};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(2, result);
    }
    @Test
    public void practiceTest() {
        int[] durations = {1,3};
        int[] weights = {2,5};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(1, result);
    }
    @Test
    public void practiceTest2() {
        int[] durations = {2,3};
        int[] weights = {2,5};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(3, result);
    }
    @Test
    public void practiceTest3() {
        int[] durations = {1,1,1};
        int[] weights = {1,1,1};
        WaitNoMoreI wait = new WaitNoMore();
        int result = wait.minTotalWaitingTime(durations, weights);
        assertEquals(3, result);
    }
}
