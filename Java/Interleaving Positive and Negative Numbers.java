M
1525230897
tags: Two Pointers

给一串数组 有正负数. 重新排列, 让数组里面 正数 和 负数 相隔开. 原来的order无所谓

#### Two pointer
- 需要知道正负的位置, 所以排序 O(nlogN)
- 考虑: 正数多还是负数多的问题, 举栗子就看出来端倪了
- 然后Two Pointer, swap 
- Time O(nlogn), space O(n)

#### extra space
- 用extra O(n) space, 把正负分成两个list
- 然后分别按照index填回去
- time O(n). space O(n)
- 但是就么有用到Two pointer了

```
/*
Given an array with positive and negative integers. 
Re-range it to interleaving with positive and negative integers.

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
Sort the array and swap to correct order.
1. Negative # == positive #, swap start/end untill start == end
2. Negative # != positive #, if mid point is positive, end = end - 1; if mid point is negative, start = start + 1. 
3. Then do the same swap based on start, end

O(nlogn) runtime
*/
public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        Arrays.sort(A);
        int start = 0;
        int end = A.length - 1;
        if (A.length % 2 != 0) {
            if (A[(start + end) / 2] >= 0) {
                end--;
            } else {
                start++;
            }    
        }
        
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start += 2;
            end -= 2;
        }
    }
}

// Extra O(n) space, O(n) runtime
public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int num : A) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        int extraPositive = positive.size() > negative.size() ? 0 : 1;
        
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == extraPositive) {
                A[i] = positive.get(0);
                positive.remove(0);
            } else {
                A[i] = negative.get(0);
                negative.remove(0);
            }
            System.out.println(A[i]);
        }
    }
}

/*
	Previous notes:
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