M
1531118637
tags: Sort, PreSum

给一串数字, 找subarray的首尾index, 条件: subarray最接近0.

#### PreSum
- Can be a 2D array, or a `class Point`
- Sort preSum: smaller (有可能负数) 靠前, 大数字靠后
- 比较preSum种相连接的两个节点, 找差值min
- 因为最接近的两个preSum节点的差值肯定是最小
- min所在的两个节点的index, 就是result candidate: 这两个index可能再原nums里面相差很远
- time O(nlogn), sort
- space: O(n)

```
/*
Given an integer array, find a subarray with sum closest to zero. 
Return the indexes of the first number and last number.

Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4]

Challenge
O(nlogn) time

Tags Expand 
Subarray Sort

Thoughts:
Took a me a while to think through how to find the closest sum to 0.
Credits should be given to: http://rafal.io/posts/subsequence-closest-to-t.html
*/


// Use a class point to track both preSum value and index
public class Solution {
    class Point {
        int val, index;
        public Point(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    public int[] subarraySumClosest(int[] nums) {
        int[] rst = new int[2];
        if(nums == null || nums.length <= 1) return rst;

        int n = nums.length;
        Point[] points = buildPoints(nums);

        int start = 0, end = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int temp = points[i + 1].val - points[i].val;
            if (temp <= min) {
                min = temp;
                start = points[i].index;
                end = points[i + 1].index;
            }
        }
        if (start < end) {
            rst[0] = start + 1;
            rst[1] = end;
        } else {
            rst[0] = end + 1;
            rst[1] = start;
        }
        return rst;
    }

    private Point[] buildPoints(int[] nums) {
        int n = nums.length;
        Point[] points = new Point[n];
        points[0] = new Point(nums[0], 0);
        for (int i = 1; i < n; i++) {
            points[i] = new Point(points[i - 1].val + nums[i], i);
        }
        Arrays.sort(points, Comparator.comparing(p -> p.val));
        return points;
    }
}

// use 2D array, same concept, a bit messy
class CustomComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
    }
}

public class Solution {
   public ArrayList<Integer> subarraySumClosest(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) {
            return rst;
        }
        if (nums.length == 1) {
            rst.add(0); rst.add(0);
            return rst;
        }
        int[][] culmulate = new int[nums.length][2];
        culmulate[0][0] = nums[0];
        culmulate[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            culmulate[i][0] = culmulate[i - 1][0] + nums[i];
            culmulate[i][1] = i;
        }

        Arrays.sort(culmulate, new CustomComparator());
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = culmulate[i + 1][0] - culmulate[i][0];
            if (temp <= min) {
                min = temp;
                start = culmulate[i][1];
                end = culmulate[i + 1][1];
            }
        }
         if (start < end) {
            rst.add(start + 1);
            rst.add(end);
        } else {
            rst.add(end + 1);
            rst.add(start);
        }
        return rst;
    }
}





//I also had to run a little java program locally to test/debug:
/*

import java.lang.*;
import java.util.*;

class CustomComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return Integer.compare(a[0], b[0]);
    }
}

public class test {
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) {
            return rst;
        }
        int[][] culmulate = new int[nums.length][2];
        culmulate[0][0] = nums[0];
        culmulate[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            culmulate[i][0] = culmulate[i - 1][0] + nums[i];
            culmulate[i][1] = i;
        }
        //TEST:
        for(int i =0 ; i < nums.length; i++) {
            System.out.println("test:" + culmulate[i][0] + " " + culmulate[i][1]);
        }
        Arrays.sort(culmulate, new CustomComparator());
        for(int i =0 ; i < nums.length; i++) {
            System.out.println("sorted:" + culmulate[i][0] + " " + culmulate[i][1]);
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = culmulate[i + 1][0] - culmulate[i][0];
            System.out.println(culmulate[i + 1][0] + " minus " + culmulate[i][0] + " = " + temp);
            if (temp <= min) {
                min = temp;
                start = culmulate[i][1];
                end = culmulate[i + 1][1];
                System.out.println("record:" + start + " " + end );
            }
        }
        System.out.println("min:" + min);
        if (start < end) {
            rst.add(start + 1);
            rst.add(end);
        } else {
            rst.add(end + 1);
            rst.add(start);
        }
        return rst;
    }

    public static void main(String[] args){

        int[] nums = {6,-4,-8,3,1,7};//{5,10,5,3,2,1,1,-2,-4,3};
        test t = new test();
        ArrayList<Integer> rst = t.subarraySumClosest(nums);
        System.out.println(rst.get(0) + " " + rst.get(1));
    }
}

*/





```