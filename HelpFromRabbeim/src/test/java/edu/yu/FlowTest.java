package edu.yu;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static edu.yu.da.HelpFromRabbeimI.HelpTopics.*;
import static org.junit.Assert.*;
import static edu.yu.da.HelpFromRabbeimI.Rebbe;

import edu.yu.da.HelpFromRabbeim;
import edu.yu.da.HelpFromRabbeimI;
import edu.yu.da.HelpFromRabbeimI.HelpTopics;
import edu.yu.da.HelpFromRabbeimI.Rebbe;

public class FlowTest {
    @Test
    public void leffTest() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (BAVA_KAMMA)) ;
        final Rebbe rebbe1 = new Rebbe ( 1 , List.of (BAVA_KAMMA)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 2) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(BAVA_KAMMA, schedule.get(0));
        assertEquals(BAVA_KAMMA, schedule.get(1));
    }
    @Test
    public void noMatchTest() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (MUSSAR)) ;
        final Rebbe rebbe1 = new Rebbe ( 1 , List.of (MISHNAYOS)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 1) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(Collections.emptyMap(), schedule);
    }
    @Test
    public void partialMatchTest() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (MUSSAR, BAVA_KAMMA)) ;
        final Rebbe rebbe1 = new Rebbe ( 1 , List.of (MISHNAYOS)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 2) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(Collections.emptyMap(), schedule);
    }
    @Test
    public void extraRebbeTest2() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (BAVA_KAMMA)) ;
        final Rebbe rebbe1 = new Rebbe ( 1 , List.of (MISHNAYOS, SANHEDRIN, CHUMASH)) ;
        final Rebbe rebbe2 = new Rebbe ( 2 , List.of (MUSSAR, SANHEDRIN, BAVA_KAMMA)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 1) ;
        requestedHelp.put(MUSSAR, 1) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1, rebbe2 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(BAVA_KAMMA, schedule.get(0));
        assertEquals(MUSSAR, schedule.get(2));
        assertNull(schedule.get(1));
    }
    @Test
    public void matchTest3() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (BAVA_KAMMA)) ;
        final Rebbe rebbe1 = new Rebbe ( 1 , List.of (MISHNAYOS, SANHEDRIN, CHUMASH)) ;
        final Rebbe rebbe2 = new Rebbe ( 2 , List.of (MUSSAR, SANHEDRIN, BAVA_KAMMA)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 1) ;
        requestedHelp.put(MUSSAR, 1) ;
        requestedHelp.put(SANHEDRIN, 1) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1, rebbe2 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(BAVA_KAMMA, schedule.get(0));
        assertEquals(SANHEDRIN, schedule.get(1));
        assertEquals(MUSSAR, schedule.get(2));
        
    }
    @Test
    public void moreTalmidimThanRabbeim() {
        final Rebbe rebbe0 = new Rebbe (0 , List.of (BAVA_KAMMA, SANHEDRIN, CHUMASH, NACH, MUSSAR, MISHNAYOS, BROCHOS, SHABBOS, BEITZA)) ;
        final Rebbe rebbe1 = new Rebbe (1 , List.of (BAVA_KAMMA, SANHEDRIN, CHUMASH, NACH, MUSSAR, MISHNAYOS, BROCHOS, SHABBOS, BEITZA)) ;
        final Rebbe rebbe2 = new Rebbe (2 , List.of (BAVA_KAMMA, SANHEDRIN, CHUMASH, NACH, MUSSAR, MISHNAYOS, BROCHOS, SHABBOS, BEITZA));
        final Rebbe rebbe3 = new Rebbe (3 , List.of (BAVA_KAMMA, SANHEDRIN, CHUMASH, NACH, MUSSAR, MISHNAYOS, BROCHOS, SHABBOS, BEITZA)) ;
        final Map<HelpTopics , Integer> requestedHelp = new HashMap<>() ;
        requestedHelp.put(BAVA_KAMMA, 1) ;
        requestedHelp.put(MUSSAR, 1) ;
        requestedHelp.put(SANHEDRIN, 1) ;
        requestedHelp.put(SHABBOS, 1) ;
        requestedHelp.put(NACH, 1) ;
        final HelpFromRabbeimI hfr = new HelpFromRabbeim ( ) ;
        final List <Rebbe> rabbeim = List.of ( rebbe0 , rebbe1, rebbe2, rebbe3 ) ;
        final Map<Integer , HelpTopics> schedule = hfr . scheduleIt ( rabbeim , requestedHelp ) ;
        assertEquals(Collections.emptyMap(), schedule);
        
    }
}
