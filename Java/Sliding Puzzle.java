H
1534211551
tags: BFS

```
/*
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, 
and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the 
state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

*/

/*
1. convert input
2. create node (x, y, nums)
3. start bfs, track step; enumerate 4 directions 
4. swap(nums, x, y, newX, newY), evaluate(nums)
*/
class Solution {
    class Node {
        int x, y;
        int[] nums;
        public Node(int x, int y, int[] nums) {
            this.x = x;
            this.y = y;
            this.nums = nums;
        }
    }
    int[] dx = {1, - 1, 0, 0};
    int[] dy = {0, 0, 1, - 1};
    
    public int slidingPuzzle(int[][] board) {
        // Convert
        int x = 0, y = 0;
        int[] nums = new int[6];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                nums[i * 3 + j] = board[i][j];
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        // Init and process queue
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, nums));
        
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (evaluate(node.nums)) return count;
                for (int j = 0; j < 4; j++) {
                    int mX = node.x + dx[j];
                    int mY = node.y + dy[j];
                    if (mX >= 0 && mX < 2 && mY >= 0 && mY < 3) {
                        int[] newBoard = (int[]) node.nums.clone();
                        swap(newBoard, node.x, node.y, mX, mY);
                        String serializedBoard = boardToString(newBoard);
                        if (!visited.contains(serializedBoard)) {
                            visited.add(serializedBoard);
                            queue.offer(new Node(mX, mY, newBoard));
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }
    
    private void swap(int[] nums, int x, int y, int mX, int mY) {
        int oldIndex = x * 3 + y;
        int newIndex = mX * 3 + mY;
        int temp = nums[oldIndex];
        nums[oldIndex] = nums[newIndex];
        nums[newIndex] = temp;
    }
    
    private boolean evaluate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                if (i == 5 && nums[i] == 0) continue;
                return false;
            }
        }
        return true;
    }
    
    private String boardToString(int[] nums) {
        StringBuffer sb = new StringBuffer();
        for (int num : nums) {
            sb.append(num + "#");
        }
        return sb.toString();
    }
}
```