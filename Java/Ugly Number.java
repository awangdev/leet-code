M
1527833857
tags: Math

LeetCode: 判断数字是否是ugly number. (definition: factor only have 2, 3, 5)

#### Math
- 看是否可以整除. 
- 看整除最终结果是否== 1

LintCode: 找kth ugly number, 应该与 Ugly Number II是一样的

- 方法1: PriorityQueue排序。用ArrayList check 新的ugly Number是否出现过。
- 方法1-1：(解释不通，不可取)用PriorityQueue排序。神奇的3，5，7走位：按照题目答案的出发，定了3，5，7以什么规律出现。但是题目并没有特殊表明。
- 方法2: DP . Not Done yet.


```
/*
LeetCode
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:

Input: 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2
Example 3:

Input: 14
Output: false 
Explanation: 14 is not ugly since it includes another prime factor 7.
Note:

1 is typically treated as an ugly number.
Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].

*/

class Solution {
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
}


/*
LintCode
Ugly number is a number that only have factors 3, 5 and 7.

Design an algorithm to find the Kth ugly number. The first 5 ugly numbers are 3, 5, 7, 9, 15 ...

Example
If K=4, return 9.

Challenge
O(K log K) or O(K) time.

Tags Expand 
LintCode Copyright Priority Queue


*/

/*

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

//Ignore the sequence of 3, 5, 7. Use arraylist to check for duplicates
class Solution {
    /**
     * @param k: The number k.
     * @return: The kth prime number as description.
     */
    public long kthPrimeNumber(int k) {
    	if (k == 0) {
    		return 0;
    	}
        ArrayList<Long> set = new ArrayList<Long>();
    	PriorityQueue<Long> queue = new PriorityQueue<Long>();
    	queue.offer((long)3);
    	queue.offer((long)5);
    	queue.offer((long)7);
    	set.add((long)3);
    	set.add((long)5);
    	set.add((long)7);
    	long num = 0;
		for (int i = 0; i < k; i++) {
			num = queue.poll();
			
			if (!set.contains(num * 3)) {
			    queue.offer(num * 3);
			    set.add(num * 3);
			}
			if (!set.contains(num * 5)) {
			    queue.offer(num * 5);
			    set.add(num * 5);
			}
			if (!set.contains(num * 7)) {
			    queue.offer(num * 7);
			    set.add(num * 7);
			}
		}
		return num;
    }
};

/*
Can use DP as well:http://blog.welkinlan.com/2015/07/28/ugly-number-lintcode-java/
*/
```