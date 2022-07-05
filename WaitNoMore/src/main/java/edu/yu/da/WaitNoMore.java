package edu.yu.da;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Implements the WaitNoMoreI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

public class WaitNoMore implements WaitNoMoreI {

  /** No-op constructor
   */
  List<List<Integer>> list;
  public WaitNoMore() {
    this.list = new ArrayList<>();
  }

  @Override
  public int minTotalWaitingTime(final int[] durations, final int[] weights) {
      for (int i=0; i<durations.length; i++) {
          list.add(new ArrayList<>());
          list.get(i).add(weights[i]);
          list.get(i).add(durations[i]);
      }
      Collections.sort(list, (a,b)->Double.compare(((double)a.get(1))/a.get(0), ((double)b.get(1))/b.get(0)));
      int timeElapsed=0;
      int result=0;
      for (int j=0; j<list.size(); j++) {
          result+=timeElapsed;
          timeElapsed+=list.get(j).get(1);
      }
      return result;
  }
} // WaitNoMore