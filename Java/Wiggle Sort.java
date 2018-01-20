M
1516434841

方法1:
排序, nLog(n). 然后把直线上坡变成层叠山峰, 需要每隔几个(题目中是每隔2位)就做个swap 造成高低不平.
Note: 每隔山峰之间是相互没有关系的, 所以每次只要操心 [i], [i-1]两个位置就好了.

方法2:
O(n)
看好奇数偶数位的规律, 然后根据题目给出的规律, 跑一遍, 每次只关注两个位置: 把不合适的[i], [i-1]调换位置就好了.

方法3:
跟法2一样, 只是更巧妙一点罢了:
第一遍想太多. 其实做一个fall-through就能把问题解决，原因是因为：
这样的fall-through每次在乎两个element，可以一口气搞定，无关乎再之前的elements。
特别的一点：flag来巧妙的掌控山峰和低谷的变化。又是神奇的一幕啊！
这样子的奇观，见过就要知道了，没见过的时候有点摸不着头脑。

```
/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

Tags: Array Sort
Similar Problems: (M) Sort Colors

*/

/*
Thoughts:
Sort the orignal array.
Every 2 spots there is a peek, switch [i], [i-1] every 2 spots.
nLog(n).
draw a ascending line with dots at positions: 0, 1, 2, 3, 4 ... etc.
To make the dots into mountain, some dots needs to swap. According to the question, we can swap every 2 dots.
*/
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i+=2) {
            swap(nums, i, i - 1);
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

/*
Thoughts:
O(n)
Odd indexs are taking the peek, and even indexes are taking the bottom.
However, values that are two indexes apart does not need to follow any sequence rules.
That is, at every index, we only have to make sure [i] and [i-1] follows the rules provided.
Once we make one pass through the array, it should be swapped completely.

same as the 'flag' solution below.
*/
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0 && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
            }
            if (i % 2 == 1 && nums[i] < nums[i - 1]) {
                swap(nums, i, i - 1);
            }
            /*
            // Of course, in one line:
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
            */
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

/*
O(n)
Attemp2, Thoughts: (http://www.cnblogs.com/easonliu/p/4798814.html)
Draw wiggle on original array. Whenever any postion i is not working with i-1, swap them.
Cases:
1. when nums[i] is supposed to >= nums[i - 1], however it's nums[i] < nums[i - 1], swap them. For example (nums[1] vs. nums[0])
2. when nums[i] is supposed to <= nums[i - 1], however it's nums[i] > nums[i - 1], swap them. For example (nums[2] vs. nums[1])
Specially, for case 2: can times a -1 on both side, to make nums[i] * -1 < nums[i - 1] * -1. Therefore we only need 1 if statement.

Concept: whenver something does not work, fix it. (especailly now we are only taking caer of 2 elements at a time, so we can do it as a fall-through)
*/
public class Solution {
    public void wiggleSort(int[] nums) {
    	if (nums == null || nums.length <= 1) {
    		return;
    	}
    	int flag = 1;
    	for (int i = 1; i < nums.length; i++) {
    		if (flag * nums[i] < flag * nums[i - 1]) {
    			swap(nums, i, i - 1);
    		}
    		flag = -1 * flag;
    	}
    }

	public void swap(int[] nums, int x, int y) {
    	int temp = nums[x];
    	nums[x] = nums[y];
    	nums[y] = temp;
    }
}


/*
Attempt1:
Wiggle sort, ofcourse can't do Arrays.sort()! Should sort in one pass.
Also, we don't need to make each wiggle elements in ascending order (1,(4),2,(5),3,(6) ... etc). I thought it over.
----
List out the example of 1,2,3,4,5,6,7,8, want to move (5,6,7,8) and insert between (1,2,3,4). It follows the follows patter:
Assume total length = n, and we are moving index i = (1 ~ n)
Step1: swap (n + i)/2, and i, (where initially i == 1)
Step2: swap (n + i)/2, and i+1
Step2: i+=2;


public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return;
        }
        Arrays.sort(nums);
        if (nums.length <= 2) {
        	return;
        }
        int leng = nums.length;
        int ind1 = 0;
        int ind2 = 0
        for (int i = 1; i < leng; i++) {
        	//Step1
        	ind1 = (leng + i) / 2;
        	ind2 = i;
        	swap(ind1, ind2);
        	//Step2:
        	ind2 = i + 1;
        	swap(ind1, ind2);
        }
    }

    public void swap(int[] nums, int x, int y) {
    	int temp = nums[x];
    	nums[x] = nums[y];
    	nums[y] = temp;
    }
}
*/

```