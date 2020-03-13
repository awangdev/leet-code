 
 
 
## PreProduct (2)
**0. [152. Maximum Product Subarray.java](https://github.com/awangdev/LintCode/blob/master/Java/152.%20Maximum%20Product%20Subarray.java)**      Level: Medium      Tags: [Array, DP, PreProduct, Subarray]
      

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



---

**1. [238. Product of Array Except Self.java](https://github.com/awangdev/LintCode/blob/master/Java/238.%20Product%20of%20Array%20Except%20Self.java)**      Level: Medium      Tags: [Array, PreProduct]
      

给一串数字, output rst[n], 每个index是 除了nums[i]以外 所有itemd的乘积.

#### Array, PreProduct
- 分析普通做法, 了结到用从左到右一遍O(n), 从右到左一遍 O(n) 就可以
- 注意carry的维护
- 第一轮:PreProduct (跟preSum的感觉有点像)
    - PreProduct[i] stores product from num[0] -> num[i-1] (skipping current num[i])
    - init preProduct[i] = 1, as base for product
    - 错过一位操作: always `preProduct[i] *= carry;` and `carry *= nums[i]`
- 第二轮: 从右边乘起, 每次在index i, 收到的carry都是 `nums[i+1] *....* nums[end]`
    - 第一轮的结果 * 第二轮的结果, 刚好在index i 缺少掉 nums[i]. 如题所愿.
- Time: O(n)



---

