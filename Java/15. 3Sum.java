M
tags: Array, Sort, Two Pointers
time: O(n^2)

#### sort array, for loop + two pointer
- 处理duplicate wthin triplets: 
    - 如果最外圈的移动点i重复, 一直顺到结尾的最后一个再用.
    - 如果是triplet内有重复, 用完start point, 移动到结尾.
- Note:
   - 1. 找 value triplets, 多个结果。注意，并非找index。    
   - 2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   - 3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。
- 时间 O(n^2), 两个nested loop

#### For loop + 2Sum
- HashMap 2Sum. Remember to handle duplicates
   - 1. For loop 挑个数字A
   - 2. 2Sum 出一堆2个数字的结果
   - 3. Cross match 步骤1里面的A

```
/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)
Note
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.

Tags Expand 
Two Pointers Sort Array Facebook
*//*
Thoughts:
Sort the list, do a for loop and two pointer within.
Make sure to skip duplicated index value:
when 'start' is duplicated, start++ until no duplicates.
when i is duplicated, continue in for loop and get to end of last duplicate and use that as i.

O(n) * O(n) -> O(n^2)
*/

// Simplified solution
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);// O(nlogn)
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) { // check lo/hi after i
            if (i > 0 && nums[i] == nums[i - 1]) continue; // check duplicate

            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++;
                    hi--;
                } else if (sum < 0) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return result;
    }
}

/*
    Thoughts: use HashMap with 2Sum
*/
//With HashMap 2Sum
public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (numbers == null && numbers.length <= 2) {// Length at least >= 3
            return rst;
        }
        Arrays.sort(numbers);//Sort in order to handle duplicates
        for (int i = numbers.length - 1; i >= 2; i--) {// i >=2 because at least 3 element in result; starting from end, ensures non-descending order
            if (i < numbers.length - 1 && numbers[i] == numbers[i + 1]) {
                continue;//The case of numbers[i + 1]: should have already covered all possibilities of the case numbers[i], so safe to skip
            }
            ArrayList<ArrayList<Integer>> twoSum = calTwoSum(numbers, i - 1, 0 - numbers[i]);//Pick the 3rd element numbers[i]
            for (int j = 0; j < twoSum.size(); j++) {//Find two sum of rest-front elements. Cross add them with numbers[i]
                twoSum.get(j).add(numbers[i]);
            }
            rst.addAll(twoSum);
        }
        return rst;
    }
    //Two Sum. Multiple answer, with HashMap
    public ArrayList<ArrayList<Integer>> calTwoSum(int[] num, int end, int target) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> match;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i <= end; i++) {
            if (map.containsKey(num[i])) {
                match = new ArrayList<Integer>();
                match.add(num[map.get(num[i])]);
                match.add(num[i]);
                if (!rst.contains(match)) {
                    rst.add(new ArrayList<Integer>(match));
                }
            } else {
                map.put(target - num[i], i);
            }
            //Skip duplicate
            if (i < end && num[i] == num[i + 1]) {
                continue;
            }
        } 
        return rst;
    }
}

```