/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Hide Company Tags Google
Hide Tags Array Math
Hide Similar Problems (M) Multiply Strings (E) Add Binary

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


/*
    if last index + 1 != 10, cool, just return.
    if so, loop from last index to i = 0. WHenever that point != 10, cool return.
    at the end if digits[0] != 10, cool return.
    However, it that is == 10, need to create a new array and hold everything
*/
public class Solution {
    public int[] plusOne(int[] digits) {
    	if (digits == null || digits.length == 0) {
    		return null;
    	}
    	//Add to in[] directly, and at the end check for int[i]==10
    	int n = digits.length;
    	digits[n - 1] += 1;
    	if (digits[n - 1] != 10) {
    	    return digits;
    	}
    	//Update and check each position
    	digits[n - 1] = 0;
    	for (int i = n - 2; i>= 0; i--) {
    	    digits[i] += 1;
    	    if (digits[i] != 10) {
    	        return digits;
    	    }
    	    digits[i] = 0;
    	}
    	//Create a array with length + 1
    	int[] rst = new int[n + 1];
    	rst[0] = 1;
    	rst[1] = 0;
    	for (int i = 2; i < rst.length; i++) {
    	    rst[i] = digits[i - 1];
    	}
    	return rst;
    }
}
/* Trivial solution
   create a secondary method func(int index, int[]digits).
   add check index from digits.length-1 to 0: digits[index]+1==10? 0 : digits[index]+1;
   if add up to 10, push into another level; if not ,return digits.
   if index==0, check if add up to 10. If ==10, create a new array and put 1 infront. else return digits.

*/
