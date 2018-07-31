 
 
 
## TreeMap (1)
**0. [My Calendar I.java](https://github.com/awangdev/LintCode/blob/master/Java/My%20Calendar%20I.java)**      Level: Medium      Tags: [Array, TreeMap]
      

Given a list of interval as calendar items. Check if newly added calendar item is overlapping.

Understand it is only checking time, but not requiring to insert into right spot. No need to overthink.

#### Simply O(n) check on array
- number of test cases is small, like 1000, so less concern about the time complexity
- simply loop over the list of intervals, and check if any overlapping.
- where to insert does not really matter: every time we are just checking for overlaopping, not merging any range
- **IMPORTANT**: if interval over lapping, they will have this property `Math.max(s1, s2) < Math.min(e1, e2)`. This will help detect the overlapping very easily.
- O(n^2) runtime, with simple code. But somehow this approach is faster than the TreeMap solution: maybe the test cause causes avg O(n)?

#### TreeMap
- One constraint from the simply array solution: it always cost O(n) to find the potential overlapping interval
- We can manually sort and always manually try to find the correct element via binary search, or we could store the interval in a treeMap<startKey, endValue>, where the intervals are sorted by `start`.
- As result, all we need to do for book(start, end) is to find the next element ceiling(start), last element floor(start), and check for overlapping
- This approach also saves the custom data structure
- Overall cost O(nlogn)

##### About TreeMap
- always with key sorted ascendingly 
- more costly than regular HashMap because of the sorting. Building treemap of n items: O(nlogn)

#### Sweep line
- use `Point{int start, end; boolean start}` to mark start/end of class. Add to pq.
- Adding new item to pq, sort, and check if overlapping occurs by counting started classes
- If started classes > 1, that means we overlapped.
- Every time it could consume all classes to find the overlap, O(n^2).
- Not quite need to sort or insert at correct point, and this solution requires longer code. Not quite worthy it for a simple problem.




---

