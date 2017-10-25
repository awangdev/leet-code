不管遇到啥，先排个序。
这里主要要特别考虑，正数多还是负数多的问题。
count一下，然后举两个小栗子就看出来端倪了。
然后Two Pointer
```
/*
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Note
You are not necessary to keep the original order of positive integers or negative integers.

Challenge
Do it in-place and without extra memory.

Tags Expand 
Two Pointers
*/

/*
	Thoughts:
	Sort, so it becomes [-1,-2,-3,-4,4,5,6,7]
	Two pointer start,end.
	Every round, start +=2, end -= 2;
	Note: have to find out how many negative/positive integers first.
*/

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
	   	if (A == null || A.length == 0) {
	   		return;
	   	}
	   	Arrays.sort(A);
	   	int count = 0;
	   	for (int num : A){
	   		count += num >= 0 ? 1 : -1;
	   	}
	   	int start = 0;
	   	int end = A.length - 1;
	   	if (count < 0) {
	   		start++;
	   	} else if (count > 0){
	   		end--;
	   	}
	   	
	   	while (start < end) {
	   		if (A[start] < 0 && A[end] >= 0) {
	   			int temp = A[start];
	   			A[start] = A[end];
	   			A[end] = temp;
	   		}
	   		start += 2;
	   		end -= 2;
	   	}
   	}
}
```