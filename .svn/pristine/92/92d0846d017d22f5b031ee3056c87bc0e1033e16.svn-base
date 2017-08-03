package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePTPController
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月20日
 * @version V1.0
 * history:
 * 1、2017年6月20日 jinzhm 创建文件
 */
@Controller
public class WmsInvePTPController
{

    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    @RequestMapping(value = "/inve/generatePTPTransa.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> generatePTPTransa(String param)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {
            List<Map<String, Object>> resMapList = wmsInveSignedApplicationService.generatePTPTransa(param);
            resMap.put("ret_code", "000");
            resMap.put("ret_msg", "操作成功");
            resMap.put("flag", true);
            resMap.put("ret_data", resMapList);
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            resMap.put("ret_code", "002");
            resMap.put("ret_msg", "接口调用失败");
            resMap.put("flag", false);
            resMap.put("message", e.toString());
        }
        return resMap;
    }

}
