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
            "### 程序员的一天\n" +
            "从开始这个Github已经有将近两年时间, 很高兴这个repo可以帮到有需要的人. 我一直认为, 知识本身是无价的, 因此每逢闲暇, 我就会来维护这个repo, 给刷题的朋友们一些我的想法和见解. 下面来简单介绍一下这个repo:\n" + 
            "**README.md**: 所有所做过的题目\n" + 
            "**ReviewPage.md**: 所有题目的总结和归纳（不断完善中）\n" + 
            "**KnowledgeHash2.md**: 对所做过的知识点的一些笔记\n" + 
            "**SystemDesign.md**: 对系统设计的一些笔记\n" + 
            "**Future Milestone**: 我准备将一些有意思的题目，做成视频的形式给大家参考\n" + 
            "**借此机会, 正式介绍一下自己, 以及我背后的大老板**\n" + 
            "[![介绍一下自己！](https://img.youtube.com/vi/3keMZsV1I1U/0.jpg)](https://youtu.be/3keMZsV1I1U)\n" + 
            "希望大家学习顺利, 对未来充满希望(程序员也是找到好老板的!)\n" + 
            "有问题可以给我写邮件(wangdeve@gmail.com), 或者在GitHub上发issue给我.\n\n" +         
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