M

permutation的综合题：    
1. validate Input 是不是可以做palindromic permutation. 这个就是（Palindrome Permutation I）   
2. 顺便存一下permutation string的前半部分和中间的single character(if any)    
3. DFS 做unique permutation: given input有duplicate characters。       

```
/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
Hide Tags Backtracking
Hide Similar Problems (M) Next Permutation (M) Permutations II (E) Palindrome Permutation

*/


//Validate if can build palindromic, add half of the char, and record the odd char.
//Do permutation on first half
public class Solution {
    String halfStr = "";
    String oddStr = "";
    public List<String> generatePalindromes(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        if (!validate(s)) {
            return rst;
        }
        boolean[] visited = new boolean[halfStr.length()];
        permutateUnique(rst, "", visited, halfStr);
        
        for (int i = 0; i < rst.size(); i++) {
            String str = rst.get(i);
            StringBuffer sb = new StringBuffer(str);
            String reverse = sb.reverse().toString();
            rst.set(i, str + oddStr + reverse);
        }
        
        return rst;
    }
    
    //Validate and return palidrome candidate
    public boolean validate(String s) {
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        char[] arr = new char[s.length() / 2];
        int countOdd = 0;
        int ind = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 != 0) {
                countOdd++;
                oddStr += (char)i;
            }
            
            map[i] = map[i] / 2;
            while (map[i] > 0) {
                arr[ind] = (char)i;
                map[i]--;
                ind++;
            }
            
            if (countOdd > 1) {
                return false;
            }
        }
        
        Arrays.sort(arr);
        halfStr = new String(arr);
        return true;
    }
    
    //Permutation with duplicates control:
    public void permutateUnique(List<String> rst, String str, boolean[] visited, String s) {
        if (str.length() == s.length()) {
            rst.add(str);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && s.charAt(i - 1) == s.charAt(i))) {
                continue;
            }
            visited[i] = true;
            permutateUnique(rst, str + s.charAt(i), visited, s);
            visited[i] = false;
        }
    }
    
    
        /*
    //validation using StringBuffer
    public boolean validate(String s) {
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        StringBuffer sb = new StringBuffer();
        int countOdd = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 != 0) {
                countOdd++;
                oddStr += (char)i;
            }
            
            map[i] = map[i] / 2;
            while (map[i] > 0) {
                sb.append((char)i);
                map[i]--;
            }
            
            if (countOdd > 1) {
                return false;
            }
            
        }
        halfStr = sb.toString();
        char[] arr = halfStr.toCharArray();
        Arrays.sort(arr);
        
        halfStr = new String(arr);
        return true;
    }
    */
    
}
```