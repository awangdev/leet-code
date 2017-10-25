M

Binary Search

根据isBadVersion的性质，判断还如何end=mid or start=mid.     
isBadVersion 是有方向的嘛，一个点错了，后面全错。

```
/*
The code base version is an integer start from 1 to n. 
One day, someone committed a bad version in the code case, 
so it caused this version and the following versions are all failed in the unit tests. 
Find the first bad version.

You can call isBadVersion to help you determine which version is the first bad one. 
The details interface can be found in the code's annotation part.


Example
Given n = 5:

isBadVersion(3) -> false
isBadVersion(5) -> true
isBadVersion(4) -> true
Here we are 100% sure that the 4th version is the first bad version.

Note
Please read the annotation in code area to get the correct way to call isBadVersion in different language. 
For example, Java is SVNRepo.isBadVersion(v)

Challenge
You should call isBadVersion as few as possible.

Tags Expand 
Binary Search LintCode Copyright Facebook

*/

/*
    Recap: 12.07.2015.
    Feels like to find the 1st occurance of the match. going left all the way.
*/
/**
 * public class SVNRepo {
 *     public static boolean isBadVersion(int k);
 * }
 * you can use SVNRepo.isBadVersion(k) to judge whether 
 * the kth code version is bad or not.
*/
class Solution {
    public int findFirstBadVersion(int n) {
        if (n < 1) {
            return 0;
        }
        int start = 1;
        int end = n;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (SVNRepo.isBadVersion(mid)) {
                if (mid - 1 >= 1 && SVNRepo.isBadVersion(mid - 1)) {
                    end = mid;
                } else {
                    return mid;
                }
            } else {
                start = mid;
            }
        }
        if (SVNRepo.isBadVersion(start)) {
            return start;
        } 
        return end;
    }
}







class Solution {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        int start = 1;
        int end = n;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (VersionControl.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }//end while
        
        if (VersionControl.isBadVersion(start)) {
            return start;
        } else if (VersionControl.isBadVersion(end)) {
            return end;
        } else {
            return 0;
        }
        
    }
}


```