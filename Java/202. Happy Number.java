E
tags: Hash Table, Math
time: O(m), m iterations
space: O(m), m number in set

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
User HashMap/HashSet to record the calculated number
*/
class Solution {
    public boolean isHappy(int n) {
        Set<Long> set = new HashSet<>();
        long sum = n;
        set.add(sum);
        
        while(sum != 1) {
            sum = process(sum); // 1 + 81 = 82
            if(!set.add(sum)) return false; // 82, 68, 100, 1
        }
        return true;
    }

    private long process(long n) {
        long sum = 0;
        while (n != 0) {
            long mod = n % 10;
            sum +=  mod * mod;
            n /= 10;
        }
        return sum;
    }
}

```