E
tags: Sort, Basic Implementation
time: O(1), only 3 elements
space: O(1)

#### Analyze to understand
- put 3 elements into array, sort and follow below rules:
- min: 
    - if 3 elements consecutive, 0 move.
    - if only 1 pair of the two elemnets consecutive or if they have 1 slot in between, it needs exactly 1 move
    - otherwise, at most 2 moves
- max: # of open slots between them (high - low + 1) - n, where n = 3
- Follow up: `1040. Moving Stones Until Consecutive` is more interesting with special rulese (cannot move to `ending spot`), and it uses sliding window concept

```
/*
Three stones are on a number line at positions a, b, and c.

Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

 

Example 1:

Input: a = 1, b = 2, c = 5
Output: [1,2]
Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
Example 2:

Input: a = 4, b = 3, c = 2
Output: [0,0]
Explanation: We cannot make any moves.
Example 3:

Input: a = 3, b = 5, c = 1
Output: [1,2]
Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
 

Note:

1 <= a <= 100
1 <= b <= 100
1 <= c <= 100
a != b, b != c, c != a

 */

/*
- min: min dist between them or 2
- max: # of open slots between them c - a + 1 - 3
*/
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        int max = arr[2] - arr[0] - 2;
        if (arr[2] - arr[0] == 2) return new int[]{0, max};
        if (arr[1] - arr[0] <= 2 || arr[2] - arr[1] <= 2) return new int[]{1, max};
        return new int[]{2, max};
    }
}

/*
if consecutive pts, no need to move, return {0, 0}
max steps: the amount of empty slots between end - start - 2
min steps case1: 
if min of the two intervals has <= 1 spot, we can fill the spot => 1 step
if min of the two intervals has > 1 spot, we have to move 2 steps.

Tip:
Sort input, no need manual find min/mid/max. Adds some delay.
*/
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] input = {a, b, c};
        Arrays.sort(input);
        
        // calculate
        int max = calDist(input[0], input[2]) - 1;
        int left = calDist(input[0], input[1]);
        int right = calDist(input[1], input[2]);
        
        int min = 2;
        if (left + right == 0) {
            min = 0;
        } else if (Math.min(left, right) <= 1) {
            min = 1;
        }
        
        return new int[] {min, max};
    }
    
    private int calDist(int start, int end) {
        return end - start - 1;
    }
}
```