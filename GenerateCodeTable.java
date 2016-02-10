import java.io.*;
/*
Used to generate table of contents.
	- No args: generate GitHub table
	- args == 'wordpress', generate WordPress table.
	- args == 'review', generate Review Page
	- args == 'all', genereate both table
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
		} else if (args != null && args[0].contains("wordpress")) {//Wordpress
			outputContent = generateWordPressPage(listOfFiles);
			printTable("WordPress.txt", outputContent);
		} else if (args != null && args[0].contains("review")) {//Review Page
			outputContent = generateReviewPage(listOfFiles);
			printTable("ReviewPage.md", outputContent);
		} else if (args != null && args[0].contains("all")) {
			outputContent = generateREADME(listOfFiles);
			printTable("README.md", outputContent);
			outputContent = generateWordPressPage(listOfFiles);
			printTable("WordPress.txt", outputContent);
			outputContent = generateReviewPage(listOfFiles);
			printTable("ReviewPage.md", outputContent);
		} else {
			return;
		}


	}	

	/*
		Output the content into file
	*/
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

	/*
		Generate Wordpress contents
	*/
	public static String generateWordPressPage(File[] listOfFiles) {
		//Assemble output
		String outputContent = "Java Solutions to algorithm problems from LintCode, LeetCode...etc.\n" +
		"<table>" +
			"<thead>" + 
			"<tr>" + 
			"<th align='center'>#</th>" + 
			"<th align='left'>Problem</th>" + 
			"<th align='left'>      Level</th>" + 
			"<th align='center'>  Language</th>" + 
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
		Generate GitHub ReadMe contents
	*/
	public static String generateREADME(File[] listOfFiles) {
		//Assemble output
		String outputContent = "# Java Algorithm Problems\n\n" + 
			"To host Java Solutions to algorithm problems from LintCode, LeetCode...etc.\n" + 
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


	/*
		Generate Review Page contents
		Review Page content:
		1. Sequence
		2. Name
		3. Difficulty
		4. Summary of solution, key points.
	*/
	public static String generateReviewPage(File[] listOfFiles) {
		//Assemble output
		String outputContent = "# Review Page\n\n" + 
			"This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. \n" + 
			"New problems will be automatically updated once added.\n\n";
			
		int count = 0;
		for (File file : listOfFiles) {
			if (file.getName().contains(".java")) {
				outputContent += "**" + count + ". [" + file.getName() + "](https://github.com/shawnfan/LintCode/blob/master/Java/"+ file.getName() +")**";
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Java/" + file.getName()), "UTF-8"));
					String line = null;
					int countLine = 0;
					while ((line = reader.readLine()) != null && !line.equals("```")) {
						if (line.equals("")) {
						//	continue;
						}
						if (countLine == 0) {
							String level = line.substring(0).toUpperCase();
							switch(level) {
								case "N" : 
									outputContent += "		Level: Naive\n";
									break;
								case "E" : 
									outputContent += "		Level: Easy\n";
								break;
								case "M" : 
									outputContent += "		Level: Medium\n";
								break;
								case "H" : 
									outputContent += "		Level: Hard\n";
								break;
								case "S" : 
									outputContent += "		Level: Super\n";
								break;
							}
						} else {
							outputContent += line + "\n";
						}
						countLine++;

						//System.out.println(line);
						//outputContent += line;
					}
				} catch (Exception e) {
					System.err.format("IOException: %s%n", e);
				}//end of one file
				outputContent += "\n---\n";
				count++;			
			}
		}	
		return outputContent;
	}


	

}