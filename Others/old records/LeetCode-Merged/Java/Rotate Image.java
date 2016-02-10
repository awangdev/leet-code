M

```
/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

Tags:
Array
*/
/*
Thoughts:
Method1: 1. flip the upper half UP/DOWN. 2. Flip the diagal where j >= i + 1
In place
*/

public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        //Flip UP/DOWN
        for (int i = 0; i < n / 2; i++){
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];//(n-1) is end index, then, matrix[(n-1) - i] means the Symmetry element opposed item matrix[i]
                matrix[n - 1 - i][j] = temp;
            }
        }
        //Flip '\' diagnal.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {//Skip j = i, which is the diagnal. It's not necessary, so we do j = i + 1
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

/*
Method2: Based on method1, we can figure out: if the current position is (i,j), what's the four corresponding positoins?
Then, we just do a overall swap.

Note: We only do this on the one of the corner in the matrix. Let's do top-left corner.

About the 4 swapping: 
Already figuired out matrix[n-1-i] in method1. 
X0 W2 W1 D
X1      Z2
X2      Z1
B Y1 Y2 C
1st swap: Use[i] to map [j] is because:
    When matching B to X0, and Y1 to X1, and Y2 to X2,    X's i is equal to Y' j. We want Y's j increase with X's i increasing. So, map matrix[i][] = matrix[][i]
2nd swap: 
    Z's i decrease as X's i incease. Same for Z's j
3rd swap:
    W's i stable as X's j. However, W's j decrease as X's j increase.
4th swap:

*/
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++){
            for (int j = 0; j < Math.ceil(n / 2.0); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}

```