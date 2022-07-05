package edu.yu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import edu.yu.da.DataCompression;
import edu.yu.da.DataCompressionI;
import edu.yu.da.GeneticAlgorithmConfig;
import edu.yu.da.DataCompressionI.SolutionI;
import edu.yu.da.GeneticAlgorithmConfig.SelectionType;

public class DataTest {
    @Test
    public void sampleTest() {
        List<String> list = new ArrayList<>();
        list.add("AAAAAA On May 7, 1931, the most sensational manhunt New York City had ever known had come to its climax");
        list.add("BBBBBB One hundred and fifty policemen and detectives laid siege to his top-floor hideaway");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("DDDDDD Crowley, crouching behind an overstuffed chair, fired incessantly at the police");
        list.add("EEEEEE Ten thousand excited people watched the battle. Nothing like it had ever been seen before on the sidewalks of New York");
        list.add("FFFFFF When Crowley was captured, Police Commissioner E. P. Mulrooney declared that the two-gun desperado was one of the most dangerous criminals ever encountered in the history of New York.");
        list.add("GGGGGG A short time before this, Crowley had been having a necking party with his girl friend on a country road out on Long Island");
        list.add("GGGGGG A short time before this, Crowley had been having a necking party with his girl friend on a country road out on Long Island");
        list.add("HHHHHH Without saying a word, Crowley drew his gun and cut the policeman down with a shower of lead.");
        list.add("IIIIII Crowley was sentenced to the electric chair");
        DataCompression s = new DataCompression(list);
        GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(20, 100, DataCompressionI.bytesCompressed(list), SelectionType.TOURNAMENT, 1, 1);
        SolutionI sol = s.solveIt(gac);
        //System.out.println("original compressed: " + DataCompressionI.bytesCompressed(sol.getOriginalList()));
        //System.out.println("new compressed: " + DataCompressionI.bytesCompressed(sol.getList()));
        List<String> solution = sol.getList();
        List<String> original = sol.getOriginalList();
        assertEquals(list, original);
        Collections.sort(solution);
        assertEquals(original, solution);
        assertEquals(list, solution);
        //assertTrue(sol.nGenerations()<250);
    }
    @Test
    public void compressionTest() {
        List<String> list = new ArrayList<>();
        list.add("AAAAAA On May 7, 1931, the most sensational manhunt New York City had ever known had come to its climax");
        list.add("BBBBBB One hundred and fifty policemen and detectives laid siege to his top-floor hideaway");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("CCCCCC Then they mounted their machine guns on surrounding buildings, and for more than an hour one of New York’s fine residential areas reverberated with the crack of pistol fire and the rat-tat-tat of machine guns.");
        list.add("DDDDDD Crowley, crouching behind an overstuffed chair, fired incessantly at the police");
        list.add("EEEEEE Ten thousand excited people watched the battle. Nothing like it had ever been seen before on the sidewalks of New York");
        list.add("FFFFFF When Crowley was captured, Police Commissioner E. P. Mulrooney declared that the two-gun desperado was one of the most dangerous criminals ever encountered in the history of New York.");
        list.add("GGGGGG A short time before this, Crowley had been having a necking party with his girl friend on a country road out on Long Island");
        list.add("GGGGGG A short time before this, Crowley had been having a necking party with his girl friend on a country road out on Long Island");
        list.add("HHHHHH Without saying a word, Crowley drew his gun and cut the policeman down with a shower of lead.");
        list.add("IIIIII Crowley was sentenced to the electric chair");
        List<String> list2 = new ArrayList<>(list);
        Collections.shuffle(list2);
        //System.out.println("original compressed: " + DataCompressionI.bytesCompressed(list));
        //System.out.println("new compressed: " + DataCompressionI.bytesCompressed(list2));
    }
    /*
    @Test
    public void rouletteVsTourney() {
        DataCompression s = new DataCompression();
        DataCompression t = new DataCompression();
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
    }*/
}
