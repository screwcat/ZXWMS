package com.zx.emanage.loanpost.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.loanfina.service.IWmsFinaCrePeriodRepayService;
import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller("loanPostWmsFinaCrePeriodRepayController")
public class WmsFinaCrePeriodRepayController
{
    private static Logger log = LoggerFactory.getLogger(WmsFinaCrePeriodRepayController.class);

    @Autowired
    private IWmsFinaCrePeriodRepayService wmsfinacreperiodrepayService;

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    @RequestMapping(value = "/loanpost/wmsfinacreperiodrepaywithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                             RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo)
    {
        return wmsfinacreperiodrepayService.getListWithoutPaging(queryInfo);
    }

}