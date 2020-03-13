 
 
 
## Binary Search on Value (1)
**0. [287. Find the Duplicate Number.java](https://github.com/awangdev/LintCode/blob/master/Java/287.%20Find%20the%20Duplicate%20Number.java)**      Level: Medium      Tags: [Array, Binary Search, Binary Search on Value, Cycle Detection, Slow Fast Pointer, Two Pointers]
      

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

