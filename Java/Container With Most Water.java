M

类似木桶理论。盛水的最高取决于最低的那面墙。
左右两墙，往中间跑动。
另，若一面墙已经小于另外一面，就要移动，换掉矮墙（可能下一面更高，或更低）；但决不能换掉当下的高墙，因为低墙已经limit的盛水的上限，若高墙移动，导致两墙之间距离减少，就注定水量更少了。（弄啥来，不能缺心眼啊）
```
/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Example
Given [1,3,2], the max area of the container is 2.

Note
You may not slant the container.

Tags Expand 
Two Pointers Array
*/

/*
Thoughts:
Start from 2 sides with 2 pointers, use those as 2 walls.

Height of water is limited by the lower wall. For example, left wall < right wall, width = right.x - left.x
	Now, if we move right wall: right--, then width = width-1, and the whole block is still limited by the lower left wall. So, this is not a good move. 
	Instead, when left wall < right wall, we move left++.
	On the other hand, if lett wall > right wall, right--.
*/
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: an integer
     */
    public int maxArea(int[] heights) {
    	if (heights == null || heights.length == 0) {
    		return 0;
    	}
    	int left = 0;
    	int right = heights.length - 1;
    	int maxWater = Integer.MIN_VALUE;
    	while (left < right) {
    		maxWater = Math.max(maxWater, (right-left) * (heights[left] < heights[right] ? heights[left] : heights[right]));
    		if (heights[left] < heights[right]) {
    			left++;
    		} else {
    			right--;
    		}
    	}
    	return maxWater;
    }
}

```