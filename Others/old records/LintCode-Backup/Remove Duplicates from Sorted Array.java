Remove Duplicate from Array 不同于remove from linked list.

LinkedList里面我们是最好不要动node.val的，直接把node去掉。
而array我们很难直接把node去掉，又不能用新array，那么就要：

把不重复的element一个个放到最前面。


这个思想跟merge two sorted array （其中一个后续非常长的array可以放下arr1,arr2） 类似。
就是找个不会事后mess up，不会去动得index,把满足条件的element 填进去。这样保证了in place.

* 有个反向思维：remove duplicate,实际上也是找unique elements, and insert into original array

```
/*31% Accepted
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].

Example
Tags Expand 
Array Two Pointers

Thinking Process:
Two pointers, i, j
i: the regular for loop
j - jumper:
If nums[i] == nums[j], do not update nums[j]. It stays the same.
after i++, compare nums[j] with the new nums[i]. If not the same, means the position after j can have a new number that’s not duplicate of nums[j]. In this case, we update nums[j] = nums[i].
Do this until regular i runs out.
At the end, j is actually the last index of new Array. j + 1 is the size.
*/

public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
}


```