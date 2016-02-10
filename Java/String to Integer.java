/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
*/

/*
Thoughts:
Ask for requirement, and ajust the code:
Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
        	return 0;
        }
        String sign = "";
        String digits = "";
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
        	if (digits.length() == 0 && sign.length() == 0) {
        		if (arr[i] == '+' || arr[i] == '-') {
        			sign = arr[i] + "";
        			continue;
        		} else if (arr[i] == ' ') {
        			continue;
        		} else if (arr[i] < '0' || arr[i] > '9') {
        			break;
        		}
        	}//END: integer haven't begin, but can take '+/-' and skip space

        	if (arr[i] >= '0' && arr[i] <= '9') {
        		digits += arr[i];
        	} else {
        	    break;
        	}
        }
        if (digits.length() == 0) {//Check if empty()
            return 0;
        }
        if (digits.length() > 10) {//Check if we have more than 10 digits. It's here because it can exceed Long's MAX as well.
            return sign.equals("-") ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }
        long rst = Long.parseLong(digits) * (sign.equals("-") ? -1 : 1);
        if (rst > Integer.MAX_VALUE || rst < Integer.MIN_VALUE ) {//Double check if digits is within Integer's MAX and MIN
        	return rst > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int)rst;//Convert long to int
    }
}