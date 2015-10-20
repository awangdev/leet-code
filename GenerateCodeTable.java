import java.io.*;
/*
Used to generate table of contents.
1. No args: generate GitHub table
2. args == 'word', generate WordPress table.
3. args == 'all', genereate both table
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
			printTable("README.md", outputContent);
		} else if (args != null && args[0].contains("word")) {//Wordpress
			outputContent = generateWordPressPage(listOfFiles);
			printTable("WordPress.txt", outputContent);
		} else if (args != null && args[0].contains("all")) {
			outputContent = generateREADME(listOfFiles);
			printTable("README.md", outputContent);
			outputContent = generateWordPressPage(listOfFiles);
			printTable("WordPress.txt", outputContent);
		} else {
			return;
		}


	}	

	public static String generateWordPressPage(File[] listOfFiles) {
		//Assemble output
		String outputContent = "Java Solutions to problems from LintCode(<a href='http://lintcode.com/'>http://LintCode.com</a>).\n" +
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
					"<td align='left'><a href='https://github.com/shawnfan/LintCode/blob/master/Java/"+ file.getName() + "'>" + file.getName() + "</a></td>" +
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
		String outputContent = "# LintCode\n\n" + 
			"To host Java Solutions to problems from LintCode(http://LintCode.com).\n" + 
			"I Will try to revise the solutions once new problem or new testing case occurs.\n\n" + 
			"| Squence | Problem       | Level			| Language  |\n" + 
			"|:-------:|:--------------|:---------------|:---------:|\n";
		int count = 0;
		for (File file : listOfFiles) {
			if (file.getName().contains(".java")) {
				outputContent += "|" + count + "|[" + file.getName() + "](https://github.com/shawnfan/LintCode/blob/master/Java/"+ file.getName() +")| |" + "Java|\n";
				count++;			
			}
		}	
		return outputContent;
	}

	public static void printTable(String fileName, String outputContent) {
		System.out.println(outputContent);
		//Write to README.md
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