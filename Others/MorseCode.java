想法:
1. 只关注正数，找一个分割点，开始查看能不能有valid的解。
2. 过程中，把正数可能有的所有正数的解都找出来，可能存为一个ArrayList<String> letters in morse format:
String 是 "...    ---      ..." 每个字母之间有空格分开。
这里可能生成所有possibilities。 其中有很多possibility在结合了负数后，可能就会不成立。

3. loop through arraylist: letters
根据每个字母，以及每个字母后面的停顿，来尝试跟所有的负数，分段比较。
比如第一个字母：[. -x . -y . -z]        z 肯定比x 和y都大； 而SOS后面的空出来的负数时间，应该比这个词里面的所有负数绝对值都大。

这样就把ArrayList Letters 里面的很多可能性都灭掉。出一个或者多个结果~



当然：这里负数只做了最终判断的作用，而一开始正数出的结果可能会非常耗时。 
而且假设了分段判断的规律，而不假设全局的规律。（好像更切合实际）。


```
//Trie
  
  
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


/*
Double[] = {0.15, -0.12, 0.16, -0.1, 0.17, -0.3}
pos: . - time
neg: empty time

        morseCodeDict["A"] = ".-"
        morseCodeDict["B"] = "-..."
        morseCodeDict["C"] = "-.-."
        morseCodeDict["D"] = "-.."
        morseCodeDict["E"] = "."
        morseCodeDict["F"] = "..-."
        morseCodeDict["G"] = "--."
        morseCodeDict["H"] = "...."
        morseCodeDict["I"] = ".."
        morseCodeDict["J"] = ".---"
        morseCodeDict["K"] = "-.-"
        morseCodeDict["L"] = ".-.."
        morseCodeDict["M"] = "--"
        morseCodeDict["N"] = "-."
        morseCodeDict["O"] = "---"
        morseCodeDict["P"] = ".--."
        morseCodeDict["Q"] = "--.-"
        morseCodeDict["R"] = ".-."
        morseCodeDict["S"] = "..."
        morseCodeDict["T"] = "-"
        morseCodeDict["U"] = "..-"
        morseCodeDict["V"] = "...-"
        morseCodeDict["W"] = ".--"
        morseCodeDict["X"] = "-..-"
        morseCodeDict["Y"] = "-.--"
        morseCodeDict["Z"] = "--.."
        
        morseCodeDict["1"] = "-----"
        morseCodeDict["2"] = ".----"
        morseCodeDict["3"] = "..---"
        morseCodeDict["4"] = "...--"
        morseCodeDict["5"] = "....-"
        morseCodeDict["6"] = "....."
        morseCodeDict["7"] = "-...."
        morseCodeDict["8"] = "--..."
        morseCodeDict["9"] = "---.."
        morseCodeDict["0"] = "----."

Example:
[0.0450931929901705, -0.0901581814005595, 0.0678896922281069, -0.121032138856854, 0.152995205350165, -0.276777733213167, 0.275236261113044, -0.0919186682910514, 0.347645293572589, -0.125459771265131, 0.342321600880377, -0.363701690087054, 0.102393076997638, -0.117040938252157, 0.114223775510942, -0.0895479217582997, 0.0973858877985071] = SOS ...---...

Thoughts:
Pos: 
Two sets:  dot or dash.
Negative:
Three sets: time between morse code, time between letter, time between words.

Pick a pos time for '.', pick a negtive time for time between morse code, then start DFS.

For initial judgement:
Pos:
Sort all postive number, let the smallest be '.' and the rest to be '-'. try it out. If not working, move the threshold.

Negative:
Sort by absolute value.
Assume all letters are the shortest one morse code. So the set for 'time between word' will have most of number.
    The set for 'time between letter' will be empty
    The set for 'time between morse code' will be empty
If not working, move number around the tree sets, and do dfs.

The possible solution is to try all posibility with dfs, and cut it off when it seems not valid.



Thought2:
Ignore negative for now. Try to use same method for positive number to try out all posibilities with postiive numbers.
Use the results to match negative numbers, see if the time pulse is valid. 
If match, return all posibilities.

*/
class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");

    for (String string : strings) {
      System.out.println(string);
    }
  }
}

```