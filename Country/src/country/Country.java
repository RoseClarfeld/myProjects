/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;
import java.util.Comparator;
/**
 *
 * @author roseclarfeld
 */
/**
   A country with a name and area.
*/
public class Country implements Comparable<Country>
{
   /**
      Constructs a country.
      @param aName the name of the country
      @param anArea the area of the country
   */
   public Country(String aName, double anArea)
   {
      name = aName;
      area = anArea;
   }

   /**
      Gets the name of the country.
      @return the name
   */
   public String getName()
   {
      return name;
   }

   /**
      Gets the area of the country.
      @return the area
   */
   public double getArea()
   {
      return area;
   }


   /**
      Compares two countries by area.
      @param other the other country
      @return a negative number if this country has a smaller
      area than otherCountry, 0 if the areas are the same,
      a positive number otherwise
   */
   public int compareTo(Country other)
   {
      if (area < other.area) return -1;
      if (area > other.area) return 1;
      return 0;
   }  
   /**
    * 
    * @param increasing
    * @return 
    */
   public static Comparator<Country> createComparatorByName
        (final boolean increasing){ 
            return new Comparator<Country>() {
                public int compare(Country usa, Country mexico) {  
                     int order; 
                
                    if(increasing){ 
                        order = 1; 
                    }
                    else{
                        order = 1; 
                        
                    }
                    return order *usa.getName().compareTo(mexico.getName());
            }
            };
        } 
        
       /**
        * 
        * @param increasing
        * @return 
        */
   public static Comparator<Country> createComparatorbyArea
           (final boolean increasing){ 
                return new Comparator<Country>() {
                public int compare(Country usa, Country mexico) { 
                
                    if(increasing){ 
                        return usa.compareTo(mexico);
                    }
                    else{
                        return mexico.compareTo(usa); 
                        
                    }
             
           }
                };
                        }
           
           

   private String name;
   private double area;
}