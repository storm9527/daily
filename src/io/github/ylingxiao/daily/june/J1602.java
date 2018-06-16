package io.github.ylingxiao.daily.june;

import java.io.*;

/**
 * 获取.java文件的空行，注释行和代码行
 *  @Author ylingxiao
 */
public class J1602 {
    public static int whiteLine ;
    public static int notesLine ;
    public static int codeLine ;
    public static int allLine;

    public static void main (String[] args){
        getCount(new File("D:\\WorkSpace\\idea_workspace\\daily\\src\\io\\github\\ylingxiao\\daily\\june\\J1601.java"));
        System.out.println("空行："+whiteLine);
        System.out.println("注释行："+notesLine);
        System.out.println("代码行："+codeLine);
        System.out.println("共计："+allLine);
    }

    public static void getCount(File file) {
        BufferedReader bf ;
        FileReader fd ;
        try{
            fd = new FileReader(file);
            bf = new BufferedReader(fd);
            String line ;
            while((line=bf.readLine())!=null){
                allLine++;
                line = line.trim();
                System.out.println(line);
                if("".equals(line)){
                    whiteLine++;
                    continue;
                }
                if( line.startsWith("//") || line.startsWith("/*")|| line.endsWith("*/") || line.startsWith("*") ){
                    notesLine++;
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
}
