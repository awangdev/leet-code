M
1518159874

分割型. 考虑最后的数字: 要是12割个1出来, 剩下11怎么考虑? 割个4出来,剩下8怎么考虑?
就变成了dp = Min{dp[i - j^2] + 1}

时间复杂度: 乍一看是O(n*sqrt(n)). 实际也是. 但如何推导?
考虑sqrt(1) + sqrt(2) + ....sqrt(n):找这个的upper bound and lower bound.
最后发现它的两边是 A*n*sqrt(n) <= actual time complexity <= B*n*sqrt(n)
那么就是O(n*sqrt(n))啦

Previous Notes:
一开始没clue.看了一下提示。

１.　第一步想到了，从数学角度，可能是从最大的perfect square number开始算起。
２.　然后想法到了dp， 假设最后一步用了最大的maxSqrNum, 那么就在剩下的 dp[i - maxSqrNum^2] +１　不就好了？
３.　做了，发现有个问题．．．最后一步选不选maxSqrNum?  比如12就是个例子。
	然后就根据提示，想到BFS。顺的。 把1～maxSqrNum 都试一试。找个最小的。
	看我把12拆分的那个example. 那很形象的就是BFS了。
	面试时候，如果拆分到这个阶段不确定，那跟面试官陶瓷一下，说不定也就提示BFS了。

```
/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags Dynamic Programming Breadth-first Search Math
Hide Similar Problems (E) Count Primes (M) Ugly Number II

*/
/*
Thoughts:
Take the 12 example: to start with can be break down to (1, 11), (4, 8), (9, 3).
Then we need to find minNumSquares for 11, or for 8, or for 3, where we can verify that 11 and 3 does not have a perfect square root, so we need to go with (4, 8) -> (4, 4, 4) or (4, 1, 7) => (4, 4, 4)
let's say dp[i]: # of perfect square at i = Min{dp[i - j^2] + 1}, where 1<=j<=i
We are not sure what last number we could pick, so loop over 1<=j<=i to try all
O(n*sqrt(n))
*/
class Solution {
    public int numSquares(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) { // n
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) { // sqrt(i)
                if ((i - j * j) >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);    
                } else {
                    break; // if (i - j * j) < 0, there is no point to j++
                }
            }
        }
        return dp[n];
    }
}

/*
Thoughts:
	Math:
	num =13. sqrt(13) = 3.xxx. Floor() = 3. count++;//1
	num = 13 - 9 = 4. sqrt(4) = 2. No remaining. count++;//2
	DP:
	state
		dp[i]: min # of perfect square till i.
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1 + 1 = 2;
		dp[3] = 1,1,1;//3
		dp[4] = 2^2;//1
		dp[5] = dp[5 - floor(sqrt(5))^2] + 1;
	fn: 		//Pick the largest perfect square possible, then added on what's remaining's dp. Do a BFS on all possiblilities
		maxFlorNum = Math.floor(Math.sqrt(i))
								12				
			-3^2 = 3		-2^2 = 8			-1^2 = 11
			1 + dp[3]		1 + dp[8]			1 + dp[11]
		for (j = 0 ~ i)
			dp[i] = min(min, dp[i - j ^ 2] + 1)
	init:
		dp[0] = 0;
		dp[1] = 1;
	return dp[n];
*/



public class Solution {
    public int numSquares(int n) {
        if (n <= 0) {
        	return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
        	int maxSqrNum = (int)Math.floor(Math.sqrt(i));
        	int min = Integer.MAX_VALUE;
        	for (int j = 1; j <= maxSqrNum; j++) {
        		min = Math.min(min, dp[i - j * j] + 1);
        	}
        	dp[i] = min;
        }
        return dp[n];
    }
}


/*
//Test Cases
	dp[2] =2;
	dp[4] = 1
	dp[5] = 2;
	dp[6] = 2 + 1 =3;
	dp[7] = 3 + 1 = 4;
	dp[8] = dp[4] + 1 = 1 = 1 = 2;
	dp[9] = 1
	dp[10] = 1 + 1 = 2;
	dp[11] = 2 + 1 = 3
	dp[12] = dp[12 - 9] + 3
*/
```