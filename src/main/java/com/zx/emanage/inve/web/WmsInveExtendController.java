package com.zx.emanage.inve.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveExtendService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

@Controller
public class WmsInveExtendController {
    @Autowired
    private IWmsInveExtendService wmsInveExtendService;// 续期
    
    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    /**
     * 
     * @Title: inveExtendSave
     * @Description: 续期处理
     * @param wmsInveTransaProd 上单产品信息对象
     * @param date_of_payment 续期开始时间
     * @param date_of_end 续期结束时间
     * @param wms_inve_transa_id 上单表主键ID
     * @param is_order_redeem 是否预约赎回
     * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
     * @param protocol_type 债权协议类型1表示字纸债权,2表示电子债权
     * @param new_customer_email 邮箱地址
     * @param request 请求信息
     * @return 返回字符串类型的信息提示
     * @author: DongHao
     * @time:2017年3月13日 下午4:39:31
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/inveExtendSave.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    protected String inveExtendSave(WmsInveTransaProd wmsInveTransaProd, Date date_of_payment, Date date_of_end, Integer wms_inve_transa_id,
                                    Integer is_order_redeem, String bill_type, String protocol_type, String new_customer_email,
                                    HttpServletRequest request, WmsInveCustomerCard wmsInveCustomerCard)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            wmsInveExtendService.inveInveExtendSave(wmsInveTransaProd, wms_inve_transa_id, date_of_payment, date_of_end, is_order_redeem, bill_type,
                                                    protocol_type, new_customer_email, user, wmsInveCustomerCard);

            int count = wmsInveSignedApplicationService.findClerkDataBySalemanCount(user);
            if (count != 0)
            {
                count = count - 1;
            }
            return "尊敬的客户您好:<br/>&nbsp;&nbsp;&nbsp;&nbsp;您的前方正有" + count + "个客户等待办理业务,请关注窗口信息,过号则会重排";

        } catch (Exception e) {
            // do nothing
        }
        return "error";
    }
    
    /**
     * 
     * @Title: autoInveExtendSave
     * @Description: 股东单据自动续期
     * @param is_new_data 0否 续期2016-07-01前的数据  1是续期2016-07-01后的数据 
     * @return 
     * @author: Guanxu
     * @time:2016年11月21日 下午4:57:21
     * history:
     * 1、2016年11月21日 Guanxu 创建方法 针对11.25日发版处理需要手动续期的单据
     * 2、2016年11月29日 Guanxu 股东单据自动续期
     */
    @RequestMapping(value = "/inve/autoInveExtendSaveMoa.dos", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    protected Map<String, Object> autoInveExtendSave(String is_new_data)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            wmsInveExtendService.autoInveExtendSave(is_new_data);
            map.put("result", "success");
            return map;
        } catch (Exception e) {
            // do nothing
        }
        map.put("result", "error");
        return map;
    }
    
    /**
     * 
     * @Title: inveReservationRenewalAutoHandler
     * @Description: 将预约续期的单据进行续期操作
     * @param is_new_data 0否 续期2016-07-01前的数据  1是续期2016-07-01后的数据 
     * @return 操作的结果 success 或则 error
     * @author: DongHao
     * @time:2016年11月18日 下午5:05:22
     * history:
     * 1、2016年11月18日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/inveReservationRenewalAutoHandler.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    protected Map<String, Object> inveReservationRenewalAutoHandler(String is_new_data)
    {
        String resultStr = wmsInveExtendService.inveReservationRenewalAutoHandler(is_new_data);
        Map<String, Object> map = new HashMap<>();
        map.put("resultStr", resultStr);
        return map;
    }

    /**
     * 
     * @Title: inveRenewalOldBill
     * @Description: 老产品单据跨年续期
     * @param date_of_payment
     * @param wms_inve_transa_id
     * @param is_order_redeem
     * @param order_financial_bill_code
     * @param bill_type
     * @param request
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 上午1:30:05
     * history:
     * 1、2017年1月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/inveRenewalOldBill.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> inveRenewalOldBill(Date date_of_payment, Integer wms_inve_transa_id, Integer is_order_redeem,
                                                  HttpServletRequest request)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            wmsInveExtendService.inveInveExtendSave(null, wms_inve_transa_id, date_of_payment, null, is_order_redeem, "0", "", "", user, null);
            resultMap.put("ret_code", "续期成功!");
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "续期失败!");
        }
        return resultMap;
    }

    /**
     * 
     * @Title: verifyRenewalBill
     * @Description: 验证所要续期的单据是否是预约续期并且是可拆分类的产品
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param date_of_payment 续期开始时间
     * @return 返回boolean类型的值,如果所要续期的单据是预约续期并且是可拆分类的产品返回true,否则返回false
     * @author: DongHao
     * @time:2017年3月13日 下午3:56:49
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/verifyRenewalBill.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public boolean verifyRenewalBill(String wms_inve_transa_id, String wms_inve_transa_prod_id, Date date_of_payment)
    {

        return wmsInveExtendService.verifyRenewalBill(wms_inve_transa_id, wms_inve_transa_prod_id, date_of_payment);
    }

    /**
     * 
     * @Title: generateCreditProtocol
     * @Description: 针对预约续期的单据进行生成债权合同
     * @return 返回map集合提示信息
     * @author: DongHao
     * @time:2017年3月20日 上午9:14:12
     * history:
     * 1、2017年3月20日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/generateCreditProtocol.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> generateCreditProtocol()
    {

        return wmsInveExtendService.generateCreditProtocol();
    }
   
}
