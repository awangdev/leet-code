import java.io.*;
import java.util.*;



/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

//
class Solution {
  
  
  public static ArrayList<String> validateClock() {
    //p:1, n:5, d: 10
    int[] nums = {1,1,1,1,5,5,5,5,10,10,10,10};
   	ArrayList<String> rst = new ArrayList<String>();
   	ArrayList<Integer> list = new ArrayList<Integer>();
 
    helper(rst, " ", 4,4,4);
    
    return rst;
    
  }
  
  public static void helper(ArrayList<String> rst, ArrayList<Integer> list, int[] nums, int p, int n, int d) {
  	if (p < 0 || n < 0 || d < 0) {
  		return;
  	}
    if (list.size() == nums.length) {
		if (validate(list)) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == 1) sb.append("P");
				if (list.get(i) == 5) sb.append("N");
				if (list.get(i) == 10) sb.append("D");
			}
	      	rst.add(sb.toString());
		}
    	return;
    } 

    for (int i = 0; i < nums.length; i++) {
    	if (nums[i] == 1 && p > 0) {
    		list.add(nums[i]);
    		helper(rst, list, nums, p - 1, n, d);
    	}
    	else if (nums[i] == 5 && n > 0) {
    		list.add(nums[i]);
    		helper(rst, list, nums, p, n - 1, d);
    	}

    	else if (nums[i] == 10 && n > 0) {
    		list.add(nums[i]);
    		helper(rst, list, nums, p, n, d - 1);
    	}
    	list.remove(list.size() - 1);
    }
    
  }
  
  public static boolean validate(String str) {//{p}, 0, c = 
    if (str == null || str.length() < 12) {
    	return false;
    }
    String[] arr = str.split(" ");
    String[] test = {" "," "," "," "," "," "," "," "," "," "," "," "};

    for (int i = 0; i < arr.length; i++) {
    	int num = Integer.parseInt(arr[i]);
    	
    	if (num + i >= 12) {
    		int index = (num + i) % 12;
    		if (!test[index].equals(" ")) {
    			return false;
    		} else {
    			test[i] = arr[i];
    		}
    	}
    }
    return true;
  }
  
  
  
  public static void main(String[] args) {
    ArrayList<String> rst = validateClock();
    
    for (String string : rst) {
      System.out.println(string);
    }
  }
}



///Generate all possible solutions, then validate them all.




