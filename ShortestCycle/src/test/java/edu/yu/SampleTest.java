package edu.yu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.yu.da.ShortestCycle;
import edu.yu.da.ShortestCycleBase;
import edu.yu.da.ShortestCycleBase.Edge;

public class SampleTest {
    
    @Test
    public void edgeEqualityTest() {
        Edge a = new Edge (4,5,1);
        Edge b = new Edge (5,4,1);
        assertEquals(a,b);
    }
    @Test
    public void mainTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(2,3,3));
        edges.add(new Edge(3,4,3));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(6,0,3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(7, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
    }
    @Test
    public void main2Test() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(2,1,3));
        edges.add(new Edge(3,2,3));
        edges.add(new Edge(4,3,3));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(0,6,3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(7, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
    }
    @Test
    public void outOfRangeTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(2,1,3));
        edges.add(new Edge(10,2,3));
        edges.add(new Edge(10, 0, 3));
        edges.add(new Edge(5, 7, 3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(4, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
    }
    @Test
    public void outOfRangeTest2() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(2,1,3));
        edges.add(new Edge(10,2,3));
        edges.add(new Edge(10, 1, 3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(0, s.doIt().size());
        assertTrue(!s.doIt().contains(interest));
    }
    @Test
    public void leffTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(1, 2, 3);
        edges.add(interest);
        edges.add(new Edge(1,3,1));
        edges.add(new Edge(2,3,7));
        edges.add(new Edge(2, 4, 5));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(2, 5, 1));
        edges.add(new Edge(4, 5, 7));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertTrue(s.doIt().size()>=3);
        assertTrue(s.doIt().contains(interest));
    }
    @Test
    public void shortcutTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(2,1,3));
        edges.add(new Edge(3,2,3));
        edges.add(new Edge(4,3,3));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(0,6,3));
        edges.add(new Edge(0,3,3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(4, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
        assertTrue(s.doIt().contains(new Edge(1,0,3)));
        assertTrue(s.doIt().contains(new Edge(3,0,3)));
        assertTrue(!s.doIt().contains(new Edge(0,6,3)));
    }
    @Test
    public void shortcutTest2() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(2,1,3));
        edges.add(new Edge(3,2,3));
        edges.add(new Edge(4,3,3));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(0,6,3));
        edges.add(new Edge(0,3,13));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(7, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
        assertTrue(!s.doIt().contains(new Edge(3,0,3)));
        assertTrue(s.doIt().contains(new Edge(6,0,3)));
    }
    @Test
    public void moreVerticesShorterTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 6);
        edges.add(interest);
        edges.add(new Edge(2,1,6));
        edges.add(new Edge(2,0,6));
        edges.add(new Edge(1,5,1));
        edges.add(new Edge(5,4,1));
        edges.add(new Edge(0,4,1));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(4, s.doIt().size());
        assertTrue(s.doIt().contains(interest));
        assertTrue(s.doIt().contains(new Edge(5,4,1)));
        assertTrue(s.doIt().contains(new Edge(4,5,1)));
        assertTrue(!s.doIt().contains(new Edge(2,0,6)));
    }
    @Test
    public void noCyclesTest() {
        List<Edge> edges = new ArrayList<>();
        Edge interest = new Edge(0, 1, 3);
        edges.add(interest);
        edges.add(new Edge(1,2,3));
        edges.add(new Edge(3,4,3));
        edges.add(new Edge(4,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(5,7,3));
        edges.add(new Edge(6,7,3));
        edges.add(new Edge(4,7,3));
        ShortestCycleBase s = new ShortestCycle(edges, interest);
        assertEquals(0, s.doIt().size());
    }
    @Test
    public void testHashCode() {
        Edge m = new Edge(0, 2, 4.0);
        Edge n = new Edge(2, 0, 4.0);
        assertEquals(m, n);
        assertEquals(m.hashCode(), n.hashCode());
    }

    @Test
    public void testPuttinginSet() {
        Set<Edge> set = new HashSet<>();
        Edge m = new Edge(0, 2, 4.0);
        Edge n = new Edge(2, 0, 4.0);
        assertEquals(m, n);
        set.add(m);
        assertTrue(set.contains(n));
    }
}
