package com.zx.emanage.sysmanage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.sysmanage.service.IWmsCreContractLenderService;


/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCreContractLenderController
{
    private static Logger log = LoggerFactory.getLogger(WmsCreContractLenderController.class);

    @Autowired
    private IWmsCreContractLenderService iwmscrecontractlenderservice;

    /**
     * Description :通过传入字典ID获取字典数据值
     * 
     * @param 
     * @return wmsCreContractLenderList
     * @author jiaodelong
     */
    @RequestMapping(value = "/sysmanage/wmscrecontractlender.do", method = { RequestMethod.GET })
    @ResponseBody
    public List<com.zx.emanage.util.gen.entity.WmsCreContractLender> getWmsCreContractLender()
    {
        List<com.zx.emanage.util.gen.entity.WmsCreContractLender> wmsCreContractLenderList = iwmscrecontractlenderservice.getWmsCreContractLender();
        return wmsCreContractLenderList;
    }
    /**
     * 
     * @Title: getWmsCreContractLenderCards
     * @Description: TODO(还款用户名获取相应还款账号、还款银行列表；还款用户名、还款银行获取相应还款账号列表)
     * @return 
     * @author: handongchun
     * @time:2017年5月23日 上午10:00:12
     * history:
     * 1、2017年5月23日 handongchun 创建方法
     */
    @RequestMapping(value = "/sysmanage/wmscrecontractlendercards.do", method = { RequestMethod.POST })
    @ResponseBody
    public List<Map<String,Object>> getWmsCreContractLenderCards(
                                    @RequestParam(value="refund_name",defaultValue="") String refund_name,
                                    @RequestParam(value="refund_bank",defaultValue="") String refund_bank,
                                    @RequestParam(value="refund_number",defaultValue="") String refund_number){
        
        Map<String, String> params = new HashMap<>();
        params.put("refund_name", refund_name);
        params.put("refund_bank", refund_bank);
        params.put("refund_number", refund_number);
        
        List<Map<String, Object>> wmsCreContractLenderCards = iwmscrecontractlenderservice.getWmsCreContractLenderCards(params);
        return wmsCreContractLenderCards;
    }
   
}