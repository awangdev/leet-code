/*
Singleton is a most widely used design pattern. If a class has and only has one instance at every moment, we call this design as singleton. For example, for class Mouse (not a animal mouse), we should design it in singleton.

You job is to implement a getInstance method for given class, return the same instance of this class every time you call this method.


Example
In Java:

A a = A.getInstance();
A b = A.getInstance();
a should equal to b.

Challenge
If we call getInstance concurrently, can you make sure your code could run correctly?

Tags Expand 
LintCode Copyright OO Design

Thoughts:
...
Was not clear to me. Need to loop up more on synchronized/volatile
Good reference:
http://www.cnblogs.com/EdwardLiu/p/4443230.html

*/


class Solution {
	public static volatile Solution solution = null;
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
