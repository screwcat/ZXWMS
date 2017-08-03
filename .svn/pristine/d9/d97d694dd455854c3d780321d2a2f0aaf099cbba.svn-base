package com.zx.emanage.inve.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.WmsInvePrintEmptyContractService;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePrintContractController
 * 模块名称：空合同打印
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月20日
 * @version V3.5
 * history:
 * 1、2017年3月20日 zhangmingjian 创建文件
 */
@Controller
public class WmsInvePrintEmptyContractController
{
    @Autowired
    private WmsInvePrintEmptyContractService wmsInvePrintEmptyContractServiceImpl;

    /**
     * 
     * @Title: selectPrintEmptyContractList
     * @Description: 查询空合同打印列表数据
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月20日 上午10:47:39
     * history:
     * 1、2017年3月20日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/selectPrintEmptyContractList.do")
    @ResponseBody
    public Map<String, Object> selectPrintEmptyContractList()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("Rows", wmsInvePrintEmptyContractServiceImpl.selectPrintEmptyContractList(null));

        return map;
    };

    /**
     * 
     * @Title: selectPrintEmptyContractList
     * @Description: 打印空合同
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月20日 上午10:47:39
     * history:
     * 1、2017年3月20日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/printEmptyContract.do")
    @ResponseBody
    public void printEmptyContract(HttpServletRequest request, HttpServletResponse response, String url)
    {
        try
        {

            String text_name = url.split("/")[3];

            String file_path = WmsInveCreditController.class.getResource("/").getPath();
            file_path = file_path + url;
            File downloadFile = new File(file_path);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream fos = null;
            InputStream fis = null;
            fis = new FileInputStream(downloadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);

            // 中文文件名支持
            String encodedfileName = null;
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE"))
            {// IE
                encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
            }
            else if (null != agent && -1 != agent.indexOf("Mozilla"))
            {
                encodedfileName = new String(text_name.getBytes("UTF-8"), "iso-8859-1");
            }
            else
            {
                encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");

            int byteRead = 0;
            byte[] buffer = new byte[8192];
            while ((byteRead = bis.read(buffer, 0, 8192)) != -1)
            {
                bos.write(buffer, 0, byteRead);
            }

            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
