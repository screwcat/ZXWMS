package com.zx.emanage.inve.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.inve.service.IWmsInveRedeemRecordService;
import com.zx.emanage.util.gen.SysTools;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveRedeemRecordController {
	private static Logger log = LoggerFactory.getLogger(WmsInveRedeemRecordController.class);
	
	@Autowired
	private IWmsInveRedeemRecordService wmsinveredeemrecordService;

    /**
    * @Title: saveWmsInveRedeemRecordPTP
    * @Description: PTP在还款时（最后一期 或提前全部还款）、回购 生成对应的赎回单据记录
    * @param request
    * @param wmsInveRedeemRecordList
    * @return 
    * @author: zhangyunfei
    * @time:2017年6月21日 上午9:55:38
    * history:
    * 1、2017年6月21日 zhangyunfei 创建方法
    */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/inve/saveWmsInveRedeemRecordPTP.dos", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> saveWmsInveRedeemRecordPTP(HttpServletRequest request, String wmsInveRedeemRecordList)
    {

        String result = "";
        Map<String, Object> map = new HashMap<>();

        // List<WmsInveRedeemRecord> list = new
        // Gson().fromJson(wmsInveRedeemRecordList, ArrayList.class);
        List<LinkedTreeMap> list = new Gson().fromJson(wmsInveRedeemRecordList, ArrayList.class);

        // 生成赎回记录
        result = wmsinveredeemrecordService.saveWmsInveRedeemRecordBatch(list);
        // 记录日志
        if ("success".equals(result))
        {
            String msg = "业务管理--理财管理--PTP生成赎回任务记录";
            SysTools.saveLog(request, msg);
            map.put("ret_code", "000");
            map.put("ret_msg", "生成赎回单据记录成功");
        }
        else
        {
            map.put("ret_code", "001");
            map.put("ret_msg", "生成赎回单据记录失败");
        }
        return map;
    }

}