/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a = b = c)

The solution set must not contain duplicate triplets.

Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)

(-1, -1, 2)

Tags Expand 
Two Pointers Sort Array


*/

/*
Thoughts:
    Cannot use HashMap for this problem because of the duplicates. See the bottom of this file for the failed version.
    Remember to check for null and edge-soluton.
    Before everything, Arrays.sort() the given array, in order to effectively handle the duplicates.
    At 3SUM level, takes 1 element out and do 2SUM on the rest of the front elements of the array. Note, 2SUM has multitple solutions (need to handle duplicates)
    Cross-match the 2SUM solution with the selected element from 3SUM level.

*/

public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (numbers == null && numbers.length <= 2) {// Length at least >= 3
            return rst;
        }
        Arrays.sort(numbers);//Sort in order to handle duplicates
        for (int i = numbers.length - 1; i >= 2; i--) {// i >=2 because at least 3 element in result.
            if (i < numbers.length - 1 && numbers[i] == numbers[i + 1]) {
                continue;//The case of numbers[i + 1] should have already covered all possibilities of the case numbers[i], so safe to skip
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
        if (num == null || num.length <= 1) {//Length at least >= 2
            return rst;
        }
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
The following is a exceeding time version.
I believe the concept is clear, but it does not handle duplicates well. So we can't use this version.


public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (numbers.length <= 2) {
            return rst;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++){
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i; j < numbers.length; j++) {
                int remain = 0 - numbers[i] - numbers[j];
                if (map.containsKey(remain) && map.get(remain) != i) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(numbers[i]);
                    list.add(remain);
                    list.add(numbers[j]);
                    if (!rst.contains(list)){
                        rst.add(list);                        
                    }
                } else {
                    map.put(numbers[j], j);
                }
            }
        }
        return rst;
    }
}

*/