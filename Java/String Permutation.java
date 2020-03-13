E

把#of occurrences 存进HashMap, 第一个string 做加法，第二个string做减法。最后看是否有不等于0的作判断。

```
public class Solution {
    /*
     * @param A: a string
     * @param B: a string
     * @return: a boolean
     */
    public boolean Permutation(String A, String B) {
        if (A == null || B == null || A.length() != B.length()) {
            return false;
        }
        final Map<Character, Integer> strMap = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            final char charA = A.charAt(i);
            final char charB = B.charAt(i);
            if (!strMap.containsKey(charA)) {
                strMap.put(charA, 0);
            }
            strMap.put(charA, strMap.get(charA) + 1);
            if (!strMap.containsKey(charB)) {
                strMap.put(charB, 0);
            }
            strMap.put(charB, strMap.get(charB) - 1);
        }
        for (Map.Entry<Character, Integer> entry : strMap.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}

```
