package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    //check whether the method countNumbers() will throw NullPointerException if the string is null
    @Test(expected = NullPointerException.class)
    public void testCountNumbers2() {
    	mycustomstring.setString(null);
        mycustomstring.countNumbers();
    }

    //check whether countNumbers() accept a large number
    @Test
    public void testCountNumbers3() {
    	mycustomstring.setString("4503599627370496");
        assertEquals(1, mycustomstring.countNumbers());
    }
    //check whether countNumbers() run correctly if the string starts and end with a digit
    @Test
    public void testCountNumbers4() {
    	mycustomstring.setString("4neer45 gona gie 1314yu up6");
        assertEquals(4, mycustomstring.countNumbers());
    }
    //check whether countNumbers() will output 0 if the string is empty.
    @Test
    public void testCountNumbers5() {
    	mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }
    //check whether countNumbers() will run correctly if the string contain specific sign.
    @Test
    public void testCountNumbers6() {
    	mycustomstring.setString("(*0 3,67ô0-9'fds\\ɱ910.1");
        assertEquals(7, mycustomstring.countNumbers());
    }

    
    
    /**Test for the method testGetEveryNthCharacterFromBeginningOrEnd*/
    //regular test
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    //it will output following the order in which they appear in the string when startFromEnd = true
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
    	mycustomstring.setString("4ne$er45 go@nna give 1314y,oU,up6");
        assertEquals("$ nv1U", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
    	mycustomstring.setString("4ne$er45 go@nna give 1314y,oU,up6");
        assertEquals("egae4,", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, false));
    }
    
    //check whether the method will output empty if n > the length of the string
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
    	mycustomstring.setString("12345678910");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(12, true));
    }
    
    //check whether the method will output empty if the string is empty
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
    	mycustomstring.setString("");
        assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }
   
    //check the method will throw IllegalArgumentException if n = 0
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
    	mycustomstring.setString("12345678910");
        assertEquals("12345678910", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true));
    }

    //check whether the method runs correctly after multiple modified
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        String testString = "";
    	for(int i = 1; i <= 10 ; i++) {
    		mycustomstring.setString(testString);
    		testString += i;
    	}
        assertEquals("123456789", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    //when the string consists of space
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
    	mycustomstring.setString("           ");
        assertEquals("  ", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
    	mycustomstring.setString("IllegalArgumentException");
        assertEquals("eAmEpn", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    //check the method will throw IllegalArgumentException even if empty string with n = 0
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
    	mycustomstring.setString("");
    	assertEquals("",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, false));
    }

    //check the IllegalArgumentException if the n <=0
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
    	mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-1, false);
    }
    
    //n <= 0, it will throw IllegalArgumentException even if the string is null
    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
    	mycustomstring.setString(null);
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-1, true);
    }

    //the string is null and n > 0
    @Test(expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
    	mycustomstring.setString(null);
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false);
    }
    
    
    /**Tests for the method ConvertDigitsToNamesInSubstring()*/
    @Test
    public void testConvertDigitsToNamesInSubstring1() {
       mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
       mycustomstring.convertDigitsToNamesInSubstring(17, 23);
       assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
   }

   //it will output the origin string if the string does not contain digits
    @Test
    public void testConvertDigitsToNamesInSubstring2() {
    	 mycustomstring.setString("I'd better put some digits in this string, right?");
         mycustomstring.convertDigitsToNamesInSubstring(17, 23);
         assertEquals("I'd better put some digits in this string, right?", mycustomstring.getString());
    }

    //covert all digits from the string
    @Test
    public void testConvertDigitsToNamesInSubstring3() {
    	mycustomstring.setString("4ne$er45 9");
        mycustomstring.convertDigitsToNamesInSubstring(1,10);
        assertEquals("Fourne$erFourFive Nine", mycustomstring.getString());
    }

    //check the method will throw MyIndexOutOfBoundsException 
    //if startPosition < endPosition but both are less than zero 
    @Test(expected = MyIndexOutOfBoundsException.class )
    public void testConvertDigitsToNamesInSubstring4() {
    	mycustomstring.setString("4ne$er45 go@nna give 1314y,oU,upn");
        mycustomstring.convertDigitsToNamesInSubstring(-1,0);
    }

    //check the method will throw IllegalArgumentException 
    //when "startPosition" > "endPosition", even if the string is null
    @Test(expected = IllegalArgumentException.class )
    public void testConvertDigitsToNamesInSubstring5() {
    	mycustomstring.setString("null");
        mycustomstring.convertDigitsToNamesInSubstring(24,22);
    }
    
    //check the method will throw MyIndexOutOfBoundsException
    //If "startPosition" and "endPosition" are greater than the string
    @Test(expected = MyIndexOutOfBoundsException.class )
    public void testConvertDigitsToNamesInSubstring6() {
       	 mycustomstring.setString("");
         mycustomstring.convertDigitsToNamesInSubstring(17, 23);
         assertEquals("", mycustomstring.getString());
    }
    
    //check the method will throw MyIndexOutOfBoundsException when startPosition and endPosition < 1, 
    //even if the string is null
    @Test(expected = MyIndexOutOfBoundsException.class )
    public void testConvertDigitsToNamesInSubstring7() {
    	mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(-2,0);
    }

    //check the method will throw NullPointerException 
    //If "startPosition" <= "endPosition" and both > 0 and the string is null
    @Test(expected = NullPointerException.class )
    public void testConvertDigitsToNamesInSubstring8() {
    	mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(4,5);
    }
}
