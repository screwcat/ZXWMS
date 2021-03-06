package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveCurrentRateDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveRedeemAttDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveRedeemRecordDao;
import com.zx.emanage.inve.persist.WmsInveReplaceDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveRedeemService;
import com.zx.emanage.inve.service.IWmsInveSalaryPreTotalService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.util.export.PoiMergeAbstract;
import com.zx.emanage.inve.util.redeem.CountIncomeRedeemData;
import com.zx.emanage.inve.util.redeem.CountIncomeRedeemFactory;
import com.zx.emanage.inve.util.transa.SignHelper;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemRecordVO;
import com.zx.emanage.inve.vo.WmsInveRedeemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveRedeemVO;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveCurrentRate;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemAtt;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsInveRedeemServiceImpl
 * @Description: 赎回相关操作
 * @author Administrator
 * @date 2016年11月23日
 * @version V1.0
 * history:
 * 1、2016年11月23日 Administrator 创建文件
 */
@Service("wmsinveredeemService")
public class WmsInveRedeemServiceImpl implements IWmsInveRedeemService {
    private static Logger log = LoggerFactory.getLogger(WmsInveRedeemServiceImpl.class);

    @Autowired
    private WmsInveTransaDao wmsinvetransaDao;

    @Autowired
    private WmsInveRedeemDao wmsinveredeemDao;

    @Autowired
    private WmsInveRedeemDetailDao wmsInveRedeemDetailDao;

    @Autowired
    private WmsInveTransaCardDao wmsInveTransaCardDao;

    @Autowired
    IWmsInveWorkFlowService approveInveWorkFlowService;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsInveRedeemDetailDao wmsinveredeemdetailDao;

    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;

    @Autowired
    private WmsInveRedeemAttDao wmsInveRedeemAttDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;

    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;

    @Autowired
    private SysUserRoleDao sysuserroleDao_m;

    @Autowired
    private WmsInveTransaMatchDao wmsinvetransamatchDao;

    @Autowired
    private SysSpecialUserDao specialUserDao;

    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;// 产品信息表

    @Autowired
    private PmPersonnelDao pmPersonnelDao;
    @Autowired
    private WmsInveReplaceDao wmsinvereplaceDao;

    @Autowired
    private SysDeptDao sysDeptDao;
    @Autowired
    private SysRoleDao sysroleDao_m;
    @Autowired
    private IWmsInveTransaProtocolService wmsInveTransaProtocolService;
    @Autowired
    private WmsInveTransaLogDao wmsInveTransaLogDao;// 上单操作表
    @Autowired
    private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;// 收益表
    @Autowired
    private WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao;// 赎回本金表
    @Autowired
    private WmsInveTransaProdDao wmsinvetransaprodDao; // 上单产品信息表

    @Autowired
    private WmsInveClerkProtocolDao wmsInveClerkProtocolDao; // 上单产品信息表

    @Autowired
    private IWmsInveClerkProtocolService wmsInveClerkProtocolService; // 柜员合同表

    @Autowired
    private IWmsInveTransaService wmsInveTransaService; // 柜员合同表
    
    @Autowired
    private IWmsInveSalaryPreTotalService wmsInveSalaryPreTotalService;// 绩效工资service

    @Autowired
    private WmsInveCurrentRateDao wmsInveCurrentRateDao; // 活期收益dao

    @Autowired
    private WmsInveTransaCrepkgDao wmsInveTransaCrepkgDao;

    @Autowired
    private WmsInveClerkProtocolTransaCrepkgDao wmsInveClerkProtocolTransaCrepkgdDao;

    @Autowired
    private WmsInveRedeemRecordDao wmsInveRedeemRecordDao;

    @Override
    public Map<String, Object> getListWithoutPagingForQuerySH(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        boolean invisible = false;
        int userId = 0;
        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            if (role.equals("理财业务接待专员"))
            {
                // 只能看见自己创建的单据
                paramMap.put("create_user_id", user.getUserId());
            }
            if (role.equals("理财业务接待部主管"))
            {
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
                invisible = true;
                userId = user.getUserId();
            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", queryInfo.getMenu_id());
                paramMap.put("deptIds_user_id", user.getUserId());
            }
            if (role.equals("理财财务专员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
            if (role.equals("财务柜员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("理财财务主管"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
        }
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_begin()))
        {
            paramMap.put("redeem_date_begin", queryInfo.getRedeem_date_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_end()))
        {
            paramMap.put("redeem_date_end", queryInfo.getRedeem_date_end());
        }
        if (StringUtils.isNotBlank(queryInfo.getIs_special_approval()))
        {
            paramMap.put("is_special_approval", queryInfo.getIs_special_approval());
        }
        if (StringUtils.isNotBlank(queryInfo.getSpecial_approval_leader_id()))
        {
            paramMap.put("special_approval_leader_id", queryInfo.getSpecial_approval_leader_id());
        }
        if (StringUtils.isNotBlank(queryInfo.getData_status_name()))
        {
            paramMap.put("data_status_name", queryInfo.getData_status_name());
        }
        if (StringUtils.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // jinzhm修改，添加归属业务员查询条件
        if (StringUtils.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinveredeemDao.searchForQuerySH(paramMap);

        // 实现对数据屏蔽
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        for (Map<String, Object> map : list)
        {
            map.remove("id_card");
        }
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * @Titile:getListWithPaging Description:实现理财赎回三级审批页面数据显示
     * @param queryInfo
     * @param user
     * @author hancd
     * @date 2015年12月24日
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "3");
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        // 主要作用：区分变更假赎回和真正赎回 数据的区分 :0代表正常赎1代表产品变更赎回
        paramMap.put("redeem_type", "0");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.search(paramMap);
        // 实现对数据屏蔽
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveredeemDao.findCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * @Titile:getListWithPaging Description:实现理财赎回三级审批页面数据导出
     * @param queryInfo
     * @param user
     * @author hancd
     * @date 2015年12月24日
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "4");
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("userid", user.getUserId());
        // 主要作用：区分变更假赎回和真正赎回 数据的区分 :0代表正常赎1代表产品变更赎回
        paramMap.put("redeem_type", "0");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.searchSpecialRedemptionList(paramMap);
        // 实现对数据屏蔽
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * 赎回查询列表显示
     */
    @Override
    public Map<String, Object> getListWithPagingForQuerySH(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        boolean invisible = false;
        int userId = 0;
        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            if (role.equals("理财业务接待专员"))
            {
                // 只能看见自己创建的单据
                paramMap.put("create_user_id", user.getUserId());
            }
            if (role.equals("理财业务接待部主管"))
            {
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
                invisible = true;
                userId = user.getUserId();
            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", queryInfo.getMenu_id());
                paramMap.put("deptIds_user_id", user.getUserId());
            }
            if (role.equals("理财财务专员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
            if (role.equals("财务柜员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("理财财务主管"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
        }
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_begin()))
        {
            paramMap.put("redeem_date_begin", queryInfo.getRedeem_date_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_end()))
        {
            paramMap.put("redeem_date_end", queryInfo.getRedeem_date_end());
        }
        if (StringUtils.isNotBlank(queryInfo.getIs_special_approval()))
        {
            paramMap.put("is_special_approval", queryInfo.getIs_special_approval());
        }
        if (StringUtils.isNotBlank(queryInfo.getSpecial_approval_leader_id()))
        {
            paramMap.put("special_approval_leader_id", queryInfo.getSpecial_approval_leader_id());
        }
        if (StringUtils.isNotBlank(queryInfo.getData_status_name()))
        {
            paramMap.put("data_status_name", queryInfo.getData_status_name());
        }
        if (StringUtils.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // jinzhm修改，添加归属业务员查询条件
        if (StringUtils.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }

        // zmj修改，添加上单单据来源查询条件
        if (StringUtils.isNotBlank(queryInfo.getBill_source()))
        {
            
            paramMap.put("bill_source", queryInfo.getBill_source().equals("-1") ? null : queryInfo.getBill_source());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinveredeemDao.searchForQuerySH(paramMap);
        // 实现对数据屏蔽
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        for (Map<String, Object> map : list)
        {
            map.remove("id_card");
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveredeemDao.findCountForQuerySH(paramMap), list);
        return bean.getGridData();
    }

    @Override
    public WmsInveRedeem getInfoByPK(Integer wms_inve_redeem_id)
    {
        return wmsinveredeemDao.get(wms_inve_redeem_id);
    }

    @Override
    @Transactional
    public String save(WmsInveRedeem bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveredeemDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveRedeem bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveredeemDao.update(bean); // update a record replace columns,
                                             // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }

        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveRedeem() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag
     *            , true is exclude primary key, false is include primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveRedeem> getListByEntity(WmsInveRedeem queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveRedeem> beanList = wmsinveredeemDao.getListByEntity(queryInfo);
        return beanList;
    }

    public Map<String, Object> getWmsInveredeemList(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "2");
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (queryInfo.getIs_finish() != null && !"".equals(queryInfo.getIs_finish()))
        {
            paramMap.put("is_finish", queryInfo.getIs_finish());
        }
        if (queryInfo.getId_card() != null && !"".equals(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        /**
         * 因产品变更需求 修改此处 2015-10-22 添加redeem_type标识 区分是赎回还是产品变更单据
         */
        paramMap.put("redeem_type", "0");// 标识 主要作用：区分变更假赎回和真正赎回 数据的区分
                                         // :0代表正常赎1代表产品变更赎回
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.selectRedeemList(paramMap);
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveredeemDao.selectRedeemListCount(paramMap), list);
        return bean.getGridData();
    }

    @Override
    @Transactional
    public List<WmsInveTransaCard> getCardListForSelect(Integer wms_inve_redeem_id)
    {
        WmsInveRedeemDetail wmsInveRedeemDetailSearch = new WmsInveRedeemDetail();
        wmsInveRedeemDetailSearch.setWms_inve_redeem_id(wms_inve_redeem_id);
        List<WmsInveRedeemDetail> wmsInveRedeemDetailList = wmsInveRedeemDetailDao.getListByEntity(wmsInveRedeemDetailSearch);
        List<WmsInveTransaCard> wmsInveTransaCardListForSelect = new ArrayList<>();
        for (int i = 0; i < wmsInveRedeemDetailList.size(); i++)
        {
            WmsInveRedeemDetail wmsInveRedeemDetail = wmsInveRedeemDetailList.get(i);
            WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
            WmsInveTransaCard wmsInveTransaCard1 = new WmsInveTransaCard();
            wmsInveTransaCard1.setCard_owner_name("（收益卡）" + wmsInveTransaProd.getCard_owner_name());
            wmsInveTransaCard1.setCard_no(wmsInveTransaProd.getCard_no());
            wmsInveTransaCardListForSelect.add(wmsInveTransaCard1);
        }
        WmsInveTransaCard wmsInveTransaCard2 = new WmsInveTransaCard();
        wmsInveTransaCard2.setCard_owner_name("请选择");
        wmsInveTransaCard2.setCard_no("-1");
        wmsInveTransaCardListForSelect.add(0, wmsInveTransaCard2);

        return wmsInveTransaCardListForSelect;

    }

    @Override
    @Transactional
    public String saveWmsInveRedeem(WmsInveRedeem bean, UserBean user, WmsInveRedeemDetail wmsInveRedeemDetail, WmsInveRedeemVO wmsInveRedeemVO, Integer... type)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        // 申请赎回日期由页面用户选择(原来默认为当前日期)
        // java.sql.Date redeem_date = new
        // java.sql.Date(System.currentTimeMillis());
        String is_fully_redeemed = "";
        BigDecimal redeem_apply_total_amount = new BigDecimal(0);// 申请总赎回金额
        BigDecimal total_invest_amount = new BigDecimal(0);// 客户总投资金额
        // BigDecimal total_due_income = new BigDecimal(0);// 总应得收益
        BigDecimal total_management_fee = new BigDecimal(0);// 总应付管理费
        BigDecimal total_deduct_money = new BigDecimal(0);// 总其他扣款金额
        BigDecimal total_deduct_tax_point = new BigDecimal(0);// 总是否扣税点金额

        is_fully_redeemed = wmsInveRedeemDetail.getIs_fully_redeemed();
        // 计算总赎回金额，总应得收益，总管理费
        redeem_apply_total_amount = redeem_apply_total_amount.add(wmsInveRedeemDetail.getRedeem_amount());
        total_management_fee = total_management_fee.add(wmsInveRedeemDetail.getManagement_fee());
        // total_due_income.add(wmsInveRedeemDetail.getDue_income());
        if (wmsInveRedeemDetail.getDeduct_money() != null)
        {
            total_deduct_money = total_deduct_money.add(wmsInveRedeemDetail.getDeduct_money());
        }
        if (wmsInveRedeemDetail.getDeduct_tax_point() != null)
        {
            total_deduct_tax_point = total_deduct_tax_point.add(wmsInveRedeemDetail.getDeduct_tax_point());
        }

        // 获取上单协议表同一客户的所有单据
        List<Map<String, Object>> list = wmsinvetransaprotocolDao.getTotalInvestAmount(bean.getWms_inve_customer_id());
        for (Map<String, Object> map : list)
        {
            if (map.get("product_account") != null)
            {
                // 计算客户总投资金额
                total_invest_amount = total_invest_amount.add((BigDecimal) map.get("product_account"));
            }
        }
        // 判断单据表是否完全赎回
        if (is_fully_redeemed.indexOf('0') < 0)
        {
            bean.setIs_fully_redeemed("1");
            bean.setIs_finish("2");
            bean.setIs_protocol_finish("2");
        }
        else
        {
            // 未全部赎回
            bean.setIs_fully_redeemed("0");
            bean.setIs_finish("0");
            bean.setIs_protocol_finish("0");
        }
        // 计算实际总赎回合计
        bean.setRedeem_apply_total_amount(redeem_apply_total_amount);
        // bean.setTotal_due_income(total_due_income);
        bean.setTotal_invest_amount(total_invest_amount);
        bean.setTotal_management_fee(total_management_fee);
        bean.setTotal_deduct_money(total_deduct_money); // 总其他扣款金额
        bean.setTotal_deduct_tax_point(total_deduct_tax_point);// 总是否扣税点金额
        // bean.setRedeem_date(redeem_date);
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setLast_update_user_id(user.getUserId());
        bean.setCreate_user_dept_id(user.getDeptId());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_timestamp(sysTime);
        bean.setBill_code(CodeNoUtil.getReturnRedeemCode());

        // 改
        WmsInveTransa transa = wmsinvetransaDao.get(wmsInveRedeemDetail.getWms_inve_transa_id());
        if(!"4".equals(transa.getData_status())){
            throw new RuntimeException();
        }
        
        // jinzhm 2017-01-06修改
        // 是否是预约续期标记，默认是false
        boolean isOrderExtend = false;
        // 如果是预约续期
        if ("1".equals(transa.getIs_order_extend()))
        {
            isOrderExtend = true;
        }

        bean.setId_card(transa.getId_card());
        bean.setSalesman_id(transa.getBel_salesman_id_id());
        bean.setSalesman_dept_id(transa.getBel_salesman_dept_id());
        bean.setIs_special_approval("0");
        bean.setIs_payback("0");
        bean.setEnable_flag("1");
        // 如果是预约赎回
        if ("1".equals(wmsInveRedeemVO.getIs_order_redeem()))
        {
            // 赎回信息设置是预约赎回
            bean.setIs_order_redeem("1");
        }
        // 保存赎回单据表信息
        try
        {
            wmsinveredeemDao.save(bean);
        }
        catch (Exception e)
        {
            return "error";
        }
        // 赎回详细
        wmsInveRedeemDetail.setCreate_user_id(user.getUserId());
        wmsInveRedeemDetail.setLast_update_user_id(user.getUserId());
        wmsInveRedeemDetail.setCreate_user_dept_id(user.getDeptId());
        wmsInveRedeemDetail.setCreate_timestamp(sysTime);
        wmsInveRedeemDetail.setLast_update_timestamp(sysTime);
        wmsInveRedeemDetail.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        wmsInveRedeemDetail.setIs_protocol_finish("0");
        wmsInveRedeemDetail.setEnable_flag("1");
        wmsInveRedeemDetail.setDue_income(wmsInveRedeemDetail.getPayable_basic_income().add(wmsInveRedeemDetail.getPayable_reward_income()));
        if (wmsInveRedeemDetail.getDeduct_money() == null || "".equals(wmsInveRedeemDetail.getDeduct_money()))
        {
            wmsInveRedeemDetail.setDeduct_money(new BigDecimal(0));
        }
        if (wmsInveRedeemDetail.getDeduct_tax_point() == null || "".equals(wmsInveRedeemDetail.getDeduct_tax_point()))
        {
            wmsInveRedeemDetail.setDeduct_tax_point(new BigDecimal(0));

        }
        // 保存赎回明细表信息
        int wms_inve_redeem_detail_id;
        try
        {
            wmsinveredeemdetailDao.save(wmsInveRedeemDetail);
            wms_inve_redeem_detail_id = wmsInveRedeemDetail.getWms_inve_redeem_detail_id();
        }
        catch (Exception e)
        {
            return "error";
        }
        WmsInveTransa wmsInveTransa = new WmsInveTransa();
        wmsInveTransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
        // 非预约赎回0 改变状态5赎回中
        if ("0".equals(wmsInveRedeemVO.getIs_order_redeem()))
        {
            wmsInveTransa.setData_status("5");
            wmsInveTransa.setIs_order_redeem(wmsInveRedeemVO.getIs_order_redeem());
        }
        else if ("1".equals(wmsInveRedeemVO.getIs_order_redeem()))
        {
            wmsInveTransa.setIs_order_redeem(wmsInveRedeemVO.getIs_order_redeem());
        }

        // 预约续期标识设置为0(未预约续期)
        wmsInveTransa.setIs_order_extend("0");
        // 修改上单信息表单据状态
        try
        {
            wmsinvetransaDao.updateForRedeem(wmsInveTransa);
        }
        catch (Exception e)
        {
            return "error";
        }

        List<WmsInveRedeemPrincipalDetail> listBean = JsonUtil.jsonArrayToList(wmsInveRedeemVO.getRedeemGridData(), WmsInveRedeemPrincipalDetail.class);
        // 保存赎回本金表
        for (WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail : listBean)
        {
            wmsInveRedeemPrincipalDetail.setWms_inve_redeem_detail_id(wms_inve_redeem_detail_id);
            wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal(0));
            // 保存赎回明细表信息
            try
            {
                wmsInveRedeemPrincipalDetailDao.save(wmsInveRedeemPrincipalDetail);
            }
            catch (Exception e)
            {
                return "error";
            }
        }

        // 清空验证码
        WmsInveTransaProd wmsInveTransaProd = new WmsInveTransaProd();
        wmsInveTransaProd.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
        try
        {
            wmsinvetransaprodDao.updateSmsCard(wmsInveTransaProd);
        }
        catch (Exception e)
        {
            return "error";
        }

        // 单据赎回启动流程
        // 判断类型是否存在值,存在值则说明是pad端发生的赎回,pad端发生赎回申请,直接到回款任务节点
        if(type != null && type.length > 0)
        {
            wmsInveWorkFlowService.startPadRedeemProcess(user.getUserId().toString(), bean.getWms_inve_redeem_id().toString(),WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        }
        else
        {
            wmsInveWorkFlowService.startFinancialWorkFlow(user.getUserId().toString(), bean.getWms_inve_redeem_id().toString(), bean.getSalesman_dept_id().toString(), String.valueOf(bean.getSalesman_id()), WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, wmsInveRedeemDetail.getWms_inve_transa_id());
        }

        // jinzhm添加
        // 是取消预约续期
        if (isOrderExtend)
        {
            // 封装协议查询信息数据
            WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
            protocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
            // 查询协议信息（根据主键倒序排序）
            List<WmsInveTransaProtocol> protocolList = wmsinvetransaprotocolDao.getListByEntity(protocol);
            // 预约续期取消时对客户收益处理
            CountIncomeFactory.getCountIncome(protocolList.get(0))
                              .handleTransaIncomeForCancelNewExtend(wmsInveRedeemDetail.getWms_inve_transa_id(), user);

            // 非股东单据没有生成预约续期的协议表和柜员业务表数据 不用执行删除
            if (wmsInveTransaService.verifyIsShareholderBill(wmsInveRedeemDetail.getWms_inve_transa_id()))
            {
                wmsInveClerkProtocolService.deleteClerkDataAndClerkProtocol(wmsInveRedeemDetail.getWms_inve_transa_id());
            }
        }
        // 如果是预约赎回
        if ("1".equals(wmsInveRedeemVO.getIs_order_redeem()))
        {
            // 封装赎回时客户收益处理请求对象
            CountIncomeRedeemData redeemData = new CountIncomeRedeemData();
            // 封装赎回明细信息
            redeemData.setRedeemDetail(wmsInveRedeemDetail);
            // 封装赎回信息
            redeemData.setRedeem(bean);
            // 封装登录用户信息
            redeemData.setUserBean(user);
            // 处理预约赎回时客户收益
            CountIncomeRedeemFactory.getCountIncomeRedeem(redeemData).orderRedeem(redeemData);
        }

        // 判断单据表是否完全赎回
        if (!(is_fully_redeemed.indexOf('0') < 0))
        {
            // 预约部分赎回 生成新合同
            if ("1".equals(wmsInveRedeemVO.getIs_order_redeem()))
            {
                // 非股东单据 生成柜员合同
                if (wmsInveTransaService.verifyIsShareholderBill(wmsInveRedeemDetail.getWms_inve_transa_id()))
                {
                    // 生成柜员合同
                    wmsInveClerkProtocolService.saveWmsInveClerkProtocolRedeem(bean, wmsInveRedeemDetail, user);
                }

                // 选择电邮合同时 并且更新标志为1(新邮箱与原邮箱不一致) 需要更新上单表 客户电邮地址
                if ("2".equals(bean.getGet_credit_type()) && "1".equals(wmsInveRedeemVO.getIs_update()))
                {
                    WmsInveTransa wmsinvetransa = new WmsInveTransa();
                    wmsinvetransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                    wmsinvetransa.setCustomer_email(wmsInveRedeemVO.getCustomer_email());
                    wmsinvetransaDao.update(wmsinvetransa);
                }
            }
            // 当天赎回
            else
            {
                // 非股东单据(生成柜员协议和柜员业务单据 匹配债权)
                if (wmsInveTransaService.verifyIsShareholderBill(wmsInveRedeemDetail.getWms_inve_transa_id()))
                {
                    // 生成柜员协议和柜员业务单据
                    wmsInveClerkProtocolService.saveWmsInveClerkProtocolRedeem(bean, wmsInveRedeemDetail, user);
                    // 根据赎回主键和上单主键查询柜员合同
                    WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
                    wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                    wmsInveClerkProtocol.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                    wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
                    WmsInveTransaProd wmsinvetransaprod = wmsinvetransaprodDao.getForJEGL(wmsInveClerkProtocol.getWms_inve_transa_id());

                    CreditBusiness.getInstance().matchForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), wmsinvetransaprod.getWms_inve_pruduct_category_id(), wmsInveClerkProtocol.getProduct_account(), bean.getRedeem_date(), user);
                }
            }
        }

        return resStr;
    }

    @Override
    public Map<String, Object> checkReturnList(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "1");
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {

            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());

        List<Map<String, Object>> list = wmsinveredeemDao.checkReturnList(paramMap);
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveredeemDao.findCountCheckReturn(paramMap), list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> checkReturnListWithout(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "1");
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.checkReturnList(paramMap);
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * @Title: checkNeedRedeemSplit
     * @Description: 判断是否需要进行赎回拆分
     * @param redeemDetail 赎回明细信息
     * @return 需要赎回拆分时返回赎回本金表记录，不需要拆分时返回空集合
     * @author: jinzhiming
     * @time:2016年11月4日 上午8:24:42
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private List<WmsInveRedeemPrincipalDetail> checkNeedRedeemSplit(WmsInveRedeemDetail redeemDetail)
    {
        // 通过赎回明细信息表主键查询赎回本金表中记录
        WmsInveRedeemPrincipalDetail principalDetail = new WmsInveRedeemPrincipalDetail();
        principalDetail.setWms_inve_redeem_detail_id(redeemDetail.getWms_inve_redeem_detail_id());
        List<WmsInveRedeemPrincipalDetail> principalDetailList = wmsInveRedeemPrincipalDetailDao.getListByEntity(principalDetail);

        // 判断赎回本金表查询出的记录个数，如果记录个数=1的话，肯定不需要进行赎回拆分
        if (principalDetailList.size() != 1)
        {
            boolean haveManagerFee = false;// 有管理费标志，有收取管理费的记录时为true
            boolean noManagerFee = false;// 没有管理费标志，有不收取管理费的记录时为true
            for (int i = 0; i < principalDetailList.size(); i++)
            {
                principalDetail = principalDetailList.get(i);
                if (principalDetail.getTotal_management_fee().compareTo(BigDecimal.ZERO) > 0)
                {
                    // 管理费用大于0，标志有收取管理费记录
                    haveManagerFee = true;
                }
                if (principalDetail.getTotal_management_fee().compareTo(BigDecimal.ZERO) == 0)
                {
                    // 管理费用等于0，标志有不收取管理费的记录
                    noManagerFee = true;
                }
            }
            // 如果有收取服务费的记录且也有不收取服务费的记录，需要处理赎回拆分
            if (haveManagerFee && noManagerFee)
            {
                return principalDetailList;
            }
        }
        return new ArrayList<WmsInveRedeemPrincipalDetail>();// 默认返回空集合
    }

    /**
     * @Title: saveRedeemSplit
     * @Description: 修改复制生成的赎回信息，修改相应数据后保存
     * @param redeemNew 复制出的赎回信息
     * @param redeem 原赎回信息
     * @param principalDetailList 赎回本金表数据
     * @param taxPoint 税点
     * @param i 外层循环数
     * @return 返回税点
     * @author: jinzhiming
     * @time:2016年11月4日 上午10:18:55
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private BigDecimal saveRedeemSplit(WmsInveRedeem redeemNew, WmsInveRedeem redeem, List<WmsInveRedeemPrincipalDetail> principalDetailList, BigDecimal taxPoint, int i)
    {
        WmsInveRedeemPrincipalDetail principalDetail = principalDetailList.get(i);// 赎回本金信息
        redeemNew.setWms_inve_redeem_id(null);// id设置成null
        redeemNew.setBill_code(redeem.getBill_code() + "_" + (i + 1));// 赎回单据编号设置
        redeemNew.setRedeem_apply_total_amount(principalDetail.getPrincipal_amount());// 申请赎回金额=本金表中的本金
        redeemNew.setTotal_management_fee(principalDetail.getTotal_management_fee());// 服务费=本金表中服务费
        // 根据违约金金额设置是否扣除违约金字段
        if (redeemNew.getTotal_management_fee().compareTo(BigDecimal.ZERO) > 0)
        {
            redeemNew.setIs_take_off_damages("1");
        }
        else
        {
            redeemNew.setIs_take_off_damages("0");
        }
        redeemNew.setOrg_wms_inve_redeem_id(redeem.getWms_inve_redeem_id());// 设置原赎回信息主键
        if ("1".equals(redeemNew.getIs_fully_redeemed()))
        {// 如果是全部赎回的情况，将最后复制出的记录设置成全部赎回，其他复制出的记录设置成部分赎回
            redeemNew.setIs_fully_redeemed("0");// 设置成部分赎回
            if (i == principalDetailList.size() - 1)
            {
                redeemNew.setIs_fully_redeemed("1");
            }
        }
        // 税点赋值；税点拆分时，最后一个税点用之前拆分的来减去获得。
        if (i == principalDetailList.size() - 1)
        {
            redeemNew.setTotal_deduct_tax_point(redeem.getTotal_deduct_tax_point().subtract(taxPoint));
        }
        else
        {
            // 税点拆分；税点 = 税点*（赎回本金表中赎回本金/原赎回信息表中赎回本金）
            redeemNew.setTotal_deduct_tax_point(redeem.getTotal_deduct_tax_point().multiply(principalDetail.getPrincipal_amount().divide(redeem.getRedeem_apply_total_amount(), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP));
            taxPoint = taxPoint.add(redeemNew.getTotal_deduct_tax_point());
        }
        // 总收益金额 = 基本收益+公益收益+奖励收益-已付收益
        redeemNew.setTotal_due_income(principalDetail.getIncome_amount().add(principalDetail.getExtend_amount()).add(principalDetail.getReward_income()).subtract(principalDetail.getPaid_income_amount()));
        // 实际总应收金额 = 本金（需要判断是否是只打收益不打本金）+总应得收益-服务费-税点(拆分后)
        if ("1".equals(principalDetail.getPrincipal_type()))
        {
            // 如果是收益本金都打
            redeemNew.setRedeem_reality_total_amount(principalDetail.getPrincipal_amount().add(redeemNew.getTotal_due_income()).subtract(principalDetail.getTotal_management_fee()).subtract(redeemNew.getTotal_deduct_tax_point()));
        }
        else
        {// 如果是只打收益不打本金
            redeemNew.setRedeem_reality_total_amount(redeemNew.getTotal_due_income().subtract(principalDetail.getTotal_management_fee()).subtract(redeemNew.getTotal_deduct_tax_point()));
        }
        redeemNew.setRedeem_reality_total_amount_upper(digitUppercase(redeemNew.getRedeem_reality_total_amount().doubleValue(), true));
        wmsinveredeemDao.save(redeemNew);// 保存新的赎回信息
        return taxPoint;
    }

    /**
     * @Title: saveRedeemDetailSplit
     * @Description: 修改复制生成的赎回明细信息并保存
     * @param redeemDetailNew 复制生成的赎回明细信息
     * @param redeemDetail 原赎回明细信息
     * @param taxPoint 税点
     * @param i 外层循环数
     * @author: jinzhiming
     * @time:2016年11月4日 上午10:33:05
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private void saveRedeemDetailSplit(WmsInveRedeemDetail redeemDetailNew, WmsInveRedeemDetail redeemDetail, WmsInveRedeem redeemNew, List<WmsInveRedeemPrincipalDetail> principalDetailList, int i)
    {
        WmsInveRedeemPrincipalDetail principalDetail = principalDetailList.get(i);// 赎回本金信息
        redeemDetailNew.setWms_inve_redeem_detail_id(null);// id设置成null
        redeemDetailNew.setWms_inve_redeem_id(redeemNew.getWms_inve_redeem_id());// 赎回表主键
        redeemDetailNew.setRedeem_amount(redeemNew.getRedeem_apply_total_amount());// 赎回金额
        redeemDetailNew.setDue_income(redeemNew.getTotal_due_income());// 应得收益
        redeemDetailNew.setManagement_fee(redeemNew.getTotal_management_fee());// 应付管理费
        redeemDetailNew.setIs_fully_redeemed(redeemNew.getIs_fully_redeemed());// 是否完全赎回
        redeemDetailNew.setDeduct_tax_point(redeemNew.getTotal_deduct_tax_point());// 税点
        redeemDetailNew.setPaid_income(principalDetail.getPaid_income_amount());// 已付收益合计
        redeemDetailNew.setExtend_income(principalDetail.getExtend_amount());// 公益收益合计
        redeemDetailNew.setPayable_reward_income(principalDetail.getReward_income());// 应付奖励收益
        redeemDetailNew.setPayable_basic_income(principalDetail.getIncome_amount());// 应付基本收益
        redeemDetailNew.setRemark(redeemDetail.getRemark());// 备注
        redeemDetailNew.setCurrent_income(principalDetail.getCurrent_income());// 活期收益
        wmsinveredeemdetailDao.save(redeemDetailNew);// 保存新的赎回明细信息
    }

    /**
     * @Title: redeemSplit
     * @Description: 赎回拆分：根据本金表中判断是否需要对赎回进行拆分，需要拆分时，做拆分处理
     * @param redeem 赎回信息
     * @param redeemDetail 赎回明细信息 
     * @author: jinzhiming
     * @time:2016年11月4日 上午8:19:35
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private void redeemSplit(Integer redeemId)
    {
        WmsInveRedeem redeem = wmsinveredeemDao.get(redeemId);
        WmsInveRedeemDetail redeemDetail = new WmsInveRedeemDetail();
        redeemDetail.setWms_inve_redeem_id(redeemId);
        List<WmsInveRedeemDetail> redeemDetailList = wmsinveredeemdetailDao.getListByEntity(redeemDetail);
        redeemDetail = redeemDetailList.get(0);

        // 判断是否需要赎回拆分，如果返回结果不是空集合说明需要进行赎回拆分
        List<WmsInveRedeemPrincipalDetail> principalDetailList = checkNeedRedeemSplit(redeemDetail);
        if (!principalDetailList.isEmpty())
        {
            // 声明一些变量
            Gson gson = new Gson();

            WmsInveRedeemPrincipalDetail principalDetail = null;// 原赎回本金信息对象
            WmsInveRedeem redeemNew = null;// 新赎回信息对象
            WmsInveRedeemDetail redeemDetailNew = null;// 新赎回明细信息对象
            WmsInveRedeemPrincipalDetail principalDetailNew = null;// 新赎回本金信息对象

            String json = "";
            BigDecimal taxPoint = BigDecimal.ZERO;// 税点金额，在拆分时会不断增加
            /**
             * 1. 将赎回信息根据赎回本金表中记录个数进行复制并赋值
             * 2. 将赎回明细信息根据赎回本金表中记录个数进行复制并赋值
             * 3. 将赎回本金表中的记录赋值一份保存到库中，原赎回本金信息的赎回明细表主键修改成新生成的赎回明细表主键
             * 4. 将原赎回信息状态设置成8（已拆分）状态
             */
            for (int i = 0; i < principalDetailList.size(); i++)
            {
                principalDetail = principalDetailList.get(i);
                // 复制一份赎回信息，修改相应值并保存
                json = gson.toJson(redeem);
                redeemNew = gson.fromJson(json, WmsInveRedeem.class);
                taxPoint = saveRedeemSplit(redeemNew, redeem, principalDetailList, taxPoint, i);// 保存拆分后的赎回信息

                // 复制一份赎回明细信息，修改相应值并保存
                json = gson.toJson(redeemDetail);
                redeemDetailNew = gson.fromJson(json, WmsInveRedeemDetail.class);
                saveRedeemDetailSplit(redeemDetailNew, redeemDetail, redeemNew, principalDetailList, i);

                // 复制一份赎回本金信息，修改原赎回本金信息的赎回明细信息主键，将新生成的赎回明细信息直接保存
                json = gson.toJson(principalDetail);
                principalDetailNew = gson.fromJson(json, WmsInveRedeemPrincipalDetail.class);
                principalDetailNew.setWms_inve_redeem_principal_detail_id(null);
                wmsInveRedeemPrincipalDetailDao.save(principalDetailNew);// 新生成的赎回本金信息直接保存
                principalDetail.setWms_inve_redeem_detail_id(redeemDetailNew.getWms_inve_redeem_detail_id());// 修改原赎回本金信息的赎回明细信息主键
                wmsInveRedeemPrincipalDetailDao.update(principalDetail);// 修改原赎回本金信息的赎回明细信息主键
            }

            redeem.setData_status("8");// 将原赎回单据状态设置成8（已拆分）
            wmsinveredeemDao.update(redeem);
        }
    }

    /**
     * 实现赎回回款操作
     */
    @Override
    @Transactional
    public String saveRedeemPayBackInfo(WmsInveRedeem bean, UserBean user, String fileArr, WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String resStr = "success";
        Date end_of_date = null;
        Date begin_of_date = null;
        Date end_date_addDay = null;
        Date nowDate = null;
        // 删除附件
        wmsInveRedeemAttDao.delete(bean.getWms_inve_redeem_id());
        List<WmsInveRedeemAtt> wmsInveRedeemAttList = JsonUtil.jsonArrayToList(fileArr, WmsInveRedeemAtt.class);// 将附件集合字符串转换成实体类的形式
        // 保存附件wms_inve_transa_prod_id
        for (WmsInveRedeemAtt wmsInveRedeemAtt : wmsInveRedeemAttList)
        {
            wmsInveRedeemAtt.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
            wmsInveRedeemAttDao.save(wmsInveRedeemAtt);
        }

        Calendar nowcalendar = Calendar.getInstance();
        int year = nowcalendar.get(Calendar.YEAR);
        int month = nowcalendar.get(Calendar.MONTH) + 1;
        int day = nowcalendar.get(Calendar.DATE);
        String today = "" + year + "-" + month + "-" + day + "";
        bean.setPayback_date(java.sql.Date.valueOf(today));
        bean.setPayback_operator_id(user.getUserId());
        bean.setIs_payback("1");
        bean.setIs_payback("1");
        bean.setIs_finish("1");
        bean.setIs_protocol_finish("1");
        bean.setIs_take_off_damages("0");

        WmsInveRedeemDetail wmsInveRedeemDetailSearch = new WmsInveRedeemDetail();
        wmsInveRedeemDetailSearch.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        List<WmsInveRedeemDetail> wmsInveRedeemDetailList = wmsInveRedeemDetailDao.getListByEntity(wmsInveRedeemDetailSearch);

        // 根据是否收取违约服务费设置赎回单据信息中的是否收取违约服务费字段
        WmsInveRedeemPrincipalDetail principalDetail = new WmsInveRedeemPrincipalDetail();
        principalDetail.setWms_inve_redeem_detail_id(wmsInveRedeemDetailList.get(0).getWms_inve_redeem_detail_id());
        List<WmsInveRedeemPrincipalDetail> principalDetailList = wmsInveRedeemPrincipalDetailDao.getListByEntity(principalDetail);
        for (WmsInveRedeemPrincipalDetail detail : principalDetailList)
        {
            if (detail.getTotal_management_fee().compareTo(BigDecimal.ZERO) > 0)
            {
                bean.setIs_take_off_damages("1");
                break;
            }
        }
        // 根据主键查询原始上单单据 该实体类目前用于更新柜员协议表
        WmsInveTransa wmsinvetransa = wmsinvetransaDao.get(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());
        // 支付时间
        begin_of_date = wmsinvetransa.getDate_of_payment();
        // 存储已回款标记信息
        // 存储债权已调整
        // 存储协议已签署
        wmsinveredeemDao.update(bean);

        WmsInveRedeem wmsInveRedeem = wmsinveredeemDao.get(bean.getWms_inve_redeem_id());

        List<String> wmsInveTransaIdList = new ArrayList<>();
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < wmsInveRedeemDetailList.size(); i++)
        {
            // 赎回明细中对应的单据完全赎回，则更新匹配和协议的赎回单据ID
            WmsInveRedeemDetail wmsInveRedeemDetail = wmsInveRedeemDetailList.get(i);
            if (wmsInveRedeemDetail.getIs_fully_redeemed().equals("1"))
            {
                BigDecimal sy = wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());
                WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
                protocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                protocol.setWms_inve_redeem_id(0);
                protocol.setEnable_flag("1");
                WmsInveTransaProtocol transaprotocol = wmsinvetransaprotocolDao.getProtocolByCondition(protocol);
                // 完全赎回的单据，把匹配过的债权变成失效状态
                WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                wmsInveTransaMatchSearch.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                wmsinvetransamatchDao.updateRedeem(wmsInveTransaMatchSearch);
                // 完全赎回的单据，把协议变成失效状态
                WmsInveTransaProtocol updatebean = new WmsInveTransaProtocol();
                updatebean.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                updatebean.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                updatebean.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                updatebean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsinvetransaprotocolDao.updateforback(updatebean);
                // 修改上单信息表信息
                // try {
                    WmsInveTransa wmsInveTransa = new WmsInveTransa();
                wmsInveTransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                wmsInveTransa.setData_status("6");// 已完成
                    wmsInveTransa.setProduct_total_count_lower(sy);
                wmsInveTransa.setProduct_total_count_upper(digitUppercase(sy.doubleValue()));
                    wmsInveTransa.setLast_update_user_id(user.getUserId());
                    wmsInveTransa.setLast_update_timestamp(sysTime);
                    wmsinvetransaDao.updateInve_transaForJEZF(wmsInveTransa);
                    WmsInveTransaProd wmsTransaProd = new WmsInveTransaProd();
                wmsTransaProd.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                    wmsTransaProd.setProduct_account(sy);
                    wmsInveTransaProdDao.update(wmsTransaProd);
                // } catch (Exception e) {
                // resStr = "error";
                // }
                /*
                 * //完全赎回的单据,并且把占用的债权全部归还 List<WmsInveTransaMatch>
                 * wmsInveTransaMatchList
                 * =wmsinvetransamatchDao.getListByEntity(
                 * wmsInveTransaMatchSearch); for(WmsInveTransaMatch
                 * m:wmsInveTransaMatchList){ WmsFinaCreRepay wRepay = new
                 * WmsFinaCreRepay();
                 * wRepay.setWms_fina_cre_pay_id(m.getWms_fina_cre_repay_id());
                 * wRepay.setMatching_creditor(m.getAssign_account());
                 * wmsFinaCreRepayDao.updateRedeem(wRepay); }
                 */
                wmsInveTransaIdList.add(wmsInveRedeemDetail.getWms_inve_transa_id().toString());
                /* 如果是完全赎回需要对收益表和上单操作日志表的预处理进行处理-- baisong 2016/4/19 */

                transaprotocol.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());

                // jinzhm添加
                // 如果是预约赎回
                if ("1".equals(wmsInveRedeem.getIs_order_redeem()))
                {
                    // 封装赎回数据对象
                    CountIncomeRedeemData redeemData = new CountIncomeRedeemData();
                    // 赎回信息
                    redeemData.setRedeem(wmsInveRedeem);
                    // 赎回明细信息
                    redeemData.setRedeemDetail(wmsInveRedeemDetail);
                    // 登录用户信息
                    redeemData.setUserBean(user);
                    // 预约赎回财务回款后客户收益影响处理
                    CountIncomeRedeemFactory.getCountIncomeRedeem(redeemData).finishOrderRedeem(redeemData);
                }
                // 是正常赎回
                else
                {
                    // 计算生成收益
                    wmsInveTransaProtocolService.SethandleIncomeAndLogInfoSH(transaprotocol, user);
                }

                // 全部赎回 财务回款 更新柜员合同表 实际到期日期 并切释放债权
                WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
                wmsInveClerkProtocol.setWms_inve_transa_id(wmsinvetransa.getWms_inve_transa_id());
                wmsInveClerkProtocol.setProduct_account(wmsinvetransa.getProduct_total_count_lower());
                // 根据上单主键 和 上单金额 查询柜员协议(原合同)
                wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
                if (wmsInveClerkProtocol != null)
                {
                    // 更新原合同实际到期日为 赎回日
                    wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(wmsInveRedeem.getRedeem_date().getTime()));

                    wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
                    // 释放债权
                    CreditBusiness.getInstance().releaseMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
                }
            }
            else if (wmsInveRedeemDetail.getIs_fully_redeemed().equals("0"))
            {
                // 把匹配过的债权变成失效状态
                WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                wmsInveTransaMatchSearch.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                wmsinvetransamatchDao.updateRedeem(wmsInveTransaMatchSearch);
                // 把协议变成失效状态
                WmsInveTransaProtocol updatebean = new WmsInveTransaProtocol();
                updatebean.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                updatebean.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                updatebean.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                updatebean.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                wmsinvetransaprotocolDao.updateforback(updatebean);
                updatebean.setLast_update_timestamp(null);
                List<WmsInveTransaProtocol> protocolList = wmsinvetransaprotocolDao.getListByEntity(updatebean);
                // 把剩余投资金额进行重新债权匹配(假)
                BigDecimal sy = wmsInveRedeemDetail.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount());
                // 正常应该是多条，为了简单无论剩余多少金额都只给匹配一条假数据
                WmsInveTransaMatch inveTransaMatch = new WmsInveTransaMatch();
                inveTransaMatch.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                inveTransaMatch.setWms_fina_cre_repay_id(0);
                inveTransaMatch.setCredit_name("测试1");
                inveTransaMatch.setCredit_id_card("111111111111111111");
                inveTransaMatch.setAssign_account(sy);
                inveTransaMatch.setDate_of_assign(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setRepay_begin_date(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setRepay_end_date(new java.sql.Date(System.currentTimeMillis()));
                inveTransaMatch.setProduct_interest_month(new BigDecimal(1));
                inveTransaMatch.setCreate_user_id(user.getUserId());
                inveTransaMatch.setCreate_timestamp(sysTime);
                inveTransaMatch.setLast_update_user_id(user.getUserId());
                inveTransaMatch.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                inveTransaMatch.setEnable_flag("1");
                inveTransaMatch.setWms_inve_redeem_id(0);
                wmsInveTransaMatchDao.save(inveTransaMatch);
                // 把剩余投资金额进行重新打印协议(假)
                WmsInveTransaProtocol wInveTransaProtocol = new WmsInveTransaProtocol();
                wInveTransaProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());// 上单信息表主键
                wInveTransaProtocol.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());// 上单产品表主键
                wInveTransaProtocol.setProt_code(CodeNoUtil.getEnviProCode());// 获取协议编号
                wInveTransaProtocol.setA_name("测试甲");// 甲方姓名
                wInveTransaProtocol.setA_id_card("111111111111111111");// 甲方身份证号
                wInveTransaProtocol.setProduct_account(sy);
                // wInveTransaProtocol.setDate_of_payment(getNow());
                wInveTransaProtocol.setDate_of_payment(protocolList.get(0).getDate_of_payment());
                wInveTransaProtocol.setA_bank("111111111111111111");
                wInveTransaProtocol.setA_number("111111111111111111");
                wInveTransaProtocol.setB_name(wmsinvetransaDao.get(wmsInveRedeemDetail.getWms_inve_transa_id()).getCus_name());// 客户姓名
                wInveTransaProtocol.setB_id_card(wmsinvetransaDao.get(wmsInveRedeemDetail.getWms_inve_transa_id()).getId_card());// 客户身份证号
                wInveTransaProtocol.setCategory_name(wmsInveTransaProdDao.get(wmsInveRedeemDetail.getWms_inve_transa_prod_id()).getCategory_name());
                wInveTransaProtocol.setProduct_deadline(wmsInveTransaProdDao.get(wmsInveRedeemDetail.getWms_inve_transa_prod_id()).getProduct_deadline());
                wInveTransaProtocol.setSign_place("111111111111111111");
                wInveTransaProtocol.setB_data("111111111111111111");
                // wInveTransaProtocol.setEnd_of_date(setDatebyCalendar(wInveTransaProtocol.getDate_of_payment(),
                // wInveTransaProtocol.getProduct_deadline()));//结束日期
                wInveTransaProtocol.setEnd_of_date(protocolList.get(0).getEnd_of_date());// 结束日期
                wInveTransaProtocol.setCreate_user_id(user.getUserId());
                wInveTransaProtocol.setCreate_user_name(user.getRealname());
                wInveTransaProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                wInveTransaProtocol.setLast_update_user_id(user.getUserId());
                wInveTransaProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                wInveTransaProtocol.setEnable_flag("1");
                wInveTransaProtocol.setContact_address("111111111111111111");
                wInveTransaProtocol.setPost_code("111111");
                wInveTransaProtocol.setWms_inve_redeem_id(0);
                wmsinvetransaprotocolDao.save(wInveTransaProtocol);
                wInveTransaProtocol.setWms_inve_redeem_id(wmsInveRedeemDetail.getWms_inve_redeem_id());
                // 修改上单信息表信息
                // try {
                    WmsInveTransa wmsInveTransa = new WmsInveTransa();
                wmsInveTransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                    wmsInveTransa.setData_status("4");
                    wmsInveTransa.setProduct_total_count_lower(sy);
                wmsInveTransa.setProduct_total_count_upper(digitUppercase(sy.doubleValue()));
                    wmsInveTransa.setLast_update_user_id(user.getUserId());
                    wmsInveTransa.setLast_update_timestamp(sysTime);
                    wmsinvetransaDao.updateInve_transaForJEZF(wmsInveTransa);
                // } catch (Exception e) {
                // resStr = "error";
                // }
                // 修改上单产品表信息
                // try {
                    WmsInveTransaProd wmsTransaProd = new WmsInveTransaProd();
                wmsTransaProd.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                    wmsTransaProd.setProduct_account(sy);
                    wmsInveTransaProdDao.update(wmsTransaProd);
                // } catch (Exception e) {
                // resStr = "error";
                // }

                // jinzhm添加
                // 如果是预约赎回
                if ("1".equals(wmsInveRedeem.getIs_order_redeem()))
                {
                    // 封装赎回数据对象
                    CountIncomeRedeemData redeemData = new CountIncomeRedeemData();
                    // 赎回信息
                    redeemData.setRedeem(wmsInveRedeem);
                    // 赎回明细信息
                    redeemData.setRedeemDetail(wmsInveRedeemDetail);
                    // 登录用户信息
                    redeemData.setUserBean(user);
                    // 预约赎回财务回款后客户收益影响处理
                    CountIncomeRedeemFactory.getCountIncomeRedeem(redeemData).finishOrderRedeem(redeemData);

                }
                // 是正常赎回
                else
                {
                    // 把剩余的投资金额的理财单据重新计算收益和生产一条新的日志信息
                    wmsInveTransaProtocolService.SethandleIncomeAndLogInfoSH(wInveTransaProtocol, user);
                }
                // 单据到期日期
                end_of_date = protocolList.get(0).getEnd_of_date();
                // 更新原合同到期日 释放原合同
                WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
                wmsInveClerkProtocol.setWms_inve_transa_id(wmsinvetransa.getWms_inve_transa_id());
                wmsInveClerkProtocol.setProduct_account(wmsinvetransa.getProduct_total_count_lower());
                // 根据上单主键 和 上单金额 查询柜员协议(原合同)
                wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
                if (wmsInveClerkProtocol != null)
                {
                    // 更新原合同实际到期日为 赎回日
                    wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(wmsInveRedeem.getRedeem_date().getTime()));

                    wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
                    // 释放原合同债权
                    CreditBusiness.getInstance().releaseMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);

                }
            }
        }

        // 根据上单主键和赎回主键查询柜员合同
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        WmsInveClerkProtocol wmsInveClerkPublicProtocol = new WmsInveClerkProtocol();

        wmsInveClerkProtocol.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());
        wmsInveClerkPublicProtocol = wmsInveClerkProtocolDao.getWmsInveClerkPublicProtocol(wmsInveClerkProtocol);
        wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
        // 单据存在柜员合同 并且prot_code存在时 去更新上单表合同编码
        if (wmsInveClerkProtocol != null && wmsInveClerkProtocol.getProt_code()!=null && !"".equals(wmsInveClerkProtocol.getProt_code()))
        {
            WmsInveTransa wmsInveTransa = new WmsInveTransa();
            wmsInveTransa.setWms_inve_transa_id(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());
            wmsInveTransa.setFinancial_bill_code(wmsInveClerkProtocol.getProt_code());
            // 根据主键更新单据编号
            wmsinvetransaDao.update(wmsInveTransa);
        }
        // 部分赎回 并且当前回款日期 >= 到期日+一天 需要生成公益合同
        if (wmsInveRedeemDetailList.get(0).getIs_fully_redeemed().equals("0"))
        {
            /* 
             * 当前回款日期 = 到期日+一天 
             * 这时需要作废部分赎回时所生成的柜员合同 并且释放债权
             * 生成新的公益合同 并把原部分赎回生成合同的债权给现在的公益合同
             */
            // 到期日+一天
            end_date_addDay = com.zx.sframe.util.DateUtil.getDate10(com.zx.sframe.util.DateUtil.getDateAddDays(end_of_date, 1));
            nowDate = com.zx.sframe.util.DateUtil.getDate10(new Date());
            // 合同到期日+1天等于当前日期
            if (end_date_addDay.compareTo(nowDate) <= 0)
            {
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                // 生成公益合同
                wmsInveClerkPublicProtocol.setProt_type("1");
                wmsInveClerkPublicProtocol.setCategory_name("公益6号");
                wmsInveClerkPublicProtocol.setExpect_interest("6");
                wmsInveClerkPublicProtocol.setBegin_of_date(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(begin_of_date, Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())).getTime()));
                wmsInveClerkPublicProtocol.setCreate_timestamp(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(begin_of_date, Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())).getTime()));
                wmsInveClerkPublicProtocol.setEnd_of_date(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(end_of_date, 1).getTime()));
                wmsInveClerkPublicProtocol.setProduct_deadline("1");
                wmsInveClerkPublicProtocol.setEnable_flag("1");
                wmsInveClerkPublicProtocol.setCreate_user_id(115);
                // 通过上单主键和柜员合同主键查询出当前匹配生效的债权
                paramsMap.put("wms_inve_transa_id", wmsInveClerkProtocol.getWms_inve_transa_id());
                paramsMap.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
                // 保存柜员合同(公益)
                wmsInveClerkProtocolService.saveWmsInveClerkProtocol(paramsMap, wmsInveClerkPublicProtocol);
                // 更新部分赎回所生成柜员合同的实际到期日期
                if (wmsInveClerkProtocol != null)
                {
                    wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(end_of_date.getTime()));
                    wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
                }
            }
        }

        if (wmsInveRedeem.getIs_protocol_finish().equals("1") || wmsInveRedeem.getIs_protocol_finish().equals("2"))
        {
            wDebtWorkFlowVO.setDebtkey("5");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setBusinessId(wmsInveRedeem.getWms_inve_redeem_id().toString());
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
            wDebtWorkFlowVO.setwInveTransaIds(wmsInveTransaIdList);
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }

        redeemSplit(wmsInveRedeem.getWms_inve_redeem_id());// 赎回拆分

        return resStr;
    }

    /**
     * 保存理财赎回 特批信息
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @Override
    @Transactional
    public String saveRedeemspecialapprovalInfo(WmsInveRedeem bean, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {

        String resStr = "success";
        Calendar nowcalendar = Calendar.getInstance();
        int year = nowcalendar.get(Calendar.YEAR);
        int month = nowcalendar.get(Calendar.MONTH) + 1;
        int day = nowcalendar.get(Calendar.DATE);
        String today = "" + year + "-" + month + "-" + day + "";
        bean.setIs_special_approval("1");
        bean.setSpecial_approval_date(java.sql.Date.valueOf(today));
        bean.setSpecial_approval_operator_id(user.getUserId());
        bean.setSpecial_approval_advice(wDebtWorkFlowVO.getAdvice());
        // 存储赎回特批意见
        wmsinveredeemDao.update(bean);

        WmsInveRedeemDetail wmsInveRedeemDetailSearch = new WmsInveRedeemDetail();
        wmsInveRedeemDetailSearch.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        List<WmsInveRedeemDetail> wmsInveRedeemDetailList = wmsinveredeemdetailDao.getListByEntity(wmsInveRedeemDetailSearch);
        if (bean.getSpecial_approval_result().equals("0"))
        {
            // 如果特批不同意，赎回单据对应的理财单据状态都变为收益中
            for (int i = 0; i < wmsInveRedeemDetailList.size(); i++)
            {
                WmsInveRedeemDetail wmsInveRedeemDetail = wmsInveRedeemDetailList.get(i);
                if (bean.getSpecial_approval_result().equals("0"))
                {
                    WmsInveTransa wmsinvetransa = new WmsInveTransa();
                    wmsinvetransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                    wmsinvetransa.setData_status("4");
                    wmsinvetransa.setIs_order_redeem("0");
                    wmsinvetransaDao.update(wmsinvetransa);
                }
            }
            // jinzhm添加 2016-12-29
            // 查询赎回信息
            WmsInveRedeem redeem = wmsinveredeemDao.get(bean.getWms_inve_redeem_id());
            // 如果是预约赎回
            if ("1".equals(redeem.getIs_order_redeem()))
            {
                // 赎回数据对象
                CountIncomeRedeemData redeemData = new CountIncomeRedeemData();
                // 查询赎回明细信息
                WmsInveRedeemDetail detail = new WmsInveRedeemDetail();
                detail.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                List<WmsInveRedeemDetail> detailList = wmsinveredeemdetailDao.getListByEntity(detail);
                // 查询赎回明细信息不是空集合
                if (!detailList.isEmpty())
                {
                    // 取最新的赎回明细信息
                    detail = detailList.get(0);
                }
                redeemData.setRedeem(redeem);
                redeemData.setRedeemDetail(detail);
                redeemData.setUserBean(user);
                // 取消预约赎回时对客户收益影响处理
                CountIncomeRedeemFactory.getCountIncomeRedeem(redeemData).cancelOrderRedeem(redeemData);
            }

            // 释放债权(先根据赎回主键查询出柜员协议表的协议单据)
            WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
            WmsInveClerkProtocol wmsInveClerkPublicProtocol = new WmsInveClerkProtocol();

            wmsInveClerkProtocol.setWms_inve_redeem_id(redeem.getWms_inve_redeem_id());

            wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);

            if (wmsInveClerkProtocol != null)
            {
                // 特批不同意 失效柜员协议 和 柜员业务单据
                wmsInveClerkProtocolService.disableWmsInveClerkProtocol(redeem.getWms_inve_redeem_id());

                CreditBusiness.getInstance().disableMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
                // 释放债权匹配关系历史表
                CreditBusiness.getInstance().deleteCreditMatchHistory(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
                CreditBusiness.getInstance().deleteCreditMatchHistory(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
            }

            /* 
             * 当前回款日期 = 到期日+一天 
             * 这时需要作废部分赎回时所生成的柜员合同 并且释放债权
             * 生成新的公益合同 并把原部分赎回生成合同的债权给现在的公益合同
             */
            // 通过上单主键查询上单信息
            WmsInveTransa wmsinvetransa = wmsinvetransaDao.get(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());

            WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
            protocol.setWms_inve_transa_id(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());
            protocol.setWms_inve_redeem_id(0);
            protocol.setEnable_flag("1");
            WmsInveTransaProtocol wmsInveTransaProtocol = wmsinvetransaprotocolDao.getProtocolByCondition(protocol);
            // 通过上单主键去查询 未到期并且生效中的合同
            wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetailList.get(0).getWms_inve_transa_id());
            // 公益合同
             wmsInveClerkPublicProtocol = wmsInveClerkProtocolDao.getWmsInveClerkPublicProtocol(wmsInveClerkProtocol);

            // 单据到期日期
            Date end_of_date = wmsInveTransaProtocol.getEnd_of_date();
            // 到期日+一天
            Date end_date_addDay = com.zx.sframe.util.DateUtil.getDate10(com.zx.sframe.util.DateUtil.getDateAddDays(end_of_date, 1));
            Date nowDate = com.zx.sframe.util.DateUtil.getDate10(new Date());
            // 合同到期日+1天等于当前日期
            if (end_date_addDay.compareTo(nowDate) <= 0 && !"1".equals(redeem.getIs_fully_redeemed()))
            {
                // 生成公益合同
                savePublicProtocol(wmsinvetransa, end_of_date, wmsInveClerkPublicProtocol);
            }
        }
        else
        {
            for (int i = 0; i < wmsInveRedeemDetailList.size(); i++)
            {
                WmsInveRedeemDetail wmsInveRedeemDetail = wmsInveRedeemDetailList.get(i);
                WmsInveTransaProd wmsInveTransaProd = new WmsInveTransaProd();
                wmsInveTransaProd.setProduct_account(wmsInveRedeemDetail.getRedeem_amount());
                wmsInveTransaProd.setWms_inve_transa_prod_id(wmsInveRedeemDetail.getWms_inve_transa_prod_id());
                // 财务特批通过后不更新上单产品表的产品金额，待财务回款后进行更新 -- 2016-11-15 关旭
                // wmsInveTransaProdDao.updateRedeem(wmsInveTransaProd);
                WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
                List<WmsInveTransaMatch> wmsInveTransaMatchList = wmsInveTransaMatchDao.getListByEntity(wmsInveTransaMatchSearch);
                for (int j = 0; j < wmsInveTransaMatchList.size(); j++)
                {
                    WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchList.get(j);
                    WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
                    wmsFinaCreRepay.setWms_fina_cre_pay_id(wmsInveTransaMatch.getWms_fina_cre_repay_id());
                    wmsFinaCreRepay.setMatching_creditor(wmsInveTransaMatch.getAssign_account());
                    wmsFinaCreRepayDao.updateRedeem(wmsFinaCreRepay);
                }
            }
        }
        wDebtWorkFlowVO.setDebtkey("6");
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        wDebtWorkFlowVO.setAdvice(bean.getSpecial_approval_advice());
        wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_redeem_id().toString());
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        if (bean.getSpecial_approval_result().equals("1"))
        {
            wDebtWorkFlowVO.setPass("true");
        }
        else if (bean.getSpecial_approval_result().equals("0"))
        {
            wDebtWorkFlowVO.setPass("is_over");

        }

        wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        return resStr;
    }

    @Override
    @Transactional
    public String updateWmsInveRedeem(WmsInveRedeem bean, UserBean user, WmsInveRedeemDetail wmsInveRedeemDetail, String redeemGridData, String taskId, String cxdeptid)
    {
        String resStr = "success";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        String is_fully_redeemed = "";
        BigDecimal redeem_apply_total_amount = new BigDecimal(0);// 申请总赎回金额
        BigDecimal total_due_income = new BigDecimal(0);// 总应得收益
        BigDecimal total_management_fee = new BigDecimal(0);// 总应付管理费

        BigDecimal total_deduct_money = new BigDecimal(0);// 总其他扣款金额
        BigDecimal total_deduct_tax_point = new BigDecimal(0);// 总是否扣税点金额

        is_fully_redeemed = wmsInveRedeemDetail.getIs_fully_redeemed();
        // 计算总赎回金额，总应得收益，总管理费
        redeem_apply_total_amount = redeem_apply_total_amount.add(wmsInveRedeemDetail.getRedeem_amount());
        total_management_fee = total_management_fee.add(wmsInveRedeemDetail.getManagement_fee());
        // total_due_income =
        // total_due_income.add(wmsInveRedeemDetail.getDue_income());
        if (wmsInveRedeemDetail.getDeduct_money() != null)
        {
            total_deduct_money = total_deduct_money.add(wmsInveRedeemDetail.getDeduct_money());
        }
        if (wmsInveRedeemDetail.getDeduct_tax_point() != null)
        {
            total_deduct_tax_point = total_deduct_tax_point.add(wmsInveRedeemDetail.getDeduct_tax_point());
        }

        // 判断是否完全赎回
        if (is_fully_redeemed.indexOf('0') < 0)
        {
            bean.setIs_fully_redeemed("1");
            // 完全赎回时，不用匹配债权，不用打印协议
            bean.setIs_finish("2");
            bean.setIs_protocol_finish("2");
        }
        else
        {
            bean.setIs_fully_redeemed("0");
            bean.setIs_finish("0");
            bean.setIs_protocol_finish("0");
        }
        // BigDecimal redeem_reality_total_amount =
        // redeem_apply_total_amount.add(total_due_income).subtract(total_management_fee);
        // BigDecimal redeem_reality_total_amount =
        // redeem_apply_total_amount.add(total_due_income).subtract(total_management_fee).subtract(total_deduct_tax_point).subtract(total_deduct_money);
        bean.setTotal_deduct_money(total_deduct_money); // 总其他扣款金额
        bean.setTotal_deduct_tax_point(total_deduct_tax_point);// 总是否扣税点金额
        // bean.setRedeem_reality_total_amount(redeem_reality_total_amount);
        bean.setRedeem_apply_total_amount(redeem_apply_total_amount);
        // bean.setTotal_due_income(total_due_income);
        bean.setTotal_management_fee(total_management_fee);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        // 修改赎回单据表信息
        ret = wmsinveredeemDao.update(bean);
        // 清空三级审批状态结果
        ret = wmsinveredeemDao.updateToNull(bean);

        // 判断申请赎回金额是否为0
        if (wmsInveRedeemDetail.getRedeem_amount().compareTo(new BigDecimal(0)) == 0)
        {
            // 删除赎回明细表中申请赎回金额为0的单据
            wmsinveredeemdetailDao.deleteForId(wmsInveRedeemDetail.getWms_inve_redeem_detail_id());
            WmsInveTransa wmsInveTransa = new WmsInveTransa();
            wmsInveTransa.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
            wmsInveTransa.setData_status("4");
            // 修改上单信息表中对应的单据状态
            try
            {
                wmsinvetransaDao.updateForRedeem(wmsInveTransa);
            }
            catch (Exception e)
            {
                return "error";
            }
        }
        else
        {
            wmsInveRedeemDetail.setLast_update_user_id(user.getUserId());
            wmsInveRedeemDetail.setLast_update_timestamp(sysTime);
            wmsInveRedeemDetail.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
            wmsInveRedeemDetail.setDue_income(wmsInveRedeemDetail.getPayable_basic_income().add(wmsInveRedeemDetail.getPayable_reward_income()));
            // 修改赎回明细表单据
            try
            {
                wmsinveredeemdetailDao.update(wmsInveRedeemDetail);
            }
            catch (Exception e)
            {
                return "error";
            }
        }

        try
        {
            wmsInveRedeemPrincipalDetailDao.deleteByDetailId(wmsInveRedeemDetail.getWms_inve_redeem_detail_id());

            List<WmsInveRedeemPrincipalDetail> listBean = JsonUtil.jsonArrayToList(redeemGridData, WmsInveRedeemPrincipalDetail.class);
            // 保存赎回本金表
            for (WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail : listBean)
            {
                wmsInveRedeemPrincipalDetail.setWms_inve_redeem_detail_id(wmsInveRedeemDetail.getWms_inve_redeem_detail_id());
                wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal(0));
                // 保存赎回明细表信息
                wmsInveRedeemPrincipalDetailDao.save(wmsInveRedeemPrincipalDetail);
            }
        }
        catch (Exception e)
        {
            return "error";
        }
        // 赎回退回 部分赎回(并且不是股东单据)才需要生成合同 匹配债权
        if (!(is_fully_redeemed.indexOf('0') < 0) && wmsInveTransaService.verifyIsShareholderBill(wmsInveRedeemDetail.getWms_inve_transa_id()))
        {
            WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
            // 根据上单主键查询原上单金额 赎回修改时 需要使用原上单金额-赎回金额
            WmsInveClerkProtocol protocol = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByTransaIdWithoutCode(wmsInveRedeemDetail.getWms_inve_transa_id());
            // 更新柜员合同表金额和状态
            wmsInveClerkProtocolService.updateWmsInveClerkProtocolRedeem(protocol, bean, wmsInveRedeemDetail, user);
            // 根据赎回主键和上单主键查询柜员合同
            wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
            wmsInveClerkProtocol.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
            wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
            if (wmsInveClerkProtocol != null)
            {
                // 保存柜员业务单据
                wmsInveClerkProtocolService.saveWmsInveClerkData(protocol, bean, wmsInveRedeemDetail, user);
                // 非预约 部分赎回 匹配债权
                if (!("1".equals(bean.getIs_order_redeem())))
                {
                    // 部分赎回时 匹配债权
                    WmsInveTransaProd wmsinvetransaprod = wmsinvetransaprodDao.getForJEGL(wmsInveClerkProtocol.getWms_inve_transa_id());
                    CreditBusiness.getInstance().matchForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), wmsinvetransaprod.getWms_inve_pruduct_category_id(), wmsInveClerkProtocol.getProduct_account(), bean.getRedeem_date(), user);
                }
            }
            // 说明第一次做的全部赎回 未生成柜员合同
            else
            {
                // 生成柜员合同
                wmsInveClerkProtocolService.saveWmsInveClerkProtocolRedeem(bean, wmsInveRedeemDetail, user);
                // 当天赎回
                if (!("1".equals(bean.getIs_order_redeem())))
                {
                    // 根据赎回主键和上单主键查询柜员合同
                    wmsInveClerkProtocol = new WmsInveClerkProtocol();
                    wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
                    wmsInveClerkProtocol.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
                    wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
                    WmsInveTransaProd wmsinvetransaprod = wmsinvetransaprodDao.getForJEGL(wmsInveClerkProtocol.getWms_inve_transa_id());
                    // 匹配债权
                    CreditBusiness.getInstance().matchForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), wmsinvetransaprod.getWms_inve_pruduct_category_id(), wmsInveClerkProtocol.getProduct_account(), bean.getRedeem_date(), user);
                }
            }
        }
        WmsInveDebtWorkFlowVO vo = new WmsInveDebtWorkFlowVO();
        vo.setPass("true");
        // 判断申请总赎回金额是否为0
        if (redeem_apply_total_amount.compareTo(new BigDecimal(0)) == 0)
        {
            vo.setPass("false");
        }
        vo.setDebtkey("4");
        vo.setTaskId(taskId);
        vo.setCxdept_id(cxdeptid);
        vo.setBusinessId(bean.getWms_inve_redeem_id().toString());
        vo.setUserID(user.getUserId().toString());
        vo.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        // TODO
        wmsInveWorkFlowService.publicApproval(vo, wmsInveRedeemDetail.getWms_inve_transa_id());
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String cancelWmsInveRedeem(UserBean user, WmsInveDebtWorkFlowVO vo)
    {
        // 取消预约赎回时，处理客户收益
        handleIncomeForCancelOrderRedeem(Integer.parseInt(vo.getBusinessId()), user);
        String resStr = handleCancelData(vo);
        if (resStr.equals("error"))
        {
            return resStr;
        }
        if (StringUtil.isBlank(vo.getPass()))
        {
            vo.setPass("false");
            vo.setDebtkey("4");
        }
        else
        {
            vo.setPass("false");
            vo.setDebtkey("7");
        }
        vo.setUserID(user.getUserId().toString());
        vo.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        vo.setBusinessId(vo.getWms_inve_redeem_id().toString());
        wmsInveWorkFlowService.publicApproval(vo);
        return resStr;
    }

    /**
     * @Title: handleIncomeForCancelOrderRedeem
     * @Description: 取消赎回时，检查是不是预约赎回，如果是预约赎回需要处理客户收益
     * @param redeemId 赎回表主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2016年12月29日 下午6:11:24
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    private void handleIncomeForCancelOrderRedeem(Integer redeemId, UserBean user)
    {
        // jinzhm添加
        // 查询赎回明细信息
        WmsInveRedeemDetail detail = new WmsInveRedeemDetail();
        detail.setWms_inve_redeem_id(redeemId);
        List<WmsInveRedeemDetail> detailList = wmsinveredeemdetailDao.getListByEntity(detail);
        // 查询赎回明细信息不是空集合
        if (!detailList.isEmpty())
        {
            // 取最新的赎回明细信息
            detail = detailList.get(0);

            WmsInveRedeem redeem = wmsinveredeemDao.get(redeemId);
            // 如果是预约赎回
            if ("1".equals(redeem.getIs_order_redeem()))
            {
                // 封装请求数据
                CountIncomeRedeemData redeemData = new CountIncomeRedeemData();
                redeemData.setUserBean(user);
                redeemData.setRedeem(redeem);
                redeemData.setRedeemDetail(detail);
                // 处理赎回作废对收益的影响
                CountIncomeRedeemFactory.getCountIncomeRedeem(redeemData).cancelOrderRedeem(redeemData);
            }


            // 释放债权(先根据赎回主键查询出柜员协议表的协议单据)
            WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_redeem_id(redeemId);

            wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);

            if (wmsInveClerkProtocol != null)
            {
                // 作废失效柜员协议 和 柜员业务单据
                wmsInveClerkProtocolService.disableWmsInveClerkProtocol(redeemId);
                CreditBusiness.getInstance()
                              .disableMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(),
                                                                 wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(),
                                                                 user);
                // 释放债权匹配关系历史表
                CreditBusiness.getInstance().deleteCreditMatchHistory(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);

            }

            /* 
             * 当前回款日期 = 到期日+一天 
             * 这时需要作废部分赎回时所生成的柜员合同 并且释放债权
             * 生成新的公益合同 并把原部分赎回生成合同的债权给现在的公益合同
             */
            // 通过上单主键查询上单信息

            WmsInveTransa wmsinvetransa = wmsinvetransaDao.get(detail.getWms_inve_transa_id());

            // 支付时间
            Date begin_of_date = wmsinvetransa.getDate_of_payment();

            WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
            protocol.setWms_inve_transa_id(detail.getWms_inve_transa_id());
            protocol.setWms_inve_redeem_id(0);
            protocol.setEnable_flag("1");
            WmsInveTransaProtocol wmsInveTransaProtocol = wmsinvetransaprotocolDao.getProtocolByCondition(protocol);

            // 通过上单主键去查询 未到期并且生效中的合同
            wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_transa_id(detail.getWms_inve_transa_id());
            // 公益合同
            WmsInveClerkProtocol wmsInveClerkPublicProtocol = wmsInveClerkProtocolDao.getWmsInveClerkPublicProtocol(wmsInveClerkProtocol);

            // 单据到期日期
            Date end_of_date = wmsInveTransaProtocol.getEnd_of_date();
            // 到期日+一天
            Date end_date_addDay = com.zx.sframe.util.DateUtil.getDate10(com.zx.sframe.util.DateUtil.getDateAddDays(end_of_date, 1));
            Date nowDate = com.zx.sframe.util.DateUtil.getDate10(new Date());
            // 合同到期日+1天等于当前日期 需要生成公益合同
            if (end_date_addDay.compareTo(nowDate) <= 0 && !"1".equals(redeem.getIs_fully_redeemed()))
            {
                savePublicProtocol(wmsinvetransa, end_of_date, wmsInveClerkPublicProtocol);
            }


        }
    }

    /**
     * @Title: savePublicProtocol
     * @Description: 生成公益合同 更新原合同到期日期
     * @param wmsinvetransa
     * @param end_date_addDay 
     * @author: zhangyunfei
     * @time:2017年5月18日 下午1:13:57
     * history:
     * 1、2017年5月18日 Administrator 创建方法
    */
    private void savePublicProtocol(WmsInveTransa wmsinvetransa, Date end_of_date, WmsInveClerkProtocol wmsInveClerkPublicProtocol)
    {
        // 更新原合同实际到期日期
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        wmsInveClerkProtocol.setWms_inve_transa_id(wmsinvetransa.getWms_inve_transa_id());
        wmsInveClerkProtocol.setProduct_account(wmsinvetransa.getProduct_total_count_lower());
        // 根据上单主键 和 上单金额 查询柜员协议(原合同)
        wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
        if (wmsInveClerkProtocol != null)
        {
            wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(end_of_date.getTime()));
            wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
        }
        // 生成公益合同
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        wmsInveClerkPublicProtocol.setProt_type("1");
        wmsInveClerkPublicProtocol.setProduct_account(wmsinvetransa.getProduct_total_count_lower());
        wmsInveClerkPublicProtocol.setProduct_account_upper(wmsinvetransa.getProduct_total_count_upper());
        wmsInveClerkPublicProtocol.setCategory_name("公益6号");
        wmsInveClerkPublicProtocol.setExpect_interest("6");
        wmsInveClerkPublicProtocol.setBegin_of_date(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(wmsinvetransa.getDate_of_payment(), Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())).getTime()));
        wmsInveClerkPublicProtocol.setCreate_timestamp(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(wmsinvetransa.getDate_of_payment(), Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())).getTime()));
        wmsInveClerkPublicProtocol.setEnd_of_date(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(end_of_date, 1).getTime()));
        wmsInveClerkPublicProtocol.setProduct_deadline("1");
        wmsInveClerkPublicProtocol.setEnable_flag("1");
        wmsInveClerkPublicProtocol.setCreate_user_id(115);
        // 通过上单主键和柜员合同主键查询出当前匹配生效的债权
        paramsMap.put("wms_inve_transa_id", wmsInveClerkProtocol.getWms_inve_transa_id());
        paramsMap.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
        // 保存柜员合同(公益)
        wmsInveClerkProtocolService.saveWmsInveClerkProtocol(paramsMap, wmsInveClerkPublicProtocol);
        
    }

    @Override
    @Transactional
    public String cancelWmsInveRedeemByCw(UserBean user, WmsInveDebtWorkFlowVO vo)
    {
        // 释放债权(先根据赎回主键查询出柜员协议表的协议单据)
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        wmsInveClerkProtocol.setWms_inve_redeem_id(vo.getWms_inve_redeem_id());
        wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
        if (wmsInveClerkProtocol != null)
        {
            // 作废失效柜员协议 和 柜员业务单据
            wmsInveClerkProtocolService.disableWmsInveClerkProtocol(vo.getWms_inve_redeem_id());
            CreditBusiness.getInstance().disableMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(),
                                                                           wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
            // 释放债权匹配关系历史表
            CreditBusiness.getInstance().deleteCreditMatchHistory(wmsInveClerkProtocol.getWms_inve_transa_id(),
                                                                  wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
        }

        // 取消预约赎回时，处理客户收益(先作废柜员合同 在生成公益合同 否则生成公益合同的编号不正确)
        handleIncomeForCancelOrderRedeem(Integer.parseInt(vo.getBusinessId()), user);

        String resStr = handleCancelData(vo);
        if (resStr.equals("error"))
        {
            return resStr;
        }
        vo.setPass("is_over");
        vo.setDebtkey("7");
        vo.setUserID(user.getUserId().toString());
        vo.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        vo.setBusinessId(vo.getWms_inve_redeem_id().toString());
        wmsInveWorkFlowService.publicApproval(vo);
        return resStr;
    }

    protected String handleCancelData(WmsInveDebtWorkFlowVO vo)
    {
        String resStr = "success";
        int ret = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_redeem_id", vo.getBusinessId());
        paramMap.put("sortname", "wms_inve_transa_id");
        paramMap.put("sortorder", "desc");
        // 根据赎回单据表主键查询赎回明细表所有数据
        List<Map<String, Object>> list = wmsinveredeemdetailDao.search(paramMap);
        for (Map<String, Object> map : list)
        {
            WmsInveTransa wmsInveTransa = new WmsInveTransa();
            wmsInveTransa.setWms_inve_transa_id((Integer) map.get("wms_inve_transa_id"));
            wmsInveTransa.setData_status("4");
            wmsInveTransa.setIs_order_redeem("0");// 是否预约赎回为否
            // 根据明细表单据内的信息表主键更改信息表单据状态
            ret = wmsinvetransaDao.updateForRedeem(wmsInveTransa);
            if (ret == 0)
            {
                return "error";
            }
        }
        return resStr;
    }

    /**
     * 财务回款导出excel baisong
     */
    @Override
    public void getWmsInveRedeemListforexl(WmsInveRedeemSearchBeanVO queryInfo, UserBean user, HttpServletResponse response)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "2");
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (queryInfo.getIs_finish() != null && !"".equals(queryInfo.getIs_finish()))
        {
            paramMap.put("is_finish", queryInfo.getIs_finish());
        }
        if (queryInfo.getId_card() != null && !"".equals(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        // paramMap.put("pagesize", queryInfo.getPagesize());
        // paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.selectRedeemListforExcel(paramMap);

        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);

        Map<String, Object> listMap = new HashMap<String, Object>();

        listMap.put("回款表", list);

        ExpertUtil eu = new ExpertUtil();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = "回款打款表_平台版." + sdf.format(dt) + ".xls";
        eu.expertExcel("回款打款表模板.xls", listMap, fileName, null, 1, response);
    }

    @Override
    @Transactional
    public List<WmsInveRedeemAtt> getAttList(Integer wms_inve_redeem_id)
    {
        return wmsInveRedeemAttDao.getAttList(wms_inve_redeem_id);

    }


    /**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式 baisong
     */
    public String digitUppercase(double n)
    {
        String unit[][] = { { "", "万", "亿" }, { "", "拾", "佰", "仟" } };
        return digitUppercase(n, unit);
    }

    /**
     * @Title: digitUppercase
     * @Description: 数字金额大写转换
     * @param n 数字
     * @param flag 是否带元标记，如果是true表示带‘元’，如果是false表示不带‘元’
     * @return 
     * @author: jinzhiming
     * @time:2016年11月1日 下午5:15:14
     * history:
     * 1、2016年11月1日 jinzhiming 创建方法
     */
    public String digitUppercase(double n, boolean flag)
    {
        if (flag)
        {
            String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
            return digitUppercase(n, unit);
        }
        else
        {
            return digitUppercase(n);
        }
    }

    /**
     * @Title: digitUppercase
     * @Description: 数字金额大写转换
     * @param n 数字
     * @param unit 中文金额单位数组
     * @return 
     * @author: jinzhiming
     * @time:2016年11月1日 下午5:16:21
     * history:
     * 1、2016年11月1日 jinzhiming 创建方法
     */
    public String digitUppercase(double n, String unit[][])
    {
        String fraction[] = { "角", "分" };
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

        String head = n < 0 ? "负" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1)
        {
            // s = "整";
            s = "";
        }
        int integerPart = (int) Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 获取当前日期
     * 
     * @return
     */
    private java.sql.Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new java.sql.Date(nowcalendar.getTimeInMillis());
    }

    /**
     * 设置日期 参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private java.sql.Date setDatebyCalendar(java.sql.Date sDate, int i)
    {

        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        if (calendar.get(Calendar.DAY_OF_MONTH) - 1 == 0)
        {
            calendar.add(Calendar.MONTH, +i);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        else
        {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.add(Calendar.MONTH, +i);
            java.util.Date date_ = calendar.getTime();
            date1 = new java.sql.Date(date_.getTime());
        }
        return date1;
    }

    /**
     * @Titile:getListWithPaging Description:实现理财赎回三级审批页面数据显示--给手机审批使用 获取列表
     * @param queryInfo
     * @param user
     * @author baisong
     * @date 2016年1月11日
     */
    @Override
    public Map<String, Object> phoneGetBackInfo(String personnel_shortcode, String searchInfo)
    {
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortcode);
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        if (listp == null || listp.size() == 0)
        {
            return null;
        }
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "3");
        Map<String, Object> paramMaptp = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "4");
        if (searchInfo != null && !"".equals(searchInfo))
        {
            paramMap.put("searchInfo", searchInfo);
        }
        // paramMap.put("user_id", listp.get(0).getPersonnel_id());
        paramMap.put("sortname", "bill_code");
        paramMap.put("sortorder", "desc");

        paramMaptp.put("user_id", listp.get(0).getPersonnel_id());
        paramMaptp.put("sortname", "bill_code");
        paramMaptp.put("sortorder", "desc");

        List<Map<String, Object>> list = wmsinveredeemDao.searchforphone(paramMap);
        List<Map<String, Object>> listtp = wmsinveredeemDao.searchforphone(paramMaptp);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        listtp = wmsInveWorkFlowService.addTaskIdToList(listtp, (List<Integer>) paramMaptp.get("idList"), (List<String>) paramMaptp.get("taskIdList"), (String) paramMaptp.get("processDefinitionKey"));
        if (list != null && listtp != null)
        {
            list.addAll(listtp);
        }
        else if (list == null && listtp != null)
        {
            list = listtp;
        }
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("wait_approval_rows", list == null ? 0 : list.size());
        Map<String, Object> paramMap_ys = new HashMap<>();
        if (searchInfo != null && !"".equals(searchInfo))
        {
            paramMap_ys.put("searchInfo", searchInfo);
        }

        paramMap_ys.put("sortname", "bill_code");
        paramMap_ys.put("sortorder", "desc");
        paramMap_ys.put("user_id", listp.get(0).getPersonnel_id());
        paramMap_ys.put("qry_date", DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<Map<String, Object>> list_ys = wmsinveredeemDao.searchforphone_ys(paramMap_ys);
        if (list != null && list_ys != null)
        {
            list.addAll(list_ys);
        }
        else if (list == null && list_ys != null)
        {
            list = list_ys;
        }

        // 产品转换申请，只有冯总可以查询到数据，目前该功能已废弃--20161018 关旭
        if (PlatformGlobalVar.SYS_PROPERTIES.get("com.zx.usercode").equals(personnel_shortcode))
        {
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("sortname", "t1.apply_datetime");
            paramMap1.put("sortorder", "desc");
            paramMap1.put("pagesize", 100);
            paramMap1.put("offset", 0);
            paramMap1.put("data_status", "2");
            List<Map<String, Object>> list2 = wmsinvereplaceDao.search(paramMap1);
            if (list2 != null && list2.size() > 0)
            {
                list.addAll(list2);
            }
        }
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * @Titile:getListWithPaging Description:实现理财赎回三级审批页面数据显示--给手机审批使用 获取列表审批历史
     * @param queryInfo
     * @param user
     * @author baisong
     * @date 2016年1月11日
     */
    @Override
    public Map<String, Object> phoneGetBackInfoAndHistory(String wms_inve_redeem_id)
    {
        Map<String, Object> map = wmsinveredeemDao.getforphone(Integer.valueOf(wms_inve_redeem_id));
        return map;
    }
    @Override
    public Map<String, Object> getSpecialRedemptionList(WmsInveRedeemSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, user.getUserId().toString(), "4");
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("userid", user.getUserId());
        // 主要作用：区分变更假赎回和真正赎回 数据的区分 :0代表正常赎1代表产品变更赎回
        paramMap.put("user_id", user.getUserId());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemDao.searchSpecialRedemptionList(paramMap);
        // 实现对数据屏蔽
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specialUsers);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"), (List<String>) paramMap.get("taskIdList"), (String) paramMap.get("processDefinitionKey"));
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveredeemDao.searchSpecialRedemptionCount(paramMap), list);
        return bean.getGridData();
    }


        /**
    * @Title: getRedeemApplyinfoByPk
    * @Description: 通过wms_inve_transa_id和wms_inve_redeem_id查询赎回客户详细信息
    * @param wms_inve_transa_id
    * @param wms_inve_redeem_id
    * @param is_return 是否是审核退回 修订进行的查询
    * @return 
    * @author: Administrator
    * @time:2017年5月3日 下午3:05:58
    * @see com.zx.emanage.inve.service.IWmsInveRedeemService#getRedeemApplyinfoByPk(java.lang.Integer, java.lang.Integer, java.lang.String)
    * history:
    * 1、2017年5月3日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getRedeemApplyinfoByPk(Integer wms_inve_transa_id, Integer wms_inve_redeem_id, String is_return)
    {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramMap.put("wms_inve_redeem_id", wms_inve_redeem_id);
        List<Map<String, Object>> redeemApplyinfolist = wmsinvetransaprotocolDao.getRedeemApplyByRedeemId(paramMap);

        paramMap.clear();
        paramMap.put("wms_inve_redeem_id", wms_inve_redeem_id);
        paramMap.put("sortname", "wms_inve_transa_id");
        paramMap.put("sortorder", "desc");
        List<Map<String, Object>> wmsInveRedeemDetailList = wmsinveredeemDao.getRedeemInfoDetailList(paramMap);
        paramMap.clear();
        paramMap.put("wms_inve_redeem_detail_id", wmsInveRedeemDetailList.get(0).get("wms_inve_redeem_detail_id"));
        List<Map<String, Object>> wmsInveRedeemPrincipalDetailList = wmsInveRedeemPrincipalDetailDao.findListByWmsInveRedeemDetailId(paramMap);
        // 获取当前生效的活期产品对象
        WmsInveCurrentRate wmsInveCurrentRate = wmsInveCurrentRateDao.getWmsInveCurrentRateEnable();
        // 如果是审核退回 修订时需要根据当前活期利率重新计算收益
        if ("1".equals(is_return) && wmsInveCurrentRate != null && wmsInveRedeemDetailList.size() > 0)
        {
            // 如果当前活期收益利率发生变化 需要重新计算活期收益
            if (wmsInveRedeemDetailList.get(0).get("current_income_rate") == null || (!wmsInveCurrentRate.getCurrent_rate().toString().equals(wmsInveRedeemDetailList.get(0).get("current_income_rate").toString())))
            {
                BigDecimal sum_current_income = BigDecimal.ZERO;
                for (int i = 0; i < wmsInveRedeemPrincipalDetailList.size(); i++)
                {
                    // 重新计算活期收益
                    BigDecimal current_income = (new BigDecimal(wmsInveRedeemDetailList.get(0).get("days_current_income").toString())).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP).multiply((BigDecimal) (wmsInveRedeemPrincipalDetailList.get(i).get("principal_amount"))).multiply(wmsInveCurrentRate.getCurrent_rate().divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)).setScale(2, RoundingMode.UP);
                    sum_current_income = sum_current_income.add(current_income);
                    wmsInveRedeemPrincipalDetailList.get(i).put("current_income", current_income);
                }
                // 把赎回明细表对应的活期收益总和/活期收益利率更新
                wmsInveRedeemDetailList.get(0).put("current_income_rate", wmsInveCurrentRate.getCurrent_rate());
                wmsInveRedeemDetailList.get(0).put("current_income", sum_current_income);

            }
        }

        paramMap.clear();
        paramMap.put("redeemApplyInfo", redeemApplyinfolist);
        paramMap.put("redeemDetailInfo", wmsInveRedeemDetailList);
        paramMap.put("redeemPrincipalDetailInfo", wmsInveRedeemPrincipalDetailList);
        // prompt_date是赎回申请时显示的提示在该日期前完成赎回操作，为本月的倒数第二天
        Calendar today = Calendar.getInstance();
        paramMap.put("prompt_date", today.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        return paramMap;
    }

    /**
     * 
     * @Title: getRedeemApplyInfoByPkMoa
     * @Description: 通过主键查询赎回单据详细信息
     * @param wms_inve_redeem_id
     * @param personnel_id 当前登录MOA系统的人员id
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月7日 上午11:27:51
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#getRedeemApplyinfoByPk(java.lang.Integer, java.lang.Integer)
     * history:
     * 1、2016年12月7日 Administrator 创建方法
     * 2、2016年12月15日 DongHao 修改方法  方法添加参数当前登录MOA系统的人员id
     */
    @Override
    public Map<String, Object> getRedeemApplyInfoByPkMoa(Integer wms_inve_redeem_id, Integer personnel_id)
    {
        Map<String, Object> map = wmsinveredeemDao.getRedeemInfoDetailListMoa(wms_inve_redeem_id);
        if (map != null)
        {
            if ("1".equals(map.get("data_status") + ""))
            {
                if (personnel_id.toString().equals(map.get("bel_department_manager_id") + ""))
                {
                    map.put("is_approval_info", 1);
                }
                else
                {
                    map.put("is_approval_info", 0);
                }
            }
            else if ("2".equals(map.get("data_status") + ""))
            {
                if (personnel_id.toString().equals(map.get("bel_vice_general_manager_id") + ""))
                {
                    map.put("is_approval_info", 1);
                }
                else
                {
                    map.put("is_approval_info", 0);
                }
            }
            else if ("3".equals(map.get("data_status") + ""))
            {
                if (personnel_id.toString().equals(map.get("bel_general_manager_id") + ""))
                {
                    map.put("is_approval_info", 1);
                }
                else
                {
                    map.put("is_approval_info", 0);
                }
            }
            else
            {
                map.put("is_approval_info", 0);
            }
        }
        return map;
    }


    /**
     * @Title: phoneGetPendingApprovalInfo
     * @Description: app端首页查询代办事项
     * @param personnel_shortCode app端登录人员短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午3:44:25
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#phoneGetPendingApprovalInfo(java.lang.String)
     * history:
     * 1、2016年12月6日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> phoneGetPendingApprovalInfo(String personnel_shortCode, String searchInfo,String version)
    {
        // (1)根据传入的员工的短工号进行获取人员信息
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortCode);
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        if (listp == null || listp.size() == 0)
        {
            return null;
        }

        // (2)获取待审批的数据
        // 三级审批
        Map<String, Object> threeApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "3");
        // 特批
        Map<String, Object> specialApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "4");

        // (3)设置查询条件
        if (searchInfo != null && !"".equals(searchInfo))
        {
            threeApprovalMap.put("searchInfo", searchInfo);
            specialApprovalMap.put("searchInfo", searchInfo);
        }
        threeApprovalMap.put("sortname", "bill_code");
        threeApprovalMap.put("sortorder", "desc");

        specialApprovalMap.put("user_id", listp.get(0).getPersonnel_id());
        specialApprovalMap.put("sortname", "bill_code");
        specialApprovalMap.put("sortorder", "desc");

        // 获取该用户的角色信息
        boolean flag = false;
        
        Integer role_type = 0;// 定义变量用于标记当前登录人的角色是什么
        
        Map<String, Object> paramMap_ys = new HashMap<>();
        List<String> roleList = sysroleDao_m.getUserRoleNameList(listp.get(0).getPersonnel_id());
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                threeApprovalMap.put("roleType", 1);
                specialApprovalMap.put("roleType", 1);
                paramMap_ys.put("roleType", 1);
                role_type = 1;
                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                threeApprovalMap.put("roleType", 3);
                specialApprovalMap.put("roleType", 3);
                paramMap_ys.put("roleType", 3);
                role_type = 3;
                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                threeApprovalMap.put("roleType", 2);
                specialApprovalMap.put("roleType", 2);
                paramMap_ys.put("roleType", 2);
                role_type = 2;
                flag = true;
            }
        }

        if (roleList == null || roleList.size() == 0 || !flag)
        {
            threeApprovalMap.put("roleType", 4);
            specialApprovalMap.put("roleType", 4);
            paramMap_ys.put("roleType", 4);
            role_type = 4;
        }

        List<Map<String, Object>> list = wmsinveredeemDao.searchForPhoneAppData(threeApprovalMap);
        List<Map<String, Object>> listtp = wmsinveredeemDao.searchForPhoneAppData(specialApprovalMap);

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
        resMap.put("wait_approval_rows", list == null ? 0 : list.size());


        if (searchInfo != null && !"".equals(searchInfo))
        {
            paramMap_ys.put("searchInfo", searchInfo);
        }

        paramMap_ys.put("sortname", "bill_code");
        paramMap_ys.put("sortorder", "desc");
        paramMap_ys.put("user_id", listp.get(0).getPersonnel_id());
        paramMap_ys.put("qry_date", DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<Map<String, Object>> list_ys = wmsinveredeemDao.searchForPhoneAppYsData(paramMap_ys);
        if (list != null && list_ys != null)
        {
            list.addAll(list_ys);
        }
        else if (list == null && list_ys != null)
        {
            list = list_ys;
        }
        
        if(version != null && !"".equals(version))
        {
            /**************************向业务单据中添加绩效工资的带我审批的单据******************************/
            // 判断角色是否是副总和总
            if(role_type.compareTo(2) == 0 || role_type.compareTo(3) == 0)
            {
                List<Map<String, Object>> salaryLis = wmsInveSalaryPreTotalService.getSalaryPendingApproval(listp.get(0).getPersonnel_id());
                
                // 判断获取的待我审批的绩效工资的单据集合是否为空
                if(salaryLis != null && salaryLis.size() > 0)
                {
                    // 添加工资的带我审批的单据
                    list.addAll(0, salaryLis);
                }
            }
        }
        
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * @Title: phoneGetRedeemByQueryInfo
     * @Description: 根据条件进行查询赎回单据信息
     * @param personnel_shortCode app端登录的用户短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @param query_type 查询的类型
     * @param page
     * @param page_size
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午5:34:26
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#phoneGetRedeemByQueryInfo(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2016年12月6日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> phoneGetRedeemByQueryInfo(String personnel_shortCode, String searchInfo, String query_type, int page, int page_size, String version)
    {
        // (1)根据传入的员工的短工号进行获取人员信息
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortCode);
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        if (listp == null || listp.size() == 0)
        {
            return null;
        }
        else
        {
            pmPersonnel = listp.get(0);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        int userId = 0;

        // 获取该用户的角色信息
        boolean flag = false;
        List<String> roleList = sysroleDao_m.getUserRoleNameList(pmPersonnel.getPersonnel_id());
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                // 部门经理
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(pmPersonnel.getPersonnel_deptid());
                deptIds.add(pmPersonnel.getPersonnel_deptid());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
                userId = pmPersonnel.getPersonnel_id();
                paramMap.put("roleType", 1);
                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                // 总
                paramMap.put("super_user", 1);
                paramMap.put("roleType", 3);
                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                // 副总
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", pmPersonnel.getPersonnel_id());
                paramMap.put("deptIds_menu", 110);
                paramMap.put("deptIds_user_id", pmPersonnel.getPersonnel_id());
                paramMap.put("roleType", 2);
                flag = true;
            }
        }

        if (roleList == null || roleList.size() == 0 || !flag)
        {
            // 其他
            paramMap.put("salesman_id", pmPersonnel.getPersonnel_id());
            paramMap.put("roleType", 4);
        }

        // 单据的状态条件
        if (query_type != null && !"".equals(query_type))
        {
            if (query_type.equals("003"))
            {
                paramMap.put("query_type", query_type);
            }
            if (query_type.equals("004"))
            {
                paramMap.put("query_type", query_type);
            }
            if (query_type.equals("005"))
            {
                paramMap.put("data_status", 6);
            }
            if (query_type.equals("006"))
            {
                paramMap.put("data_status", 7);
            }

        }
        // 单据检索条件
        if (searchInfo != null && !"".equals(searchInfo))
        {
            paramMap.put("searchInfo", searchInfo);
        }

        paramMap.put("offset", page);
        paramMap.put("pagesize", page_size);
        
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        // 判断查询类型是否为已退回,如果时已退回的单据则不查询赎回的单据
        if(!query_type.equals("007"))
        {
            list = wmsinveredeemDao.phoneGetRedeemByQueryInfo(paramMap);
        }
        
        if(version != null && !"".equals(version))
        {
            /**************************向业务单据中添加绩效工资的带我审批的单据******************************/
            // 添加绩效工资的相关单据信息(查询类型为与我相关003、审批中004、已完成005、已退回007)三种类型的时候获取绩效工资的相关单据
            if(query_type.equals("003") || query_type.equals("004") || query_type.equals("005") || query_type.equals("007"))
            {
                paramMap.put("query_type", query_type);
                paramMap.put("create_salary_user_id", pmPersonnel.getPersonnel_id());
                // 根据参数进行获取绩效工资业务单据信息
                List<Map<String, Object>> salaryLis = wmsInveSalaryPreTotalService.getSalaryBusinessInfos(paramMap);
                
                // 判断绩效工资的单据信息是否为空
                if(salaryLis != null && salaryLis.size() > 0)
                {
                    list.addAll(0, salaryLis);
                }
                
            }
        }
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: wmsPhoneQueryType
     * @Description: 根据员工短工号的信息进行获取对应的信息
     * @param personnel_shortCode
     * @return 
     * @author: DongHao
     * @time:2016年12月8日 下午3:00:45
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#wmsPhoneQueryType(java.lang.String)
     * history:
     * 1、2016年12月8日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> wmsPhoneQueryType(String personnel_shortCode)
    {
        List<Map<String, Object>> resultList = null;
        // (1)根据传入的员工的短工号进行获取人员信息
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortCode);
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        if (listp == null || listp.size() == 0)
        {
            return null;
        }
        else
        {
            pmPersonnel = listp.get(0);
        }

        boolean flag = false;
        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(pmPersonnel.getPersonnel_id());
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                // 部门经理
                resultList = new ArrayList<Map<String, Object>>();

                Map<String, Object> resultMap1 = new HashMap<String, Object>();
                resultMap1.put("bill_type_code", "001");
                resultMap1.put("bill_msg", "全部");
                resultMap1.put("is_default", "0");

                Map<String, Object> resultMap2 = new HashMap<String, Object>();
                resultMap2.put("bill_type_code", "002");
                resultMap2.put("bill_msg", "待我审批");
                resultMap2.put("is_default", "1");

                Map<String, Object> resultMap3 = new HashMap<String, Object>();
                resultMap3.put("bill_type_code", "003");
                resultMap3.put("bill_msg", "与我相关");
                resultMap3.put("is_default", "0");

                Map<String, Object> resultMap4 = new HashMap<String, Object>();
                resultMap4.put("bill_type_code", "004");
                resultMap4.put("bill_msg", "审批中");
                resultMap4.put("is_default", "0");

                Map<String, Object> resultMap5 = new HashMap<String, Object>();
                resultMap5.put("bill_type_code", "005");
                resultMap5.put("bill_msg", "已完成");
                resultMap5.put("is_default", "0");

                Map<String, Object> resultMap6 = new HashMap<String, Object>();
                resultMap6.put("bill_type_code", "006");
                resultMap6.put("bill_msg", "已撤销");
                resultMap6.put("is_default", "0");
                
                Map<String, Object> resultMap7 = new HashMap<String, Object>();
                resultMap7.put("bill_type_code", "007");
                resultMap7.put("bill_msg", "已退回");
                resultMap7.put("is_default", "0");

                resultList.add(resultMap1);// 全部
                resultList.add(resultMap2);// 待我审批
                resultList.add(resultMap3);// 与我相关
                resultList.add(resultMap4);// 审批中
                resultList.add(resultMap5);// 已完成
                resultList.add(resultMap6);// 已撤销
                resultList.add(resultMap7);// 已退回

                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                // 总
                resultList = new ArrayList<Map<String, Object>>();

                Map<String, Object> resultMap1 = new HashMap<String, Object>();
                resultMap1.put("bill_type_code", "001");
                resultMap1.put("bill_msg", "全部");
                resultMap1.put("is_default", "0");

                Map<String, Object> resultMap2 = new HashMap<String, Object>();
                resultMap2.put("bill_type_code", "002");
                resultMap2.put("bill_msg", "待我审批");
                resultMap2.put("is_default", "1");

                Map<String, Object> resultMap3 = new HashMap<String, Object>();
                resultMap3.put("bill_type_code", "003");
                resultMap3.put("bill_msg", "与我相关");
                resultMap3.put("is_default", "0");

                Map<String, Object> resultMap4 = new HashMap<String, Object>();
                resultMap4.put("bill_type_code", "004");
                resultMap4.put("bill_msg", "审批中");
                resultMap4.put("is_default", "0");

                Map<String, Object> resultMap5 = new HashMap<String, Object>();
                resultMap5.put("bill_type_code", "005");
                resultMap5.put("bill_msg", "已完成");
                resultMap5.put("is_default", "0");
               
                Map<String, Object> resultMap6 = new HashMap<String, Object>();
                resultMap6.put("bill_type_code", "006");
                resultMap6.put("bill_msg", "已撤销");
                resultMap6.put("is_default", "0");
                
                resultList.add(resultMap1);// 全部
                resultList.add(resultMap2);// 待我审批
                resultList.add(resultMap3);// 与我相关
                resultList.add(resultMap4);// 审批中
                resultList.add(resultMap5);// 已完成
                resultList.add(resultMap6);// 已撤销

                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                // 副总
                resultList = new ArrayList<Map<String, Object>>();

                Map<String, Object> resultMap1 = new HashMap<String, Object>();
                resultMap1.put("bill_type_code", "001");
                resultMap1.put("bill_msg", "全部");
                resultMap1.put("is_default", "0");

                Map<String, Object> resultMap2 = new HashMap<String, Object>();
                resultMap2.put("bill_type_code", "002");
                resultMap2.put("bill_msg", "待我审批");
                resultMap2.put("is_default", "1");

                Map<String, Object> resultMap3 = new HashMap<String, Object>();
                resultMap3.put("bill_type_code", "003");
                resultMap3.put("bill_msg", "与我相关");
                resultMap3.put("is_default", "0");

                Map<String, Object> resultMap4 = new HashMap<String, Object>();
                resultMap4.put("bill_type_code", "004");
                resultMap4.put("bill_msg", "审批中");
                resultMap4.put("is_default", "0");

                Map<String, Object> resultMap5 = new HashMap<String, Object>();
                resultMap5.put("bill_type_code", "005");
                resultMap5.put("bill_msg", "已完成");
                resultMap5.put("is_default", "0");

                Map<String, Object> resultMap6 = new HashMap<String, Object>();
                resultMap6.put("bill_type_code", "006");
                resultMap6.put("bill_msg", "已撤销");
                resultMap6.put("is_default", "0");

                Map<String, Object> resultMap7 = new HashMap<String, Object>();
                resultMap7.put("bill_type_code", "007");
                resultMap7.put("bill_msg", "已退回");
                resultMap7.put("is_default", "0");
                
                resultList.add(resultMap1);// 全部
                resultList.add(resultMap2);// 带我审批
                resultList.add(resultMap3);// 与我相关
                resultList.add(resultMap4);// 审批中
                resultList.add(resultMap5);// 已完成
                resultList.add(resultMap6);// 已撤销
                resultList.add(resultMap7);// 已退回

                flag = true;
            }
        }

        if (roleList == null || roleList.size() == 0 || !flag)
        {
            // 其他
            resultList = new ArrayList<Map<String, Object>>();

            Map<String, Object> resultMap1 = new HashMap<String, Object>();
            resultMap1.put("bill_type_code", "005");
            resultMap1.put("bill_msg", "已完成");
            resultMap1.put("is_default", "0");

            Map<String, Object> resultMap2 = new HashMap<String, Object>();
            resultMap2.put("bill_type_code", "006");
            resultMap2.put("bill_msg", "已撤销");
            resultMap2.put("is_default", "0");

            Map<String, Object> resultMap3 = new HashMap<String, Object>();
            resultMap3.put("bill_type_code", "001");
            resultMap3.put("bill_msg", "全部");
            resultMap3.put("is_default", "1");

            Map<String, Object> resultMap4 = new HashMap<String, Object>();
            resultMap4.put("bill_type_code", "004");
            resultMap4.put("bill_msg", "审批中");
            resultMap4.put("is_default", "0");

            resultList.add(resultMap1);// 全部
            resultList.add(resultMap2);// 审批中
            resultList.add(resultMap3);// 已完成
            resultList.add(resultMap4);// 已撤销
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("Rows", resultList);
        return resultMap;
    }

    /**
     * @Title: phoneGetPendingApprovalInfoCountWMS
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param personnel_shortCode
     * @param searchInfo
     * @return 
     * @author: DongHao
     * @time:2016年12月14日 上午8:33:46
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#phoneGetPendingApprovalInfoCountWMS(java.lang.String, java.lang.String)
     * history:
     * 1、2016年12月14日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> phoneGetPendingApprovalInfoCountWMS(String personnel_shortCode, String searchInfo)
    {
        // (1)根据传入的员工的短工号进行获取人员信息
        PmPersonnel pmPersonnel = new PmPersonnel();
        pmPersonnel.setPersonnel_shortcode(personnel_shortCode);
        List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
        if (listp == null || listp.size() == 0)
        {
            return null;
        }

        // (2)获取待审批的数据
        // 三级审批
        Map<String, Object> threeApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "3");
        // 特批
        Map<String, Object> specialApprovalMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS, listp.get(0).getPersonnel_id().toString(), "4");

        // (3)设置查询条件
        if (searchInfo != null && !"".equals(searchInfo))
        {
            threeApprovalMap.put("searchInfo", searchInfo);
        }
        threeApprovalMap.put("sortname", "bill_code");
        threeApprovalMap.put("sortorder", "desc");

        specialApprovalMap.put("user_id", listp.get(0).getPersonnel_id());
        specialApprovalMap.put("sortname", "bill_code");
        specialApprovalMap.put("sortorder", "desc");

        // 获取该用户的角色信息
        boolean flag = false;
        Map<String, Object> paramMap_ys = new HashMap<>();
        List<String> roleList = sysroleDao_m.getUserRoleNameList(listp.get(0).getPersonnel_id());
        for (String role : roleList)
        {
            if (role.equals("理财业务部主管"))
            {
                threeApprovalMap.put("roleType", 1);
                specialApprovalMap.put("roleType", 1);
                paramMap_ys.put("roleType", 1);
                flag = true;
            }
            else if (role.equals("理财业务部总监"))
            {
                threeApprovalMap.put("roleType", 3);
                specialApprovalMap.put("roleType", 3);
                paramMap_ys.put("roleType", 3);
                flag = true;
            }
            else if (role.equals("理财业务部副总"))
            {
                threeApprovalMap.put("roleType", 2);
                specialApprovalMap.put("roleType", 2);
                paramMap_ys.put("roleType", 2);
                flag = true;
            }
        }

        if (roleList == null || roleList.size() == 0 || !flag)
        {
            threeApprovalMap.put("roleType", 4);
            specialApprovalMap.put("roleType", 4);
            paramMap_ys.put("roleType", 4);
        }

        List<Map<String, Object>> list = wmsinveredeemDao.searchForPhoneAppData(threeApprovalMap);
        List<Map<String, Object>> listtp = wmsinveredeemDao.searchForPhoneAppData(specialApprovalMap);

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
        resMap.put("Rows", list);
        return resMap;
    }
    
    /**
     * @Title: getSearchParamsMap
     * @Description: 将查询条件对象转成map对象
     * @param queryInfo 查询条件对象
     * @return 查询条件map对象
     * @author: jinzhm
     * @time:2017年5月10日 下午5:13:21
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    private Map<String, Object> getSearchParamsMap(WmsInveRedeemSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 客户姓名
        if (StringUtils.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        // 身份证号
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        // 赎回申请开始日期
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_begin()))
        {
            paramMap.put("redeem_date_begin", queryInfo.getRedeem_date_begin());
        }
        // 赎回申请结束日期
        if (StringUtils.isNotBlank(queryInfo.getRedeem_date_end()))
        {
            paramMap.put("redeem_date_end", queryInfo.getRedeem_date_end());
        }
        // 是否特批
        if (StringUtils.isNotBlank(queryInfo.getIs_special_approval()))
        {
            paramMap.put("is_special_approval", queryInfo.getIs_special_approval());
        }
        // 特批领导
        if (StringUtils.isNotBlank(queryInfo.getSpecial_approval_leader_id())
            && !"-1".equals(queryInfo.getSpecial_approval_leader_id()))
        {
            paramMap.put("special_approval_leader_id", queryInfo.getSpecial_approval_leader_id());
        }
        // 赎回单据状态
        if (StringUtils.isNotBlank(queryInfo.getData_status_name()) && !"-1".equals(queryInfo.getData_status_name()))
        {
            paramMap.put("data_status_name", queryInfo.getData_status_name());
        }
        // 录入员
        if (StringUtils.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        // 业务员
        if (StringUtils.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // 归属业务员
        // jinzhm修改，添加归属业务员查询条件
        if (StringUtils.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }
        // zmj修改，添加单据来源查询条件
        if (StringUtils.isNotBlank(queryInfo.getBill_source()))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }
        return paramMap;
    }

    /**
     * @Title: exportFinancialRedeem
     * @Description: 财务主管导出赎回报表
     * @param queryInfo 查询条件对象
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 下午5:12:13
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#exportFinancialRedeem(com.zx.emanage.inve.vo.WmsInveRedeemSearchBeanVO, javax.servlet.http.HttpServletResponse)
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
    */
    @Override
    public void exportFinancialRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response)
    {
        // 转换查询条件对象
        Map<String, Object> paramMap = getSearchParamsMap(queryInfo);

        List<Map<String, Object>> dataList = wmsinveredeemDao.searchFinancialRedeemListForExport(paramMap);

        // 封装导出数据
        Map<String, Object> listMap = new HashMap<String, Object>();
        // 设置导出数据
        listMap.put("赎回单据信息", dataList);

        try
        {
            String out_file_name = "赎回单据信息一览表_" + DateUtil.date2String(new Date(), "yyyyMMddHHmmss") + ".xlsx";
            ExpertUtil eu = new ExpertUtil();
            eu.expertExcel("赎回单据信息一览表.xlsx", listMap, out_file_name, null, 1, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: exportRedeem
     * @Description: 柜员主管导出赎回报表
     * @param queryInfo 查询条件对象
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 下午4:10:16
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#exportRedeem(com.zx.emanage.inve.vo.WmsInveRedeemSearchBeanVO, javax.servlet.http.HttpServletResponse)
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
    */
    @Override
    public void exportRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response)
    {
        // 格式化查询条件
        Map<String, Object> paramMap = getSearchParamsMap(queryInfo);

        // 查询要导出数据
        List<Map<String, Object>> dataList = formatMergeDataList(wmsinveredeemDao.searchRedeemListForExport(paramMap));

        // 封装要导出数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(PoiMergeAbstract.DATA_LIST, dataList);
        dataMap.put(PoiMergeAbstract.START_ROW_NUM, 1);

        PoiMergeAbstract poiMerge = new PoiMergeAbstract()
        {

            @Override
            protected String getUnMergeListKeyName()
            {
                return "principal_types";
            }

            @Override
            protected String getExceptMergeLabel()
            {
                return "principal_type,principal_amount,total_management_fee";
            }
        };
        poiMerge.export("每日柜员统计赎回报表.xlsx", "每日柜员统计赎回报表_" + DateUtil.date2String(new java.util.Date(), "yyyyMMddHHmmss")
                                           + ".xlsx", dataMap, response);
    }

    /**
     * @Title: formatMergeDataList
     * @Description: 格式化合并集合
     *      根据支付方式合并同一个单据的公共信息
     *      [{
     *          bill_code: bill_code,
     *          principal_type: principal_type1,
     *          principal_amount: principal_amount1
     *      },{
     *          bill_code: bill_code,
     *          principal_type: principal_type2,
     *          principal_amount: principal_amount2
     *      }]
     *      合并成
     *      [{
     *          bill_code: bill_code,
     *          pay_types: [{
     *              principal_type: principal_type1,
     *              principal_amount: principal_amount1
     *          },{
     *              principal_type: principal_type2,
     *              principal_amount: principal_amount2
     *          }]
     *      }]
     * @param dataList 原集合
     * @return 格式化后集合
     * @author: jinzhm
     * @time:2017年5月11日 下午4:44:43
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    private List<Map<String, Object>> formatMergeDataList(List<Map<String, Object>> dataList)
    {
        // 合并后要返回的新集合
        List<Map<String, Object>> mergeDataList = new ArrayList<Map<String, Object>>();

        String currentRedeemId = "";

        // 循环要格式化的数据集合
        for (int i = 0; i < dataList.size(); i++)
        {
            // 获得数据
            Map<String, Object> dataMap = getDataMapFromList(dataList, i);
            // 获得理财单据编号
            String redeemId = String.valueOf(dataMap.get("wms_inve_redeem_id"));

            // 如果当前
            if (currentRedeemId.equals(redeemId))
            {
                continue;
            }
            // 设置当前处理的理财单据编号
            currentRedeemId = redeemId;

            // 临时存储支付方式集合
            List<Map<String, Object>> principalTypeList = new ArrayList<Map<String, Object>>();
            // 根据支付方式的新数据集合(包含支付金额)
            Map<String, Object> principalTypeMap = null;

            int count = 0;

            // 如果理财单据编号和下一条记录的理财单据编号相同，一直循环取下一个进行判断
            do
            {
                principalTypeMap = new HashMap<String, Object>();
                // 设置支付方式及支付金额
                principalTypeMap.put("principal_type", getDataMapFromList(dataList, i + count).get("principal_type"));
                principalTypeMap.put("principal_amount",
                                     getDataMapFromList(dataList, i + count).get("principal_amount"));
                principalTypeMap.put("total_management_fee",
                               getDataMapFromList(dataList, i + count).get("total_management_fee"));
                // 保存在临时集合中
                principalTypeList.add(principalTypeMap);
                count++;
            } while (redeemId.equals(String.valueOf(getDataMapFromList(dataList, i + count).get("wms_inve_redeem_id"))));
            // 将新的payTypeList存储到原list集合的map中
            dataMap.put("principal_types", principalTypeList);
            // 存储到新集合中
            mergeDataList.add(dataMap);
        }
        return mergeDataList;
    }

    /**
     * @Title: getDataMapFromList
     * @Description: 获得list集合中的元素，如果要获得的元素的下标超过集合长度返回空map集合
     * @param dataList list集合
     * @param i 要获得的元素下标
     * @return 元素，如果下标超过list长度返回空map集合
     * @author: jinzhm
     * @time:2017年5月11日 下午1:37:52
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    private Map<String, Object> getDataMapFromList(List<Map<String, Object>> dataList, int i)
    {
        if (i > dataList.size() - 1)
        {
            return new HashMap<String, Object>();
        }
        return dataList.get(i);
    }

    /**
     * @Title: saveWmsInveRedeemInfoPTP
     * @Description: 定时任务生成PTP赎回单据相关信息 
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月24日 上午9:51:24
     * @see com.zx.emanage.inve.service.IWmsInveRedeemService#saveWmsInveRedeemInfoPTP()
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    @Override
    @Transactional
    public void saveWmsInveRedeemInfoPTP()
    {
        // 查询出当前时间前一天的赎回记录表数据
        List<WmsInveRedeemRecordVO> wmsInveRedeemRecordVOList = wmsInveRedeemRecordDao.getWmsInveRedeemRecordLastDay();

        WmsInveTransa wmsInveTransa = null;
        WmsInveTransaProd wmsInveTransaProd = null;

        for (int i = 0; i < wmsInveRedeemRecordVOList.size(); i++)
        {
            wmsInveTransaProd = wmsInveTransaProdDao.get(wmsInveRedeemRecordVOList.get(i).getWms_inve_transa_prod_id());
            // 赎回后剩余投资金额
            BigDecimal productAccount = wmsInveTransaProd.getProduct_account()
                                                         .subtract(wmsInveRedeemRecordVOList.get(i)
                                                                                            .getPrincipal_amount());
            // 如果剩余投资金额大于0，说明是部分赎回
            if (productAccount.compareTo(BigDecimal.ZERO) > 0)
            {
                wmsInveRedeemRecordVOList.get(i).setIs_fully_redeemed("0");
            }
            // 如果剩余投资金额小于等于0，说明是全部赎回
            else
            {
                wmsInveRedeemRecordVOList.get(i).setIs_fully_redeemed("1");
            }

            // 保存赎回表信息
            wmsInveRedeemRecordVOList.get(i).setBill_code(CodeNoUtil.getReturnRedeemCode());
            wmsInveRedeemRecordVOList.get(i).setRedeem_reason("PTP赎回");
            wmsInveRedeemRecordVOList.get(i).setRedeem_reality_total_amount_upper(digitUpperUtil.digitUppercase(wmsInveRedeemRecordVOList.get(i).getRedeem_reality_total_amount().doubleValue(), true));
            wmsinveredeemDao.saveWmsInveRedeemPTP(wmsInveRedeemRecordVOList.get(i));
            // 保存赎回明细表
            wmsInveRedeemDetailDao.saveWmsInveRedeemDetailPTP(wmsInveRedeemRecordVOList.get(i));
            // 保存赎回本金表
            wmsInveRedeemPrincipalDetailDao.saveWmsInveRedeemPrincipalDetailPTP(wmsInveRedeemRecordVOList.get(i));
            // 根据主键查询上单表记录
            wmsInveTransa = wmsinvetransaDao.getWmsInveTransaByWmsInveTransaId(wmsInveRedeemRecordVOList.get(i).getWms_inve_transa_id());
            // 更新上单表单据状态为已完成 产品金额-赎回金额
            wmsInveTransa.setData_status("6");
            wmsInveTransa.setProduct_total_count_lower(productAccount);
            wmsInveTransa.setProduct_total_count_upper(digitUpperUtil.digitUppercase(productAccount.doubleValue(), true));
            wmsinvetransaDao.update(wmsInveTransa);
            // 更新上单产品表理财金额
            wmsInveTransaProd.setProduct_account(productAccount);
            wmsInveTransaProdDao.update(wmsInveTransaProd);

            // 更新老协议表中的赎回主键
            WmsInveTransaProtocol protocol = wmsinvetransaprotocolDao.getByPk(wmsInveRedeemRecordVOList.get(i).getWms_inve_transa_prod_id());
            WmsInveTransaProtocol updProtocol = new WmsInveTransaProtocol();
            updProtocol.setWms_inve_transa_protocol_id(protocol.getWms_inve_transa_protocol_id());
            updProtocol.setWms_inve_redeem_id(wmsInveRedeemRecordVOList.get(i).getWms_inve_redeem_id());
            wmsinvetransaprotocolDao.update(updProtocol);

            // 如果是部分赎回，给老协议设置赎回主键后，还需要生成一份新的老协议
            if ("0".equals(wmsInveRedeemRecordVOList.get(i).getIs_fully_redeemed()))
            {
                // 清空主键
                protocol.setWms_inve_transa_protocol_id(null);
                // 设置剩余的投资金额
                protocol.setProduct_account(productAccount);
                // 保存剩余投资金额的协议数据
                wmsinvetransaprotocolDao.save(protocol);
            }

            // 生成赎回收益信息
            UserBean user = new UserBean();
            user.setUserId(wmsInveRedeemRecordVOList.get(i).getBel_salesman_id_id());
            user.setRealname(wmsInveRedeemRecordVOList.get(i).getBel_salesman_name());
            protocol.setWms_inve_redeem_id(wmsInveRedeemRecordVOList.get(i).getWms_inve_redeem_id());
            CountIncomeFactory.getCountIncome(SignHelper.PTP_CATEGORY_ID).getIncomeAndLogForRedeem(protocol, user);

        }
    }
}
