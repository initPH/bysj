package ph.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class common {

    public static boolean isExistZero(float[] floats ){
        for (int i=0;i<256;i++){
            if (floats[i]==0.0){
                System.out.println(i);
                return false;
            }
        }
            return  true;
    }

    //使用UUID生产唯一的Id
    public static String getUUID(){
        String s=UUID.randomUUID().toString();
        return s.replaceAll("-","");
    }

    //图片转成Base64
    public static String getImageStr(String imgFilePath){
        byte[] data=null;
        //读取图片字节数组
        try{
            InputStream in=new FileInputStream(imgFilePath);
            data=new byte[in.available()];
            in.read();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    //base64转成图片并输出
    public static boolean GenerateImage(String imgStr,String imgFilePath,String fileName){
        if(imgStr==null){
            return false;
        }
        // 去掉前面的data:image/png;base64,
        if (imgStr.indexOf("data:image/jpeg;base64,") != -1) {
            imgStr = imgStr.replace("data:image/jpeg;base64,", "");
        }else if (imgStr.indexOf("data:image/png;base64,") != -1){
            imgStr = imgStr.replace("data:image/png;base64,", "");
        }else if(imgStr.indexOf("data:image/jpg;base64,") != -1){
            imgStr = imgStr.replace("data:image/jpg;base64,", "");
        }

        BASE64Decoder decoder= new BASE64Decoder();
        try{
            byte[] bytes=decoder.decodeBuffer(imgStr);
            for (int i=0;i<bytes.length;i++){
                if(bytes[i]<0){
                    bytes[i]+=256;
                }
            }
            //String fileName=common.getCurrentDate().toString().replace("-","");
            //生成jpeg图片
            OutputStream out=new FileOutputStream(imgFilePath+"\\"+fileName);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //生成系统当前时间
    public static Timestamp getCurrentDate(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }

    //当前日期
    public static String  getNowDate(){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

   //判断是否为空
    public static boolean isEmpty(Object o){
        if(o==null){
            return true;
        }
        if(o instanceof Collection){
            return ((Collection<?>) o).isEmpty();
        }
        if(o instanceof Map){
            return ((Map<?,?>)o).isEmpty();
        }
        if(o.getClass().isArray()){
            return Array.getLength(o)==0;
        }
        if(o instanceof Iterator){
            return !((Iterator<?>) o).hasNext();
        }
        return false;
    }

    //重新计算人证对比分数
    public static int galculation(float rawScore,float passScore){
        if(rawScore-passScore>0){
            return (int) ((0.6+(rawScore-passScore)/(1-passScore)*0.4)*100);
        }else{
            return (int) ((0.6*(rawScore-passScore)/(0-passScore))*100);
        }
    }
}
