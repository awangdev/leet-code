E
1525415182
tags: Design

让一个class 是 singleton

```
/*
Singleton is a most widely used design pattern. 
If a class has and only has one instance at every moment, 
we call this design as singleton. 

For example, for class Mouse (not a animal mouse), we should design it in singleton.

You job is to implement a getInstance method for given class, 
return the same instance of this class every time you call this method.


Example
In Java:

A a = A.getInstance();
A b = A.getInstance();
a should equal to b.

Challenge
If we call getInstance concurrently, can you make sure your code could run correctly?

Tags Expand 
LintCode Copyright OO Design

*/

class Solution {
   public static Solution instance = null;
    public static Solution getInstance() {
        if (instance == null) {
            instance = new Solution();
        }
        return instance;
    }
};

// Thread safe:
class Solution {
	public static Solution solution = null;
    /**
     * @return: The same instance of this class every time
     */
    public static Solution getInstance() {
        if (solution == null) {
            synchronized (Solution.class) {
                 // Double check
                 if (solution == null) {
                     solution = new Solution();
                 }
             }
         }
         return solution;
    }
};

```