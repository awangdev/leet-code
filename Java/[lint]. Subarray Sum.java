E
tags: Array, Hash Table, PreSum, Subarray, Lint
time: O(n)
space: O(n)

给一串数字, 找其中的一个subarray的 [start, end] index, 条件: subarary sum == 0.

#### Hash Table
- `subarray sum equals k` 的简单版: k = 0
    - 求preSum, 然后不断check `map.containsKey(preSum - k)`. 
    - 如果 `priorSum = preSum - k == 0`, 说明 [priorSum.index + 1, curr index] 就是我们要找的这一段

#### Previous notes, same preSum + map solution
- 分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum[a+1 ~ b] == 0
- 用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组.

```
/*
Given an integer array, find a subarray where the sum of numbers is zero. 
Your code should return the index of the first number and the index of the last number.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

Note
There is at least one subarray that it's sum equals to zero.

Tags Expand 
Subarray Hash Table
*/
public class Solution {
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(preSum, -1);
        //we know that sub-array (a,b) has zero sum if SUM(0 ... a-1) = SUM(0 ... b)
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum)) {
                rst.add(map.get(preSum) + 1);
                rst.add(i);
                return rst;
            }
            map.put(preSum, i);
        }
        return rst;
    }
}


/*
Thougths:
Record the sum from (0 ~ a).
Check sum on each index i, when found an existing sum in the hashMap, we are done.
Reason:
If adding all the numbers together, for example if sum[0 ~ a] = -3, ... sum[0 - b] = -3 again, a<b
That means from a ~ b, there is not change: that is, sum[a - b] = 0.
As result, hashmap.get(a)+1 will be the satrting index, and b will be ending index.
*/


```