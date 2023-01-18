/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import java.util.Dictionary;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MELIH BEY
 */
public class AlgorithmProjectCheckTest {
    
    public AlgorithmProjectCheckTest() {
    }

    /**
     * Test of main method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        AlgorithmProjectCheck.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAsciiEqual method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testIsAsciiEqual() {
        System.out.println("isAsciiEqual");
        String s1 = "";
        String s2 = "";
        boolean expResult = false;
        boolean result = AlgorithmProjectCheck.isAsciiEqual(s1, s2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compare method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        String s1 = "";
        int[] expResult = null;
        int[] result = AlgorithmProjectCheck.compare(s1);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swap method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        String s1 = "";
        String expResult = "";
        String result = AlgorithmProjectCheck.swap(s1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of differentChar method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testDifferentChar() {
        System.out.println("differentChar");
        String usersWord = "";
        String[] array = null;
        char[] c1 = null;
        Dictionary expResult = null;
        Dictionary result = AlgorithmProjectCheck.differentChar(usersWord, array, c1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editDistance method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testEditDistance() {
        System.out.println("editDistance");
        String s1 = "";
        String s2 = "";
        int expResult = 0;
        int result = AlgorithmProjectCheck.editDistance(s1, s2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check method, of class AlgorithmProjectCheck.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        String str = "";
        boolean expResult = false;
        boolean result = AlgorithmProjectCheck.check(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
