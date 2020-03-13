 
 
 
## Two Pointers (57)
**0. [Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Linked List, Math, Two Pointers]
      
LinkedList 里面有 cycle, 找到cycle的起始点(第一个重复出现的element).

#### Slow, fast Pointer
- 快慢指针, O(1)space.
- 1. 确认有cycle后 2. 数学问题:找到开头.
- 当head == slow.next时候， head就是cycle starting point.
- 也就是说，当slow 移动到了那个回溯点，slow.next那个点就刚好是head的那个点...

#### 证明
- 1. 假设慢指针走t步, 快指针走快一倍, 也就是2t.
- 2. 我们假设cycle的长度是Y, 而进入cycle之前的长度为X.
- 3. 假设慢指针走了m圈cycle, 而快指针走了n圈cycle之后, 两个pointer相遇.
- 4. 最终在Y cycle里面的K点相遇, 也就是两个指针都在这最后一圈里面走了K 步.
- 那么:
- t = X + mY + K
- 2t = X + nY + K
- 整合公式: X + K = (n - 2m)Y
- 这里的m和n不过是整数的跑圈数, 也就是说X和K加在一起, 总归是结束cycle. X 和 K 互补
- 结论: 当slow/fast 指针在K点相遇后, 再走X步, 就到了cycle的起点, 也就是题目要求的起点.

#### Hash Table, O(n) space




---

**1. [Reverse String.java](https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail



---

**2. [Backspace String Compare.java](https://github.com/awangdev/LintCode/blob/master/Java/Backspace%20String%20Compare.java)**      Level: Easy      Tags: [Stack, Two Pointers]
      


---

**3. [Minimum Size Subarray Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/Minimum%20Size%20Subarray%20Sum.java)**      Level: Medium      Tags: [Array, Binary Search, Subarray, Two Pointers]
      

给一串positive integer, 找最短的subarray sum, where the sum >= s

#### Two pointer
- 全部是positive integer, 那么preSum一定是增长的.
- 那其实就用two pointer: `start=0, end=0` 不断往前移动. 策略: 
- 1. end++ until a solution where sum >= s is reached
- 2. 然后移动start; 记录每个solution, Math.min(min, end - start);
- 3. 然后再移动end，往下找
- Note: 虽然一眼看上去是nested loop.但是分析后，发现其实就是按照end pointer移动的Loop。start每次移动一格。总体上，还是O(n)

#### Binary Search
- O(nlogn) NOT DONE.

#### Double For loop
- O(n^2), inefficient



---

**4. [Rotate List.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      
给一个single linked list, 右移k steps. k non-negative.

#### Linked List basics
- 记得用dummy.next来存head.
- 特殊: 这里k可能大于list总长. 写一写linked node 移动的步数, 然后 k = k % n.
- 找到newTail, newHead, 然后利用dummy, 换位子



---

**5. [Longest Substring Without Repeating Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Substring%20Without%20Repeating%20Characters.java)**      Level: Medium      Tags: [Hash Table, String, Two Pointers]
      
方法1:
Two Pointers
双指针: 从start开始遍历, 但是第一步是while loop来推进end; 直到推不动end, 然后start++
巧妙点: 因为end是外围variable, 在start的loop上, end不会重置;[star ~ end] 中间不需要重复计算.
最终可以O(n);

Previous verison of two pointers:
用两个pointer, head和i.
注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯

方法2:
   HashMap<Char, Integer>: <character, last occurance index>
   一旦有重复, rest map.
   没有重复时候, 不断map.put(), 然后求max值

问题: 每次reset map之后就开始从新从一个最早的index计算, 最坏情况是O(n^2):
'abcdef....xyza'




---

**6. [[tool] Quick Select - Median.java](https://github.com/awangdev/LintCode/blob/master/Java/[tool]%20Quick%20Select%20-%20Median.java)**      Level: Easy      Tags: [Array, Lint, Quick Select, Quick Sort, Two Pointers]
      

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

**7. [Container With Most Water.java](https://github.com/awangdev/LintCode/blob/master/Java/Container%20With%20Most%20Water.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
#### Two Pointers
- 木桶理论。盛水的最高取决于最低的那面墙。
- 左右两墙，往中间跑动。
- 另:若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低)
- 但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）



---

**8. [Partition List.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      
#### Linked List
- linked list 不能像partitioin array一样从两边遍历
- 把小于value的加在前半段, 把 >= value的加在后半段
- 做法很普通: 建造两个list, midTail pointer, post pointer
- 把满足条件（<x, >=x）的数字分别放到两个list里面
- 记得用dummyNode track head.
- 最终midTail.next = post链接起来。



---

**9. [The Smallest Difference.java](https://github.com/awangdev/LintCode/blob/master/Java/The%20Smallest%20Difference.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      


---

**10. [Subarray Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/Subarray%20Sum%20II.java)**      Level: Hard      Tags: [Array, Binary Search, Two Pointers]
      


---

**11. [Sort Colors II.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors%20II.java)**      Level: Medium      Tags: [Partition, Quick Sort, Sort, Two Pointers]
      
Sort Color的普通版, sort all k colors in colors array.

Details 参见: https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Color.java

#### Quick Sort
- O(nk)



---

**12. [Sort Letters by Case.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Letters%20by%20Case.java)**      Level: Medium      Tags: [Partition, Sort, String, Two Pointers]
      
给一串字符(ASCII 大写, 小写字母), 要求sort 小写字母, 在大写字母前面. 

字母间的前后顺序无所谓, 也不需要preserve original order .

跟sort color分成相似.

#### Partition + Two pointers
- 其实就是quick sort里面的partition function的简化版
- Two pointers, 找一个 pivot 'a' 来区分大写小写字母
- ASCII code 里面 大写字母在小写字母前面, 数字更小
- 然后 while, move start++, end--,
- 每一轮都swap

#### Two pointers
- 直接用两个 pointer left/right 标记开头结尾
- 每次遇到 `>= 'a'` 就是小写字母, swap(chars, i, left);
- 每次遇到 `< 'a'` 就是大写字母, swap(chars, i, right);
- 注意: 每次处理完left swap, 任由for loop i++, 因为确定 [0 left] 都是准确的
- 每次处理完 right swap, 我们不确定从 right index 换过来的是不是正确的, 所以 i--, 跟for loop 的 i++抵消.
- 写 while loop 的 solution看起来更容易理解.



---

**13. [Two Sum II - Input array is sorted.java](https://github.com/awangdev/LintCode/blob/master/Java/Two%20Sum%20II%20-%20Input%20array%20is%20sorted.java)**      Level: Medium      Tags: [Array, Binary Search, Two Pointers]
      
升序array, 找2SUM.

#### Two pointers
- 排序好的array. Two pointer移动start和end，核查sum.
- 注意sum用long.
- O(n) time

#### Binary Search, 因为已经排好序了啊
- 定住一个valueA, 然后在剩下的里面 binary serach 找 (target - valueB)
- for loop O(n), binary search O(logn)
- overall time: O(nLogN), 就不写了



---

**14. [Partition Array by Odd and Even.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array%20by%20Odd%20and%20Even.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
- 更正常的start/end partition pointer类似: when condition meet, swap
- Clean up TODO



---

**15. [Sort Colors.java](https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java)**      Level: Medium      Tags: [Array, Partition, Quick Sort, Sort, Two Pointers]
      
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

**16. [Interleaving Positive and Negative Numbers.java](https://github.com/awangdev/LintCode/blob/master/Java/Interleaving%20Positive%20and%20Negative%20Numbers.java)**      Level: Medium      Tags: [Two Pointers]
      
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



---

**17. [Partition Array.java](https://github.com/awangdev/LintCode/blob/master/Java/Partition%20Array.java)**      Level: Medium      Tags: [Array, Quick Sort, Sort, Two Pointers]
      
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

**18. [[lint]. 3 Sum Closest.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%203%20Sum%20Closest.java)**      Level: Medium      Tags: [Array, Lint, Two Pointers]
      
3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用



---

**19. [[lint]. 2 Sum II.java](https://github.com/awangdev/LintCode/blob/master/Java/[lint].%202%20Sum%20II.java)**      Level: Medium      Tags: [Array, Binary Search, Lint, Two Pointers]
      
与 2sum II - input array is sorted类似. 都是sort array, 然后two pointer.

LintCode的题. 注意找的是greater/bigger than target。

由于给定条件允许O(nLogn):   
   sort
   two pointer

while里面two pointer移动。每次如果num[left]+num[right] > target，那么其中所有num[left++]的加上num[right]都>target.   
也就是,num[right]不动，计算加入挪动left能有多少组，那就是: right-left这么多。 全部加到count上去。     
然后right--.换个right去和前面的left部分作比较。



---

**20. [42. Trapping Rain Water.java](https://github.com/awangdev/LintCode/blob/master/Java/42.%20Trapping%20Rain%20Water.java)**      Level: Hard      Tags: [Array, Stack, Two Pointers]
      

这道题目的方法比较多.

#### Method1: Max wall from both sides
- Array: Left Max Wall vs Right Max Wall.
- 对于每个index而言, vertically 能存放的最大水柱, 就是靠 左 右 最高墙决定的: 
    - min(leftHighestWall, rightHighestWall) - currHeight.
- time: O(n)
- space: O(n)

#### Method2: Two Pointers
- Optimization from Method1: two pointer, 还是找左边最高和右边最高. O(1) space.
- 利用到了方法3里面的想法一样: 整个structure是被中间的最高bar 二分天下:
- always limited by the shorter wall: 左边按照maxLeft来计算, 右边按照maxRight来计算.
- time: O(n)
- space: O(1)

#### Method3: 2 Pointers, start from 2 sides
- 1. 找中间最高bar的index    
- 2. 两面往中心扫：每次加上（topBarIndex - currIndex）* (elevation from previous index).也就是每次加一个横条
- 3. 每次还要减去block自身的height
- time: O(n)
- space: O(1)

#### Method4: Stack
- 主要想法和方法3一致: 在山坡下坡的基础上, 一直用stack堆积bottom. 
- 最后遇到上升之前, 此时bottom可以用来跟stack之前堆积的所有下坡index做比较, 算跟他们高度相差的积水.
- 用了stack记录下坡, 然后用个while loop一挖到底的想法非常棒.
- time: O(n)
- space: O(n)




---

**21. [142. Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/142.%20Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Slow Fast Pointers
- find slow/fast to detect the meeting point
- find begin node of the cycle: traverse from head, also move slow; utill head/slow meets slow



---

**22. [360. Sort Transformed Array.java](https://github.com/awangdev/LintCode/blob/master/Java/360.%20Sort%20Transformed%20Array.java)**      Level: Medium      Tags: [Math, Two Pointers]
      

#### Two Pointers, Math
- Being able to analys the a*x^2 + b*x graph and find the `peak` or `valley`
- Math basics: x^2 dominates the overall curve so it is up to a to determine:
    - `valley`: if a < 0, both sides will be small and center will be large. Prioritize larger value.
    - `peak`: if a > 0, center will be small and both sides will be large. Prioritize smaller value.
    - starting index being 0 or n-1, is driven by `a`



---

**23. [849. Maximize Distance to Closest Person.java](https://github.com/awangdev/LintCode/blob/master/Java/849.%20Maximize%20Distance%20to%20Closest%20Person.java)**      Level: Easy      Tags: [Array, Basic Implementation, Two Pointers]
      

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, Two Pointers: start/end
- start/end point, 然后比较大小记录dist
    - 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
    - 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`




---

**24. [1213. Intersection of Three Sorted Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/1213.%20Intersection%20of%20Three%20Sorted%20Arrays.java)**      Level: Easy      Tags: [Hash Table, Two Pointers]
      

Very similar to 349.Intersection of Two Arrays.

#### Hash Table
- Use set to check
- Verify duplicates at end rst

#### Two Pointers
- similar to Intersection of Two Sorted Arrays
- Start from front/back, process 1 item at a time
    - if match, move all pointers 
- Optoin1: check from back
- Optoin2: check from frotn



---

**25. [986. Interval List Intersections.java](https://github.com/awangdev/LintCode/blob/master/Java/986.%20Interval%20List%20Intersections.java)**      Level: Medium      Tags: [Two Pointers]
      



#### Method1: Merge Interval
- There can be 1 overlapping on any interval, calculate the inner intersection: lo(A[i][0], B[j][0]), hi(A[i][1], B[j][1])
    - if low <= hi, a valid intersection exist; add
    - also, if A[i][1] < B[j][1]; that is A[i].end < B[j].end, then i++; otherwise j++
        - because the further-away `end` has been used, so move on.
- O(n)

#### Method2: Sweep line
- code is much more complex (pq, Point, process code... etc) than method1
- we can use point to track open/close, also want to specify if point belongs to A/B
- mark 2 global parameters: aOpen, bOpen.
    - process when A/B close, record if (aOpen, bOpen) has overlaop
    - clear up corresponding global parameter after A/B closes
- sort all pointers in priority queue by index
- Point: {boolean isOpen; int index}
- process the queue and remember to clean up all items on same index
- time: O(nlogn)
- space: O(n)




---

**26. [76. Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/76.%20Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

基本思想:
- 用个char[]存string的frequency.
- 2 pointer: 
    - move `end` to find a valid window; 
    - once valid inwindow found: now move `start` to narrow down to minimum window.
    - once window invalid, continue moving `end` and repeat last 2 steps
- HashMap的做法比char[]写起来要复杂一点, 但是更generic

#### Method0: Sliding Window + freq[256] + counter
- Almost identical approach as in `438. Find All Anagrams in a String` 
- use sliding window template:
    - 1) extend right pointer and reduce char count
    - 2) process when count == 0
    - 3) contract/shrink left side
- special on the `3) step`:
    - there is no hard length limit in this problem: in fact, the goal is to find the shortest length
    - `3) step` now apperas in the `while(counter == 0)` loop
    - shrink the left side of the window as long as counter == 0, until we break the `counter==0` balance.
- time: O(n) one pass
- space: O(1), freq[256] can be ignored.


#### Method1: init a valid freq map; maintain with counter
- Two Pointers, use 1 char freq map + counter to determine valid state
- Inspired by: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
- Idea: use freqMap and counter to maintain a valid substring range, use two pointers to iterate; reduce to `counter==0` which is the valid substring state.
- Steps:
    - 1) build valid freq count map based on target string
    - 2) use end index [0~n) to find valid char and reduce counter to find valid range
    - 3) count==0 gives valid range: process; then `map[s.charAt(start++)]++ == 0` to break the peace
- Explain `if (map[s.charAt(start++)]++ == 0) counter++`: 
    - when `count != 0`, `map[s.charAt(end++)]--` reduces freq regardless of what char it visits (it can be ANY char, rather than T characters)
    - when `count == 0`, `map[s.charAt(start++)]++` increases freq regardless of what char that is.
        - if `map[s.charAt(start)] == 0`: it is a T character being reduced to 0 previously (so we can break the balance on this char)
        - YES, map has other index that has 0 freq: however, `start` ONLY covers indexes that `end` has stepped through :)
- time: O(n)
- space: O(1)
- much faster than method2: skip the O(256*n) comparison logic.
- Note: from the concept, it is the reversed thinking of method2.

#### Method2: build valid map on the fly and compare. Two Pointers, Use 2 Char freq map
- Use 2 char freq maps: source/target.
    - target map: fixed freq map, used for comparision
    - source map: attempt to build a valid freq map on the fly
- two pointers: 
    - use index `start=[0, n)` as start index of source candidate
    - have a end pointer that will attempt to as far as possible to find 1st valid sequence
- time: have double while loop, but still O(n), why?
    - end pointer will at most reach full length n, only once
    - start pointer iterate source strichtly once O(n)
    - overall, it will be O(n)
- space: O(1), only used a constant char[256]
- Option2: use map, a bit more generic



---

**27. [244. Shortest Word Distance II.java](https://github.com/awangdev/LintCode/blob/master/Java/244.%20Shortest%20Word%20Distance%20II.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list



---

**28. [80.Remove Duplicates from Sorted Array II.java](https://github.com/awangdev/LintCode/blob/master/Java/80.Remove%20Duplicates%20from%20Sorted%20Array%20II.java)**      Level: Medium      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

最多可重复出元素的数量不超过2个. return unique item 的长度.

#### Basic 
- sorted array, 重复元素都在一起
- 跟 `Remove Duplicates from Sorted Array` 几乎一模一样, 只不过unique index现在可以 validate 2 位
- 其余一模一样, use index to track unique item; skip if duplicated for more than 2 times
- O(n) time, O(1) space
- 这里也可以真的用2个pointers 写while loop, 但是没有必要, 只是单纯地走一个for loop其实就足够.

#### Follow up: k duplicates, Two Pointers
- when index i and i-1 are diff, use count=1 to start
- in while loop, keep count++ until count==k
- reset when next diff comes in



---

**29. [26.Remove Duplicates from Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/26.Remove%20Duplicates%20from%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      
给一个sorted array, 把重复的去掉: 也就是把不重复的按照顺序贴上来, array末尾多余的位置无所谓.

return unique item 的长度.

#### Two Pointers
- sorted array, 重复元素都在一起
- Two pointers 其实也可以是一个 for loop pointer, 另一个 dynamic variable.
- track unique index
- skip duplicated items
- O(n)

#### 思考模式:
- Remove Duplicate from Array 不同于remove from linked list.
- LinkedList里面我们是最好不要动node.val的，直接把node去掉。
- 而array我们很难直接把node去掉，又不能用新array，那么就要：
- 把不重复的element一个个放到最前面。
- 这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
- 就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.
- *反向思维*：remove duplicate, 实际上也是找unique elements, and insert into original array



---

**30. [259. 3Sum Smaller.java](https://github.com/awangdev/LintCode/blob/master/Java/259.%203Sum%20Smaller.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
1. Similar to 15. 3Sum, but simpler.
1. 只需要count triplet, 但是不需要save triplet, 而且还不需要handle duplicated triplets
1. 发现start, end满足条件时候，(end - start)就是所有 sum <target的情况了。
1. 而一旦 > target, 那么就end--
1. 两层循环, O(n2)



---

**31. [977. Squares of a Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/977.%20Squares%20of%20a%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

#### Two Pointers
- negative index i, positive index j



---

**32. [67. Add Binary.java](https://github.com/awangdev/LintCode/blob/master/Java/67.%20Add%20Binary.java)**      Level: Easy      Tags: [Math, String, Two Pointers]
      
#### Two Pointers
- 注意加法结果的位置.
- Use two pointers i, j to track the 2 strings
- Add when i and j are applicable. While (i >= 0 || j >= 0)
- `StringBuffer.insert(0, x);`
- handle carry

#### reverse
- Reverse string -> Convert to Integer List, add up -> Convert back to string
- pointer 从前向后, 所以只需要 1个pointer.
- 操作复杂, 如上, 证明可以解决. 没必要reverse.

#### Incorrect: convert to Integer
把binary换成数字作加法. 如果input很大，那么很可能int,long都hold不住。不保险。



---

**33. [15. 3Sum.java](https://github.com/awangdev/LintCode/blob/master/Java/15.%203Sum.java)**      Level: Medium      Tags: [Array, Sort, Two Pointers]
      
#### sort array, for loop + two pointer
- 处理duplicate wthin triplets: 
    - 如果最外圈的移动点i重复, 一直顺到结尾的最后一个再用.
    - 如果是triplet内有重复, 用完start point, 移动到结尾.
- Note:
   - 1. 找 value triplets, 多个结果。注意，并非找index。    
   - 2. 要升序, 第一层for loop 从最后一个元素挑起, 保证了顺序。    
   - 3. 去掉duplicate: check用过的同样的数字，都跳掉。不需要用同样的数字再计算一边已有结果。
- 时间 O(n^2), 两个nested loop

#### For loop + 2Sum
- HashMap 2Sum. Remember to handle duplicates
   - 1. For loop 挑个数字A
   - 2. 2Sum 出一堆2个数字的结果
   - 3. Cross match 步骤1里面的A



---

**34. [19. Remove Nth Node From End of List.java](https://github.com/awangdev/LintCode/blob/master/Java/19.%20Remove%20Nth%20Node%20From%20End%20of%20List.java)**      Level: Medium      Tags: [Linked List, Two Pointers]
      

#### Two Pointer
- 1 end pointer to define the window based n steps
- 1 pre pointer to track the node before the targeting node
- when end reaches null, remove nth node: link pre and head.next 



---

**35. [349. Intersection of Two Arrays.java](https://github.com/awangdev/LintCode/blob/master/Java/349.%20Intersection%20of%20Two%20Arrays.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### Set
- 用到hashset找unique && duplicate: O(m+n)

#### Binary Search
- 可以用binary search 找数字. 
- Note:binary search一定需要array sorted: nLog(m)



---

**36. [844. Backspace String Compare.java](https://github.com/awangdev/LintCode/blob/master/Java/844.%20Backspace%20String%20Compare.java)**      Level: Easy      Tags: [Stack, Two Pointers]
      

#### Method1: Two pointers to backtack from end of string
- time: O(n)
- space: O(1)

#### Method2: Stack
- need to remove entity just added 
- use stack to hold array content; pop if # is found



---

**37. [245. Shortest Word Distance III.java](https://github.com/awangdev/LintCode/blob/master/Java/245.%20Shortest%20Word%20Distance%20III.java)**      Level: Medium      Tags: [Array, Design, Hash Table, Two Pointers]
      

跟243/244不同: 这里允许list里面有重复的word.

#### Method1: Two Pointers, one pass
- Follow up of 243. Shortested Word Distance
- 特别handle `word == word1 == word2` case:
    - p1 and p2 will always be the same
    - when `word == word1 == word2`, simply calculate distance using the `old p1 or p2` with `curr index i`
- The rest impl aligns with 243.

#### Method2: Hash Table
- when `word1==word2`, make usre to skip `p1==p2` by increasing i or j
- The rest impl aligns with 244
- Time: still O(n), but slower than Method1: 2 passes
- Space: uses extra space O(n) to hold all indexes



---

**38. [141. Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/141.%20Linked%20List%20Cycle.java)**      Level: Easy      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Method1: Two Pointer: Slow Fast Pointer
- Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
- https://leetcode.com/problems/linked-list-cycle/solution/
- O(1) sapce: 用快慢指针, `start=head.next`, `end=head.next.next`
- Fast pointer will eventually catch up to slow pointer

#### Method1: Hash Table
- O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle



---

**39. [567. Permutation in String.java](https://github.com/awangdev/LintCode/blob/master/Java/567.%20Permutation%20in%20String.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      

#### Method1: Sliding window with left/right Pointers
- Sliding window template:
    - 1) Check right pointer and move right
    - 2) Move left when necessary
    - 3) Verify count == 0 & end state
    - Note: normally 2) and 3) are in reversed order; this problem is a bit different
- This is efficient when the number of characters is not limited to 26, the runtime is still O(m + n)
- time: O(m + n), m = s1 length, n = s2 length
- space: O(k), k = # of possible chars, 26 in this case

#### Method2: Two Pointer, but brutle verify freq count
- 如果做s1的permudation, 时间复杂度是O(n!) 肯定不可以
- 这里用HashTable的做法 (因为26字母, 所以用int[26]简化) 来记录window内的 character count
- 如果window内的character count 相等, 那么就是permudation
- 更进一步优化: 找两个map相互对应, 不如用一个 int[26]: s1对遇到的character做加法, s2对遇到的character做减法
- two pointer 运用在 n1, n2 的把控; 以及 s2.charAt(i - n1) 这一步
- time: (m + n)
    - However, if # of possible chars is more than 26
    - For example, `k unique characters`, then the runtime will become: O(m + nk)
    
- space: O(k), k = # of possible chars, 26 in this case



---

**40. [727. Minimum Window Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/727.%20Minimum%20Window%20Subsequence.java)**      Level: Hard      Tags: [DP, Hash Table, Sliding Window, String, Two Pointers]
      

#### Sliding Window
- DIFFERENT from sliding window for substring (`76. Minimum Window Substring`)
    - because this problem rquries keeping the order of characters from the target string
    - Use a `backtrack mechanism` based on target matching to find closest left starting point to right
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T chars in S, in order.
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- Be VERY careful about pointer and index.
- time: O(n^2), backtrack n steps
- Since it requires **order of substring**, `freqMap+counter+twoPointers` approach is NOT applicable

#### DP
- TODO



---

**41. [345. Reverse Vowels of a String.java](https://github.com/awangdev/LintCode/blob/master/Java/345.%20Reverse%20Vowels%20of%20a%20String.java)**      Level: Easy      Tags: [String, Two Pointers]
      
vowels: 元音字母. 要求reverse所有元音字母.

##### 方法1: two pointer.
- 前后两个指针, 在while loop里面跑.
- 注意 i<j, 一旦相遇, 就break.
- 找到合适的, 就做swap.
- StringBuffer可以 sb.setCharAt()记得用.
- O(n)

##### 方法2:
拿出所有vowels, 反过来放进去. O(n)



---

**42. [28. Implement strStr().java](https://github.com/awangdev/LintCode/blob/master/Java/28.%20Implement%20strStr().java)**      Level: Easy      Tags: [String, Two Pointers]
      
给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1



---

**43. [246. Strobogrammatic Number.java](https://github.com/awangdev/LintCode/blob/master/Java/246.%20Strobogrammatic%20Number.java)**      Level: Easy      Tags: [Enumeration, Hash Table, Math, Two Pointers]
      

根据题意枚举, 再根据规则basic implementation

#### HashTable + Two Pointer
- compare left/right

#### Alter input
- flip number (6 and 9), and then reverse the string, see if the string is the same.
- takes more




---

**44. [88. Merge Sorted Array.java](https://github.com/awangdev/LintCode/blob/master/Java/88.%20Merge%20Sorted%20Array.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

给两个排好序的数组, merge. 其中一个数组nums1有多余的位置

#### Basics
- A够长，那么可以从A的尾部开始加新元素: 从尾部，是大数字优先排末尾的.  
- Deal with remaining:
    - When A values are used up, put remian of B into it
    - When B values are finished, there is nothing todo. The remain of A is already in place.



---

**45. [243. Shortest Word Distance.java](https://github.com/awangdev/LintCode/blob/master/Java/243.%20Shortest%20Word%20Distance.java)**      Level: Easy      Tags: [Array, Two Pointers]
      


#### Two Pointers
- Use 2 pointers to record **most recent** pos of word1 and word2
    - move pointer i [0 ~ n) and keep refreshing pos1 and pos2
    - both pos1 and pos2 will be as adjacent as possible since they both moving towards same direction
- keep recalculating best distance when either word is matched
- 而同一时间，只需要计算一个最近的curr distance: greedy不断变更A/B index, 做比较




---

**46. [234. Palindrome Linked List.java](https://github.com/awangdev/LintCode/blob/master/Java/234.%20Palindrome%20Linked%20List.java)**      Level: Easy      Tags: [Linked List, Two Pointers]
      

#### Reverse Linked List
- Palindrome概念很简单: 两边回溯相等. However: 
    - 1) cannot random access index on linkded list
    - 2) cannot reverse iterating linked list
- solution: reverse linked list: 遍历接开头
    - 1) 用快慢指正找到mid point
    - 2) reverse 2nd half
    - 3) compare leftList and rightList
- Time O(n), 而且不需要用额外的空间(只是调换半个list的内部顺序), 所以空间O(1)



---

**47. [287. Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/287.%20Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Binary Search on Value, Cycle Detection, Slow Fast Pointer, Two Pointers]
      

#### Method1: Slow Fast Pointer
- Use LinkedList Cycle Concept:
    - Each element the array is like a `Node {int currIndex; int val;}`, where the `val` is also pointer to next Node
    - A node is like a portal; a pointer can: 1) visit a node by currIndex, 2) pick up newIndex = `nums[currIndex]`, then keep repeating step 1 and 2.
    - Important: since nums is immutable, the pointer footprint is unique/linear
    - Just like linked list. Therefore, use slow/fast pointer to detect cycle.
- https://leetcode.com/problems/find-the-duplicate-number/solution/
- it is now the same as `142. Linked List Cycle II`

#### Method2: Binary Search on value
- 注意不要思维定式: binary search `NOT on index`
    - `binary search on value`: [1, n]
    - O(logN)
- validate(nums, candidate): for loop to count number of `value <= candidate`
    - `count == candidate`: no duplicate from [1 ~ candidate]. 
    - `count < candidate`: missing element in [1~ candidate], so duplicates are in later range. start = mid;
    - `count > candidate`: there are duplicates in [1~ candidate]. end = mid;
- Time: O(nLogN)
- Space: O(1)



---

**48. [340. Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/340.%20Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, LinkedHashMap, Sliding Window, String, Two Pointers]
      

- Method1 and Method2 are identical to `159. Longest Substring with At Most Two Distinct Characters`. 
- However, time complexity for Method2 in increases to O(nk). https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- we want to do better than that (Method3)

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == k, process and record max len
    - 3) if map.size() > k, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(k)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(nk) to find the left-most element
- space: O(k)

#### Method3: Sliding window + LinkedHashMap
- https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- as mentioned above, Method2 uses O(nk), because it takes O(k) to find head item that was inserted first
    - meanwhile, we still need the hash map feature to get/put/remove last occurance of a char with O(1)
- Solution: use a LinkedHashMap: 
    - `map.entrySet().iterator()` maintains the insertion order!
- Special handling:
    - since we want the `lastOccurMap` to preserve laset insertion order
    - we need to `remove` the char every time before put.
- time: O(n)
- space: O(k)




---

**49. [1004. Max Consecutive Ones III.java](https://github.com/awangdev/LintCode/blob/master/Java/1004.%20Max%20Consecutive%20Ones%20III.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      


#### Sliding window + Left/Right Two Pointers
- https://leetcode.com/problems/max-consecutive-ones-iii/solution/
- Start with DFS thought, but realize redundant calculations: 
    - we never need to flip 2 indexes [A], [C] from 0 -> 1, if there is a [B] in middle that is 0 too
    - the flipped k zeroes must be consecutive too
- we can utilize two pointers to establish a window that captures k zeroes
    - always expend right pointer; if seeing an zero, k--
        - note: `len = right - left + 1` is the ongoing max length
    - when k < 0 (too many zeros), we need to slide the left side of the window to make sure:
        - keep window len 
        - potentially do k++ when A[left]==0
- goal: matain a max size of the window, until right  == n
- return (right - left). at this moment, right == n, so no need to (right - left + 1)



---

**50. [283. Move Zeroes.java](https://github.com/awangdev/LintCode/blob/master/Java/283.%20Move%20Zeroes.java)**      Level: Easy      Tags: [Array, Two Pointers]
      

Move non-zero elements to front of array; preseve order.

#### Two Pointers
- Outside pointer that moves in certain condition. 
- Save appropirate elements



---

**51. [125. Valid Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/125.%20Valid%20Palindrome.java)**      Level: Easy      Tags: [String, Two Pointers]
      
验证string是不是 palindrome. 只考虑 alphanumeric, 其他字符可以忽略

#### Two Pointers
- Time O(n), Space O(1).
- 普通方法, 两边check, 速度相比较regular expression更快. leetcode 4ms.
- Use helper functions.

#### Check Palindrome
- 前后两个指针, 往中间移动, 查看是否字母重合

#### 过滤 alphanumeric
- 可以用 ASCII code 来手动过滤, 只要 '0' ~ '9', 'a' ~ 'z', 'A' - 'Z' 之间的
- 也可以用 regular expression: match 所有这些字母, 是 [a-zA-Z0-9]
- 那凡是不是这些字母的 match, 就是取反: "[^a-zA-Z0-9]". 测试: https://regex101.com/



---

**52. [350. Intersection of Two Arrays II.java](https://github.com/awangdev/LintCode/blob/master/Java/350.%20Intersection%20of%20Two%20Arrays%20II.java)**      Level: Easy      Tags: [Binary Search, Hash Table, Sort, Two Pointers]
      

#### HashMap
- Map of nums1: <num, # appearance>
- check nums2 against nums1 map
- time:O(n + m)
- space:O(n + m)

#### Binary Search
- 



---

**53. [438. Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/438.%20Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, Two Pointers]
      

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### Method1: Sliding Window, Hash Table. 
- A creative way of using anagram char count `hash[c] >= 0` to determine if the curr c is a target char of the deesired anagram.
    - because we always reduce hash[c]-- for all characters
    - so only the anagram chars would be `hash[c] >= 0` after reducing.
- https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
- Slinding window always has left/right pointer:
    - 1) at any given time move 1 index at a time: expand right window, process rsult, shrink left window
    - 2) one of the basic goal is to maintain fixed window size
- algo:
    - calc char freq of the target p, and store in a hash[256]; it will be used to distinguish anagram chars: `hash[c] >= 0` indicates a anagram char
    - expand right window: move right to expand the window; ONLY when meeting a anagram char, count--
    - process result: if count reduces to 0, one anagram is found
    - shrink left window: if (right - left) == p.length(), drop curr left char, and move forward
- how could we rely on only just `count == 0`? 
    - the hidden pre-condition is `right - left must already be p.length()`, which is validaterd in prev iteration
- time: O(n)
- space: O(1)

#### Method2: HashTable
- count character apperance -> hash table, here just a int[26]
    - use a window to record count++ and count--, in order to compare with countP
- prep the countP takes O(m) time
- time: O(n) + O(m)
- space: O(n)





---

**54. [632. Smallest Range Covering Elements from K Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/632.%20Smallest%20Range%20Covering%20Elements%20from%20K%20Lists.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, Two Pointers]
      

#### Method1: Sliding Window
- First sort all of the items together by actual val using `Node {int val, int row}`
- Slinding window goal: 
    - 1) use right to find range that touches all rows, 
    - 2) use left to shrink the range
- Sliding Window Template
    - move right pointer
    - Counts[i] = # of elements used in left/right range
        - when counts[i] == 0, countUnique++; the number of row/list being included
    - when count == row size: 
        - processing & save shorter range by using left/right Pointers
        - move left pointer; when counts[i] == 0, countUnique--
- time: O(nlogn) for initial sort and then O(n) to process
- space: O(n)
- What is hard here? To think of the idea of counting one usage of each row: 
    - when each all rows are used at least 1 time
    - calculate the min dist

#### Method2 PQ?, Similar to merging k array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/discuss/104893/Java-Code-using-PriorityQueue.-similar-to-merge-k-array
- https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solution/



---

**55. [159. Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/159.%20Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

如题.

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == 2, process and record max len
    - 3) if map.size() > 2, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(1)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(n) 
- space: O(1)



---

**56. [611. Valid Triangle Number.java](https://github.com/awangdev/LintCode/blob/master/Java/611.%20Valid%20Triangle%20Number.java)**      Level: Medium      Tags: [Array, Two Pointers]
      

#### Method1: Fix max and backward counting
- Sort nums: O(nlogn)
- Set max value fixed on right side at k
    - set 2nd value from right index j
    - set last value at min index i
    - if `nums[i] + nums[j] > nums[k]`: with fixed j, i can pick from [i, j-1] combinations
        - then j--, to pick another j candidate
    - maintain a window [i,j]; if invalid, move i++
- time: O(n^2)
- Note: very similar to 3-sum, fixing 1 index and use 2 pointers to move window

#### Method2: Fix min and forward counting
- Sort nums: O(nlogn)
- Set min value at i
    - set 2nd value at j=i+1; and 3rd value at k=i+2
    - find max of k that fits into triangle
    - count all possible k candidates from [j+1, k]
    - then move j to a new candidate
- O(n^2)



---

