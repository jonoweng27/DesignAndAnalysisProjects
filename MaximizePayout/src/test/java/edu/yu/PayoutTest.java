package edu.yu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.yu.da.MaximizePayout;

public class PayoutTest {
    @Test
    public void basicTest(){
        List<Long> A = new ArrayList<>();
        A.add((long)2);
        A.add((long)3);
        A.add((long)4);
        A.add((long)5);
        A.add((long)1);
        List<Long> B = new ArrayList<>();
        B.add((long)4);
        B.add((long)5);
        B.add((long)2);
        B.add((long)1);
        B.add((long)3);
        MaximizePayout m = new MaximizePayout();
        long solution = m.max(A, B);
        assertEquals(86400000, solution);
        assertEquals(5, A.size());
        assertEquals(5, B.size());
        assertEquals ((long)2, (long)A.get(0));
        assertEquals ((long)4, (long)B.get(0));
    }
    @Test
    public void basicTest2(){
        List<Long> A = new ArrayList<>();
        A.add((long)4);
        A.add((long)5);
        List<Long> B = new ArrayList<>();
        B.add((long)5);
        B.add((long)4);
        MaximizePayout m = new MaximizePayout();
        long solution = m.max(A, B);
        assertEquals(800000, solution);
        assertEquals(2, A.size());
        assertEquals(2, B.size());
        assertEquals ((long)4, (long)A.get(0));
        assertEquals ((long)5, (long)B.get(0));
    }
}
