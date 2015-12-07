/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.


Example
Given [1,2,3] which represents 123, return [1,2,4].

Given [9,9,9] which represents 999, return [1,0,0,0].

Tags Expand 
Array

*/

public class Solution {
    public int[] plusOne(int[] digits) {
        if(digits.length==0) return digits;
        
        digits[digits.length-1] += 1;
        //Check index digit.length-1 to 1
        for(int i = digits.length-1; i>0; i--){
            if(digits[i] == 10){
                digits[i]=0;
                digits[i-1]+=1;
            }
            else return digits;
        }
        
        //Check index 0. If ==0, set it to 0 and carry over 1
        if(digits[0]==10){
            int[] output = new int[digits.length+1];
            output[0] = 1;
            output[1] = 0;
            for(int i=2; i<output.length-1; i++){
                output[i]=digits[i-1];
            }
            return output;
        }
        else return digits;
    }
}

/* Trivial solution
   create a secondary method func(int index, int[]digits).
   add check index from digits.length-1 to 0: digits[index]+1==10? 0 : digits[index]+1;
   if add up to 10, push into another level; if not ,return digits.
   if index==0, check if add up to 10. If ==10, create a new array and put 1 infront. else return digits.

*/


/*

Thoughts: Old soluton .will fail LeetCode
It looks I should convert array to int, then add, and then convert back to array.
1. Convert to string: Arrays.toString(xxx);
2. Integer.parseInt(str)
3. add
4. split to int array


Note:
Int may not hold the rst since it could exceed 32 bits, so use Long.

But ... What if long does not work neither?
*/

public class Solution {
    /**
     * @param digits a number represented as an array of digits
     * @return the result
     */
    public int[] plusOne(int[] digits) {
    	if (digits == null || digits.length == 0) {
    		return null;
    	}
    	
    	String str = "";
    	for (int i = 0; i < digits.length; i++) {
    		str += digits[i];
    	}
    	long digit = Long.parseLong(str);
    	digit += 1;
    	str = digit + "";
    	int[] rst = new int[str.length()];
    	for (int i = 0; i < str.length(); i++) {
    		rst[i] = Character.getNumericValue(str.charAt(i));
    	}
    	return rst;
    }
}


