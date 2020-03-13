M
tags: Array, DP, Subarray, PreProduct
time: O(n)
space: O(1)

从一组数列(正负都有)里面找一串连续的子序列, 而达到乘积product最大值.

#### Method1: DP, Two PreProduct array
- Continuous product can be positive/negative/zero
    - If nums[i] > 0, want prior largest product[i-1] * nums[i]
    - If nums[i] < 0, want prior smallest product[i-1] * nums[i]
    - If nums[i] == 0, product = 0
- `prior product[i-1]: 想到DP
    - 1. 正负数情况, 需要用两个 `PreProduct` array: minProduct[], maxProduct[]
    - 2. continuous prodct: it has to utilize curr nums[i]
        - 是跟nums[x]当下值比较的, 如果当下值更适合, 会舍去之前的continous product, 然后重新开始.
        - Use a global variable to hold overall result.
- Time/Space O (n)
- Space optimization, rolling array
    - maxProduct && minProduct 里面的 index i, 都只能 i - 1相关, 所以可以省去redundant operatoins
    - Time: O(n)
    - space: O(1)

#### Method2: hold `local max at index i` and `local min at index i`
- same concept as method1, but simplified: given that we always have to use nums[i], so only 1 result can be passed on
- FAST, simple to write and read
- time: O(n)
- space: O(1)

#### Failed attempt: `memo[i][j]` of continuous product from index i -> j
- working solution, BUT Time/Space complexity O(n^2) are too much

```
/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Tags Expand 
Dynamic Programming Subarray
*/
/*
Method1: 2 preProduct array; 'Largest', DP.
Consider positivie/Negative numbers.
- If nums[i] > 0, want prior largest product[i-1] * nums[i]
    - If nums[i] < 0, want prior smallest product[i-1] * nums[i]
    - If nums[i] == 0, product = 0
*/
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxProduct = new int[n], minProduct = new int[n];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > 0) {
                maxProduct[i] = Math.max(num, maxProduct[i - 1] * num);
                minProduct[i] = Math.min(num, minProduct[i - 1] * num);
            } else {
                maxProduct[i] = Math.max(num, minProduct[i - 1] * num);
                minProduct[i] = Math.min(num, maxProduct[i - 1] * num);
            }
            max = Math.max(max, maxProduct[i]);
        }
        return max;
    }
}

/*
Method1: improve with Rolling array
Space: O(1)
 */
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxProduct = new int[2], minProduct = new int[2];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > 0) {
                maxProduct[i % 2] = Math.max(num, maxProduct[(i - 1) % 2] * num);
                minProduct[i % 2] = Math.min(num, minProduct[(i - 1) % 2] * num);
            } else {
                maxProduct[i % 2] = Math.max(num, minProduct[(i - 1) % 2] * num);
                minProduct[i % 2] = Math.min(num, maxProduct[(i - 1) % 2] * num);
            }
            max = Math.max(max, maxProduct[i % 2]);
        }
        return max;
    }
}

// Method1 simplification
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxProduct = new int[n], minProduct = new int[n];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            // lazy writing: knowing what to look for, so just calculate regardless if num < 0
            maxProduct[i] = Math.max(num, Math.max(maxProduct[i - 1] * num, minProduct[i - 1] * num));
            minProduct[i] = Math.min(num, Math.min(minProduct[i - 1] * num, maxProduct[i - 1] * num));
            max = Math.max(max, maxProduct[i]);
        }
        return max;
    }
}


/*
Method2: hold `local max at index i` and `local min at index i`
- same concept as method1, but simplified: given that we always have to use nums[i], so only 1 result can be passed on
- time: O(n),
- space: O(1)
*/
class Solution {
    int maxProduct(int nums[]) {
        // store the result that is the max we have found so far
        int max = nums[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number nums[i]
        for (int i = 1, imax = max, imin = max; i < nums.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(nums[i], imax * nums[i]);
            imin = Math.min(nums[i], imin * nums[i]);

            // the newly computed max value is a candidate for our global result
            max = Math.max(max, imax);
        }
        return max;
    }
}

```