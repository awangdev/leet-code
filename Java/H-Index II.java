M
1531083215
tags: Binary Search

找到h-index, 给的citation int[] 已经sorted. h-index 的definition 具体看题目.

#### Binary Search
- H-index的一个简单版, 已经sorted(从小到大), 找target value
- 按定义, 找最后一个 `dictations[mid] >= h`, where `h = n - mid`
- O(logn)

```
/*
Follow up for H-Index: What if the citations array is sorted in ascending order? 
Could you optimize your algorithm?
Hint:

Expected runtime complexity is in O(log n) and the input is sorted.

Hide Company Tags Facebook
Hide Tags Binary Search
Hide Similar Problems (M) H-Index

*/

public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int h = n - mid;
            if (citations[mid] < h) start = mid;
            else { // citations[mid] >= h
                if (mid - 1 >= 0 && citations[mid - 1] <= h) return h;  // verify the prior node: (N-h)
                end = mid;
            }
        }
	// validate start && end
        if (citations[start] >= n - start) return n - start;
        if (citations[end] >= n - end) return n - end;
        return 0;
    }
}

/*
	citations[0,1,3,5,6]
	look for a h, where x = N-h, arr[x] >= h
	h is from right ot left.
	We want to find smallest x that has arr[x] >= n-x
	binary search:
		start,mid,end
		if match, keep going left until not able to
	O(nLogN)
*/

public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
        int n = citations.length;
        int start = 0;
        int end = n - 1;
        int mid;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
            int h = n - mid;
        	if (citations[mid] >= h) {
        		if (mid - 1 >= 0 && citations[mid - 1] > h) { // check last (N-h) node
        			end = mid;
        		} else {
        			return h;
        		}
        	} else if (citations[mid] < h) {
        		start = mid;
        	}
        }
        if (citations[start] >= n - start) {
        	return n - start;
        } else if (citations[end] >= n - end) {
        	return n - end;
        }
        return 0;
    }
}

```
