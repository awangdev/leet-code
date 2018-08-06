H
1533535937
tags: DP, Hash Table

Frog jump 的题目稍微需要理解: 每个格子可以 jump k-1, k, k+1 steps, 而k取决于上一步所跳的步数. 默认 0->1 一定是跳了1步.

注意: int[] stones 里面是stone所在的unit (不是可以跳的步数, 不要理解错).

#### DP
- 原本想按照corrdiante dp 来做, 但是发现很多问题, 需要track 不同的 possible previous starting spot.
- 根据jiuzhang答案: 按照定义, 用一个 map of <stone, Set<possible # steps to reach stone>>
- 每次在处理一个stone的时候, 都根据他自己的 set of <previous steps>, 来走下三步: k-1, k, or k+1 steps.
- 每次走一步, 查看 stone + step 是否存在; 如果存在, 就加进 next position: `stone+step`的 hash set 里面

##### 注意init
- `dp.put(stone, new HashSet<>())` mark 每个stone的存在
- `dp.get(0).add(0)` init condition, 用来做 dp.put(1, 1)

##### 思想
- 最终做下来思考模式, 更像是BFS的模式: starting from (0,0), add all possible ways 
- 然后again, try next stone with all possible future ways ... etc

```

/*
A frog is crossing a river. The river is divided into x units and at each unit 
there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, 
determine if the frog is able to cross the river by landing on the last stone. 
Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. 
Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
*/

// simplified with helper function
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        // Init: all stone slots has nothing on them
        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }
        dp.get(0).add(0); // such that dp.get(0) will move (dp, 0-stone, 1-step) on index 0
        for (int stone : stones) {
            for (int k : dp.get(stone)) {
                move(dp, stone, k);
                move(dp, stone, k + 1);
                move(dp, stone, k - 1);
            }
        }
        int lastStone = stones[stones.length - 1];
        return !dp.get(lastStone).isEmpty(); // able to reach
    }
    
    private void move(Map<Integer, Set<Integer>> dp, int stone, int step) {
        if (step > 0 && dp.containsKey(stone + step)) {
            dp.get(stone + step).add(step);
        }
    }
}

// original 
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;
        int n = stones.length;
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }
        dp.get(0).add(0);
        for (int stone : stones) {
            for (int k : dp.get(stone)) {
                if (k - 1 > 0 && dp.containsKey(stone + k - 1)) { // k - 1
                    dp.get(stone + k - 1).add(k - 1);
                }
                if (k > 0 && dp.containsKey(stone + k)) {// k
                    dp.get(stone + k).add(k);
                }
                if (k + 1 > 0 && dp.containsKey(stone + k + 1)) { // k + 1
                    dp.get(stone + k + 1).add(k + 1);
                }
            }
        }
        int lastStone = stones[n - 1];
        return !dp.get(lastStone).isEmpty(); // able to reach
    }
}
```