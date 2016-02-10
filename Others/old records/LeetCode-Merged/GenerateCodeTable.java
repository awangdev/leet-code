import java.io.*;
/*
Used to generate table of contents.
1. No args: generate both tables
2. args == 'word', generate WordPress table.
3. args == 'git', genereate GitHub table.
*/
public class GenerateCodeTable {
	public static void main(String[] args) {	
		//Read Java Solution Folder
		File folder = new File("./Java");//"." = current path
		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println("Check Directory:1");
			return;
		}
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null) {
			System.out.println("Check Directory:2");
			return;
		}

		String outputContent = "";
		File outFile;
		
		if (args.length == 0){
			outputContent = generateREADME(listOfFiles);
			printPage("README.md", outputContent);
			outputContent = generateWordPressPage(listOfFiles);
			printPage("WordPress.txt", outputContent);
		} else if (args != null && args[0].contains("word")) {//Wordpress
			outputContent = generateWordPressPage(listOfFiles);
			printPage("WordPress.txt", outputContent);
		} else if (args != null && args[0].contains("git")) {
			outputContent = generateREADME(listOfFiles);
			printPage("README.md", outputContent);
		} else {
			return;
		}


	}	
	/*
		Generate Wordpress Table
	*/
	public static String generateWordPressPage(File[] listOfFiles) {
		//Assemble output
		String outputContent = "Java Solutions to problems from LeetCode(<a href='https://leetcode.com/problemset/algorithms/'>https://leetcode.com/problemset/algorithms/</a>).\n" +
		"<table>" +
			"<thead>" + 
			"<tr>" + 
			"<th align='center'>#</th>" + 
			"<th align='left'>Problem</th>" + 
			"<th align='left'>      Level</th>" + 
			"<th align='center'>  Language</th>" + 
			"</tr>" +
			"</thead>" +
			"<tbody>";

		int count = 0;
		for (File file : listOfFiles) {
			if (file.getName().contains(".java")) {
				//outputContent += "|" + count + "|[" + file.getName() + "](https://github.com/shawnfan/LintCode/blob/master/Java/"+ file.getName() +")| |" + "Java|\n";
				outputContent+= 
				"<tr>" + 
					"<td align='center'>" + count + "</td>" +
					"<td align='left'><a href='https://github.com/shawnfan/LeetCode/blob/master/Java/"+ file.getName() + "'>" + file.getName() + "</a></td>" +
					"<td align='left'></td>" +
					"<td align='center'>Java</td>" +
				"</tr>";
				count++;			
			}
		}	

		outputContent += "</tbody></table>";
		return outputContent;
	}


	/*
		Generate GitHub ReadMe file
	*/
	public static String generateREADME(File[] listOfFiles) {
		//Assemble output
		String outputContent = "# LeetCode\n\n" + 
			"To host Java Solutions to problems from LeetCode(https://leetcode.com/problemset/algorithms/).\n" + 
			"I Will try to revise the solutions once new problem or new testing case occurs.\n" + 
			"Since I do not run .java files, they are formatted with markdowns to help compressing code in blog format.\n\n"+
			"| Squence | Problem       | Level			| Language  |\n" + 
			"|:-------:|:--------------|:---------------|:---------:|\n";
		int count = 0;
		for (File file : listOfFiles) {
			if (file.getName().contains(".java")) {
				outputContent += "|" + count + "|[" + file.getName() + "](https://github.com/shawnfan/LeetCode/blob/master/Java/"+ file.getName() +")| |" + "Java|\n";
				count++;			
			}
		}	
		return outputContent;
	}
	/*
		Generate a combined post of all files, with proper markdown
	*/
	public static String generateCombinedPost() {
		return "";
	}
	/*
		Write the outputContent to specific file
	*/
	public static void printPage(String fileName, String outputContent) {
		System.out.println(outputContent);
		try {	
			File outFile = new File(fileName);
			FileOutputStream fop = new FileOutputStream(outFile);
			byte[] contentInBytes = outputContent.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("Mission Accomplished. Now go ahead and commit");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}