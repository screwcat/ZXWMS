package com.zx.sframe.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.zx.platform.syscontext.PlatformGlobalVar;

public class GlobalFileUtil
{
    /**
     * 获取文件扩展名
     * 
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename)
    {
        if ((filename != null) && (filename.length() > 0))
        {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1)))
            {
                return filename.toLowerCase().substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * @author Allen
     * @param 1、上传的文件流，2、当前用户的ID，3、上传文件名
     * @return 返回保存的文件路径
     */
    public static String saveUploadFile(InputStream fileStream, String userId, String srcFileName) throws IOException
    {

        String catalog = DateUtil.date2String(new Date(), "yyyyMM");
        String postfix = "";
        if (srcFileName.lastIndexOf(".") > -1)
        {
            postfix = srcFileName.substring(srcFileName.lastIndexOf(".")).toLowerCase();
        }
        String fileName = userId + "_" + System.currentTimeMillis();
        /*
         * String fileName = srcFileName;
         * if(srcFileName.lastIndexOf(System.getProperty("file.separator")) >
         * -1){ fileName =
         * fileName.substring(fileName.lastIndexOf(System.getProperty
         * ("file.separator"))+1); } String postfix = ""; if
         * (fileName.lastIndexOf(".") > -1) { postfix =
         * fileName.substring(fileName.lastIndexOf(".")).toLowerCase(); fileName
         * = fileName.substring(0,fileName.lastIndexOf(".")); } String now =
         * ""+System.currentTimeMillis(); fileName = fileName+ "_" +
         * now.substring(now.length()-6); fileName = fileName.replaceAll(">",
         * "＞").replaceAll("<", "＜").replaceAll("%", "％").replaceAll("&",
         * "＆").replaceAll(";", "；");
         */
        String isDebug = PlatformGlobalVar.SYS_PROPERTIES.get("isDebugUpload");
        String realPath = "";
        if ("1".equals(isDebug))
        {
            String nowPath = GlobalFileUtil.class.getClassLoader().getResource("/").getPath();
            nowPath = nowPath.substring(0, nowPath.indexOf("WEB-INF"));
            realPath = nowPath + "/resources/upload/" + catalog;
        }
        else
        {
            realPath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog") + "/" + catalog;
        }

        File dir = new File(realPath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        FileOutputStream fout = new FileOutputStream(new File(realPath + "/" + fileName + postfix));

        BufferedInputStream bis = new BufferedInputStream(fileStream);
        byte[] cacheArray = new byte[2048];
        int size;
        while ((size = bis.read(cacheArray)) != -1)
        {
            fout.write(cacheArray, 0, size);
        }

        fout.close();
        bis.close();
        return "/" + catalog + "/" + fileName + postfix;
    }

    public static String getUploadPath()
    {
        String isDebug = PlatformGlobalVar.SYS_PROPERTIES.get("isDebugUpload");
        String realPath = "";
        if ("1".equals(isDebug))
        {
            String nowPath = GlobalFileUtil.class.getClassLoader().getResource("/").getPath();
            nowPath = nowPath.substring(0, nowPath.indexOf("WEB-INF"));
            realPath = nowPath + "/resources/upload";
        }
        else
        {
            realPath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog");
        }

        return realPath;
    }
}
