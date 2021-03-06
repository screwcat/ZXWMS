package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSalaryPreItemDao;
import com.zx.emanage.inve.persist.WmsInveSalaryPreItemHisDao;
import com.zx.emanage.inve.persist.WmsInveSalaryPreTotalDao;
import com.zx.emanage.inve.service.IWmsInveSalaryPreItemService;
import com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveSalaryPreItemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItem;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItemHis;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvesalarypreitemService")
public class WmsInveSalaryPreItemServiceImpl implements IWmsInveSalaryPreItemService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveSalaryPreItemServiceImpl.class);

    @Autowired
    private WmsInveSalaryPreItemDao wmsinvesalarypreitemDao;

    @Autowired
    private WmsInveSalaryPreTotalDao wmsInveSalaryPreTotalDao;

    @Autowired
    private WmsInveSalaryPreItemHisDao wmsInveSalaryPreItemHisDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private IWmsInveSalaryPreTotalService wmsInveSalaryPreTotalService;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvesalarypreitemDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveSalaryPreItemSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvesalarypreitemDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinvesalarypreitemDao.findCount(paramMap), list);
        return bean.getGridData();
    }

    @Override
    public WmsInveSalaryPreItem getInfoByPK(Integer wms_inve_salary_pre_item_id)
    {
        return wmsinvesalarypreitemDao.get(wms_inve_salary_pre_item_id);
    }

    @Override
    @Transactional
    public String save(WmsInveSalaryPreItem bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvesalarypreitemDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveSalaryPreItem bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvesalarypreitemDao.update(bean); // update a record replace
                                                    // columns, nonsupport null
                                                    // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new WmsInveSalaryPreItem() include check-params
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveSalaryPreItem> getListByEntity(WmsInveSalaryPreItem queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveSalaryPreItem> beanList = wmsinvesalarypreitemDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * @Title: getPerformanceSalaryPreItemsByDeptId
     * @Description: 根据static_month 和部门id 获取工资前提配置子表信息
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月11日 上午10:03:38
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreItemService#getPerformanceSalaryPreItemsByDeptId(com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO)
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getPerformanceSalaryPreItemsByDeptId(WmsInveSalaryPreItemSearchBeanVO queryInfo)
    {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = wmsinvesalarypreitemDao.getPerformanceSalaryPreItemsByDeptId(queryInfo);
        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: updatePerformanceSalaryPreItemsById
     * @Description: 根据主键批量更新工资前提配置表item表（更新是否开资和绩效工资）
     * @param wmsInveSalaryPreItemList
     * @return 
     * @author: Administrator
     * @time:2017年1月11日 下午2:09:09
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreItemService#updatePerformanceSalaryPreItemsById(java.util.List)
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    @Override
    @Transactional
    public String updatePerformanceSalaryPreItemsById(String gridData, UserBean user, String wms_inve_salary_pre_total_id, String create_user_id, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO)
    {
        String resStr = "success";
        try
        {
            Map<String, Map> map = JsonUtil.jsonStringToBean(gridData, Map.class);

            List<WmsInveSalaryPreItem> wmsInveSalaryPreItemList = new ArrayList<WmsInveSalaryPreItem>();

            WmsInveSalaryPreItem wmsInveSalaryPreItem = null;

            for (Map.Entry<String, Map> entry : map.entrySet())
            {
                wmsInveSalaryPreItem = new WmsInveSalaryPreItem();

                wmsInveSalaryPreItem.setWms_inve_salary_pre_item_id(Integer.parseInt(entry.getKey()));
                if (user.getPostName().endsWith("部门经理"))
                {
                    wmsInveSalaryPreItem.setIs_pay_department(entry.getValue().get("is_pay").toString());
                    if (entry.getValue().get("performance_salary") != null && !"".equals(entry.getValue().get("performance_salary").toString()))
                    {
                        wmsInveSalaryPreItem.setPerformance_salary_department(new BigDecimal(entry.getValue().get("performance_salary").toString()));
                    }
                }
                else if (user.getPostName().endsWith("副总经理") || user.getPostName().endsWith("中心分公司总经理"))
                {
                    wmsInveSalaryPreItem.setIs_pay_vice_general(entry.getValue().get("is_pay").toString());
                    if (entry.getValue().get("performance_salary") != null && !"".equals(entry.getValue().get("performance_salary").toString()))
                    {
                        wmsInveSalaryPreItem.setPerformance_salary_vice_general(new BigDecimal(entry.getValue().get("performance_salary").toString()));
                    }
                }
                wmsInveSalaryPreItem.setLast_update_user_id(user.getUserId());
                wmsInveSalaryPreItemList.add(wmsInveSalaryPreItem);
            }
            if (wmsInveSalaryPreItemList.size() > 0)
            {
                wmsinvesalarypreitemDao.updatePerformanceSalaryPreItemsById(wmsInveSalaryPreItemList);
            }
            // 根据dept_id和static_month查询对应部门的开资人数
            wmsInveSalaryPreTotalVO.setLast_update_user_id(user.getUserId());
            List<WmsInveSalaryPreTotal> wmsInveSalaryPreTotalList = wmsinvesalarypreitemDao.getDeptPayNum(wmsInveSalaryPreTotalVO);
            // 根据dept_id和static_month更新对应部门的开资人数
            if (wmsInveSalaryPreTotalList.size() > 0)
            {
                wmsInveSalaryPreTotalDao.updatePayNumByDeptId(wmsInveSalaryPreTotalList);
            }

            // 判断单据状态是否为空并且判断单据状态为待修订状态则执行重新提交申请流程否则启动新的流程
            if (wmsInveSalaryPreTotalVO.getData_status_code() != null && !"".equals(wmsInveSalaryPreTotalVO.getData_status_code()) && "5".equals(wmsInveSalaryPreTotalVO.getData_status_code()))
            {
                wmsInveSalaryPreTotalService.reSubmitSalarySetProcess(user.getUserId().toString(), wms_inve_salary_pre_total_id, create_user_id);
            }
            else
            {
                // 开启业绩工资设定流程
                wmsInveWorkFlowService.startSalarSetProcess(user.getUserId().toString(), wms_inve_salary_pre_total_id, create_user_id);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return resStr;
    }

    /**
     * @Title: approvePerformanceSalaryPre
     * @Description:工资前提配置审批操作
     * @param gridData
     * @param vo
     * @return 
     * @author: Administrator
     * @time:2017年1月12日 下午6:23:40
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreItemService#approvePerformanceSalaryPre(java.lang.String, com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO)
     * history:
     * 1、2017年1月12日 Administrator 创建方法
    */
    @Override
    @Transactional
    public String approvePerformanceSalaryPre(String gridData, WmsInveSalarySetWorkFlowVO vo, UserBean user, WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO)
    {
        String resStr = "success";
        // 总审批不通过 清空副总项绩效工资传参map
        Map<String, Object> paramMap = new HashMap<String, Object>();

        Map<String, Map> map = JsonUtil.jsonStringToBean(gridData, Map.class);
        List<WmsInveSalaryPreItem> wmsInveSalaryPreItemList = new ArrayList<WmsInveSalaryPreItem>();
        List<WmsInveSalaryPreItemHis> wmsInveSalaryPreItemHisList = new ArrayList<WmsInveSalaryPreItemHis>();

        WmsInveSalaryPreItem wmsInveSalaryPreItem = null;
        WmsInveSalaryPreItemHis wmsInveSalaryPreItemHis = null;

        for (Map.Entry<String, Map> entry : map.entrySet())
        {
            wmsInveSalaryPreItem = new WmsInveSalaryPreItem();
            wmsInveSalaryPreItemHis = new WmsInveSalaryPreItemHis();

            wmsInveSalaryPreItem.setWms_inve_salary_pre_item_id(Integer.parseInt(entry.getKey()));

            wmsInveSalaryPreItem.setLast_update_user_id(user.getUserId());
            wmsInveSalaryPreItemList.add(wmsInveSalaryPreItem);
            // 审批人是副总或中分总 更新副总列对应的绩效工资
            if (user.getPostName().endsWith("副总经理") || user.getPostName().endsWith("中心分公司总经理"))
            {
                wmsInveSalaryPreItem.setIs_pay_vice_general(entry.getValue().get("is_pay").toString());
                if (entry.getValue().get("performance_salary") != null && !"".equals(entry.getValue().get("performance_salary").toString()))
                {
                    wmsInveSalaryPreItem.setPerformance_salary_vice_general(new BigDecimal(entry.getValue().get("performance_salary").toString()));
                }
            }

            // 审批人是总 更新副总列对应的绩效工资
            else if (user.getPostName().endsWith("总经理"))
            {
                wmsInveSalaryPreItem.setIs_pay_general(entry.getValue().get("is_pay").toString());
                if (entry.getValue().get("performance_salary") != null && !"".equals(entry.getValue().get("performance_salary").toString()))
                {
                    wmsInveSalaryPreItem.setPerformance_salary_general(new BigDecimal(entry.getValue().get("performance_salary").toString()));
                }
            }

            wmsInveSalaryPreItemHis.setStatics_month(wmsInveSalaryPreTotalVO.getStatics_month());
            wmsInveSalaryPreItemHis.setPersonnel_id(Integer.parseInt(entry.getValue().get("personnel_id").toString()));
            wmsInveSalaryPreItemHis.setPerformance_salary(new BigDecimal(entry.getValue().get("performance_salary").toString()));
            wmsInveSalaryPreItemHis.setIs_pay(entry.getValue().get("is_pay").toString());
            wmsInveSalaryPreItemHis.setCreate_user_id(user.getUserId());
            wmsInveSalaryPreItemHisList.add(wmsInveSalaryPreItemHis);
        }

        // 总经理审批不通过 并且单据是部门经理提交的需要把副总列清空(单据是否是部门经理提交 在sql中判断)
        if ("false".endsWith(vo.getPass()) && !user.getPostName().endsWith("副总经理") && user.getPostName().endsWith("总经理"))
        {
            paramMap.put("statics_month", wmsInveSalaryPreTotalVO.getStatics_month());
            paramMap.put("dept_ids", wmsInveSalaryPreTotalVO.getDept_ids());
            paramMap.put("last_update_user_id", user.getUserId());
            wmsinvesalarypreitemDao.updateViceGeneralPerformanceSalary(paramMap);
        }
        else
        {
            // 副总/总审批通过 并且工资设定有修改项 需要去更新工资前提配置item表
            // 审批不通过什么也不做
            if ("true".endsWith(vo.getPass()))
            {
                wmsinvesalarypreitemDao.updatePerformanceSalaryPreItemsById(wmsInveSalaryPreItemList);
            }
        }

        // 根据dept_id和static_month查询对应部门的开资人数
        wmsInveSalaryPreTotalVO.setLast_update_user_id(user.getUserId());
        List<WmsInveSalaryPreTotal> wmsInveSalaryPreTotalList = wmsinvesalarypreitemDao.getDeptPayNum(wmsInveSalaryPreTotalVO);

        // 根据dept_id和static_month更新对应部门的开资人数
        if (wmsInveSalaryPreTotalList.size() > 0)
        {
            wmsInveSalaryPreTotalDao.updatePayNumByDeptId(wmsInveSalaryPreTotalList);
        }

        // 批量保存历史记录
        if (wmsInveSalaryPreItemHisList.size() > 0)
        {
            wmsInveSalaryPreItemHisDao.savePerformanceSalaryPreItemHis(wmsInveSalaryPreItemHisList);
        }
        // 审批流程
        wmsInveSalaryPreTotalService.inveSalarySetAuditInfos(vo, user);

        return resStr;
    }

    /**
     * @Title: approvePerformanceSalaryPreMoa
      * @Description: 手持审批绩效工资
     * @param advice(审批意见)
     * @param pass(审核结果 同意0、驳回1)
     * @param dept_ids部门id集合
     * @author: zhangyunfei
     * @time:2017年4月24日 上午10:47:57
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#approvePerformanceSalaryPreMoa(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年4月24日 Administrator 创建方法
    */
    @Override
    @Transactional
    public String approvePerformanceSalaryPreMoa(String advice, String pass, String personnel_shortcode, String dept_ids)
    {
        String resStr = "success";
        // 总审批不通过 清空副总项绩效工资传参map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortcode);
        // 根据moa传过来的personnel_shortcode 查询人员信息
        List<PmPersonnel> pList = pmPersonnelDao.getListByEntity(pmPersonnel);
        try
        {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String statics_month = sdf.format(c.getTime());

            // 根据月份和部门集合 查询当月该部门的工资前提配置子表集合
            WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO = new WmsInveSalaryPreTotalSearchBeanVO();
            wmsInveSalaryPreTotalVO.setStatics_month(statics_month);
            wmsInveSalaryPreTotalVO.setDept_ids(dept_ids);
            List<WmsInveSalaryPreItem> wmsInveSalaryPreItemList = wmsinvesalarypreitemDao.getWmsInveSalaryPreItemByDeptIdsAndMonth(wmsInveSalaryPreTotalVO);
            // 工资前提配置历史表
            WmsInveSalaryPreItemHis wmsInveSalaryPreItemHis = null;
            List<WmsInveSalaryPreItemHis> wmsInveSalaryPreItemHisList = new ArrayList<WmsInveSalaryPreItemHis>();
            // 遍历工资前提配置子表 封装审批历史表实体类
            for (WmsInveSalaryPreItem item : wmsInveSalaryPreItemList)
            {
                wmsInveSalaryPreItemHis = new WmsInveSalaryPreItemHis();
                // 封装审批历史表数据
                wmsInveSalaryPreItemHis.setStatics_month(wmsInveSalaryPreTotalVO.getStatics_month());
                wmsInveSalaryPreItemHis.setPersonnel_id(item.getPersonnel_id());
                wmsInveSalaryPreItemHis.setPerformance_salary(item.getPerformance_salary_general());
                wmsInveSalaryPreItemHis.setIs_pay(item.getIs_pay_general());
                wmsInveSalaryPreItemHis.setCreate_user_id(pList.get(0).getPersonnel_id());
                wmsInveSalaryPreItemHisList.add(wmsInveSalaryPreItemHis);
            }

            // 封装更新工资前提配置子表所需要的参数
            paramMap.put("statics_month", statics_month);
            paramMap.put("dept_ids", dept_ids);
            paramMap.put("last_update_user_id", pList.get(0).getPersonnel_id());
            // 总经理审批不通过 并且单据是部门经理提交的需要把副总列清空(单据是否是部门经理提交 在sql中判断)
            if ("1".endsWith(pass) && !pList.get(0).getPersonnel_postname().endsWith("副总经理") && pList.get(0).getPersonnel_postname().endsWith("总经理"))
            {
                wmsinvesalarypreitemDao.updateViceGeneralPerformanceSalary(paramMap);
            }
            else
            {
                // 副总/总审批通过 0 并且工资设定有修改项 需要去更新工资前提配置item表
                // 审批不通过什么也不做
                if ("0".endsWith(pass))
                {
                    // 审批人是副总或中分总 更新副总列对应的绩效工资
                    if (pList.get(0).getPersonnel_postname().endsWith("副总经理") || pList.get(0).getPersonnel_postname().endsWith("中心分公司总经理"))
                    {
                        paramMap.put("is_pay_vice_general", 1);
                    }
                    // 审批人是总 更新副总列对应的绩效工资 is_pay_general
                    else if (pList.get(0).getPersonnel_postname().endsWith("总经理"))
                    {
                        paramMap.put("is_pay_general", 1);
                    }
                    wmsinvesalarypreitemDao.updateWmsInveSalaryPreItemByDeptsAndMonth(paramMap);
                }

            }

            // 根据dept_id和static_month查询对应部门的开资人数
            wmsInveSalaryPreTotalVO.setLast_update_user_id(pList.get(0).getPersonnel_id());
            List<WmsInveSalaryPreTotal> wmsInveSalaryPreTotalList = wmsinvesalarypreitemDao.getDeptPayNum(wmsInveSalaryPreTotalVO);

            // 根据dept_id和static_month更新对应部门的开资人数
            if (wmsInveSalaryPreTotalList.size() > 0)
            {
                wmsInveSalaryPreTotalDao.updatePayNumByDeptId(wmsInveSalaryPreTotalList);
            }

            // 批量保存历史记录
            if (wmsInveSalaryPreItemHisList.size() > 0)
            {
                wmsInveSalaryPreItemHisDao.savePerformanceSalaryPreItemHis(wmsInveSalaryPreItemHisList);
            }

            // 封装流程用的User对象
            UserBean user = new UserBean();
            user.setUserId(pList.get(0).getPersonnel_id());
            user.setDeptId(pList.get(0).getPersonnel_deptid());
            user.setPostName(pList.get(0).getPersonnel_postname());
            // 流程实例Key
            WmsInveSalarySetWorkFlowVO wmsInveSalaryVO = new WmsInveSalarySetWorkFlowVO();
            String wmsInveSalaryPreTotalIds = wmsInveSalaryPreTotalDao.getPerformanceSalaryPreTotalIds(wmsInveSalaryPreTotalVO);
            // 赎回单据ID
            wmsInveSalaryVO.setBusinessId(wmsInveSalaryPreTotalIds);
            wmsInveSalaryVO.setAdvice(advice);
            // 审批通过
            if ("0".equals(pass))
            {
                wmsInveSalaryVO.setPass("true");
            }
            else
            {
                wmsInveSalaryVO.setPass("false");

            }
            // 审批流程
            wmsInveSalaryPreTotalService.inveSalarySetAuditInfos(wmsInveSalaryVO, user);
        }
        catch (Exception e)
        {
            return "error";
        }
        return resStr;

    }
}
