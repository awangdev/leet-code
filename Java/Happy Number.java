E

Basic Implementation of the requirements.

用HashSet存查看过的数值。若重复，return false.

```
/*
Write an algorithm to determine if a number is happy.

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example
19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
Tags Expand 
Hash Table Mathematics
*/

/*
    Thoughts:
    Try some examples then find out: if it's not happy number, the 'sum of square of its digits' will
    repeatedly occur. Use hashset to track existance.
*/
public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        long sum = n;
        HashSet<Long> set = new HashSet<Long>();
        while (sum != 1) {
            String s = String.valueOf(sum);
            sum = 0;
            for (char c : s.toCharArray()){
                sum += (c-'0')*(c-'0');
            }
            if (set.contains(sum)) {
                return false;   
            } else {
                set.add(sum);
            }
        }
        return true;
    }
}





```