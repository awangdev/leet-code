M
1516863852
tags: Binary Search, DP, Sequence DP, Coordinate DP, Memoization

无序数组, 找最长的上升(不需要连续)数组 的长度. 先做O(n^2), 然后可否O(nLogN)?

#### DP, double for loop, O(n^2)
- 考虑nums[i]结尾的时候, 在[0, i) 里count有多少小于nums[i]
- 对于所有 i in [0, n), 最常的increasing序列有多少length?
- max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
- 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
- dp[i] = Maht.max(dp[i], dp[j] + 1); j = [0 , i - 1]
- 时间复杂度  O(n^2)


#### O(nLogN)
- 维持一个list of increasing sequence
- 这个list其实是一个base-line, 记录着最低的increasing sequence.
- 当我们go through all nums的时候, 如果刚好都是上升, 直接append
- 如果不上升, 应该去list里面, 找到最小的那个刚好大于new num的数字, 把它换成num
- 这样就完成了baseline. 举个例子, 比如替换的刚好是在list最后一个element, 等于就是把peak下降了, 那么后面其他的数字就可能继续上升.
- '维护baseline就是一个递增的数列' 的证明, 还没有仔细想.

```

/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, 
it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */

/*
Thoughts:
Want to the max increasing sequence length. For example, if nums[i] > nums[i - 1], count++.
To find all count through [0 ~ i] history, need to calculate them all and count the maximum.
dp[i]: represents the max increasing sequence length for nums[i].
Note: not 'up to i', it's just specifically calculated for index i: nums[i]

Trying to compare all prior values:
dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1, where j = [0, i). 

dp[0]: max index count should = 0 since only 1 element in the race.
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n]; // dp[0] = 0
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max + 1; // 连续增长了max次, 序列长度为 max+1
    }
}


/*
Thoughts:
O(nLogN) using binary serach.
Maintain a list of nums in increasing order.
When considering new num:
- See if it can append (num > last-max-num from the list)
- If not, do binary search with the list and see where the number may fit.
- Every time, set num to where may fit in the list (find the smallest item from list which also > num)

Why setting a number in the list?
The list works as a baseline, which adjusts dynamically: any number less than the baseline won't be able to append.
However, it can hellp refine (lower) the baseline, which potentially allow future number to append.

In the end, return the size of list.
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (list.size() == 0 || nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
            } else {
                int index = binarySearch(list, nums[i]);
                list.set(index, nums[i]);
            }
        }
        return list.size();
    }
    
    public int binarySearch(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return list.get(start) >= target ? start : end;
    }
}
```