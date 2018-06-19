package io.github.ylingxiao.daily.june;

import java.io.*;

/**
 * 通过位运算加密文件
 */
public class J1901 {
    public static void main(String[] args) throws IOException {
        // File f = new File("E:\\My Pictures\\文字");
        File f = new File("D:\\test");
        getFiles(f);
}

    /**
     * 获取所有文件
     */
    static void getFiles(File file){
        if(file.isDirectory()){
            for (File item: file.listFiles()) {
                getFiles(item);
            }
        }else{
            encryptionFile("D:\\test",file);
        }

    }

    /**
     * 单个文件，加密到指定目录下
     * @param path 目录
     * @param f 文件
     * @throws IOException
     */
    private static void encryptionFile(String path , File f){
        File encryptionFile = new File(path+"\\ylx_"+f.getName());
        try {
            encryptionFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(encryptionFile);){

            byte[] bys = new byte[1024];
            int result;
            while((result = fis.read(bys)) != -1){
                for (int i = 0; i < result; i++) {
                    bys[i] = (byte)(bys[i]^1);
                }
                fos.write(bys);
                fos.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
