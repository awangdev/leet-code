 
 
 
## Quick Sort (7)
**0. [QuickSort.java](https://github.com/awangdev/LintCode/blob/master/Java/QuickSort.java)**      Level: Medium      Tags: [Quick Sort, Sort]
      
implement quick sort.

#### Quick Sort
- 首先partition. 返还一个partition的那个中间点的位置: 这个时候, 所有小于nums[partitionIndex] 都应该在 partitionIndex左边
- 然后劈开两半
- 前后各自 quick sort, recursively
- 注意：在partition里面, 比较的时候nums[start] < pivot, nums[end]>pivot, 如果写成了 <= 会 stack overflow.
- Time O(nlogn), Space: O(1)



---

**1. [[tool] Quick Select - Median.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool]%20Quick%20Select%20-%20Median.java)**      Level: Easy      Tags: [Array, Lint, Quick Select, Quick Sort, Two Pointers]
      

给一串无序数组, 找到median(sort之后 位置在中间的数字).

#### Quick Select
- 跟`kth largest element in an Array`的 template一样.
- quickSelect 可以找到 kth 最小的元素
    - 利用这个原理, 找这个kth最小值, 然后如果 == target index, 就找到了我们的median
- 主要步骤: 
    - 1. partition
    - 2. check end state `pivot index ?= target index`
    - 3. recursive call one part of the array 
- time: 与quickSort不同在于, 每次只要在一半list里面recurring, 所以把O(logn)的时间复杂度降到O(n)
    - n + n/2 + n/4 + n/8 + ....+ 1 = O(2n) = O(n)
- space: O(logn), based on recursive stacks




---

**2. [Sort Colors II.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors%20II.java)**      Level: Medium      Tags: [Partition, Quick Sort, Sort, Two Pointers]
      
Sort Color的普通版, sort all k colors in colors array.

Details 参见: https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Color.java

#### Quick Sort
- O(nk)



---

**3. [Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Sort]
      
kth largest in array

#### PriorityQueue, MinHeap
- Need to maintain k large elements, where the smallest will be compared and dropped if applicable: 
- Maintain k elements with min value: consider using minHeap
- add k base elements first
- Maintain MinHeap: only allow larger elements (which will squzze out the min value)
- Remove peek() of queue if over size
- O(nlogk)


#### Quick Sort
- 用Quick Sort 里面partion的一部分
- sort结束后是ascending的, 那么 n - k 就是第k大. 
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.



---

**4. [Sort Colors.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java)**      Level: Medium      Tags: [Array, Partition, Quick Sort, Sort, Two Pointers]
      
给一串数字 nums, 数字代表颜色[0,1,2]; 要求 sort nums, 数字最终按照大小排列. 

虽然叫sort color, 其实就是sort 这些 numbers, 只不过抽象了一下.

#### partition array, the base of quick sort
- partition the array by pivot k = {0, 1, 2}
- 每一次partition都return starting point of the current partition
- 然后根据下一个 color, 去还没有sort 干净的那个部分, 再sort一下就好
- time O(kn), where k = 0 => O(n)
- 这里只是partion, 并不需要recursively quick sort, 所以结果是简单的O(n)

#### One pass
- have two pointers, left/right
- start tracks red, end tracks blue. Swap red/blue to right position, and left++ or right--.
- leave white as is and it will be sorted automatically
- be very careful with index i: when swapping with index right, we do not know what is nums[right], so need to re-calculate index i .
- O(n)
- Note: this one pass solution does not work if there are more than 3 colors. Need to use the regular quick sorty.

#### Counting sort
- TODO: count occurance and reassign array



---

**5. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      
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

**6. [215. Kth Largest Element in an Array.java](https://github.com/awangdev/LintCode/blob/master/Java/215.%20Kth%20Largest%20Element%20in%20an%20Array.java)**      Level: Medium      Tags: [Divide and Conquer, Heap, MinHeap, PriorityQueue, Quick Select, Quick Sort]
      

kth largest in array

#### PriorityQueue, MinHeap
- Use minHeap to maintain PQ of k size and return PQ.peek()
    - Maintain MinHeap: only allow larger elements (which will squzze out the min value)
    - Remove peek() of queue if over size
- O(nlogk)

#### Quick Select, Quick Sort
- 用Quick Sort 里面partion的一部分: sort结束后是ascending的.
  - kth largest = (n - k)th smallest
  - in partioned array (quick sort), the portion before pivot are less than pivot
  - that is, the `pivot value` is the divider: anything after pivot is larger than it.
  - after `swap(nums, low, pivot)`: index low has the (n-k)th smallest, if `low = n-k`
- Steps:
  - each iteration: pick pivot,然后从low,和high都和pivot作比较
  - Find `low>pivot, high<pivot` to swap
  - The new low is the next partion point
- Time: average O(n), worst case O(n^2)
- space: O(1) extra spaces besides recursive stack



---

