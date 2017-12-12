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

    //项目路径 用于后面的解析完整类名使用
    private static String path = "";

    //定义一个list集合，用于存放所有类的完整类名
    private static List<String> list = new ArrayList<String>();


    //默认路径
   public static  List scan()   {
       //扫描文件的方法 传入处理过的path路径
       readFile(urlutf8(path));
       return list;
   }

    //指定包名路径
    public static List scan(String url) {
        url = url.replace(".","/");
        readFile( urlutf8(url));
        return list;
    }
    /**
     * 读取clss文件信息
     * @param
     */
    private static void readFile(String paths)  {
        System.out.println("处理后的路径？"+paths);
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
            //根据当前线程的执行类获取当前路径
            url =Thread.currentThread().getContextClassLoader().getResource(pathurl).getPath();
            //如果你的路径有中文，则需要进行中文处理
            url = URLDecoder.decode(url,"utf-8");
            //为全局变量赋值
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
        scan("nf.factory.entity");
       // readFile(path);
    }
}
