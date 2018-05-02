M
1525244424
tags: Array, Hash Table

给一串数字, unsorted, 找这串数字里面的连续元素序列长度 (consecutive序列, 是数字连续, 并不是说要按照原order)

#### HashSet
- 要想看连续元素, 必须要num++, num--这样搜索
- 1. 需要O(1)找到元素
- 2. 需要简单快速找到 num - 1, num + 1.
- 如果用min,max开array, 耗费空间
- 用HashSet来存, 用set.contains() 来查找 num - 1, num + 1 存在与否
- for loop. O(n) 
- 里面的while loop 一般不会有O(n); 一旦O(n), 也意味着set 清零, for loop也不会有更多 inner while 的衍生.
- overall O(n) 时间复杂度

```
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Hide Tags Array
*/

/*
Thoughts:
Find a way to:
1. directly check existance of a number
2. quickly gets to the num-1, num+1, if exist.

One way: find min,max of the number, and set up a long array. That will be waste of space, not applicable.

Instead: use a set and put all numbers in. 

Iterate all nums: if one num exist, find all of the num++, num-- and track the length
*/
public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int longest = 0;
        for (int num : nums) {
            int start = num;
            while (set.contains(start - 1)) {
                start--;
                set.remove(start);
            }
            
            int end = num;
            while (set.contains(end + 1)) {
                end++;
                set.remove(end);
            }
            longest = Math.max(longest, end - start + 1);
        }
        return longest;
    }
}

/**
Previous notes:
Thinking process:
0. This problem can be done using sorting, but time complexity of sorting is O(nlogn). This problem requires O(n).
1. Want to check if a number's left and right is consecutive to itself, but cannot do it due to the given unsorted array: think about a Hashmap.
2. HashMap(Key, Value) = (the number itself, boolean: have been counted or not). If you count a number as a consecutive, you only need to count it once.
3. How HashMap works: 
	when checking a number's consecutive, look at number--, number++, see if they are in the HashMap. If exist, means consecutive.
	If a number exist in the hashmap and its value is 'true', then we need to skip this number beacuse it has been checked.
4. Track the total number consecutives of 1 perticular number, compare it with the maxL. Save the Math.max to maxL.
5. Depending on the problem, we can store a consecutive sequence or simply just its length: maxL. This problem wants the maxL.
 */
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int maxL = 1;
        HashMap<Integer, Boolean> history = new HashMap<Integer, Boolean>();
        for (int i : num) {
            history.put(i, false);
        }
        for (int i : num) {
            if (history.get(i)) {
                continue;
            }
            //check ++ side
            int temp = i;
            int total = 1;
            while (history.containsKey(temp + 1)) {
                total++;
                temp++;
                history.put(temp, true);
            }
            //check -- side
            temp = i;
            while (history.containsKey(temp - 1)) {
                total++;
                temp--;
                history.put(temp, true);
            }
            maxL = Math.max(maxL, total);
        }
        return maxL;
    }
}



/*
10.19.2015
Thougths:
1. sort
2. use a 'count' and 'max' to keep track of consecutive elements
3. one-pass

Note:
Take care of equal numbers: skip/continue those

*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        if (num.length == 1) {
            return 1;
        }
        int count = 1;
        int max = 1;
        Arrays.sort(num);
        for (int i = 1; i < num.length; i++) {
            if (num[i - 1] == num[i]) {
                   continue;
            } else if (num[i - 1] + 1 == num[i]) {
                count++;
                max = Math.max(count, max);
            } else {
                count = 1;
            }
        }
        return max;
    }
}

```