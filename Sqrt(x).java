/*
Implement int sqrt(int x).

Compute and return the square root of x.

Example
sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3
Challenge
O(log(x))

Tags Expand 
Binary Search

Thinking process:
Binary search. While loop until the head and tail meets.
*/

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
       long start = 0;
       long end = x;
       while (end >= start) {
           long mid = start + (end - start) / 2;
           if (mid * mid > x) {
               end = mid - 1;
           } else if (mid * mid < x) {
               start = mid + 1;
           } else {
               return (int)mid;
           }
       }
	//When start > end, while loop ends. That means, end must be the largest possible integer that end^2 is closest to x.
       return (int)end;
    }
}
