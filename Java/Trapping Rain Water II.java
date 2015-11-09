/*
Trapping Rain Water II
Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.


Example
Given 5*4 matrix

[12,13,0,12]
[13,4,13,12]
[13,8,10,12]
[12,13,12,12]
[13,13,13,13]
return 14.

Tags Expand 

LintCode Copyright Heap Matrix

*/

/*
Thoughts: same idea as the trap Rain Water I.
Since this is not 1-way run through a 1D array (2D array can go 4 directions...), need to mark visted spot.
Again, 4 border are cached with heigest wall values. The highest will always be passed to current spot for comparison.
Check current spot against its surrounding

Use PriorityQueue, sort highest on top: always give highest one. This is used to do comparison.


*/