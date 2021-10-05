package com.example.sathkaaraya;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class landingReceiptTest {
    private landingReceipt landingreceipt;

    @Before
    public void setup(){
        landingreceipt=new landingReceipt();
    }

    //test implemented by S.M.A.D.Samarakoon IT20233808

    @Test
    public void testCalBronze(){
        int value=landingreceipt.testFoodPrice("bronze",10);
        assertEquals(15000,value);
    }
    @Test
    public void testCalGold(){
        int value=landingreceipt.testFoodPrice("gold",10);
        assertEquals(25000,value);
    }

    //test implemented by T.M.Y.M.Bandara IT20492052
    @Test
    public void testCalPlatinum(){
        int value=landingreceipt.testFoodPrice("platinum",10);
        assertEquals(35000,value);
    }

    @Test
    public void testCalLite(){
        int value=landingreceipt.testFoodPrice("lite",10);
        assertEquals(10000,value);
    }

    //test implemented by R.A.D.M.Ranasignhe IT20244552

    @Test
    public void testGymLite(){
        int value= landingreceipt.testGymPrice("lite",10);
        assertEquals(10000,value);
    }

    @Test
    public void testGymBronze(){
        int value= landingreceipt.testGymPrice("bronze",10);
        assertEquals(15000,value);
    }

    //test implemented by N.A.C.Gavindya IT20409982
    @Test
    public void testGymGold(){
        int value= landingreceipt.testGymPrice("gold",10);
        assertEquals(25000,value);
    }
    @Test
    public void testGymPlatinum(){
        int value= landingreceipt.testGymPrice("platinum",10);
        assertEquals(32000,value);
    }










}
