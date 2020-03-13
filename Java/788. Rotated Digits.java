E
tags: String, Basic Implementation
time: O(n)
space: O(n)

#### Basic Implementation of the rules
- [3,4,7] -> cannot rotate, failures. Must NOT have. set1
- 2,5,6,9 -> good candidates. Must have 1. set2
- [0,1,8] -> goes back to itself. can have
- loop over [1, N], count=int[10] appearance.
    - set1 meet 0
    - set2 meet at least 1
    
```
/*
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:

N  will be in range [1, 10000].
*/

/*
- [3,4,7] -> cannot rotate, failures. Must NOT have. set1
- 2,5,6,9 -> good candidates. Must have 1. set2
- [0,1,8] -> goes back to itself. can have
- loop over [1, N], count=int[10] appearance.
    - set1 meet 0
    - set2 meet at least 1
*/
class Solution {
    public int rotatedDigits(int N) {
        
        int start = 1;
        int count = 0;
        while (start++ < N) {
            count += validate(freq(start)) ? 1 : 0;
        }
        return count;
    }
    
    private int[] freq(int num) {
        int[] count = new int[10];
        while (num != 0) {
            count[num%10]++;
            num /= 10;
        }
        return count;
    }
    private boolean validate(int[] count) {
        int failure = count[3] + + count[4] + count[7];
        if (failure != 0) return false;
        int success = count[2] + + count[5] + count[6] + count[9];
        return success != 0;
    }
}
```