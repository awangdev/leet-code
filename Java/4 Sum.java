M
1516696879
tags: Hash Table

方法1：  
   1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
   2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
   3. 再尝试从所有i+1后面,找合适的2nd pair。   
 
   可以用HashSet<List>, 可以直接比较list里面每一个元素, 保证set不重复.
   Previous Notes: 在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。
   参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/    

方法2： 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)

```
/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?

Find all unique quadruplets in the array which gives the sum of target.

Example
Given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:

(-1, 0, 0, 1)
(-2, -1, 1, 2)
(-2, 0, 0, 2)
Note
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

Tags Expand 
Two Pointers Sort Hash Table Array

*/

/*
Thoughts:
Can't do for loop over 3sum, which is O(n^3), not doable.
We can break the nums into 2 major parts by index i.
1st part: from [0 ~ i], we'll try all possible ways to pair [x, i] and store the sum of nums[x]+nums[i] in map. 
Note: key is sum, and the value is a hashSet of ArrayList. Where the hashset has itself functions to tell duplicate of pairs.
2nd part: try [i + 1, end], see if any combination sum that makes: target - sum exist in map. -> becomes same old 2sum problem.
O(n)
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        final List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 3) {
            return result;
        }
        Arrays.sort(nums);
        final Map<Integer, HashSet<List>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) { // O(n)
            // Check end-pair in [i, end] and see if they can add up to target
            for (int k = i + 1; k < nums.length; k++) { //O(n)
                int sum = nums[i] + nums[k];
                if (map.containsKey(target - sum)) {// Try to match up the 4 pairs
                    for (final List<Integer> frontPair : map.get(target - sum)) { // almost O(constant) time
                        final Integer[] matchedArray = {frontPair.get(0), frontPair.get(1), nums[i], nums[k]};
                        final List<Integer> list = Arrays.asList(matchedArray);
                        if (!result.contains(list)) {
                            result.add(list);
                        }
                    }
                }
            }
            /**
              Why having below front-pair-building after previous for end-pair-checking for loop?
              Here: we build [0 ~ i], and in next round, when we processs [i + 1, end], pairs built among [0~i] below will all be used.
              Rather: if we have lift the for loop below to calculate [0~i] before the end-pair-checking of same [i~end], 
                      there is one index at [i] will overlap, which turns to be incorrect.
              Therefore, we build front-pairs aftwarwards: it's building [0~i] here, which aims to help next round of end-pair-checking on [i+1, end].
             */
            // Build up the front-pair from [0 ~ i]
            for (int j = 0; j < i; j++) {
                int sum = nums[j] + nums[i];
                if (!map.containsKey(sum)) {
                    map.put(sum, new HashSet<>());
                }
                final Integer[] pair = {nums[j], nums[i]};
                map.get(sum).add(Arrays.asList(pair));
            }
        }
        return result;
    }
}

/*
Thoughts
Perform another layer outside of 3SUM. O(n^3).
Note: If try to divide and perform two 2SUM, it will be a bit difficult. Refer to http://blog.csdn.net/linhuanmars/article/details/24826871

*/
public class Solution {

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


/*
NOT Complete yet. Has a order issue in HashSet
http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/
Thoughts:
Utilize 2Sum.

Notes 2017: no need to create a new class Pair. We can just use list in HashSet<List>, which compairs the list element and eleminate duplicated list.
*/

public class Solution {
    //Create class Pair for HashSet<Pair> to use
    class Pair {
      Integer x;
      Integer y;

      public Pair(int x, int y){
        this.x = x;
        this.y = y;
      }

      @Override
      public int hashCode(){
        return this.x.hashCode() + this.y.hashCode();
      }

      @Override
      public boolean equals(Object d) {
        if (!(d instanceof Pair)) {
            return false;
        }
        Pair p = (Pair)d;
        return (this.x == p.x) && (this.y == p.y);
      }
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
      ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>(); 
      if (numbers == null || numbers.length < 4) {
        return rst;
      }
      Arrays.sort(numbers);
      HashMap<Integer, HashSet<Pair>> map = new HashMap<Integer, HashSet<Pair>>();
      for (int i = 0; i < numbers.length; i++) {
        for (int j = i + 1; j < numbers.length; j++) {
          int sum = numbers[i] + numbers[j];
          if (map.containsKey(target - sum)) {
            for (Pair p : map.get(target - sum)) {
              ArrayList<Integer> list = new  ArrayList<Integer>();
              list.add(p.x);
              list.add(p.y);
              list.add(numbers[i]);
              list.add(numbers[j]);
              if (!rst.contains(list)) {
                rst.add(list);
              }
            }
          }
        }
        //Add all pairs up to i
        for (int j = 0; j < i; j++) {
          int sum = numbers[i] + numbers[j];
          if (!map.containsKey(sum)) {
            map.put(sum, new HashSet<Pair>());
          }
          map.get(sum).add(new Pair(numbers[j], numbers[i]));
        }
      }

      return rst;
    }

}

```