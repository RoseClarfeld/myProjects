/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.program;

/**
 *
 * @author moclarfeld
 */
public class SimpleProgram { 
  
 
  public static int simpleProgram (int num){
       int numby = 0; 
   
        numby= num + num; 
        
  
       return numby; 
       
        
  }
 

 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
int inty; 
 inty= 5; 
 simpleProgram(inty); 
 System.out.println(simpleProgram(inty));



        // TODO code application logic here
    }
    
}
