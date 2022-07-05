package edu.yu.da;

/** Implements the MaximizePayoutI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

public class MaximizePayout implements MaximizePayoutI {

    List<Long> aList;
    List<Long> bList;
  /** No-op constructor
   */
  public MaximizePayout() {
    this.aList=new ArrayList<>();
    this.bList=new ArrayList<>();
  }

  /** Computes the maximum payout (per definition in the requirements doc) of
   * the two sets.  The results are undefined if any set element is less than
   * or equal to 0.
   *
   * @param A List of non-negative long elements, client maintains ownership
   * @param B List of non-negative long elements, client maintains ownership
   * @return the maximum payout possible given the input parameters.
   * @throws IllegalArgumentException if the two lists aren't the same size, if
   * sets are null, or if the sets are empty.
   */
  @Override
  public long max(final List<Long> A, final List<Long> B) {
    if (A.size()!=B.size()||A==null||B==null||A.isEmpty()||B.isEmpty()) {
        throw new IllegalArgumentException();
    }
    this.aList.addAll(A);
    this.bList.addAll(B);
    Collections.sort(aList, (a,b)->b.compareTo(a));
    Collections.sort(bList, (a,b)->b.compareTo(a));
    long result=1;
    for (int i=0; i<A.size(); i++) {
        result*=Math.pow(aList.get(i), bList.get(i));
    }
    return result;
  }

} // MaximizePayout