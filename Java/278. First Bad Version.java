E
tags: Binary Search
time: O(logN)
space: O(1)

#### Method1: Check is-NOT-BadVersion
- simply binary Search: if not bad, assign `start = mid+1`

#### Method2: Check ifBadVersion
- 根据isBadVersion的性质，判断还如何end=mid or start=mid.     
- A bit more code to handle

```
/*
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 

*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
// Check is-NOT-BadVersion
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid)) start = mid;
            else end = mid;            
        }        
        return isBadVersion(start) ? start : end;
    }
}

// Check isBadVersion.
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 0) return n;
        int start = 0, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            boolean isBad = isBadVersion(mid);
            if (isBad) {
                if (mid - 1 >= 1 && !isBadVersion(mid - 1)) return mid;
                end = mid;
            } else start = mid;
        }
        return isBadVersion(start) ? start : end;
    }
}

```