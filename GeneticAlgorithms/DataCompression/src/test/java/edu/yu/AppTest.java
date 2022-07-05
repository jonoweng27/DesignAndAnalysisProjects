package edu.yu;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import edu.yu.da.DataCompression;
import edu.yu.da.DataCompressionI;
import edu.yu.da.GeneticAlgorithmConfig;
import edu.yu.da.DataCompressionI.SolutionI;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public List<String> generateRandomList(int n) {
        List<String> result = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            result.add(generateStirng());
        }

        return result;
    }
    public static String generateStirng() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    @Test
    public void rigorousTest() {
        //List<String> list = generateRandomList(20);
        //String[] x = new String[] {"jwimlbf", "ycgqivui", "ddanpyl", "iss", "xpknp", "hopgowv", "nhiih", "xchs", "yrzyju", "uednlapv", "qwto", "qovrhrlq", "svh", "atmsocwt", "ytprkwin", "qkzx", "ugpha", "chuylyh", "stle", "itzlrpud", "cikzgmcf", "rcu", "ngozp", "lkgw", "ifl", "wrljqj", "rivtqw", "mgx", "dehq", "mzvryylvc", "ekgq", "xnwc", "iobvdrr", "jbwpfdd", "ywtixspo", "wpng", "ozna", "vzskpoo", "scwpn", "amgzskzs", "ycn", "fuafn", "euj", "ofvdr", "upj", "zkzh", "zbiwg", "barmn", "ofdzg", "qhtczyvo", "efxdqhf", "jcjbog", "yixixw", "zepzyxqu", "cuqig", "xnrvhmvga", "whclbvgnj", "sqf", "dfc", "kvlcncbc", "xlw", "xojovrzux", "ughtu", "nvxfa", "ulymubl", "tijkki", "htxl", "wtc", "tntllycv", "qqfb", "nruy", "guhe", "rxuszmw", "enhgcwtsv", "vxpmhz"};
        List<String> list = Arrays.asList("AAAAA", "BBBBB", "AAAAA", "BBBBB", "AAAAA", "BBBBB");
        List<String> list2 = Arrays.asList("AAAAA", "BBBBB", "BBBBB", "BBBBB", "AAAAA", "AAAAA");
        System.out.println(DataCompressionI.bytesCompressed(list));
        System.out.println(DataCompressionI.bytesCompressed(list2));
        //List<String> list = Arrays.asList(x);
        GeneticAlgorithmConfig gac = new GeneticAlgorithmConfig(
                20,
                100,
                DataCompressionI.bytesCompressed(list),
                GeneticAlgorithmConfig.SelectionType.TOURNAMENT,
                0.1,
                0.7);
                
        System.out.println("Creating se...");
        DataCompression se = new DataCompression(list);
        System.out.println("Slving se...");
        SolutionI maxima = se.solveIt(gac);

        System.out.println("Fitness: " + maxima.relativeImprovement());
        System.out.println("Generation: " + maxima.nGenerations());
    }


}
