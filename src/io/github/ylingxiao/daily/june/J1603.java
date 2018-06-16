package io.github.ylingxiao.daily.june;

import java.io.File;
import java.util.ResourceBundle;

/**
 * 递归查询文件，并统计.java文件代码信息
 * @Author ylingxiao
 */
public class J1603 {
    private static ResourceBundle rb = ResourceBundle.getBundle("count") ;

    public static void main(String[] args){
        File f = new File(rb.getString("countDirectory"));
        getFiles(f);
        System.out.println("空行："+ J1602.whiteLine);
        System.out.println("注释行："+ J1602.notesLine);
        System.out.println("代码行："+ J1602.codeLine);
        System.out.println("共计："+ J1602.allLine);
        J1604.writer(rb.getString("fileUrl"), J1602.whiteLine, J1602.notesLine, J1602.codeLine, J1602.allLine);
    }
    /**
     * 得到所有文件递归
     * @param f 目录或文件
     */
    static void getFiles(File f){
        if(f.isDirectory()){
            for (File item : f.listFiles()) {
                getFiles(item);
            }
            return;
        }
        String patten = ".java";
        if(f.getName().endsWith(patten)){
            J1602.getCount(f);
        }
    }

}
