E

有序, 假设有这样的数字:target.        
target 左边的数字，一定不比index大，target右边的数字，一定比index大。     
这样可以binary search.O(logn)

```
/*
来自网上uber面经：给一个有序数组（不含重复），返回任意一个数字，这个数字的值和它的数组下标相等

可能的follow up:
1. 如果含有重复数字怎么办？ 
	如果有序，也没问题，还是binary search
	
2. 另一种follow up:找这样数字的边界. 
	如果存在，那么和index match的一定在一块. 
	找到任何一个candiate, 找边界，也可以binary search,只是match的condition不同罢了。

*/
//Binary search.
import java.io.*;
import java.util.*;
class Solution {
    
    public int indexMatch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == mid) {
                return mid;
            } else if (nums[mid] < mid) {
                start = mid;
            } else {//nums[mid] > mid
                end = mid;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("START");
        int[] input = {-1, 0, 1, 3, 5, 8, 9};//return 3
        Solution sol = new Solution();
        int rst = sol.indexMatch(input);
        System.out.println(rst);
    }
}
```