 
 
 
## Quick Sort (4)
**0. [Kth Largest Element.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element.java)**      Level: Review      Tags: [Divide and Conquer, Heap, Quick Sort]
      

#### Quick Sort
- 用Quick Sort 里面partion的一部分。     
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.

#### Heap
- Learn how to build min-heap or max-heap to solve this problem.
- O(n + kLogN)




---

**1. [Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Median.java)**      Level: Easy      Tags: [Array, Quick Select, Quick Sort]
      

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
- quickSelect 可以找到 kth 最小的元素
- 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- quick select 的template要熟悉一下, 一下子可能想得到, 但写不出来
- 主要步骤: partition, dfs, only recur on one part of the array 



---

**2. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      

给一串数字, 和 int k. 根据k的值partition array, 找到第一个i, nums[i] >= k.

#### Two Pointer
- Quick sort的基础. 
- Partition Array根据pivot把array分成两半。
- 从array两边开始缩进。while loop到遍历完。非常直白的implement。
- 注意low/high,或者叫start/end不要越边界
- O(n)
- 注意: 这里第二个inner while `while(low <= high && nums[high] >= pivot) {..}` 采用了 `nums[high] >= pivot`
- 原因是题目要找第一个nums[i] >= k, 也就是说, 即便是nums[i]==k也应该swap到前面去
- 这个跟quick sort 原题有一点点不一样.




---

**3. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, Quick Sort]
      



---

