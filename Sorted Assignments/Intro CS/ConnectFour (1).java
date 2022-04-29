/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;


import java.util.Scanner;

/**
 *
 * @author yordanosmogos
 */
public class ConnectFour(1) {
    
  public static void main (String[] args) {
      // DON'T MODIFY THE MAIN METHOD UNLESS FOR DEBUGGING
      //MAKE SURE YOU GET RID OF YOUR MODIFICATIONS HERE BEFORE SUBMISSION

    String[][] board = createEmptyBoard();

    Scanner input = new Scanner(System.in);

    boolean bl = true;

    printPattern(board);

    while(bl) {
        
        int player1 = 1 , player2 = 2 ,  userInput;
       
       
       System.out.println("Please drop a RED disk at the column between 0 and 6:");
        userInput = input.nextInt();
        dropDisk(board, userInput , player1);
        printPattern(board);
      
       System.out.println("Please drop a YELLOW disk at the column between 0 and 6:");
       userInput  = input.nextInt();
       dropDisk(board, userInput , player2);
       printPattern(board);  
          

        String win = checkWinner(board);
        /*
        Write code to announce  if there is  winner and end the game
        */

      
  } // end of while loop

} // end of main
  
  
  public static String[][] createEmptyBoard() {
       /* This method prints the first empty pattern for the game
       DON'T MODIFY THIS METHOD
       */
    
        String[][] f = new String[7][15];
        for (int i =0;i<f.length;i++) {
              for (int j =0;j<f[i].length;j++) {

                 if (j% 2 == 0) f[i][j] ="|";
                 else f[i][j] = " ";
                 if (i==6) f[i][j]= "-";
             }
           }
        
        return f;
        
   } // end of createEmptyBoard 
  
  
  public static void printPattern(String[][] brd) {
  //Write your code here to print an updated pattern
  
  
  } // end of printPattern
  
  
   public static void dropDisk(String[][] brd, int positin, int player) {
       
       /*Write your code to drop the disk at the position the user entered 
       depending on which player*/ 
       
   } // end of dropDisk
  
   
   public static String checkWinner(String[][] brd) {
       
       /*Write your code to check if there is a winner. If there is, then
       return the charaster of the winners color( withe R or Y)
       */
       String str = " ";
       
       
       
       
       return str;
   } // end of checkWinner
   
   
   
   
   
   
} // end of class
