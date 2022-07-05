package edu.yu.da;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import edu.yu.da.DataCompressionI.SolutionI;
import edu.yu.da.GeneticAlgorithmConfig.SelectionType;
import edu.yu.da.DataCompressionI;

public class DataCompression implements DataCompressionI {
    public class Solution implements SolutionI {
        int generations;
        List<String> list;
        final List<String> originalList;

        public Solution(List <String> originalList, List<String> list, int generations) {
            this.generations=generations;
            this.originalList=originalList;
            this.list=list;
        }
        /** Returns the list associated with this solution: the elements are
         * identical to the original list, but may be ordered differently to
         * require fewer bytes when compressed.
         *
         * @return the solution's List.
         */
        @Override
        public List<String> getList() {
            return this.list;
        }
    
        /** Returns the original List.
         *
         * @return the original List.
         */
        @Override
        public List<String> getOriginalList() {
            return this.originalList;
        }
    
        /** Returns the ratio of the compressed number of bytes associated with the
         * original list (numerator) to the solution's compressed number of bytes
         * (denominator).
         *
         */
        @Override
        public double relativeImprovement() {
            double originalCompressed = DataCompressionI.bytesCompressed(this.originalList);
            double newCompressed = DataCompressionI.bytesCompressed(this.list);
            return originalCompressed/newCompressed;
        }
    
        /** Returns the number of generations required to generate the solution.
         *
         * @return number of generations required.
         */
        @Override
        public int nGenerations() {
            return this.generations;
        }
        
        public void setGenNumber(int i) {
            this.generations=i;
        }
        @Override
        public String toString() {
            return "Relative Improvement " + this.relativeImprovement() + "\nGeneration: " + this.generations + "\n";
        }
      } // inner Solution class



    PriorityQueue<Solution> solutions;
    final List<String> original;
    /** Constructor.
    *
    * @param original the list whose elements we want to reorder
    * to reduce the
    * number of bytes when compressing the list.
    */
    public DataCompression(final List<String> original) {
        this.solutions=new PriorityQueue<>(((a,b)->((Double)b.relativeImprovement()).compareTo(a.relativeImprovement())));
        this.original=original;
    }

    /** Returns the best Solution found by a genetic algorithm for the simple
     * equation specified by the requirements document.
     *
     * @param gac contains properties needed by a genetic algorithm
     * @see GeneticAlgorithmConfig
     */
    @Override
    public SolutionI solveIt(GeneticAlgorithmConfig gac) {
        final int threshold = DataCompressionI.bytesCompressed(this.original);
        for (int i=0; i<gac.getInitialPopulationSize(); i++) {
            List<String> newInstance = new ArrayList<>(original);
            Collections.shuffle(newInstance);
            this.solutions.add(new Solution(this.original, newInstance, 0));
        }
        int generationNum=0;
        while (generationNum<gac.getMaxGenerations()&&this.solutions.peek().relativeImprovement()<threshold) {
            //do some crossovers here
            //will mate half of the generation, take the best
            int numOfReproductive = gac.getInitialPopulationSize()/2;
            List<Solution> genesSelectedForReproduction = new ArrayList<>();
            Iterator<Solution> iterator = this.solutions.iterator();
            for (int j=0; j<numOfReproductive; j++) {
                genesSelectedForReproduction.add(iterator.next());
            }
            int numOfPairsToReproduce = (int)((double)numOfReproductive*(numOfReproductive-1)/2*gac.getCrossoverProbability());
            if (gac.getSelectionType()==SelectionType.ROULETTE) {
                Collections.sort(genesSelectedForReproduction, (a,b)->((Double)b.relativeImprovement()).compareTo(a.relativeImprovement()));
            } else {
                Collections.shuffle(genesSelectedForReproduction);
            }
            int count = 0;
            outer: for (int k=0; k<genesSelectedForReproduction.size()-1; k++) {
                    for (int l=k+1; l<genesSelectedForReproduction.size(); l++) {
                        if (count==numOfPairsToReproduce) {
                            break outer;
                        }
                        Solution sol1 = genesSelectedForReproduction.get(k);
                        Solution sol2 = genesSelectedForReproduction.get(l);
                        List<String> refrence = new ArrayList<>(this.original);
                        List<String> child = new ArrayList<>();
                        for (int i=0; i<this.original.size(); i++) {
                            String s;
                            if (i<this.original.size()/4||i>this.original.size()*3/4) {
                                s=sol1.getList().get(i);
                            } else {
                                s=sol2.getList().get(i);
                            }
                            if (refrence.contains(s)) {
                                refrence.remove(s);
                                child.add(s);
                            } else {
                                child.add(null);
                            }
                        }
                        while (!refrence.isEmpty()) {
                            child.add(child.indexOf(null), refrence.get(0));
                            refrence.remove(0);
                        }
                    this.solutions.add(new Solution(this.original, child, generationNum));    
                    count++;
                }
            }

            //run some mutations
            int numberToMutate = (int)(gac.getMutationProbability()*gac.getInitialPopulationSize());
            iterator = this.solutions.iterator();
            List<Solution> toAdd = new ArrayList<>();
            for (int y=0; y<numberToMutate; y++) {
                Random r = new Random();
                Solution sol = iterator.next();
                for (int z=0; z<3; z++) {
                    int rand1 = r.nextInt(this.original.size());
                    int rand2 = r.nextInt(this.original.size());
                    while (rand2==rand1) {
                        rand2 = r.nextInt(this.original.size());
                    }
                    List<String> newList = new ArrayList<>(sol.getList());
                    String s1 = sol.getList().get(rand1);
                    String s2 = sol.getList().get(rand2);
                    newList.set(rand2, s1);
                    newList.set(rand1, s2);
                    toAdd.add(new Solution(this.original, newList, generationNum));
                }
            }
            this.solutions.addAll(toAdd);
            for (int l=0; l<10; l++) {
                List<String> newInstance = new ArrayList<>(original);
                Collections.shuffle(newInstance);
                this.solutions.add(new Solution(this.original, newInstance, generationNum));
            }
            //sort genes, increase generation number by 1, and set all genes to that generation number, and remove the worst ones
            generationNum++;
            int i;
            PriorityQueue<Solution> pq = new PriorityQueue<>(((a,b)->((Double)b.relativeImprovement()).compareTo(a.relativeImprovement())));
            iterator = this.solutions.iterator();
            for (i=0; i<gac.getInitialPopulationSize(); i++) {
                pq.add(new Solution(this.original, iterator.next().getList(), generationNum));
            }
            this.solutions=pq;
            /*
            for (int x=0; x<solutions.size(); x++) {
                System.out.println(solutions.get(x));
            }*/
            //System.out.println(this.solutions.peek());
        }
        return this.solutions.peek();
    }

    /** Return the number of bytes when applying compression to the original
     * list.
     *
     * @return number of bytes
     */
    public int nCompressedBytesInOriginalList() {
        return DataCompressionI.bytesCompressed(this.original);
    }
  
}
