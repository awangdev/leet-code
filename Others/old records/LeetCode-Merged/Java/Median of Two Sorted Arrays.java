/*
There are two sorted arrays nums1 and nums2 of size m and n respectively. 
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/


/*
THoughts:
max-heap on left and min-heap on right; Center: the median
left heap size: x
right heap size: x, or x + 1

However, this might (m+n)Log(m+n)

*/

//NOT DONE
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
        	return 0;
        }
        //min-heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>(){
        	public int compare(int x, int y){
        		return y - x;
        	}
        });

        int median = Integer.MIN_VALUE;


    }
}