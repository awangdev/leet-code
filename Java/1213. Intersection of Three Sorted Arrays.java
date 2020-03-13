E
tags: Hash Table, Two Pointers
time: O(m + n + h) two pointers approach
space: O(1)

Very similar to 349.Intersection of Two Arrays.

#### Hash Table
- Use set to check
- Verify duplicates at end rst

#### Two Pointers
- similar to Intersection of Two Sorted Arrays
- Start from front/back, process 1 item at a time
    - if match, move all pointers 
- Optoin1: check from back
- Optoin2: check from frotn

```

/*

Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

 

Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
 

Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
*/

class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        Set<Integer> set1 = toSet(arr1), set2 = toSet(arr2);
        
        List<Integer> rst = new ArrayList<>();
        for (int num : arr3) {
            if (set1.contains(num) && set2.contains(num)) {
                if (rst.size() == 0) rst.add(num);
                else if (rst.size() > 0 && rst.get(rst.size() - 1) != num) rst.add(num);
            }
        }
        return rst;
    }
    
    private Set<Integer> toSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        return set;
    }
}

// Method2: Two Pointers, Optoin1:  check from back
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> rst = new LinkedList<>();
        int i = arr1.length - 1, j = arr2.length - 1, k = arr3.length - 1;
        
        while (i >= 0 && j >= 0 && k >= 0) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                if (rst.isEmpty() || arr1[i] != rst.get(rst.size() - 1)) rst.add(0, arr1[i]);
                i--;
                j--;
                k--;
            } else if (arr2[j] < arr3[k]) k--; 
            else if (arr1[i] < arr2[j]) j--;
            else i--;
        }
        
        return rst;
    }
}

// Method2: Two Pointers, Optoin2: check from front
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> rst = new LinkedList<>();
        
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                if (rst.isEmpty() || arr1[i] != rst.get(rst.size() - 1)) rst.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (arr1[i] < arr2[j]) i++;
            else if (arr2[j] < arr3[k]) j++; 
            else k++;
        }
        
        return rst;
    }
}
```