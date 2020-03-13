M
1529974964
tags: Array, PreSum, Hash Table

给一个int[][] matrix, 找一个sub matrix, where the sum == 0.

#### PreSum的思想
- 算出一个右下角点(i,j)到(0,0)的大小: 上一块 + 左一块 + curr node - overlap area
- preSum[i][j]: sum from (0,0) to (i-1,j-1)
- same approach as `subarray sum`: use hashmap to store diff->index; if diff re-appears, that means sum of 0 has occurred
- sequence of calculation: 1. iterate over start row. 2. iterate over end row. 3. iterate over col number (this is where hashmap is stored based on)
- the iteration over col is like a screening: find previous sum and determine result
- Note: 其实并没有真的去找 `== 0` 的解答,而是根据特性来判断 `剩下的/后来加上的一定是0`

```
/*
Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

Example:
[
  [1 ,5 ,7],
  [3 ,7 ,-8],
  [4 ,-8 ,9],
]
return [(1,1), (2,2)]

Challenge
O(n^3) time.
*/


class Solution {
    public int[][] submatrixSum(int[][] nums) {
        int[][] rst = new int[2][2];
        // check input
        if (validateInput(nums)) {
            return rst;
        }

        int m = nums.length, n = nums[0].length;

        // calculate presum
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i+1][j+1] = preSum[i][j+1] + preSum[i+1][j] + nums[i][j] - preSum[i][j];
            }
        }

        // iterations
        for (int start = 0; start < m; start++) {
            for (int end = start + 1; end <= m; end++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int col = 0; col <= n; col++) {
                    int diff = preSum[end][col] - preSum[start][col];
                    if (map.containsKey(diff)) {
                        rst[0][0] = start;
                        rst[0][1] = map.get(diff);
                        rst[1][0] = end - 1;
                        rst[1][1] = col - 1;
                        return rst;
                    }
                    map.put(diff, col);
                }
            }
        }

        return rst;
    }

    private boolean validateInput(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return true;
        }
        return false;
    }
}



```