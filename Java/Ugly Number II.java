M
tags: Math, DP, Heap

非常brutle的。
每次把dp[i-1]拿出来，不管三七二十一，分别乘以2,3,5. 出来的结果放进priority queue做比较。
最后时间是n*log(n*3)

注意：
Long
HashSet确保没有重复。
```
/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.
*/

/*
	Thought:
	Could do a infinite while loop, check each number see if that's an ugly number; till we find the nth.
	time = m * O(avg # of factors for each integer) [m is probably a lot greater than n]~= O(m^2)

	So we definitely need to calculate it:
	if we know a number x is ugly, we can *2, *3, *5 to make furture ugly numbers.
	State:
		dp[i]: ith ugly number.
	fn:
		candidate: Priority queue to hold candiates.
		A HashSet() that makes sure dp[i-1]*2/3/5 is not duplicate.
		candidate.add(dp[i - 1] * 2)
		candidate.add(dp[i - 1] * 3)
		candidate.add(dp[i - 1] * 5)
		dp[i] = candidate.poll();
	init:
	dp[0] = 0;
	dp[1] = 1;

	return dp[n]

Note: some number * 5 could be long. Just make sure it's long, then convert to int at the end.
*/

public class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
        	return 0;
        } else if (n == 1) {
        	return 1;
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        PriorityQueue<Long> candidate = new PriorityQueue<Long>();
		HashSet<Long> set = new HashSet<Long>();
		set.add(dp[1]);
		for (int i = 2; i <= n; i++) {
			if (!set.contains(dp[i - 1] * 2)) {
				candidate.add(dp[i - 1] * 2);
				set.add(dp[i - 1] * 2);
			}
			if (!set.contains(dp[i - 1] * 3)) {
				candidate.add(dp[i - 1] * 3);
				set.add(dp[i - 1] * 3);
			}
			if (!set.contains(dp[i - 1] * 5)) {
				candidate.add(dp[i - 1] * 5);
				set.add(dp[i - 1] * 5);
			}
			dp[i] = candidate.poll();
		}

		return (int)dp[n];
    }
}
```