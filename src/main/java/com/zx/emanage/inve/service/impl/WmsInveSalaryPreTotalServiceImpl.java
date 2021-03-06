package com.zx.emanage.inve.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveSalaryPreItemDao;
import com.zx.emanage.inve.persist.WmsInveSalaryPreTotalDao;
import com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.emanage.workflow.util.WorkflowHistoryInfoHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalaryPreTotalServiceImpl
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年1月7日
 * @version V1.0
 * history:
 * 1、2017年1月7日 DongHao 创建文件
 */
@Service("wmsinvesalarypretotalService")
public class WmsInveSalaryPreTotalServiceImpl implements IWmsInveSalaryPreTotalService {
	private static Logger log = LoggerFactory.getLogger(WmsInveSalaryPreTotalServiceImpl.class);

	@Autowired
	private WmsInveSalaryPreTotalDao wmsinvesalarypretotalDao;

    @Autowired
    private WmsInveSalaryPreItemDao wmsInveSalaryPreItemDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private WmsInveSalaryPreTotalDao wmsInveSalaryPreTotalDao;

    @Autowired
    private IWmsInveWorkFlowService aInveWorkFlowService;



	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvesalarypretotalDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveSalaryPreTotalSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvesalarypretotalDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvesalarypretotalDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveSalaryPreTotal getInfoByPK(Integer wms_inve_salary_pre_total_id) {
		return wmsinvesalarypretotalDao.get(wms_inve_salary_pre_total_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveSalaryPreTotal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalarypretotalDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveSalaryPreTotal bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvesalarypretotalDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveSalaryPreTotal() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveSalaryPreTotal> getListByEntity(WmsInveSalaryPreTotal queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveSalaryPreTotal> beanList = wmsinvesalarypretotalDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: getTeamLis
     * @Description: 获取所属团队信息
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:19:30
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getTeamLis()
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getTeamLis(UserBean user, String menu_id)
    {
        // 定义参数集合map
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        
        paramsMap.put("user_id", user.getUserId());

        // 获取人员信息
        if (user.getUserId() == 184)
        {
            paramsMap.put("super_user", 1);
        }

        // 获取角色列表
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());

        for (String role : roleList)
        {

            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramsMap.put("salesman_id", user.getUserId());
                paramsMap.put("deptIds_menu", menu_id);
                paramsMap.put("deptIds_user_id", user.getUserId());
                paramsMap.put("super_user", 0);
            }

        }

        List<Map<String, Object>> list = wmsinvesalarypretotalDao.getTeamLis(paramsMap);

        if (list == null)
        {
            list = new ArrayList<Map<String, Object>>();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dept_id", -1);
        map.put("dept_name", "请选择");
        list.add(0, map);

        return list;
    }

    /**
     * @Title: getPerformanceSalarySettingInfos
     * @Description: 获取工资设定单列表信息
     * @param user
     * @param queryInfo
     * @return 
     * @author: DongHao
     * @time:2017年1月7日 下午5:54:32
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getPerformanceSalarySettingInfos(com.zx.sframe.util.vo.UserBean, com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO)
     * history:
     * 1、2017年1月7日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getPerformanceSalarySettingInfos(UserBean user, WmsInveSalaryPreTotalSearchBeanVO queryInfo)
    {
        // 定义参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 判断统计月份为不空
        if (queryInfo.getStatics_month() != null && !"".equals(queryInfo.getStatics_month()))
        {
            paramsMap.put("statics_month", queryInfo.getStatics_month());
        }

        // 判断单据状态不为空
        if (queryInfo.getData_status() != null)
        {
            paramsMap.put("data_status", queryInfo.getData_status());
        }

        // 判断部门id不为空
        if (queryInfo.getDept_id() != null && queryInfo.getDept_id() != -1)
        {
            paramsMap.put("dept_id", queryInfo.getDept_id());
        }

        paramsMap.put("sortname", queryInfo.getSortname());
        paramsMap.put("sortorder", queryInfo.getSortorder());
        paramsMap.put("pagesize", queryInfo.getPagesize());
        paramsMap.put("offset", queryInfo.getOffset());
        paramsMap.put("user_id", user.getUserId());

        List<Map<String, Object>> list = wmsinvesalarypretotalDao.getPerformanceSalarySettingInfos(paramsMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinvesalarypretotalDao.getPerformanceSalarySettingCount(paramsMap), list);

        return bean.getGridData();
    }


    /**
     * @Title: getPerformanceSalaryAuditInfos
     * @Description: 获取待审核的单据
     * @param user
     * @param queryInfo
     * @return 
     * @author: DongHao
     * @time:2017年1月9日 上午9:13:55
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getPerformanceSalaryAuditInfos(com.zx.sframe.util.vo.UserBean, com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO)
     * history:
     * 1、2017年1月9日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getPerformanceSalaryAuditInfos(UserBean user, WmsInveSalaryPreTotalSearchBeanVO queryInfo)
    {
        String invekdy = "3";// 待总审批

        // 区分当前登录人是否是副总或者总
        Map<String, Object> pmMap = wmsinvesalarypretotalDao.getPersonnelByPersonnelId(user.getUserId().toString());
        if (pmMap != null && pmMap.size() > 0)
        {
            if (Integer.parseInt(pmMap.get("dept_level").toString()) == 4 || Integer.parseInt(pmMap.get("dept_level").toString()) == 5)
            {
                invekdy = "2";
            }
        }

        // 参数集合
        // 获取工作流
        Map<String, Object> paramsMap = wmsInveWorkFlowService.getSalarSetWorkFlowToIdList(user.getUserId().toString(), invekdy);

        if (queryInfo.getStatics_month() != null && !"".equals(queryInfo.getStatics_month()))
        {
            paramsMap.put("statics_month", queryInfo.getStatics_month());
        }

        if (queryInfo.getData_status() != null)
        {
            paramsMap.put("data_status", queryInfo.getData_status());
        }

        if (queryInfo.getDept_id() != null && queryInfo.getDept_id() != -1)
        {
            paramsMap.put("dept_id", queryInfo.getDept_id());
        }

        if (queryInfo.getDept_manager_name() != null)
        {
            paramsMap.put("dept_manager_name", queryInfo.getDept_manager_name());
        }

        paramsMap.put("sortname", queryInfo.getSortname());
        paramsMap.put("sortorder", queryInfo.getSortorder());
        paramsMap.put("pagesize", queryInfo.getPagesize());
        paramsMap.put("offset", queryInfo.getOffset());

        List<Map<String, Object>> list = wmsinvesalarypretotalDao.getPerformanceSalaryAuditInfos(paramsMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinvesalarypretotalDao.getPerformanceSalaryAuditCount(paramsMap), list);
        return bean.getGridData();
    }

    /**
     * @Title: getDataStatus
     * @Description: 获取工资设定状态
     * @param wms_sys_dict_id
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年1月12日 上午10:50:22
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getDataStatus(java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月12日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getDataStatus(String wms_sys_dict_id, UserBean user)
    {
        // 定义查询条件map集合参数
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("wms_sys_dict_id", wms_sys_dict_id);

        List<Map<String, Object>> list = wmsinvesalarypretotalDao.getDataStatus(paramsMap);

        for (Map<String, Object> map : list)
        {
            if ("2".equals(map.get("value_code").toString()))
            {
                map.put("value_meaning", "审批中");
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("value_code", -1);
        map.put("value_meaning", "请选择");
        list.add(0, map);
        return list;
    }

    /**
     * @Title: inveSalarySetAuditInfos
     * @Description: 执行审核流程操作
     * @param vo 流程对象信息
     * @param user 当前登录用户信息
     * @return 返回map信息提示
     * @author: DongHao
     * @time:2017年1月12日 下午3:21:15
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#inveSalarySetAuditInfos(com.zx.emanage.inve.vo.WmsInveSalarySetWorkFlowVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月12日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> inveSalarySetAuditInfos(WmsInveSalarySetWorkFlowVO vo, UserBean user)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        Map<String, Object> personnelMap = wmsinvesalarypretotalDao.getPersonnelByPersonnelId(user.getUserId().toString());
        if (Integer.parseInt(personnelMap.get("dept_level").toString()) == 4 || Integer.parseInt(personnelMap.get("dept_level").toString()) == 5)
        {
            vo.setDebtkey("1");
        }
        else
        {
            vo.setDebtkey("2");
        }
        vo.setUserID(user.getUserId().toString());
        wmsInveWorkFlowService.inveSalarySetAudit(vo);

        resultMap.put("success", "审核成功!");

        return resultMap;
    }

    /**
     * @Title: getJobTime
     * @Description: 判断当前系统时间提交绩效工资设定单是否允许
     * @return 
     * @author: DongHao
     * @time:2017年1月17日 下午5:09:13
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getJobTime()
     * history:
     * 1、2017年1月17日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getJobTime()
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String resultStr = "success";

        Map<String, Object> jobTimeMap = wmsInveSalaryPreTotalDao.getWmsInveJobTimeToEndOfDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDateStr = format.format(new Date());
        String jobTimeStr = jobTimeMap.get("job_date_str").toString() + " 00:00:00";

        try
        {
            Date jobTime = format.parse(jobTimeStr);
            Date nowDate = format.parse(nowDateStr);

            if (nowDate.compareTo(jobTime) > 0)
            {
                resultStr = "error";
            }

        }
        catch (ParseException e)
        {
            resultStr = "error";
        }

        resultMap.put("result", resultStr);

        return resultMap;
    }

    /**
     * @Title: getGeneralAdvice
     * @Description: 根据绩效工资设定总表获取待修订状态的审批意见
     * @param wms_inve_salary_pre_total_id
     * @return 
     * @author: DongHao
     * @time:2017年1月4日 下午2:54:35
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getGeneralAdvice(java.lang.String)
     * history:
     * 1、2017年1月4日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getGeneralAdvice(String wms_inve_salary_pre_total_id)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_salary_pre_total_id", wms_inve_salary_pre_total_id);

        Map<String, Object> resultMap = wmsInveSalaryPreTotalDao.getGeneralAdvice(paramsMap);
        return resultMap;
    }
    
    /**
     * @Title: getSalaryPendingApproval
     * @Description: 获取待我审批的绩效工资单据
     * @param personnel_id 当前登录MOA系统的人员
     * @return 返回待我审批的单据列表
     * @author: DongHao
     * @time:2017年4月10日 上午2:14:48
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getSalaryPendingApproval(java.lang.Integer)
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getSalaryPendingApproval(Integer personnel_id)
    {
        String invekdy = "3";// 待总审批

        // 区分当前登录人是否是副总或者总
        Map<String, Object> pmMap = wmsinvesalarypretotalDao.getPersonnelByPersonnelId(personnel_id.toString());
        if (pmMap != null && pmMap.size() > 0)
        {
            if (Integer.parseInt(pmMap.get("dept_level").toString()) == 4 || Integer.parseInt(pmMap.get("dept_level").toString()) == 5)
            {
                invekdy = "2";
            }
        }
        
        // 获取工作流
        Map<String, Object> paramsMap = wmsInveWorkFlowService.getSalarSetWorkFlowToIdList(personnel_id.toString(), invekdy);
        
        //获取待我审批的单据
        List<Map<String, Object>> res_list = wmsinvesalarypretotalDao.getSalaryPendingApproval(paramsMap);
        
        return res_list;
    }
    
    /**
     * @Title: getSalaryBusinessInfos
     * @Description: 根据查询条件获取绩效工资的相关业务单据信息
     * @param paramMap 查询条件参数map集合
     * @return 返回绩效工资的相关单据信息集合
     * @author: DongHao
     * @time:2017年4月10日 上午7:47:41
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getSalaryBusinessInfos(java.util.Map)
     * history:
     * 1、2017年4月10日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getSalaryBusinessInfos(Map<String, Object> paramMap)
    {
        
        List<Map<String, Object>> res_lis = wmsinvesalarypretotalDao.getSalaryBusinessInfos(paramMap);
        
        return res_lis;
    }

    /**
     * @Title: getSalarySetProcessInfos
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_salary_pre_total_id
     * @return 
     * @author: DongHao
     * @time:2017年5月1日 下午10:25:14
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getSalarySetProcessInfos(java.lang.Integer)
     * history:
     * 1、2017年5月1日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSalarySetProcessInfos(Integer wms_inve_salary_pre_total_id)
    {
        
        return wmsInveWorkFlowService.getSalarySetProcessInfos(WorkflowConstantHelp.INVESALARYSET, wms_inve_salary_pre_total_id);
    }

    /**
     * @Title: getPerformanceSalaryApproveInfosMoa
     * @Description: 获取绩效工资审核单据详情接口
     * @param wms_inve_salary_pre_total_ids
     * @param bill_type_code
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月19日 上午11:08:55
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#getPerformanceSalaryApproveInfosMoa(java.lang.String, java.lang.String)
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    @Override
    public List<Map<String, Object>> getPerformanceSalaryApproveInfosMoa(String wms_inve_salary_pre_total_ids, String bill_attr)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        // 定义参数集合map
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        if (!(wms_inve_salary_pre_total_ids.equals("")))
        {
            for (String id : wms_inve_salary_pre_total_ids.split(","))
            {
                
                Map<String, Object> map = wmsinvesalarypretotalDao.getPerformanceSalaryInfoByIdMoa(Integer.valueOf(id));
                paramsMap.put("wms_inve_salary_pre_total_id", Integer.valueOf(id));
                paramsMap.put("statics_month", map.get("statics_month").toString());
                // 绩效工资详细信息list
                List<Map<String, Object>> pslist = wmsinvesalarypretotalDao.getPerformanceSalaryDetailInfoByIdMoa(paramsMap);
                map.put("team_pay_detailInfo", pslist);
                // bill_attr等于0 单据为只读 需要显示流程信息
                if ("0".equals(bill_attr))
                {
                    // 查询流程历程
                    Map<String, Object> reMap = aInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.INVESALARYSET, id);
                    List<WorkflowHistoryInfoHelp> works = (List<WorkflowHistoryInfoHelp>) reMap.get("Rows");
                    // 设置流程详细信息
                    setWorkFlowDetailInfo(works);
                    // 拼接所有的流程节点
                    getWorkFlowListByStatus(map, works);
                    map.put("Rows", works);
                }
                list.add(map);
            }
        }
        
        return list;
    }

    /**
     * @Title: setWorkFlowDetailInfo
     * @Description: 设置流程详细信息
     * @param works 
     * @author: zhangyunfei
     * @time:2017年4月4日 下午5:29:55
     * history:
     * 1、2017年4月4日 Administrator 创建方法
     */
    private void setWorkFlowDetailInfo(List<WorkflowHistoryInfoHelp> works)
    {
        Date d1 = null;
        Date d2 = null;
        int betweenDays = 0;
        int hours = 0;
        int minutes = 0;
        for (int i = 0; i < works.size(); i++)
        {
            // i=0 该单据状态为提交状态 没有处理单据的时间间隔
            if (i != 0)
            {
                d1 = DateUtil.strDate(works.get(i).getApproveTime(), "yyyy-MM-dd HH:mm:ss");
                d2 = DateUtil.strDate(works.get(i - 1).getApproveTime(), "yyyy-MM-dd HH:mm:ss");
                long seconds = DateUtil.getBetweenSecondsInt(d1, d2);
                betweenDays = (int) Math.abs(seconds / (1000 * 60 * 60 * 24));
                hours = (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                // 不足一分钟按一分钟计算
                minutes = (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60)) == 0 ? 1 : (int) Math.abs((seconds % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60));
                // 拼接审核处理过程花费的时间
                works.get(i).setApproveTimeInterval(betweenDays + "天" + hours + "小时" + minutes + "分钟");
                // 单据已完成
                if (!("重新修订".equals(works.get(i).getTaskName())))
                {
                    works.get(i).setTaskName("审批");
                }

                if ("重新修订".equals(works.get(i).getTaskName()))
                {
                    works.get(i).setTaskName("修订");
                }
            }
            // 单纯的修改第一个流程的taskName
            if (i == 0)
            {
                works.get(0).setTaskName("提交");
            }

            if ("审批通过".equals(works.get(i).getApproveResult()) || "已提交".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(1);
            }
            else if ("待审核".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(0);
            }
            else if ("审批不通过".equals(works.get(i).getApproveResult()))
            {
                works.get(i).setApprove_status(2);
            }
            // 拼接审批人员+短工号
            works.get(i).setApprovers(works.get(i).getApprovers() + " " + works.get(i).getPersonnel_shortCode());
        }
    }

    /**
     * 
     * @Title: getWorkFlowListByStatus
     * @Description: 封装所有的流程节点
     * @param map
     * @param works 
     * @author: zhangyunfei
     * @time:2017年5月2日 下午4:31:19
     * history:
     * 1、2017年5月2日 Administrator 创建方法
     */
    private void getWorkFlowListByStatus(Map<String, Object> map, List<WorkflowHistoryInfoHelp> works)
    {
        // data_status 2待副总中分总审批 6待总审批
        if (Integer.parseInt(map.get("data_status").toString()) == 2)
        {
            // 中分总 / 副总
            if (map.get("bel_center_manager_name") != null)
            {
                works.add(setWorkFlow(map.get("bel_center_manager_name") + "", map.get("bel_center_manager_dept_name") + "", map.get("bel_center_manager_postName") + "", "审批"));
            }
            else
            {
                works.add(setWorkFlow(map.get("bel_vice_general_manager_name") + "", map.get("bel_vice_general_manager_dept_name") + "", map.get("bel_vice_general_manager_postName") + "", "审批"));
            }
            // 总
            works.add(setWorkFlow(map.get("bel_general_manager_name") + "", map.get("bel_general_manager_dept_name") + "", map.get("bel_general_manager_postName") + "", "审批"));

        }
        if (Integer.parseInt(map.get("data_status").toString()) == 6)
        {
            // 总
            works.add(setWorkFlow(map.get("bel_general_manager_name") + "", map.get("bel_general_manager_dept_name") + "", map.get("bel_general_manager_postName") + "", "审批"));
        }
    }

    /**
     * @Title: setWorkFlow
     * @Description: 封装未到达的流程基本信息
     * @param approvers
     * @param approversName
     * @param personnel_postName
     * @param taskName
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月2日 下午4:32:46
     * history:
     * 1、2017年5月2日 Administrator 创建方法
     */
    private WorkflowHistoryInfoHelp setWorkFlow(String approvers, String approversName, String personnel_postName, String taskName)
    {
        WorkflowHistoryInfoHelp work = new WorkflowHistoryInfoHelp();
        work.setApprove_status(0);
        work.setApprovers(approvers);
        work.setPersonnel_deptName(approversName);
        work.setPersonnel_postName(personnel_postName);
        work.setTaskName(taskName);
        return work;
    }

    /**
     * @Title: reSubmitSalarySetProcess
     * @Description: 工资设定流程重新提交申请待修订状态的单据
     * @param userId 当前登录人id
     * @param wms_inve_salary_pre_total_id 工资设定业务id
     * @param create_user_id 创建人id
     * @author: DongHao
     * @time:2017年4月1日 下午9:09:08
     * @see com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService#reSubmitSalarySetProcess(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    @Override
    public void reSubmitSalarySetProcess(String userId, String wms_inve_salary_pre_total_id, String create_user_id)
    {
        //创建流程对象
        WmsInveSalarySetWorkFlowVO vo = new WmsInveSalarySetWorkFlowVO();
        
        vo.setPass("true");//设置执行流程的通过条件
        vo.setBusinessId(wms_inve_salary_pre_total_id);//设置流程业务id
        vo.setDebtkey("3");//设置执行流程为重新提交
        vo.setAdvice("重新提交");//设置流程审批意见
        vo.setUserID(userId);//设置当前登录人id
        
        //执行流程
        wmsInveWorkFlowService.inveSalarySetAudit(vo);
    }
}
