H
1518424818
tags: DP, Binary Search

#### 方法1: Binary Search
- 根据: 每个人花的多少时间(time)来做binary search: 每个人花多久时间, 可以在K个人之内, 用最少的时间完成?
- time variable的范围不是index, 也不是page大小. 而是[minPage, pageSum]
- validation 的时候注意3种情况: 人够用 k>=0, 人不够所以结尾减成k<0, 还有一种是time(每个人最多花的时间)小于当下的页面, return -1
- O(nLogM). n = pages.length; m = sum of pages.

#### 方法2: DP
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
Stupid way of asking 'slowest copier to finish at earliest time'. It does not make sense.
The question does not provide speed, what does 'slow/fast' mean? 
The question does not distinguish each copier, what does 'earliest' time mean?

Given the sample results, it's asking: 
what's the minimum time can be used to finish all books with given k people.

- 1 book cannot be split to give to multiple people
- cannot sort the book array

A person at minimum needs to finish 1 book, and a person at maximum can read all books.
should find x in [min, max] such that all books can be finished.
The person can read at most x mins, but don't over-read if next book in the row can't be covered within x mins.

It's find to not use up all copiers.
*/
public class Solution {
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int m = pages.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
        // Find minimum read mins
        for (int page : pages) {
            min = Math.min(min, page);
            sum += page;
        }
        
        int start = min;
        int end = sum;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2; // mid: total time spent per person
            int validation = validate(pages, mid, k);
            if (validation < 0) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (validate(pages, start, k) >= 0) {
            return start;
        } else {
            return end;
        }
    }
    
    /*
        Validate if all copies are utilized to finish.
        Return 0 if k used to finish books.
        Return negative needing more copier, which means value is too small.
        Return postivie, if value is too big, and copies are not utilized.
    */
    private int validate(int[] pages, int totalTimePerPerson, int k) {
        int temp = 0;
        for (int i = 0; i < pages.length; i++) {
            temp += pages[i];
            if (temp == totalTimePerPerson) {
                temp = 0;
                k--;
            } else if (temp < totalTimePerPerson) {
                if (i + 1 >= pages.length || temp + pages[i + 1] > totalTimePerPerson) {
                    // no next page; or not enough capacity to read next page
                    temp = 0;
                    k--;
                }
                // else: (i + 1 < pages.length && temp + pages[i + 1] <= totalTimePerPerson)
            } else {
                return -1;
            }
        }
        return k;
    }
}

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