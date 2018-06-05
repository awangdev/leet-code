 
 
 
## Merge Sort (2)
**0. [MergeSort.java](https://github.com/awangdev/LintCode/blob/master/Java/MergeSort.java)**      Level: Medium      Tags: [Merge Sort, Sort]
      

#### Merge Sort
- Divide and conquer, recursively
- 先从中间分段, merge sort 左边 (dfs), merge sort 右边
- 最后merge起来
- merge的时候因为是做int[], 所以没办法必须要O(n) space
- Time O(nlogn), Space O(n)



---

**1. [Sort List.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20List.java)**      Level: Medium      Tags: [Divide and Conquer, Linked List, Merge Sort, Sort]
      

#### Merge sort
- 1. find middle. 快慢指针
- 2. Sort: 切开两半，先sort前半, 如果先sort了mid.next~end, sort后，中间点mid.next == null，再sort前半段
- 3. Merge:  假设given list A, B 已经是sorted, 然后按照大小，混合。
- 要recursively call sortList() on partial list.

#### Quick sort
- 想做可以看讲义：http://www.jiuzhang.com/solutions/sort-list/
- 但是quick sort不建议用在list上面。
- 排列list, merge sort可能更可行和合理。原因分析在下面， 以及： http://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/



---

