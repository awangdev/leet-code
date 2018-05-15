M
1526363774
tags: Sort

#### Merge Sort
- Divide and conquer, recursively
- 先从中间分段, merge sort 左边 (dfs), merge sort 右边
- 最后merge起来
- merge的时候因为是做int[], 所以没办法必须要O(n) space
- Time O(nlogn), Space O(n)

```
/*
Merge Sort
*/

public class Solution {

    private void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }
    
    private void mergeSort(int[] nums, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, temp, start, mid);
        mergeSort(nums, temp, mid + 1, end);
        merge(nums, temp, start, end);
    }
    
    private void merge(int[] nums, int[] temp, int start, int end) {
        int leftEnd = start + (end - start) / 2; // mid point
        int rightStart = leftEnd + 1;
        int size = end - start + 1;
        
        int i = start;
        int j = rightStart;
        int index = start;
        
        while (i <= leftEnd && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[index] = nums[i];
                i++;
            } else {
                temp[index] = nums[j];
                j++;
            }
            index++;
        }
        
        // append the remaining array.
        // arraycopy: copy from array[i] to temp[index] for (x) items
        System.arraycopy(nums, i, temp, index, leftEnd - i + 1);
        System.arraycopy(nums, j, temp, index, end - j + 1);
        System.arraycopy(temp, start, nums, start, end - start + 1);
    }

    private void printArray(int[] nums, String str) {
        System.out.print(str);
        if (nums == null || nums.length == 0) {
            System.out.println();
            return;
        }
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("Implement and test quick sort");
        
        int[] array = {5,3,2,7,5,4,1,1,2,9,5,3, -1};
        sol.mergeSort(array);
        sol.printArray(array, "Return: ");
        
        array = null;
        sol.mergeSort(array);
        sol.printArray(array, "Return Empty: ");
        
        array = new int[]{ - 1, 2};
        sol.mergeSort(array);
        sol.printArray(array, "Return: ");
        
        array = new int[]{ 1,1,1,1,1};
        sol.mergeSort(array);
        sol.printArray(array, "Return: ");
        
        array = new int[]{ 19,8,7,6,5,4,3,2,1};
        sol.mergeSort(array);
        sol.printArray(array, "Return: ");
    }
}
```