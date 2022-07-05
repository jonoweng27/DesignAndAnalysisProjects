package edu.yu.da;

import java.util.List;
import java.util.Set;

import edu.yu.da.ShortestCycleBase.Edge;

import java.util.ArrayList;
import java.util.HashSet;

//Adapted from Sedgewick, page 643. Made some minor changes, such as changing Bags to Lists, and making it work for undirected graphs
public class EdgeWeightedGraph {
    private final int V; // number of vertices
    private int E; // number of edges
    private List<List<Edge>> adj; // adjacency lists
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>();
        for (int v = 0; v <= V; v++) {
            adj.add(new ArrayList<>());
        }
    }
    public int V() { 
        return V; 
    }
    public int E() { 
        return E; 
    }
    public void addEdge(Edge e) {
        adj.get(e.v()).add(e);
        adj.get(e.w()).add(e);
        E++;
    }
    public Iterable<Edge> adj(int v){ 
        return adj.get(v); 
    }
    public Iterable<Edge> edges(){
        Set<Edge> bag = new HashSet<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj.get(v)) {
                bag.add(e);
            }
        }
        return bag;   
    }
}
