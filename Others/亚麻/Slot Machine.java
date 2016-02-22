M

检查字符串是否可以组成回文. 用hashmap

```
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
Summer has reached a casino in Las Vegas and she wanted to gamble in the slot machines, Casino manager has given her some chances to play (T) ,where T is the no of different slot machines; each one has it's own unique combination of letters and numbers blinking in the panel(N).

The condition of winning is to have a pallindromic sequence within those N characters provided she can press the randomization switch as many times she wants untill all the combinations with those letters are complete.

Help summer to identify in which of those slot machines she has a chance of winning.

Constraints:

1≤T≤10000

1≤N≤100 (characters)

Input Format

The first line contains an integer T (The no of slot machines).

The second line contains the string of characters(N) blinking on the slot machine.

The string inputed in the second line may cotain any numbers or letters of any length .

Note :

The string(N) may have repetitive letters or numbers in it. Capital letters and small letters are considered separate characters.

Output Format

if a slot machine has a possibility of winning then display yes, else display no

Sample Input

2

abac11c

dkvvfvd

Sample Output

yes

no

Explanation

Since the 1st string contains a permutation which is palindromic in nature 'ca1b1ac' therefore output is yes.

In the 2nd case there is no such permutation hence the output is no.

*/
//check pallidromic by counting chars in HashMap. O(n). If more than 1 single char, that is no.
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int line = Integer.parseInt(in.nextLine());
        while (in.hasNextLine()) {
            if (checkPalindrome(in.nextLine())) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
    
    public static boolean checkPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        int countSingle = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            countSingle += entry.getValue() % 2;
            if (countSingle > 1) {
                return false;
            }
        }
        return true;
    }
    
    
}
```