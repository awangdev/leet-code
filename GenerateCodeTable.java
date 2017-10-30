import java.io.*;
/*
Used to generate table of contents.
    - No args: generate GitHub table
    - args == 'wordpress', generate WordPress table.
    - args == 'review', generate Review Page
    - args == 'all', genereate both table
*/
public class GenerateCodeTable {
    public final static String TUTORIAL_KEY_WORD = "tutorial:";
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
            "<th align='center'>  Language</th>" + 
            "</tr>" +
            "</thead>" +
            "<tbody>";

        int count = 0;
        for (File file : listOfFiles) {
            if (file.getName().contains(".java")) {
                //outputContent += "|" + count + "|[" + file.getName() + "](https://github.com/awangdev/LintCode/blob/master/Java/"+ file.getName() +")| |" + "Java|\n";
                outputContent+= 
                "<tr>" + 
                    "<td align='center'>" + count + "</td>" +
                    "<td align='left'><a href='https://github.com/awangdev/LintCode/blob/master/Java/" + file.getName() + "'>" + file.getName() + "</a></td>" +
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
            "### 前戏\n" +
            "To host Java Solutions to algorithm problems from LintCode, LeetCode...etc.\n" + 
            "I Will try to revise the solutions once new problem or new testing case occurs.\n" + 
            "**Mid 2016** I realize that people may want to contribute to this repo, and make it better by contributing fixes, better solutions ... etc. Free free to send pull request. Once verified, I'm happy to merge in!\n" +
            "CALM DOWN AND CODE ON! Fellows! \n\n" +  
            "### News\n" + 
            "2017年1月17日, 陪我征战多年的 2014 MackBookPro i7 3.xGHz 被一杯清水结束了生命，在这里深切缅怀悼念。这个Git Repo是小M陪我一字一句打出来的，有过蹉跎，也有过辉煌，陪我从Day1刷题一直刷到了Day1之中。直至今日，小M记录的代码还在给广大coder带来福利。为了延续小M无私奉献的精神,我将重新在这个repo活跃起来，重整已有的问题，也会尝试总结一些System Design方面的想法，将小M还没有能够达成的梦想实现。\n\n" + 
            "| Squence | Problem       | Level  | Language  | Video Tutorial|\n" + 
            "|:-------:|:--------------|:------:|:---------:|:-------------:|\n";
        int count = 0;
        for (File file : listOfFiles) {
            String tutorialLink = "";
            String calculatedLevel = "";
            if (file.getName().contains(".java")) {
                try {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(
                                                  new FileInputStream("Java/" + file.getName()), "UTF-8"));
                    final String levelLine = reader.readLine().trim();
                    if (levelLine.length() == 1) {
                        calculatedLevel = calculateLevel(levelLine.toUpperCase());
                    }
                    final String tutorialLine = reader.readLine();
                    if (tutorialLine.indexOf(TUTORIAL_KEY_WORD) == 0) {
                        tutorialLink = "[Link](" + tutorialLine.substring(TUTORIAL_KEY_WORD.length()) + ")";
                    }
                } catch (Exception e) {
                    System.err.format("IOException: %s%n", e);
                }
                String convertedFileName = file.getName().replace(" ", "%20");
                outputContent += "|" + count + "|[" + file.getName() + "](https://github.com/awangdev/LintCode/blob/master/Java/"
                                + convertedFileName + ")|" + calculatedLevel + "|" + "Java|" + tutorialLink + "|\n";
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
                String convertedFileName = file.getName().replace(" ", "%20");
                outputContent += "**" + count + ". [" + file.getName() + "](https://github.com/awangdev/LintCode/blob/master/Java/"+ convertedFileName +")**";
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Java/" + file.getName()), "UTF-8"));
                    String line = null;
                    int countLine = 0;
                    while ((line = reader.readLine()) != null && !line.equals("```")) {
                        if (countLine == 0) {
                            final String trimedLine = line.trim().toUpperCase();
                            if (trimedLine.length() == 1 && !calculateLevel(trimedLine).isEmpty()) {
                                outputContent += "      Level: " + calculateLevel(trimedLine) + "\n";
                            } else {
                                outputContent += "\n";
                            }
                        } else if (countLine == 1 && line.indexOf(TUTORIAL_KEY_WORD) == 0) {
                            outputContent += "      [Tutorial Link](" + line.substring(TUTORIAL_KEY_WORD.length()) + ")\n";
                        } else {
                            outputContent += line + "\n";
                        }
                        countLine++;
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

    private static String calculateLevel(final String level) {
        switch(level) {
            case "N" : 
                return "Naive";
            case "E" : 
                return "Easy";
            case "M" : 
                return "Medium";
            case "H" : 
                return "Hard";
            case "S" : 
                return "Super";
        }
        return "";
    }
}