package nf.factory.Util;
import java.io.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * //扫描注解
 * Created by Administrator on 2017/12/7.
 */
public class ScanFileUtil {

    //获取当前项目的绝对路径
    private static String path = null;

    //定义一个list集合，用于存放所有类的完整类名
    private static List<String> list = new ArrayList<String>();



    //默认路径
   public static  List scan() throws ClassNotFoundException {
       readFile(path);
       return list;
   }

    //指定包名路径
    public static List scan(String url) throws ClassNotFoundException {
        url = url.replace(".","/");
         url = urlutf8(url);
        readFile(url);
        return list;
    }
    /**
     * 读取clss文件信息
     * @param
     */
    private static void readFile(String paths) throws ClassNotFoundException {
        File f = new File(paths);
        File[] files = f.listFiles();
        if(files!=null){
            for (File file:files){
                //如果是文件
               if(file.isFile()){
                   String className = resolveClass(file.getAbsolutePath());
                    list.add(className);
               }else{
                   //如果是目录，那么就执行递归，继续遍历目录
                   readFile(file.getAbsolutePath());
               }
            }
        }
    }

    //处理路径
    public static String urlutf8(String pathurl){
        String url = null;
        try {
            url =Thread.currentThread().getContextClassLoader().getResource(pathurl).getPath();
            url = URLDecoder.decode(url,"utf-8");
            path =Thread.currentThread().getContextClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 文件路径解析
     */
    private static String resolveClass(String classPath){
        String className = classPath.substring(path.length()-1, classPath.length());
        className = className.replace(".class", "").replace("\\", ".");
        return className;

        /*String className=null;
        if(classPath!=null){
            className = classPath.substring(classPath.lastIndexOf("\\")+1);
            if(className==classPath){
                className = classPath.substring(classPath.lastIndexOf("/")+1);
            }
        }
        return className;*/
    }

    public static void main(String[] args) throws ClassNotFoundException {
        scan("nf");
       // readFile(path);
    }
}
