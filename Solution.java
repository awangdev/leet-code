import java.util.*;
public class Solution {
    public static void quickSort(int[] nums){
        if (nums == null || nums.length == 0) {
            return;
        }
        sortHelper(nums, 0, nums.length - 1);
    }

    public static void sortHelper(int[] nums, int start, int end){
        if (start >= end) {
            return;
        } else {

            int partitionPoint = partition(nums, start, end);
            sortHelper(nums, start, partitionPoint - 1);
            sortHelper(nums, partitionPoint, end);
        }
    }

    public static  void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp; 
    }

    public  static int partition(int[] nums, int start, int end){
        int mid = start + (end - start)/2;
        
        int pivot = nums[mid];
        while (start <= end) {
            while (start <= end && nums[start] < pivot) {
                start++;
            }
            while (start <= end && nums[end] > pivot) {
                end--;
            }
            if (start <= end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

/*
public static void sortHelper(int[] data, int left, int right) {  
    if (left >= right)  
        return;  
    else {  
        int partition = partition(data, left, right);  
        sortHelper(data, left, partition - 1);  
        sortHelper(data, partition, right);  
    }   
}  */
/*
public  static int partition(int[] data, int left, int right)  
{  
      int i = left, j = right;  
      int tmp;  
      int pivot = data[(left + right) / 2];  
       
      while (i <= j) {  
            while (data[i] < pivot)  
                  i++;  
            while (data[j] > pivot)  
                  j--;  
            if (i <= j) {  
                  tmp = data[i];  
                  data[i] = data[j];  
                  data[j] = tmp;  
                  i++;  
                  j--;  
            }  
      };       
      return i;  
} */

    public static void main(String[] args){
    	System.out.println("START");
        int[] nums = new int[] {3,2,1,0,9,8,0,0};
        quickSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println("END ");
    }
}










