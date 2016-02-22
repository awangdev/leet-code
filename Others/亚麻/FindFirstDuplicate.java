/*
	给你一个字符串，找出其中第一个重复的单词。比如，He had had a book. 返回had。
	若真是这么简单, loop through str by ' '。每个单词都尝试在str里面找重复

	regular expression: "\\s+"
*/

public class Solution {

	public static String findFirstDuplicatedWord(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}

		String[] arr = str.split("\\s+");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			if (list.contains(arr[i])) {
				return arr[i];
			} 
			list.add(arr[i]);
		}

		return "";
	}

	public static void main(String[] args) {
		System.out.println("START");

		String str = "a is a sample program ok a";// is a a is

		String rst = findFirstDuplicatedWord(str);

		System.out.println(rst);
	}

}