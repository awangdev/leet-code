/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Example
For example, given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:

(-1, 0, 0, 1)

(-2, -1, 1, 2)

(-2, 0, 0, 2)

Note
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)

The solution set must not contain duplicate quadruplets.

Tags Expand 
Two Pointers Sort Hash Table Array

Thinking process:
Perform another layer outsideo of 3SUM.

Note: If try to divide and perform two 2SUM, it will be a bit difficult. Refer to http://blog.csdn.net/linhuanmars/article/details/24826871
*/

public class Solution {
    /**
     * @param numbers : Give an array numbersbers of n integer
     * @param target : you need to find four elements that's sum of target
     * @return : Find all unique quadruplets in the array which gives the sum of
     *           zero.
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {     
    	ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    	if(numbers == null || numbers.length < 4) {
    		return rst;
    	}
    	Arrays.sort(numbers);
    	//Pick 1st element
   		for (int i = 0; i < numbers.length - 3; i++) {
   			if (i != 0 && numbers[i] == numbers[i - 1]) {//Check for duplicate of 1st element
   				continue;
   			}
   			//Pick 2nd element
   			for (int j = i + 1; j < numbers.length - 2; j++) {
   				if (j != i + 1 && numbers[j] == numbers[j - 1]) {//Check for duplicate of 2nd element
   					continue;
   				}
   				//Pick 3rd and 4th element
   				int third = j + 1;
   				int fourth = numbers.length - 1;
   				while (third < fourth) {
	   				int sum = numbers[i] + numbers[j] + numbers[third] + numbers[fourth];
	   				if (sum < target) {
	   					third++;
	   				} else if (sum > target) {
	   					fourth--;
	   				} else {//sum == target
	   					ArrayList<Integer> list = new ArrayList<Integer>();
	   					list.add(numbers[i]);
	   					list.add(numbers[j]);
	   					list.add(numbers[third]);
	   					list.add(numbers[fourth]);
	   					rst.add(list);
	   					third++;
	   					fourth--;
	   					while (third < fourth && numbers[third] == numbers[third - 1]) {
	   						third++;
		   				}
		   				while (third < fourth && numbers[fourth] == numbers[fourth + 1]){
		   					fourth--;
		   				}
	   				}
   				}
   			}
   		}
   		return rst;
    }
}

