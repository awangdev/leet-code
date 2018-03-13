import java.io.*;
import java.util.*;
/*
Used to generate table of contents.
    - No args: generate GitHub table
    - args == 'wordpress', generate WordPress table.
    - args == 'review', generate Review Page
    - args == 'all', genereate both table
*/
public class GenerateCodeTable {
    private static final String NOT_AVAILABLE = "N/A";
    /*
        TableRow, used to hold table object and sort by date.
    */
    private static class TableRow {
        private long date = 0;
        private String fileName;
        private String level;
        private String tutorialLink;
        private String note;
        private List<String> tags;

        public TableRow(
            long date,
            String fileName,
            String level,
            String tutorialLink,
            String note,
            List<String> tags) {
            this.date = date;
            this.fileName = fileName;
            this.level = level;
            this.tutorialLink = tutorialLink;
            this.note = note;
            this.tags = tags;
        }

        public long getDate() {
            return this.date;
        }

        public String getFileName() {
            return this.fileName;
        }

        public String getLevel() {
            return this.level;
        }

        public String getTutorialLink() {
            return this.tutorialLink;
        }

        public String getNote() {
            return this.note;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public String getTableComposedLine() {
            return "|[" + this.fileName + "](https://github.com/awangdev/LintCode/blob/master/Java/" + fileName.replace(" ", "%20")
                 + ")|" + this.level + "|Java|" + this.tags + "|"+ this.tutorialLink + "|\n";
        }
    }

    public final static String TUTORIAL_KEY_WORD = "tutorial:";
    public final static String TAGS_KEY_WORD = "tags:";
    public static void main(String[] args) {
        GenerateCodeTable gct = new GenerateCodeTable();   
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
            outputContent = gct.generateREADME(listOfFiles);
            gct.printTable("README.md", outputContent);
        } else if (args != null && args[0].contains("wordpress")) {//Wordpress
            outputContent = gct.generateWordPressPage(listOfFiles);
            gct.printTable("WordPress.txt", outputContent);
        } else if (args != null && args[0].contains("review")) {//Review Page
            outputContent = gct.generateReviewPage(listOfFiles);
            gct.printTable("ReviewPage.md", outputContent);
        } else if (args != null && args[0].contains("tags")) {//Wordpress
            outputContent = gct.generateTagREADME(listOfFiles);
            gct.printTable("TagsREADME.txt", outputContent);
        } else if (args != null && args[0].contains("all")) {
            outputContent = gct.generateREADME(listOfFiles);
            gct.printTable("README.md", outputContent);
            //outputContent = generateWordPressPage(listOfFiles);
            //printTable("WordPress.txt", outputContent);
            outputContent = gct.generateReviewPage(listOfFiles);
            gct.printTable("ReviewPage.md", outputContent);
            outputContent = gct.generateTagREADME(listOfFiles);
            gct.printTable("TagsREADME.md", outputContent);
            outputContent = gct.generateTagReviewPage(listOfFiles);
            gct.printTable("TagReviewPage.md", outputContent);
        }
    }   

    /*
        Output the content into file
    */
    public void printTable(String fileName, String outputContent) {
        //System.out.println(outputContent);
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
    public String generateWordPressPage(File[] listOfFiles) {
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
    public String generateREADME(File[] listOfFiles) {
        //Assemble output
        String outputContent = "# Java Algorithm Problems\n\n" + 
            "### 程序员的一天\n" +
            "从开始这个Github已经有将近两年时间, 很高兴这个repo可以帮到有需要的人. 我一直认为, 知识本身是无价的, 因此每逢闲暇, 我就会来维护这个repo, 给刷题的朋友们一些我的想法和见解. 下面来简单介绍一下这个repo:\n\n" + 
            "**README.md**: 所有所做过的题目\n\n" + 
            "**ReviewPage.md**: 所有题目的总结和归纳（不断完善中）\n\n" + 
            "**KnowledgeHash2.md**: 对所做过的知识点的一些笔记\n\n" + 
            "**SystemDesign.md**: 对系统设计的一些笔记\n\n" + 
            "**Future Milestone**: 我准备将一些有意思的题目，做成视频的形式给大家参考\n\n" + 
            //"**借此机会, 正式介绍一下自己, 以及我背后的大老板**\n\n" + 
            //"[![介绍一下自己！](https://img.youtube.com/vi/3keMZsV1I1U/0.jpg)](https://youtu.be/3keMZsV1I1U)\n\n" + 
            "希望大家学习顺利, 对未来充满希望(程序员也是找到好老板的!)\n" + 
            "有问题可以给我写邮件(wangdeve@gmail.com), 或者在GitHub上发issue给我.\n\n" +         
            "| Squence | Problem       | Level  | Language  | Tags | Video Tutorial|\n" + 
            "|:-------:|:--------------|:------:|:---------:|:----:|:-------------:|\n";
        final List<TableRow> tableRows = getTableRows(listOfFiles);
        for (int i = 0; i < tableRows.size(); i++) {
            outputContent += "|" + i + tableRows.get(i).getTableComposedLine();
        }
        return outputContent;
    }

    public String generateTagREADME(File[] listOfFiles) {
        String outputContent = "# Problems Sorted By Tag\n\n";
        String header = "| Squence | Problem       | Level  | Language  | Tags | Video Tutorial|\n" + 
                        "|:-------:|:--------------|:------:|:---------:|:----:|:-------------:|\n";
        final List<TableRow> tableRows = getTableRows(listOfFiles);
        final HashMap<String, List<TableRow>> tagToRows = new HashMap<>();
        tableRows.forEach(tableRow -> {
            for (String tag: tableRow.getTags()) {
                if (tag == null || tag.length() == 0) {
                    continue;
                }
                if (!tagToRows.containsKey(tag)) {
                    tagToRows.put(tag, new ArrayList<TableRow>());
                }
                tagToRows.get(tag).add(tableRow);
            }
        });
        // Build View
        for (Map.Entry<String, List<TableRow>> entry : tagToRows.entrySet()) {
            StringBuffer sb = new StringBuffer("## " + entry.getKey() + " (" + entry.getValue().size() + ")\n");
            sb.append(header);
            List<TableRow> entryTableRows = entry.getValue();
            for (int i = 0; i < entryTableRows.size(); i++) {
                sb.append("|" + i + entryTableRows.get(i).getTableComposedLine());
            }
            outputContent += sb.toString() + "\n\n\n";
        }
        return outputContent;
    }

    public String generateTagReviewPage(File[] listOfFiles) {
        String outputContent = "# Review Notes Sorted By Tag\n\n";
        final List<TableRow> tableRows = getTableRows(listOfFiles);
        final HashMap<String, List<TableRow>> tagToRows = new HashMap<>();
        // Group by tags:
        tableRows.forEach(tableRow -> {
            for (String tag: tableRow.getTags()) {
                if (tag == null || tag.length() == 0) {
                    continue;
                }
                if (!tagToRows.containsKey(tag)) {
                    tagToRows.put(tag, new ArrayList<TableRow>());
                }
                tagToRows.get(tag).add(tableRow);
            }
        });
        // Build View
        for (Map.Entry<String, List<TableRow>> entry : tagToRows.entrySet()) {
            StringBuffer sb = new StringBuffer("## " + entry.getKey() + " (" + entry.getValue().size() + ")\n");
            sb.append(buildReviewSection(entry.getValue()));
            outputContent += sb.toString() + "\n\n\n";
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
    public String generateReviewPage(File[] listOfFiles) {
        //Assemble output
        String outputContent = "# Review Page\n\n" + 
            "This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. \n" + 
            "New problems will be automatically updated once added.\n\n";
            
        final List<TableRow> tableRows = getTableRows(listOfFiles);
        int count = 0;
        int countMiss = 0;
        outputContent += buildReviewSection(tableRows);
        return outputContent;
    }

    private String buildReviewSection(List<TableRow> tableRows) {
        int count = 0;
        int countMiss = 0;
        StringBuffer sb = new StringBuffer();
        for (TableRow tableRow: tableRows) {
            // Skip non-ready items
            if (NOT_AVAILABLE.equals(tableRow.getLevel())) {
                System.out.println(countMiss + ". [File not yet formatted]: " + tableRow.getFileName() + " [Skipping. Please revisit]");
                countMiss++;
                continue;
            }
            //System.out.println(count +  ". " + tableRow.getLevel() + " [File not yet formatted]: " + tableRow.getFileName());
            sb.append("**" + count + ". [" + tableRow.getFileName());
            sb.append("](https://github.com/awangdev/LintCode/blob/master/Java/");
            sb.append(tableRow.getFileName().replace(" ", "%20") +")**");
            
            sb.append("      Level: " + tableRow.getLevel() + "\n");
            sb.append("      " + tableRow.getTutorialLink() + "\n");
            sb.append(tableRow.getNote() + "\n");
            sb.append("\n---\n");
            count++;
        }
        return sb.toString();
    }



    private List<TableRow> getTableRows(File[] listOfFiles) {
        final ArrayList<TableRow> tableRows = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.getName().contains(".java")) {
                tableRows.add(getTableRow(file.getName()));
            }
        }
        Collections.sort(tableRows, Comparator.comparing(TableRow::getDate));
        return tableRows;
    }

    private TableRow getTableRow(String fileName) {
        TableRow tableRow = null;
        String tutorialLink = "";
        String calculatedLevel = NOT_AVAILABLE;
        long timestamp = 0;
        List<String> tags = new ArrayList<>();
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(
                                          new FileInputStream("Java/" + fileName), "UTF-8"));
            // Get level
            String line = reader.readLine().trim();
            if (line.length() == 1 && !calculateLevel(line).isEmpty()){
                calculatedLevel = calculateLevel(line.toUpperCase());
                line = reader.readLine().trim();
            }

            // Get timestamp
            if (line.length() != 0) {
                try{
                    timestamp = Long.parseLong(line);
                    line = reader.readLine().trim();
                }catch(final Exception e){
                    System.out.println("Timestamp Not added yet: " + fileName);
                }
            }

            // Get tutorial
            if (line.contains(TUTORIAL_KEY_WORD)) {
                tutorialLink = "[Link](" + line.substring(TUTORIAL_KEY_WORD.length()) + ")";
                line = reader.readLine().trim();
            }

            // Get Tags
            if (line.contains(TAGS_KEY_WORD)) {
                // Do something
                String tagLine = line.substring(TAGS_KEY_WORD.length());
                for (String tag : tagLine.split(",")) {
                    tags.add(tag.trim());
                }
                Collections.sort(tags);
            }

            // Get Note
            String note = "";
            while ((line = reader.readLine()) != null && !line.equals("```") && !line.equals("/*")) {
                note += line + "\n";
            }

            // Get result
            tableRow = new TableRow(timestamp, fileName, calculatedLevel, tutorialLink, note, tags);
        } catch (final Exception e) {
            System.err.format("IOException: %s%n", e);
        }
        return tableRow;
    }

    private String calculateLevel(final String level) {
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
            case "R" : 
                return "Review";
        }
        return NOT_AVAILABLE;
    }
}