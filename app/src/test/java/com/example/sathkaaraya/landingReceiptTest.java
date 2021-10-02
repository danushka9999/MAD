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

    @Test

    public void add(){
        int val=100;
        assertEquals(100,val);
    }

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










}
