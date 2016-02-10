这个挺直接的，也被李特标了EASY. 
就是在1～n里面找第一个出错点。其实就是搜一个数字i（同时用isBadVersion(i)）检查这个数字是否报错。那搜index的活，直接binary search就好了。
注意特别条件：
```
!isBadVersion(i) && isBadVersion(i+1)
```
一定要找出这个i+1，就是第一个出错点。

还有就是：很可能start == end了呀，如果还没有完结，那么最终的那个情况必然是start==end==mid, 然后既然mid之前没找到错，那么try 一try isBadVersion(mid) 就好了。
```
/*
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 1) {
        	return isBadVersion(n) ? n : -1;
        }
        int start = 1;
        int end = n;
        int mid = start + (end - start) / 2;
        while(start < end) {
        	mid = start + (end - start) / 2;
        	if (!isBadVersion(mid) && isBadVersion(mid + 1)) {
        		return mid + 1;
        	} else if (!isBadVersion(mid)){
        		start = mid;
        	} else {
        		end = mid;
        	}
        }
        return isBadVersion(mid) ?  mid : -1;
    }
}
```