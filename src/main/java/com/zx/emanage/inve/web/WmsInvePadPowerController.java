package com.zx.emanage.inve.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInvePadPowerService;
import com.zx.emanage.util.gen.entity.WmsInvePadPower;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInvePadPowerController {
	private static Logger log = LoggerFactory.getLogger(WmsInvePadPowerController.class);
	
	@Autowired
	private IWmsInvePadPowerService wmsinvepadpowerService;

    /**
     * @Title: authorPad
     * @Description: pad解锁 或强退 
     * @param personnel_id
     * @param personnel_shortCode
     * @param device_num
     * @param sys_status 1解锁  4强退
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:29:48
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/authorPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> authorPad(WmsInvePadPower wmsInvePadPower, String personnel_shortCode, Integer sys_status)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {
            wmsInvePadPower.setPersonnel_shortcode(personnel_shortCode);
            wmsinvepadpowerService.authorPad(wmsInvePadPower, sys_status);
            resMap.put("flag", true);
        }
        catch (Exception e)
        {
            resMap.put("flag", false);
            e.printStackTrace();
        }
        return resMap;
    }
}