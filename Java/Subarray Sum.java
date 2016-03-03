E

分析出，如果sum[0~a]=x, 然后sum[0~b]=x, 说明sum(a ~ b] = 0.    

    这样理解后，用hashMap存每个sum[0~i]的值和index i. 如果有重复，就找到了一组sum为0的数组。

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

/*
Thougths:
Record the sum from (0 ~ a).
Check sum on each index i, when found an existing sum in the hashMap, we are done.
Reason:
If adding all the numbers together, for example if sum[0 ~ a] = -3, ... sum[0 - b] = -3 again, a<b
That means from a ~ b, there is not change: that is, sum[a - b] = 0.
As result, hashmap.get(a)+1 will be the satrting index, and b will be ending index.
*/
public class Solution {

    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        //we know that sub-array (a,b) has zero sum if SUM(0 ... a-1) = SUM(0 ... b)
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                rst.add(map.get(sum) + 1);
                rst.add(i);
                return rst;
            }
            map.put(sum, i);
        }//for
        return rst;
    }
}

```