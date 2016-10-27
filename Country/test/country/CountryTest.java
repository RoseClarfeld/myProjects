/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roseclarfeld
 */
public class CountryTest {
    
    public CountryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Testing GetName method of class country
     */
    @Test
    public void testGetName() {
        System.out.println("JUnit test 4: Test of GetName");
        Country c = new Country("Rosevelt Island" , 666);
                System.out.println(c.getName());
        assertEquals("Rosevelt Island",c.getName());
       
     
    }

    /**
     * Test of getArea method, of class Country.
     */
    @Test
    public void testGetArea() {
     System.out.println("JUnit test 4: Test of GetArea");
        Country d = new Country("Rosevelt Island" , 666);
                System.out.println(d.getArea()); 
                double area = d.getArea();
        assertEquals( 666, area, 0);
        
       
    }
}


