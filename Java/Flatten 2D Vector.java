M
tags: Design

Implement an iterator to flatten a 2d vector.

#### Basic Implementation using x, y corrdinate
- 就是把2D list里面的element全部遍历一遍。
- 跟一个nxn的matrix遍历，是没区别的拉; 所有来个x,y，把2d list跑一变。

#### Always return item at index 0, and remove from list?
- list 方便remove, 考虑吧reduce input vector (就像给的是linked list 一样)

```
/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.


Tags: Design
Similar Problems: (M) Binary Search Tree Iterator, (M) Zigzag Iterator, (M) Peeking Iterator

*/

/*
Thoughts:
As hint indicates: use 2 pointers to hold position.
Use hasNext to validate (x,y)  and move x.
Use next() to return (x,y) and move it(regardless of correctness, which is determined by hasNext())
*/
public class Vector2D {
	private int x;
	private int y;
	private List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null) {
        	return;
        }
        this.x = 0;
        this.y = 0;
        this.list = vec2d;
    }

    public int next() {
        int rst = list.get(x).get(y);
        if (y + 1 >= list.get(x).size()) {
        	y = 0;
        	x++;
        } else {
        	y++;
        }
        return rst;
    }

    public boolean hasNext() {
        if (list == null) {
            return false;
        }
        while (x < list.size() && list.get(x).size() == 0) {
        	x++;
        	y = 0;
        }
        if (x >= list.size()) {
        	return false;
        }
        if (y >= list.get(x).size()) {
        	return false;
        }
        return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
```