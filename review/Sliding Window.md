 
 
 
## Sliding Window (13)
**0. [Sliding Window Median.java](https://github.com/awangdev/LintCode/blob/master/Java/Sliding%20Window%20Median.java)**      Level: Hard      Tags: [Design, Heap, MaxHeap, MinHeap, Sliding Window]
      
Data Stream Median 的同理题目: 不只是不断增加的Sequence, 而且要remove item (保持一个window size)

#### MaxHeap, MinHeap
- Median还是用min-heap 和 max-heap. Time(logN)
- 加/减: prioirtyQueue, log(n)
- findMedian: O(1)
- 加一个数, 减一个数。
- 加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
- 抽完balance一下

#### 注意
- 用maxHeap, minHeap时候, 习惯选择让maxHeap多一个数字:
- 左边的maxHeap总有 x+1或者x个数字
- 后边minHeap应该一直有x个数字



---

**1. [76. Minimum Window Substring.java](https://github.com/awangdev/LintCode/blob/master/Java/76.%20Minimum%20Window%20Substring.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

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

**2. [674. Longest Continuous Increasing Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/674.%20Longest%20Continuous%20Increasing%20Subsequence.java)**      Level: Easy      Tags: [Array, Coordinate DP, DP, Sliding Window]
      

找连续的持续上升子序列的长度.

#### Sliding window
- update the window start index;
    - `left` in sliding window
    - update when we need to start a new range: `nums[i-1] >= nums[i]` 
- calculate the max distance `i - widowStart + 1`
- O(n) time and O(1) space

#### Simple Array solution
- size++ when meeting condition `nums[i] > nums[i - 1]`
- otherwise, reset size = 1
- track max all the way

#### Coordinate DP
- 1D coordinate, dp 的角标, 就是代表 index i 的状态
- 求最值, dp[i] = 在index i位置的最长子序列
    - 如果 nums[i] > nums[i - 1], dp[i] = dp[i - 1] + 1
    - 如果没有持续上升, 那么dp[i] = 1, 重头来过
- maintain max




---

**3. [567. Permutation in String.java](https://github.com/awangdev/LintCode/blob/master/Java/567.%20Permutation%20in%20String.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      

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

**4. [727. Minimum Window Subsequence.java](https://github.com/awangdev/LintCode/blob/master/Java/727.%20Minimum%20Window%20Subsequence.java)**      Level: Hard      Tags: [DP, Hash Table, Sliding Window, String, Two Pointers]
      

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

**5. [239. Sliding Window Maximum.java](https://github.com/awangdev/LintCode/blob/master/Java/239.%20Sliding%20Window%20Maximum.java)**      Level: Hard      Tags: [Deque, Heap, Sliding Window]
      

#### Method1: Deque, Monotonous queue
- 维持monotonuous queue: `front is always at max` and the `tail end is min`. Always need to return the max end of queue.
- when adding new elements x: 
    - 1) start from small-end of the queue
    - 2) drop all smaller elements 
    - 3) append to the ending element that is larger than x.
    - This is to maintain a front->tail decreasing queue
- when sliding window: queue curr window 里面 最大的已经在max-end,  remove it if needed.
- 妙：用deque数据结构（实际上采用LinkedList的形式）来做一个`递减的queue`: better than using arraylist, since DeQueue(linked list) removes at O(1) cost
- 每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
- 我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
- Option1: sliding window template using right/left + while loop
    - 1) tailing the new number to max queue, if applicable
    - 2) process: record max
    - 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
- Option2: same concept, but use index `i` to mark right, and `i - k + 1` to mark left.
- time: O(n), one pass
- space: O(k), store the deque


#### Method2: Heap
- can always build a `class Node{index, val}`; and sort them with PQ of size k
- time: O(nlogK)
- space: O(k)
- this is not linear time, not as good as method1



---

**6. [1040. Moving Stones Until Consecutive II.java](https://github.com/awangdev/LintCode/blob/master/Java/1040.%20Moving%20Stones%20Until%20Consecutive%20II.java)**      Level: Medium      Tags: [Array, Sliding Window]
      


#### Analyze to understand
- Make sure to sort array: we need to use the actual number range `A[j] - A[i]`, which requires the array to  be sorted
- we want to form a new array where A[n-1] - A[0] + 1 == n; order does not matter but all slots need to be filled consecutivly
- max moves: https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/289357/c%2B%2B-with-picture
    - A interval will be automatically dropped between A[0] and A[1], if moving A[0] first
    - Same, a interval between A[n-2] and A[n-1] will be dropped when moving A[n-1] first
    - so largest possible move = firstItem + remaining range size - n items = 1 + (A[n-1] - A[1] + 1) - n = A[n-1] - A[1] -n + 2
        - or A[n-2] - A[0] - n + 2
- min moves: `Sliding Window`
    - use slinding window to assume a right pointer, to make sure A[right] - A[left] + 1 < n; otherwise, move left++
    - check # of included stones
    - calculate remaining, which is remaining moves
- Handle min move edge case:
    - Consecutive Array up to right = n - 1; need 2 moves to finish



---

**7. [346. Moving Average from Data Stream.java](https://github.com/awangdev/LintCode/blob/master/Java/346.%20Moving%20Average%20from%20Data%20Stream.java)**      Level: Easy      Tags: [Design, Queue, Sliding Window]
      

给一个interface, design一个structure, 能够计算moving window average.

#### Queue
- 读懂题目, 注意average 和 window 的处理.
- 简单的queue.size() comparison
- Note: if we it is calculate moving-window-product, better to use deque :)
- Sliding window?
    - It has the spirit of slinding window: 1) maintain a range; 2) check range size `if (queue.size() > size)`
    - Though, the solution must use a data structure to store data; it is not the traditional sliding window type of `left/right` pointer problem



---

**8. [340. Longest Substring with At Most K Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/340.%20Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters.java)**      Level: Hard      Tags: [Hash Table, LinkedHashMap, Sliding Window, String, Two Pointers]
      

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

**9. [1004. Max Consecutive Ones III.java](https://github.com/awangdev/LintCode/blob/master/Java/1004.%20Max%20Consecutive%20Ones%20III.java)**      Level: Medium      Tags: [Sliding Window, Two Pointers]
      


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

**10. [438. Find All Anagrams in a String.java](https://github.com/awangdev/LintCode/blob/master/Java/438.%20Find%20All%20Anagrams%20in%20a%20String.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, Two Pointers]
      

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

**11. [632. Smallest Range Covering Elements from K Lists.java](https://github.com/awangdev/LintCode/blob/master/Java/632.%20Smallest%20Range%20Covering%20Elements%20from%20K%20Lists.java)**      Level: Hard      Tags: [Hash Table, Sliding Window, Two Pointers]
      

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

**12. [159. Longest Substring with At Most Two Distinct Characters.java](https://github.com/awangdev/LintCode/blob/master/Java/159.%20Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters.java)**      Level: Medium      Tags: [Hash Table, Sliding Window, String, Two Pointers]
      

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

