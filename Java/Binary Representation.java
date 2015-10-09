/*
Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Have you met this question in a real interview? Yes
Example
For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".

Tags Expand 
String Cracking The Coding Interview Bit Manipulation

Thoughts:
Expan the value for binary representation:
8, 4, 2, 1, 0.5, 0.25 ... etc
3, 2, 1, 0, -1, -2, ... etc

1. Think of a good method to split the number into front . end part.
2. Deal with integer transforming into binary: integer divided by 2 see if != 0 
3. Deal with double transforming into binary: times 2 see if >= 1

Split: split string by '.'.
Checkfloat first: if float part is '0' or "", can just move on the integer part.

Note: str.split("\\.")
Note2: use a set to prevent infinite loop on float: for example: 2x - 1 = x -> x = 1. that will cause infinite loop.
*/
public class Solution {
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
    	if (n.length() == 0 || n.equals("0")) {
    		return "0";
    	}
    	if (n.indexOf(".") == -1) {
    		return parseInteger(n);
    	}
    	String[] strs = n.split("\\.");
    	String decimal = parseDecimal(strs[1]);
    	if (decimal.equals("ERROR")) {
    		return decimal;
    	}
    	if (decimal.length() == 0 || decimal.equals("0")) {
    		return parseInteger(strs[0]);
    	} else {
    		return parseInteger(strs[0]) + "." + decimal;
    	}
    }

    public String parseInteger(String n) {
    	if (n.length() == 0 || n.equals("0")) {
    		return n;
    	}
    	int num = Integer.parseInt(n);
    	String rst = "";
    	while (num != 0) {
    		rst = num % 2 + rst;
    		num = num / 2;
    	}
    	return rst;
    }

    public String parseDecimal(String n) {
    	if (n.length() == 0 || n.equals("0")) {
    		return "";
    	}
    	double num = Double.parseDouble("0." + n);
    	HashSet<Double> set = new HashSet<Double>();
    	String rst = "";
    	while (num > 0) {
    		if (rst.length() > 32 || set.contains(num)) {
    			return "ERROR";
    		}
    		set.add(num);
    		if (num * 2 >= 1) {
    			rst = rst + "1";
    			num = num * 2 - 1;
    		} else {
    			rst = rst + "0";
    			num = num * 2;
    		}
    	}
    	return rst;
    }
}