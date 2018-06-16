package io.github.ylingxiao.daily.util;

import java.io.*;
import java.util.ResourceBundle;

/**
 * 统计项目目录下java文件信息并写入特定文件
 * @Author ylingxiao
 */
public class ProjectCodeLinesCount {
    public static int whiteLine ;
    public static int noteLine ;
    public static int codeLine ;
    public static int allLine;
    private static ResourceBundle rb = ResourceBundle.getBundle("count") ;

    public static void main(String[] args){
        //获取项目目录
        File f = new File(rb.getString("countDirectory"));
        // 遍历文件统计信息
        searchFiles(f);
        // 将统计信息写入文件中
        File countFile = new File(rb.getString("countFile"));
        doWriter(countFile);
    }

    /**
     * 递归遍历文件
     * @param f
     */
    static void searchFiles(File f){
        if(f.isDirectory()){
            for (File item : f.listFiles()) {
                searchFiles(item);
            }
            return;
        }
        String patten = ".java";
        if(f.getName().endsWith(patten)){
            getCount(f);
        }
    }

    static void getCount(File file) {
        try(FileReader fd = new FileReader(file);
            BufferedReader bf = new BufferedReader(fd);){
            String line ;
            while((line = bf.readLine())!=null){
                allLine++;
                line = line.trim();
                if("".equals(line)){
                    whiteLine++;
                    continue;
                }
                if( line.startsWith("//") || line.startsWith("/*")|| line.endsWith("*/") || line.startsWith("*") ){
                    noteLine++;
                    continue;
                }
                codeLine++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件
     * @param f
     */
    static void doWriter(File f) {
        FileWriter fw ;
        try {
            // 如果文件不存在，创建文件，如果存在，校验重复
            if(f.exists()){
                f = reSet(f);
            }else{
                f.createNewFile();
            }
            fw = new FileWriter(f,true);
            fw.write("####----"+DateUtils.formatDate("yyMMdd")+"----<br/>\n");
            fw.flush();
            fw.write("空 &nbsp;&nbsp;行：" + whiteLine+"<br/>\n");
            fw.flush();
            fw.write("注释行：" + noteLine+"<br/>\n");
            fw.flush();
            fw.write("代码行：" + codeLine+"<br/>\n");
            fw.flush();
            fw.write("总行数：" + allLine +"<br/>\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对打印文字验重
     * @param f 要检验的文件
     * @return
     */
    private static File reSet(File f) {
        File copy = new File(Math.random()+".txt");
        try (FileReader fr = new FileReader(f);
             BufferedReader bf = new BufferedReader(fr);
             FileWriter fw = new FileWriter(copy) ) {
            String line ;
            while((line = bf.readLine()) != null){
                // 判断日期是否相同
                String patten = "####----" + DateUtils.formatDate("yyMMdd");
                if(line.startsWith(patten)){
                    break;
                }
                fw.write(line + "\n");
                fw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 删除源文件
        f.delete();
        // 重命名为源文件
        copy.renameTo(f);
        return f;
    }

}
