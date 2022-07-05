package edu.yu.da;

/** Implements the StockYourBookshelfI API.
 *
 * Students MAY NOT change the provided constructor signature!
 * 
 * @author Avraham Leff
 */

import java.util.*;

public class StockYourBookshelf implements StockYourBookshelfI {
    public class Entry {
        String name;
        List<Integer> elements;
        public Entry(String name, List<Integer> elements) {
            this.name=name;
            this.elements=elements;
        }
    }

    /** No-op constructor
     */
    List<Entry> list;
    int[][] prices;
    boolean[][] hasIssue;
    int budget;
    int maxAmount;
    boolean maxCalled;
    public StockYourBookshelf() {
	    this.list=new ArrayList<>();
        this.maxCalled=false;
    }


    @Override
    public int maxAmountThatCanBeSpent(final int budget, final Map<String, List<Integer>> seforimClassToTypePrices) {
        this.maxCalled=true;
        this.budget=budget;
        //initialize 2d array
        this.prices=new int[seforimClassToTypePrices.keySet().size()+1][budget+1];
        //initialize boolean array
        this.hasIssue = new boolean[seforimClassToTypePrices.keySet().size()+1][budget+1];
        //put in sorted map to help with ascending order
        for (String s : seforimClassToTypePrices.keySet()){
            list.add(new Entry(s, seforimClassToTypePrices.get(s)));
        }
        Collections.sort(this.list, (a,b)->b.name.compareTo(a.name));
        list.add(0,null);
        //iterate through types of seforim
        for (int j=1; j<list.size(); j++) {
            //iterate through budget (from 0 till size of budget)
            for (int i=1; i<=budget; i++) {
                int maxNotOver = 0;
                //find most optimal sefer in type that will get highest value, not having the sefer be greater than the weight size and making sure all are included from previous classes
                for (int k=0; k<this.list.get(j).elements.size(); k++) {
                    int val = this.list.get(j).elements.get(k);
                    if ((val<=i)&&(val+this.prices[j-1][i-val]>maxNotOver)&&(j-1==0||i-val>0)&&(!this.hasIssue[j-1][i-val])) {
                        maxNotOver=val+this.prices[j-1][i-val];
                    }
                }
                if (maxNotOver==0) {
                    this.hasIssue[j][i]=true;
                }
                this.prices[j][i]=maxNotOver;
            }
            if (this.hasIssue[j][budget]) {
                this.maxAmount=Integer.MIN_VALUE;
                return this.maxAmount;
            }
        }
        this.maxAmount = this.prices[seforimClassToTypePrices.keySet().size()][budget];
        return this.maxAmount;
    }

    @Override
    public List<Integer> solution() {
        if (!maxCalled) {
            throw new IllegalStateException();
        }
        if (this.maxAmount==Integer.MIN_VALUE) {
            return Collections.emptyList();
        }
        List<Integer> solution = new ArrayList<>();
        int currentTotal=this.maxAmount;
        int classes = list.size()-1;
        int weight=this.budget;
        while (currentTotal>0&&classes>0) {
            int val=0;
            for (int z=0; z<this.list.get(classes).elements.size(); z++) {
                val = this.list.get(classes).elements.get(z);
                int amountLevelBelow = weight-val;
                if (amountLevelBelow>=0&&this.prices[classes-1][amountLevelBelow]==currentTotal-val) {
                    solution.add(val);
                    break;
                }
            }
            classes--;
            currentTotal-=val;
            weight-=val;
        }
        return solution;
    }

} // StockYourBookshelf