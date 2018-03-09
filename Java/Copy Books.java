R
1518424818
tags: DP

k个人copy完i本书.
定义Integer.MAX_VALUE的地方需要注意.
Review: 为什么有i level的iteration? Chapter4.1

```
/*
Given n books and the ith book has A[i] pages. 
You are given k people to copy the n books.

n books list in a row and each person can claim a continous range of the n books. 
For example one copier can copy the books from ith to jth continously, 
but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book.
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

*/


/*
Thoughts:
Considering k people finishing i books.
If last person read some books, then all the rest people just need to:
k - 1 people to finish j books, and the kth person read [j, j + 1, ..., i-1] books

Overall possible time spent is determined by the slowest person(bucket theory):
Either dp[k - 1][j] or (pages[j] + pages[j + 1] + ... + pages[i - 1])
= Max{dp[k - 1][j], pages[j] + pages[j + 1] + ... + pages[i - 1]}

Of course we weant to minimize the overall time spent:
take min of the above equation, where j = 0 ~ i

dp[k][i]: time spent for k people to read i books.
dp[k][i] = Min{Max{dp[k - 1][j] + sum(pages[j ~ i-1])}}, where j = 0 ~ i

Return dp[k][i]

init: dp[0][0] = 0;  time spent for 0 people copy 0 books

*/
public class Solution {
    public int copyBooks(int[] pages, int K) {
        if (pages == null || pages.length == 0) {
            return 0;
        }

        int n = pages.length;
        if (K > n) {
            K = n;
        }
        int[][] dp = new int[K + 1][n + 1];

        //dp[0][0~n] = Integer.MAX_VALUE; // 0 people read n books
        dp[0][0] = 0; // 0 people read 0 book
        for (int i = 1; i <= n; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        
        for (int k = 1; k <= K; k++) { // k people
            for (int i = 1; i <= n; i++) {// read i books
                int sum = 0;
                dp[k][i] = Integer.MAX_VALUE;
                for (int j = i; j >= 0; j--) { // person k read from j -> i
                    dp[k][i] = Math.min(dp[k][i], Math.max(dp[k - 1][j], sum));
                    if (j > 0) {
                        sum += pages[j - 1];
                    }
                }
            }
        }

        return dp[K][n];
    }
}
```