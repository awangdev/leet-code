/*
Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...


Example
Given 1, return 0

Given 2, return 1

Given 10, return 34

Note
The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Tags Expand 
Enumeration Mathematics Non Recursion

Thoughts:
1. If non-recursion, do for loop for that n
2. Note: this specfiic problem is not 0-based. it's 1-based.
3. return fib[n]
*/

class Solution {
    /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        if (n <= 1) {
        	return 0;
        }
        int[] fib = new int[n + 1];
        fib[1] = 0;
        fib[2] = 1;
        for (int i = 3; i <= n; i++) {
        	fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}
