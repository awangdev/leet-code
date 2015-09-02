/*
The count-and-say sequence is the sequence of integers beginning as follows:

1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Example
Given n = 5, return "111221".

Note
The sequence of integers will be represented as a string.

Tags Expand 
String


1. Set up initial value '11'
2. use while loop to build on past variable
3. In each while loop case, break the string into charArray, count and name mark the type
4. In for loop: when different, append string (count+type); when same, count++.
*/


public class Solution {
    /**
     * @param n the nth
     * @return the nth sequence
     */
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String str = "11";
        int ind = 2;
        while (ind < n) {
        	StringBuffer sb = new StringBuffer();
        	char[] arr = str.toCharArray();
	        int count = 1;
	        int type = Character.getNumericValue(arr[0]);
	        for (int i = 1; i < arr.length; i++) {
	            if (arr[i] == arr[i - 1]) {
	                count++;
	            } else {
	                sb.append(count + "" + type);
	                type = Character.getNumericValue(arr[i]);
	                count = 1;
	            }
	        }
        	ind++;
        	sb.append(count + "" + type);
        	str = sb.toString();
        }
        return str;
    }
}
