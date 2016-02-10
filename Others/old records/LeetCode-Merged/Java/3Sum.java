/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
Hide Company Tags Facebook
Hide Tags Array Two Pointers
Hide Similar Problems (M) Two Sum (M) 3Sum Closest (M) 4Sum (M) 3Sum Smaller

*/
/*
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