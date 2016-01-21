M

方法1： 3Sum外面再加一层. 参考3Sum. 时间O(n^3)。 但此方法在k-sum时候，无疑过于费时间. O(n^k)

方法2： 参见 http://lifexplorer.me/leetcode-3sum-4sum-and-k-sum/      
   1. 利用2Sum的原理，把4Sum分为连个2Sum。左一个pair,右一个pair，每个pair里面放2个数字。   
   2. 以一个点，i，作为分界口，也要列举出所有i之前的pair,作为基础。   
   3. 再尝试从所有i+1后面,找合适的2nd pair。   

   注意：在造class Pair时候，要做@override的function: hashCode(), equals(Object d). 平时不太想得起来用。

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
      HashMap<Integer, List<Pair>> map = new HashMap<Integer, ArrayList<Pair>>();
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
            map.put(sum, new ArrayList<Pair>());
          }
          map.get(sum).add(new Pair(numbers[j], numbers[i]));
        }
      }

      return rst;
    }

}



```