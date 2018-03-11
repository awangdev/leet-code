E
1516256263
tags: String

简单的character checking. 各个方向, 加加减减.

```

/*
Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false
*/

/*
Thoughts:
Each letter represent a movement:
U: {0, 1}
D: {0, -1}
R: {1, 0}
L: {-1, 0}
Just add them all together O(n). 

Or, even easier:
Add all of U && D; check if equal to 0.
Add all of R && L; check if equal to 0
*/

class Solution {
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() % 2 == 1) {
            return false;
        }
        int trackX = 0;
        int trackY = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'U' || moves.charAt(i) == 'D') {
                trackY += moves.charAt(i) == 'U' ? 1 : -1;
            }
            if (moves.charAt(i) == 'L' || moves.charAt(i) == 'R') {
                trackX += moves.charAt(i) == 'L' ? 1 : -1;
            }
        }
        return trackX == 0 && trackY == 0;
    }
}
```