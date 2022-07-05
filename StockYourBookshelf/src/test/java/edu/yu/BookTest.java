package edu.yu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.yu.da.StockYourBookshelf;
import edu.yu.da.StockYourBookshelfI;

public class BookTest {
    @Test
    public void basicTest() {
        List<Integer> tanachPrices = Arrays.asList(1,2,3);
        List<Integer> mishnaPrices = Arrays.asList(2,4,6,8);
        List<Integer> gemaraPrices = Arrays.asList(3,10);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        assertEquals(21, stock.maxAmountThatCanBeSpent(30, map));
        List<Integer> solution = Arrays.asList(10,8,3);
        assertEquals(solution, stock.solution());
    }
    @Test
    public void basicTest3() {
        List<Integer> tanachPrices = Arrays.asList(1,2,3);
        List<Integer> mishnaPrices = Arrays.asList(2,4,6,8);
        List<Integer> gemaraPrices = Arrays.asList(3,10);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        assertEquals(6, stock.maxAmountThatCanBeSpent(6, map));
        List<Integer> solution = Arrays.asList(3,2,1);
        assertEquals(solution, stock.solution());
    }
    @Test
    public void basicTest2() {
        List<Integer> tanachPrices = Arrays.asList(12,25,10);
        List<Integer> mishnaPrices = Arrays.asList(2,4,6,8);
        List<Integer> gemaraPrices = Arrays.asList(3,10);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        assertEquals(15, stock.maxAmountThatCanBeSpent(16, map));
        List<Integer> solution = Arrays.asList(3,2,10);
        assertEquals(solution, stock.solution());
    }
    @Test
    public void basicTest4() {
        List<Integer> tanachPrices = Arrays.asList(4);
        List<Integer> mishnaPrices = Arrays.asList(4);
        List<Integer> gemaraPrices = Arrays.asList(8,4);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        assertEquals(12, stock.maxAmountThatCanBeSpent(13, map));
        List<Integer> solution = Arrays.asList(4,4,4);
        assertEquals(solution, stock.solution());
    }
    @Test
    public void noSolution() {
        List<Integer> tanachPrices = Arrays.asList(1,2,3);
        List<Integer> mishnaPrices = Arrays.asList(2,4,6,8);
        List<Integer> gemaraPrices = Arrays.asList(3,10);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        assertEquals(Integer.MIN_VALUE, stock.maxAmountThatCanBeSpent(5, map));
        assertEquals(Collections.emptyList(), stock.solution());
    }
    @Test
    public void makeSureException() {
        List<Integer> tanachPrices = Arrays.asList(1,2,3);
        List<Integer> mishnaPrices = Arrays.asList(2,4,6,8);
        List<Integer> gemaraPrices = Arrays.asList(3,10);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Tanach", tanachPrices);
        map.put("Mishna", mishnaPrices);
        map.put("Gemara", gemaraPrices);
        StockYourBookshelfI stock = new StockYourBookshelf();
        try {
            List<Integer> list = stock.solution();
            fail();
        } catch (IllegalStateException e) {}
    }
    
}
