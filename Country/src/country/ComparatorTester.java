/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import java.util.*;
/*
Rose Clarfeld 
*/

public class ComparatorTester
{
   public static void main(String[] args)
   {
      ArrayList<Country> countries = new ArrayList<Country>();
      countries.add(new Country("Uruguay", 176220));
      countries.add(new Country("Thailand", 514000));
      countries.add(new Country("Belgium", 30510));
 
      
      System.out.println("Given List of Countries and Their Areas ");
      for (Country c : countries)
        System.out.println(c.getName() + " " + c.getArea());
     
      Collections.sort(countries, Country.createComparatorByName(true));
      System.out.println("\n Countries Sorted by Name in Ascending Order"); 
      for (Country c : countries){ 
          System.out.println(c.getName() + " "+ c.getArea());
      }
      Collections.sort(countries, Country.createComparatorByName(false));
      System.out.println("\n Countries Sorted by Name in Decending Order"); 
      for (Country c : countries){ 
          System.out.println(c.getName() + " "+ c.getArea());
      }
      Collections.sort(countries, Country.createComparatorbyArea(true));
      System.out.println("\n Countries Sorted by Area in Ascending Order"); 
      for (Country c : countries){ 
          System.out.println(c.getName() + " "+ c.getArea());
      }
      Collections.sort(countries, Country.createComparatorbyArea(false));
      System.out.println("\n Countries Sorted by Area in Decending Order"); 
      for (Country c : countries){ 
          System.out.println(c.getName() + " "+ c.getArea());
      }
         // Now the array list is sorted by area
      
   }
}
