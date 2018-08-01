R
tags: Heap, MinHeap

Turn unsorted array into a min-heap array, where for each A[i], 

A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

#### Heap
- Heap用的不多. 得用一下, 才好理解. 通常default 的PriorityQueue就是给了一个现成的min-heap:
- 所有后面的对应element都比curr element 小。
- Heapify里面的**siftdown**的部分:
- 只能从for(i = n/2-1 ~ 0)， 而不能从for(i = 0 ~ n/2 -1): 必须中间开花，向上跑的时候才能确保脚下是符合heap规则的

#### Heapify/SiftDown做了什么?
- 确保在heap datastructure里面curr node下面的两个孩子，以及下面所有的node都遵循一个规律
- 比如在这里，若是min-heap,就是后面的两孩子都要比自己大。若不是，就要swap。    

#### min-heap的判断规律:
- for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
- siftdown时：在curr node和两个son里面小的比较。如果的确curr < son, 搞定，break while.   
- 但若curr 并不比son小，那么就要换位子，而且继续从son的位子往下面盘查。    

```
/*
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] 
and A[i * 2 + 2] is the right child of A[i].

Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.

Challenge
O(n) time complexity

Clarification

What is heap?
Heap is a data structure, which usually have three methods: push, pop and top. 
where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, 
"top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. 
If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Tags Expand 
LintCode Copyright Heap
*/


/*
Thoughts:
Based on the knowledge of Hash Heap: http://www.jiuzhang.com/solutions/hashheap/
Try to implement part of the Heap basis, heapify.

In this problem, re-organize the input array to fit heap basis

1. Compare next value with head. 
2. If smaller than head, do a siftdown

siftdown:
always swap with the smaller child
As long as left.child.i < array length, continue while:
	If no right child, or left.val < right.val,
		son = left.
	else
		son = right
Check if curr.val < son.val
	if so, break, we are good.
	If not, swap(curr,son)
curr = son, and move on the next round of while


NOTE:
The for loop start from i = n/2 -1, which makes the right-most index = 2*(n/2-1) + 1 = n - 2 + 1 = n-1. 
*/


public class Solution {
    public void heapify(int[] A) {
    	if (A == null || A.length == 0) {
    		return;
    	}
    	int son = 0;
    	int currId = 0;
    	int leftId = 0;
    	int rightId = 0;
    	int n = A.length;
    	for (int i = n/2 - 1; i >= 0; i--) {
    		currId = i;
    		while (currId * 2 + 1 < n) {
    			leftId = currId * 2 + 1;
    			rightId = currId * 2 + 2;
    			if (rightId >= n || A[leftId] <= A[rightId]) {
    				son = leftId;
    			} else {
    				son = rightId;
    			}
    			if (A[currId] <= A[son]) {
    				break;
    			} else {
    				int temp = A[currId];
    				A[currId] = A[son];
    				A[son] = temp;
    			}
    			currId = son;
    		}//end while

    	}//end for
    }
}




```