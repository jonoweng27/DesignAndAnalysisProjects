package edu.yu.da;

/** Implements the HelpFromRabbeimI interface.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

import static edu.yu.da.HelpFromRabbeimI.HelpTopics.*;
import static edu.yu.da.HelpFromRabbeimI.Rebbe;

public class HelpFromRabbeim implements HelpFromRabbeimI {
    FlowNetwork graph;
  /** No-op constructor
   */
  public HelpFromRabbeim() {
    // no-op, students may change the implementation
  }

  @Override
  public Map<Integer, HelpTopics> scheduleIt(List<Rebbe> rabbeim, Map<HelpTopics, Integer> requestedHelp) {
    List<HelpTopics> list = new ArrayList<>();
    for (HelpTopics h : requestedHelp.keySet()) {
        for (int j=0; j<requestedHelp.get(h); j++) {
            list.add(h);
        }
    }
    //s is 0, rabbeim is 1-n, students from n+1 to 2nd last, t is last
    this.graph = new FlowNetwork(2+rabbeim.size()+list.size());
    int i;
    for (i=1; i<rabbeim.size()+1; i++) {
        this.graph.addEdge(new FlowEdge(0, i, 1));
    }
    for (; i<rabbeim.size()+list.size()+1; i++) {
        this.graph.addEdge(new FlowEdge(i, 1+rabbeim.size()+list.size(), 1));
    }
    for (int k=0; k<list.size(); k++) {
        HelpTopics h = list.get(k);
        int l=k;
        while (l+1<list.size()&&list.get(l+1)==list.get(k)) {
            l++;
        }
        for (int m=k; m<=l; m++) {
            for (int r=0; r<rabbeim.size(); r++) {
                if (rabbeim.get(r)._helpTopics.contains(h)) {
                    this.graph.addEdge(new FlowEdge(1+r, 1+rabbeim.size()+m, 1));
                }
            }
        }
        k=l;
    }
    FordFulkerson ford = new FordFulkerson(this.graph, 0, this.graph.V()-1);
    if (ford.value()!=list.size()) {
        return Collections.emptyMap();
    }
    Map<Integer, HelpTopics> map = new HashMap<>();
    for (int r=0; r<rabbeim.size(); r++) {
        int vertexNumber = 1+r;
        for (FlowEdge e : this.graph.adj(vertexNumber)) {
            if (e.to()>vertexNumber&&e.flow()==1) {
                int studentVertex = e.to();
                int vertexInList = studentVertex-rabbeim.size()-1;
                map.put(rabbeim.get(r)._id, list.get(vertexInList));
                break;
            }
        }
    }
    return map;
  }
} // HelpFromRabbeim