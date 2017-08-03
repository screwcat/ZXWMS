/**
 * 版权所有：版权所有(C) 2014，沈阳新融金融信息服务有限公司
 * 文件名称: WmsCusCustomerHeadController.java
 * 系统名称：WMS财务管理系统
 * 模块名称：客户管理模块
 * 内容摘要：主要实现贷款客户信息
 */
package com.zx.emanage.cusmanage.web;

import java.util.HashMap;
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

import com.zx.emanage.cusmanage.service.IWmsCusCustomerHeadService;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineHouseinfoService;
import com.zx.emanage.cusmanage.service.IWmsCusCustomerLineWorkinfoService;
import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCusCustomerHead;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsCusCustomerHeadController
{
    private static Logger log = LoggerFactory.getLogger(WmsCusCustomerHeadController.class);

    @Autowired
    private IWmsCusCustomerHeadService wmscuscustomerheadService; // 客户信息

    @Autowired
    private IWmsCusCustomerLineHouseinfoService wmscuscustomerlinehouseinfoservice; // 房产信息

    @Autowired
    private IWmsCusCustomerLineWorkinfoService wmscuscustomerlineworkinfoservice; // 工作信息

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadwithoutpaginglist.do", method = { RequestMethod.GET,
                                                                                           RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        return wmscuscustomerheadService.getListWithoutPaging(queryInfo);
    }

    @RequestMapping(value = "/cusmanage/wmscuscustomerheadwithoutpaginglistforadd.do", method = { RequestMethod.GET,
                                                                                                 RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithoutPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo,
                                                          HttpServletRequest request)
    {
        // 获取当前登录用户信息
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscuscustomerheadService.getListWithoutPagingforadd(queryInfo, user.getUserId());
    }

    /**
     * Description :实现客户查询列表 主要客户查询功能
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadwithpaginglist.do", method = { RequestMethod.GET,
                                                                                        RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPaging(WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        return wmscuscustomerheadService.getListWithPaging(queryInfo);
    }

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadwithpaginglistforadd.do", method = { RequestMethod.GET,
                                                                                              RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getListWithPagingforadd(WmsCusCustomerHeadSearchBeanVO queryInfo,
                                                       HttpServletRequest request)
    {
        // 获取当前登录用户信息
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscuscustomerheadService.getListWithPagingforadd(queryInfo, user);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCusCustomerHeadVO
     * @author auto_generator
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadinfobypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoByPK(Integer wms_cus_customer_id)
    {
        return wmscuscustomerheadService.getInfoMapByPK(wms_cus_customer_id);
    }

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return Map
     * @author
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadinfovomapbypk.do", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getInfoMapByPK(Integer wms_cus_customer_id)
    {
        Map<String, Object> mapAll = new HashMap<String, Object>();
        WmsCusCustomerHead mcch = wmscuscustomerheadService.getInfoByPK(wms_cus_customer_id);
        mapAll.put("onCus", mcch);
        return mapAll;
    }

    /***
     * 新增于暂存方法
     * 
     * @param request
     * @param bean
     * @param bean2
     * @param housestr
     * @return
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerheadsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, 
         com.zx.emanage.util.gen.entity.WmsCusCustomerHead khxinfo,
         com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo gzinfo, 
         String housestr,
         String delrowids, 
         String delccrowids, 
         String delcomprowids, 
         String delChildIds, 
         String cusChild, 
         String companystr, 
         String carstr,
         WmsCusCustomerHeadSearchBeanVO queryInfo)
    {
        // 返回信息
        String result = "";
        HttpSession session = request.getSession();

        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        int nwid = 0;
        String flag = "0";
        try
        {
            // 如果客户信息存在则进行更新操作,否则进行插入操作
            if (khxinfo.getWms_cus_customer_id() == null)
            {
                nwid = wmscuscustomerheadService.saveByPk(khxinfo, gzinfo, housestr, user, cusChild, companystr, carstr); // 调用
                                                                                                                  // 保存
                                                                                                                  // 新增方法，保存客户信息,工作信息,房产信息.
                flag = "1";
            }
            else
            {
                wmscuscustomerheadService.updateHHW(khxinfo, gzinfo, housestr, user, delrowids, delccrowids, delcomprowids,
                                                    delChildIds, cusChild, companystr, carstr, queryInfo);
                flag = "2";
                nwid = khxinfo.getWms_cus_customer_id();
            }
            result = "success@@" + nwid;
        }
        catch (Exception e)
        {
            log.error("error", e);
            result = "error";
        }
        if (!"error".equals(result) && "1".equals(flag))
        {
            String msg = "客户管理-新增客户-新增";
            SysTools.saveLog(request, msg); // record log method
        }
        else if (!"error".equals(result) && "2".equals(flag))
        {
            String msg = "客户管理-新增客户-修改";
            SysTools.saveLog(request, msg);
        }
        return result;
    };

    /**
     * Description :获得当前用户所在的省和市
     * 
     * @param
     * @return 省、市
     * @author ry
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomergetproandcity.do", method = { RequestMethod.POST })
    @ResponseBody
    public String getUserProAndCity(HttpServletRequest request)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        String pro = "";
        String city = "";
        if (user.getUser_province() != null)
        {
            pro = user.getUser_province();
        }
        if (user.getUser_city() != null)
        {
            city = user.getUser_city();
        }
        result = pro + "@@@@" + city;
        return result;
    }

    /**
     * Description :实现删除功能 软删除 客户管理--新增客户
     */
    @RequestMapping(value = "/cusmanage/wmscuscustomerinfofordelete.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doDelete(HttpServletRequest request, Integer wms_cus_customer_id)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        try
        {
            result = wmscuscustomerheadService.deleteByPk(wms_cus_customer_id);
        }
        catch (Exception e)
        {
            log.error("error", e);
            result = "error";
        }
        if ("success".equals(result))
        {
            String msg = "客户管理-新增客户-删除";
            SysTools.saveLog(request, msg);
        }
        return result;
    };
    
    
    
    /***
     * 客户信息保存
     * @author Administrator
     */
    @RequestMapping(value = "/cusmanage/wmsCustomerSave.do", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> wmsCustomerSave(HttpServletRequest request, WmsCusCustomerHeadSearchBeanVO queryInfo) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        // 返回信息
        String result = "success";
        HttpSession session = request.getSession();

        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION); // 获取登陆人信息
        try {
            int wms_cre_credit_line_customer_change_head_id = wmscuscustomerheadService.wmsCustomerSave(queryInfo, user);
            resMap.put("wms_cre_credit_line_customer_change_head_id", wms_cre_credit_line_customer_change_head_id);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        resMap.put("result", result);
        return resMap;
    };
    
    
    
}