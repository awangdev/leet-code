 
 
 
## Cycle Detection (3)
**0. [142. Linked List Cycle II.java](https://github.com/awangdev/LintCode/blob/master/Java/142.%20Linked%20List%20Cycle%20II.java)**      Level: Medium      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Slow Fast Pointers
- find slow/fast to detect the meeting point
- find begin node of the cycle: traverse from head, also move slow; utill head/slow meets slow



---

**1. [141. Linked List Cycle.java](https://github.com/awangdev/LintCode/blob/master/Java/141.%20Linked%20List%20Cycle.java)**      Level: Easy      Tags: [Cycle Detection, Linked List, Slow Fast Pointer, Two Pointers]
      

#### Method1: Two Pointer: Slow Fast Pointer
- Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
- https://leetcode.com/problems/linked-list-cycle/solution/
- O(1) sapce: 用快慢指针, `start=head.next`, `end=head.next.next`
- Fast pointer will eventually catch up to slow pointer

#### Method1: Hash Table
- O(n) space: 用HashMap，一直add elements.  如果有重复，那么很显然是有Cycle



---

**2. [287. Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/287.%20Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Binary Search on Value, Cycle Detection, Slow Fast Pointer, Two Pointers]
      

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

