package com.zx.emanage.inve.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInvePhoneDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveSignedApplicationDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaPruductUserDao;
import com.zx.emanage.inve.service.IWmsInveClerkDataService;
import com.zx.emanage.inve.service.IWmsInvePhoneService;
import com.zx.emanage.inve.service.IWmsInveRedeemService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.service.ICrmCustomerInfoService;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.vo.WmsInveRedeemPrincipalDetailSearchBeanVO;
import com.zx.emanage.util.gen.vo.WmsInveRedeemVO;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInvePhoneServiceImpl
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2016年12月30日
 * @version V1.0
 * history:
 * 1、2016年12月30日 DongHao 创建文件
 */
@Service("wmsInvePhoneService")
public class WmsInvePhoneServiceImpl implements IWmsInvePhoneService
{

    @Autowired
    private WmsInvePhoneDao wmsInvePhoneDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;

    @Autowired
    private WmsInveTransaPruductUserDao wmsInveTransaPruductUserDao;// 上单产品限制表

    @Autowired
    private WmsInvePruductCategoryDao wmsinvepruductcategoryDao;// 理财产品表

    @Autowired
    private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;

    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;

    @Autowired
    private ICrmCustomerInfoService crmCustomerInfoService;

    @Autowired
    private WmsInveSignedApplicationDao wmsInveSignedApplicationDao;

    @Autowired
    private IWmsInveClerkDataService wmsInveClerkDataService;

    @Autowired
    private IWmsInveSignedApplicationService wmsInveSignedApplicationService;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

    @Autowired
    private IWmsInveTransaProtocolService wmsInveTransaProtocolService;

    @Autowired
    private WmsInveRedeemDao wmsInveRedeemDao;

    @Autowired
    private IWmsInveRedeemService wmsinveredeemService;

    /**
     * @Title: getSpecialMenuInfosMoa
     * @Description: 根据当前登录的用户id,获取app端特殊菜单项
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2016年12月30日 下午2:23:44
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getSpecialMenuInfosMoa(java.lang.String)
     * history:
     * 1、2016年12月30日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSpecialMenuInfosMoa(String personnel_id, String version)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("personnel_id", Integer.parseInt(personnel_id));
        if (StringUtil.isNotBlank(version))
        {
            map.put("sortname", "m.menu_sort");
        }
        else
        {
            map.put("sortname", "m.menu_remark,m.menu_sort");
        }

        List<Map<String, Object>> list = wmsInvePhoneDao.getSpecialMenuInfosMoa(map);
        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getSpecialTeamInfosMoa
     * @Description: 获取团队信息
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月1日 下午5:08:11
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getSpecialTeamInfosMoa(int, java.lang.String, java.lang.String)
     * history:
     * 1、2017年1月1日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSpecialTeamInfosMoa(int personnel_id, String dept_id, String menu_code)
    {
        // 定义返回的结果集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 根据人员id获取人员信息
        Map<String, Object> personnelMap = wmsInvePhoneDao.getPersonnelById(personnel_id);

        paramsMap.put("personnel_id", personnel_id);

        // 判断部门id是否为空,如果不为空则将部门id添加到部门id集合中便于查询
        if (dept_id != null && !"".equals(dept_id))
        {
            List<String> deptIds = new ArrayList<String>();
            String[] ids = dept_id.split(",");
            for (String id : ids)
            {
                deptIds.add(id);
            }
            paramsMap.put("dept_id", deptIds);
        }

        // 判断菜单编码是否为空
        if (menu_code != null && !"".equals(menu_code))
        {
            paramsMap.put("menu_code", menu_code);
        }

        // 获取取角色列表
        List<String> roleList = sysroleDao_m.getUserRoleNameList(personnel_id);
        boolean flag = false;
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                // 部门经理
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(Integer.parseInt(personnelMap.get("personnel_deptid").toString()));
                deptIds.add(Integer.parseInt(personnelMap.get("personnel_deptid").toString()));
                // 可以看见子部门的所有业务员单据
                paramsMap.put("dept_ids", deptIds);
            }
            if (role.equals("理财业务部总监"))
            {
                flag = true;
            }
        }

        if (flag)
        {
            paramsMap.put("role_type", 1);
        }
        else
        {
            paramsMap.put("role_type", 0);
        }

        // 查询结果集合
        List<Map<String, Object>> list = wmsInvePhoneDao.getSpecialTeamInfosMoa(paramsMap);

        // 判断查询结果集合的数据是否大于1条,大于则设置所有团队
        if (list != null && list.size() > 1)
        {
            Map<String, Object> allMap = new HashMap<String, Object>();
            allMap.put("team_name", "所有团队");
            allMap.put("team_id", wmsInvePhoneDao.getSpecialTeamIdsStr(paramsMap));
            allMap.put("team_is_init", 1);
            list.add(0, allMap);
        }
        else
        {
            if (list != null && list.size() > 0)
            {
                list.get(0).put("team_is_init", 1);
            }
        }

        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getSpecialDeptInfosMoa
     * @Description: 获取数据权限下的部门信息
     * @param personnel_id
     * @param dept_id
     * @param menu_code
     * @return 
     * @author: DongHao
     * @time:2017年1月3日 上午9:33:17
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getSpecialDeptInfosMoa(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年1月3日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSpecialDeptInfosMoa(String personnel_id, String dept_id, String menu_code)
    {
        // 定义返回结果集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 判断人员id是否为空
        if (personnel_id != null && !"".equals(personnel_id))
        {
            paramsMap.put("personnel_id", Integer.parseInt(personnel_id));
        }

        // 判断部门id是否为空
        if (dept_id != null && !"".equals(dept_id))
        {
            List<String> deptIds = new ArrayList<String>();
            String[] ids = dept_id.split(",");
            for (String id : ids)
            {
                deptIds.add(id);
            }
            paramsMap.put("dept_id", deptIds);
        }

        // 判断菜单编码是否为空
        if (menu_code != null && !"".equals(menu_code))
        {
            paramsMap.put("menu_code", menu_code);
        }

        // 根据人员id查询人员信息
        Map<String, Object> personnelMap = wmsInvePhoneDao.getPersonnelById(Integer.parseInt(personnel_id));

        // 获取取角色列表
        List<String> roleList = sysroleDao_m.getUserRoleNameList(Integer.parseInt(personnel_id));
        boolean flag = false;
        for (String role : roleList)
        {
            if (role.equals("理财业务部总监"))
            {
                flag = true;
            }
        }

        if (flag)
        {
            paramsMap.put("role_type", 1);
        }
        else
        {
            paramsMap.put("role_type", 0);
        }

        // 查询获取数据集合
        List<Map<String, Object>> list = wmsInvePhoneDao.getSpecialDeptInfosMoa(paramsMap);

        // 判断结果集合中的数据是否大于1条数据,如果大于1条则设置所有部门
        if (list != null && list.size() > 1)
        {
            Map<String, Object> allMap = new HashMap<String, Object>();
            allMap.put("dept_name", "所有部门");
            allMap.put("dept_id", wmsInvePhoneDao.getSpecialDeptIdsStr(paramsMap));
            allMap.put("dept_is_init", 1);
            list.add(0, allMap);
        }
        else
        {
            if (list != null && list.size() > 0)
            {
                list.get(0).put("dept_is_init", 1);
            }
        }

        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getSyncMePerformanceInfosMoa
     * @Description: 同步与我有关的业绩信息
     * @param personnel_id 人员id
     * @param query_type 查询数据范围(1:表示待我处理数据,2:与我相关数据)
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午2:51:13
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getSyncMePerformanceInfosMoa(java.lang.String, java.lang.String)
     * history:
     * 1、2017年1月5日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSyncMePerformanceInfosMoa(String personnel_id, String query_type)
    {
        Map<String, Object> resultMap = null;
        // 判断查询数据的范围
        if (query_type != null && !"".equals(query_type) && "1".equals(query_type))
        {
            resultMap = pendingApprovalInfos(personnel_id);
        }
        else
        {
            resultMap = relatedMeInfos(personnel_id);
        }
        // 查询业务员下的柜员单据总条数 by20170216guanx
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bel_salesman_id_id", personnel_id);
        Integer count = wmsInveClerkDataDao.findClerkDataBySalemanCount(paramMap);
        if (resultMap == null)
        {
            resultMap = new HashMap<>();
        }
        resultMap.put("custom_total_number", count);
        return resultMap;
    }

    /**
     * 
     * @Title: relatedMeInfos
     * @Description: 获取与我有关的单据
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午3:20:16
     * history:
     * 1、2017年1月5日 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> relatedMeInfos(String personnel_id)
    {
        // 获取待审批的数据
        // 三级审批
        Map<String, Object> threeApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, personnel_id, "3");
        // 特批
        Map<String, Object> specialApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, personnel_id, "4");

        specialApprovalMap.put("user_id", personnel_id);

        List<Map<String, Object>> list = wmsInvePhoneDao.searchForPhoneAppData(threeApprovalMap);
        List<Map<String, Object>> listtp = wmsInvePhoneDao.searchForPhoneAppData(specialApprovalMap);

        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) threeApprovalMap.get("idList"), (List<String>) threeApprovalMap.get("taskIdList"), (String) threeApprovalMap.get("processDefinitionKey"));
        listtp = wmsInveWorkFlowService.addTaskIdToList(listtp, (List<Integer>) specialApprovalMap.get("idList"), (List<String>) specialApprovalMap.get("taskIdList"), (String) specialApprovalMap.get("processDefinitionKey"));

        if (list != null && listtp != null)
        {
            list.addAll(listtp);
        }
        else if (list == null && listtp != null)
        {
            list = listtp;
        }

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("personnel_id", personnel_id);
        if (list != null && list.size() > 0)
        {
            paramsMap.put("list", list);
        }

        PmPersonnel personnel = pmPersonnelDao.get(Integer.parseInt(personnel_id));

        List<String> roleList = sysroleDao_m.getUserRoleNameList(personnel.getPersonnel_id());
        boolean flag = false;
        int userId = 0;
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                // 部门经理
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(personnel.getPersonnel_deptid());
                deptIds.add(personnel.getPersonnel_deptid());
                // 可以看见子部门的所有业务员单据
                paramsMap.put("deptIds", deptIds);
                userId = personnel.getPersonnel_id();
                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                // 总
                paramsMap.put("super_user", 1);
                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                // 副总
                // 根据数据权限去获取部门
                paramsMap.put("salesman_id", personnel.getPersonnel_id());
                paramsMap.put("deptIds_menu", 110);
                paramsMap.put("deptIds_user_id", personnel.getPersonnel_id());
                flag = true;
            }
        }

        if (roleList == null || roleList.size() == 0 || !flag)
        {
            // 其他
            paramsMap.put("salesman_id", personnel.getPersonnel_id());
        }

        paramsMap.put("userid", personnel.getPersonnel_id());

        List<Map<String, Object>> resultList = wmsInvePhoneDao.getRelatedMeInfos(paramsMap);

        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("list", resultList);

        return resMap;
    }

    /**
     * 
     * @Title: pendingApprovalInfos
     * @Description: 查询待我审批的数据
     * @param personnel_id 人员id
     * @return 
     * @author: DongHao
     * @time:2017年1月5日 下午2:55:05
     * history:
     * 1、2017年1月5日 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> pendingApprovalInfos(String personnel_id)
    {
        // 获取待审批的数据
        // 三级审批
        Map<String, Object> threeApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, personnel_id, "3");
        // 特批
        Map<String, Object> specialApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, personnel_id, "4");

        specialApprovalMap.put("user_id", personnel_id);

        List<Map<String, Object>> list = wmsInvePhoneDao.searchForPhoneAppData(threeApprovalMap);
        List<Map<String, Object>> listtp = wmsInvePhoneDao.searchForPhoneAppData(specialApprovalMap);

        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) threeApprovalMap.get("idList"), (List<String>) threeApprovalMap.get("taskIdList"), (String) threeApprovalMap.get("processDefinitionKey"));
        listtp = wmsInveWorkFlowService.addTaskIdToList(listtp, (List<Integer>) specialApprovalMap.get("idList"), (List<String>) specialApprovalMap.get("taskIdList"), (String) specialApprovalMap.get("processDefinitionKey"));

        if (list != null && listtp != null)
        {
            list.addAll(listtp);
        }
        else if (list == null && listtp != null)
        {
            list = listtp;
        }

        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("list", list);

        return resMap;
    }

    /**
     * @Title: getAchVicegelListMoa
     * @Description: 获取数据权限下的副总清单
     * @param menu_code
     * @param personnel_id
     * @return 
     * @author: DongHao
     * @time:2017年1月6日 上午11:46:05
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getAchVicegelListMoa(java.lang.String)
     * history:
     * 1、2017年1月6日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getAchVicegelListMoa(String menu_code, String personnel_id)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("menu_code", menu_code);
        paramsMap.put("personnel_id", personnel_id);

        // 获取取角色列表
        List<String> roleList = sysroleDao_m.getUserRoleNameList(Integer.parseInt(personnel_id));
        boolean flag = false;
        for (String role : roleList)
        {
            if (role.equals("理财业务部总监"))
            {
                flag = true;
            }
        }

        if (flag)
        {
            paramsMap.put("role_type", 1);
        }
        else
        {
            paramsMap.put("role_type", 0);
        }

        List<Map<String, Object>> list = wmsInvePhoneDao.getAchVicegelListMoa(paramsMap);

        if (list != null && list.size() > 1)
        {
            Map<String, Object> allMap = new HashMap<String, Object>();
            allMap.put("vice_team_name", "所有副总");
            allMap.put("vice_team_id", 0);
            allMap.put("vice_personnel_id", 0);
            allMap.put("is_init", 1);
            list.add(0, allMap);
        }
        else
        {
            if (list != null && list.size() > 0)
            {
                list.get(0).put("is_init", 1);
            }
        }

        return list;
    }

    /**
     * @Title: getClerkData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param personnel_id
     * @return 
     * @author: Guanxu
     * @time:2017年2月16日 下午1:58:41
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getClerkData(java.lang.String)
     * history:
     * 1、2017年2月16日 Guanxu 创建方法
    */
    @Override
    public List<Map<String, Object>> getClerkData(String personnel_id)
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bel_salesman_id_id", personnel_id);
        return wmsInveClerkDataDao.searchClerkDataBySaleman(paramMap);
    }

    /**
     * 
     * @Title: getClerkDataCount
     * @Description: 获取客户经理名下排队人员总数
     * @param personnel_id
     * @return 
     * @author: Guanxu
     * @time:2017年3月1日 下午1:21:15
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getClerkDataCount(java.lang.String)
     * history:
     * 1、2017年3月1日 Guanxu 创建方法
     */
    @Override
    public int getClerkDataCount(String personnel_id)
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bel_salesman_id_id", personnel_id);
        return wmsInveClerkDataDao.findClerkDataBySalemanCount(paramMap);
    }

    /**
     * @Title: getIncomeCardPad
     * @Description: 根据客户姓名和客户身份证号获取客户收益卡信息
     * @param cus_name 客户姓名
     * @param id_card 客户身份证号
     * @return 返回客户收益卡list集合
     * @author: DongHao
     * @time:2017年3月1日 下午8:48:52
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getIncomeCardPad(java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getIncomeCardPad(String cus_name, String id_card)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cus_name", cus_name);
        paramsMap.put("id_card", id_card);
        list = wmsInvePhoneDao.getIncomeCardPadList(paramsMap);
        return list;
    }

    /**
     * @Title: getCustomerListPad
     * @Description: 根据客户姓名、联系方式和有效证件进行获取客户的信息
     * @param bean 客户信息对象
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月1日 下午9:22:02
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getCustomerListPad(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getCustomerListPad(CrmCustomerInfo bean)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, Object> resMap = new HashMap<String, Object>();

        int is_update = 1;

        List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
        // 设置调用接口信息
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_001"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        // 判断联系电话是否为空
        if (StringUtil.isNotBlank(bean.getContact_number()))
        {
            is_update = 0;
            nvps.add(new BasicNameValuePair("contact_number", bean.getContact_number()));// 联系电话
        }
        else
        {
            nvps.add(new BasicNameValuePair("contact_number", ""));// 联系电话
        }
        // 判断客户有效证件是否为空
        if (StringUtil.isNotBlank(bean.getId_card_number()))
        {
            nvps.add(new BasicNameValuePair("id_card_number", bean.getId_card_number()));// 身份证号码
        }
        else
        {
            nvps.add(new BasicNameValuePair("id_card_number", ""));// 身份证号码
        }
        // 判断客户姓名是否为空
        if (StringUtil.isNotBlank(bean.getCustomer_name()))
        {
            nvps.add(new BasicNameValuePair("customer_name", bean.getCustomer_name()));// 客户姓名
        }
        else
        {
            nvps.add(new BasicNameValuePair("customer_name", ""));// 客户姓名
        }
        // 判断业务员id是否为空
        if (bean.getPersonnel_id() != null)
        {
            nvps.add(new BasicNameValuePair("personnel_id", bean.getPersonnel_id().toString()));// 业务员id
        }

        if (bean.getPage() != null)
        {
            nvps.add(new BasicNameValuePair("page", bean.getPage().toString()));
        }

        if (bean.getSize() != null)
        {
            nvps.add(new BasicNameValuePair("size", bean.getSize().toString()));
        }

        try
        {
            HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);

            InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
            BufferedReader rd = new BufferedReader(reader);
            String result_str = rd.readLine();
            JSONObject obj = JSONObject.parseObject(result_str);

            List<Map<String, Object>> crmlist = (List<Map<String, Object>>) obj.get("result");

            // 判断获取的客户信息是否为空
            if (crmlist != null && crmlist.size() > 0)
            {
                for (int i = 0; i < crmlist.size(); i++)
                {
                    Map<String, Object> crmMap = crmlist.get(i);
                    if (crmMap != null && crmMap.size() > 0)
                    {
                        Map<String, Object> resultMap = new HashMap<String, Object>();
                        resultMap.put("costomer_id", crmMap.get("costomer_id"));// 客户id
                        resultMap.put("cus_name", ((String) crmMap.get("customer_name")) != null && !"".equals(((String) crmMap.get("customer_name"))) ? ((String) crmMap.get("customer_name")).trim() : "");// 客户姓名
                        resultMap.put("mobile_phone_str", ((String) crmMap.get("contact_number_hide")) != null && !"".equals(((String) crmMap.get("contact_number_hide"))) ? ((String) crmMap.get("contact_number_hide")).trim() : "");// 隐藏电话
                        resultMap.put("mobile_phone", ((String) crmMap.get("contact_number")) != null && !"".equals(((String) crmMap.get("contact_number"))) ? ((String) crmMap.get("contact_number")).trim() : "");// 移动电话
                        resultMap.put("id_card_str", ((String) crmMap.get("id_card_number_hide")) != null && !"".equals(((String) crmMap.get("id_card_number_hide"))) ? ((String) crmMap.get("id_card_number_hide")).trim() : "");// 隐藏身份证号
                        resultMap.put("id_card", ((String) crmMap.get("id_card_number")) != null && !"".equals(((String) crmMap.get("id_card_number"))) ? ((String) crmMap.get("id_card_number")).trim() : "");// 身份证号
                        resultMap.put("customer_email", ((String) crmMap.get("email_adress")) != null && !"".equals(((String) crmMap.get("email_adress"))) ? ((String) crmMap.get("email_adress")).trim() : "");// 邮件地址
                        resultMap.put("contact_address", ((String) crmMap.get("domicile_place")) != null && !"".equals(((String) crmMap.get("domicile_place"))) ? ((String) crmMap.get("domicile_place")).trim() : "");// 通讯地址
                        resultMap.put("customer_num", ((String) crmMap.get("customer_num")) != null && !"".equals(((String) crmMap.get("customer_num"))) ? ((String) crmMap.get("customer_num")).trim() : "");// 客户编号
                        resultMap.put("crm_create_timestamp", ((String) crmMap.get("create_timestamp")) != null && !"".equals(((String) crmMap.get("create_timestamp"))) ? ((String) crmMap.get("create_timestamp")).trim() : "");// 客户创建时间
                        customerList.add(resultMap);
                    }
                }
            }
            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        resMap.put("is_update", is_update);
        resMap.put("customerList", customerList);

        return resMap;
    }

    /**
     * @Title: getCategoryListPad
     * @Description: 获取产品信息
     * @param category_type 产品类型 1:表示公司主推产品,2:表示为柜员可售产品
     * @param user 用户信息
     * @return  返回产品信息list集合
     * @author: DongHao
     * @time:2017年3月1日 下午9:59:57
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getCategoryListPad(int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getCategoryListPad(int category_type, UserBean user)
    {
        // 定义返回结果集合
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        // 根据客户id获取客户信息
        PmPersonnel pmPersonnel = pmPersonnelDao.get(user.getUserId());

        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 判断查询产品信息是公司主推产品信息还是归属地柜员可售产品
        if (category_type == 1)
        {
            resultList = wmsInvePhoneDao.getCategoryInfosByCompanyMainPush(pmPersonnel);

            for (Map<String, Object> map : resultList)
            {
                map.put("pruduct_image_small", GlobalVal.SERVER_WMS_URL + "/" + map.get("pruduct_image_small"));
                map.put("pruduct_image_big", GlobalVal.SERVER_WMS_URL + "/" + map.get("pruduct_image_big"));
            }
        }
        else
        {
            // 判断如果当前登录的业务员是沈阳地区的则查询lichangyu可售的产品
            if (pmPersonnel.getPersonnel_regionnumber() != null && "0024".equals(pmPersonnel.getPersonnel_regionnumber()))
            {
                PmPersonnel personnel = pmPersonnelDao.getPersonnelByShortCode("101226");

                List<Integer> idlist = wmsInveTransaPruductUserDao.getidList(personnel.getPersonnel_id());// 根据当前登陆人获取当前人的上单产品

                if (idlist != null && idlist.size() > 0)
                {
                    paramMap.put("idList", idlist);
                }
                else
                {
                    return resultList;
                }
            }
            else
            {
                // 获取归属地的人员信息
                List<PmPersonnel> pmList = pmPersonnelDao.getPersonnelByPersonnelRegionnumber(pmPersonnel.getPersonnel_regionnumber());

                Integer user_id = null;
                boolean bool = false;

                // 循环取出柜员id
                for (PmPersonnel p : pmList)
                {
                    if (bool)
                    {
                        break;
                    }
                    // 获取当前登录业务员所在归属地的柜员
                    List<String> roleList = sysroleDao_m.getUserRoleNameList(p.getPersonnel_id());
                    for (String role : roleList)
                    {
                        if (role.equals("财务柜员"))
                        {
                            user_id = p.getPersonnel_id();
                            bool = true;
                            break;
                        }
                    }
                }

                List<Integer> idlist = wmsInveTransaPruductUserDao.getidList(user_id);// 根据当前登陆人获取当前人的上单产品

                if (idlist != null && idlist.size() > 0)
                {
                    paramMap.put("idList", idlist);
                }
                else
                {
                    return resultList;
                }

            }

            // 根据参数获取归属地柜员可售产品
            resultList = wmsInvePhoneDao.getCategoryInfos(paramMap);

            for (Map<String, Object> map : resultList)
            {
                map.put("pruduct_image_small", GlobalVal.SERVER_WMS_URL + "/" + map.get("pruduct_image_small"));
                map.put("pruduct_image_big", GlobalVal.SERVER_WMS_URL + "/" + map.get("pruduct_image_big"));
            }
        }

        return resultList;
    }

    /**
     * @Title: verificationCustomerPad
     * @Description: pad客户端验证客户关键信息是否存在
     * @param contact_number 联系电话
     * @param id_card_number 有效证件
     * @param costomer_id 客户id
     * @param user 用户对象
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年3月2日 上午12:45:05
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#verificationCustomerPad(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月2日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> verificationCustomerPad(String contact_number, String id_card_number, String costomer_id, UserBean user)
    {
        /*****************(1) 本地验证有效证件号是否重复********************/
        Map<String, Object> resultMap = padToLocalVerificationIdCard(id_card_number, costomer_id, user);
        // 验证身份证号
        resultMap = padToCrmVerificationContactNumber(contact_number, id_card_number, costomer_id, user, resultMap);

        return resultMap;
    }

    /**
     * 
     * @Title: padToCrmVerificationContactNumber
     * @Description: pad端到crm验证联系电话是否重复
     * @param contact_number 联系电话
     * @param id_card_number 有效证件
     * @param costomer_id crm客户id
     * @param user 当前登录用户信息
     * @param resultMap 验证信息对象
     * @return 返回验证信息对象
     * @author: DongHao
     * @time:2017年12月21日 上午8:06:00
     * history:
     * 1、2017年12月21日 DongHao 创建方法
     */
    private Map<String, Object> padToCrmVerificationContactNumber(String contact_number, String id_card_number, String costomer_id, UserBean user, Map<String, Object> resultMap)
    {
        // 判断验证信息对象是否为空
        if (resultMap == null)
        {
            resultMap = new HashMap<String, Object>();
        }

        // 调用crm接口
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_JudgeContactNumber"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        nvps.add(new BasicNameValuePair("costomer_id", costomer_id));
        nvps.add(new BasicNameValuePair("contact_number", contact_number));
        nvps.add(new BasicNameValuePair("id_card_number", id_card_number));
        nvps.add(new BasicNameValuePair("personnel_id", user.getUserId().toString()));

        try
        {
            HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);

            InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
            BufferedReader rd = new BufferedReader(reader);
            String result_str = rd.readLine();
            JSONObject obj = JSONObject.parseObject(result_str);

            if ("000".equals(obj.get("ret_code").toString()))
            {
                resultMap.put("contact_number_result", "0");
                resultMap.put("contact_number_reason", "成功");
            }
            else if ("002".equals(obj.get("ret_code").toString()))
            {
                resultMap.put("contact_number_result", "1");
                resultMap.put("contact_number_reason", "请使用该联系电话重新查询，获取客户信息。");
            }
            else if ("003".equals(obj.get("ret_code").toString()))
            {
                resultMap.put("contact_number_result", "1");
                resultMap.put("contact_number_reason", "该联系电话已在其他业务员名下签单，请输入有效联系电话。");
            }
            else if ("004".equals(obj.get("ret_code").toString()))
            {
                resultMap.put("contact_number_result", "1");
                resultMap.put("contact_number_reason", "该联系电话处于其他业务员保护期内，无法签单，请输入有效联系电话。");
            }

            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * @Title: padToLocalVerificationIdCard
     * @Description: pad端验证有效证件号是否重复
     * @param id_card_number 有效证件号
     * @param costomer_id crm客户id
     * @param user 当前登录用户
     * @return  返回错误提示信息
     * @author: DongHao
     * @time:2017年12月21日 上午7:38:58
     * history:
     * 1、2017年12月21日 DongHao 创建方法
    */
    private Map<String, Object> padToLocalVerificationIdCard(String id_card_number, String costomer_id, UserBean user)
    {
        // 定义返回信息提示的map集合
        Map<String, Object> res_map = new HashMap<String, Object>();

        // 判断crm客户id是否为空
        Integer crmId = costomer_id != null && !"".equals(costomer_id.trim()) ? Integer.parseInt(costomer_id.trim()) : 0;
        // 1 根据crm客户id进行本地查询
        List<Map<String, Object>> crmLis = wmsInveSignedApplicationDao.getLocalCustomerInfosByCostomerId(crmId);

        // 判断通过客户id进行获取客户的签单信息是否存在,不存在则通过客户有效证件号进行查询
        if (crmLis == null || crmLis.size() == 0)
        {

            /* (1) 通过crm客户id没有获取客户的签单信息,则使用客户的有效证件号进行查询*/
            crmLis = wmsInveSignedApplicationDao.getLocalCustomerInfosByIdCard(id_card_number.trim());

            // 判断通过客户有效证件号没有获取到客户签单信息, 否则对获取的客户签单信息进行遍历
            if (crmLis != null || crmLis.size() != 0)
            {

                /*定义boolean类型的标记变量, bool == true 说明存在crm客户id与获取的客户id不一致, bool == false 说明不存在客户id不一致的数据*/
                boolean bool = false;

                // 对获取的客户信息进行遍历
                for (Map<String, Object> map : crmLis)
                {

                    Integer crm_id = (Integer) map.get("costomer_id");

                    String data_status = (String) map.get("data_status");

                    // 判断客户id是否存在不一致的情况
                    if (!crm_id.equals(costomer_id))
                    {
                        // 判断单据状态是否为待支付、待签约、待审核、待修订状态
                        if (data_status.equals("2") || data_status.equals("11") || data_status.equals("12") || data_status.equals("13") || data_status.equals("17") || data_status.equals("19") || data_status.equals("20"))
                        {
                            res_map.put("id_card_result", "1");
                            res_map.put("id_card_reason", "该有效证件所指客户签单进行中,因此无法使用该有效证件号绑定其他客户,请知悉");
                            break;
                        }
                        else if (user.getUserId().compareTo((Integer) map.get("bel_salesman_id_id")) != 0) // 判断业务员是否一致
                        {
                            res_map.put("id_card_result", "1");
                            res_map.put("id_card_reason", "该有效证件已在其他业务员名下签单,请输入有效证件号");
                            break;
                        }
                        else
                        {
                            bool = true;
                            break;
                        }
                    }
                }

                // 判断bool == true 说明存在客户id不一致的数据
                if (bool)
                {
                    res_map.put("id_card_result", "1");
                    res_map.put("id_card_reason", "请使用有效证件重新查询,获取客户信息");
                }

                // 判断bool为false时说明不存在不一致的数据, res_map的size==0时说明不存在不一致的数据
                if (!bool && res_map.size() == 0)
                {
                    res_map.put("id_card_result", "0");
                    res_map.put("id_card_reason", "验证通过");
                }
            }
            else
            {
                res_map.put("id_card_result", "0");
                res_map.put("id_card_reason", "验证通过");
            }
        }
        else
        {

            /* 定义boolean类型的标记变量, bool == true 说明存在有效证件号不一致的数据, bool == false 说明不存在有效证件不一致的数据*/
            boolean bool = false;

            /*(2)通过crm客户id获取到客户签单信息,则使用客户的有效证件号与获取的客户签单信息进行比对,身份证号一致允许上单,不一致不允许上单,给出提示信息*/

            for (Map<String, Object> map : crmLis)
            {

                String id_card = (String) map.get("id_card");

                String data_status = (String) map.get("data_status");

                // 判断客户有效证件是否存在不一致的情况
                if (!id_card.toLowerCase().equals(id_card_number.toLowerCase()))
                {
                    if (user.getUserId().compareTo((Integer) map.get("bel_salesman_id_id")) != 0) // 判断业务员是否一致
                    {
                        res_map.put("id_card_result", "1");
                        res_map.put("id_card_reason", "该有效证件已在其他业务员名下签单,请输入有效证件号");
                        break;
                    }
                    else
                    {
                        bool = true;
                        break;
                    }
                }
            }

            // 判断bool == true 说明存在有效证件不一致的数据
            if (bool)
            {
                res_map.put("id_card_result", "1");
                res_map.put("id_card_reason", "请使用有效证件重新查询,获取客户信息");
            }

            // 判断bool为false时说明不存在不一致的数据, res_map的size==0时说明不存在不一致的数据
            if (!bool && res_map.size() == 0)
            {
                res_map.put("id_card_result", "0");
                res_map.put("id_card_reason", "验证通过");
            }
        }

        return res_map;
    }

    /**
     * @Title: getTeamCommMonByDeptManagerMoa
     * @Description: 接口33 获取管理佣金按部门经理进行汇总(接口 33)
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param statics_month 统计月份
     * @param dept_ids 团队id(多个团队id以","分隔)
     * @param personnel_info 部门经理姓名或者部门经理短工号
     * @param personnel_id 当前登录moa人员id
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年3月10日 下午4:25:16
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getTeamCommMonByDeptManagerMoa(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月10日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getTeamCommMonByDeptManagerMoa(Integer page, Integer page_size, String statics_month, String dept_ids, String personnel_info, Integer personnel_id)
    {

        // 定义返回结果map集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询sql需要的参数
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 设置分页参数
        paramsMap.put("page", page);
        paramsMap.put("page_size", page_size);

        // 判断统计月份是否为空
        if (statics_month != null && !"".equals(statics_month))
        {
            paramsMap.put("statics_month", statics_month);
        }

        // 判断部门id是否为空
        if (dept_ids != null && !"".equals(dept_ids))
        {
            paramsMap.put("dept_ids", dept_ids);
        }

        // 判断人员查询信息是否为空
        if (personnel_info != null && !"".equals(personnel_info))
        {
            paramsMap.put("personnel_info", personnel_info);
        }

        // 根据当前登录moa人员id进行获取人员信息
        PmPersonnel personnel = pmPersonnelDao.get(personnel_id);

        // 获取当前登录人的权限
        List<String> roleList = sysroleDao_m.getUserRoleNameList(personnel.getPersonnel_id());

        // 设置标识变量,标识获取的当前人员角色是否存在,默认参数是不存在,存在for循环中的角色则设置成true
        boolean flag = false;

        // 循环遍历角色列表
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                // 部门经理 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(personnel.getPersonnel_deptid());
                deptIds.add(personnel.getPersonnel_deptid());
                // 可以看见子部门的所有业务员单据
                paramsMap.put("deptIds", deptIds);
                paramsMap.put("salesman_id", personnel.getPersonnel_id());
                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                // 总
                paramsMap.put("super_user", 1);
                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                // 副总 根据数据权限去获取部门
                paramsMap.put("salesman_id", personnel.getPersonnel_id());
                paramsMap.put("deptIds_menu", 110);
                paramsMap.put("deptIds_user_id", personnel.getPersonnel_id());
                flag = true;
            }
        }

        // 判断获取的角色列表和标识变量判断是否存在上面的for循环中的角色,不存在则设置人员id
        if (roleList == null || roleList.size() == 0 || !flag)
        {
            // 其他
            paramsMap.put("salesman_id", personnel.getPersonnel_id());
        }

        // 根据条件进行查询获取管理佣金按部门经理进行汇总
        List<Map<String, Object>> resultList = wmsInvePhoneDao.getTeamCommMonByDeptManagerMoa(paramsMap);

        resultMap.put("Rows", resultList);

        return resultMap;
    }

    /**
     * @Title: getTeamCommMonBySalemanMoa
    * @Description: 接口34 获取管理佣金按客户经理汇总
     * @param offset 当前页
     * @param pagesize 每页显示的记录数
     * @param statics_month 统计月份
     * @param dept_ids 团队id值(多个团队id以","分隔)
     * @param personnel_id 部门经理人员id
     * @param personnel_info 客户经理姓名或客户经理短工号
     * @param user_id 当前登录moa的人员id
     * @return 返回Map结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午9:49:06
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getTeamCommMonBySalemanMoa(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getTeamCommMonBySalemanMoa(Integer offset, Integer pagesize, String statics_month, String dept_ids, String personnel_info, String personnel_id, Integer user_id)
    {
        // 定义返回结果map集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("page", offset);// 设置当前页
        paramsMap.put("pagesize", pagesize);// 设置每页显示多少记录

        // 判断佣金统计月份是否为空
        if (statics_month != null && !"".equals(statics_month))
        {
            paramsMap.put("statics_month", statics_month);
        }

        // 判断团队id值是否为空
        if (dept_ids != null && !"".equals(dept_ids))
        {
            paramsMap.put("dept_ids", dept_ids);
        }

        // 判断客户经理姓名或客户经理短工号
        if (personnel_info != null && !"".equals(personnel_info))
        {
            paramsMap.put("personnel_info", personnel_info);
        }

        // 判断部门经理人员id是否为空
        if (personnel_id != null && !"".equals(personnel_id))
        {
            paramsMap.put("personnel_id", personnel_id);
        }

        // 根据条件进行查询按照客户经理进行汇总的数据信息
        List<Map<String, Object>> list = wmsInvePhoneDao.getTeamCommMonBySalemanMoa(paramsMap);

        resultMap.put("Rows", list);

        return resultMap;
    }

    /**
     * @Title: getTeamCommMonByItemMoa
     * @Description: 接口35 获取管理佣金按佣金项汇总
     * @param statics_month 佣金统计月份
     * @param personnel_id 客户经理人员id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:17:40
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getTeamCommMonByItemMoa(java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getTeamCommMonByItemMoa(String statics_month, String personnel_id)
    {
        // 定义返回结果集合map
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 判断统计月份是否为空
        if (statics_month != null && !"".equals(statics_month))
        {
            paramsMap.put("statics_month", statics_month);
        }

        // 判断人员id是否为空
        if (personnel_id != null && !"".equals(statics_month))
        {
            paramsMap.put("personnel_id", personnel_id);
        }

        // 根据条件查询佣金项汇总信息
        List<Map<String, Object>> list = wmsInvePhoneDao.getTeamCommMonByItemMoa(paramsMap);

        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getTeamCommMonByDataMoa
     * @Description: 接口36 获取管理佣金按单据展现
     * @param page 当前页
     * @param pagesize 每页显示多少记录数
     * @param personnel_info 客户姓名
     * @param statics_month 佣金统计月份
     * @param personnel_id 人员id
     * @param comm_item_ids 佣金项id(01表示老佣金,02表示新佣金)
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:59:38
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getTeamCommMonByDataMoa(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getTeamCommMonByDataMoa(Integer page, Integer pagesize, String personnel_info, String statics_month, Integer personnel_id, String comm_item_ids)
    {
        // 定义返回结果集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义条件查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 定义查询结果list集合
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        paramsMap.put("page", page);// 设置当前页
        paramsMap.put("pagesize", pagesize);// 设置每页显示多少记录

        // 判断客户姓名查询条件是否为空
        if (personnel_info != null && !"".equals(personnel_info))
        {
            paramsMap.put("personnel_info", personnel_info);
        }

        // 判断佣金统计月份是否为空
        if (statics_month != null && !"".equals(statics_month))
        {
            paramsMap.put("statics_month", statics_month);
        }

        // 判断人员id是否为空
        if (personnel_id != null)
        {
            paramsMap.put("personnel_id", personnel_id);
        }

        // 判断佣金项id是否为空并且佣金id等于01
        if (comm_item_ids != null && "01".equals(comm_item_ids))
        {
            // 获取老佣金按照单据展现
            list = wmsInvePhoneDao.getOldTeamCommMonByData(paramsMap);
        }
        else
        {
            // 获取新佣金按照单据展现
            list = wmsInvePhoneDao.getNewTeamCommMonByData(paramsMap);
        }

        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getTeamCommMonByManagerMoa
     * @Description: 接口37 获取管理佣金按客户经理汇总For副总/中分总/总经理/后线领导
     * @param page 当前页
     * @param page_size 每页显示的记录数
     * @param statics_month 佣金统计月份
     * @param personnel_info 客户经理姓名或客户经理的短工号
     * @param personnel_id 当前登录moa系统的人员Id
     * @return 返回结果集合
     * @author: DongHao
     * @time:2017年3月13日 上午11:44:45
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getTeamCommMonByManagerMoa(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getTeamCommMonByManagerMoa(Integer page, Integer pagesize, String personnel_info, String statics_month, Integer personnel_id)
    {
        // 定义返回结果集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询条件集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("page", page);// 设置当前页
        paramsMap.put("pagesize", pagesize);// 设置每页显示多少记录
        // paramsMap.put("personnel_id", personnel_id);// 设置当前登录人id

        // 判断客户经理的姓名或者客户经理短工号是否为空
        if (personnel_info != null && !"".equals(personnel_info))
        {
            paramsMap.put("personnel_info", personnel_info);
        }

        // 判断佣金统计月份是否为空
        if (statics_month != null && !"".equals(statics_month))
        {
            paramsMap.put("statics_month", statics_month);
        }

        paramsMap.put("personnel_id", personnel_id);

        // 获取副总/中分总/总经理/后线领导佣金情况
        List<Map<String, Object>> list = wmsInvePhoneDao.getTeamCommMonByManagerMoa(paramsMap);

        // 获取副总/中分总/总经理/后线领导佣金合计
        Map<String, Object> resMap = wmsInvePhoneDao.getTeamCommMonByManagerMoaReturnMap(paramsMap);

        resultMap.put("saleman_data", list);
        resultMap.put("team_comm_mon_fmt", (String) resMap.get("team_comm_mon_fmt"));
        resultMap.put("team_comm_mon", (String) resMap.get("team_comm_mon_fmt"));

        return resultMap;
    }

    /**
     * @Title: getClerkSortData
     * @Description: 获取排队动态信息及当日剩余可售额度
     * @param personnel_id 登录用户id
     * @return 排队动态信息及当日剩余可售额度
     * @author: jinzhm
     * @time:2017年4月6日 下午5:01:34
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getClerkSortData(java.lang.String)
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getClerkSortData(String personnel_id)
    {
        // 获取排队动态数据
        List<Map<String, Object>> clerkData = getClerkData(personnel_id);

        // 获取当日设置剩余可售额度
        UserBean user = new UserBean();
        user.setUserId(Integer.parseInt(personnel_id));
        String keepAccount = wmsInveClerkDataService.getKeepAccount(user);

        // 封装数据并返回
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("clerk_data", clerkData);
        dataMap.put("keep_account", keepAccount);
        return dataMap;
    }

    /**
     * 
     * @Title: delIncomeCardByWmsInveCustomerCardId
     * @Description: 根据收益卡表主键进行删除收益卡信息
     * @param wms_inve_customer_card_id 收益卡信息表主键
     * @return 返回布尔类型的值,删除成功返回true,否则返回false
     * @author: DongHao
     * @time:2017年6月6日 下午1:23:59
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#delIncomeCardByWmsInveCustomerCardId(java.lang.Integer)
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    @Override
    public boolean delIncomeCardByWmsInveCustomerCardId(Integer wms_inve_customer_card_id)
    {
        boolean bool = true;

        try
        {
            wmsInvePhoneDao.delIncomeCardByWmsInveCustomerCardId(wms_inve_customer_card_id);
        }
        catch (Exception e)
        {
            bool = false;
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 
     * @Title: getRedeemBillsPad
     * @Description: 根据客户crmid进行获取客户的到期以及超期的收益中的单据
     * @param costomer_id 客户crmid
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年6月6日 下午2:15:28
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#getRedeemBillsPad(java.lang.Integer)
     * history:
     * 1、2017年6月6日 DongHao 创建方法
     */
    @Override
    public List<Map<String, Object>> getRedeemBillsPad(Integer costomer_id)
    {
        List<Map<String, Object>> list = wmsInvePhoneDao.getRedeemBillsPadByCrmid(costomer_id);

        return list;
    }

    /**
     * 
     * @Title: inveRedemptionPadHandler
     * @Description: pad端执行赎回在上单的流程
     * @param user 当前登录的用户信息
     * @param wmsInveTransa 上单对象信息
     * @param wmsInveTransaProd 上单产品对象信息
     * @param wmsInveCustomer 上单客户对象信息
     * @param list 上单产品以及投资金额信息
     * @param beanVO 上单对象
     * @param version 版本号
     * @param wms_inve_transa_id_strs 上单产品表主键(多个以","分隔) 
     * @author: DongHao
     * @time:2017年6月7日 下午1:11:54
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#inveRedemptionPadHandler(com.zx.sframe.util.vo.UserBean, com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd, com.zx.emanage.util.gen.entity.WmsInveCustomer, java.util.List, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO, java.lang.String, java.lang.String)
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    @Override
    @Transactional
    public Map<String, Object> inveRedemptionPadHandler(UserBean user, WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsInveCustomer, List<Map<String, Object>> list, WmsInveTransaSearchBeanVO beanVO, String version, String wms_inve_transa_id_strs)
    {
        // 定义返回结果map集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 对单据进行赎回操作
        inveRedeemHandler(wms_inve_transa_id_strs, user);

        // 遍历上单的产品
        for (Map<String, Object> map : list)
        {

            // 设置上单所购买的产品
            wmsInveTransaProd.setWms_inve_pruduct_category_id(Double.valueOf(map.get("wms_inve_pruduct_category_id").toString()).intValue());

            // 设置上单金额
            wmsInveTransaProd.setProduct_account(new BigDecimal(map.get("product_account").toString()));

            wmsInveTransa.setIs_extend_amount("1");

            // 执行上单
            resultMap = wmsInveSignedApplicationService.saveSignedInfoPad(wmsInveTransa, wmsInveTransaProd, wmsInveCustomer, beanVO, user);

        }

        int count = wmsInveSignedApplicationService.findClerkDataBySalemanCount(user);

        count = count - list.size();

        if (count < 0)
        {
            count = 0;
        }

        resultMap.put("ret_code", "000");
        resultMap.put("ret_msg", "尊敬的客户您好:您的前方正有" + count + "个客户等待办理业务,请关注窗口信息,过号则会重排");

        return resultMap;

    }

    /**
     * 
     * @Title: getRedeemInfoPad
     * @Description: 获取单据赎回详情信息
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月7日 下午1:11:48
     * history:
     * 1、2017年6月7日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> getRedeemInfoPad(String wms_inve_transa_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("ret_code", "000");
        resultMap.put("ret_msg", "获取单据信息成功");
        try
        {

            List<String> ids = java.util.Arrays.asList(wms_inve_transa_id.split(","));
            List<Map<String, Object>> redeem = wmsInvePhoneDao.getRedeemInfoPad(ids);
            // 计算应该收益和实际回收
            for (int i = 0; i < redeem.size(); i++)
            {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                WmsInveRedeemVO bean = new WmsInveRedeemVO();
                Map<String, Object> map = redeem.get(i);

                // 产品名称
                bean.setCategory_name((String) map.get("category_name"));
                if (map.get("old_date_of_payment") != null)
                {
                    bean.setOld_date_of_payment_str(map.get("old_date_of_payment").toString());
                    bean.setOld_date_of_payment((Date) map.get("old_date_of_payment"));
                }

                // 原上单id
                if (map.get("old_wms_inve_transa_id") != null)
                {
                    bean.setOld_wms_inve_transa_id((int) map.get("old_wms_inve_transa_id"));
                }
                // 赎回时间
                bean.setRedeem_date_str(sf.format(new Date(System.currentTimeMillis())));
                // 上单id
                bean.setWms_inve_transa_id((int) map.get("wms_inve_transa_id"));

                // 处理赎回本金
                BigDecimal product_account = (BigDecimal) map.get("redeem_amount");
                Map<String, Object> m = new HashMap<>();
                m.put("principal_amount", product_account.intValue() / 10000);
                JSONArray ja = new JSONArray();
                ja.add(0, m);
                bean.setRedeem_amount_grid(ja.toString());



                Map<String, Object> result = wmsInveTransaProtocolService.getRedeemDueIncome(bean);
                List<Map<String, Object>> protocolInfo = (List<Map<String, Object>>) result.get("protocolInfo");
                List<WmsInveRedeemPrincipalDetailSearchBeanVO> listgridValList = (List<WmsInveRedeemPrincipalDetailSearchBeanVO>) protocolInfo.get(0).get("listgridVal");
                WmsInveRedeemPrincipalDetailSearchBeanVO listgridVal = listgridValList.get(0);
                // 应付基本收益(B1)
                BigDecimal B1 = listgridVal.getPayable_basic_income();
                // 应付奖励收益(B2)
                BigDecimal B2 = listgridVal.getPayable_reward_income();
                // 公益产品收益(E)
                BigDecimal E = listgridVal.getPublic_amount();
                // 应付活期收益(I)
                BigDecimal I = listgridVal.getCurrent_income();
                if (I == null)
                {
                    I = new BigDecimal(0);
                }
                // 转让服务费(D)
                BigDecimal D = new BigDecimal(0);
                // 计算应付收益
                BigDecimal due_income = B1.add(B2).add(E).add(I).subtract(D);
                // 计算实际回收
                redeem.get(i).put("due_income", due_income);
                redeem.get(i).put("redeem_reality_total_amount", due_income);
                redeem.get(i).put("management_fee", D.intValue());
                redeem.get(i).put("redeem_amount", product_account.intValue() / 10000);

                // 处理map中不需要返回的数据
                redeem.get(i).remove("date_of_payment");
                redeem.get(i).remove("wms_inve_transa_id");
            }
            resultMap.put("ret_data", redeem);

        }
        catch (Exception e)
        {
            resultMap.put("ret_code", "001");
            resultMap.put("ret_msg", "获取单据信息失败");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 
     * @Title: inveRedeemHandler
     * @Description: 根据上单表主键进行赎回操作
     * @param wms_inve_transa_id_strs 上单表主键(多个以","分隔)
     * @param user 当前登录的用户
     * @author: DongHao
     * @time:2017年6月7日 下午1:12:10
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    private void inveRedeemHandler(String wms_inve_transa_id_strs, UserBean user)
    {

        // 定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 将上单表主键以","分隔成数组
        String[] ids = wms_inve_transa_id_strs.split(",");

        // 遍历上单表主键数组进行逐条单据进行赎回操作
        for (String wms_inve_transa_id : ids)
        {
            // 根据上单表主键获取单据信息
            Map<String, Object> wmsInveMap = wmsInvePhoneDao.getWmsInveTransaInfoById(wms_inve_transa_id);

            // 判断根据主键获取的上单信息是否为空
            if (wmsInveMap != null && wmsInveMap.size() > 0)
            {
                // 获取当前系统时间
                java.sql.Date redeem_date = new Date(System.currentTimeMillis());

                // 定义需要计算赎回单据的各个收益情况
                WmsInveRedeemVO vo = new WmsInveRedeemVO();

                // 设置上单表主键
                vo.setWms_inve_transa_id((Integer) wmsInveMap.get("wms_inve_transa_id"));

                // 设置原始上单时间
                vo.setOld_date_of_payment(wmsInveMap.get("old_date_of_payment") != null ? (java.sql.Date) wmsInveMap.get("old_date_of_payment") : null);

                // 设置原始上单表主键
                vo.setOld_wms_inve_transa_id(wmsInveMap.get("old_last_wms_inve_transa_id") != null ? (Integer) wmsInveMap.get("old_last_wms_inve_transa_id") : null);

                // 设置单据来源
                vo.setBill_source(wmsInveMap.get("bill_source") != null ? wmsInveMap.get("bill_source").toString() : "");

                // 设置赎回金额
                List<Map<String, Object>> redeemInfos = new ArrayList<Map<String, Object>>();
                Map<String, Object> redeemMapInfo = new HashMap<String, Object>();
                redeemMapInfo.put("principal_amount", (BigDecimal) wmsInveMap.get("product_account"));
                redeemInfos.add(redeemMapInfo);
                vo.setRedeem_amount_grid(new Gson().toJson(redeemInfos));

                // 设置赎回时间
                vo.setRedeem_date_str(format.format(redeem_date));
                
                //设置产品名称
                vo.setCategory_name(wmsInveMap.get("category_name")!=null?wmsInveMap.get("category_name").toString():"");

                // 获取赎回收益信息
                Map<String, Object> map = wmsInveTransaProtocolService.getRedeemDueIncome(vo);

                // 执行赎回操作
                redeemHandler(wms_inve_transa_id, redeem_date, map, wmsInveMap, user);

            }
        }

    }

    /**
     * 
     * @Title: redeemHandler
     * @Description: 针对上单单据进行赎回操作
     * @param wms_inve_transa_id 上单表主键
     * @param redeem_date 赎回时间
     * @param map 各种收益参数
     * @param wmsInveMap 上单表信息
     * @param user 当前登录人信息对象
     * @author: DongHao
     * @time:2017年6月7日 下午2:43:39
     * history:
     * 1、2017年6月7日 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    private void redeemHandler(String wms_inve_transa_id, Date redeem_date, Map<String, Object> map, Map<String, Object> wmsInveMap, UserBean user)
    {
        // 定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        /**************************设置赎回单据信息**********************************/
        // 定义赎回对象
        WmsInveRedeem bean = new WmsInveRedeem();
        // 客户姓名
        bean.setCus_name(wmsInveMap.get("cus_name").toString());
        // 是否全部赎回
        bean.setIs_fully_redeemed("1");
        // 是否预约赎回
        bean.setIs_order_redeem("0");
        // 是否扣除违约金
        bean.setIs_take_off_damages("0");
        // 回收事由
        bean.setRedeem_company_reason("3");
        // 赎回时间
        bean.setRedeem_date(redeem_date);
        // 赎回事由
        bean.setRedeem_reason("到期赎回");
        // 赎回类型
        bean.setRedeem_type("4");

        List<Map<String, Object>> protocolInfo = (List<Map<String, Object>>) map.get("protocolInfo");
        Map<String, Object> redeemMap = protocolInfo.get(0);
        List<WmsInveRedeemPrincipalDetailSearchBeanVO> listgridValList = (List<WmsInveRedeemPrincipalDetailSearchBeanVO>) protocolInfo.get(0).get("listgridVal");
        WmsInveRedeemPrincipalDetailSearchBeanVO listgridVal = listgridValList.get(0);

        // 应得总收益 = 应付基本收益 + 应付奖励收益 + 公益产品收益 + 应付活期收益 + 已付收益
        BigDecimal total_due_income = listgridVal.getPayable_basic_income() != null ? listgridVal.getPayable_basic_income() : BigDecimal.ZERO;// 应付基本收益
        total_due_income = total_due_income.add(listgridVal.getPayable_reward_income() != null ? listgridVal.getPayable_reward_income() : BigDecimal.ZERO);// 应付奖励收益
        total_due_income = total_due_income.add(listgridVal.getExtend_income() != null ? listgridVal.getExtend_income() : BigDecimal.ZERO);// 公益产品收益
        total_due_income = total_due_income.add(listgridVal.getCurrent_income() != null ? listgridVal.getCurrent_income() : BigDecimal.ZERO);// 应付活期收益
        total_due_income = total_due_income.add(BigDecimal.ZERO);// 已付收益
        bean.setTotal_due_income(total_due_income);

        // 实际回收
        bean.setRedeem_reality_total_amount(total_due_income);
        bean.setRedeem_reality_total_amount_upper(digitUpperUtil.digitUppercase(total_due_income.doubleValue(), true));

        // 客户id
        bean.setWms_inve_customer_id(Integer.parseInt(wmsInveMap.get("wms_inve_customer_id").toString()));

        /**************************设置赎回格外信息**********************************/
        com.zx.emanage.inve.vo.WmsInveRedeemVO vo = new com.zx.emanage.inve.vo.WmsInveRedeemVO();
        vo.setCustomer_email("");
        vo.setEnd_of_date_org(wmsInveMap.get("end_of_date").toString());
        vo.setIs_order_redeem("0");
        vo.setIs_update("0");
        List<Map<String, Object>> paramsLis = new ArrayList<Map<String, Object>>();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("principal_type", "2");
        paramsMap.put("principal_amount", listgridVal.getPrincipal_amount() != null ? listgridVal.getPrincipal_amount().multiply(new BigDecimal(10000)) : BigDecimal.ZERO);
        paramsMap.put("income_amount", listgridVal.getPayable_basic_income() != null ? listgridVal.getPayable_basic_income() : BigDecimal.ZERO);
        paramsMap.put("reward_income", listgridVal.getPayable_reward_income() != null ? listgridVal.getPayable_reward_income() : BigDecimal.ZERO);
        paramsMap.put("paid_income_amount", BigDecimal.ZERO);
        paramsMap.put("extend_amount", listgridVal.getExtend_income() != null ? listgridVal.getExtend_income() : BigDecimal.ZERO);
        paramsMap.put("current_income", listgridVal.getCurrent_income() != null ? listgridVal.getCurrent_income() : BigDecimal.ZERO);
        paramsMap.put("total_management_fee", listgridVal.getManagement_fee() != null ? listgridVal.getManagement_fee() : BigDecimal.ZERO);
        paramsLis.add(paramsMap);
        vo.setRedeemGridData(new Gson().toJson(paramsLis));
        vo.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));

        /**************************设置赎回单据详细信息**********************************/
        // 赎回详细信息对象
        WmsInveRedeemDetail wmsInveRedeemDetail = new WmsInveRedeemDetail();
        // 基本收益利率
        wmsInveRedeemDetail.setBasic_inceom_rate(redeemMap.get("basic_inceom_rate") != null ? new BigDecimal(redeemMap.get("basic_inceom_rate").toString()) : BigDecimal.ZERO);
        // 设置产品名称
        wmsInveRedeemDetail.setCategory_name(wmsInveMap.get("category_name") != null ? wmsInveMap.get("category_name").toString() : "");
        // 设置创建时间
        wmsInveRedeemDetail.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        // 设置创建部门id
        wmsInveRedeemDetail.setCreate_user_dept_id(user.getDeptId());
        // 设置创建人id
        wmsInveRedeemDetail.setCreate_user_id(user.getUserId());
        // 活期收益
        wmsInveRedeemDetail.setCurrent_income(redeemMap.get("current_income") != null ? new BigDecimal(redeemMap.get("current_income").toString()) : BigDecimal.ZERO);
        // 活期收益利率
        wmsInveRedeemDetail.setCurrent_income_rate(redeemMap.get("current_income_rate") != null ? new BigDecimal(redeemMap.get("current_income_rate").toString()) : BigDecimal.ZERO);
        // 活期收益天数
        wmsInveRedeemDetail.setDays_current_income(redeemMap.get("days_of_current_income") != null ? Integer.parseInt(redeemMap.get("days_of_current_income").toString()) : 0);
        // 公益收益天数
        wmsInveRedeemDetail.setDays_extend_income(redeemMap.get("days_extend_income") != null ? Integer.parseInt(redeemMap.get("days_extend_income").toString()) : 0);
        // 基本收益天数
        wmsInveRedeemDetail.setDays_of_basic_income(redeemMap.get("days_of_basic_income") != null ? Integer.parseInt(redeemMap.get("days_of_basic_income").toString()) : 0);
        // 其它扣款
        wmsInveRedeemDetail.setDeduct_money(BigDecimal.ZERO);
        // 税点
        wmsInveRedeemDetail.setDeduct_tax_point(BigDecimal.ZERO);
        // 应得收益
        wmsInveRedeemDetail.setDue_income(redeemMap.get("due_income") != null ? new BigDecimal(redeemMap.get("due_income").toString()) : BigDecimal.ZERO);
        wmsInveRedeemDetail.setEnable_flag("1");
        // 公益收益合计
        wmsInveRedeemDetail.setExtend_income(redeemMap.get("extend_income") != null ? new BigDecimal(redeemMap.get("extend_income").toString()) : BigDecimal.ZERO);
        // 公益收益利率
        wmsInveRedeemDetail.setExtend_income_rate(redeemMap.get("extend_income_rate") != null ? new BigDecimal(redeemMap.get("extend_income_rate").toString()) : BigDecimal.ZERO);
        wmsInveRedeemDetail.setgetIsExcludePKFlag(false);
        // 其它扣款是否
        wmsInveRedeemDetail.setIs_deduct_money("0");
        // 税点是否
        wmsInveRedeemDetail.setIs_deduct_tax_point("0");
        // 是否全部赎回
        wmsInveRedeemDetail.setIs_fully_redeemed("1");
        // 是否自行办理
        wmsInveRedeemDetail.setIs_handle_self("1");
        // 是否打印
        wmsInveRedeemDetail.setIs_protocol_finish("0");
        // 应收管理费
        wmsInveRedeemDetail.setManagement_fee(BigDecimal.ZERO);
        wmsInveRedeemDetail.setManagement_fee_rate(BigDecimal.ZERO);

        // 已付收益合计
        wmsInveRedeemDetail.setPaid_income(redeemMap.get("paid_income") != null ? new BigDecimal(redeemMap.get("paid_income").toString()) : BigDecimal.ZERO);
        // 基本收益
        wmsInveRedeemDetail.setPayable_basic_income(listgridVal.getPayable_basic_income() != null ? listgridVal.getPayable_basic_income() : BigDecimal.ZERO);
        // 奖励收益
        wmsInveRedeemDetail.setPayable_reward_income(listgridVal.getPayable_reward_income() != null ? listgridVal.getPayable_reward_income() : BigDecimal.ZERO);
        // 产品上单金额
        wmsInveRedeemDetail.setProduct_account(new BigDecimal(wmsInveMap.get("product_account").toString()).multiply(new BigDecimal(10000)));
        // 协议签订日期
        wmsInveRedeemDetail.setProtocol_create_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveRedeemDetail.setProtocol_create_timestamp_str(format.format(new Timestamp(System.currentTimeMillis())));
        // 赎回金额
        wmsInveRedeemDetail.setRedeem_amount(new BigDecimal(wmsInveMap.get("product_account").toString()).multiply(new BigDecimal(10000)));
        wmsInveRedeemDetail.setRedeem_product_interest(redeemMap.get("redeem_product_interest") != null ? new BigDecimal(redeemMap.get("redeem_product_interest").toString()) : BigDecimal.ZERO);
        wmsInveRedeemDetail.setRedeem_sms_code("");
        wmsInveRedeemDetail.setRedeem_type("4");
        wmsInveRedeemDetail.setRemain_interest_days(redeemMap.get("remain_interest_days") != null ? new BigDecimal(redeemMap.get("remain_interest_days").toString()) : BigDecimal.ZERO);
        wmsInveRedeemDetail.setRemark(redeemMap.get("remark") != null ? redeemMap.get("remark").toString() : "");
        wmsInveRedeemDetail.setReward_income_rate(redeemMap.get("reward_income_rate") != null ? new BigDecimal(redeemMap.get("reward_income_rate").toString()) : BigDecimal.ZERO);
        wmsInveRedeemDetail.setWms_inve_transa_id(Integer.parseInt(wmsInveMap.get("wms_inve_transa_id").toString()));
        wmsInveRedeemDetail.setWms_inve_transa_prod_id(Integer.parseInt(wmsInveMap.get("wms_inve_transa_prod_id").toString()));

        // 调用赎回申请
        wmsinveredeemService.saveWmsInveRedeem(bean, user, wmsInveRedeemDetail, vo, 1);

    }

    /**
     * 
     * @Title: getCustomerBankCard
     * @Description: 根据身份证号，银行卡号获取银行卡相关信息
     * @param map
     * @return 
     * @author: zhangmingjian
     * @time:2017年6月9日 下午1:06:42
     * history:
     * 1、2017年6月9日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> getCustomerBankCard(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return wmsInvePhoneDao.getIncomeCardPadList(map);
    }

    /**
     * 
     * @Title: verifyBillIsSingle
     * @Description: 根据传入的信息验证是否可以进行赎回再签
     * @param categoryLis 上单产品信息
     * @param wms_inve_transa_id 赎回单据上单表主键(多个以“,”分隔)
     * @return 返回错误提示信息
     * @author: DongHao
     * @time:2017年9月20日 下午1:03:40
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#verifyBillIsSingle(java.util.List, java.lang.String)
     * history:
     * 1、2017年9月20日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> verifyBillIsSingle(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, List<Map<String, Object>> categoryLis, String wms_inve_transa_id)
    {
        // 定义返回结果map集合
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // (1)验证上单表主键对应的单据状态是否都为收益中
        // 判断单据主键是否为空
        if (wms_inve_transa_id != null && !"".equals(wms_inve_transa_id))
        {
            // 将上单表主键字符串转换成list数组
            List<String> ids = Arrays.asList(wms_inve_transa_id.split(","));

            List<Map<String, Object>> wmsInveTransas = wmsInvePhoneDao.getWmsInveTransaByIds(ids);

            // 判断传入上单单据主键是否与获取对应的单据信息的数量相同
            if (ids.size() == wmsInveTransas.size())
            {
                resultMap.put("ret_code", "000");
                resultMap.put("ret_msg", "验证通过");
            }
            else
            {
                resultMap.put("ret_code", "150");
                resultMap.put("ret_msg", "已选赎回单据存在重复提交问题，请核对单据后在提交。");
            }
        }

        // (2)验证上单所购买的产品是否符合要求
        if ("000".equals(resultMap.get("ret_code").toString()))
        {
            for (Map<String, Object> map : categoryLis)
            {
                wmsInveTransaProd.setWms_inve_pruduct_category_id(Double.valueOf(map.get("wms_inve_pruduct_category_id").toString()).intValue());
                wmsInveTransaProd.setProduct_account(new BigDecimal(map.get("product_account").toString()));
                resultMap = wmsInveSignedApplicationService.isVerifyCategory(wmsInveTransa, wmsInveTransaProd);

                // 判断返回结果是否通过
                if (!"000".equals(resultMap.get("ret_code").toString()))
                {
                    break;
                }

            }
        }

        return resultMap;
    }

    /**
     * 
     * @Title: genCustomerBuyNewCategoryData
     * @Description: 生成可以购买新产品的客户信息数据(获取满足条件的单据收益中、赎回中、已完成的数据(时间开始2016-08-01 -- 结束日期2017-07-31))
     *               1. 2016-08-01 - 2017-07-31
     *               2. 2016-09-01 - 2017-08-01
     * @return 返回map信息提示
     * @author: DongHao
     * @throws Exception 
     * @time:2017年9月2日 上午9:22:27
     * @see com.zx.emanage.inve.service.IWmsInvePhoneService#genCustomerBuyNewCategoryData()
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> genCustomerBuyNewCategoryData() throws Exception
    {
        //定义返回结果的map集合
        Map<String, Object> resMap = new HashMap<String, Object>();
        
        //定义查询条件参数map集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        
        //(1) 获取所有满足指定日期时间段内的收益中、赎回中、已完成的数据(时间开始2016-08-01 -- 结束日期2017-07-31)
        paramsMap.put("startDate", "2016-08-01");
        paramsMap.put("endDate", "2017-07-31");
        List<WmsInveTransa> customerLis = wmsInvePhoneDao.getCustomerByParamsMap(paramsMap);
        
        //获取时间范围内的所有日期
        List<String> monthList = getStartToEndDateDesc(paramsMap);
        
        //验证通过的客户
        List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
        
        //遍历客户进行验证是否满足购买新产品的要求
        for(WmsInveTransa transa : customerLis)
        {
            //验证指定客户信息id
            customerList = getSatisfyConditionCustomer(transa.getId_card(), customerList, paramsMap, monthList);
        }
        
        //(2) 获取所有满足指定日期时间段内的收益中、赎回中、已完成的数据(时间开始2016-09-01 -- 结束日期2017-08-01)
        paramsMap.put("startDate", "2016-09-01");
        paramsMap.put("endDate", "2017-08-01");
        monthList = getStartToEndDateDesc(paramsMap);
        customerLis = wmsInvePhoneDao.getCustomerByParamsMap(paramsMap);
        
        //遍历客户进行验证是否满足购买新产品的要求
        for(WmsInveTransa transa : customerLis)
        {
            //验证指定客户信息id
            customerList = getSatisfyConditionCustomer(transa.getId_card(), customerList, paramsMap, monthList);
        }
        
        //将得到的满足购买新产品的客户信息插入到表中
        wmsInvePhoneDao.saveCustomerByNewCategory(customerList);
        
        resMap.put("ret_code", "000");
        resMap.put("ret_code", "操作成功");
        
        return resMap;
    }
    
    /**
     * 
     * @Title: getSatisfyConditionCustomer
     * @Description: 验证指定身份证号的客户是否满足购买新产品的要求
     * @param id_card 身份证号
     * @param customerList 满足条件的客户
     * @param paramsMap 查询条件参数
     * @param monthList 时间日期
     * @return 返回可以购买的客户信息对象集合
     * @author: DongHao
     * @time:2017年9月2日 上午9:46:00
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private List<Map<String, Object>> getSatisfyConditionCustomer(String id_card, List<Map<String, Object>> customerList, Map<String, Object> paramsMap
                                                                                                                        , List<String> monthList)
    {
        //设置需要查询客户的身份证号
        paramsMap.put("id_card", id_card);
        
        //获取指定身份证号满足条件的单据
        List<WmsInveTransa> satisfyConditionCustomerBills = wmsInvePhoneDao.getSatisfyConditionCustomer(paramsMap);
        
        //验证客户是否满足购买新产品的要求
        Map<String, Object> customerMap = checkCustomerIsQualified(satisfyConditionCustomerBills, paramsMap, monthList);
        
        //判断客户信息是否为空
        if(customerMap != null && customerMap.size() > 0)
        {
            //判断验证通过的客户在验证果果的客户集合中是否存在,不存在则添加,存在则不继续添加
            if(!isExistList(customerList, customerMap))
            {
                //将满足条件的客户信息添加到满足客户信息集合中
                customerList.add(customerMap);
            }
        }
        
        return customerList;
    }
    
    /**
     * 
     * @Title: isExistList
     * @Description: 验证通过的数据集合中的客户与刚验证通过的客户是否存在
     * @param customerList 验证通过的客户数据集合
     * @param customerMap 验证通过的数据
     * @return 返回布尔类型的变量,返回true则说明验证通过的数据集合中已经存在,false说明不存在
     * @author: DongHao
     * @time:2017年9月2日 上午11:30:45
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private boolean isExistList(List<Map<String, Object>> customerList, Map<String, Object> customerMap)
    {
        //定义布尔类型的变量用来标记当前客户是否已经存在了满足的客户集合表中
        boolean bool = false;
        
        //获取验证通过的客户的身份证号
        String id_card = customerMap.get("id_card").toString();
        
        //遍历循环已经验证通过的客户集合中是否存在,如果存在则不继续添加到数据库表中,则设置为false
        for(Map<String, Object> map : customerList)
        {
            String id_card_number = map.get("id_card").toString();
            
            //判断身份证号是否相同
            if(id_card_number.equals(id_card))
            {
                bool = true;
                break;
            }
        }
        
        return bool;
    }
    
    /**
     * 
     * @Title: checkCustomerIsQualified
     * @Description: 验证客户是否满足购买新产品的需求
     * @param list 数据集合
     * @param paramsMap 查询参数集合
     * @param monthList 时间月份集合
     * @return 返回客户信息map集合
     * @author: DongHao
     * @time:2017年9月2日 上午10:35:25
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private Map<String, Object> checkCustomerIsQualified(List<WmsInveTransa> list, Map<String, Object> paramsMap, List<String> monthList)
    {
        //定义返回的客户map集合信息
        Map<String, Object> customerMap = new HashMap<String, Object>();
        
        //定义布尔类型的变量用来表示该客户是否允许购买新产品,true允许购买,false不允许购买,默认false
        boolean bool = false;
        
        //遍历月份验证每个月份客户是否都存在有收益过的单据
        for(String month : monthList)
        {
            //获取指定月份的上单单据数据
            List<WmsInveTransa> transaLis = getWmsInveTransaByMonth(list, month);
            
            //验证指定月份的单据中是否满足要求
            bool = checkMonthInve(transaLis, month);
            
            //判断返回的结果是否为false,如果为false,则说明该月份断过,则不满足要求,则停止循环不允许购买新产品
            if(!bool)
            {
                break;
            }
            
        }
        
        //判断是否验证通过,验证通过则需要设置客户信息
        if(bool)
        {
            //设置需要插入的客户基础信息
            WmsInveTransa wmsTransa = list.get(0);
            
            //设置客户姓名
            customerMap.put("cus_name", wmsTransa.getCus_name());
            //设置客户身份证号
            customerMap.put("id_card", wmsTransa.getId_card());
            //设置客户手机号
            customerMap.put("mobile_phone", wmsTransa.getMobile_phone());
            //设置客户crm客户id
            customerMap.put("costomer_id", wmsTransa.getCostomer_id());
            //设置客户归属业务员id
            customerMap.put("bel_salesman_id_id", wmsTransa.getBel_salesman_id_id());
            //设置客户归属部门经理经理id
            customerMap.put("bel_department_manager_id", wmsTransa.getBel_department_manager_id());
            //设置客户归属中分总
            customerMap.put("bel_center_manager_id", wmsTransa.getBel_center_manager_id());
            //设置客户归属副总
            customerMap.put("bel_vice_manager_id", wmsTransa.getBel_vice_general_manager_id());
            //设置客户归属总
            customerMap.put("bel_gen_manager_id", wmsTransa.getBel_general_manager_id());
        }
        
        return customerMap;
    }
    
    /**
     * 
     * @Title: checkMonthInve
     * @Description: 验证指定月份是否满足要求
     * @param transaLis 指定月份的单据集合
     * @param month 指定月份
     * @return 返回布尔类型的数据true则符合要求,false不符合要求
     * @author: DongHao
     * @time:2017年9月2日 上午10:43:54
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private boolean checkMonthInve(List<WmsInveTransa> transaLis, String month)
    {
        //定义布尔类型的变量,用来标记客户是否在当前月份中的单据中满足要求,满足要求返回true,否则返回false,默认是false
        boolean bool = false;
        
        //遍历上单单据集合,逐个单据进行验证是否存在收益中的单据,存在收益中的单据则验证通过,如果不存在收益中的单据还需要进行赎回单据的验证
        for(WmsInveTransa transa : transaLis)
        {
            //判断单据中是否存在收益中的,存在收益中的则符合要求
            if("4".equals(transa.getData_status()))
            {
                //达到满足条件则设置bool为true表示验证通过, 并结束循环
                bool = true;
                break;
            }
        }
        
        //不存在收益中的单据则需要验证赎回中、已完成的单据是否满足要求
        if(!bool)
        {
            //判断单据集合中是否存在当月进行赎回的单据,存在则验证通过,不存在则验证不通过
            if(transaLis != null && transaLis.size() > 0)
            {
                //定义查询参数的map集合
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                
               //设置需要查询的单据
                paramsMap.put("transaLis", transaLis);
                
                //设置需要查询的赎回月份
                paramsMap.put("month", month);
                
                //获取当前传入的月份是否存在赎回单据,如果存在赎回单据则说明该月份满足条件
                int count = wmsInvePhoneDao.getWmsInveTransaInfoByLis(paramsMap);
                
                //如果本月发生赎回则验证通过
                if(count > 0)
                {
                    //达到满足条件则设置bool为true表示验证通过
                    bool = true;
                }
            }
            
        }
        
        return bool;
    }
    
    /**
     * 
     * @Title: getWmsInveTransaByMonth
     * @Description: 获取指定月份的数据
     * @param list 数据集合
     * @param month 月份
     * @return 返回指定月份的数据
     * @author: DongHao
     * @time:2017年9月2日 上午10:42:13
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private List<WmsInveTransa> getWmsInveTransaByMonth(List<WmsInveTransa> list, String month)
    {
        
        //定义list集合变量,用来存放满足时间的上单单据
        List<WmsInveTransa> lis = new ArrayList<>();
        
        //遍历上单数据集合取出指定时间上单的单据重新封装到list集合中
        for(WmsInveTransa transa : list)
        {
            try
            {
                //将月份由字符串类型转换成date类型
                java.util.Date endDate = DateUtil.string2Date(month, "yyyy-MM");
                //将上单日期的格式转换成年月的格式
                java.util.Date startDate = DateUtil.string2Date(DateUtil.date2String(transa.getDate_of_payment(), "yyyy-MM"), "yyyy-MM");
                
                //判断上单月份小于等于当前月份
                if(startDate.compareTo(endDate) != 1)
                {
                    //将满足条件的上单单据添加到list集合中,便于接下来的验证
                    lis.add(transa);
                }
                
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        
        return lis;
    }
    
    /**
     * 
     * @Title: getStartToEndDateDesc
     * @Description: 获取时间段内的所有月份
     * @param paramsMap 查询条件参数
     * @return 返回list集合
     * @throws Exception 
     * @author: DongHao
     * @time:2017年9月2日 上午10:31:04
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private List<String> getStartToEndDateDesc(Map<String, Object> paramsMap) throws Exception
    {
        //获取开始日期和结束日期
        String startDate = paramsMap.get("endDate").toString();
        String endDate = paramsMap.get("startDate").toString();
        
        //分别将开始日期和结束日期转换为date时间类型
        java.util.Date startDateTime = DateUtil.string2Date(startDate, "yyyy-MM-dd");
        java.util.Date endDateTime = DateUtil.string2Date(endDate, "yyyy-MM-dd");
        
        //进行获取起始时间和结束时间范围内的所有日期(日期格式为yyyy-MM)
        List<String> list = getMonthDesc(null, DateUtil.date2String(startDateTime, "yyyy-MM"), DateUtil.date2String(endDateTime, "yyyy-MM"));
        
        return list;
    }

    /**
     * 
     * @Title: getMonthDesc
     * @Description: 递归取出时间段内的时间
     * @param list 存放时间的数据集合
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @return 返回时间数据集合
     * @author: DongHao
     * @time:2017年9月2日 上午10:31:31
     * history:
     * 1、2017年9月2日 DongHao 创建方法
     */
    private List<String> getMonthDesc(List<String> list, String startDate, String endDate)
    {

        // 判断集合是否为null,为空则创建list对象
        if (list == null)
        {
            list = new ArrayList<String>();
        }

        // 向结果集合中添加map集合
        list.add(startDate);

        // 判断日期是否与截止日期相等,如果相等则返回结果集,不继续进行递归获取日期,否则进行递归继续获取日期
        if (startDate.equals(endDate))
        {
            return list;
        }
        else
        {
            // 将传入的日期进行每次递减一个月
            Calendar calendar = Calendar.getInstance();
            try
            {
                calendar.setTime(DateUtil.string2Date(startDate, "yyyy-MM"));
                calendar.add(Calendar.MONTH, -1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return getMonthDesc(list, DateUtil.date2String(new Date(calendar.getTimeInMillis()), "yyyy-MM"), endDate);
        }
    }
    
}
