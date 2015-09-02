/*
Given two binary strings, return their sum (also a binary string).

Example
a = 11

b = 1

Return 100

Tags Expand 
String Binary



//Thougths:
1. Turn string binary format into integer
2. add integer
3. turn integer into binary string
Note: this just test if we know how to manipulate string/binary/Integer
*/


public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return null;
        }
        int decimalA = Integer.parseInt(a, 2);
        int decimalB = Integer.parseInt(b, 2);
        
        int sum = decimalA + decimalB;
        
        return Integer.toBinaryString(sum);
    }
}
