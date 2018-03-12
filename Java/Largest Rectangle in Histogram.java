H
1520813180
tags: Array, Stack

#### Monotonous Stack
重点是根据找Histogram里面rectangle的性质, 维持一个单调递增的Stack
在loop over indexes的时候:
- 如果高度>= previous peek(), 那么对于那个peek, 就意味着, 往下走, 一直走高嘛, 之前的peek总可以继续抄底
- 什么时候不能抄底了呢? 就是有一个下降趋势的时候
- 这时候并不是calculate所有前面的peek, 而是考虑 大于 current height的之前所有的peek.
- 把这些peek到 current height 前一格的rectangle全部找出来: stack.pop()
- 这个stack.pop()的过程里面, 其实没有算上 current height, 因为需要留到下一轮, 把current index加进stack 再说
- 为什么用stack? 因为需要知道连续递增的peek, stack.peek() O(1), 好用
  而其实不用stack, 也可以用其他方式记录所有height, 只不过要 O(n)去找peek不方便

#### 知识点
- 理解monotonous stack 是如何被维护的
- 维护monotonous stack 是题目需要, 而不是stack本身性质, 是一种借助 stack.peek() O(1)的巧妙用法.


```
/*
LeetCode:
https://leetcode.com/problems/largest-rectangle-in-histogram/description/

Given n non-negative integers representing the histogram's bar height,
where the width of each bar is 1, find the area of largest rectangle in the histogram.

[missing image]
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

[missing image]
The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
 */

/*
Thoughts:
Maintain monotonous stack: whenever seeing a decreasing element, process.
Some characteristics when calculating rectangle in histogram
- You turn to think: I have to loop over all indexes then I know for any specific height, for example, 1, occurs across [0 ~ n]. 
  That's partially true
- We should make be best efforts on calculating: up to certain index, what's maximum we could get.
  As we maintain the monotonous ascending stack, stack.peek() element is always the starting point of the rectangle
- [important] Only need to stop and calculate rectangle when seeing a descending element
- It's like, keep climbing the mountain; when it descreases at a point, 
  trace back to use all previous peeks and form rectangle with current position
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int max = 0;
        Stack<Integer> stack = new Stack<>(); // Use stack to store the index
        for (int i = 0; i <= n; i++) {
            int currHeight = i == n ? -1 : heights[i];
            // Keep stack monotonous; if not, process && calculate rectangle
            while (!stack.isEmpty() && currHeight <= heights[stack.peek()]) {
                int currPeekHeight = heights[stack.pop()];
                // exclude current position; it'll be calculate in next round.
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, currPeekHeight * width);
            }
            stack.push(i);
        }

        return max;
    }
}
```