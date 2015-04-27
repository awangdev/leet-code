import java.io.*;

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
		System.out.println(outputContent);
		//Write to README.md
		try {		
			File outFile = new File("README.md");
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