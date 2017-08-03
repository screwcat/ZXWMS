package com.zx.emanage.inve.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveSaleLimitService;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveSaleLimitController {

	private static Logger log = LoggerFactory.getLogger(WmsInveSaleLimitController.class);
	
	@Autowired
    private IWmsInveSaleLimitService wmsInveSalelLimitService;

    /**
     * @Title: saveWmsInveSaleLimitCurrentDay
     * @Description: 批量保存当日销售限额信息 
     * @param request
     * @param dataJson(当日销售限额集合)
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午2:38:56
     * history:
     * 1、2017年4月7日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/saveWmsInveSaleLimitCurrentDay.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveWmsInveSaleLimitCurrentDay(HttpServletRequest request, String dataJson)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsInveSalelLimitService.saveWmsInveSaleLimitCurrentDay(user, dataJson);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
       
        return result;
    }
}