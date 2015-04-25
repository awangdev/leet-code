/*
Example
Given height = [2,1,5,6,2,3],
return 10.

Tags Expand 
Array Stack

Thinking Process:
///TODO: missing thinking process for Largest Rectangle in Histogram

*/

public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }    
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int current = (i == height.length) ? -1 : height[i];
            while (!stack.empty() && current <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.empty() ? i : i - stack.peek() - 1;
                max = Math.max(max, w * h);
            }
            stack.push(i);
        }
        return max;
    }
}

