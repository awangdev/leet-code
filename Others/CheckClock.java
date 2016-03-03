import java.io.*;
import java.util.*;

/*
 R.L.
 12 spots to fill 4P, 4N, 4D. make sure they are not jumping onto each other.
 P: move 1 step.
 N: 5.
 D: 10
 */

//
class Solution {
  
  // Generate all valid 12-length strings
  public static void generateAll(ArrayList<String> solutions, String current, int p, int n, int d) {
    if (current.length() == 12) {
      solutions.add(current);
      return;
    }
    
    if (p > 0) generateAll(solutions, current + "P", p - 1, n, d);
    if (n > 0) generateAll(solutions, current + "N", p, n - 1, d);
    if (d > 0) generateAll(solutions, current + "D", p, n, d - 1);
  }
  
  
  // Return true whether the string solution is valid, or false otehrwise
  public static boolean isValid(String solution) {
    if (solution == null || solution.length() != 12)
      return false;
  
    boolean[] clock = new boolean[12];
    int pointer = 0;
    
    for (int c = 0; c < 12; c++) {
      if (clock[pointer])
        return false;

      int advance = 0;
      switch (solution.charAt(c)) {
        case 'P': advance = 1; break;
        case 'N': advance = 5; break;
        case 'D': advance = 10; break;
      }
      
      clock[pointer] = true;  
      pointer = (pointer + advance) % 12; 
    }
    
    for (int c = 0; c < 12; c++) {
      if (!clock[c])
        return false;
    }
    return true;
  }
  
  
  // Generate all valid strings, then filter them out and only print the valid ones
  public static void printSolution() {
    ArrayList<String> solutions = new ArrayList<String>();
    generateAll(solutions, "", 4, 4, 4);
    
    for (String s : solutions) {
      if (isValid(s))
        System.out.println(s);
    }
  }
  
  
  
  public static void main(String[] args) {
    printSolution();
    
    /*
    ArrayList<String> rst = validateClock();
    System.out.println(rst.size());
    for (String string : rst) {
      System.out.println(string);
    }
    */
  }
}



///Generate all possible solutions, then validate them all.