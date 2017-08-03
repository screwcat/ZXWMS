package com.zx.sframe.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.zx.platform.syscontext.PlatformGlobalVar;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: uploadFileUtil
 * 模块名称：
 * @Description: 文件上传
 * @author jinzm
 * @date 2016年12月2日
 * @version V1.0
 * history:
 * 1、2016年12月2日 Administrator 创建文件
 */
public class UploadFileUtil
{

    public static void uploadFile(String srcFileName, InputStream inputStream, HttpServletRequest request, String... filePath) throws IOException
    {
        // 上传服务器的路径
        String path = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog");
        if (filePath == null || filePath.length == 0)
        {
            path = path + "/income/";
        }
        else
        {
            path = path + "/" + filePath[0];
        }
        // String path =
        // request.getSession().getServletContext().getRealPath("/WEB-INF/upload/income");
        File file = new File(path);
        if (!file.exists())
        {
            file.mkdirs();
        }
        path += "\\" + srcFileName;
        // 文件流写到服务器端
        FileOutputStream outputStream = new FileOutputStream(path);
        IOUtils.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
    }
}
