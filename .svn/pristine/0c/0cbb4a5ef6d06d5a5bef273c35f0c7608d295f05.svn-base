package com.zx.emanage.inve.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zx.emanage.inve.service.IWmsInvePhoneService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveVerifyCustomerSignedVo;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePhoneController
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2016年12月30日
 * @version V1.0
 * history:
 * 1、2016年12月30日 DongHao 创建文件
 */
@Controller
public class WmsInvePhoneController
{

    @Autowired
    private IWmsInvePhoneService wmsInvePhoneService;

    @Autowired
    private IWmsSysDictDataService wmsSysDictDataService;

    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    /**
     * 
     * @Title: getSpecialMenuInfosMoa
     * @Description: 获取特殊功能菜单项
     * @param one_menu_code
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2016年12月30日 下午2:18:57
     * history:
     * 1、2016年12月30日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSpecialMenuInfosMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSpecialMenuInfosMoa(String one_menu_code, String personnel_id, String version)
    {
        Map<String, Object> resultMap = wmsInvePhoneService.getSpecialMenuInfosMoa(personnel_id, version);
        return resultMap;
    }

    /**
     * 
     * @Title: getSpecialTeamInfosMoa
     * @Description: 获取团队信息
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午5:06:59
     * history:
     * 1、2017年1月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSpecialTeamInfosMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSpecialTeamInfosMoa(String personnel_id, String dept_id, String menu_code)
    {
        Map<String, Object> resultMap = wmsInvePhoneService.getSpecialTeamInfosMoa(Integer.parseInt(personnel_id), dept_id, menu_code);
        return resultMap;
    }

    /**
     * 
     * @Title: getSpecialDeptInfosMoa
     * @Description: 获取数据权限下的部门
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月3日 上午9:32:05
     * history:
     * 1、2017年1月3日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSpecialDeptInfosMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSpecialDeptInfosMoa(String personnel_id, String dept_id, String menu_code)
    {
        Map<String, Object> resultMap = wmsInvePhoneService.getSpecialDeptInfosMoa(personnel_id, dept_id, menu_code);
        return resultMap;
    }

    /**
     * 
     * @Title: getSyncMePerformanceInfosMoa
     * @Description: 同步与我有关的业绩信息
     * @param personnel_id 人员id
     * @param query_type 查询数据范围(1:表示待我处理数据,2:与我相关数据)
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午2:49:48
     * history:
     * 1、2017年1月5日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getSyncMePerformanceInfosMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getSyncMePerformanceInfosMoa(String personnel_id, String query_type)
    {
        Map<String, Object> resultMap = wmsInvePhoneService.getSyncMePerformanceInfosMoa(personnel_id, query_type);
        return resultMap;
    }


    @RequestMapping(value = "/inve/phoneGetClerkDataMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getClerkData(String personnel_id)
    {
        Map<String, Object> resultMap = wmsInvePhoneService.getClerkSortData(personnel_id);
        return resultMap;
    }

    @RequestMapping(value = "/inve/phoneGetClerkDataCountMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getClerkDataCount(String personnel_id)
    {
        int i = wmsInvePhoneService.getClerkDataCount(personnel_id);
        Map<String, Object> map = new HashMap<>();
        map.put("count", i);
        return map;
    }

    /**
     * 
     * @Title: getAchVicegelListMoa
     * @Description: 获取数据权限下的副总清单
     * @param menu_code
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2017年1月6日 上午11:45:30
     * history:
     * 1、2017年1月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getAchVicegelListMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getAchVicegelListMoa(String menu_code, String personnel_id)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsInvePhoneService.getAchVicegelListMoa(menu_code, personnel_id);
        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getDictionaryDataPad
     * @Description: pad请求获取数据字典详细内容
     * @param wms_sys_dict_id
     * @param p_wms_sys_dict_data_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月23日 上午8:54:28
     * history:
     * 1、2017年2月23日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getDictionaryDataPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getDictionaryDataPad(String wms_sys_dict_id, String p_wms_sys_dict_data_id)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        try
        {
            map = wmsSysDictDataService.getDictionaryDataPad(wms_sys_dict_id, p_wms_sys_dict_data_id);
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "获取字典数据成功");
            resultMap.put("ret_data", map);
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "获取字典数据失败");
            resultMap.put("ret_data", map);
        }
        return resultMap;
    }

    /**
     * 
     * @Title: getIncomeCardPad
     * @Description: pad客户端获取用户收益卡数据
     * @param cus_name 客户姓名 
     * @param id_card 客户身份证号
     * @param version 版本号
     * @return 返回客户收益卡map集合
     * @author: DongHao
     * @time:2017年3月1日 下午8:45:38
     * history:
     * 1、2017年3月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getIncomeCardPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getIncomeCardPad(String cus_name, String id_card, String version)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try
        {
            list = wmsInvePhoneService.getIncomeCardPad(cus_name, id_card.trim().toUpperCase());
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "获取收益卡信息成功");
            resultMap.put("ret_data", list);
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "获取收益卡信息失败");
            resultMap.put("ret_data", list);
        }

        return resultMap;
    }

    /**
     * 
     * @Title: getCustomerListPad
     * @Description: 根据客户名称、客户联系方式和客户有效证件进行获取客户信息
     * @param bean 客户信息
     * @return 返回map集合(包含电话号是否可以修改,客户信息结果list)两部分
     * @author: DongHao
     * @time:2017年3月1日 下午9:19:09
     * history:
     * 1、2017年3月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCustomerListPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCustomerListPad(CrmCustomerInfo bean, HttpServletRequest request)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            bean.setPersonnel_id(user.getUserId());
            bean.setPage(1);
            bean.setSize(1000);
            map = wmsInvePhoneService.getCustomerListPad(bean);
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "获得客户信息成功");
            resultMap.put("ret_data", map);
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "获得客户信息失败");
            resultMap.put("ret_data", map);
        }
        return resultMap;
    }

    /**
     * 
     * @Title: getCategoryListPad
     * @Description: pad客户端获取产品信息
     * @param category_type 产品类型1:表示主推产品;2:表示公司可售产品
     * @param device_num 设备序列号
     * @return 
     * @author: DongHao
     * @time:2017年3月1日 下午9:48:20
     * history:
     * 1、2017年3月1日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getCategoryListPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getCategoryListPad(int category_type, String device_num, HttpServletRequest request)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        List<Map<String, Object>> list = new ArrayList<>();
        try
        {
            list = wmsInvePhoneService.getCategoryListPad(category_type, user);
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "获得产品信息成功");
            resultMap.put("ret_data", list);
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "获得产品信息失败");
            resultMap.put("ret_data", list);
        }
        return resultMap;
    }

    /**
     * 
     * @Title: verificationCustomerPad
     * @Description: 验证客户关键信息是否存在
     * @param contact_number 联系电话
     * @param id_card_number 有效证件
     * @param costomer_id 客户id
     * @param request 信息对象
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月2日 上午12:43:43
     * history:
     * 1、2017年3月2日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/verificationCustomerPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> verificationCustomerPad(String contact_number, String id_card_number, String costomer_id, HttpServletRequest request)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            map = wmsInvePhoneService.verificationCustomerPad(contact_number, id_card_number.trim().toUpperCase(), costomer_id, user);
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "验证成功");
            resultMap.put("ret_data", map);
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "验证失败");
            resultMap.put("ret_data", map);
        }

        return resultMap;
    }

    /**
     * 
     * @Title: saveSignedInfoPad
     * @Description: pad客户端保存上单信息
     * @param request 页面参数信息
     * @param wmsInveTransa 上单表信息
     * @param wmsInveTransaProd 上单产品表信息
     * @param wmsCustomer 客户信息
     * @param beanVO 查询条件对象
     * @param version 版本号
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月2日 上午3:07:39
     * history:
     * 1、2017年3月2日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/saveSignedInfoPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> saveSignedInfoPad(HttpServletRequest request, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd
                                                                                                        , WmsInveCustomer wmsCustomer
                                                                                                        , WmsInveTransaSearchBeanVO beanVO
                                                                                                        , String version
                                                                                                        ,WmsInveVerifyCustomerSignedVo vo)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            /*****************(1)pad客户端进行签单的时候首先对必填字段进行非空验证***********************/
            resultMap = wmsInveSignedApplicationService.verificationSignedInfo(wmsInveTransa, wmsInveTransaProd, wmsCustomer, beanVO);

            // 判断针对签单信息的非空验证是否通过
            if (resultMap != null && resultMap.size() != 0 && "000".equals(resultMap.get("ret_code").toString()))
            {
                if (wmsInveTransaProd != null)
                {
                    // 判断收益卡银行是否为空
                    if (wmsInveTransaProd.getBank_of_deposit_code() != null)
                    {
                        if (wmsInveTransaProd.getBank_of_deposit().contains("广东发展银行"))
                        {
                            wmsInveTransaProd.setBank_of_deposit_code("广发银行");
                        }
                        else if (wmsInveTransaProd.getBank_of_deposit().contains("中国光大银行"))
                        {
                            wmsInveTransaProd.setBank_of_deposit_code("光大银行");
                        }
                        wmsInveTransaProd.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit_code());
                    }
                    // 判断收益卡所属省是否为空
                    if (wmsInveTransaProd.getBank_of_deposit_pro_code() != null)
                    {
                        wmsInveTransaProd.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro_code());
                    }
                    // 判断收益卡所属市是否为空
                    if (wmsInveTransaProd.getBank_of_deposit_city_code() != null)
                    {
                        wmsInveTransaProd.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city_code());
                    }
                }

                /*****************(2)pad客户端进行签单的时候要对有效证件和身份证号进行二次校验***********************/
                resultMap = wmsInvePhoneService.verificationCustomerPad(wmsInveTransa.getMobile_phone(), wmsInveTransa.getId_card(), wmsInveTransa.getCostomer_id() != null ? wmsInveTransa.getCostomer_id().toString() : "", user);

                // 判断针对pad客户端签单时的联系电话和有效证件号验证是否通过
                if (resultMap != null && resultMap.size() != 0 && "0".equals(resultMap.get("id_card_result").toString()) && "0".equals(resultMap.get("contact_number_result").toString()))
                {
                    //pad端上单验证所购买的产品是否允许上单
                    resultMap = wmsInveSignedApplicationService.verifyCustomerIsBuy(vo);
                    
                    //验证返回的验证结果是否可以购买
                    if(resultMap != null && resultMap.size() != 0 && !"0".equals(resultMap.get("is_buy").toString()))
                    {
                        // 执行pad端签单操作
                        resultMap = wmsInveSignedApplicationService.saveSignedInfoPad(wmsInveTransa, wmsInveTransaProd, wmsCustomer, beanVO, user);
                    }
                }
                else
                {
                    // 判断错误提示信息是否为空
                    if (resultMap != null && resultMap.size() != 0)
                    {
                        // 判断手机号验证是否出错
                        if ("1".equals(resultMap.get("contact_number_result").toString()))
                        {
                            resultMap.put("ret_code", "130");
                            resultMap.put("ret_msg", resultMap.get("contact_number_reason").toString());
                        }

                        // 判断有效证件好验证是否出错
                        if ("1".equals(resultMap.get("id_card_result").toString()))
                        {
                            resultMap.put("ret_code", "130");
                            resultMap.put("ret_msg", resultMap.get("id_card_reason").toString());
                        }

                    }
                }

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            resultMap.put("ret_code", "107");
            resultMap.put("ret_msg", "签单失败!");
        }

        return resultMap;
    }

    /**
     * 
     * @Title: getTeamCommMonByDeptManagerMoa
     * @Description: 获取管理佣金按部门经理进行汇总(接口 33)
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param statics_month 统计月份
     * @param dept_ids 团队id(多个团队id以","分隔)
     * @param personnel_info 部门经理姓名或者部门经理短工号
     * @param personnel_id 当前登录moa人员id
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年3月10日 下午4:09:36
     * history:
     * 1、2017年3月10日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamCommMonByDeptManagerMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamCommMonByDeptManagerMoa(Integer page, Integer page_size, String statics_month, String dept_ids, String personnel_info, Integer personnel_id)
    {
        Integer offset = 0;
        Integer pagesize = 0;
        if (page == 1)
        {
            offset = 0;
            pagesize = page_size;
        }
        else
        {
            offset = page_size * page - page_size;
            pagesize = page_size;
        }

        return wmsInvePhoneService.getTeamCommMonByDeptManagerMoa(offset, pagesize, statics_month, dept_ids, personnel_info, personnel_id);
    }

    /**
     * 
     * @Title: getTeamCommMonBySalemanMoa
     * @Description: 获取管理佣金按客户经理汇总
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param statics_month 统计月份
     * @param dept_ids 团队id值(多个团队id以","分隔)
     * @param personnel_id 部门经理人员id
     * @param personnel_info 客户经理姓名或客户经理短工号
     * @param user_id 当前登录moa的人员id
     * @return 返回Map数据集合
     * @author: DongHao
     * @time:2017年3月13日 上午9:45:34
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamCommMonBySalemanMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamCommMonBySalemanMoa(Integer page, Integer page_size, String statics_month, String dept_ids, String personnel_info, String personnel_id, Integer user_id)
    {
        Integer offset = 0;
        Integer pagesize = 0;
        if (page == 1)
        {
            offset = 0;
            pagesize = page_size;
        }
        else
        {
            offset = page_size * page - page_size;
            pagesize = page_size;
        }

        return wmsInvePhoneService.getTeamCommMonBySalemanMoa(offset, pagesize, statics_month, dept_ids, personnel_info, personnel_id, user_id);
    }

    /**
     * 
     * @Title: getTeamCommMonByItemMoa
     * @Description: 获取管理佣金按佣金项汇总
     * @param statics_month 佣金统计月份
     * @param personnel_id 客户经理人员id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:16:48
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamCommMonByItemMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamCommMonByItemMoa(String statics_month, String personnel_id)
    {
        return wmsInvePhoneService.getTeamCommMonByItemMoa(statics_month, personnel_id);
    }

    /**
     * 
     * @Title: getTeamCommMonByDataMoa
     * @Description: 获取管理佣金按单据展现
     * @param page 当前页
     * @param page_size 每页显示多少记录数
     * @param personnel_info 客户姓名
     * @param statics_month 佣金统计月份
     * @param personnel_id 人员id
     * @param comm_item_ids 佣金项id(01表示老佣金,02表示新佣金)
     * @return  返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:57:18
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamCommMonByDataMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamCommMonByDataMoa(Integer page, Integer page_size, String personnel_info, String statics_month, Integer personnel_id, String comm_item_ids)
    {
        Integer offset = 0;
        Integer pagesize = 0;
        if (page == 1)
        {
            offset = 0;
            pagesize = page_size;
        }
        else
        {
            offset = page_size * page - page_size;
            pagesize = page_size;
        }

        return wmsInvePhoneService.getTeamCommMonByDataMoa(offset, pagesize, personnel_info, statics_month, personnel_id, comm_item_ids);
    }

    /**
     * 
     * @Title: getTeamCommMonByManagerMoa
     * @Description: 获取管理佣金按客户经理汇总For副总/中分总/总经理/后线领导
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param personnel_info 客户经理姓名或客户经理的短工号
     * @param statics_month 佣金统计月份
     * @param personnel_id 当前登录Moa系统的人员id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:42:49
     * history:
     * 1、2017年3月13日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getTeamCommMonByManagerMoa.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamCommMonByManagerMoa(Integer page, Integer page_size, String personnel_info, String statics_month, Integer personnel_id)
    {
        Integer offset = 0;
        Integer pagesize = 0;
        if (page == 1)
        {
            offset = 0;
            pagesize = page_size;
        }
        else
        {
            offset = page_size * page - page_size;
            pagesize = page_size;
        }

        return wmsInvePhoneService.getTeamCommMonByManagerMoa(offset, pagesize, personnel_info, statics_month, personnel_id);
    }

    /**
     * 
     * @Title: delIncomeCard
     * @Description: 根据收益卡表主键进行删除收益卡信息
     * @param wms_inve_customer_card_id 收益卡表主键
     * @return 返回map集合(错误提示信息)
     * @author: DongHao
     * @time:2017年6月6日 下午1:19:58
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/delIncomeCardPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> delIncomeCard(Integer wms_inve_customer_card_id)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try
        {
            boolean flag = wmsInvePhoneService.delIncomeCardByWmsInveCustomerCardId(wms_inve_customer_card_id);
            if (flag)
            {
                resultMap.put("ret_code", "000");
                resultMap.put("ret_msg", "删除成功");
            }
            else
            {
                resultMap.put("ret_code", "001");
                resultMap.put("ret_msg", "删除失败");
            }
        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "删除失败");
        }

        return resultMap;
    }

    /**
     * 
     * @Title: getRedeemBillsPad
     * @Description: 根据crm客户id进行获取赎回再签的上单单据
     * @param costomer_id crm客户id
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年6月6日 下午2:07:45
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getRedeemBillsPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemBillsPad(Integer costomer_id)
    {
        // 定义返回给客户端的map集合的数据格式
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 定义存放数据的集合
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try
        {
            list = wmsInvePhoneService.getRedeemBillsPad(costomer_id);
            resMap.put("ret_code", "000");
            resMap.put("ret_msg", "操作成功");
            resMap.put("ret_data", list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败");
            resMap.put("ret_data", list);
        }
        return resMap;
    }

    /**
     * 
     * @Title: inveRedemptionPad
     * @Description: pad端赎回再签
     * @param request 请求信息
     * @param wmsInveTransa 上单信息对象
     * @param wmsInveTransaProd 上单产品信息对象
     * @param wmsInveCustomer 客户信息对象
     * @param list 上单产品集合
     * @param beanVO 上单信息
     * @param version 版本号
     * @param wms_inve_transa_id_strs 上单单据表主键(多个单据主键以“,”分隔)
     * @return 返回map集合信息提示
     * @author: DongHao
     * @time:2017年6月7日 上午11:58:49
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/inve/inveRedemptionPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> inveRedemptionPad(HttpServletRequest request, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd
                                                                                                        , WmsInveCustomer wmsInveCustomer
                                                                                                        , String list
                                                                                                        , WmsInveTransaSearchBeanVO beanVO
                                                                                                        , String version
                                                                                                        , String wms_inve_transa_id_strs)
    {
        // 需要将json字符串类型的list转换成list集合
        List<Map<String, Object>> categoryLis = new Gson().fromJson(list, ArrayList.class);
            
        // 定义返回错误信息集合
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 从session中获取当前登录的用户信息
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);

        try
        {
            if (wmsInveTransaProd != null)
            {
                // 判断收益卡银行是否为空
                if (wmsInveTransaProd.getBank_of_deposit_code() != null)
                {
                    if (wmsInveTransaProd.getBank_of_deposit().contains("广东发展银行"))
                    {
                        wmsInveTransaProd.setBank_of_deposit_code("广发银行");
                    }
                    else if (wmsInveTransaProd.getBank_of_deposit().contains("中国光大银行"))
                    {
                        wmsInveTransaProd.setBank_of_deposit_code("光大银行");
                    }
                    wmsInveTransaProd.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit_code());
                }
                // 判断收益卡所属省是否为空
                if (wmsInveTransaProd.getBank_of_deposit_pro_code() != null)
                {
                    wmsInveTransaProd.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro_code());
                }
                // 判断收益卡所属市是否为空
                if (wmsInveTransaProd.getBank_of_deposit_city_code() != null)
                {
                    wmsInveTransaProd.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city_code());
                }
            }
            
            // 验证赎回再签功能需要验证的信息
            resMap = wmsInvePhoneService.verifyBillIsSingle(wmsInveTransa, wmsInveTransaProd, categoryLis,wms_inve_transa_id_strs);
            
            if("000".equals(resMap.get("ret_code").toString()))
            {
                
                //定义产品id字符串变量,多个以","分割,该变量是为了验证新产品上单的验证
                String category_ids = "";
                
                //遍历产品集合
                for(Map<String, Object> map : categoryLis)
                {
                    category_ids += Double.valueOf(map.get("wms_inve_pruduct_category_id").toString()).intValue() + ",";
                }
                
                //判断产品id集合是否为空
                if(!"".equals(category_ids))
                {
                    //定义验证赎回再签时购买的产品是否满足条件(针对不提供纸质合同的产品)
                    WmsInveVerifyCustomerSignedVo vo = new WmsInveVerifyCustomerSignedVo();
                    //设置产品id
                    vo.setWms_inve_pruduct_category_id(category_ids.substring(0, category_ids.length() - 1));
                    //设置客户的有效证件号
                    vo.setId_card_number(wmsInveTransa.getId_card());
                    //设置客户的联系电话
                    vo.setContact_number(wmsInveTransa.getMobile_phone());
                    //设置crm客户id
                    vo.setCostomer_id(wmsInveTransa.getCostomer_id());
                    
                    //pad端上单验证所购买的产品是否允许上单
                    resMap = wmsInveSignedApplicationService.verifyCustomerIsBuy(vo);
                    
                    //验证返回的验证结果是否可以购买
                    if(resMap != null && resMap.size() != 0 && !"0".equals(resMap.get("is_buy").toString()))
                    {
                        // 执行赎回再签的业务逻辑
                        resMap = wmsInvePhoneService.inveRedemptionPadHandler(user, wmsInveTransa, wmsInveTransaProd, wmsInveCustomer, categoryLis, beanVO, version, wms_inve_transa_id_strs);
                    }
                    
                }
                else
                {
                    resMap.put("ret_code", "002");
                    resMap.put("ret_msg", "操作失败");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "002");
            resMap.put("ret_msg", "操作失败");
        }

        return resMap;
    }

    /**
     * 
     * @Title: getRedeemInfoPad
     * @Description: 获取单据赎回详情信息
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月7日 下午1:10:35
     * history:
     * 1、2017年6月7日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/getRedeemInfoPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getRedeemInfoPad(String wms_inve_transa_id)
    {

        return wmsInvePhoneService.getRedeemInfoPad(wms_inve_transa_id);
    }


    /**
     * 
     * @Title: scanCustomerIdCard
     * @Description: 扫描身份证
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月8日 下午6:13:53
     * history:
     * 1、2017年6月8日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/scanCustomerIdCardPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> scanCustomerIdCard(String id_card_url, HttpServletRequest request)
    {

        Map<String, Object> result = new HashMap<>();

        if (StringUtils.isBlank(id_card_url))
        {
            result.put("ret_code", "002");
            result.put("ret_msg", "操作失败，请联系客服");
            return result;
        }

        result.put("ret_code", "000");
        result.put("ret_msg", "操作成功");

        // 调用平台接口查询身份证信息
        RestTemplate restTemplate = new RestTemplate();

        String url = PlatformGlobalVar.SYS_PROPERTIES.get("server_upload_url")+"/upload/"+ id_card_url;
    
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        // 这3个参数是每个接口都要传的
        form.add("interface_num", "OCR_OUT_IdcardRecognition");// 接口编号
        form.add("sys_num", "WMS");// 系统编号
        form.add("user_id", "0");// 用户id
        form.add("image", url);
        try
        {

        Map<String, Object> resultMap = restTemplate.postForObject(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url") + "/modi/restful/simple", form, Map.class);

        if ((boolean) resultMap.get("flag"))
        {
            try
            {
                Map<String, Object> resMap = (Map<String, Object>) resultMap.get("result");
                Map<String, Object> resMap1 = (Map<String, Object>) resMap.get("words_result");
                    Map<String, Object> resMap2 = (Map<String, Object>) resMap1.get("公民身份号码");
                    Map<String, Object> resMap3 = (Map<String, Object>) resMap1.get("姓名");
                    Map<String, Object> resMap4 = (Map<String, Object>) resMap1.get("住址");

                    // 从扫描出的信息中，取出客户id ,姓名,地址
                String id_card = (String) resMap2.get("words");
                String cus_name = (String) resMap3.get("words");
                String contact_address = (String) resMap4.get("words");

                CrmCustomerInfo bean = new CrmCustomerInfo();
                bean.setId_card_number(StringUtils.upperCase(id_card));
                UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
                bean.setPersonnel_id(user.getUserId());
                Map<String, Object> map = wmsInvePhoneService.getCustomerListPad(bean);
                    // 如果些客户存在crm系统 中，返回客户相关信息，如果不存在，返回姓名，地址，身份证号信息
                List<Map<String, Object>> list = new ArrayList<>();
                list = (List<Map<String, Object>>) map.get("customerList");
                if (list.size() > 0)
                {

                    result.put("ret_data", list);

                }
                else
                {

                    list = new ArrayList<>();
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("costomer_id", "");
                    map1.put("cus_name", cus_name);
                    map1.put("contact_address", contact_address);
                    map1.put("id_card", id_card);
                    list.add(map1);
                    result.put("ret_data", list);

                }
            }
            catch (Exception e)
            {
                    e.printStackTrace();
                    result.put("ret_code", "002");
                    result.put("ret_msg", "操作失败，请联系客服");
            }

        }
        else
        {
            if (resultMap.get("result") != null)
            {
                Map<String, Object> errorMap = (Map<String, Object>) resultMap.get("result");
                result.put("ret_code", "002");
                result.put("ret_msg", errorMap.get("error_msg"));
            }
            else
            {
                    result.put("ret_code", "002");
                    result.put("ret_msg", "操作失败，请联系客服");
            }
        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("ret_code", "002");
            result.put("ret_msg", "操作失败，请联系客服");
        }
        return result;
    }


    /**
     * 
     * @Title: scanCustomerBankCard
     * @Description: 扫描银行卡
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月8日 下午6:14:18
     * history:
     * 1、2017年6月8日 zhangmingjian 创建方法
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/inve/scanCustomerBankCardPad.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> scanCustomerBankCard(String bank_card_url, String id_card_number)
    {
        Map<String, Object> resultdata = new HashMap<>();
        if (StringUtils.isBlank(bank_card_url) || StringUtils.isBlank(id_card_number))
        {
            resultdata.put("ret_code", "002");
            resultdata.put("ret_msg", "操作失败，请联系客服");
            return resultdata;
        }


        resultdata.put("ret_code", "000");
        resultdata.put("ret_msg", "操作成功");

        RestTemplate restTemplate = new RestTemplate();
        String url = PlatformGlobalVar.SYS_PROPERTIES.get("server_upload_url") + "/upload/" + bank_card_url;
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        // 这3个参数是每个接口都要传的
        form.add("interface_num", "OCR_OUT_BankcardRecognition");// 接口编号
        form.add("sys_num", "WMS");// 系统编号
        form.add("user_id", "0");// 用户id
        form.add("image", url);
        try
        {
            Map<String, Object> resultMap = restTemplate.postForObject(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url") + "/modi/restful/simple", form, Map.class);

            if ((boolean) resultMap.get("flag"))
            {
                Map<String, Object> result = (Map<String, Object>) resultMap.get("result");
                Map<String, Object> result1 = (Map<String, Object>) result.get("result");
                // 如果不是借记卡，返回错误信息并提示(0为未识别1借记卡2信用卡)
                Integer bank_card_type = (Integer) result1.get("bank_card_type");
                if (bank_card_type == 2)
                {
                    resultdata.put("ret_code", "002");
                    resultdata.put("ret_msg", "请使用银行借记卡重新拍照识别，谢谢");
                    return resultdata;
                }
                String bank_name = StringUtils.trim((String) result1.get("bank_name"));
                // 去掉银行卡名称的空格，如果卡名是广发，改成广发银行
                bank_name = bank_name.replace(" ", "");
                if (bank_name.equals("广发"))
                {
                    bank_name = bank_name + "银行";
                }
                // 如果收益卡为邮政储蓄，返回错误信息
                if (bank_name.indexOf("邮政储蓄") >= 0 || bank_name.indexOf("邮储") >= 0)
                {
                    resultdata.put("ret_code", "002");
                    resultdata.put("ret_msg", "银行卡不支持邮政储蓄银行请重新拍照识别，谢谢");
                    return resultdata;
                }

                String bank_card_number = (String) result1.get("bank_card_number");
                bank_card_number = bank_card_number.replace(" ", "");

                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("id_card", id_card_number);
                paraMap.put("card_no", bank_card_number);
                List<Map<String, Object>> list = wmsInvePhoneService.getCustomerBankCard(paraMap);
                // 如果查询出的银行卡信息为多条，说明此卡号在数据库存储有问题，
                // 如果没有查询出数据，将卡号，银行名称，银行卡表的id传给客户端，
                // 如果数据为一条那么将这条数据反回给和客户端
                if (list.size() > 1)
                {
                    resultdata.put("ret_code", "002");
                    resultdata.put("ret_msg", "此卡号在数据库存有问题");
                }
                else if (list.size() == 0)
                {
                    list = new ArrayList();
                    Map<String, Object> map = new HashMap();
                    map.put("card_no", bank_card_number);
                    map.put("bank_of_deposit", bank_name);
                    map.put("wms_inve_customer_card_id", "");
                    list.add(map);
                    resultdata.put("ret_data", list);
                }
                else
                {
                    resultdata.put("ret_data", list);
                }
            }
            else
            {
                if (resultMap.get("result") != null)
                {
                    Map<String, Object> errorMap = (Map<String, Object>) resultMap.get("result");
                    resultdata.put("ret_code", "002");
                    resultdata.put("ret_msg", errorMap.get("error_msg"));
                }
                else
                {
                    resultdata.put("ret_code", "002");
                    resultdata.put("ret_msg", "操作失败，请联系客服");
                }
            }
        }
        catch (Exception e)
        {
            resultdata.put("ret_code", "002");
            resultdata.put("ret_msg", "操作失败，请联系客服");
            e.printStackTrace();
        }

        return resultdata;
    }
    
    /**
     * 
     * @Title: verifyCustomerIsBuyPad
     * @Description: pad端对客户进行购买的产品进行验证
     * @param vo 购买的产品信息对象
     * @return 返回map信息集合
     * @author: DongHao
     * @time:2017年7月18日 下午5:45:37
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/verifyCustomerIsBuyPad.do", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> verifyCustomerIsBuyPad(WmsInveVerifyCustomerSignedVo vo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try
        {
            resMap = wmsInveSignedApplicationService.verifyCustomerIsBuy(vo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败!");
        }
        return resMap;
    }

    /**
     * 
     * @Title: genCustomerBuyNewCategoryData
     * @Description: 生成可以购买的新产品的客户名单
     * @return 返回信息提示
     * @author: DongHao
     * @time:2017年9月2日 下午1:21:19
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    @RequestMapping(value="/inve/genCustomerBuyNewCategoryData.dos", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> genCustomerBuyNewCategoryData()
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        try
        {
            resMap = wmsInvePhoneService.genCustomerBuyNewCategoryData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            resMap.put("ret_code", "001");
            resMap.put("ret_msg", "操作失败");
            resMap.put("exception_msg", e.getMessage());
        }
        return resMap;
    }
    
}
