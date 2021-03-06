package com.zx.emanage.inve.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.typeconverter.Convert;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.inve.persist.WmsInveAttDao;
import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveCommissionBillSpDao;
import com.zx.emanage.inve.persist.WmsInveCommissionCategoryDao;
import com.zx.emanage.inve.persist.WmsInveCommissionGeneralRulesDao;
import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.persist.WmsInveCustomerRemoveHisDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductDeadlineRewardDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaApprovalInfoDao;
import com.zx.emanage.inve.persist.WmsInveTransaAttDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaCusConfirmDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdRewardDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaSalesmanHisDao;
import com.zx.emanage.inve.persist.WmsInveTransaSpecialappChangeDao;
import com.zx.emanage.inve.service.IWmsInveCreditLimitService;
import com.zx.emanage.inve.service.IWmsInveCustomerCardService;
import com.zx.emanage.inve.service.IWmsInveTransaCardService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.util.IncomeGlobal;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.util.export.PoiMergeAbstract;
import com.zx.emanage.inve.vo.WmsInveDebtHeadSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveIncomeBillVo;
import com.zx.emanage.inve.vo.WmsInveSelectBillBeanVo;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.sysmanage.persist.CrmCustomerInfoDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.SysUserRole;
import com.zx.emanage.util.gen.entity.WmsInveAtt;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.emanage.util.gen.entity.WmsInveCommissionBillSp;
import com.zx.emanage.util.gen.entity.WmsInveCommissionCategory;
import com.zx.emanage.util.gen.entity.WmsInveCommissionGeneralRules;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInveCustomerRemoveHis;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.util.gen.entity.WmsInveSaleLimit;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsInveTransaAtt;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaCusConfirm;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProdReward;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaSpecialappChange;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.CustomerBillExportPdfUtil;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransaService")
public class WmsInveTransaServiceImpl implements IWmsInveTransaService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaServiceImpl.class);

    @Autowired
    private WmsInveTransaDao wmsinvetransaDao;

    @Autowired
    private WmsInveCustomerDao wmsInveCustomerDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsInveAttDao wmsInveAttDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysUserRoleDao sysuserroleDao_m;

    @Autowired
    private SysDeptDao sysdeptDao;

    @Autowired
    private WmsSysPropertyDao wmssyspropertyDao;

    @Autowired
    private WmsInveTransaCardDao wmsInveTransaCardDao;

    @Autowired
    private WmsInvePruductDeadlineRewardDao wmsInvePruductDeadlineRewardDao;

    @Autowired
    private WmsInveTransaProdRewardDao wmsInveTransaProdRewardDao;

    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;

    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;

    @Autowired
    private CrmCustomerInfoDao crmCustomerInfoDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;

    @Autowired
    private WmsInveTransaLogDao wmsInveTransaLogDao;

    @Autowired
    private WmsInveCommissionBillSpDao wmsInveCommissionBillSpDao;

    @Autowired
    private WmsInveCommissionGeneralRulesDao wmsInveCommissionGeneralRulesDao;

    @Autowired
    private WmsInveCommissionCategoryDao wmsInveCommissionCategoryDao;

    @Autowired
    private WmsInveTransaSpecialappChangeDao wmsInveTransaSpecialappChangeDao;

    @Autowired
    private SysSpecialUserDao specialUserDao;

    @Autowired
    private IWmsInveTransaProtocolService inveTransaProtocolService;

    @Autowired
    private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;

    @Autowired
    private SysSpecialUserDao sysSpecialUserDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private WmsInveTransaAttDao wmsInveTransaAttDao;

    @Autowired
    private WmsInveTransaApprovalInfoDao wmsInveTransaApprovalInfoDao;

    @Autowired
    private WmsInveTransaCusConfirmDao wmsInveTransaCusConfirmDao;

    @Autowired
    private IWmsInveWorkFlowService iWmsInveWorkFlowService;

    @Autowired
    private WmsInveTransaSalesmanHisDao wmsInveTransaSalesmanHisDao;

    @Autowired
    private WmsInveCustomerRemoveHisDao wmsInveCustomerRemoveHisDao;

    @Autowired
    private WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao;

    @Autowired
    private IWmsInveTransaCardService wmsInveTransaCardService;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;
    
    @Autowired
    private IWmsInveCustomerCardService wmsInveCustomerCardService;
    
    @Autowired
    private IWmsInveCreditLimitService wmsInveCreditLimitService;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * Description :【理财上单】-【金额管理】获取金额管理列表页数据
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @Override
    public Map<String, Object> getListForJEGLWithPaging(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "2");
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotEmpty(queryInfo.getCategory_name()) && !"-1".equals(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (queryInfo.getId_card() != null && !"".equals(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.searchForJEGL(paramMap);
        // 加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                      (List<String>) paramMap.get("taskIdList"),
                                                      (String) paramMap.get("processDefinitionKey"));
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountForJEGL(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * Description :【理财上单】-【金额管理】获取金额管理导出数据
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     */
    @Override
    public Map<String, Object> getListForJEGLWithoutPaging(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "2");
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotEmpty(queryInfo.getCategory_name()) && !"-1".equals(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (queryInfo.getId_card() != null && !"".equals(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.searchForJEGL(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransa getInfoByPK(Integer wms_inve_transa_id)
    {
        return wmsinvetransaDao.get(wms_inve_transa_id);
    }

    /**
     * 保存理财上单信息
     */
    @Override
    @Transactional
    public String save(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr, UserBean user,
                       String saveFlag, WmsInveCustomer wmsCustomer, WmsInveTransaSearchBeanVO beanVO)
    {
        // 判断上单、追单金额是否在其正确的范围内
        HashMap<String, Object> paramMap = new HashMap<>();
        if ("1".equals(saveFlag))
        {
            WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
            {// 是追单
                if (wmsInvePruductCategory.getCategory_additional_monery_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_additional_monery_min()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error1";
                }
                if (wmsInvePruductCategory.getCategory_additional_monery_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_additional_monery_max()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error2";
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null)
                {
                    paramMap.put("data_status1", "4");
                    paramMap.put("data_status2", "5");
                    List<Map<String, Object>> products = wmsInveTransaProdDao.search(paramMap);
                    double sumZdje = 0;// 追单金额的和
                    for (Map<String, Object> product : products)
                    {
                        BigDecimal product_account = (BigDecimal) product.get("product_account");
                        if (product_account.compareTo(BigDecimal.ZERO) == 0)
                        {
                            continue;
                        }
                        sumZdje += Integer.parseInt(product.get("product_account").toString().indexOf('.') > 0 ? product.get("product_account")
                                                                                                                        .toString()
                                                                                                                        .substring(0,
                                                                                                                                   product.get("product_account")
                                                                                                                                          .toString()
                                                                                                                                          .indexOf('.'))
                                                                                                              : product.get("product_account")
                                                                                                                       .toString());
                    }
                    // if (sumZdje >
                    // wmsInvePruductCategory.getCategory_maximum_amount().doubleValue()
                    // * 10000)
                    if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                        && sumZdje > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
                    {
                        return "error5";
                    }
                }
            }
            else
            {// 是上单
                if (wmsInvePruductCategory.getCategory_investment_money_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_investment_money_min()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error3";
                }
                if (wmsInvePruductCategory.getCategory_investment_money_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_investment_money_max()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error4";
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getMaximum_holding_amount()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error6";
                }
            }
            // 判断当前上单产品所有金额是否大于等于当前产品的最大限制金额
            // (1) 如果当前产品的上单金额已经大于等于当前产品的最大限制金额,需要将当前产品设置成禁用
            if (wmsInvePruductCategory.getCategory_maximum_amount() != null)
            {
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                paramsMap.put("wms_inve_pruduct_category_id", wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                paramsMap.put("wms_inve_transa_id", wmsInveTransa.getWms_inve_transa_id());

                int sumCategoryInveTransaPayaccount = wmsinvetransaDao.getSumCategoryInveTransaPayaccountByCategoryId(paramsMap);
                sumCategoryInveTransaPayaccount += wmsInveTransaProd.getProduct_account().doubleValue();
                // (2) 判断当前上单记录所上单的金额加上已经上单的总金额大于等于当前产品的最大限制金额则将当前产品禁用
                if (sumCategoryInveTransaPayaccount >= wmsInvePruductCategory.getCategory_maximum_amount()
                                                                             .doubleValue() * 10000)
                {
                    WmsInvePruductCategory disableWmsInveProductCategory = new WmsInvePruductCategory();
                    disableWmsInveProductCategory.setWms_inve_pruduct_category_id(wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                    disableWmsInveProductCategory.setIs_forbidden("1");
                    wmsInvePruductCategoryDao.update(disableWmsInveProductCategory);
                }
            }
        }
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 检查客户表里是否存在记录
        WmsInveCustomer wmsInveCustomer = null;
        String id_card = wmsCustomer.getId_card();
        paramMap = new HashMap<>();
        if (id_card != null && !"".equals(id_card))
        {
            paramMap.put("id_card", id_card);
            wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
        }
        // 在提交时，如果不存在客户记录，那保存客户信息 2015-11-24baisong 上单暂存不保存客户表 提交保存到客户表 保证客户的唯一性
        if (wmsInveCustomer == null && "1".equals(saveFlag))
        // if (wmsInveCustomer == null)
        {
            wmsInveCustomer = setWmsInveCustomer(wmsCustomer, wmsInveTransa, user, sysTime);// 保存客户信息
            wmsInveCustomer.setCostomer_id(wmsCustomer.getCostomer_id());// CRM关联信息表主键
            wmsInveCustomer.setCustomer_source(wmsCustomer.getCustomer_source());// 客户来源
            wmsInveCustomerDao.save(wmsInveCustomer);
            /*CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();
            crmCustomerInfo.setCostomer_id(wmsInveCustomer.getCostomer_id());//主键
            crmCustomerInfo.setId_card_number(wmsInveCustomer.getId_card());//身份证号码
            crmCustomerInfo.setCustomer_name(wmsInveCustomer.getCus_name());//客户姓名
            crmCustomerInfo.setContact_number(wmsInveCustomer.getMobile_phone());//移动手机
            crmCustomerInfo.setEmail_adress(wmsInveCustomer.getCustomer_email());//Email_adress
            crmCustomerInfo.setCustomer_birthday(wmsInveCustomer.getCus_birthday());//出生日期
            crmCustomerInfo.setPresent_address(wmsInveCustomer.getContact_address());//通讯地址
            crmCustomerInfo.setDomicile_place(wmsInveCustomer.getCus_address());//居住地址
            crmCustomerInfoDao.update(crmCustomerInfo);*/
        }
        else
        {
            wmsCustomer.setLast_update_timestamp(sysTime);
            wmsCustomer.setLast_update_user_id(user.getUserId());
            wmsInveCustomerDao.update(wmsCustomer);
            paramMap = new HashMap<>();
            if (id_card != null && !"".equals(id_card))
            {
                paramMap.put("id_card", id_card);
                wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
            }
        }
        // 修改上单信息
        if (wmsInveTransa.getWms_inve_transa_id() != null)
        {

            wmsInveTransa = setWmsInveTransaForUpdate(wmsInveTransa, wmsInveCustomer, wmsInveTransaProd, saveFlag,
 sysTime, user, beanVO.getLCtype());
            // 如果zctype不等于空 不修改单据的状态，只修改单据的数据（用于暂存）
            if (beanVO.getZctype() != "")
            {
                wmsInveTransa.setData_status(null);
            }
            wmsinvetransaDao.update(wmsInveTransa);

        }
        else
        // 新增上单信息
        {
            wmsInveTransa = setWmsInveTransaForAdd(wmsInveTransa, wmsInveCustomer, wmsInveTransaProd, saveFlag,
                                                   sysTime, user);
            wmsInveTransa.setBill_source(0);// 单据来源 0上单
            // 处理兼职单和在职单
            // 根据user_id获取状态
            PmPersonnel pmPersonnel = pmPersonnelDao.get(wmsInveTransa.getSalesman_id());
            String personnel_state = "1";
            if ("7".equals(pmPersonnel.getPersonnel_state()))
            {
                personnel_state = "2";
            }
            wmsInveTransa.setTrans_salesman_status(personnel_state);
            wmsinvetransaDao.save(wmsInveTransa);
        }
        /*// 重新设置预计收益的值
        List<Map<String, Object>> wmsInvePruductRebateWayList = new ArrayList<>();
        if (wmsInveTransaProd.getExpect_interest_account() != null)
        {
            paramMap = new HashMap<>();
            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            int recordNum = wmsInveTransaProdDao.calRecordNum(paramMap);
            paramMap = new HashMap<>();
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            wmsInvePruductRebateWayList = wmsInvePruductRebateWayDao.search(paramMap);
            double yqsySum = 0;// 针对老客户有效的奖励利率之和
            if (recordNum > 0)
            {
                for (Map<String, Object> wmsInvePruductDeadlineReward : wmsInvePruductRebateWayList)
                {
                    if ("1".equals(wmsInvePruductDeadlineReward.get("is_enable_recustomer").toString()))
                    {
                        yqsySum += Double.parseDouble(wmsInvePruductDeadlineReward.get("reward_interest").toString());
                    }
                }
                wmsInveTransaProd.setReward_interest(new BigDecimal(yqsySum));
                wmsInveTransaProd.setExpect_interest_account(new BigDecimal(
                                                                            (yqsySum + wmsInveTransaProd.getProduct_interest()
                                                                                                        .doubleValue())
                                                                                    * wmsInveTransaProd.getProduct_account()
                                                                                                       .doubleValue()));
            }
        }*/

        // 修改上单产品信息
        if (wmsInveTransaProd.getWms_inve_transa_prod_id() != null)
        {
            wmsInveTransaProd = setWmsInveTransaProdForUpdate(wmsInveTransaProd, user, sysTime);
            wmsInveTransaProdDao.update(wmsInveTransaProd);
            // 新增上单产品信息
        }
        else
        {
            wmsInveTransaProd = setWmsInveTransaProdForAdd(wmsInveTransaProd, wmsInveTransa, user, sysTime);
            wmsInveTransaProdDao.save(wmsInveTransaProd);
        }
        /*// 当为提交时，保存上单产品奖励利率记录
        if ("1".equals(saveFlag))
        {
            for (Map<String, Object> wmsInvePruductDeadlineReward : wmsInvePruductDeadlineRewardList)
            {
                if ("1".equals(wmsInvePruductDeadlineReward.get("is_enable_recustomer").toString())
                    && Double.parseDouble(wmsInvePruductDeadlineReward.get("reward_interest").toString()) > 0)
                {
                    WmsInveTransaProdReward wmsInveTransaProdReward = new WmsInveTransaProdReward();
                    wmsInveTransaProdReward = setWmsInveTransaProdReward(wmsInveTransaProdReward, wmsInveTransaProd,
                                                                         wmsInvePruductDeadlineReward);
                    wmsInveTransaProdRewardDao.save(wmsInveTransaProdReward);
                }
            }
        }*/
        // 删除附件
        Map<String, Object> map = new HashMap<>();
        map.put("wms_inve_transa_id", wmsInveTransa.getWms_inve_transa_id());
        map.put("data_type_name", beanVO.getData_type_name());
        wmsInveTransaAttDao.deleteForAtt(map);
        List<WmsInveTransaAtt> wmsInveTransaAttList = JsonUtil.jsonArrayToList(fileArr, WmsInveTransaAtt.class);// 将附件集合字符串转换成实体类的形式
        // 保存附件wms_inve_transa_prod_id
        for (WmsInveTransaAtt wmsInveTransaAtt : wmsInveTransaAttList)
        {
            wmsInveTransaAtt.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
            wmsInveTransaAtt = setWmsInveTransaAtt(wmsInveTransaAtt, wmsInveTransa);
            wmsInveTransaAtt.setWms_inve_transa_att_id(null);
            wmsInveTransaAttDao.save(wmsInveTransaAtt);
        }
        if ("0".equals(saveFlag))
        {
            return wmsInveTransa.getWms_inve_transa_id() + ";" + wmsInveTransaProd.getWms_inve_transa_prod_id();
        }
        // 添加上单提交启动流程
        if ("1".equals(saveFlag) && "".equals(beanVO.getLCtype()))
        {
            // 调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            iWmsInveWorkFlowService.startFinancialWorkFlow(user.getUserId().toString(),
                                                           wmsInveTransa.getWms_inve_transa_id().toString(),
                                                           wmsInveTransa.getSalesman_dept_id().toString(),
                                                           wmsInveTransa.getSalesman_id().toString(),
                                                           WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        }
        // 添加复核退回流程
        if (beanVO.getLCtype().equals("FHTH"))
        {
            // 调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(beanVO.getTaskId());
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());// 上单信息表id
            wDebtWorkFlowVO.setDebtkey("6");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        // 添加审核退回流程
        if (beanVO.getLCtype().equals("ZFTH"))
        {
            // 调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(beanVO.getTaskId());
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());// 上单信息表id
            wDebtWorkFlowVO.setDebtkey("7");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        return "success";
    }

    // 设置上件附件对象
    public WmsInveTransaAtt setWmsInveTransaAtt(WmsInveTransaAtt wmsInveTransaAtt, WmsInveTransa wmsInveTransa)
    {
        wmsInveTransaAtt.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
        return wmsInveTransaAtt;
    }

    @Override
    @Transactional
    public String update(WmsInveTransa bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransaDao.update(bean); // update a record replace columns,
                                             // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * @Title: updateForJEZF 
     * Description :【理财上单】财务管理>金额管理>金额支付
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param ransaAtt
     * @param flagKey
     * @return "success" or "error" or user defined
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @Override
    @Transactional
    public String updateForJEZF(WmsInveTransa bean, String itcardJSON, UserBean user, WmsInveTransaProd wmsTransaProd,
                                WmsInveTransaSearchBeanVO beanvo,WmsInveCustomerCard wmsInveCustomerCard)
    {
        String resStr = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis()); // 获取当前系统时间
        Date sysDate = new Date(System.currentTimeMillis());
        // 获取支付附件信息
        List<WmsInveTransaAtt> wmsInveTransaAttList = JsonUtil.jsonArrayToList(beanvo.getFileArrpay(),
                                                                               WmsInveTransaAtt.class);
        // 获取支付卡信息
        List<WmsInveTransaCard> wmsInveTransaCardList = JsonUtil.jsonArrayToList(itcardJSON, WmsInveTransaCard.class);

        resStr = setTransaCardInfo(bean, wmsTransaProd, wmsInveTransaCardList, wmsInveTransaAttList, user, beanvo, "2", wmsInveCustomerCard);

        /* for(WmsInveTransaCard wmsInveTransaCard : wmsInveTransaCardList){
             if("1".equals(wmsInveTransaCard.getPay_type())){
                 
             }else if("2".equals(wmsInveTransaCard.getPay_type())){
                 bean.setAccount_status("2");
                 bean.setPay_usr_id(user.getUserId());
                 bean.setDate_of_payment(sysDate);
                 bean.setLast_update_user_id(user.getUserId());
                 bean.setLast_update_timestamp(sysTime);
                 bean.setPay_type(wmsInveTransaCard.getPay_type());
                 resStr=setTransaCardInfo(bean,wmsTransaProd, wmsInveTransaCardList,wmsInveTransaAttList,user,beanvo,"2");
             }else if("3".equals(wmsInveTransaCard.getPay_type())){
                 bean.setAccount_status("4");
                 bean.setPay_usr_id(user.getUserId());
                 bean.setDate_of_payment(sysDate);
                 bean.setLast_update_user_id(user.getUserId());
                 bean.setLast_update_timestamp(sysTime);
                 bean.setCash_pay_name(bean.getCus_name());
                 bean.setPay_type(wmsInveTransaCard.getPay_type());
                 resStr=setTransaCardInfo(bean,wmsTransaProd, wmsInveTransaCardList,wmsInveTransaAttList,user,beanvo,"2");
             }
         }*/
        // 提交
        if (beanvo.getFlagKey().equals("1"))
        {
            String taskId = "";
            if (beanvo.getTaskId() == null || "".equals(beanvo.getTaskId()))
            {
                taskId = wmsInveWorkFlowService.getSignedProcessTaskId(bean.getWms_inve_transa_id().toString(),
                                                                       WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZF);
            }
            else
            {
                taskId = beanvo.getTaskId();
            }

            // 执行流程
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("2");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);

            // 生效债权
            CreditBusiness.getInstance().effectMatchedCredit(bean.getWms_inve_transa_id(), user);
        }
        return resStr;
    }

    /**
     * 集中处理信息
     * (1) 删除当前单据原有上传的附件信息
     * (2) 存储支付卡信息
     * (3) 释放当前单据的支付方式为续单本金的使用金额
     * (4) 根据当前单据的信息主键删除掉之前录入的支付信息
     * (5) 然后添加支付信息到支付信息表中
     *     1.上单信息表中的支付方式是以二进制的信息进行存储
     *     2.组合二进制的顺序是从左到右,依次为  现金--银行卡--续单本金
     *     3.存在的则用1表示,不存在的用0表示。(如：现金 + 银行卡  表示为110)
     * (6) 更新上单信息表和产品信息表
     * @param bean
     * @param inveTransaProd
     * @param wmsInveTransaCardList
     * @param wmsInveTransaAttList
     */
    private String setTransaCardInfo(WmsInveTransa bean, WmsInveTransaProd inveTransaProd,
                                     List<WmsInveTransaCard> wmsInveTransaCardList,
                                     List<WmsInveTransaAtt> wmsInveTransaAttList, UserBean user,
                                     WmsInveTransaSearchBeanVO beanvo, String falg,WmsInveCustomerCard wmsInveCustomerCard)
    {
        String result = "success";
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        Date sysDate = new Date(System.currentTimeMillis());
        // (1) 删除当前单据原有上传的附件信息
        Map<String, Object> param = new HashMap<>();
        param.put("wms_inve_transa_id", bean.getWms_inve_transa_id());
        param.put("data_type_name", beanvo.getData_type_name());
        wmsInveTransaAttDao.deleteForAtt(param);
        // (2) 存储支付卡信息
        for (WmsInveTransaAtt attVo : wmsInveTransaAttList)
        {
            attVo.setWms_inve_transa_id(bean.getWms_inve_transa_id());
            attVo.setWms_inve_transa_prod_id(inveTransaProd.getWms_inve_transa_prod_id());
            wmsInveTransaAttDao.save(attVo);
        }
        // (3) 释放掉之前暂存的数据
        List<Map<String, Object>> wmsInveTransaCardMap = wmsInveTransaCardDao.getWmsInveTransaCardInfoByWmsInveTransaId(bean.getWms_inve_transa_id());
        for (Map<String, Object> map : wmsInveTransaCardMap)
        {
            if ("3".equals("" + map.get("pay_type")))
            {
                if ("1".equals("" + map.get("is_valid")))
                {
                    int wms_inve_redeem_principal_detail_id = (int) map.get("wms_inve_redeem_principal_detail_id");

                    WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
                    wmsInveRedeemPrincipalDetail.setWms_inve_redeem_principal_detail_id(wms_inve_redeem_principal_detail_id);
                    wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal("" + map.get("act_account")));
                    wmsInveRedeemPrincipalDetailDao.updateToUsedIncomeAmount(wmsInveRedeemPrincipalDetail);
                }
            }
        }
        // (4) 根据上单信息表主键,把曾经存入支付信息删除
        wmsInveTransaCardDao.deleteForInfo(bean.getWms_inve_transa_id());
        // (5) 添加支付信息到支付信息表中
        String payType = "";
        for (WmsInveTransaCard witcVo : wmsInveTransaCardList)
        {
            bean.setPay_usr_id(user.getUserId());// 支付操作人
            bean.setDate_of_payment(sysDate);// 支付时间
            bean.setPay_account(bean.getPay_account());// 支付金额合计
            bean.setCash_pay_name(bean.getCus_name());// 现金支付人姓名
            bean.setLast_update_user_id(user.getUserId());
            bean.setLast_update_timestamp(sysTime);
            bean.setAct_account_usr_id(user.getUserId());// 到账操作人
            // bean.setDate_of_act(sysDate);// 到账时间
            bean.setAct_account(bean.getPay_account());// 到账金额合计
            bean.setFee_account(new BigDecimal(0));// 手续费

            witcVo.setWms_inve_transa_id(bean.getWms_inve_transa_id());
            witcVo.setCreate_user_id(user.getUserId());
            witcVo.setCreate_user_name(user.getRealname());
            witcVo.setCreate_timestamp(sysTime);
            witcVo.setEnable_flag("1");

            // 使用签单支付金额乘以10000是以为前台页面显示以万元为单位
            BigDecimal pay_account = witcVo.getPay_account() != null ? witcVo.getPay_account() : BigDecimal.ZERO;
            witcVo.setPay_account(pay_account.multiply(new BigDecimal(10000)));

            if ("1".equals(witcVo.getPay_type()))
            {
                bean.setAccount_status("3");// 支付状态
                payType += 1 + ",";

                witcVo.setAct_account(witcVo.getPay_account());
                witcVo.setIs_finish("1");
            }
            else if ("2".equals(witcVo.getPay_type()))
            {
                witcVo.setIs_finish("0");
                bean.setAccount_status("2");
                payType += witcVo.getPay_type() + ",";
            }
            else if ("3".equals(witcVo.getPay_type()))
            {
                witcVo.setBank_of_deposit_pro(null);
                witcVo.setBank_of_deposit_city(null);

                // 使用签单支付金额乘以10000是以为前台页面显示以万元为单位
                witcVo.setAct_account(witcVo.getPay_account());
                witcVo.setIs_finish("1");

                bean.setAccount_status("3");
                payType += witcVo.getPay_type() + ",";
                // 根据选择的赎回单据信息进行更新所选择对应的单据所使用的金额
                WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
                wmsInveRedeemPrincipalDetail.setWms_inve_redeem_principal_detail_id(witcVo.getWms_inve_redeem_principal_detail_id());

                // 使用签单支付金额乘以10000是以为前台页面显示以万元为单位
                wmsInveRedeemPrincipalDetail.setUsed_income_amount(witcVo.getPay_account());
                wmsInveRedeemPrincipalDetailDao.updatePlusUsedIncomeAmount(wmsInveRedeemPrincipalDetail);
            }
            witcVo.setIs_valid("1");
            witcVo.setShould_pay_account(bean.getProduct_total_count_lower());
            wmsInveTransaCardDao.save(witcVo);
        }
        // 判断payType字符串中是否存在银行卡的支付方式,如果存在返回true,并且更新上单信息表的account_status状态为部分到账
        boolean bool = payType.contains("2");
        if (bool)
        {
            bean.setAccount_status("4");
        }
        // 将拼接的支付方式的字符串组合成二进制的形式
        String newPayType = "";
        if (!StringUtil.isEmpty(payType))
        {
            if (payType.contains("1"))
            {
                newPayType += "1";
            }
            else
            {
                newPayType += "0";
            }
            if (payType.contains("2"))
            {
                newPayType += "1";
            }
            else
            {
                newPayType += "0";
            }
            if (payType.contains("3"))
            {
                newPayType += "1";
            }
            else
            {
                newPayType += "0";
            }
        }
        // 将二进制的形式的字符串转换成十进制的数字
        bean.setPay_type(Integer.valueOf(newPayType, 2));

        // 支付时间设置成上单时间，实际金额支付时间设置成支付时间，在上面代码中已将两个字段全部设置成支付时间。 jinzhm2016-11-16修改
        WmsInveTransa transaInfo = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
        bean.setDate_of_payment(new java.sql.Date(DateUtil.getDate10(new Date(transaInfo.getCreate_timestamp()
                                                                                        .getTime())).getTime()));// 金额支付时间设置成上单时间

        // 更新上单信息表信息
        if (bean != null)
        {
            //根据上单产品表中的上单产品进行获取产品的类型,如果产品的类型为不可拆分类产品则将合同类型设置成线下合同
            Map<String, Object> categoryMap = wmsinvetransaDao.getCategoryType(inveTransaProd.getWms_inve_pruduct_category_id());
            if(categoryMap != null)
            {
                //判断产品类型是否为空
                if(categoryMap.get("category_type") != null)
                {
                    Integer category_type = (Integer) categoryMap.get("category_type");
                    String has_paper_protocol = (String) categoryMap.get("has_paper_protocol");
                    
                    //判断产品类型是否为可拆分类型
                    if(category_type.compareTo(4) != 0)
                    {
                        if(has_paper_protocol != null && !"".equals(has_paper_protocol) && "0".equals(has_paper_protocol))
                        {
                            bean.setContract_signing_type("2");
                        }
                        else
                        {
                            //当产品为不可拆分类产品时,设置合同类型为线下合同类型
                            bean.setContract_signing_type("1");
                        }
                    }
                    else
                    {
                        //产品为可拆分类产品
                        //判断上单是的产品是否与支付时产品不一致,不一致时判断上单时的合同类型
                        if(beanvo.getOld_wms_inve_pruduct_category_id().compareTo(inveTransaProd.getWms_inve_pruduct_category_id()) != 0)
                        {
                            //不一致的情况
                            //进入该逻辑说明该产品时可拆分类产品
                            //根据上单表主键获取上单信息
                            WmsInveTransa transa = wmsinvetransaDao.get(bean.getWms_inve_transa_id());
                            
                            //判断根据主键获取的上单信息是否为空
                            if(transa != null)
                            {
                                //判断上单时合同类型
                                if(transa.getContract_signing_type() != null && !"".equals(transa.getContract_signing_type()))
                                {
                                    //如果上单时设置的合同类型为线下合同则设置成线上合同
                                    if("1".equals(transa.getContract_signing_type()))
                                    {
                                        bean.setContract_signing_type("2");
                                    }
                                    else if(has_paper_protocol != null && !"".equals(has_paper_protocol) && "0".equals(has_paper_protocol))
                                    {
                                        bean.setContract_signing_type("2");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            wmsinvetransaDao.update(bean);
        }
        // 存储收益卡信息
        if (inveTransaProd != null && "2".equals(falg))
        {
            //执行添加收益卡的操作
            Map<String, Object> res_map = wmsInveCustomerCardService.newAddIncomeCard(user, wmsInveCustomerCard);
            if(res_map.get("error").toString().equals("success"))
            {
                inveTransaProd.setWms_inve_customer_card_id((Integer)res_map.get("wms_inve_customer_card_id"));
            }
            else
            {
                try
                {
                    throw new Exception("添加收益卡信息抛异常!");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            
            //判断银行卡所属银行是否为空
            if(inveTransaProd.getBank_of_deposit() != null && !"".equals(inveTransaProd.getBank_of_deposit()))
            {
                if(inveTransaProd.getBank_of_deposit().contains("广东发展银行"))
                {
                    inveTransaProd.setBank_of_deposit("广发银行");
                }
                else if(inveTransaProd.getBank_of_deposit().contains("中国光大银行"))
                {
                    inveTransaProd.setBank_of_deposit("光大银行");
                }
            }
            
            updateWmsInveClerkData(bean.getWms_inve_transa_id(), inveTransaProd, user);
            
            wmsInveTransaProdDao.update(inveTransaProd);
        }
        return result;
    }

    /**
     * 
     * @Title: updateWmsInveClerkData
     * @Description: 修改柜员业务表数据信息
     * @param wms_inve_transa_id
     * @param inveTransaProd 
     * @author: DongHao
     * @time:2017年2月28日 上午1:30:45
     * history:
     * 1、2017年2月28日 DongHao 创建方法
     */
    public void updateWmsInveClerkData(Integer wms_inve_transa_id, WmsInveTransaProd inveTransaProd, UserBean user)
    {
        WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByTransaId(wms_inve_transa_id);
        wmsInveClerkData.setCategory_deadline(inveTransaProd.getProduct_deadline());
        wmsInveClerkData.setCategory_name(inveTransaProd.getCategory_name());
        wmsInveClerkData.setProduct_account(inveTransaProd.getProduct_account());
        wmsInveClerkData.setWms_inve_pruduct_category_id(inveTransaProd.getWms_inve_pruduct_category_id());
        wmsInveClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setLast_update_user_id(user.getUserId());
        wmsInveClerkDataDao.update(wmsInveClerkData);
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransa() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransa> getListByEntity(WmsInveTransa queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransa> beanList = wmsinvetransaDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 获取债权匹配列表数据
     * 
     * @param queryInfo
     * @return Map
     * @author 张风山
     */
    @Override
    public Map<String, Object> getListWithPagingForMatch(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.searchForMatch(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountForMatch(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 获取协议续期页面列表数据 
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithPagingForRenewal(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.searchForRenewal(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountForMatch(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 获取房产和车产债权选择列表数据显示
     * @param queryInfo
     * @return Map
     * @author hancd
     */
    public Map<String, Object> getListWithPagingForChoose(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("zrjeBefore", queryInfo.getZrjeBefore());
        paramMap.put("zrjeAfter", queryInfo.getZrjeAfter());
        paramMap.put("zzsjBefore", queryInfo.getZzsjBefore());
        paramMap.put("zzsjAfter", queryInfo.getZzsjAfter());
        paramMap.put("category_type", queryInfo.getCategory_type());

        paramMap.put("not_occupy", "true");// 未被占用(1代表被占用 0或空代表未被占用)

        List<Map<String, Object>> list = wmsinvetransaDao.searchForChoose(paramMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountForChoose(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现理财查询
     */
    @Override
    public Map<String, Object> getSearchFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        boolean invisible = false;
        int userId = 0;
        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        Map<String, Object> paramMap = new HashMap<String, Object>();
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
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
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
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
                invisible = true;
                userId = user.getUserId();
            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
                // // 根据部门ID获得子部门ID
                // // List<Integer> deptIds =
                // sysdeptDao.getDeptId(user.getDeptId());
                // // deptIds.add(user.getDeptId());
                // // // 可以看见子部门的所有业务员单据
                // // paramMap.put("deptIds", deptIds);
                // //根据数据权限去获取部门
                // paramMap.put("salesman_id", user.getUserId());
                // paramMap.put("deptIds_menu", queryInfo.getMenu_id());
                // paramMap.put("deptIds_user_id", user.getUserId());
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
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getData_status()) && !queryInfo.getData_status().equals("-1"))
        {
            paramMap.put("data_status", queryInfo.getData_status());
        }

        if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }

        if (StringUtil.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // jinzhm修改，添加归属业务员的查询条件
        if (StringUtil.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }
        if (StringUtils.isNotBlank(queryInfo.getContract_begin()))
        {
            paramMap.put("contract_begin", queryInfo.getContract_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getContract_end()))
        {
            paramMap.put("contract_end", queryInfo.getContract_end());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        
        
        List<Map<String, Object>> list = wmsinvetransaDao.searchFinancial(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        for (Map<String, Object> map : list)
        {
            // 当该用户为理财业务部主管时，去除客户手机与身份证信息并添加用户ID
            map.remove("mobile_phone");
            map.remove("id_card");
            // 用户ID为理财查看页面判断是否显示手机和身份证字段需要
            map.put("user_id", userId);
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountFinancial(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现理财查询导出
     */
    @Override
    public Map<String, Object> getSearchFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                   UserBean user)
    {
        boolean invisible = false;
        int userId = 0;
        // 获取该用户的角色信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        boolean flag = true;
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
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
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
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
                invisible = true;
                userId = user.getUserId();
            }
            if (role.equals("理财业务部总监"))
            {
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
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
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getData_status()) && !queryInfo.getData_status().equals("-1"))
        {
            paramMap.put("data_status", queryInfo.getData_status());
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // jinzhm修改，添加归属业务员的查询条件
        if (StringUtil.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }
        if (StringUtil.isBlank(queryInfo.getCus_name()) && StringUtil.isBlank(queryInfo.getCategory_name())
            && StringUtil.isBlank(queryInfo.getCreate_timestamp_begin())
            && StringUtil.isBlank(queryInfo.getCreate_timestamp_end()) && StringUtil.isBlank(queryInfo.getId_card())
            && StringUtil.isBlank(queryInfo.getData_status())
            && (StringUtil.isNotBlank(queryInfo.getBill_source()) || !queryInfo.getBill_source().equals("-1"))
            && StringUtil.isBlank(queryInfo.getCreate_user_name()) && StringUtil.isBlank(queryInfo.getSalesman_name())
            && StringUtil.isBlank(queryInfo.getBel_salesman_id_id()))
        {
            String time1 = new Timestamp(System.currentTimeMillis()).toString();
            String time = time1.split(" ")[0];
            paramMap.put("time", time);

        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.searchFinancial(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        for (Map<String, Object> map : list)
        {
            // 当该用户为理财业务部主管时，去除客户手机与身份证信息并添加用户ID
            map.remove("mobile_phone");
            map.remove("id_card");
            // 用户ID为理财查看页面判断是否显示手机和身份证字段需要
            map.put("user_id", userId);
        }
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getSdlcxx(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wms_inve_transa_id);
        Map<String, Object> paMap = new HashMap<>();
        // paMap.put("id_card", wmsInveTransa.getId_card());
        WmsInveCustomer wmsInveCustomer = null;
        if (wmsInveTransa.getWms_inve_customer_id() != null)
        {
            paMap.put("wms_inve_customer_id", wmsInveTransa.getWms_inve_customer_id());
            wmsInveCustomer = wmsInveCustomerDao.getByEntity(paMap);
        }

        resultMap.put("wmsInveCustomer", wmsInveCustomer);
        Map<String, Object> wmsInveTransaMap = new HashMap<String, Object>();
        try
        {
            wmsInveTransaMap = SysTools.convertBean(wmsInveTransa);
        }
        catch (Exception e)
        {

        }
        // 根据目前上单信息表中的各个总的id进行获取对应的人员信息
        Map<String, Object> listRY = new HashMap<String, Object>();
        Map<String, Object> mapYeWuYuan = pmPersonnelDao.getPersonnelByPersonnelId(wmsInveTransa.getSalesman_id());
        PmPersonnel buMenJingLi = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getDepartment_manager_id() != null ? wmsInveTransa.getDepartment_manager_id()
                                                                                                                            : -1);
        PmPersonnel zhongFenZong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getCenter_manager_id() != null ? wmsInveTransa.getCenter_manager_id()
                                                                                                                         : -1);
        PmPersonnel fuZhong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getVice_general_manager_id() != null ? wmsInveTransa.getVice_general_manager_id()
                                                                                                                          : -1);
        PmPersonnel zong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getGeneral_manager_id() != null ? wmsInveTransa.getGeneral_manager_id()
                                                                                                                  : -1);

        if (buMenJingLi == null)
        {
            buMenJingLi = new PmPersonnel();
        }
        if (zhongFenZong == null)
        {
            zhongFenZong = new PmPersonnel();
        }
        if (fuZhong == null)
        {
            fuZhong = new PmPersonnel();
        }
        if (zong == null)
        {
            zong = new PmPersonnel();
        }
        // 将上单信息表中的city_code赋值跟通过查询得到的对象
        mapYeWuYuan.put("personnel_regionnumber", wmsInveTransa.getSalesman_city_code());
        mapYeWuYuan.put("personnel_deptId", wmsInveTransa.getSalesman_dept_id());

        buMenJingLi.setPersonnel_deptid(wmsInveTransa.getDepartment_manager_dept_id());
        buMenJingLi.setPersonnel_regionnumber(wmsInveTransa.getDepartment_manager_city_code());

        zhongFenZong.setPersonnel_deptid(wmsInveTransa.getCenter_manager_dept_id());

        fuZhong.setPersonnel_deptid(wmsInveTransa.getVice_general_manager_dept_id());
        fuZhong.setPersonnel_regionnumber(wmsInveTransa.getVice_general_manager_city_code());

        zong.setPersonnel_deptid(wmsInveTransa.getGeneral_manager_dept_id());
        zong.setPersonnel_regionnumber(wmsInveTransa.getGeneral_manager_city_code());

        listRY.put("yeWuYuan", mapYeWuYuan);
        listRY.put("buMenJingLi", buMenJingLi);
        listRY.put("zhongFenZong", zhongFenZong);
        listRY.put("fuZhong", fuZhong);
        listRY.put("zong", zong);

        Map<String, Object> belListRY = new HashMap<String, Object>();
        Map<String, Object> belMapYeWuYuan = pmPersonnelDao.getPersonnelByPersonnelId(wmsInveTransa.getBel_salesman_id_id() != null ? wmsInveTransa.getBel_salesman_id_id()
                                                                                                                                   : -1);
        PmPersonnel belBuMenJingLi = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getBel_department_manager_id() != null ? wmsInveTransa.getBel_department_manager_id()
                                                                                                                                   : -1);
        PmPersonnel belZhongFenZong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getBel_center_manager_id() != null ? wmsInveTransa.getBel_center_manager_id()
                                                                                                                                : -1);
        PmPersonnel belFuZhong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getBel_vice_general_manager_id() != null ? wmsInveTransa.getBel_vice_general_manager_id()
                                                                                                                                 : -1);
        PmPersonnel belzong = pmPersonnelDao.getPersonnelByPersonnelId2(wmsInveTransa.getBel_general_manager_id() != null ? wmsInveTransa.getBel_general_manager_id()
                                                                                                                         : -1);

        belListRY.put("belMapYeWuYuan", belMapYeWuYuan);
        belListRY.put("belBuMenJingLi", belBuMenJingLi);
        belListRY.put("belZhongFenZong", belZhongFenZong);
        belListRY.put("belFuZhong", belFuZhong);
        belListRY.put("belzong", belzong);

        // 业务员目前状态（兼职:jz、在职:zz、离职:lz）
        PmPersonnel pmPersonnel = pmPersonnelDao.get(wmsInveTransa.getSalesman_id());
        String salesmanStatus = "";
        if ("7".equals(pmPersonnel.getPersonnel_state()))
        {
            salesmanStatus = "jz";
        }
        else if ("0".equals(pmPersonnel.getPersonnel_state()) || "1".equals(pmPersonnel.getPersonnel_state())
                 || "2".equals(pmPersonnel.getPersonnel_state()))
        {
            salesmanStatus = "zz";
        }
        else if ("3".equals(pmPersonnel.getPersonnel_state()) || "4".equals(pmPersonnel.getPersonnel_state()))
        {
            salesmanStatus = "lz";
        }
        wmsInveTransaMap.put("salesman_status", salesmanStatus);

        /* wmsInveTransaMap.put("department_manager_name", bmjlStr);
         wmsInveTransaMap.put("vice_general_manager_name", fzjlStr);
         wmsInveTransaMap.put("general_manager_name", zjlStr);*/
        wmsInveTransaMap.put("listRY", listRY);
        wmsInveTransaMap.put("belListRY", belListRY);
        resultMap.put("wmsInveTransa", wmsInveTransaMap);
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        resultMap.put("wmsInveTransaProd", wmsInveTransaProd);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramMap.put("sortname", "");
        paramMap.put("offset", "");
        List<Map<String, Object>> wmsInveTransaAttList = wmsInveTransaAttDao.search(paramMap);
        resultMap.put("wmsInveTransaAttList", wmsInveTransaAttList);
        return resultMap;
    }

    /**
     * @Title:getAmountCheckingReportWithPaging
     * 
     * Description:【理财上单流程】 【财务管理】-【理财上单】-【对账报表】 列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @Override
    public Map<String, Object> getAmountCheckingReportWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 金额状态
        if (StringUtil.isNotBlank(queryInfo.getAccount_status()))
        {
            paramMap.put("account_status", queryInfo.getAccount_status());
        }
        // 协议编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }
        // 卡主姓名
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        // 支付卡支付时间起始
        if (StringUtil.isNotBlank(queryInfo.getDate_of_payment_begin()))
        {
            paramMap.put("date_of_payment_begin", queryInfo.getDate_of_payment_begin());
        }
        // 支付卡支付时间截止
        if (StringUtil.isNotBlank(queryInfo.getDate_of_payment_end()))
        {
            paramMap.put("date_of_payment_end", queryInfo.getDate_of_payment_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsInveTransaCardDao.getAmountCheckingReport(paramMap);
        list = wmsInveTransaCardService.setPayName(list);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveTransaCardDao.findCountAmountCheckingReport(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title:getAmountCheckingReportWithoutPaging
     * Description:【理财上单流程】 【财务管理】-【理财上单】-【对账报表】 列表导出
     * @param queryInfo
     * @return record list
     * @author zhubo
     */
    @Override
    public Map<String, Object> getAmountCheckingReportWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                    UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 金额状态
        if (StringUtil.isNotBlank(queryInfo.getAccount_status()))
        {
            paramMap.put("account_status", queryInfo.getAccount_status());
        }
        // 协议编号
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        // 卡主姓名
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        // 支付卡支付时间起始
        if (StringUtil.isNotBlank(queryInfo.getDate_of_payment_begin()))
        {
            paramMap.put("date_of_payment_begin", queryInfo.getDate_of_payment_begin());
        }
        // 支付卡支付时间截止
        if (StringUtil.isNotBlank(queryInfo.getDate_of_payment_end()))
        {
            paramMap.put("date_of_payment_end", queryInfo.getDate_of_payment_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsInveTransaCardDao.getAmountCheckingReport(paramMap);
        list = wmsInveTransaCardService.setPayName(list);
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("Rows", list);
        return parMap;
    }

    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    public Map<String, Object> getMapForLc(Integer wms_inve_transa_id)
    {
        Map<String, Object> paramMap = wmsinvetransaDao.getMapForLc(wms_inve_transa_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("assign_account", paramMap.get("product_account"));// 本次转让额度
        map.put("wms_inve_pruduct_category_id", paramMap.get("wms_inve_pruduct_category_id"));// 上单产品主键
        map.put("prot_code", paramMap.get(""));// 协议编号
        BigDecimal bd = (BigDecimal) paramMap.get("product_account");
        map.put("product_account", getDecimalFormat(bd));// 上单金额
        map.put("product_account_lower", paramMap.get("product_account"));// 上单金额小写
        map.put("date_of_payment", paramMap.get("date_of_payment"));// 支付时间
        map.put("b_name", paramMap.get("cus_name"));// 乙方姓名
        map.put("b_id_card", paramMap.get("id_card"));// 乙方身份证号码
        map.put("category_name", paramMap.get("category_name"));// 产品名称
        map.put("product_deadline", paramMap.get("product_deadline"));// 理财期限
        map.put("sign_place", paramMap.get("salesman_city"));// 协议签署地
        map.put("contact_address", paramMap.get("cus_address"));// 客户通讯地址
        map.put("post_code", paramMap.get("post_code"));// 客户通讯地址邮编
        List<Integer> list = new ArrayList<Integer>();
        list.add(8);
        list.add(9);
        list.add(30);
        list.add(31);
        list.add(32);
        List<WmsSysProperty> li = wmssyspropertyDao.searchforhouse(list);
        WmsSysProperty wms = (WmsSysProperty) li.get(0);
        map.put("a_name", wms.getProperty_value());// 甲方 原债权人姓名
        WmsSysProperty wms1 = (WmsSysProperty) li.get(1);
        map.put("a_id_card", wms1.getProperty_value());// 甲方 原债权人身份证号码
        WmsSysProperty wms2 = (WmsSysProperty) li.get(2);
        map.put("a_bank", wms2.getProperty_value());// 甲方 开户银行
        WmsSysProperty wms3 = (WmsSysProperty) li.get(3);
        map.put("a_number", wms3.getProperty_value());// 甲方 开户银行账号
        WmsSysProperty wms4 = (WmsSysProperty) li.get(4);
        map.put("b_data", wms4.getProperty_value());// 具体资料

        map.put("end_of_date",
                setDatebyCalendar((Date) paramMap.get("date_of_payment"), (int) paramMap.get("product_deadline")));// 设置协议结束日期
        map.put("date_of_payment", paramMap.get("date_of_payment"));// 设置支付日期date_of_payment
        map.put("prot_code", CodeNoUtil.getEnviProCode());// 转让编码
        return map;
    }

    public String getDecimalFormat(BigDecimal str)
    {
        String outStr = "";
        if (str.compareTo(new BigDecimal(0)) > 0)
        {
            BigDecimal str1 = str;
            DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.00");
            try
            {
                outStr = fmt.format(str1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return outStr;
    }

    /**
     * 设置日期 参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date setDatebyCalendar(Date sDate, int i)
    {

        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        calendar.add(Calendar.MONTH, +i);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }

    // 获取当前日期
    private Date getNow()
    {
        Calendar nowcalendar = Calendar.getInstance();
        return new Date(nowcalendar.getTimeInMillis());
    }

    /**
     * 获取债权匹配列表数据excel导出
     * 
     * @param queryInfo
     * @return Map
     * @author 白松
     */
    @Override
    public Map<String, Object> getList(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", "%" + queryInfo.getCategory_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsinvetransaDao.searchForMatch(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        map.put("Rows", list);
        return map;
    }

    // 设置上单客户对象属性的值
    public WmsInveCustomer setWmsInveCustomer(WmsInveCustomer wmsInveCustomer, WmsInveTransa wmsInveTransa,
                                              UserBean user, Timestamp sysTime)
    {
        wmsInveCustomer.setId_card(wmsInveTransa.getId_card());
        wmsInveCustomer.setCus_code(CodeNoUtil.getEnviCustomerCode());
        wmsInveCustomer.setCus_name(wmsInveTransa.getCus_name());
        wmsInveCustomer.setCus_birthday(wmsInveTransa.getCus_birthday());
        wmsInveCustomer.setCus_gender(wmsInveTransa.getCus_gender());
        wmsInveCustomer.setCus_address(wmsInveTransa.getCus_address());
        wmsInveCustomer.setPost_code(wmsInveTransa.getPost_code());
        wmsInveCustomer.setAddress_phone(wmsInveTransa.getAddress_phone());
        wmsInveCustomer.setWork_phone(wmsInveTransa.getWork_phone());
        wmsInveCustomer.setCus_fax(wmsInveTransa.getCus_fax());
        wmsInveCustomer.setContact_address(wmsInveTransa.getContact_address());
        wmsInveCustomer.setCustomer_email(wmsInveTransa.getCustomer_email());
        wmsInveCustomer.setSalesman_name(wmsInveTransa.getSalesman_name());
        wmsInveCustomer.setSalesman_id(wmsInveTransa.getSalesman_id());
        wmsInveCustomer.setSalesman_city_code(wmsInveTransa.getSalesman_city_code());
        wmsInveCustomer.setSalesman_city(user.getUser_city());
        wmsInveCustomer.setSalesman_dept_id(wmsInveTransa.getSalesman_dept_id());
        wmsInveCustomer.setCreate_user_dept_id(user.getDeptId());
        wmsInveCustomer.setCreate_user_id(user.getUserId());
        wmsInveCustomer.setCreate_user_name(user.getRealname());
        wmsInveCustomer.setCreate_timestamp(sysTime);
        wmsInveCustomer.setLast_update_user_id(user.getUserId());
        wmsInveCustomer.setLast_update_timestamp(sysTime);
        wmsInveCustomer.setEnable_flag("1");
        wmsInveCustomer.setMobile_phone(wmsInveTransa.getMobile_phone());
        // 存储部门经理
        wmsInveCustomer.setDepartment_manager_id(wmsInveCustomer.getDepartment_manager_id());
        wmsInveCustomer.setDepartment_manager_city_code(wmsInveCustomer.getDepartment_manager_city_code());
        wmsInveCustomer.setDepartment_manager_dept_id(wmsInveCustomer.getDepartment_manager_dept_id());
        // 存储副总经理
        wmsInveCustomer.setVice_general_manager_id(wmsInveCustomer.getVice_general_manager_id());
        wmsInveCustomer.setVice_general_manager_city_code(wmsInveCustomer.getVice_general_manager_city_code());
        wmsInveCustomer.setVice_general_manager_dept_id(wmsInveCustomer.getVice_general_manager_dept_id());
        // 存储总经理
        wmsInveCustomer.setGeneral_manager_id(wmsInveCustomer.getGeneral_manager_id());
        wmsInveCustomer.setGeneral_manager_city_code(wmsInveCustomer.getGeneral_manager_city_code());
        ;
        wmsInveCustomer.setGeneral_manager_dept_id(wmsInveCustomer.getGeneral_manager_dept_id());
        return wmsInveCustomer;
    }

    // 设置上单信息属性的值（修改时）
    public WmsInveTransa setWmsInveTransaForUpdate(WmsInveTransa wmsInveTransa, WmsInveCustomer wmsInveCustomer, WmsInveTransaProd wmsInveTransaProd,
                                                   String saveFlag, Timestamp sysTime, UserBean user, String data_status)
    {
        boolean flag = false;

        if (wmsInveCustomer != null)
        {
            wmsInveTransa.setWms_inve_customer_id(wmsInveCustomer.getWms_inve_customer_id());
        }
        if ("1".equals(saveFlag))
        {
            wmsInveTransa.setData_status("2");

            if ("12".equals(data_status) || "19".equals(data_status))
            {
                flag = true;
            }
            if (!flag)
            {
                wmsInveTransa.setAccount_status("1");
            }

            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
            {
                wmsInveTransa.setInve_transa_type("2");
            }
            else
            {
                wmsInveTransa.setInve_transa_type("1");
            }
        }
        else if ("0".equals(saveFlag))
        {
            wmsInveTransa.setData_status("1");
        }
        if (StringUtil.isNotBlank(wmsInveTransa.getSalesman_city_code()))
        {
            wmsInveTransa.setSalesman_city(UserCityUtil.getUserCity(wmsInveTransa.getSalesman_city_code()));
        }
        wmsInveTransa.setLast_update_user_id(user.getUserId());
        wmsInveTransa.setLast_update_timestamp(sysTime);
        return wmsInveTransa;
    }

    // 设置上单信息属性的值（新增时）
    public WmsInveTransa setWmsInveTransaForAdd(WmsInveTransa wmsInveTransa, WmsInveCustomer wmsInveCustomer,
                                                WmsInveTransaProd wmsInveTransaProd, String saveFlag,
                                                Timestamp sysTime, UserBean user)
    {
        if (wmsInveCustomer != null)
        {
            wmsInveTransa.setWms_inve_customer_id(wmsInveCustomer.getWms_inve_customer_id());
        }
        wmsInveTransa.setBill_code(CodeNoUtil.getEnviNoteCode());
        if ("1".equals(saveFlag))
        {
            wmsInveTransa.setData_status("2");
            wmsInveTransa.setAccount_status("1");
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
            {
                wmsInveTransa.setInve_transa_type("2");
            }
            else
            {
                wmsInveTransa.setInve_transa_type("1");
            }
        }
        else if ("0".equals(saveFlag))
        {
            wmsInveTransa.setData_status("1");
        }
        if (StringUtil.isNotBlank(wmsInveTransa.getSalesman_city_code()))
        {
            wmsInveTransa.setSalesman_city(UserCityUtil.getUserCity(wmsInveTransa.getSalesman_city_code()));
        }
        wmsInveTransa.setCreate_user_id(user.getUserId());
        wmsInveTransa.setCreate_user_name(user.getRealname());
        wmsInveTransa.setCreate_user_dept_id(user.getDeptId());
        wmsInveTransa.setCreate_timestamp(sysTime);
        wmsInveTransa.setLast_update_user_id(user.getUserId());
        wmsInveTransa.setLast_update_timestamp(sysTime);
        wmsInveTransa.setEnable_flag("1");
        return wmsInveTransa;
    }

    // 设置上单产品属性的值（修改时）
    public WmsInveTransaProd setWmsInveTransaProdForUpdate(WmsInveTransaProd wmsInveTransaProd, UserBean user,
                                                           Timestamp sysTime)
    {
        //判断银行卡所属银行是否为空
        if(wmsInveTransaProd.getBank_of_deposit() != null && !"".equals(wmsInveTransaProd.getBank_of_deposit()))
        {
            if(wmsInveTransaProd.getBank_of_deposit().contains("广东发展银行"))
            {
                wmsInveTransaProd.setBank_of_deposit("广发银行");
            }
            else if(wmsInveTransaProd.getBank_of_deposit().contains("中国光大银行"))
            {
                wmsInveTransaProd.setBank_of_deposit("光大银行");
            }
        }
        wmsInveTransaProd.setLast_update_user_id(user.getUserId());
        wmsInveTransaProd.setLast_update_timestamp(sysTime);
        wmsInveTransaProd.setOrg_product_account(wmsInveTransaProd.getProduct_account());
        return wmsInveTransaProd;
    }

    // 设置上单产品属性的值（新增时）
    public WmsInveTransaProd setWmsInveTransaProdForAdd(WmsInveTransaProd wmsInveTransaProd,
                                                        WmsInveTransa wmsInveTransa, UserBean user, Timestamp sysTime)
    {
        wmsInveTransaProd.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
        wmsInveTransaProd.setIs_finish("0");
        wmsInveTransaProd.setCreate_user_id(user.getUserId());
        wmsInveTransaProd.setCreate_user_name(user.getRealname());
        wmsInveTransaProd.setCreate_timestamp(sysTime);
        wmsInveTransaProd.setLast_update_user_id(user.getUserId());
        wmsInveTransaProd.setLast_update_timestamp(sysTime);
        wmsInveTransaProd.setEnable_flag("1");
        wmsInveTransaProd.setOrg_product_account(wmsInveTransaProd.getProduct_account());
        if (wmsInveTransaProd.getProduct_deadline() != null)
        {
            wmsInveTransaProd.setProduct_deadline_name(wmsInveTransaProd.getProduct_deadline() + "期");
        }

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
        paramsMap.put("product_account", wmsInveTransaProd.getProduct_account());
        paramsMap.put("product_dealine", wmsInveTransaProd.getProduct_deadline());

        Map<String, Object> map = wmsinvetransaDao.getCategoryIncomeByCategoryId(paramsMap);
        if (map != null && map.size() > 0)
        {
            if (map.get("reward_interest") != null)
            {
                wmsInveTransaProd.setReward_interest(new BigDecimal(map.get("reward_interest").toString()));
            }
            if (map.get("expect_interest_account") != null)
            {
                wmsInveTransaProd.setExpect_interest_account(new BigDecimal(map.get("expect_interest_account").toString()));
            }
        }
        
        //判断银行卡所属银行是否为空
        if(wmsInveTransaProd.getBank_of_deposit() != null && !"".equals(wmsInveTransaProd.getBank_of_deposit()))
        {
            if(wmsInveTransaProd.getBank_of_deposit().contains("广东发展银行"))
            {
                wmsInveTransaProd.setBank_of_deposit("广发银行");
            }
            else if(wmsInveTransaProd.getBank_of_deposit().contains("中国光大银行"))
            {
                wmsInveTransaProd.setBank_of_deposit("光大银行");
            }
        }

        return wmsInveTransaProd;
    }

    // 设置上单奖励利率对象
    private WmsInveTransaProdReward setWmsInveTransaProdReward(WmsInveTransaProdReward wmsInveTransaProdReward,
                                                               WmsInveTransaProd wmsInveTransaProd,
                                                               Map<String, Object> wmsInvePruductDeadlineReward)
    {
        wmsInveTransaProdReward.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        wmsInveTransaProdReward.setProduct_deadline_month(Integer.parseInt(wmsInvePruductDeadlineReward.get("product_deadline_month")
                                                                                                       .toString()));
        wmsInveTransaProdReward.setReward_interest(new BigDecimal(
                                                                  Double.parseDouble(wmsInvePruductDeadlineReward.get("reward_interest")
                                                                                                                 .toString())));
        wmsInveTransaProdReward.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
        return wmsInveTransaProdReward;
    }

    // 设置上件附件对象
    private WmsInveAtt setWmsInveAtt(WmsInveAtt wmsInveAtt, WmsInveTransa wmsInveTransa)
    {
        wmsInveAtt.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
        return wmsInveAtt;
    }

    /**
     * 实现赎回申请查询
     * 
     * 修改内容：赎回申请原先对角色的判断过滤更新  现在只对 财务部门主管   理财财务专员  财务柜员  超级管理员  查看所有符合条件的单据
     * 修改时间: 2015-09-07 12:11
     * 修改人: hancd
     */
    @Override
    public Map<String, Object> getRedeemApplyListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SysUserRole> sysyuserroleList = sysuserroleDao_m.getUserRole(user.getUserId());
        for (SysUserRole sysyuserrole : sysyuserroleList)
        {
            // // 判断用户的角色是否为业务员
            // if (sysyuserrole.getRole_id() == 137)
            // {
            // paramMap.put("salesman_id", user.getUserId());
            // }
            // // 判断用户的角色是否为接待专员
            // if (sysyuserrole.getRole_id() == 141)
            // {
            // paramMap.put("reception_personnel", 1);
            // }
            // // 判断用户的角色是否为超级用户
            // if (sysyuserrole.getRole_id() == 110)
            // {
            // paramMap.put("super_user", 1);
            // }
            // 财务部门主管 理财财务专员 财务柜员 超级管理员
            if (sysyuserrole.getRole_id() == 128)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 138)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 150)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 110)
            {
                paramMap.put("super_user", 1);
            }
            if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
            {
                paramMap.put("bill_source", queryInfo.getBill_source());
            }
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        String[] sort = queryInfo.getSortname().split(",");
        paramMap.put("sortname", sort[0]);
        paramMap.put("sortorder", sort[1]);
        paramMap.put("sortname1", sort[2]);
        paramMap.put("sortorder1", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.redeemApplyList(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountRedeem(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 实现赎回申请导出
     */
    @Override
    public Map<String, Object> getRedeemApplyListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SysUserRole> sysyuserroleList = sysuserroleDao_m.getUserRole(user.getUserId());
        for (SysUserRole sysyuserrole : sysyuserroleList)
        {
            // // 判断用户的角色是否为业务员
            // if (sysyuserrole.getRole_id() == 137)
            // {
            // paramMap.put("salesman_id", user.getUserId());
            // }
            // // 判断用户的角色是否为接待专员
            // if (sysyuserrole.getRole_id() == 141)
            // {
            // paramMap.put("reception_personnel", 1);
            // }
            // // 判断用户的角色是否为超级用户
            // if (sysyuserrole.getRole_id() == 110)
            // {
            // paramMap.put("super_user", 1);
            // }
            // 财务部门主管 理财财务专员 财务柜员 超级管理员
            if (sysyuserrole.getRole_id() == 128)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 138)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 150)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 110)
            {
                paramMap.put("super_user", 1);
            }
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        String[] sort = queryInfo.getSortname().split(",");
        paramMap.put("sortname", sort[0]);
        paramMap.put("sortorder", sort[1]);
        paramMap.put("sortname1", sort[2]);
        paramMap.put("sortorder1", queryInfo.getSortorder());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.redeemApplyList(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * 获取协议续期列表数据excel导出
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getListRenewalExcel(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", "%" + queryInfo.getCategory_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp()))
        {
            StringBuffer sb = new StringBuffer(queryInfo.getCreate_timestamp());
            sb.append(" 23:59:59.9");
            paramMap.put("create_timestamp", Convert.toDate(queryInfo.getCreate_timestamp()));
            paramMap.put("create_timestamp1", sb.toString());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = wmsinvetransaDao.searchForRenewal(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        map.put("Rows", list);
        return map;
    }

    @Override
    public Map<String, Object> getApprovalFinancialListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", SysTools.getSqlLikeParam(queryInfo.getMobile_phone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }

        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("data_status", "4");// 收益中
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.approvalFinancial(paramMap);
        for (Map<String, Object> map : list)
        {
            PmPersonnel pmPersonnel = pmPersonnelDao.get((Integer) map.get("salesman_id"));
            if (pmPersonnel != null)
            {
                map.put("deptId", pmPersonnel.getPersonnel_deptid());
                map.put("deptName", pmPersonnel.getPersonnel_deptname());
                SysDept sysDept = sysdeptDao.getFatherDeptInfo(pmPersonnel.getPersonnel_deptid());
                if (sysDept != null)
                {
                    map.put("deptPId", sysDept.getDept_id());
                    map.put("deptPName", sysDept.getDept_name());
                }
            }
        }
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountApprovalFinancial(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getApprovalFinancialListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,
                                                                     UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", SysTools.getSqlLikeParam(queryInfo.getMobile_phone()));
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }

        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        paramMap.put("data_status", "4");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.approvalFinancial(paramMap);
        for (Map<String, Object> map : list)
        {
            PmPersonnel pmPersonnel = pmPersonnelDao.get((Integer) map.get("salesman_id"));
            if (pmPersonnel != null)
            {
                map.put("deptId", pmPersonnel.getPersonnel_deptid());
                map.put("deptName", pmPersonnel.getPersonnel_deptname());
                SysDept sysDept = sysdeptDao.getFatherDeptInfo(pmPersonnel.getPersonnel_deptid());
                if (sysDept != null)
                {
                    map.put("deptPId", sysDept.getDept_id());
                    map.put("deptPName", sysDept.getDept_name());
                }
            }
        }
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;
    }

    @Override
    public Map<String, Object> getApprovalFinancialInfo(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id,
                                                        Integer wms_inve_transa_protocol_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        List<Map<String, Object>> list = wmsinvetransaDao.approvalFinancial(paramMap);
        Map<String, Object> resMap = null;
        if (list != null && list.size() > 0)
        {
            resMap = list.get(0);
            // 已还期数
            int payNumber = 0;
            // 实际支付时间
            Date dateOfPayment = (Date) resMap.get("date_of_payment");
            payNumber = getPayNumber(dateOfPayment, new java.util.Date());
            resMap.put("payNumber", payNumber);
            WmsInveTransaSpecialappChange t = new WmsInveTransaSpecialappChange();
            t.setWms_inve_transa_id(wms_inve_transa_id);
            List<WmsInveTransaSpecialappChange> changes = wmsInveTransaSpecialappChangeDao.getListByEntity(t);
            if (changes != null && changes.size() > 0)
            {
                WmsInveTransaSpecialappChange change = changes.get(0);
                resMap.put("remark", change.getRemark());
                resMap.put("wms_inve_transa_specialapp_change_id", change.getWms_inve_transa_specialapp_change_id());
            }
        }

        return resMap;
    }

    /**
     * @Title: getPayNumber 
     * @Description: 计算已换期数
     * @param startDate
     * @param endDate
     * @return int 
     * @throws
     * @author lvtu 
     * @date 2015年9月8日 上午11:46:38
     */
    private int getPayNumber(java.util.Date startDate, java.util.Date endDate)
    {
        int payNumber = 0;
        while (DateUtil.before(DateUtil.getLastDayOfMonth(startDate), endDate))
        {
            if (DateUtil.isFirstDayOfMonth(startDate))
            {
                payNumber++;
            }

            startDate = DateUtil.getFirstDayOfNextMonth(startDate);
        }
        return payNumber;
    }

    /**
     * 理财业务特批详细信息
     */
    @Override
    public Map<String, Object> getApprovalFinancialywInfo(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id,
                                                          Integer wms_inve_transa_protocol_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        List<Map<String, Object>> list = wmsinvetransaDao.approvalFinancialyw(paramMap);
        Map<String, Object> resMap = null;
        if (list != null && list.size() > 0)
        {
            resMap = list.get(0);
            // 提成系数查询
            // 理财业务单据特批表(wms_inve_commission_bill_sp)是否为空,如果为空查找一般规则配置主表(wms_inve_commission_general_rules)
            BigDecimal commissionCoeff = new BigDecimal(0);
            WmsInveCommissionBillSp billSp = new WmsInveCommissionBillSp();
            billSp.setWms_inve_transa_id(wms_inve_transa_id);
            List<WmsInveCommissionBillSp> sps = wmsInveCommissionBillSpDao.getListByEntity(billSp);
            if (sps != null && sps.size() > 0)
            {
                billSp = sps.get(0);
                commissionCoeff = billSp.getCommission_coeff();
                resMap.put("remark", billSp.getRemark());
                resMap.put("wms_inve_commission_bill_sp_id", billSp.getWms_inve_commission_bill_sp_id());
            }
            else
            {
                WmsInveCommissionGeneralRules t = new WmsInveCommissionGeneralRules();
                t.setJob_code(Integer.parseInt(resMap.get("post_id").toString()));
                t.setEmployee_state(Integer.parseInt(resMap.get("personnel_state").toString()));

                t.setRule_state(0);
                List<WmsInveCommissionGeneralRules> generalRules = wmsInveCommissionGeneralRulesDao.getListByEntity(t);
                if (generalRules != null && generalRules.size() > 0)
                {
                    for (WmsInveCommissionGeneralRules rules : generalRules)
                    {
                        WmsInveCommissionCategory category = new WmsInveCommissionCategory();
                        category.setWms_inve_commission_category_id(Integer.parseInt(resMap.get("wms_inve_pruduct_category_id")
                                                                                           .toString()));
                        category.setWms_inve_commission_general_rules_id(rules.getWms_inve_commission_general_rules_id());
                        List<WmsInveCommissionCategory> categorys = wmsInveCommissionCategoryDao.getListByEntity(category);
                        if (categorys != null && categorys.size() > 0)
                        {
                            commissionCoeff = null2zero(rules.getBase_commission_monthly1()).add(null2zero(rules.getPositive_commission_monthly1()))
                                                                                            .add(null2zero(rules.getBase_commission_monthly2()))
                                                                                            .add(null2zero(rules.getPositive_commission_monthly2()));
                            // 判断上单时间是否是当月，是当月则加上一次性佣金，如果不是当月不加一次性佣金
                            Date dateOfPayment = (Date) resMap.get("date_of_payment");
                            if (DateUtil.getDate10(dateOfPayment).compareTo(DateUtil.getDate10(new java.util.Date())) == 0)
                            {
                                commissionCoeff = commissionCoeff.add(null2zero(rules.getBase_commission_disposable()))
                                                                 .add(null2zero(rules.getPositive_commission_disposable()));
                            }

                        }
                    }
                }
            }

            resMap.put("commission_coeff", commissionCoeff);
        }

        return resMap;
    }

    /**
     * @Title: null2zero 
     * @Description: null转换成0
     * @param decimal
     * @return BigDecimal 
     * @throws
     * @author lvtu 
     * @date 2015年9月22日 上午11:55:46
     */
    private BigDecimal null2zero(BigDecimal decimal)
    {
        if (decimal == null)
        {
            return new BigDecimal(0);
        }
        return decimal;
    }

    /**
     * 根据上单信息表主键获取相关信息--上单表 上单产品表 
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getObjectInfo(WmsInveTransaCardSearchBeanVO queryInfo)
    {
        Map<String, Object> map = wmsinvetransaDao.getObjectInfo(Integer.valueOf(queryInfo.getWms_inve_transa_id()));
        return map;
    }

    /**
     * @Title: getFinancialSingleProcessView 
     * Description :【理财上单】流程历程查看
     * @param  wms_inve_transa_id
     * @return Map
     * @date 2015年12月12日 上午10:02
     * @author hancd
     */
    @Override
    public Map<String, Object> getFinancialSingleProcessView(String wms_inve_transa_id)
    {
        return wmsInveWorkFlowService.inveWorkFlowProcess(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                          wms_inve_transa_id);
    }

    /**
     * @Title: toBackforJEZF 
     * Description :【理财上单】财务管理>金额管理>支付退回操作
     * @param bean
     * @param itcardJSON
     * @param user
     * @param wmsTransaProd
     * @param ransaAtt
     * @param flagKey
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @Override
    public String toBackforJEZF(WmsInveTransa bean, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO)
    {
        String result = "success";
        // 存储业务数据
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        WmsInveTransaApprovalInfo wmsInveTransaApprovalInfo = new WmsInveTransaApprovalInfo();
        WmsInveTransaApprovalInfo t = new WmsInveTransaApprovalInfo();
        t.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        List<WmsInveTransaApprovalInfo> wmsInveTransaApprovalInfos = wmsInveTransaApprovalInfoDao.getListByEntity(t);
        try
        {
            wmsInveTransaApprovalInfo.setWms_inve_transa_approval_info_id(wmsInveTransaApprovalInfos.get(0)
                                                                                                    .getWms_inve_transa_approval_info_id());
        }
        catch (Exception e)
        {
            result = "error";
        }
        // 更改金额到账状态
        bean.setAccount_status("1");// 1待支付 2已支付 3已到账 4部分到账
        wmsinvetransaDao.update(bean);

        wmsInveTransaApprovalInfo.setAmount_paid_id(user.getUserId());
        wmsInveTransaApprovalInfo.setAmount_paid_name(user.getRealname());
        wmsInveTransaApprovalInfo.setAmount_paid_time(sysTime);
        wmsInveTransaApprovalInfo.setAmount_paid_advice(wDebtWorkFlowVO.getAdvice());
        wmsInveTransaApprovalInfo.setAmount_paid__result(wDebtWorkFlowVO.getPass().equals("true") ? 1 : 0);
        wmsInveTransaApprovalInfo.setLast_update_user_id(user.getUserId());
        wmsInveTransaApprovalInfo.setLast_update_timestamp(sysTime);
        wmsInveTransaApprovalInfoDao.update(wmsInveTransaApprovalInfo);

        // 执行流程
        WmsInveDebtWorkFlowVO workFlowVO = new WmsInveDebtWorkFlowVO();
        workFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        workFlowVO.setBusinessId(bean.getWms_inve_transa_id().toString());
        workFlowVO.setDebtkey("2");
        workFlowVO.setPass("false");
        workFlowVO.setUserID(user.getUserId().toString());
        workFlowVO.setTaskId(wDebtWorkFlowVO.getTaskId());
        workFlowVO.setAdvice(wDebtWorkFlowVO.getAdvice());
        try
        {
            wmsInveWorkFlowService.publicApproval(workFlowVO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = "error";
        }
        return result;
    }

    @Override
    public String doCancel(String wms_inve_transa_id, String status, String taskId, String different, UserBean user,
                           String adviceLC)
    {
        if (different.equals("SHTH"))
        {// 审核退回
         // 调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id.toString());// 上单信息表id
            wDebtWorkFlowVO.setDebtkey("6");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        return "success";
    }

    /**
     * Description :【理财上单】-【上单审核】获取审核列表
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @Override
    public Map<String, Object> getListForSDSHWithPaging(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "3");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.getListForSDSHList(paramMap);
        // 加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                      (List<String>) paramMap.get("taskIdList"),
                                                      (String) paramMap.get("processDefinitionKey"));
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountForSDSHList(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * Description :【理财上单】-【上单审核】获取审核导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @Override
    public Map<String, Object> getListForSDSHWithoutPaging(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "3");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.getListForSDSHList(paramMap);

        paramMap = new HashMap<String, Object>();
        paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                               user.getUserId().toString(), "3");// 待审核

        // 加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                       (List<String>) paramMap.get("taskIdList"),
                                                       (String) paramMap.get("processDefinitionKey"));

        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * Description :理财复核列表
     * @author 焦德龙
     * @date 2015年12月16日 11:41
     */
    @Override
    public Map<String, Object> getFinancialReturnpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "1");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("wms_inve_pruduct_category_id", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.searchList(paramMap);
        // 加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                       (List<String>) paramMap.get("taskIdList"),
                                                       (String) paramMap.get("processDefinitionKey"));

        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findListCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * Description :【理财复核】导出
     * @author 焦德龙
     * @date 2015年12月16日 11:41
     */
    @Override
    public Map<String, Object> getFinancialReturnWithoutPaging(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "1");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("wms_inve_pruduct_category_id", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", null);
        paramMap.put("sortorder", null);
        paramMap.put("pagesize", null);
        paramMap.put("offset", null);
        paramMap.put("userid", user.getUserId());
        List<Map<String, Object>> list = wmsinvetransaDao.searchList(paramMap);

        paramMap = new HashMap<String, Object>();
        paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                               user.getUserId().toString(), "1");// 待退单确认

        // 加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                       (List<String>) paramMap.get("taskIdList"),
                                                       (String) paramMap.get("processDefinitionKey"));
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     * @Title: toSingleNullifyforSDSH 
     * Description :【理财上单】财务管理>理财上单>上单审核>单据作废
     * @param bean
     * @param wDebtWorkFlowVO
     * @return "success" or "error" or user definedSS
     * @date 2015年12月14日 14:56
     * @author hancd
     */
    @Override
    public String toSingleNullify(WmsInveTransa bean, WmsInveTransaProd wTransaProd, UserBean user,
                                  WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String debtkey)
    {
        String result = "success";
        // 相关业务数据存储
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        WmsInveTransaApprovalInfo wmsInveTransaApprovalInfo = new WmsInveTransaApprovalInfo();
        WmsInveTransaApprovalInfo t = new WmsInveTransaApprovalInfo();
        t.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        List<WmsInveTransaApprovalInfo> wmsInveTransaApprovalInfos = wmsInveTransaApprovalInfoDao.getListByEntity(t);
        try
        {
            wmsInveTransaApprovalInfo.setWms_inve_transa_approval_info_id(wmsInveTransaApprovalInfos.get(0)
                                                                                                    .getWms_inve_transa_approval_info_id());
        }
        catch (Exception e)
        {
            result = "error";
        }
        wmsInveTransaApprovalInfo.setWms_inve_transa_id(bean.getWms_inve_transa_id());
        wmsInveTransaApprovalInfo.setWms_inve_transa_prod_id(wTransaProd.getWms_inve_transa_prod_id());
        wmsInveTransaApprovalInfo.setBill_code(wmsinvetransaDao.get(bean.getWms_inve_transa_id()).getBill_code());
        wmsInveTransaApprovalInfo.setSupervisor_confirmation_void_id(user.getUserId());
        wmsInveTransaApprovalInfo.setSupervisor_confirmation_void_name(user.getRealname());
        wmsInveTransaApprovalInfo.setSupervisor_confirmation_void_advice(wDebtWorkFlowVO.getAdvice());
        wmsInveTransaApprovalInfo.setSupervisor_confirmation_void_time(sysTime);
        wmsInveTransaApprovalInfo.setCreate_user_id(user.getUserId());
        wmsInveTransaApprovalInfo.setCreate_user_name(user.getRealname());
        wmsInveTransaApprovalInfo.setCreate_timestamp(sysTime);
        wmsInveTransaApprovalInfo.setEnable_flag("1");
        wmsInveTransaApprovalInfoDao.update(wmsInveTransaApprovalInfo);

        // 单据作废的同时更新支付信息
        wmsInveTransaInvalid(bean.getWms_inve_transa_id(), user);

        // 执行流程
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wDebtWorkFlowVO.setBusinessId(bean.getWms_inve_transa_id().toString());
        wDebtWorkFlowVO.setPass("nullify");
        wDebtWorkFlowVO.setDebtkey(debtkey);// 流程节点
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        try
        {
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        catch (Exception e)
        {
            result = "error";
        }
        return result;
    }

    /**
     * @Deprecated 上单单据作废时需要将支付信息设置成未生效,同时如果是续单本金类型的数据,需要将试用金额还回去
     * @param wms_inve_transa_id
     * @author donghao
     * @date 2016年9月2日16:31:37
     */
    public void wmsInveTransaInvalid(int wms_inve_transa_id, UserBean user)
    {
        // 单据作废,需要处理上单支付信息设置成失效,如何支付信息中含有续单本金信息也需要释放续单本金的金额
        List<Map<String, Object>> wmsInveTransaCardMap = wmsInveTransaCardDao.getWmsInveTransaCardInfoByWmsInveTransaId(wms_inve_transa_id);
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        for (Map<String, Object> map : wmsInveTransaCardMap)
        {

            if ("3".equals("" + map.get("pay_type")))
            {
                if ("1".equals("" + map.get("is_valid")))
                {
                    int wms_inve_redeem_principal_detail_id = (int) map.get("wms_inve_redeem_principal_detail_id");

                    WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
                    wmsInveRedeemPrincipalDetail.setWms_inve_redeem_principal_detail_id(wms_inve_redeem_principal_detail_id);
                    wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal("" + map.get("act_account")));
                    wmsInveRedeemPrincipalDetailDao.updateToUsedIncomeAmount(wmsInveRedeemPrincipalDetail);
                }
            }

            WmsInveTransaCard wmsInveTransaCard = new WmsInveTransaCard();
            wmsInveTransaCard.setIs_valid("0");
            wmsInveTransaCard.setWms_inve_transa_card_id((int) map.get("wms_inve_transa_card_id"));
            wmsInveTransaCard.setLast_update_user_id(user != null ? user.getUserId() : 0);
            wmsInveTransaCard.setLast_update_timestamp(sysTime);
            wmsInveTransaCard.setEnable_flag("0");
            wmsInveTransaCardDao.update(wmsInveTransaCard);
        }
    }

    /**
     *@Title: getPrintProtocolWithPaginglist 
    * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表数据
    * @param queryInfo
    * @return record list
    * @author hancd
    * @date 2015年12月15日 13:11
    */
    @Override
    public Map<String, Object> getPrintProtocolWithPaginglist(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "4");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.getProtocolToConfirmList(paramMap);
        // 加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                      (List<String>) paramMap.get("taskIdList"),
                                                      (String) paramMap.get("processDefinitionKey"));
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountProtocolToConfirmList(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(打印协议/客户确认)  获取签约列表导出
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @Override
    public Map<String, Object> getPrintProtocolWithoutPaginglist(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "4");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.getProtocolToConfirmList(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;
    }

    /**
     *@Title: getPrintProtocolWithoutPaginglist 
     * Description :【财务管理】-【理财上单】-【理财签约】(客户确认)操作
     * @param request
     * @param wTransaProd
     * @param wDebtWorkFlowVO
     * @param fileArr
     * @return "success" or "error" or other
     * @author hancd
     * @date 2015年12月15日 13:11
     */
    @Override
    public String toSingleConfirmforLCQY(WmsInveTransaProd wTransaProd, UserBean user,
                                         WmsInveDebtWorkFlowVO wDebtWorkFlowVO, String fileArr)
    {
        String result = "success";
        // 获取客户签字附件信息
        List<WmsInveAtt> wmsInveAtts = JsonUtil.jsonArrayToList(fileArr, WmsInveAtt.class);
        for (WmsInveAtt att : wmsInveAtts)
        {
            att.setData_type("2");// 代表客户确认上传的附件信息标记
            att.setWms_inve_transa_id(wTransaProd.getWms_inve_transa_id());
            wmsInveAttDao.save(att);
        }
        // 保存客户确认信息
        setWmsInveTransaCusConfirm(wTransaProd, user);
        // 修改上单产品表客户确认状态
        WmsInveTransaProd wmsInveP = new WmsInveTransaProd();
        wmsInveP.setWms_inve_transa_prod_id(wTransaProd.getWms_inve_transa_prod_id());
        wmsInveP.setIs_customer_confirmation("1");
        wmsInveTransaProdDao.update(wmsInveP);
        // TDDO以后需要添加代码.......
        // 执行流程
        WmsInveDebtWorkFlowVO wmsInveDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
        wmsInveDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wmsInveDebtWorkFlowVO.setPass("true");
        wmsInveDebtWorkFlowVO.setBusinessId(wTransaProd.getWms_inve_transa_id().toString());
        wmsInveDebtWorkFlowVO.setDebtkey("5");
        wmsInveDebtWorkFlowVO.setTaskId(wDebtWorkFlowVO.getTaskId());
        wmsInveDebtWorkFlowVO.setUserID(user.getUserId().toString());
        try
        {
            wmsInveWorkFlowService.publicApproval(wmsInveDebtWorkFlowVO);
        }
        catch (Exception e)
        {
            result = "error";
        }
        return result;
    }

    /**
     * 保存客户确认信息
     * @param wTransaProd
     */
    private void setWmsInveTransaCusConfirm(WmsInveTransaProd wTransaProd, UserBean user)
    {
        WmsInveTransaCusConfirm wmsInveTransaCusConfirm = new WmsInveTransaCusConfirm();
        wmsInveTransaCusConfirm.setWms_inve_transa_id(wTransaProd.getWms_inve_transa_id());
        wmsInveTransaCusConfirm.setWms_inve_transa_prod_id(wTransaProd.getWms_inve_transa_prod_id());
        wTransaProd = wmsInveTransaProdDao.get(wTransaProd.getWms_inve_transa_prod_id());
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wTransaProd.getWms_inve_transa_id());
        wmsInveTransaCusConfirm.setCus_name(wmsInveTransa.getCus_name());
        wmsInveTransaCusConfirm.setMobile_phone(wmsInveTransa.getMobile_phone());
        wmsInveTransaCusConfirm.setProduct_account(wTransaProd.getProduct_account());
        wmsInveTransaCusConfirm.setSalesman_id(wmsInveTransa.getSalesman_id());
        wmsInveTransaCusConfirm.setSalesman_name(wmsInveTransa.getSalesman_name());
        wmsInveTransaCusConfirm.setSalesman_dept_id(wmsInveTransa.getSalesman_dept_id());
        wmsInveTransaCusConfirm.setSalesman_shortcode(wmsInveTransa.getSalesman_shortcode());
        wmsInveTransaCusConfirm.setDepartment_manager_id(wmsInveTransa.getDepartment_manager_id());
        wmsInveTransaCusConfirm.setDepartment_manager_name(pmPersonnelDao.get(wmsInveTransa.getDepartment_manager_id())
                                                                         .getPersonnel_name());
        wmsInveTransaCusConfirm.setDepartment_manager_dept_id(wmsInveTransa.getDepartment_manager_dept_id());
        wmsInveTransaCusConfirm.setVice_general_manager_id(wmsInveTransa.getVice_general_manager_id());
        wmsInveTransaCusConfirm.setVice_general_manager_name(pmPersonnelDao.get(wmsInveTransa.getVice_general_manager_id())
                                                                           .getPersonnel_name());
        wmsInveTransaCusConfirm.setVice_general_manager_dept_id(wmsInveTransa.getVice_general_manager_dept_id());
        wmsInveTransaCusConfirm.setCard_owner_name(wTransaProd.getCard_owner_name());
        wmsInveTransaCusConfirm.setCard_no(wTransaProd.getCard_no());
        wmsInveTransaCusConfirm.setBank_of_deposit(wTransaProd.getBank_of_deposit());
        wmsInveTransaCusConfirm.setBank_of_deposit_pro(wTransaProd.getBank_of_deposit_pro());
        wmsInveTransaCusConfirm.setBank_of_deposit_city(wTransaProd.getBank_of_deposit_city());
        wmsInveTransaCusConfirm.setBank_branch(wTransaProd.getBank_branch());
        wmsInveTransaCusConfirm.setWms_inve_pruduct_category_id(wTransaProd.getWms_inve_pruduct_category_id());
        wmsInveTransaCusConfirm.setCategory_name(wTransaProd.getCategory_name());
        wmsInveTransaCusConfirm.setProduct_deadline(wTransaProd.getProduct_deadline());
        wmsInveTransaCusConfirm.setProduct_interest(new BigDecimal(wTransaProd.getProduct_interest()));
        wmsInveTransaCusConfirm.setCategory_type(Integer.valueOf(wTransaProd.getCategory_type()));
        wmsInveTransaCusConfirm.setCreate_user_id(user.getUserId());
        wmsInveTransaCusConfirm.setCreate_user_name(user.getRealname());
        wmsInveTransaCusConfirm.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveTransaCusConfirm.setEnable_flag("1");
        wmsInveTransaCusConfirmDao.save(wmsInveTransaCusConfirm);
    }

    /**
     * @Description :上单管理--支付退回列表
     * @param queryInfo
     * @return record list
     * @author baisong
     * @date 2015-12-16
     */
    @Override
    public Map<String, Object> getListWithPagingForPaidReturn(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "6");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.searchListForPaidReturn(paramMap);
        // 加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                      (List<String>) paramMap.get("taskIdList"),
                                                      (String) paramMap.get("processDefinitionKey"));
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findListCountForPaidReturn(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * Description :上单管理--支付退回列表 导出
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListWithoutPagingForPaidReturnExcel(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "6");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaDao.searchListForPaidReturn(paramMap);
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getreturnChecklist(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "5");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("wms_inve_pruduct_category_id", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("data_status", "10");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.searChreturnCheckList(paramMap);

        // 加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                       (List<String>) paramMap.get("taskIdList"),
                                                       (String) paramMap.get("processDefinitionKey"));

        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findChreturnCheckListCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public Map<String, Object> getreturncheckoutpaginglist(WmsInveDebtHeadSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "5");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("wms_inve_pruduct_category_id", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("data_status", "10");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Integer> data_statusList = new ArrayList<Integer>();
        paramMap.put("data_statusList", null);
        List<Map<String, Object>> list = wmsinvetransaDao.searChreturnCheckList(paramMap);

        // 加流程
        paramMap = new HashMap<String, Object>();
        paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                               user.getUserId().toString(), "5");// 待修订

        // 加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                       (List<String>) paramMap.get("taskIdList"),
                                                       (String) paramMap.get("processDefinitionKey"));

        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * @Description :上单管理--审核退回列表
     * @param queryInfo
     * @return record list
     * @author baisong
     * @date 2015-12-16
     */
    @Override
    public Map<String, Object> getListBackDirectorApproval(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "7");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.searchListBackDirectorApproval(paramMap);
        // 加流程
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramMap.get("idList"),
                                                      (List<String>) paramMap.get("taskIdList"),
                                                      (String) paramMap.get("processDefinitionKey"));
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findListCountBackDirectorApproval(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * Description :上单管理--审核退回列表 导出
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    @Override
    public Map<String, Object> getListBackDirectorApprovalExcel(WmsInveTransaSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS,
                                                                                  user.getUserId().toString(), "7");
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransaDao.searchListBackDirectorApproval(paramMap);
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = sysSpecialUserDao.getListByEntity(specilaUser);
        list = SysTools.setListView(list, user, specilaUsers);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
    * Description :理财上单保存理财信息--有支付页面信息的
    * 
    * @param bean
    * @return "success" or "error" or user defined
    * @author baisong
    */
    @Override
    @Transactional
    public String saveUpdate(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr,
                             UserBean user, String saveFlag, WmsInveCustomer wmsCustomer,
                             WmsInveTransaSearchBeanVO beanVO)
    {
        // 判断上单、追单金额是否在其正确的范围内
        HashMap<String, Object> paramMap = new HashMap<>();
        if ("1".equals(saveFlag))
        {
            WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());
            if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
            {// 是追单
                if (wmsInvePruductCategory.getCategory_additional_monery_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_additional_monery_min()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error1";
                }
                if (wmsInvePruductCategory.getCategory_additional_monery_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_additional_monery_max()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error2";
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null)
                {
                    paramMap.put("data_status1", "4");
                    paramMap.put("data_status2", "5");
                    List<Map<String, Object>> products = wmsInveTransaProdDao.search(paramMap);
                    double sumZdje = 0;// 追单金额的和
                    for (Map<String, Object> product : products)
                    {
                        BigDecimal product_account = (BigDecimal) product.get("product_account");
                        if (product_account.compareTo(BigDecimal.ZERO) == 0)
                        {
                            continue;
                        }
                        sumZdje += Integer.parseInt(product.get("product_account").toString().indexOf('.') > 0 ? product.get("product_account")
                                                                                                                        .toString()
                                                                                                                        .substring(0,
                                                                                                                                   product.get("product_account")
                                                                                                                                          .toString()
                                                                                                                                          .indexOf('.'))
                                                                                                              : product.get("product_account")
                                                                                                                       .toString());
                    }
                    // if (sumZdje >
                    // wmsInvePruductCategory.getCategory_maximum_amount().doubleValue()
                    // * 10000)
                    if (sumZdje > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
                    {
                        return "error5";
                    }
                }
            }
            else
            {// 是上单
                if (wmsInvePruductCategory.getCategory_investment_money_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_investment_money_min()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error3";
                }
                if (wmsInvePruductCategory.getCategory_investment_money_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_investment_money_max()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error4";
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getMaximum_holding_amount()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error6";
                }
            }
            // 判断当前上单产品所有金额是否大于等于当前产品的最大限制金额
            // (1) 如果当前产品的上单金额已经大于等于当前产品的最大限制金额,需要将当前产品设置成禁用
            if (wmsInvePruductCategory.getCategory_maximum_amount() != null)
            {
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                paramsMap.put("wms_inve_pruduct_category_id", wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                paramsMap.put("wms_inve_transa_id", wmsInveTransa.getWms_inve_transa_id());
                int sumCategoryInveTransaPayaccount = wmsinvetransaDao.getSumCategoryInveTransaPayaccountByCategoryId(paramsMap);
                sumCategoryInveTransaPayaccount += wmsInveTransaProd.getProduct_account().doubleValue();
                // (2) 判断当前上单记录所上单的金额加上已经上单的总金额大于等于当前产品的最大限制金额则将当前产品禁用
                if (sumCategoryInveTransaPayaccount >= wmsInvePruductCategory.getCategory_maximum_amount()
                                                                             .doubleValue() * 10000)
                {
                    WmsInvePruductCategory disableWmsInveProductCategory = new WmsInvePruductCategory();
                    disableWmsInveProductCategory.setWms_inve_pruduct_category_id(wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                    disableWmsInveProductCategory.setIs_forbidden("1");
                    wmsInvePruductCategoryDao.update(disableWmsInveProductCategory);
                }
            }
        }
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 检查客户表里是否存在记录
        WmsInveCustomer wmsInveCustomer = null;
        String id_card = wmsCustomer.getId_card();
        paramMap = new HashMap<>();
        if (id_card != null)
        {
            paramMap.put("id_card", id_card);
            wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
        }
        // 在提交时，如果不存在客户记录，那保存客户信息 2015-11-24baisong 上单暂存不保存客户表 提交保存到客户表 保证客户的唯一性
        if (wmsInveCustomer == null && "1".equals(saveFlag))
        // if (wmsInveCustomer == null)
        {
            wmsInveCustomer = setWmsInveCustomer(wmsCustomer, wmsInveTransa, user, sysTime);// 保存客户信息
            wmsInveCustomer.setCostomer_id(wmsCustomer.getCostomer_id());// CRM关联信息表主键
            wmsInveCustomer.setCustomer_source(wmsCustomer.getCustomer_source());// 客户来源
            wmsInveCustomerDao.save(wmsInveCustomer);
        }
        else
        {
            wmsCustomer.setLast_update_timestamp(sysTime);
            wmsCustomer.setLast_update_user_id(user.getUserId());
            wmsInveCustomerDao.update(wmsCustomer);
            paramMap = new HashMap<>();
            if (id_card != null)
            {
                paramMap.put("id_card", id_card);
                wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
            }
        }
        // 删除附件
        Map<String, Object> map = new HashMap<>();
        map.put("wms_inve_transa_id", wmsInveTransa.getWms_inve_transa_id());
        map.put("data_type_name", beanVO.getData_type_name());
        wmsInveTransaAttDao.deleteForAtt(map);
        List<WmsInveTransaAtt> wmsInveTransaAttList = JsonUtil.jsonArrayToList(fileArr, WmsInveTransaAtt.class);// 将附件集合字符串转换成实体类的形式
        // 保存附件wms_inve_transa_prod_id
        for (WmsInveTransaAtt wmsInveTransaAtt : wmsInveTransaAttList)
        {
            wmsInveTransaAtt.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
            wmsInveTransaAtt = setWmsInveTransaAtt(wmsInveTransaAtt, wmsInveTransa);
            wmsInveTransaAtt.setWms_inve_transa_att_id(null);
            wmsInveTransaAttDao.save(wmsInveTransaAtt);
        }

        String resStr = "success";
        Date sysDate = new Date(System.currentTimeMillis());
        List<WmsInveTransaAtt> inveTransaAttList = JsonUtil.jsonArrayToList(beanVO.getFileArrpay(),
                                                                            WmsInveTransaAtt.class);
        // 获取支付卡信息
        List<WmsInveTransaCard> wmsInveTransaCardList = JsonUtil.jsonArrayToList(beanVO.getItcardJSON(),
                                                                                 WmsInveTransaCard.class);
        // getData_type_name_str 是获取支付信息中的附件大类id 需要赋值到Data_type_name
        // 上以便支付里面的方法处理
        beanVO.setData_type_name(beanVO.getData_type_name_str());
        // 原始上单/追单金额
        wmsInveTransaProd.setOrg_product_account(wmsInveTransaProd.getProduct_account());
        wmsInveTransaProdDao.updateAll(wmsInveTransaProd);
        // 变更存储信息表信息
        resStr = setTransaCardInfo(wmsInveTransa, wmsInveTransaProd, wmsInveTransaCardList, inveTransaAttList, user,
                                   beanVO, "1", null);
        /* if ("1".equals(wmsInveTransa.getPay_type()))
         {
             wmsInveTransa.setAccount_status("3");//支付状态
             wmsInveTransa.setPay_usr_id(user.getUserId());//支付操作人
             wmsInveTransa.setDate_of_payment(sysDate);//支付时间
             wmsInveTransa.setPay_account(wmsInveTransa.getPay_account());//支付金额合计
             wmsInveTransa.setCash_pay_name(wmsInveTransa.getCus_name());//现金支付人姓名
             wmsInveTransa.setLast_update_user_id(user.getUserId());
             wmsInveTransa.setLast_update_timestamp(sysTime);
             wmsInveTransa.setAct_account_usr_id(user.getUserId());//到账操作人
             wmsInveTransa.setDate_of_act(sysDate);//到账时间
             wmsInveTransa.setAct_account(wmsInveTransa.getPay_account());//到账金额合计
             wmsInveTransa.setFee_account(new BigDecimal(0));//手续费
         }
         else if ("2".equals(wmsInveTransa.getPay_type()))
         {
             
             wmsInveTransa.setAccount_status("2");
             wmsInveTransa.setPay_usr_id(user.getUserId());
             wmsInveTransa.setDate_of_payment(sysDate);
             wmsInveTransa.setLast_update_user_id(user.getUserId());
             wmsInveTransa.setLast_update_timestamp(sysTime);
             resStr=setTransaCardInfo(wmsInveTransa,wmsInveTransaProd, wmsInveTransaCardList,inveTransaAttList,user,beanVO,"1");
         }
         else if ("3".equals(wmsInveTransa.getPay_type()))
         {
             wmsInveTransa.setAccount_status("3");
             wmsInveTransa.setPay_usr_id(user.getUserId());
             wmsInveTransa.setDate_of_payment(sysDate);
             wmsInveTransa.setLast_update_user_id(user.getUserId());
             wmsInveTransa.setLast_update_timestamp(sysTime);
             wmsInveTransa.setCash_pay_name(wmsInveTransa.getCus_name());
             resStr=setTransaCardInfo(wmsInveTransa,wmsInveTransaProd, wmsInveTransaCardList,inveTransaAttList,user,beanVO,"1");
         }*/
        // 提交
        if ("1".equals(saveFlag))
        {
            // 执行流程
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setTaskId(beanVO.getTaskId());
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("8");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        else if ("0".equals(saveFlag))
        {
            return wmsInveTransa.getWms_inve_transa_id() + ";" + wmsInveTransaProd.getWms_inve_transa_prod_id();
        }
        return resStr;
    }

    @Override
    public String doSearchStatus(String wms_inve_transa_id)
    {
        String str = wmsinvetransaDao.doSearchStatus(Integer.parseInt(wms_inve_transa_id));
        return str;
    }

    /**
    * 
    * @Title: getExtendapplylistwithpaging
    * @Description: 续期申请查询列表
    * @param queryInfo 查询条件对象
    * @param user 用户对象
    * @return 返回map集合
    * @author: DongHao
    * @time:2016年11月16日 上午9:20:50
    * @see com.zx.emanage.inve.service.IWmsInveTransaService#getExtendapplylistwithpaging(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, com.zx.sframe.util.vo.UserBean)
    * history:
    * 1、2016年11月16日 DongHao 修改方法   <修改内容：添加新的查询列表的方法getRenewalList,和获取统计数据的方法getRenewalCount>
    */
    @Override
    public Map<String, Object> getExtendapplylistwithpaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SysUserRole> sysyuserroleList = sysuserroleDao_m.getUserRole(user.getUserId());
        for (SysUserRole sysyuserrole : sysyuserroleList)
        {
            // // 判断用户的角色是否为业务员
            // if (sysyuserrole.getRole_id() == 137)
            // {
            // paramMap.put("salesman_id", user.getUserId());
            // }
            // // 判断用户的角色是否为接待专员
            // if (sysyuserrole.getRole_id() == 141)
            // {
            // paramMap.put("reception_personnel", 1);
            // }
            // // 判断用户的角色是否为超级用户
            // if (sysyuserrole.getRole_id() == 110)
            // {
            // paramMap.put("super_user", 1);
            // }
            // 财务部门主管 理财财务专员 财务柜员 超级管理员
            if (sysyuserrole.getRole_id() == 128)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 138)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 150)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 110)
            {
                paramMap.put("super_user", 1);
            }
            if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
            {
                paramMap.put("bill_source", queryInfo.getBill_source());
            }
        }
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {// 单据编号查询条件
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())
            && !StringUtil.isEmpty(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        String[] sort = queryInfo.getSortname().split(",");
        paramMap.put("sortname", sort[0]);
        paramMap.put("sortorder", sort[1]);
        paramMap.put("sortname1", sort[2]);
        paramMap.put("sortorder1", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("userid", user.getUserId());
        paramMap.put("extend_date", "true");
        // List<Map<String, Object>> list =
        // wmsinvetransaDao.redeemApplyList(paramMap);
        List<Map<String, Object>> list = wmsinvetransaDao.getRenewalList(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.getRenewalCount(paramMap),
                                                                                       list);
        return bean.getGridData();

    }

    @Override
    public Map<String, Object> getExtendapplylistwithoutpaging(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<SysUserRole> sysyuserroleList = sysuserroleDao_m.getUserRole(user.getUserId());
        for (SysUserRole sysyuserrole : sysyuserroleList)
        {
            // // 判断用户的角色是否为业务员
            // if (sysyuserrole.getRole_id() == 137)
            // {
            // paramMap.put("salesman_id", user.getUserId());
            // }
            // // 判断用户的角色是否为接待专员
            // if (sysyuserrole.getRole_id() == 141)
            // {
            // paramMap.put("reception_personnel", 1);
            // }
            // // 判断用户的角色是否为超级用户
            // if (sysyuserrole.getRole_id() == 110)
            // {
            // paramMap.put("super_user", 1);
            // }
            // 财务部门主管 理财财务专员 财务柜员 超级管理员
            if (sysyuserrole.getRole_id() == 128)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 138)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 150)
            {
                paramMap.put("super_user", 1);
            }
            if (sysyuserrole.getRole_id() == 110)
            {
                paramMap.put("super_user", 1);
            }
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        String[] sort = queryInfo.getSortname().split(",");
        paramMap.put("sortname", sort[0]);
        paramMap.put("sortorder", sort[1]);
        paramMap.put("sortname1", sort[2]);
        paramMap.put("sortorder1", queryInfo.getSortorder());
        paramMap.put("userid", user.getUserId());
        paramMap.put("extend_date", "true");
        // List<Map<String, Object>> list =
        // wmsinvetransaDao.redeemApplyList(paramMap);
        List<Map<String, Object>> list = wmsinvetransaDao.getRenewalList(paramMap);
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        paramMap.put("Rows", list);
        return paramMap;

    }

    @Override
    public Map<String, Object> getInfoByPK4Extend(Integer wms_inve_transa_id)
    {
        return wmsinvetransaDao.getInfoByPK4Extend(wms_inve_transa_id);
    }

    /**
     * Description: 根据业务员的人员表的id信息进行获取对应的各个总的信息
     * @param personne_id 人员信息表主键
     * @param request 
     * @param response
     * @return
     * @author donghao
     * @date 2016年8月10日14:53:31
     */
    @Override
    public Map<String, Object> findPersonnelInformationByPersonnelId(int personne_id, int concurrent_post_deptid)
    {
        Map<String, Object> personnelMap = (Map<String, Object>) pmPersonnelDao.getPersonnelByPersonnelId(personne_id);
        Map<String, Object> map = new HashMap<String, Object>();
        PmPersonnel queryInfo = new PmPersonnel();
        queryInfo.setPersonnel_id(Integer.parseInt("" + personnelMap.get("personnel_id")));
        // queryInfo.setPersonnel_deptid(concurrent_post_deptid);
        Map<String, Object> mapYeWuYuan = (Map<String, Object>) pmPersonnelDao.getPersonnelByPersonnelIdAndDeptId(queryInfo);
        mapYeWuYuan.put("personnel_deptId", concurrent_post_deptid);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("personnel_id", Integer.parseInt("" + mapYeWuYuan.get("personnel_id")));
        paramsMap.put("personnel_deptid", concurrent_post_deptid);
        PmPersonnel buMenJingLi = (PmPersonnel) pmPersonnelDao.getBuMenJingLiByDeptId(paramsMap);
        PmPersonnel zhongFenZong = (PmPersonnel) pmPersonnelDao.getZhongFenZongByDeptId(paramsMap);
        PmPersonnel fuZhong = (PmPersonnel) pmPersonnelDao.getFuZongByDeptId(paramsMap);
        PmPersonnel zong = (PmPersonnel) pmPersonnelDao.getZongByPersonnelInfo(paramsMap);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("personnel_id", personne_id);
        paramMap.put("personnel_deptid", concurrent_post_deptid);

        if (buMenJingLi == null)
        {
            buMenJingLi = new PmPersonnel();
        }
        paramMap.put("type", 1);
        buMenJingLi.setPersonnel_deptid(pmPersonnelDao.getDeptId(paramMap));
        if (zhongFenZong == null)
        {
            zhongFenZong = new PmPersonnel();
        }
        paramMap.put("type", 2);
        zhongFenZong.setPersonnel_deptid(pmPersonnelDao.getDeptId(paramMap));
        if (fuZhong == null)
        {
            fuZhong = new PmPersonnel();
        }
        paramMap.put("type", 3);
        fuZhong.setPersonnel_deptid(pmPersonnelDao.getDeptId(paramMap));
        map.put("yeWuYuan", mapYeWuYuan);
        map.put("buMenJingLi", buMenJingLi);
        map.put("zhongFenZong", zhongFenZong);
        map.put("fuZhong", fuZhong);
        map.put("zong", zong);
        return map;
    }

    /**
     * 根据传入的参数获得客户是否存在理财单据信息。
     * @param pm_personnel_id 原客户经理Id
     * @param cus_ids 迁移客户集合（多个用户使用逗号分割）
     * @return 返回理财单据信息(状态不是1.草稿，6.已完成，7.已终止）。<br/>
     * 返回格式为（影响佣金范围固定设置成影响开单奖及存量奖）：<br/>
     * {ret_code:"",ret_msg:"",<br/>
     * data_list:[{cus_name:"",category_name:"",date_of_payment:"",commission_range:"3"},{cus_name:"",category_name:"",date_of_payment:"",commission_range:"3"}]}
     * @author jinzhm
     */
    @Override
    public Map<String, Object> getCustomerDataMoa(String pm_personnel_id, String cus_ids)
    {
        // 返回参数
        Map<String, Object> rMaps = new HashMap<String, Object>();
        // 组装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> cusIdList = Arrays.asList(cus_ids.split(","));
        paramMap.put("pm_personnel_id", pm_personnel_id);
        paramMap.put("cus_ids", cusIdList);

        // 根据传入的两个参数在理财上单信息表中的理财单据。
        List<Map<String, Object>> witList = wmsinvetransaDao.getCustomerDataMoa(paramMap);

        // 将查询出的数据封装成要返回的格式。
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        Map<String, String> dataMap = null;
        for (int i = 0; i < witList.size(); i++)
        {
            dataMap = new HashMap<String, String>();
            dataMap.put("commission_range", "3");// 影响佣金范围：1 影响开单奖，2 影响存量奖，3
                                                 // 影响开单奖及存量奖；固定设置成3 影响开单奖及存量奖
            dataMap.put("cus_name", witList.get(i).get("cus_name") + "");// 签单客户名称
            dataMap.put("category_name", witList.get(i).get("category_name") + "");
            dataMap.put("date_of_payment",
                        witList.get(i).get("date_of_payment") == null ? ""
                                                                     : DateUtil.date2String((Date) witList.get(i)
                                                                                                          .get("date_of_payment"),
                                                                                            "yyyy-MM-dd"));// 签单日期信息
            dataList.add(dataMap);
        }
        rMaps.put("ret_code", "000");
        rMaps.put("ret_msg", "操作成功");
        rMaps.put("flag", true);
        rMaps.put("data_list", dataList);
        return rMaps;
    }

    /**
     * @Title: classifyCustmerList
     * @Description: TODO 将传入的cusIdList针对个人到个人，个人到公司，公司到个人进行分类
     * @param cusIdList 要进行分类的客户迁移集合
     * @return 分类后的客户迁移集合
     * @author: jinzhiming
     * @time:2016年11月4日 下午2:17:33
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private Map<String, List<String>> classifyCustmerList(List<LinkedTreeMap> cusIdList)
    {
        Map<String, List<String>> classifyCusIdMap = new HashMap<String, List<String>>();
        List<String> dataCusList1 = new ArrayList<String>();// 个人到公司集合
        List<String> dataCusList2 = new ArrayList<String>();// 公司到个人集合
        List<String> dataCusList3 = new ArrayList<String>();// 个人到个人集合
        boolean oldFlag = false;// false表示个人，true表示公司
        boolean newFlag = false;// false表示个人，true表示公司
        for (LinkedTreeMap treeMap : cusIdList)
        {
            oldFlag = false;
            newFlag = false;
            // 判断并标记是否公司还是个人
            if (BELONG_COMPANY == new Double(String.valueOf(treeMap.get("old_belong_id"))).intValue())
            {
                oldFlag = true;
            }
            if (BELONG_COMPANY == new Double(String.valueOf(treeMap.get("new_belong_id"))).intValue())
            {
                newFlag = true;
            }
            // 根据两个标记进行分类
            if (!oldFlag && !newFlag)
            {
                // 表示是个人到个人
                dataCusList3.add(String.valueOf(treeMap.get("cus_id")));
            }
            else if (!oldFlag && newFlag)
            {
                // 表示个人到公司
                dataCusList1.add(String.valueOf(treeMap.get("cus_id")));
            }
            else if (oldFlag && !newFlag)
            {
                // 表示公司到个人
                dataCusList2.add(String.valueOf(treeMap.get("cus_id")));
            }
        }
        classifyCusIdMap.put(DATA_TYPE_1, dataCusList1);// 个人到公司
        classifyCusIdMap.put(DATA_TYPE_2, dataCusList2);// 公司到个人
        classifyCusIdMap.put(DATA_TYPE_3, dataCusList3);// 个人到个人
        return classifyCusIdMap;
    }

    /**
     * 根据原客户经理id和迁移客户集合查询上单信息(状态不是1.草稿，6.已完成，7.已终止），并修改上单信息的归属业务员为新客户经理Id。
     * 
     * @param pm_personnel_id
     *            原客户经理Id
     * @param cus_ids
     *            迁移客户集合（多个用户使用逗号分割）
     * @param new_pm_personnel_id
     *            新客户经理Id
     * @return
     * @author jinzhm
     */
    @Override
    @Transactional
    public Map<String, Object> changeCustomerDataMoa(String pm_personnel_id, List<LinkedTreeMap> cusIdList,
                                                     String new_pm_personnel_id, UserBean user)
    {
        // 返回参数
        Map<String, Object> rMaps = new HashMap<String, Object>();

        Map<String, List<String>> classifyMap = classifyCustmerList(cusIdList);

        // 调用个人到公司的处理方法
        if (!classifyMap.get(DATA_TYPE_1).isEmpty())
        {
            changeCustomerDataMoaFromPersonnelToCompany(pm_personnel_id, classifyMap.get(DATA_TYPE_1),
                                                        new_pm_personnel_id, user, DATA_TYPE_1);
        }
        // 调用公司到个人（用户激活）的处理方法
        if (!classifyMap.get(DATA_TYPE_2).isEmpty())
        {
            changeCustomerDataMoaFromCompanyToPersonnel(pm_personnel_id, classifyMap.get(DATA_TYPE_2),
                                                        new_pm_personnel_id, user, DATA_TYPE_2);
        }
        // 调用个人到个人的处理方法
        if (!classifyMap.get(DATA_TYPE_3).isEmpty())
        {
            changeCustomerDataMoaFromPersonnelToPersonnel(pm_personnel_id, classifyMap.get(DATA_TYPE_3),
                                                          new_pm_personnel_id, user, DATA_TYPE_3);
        }

        rMaps.put("ret_code", "000");
        rMaps.put("ret_msg", "操作成功");
        rMaps.put("flag", true);
        return rMaps;
    }

    /**
     * @Title: updateOldRemoveHis
     * @Description: TODO 修改之前做迁移记录的有效期
     * @param costomerId
     * @param pm_personnel_id 
     * @author: jinzhiming
     * @time:2016年11月4日 下午5:08:09
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
     */
    private void updateOldRemoveHis(String costomerId, String pm_personnel_id)
    {
        Date nowDate = new Date(System.currentTimeMillis());
        Map<String, Object> cusMap = new HashMap<String, Object>();
        cusMap.put("crm_customer_id", costomerId);
        cusMap.put("bel_personnel_id", pm_personnel_id);
        List<WmsInveCustomerRemoveHis> removeHistList = wmsInveCustomerRemoveHisDao.queryWmsInveCustomerRemoveHisInCompany(cusMap);// 查询之前做迁移的那条记录
        // 如果有之前做迁移的记录进行修改
        if (!removeHistList.isEmpty())
        {
            WmsInveCustomerRemoveHis oldWicrh = removeHistList.get(0);

            oldWicrh.setEnd_of_date(new java.sql.Date(DateUtil.getDateAddDays(nowDate, -1).getTime()));// 有效时间需-1
            wmsInveCustomerRemoveHisDao.updateEndOfDateInCompany(oldWicrh);// 修改之前做迁移的那条记录的有效时间
        }
    }

    /**
     * @Title: changeCustomerDataMoaFromCompanyToPersonnel
     * @Description: TODO 客户迁移（公司到个人）方法
     * @param pm_personnel_id
     * @param cusIdList
     * @param new_pm_personnel_id
     * @param user
     * @param dataType 
     * @author: jinzhiming
     * @time:2016年11月4日 下午3:15:12
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
    */
    private void changeCustomerDataMoaFromCompanyToPersonnel(String pm_personnel_id, List<String> cusIdList,
                                                             String new_pm_personnel_id, UserBean user, String dataType)
    {
        long timeMillis = System.currentTimeMillis();
        List<WmsInveCustomerRemoveHis> wicrhList = new ArrayList<WmsInveCustomerRemoveHis>();

        Map<String, Object> cusMap = null;
        WmsInveCustomer wic = null;
        WmsInveCustomerRemoveHis wicrh = null;

        // 循环客户生成customer_remove_his对象，并修改原个人到公司迁移数据的有效时间
        for (int i = 0; i < cusIdList.size(); i++)
        {
            String costomerId = cusIdList.get(i);
            // 组装要保存到crm业务员变更信息
            cusMap = new HashMap<String, Object>();
            wicrh = new WmsInveCustomerRemoveHis();
            wicrh.setCrm_customer_id(new Double(costomerId).intValue());
            wicrh.setChange_date(new Date(timeMillis));
            wicrh.setBase_bel_personnel_id(Integer.parseInt(pm_personnel_id));
            wicrh.setBel_personnel_id(Integer.parseInt(new_pm_personnel_id));
            wicrh.setLast_update_timestamp(new Timestamp(timeMillis));// 库中备注是上次修改时间，经确认后，直接存入创建时间
            wicrh.setEnd_of_date(DateUtil.strToSqlDate("9999-12-31", null));// 时间设置成9999-12-31
            wicrh.setData_type(dataType);// 数据类型设置成1
            // 查找inve中的客户表主键
            cusMap.put("costomer_id", costomerId);
            wic = wmsInveCustomerDao.getByEntity(cusMap);
            // 没签单过的客户，在wms系统中可能不会有客户信息，没有客户信息的话就是null。
            if (wic != null)
            {
                wicrh.setWms_inve_customer_id(wic.getWms_inve_customer_id());
            }
            wicrhList.add(wicrh);

            updateOldRemoveHis(costomerId, pm_personnel_id);// 修改原迁移数据的有效时间
        }

        if (!wicrhList.isEmpty())
        {
            wmsInveCustomerRemoveHisDao.batchSaveWmsInveCustomerRemoveHis(wicrhList);
        }
    }

    /**
     * @Title: changeCustomerDataMoaFromPersonnelToPersonnel
     * @Description: 客户迁移（个人到个人）方法
     * @param pm_personnel_id 原归属员id
     * @param cusIdList 客户id集合
     * @param new_pm_personnel_id 新归属员id
     * @param user 登录用户
     * @author: jinzhiming
     * @time:2016年11月4日 下午3:07:41
     * history:
     * 1、2016年11月4日 jinzhiming 创建方法
    */
    private void changeCustomerDataMoaFromPersonnelToPersonnel(String pm_personnel_id, List<String> cusIdList,
                                                               String new_pm_personnel_id, UserBean user,
                                                               String dataType)
    {
        changeCustomerDataMoaFromPersonnelToCompany(pm_personnel_id, cusIdList, new_pm_personnel_id, user, dataType);
    }

    /**
     * 根据原客户经理id和迁移客户集合查询上单信息(状态不是1.草稿，6.已完成，7.已终止），并修改上单信息的归属业务员为新客户经理Id。
     * 
     * @param pm_personnel_id
     *            原客户经理Id
     * @param cus_ids
     *            迁移客户集合（多个用户使用逗号分割）
     * @param new_pm_personnel_id
     *            新客户经理Id
     * @return
     * @author jinzhm
     */
    private void changeCustomerDataMoaFromPersonnelToCompany(String pm_personnel_id, List<String> cusIdList,
                                                             String new_pm_personnel_id, UserBean user, String dataType)
    {
        // 组装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pm_personnel_id", pm_personnel_id);
        paramMap.put("cus_ids", cusIdList);

        // 根据传入的两个参数（pm_personnel_id，cus_ids）在理财上单信息表中取得理财单据。
        List<Map<String, Object>> witList = wmsinvetransaDao.getCustomerDataMoa(paramMap);
        // 查找新客户经理的部门Id
        PmPersonnel person = pmPersonnelDao.getPersonnelByPersonnelId2(Integer.parseInt(new_pm_personnel_id));

        // 组装修改条件并修改
        paramMap.put("new_pm_personnel_id", new_pm_personnel_id);
        paramMap.put("new_pm_personnel_dept_id", person.getPersonnel_deptid());
        wmsinvetransaDao.changeCustomerDataMoa(paramMap);// 修改单据归属

        paramMap = new HashMap<String, Object>();
        paramMap.put("personnel_id", new_pm_personnel_id);
        paramMap.put("personnel_deptid", person.getPersonnel_deptid());
        paramMap.put("type", 1);
        String departmentDeptId = pmPersonnelDao.getDeptId(paramMap) + "";
        paramMap.put("type", 2);
        String centerManagerDeptId = pmPersonnelDao.getDeptId(paramMap) + "";
        paramMap.put("type", 3);
        String viceGeneralDeptId = pmPersonnelDao.getDeptId(paramMap) + "";
        paramMap.put("type", 4);
        String generalDeptId = pmPersonnelDao.getDeptId(paramMap) + "";

        PmPersonnel buMenJingLi = (PmPersonnel) pmPersonnelDao.getBuMenJingLiByDeptId(paramMap);
        PmPersonnel zhongFenZong = (PmPersonnel) pmPersonnelDao.getZhongFenZongByDeptId(paramMap);
        PmPersonnel fuZhong = (PmPersonnel) pmPersonnelDao.getFuZongByDeptId(paramMap);
        PmPersonnel zong = (PmPersonnel) pmPersonnelDao.getZongByPersonnelInfo(paramMap);

        // 批量插入日志信息，有两个日志信息要插入

        // 组装要保存到理财上单业务人员历史表中的数据
        List<Map<String, Object>> hisList = new ArrayList<Map<String, Object>>();
        Map<String, Object> hisMap = null;
        long timeMillis = System.currentTimeMillis();
        Timestamp createTimestamp = new Timestamp(timeMillis);

        // 组装要保存到crm业务员变更信息
        Map<String, Object> cusMap = null;
        WmsInveCustomerRemoveHis wicrh = null;
        WmsInveCustomer wic = null;
        List<WmsInveCustomerRemoveHis> wicrhList = new ArrayList<WmsInveCustomerRemoveHis>();

        for (Map<String, Object> witMap : witList)
        {
            // 组装要保存到理财上单业务人员历史表中的数据
            hisMap = new HashMap<String, Object>();
            hisMap.put("new_pm_personnel_id", new_pm_personnel_id);
            hisMap.put("new_pm_personnel_dept_id", person.getPersonnel_deptid() + "");
            hisMap.put("wms_inve_transa_id", witMap.get("wms_inve_transa_id"));
            hisMap.put("create_user_id", null);// 接口调用时不会有登录人，因此直接设置成null
            hisMap.put("create_timestamp", createTimestamp);
            hisMap.put("remark", "客户迁移记录。表中信息是迁移后数据。");
            hisMap.put("department_manager_dept_id", departmentDeptId.equals("null") ? null : departmentDeptId);
            hisMap.put("center_manager_dept_id", centerManagerDeptId.equals("null") ? null : centerManagerDeptId);
            hisMap.put("vice_general_manager_dept_id", viceGeneralDeptId.equals("null") ? null : viceGeneralDeptId);
            hisMap.put("general_manager_dept_id", generalDeptId.equals("null") ? null : generalDeptId);
            hisMap.put("department_manager_id", buMenJingLi == null ? null : buMenJingLi.getPersonnel_id());
            hisMap.put("vice_general_manager_id", fuZhong == null ? null : fuZhong.getPersonnel_id());
            hisMap.put("general_manager_id", zong == null ? zong : zong.getPersonnel_id());
            hisMap.put("center_manager_id", zhongFenZong == null ? null : zhongFenZong.getPersonnel_id());
            hisList.add(hisMap);
        }

        for (int i = 0; i < cusIdList.size(); i++)
        {
            String costomerId = cusIdList.get(i);
            // 组装要保存到crm业务员变更信息
            cusMap = new HashMap<String, Object>();
            wicrh = new WmsInveCustomerRemoveHis();
            wicrh.setCrm_customer_id(new Double(costomerId).intValue());
            wicrh.setChange_date(new Date(timeMillis));
            wicrh.setBase_bel_personnel_id(Integer.parseInt(pm_personnel_id));
            wicrh.setBel_personnel_id(Integer.parseInt(new_pm_personnel_id));
            wicrh.setLast_update_timestamp(createTimestamp);// 库中备注是上次修改时间，经确认后，直接存入创建时间
            wicrh.setEnd_of_date(DateUtil.strToSqlDate("9999-12-31", null));// 时间设置成9999-12-31
            wicrh.setData_type(dataType);// 数据类型设置成1
            // 查找inve中的客户表主键
            cusMap.put("costomer_id", costomerId);
            wic = wmsInveCustomerDao.getByEntity(cusMap);
            // 没签单过的客户，在wms系统中可能不会有客户信息，没有客户信息的话就是null。
            if (wic != null)
            {
                wicrh.setWms_inve_customer_id(wic.getWms_inve_customer_id());
            }
            wicrhList.add(wicrh);

            updateOldRemoveHis(costomerId, pm_personnel_id);// 修改原迁移数据的有效时间
        }

        // 只有有修改记录时才插入理财上单业务人员历史记录
        if (!hisList.isEmpty())
        {
            wmsInveTransaSalesmanHisDao.batchSaveWmsInveTransaSalesmanHis(hisList);
        }
        if (!wicrhList.isEmpty())
        {
            wmsInveCustomerRemoveHisDao.batchSaveWmsInveCustomerRemoveHis(wicrhList);
        }
    }

    /**
     * 根据传入的客户id集合返回客户收益中单据的存量信息。<br/>
     * 当传入客户id是多个的时候，返回多个客户的存量信息；当传入客户id是空的时候，返回所有客户存量信息。
     * @param cus_ids 客户集合，多个客户间用逗号隔开；或者是空；
     * @return 客户存量信息{ret_code: 000,ret_msg: '操作成功',data_list:[{customer_id:'1',stock_amount:'100000'}]}
     * @author jinzhm
     */
    @Override
    public Map<String, Object> getCustomerStockMoa(String cus_ids)
    {
        Map<String, Object> rMap = new HashMap<String, Object>();// 用来返回结果的map

        List<String> cusIdList = null;// 查询用客户id集合
        if (StringUtil.isNotEmpty(cus_ids))
        {
            cusIdList = Arrays.asList(cus_ids.split(","));// 当传入客户id的时候多个客户是逗号隔开
        }
        // 查询客户收益中单据的存量信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusIdList", cusIdList);
        List<Map<String, Object>> costomerAmountList = wmsInveTransaProtocolDao.getCostomerAmount(paramMap);// 查询客户投资总额（收益中单据）

        // 封装返回数据
        rMap.put("data_list", costomerAmountList);
        rMap.put("ret_code", "000");
        rMap.put("ret_msg", "操作成功");
        rMap.put("flag", true);
        return rMap;
    }

    @Override
    public List<WmsInvePruductCategory> findCategoryAll()
    {
        List<WmsInvePruductCategory> results = wmsInvePruductCategoryDao.findCategoryAll();
        WmsInvePruductCategory wmsInvePruductCategory = new WmsInvePruductCategory();
        wmsInvePruductCategory.setWms_inve_pruduct_category_id(-1);
        wmsInvePruductCategory.setCategory_name("请选择");
        results.add(0, wmsInvePruductCategory);
        return results;
    }

    @Override
    public Map<String, Object> findWmsInveTransaBills(WmsInveSelectBillBeanVo queryInfo)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        if (queryInfo.getWms_inve_customer_id() != null)
        {
            paramsMap.put("wms_inve_customer_id", queryInfo.getWms_inve_customer_id());
        }
        if (queryInfo.getWms_inve_pruduct_category_id() != null)
        {
            paramsMap.put("wms_inve_pruduct_category_id", queryInfo.getWms_inve_pruduct_category_id());
        }
        if (queryInfo.getQuery_date() != null)
        {
            paramsMap.put("query_date", queryInfo.getQuery_date());
        }
        paramsMap.put("pagesize", queryInfo.getPagesize());
        paramsMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransaDao.findWmsInveTransaBills(paramsMap);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findCountWmsInveTransaBills(paramsMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransa getWmsInveTransaByWmsInveTransaId(int wms_inve_transa_id)
    {
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.getWmsInveTransaByWmsInveTransaId(wms_inve_transa_id);
        return wmsInveTransa;
    }

    /**
     * 根据传入的理财上单主键，查询客户电话号码，发送验证码通知客户超频即将到期
     * @param wms_inve_transa_id
     * @return
     */
    @Override
    public String reSendMessageNotification(String wms_inve_transa_id)
    {
        String result = "success";
        // 通过传入的理财上单id，上单信息
        WmsInveTransa transa = wmsinvetransaDao.get(Integer.parseInt(wms_inve_transa_id));

        // 通过上单信息表主键查询上单产品表数据
        WmsInveTransaProd prod = new WmsInveTransaProd();
        prod.setWms_inve_transa_id(transa.getWms_inve_transa_id());
        List<WmsInveTransaProd> prodList = wmsInveTransaProdDao.getListByEntity(prod);
        prod = prodList.get(0);// 直接获得第一个对象

        Map<String, String> sendMap = new HashMap<String, String>();// 发送短信用到的参数
        sendMap.put("tpl_id", "1933");// 设置模板id
        sendMap.put("tel", transa.getMobile_phone());// 设置电话

        String identifyCode = prod.getRedeem_sms_code();// 获得验证码
        // 如果验证码是空的，则重新生成
        if (StringUtil.isEmpty(identifyCode))
        {
            // Math.random()方法产生 大于等于0到小于1的小数
            // (0*9+1)*100000 = 100000
            // (0.999999*9+1)*100000 = 999999.1
            // 生成后截取前6位字符串返回
            identifyCode = (((Math.random() * 9 + 1) * 100000) + "").substring(0, 6);
            // 如果验证码是新生成的，就保存到上单产品表中。
            int prodId = prod.getWms_inve_transa_prod_id();
            prod = new WmsInveTransaProd();// 重新创建一个对象，防止数据篡改。当前没有问题，防止以后有人维护，在修改前，对prod对象进行了其他数据封装操作
            prod.setWms_inve_transa_prod_id(prodId);// 设置主键
            prod.setRedeem_sms_code(identifyCode);// 设置要保存的验证码
            wmsInveTransaProdDao.update(prod);// 修改保存验证码
        }

        // 查询上单协议表信息计算到期期限
        // Map<String, Object> paramMap = new HashMap<String, Object>();
        // paramMap.put("wms_inve_transa_id", transa.getWms_inve_transa_id());
        // paramMap.put("wms_inve_redeem_id", 0);
        // Map<String, Object>
        // protocolData=wmsInveTransaProtocolDao.getProtocolData(paramMap);

        // 获得理财终止日期；和当前日期进行计算获得到期期限
        // Date endOfDate = (Date) protocolData.get("end_of_date");
        // Date nowDate = new Date(System.currentTimeMillis());
        // int expireDays = DateUtil.getBetweenDaysInt(nowDate, endOfDate);

        // 封装短信模板中使用的数据
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put("redeem_sms_code", identifyCode);// 验证码
        jsonMap.put("rest_end_of_date", "0");// 设置到期天数 赎回时，天数写死就是0

        Gson gson = new Gson();
        sendMap.put("paramJson", gson.toJson(jsonMap));// 将短信模板中要用到的参数，转换成json格式
        try
        {
            SysUtil.sendMsg(sendMap);

        }
        catch (ClientProtocolException e)
        {
            // e.printStackTrace();
            result = "error";
        }
        catch (IOException e)
        {
            // e.printStackTrace();
            result = "error";
        }

        return result;
    }

    @Override
    public void wmsInveTransaInvalidByWmsInveRedeemPrincipalDetailId(Integer wms_inve_transa_id,
                                                                     Integer wms_inve_redeem_principal_detail_id,
                                                                     UserBean user)
    {
        List<Map<String, Object>> wmsInveTransaCardMap = wmsInveTransaCardDao.getWmsInveTransaCardInfoByWmsInveTransaId(wms_inve_transa_id);
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        if (wms_inve_redeem_principal_detail_id != null)
        {
            for (Map<String, Object> map : wmsInveTransaCardMap)
            {
                if (map.get("wms_inve_redeem_principal_detail_id") == null)
                {
                    continue;
                }
                if (((int) map.get("wms_inve_redeem_principal_detail_id")) == wms_inve_redeem_principal_detail_id)
                {
                    if ("3".equals("" + map.get("pay_type")))
                    {
                        if ("1".equals("" + map.get("is_valid")))
                        {
                            WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
                            wmsInveRedeemPrincipalDetail.setWms_inve_redeem_principal_detail_id(wms_inve_redeem_principal_detail_id);
                            wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal(""
                                                                                              + map.get("act_account")));
                            wmsInveRedeemPrincipalDetailDao.updateToUsedIncomeAmount(wmsInveRedeemPrincipalDetail);
                        }

                    }

                    WmsInveTransaCard wmsInveTransaCard = new WmsInveTransaCard();
                    wmsInveTransaCard.setIs_valid("0");
                    wmsInveTransaCard.setWms_inve_transa_card_id((int) map.get("wms_inve_transa_card_id"));
                    wmsInveTransaCard.setLast_update_user_id(user != null ? user.getUserId() : 0);
                    wmsInveTransaCard.setLast_update_timestamp(sysTime);
                    wmsInveTransaCard.setEnable_flag("0");
                    wmsInveTransaCardDao.update(wmsInveTransaCard);

                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void reGenerateIncomeAndLog(String transaIds)
    {
        UserBean user = new UserBean();
        user.setUserId(113);
        user.setRealname("关旭");

        List<String> transaIdList = null;
        if (StringUtil.isEmpty(transaIds))
        {
            wmsInveTransaIncomeDao.removeWmsInveTransaIncomesInIncome();
            wmsInveTransaLogDao.removeWmsInveTransaLogsInIncome();
            transaIdList = wmsinvetransaDao.queryAllInIncomeTransa();
        }
        else
        {
            List<String> list = CollectionUtils.arrayToList(transaIds.split(","));
            wmsInveTransaIncomeDao.removeWmsInveTransaIncomes(list);
            wmsInveTransaLogDao.removeWmsInveTransaLogs(list);
            transaIdList = list;
        }
        WmsInveTransaProtocol protocol = null;
        for (int i = 0; i < transaIdList.size(); i++)
        {
            String transaId = transaIdList.get(i);
            protocol = new WmsInveTransaProtocol();
            protocol.setWms_inve_transa_id(Integer.parseInt(transaId));
            protocol.setWms_inve_redeem_id(0);
            protocol.setEnable_flag("1");
            List<WmsInveTransaProtocol> protocolList = wmsInveTransaProtocolDao.getListByEntity(protocol);
            if (!protocolList.isEmpty())
            {
                protocol = protocolList.get(0);
            }
            else
            {
                log.error("理财单据主键==" + transaId + "，没有找到对应的协议信息。");
                continue;
            }
            try
            {
                CountIncomeFactory.getCountIncome(protocol).getIncomeAndLog(protocol, user);
            }
            catch (NumberFormatException e)
            {
                log.error("理财单据主键==" + transaId + "，在生成收益信息及交易日志时出现错误", e);
            }
        }
    }

    /**
     * @Title getHistoryCustomerBankInfo
     * @Description 根据客户的姓名获取客户存在系统中的收益卡信息
     * @param cus_name
     *            客户名称
     * @param id_card
     *            证件号码
     * @return 返回list集合
     * @author DongHao
     * @date 2016年11月1日11:19:43
     */
    @Override
    public List<Map<String, Object>> getHistoryCustomerBankInfo(String cus_name, String id_card)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if (cus_name != null && !"".equals(cus_name))
        {
            params.put("cus_name", cus_name);
        }
        if (id_card != null && !"".equals(id_card))
        {
            params.put("id_card", id_card);
        }
        List<Map<String, Object>> list = wmsInveTransaProdDao.getHistoryCustomerBankInfo(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bank_card_info", "请选择");
        map.put("wms_inve_transa_prod_id", "");
        map.put("card_owner_name", "");
        map.put("card_no", "");
        map.put("bank_of_deposit", "");
        map.put("bank_branch", "");
        map.put("bank_of_deposit_pro", -1);
        map.put("bank_of_deposit_city", -1);
        list.add(0, map);
        return list;
    }

    /**
     * 根据传入的理财上单主键，查询客户身份证号和收益卡号
     * @param wms_inve_transa_id
     * @return
     */
    @Override
    public Map<String, String> findCardnoAndIdCard(String wms_inve_transa_id)
    {
        // 通过传入的理财上单id，上单信息
        WmsInveTransa transa = wmsinvetransaDao.get(Integer.parseInt(wms_inve_transa_id));

        // 通过上单信息表主键查询上单产品表数据
        WmsInveTransaProd prod = new WmsInveTransaProd();
        prod.setWms_inve_transa_id(transa.getWms_inve_transa_id());
        List<WmsInveTransaProd> prodList = wmsInveTransaProdDao.getListByEntity(prod);
        prod = prodList.get(0);

        Map<String, String> map = new HashMap<String, String>();
        map.put("b_id_card", transa.getId_card());
        map.put("card_no", prod.getCard_no());

        return map;
    }

    /**
     * @Title: isFinancialEmployee
     * @Description: 校验是否拥有财务柜员角色用户
     * @param user 用户信息
     * @return 返回标记
     * @author: jinzhm
     * @time:2017年2月8日 上午8:22:53
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#isFinancialEmployee(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月8日 jinzhm 创建方法
     */
    @Override
    public Map<String, Object> isFinancialEmployee(UserBean user)
    {
        // getSearchFinancialListWithPaging
        Map<String, Object> rMap = new HashMap<String, Object>();
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String roleName : roleList)
        {
            if (roleName.equals("财务柜员"))
            {
                rMap.put("isFinancialEmployee", "1");
            }
        }
        return rMap;
    }
    


    /**
     * @Title: changeCategoryOrPaymentAccount
     * @Description: 金额支付修改金额或修改产品后处理债权方法
     * @param origCategoryId 原始产品id
     * @param newCategoryId 新产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否匹配上债权（true表示匹配上）
     *      当不需要匹配时也返回true
     * @author: jinzhm
     * @time:2017年2月14日 下午2:34:20
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#changeCategoryOrPaymentAccount(int, int, int, java.math.BigDecimal, java.util.Date)
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
    */
    @Override
    public boolean changeCategoryOrPaymentAccount(int origCategoryId, int newCategoryId, int transaId,
                                                  BigDecimal productAccount, java.util.Date actDateOfPayment,
                                                  UserBean user)
    {
        return CreditBusiness.getInstance().changeCategoryOrPaymentAccount(origCategoryId, newCategoryId, transaId,
                                                                           productAccount, actDateOfPayment, user);
    }

    /**
     * @Title: confirmCreditMatched
     * @Description: 确认单据债权匹配占用是否正确
     * @param categoryId 产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否债权匹配成功（不需要匹配债权时也返回true）
     * @author: jinzhm
     * @time:2017年2月14日 下午3:06:34
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#confirmCreditMatched(int, int, java.math.BigDecimal, java.util.Date, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
    */
    @Override
    public boolean confirmCreditMatched(int categoryId, int transaId, BigDecimal productAccount,
                                        java.util.Date actDateOfPayment, UserBean user)
    {
        return CreditBusiness.getInstance().confirmCreditMatched(categoryId, transaId, productAccount,
                                                                 actDateOfPayment, user);
    }

    /**
     * @Title: releaseMatchedCredit
     * @Description: 释放已匹配的债权信息
     * @param transaId 上单主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午3:54:19
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#releaseMatchedCredit(int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
    */
    @Override
    public void releaseMatchedCredit(int transaId, UserBean user)
    {
        CreditBusiness.getInstance().releaseMatchedCreditForTransaFlow(transaId, null, user);
    }

    /**
     * @Title: matchHoldCredit
     * @Description: 债权匹配并占用债权
     * @param transaId 上单主键
     * @param categoryId 产品主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月14日 下午4:58:00
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#matchHoldCredit(javax.servlet.http.HttpServletRequest, int, int, java.math.BigDecimal, java.util.Date)
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
    */
    @Override
    public boolean matchHoldCredit(int transaId, int categoryId, BigDecimal productAccount,
                                   java.util.Date actDateOfPayment, UserBean user)
    {
        return CreditBusiness.getInstance().matchHold(transaId, categoryId, productAccount, actDateOfPayment, user);
    }


    /**
     * @Title: handleYearPublicIncome
     * @Description: 年付产品公益收益处理
     *      将所有年付产品按年给的公益收益修改成按月给
     * @author: jinzhm
     * @time:2016年11月17日 上午8:24:31
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#handleYearPublicIncome()
     * history:
     * 1、2016年11月17日 jinzhm 创建方法
    */
    @Transactional
    @Override
    public void handleYearPublicIncome()
    {
        // 用户信息
        UserBean user = new UserBean();
        user.setUserId(113);
        user.setRealname("关旭");

        // 当前时间
        java.util.Date nowDate = DateUtil.getDate10(new java.util.Date());
        // 到期日期
        java.util.Date endDate = null;
        // 循环使用时间
        java.util.Date date = null;
        // 获得所有收益中年付产品相关信息
        List<Map<String, Object>> yearTransaList = wmsinvetransaDao.getYearCategoryTransaList();
        // 年付产品单据id集合
        List<Integer> transaIdList = new ArrayList<>();
        // 要保存的生成的年付公益收益信息
        List<WmsInveTransaIncome> transaIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 年付公益收益信息
        WmsInveTransaIncome transaIncome = null;
        // 公益收益利率
        BigDecimal publicReturnRate = IncomeUtil.getPublicProductReturnRate();

        // 循环所有收益中年付产品相关信息
        for (int i = 0; i < yearTransaList.size(); i++)
        {
            // 年付产品单据信息
            Map<String, Object> transaMap = yearTransaList.get(i);
            // 保存年付产品单据id
            transaIdList.add((Integer) transaMap.get("wms_inve_transa_id"));
            // 到期日期
            endDate = (java.util.Date) transaMap.get("end_of_date");

            // 到期日期不是月末最后一天且到期日期月份小于当前时间的月份 需要生成公益收益
            if (DateUtil.getLastDayOfMonth(endDate).compareTo(DateUtil.getLastDayOfMonth(nowDate)) <= 0
                && endDate.compareTo(DateUtil.getLastDayOfMonth(endDate)) != 0)
            {
                // 从到期日期开始循环
                date = endDate;
                // 当循环时间的月份小于等于当前时间的时候生成公益收益
                while (DateUtil.getLastDayOfMonth(date).compareTo(DateUtil.getLastDayOfMonth(nowDate)) <= 0)
                {
                    transaIncome = new WmsInveTransaIncome();

                    // 投资金额
                    BigDecimal productAccount = new BigDecimal(String.valueOf(transaMap.get("product_account")));
                    // 收益天数
                    int publicIncomeDays = 0;
                    // 如果循环使用日期和到期日期相同说明是第一次循环
                    if (date.compareTo(endDate) == 0)
                    {
                        // 收益天数 当前日期到月末最后一天的天数
                        publicIncomeDays = DateUtil.getDaysOfInterval(date);
                    }
                    else
                    {
                        // 收益天数 daysOfInterval方法不包含当天，需要+1天
                        publicIncomeDays = DateUtil.getDaysOfInterval(date) + 1;
                    }
                    // 计算公益收益
                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(publicIncomeDays, productAccount,
                                                                             publicReturnRate);

                    // 封装公益收益数据
                    transaIncome.setWms_inve_transa_id((Integer) transaMap.get("wms_inve_transa_id"));
                    transaIncome.setWms_inve_transa_prod_id((Integer) transaMap.get("wms_inve_transa_prod_id"));
                    transaIncome.setWms_inve_pruduct_category_id((Integer) transaMap.get("wms_inve_pruduct_category_id"));
                    transaIncome.setId_card(String.valueOf(transaMap.get("id_card")));
                    transaIncome.setCus_name(String.valueOf(transaMap.get("cus_name")));
                    transaIncome.setProduct_deadline_month((Integer) transaMap.get("product_deadline"));

                    transaIncome.setReturn_date(new Date(DateUtil.getLastDayOfMonth(date).getTime()));

                    transaIncome.setProduct_account(productAccount);
                    transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                    transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                    transaIncome.setDays_extend_income(publicIncomeDays);
                    transaIncome.setPay_type(IncomeGlobal.PAY_TYPE_PUBLIC);
                    transaIncome.setPay_status(DateUtil.getLastDayOfMonth(date)
                                                       .compareTo(DateUtil.getLastDayOfMonth(nowDate)) == 0 ? IncomeGlobal.PAY_STATUS_NOT_PAY
                                                                                                           : IncomeGlobal.PAY_STATUS_YEAR_PUBLIC_ALREADY_PAY);

                    transaIncome.setCreate_user_id(user.getUserId());
                    transaIncome.setCreate_user_name(user.getRealname());
                    transaIncome.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

                    transaIncome.setDays_of_calculation(0);
                    transaIncome.setBonus_rate(BigDecimal.ZERO);
                    transaIncome.setPayable_basic_income(BigDecimal.ZERO);
                    transaIncome.setPayable_reward_income(BigDecimal.ZERO);
                    transaIncome.setIncome_rate(BigDecimal.ZERO);

                    transaIncome.setRemarks(DateUtil.date2String(date, "yyyy年MM月")
                                            + String.valueOf(transaMap.get("category_name")) + "客户收益");
                    // 保存到临时集合中
                    transaIncomeList.add(transaIncome);
                    // 将日期移到下个月1号
                    date = DateUtil.getFirstDayOfNextMonth(date);
                }
            }
        }

        // 删除原公益收益
        Map<String, Object> delParamMap = new HashMap<String, Object>();
        delParamMap.put("transaIdList", transaIdList);
        delParamMap.put("pay_type", "3");
        wmsInveTransaIncomeDao.removeTransaIncomes(delParamMap);

        // 批量插入新生成的公益收益
        wmsInveTransaIncomeDao.addBatchWmsInveTransaIncomes(transaIncomeList);
    }
    /**
     * 
     * @Title: getLcInfoByTransaId
     * @Description: 通过主键查询理财相关信息 
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 上午10:36:34
     * history:
     * 1、2017年2月14日 Administrator 创建方法
     */
    @Override
    public Map<String, Object> getLcInfoByTransaId(Integer wms_inve_transa_id)
    {
        Map<String, Object> map = wmsinvetransaDao.getLcInfoByTransaId(wms_inve_transa_id);

        map.put("product_account_upper", digitUpperUtil.digitUppercase(((BigDecimal) map.get("product_account")).doubleValue(), true).replaceAll("元", ""));

        return map;
    }
    /**
     * 
     * @Title: getAdjustShortMessage_grid
     * @Description: 根据id查询上单客户短信设置信息
     * @param id
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月14日 上午11:46:49
     * history:
     * 1、2017年3月14日 zhangmingjian 创建方法
     */
    @Override
    public List<Map<String, Object>> getAdjustShortMessage_grid(String id)
    {
        List<Map<String, Object>> rows  = null;
        if(id!=null){
            String [] ids =id.split(",");
            List<String> list = java.util.Arrays.asList(ids);
            Map<String,Object> map = new HashMap<>();
            map.put("ids", list);
            
            
           rows = wmsinvetransaDao.getAdjustShortMessage_grid(map);
            
        }
        // TODO Auto-generated method stub
        return rows;
    }
    
    /**
     * 
     * @Title: adjustShortMessage_update
     * @Description: 更新批量短信设置信息
     * @param request
     * @param data
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 上午10:20:47
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    @Override
    public boolean adjustShortMessage_update(HttpServletRequest request, List<Map<String,Object>> data)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
      
            if(data.size()>0){
                for (Map<String, Object> map : data)
                {
                    map.put("last_update_user_id", user.getUserId());
                  Integer num =   wmsinvetransaDao.adjustShortMessage_update(map);
                }
               return true;
            }else{
                return false;
            }
        
    }
    /**
     * 
     * @Title: savePayVouchersInfo
     * @Description: 保存补传凭证附件
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月16日 下午5:04:19
     * history:
     * 1、2017年3月16日 zhangmingjian 创建方法
     */
    @Override
    public boolean savePayVouchersInfo(List<Map<String, Object>> data)
    {
        if(data.size()>0){
            for (Map<String, Object> map : data)
            {
              Integer num =   wmsinvetransaDao.savePayVouchersInfo(map);
            }
           return true;
        }else{
            return false;
        }
        
    }

    /**
     * 
     * @Title: verifyIsShareholderBill
     * @Description: 根据单据id验证验证该单据是否是股东单据(是股东单据返回false,不是股东单据返回true)
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年3月22日 上午8:31:05
     * history:
     * 1、2017年3月22日 DongHao 创建方法
     */
    public boolean verifyIsShareholderBill(Integer wms_inve_transa_id)
    {
        // 定义boolean类型的变量,用来标记是否存在股东单据,存在bool为false,否则为true
        boolean bool = true;

        // 根据原始上单单据id获取股东单据
        Integer shareholderBillCount = wmsinvetransaDao.getShareholderBillsByWmsInveTransaId(wms_inve_transa_id);

        // 判断根据单据id获取的股东单据记录数是否大于0,大于0说明存在股东单据所以设置bool为false
        if (shareholderBillCount > 0)
        {
            bool = false;
        }

        return bool;
    }

    /**
     * @Title: getInveIncomeBillInfos
     * @Description: 收益账单信息表
     * @param queryInfo 查询信息对象
     * @param user 当前登录系统的用户
     * @return 返回map集合信息
     * @author: DongHao
     * @time:2017年3月31日 上午9:21:48
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getInveIncomeBillInfos(com.zx.emanage.inve.vo.WmsInveIncomeBillVo, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getInveIncomeBillInfos(WmsInveIncomeBillVo queryInfo, UserBean user)
    {

        // 定义查询map参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        /*********************(1) 判断查询条件信息是否为空,如果不为空则将参数设置到查询参数map集合中*******************************/
        // 判断客户姓名是否为空
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramsMap.put("cus_name", queryInfo.getCus_name());
        }

        // 判断客户有效证件号是否为空
        if (queryInfo.getId_card() != null && !"".equals(queryInfo.getId_card()))
        {
            paramsMap.put("id_card", queryInfo.getId_card());
        }

        // 判断合同编号是否为空
        if (queryInfo.getProt_code() != null && !"".equals(queryInfo.getProt_code()))
        {
            paramsMap.put("prot_code", queryInfo.getProt_code());
        }

        // 判断收益月份是否为空
        if(queryInfo.getReturn_date() != null && !"".equals(queryInfo.getReturn_date()))
        {
        	// 设置收益月份
            paramsMap.put("return_date", queryInfo.getReturn_date());
        }
        else
        {
        	paramsMap.put("return_date", "全部月份");
        }

        // 判断单据编号是否为空
        if (queryInfo.getBill_code() != null && !"".equals(queryInfo.getBill_code()))
        {
            paramsMap.put("bill_code", queryInfo.getBill_code());
        }

        // 判断客户经理是否为空
        if (queryInfo.getSalesman_name() != null && !"".equals(queryInfo.getSalesman_name()))
        {
            paramsMap.put("salesman_name", queryInfo.getSalesman_name());
        }

        // 判断产品是否为空
        if (queryInfo.getWms_inve_pruduct_category_id() != null && !queryInfo.getWms_inve_pruduct_category_id().equals(-1))
        {
            paramsMap.put("wms_inve_pruduct_category_id", queryInfo.getWms_inve_pruduct_category_id());
        }

        // 判断单据状态是否为空
        if (queryInfo.getData_status() != null && !"".equals(queryInfo.getData_status()) && !"-1".equals(queryInfo.getData_status()))
        {
            paramsMap.put("data_status", queryInfo.getData_status());
        }

        paramsMap.put("page", queryInfo.getOffset());// 设置当前页
        paramsMap.put("page_size", queryInfo.getPagesize());// 设置每页显示的数量

        /*********************(2) 获取当前登录人的权限*******************************/
        paramsMap = getUserRole(user, paramsMap);

        /*********************(3) 根据查询条件进行获取单据信息**************************/
        List<Map<String, Object>> list = wmsinvetransaDao.getInveIncomeBillInfos(paramsMap);

        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),
                                                                                       wmsinvetransaDao.findInveIncomeBillCount(paramsMap), list);
        return bean.getGridData();
    }

    /**
     * @Title: getUserRole
     * @Description: 根据当前登录人获取当前登录人的角色
     * @param user 当前登录人信息
     * @return  返回map参数集合
     * @author: DongHao
     * @time:2017年3月31日 上午9:45:45
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    private Map<String, Object> getUserRole(UserBean user, Map<String, Object> paramMap)
    {
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            //客户经理
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }
            
            //部门经理
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            
            //总
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            
            //副总
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", 110);
                paramMap.put("deptIds_user_id", user.getUserId());
            }
            
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
            
            //柜员
            if (role.equals("财务柜员"))
            {
                // 可以查看自己所在区域的单据(传入当前柜员的id,根据当前柜员id进行获取区域)
                paramMap.put("personnel_id", user.getUserId());
            }
            
            if (role.equals("理财财务主管"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            
            //合同专员
            if(role.equals("理财合同专员"))
            {
                // 可以查看自己所在区域的单据(传入当前柜员的id,根据当前柜员id进行获取区域)
                paramMap.put("personnel_id", user.getUserId());
            }
        }
        return paramMap;
    }

    /**
     * @Title: getWmsInveTransaDataStatus
     * @Description: 获取收益账单信息单据状态
     * @return  返回list集合
     * @author: DongHao
     * @time:2017年3月31日 上午11:57:09
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getWmsInveTransaDataStatus()
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getWmsInveTransaDataStatus()
    {
        // 获取收益账单单据状态(收益中,赎回中, 已完成)
        List<Map<String, Object>> list = wmsinvetransaDao.getWmsInveTransaDataStatus();

        // 设置默认显示请选择
        Map<String, Object> indexMap = new HashMap<String, Object>();
        indexMap.put("value_code", -1);
        indexMap.put("value_meaning", "请选择");

        list.add(0, indexMap);

        return list;
    }

    /**
     * @Title: getMaxIncomeMonth
     * @Description: 获取收益月份
     * @return 返回list集合
     * @author: DongHao
     * @time:2017年3月31日 下午1:11:01
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#findIncomeMonth()
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getMaxIncomeMonth()
    {
        // 获取收益月份(获取已经打过收益的收益月份,收益月份时间倒序排序)
        Map<String, Object> res_map = wmsinvetransaDao.getMaxIncomeMonth();

        return res_map;
    }

    /**
     * @Title: verifyBillIdCardIsConsistent
     * @Description:根据传入的ids进行获取单据,并且进行判断是否有效证件一致
     * @param ids 上单表主键ids字符串(多个以逗号分隔)
     * @return 验证有效证件一致返回true,不一致返回false
     * @author: DongHao
     * @time:2017年3月31日 下午3:34:46
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#wmsinvetransaService(java.lang.String)
     * history:
     * 1、2017年3月31日 DongHao 创建方法
    */
    @Override
    public boolean verifyBillIdCardIsConsistent(String ids)
    {
        String[] ids_str = ids.split(",");// 将id以","分隔成数组

        List<String> idLis = new ArrayList<String>();// 定义id集合

        // 循环将id数组中的id值添加到list中
        for (String id : ids_str)
        {
            idLis.add(id);
        }

        boolean bool = true;// 自定义变量用于标记是否存在不一致的有效证件(有效证件一致返回true,不一致返回false)

        // 根据ids进行获取单据信息
        List<WmsInveTransa> list = wmsinvetransaDao.verifyBillIdCardIsConsistent(idLis);

        // 循环遍历判断有效证件是否一致
        for (WmsInveTransa transa : list)
        {
            // 判断是否存在不一致的有效证件
            if (!bool)
            {
                break;
            }

            for (WmsInveTransa wTransa : list)
            {

                // 判断有效证件不一致的时候设置bool为false
                if (!transa.getId_card().equals(wTransa.getId_card()))
                {
                    bool = false;
                    break;
                }
            }
        }

        return bool;
    }

    /**
     * @Title: getCategoryTotalBillInfos
     * @Description:  根据单据id进行获取对应的单据的收益信息
     * @param ids 上单表主键(多个单据id以","分隔)
     * @param return_date 收益月份
     * @param type 获取数据的类型(1表示已获收益, 2表示待获收益)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 上午10:03:51
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getCategoryTotalBillInfos(java.lang.String)
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getCategoryTotalBillInfos(String ids,String return_date, Integer type)
    {

        //定义查询条件map集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        
        // 定义返回结果集
        List<Map<String, Object>> res_list = new ArrayList<Map<String, Object>>();

        // 将字符串的ids进行以逗号分隔解析成字符串数组
        String[] idStrs = ids.split(",");

        // 定义存放单据id的集合,为了方便的进行批量查询
        List<String> idLis = new ArrayList<String>();

        // 循环遍历将字符串数组中的上单表主键id值进行循环遍历添加到单据id集合中
        for (String id : idStrs)
        {
            // 将id添加到上单表主键集合中
            idLis.add(id);
        }
        
        paramsMap.put("idLis", idLis);//设置上单表主键id集合
        
        if(return_date != null && !"".equals(return_date))
        {
        	paramsMap.put("return_date", return_date);//设置收益月份
        }
        else
        {
        	paramsMap.put("return_date", "全部月份");//设置收益月份
        }

        // 定义返回结果集合
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // 判断单据id集合是否为空或者集合中是否存在数据
        if (idLis != null && idLis.size() > 0)
        {

            // 判断单据的收益数据的类型是否为已获收益
            if (type.compareTo(1) == 0)
            {
                // 根据上单表主键集合进行获取已获收益的单据信息
                list = wmsinvetransaDao.getCategoryTotalAlreadyIncomeInfosByIds(paramsMap);
            }
            else
            {
                // 根据上单表主键集合进行获取待获收益的单据信息
                list = wmsinvetransaDao.getCategoryTotalStayIncomeInfosByIds(paramsMap);
            }

        }

        // 循环遍历得到的上单表主键id参数,为了复核页面的格式要求方便读取数据
        for (String id : idStrs)
        {
            Map<String, Object> resultMap = new HashMap<String, Object>();// 定义存放对应上单表主键的信息

            BigDecimal count = BigDecimal.ZERO;// 对应对应单据的收益求和

            // 定义对应上单表主键的集合数据信息
            List<Map<String, Object>> wmsInveTransaIdList = new ArrayList<Map<String, Object>>();

            // 循环返回的数据集合
            for (Map<String, Object> map : list)
            {
                if (id.equals(map.get("wms_inve_transa_id").toString()))
                {
                    wmsInveTransaIdList.add(map);// 将单据信息添加到list中

                    count = count.add(new BigDecimal(map.get("payable_basic_income").toString()));// 进行单据的收益求和
                }
            }

            resultMap.put("wms_inve_transa_id", id);// 设置上单表主键
            resultMap.put("data", wmsInveTransaIdList);// 设置对应主键的集合
            resultMap.put("count", count);// 设置对应单据的收益求和

            res_list.add(resultMap);
        }

        return res_list;
    }

    /**
     * @Title: getYueFenTotalBillInfos
     * @Description: 获取按照月份进行统计的单据的收益信息
     * @param return_date 收益月份
     * @param type 查询数据的类型(1表示获取已获收益,2表示待获收益)
     * @param ids 上单表主键字符串(多个以","分隔)
     * @return 返回数据集合
     * @author: DongHao
     * @time:2017年4月1日 下午3:44:31
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getYueFenTotalBillInfos(java.lang.String, java.lang.Integer, java.lang.String)
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getYueFenTotalBillInfos(String return_date, Integer type, String ids)
    {
        // 定义返回结果集合
        List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>();

        // 定义查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 将字符串的ids进行以逗号分隔解析成字符串数组
        String[] idStrs = ids.split(",");

        // 定义存放单据id的集合,为了方便的进行批量查询
        List<String> idLis = new ArrayList<String>();

        // 循环遍历将字符串数组中的上单表主键id值进行循环遍历添加到单据id集合中
        for (String id : idStrs)
        {
            // 将id添加到上单表主键集合中
            idLis.add(id);
        }

        // 定义返回结果集合
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // 判断单据id集合是否为空或者集合中是否存在数据
        if (idLis != null && idLis.size() > 0)
        {
            paramsMap.put("idLis", idLis);// 设置id集合
            
            if(return_date != null && !"".equals(return_date))
            {
            	paramsMap.put("return_date", return_date);// 设置收益月份
            }
            else
            {
            	paramsMap.put("return_date", "全部月份");// 设置收益月份
            }
            

            // 判断单据的收益数据的类型是否为已获收益
            if (type.compareTo(1) == 0)
            {
                // 根据上单表主键集合进行获取已获收益的单据信息
                list = wmsinvetransaDao.getMonthTotalAlreadyIncomeInfosByIds(paramsMap);
            }
            else
            {
                // 根据上单表主键集合进行获取待获收益的单据信息
                list = wmsinvetransaDao.getMonthTotalStayIncomeInfosByIds(paramsMap);
            }

        }
        
        // 遍历得到的结果集合,将数据组合成页面需要显示的格式
        for (Map<String, Object> map : list)
        {
        	BigDecimal count = BigDecimal.ZERO;// 定义求收益的求和变量
        	
        	String income_month = "";// 定义变量收益月份
        	
        	Map<String, Object> res_map = new HashMap<String, Object>();// 定义存放结果的map集合
        	 
        	List<Map<String, Object>> res_list = new ArrayList<Map<String, Object>>();// 定义与月份对应的数据集合
        	
        	// 判断接收到的收益月份的参数是否为全部月份
            if (return_date == null || "".equals(return_date))
            {
                income_month = (String) map.get("return_date");// 获取收益月份
            }
            else
            {
                income_month = return_date;
            }
        	
        	// 判断结果集合中是否已经存在了统计过的月份数据,如果已经统计,则跳出本次循环(防止数据重复统计)
            if (verifyIncomeMonthDataIsExist(result_list, income_month))
            {
                continue;
            }

            // 遍历取出对应收益月份的数据
            for (Map<String, Object> map1 : list)
            {
                // 判断收益月份是否相同
                if (income_month.equals((String) map1.get("return_date")))
                {
                    res_list.add(map1);// 想对应的月份中添加数据集合
                    count = count.add(new BigDecimal(map1.get("payable_basic_income").toString()));
                }
            }

            res_map.put("return_date", income_month);// 设置收益月份
            res_map.put("count", count);// 收益根据月份统计的收益总和
            res_map.put("data", res_list);// 设置对应的收益月份的数据

            result_list.add(res_map);
        }

        

        return result_list;
    }

    /**
     * @Title: verifyIncomeMonthDataIsExist
     * @Description: 验证集合中是否已经存在该收益月份的数据
     * @param res_list 数据集合
     * @param income_month 收益月份
     * @return 存在该收益月份的数据返回true,否则返回false
     * @author: DongHao
     * @time:2017年4月1日 下午4:08:22
     * history:
     * 1、2017年4月1日 DongHao 创建方法
    */
    private boolean verifyIncomeMonthDataIsExist(List<Map<String, Object>> res_list, String income_month)
    {
        boolean bool = false;// 定义变量标记数据集合中是否存在该收益月份的数据(默认为false)

        // 遍历数据集合判断收益月份的数据是否存在
        for (Map<String, Object> map : res_list)
        {
            // 判断收益月份是否存在
            if (income_month.equals(map.get("return_date").toString()))
            {
                bool = true;// 存在该收益月份的数据则设置为true
            }
        }

        return bool;
    }

    /**
     * @Title: getCustomerTotalBillInfos
     * @Description: 获取客户收益总信息
     * @param return_date 收益日期
     * @param ids 上单表主键字符串类型(多个主键以","号分隔)
     * @return 返回map客户收益账单信息
     * @author: DongHao
     * @time:2017年4月5日 下午4:54:52
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getCustomerTotalBillInfos(java.lang.String, java.lang.String)
     * history:
     * 1、2017年4月5日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getCustomerTotalBillInfos(String return_date, String ids)
    {
        //定义返回结果集map
        Map<String, Object> res_map = new HashMap<String, Object>();
        
        //定义查询参数map集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        
        //将字符串上单表主键以","进行分隔成字符串数组
        String[] idsArray = ids.split(",");
        
        //定义存放上单表主键集合,方便查询时进行批量操作
        List<String> idLis = new ArrayList<String>();
        
        //遍历字符串数组,将数组中的id循环添加到存放id的集合中
        for(String id : idsArray)
        {
            idLis.add(id);//向集合中添加数据
        }
        
        if(return_date != null && !"".equals(return_date))
        {
        	paramsMap.put("return_date", return_date);//设置查询条件参数(收益日期)
        }
        else
        {
        	paramsMap.put("return_date", "全部月份");//设置查询条件参数(收益日期)
        }
        
        paramsMap.put("idLis", idLis);//设置上单表主键id集合
        
        //根据查询条件进行获取客户的收益信息
        res_map = wmsinvetransaDao.getCustomerTotalBillInfos(paramsMap);
        
        //获取获取投资金额
        Map<String, Object> productAccountMap = wmsinvetransaDao.getCUstomerProductAccount(paramsMap);
        
        res_map.put("product_account_num", productAccountMap.get("product_account_num"));
        res_map.put("product_account", productAccountMap.get("product_account"));
        
        return res_map;
    }

    /**
     * @Title: verifyIsExistCredit
     * @Description: 验证债权每日销售限额是否可用
     * @param wms_inve_transa_id 上单表主键
     * @param user 当前登录系统的人员信息对象
     * @param product_account 投资金额
     * @return 返回布尔类型的变量,如果每日销售限额可以使用返回true,否则返回false
     * @author: DongHao
     * @time:2017年3月1日 上午12:57:18
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#verifyIsExistCredit(com.zx.sframe.util.vo.UserBean, java.math.BigDecimal)
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    @Override
    public boolean verifyIsExistCredit(Integer wms_inve_transa_id,UserBean user)
    {
        //时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        //定义布尔类型的返回结果的变量
        boolean bool = true;
        
        java.util.Date nowDate = null;
        
        try
        {
            nowDate = format.parse(format.format(new Date(System.currentTimeMillis())));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
        boolean dateBefore = wmsInveCreditLimitService.verifyActDateOfPaymentToNowDateBefore(nowDate);
        
        if(dateBefore)
        {
            //判断产品是否为可拆分类产品
            Map<String, Object> categoryMap = wmsinvetransaDao.getCategoryTypeByWmsInveTransaId(wms_inve_transa_id);
            
            //判断返回的产品类型是否为空
            if(categoryMap != null && categoryMap.size() > 0)
            {
                
                if(categoryMap.get("category_type") != null)
                {
                    
                    Integer category_type = (Integer) categoryMap.get("category_type");
                    
                    // 判断是否时可拆分类产品
                    if(category_type.compareTo(4) == 0)
                    {
                        //根据上单表主键进行获取对应的单据信息
                        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wms_inve_transa_id);
                        
                        //判断单据是否时线下合同,如果时线下合同则不进行债权限制的逻辑,如果是线上合同则进行债权的限制逻辑
                        if(wmsInveTransa != null && wmsInveTransa.getContract_signing_type().equals("2"))
                        {
                            //判断该单据是否被标记为使用续期本金进行支付,如果标记成使用续期本金进行支付则不进行限制,否则进行全集团销售限制
                            if(wmsInveTransa.getIs_extend_amount() != null && !"1".equals(wmsInveTransa.getIs_extend_amount()))
                            {
                                //获取每日已支付的线上金额
                                Map<String, Object> paidAccount = wmsinvetransaDao.getNowDayPaidAccount(user.getUserId());
                                
                                //获取每日债权销售限额
                                WmsInveSaleLimit wmsInveSaleLimit = wmsinvetransaDao.getNowDayCreditSaleLimit(user.getUserId());
                                
                                //判断获取的每日债权限制信息是否为空(为空返回true)
                                if(wmsInveSaleLimit != null && wmsInveSaleLimit.getLimit_amount() != null)
                                {
                                    //判断每日债权限制金额是否为0,为0不允许上单(为0返回false)
                                    if(wmsInveSaleLimit.getLimit_amount().compareTo(BigDecimal.ZERO) == 0)
                                    {
                                        bool = false;
                                    }
                                    else
                                    {
                                        //获取每日已支付的线上金额
                                        BigDecimal paidAccountBigDecimal = paidAccount.get("totalTransaOnlineAccountPaid") != null ? new BigDecimal(paidAccount.get("totalTransaOnlineAccountPaid").toString()).abs() : BigDecimal.ZERO;
                                        
                                        BigDecimal resAccount = wmsInveSaleLimit.getLimit_amount().subtract(paidAccountBigDecimal);
                                        
                                        //判断已支付线上金额是否小于债权限制金额
                                        if(resAccount.compareTo(BigDecimal.ZERO) != 1)
                                        {
                                            bool = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return bool;
    }


    /**
     * @Title: printCustomerIncomeBillpdf
     * @Description: 打印预览客户收益账单
     * @param return_date(收益月份)
     * @param ids (字符串数组逗号分隔)
     * @author: zhangyunfei
     * @time:2017年4月13日 上午9:34:04
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#printCustomerIncomeBillpdf(java.lang.String, java.lang.String)
     * history:
     * 1、2017年4月13日 Administrator 创建方法
    */
    @Override
    public void printCustomerIncomeBillpdf(String return_date, String ids, HttpServletResponse response)
    {
        // 表格顶部客户信息map
        Map<String, Object> cmap = getCustomerTotalBillInfos(return_date, ids);

        // 将ids转成数组 放到list中
        List<String> idsLis = new ArrayList<String>();
        String[] str = ids.split(",");
        for (String id : str)
        {
            idsLis.add(id);
        }
        // 按月份统计(1已获收益)
        List<Map<String, Object>> cBillObtainedMonth = getYueFenTotalBillInfos(return_date, 1, ids);
        // 待获收益
        List<Map<String, Object>> cBillUnObtainedMonth = getYueFenTotalBillInfos(return_date, 2, ids);

        // 按产品统计(1已获收益)
        List<Map<String, Object>> cBillObtainedProduct = getCategoryTotalBillInfos(ids, return_date, 1);
        // 待获收益
        List<Map<String, Object>> cBillUnObtainedProduct = getCategoryTotalBillInfos(ids, return_date, 2);
        // 导出客户收益账单
        CustomerBillExportPdfUtil.doExport(cBillObtainedMonth, cBillUnObtainedMonth, cBillObtainedProduct, cBillUnObtainedProduct, cmap, idsLis, response);
    }

    /**
     * @Title: verifyIsAllGroupSaleLimit
     * @Description: 金额支付时验证全集团销售限额是否可以进行支付
     * @param wmsInveTransa 上单信息对象
     * @param wmsTransaProd 上单产品信息对象
     * @param user 当前登录人信息
     * @return 返回布尔类型的值(允许支付返回true,否则返回false)
     * @author: DongHao
     * @time:2017年5月10日 上午10:27:03
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#verifyIsAllGroupSaleLimit(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd)
     * history:
     * 1、2017年5月10日 DongHao 创建方法
    */
    @Override
    public boolean verifyIsAllGroupSaleLimit(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsTransaProd, UserBean user)
    {
        boolean bool = true;//定义返回的结果允许上单返回true,不允许上单返回false
        
        //判断实际支付日期是否为当前系统时间
        boolean dateBefo = wmsInveCreditLimitService.verifyActDateOfPaymentToNowDateBefore(wmsInveTransa.getDate_of_act());
        
        //判断实际支付时间是否为当前时间,当前时间返回true,否则返回false
        if(dateBefo)
        {
            // 判断该产品是否为可拆分类的产品(根据当前上单的产品id进行获取该产品类型)
            Map<String, Object> categoryMap = wmsinvetransaDao.getCategoryType(wmsTransaProd.getWms_inve_pruduct_category_id());

            // 判断返回的产品类型是否为空
            if (categoryMap != null && categoryMap.size() > 0)
            {
                //判断获取的产品类型字段是否为空
                if(categoryMap.get("category_type") != null)
                {
                 
                    Integer category_type = (Integer) categoryMap.get("category_type");
                    
                    //判断产品类型是否为可拆分类产品
                    if(category_type.compareTo(4) == 0)
                    {
                        //根据上单产品表主键进行获取原上单的产品信息
                        WmsInveTransaProd oldWmsInveTransaProd = wmsInveTransaProdDao.get(wmsTransaProd.getWms_inve_transa_prod_id());
                        
                        //根据上单表主键获取对应的上单信息
                        WmsInveTransa wmsInveTransaBean = wmsinvetransaDao.get(wmsInveTransa.getWms_inve_transa_id());
                        
                        //获取原上单的产品类型
                        Map<String, Object> oldCategoryTypeMap = wmsinvetransaDao.getCategoryTypeByWmsInveTransaId(wmsInveTransa.getWms_inve_transa_id());
                        
                        //判断上单时选择的产品是否与支付时选择的产品一致
                        if(oldWmsInveTransaProd != null && oldWmsInveTransaProd.getWms_inve_pruduct_category_id().compareTo(wmsTransaProd.getWms_inve_pruduct_category_id()) == 0)
                        {
                            /*
                             *上单时产品id与支付时产品id一致(说明在支付时产品没有进行更换) 
                             *产品id一致的时候需要判断选择的合同类型是否为线上合同 
                             */
                            if(wmsInveTransaBean != null && "2".equals(wmsInveTransaBean.getContract_signing_type()))
                            {
                                //合同类型满足为线上合同,则进行判断是否时全新支付(全新支付不包含上单时标记的使用续期本金进行支付)
                                if(wmsInveTransaBean.getIs_extend_amount() != null && !"1".equals(wmsInveTransaBean.getIs_extend_amount()))
                                {
                                    // 获取每日已支付的线上金额
                                    Map<String, Object> paidAccount = wmsinvetransaDao.getNowDayPaidAccount(user.getUserId());

                                    // 获取每日债权销售限额
                                    WmsInveSaleLimit wmsInveSaleLimit = wmsinvetransaDao.getNowDayCreditSaleLimit(user.getUserId());

                                    // 判断获取的每日债权限制信息是否为空(为空返回true)
                                    if (wmsInveSaleLimit != null && wmsInveSaleLimit.getLimit_amount() != null)
                                    {
                                        // 判断每日债权限制金额是否为0,为0不允许上单(为0返回false)
                                        if (wmsInveSaleLimit.getLimit_amount().compareTo(BigDecimal.ZERO) == 0)
                                        {
                                            bool = false;
                                        }
                                        else
                                        {
                                            // 获取每日已支付的线上金额
                                            BigDecimal paidAccountBigDecimal = paidAccount.get("totalTransaOnlineAccountPaid") != null ? new BigDecimal(paidAccount.get("totalTransaOnlineAccountPaid").toString()).abs() : BigDecimal.ZERO;

                                            BigDecimal resAccount = wmsInveSaleLimit.getLimit_amount().subtract(paidAccountBigDecimal);

                                            // 判断已支付线上金额是否小于债权限制金额
                                            if (resAccount.compareTo(BigDecimal.ZERO) != 1)
                                            {
                                                bool = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            //上单时产品id与支付时产品id不一致(说明在支付时产品进行了更换)
                            //判断原上单产品类型是否为可拆分类型
                            if(oldCategoryTypeMap != null && oldCategoryTypeMap.size() > 0)
                            {
                                //判断获取到的产品类型不为空
                                if(oldCategoryTypeMap.get("category_type") != null)
                                {
                                    
                                    Integer old_category_type = (Integer) oldCategoryTypeMap.get("category_type");
                                    
                                    //判断产品类型是否为可拆分类产品
                                    if(old_category_type.compareTo(4) == 0)
                                    {
                                        //判断合同类型是否为线上合同
                                        if(wmsInveTransaBean != null && "2".equals(wmsInveTransaBean.getContract_signing_type()))
                                        {
                                            //合同类型满足为线上合同,则进行判断是否时全新支付(全新支付不包含上单时标记的使用续期本金进行支付)
                                            if(wmsInveTransaBean.getIs_extend_amount() != null && !"1".equals(wmsInveTransaBean.getIs_extend_amount()))
                                            {
                                                // 获取每日已支付的线上金额
                                                Map<String, Object> paidAccount = wmsinvetransaDao.getNowDayPaidAccount(user.getUserId());

                                                // 获取每日债权销售限额
                                                WmsInveSaleLimit wmsInveSaleLimit = wmsinvetransaDao.getNowDayCreditSaleLimit(user.getUserId());

                                                // 判断获取的每日债权限制信息是否为空(为空返回true)
                                                if (wmsInveSaleLimit != null && wmsInveSaleLimit.getLimit_amount() != null)
                                                {
                                                    // 判断每日债权限制金额是否为0,为0不允许上单(为0返回false)
                                                    if (wmsInveSaleLimit.getLimit_amount().compareTo(BigDecimal.ZERO) == 0)
                                                    {
                                                        bool = false;
                                                    }
                                                    else
                                                    {
                                                        // 获取每日已支付的线上金额
                                                        BigDecimal paidAccountBigDecimal = paidAccount.get("totalTransaOnlineAccountPaid") != null ? new BigDecimal(paidAccount.get("totalTransaOnlineAccountPaid").toString()).abs() : BigDecimal.ZERO;

                                                        BigDecimal resAccount = wmsInveSaleLimit.getLimit_amount().subtract(paidAccountBigDecimal);

                                                        // 判断已支付线上金额是否小于债权限制金额
                                                        if (resAccount.compareTo(BigDecimal.ZERO) != 1)
                                                        {
                                                            bool = false;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        //原上单类型为不可拆分,在支付时修改成可拆分类型的产品,则设置成线上合同,所以此处不进行判断是否时线上合同
                                        //判断是否时全新支付(全新支付不包含上单时标记的使用续期本金进行支付)
                                        if(wmsInveTransaBean.getIs_extend_amount() != null && !"1".equals(wmsInveTransaBean.getIs_extend_amount()))
                                        {
                                            // 获取每日已支付的线上金额
                                            Map<String, Object> paidAccount = wmsinvetransaDao.getNowDayPaidAccount(user.getUserId());

                                            // 获取每日债权销售限额
                                            WmsInveSaleLimit wmsInveSaleLimit = wmsinvetransaDao.getNowDayCreditSaleLimit(user.getUserId());

                                            // 判断获取的每日债权限制信息是否为空(为空返回true)
                                            if (wmsInveSaleLimit != null && wmsInveSaleLimit.getLimit_amount() != null)
                                            {
                                                // 判断每日债权限制金额是否为0,为0不允许上单(为0返回false)
                                                if (wmsInveSaleLimit.getLimit_amount().compareTo(BigDecimal.ZERO) == 0)
                                                {
                                                    bool = false;
                                                }
                                                else
                                                {
                                                    // 获取每日已支付的线上金额
                                                    BigDecimal paidAccountBigDecimal = paidAccount.get("totalTransaOnlineAccountPaid") != null ? new BigDecimal(paidAccount.get("totalTransaOnlineAccountPaid").toString()).abs() : BigDecimal.ZERO;

                                                    BigDecimal resAccount = wmsInveSaleLimit.getLimit_amount().subtract(paidAccountBigDecimal);

                                                    // 判断已支付线上金额是否小于债权限制金额
                                                    if (resAccount.compareTo(BigDecimal.ZERO) != 1)
                                                    {
                                                        bool = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bool;
    }

    /**
     * 
     * @Title: getRole
     * @Description: 获取角色
     * @param user_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年5月8日 下午5:50:01
     * history:
     * 1、2017年5月8日 zhangmingjian 创建方法
     */
    @Override
    public Map<String, Object> getRole(Integer user_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user_id);
        // 循环所有用户角色名称，判断具有哪些角色
        for (String role : roleList)
        {
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("role_super", "1");
            }
            if (role.equals("财务柜员"))
            {
                // 可以看见所有数据
                paramMap.put("role_cwgy", "1");
            }
            // 判断角色名称是否为财务柜员主管
            if (role.equals("财务柜员主管"))
            {
                paramMap.put("role_cwgyzg", "1");
            }
            // 判断角色名称是否为财务主管
            if (role.equals("理财财务主管"))
            {
                paramMap.put("role_cwzg", "1");
            }
        }
        return paramMap;
    }

    /**
     * @Title: getSearchParamsMap
     * @Description: 将传进来的查询条件转成map格式
     * @param queryBean 查询条件对象
     * @return map格式查询条件
     * @author: jinzhm
     * @time:2017年5月10日 上午10:52:38
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    private Map<String, Object> getSearchParamsMap(WmsInveTransaCardSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 客户姓名
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        // 产品
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()) && !queryInfo.getCategory_name().equals("-1"))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        // 客户身份证号
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        // 单据状态
        if (StringUtil.isNotBlank(queryInfo.getData_status()) && !queryInfo.getData_status().equals("-1"))
        {
            paramMap.put("data_status", queryInfo.getData_status());
        }
        // 单据来源
        if (StringUtil.isNotBlank(queryInfo.getBill_source()) && !queryInfo.getBill_source().equals("-1"))
        {
            paramMap.put("bill_source", queryInfo.getBill_source());
        }
        // 录入员
        if (StringUtil.isNotBlank(queryInfo.getCreate_user_name()))
        {
            paramMap.put("create_user_name", queryInfo.getCreate_user_name());
        }
        // 业务员
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        // 归属业务员
        if (StringUtil.isNotBlank(queryInfo.getBel_salesman_id_id()))
        {
            paramMap.put("bel_salesman_id_id", queryInfo.getBel_salesman_id_id());
        }
        // 签单开始时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        // 签单结束时间
        if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        // 合同开始时间
        if (StringUtils.isNotBlank(queryInfo.getContract_begin()))
        {
            paramMap.put("contract_begin", queryInfo.getContract_begin());
        }
        // 合同结束时间
        if (StringUtils.isNotBlank(queryInfo.getContract_end()))
        {
            paramMap.put("contract_end", queryInfo.getContract_end());
        }
        return paramMap;
    }

    /**
     * @Title: exportFinancialTransa
     * @Description: 理财财务主管导出签单情况报表
     * @param queryBean 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 上午10:51:09
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#exportFinancialTransa(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, javax.servlet.http.HttpServletResponse)
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
    */
    @Override
    public void exportFinancialTransa(WmsInveTransaCardSearchBeanVO queryBean, HttpServletResponse response)
    {
        // 将查询条件转成map格式
        Map<String, Object> paramMap = getSearchParamsMap(queryBean);
        // 查询财务主管要导出的数据
        List<Map<String, Object>> dataList = wmsinvetransaDao.searchFinancialTransaListForExport(paramMap);

        // 格式化导出数据格式
        Map<String, Object> listMap = new HashMap<String, Object>();
        // 设置导出数据
        listMap.put("理财单据信息", dataList);
        
        try
        {
            String out_file_name = "理财单据信息一览表_" + DateUtil.date2String(new java.util.Date(), "yyyyMMddHHmmss")
                                   + ".xlsx";
            ExpertUtil eu = new ExpertUtil();
            eu.expertExcel("理财单据信息一览表.xlsx", listMap, out_file_name, null, 1, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: exportTransa
     * @Description: 理财柜员主管导出签单情况报表
     * @param queryInfo 查询条件
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 上午9:27:21
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#exportTransa(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, javax.servlet.http.HttpServletResponse)
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
    */
    @Override
    public void exportTransa(WmsInveTransaCardSearchBeanVO queryInfo, HttpServletResponse response)
    {
        // 格式化查询条件
        Map<String, Object> paramMap = getSearchParamsMap(queryInfo);

        // 查询并格式化要导出数据
        List<Map<String, Object>> dataList = formatMergeDataList(wmsinvetransaDao.searchTransaListForExport(paramMap));
        
        // 封装要导出数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(PoiMergeAbstract.DATA_LIST, dataList);
        dataMap.put(PoiMergeAbstract.START_ROW_NUM, 1);

        // 导出具有行合并的excel
        PoiMergeAbstract poiMerge = new PoiMergeAbstract()
        {

            @Override
            protected String getUnMergeListKeyName()
            {
                return "pay_types";
            }

            @Override
            protected String getExceptMergeLabel()
            {
                return "pay_type,pay_account";
            }
        };
        poiMerge.export("每日柜员统计签单报表.xlsx", "每日柜员统计签单报表_" + DateUtil.date2String(new java.util.Date(), "yyyyMMddHHmmss")
                                           + ".xlsx",
                        dataMap, response);

    }

    /**
     * @Title: formatMergeDataList
     * @Description: 格式化合并集合
     *      根据支付方式合并同一个单据的公共信息
     *      [{
     *          bill_code: bill_code,
     *          pay_type: pay_type1,
     *          pay_account: pay_account1
     *      },{
     *          bill_code: bill_code,
     *          pay_type: pay_type2,
     *          pay_account: pay_account2
     *      }]
     *      合并成
     *      [{
     *          bill_code: bill_code,
     *          pay_types: [{
     *              pay_type: pay_type1,
     *              pay_account: pay_account1
     *          },{
     *              pay_type: pay_type2,
     *              pay_account: pay_account2
     *          }]
     *      }]
     * @param dataList
     * @return 
     * @author: jinzhm
     * @time:2017年5月11日 下午12:57:34
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    private List<Map<String, Object>> formatMergeDataList(List<Map<String, Object>> dataList)
    {
        // 合并后要返回的新集合
        List<Map<String, Object>> mergeDataList = new ArrayList<Map<String, Object>>();
        
        String currentBillCode = "";

        // 循环要格式化的数据集合
        for (int i = 0; i < dataList.size(); i++)
        {
            // 获得数据
            Map<String, Object> dataMap = getDataMapFromList(dataList, i);
            // 获得理财单据编号
            String billCode = String.valueOf(dataMap.get("bill_code"));
            
            // 如果当前
            if (currentBillCode.equals(billCode))
            {
                continue;
            }
            // 设置当前处理的理财单据编号
            currentBillCode = billCode;

            // 临时存储支付方式集合
            List<Map<String, Object>> payTypeList = new ArrayList<Map<String, Object>>();
            // 根据支付方式的新数据集合(包含支付金额)
            Map<String, Object> payTypeMap = null;

            int count = 0;

            // 如果理财单据编号和下一条记录的理财单据编号相同，一直循环取下一个进行判断
            do
            {
                payTypeMap = new HashMap<String, Object>();
                // 设置支付方式及支付金额
                payTypeMap.put("pay_type", getDataMapFromList(dataList, i + count).get("pay_type"));
                payTypeMap.put("pay_account", getDataMapFromList(dataList, i + count).get("pay_account"));
                // 保存在临时集合中
                payTypeList.add(payTypeMap);
                count++;
            } while (billCode.equals(String.valueOf(getDataMapFromList(dataList, i + count).get("bill_code"))));
            // 将新的payTypeList存储到原list集合的map中
            dataMap.put("pay_types", payTypeList);
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
     * 
     * @Title: wmsVerifyCustomerByPtp
     * @Description: 用于验证登录ptp的客户是否满足在wms的投资要求
     * @param costomer_id crm客户id
     * @param month_num 投资要求月数
     * @return 返回布尔类型的变量:如果客户在wms系统中满足投资要求返回true,否则返回false
     * @author: DongHao
     * @time:2017年6月19日 上午9:01:50
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#wmsVerifyCustomerByPtp(java.lang.String, java.lang.String)
     * history:
     * 1、2017年6月19日 DongHao 创建方法
     */
    @Override
    public boolean wmsVerifyCustomerByPtp(Integer costomer_id, Integer month_num)
    {
        
        //定义布尔类型的变量用来标记当前客户是否存在满足条件的单据,存在则设置为true,否则设置false
        boolean bool = false;
        
        //定义数据查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("costomer_id", costomer_id);
        paramsMap.put("month_num", month_num);
        
        //根据客户的身份证号获取对应的客户单据
        List<WmsInveTransa> list = wmsinvetransaDao.getWmsInveTransaInfoByIdCard(paramsMap);
        
        //判断获取的数据集合是否为空,如果为空,则说明客户没有满足条件的上单单据则设置bool为false,否则设置bool为true
        if(list != null && list.size() > 0)
        {
            bool = true;
        }
        
        return bool;
    }
}
