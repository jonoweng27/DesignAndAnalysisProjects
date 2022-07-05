package edu.yu.da;

/** Stubbed implementation of the SimpleEquationI interface.
 *
 * @author Avraham Leff
 */

import static edu.yu.da.SimpleEquationI.SolutionI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.yu.da.GeneticAlgorithmConfig.SelectionType;

public class SimpleEquation implements SimpleEquationI {
  public class Solution implements SolutionI {
    int x;
    int y;
    int generations;
    //fitness=13 is optimal
    public Solution(int x, int y, int generations) {
      this.generations=generations;
      this.x = x;
      this.y = y;
    }
    @Override
    public int getX() {
      return this.x;
    }

    @Override
    public int getY() {
      return this.y;
    }

    @Override
    public double fitness() {
      return (6*this.x)-(this.x*this.x)+(4*this.y)-(this.y*this.y);
    }

    @Override
    public int nGenerations() {
      return this.generations;
    }
    public void setGenNumber(int i) {
      this.generations=i;
    }
    @Override
    public String toString() {
      return "X Value: " + this.x + " Y Value " + this.y + " Fitness: " + this.fitness() + " Generation: " + this.generations;
    }
    public void setX (int x) {
      this.x=x;
    }
    public void setY (int y) {
      this.y=y;
    }
  }
  List<Solution> solutions;
  /** Constructor.
   *
   * Students MAY NOT define any other constructor signature.  They
   * MAY change the stubbed implemention in any way they choose.
   */
  public SimpleEquation() {
    this.solutions=new ArrayList<>();
    
  }

  @Override
  public SolutionI solveIt(final GeneticAlgorithmConfig gac) {
    Random r = new Random();
    for (int i=0; i<gac.getInitialPopulationSize(); i++) {
      this.solutions.add(new Solution(r.nextInt(100), r.nextInt(100), 0));
    }
    Collections.sort(solutions, (a,b)->((Double)b.fitness()).compareTo(a.fitness()));
    int generationNum=0;
    while (generationNum<gac.getMaxGenerations()&&this.solutions.get(0).fitness()<gac.getThreshold()) {
      //will mate half of the generation, take the best
      int numOfReproductive = gac.getInitialPopulationSize()/2;
      List<Solution> genesSelectedForReproduction = new ArrayList<>();
      for (int j=0; j<numOfReproductive; j++) {
        genesSelectedForReproduction.add(this.solutions.get(j));
      }
      int numOfPairsToReproduce = (int)((double)numOfReproductive*(numOfReproductive-1)/2*gac.getCrossoverProbability());
      if (gac.getSelectionType()==SelectionType.ROULETTE) {
        Collections.sort(genesSelectedForReproduction, (a,b)->((Double)b.fitness()).compareTo(a.fitness()));
      } else {
        Collections.shuffle(genesSelectedForReproduction);
      }
      int count = 0;
      outer: for (int k=0; k<genesSelectedForReproduction.size()-1; k++) {
        for (int l=k+1; l<genesSelectedForReproduction.size(); l++) {
          if (count==numOfPairsToReproduce) {
            break outer;
          }
          this.solutions.add(new Solution(genesSelectedForReproduction.get(k).x, genesSelectedForReproduction.get(l).y, generationNum));
          this.solutions.add(new Solution(genesSelectedForReproduction.get(l).x, genesSelectedForReproduction.get(k).y, generationNum));
          count++;
        }
      }
      //run some mutations
      Collections.sort(this.solutions, (a,b)->((Double)b.fitness()).compareTo(a.fitness()));
      int numberToMutate = (int)(gac.getMutationProbability()*gac.getInitialPopulationSize());
      for (int y=0; y<numberToMutate; y++) {
        this.solutions.get(y).setX(this.solutions.get(y).getX()+r.nextInt(5 + 5) - 5);
        this.solutions.get(y).setY(this.solutions.get(y).getY()+r.nextInt(5 + 5) - 5);
        if (this.solutions.get(y).getX()<0) {
          this.solutions.get(y).setX(0);
        }
        if (this.solutions.get(y).getX()>100) {
          this.solutions.get(y).setX(100);
        }
        if (this.solutions.get(y).getY()<0) {
          this.solutions.get(y).setY(0);
        }
        if (this.solutions.get(y).getY()>100) {
          this.solutions.get(y).setY(100);
        }
      }
      //sort genes, increase generation number by 1, and set all genes to that generation number, and remove the worst ones
      Collections.sort(this.solutions, (a,b)->((Double)b.fitness()).compareTo(a.fitness()));
      generationNum++;
      int i;
      for (i=0; i<gac.getInitialPopulationSize(); i++) {
        this.solutions.get(i).setGenNumber(generationNum);
      }
      while (this.solutions.size()>i) {
        this.solutions.remove(i);
      }
      /*System.out.println("Generation #" + generationNum);
      for (int x=0; x<solutions.size(); x++) {
        System.out.println(solutions.get(x));
      }*/
    }
    return this.solutions.get(0);
  }

} // public class SimpleEquation