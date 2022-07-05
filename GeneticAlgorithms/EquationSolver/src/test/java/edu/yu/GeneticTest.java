package edu.yu;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.yu.da.GeneticAlgorithmConfig;
import edu.yu.da.SimpleEquation;
import edu.yu.da.GeneticAlgorithmConfig.SelectionType;
import edu.yu.da.SimpleEquation.Solution;
import edu.yu.da.SimpleEquationI.SolutionI;

public class GeneticTest {
    @Test
    public void sampleTest() {
        SimpleEquation s = new SimpleEquation();
        GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(8, 250, 13, SelectionType.TOURNAMENT, .25, .25);
        SolutionI sol = s.solveIt(gac);
        //assertTrue(sol.nGenerations()<250);
    }
    @Test
    public void rouletteVsTourney() {
        SimpleEquation s = new SimpleEquation();
        SimpleEquation t = new SimpleEquation();
        int rouletteGeneration=0;
        int tourneyGeneration=0;
        for (int i=0; i<100; i++) {
            GeneticAlgorithmConfig gacR = new GeneticAlgorithmConfig(8, 250, 13, SelectionType.ROULETTE, .25, .25);
            GeneticAlgorithmConfig gacT = new GeneticAlgorithmConfig(8, 250, 13, SelectionType.TOURNAMENT, .25, .25);
            SolutionI solR = s.solveIt(gacR);
            SolutionI solT = t.solveIt(gacT);
            rouletteGeneration+=solR.nGenerations();
            tourneyGeneration+=solT.nGenerations();
            //assertTrue(solR.nGenerations()<250);
            //assertTrue(solT.nGenerations()<250);
        }
        rouletteGeneration/=100;
        tourneyGeneration/=100;
        //System.out.println("Roulette Generation Average: " + rouletteGeneration +"\nTourney Generation Average: " + tourneyGeneration);
    }
}
