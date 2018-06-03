M
Tags: Greedy, Priority Queue

#### Priority Queue
- TODO: parse into node(index, digitValue)
- find the top k, and remove from char array
- O(nlogn) time

#### Greedy
- 数位靠前的，权值更大. 所以硬来把靠前的相对更大的（跟following digit相比）去掉。

```
/*
Given string A representative a positive integer which has N digits, remove any k digits of the number, 
the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N,

Example
Given an integer A = "178542", k = 4

return a string "12"

Tags Expand 
Greedy LintCode Copyright

*/

/*

Attempt2,Thoughts:
loop k times: each interation, find one digit to remove
Rules: want to remove whatever digit at A[i] that's A[i] > A[i+1].
Reason: Higher position (left side of the string) is always stronger/high number, and remove the strong/high digit will always be right option.
Well... thinking straight (attempt2) seems much easier to understand and to code up than my attempt1

Note:
remember to remove the prefixing 0's

*/
public class Solution {
    public String DeleteDigits(String A, int k) {
        if (A == null || A.length() == 0 || k == 0) {
            return A;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < A.length(); j++) {
                if (j == A.length() - 1) {
                    A = A.substring(0, j);
                    break;
                } else if (A.charAt(j) > A.charAt(j + 1)) {
                    A = A.substring(0, j) + A.substring(j + 1);
                    break;
                }
            }
        }
        //remote prefixing-0's
        int i = 0;
        while(i < A.length() && A.charAt(i) == '0') {
            i++;
        }
        return A.substring(i);
    }
}




/*
Attempt1: Lintcode 83% correct, but Does not work for : [9876141516171818818181890001988181700198181778786761256512651653145345143, 55]
my output: 	1111111134143
expect:		1111111345143

Not sure where went wrong.

Thoughts:
This seems to be: Pick (N - k) digits and make a smallest number, without changing the order of digits.
Create an array with length == (N - k): digits
Starting from i = 0, digits[0] = A.charAt[0] - '0'
if A[i] < digits[i] , replace digits[i] with A[i]
	Note: here loop through (N - k) and see if the A[i] can be put anywhere

Note: handle prefix '0' in string
*/
public class Solution {
    public static String DeleteDigits(String A, int k) {
    	if (A == null || A.length() == 0 || k < 0) {
    		return A;
    	}
    	int n = A.length() - k;
    	//System.out.println(A.length() + " " + n);
    	int[] digits = new int[n];
    	for (int j = 0; j < n; j++) {
    		digits[j] = -1;
    	}
    	int[] backup = Arrays.copyOf(digits, digits.length);
    	for (int i = 0; i < A.length(); i++) {
    		int digit = (int)(A.charAt(i) - '0');
    		
    		for (int j = 0; j < n; j++) {
    			if ((digit < digits[j] || digits[j] < 0) && (A.length() - i) >= (n - j)) {
    				//System.out.println(j + " " + digit + " | " + (A.length() - i) + " " + (n - j));
    				if (j == 0) {
    					digits = Arrays.copyOf(backup, backup.length);
    				}
    				digits[j] = digit;
    				break;
    			}
    		}
    	}
    	//System.out.println(Arrays.toString(digits));
    	String rst = "";
    	for (int j = 0; j < n; j++) {
    		if (rst.length() == 0 && digits[j] == 0) {
    			continue;
    		} else {
    			rst += digits[j];
    		}
    	}

    	return rst;
    }

}

```