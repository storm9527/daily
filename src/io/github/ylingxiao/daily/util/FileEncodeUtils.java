package io.github.ylingxiao.daily.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 读取配置文件中的目录，对其目录下的文件加密及解密，删除源文件
 */
public class FileEncodeUtils {
    /**
     * 存储查询出的所有文件
     */
    private static List<File> files = new ArrayList<File>();
    /**
     * 文件加密目录配置
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("file") ;

    public  static void main (String[] args){
       File file = new File(rb.getString("encodeDirectory"));
       byte b = Byte.valueOf(rb.getString("code"));
        //获取目录下的所有文件
        getFile(file);
        //  遍历list集合，对每个文件进行加密或反加密
        for (File item: files) {
            // 加密文件
            encode(item, b);
            // 删除文件
            item.delete();
        }
    }

    /**
     * 加密文件
     *  判断文件中如果以“ylx_”开头，加密后去除该标志，否则加上该标志
     * @param item
     */
    private static void encode(File item, byte b) {
        String filePath = item.getParent();
        String fileName = item.getName();
        String newName = "";
        if(fileName.startsWith("ylx_")){
            newName = fileName.replace("ylx_","");
        }else{
            newName = "ylx_"+fileName;
        }
        File copy = new File(filePath,newName);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            copy.createNewFile();
            fis = new FileInputStream(item);
            fos = new FileOutputStream(copy);
            byte[] bys = new byte[1024];
            int result;
            while((result = fis.read(bys)) != -1){
                for (int i = 0; i < result; i++) {
                    bys[i] = (byte)(bys[i]^b);
                }
                fos.write(bys);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取目录下的文件，将其放入lits集合中
     * @param file
     */
    public static void getFile(File file){
        if (file.isDirectory()){
            for (File item : file.listFiles()) {
                getFile(item);
            }
        }else{
            files.add(file);
        }
    }


}
