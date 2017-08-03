package com.zx.emanage.inve.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveCustomerWxService;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.loanappro.service.IWmsSysPropertyService;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocolTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveCusProtocolForWXController
 * 模块名称：客户通过微信公众号查看无纸质合同单据及其协议信息接口
 * @Description: 内容摘要：无纸质合同单据协议查看相关接口
 * @author jinzhm
 * @date 2017年7月20日
 * @version V1.0
 * history:
 * 1、2017年7月20日 jinzhm 创建文件
 */
@Controller
public class WmsInveCusProtocolForWXController
{

    /**
     * 查看债权时间限制配置主键
     */
    private static final Integer VIEW_PROTOCOL_TIME_LIMIT_ID = 5008;

    @Autowired
    private IWmsSysPropertyService sysPropertyService;

    @Autowired
    private IWmsInveClerkProtocolService wmsInveClerkProtocolService;

    @Autowired
    private IWmsInveClerkProtocolService wmsinveclerkprotocolService;

    /**
     *  微信公众号操作无纸质债券的客户的操作service
     */
    @Autowired
    private IWmsInveCustomerWxService wmsInveCustomerWxService; 

    /**
     * 
     * @Title: verifyCustomerPhoneIsRegistered
     * @Description: 验证客户手机号是否注册过
     * @param phone_number 手机号
     * @return 返回map集合验证信息提示
     * @author: DongHao
     * @time:2017年7月20日 上午10:20:26
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/verifyCustomerPhoneIsRegistered.dos", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> verifyCustomerPhoneIsRegistered(String phone_number)
    {
        //定义返回结果map集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        try
        {
            //验证当前手机号是否已经注册过或者是否允许注册,返回map提示信息集合
            resMap = wmsInveCustomerWxService.verifyCustomerPhoneIsRegistered(phone_number);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败");
            resMap.put("ret_data", null);
        }
        
        return resMap;
    }
    
    
    /**
     * 
     * @Title: zshCustomerLogin
     * @Description: 验证客户登录
     * @param phone_number 手机号
     * @param password 登录密码
     * @return 返回map信息提示
     * @author: DongHao
     * @time:2017年7月20日 下午1:22:16
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/zshCustomerLogin.dos", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> zshCustomerLogin(String phone_number, String password)
    {
        //定义返回结果map集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        try
        {
            //验证客户的手机号和密码登录
            resMap = wmsInveCustomerWxService.zshCustomerLogin(phone_number, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败");
            resMap.put("ret_data", null);
        }
        
        return resMap;
    }
    
    /**
     * @Title: phoneGetBackInfoAndHistory
     * @Description: 获取柜员合同列表
     * @param transa_id 上单主键
     * @param status    单据状态
     * @param product_account 投资金额
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午1:07:40
     * history:
     * 1、2017年7月20日 zhangyunfei 创建方法
     */
    @RequestMapping(value = "/inve/phoneGetWmsCusProtocolList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetWmsCusProtocolList(String transa_id, String status, String product_account)
    {
        Map<String, Object> map = new HashMap<>();
        // 返回数据map
        Map<String, Object> ret_data = new HashMap<>();
        // 协议集合
        List<Map<String, String>> protocolList = new ArrayList<Map<String, String>>();
        // 主合同map
        Map<String, String> map1 = new HashMap<String, String>();
        // 补充协议map
        Map<String, String> map2 = new HashMap<String, String>();
        // 外部转让协议map
        Map<String, String> map3 = new HashMap<String, String>();
        // 内部转让协议map
        Map<String, String> map4 = new HashMap<String, String>();

        try
        {
            // 根据上单主键查询最新的柜员合同
            WmsInveClerkProtocol wmsInveClerkProtocol = wmsinveclerkprotocolService.getWmsInveClerkProtocolByTransaIdForPhone(transa_id, product_account);
            // 根据上单主键和柜员合同主键查询 债权匹配历史表主键
            WmsInveClerkProtocolTransaCrepkg wmsInveClerkProtocolTransaCrepkg = wmsinveclerkprotocolService.getWmInveClerkProtocolTransaCrepkg(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
            // 根据上单主键、柜员合同主键、债权匹配历史主键 判断是否债权的他项人全部都是赵燕国 是的话 不需要查看内部转让协议
            Map<String, String> resultMap = wmsinveclerkprotocolService.getWmsInveClaimsInfosWithoutZHAO(wmsInveClerkProtocol.getWms_inve_transa_id().toString(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id().toString(), wmsInveClerkProtocolTransaCrepkg.getWms_inve_clerk_protocol_transa_crepkg_id().toString());
            // 主合同
            map1.put("protocol_code", wmsInveClerkProtocol.getProt_code());
            map1.put("protocol_type", "1");
            map1.put("update_time_str", wmsInveClerkProtocol.getLast_update_timestamp_str());
            map1.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() + "");
            protocolList.add(map1);
            // 补充协议
            map2.put("protocol_code", wmsInveClerkProtocol.getProt_code());
            map2.put("protocol_type", "4");
            map2.put("update_time_str", wmsInveClerkProtocol.getLast_update_timestamp_str());
            map2.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() + "");
            protocolList.add(map2);
            // status=1 收益中微信需要显示四个合同 (个人出借咨询与服务协议/补充协议/债权转让及受让协议/内部转让协议)
            if ("1".equals(status) || "2".equals(status))
            {
                // 外部转让
                map3.put("protocol_code", wmsInveClerkProtocol.getProt_code());
                map3.put("protocol_type", "6");
                map3.put("update_time_str", DateUtil.date2String(wmsInveClerkProtocolTransaCrepkg.getCreate_timestamp(), "yyyy-MM-dd"));
                map3.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() + "");
                protocolList.add(map3);

                // 内部转让 (他项人不全是赵燕国 打印内部转让协议)
                if ("true".equals(resultMap.get("result")))
                {
                    map4.put("protocol_code", wmsInveClerkProtocol.getProt_code());
                    map4.put("protocol_type", "5");
                    map4.put("update_time_str", DateUtil.date2String(wmsInveClerkProtocolTransaCrepkg.getCreate_timestamp(), "yyyy-MM-dd"));
                    map4.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() + "");
                    protocolList.add(map4);
                }
            }

            map.put("ret_code", "000");
            map.put("ret_msg", "操作成功");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            map.put("ret_code", "001");
            map.put("ret_msg", "操作失败");
        }
        // 上单主键
        ret_data.put("transa_id", transa_id);
        // 合同集合
        ret_data.put("protocol_list", protocolList);
        // 返回封装好的map
        map.put("ret_data", ret_data);

        return map;
    }

    /**
     * 
     * @Title: phoneGetWmsCusProtocol
     * @Description: 微信公众号ZSH调用 通过上单主键 协议编号 协议主键查询对应的合同
     * @param transa_id
     * @param procotolIds
     * @param protocol_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午6:49:07
     * history:
     * 1、2017年7月21日 admin 创建方法
     */
    @RequestMapping(value = "/inve/phoneGetWmsCusProtocol.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> phoneGetWmsCusProtocol(String transa_id, String procotolIds, String protocol_id)
    {
        Map<String, Object> resMap = null;
        // 客户合同查询条件vo
        WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO = new WmsInveClerkProtocolSearchBeanVO();
        // 上单主键
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_transa_id(Integer.parseInt(transa_id));
        // 柜员合同主键
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_id(Integer.parseInt(protocol_id));
        // 1主合同
        if ("1".equals(procotolIds))
        {
            resMap = wmsInveClerkProtocolService.phoneGetWmsCusMainProtocolInfo(wmsInveClerkProtocolSearchBeanVO);
        }
        // 补充协议
        else if ("2".equals(procotolIds))
        {
            resMap = wmsInveClerkProtocolService.phoneGetWmsCusProtocolInfo(wmsInveClerkProtocolSearchBeanVO);
        }
        // 内部转让协议
        else if ("5".equals(procotolIds))
        {
            resMap = wmsInveClerkProtocolService.phoneGetWmsCusInterProtocolInfo(wmsInveClerkProtocolSearchBeanVO);
        }
        // 外部转让协议
        else if ("6".equals(procotolIds))
        {
            resMap = wmsInveClerkProtocolService.phoneGetWmsCusM2MProtocolInfo(wmsInveClerkProtocolSearchBeanVO);
        }
        return resMap;
    }

    /**
     * @Title: setCusPassword
     * @Description: 设置客户密码
     * @param phone_number 手机号
     * @param password 密码
     * @return 返回是否成功标志
     * @author: jinzhm
     * @time:2017年7月20日 下午2:49:04
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/setCusPassword.dos", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> setCusPassword(String phone_number, String password)
    {
        // 设置返回数据
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("ret_code", "000");
        resMap.put("ret_msg", "");
        try
        {
            // 设置密码并返回数据
            Map<String, Object> dataMap = wmsInveCustomerWxService.setPassword(phone_number, password);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("result", "1");
            resultMap.put("user", dataMap);
            resMap.put("ret_data", resultMap);
        }
        catch (Exception e)
        {
            // 如果设置密码出现错误返回错误信息
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("result", "0");
            resMap.put("ret_data", resultMap);
        }
        return resMap;
    }

    /**
     * @Title: getAllCusProtocol
     * @Description: 查询客户所有无纸质协议列表
     * @param phone_number 手机号
     * @return 无纸质合同产品相关单据信息集合
     * @author: jinzhm
     * @time:2017年7月20日 下午2:52:45
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getAllCusProtocol.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAllCusProtocol(String phone_number)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("ret_code", "000");
        resMap.put("ret_msg", "");
        try
        {
            List<Map<String, Object>> dataList = wmsInveClerkProtocolService.getWithoutPaperProtocolTransaProtocolInfoList(phone_number);
            resMap.put("ret_data", dataList);
        }
        catch (Exception e)
        {
            // e.printStackTrace();
        }

        return resMap;
    }

    /**
     * @Title: checkViewProtocolIsAvailable
     * @Description: 校验当前时间是否可以查看无纸质协议信息
     * @return 是否可用标志
     * @author: jinzhm
     * @time:2017年7月20日 下午2:56:53
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/checkViewProtocolIsAvailable.dos", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> checkViewProtocolIsAvailable()
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("result", "1");
        // 当前时间小时的数字
        int nowHourInt = Integer.parseInt(DateUtil.date2String(new Date(), "HH"));
        // 获得系统配置的不可查看协议信息的配置信息
        WmsSysProperty property = sysPropertyService.getInfoByPK(VIEW_PROTOCOL_TIME_LIMIT_ID);

        if (property != null)
        {
            // 获得配置的限制时间段
            String[] value = property.getProperty_value().split("-");
            // 将当前时间和限制时间段比较，如果当前时间在限制时间段内，则返回不能查看
            if ((Integer.parseInt(value[0]) > Integer.parseInt(value[1]) && (Integer.parseInt(value[0]) <= nowHourInt || nowHourInt < Integer.parseInt(value[1])))
                || (Integer.parseInt(value[0]) < Integer.parseInt(value[1]) && (Integer.parseInt(value[0]) <= nowHourInt && nowHourInt < Integer.parseInt(value[1]))))
            {
                dataMap.put("result", "0");
            }
        }

        //
        Map<String, Object> retMap = new HashMap<String, Object>();
        // 设置返回数据
        retMap.put("ret_code", "000");
        retMap.put("msg", "");
        retMap.put("ret_data", dataMap);
        return retMap;
    }
}
