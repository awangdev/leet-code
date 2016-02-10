/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.


Hide Tags Math
Hide Similar Problems (E) Happy Number (E) Count Primes (M) Ugly Number II

*/
/*
	Thoughts:
	Find all prime factors, check if other factors exist rather than 2,3,5.
	3 while loops.
	If num % 5 = 0 , keep it going.
	If num % 3 = 0, keep it going.
	If num % 2 = 0, keep it going ...
	At the end, it should == 1. If not, return false;
*/


public class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) {
        	return false;
        } else if (num == 1) {
        	return true;
        }

        while (num != 0 && num % 5 == 0) {
        	num = num / 5;
        }
        while (num != 0 && num % 3 == 0) {
        	num = num / 3;
        }
        while (num != 0 && num % 2 == 0) {
        	num = num / 2;
        }
        return num == 1;
    }
}