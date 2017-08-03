package com.zx.emanage.reportmanage.service.impl;
/**
 * 版权所有：版权所有(C) 2014，卓信财富
 * 文件名称: WmsCreCreditHeadController.java
 * 系统名称：
 * 模块名称：
 * 完成日期：
 * 作    者：
 * 内容摘要：  
 * 
 * 文件调用：
 * 修改记录：01
 * 修改时间：2014-11-27
 * 修 改 人:  hancd
 * 修改内容：增加能够删除信贷和房贷处于草稿状态下的单据 软删除
 * 关联BUG：
 * 修改方法：通过给定的主贷款ID和贷款标示cre_loan
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.reportmanage.persist.WmsHelpPlannersCustomerLoanRepaymentConditionsDao;
import com.zx.emanage.reportmanage.service.IWmsHelpPlannersCustomerLoanRepaymentConditionsService;
import com.zx.emanage.reportmanage.vo.WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.platform.syscontext.vo.GridDataBean;

/**
 * 实现获取客户还款情况信息ServiceImpl
 * @author hancd
 *
 */
@Service("wmshelpplannerscustomerloanrepaymentconditionsservice")
public class WmsHelpPlannersCustomerLoanRepaymentConditionsServiceImpl implements IWmsHelpPlannersCustomerLoanRepaymentConditionsService
{
    @Autowired
    private WmsHelpPlannersCustomerLoanRepaymentConditionsDao wmsHelpPlannersCustomerLoanRepaymentConditionsDao;
    @Autowired
    private SysDeptDao sysDeptDao;
    /**
     * 导出符合条件的客户还款情况统计信息数据
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //部门名称ID
        if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
        {
            paramMap.put("deptId", queryInfo.getDeptId());
        }
        //门店名称ID
        if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
        {
            paramMap.put("storesId", queryInfo.getStoresId());
        }
        //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
            paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
        }
        //业务员姓名以及编号
        if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
        {
            paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("Rows", wmsHelpPlannersCustomerLoanRepaymentConditionsDao.getListWithoutPaging(paramMap));
        return paramMap;
    }
    /**
     * 根据不同查询条件实现获取客户还款情况信息列表
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo)
    {
       Map<String, Object> paramMap = new HashMap<String, Object>();
       //部门名称ID
       if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
       {
           paramMap.put("deptId", queryInfo.getDeptId());
       }
       //门店名称ID
       if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
       {
           paramMap.put("storesId", queryInfo.getStoresId());
       }
       //业务组名称ID
       if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
           paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
       }
       //业务员姓名以及编号
       if(StringUtil.isNotBlank(queryInfo.getPersonnel_Code()))
       {
           paramMap.put("personnel_Code", SysTools.getSqlLikeParam(queryInfo.getPersonnel_Code()));
       }
       paramMap.put("sortname", queryInfo.getSortname());
       paramMap.put("sortorder", queryInfo.getSortorder());
       paramMap.put("pagesize", queryInfo.getPagesize());
       paramMap.put("offset", queryInfo.getOffset());
       List<Map<String, Object>> list = wmsHelpPlannersCustomerLoanRepaymentConditionsDao.search(paramMap);
       GridDataBean<Map<String,Object>>bean =new GridDataBean<Map<String,Object>>(queryInfo.getPage(),wmsHelpPlannersCustomerLoanRepaymentConditionsDao.findCount(paramMap),list);
       return bean.getGridData();
    }
    /**
     * Descrption:根据前台查询条件,进行显示系统提示消息
     * @param queryInfo
     * @author hancd
     */
    @Override
    public Map<String, Object> getDataViewList(WmsHelpPlannersCustomerLoanRepaymentConditionsSearchBeanVO queryInfo)
    {
        Map<String,Object> paramMap = new HashMap<>();
        //部门名称ID
        if(StringUtil.isNotBlank(queryInfo.getDeptId())&&!queryInfo.getDeptId().equals("-2"))
        {
            paramMap.put("deptId", queryInfo.getDeptId());
        }
        //门店名称ID
        if(StringUtil.isNotBlank(queryInfo.getStoresId())&&!queryInfo.getStoresId().equals("-2"))
        {
            paramMap.put("storesId", queryInfo.getStoresId());
        }
        //业务组名称ID
        if(StringUtil.isNotBlank(queryInfo.getBusinessgroupId())&& !queryInfo.getBusinessgroupId().equals("-2")){
            paramMap.put("businessgroupId", queryInfo.getBusinessgroupId());
        }
        Map<String,Object> list =wmsHelpPlannersCustomerLoanRepaymentConditionsDao.searchByDataView(paramMap);
        return list;
    }
}
