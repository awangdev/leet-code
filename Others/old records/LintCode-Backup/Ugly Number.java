/*
Ugly number is a number that only have factors 3, 5 and 7.

Design an algorithm to find the Kth ugly number. The first 5 ugly numbers are 3, 5, 7, 9, 15 ...

Example
If K=4, return 9.

Challenge
O(K log K) or O(K) time.

Tags Expand 
LintCode Copyright Priority Queue

Thoughts:
Every level it's like:
3		5		7
3		3,5		3,5,7

Use a priority queue to keep track. 
Use a for loop to keep calculating the target number, and return it at the end

Note:
Why not offer 3,5, 7 in first if statement? (Which is my original thought). Maybe, we want to limit the number of offers in 3's case, in case some 3's cases becomes bigger than 5's case. That, will accidentally prevent the program to check on 5's.
Therefore, leave 3,5,7 cases till 7's .

*/

class Solution {
    /**
     * @param k: The number k.
     * @return: The kth prime number as description.
     */
    public long kthPrimeNumber(int k) {
    	if (k == 0) {
    		return 0;
    	}
    	PriorityQueue<Long> queue = new PriorityQueue<Long>();
    	queue.offer((long)3);
    	queue.offer((long)5);
    	queue.offer((long)7);
    	long num = 0;
		for (int i = 0; i < k; i++) {
			num = queue.poll();
			if (num % 3 == 0) {
				queue.offer(num  * 3);
			} else if (num % 5 == 0) {
			    queue.offer(num  * 3);
				queue.offer(num  * 5);
			} else if (num % 7 == 0) {
				queue.offer(num  * 3);
				queue.offer(num  * 5);
				queue.offer(num  * 7);
			}
		}
		return num;
    }
};


/*
Can use DP as well:http://blog.welkinlan.com/2015/07/28/ugly-number-lintcode-java/
*/