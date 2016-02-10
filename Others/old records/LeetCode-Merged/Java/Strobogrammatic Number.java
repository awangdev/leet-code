/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic.
Tags: Hash Table Math
Similar Problems: (M) Strobogrammatic Number II, (H) Strobogrammatic Number III
*/

/*
OPTS 11.04.2015
Thoughts:
Because the symmetric pairs are:
1-1, 8-8,0-0,6-9,9-6, we make a hashmap of it.
Create left/right pointer, where each compare has to match the pair in hashmap.

Note:
On map.containsKey() line, need to check (right), or whichever item that map is going to map.get(...) afterwards. 
If containsKey fails, return false; only when it passes through, then proceed to mpa.get()
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<Character,Character>();
        map.put('0','0');
        map.put('1','1');
        map.put('8','8');
        map.put('6','9');
        map.put('9','6');
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (!map.containsKey(num.charAt(right)) || num.charAt(left) != map.get(num.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/*
Thoughts:
Compare digits to the symmetric postion; special care for (6,9) pair, mark it after comparision.
Elimite the  cases before the for-loop run through(can do it in or as well, but that just make the code a bit complex)
Note:
Didn't use HashMap. I beleive hash map is used to mark the spot?
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
        	return true;
        }
        //Any non-strobogrammatic
        if (num.indexOf("2") >= 0 || num.indexOf("3") >= 0 ||
        	num.indexOf("4") >= 0 || num.indexOf("5") >= 0 ||
        	num.indexOf("7") >= 0) {
        	return false;
        }
        //If only 6 or 9 exist:
        if ((num.indexOf("6") >= 0 && num.indexOf("9") < 0) ||
        	(num.indexOf("9") >= 0 && num.indexOf("6") < 0)) {
        	return false;
        }
        //Check if (6,9) or other strobogrammatic # are appearing at symmetric position
    	char[] arr = num.toCharArray();
    	int leng = num.length();
    	for (int i = 0; i < leng; i++) {
    	    if (arr[i] == '6' || arr[i] == '9') {
        		if ((arr[i] == '6' && arr[leng - i - 1] != '9') ||
        			(arr[i] == '9' && arr[leng - i - 1] != '6')) {
        			return false;
        		}
    		    arr[i] = arr[leng - i - 1] = 'M';//marker
    		} else if (arr[i] != 'M' && arr[i] != arr[leng - i - 1]) {
    		    return false;
    		}
    	}
        return true;
    }
}