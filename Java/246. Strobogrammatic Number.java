E
tags: Hash Table, Two Pointers, Math, Enumeration
time: O(n)
space: O(1)

根据题意枚举, 再根据规则basic implementation

#### HashTable + Two Pointer
- compare left/right

#### Alter input
- flip number (6 and 9), and then reverse the string, see if the string is the same.
- takes more


```
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
*/
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return true;
        HashMap<Character, Character> map = buildMap();
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            char charL = num.charAt(left++), charR = num.charAt(right--);
            if (!map.containsKey(charR) || (charL != map.get(charR))) {
                return false;
            }
        }
        return true;
    }

    private HashMap<Character, Character> buildMap() {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0','0');
        map.put('1','1');
        map.put('8','8');
        map.put('6','9');
        map.put('9','6');
        return map;
    }
}

//flip number (6 and 9), and then reverse the string, see if the string is the same.
class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return true;
        if (num.length() == 1) return num.equals("0") || num.equals("1") || num.equals("8");
        List<Character> candidate = new ArrayList<>(Arrays.asList('0', '1', '8', '6', '9'));
        char[] charArray = num.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (!candidate.contains(charArray[i])) return false;
            if (charArray[i] == '6') charArray[i] = '9';
            else if (charArray[i] == '9') charArray[i] = '6';
        }
        StringBuffer sb = new StringBuffer(String.valueOf(charArray));
        String reversedNum = sb.reverse().toString();
        return num.equals(reversedNum);
    }
}
```
