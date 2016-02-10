M

用个for loop 加上 2sum 的土办法。

注意:   
   1. 找 value triplets, 多个结果。注意，并非找index。    
   2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。

步骤:   
   1. For loop 挑个数字A.    
   2. 2Sum 出一堆2个数字的结果    
   3. Cross match 步骤1里面的A.   

时间 O(n^2), 两个nested loop


另外, 还是可以用HashMap来做2Sum。稍微短点。还是要注意handle duplicates.

再另外（leetcode做时写的）：先sort，然后two pointer。

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
*/

/*
Thoughts:
    Remember to check for null and edge-soluton.
    Before everything, Arrays.sort() the given array, in order to effectively handle the duplicates.
    At 3SUM level, takes 1 element out and do 2SUM on the rest of the front elements of the array. Note, 2SUM has multitple solutions (need to handle duplicates)
    Cross-match the 2SUM solution with the selected element from 3SUM level.
*/

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
    //Two Sum. Multiple answer
    public ArrayList<ArrayList<Integer>> calTwoSum(int[] num, int end, int target) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        int left = 0;
        int right = end;
        while (left < right) {
            if (num[left] + num[right] == target) {
                ArrayList<Integer> match = new ArrayList<Integer>();
                match.add(num[left]);
                match.add(num[right]);
                rst.add(match);
                left++;
                right--;
                //For unique number A, there is only 1 unique number B such that A + B == target.
                //Therefore, once found the match, erase all numbers that's equal to A or equal to B
                while (left < right && num[left] == num[left - 1]) {
                    left++;
                }
                while (left < right && num[right] == num[right + 1]) {
                    right--;
                }
            } else if (num[left] + num[right] < target) {//Since int[] num is sorted: move L to right-side to get larger value.
                left++;
            } else {
                right--;
            }
        }
        return rst;
    }
}


/*
    Thoughts:
    Exact same approach, except using HashMap in 2Sum
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


/*
    From LeetCode Solution
    Thoughts:
    sort list. O(nLogn)
    end: n^2
    for (i = 0 ~ n) {
        int target = 0 - nums[i];
        while (start + 1 < end) {
            start + end == target {
                rst.add(i, star, end);
                keep looking: start ++, end--
            }
            else start + end < target?
                start++
            else 
                end--;
            }
        }
    }

Note:
    Check duplicates. Compute a unique string to savei set
*/

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        
        Arrays.sort(nums);
        HashSet<String> set = new HashSet<String>();
        //use old target to check duplicates. instead of set.
        for (int i = 0; i < nums.length - 2; i++) {
            int target = 0 - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (start < end) {
                if (nums[start] + nums[end] == target &&  
                    !set.contains(nums[i] + "," + nums[start] + "," + nums[end])) {
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    rst.add(list);
                    set.add(nums[i] + "," + nums[start] + "," + nums[end]);
                    list = new ArrayList<Integer>();
                    start++;
                    end--;
                } else if (nums[start] + nums[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }//end while        
        }
        
        return rst;
    }
}


```