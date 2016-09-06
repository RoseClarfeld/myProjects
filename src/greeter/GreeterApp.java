/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greeter;

/**
 * GreeterApp runs the methods created in Greeter 
 * @author moclarfeld
 */
public class GreeterApp {
    
    public static void main(String[] args) {
      Greeter worldGreeter = new Greeter("World");
      String greeting = worldGreeter.sayHello(); 
      System.out.println(greeting);
      Greeter Lizard = new Greeter("Sylvan"); 
      Lizard.setName("Sylvester");
      String lizzie = Lizard.sayHello();
      System.out.println(lizzie);
      Greeter pancakes = new Greeter("Butter");
      Greeter cereal = new Greeter("Milk");  
      String firstBreakfast = pancakes.sayHello();
      String secondBreakfast = cereal.sayHello();
      System.out.println(firstBreakfast);
      System.out.println(secondBreakfast);
      pancakes.swapNames(cereal);
      
      String thirdBreakfast = pancakes.sayHello();
      String fourthBreakfast = cereal.sayHello();
      System.out.println(thirdBreakfast);
      System.out.println(fourthBreakfast);
      
     
      
    }
    
}
