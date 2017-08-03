package com.zx.sframe.util.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.GlobalFileUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 文件公共处理类
 * 
 * @author gx
 */
@Controller("fileHanldeController")
public class FileHanldeController
{
    private static Logger logger = LoggerFactory.getLogger(FileHanldeController.class);

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    /**
     * 将文件保存后，返回文件保存的服务器相对路径，如果同时上传多个文件用，分隔文件名称
     * 
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/util/fileuploadsave3.do")
    public ModelAndView handleFormUpload(HttpServletRequest request, HttpSession session, HttpServletResponse response)
    {
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        String result = "";
        if (this.multipartResolver != null && this.multipartResolver.isMultipart(request))
        {
            if (request instanceof MultipartHttpServletRequest)
            {

                MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

                Map<String, MultipartFile> fileMap = mRequest.getFileMap();
                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
                {
                    MultipartFile mf = entity.getValue();
                    long fileSize = mf.getSize();
                    if (fileSize > 60 * 1024 * 1024)
                    {
                        result = "maxsizeerror";
                    }
                    String srcFileName = mf.getOriginalFilename();
                    String postfix = "";
                    if (srcFileName.lastIndexOf(".") > -1)
                    {
                        postfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1).toLowerCase();
                        if ("exe".equals(postfix) || "cmd".equals(postfix) || "bat".equals(postfix))
                        {
                            result = "filetypeerror";
                        }
                    }
                    try
                    {
                        String path = GlobalFileUtil.saveUploadFile(mf.getInputStream(), null, mf.getOriginalFilename());
                        result = result + path + ",";
                    }
                    catch (IOException e)
                    {
                        logger.error(e.getMessage());
                        result = "error";
                    }
                }
            }
        }
        if (result.endsWith(","))
        {
            result = result.substring(0, result.length() - 1);
        }
        return new ModelAndView("redirect:http://" + PlatformGlobalVar.SYS_PROPERTIES.get("remoteServer")
                                + "/WMS/resources/html/ajaxfileUpload.html?filepath=" + result);
    }

    @RequestMapping(value = "/util/fileuploadsave2.do")
    @ResponseBody
    public String handleFormUpload2(HttpServletRequest request, HttpSession session)
    {
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        String result = "";
        if (this.multipartResolver != null && this.multipartResolver.isMultipart(request))
        {
            if (request instanceof MultipartHttpServletRequest)
            {

                MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

                Map<String, MultipartFile> fileMap = mRequest.getFileMap();
                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
                {
                    MultipartFile mf = entity.getValue();
                    long fileSize = mf.getSize();
                    if (fileSize > 10 * 1024 * 1024)
                    {
                        return "maxsizeerror";
                    }
                    String srcFileName = mf.getOriginalFilename();
                    String postfix = "";
                    if (srcFileName.lastIndexOf(".") > -1)
                    {
                        postfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1).toLowerCase();
                        if (!("rar".equals(postfix) || "zip".equals(postfix)))
                        {
                            return "filetypeerror";
                        }
                    }
                    try
                    {
                        String path = GlobalFileUtil.saveUploadFile(mf.getInputStream(), "" + user.getUserId(),
                                                                    mf.getOriginalFilename());
                        result = result + path + ",";
                    }
                    catch (IOException e)
                    {
                        logger.error(e.getMessage());
                        return "error";
                    }
                }
            }
        }
        if (result.endsWith(","))
        {
            result = result.substring(0, result.length() - 1);
        }
        return SysUtil.ajaxFileTextEncoding(result, request);
    }
}
