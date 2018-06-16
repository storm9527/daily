package io.github.ylingxiao.daily.june;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 写入指定文件并添加验重
 * @Author ylingxiao
 */
public class J1604 {

    public static void main(String[] args){
        writer("aaa.txt",1,2,3, 4);
    }

    public static void writer(String fileName ,int whiteLine, int noteLine, int codeLine, int allLine) {
        File f = new File(fileName);
        FileWriter fw ;
        try {
            if(!f.exists()){
                f.createNewFile();
            }else{
                f = reSet(f);
            }
            fw = new FileWriter(f,true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            fw.write("**"+sdf.format(new Date())+"**\n");
            fw.flush();
            fw.write("空  行：" + whiteLine+"\n");
            fw.flush();
            fw.write("注释行：" + noteLine+"\n");
            fw.flush();
            fw.write("代码行：" + codeLine+"\n");
            fw.flush();
            fw.write("总行数：" + allLine +"\n");
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
            while((line=bf.readLine()) != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
                if(line.startsWith("**"+ sdf.format(new Date()))){
                    break;
                }
                fw.write(line+"\n");
                fw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 删除源文件
        System.out.println(f.delete());
        // 重命名为源文件
        copy.renameTo(f);
        return f;
    }

}
