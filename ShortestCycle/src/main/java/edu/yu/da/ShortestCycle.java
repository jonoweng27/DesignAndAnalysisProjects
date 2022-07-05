package edu.yu.da;

import java.util.ArrayList;
import java.util.HashSet;

/** Implements the ShortestCycleBase API.
 *
 */

import java.util.List;
import java.util.Set;

import static edu.yu.da.ShortestCycleBase.Edge;

public class ShortestCycle extends ShortestCycleBase {
  List<Edge> edges;
  Edge e;
  /** Constructor
   *
   * @param edges List of edges that, in toto, represent a weighted undirected
   * graph.  The client maintains ownership of the List: the implementation may
   * not modify this input parameter.  The client guarantees that the List is
   * not null, and doesn't contains any null edges.
   * @param e One of the graph's edges, the "edge of interest" since we want to
   * determine the shortest cycle containing this edge.
   */
  public ShortestCycle(final List<Edge> edges, final Edge e) {
    // base class does nothing, but let's do it right
    super(edges, e);
    this.edges=edges;
    this.e=e;
  } // constructor

  /** Finds the shortest cycle in the graph with respect to the specified edge
   * as detailed by the requirements document.
   *
   * @return List of edges representing the shortest cyle containing the "edge
   * of interest".  The List can begin with any edge from the cycle, but must
   * be a sequence that begins and ends at the same vertex and contain the
   * "edge of interest".
   */
  @Override
  public List<Edge> doIt() {
    int max=-1;
    //find highest numbered vertex to determine number of vertices in the graph
    for (Edge x: this.edges) {
      if (x.v()>max) {
        max=x.v();
      }
      if (x.w()>max) {
        max=x.w();
      }
    }
    EdgeWeightedGraph graph = new EdgeWeightedGraph(max+1);
    //Add every edge to the digraph (EXCEPT the edge of interest)
    for (Edge x : this.edges) {
      if (!x.equals(e))  {
        graph.addEdge(new Edge(x.v(), x.w(), x.weight()));
      }
    }
    //Run dijkstra on the graph to find the shortest path (NOT including the edge of interest)
    DijkstraSP dijkstra = new DijkstraSP(graph, e.w());
    List<Edge> solution =  dijkstra.pathTo(e.v());
    if (solution!=null) {
      solution.add(e);
      return solution;
    }
    return new ArrayList<>();
  } // doIt

}