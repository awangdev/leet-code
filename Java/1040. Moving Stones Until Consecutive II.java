M
tags: Sliding Window, Array
time: O(nlogn)
space: O(n)


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

```
/**
On an infinite number line, the position of the i-th stone is given by stones[i].  Call a stone an endpoint stone if it has the smallest or largest position.

Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.

In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5, since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

 

Example 1:

Input: [7,4,9]
Output: [1,2]
Explanation: 
We can move 4 -> 8 for one move to finish the game.
Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
Example 2:

Input: [6,5,4,3,10]
Output: [2,3]
We can move 3 -> 8 then 10 -> 7 to finish the game.
Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
Notice we cannot move 10 -> 2 to finish the game, because that would be an illegal move.
Example 3:

Input: [100,101,104,102,103]
Output: [0,0]
 

Note:

3 <= stones.length <= 10^4
1 <= stones[i] <= 10^9
stones[i] have distinct values.
*/

/*
Analyze to understand:
- we want to form a new array where A[n-1] - A[0] + 1 == n; order does not matter but all slots need to be filled consecutivly
- max moves: https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/289357/c%2B%2B-with-picture
    - A interval will be automatically dropped between A[0] and A[1], if moving A[0] first
    - Same, a interval between A[n-2] and A[n-1] will be dropped when moving A[n-1] first
    - so largest possible move = firstItem + remaining range size - n items = 1 + (A[n-1] - A[1] + 1) - n = A[n-1] - A[1] -n + 2
        - or A[n-2] - A[0] - n + 2
- min moves:
    - use slinding window to assume a right pointer, to make sure A[right] - A[left] + 1 < n; otherwise, move left++
    - check # of included stones
    - calculate remaining, which is remaining moves
- Handle min move edge case:
    - Consecutive Array up to right = n - 1; need 2 moves to finish
*/
class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        
        int n = stones.length, left = 0, right = 0, min = n;
        int max = Math.max(stones[n - 1] - stones[1] - n + 2, stones[n - 2] - stones[0] - n + 2);
        
        while (right < n) {
            while (stones[right] - stones[left] + 1 > n) left++;
            int count = right - left + 1;
            if (count == n - 1 && stones[right] - stones[left] + 1 == n - 1) {
                min = Math.min(min, 2);
            } else {
                min = Math.min(min, n - count);
            }
            right++;
        }
        
        return new int[]{min, max};
    }
}
```