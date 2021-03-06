package com.zx.emanage.inve.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveSignedApplicationDao;
import com.zx.emanage.inve.persist.WmsInveTransaAttDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.service.IWmsInveCustomerCardService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.util.transa.SignHelper;
import com.zx.emanage.inve.util.transa.SignTransaData;
import com.zx.emanage.inve.util.transa.SignTransaFactory;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveVerifyCustomerSignedVo;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.sysmanage.persist.SysUserRoleDao;
import com.zx.emanage.sysmanage.service.ICrmCustomerInfoService;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.CrmCustomerInfo;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSignedApplicationServiceImpl
 * 模块名称：
 * @Description: 内容摘要：
 * @author DongHao
 * @date 2017年2月5日
 * @version V1.0
 * history:
 * 1、2017年2月5日 DongHao 创建文件
 */
@Service("wmsInveSignedApplicationService")
public class WmsInveSignedApplicationServiceImpl implements IWmsInveSignedApplicationService
{

    @Autowired
    private WmsInveSignedApplicationDao wmsInveSignedApplicationDao;

    @Autowired
    private SysSpecialUserDao specialUserDao;

    @Autowired
    private SysUserRoleDao sysuserroleDao_m;

    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsInveTransaDao wmsinvetransaDao;

    @Autowired
    private WmsInveCustomerDao wmsInveCustomerDao;

    @Autowired
    private IWmsInveTransaService wmsInveTransaService;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveTransaAttDao wmsInveTransaAttDao;

    @Autowired
    private IWmsInveWorkFlowService iWmsInveWorkFlowService;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private WmsInveClerkProtocolDao wmsInveClerkProtocolDao;

    @Autowired
    private WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao;

    @Autowired
    private WmsInveTransaCardDao wmsInveTransaCardDao;

    @Autowired
    private ICrmCustomerInfoService crmCustomerInfoService;
    
    @Autowired
    private IWmsInveCustomerCardService wmsInveCustomerCardService;


    /**
     * @Title: getSignedInfos
     * @Description: 签单申请列表信息查询
     * @param queryInfo 查询条件对象
     * @param user 当前登录用户信息
     * @return 
     * @author: DongHao
     * @time:2017年2月4日 下午5:14:30
     * @see com.zx.emanage.inve.service.IWmsInveTransaService#getSignedInfos(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月4日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSignedInfos(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 设置当前登录的用户id
        paramsMap.put("user_id", user.getUserId());

        paramsMap.put("page", queryInfo.getOffset());
        paramsMap.put("page_size", queryInfo.getPagesize());

        // 判断客户名称是否为空
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramsMap.put("cus_name", queryInfo.getCus_name());
        }

        // 判断电话号码是否为空
        if (queryInfo.getMobile_phone() != null && !"".equals(queryInfo.getMobile_phone()))
        {
            paramsMap.put("mobile_phone", queryInfo.getMobile_phone());
        }

        // 判断产品名称是否为空
        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
        {
            paramsMap.put("category_name", queryInfo.getCategory_name());
        }

        // 判断客户经理是否为空
        if (queryInfo.getSalesman_name() != null && !"".equals(queryInfo.getSalesman_name()))
        {
            paramsMap.put("salesman_name", queryInfo.getSalesman_name());
        }

        // 判断单据状态是否为空
        if (queryInfo.getData_status() != null && !"".equals(queryInfo.getData_status()))
        {
            paramsMap.put("data_status", queryInfo.getData_status());
        }

        // 判断单据提交开始时间是否为空
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramsMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }

        // 判断单据提交结束时间是否为空
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramsMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }

        List<Map<String, Object>> list = wmsInveSignedApplicationDao.getSignedInfos(paramsMap);

        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),
                                                                                       wmsInveSignedApplicationDao.getSignedInfosCount(paramsMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: getAmountConfirmInfos
     * @Description: 查询支付确认相关数据信息
     * @param queryInfo 查询条件对象 
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月6日 下午5:54:55
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#getAmountConfirmInfos(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月6日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getAmountConfirmInfos(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        // 定义查询参数map集合
        Map<String, Object> paramsMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId()
                                                                                                                                   .toString(), "2");

        // 判断客户名称是否为空
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramsMap.put("cus_name", queryInfo.getCus_name());
        }

        // 判断电话号码是否为空
        if (queryInfo.getMobile_phone() != null && !"".equals(queryInfo.getMobile_phone()))
        {
            paramsMap.put("mobile_phone", queryInfo.getMobile_phone());
        }

        // 判断产品名称是否为空
        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
        {
            paramsMap.put("category_name", queryInfo.getCategory_name());
        }

        // 判断客户经理是否为空
        if (queryInfo.getSalesman_name() != null && !"".equals(queryInfo.getSalesman_name()))
        {
            paramsMap.put("salesman_name", queryInfo.getSalesman_name());
        }

        // 判断单据提交开始时间是否为空
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramsMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }

        // 判断单据提交结束时间是否为空
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramsMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }

        // 设置当前登录的用户id
        paramsMap.put("user_id", user.getUserId());
        paramsMap.put("page", queryInfo.getOffset());
        paramsMap.put("page_size", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmsInveSignedApplicationDao.getAmountConfirmInfos(paramsMap);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramsMap.get("idList"), (List<String>) paramsMap.get("taskIdList"),
                                                      (String) paramsMap.get("processDefinitionKey"));
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),
                                                                                       wmsInveSignedApplicationDao.getAmountConfirmInfosCount(paramsMap),
                                                                                       list);
        return bean.getGridData();

    }

    /**
     * @Title: getCustomerIncomeCardInfos
     * @Description: 获取客户收益卡信息
     * @param cus_name 客户姓名
     * @param id_card 身份证号
     * @return 
     * @author: DongHao
     * @time:2017年2月10日 上午11:27:36
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#getCustomerIncomeCardInfos(java.lang.String, java.lang.String)
     * history:
     * 1、2017年2月10日 DongHao 创建方法
    */
    @Override
    public List<Map<String, Object>> getCustomerIncomeCardInfos(String cus_name, String id_card)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("cus_name", cus_name);
        paramsMap.put("id_card", id_card);

        List<Map<String, Object>> list = wmsInveSignedApplicationDao.getCustomerIncomeCardInfos(paramsMap);

        return list;
    }

    /**
     * 
     * @Title: saveSigendApplicationInfo
     * @Description: 签单申请
     * @param wmsInveTransa 上单信息
     * @param wmsInveTransaProd 上单产品信息
     * @param fileArr 附件信息
     * @param saveFlag 标识位 1表示是提交按钮, 0表示暂存按钮
     * @param wmsCustomer 客户信息
     * @param beanVO 上单信息
     * @param user 当前登录的用户信息
     * @return 返回字符串的结果信息
     * @author: DongHao
     * @time:2017年3月2日 下午2:18:48
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#saveSigendApplicationInfo(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd, java.lang.String, java.lang.String, com.zx.emanage.util.gen.entity.WmsInveCustomer, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月2日 DongHao 创建方法
     */
    @Override
    @Transactional
    public String saveSigendApplicationInfo(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, String fileArr, String saveFlag,
                                            WmsInveCustomer wmsCustomer, WmsInveTransaSearchBeanVO beanVO, UserBean user)
    {
        // 定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 定义布尔类型的变量来标记上单表主键是否存在,存在bool为true,否则为false
        boolean bool = false;// 判断上单主键是否存在

        // 定义签单的排队人数变量,默认为0
        int count = 0;

        // 判断上单表主键是否为空,不为空则为bool赋值为true
        if (wmsInveTransa.getWms_inve_transa_id() == null)
        {
            bool = true;
        }
        
        //判断上单表中客户有效证件是否为空
        if(wmsInveTransa.getId_card() != null && !"".equals(wmsInveTransa.getId_card()))
        {
            //不为空的情况下将有效证件号中存在字母的将字母转换成大写
            wmsInveTransa.setId_card(wmsInveTransa.getId_card().toUpperCase());
        }
        //判断用户信息中有效证件是否为空
        if(wmsCustomer.getId_card() != null && !"".equals(wmsCustomer.getId_card()))
        {
            //不为空的情况下将有效证件中存在字母的将字母转换成大写
            wmsCustomer.setId_card(wmsCustomer.getId_card().toUpperCase());
        }
        
        //布尔类型的标记变量用于标记该上单客户是否存在可以使用的续期本金
        boolean isExistRenewalPrincipal = true;
        
        //判断只有上单的时候需要进行验证续期本金是否存在
        if(bool && saveFlag.equals("1"))
        {
            //判断该上单客户是否存在可以使用的续期本金的单据(根据当前客户的crm客户id)
            isExistRenewalPrincipal = verifyIsExistRenewalPrincipal(wmsInveTransa.getCostomer_id(), wmsInveTransaProd.getProduct_account());
            
            //存在可以使用的续期本金单据需要更新上单表中的是否使用续期本金的
            if(isExistRenewalPrincipal)
            {
                //更新上单表中的使用续期本金的字段
                wmsInveTransa.setIs_extend_amount("1");
            }
            else
            {
                wmsInveTransa.setIs_extend_amount("0");
            }
        }

        // 判断上单、追单金额是否在其正确的范围内
        HashMap<String, Object> paramMap = new HashMap<>();

        // 判断saveFlag是否为上单
        if ("1".equals(saveFlag))
        {
            // 根据产品表主键进行获取产品信息
            WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());

            paramMap.put("id_card", wmsInveTransa.getId_card());
            paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());

            // 判断当前客户上单是否为追单
            if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
            {
                // 判断客户追单金额不能低于最小值
                if (wmsInvePruductCategory.getCategory_additional_monery_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_additional_monery_min()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error1";
                }
                // 判断追单金额不能超过最大值
                if (wmsInvePruductCategory.getCategory_additional_monery_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_additional_monery_max()
                                                                                                    .doubleValue() * 10000)
                {
                    return "error2";
                }
                // 判断追单金额总和不能超过产品最大持有金额
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
                    if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                        && sumZdje > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
                    {
                        return "error5";
                    }
                }
            }
            else
            {
                // 判断上单金额不能低于最小值
                if (wmsInvePruductCategory.getCategory_investment_money_min() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_investment_money_min().doubleValue() * 10000)
                {
                    return "error3";
                }
                // 判断上单金额不能超过最大值
                if (wmsInvePruductCategory.getCategory_investment_money_max() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_investment_money_max().doubleValue() * 10000)
                {
                    return "error4";
                }
                // 判断上单金额不能超过产品最大持有金额
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                    && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
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
                if (sumCategoryInveTransaPayaccount >= wmsInvePruductCategory.getCategory_maximum_amount().doubleValue() * 10000)
                {
                    WmsInvePruductCategory disableWmsInveProductCategory = new WmsInvePruductCategory();
                    disableWmsInveProductCategory.setWms_inve_pruduct_category_id(wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                    disableWmsInveProductCategory.setIs_forbidden("1");
                    wmsInvePruductCategoryDao.update(disableWmsInveProductCategory);
                }
            }
        }

        // 定义当前系统时间变量
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 检查客户表里是否存在记录
        WmsInveCustomer wmsInveCustomer = null;
        String id_card = wmsCustomer.getId_card();
        paramMap = new HashMap<>();
        if (id_card != null && !"".equals(id_card))
        {
            paramMap.put("id_card", id_card.toUpperCase());
            wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
        }

        // 根据身份证号嘛获取客户的性别和出生日期,判断身份证号18位
        if (wmsInveTransa.getId_card() != null && wmsInveTransa.getId_card().length() == 18)
        {
            // 判断客户性别,偶数为女奇数为男
            String sex = wmsInveTransa.getId_card().substring(16, 17);
            if (Integer.parseInt(sex) % 2 == 0)
            {
                wmsInveTransa.setCus_gender("0");
            }
            else
            {
                wmsInveTransa.setCus_gender("1");
            }
            // 获取身份证号出生日期
            String birth = wmsInveTransa.getId_card().substring(6, 14);
            String birthday = birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6, 8);
            try
            {
                Date date = format.parse(birthday);
                wmsInveTransa.setCus_birthday(new java.sql.Date(date.getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

        }
        else if (wmsInveTransa.getId_card() != null && wmsInveTransa.getId_card().length() == 15)
        {
            // 判断身份证号15位
            // 判断客户性别
            String sex = wmsInveTransa.getId_card().substring(14, 15);
            if (Integer.parseInt(sex) % 2 == 0)
            {
                wmsInveTransa.setCus_gender("0");
            }
            else
            {
                wmsInveTransa.setCus_gender("1");
            }
            // 获取身份证号出生日期
            String birth = wmsInveTransa.getId_card().substring(6, 12);
            String birthday = "19" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
            try
            {
                Date date = format.parse(birthday);
                wmsInveTransa.setCus_birthday(new java.sql.Date(date.getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        

        // 在提交时，如果不存在客户记录，那保存客户信息 2015-11-24baisong 上单暂存不保存客户表 提交保存到客户表 保证客户的唯一性
        if (wmsInveCustomer == null && "1".equals(saveFlag))
        {

            // 判断客户id是否为空
            if (wmsInveTransa.getCostomer_id() == null)
            {
                // 客户id如果为空,则进行新增客户信息,需要将客户信息发送到crm,并且crm给返回客户id和客户编号
                Map<String, Object> resMap = newAddCostomerToCrmBackCostomerId(wmsInveTransa);

                // 判断新增客户信息返回的结果是否成功
                if (!"000".equals(resMap.get("ret_code").toString()))
                {
                    // 返回的结果不为000表示不成功,返回错误提示信息
                    return resMap.get("ret_msg").toString();
                }
                else
                {
                    // 新增客户成功
                    // 设置上单表中客户id
                    wmsInveTransa.setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置上单表中客户编号
                    wmsInveTransa.setCustomer_num((String) resMap.get("customer_num"));
                    // 设置上单表中客户来源
                    wmsInveTransa.setCustomer_source(1);
                    // 设置crm创建时间
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    // 设置客户表中的客户id
                    wmsCustomer.setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置客户表中客户编号
                    wmsCustomer.setCustomer_num((String) resMap.get("customer_num"));
                    // 设置客户表中客户来源
                    wmsCustomer.setCustomer_source("1");
                }
            }
            else
            {
                // 如果客户id已经存在,则设置客户id
                wmsCustomer.setCostomer_id(wmsInveTransa.getCostomer_id());
            }

            // 保存客户信息
            wmsInveCustomer = wmsInveTransaService.setWmsInveCustomer(wmsCustomer, wmsInveTransa, user, sysTime);
            // CRM关联信息表主键
            wmsInveCustomer.setCostomer_id(wmsCustomer.getCostomer_id());
            // 客户来源
            wmsInveCustomer.setCustomer_source(wmsCustomer.getCustomer_source());
            // 保存客户信息
            wmsInveCustomerDao.save(wmsInveCustomer);
        }
        else
        {
            // 判断客户id是否为空
            if (wmsInveTransa.getCostomer_id() == null)
            {
                // 客户id如果为空,则进行新增客户信息,需要将客户信息发送到crm,并且crm给返回客户id和客户编号
                Map<String, Object> resMap = newAddCostomerToCrmBackCostomerId(wmsInveTransa);

                // 判断新增客户信息返回的结果是否成功
                if (!"000".equals(resMap.get("ret_code").toString()))
                {
                    // 返回的结果不为000表示不成功,返回错误提示信息
                    return resMap.get("ret_msg").toString();
                }
                else
                {
                    // 新增客户成功
                    // 设置上单表中客户id
                    wmsInveTransa.setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置上单表中客户编号
                    wmsInveTransa.setCustomer_num((String) resMap.get("customer_num"));
                    // 设置上单表中客户来源
                    wmsInveTransa.setCustomer_source(1);
                    // 设置crm创建时间
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    // 设置客户表中的客户id
                    wmsCustomer.setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置客户表中客户编号
                    wmsCustomer.setCustomer_num((String) resMap.get("customer_num"));
                    // 设置客户表中客户来源
                    wmsCustomer.setCustomer_source("1");
                }
            }
            
            // 如果通过id_card获取到客户信息,则设置更新客户信息时间
            wmsCustomer.setLast_update_timestamp(sysTime);
            // 设置更新客户信息操作人
            wmsCustomer.setLast_update_user_id(user.getUserId());
            // 更行客户信息
            wmsInveCustomerDao.update(wmsCustomer);
            // 定义map类型的参数变量
            paramMap = new HashMap<>();

            // 判断有效证件号是否为空
            if (id_card != null && !"".equals(id_card))
            {
                // 根据有效证件号进行获取客户信息
                paramMap.put("id_card", id_card);
                wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
            }
        }
        // 如果上单表主键不为空,则对上单信息进行更新
        if (wmsInveTransa.getWms_inve_transa_id() != null)
        {
            // 更新上单信息
            wmsInveTransa = wmsInveTransaService.setWmsInveTransaForUpdate(wmsInveTransa, wmsInveCustomer, wmsInveTransaProd, saveFlag, sysTime,
                                                                           user, beanVO.getLCtype());
            // 如果zctype不等于空 不修改单据的状态，只修改单据的数据（用于暂存）
            // if (beanVO.getLCtype() == "" && !"0".equals(saveFlag))
            // {
            // wmsInveTransa.setData_status("2");
            // }

            // 判断LCType是否为空,为空表示为草稿状态
            if ("".equals(beanVO.getLCtype()))
            {
                // 查询参数map
                Map<String, Object> queryPersonnelParams = new HashMap<String, Object>();

                // 判断人员id是否为空
                if (wmsInveTransa.getSalesman_id() != null)
                {
                    queryPersonnelParams.put("personnel_id", wmsInveTransa.getSalesman_id());
                }
                // 判断人员短工号是否为空
                if (wmsInveTransa.getSalesman_shortcode() != null)
                {
                    queryPersonnelParams.put("personnel_shortCode", wmsInveTransa.getSalesman_shortcode());
                }

                // 根据人员id和短工号进行查询获取人员信息
                Map<String, Object> personnelmap = pmPersonnelDao.getPersonnelInfos(queryPersonnelParams);

                // 判断获取的人员信息是否为空
                if ((personnelmap != null && personnelmap.size() != 0) || "1".equals(saveFlag))
                {
                    // 判断获取的人员信息为空或者不存在记录的时候返回错误信息,error9表示归属业务员信息输入有误,请输入短工号!
                    if (personnelmap == null || personnelmap.size() == 0)
                    {
                        return "error9";
                    }

                    // 定义人员状态
                    String personnel_state = "1";

                    // 判断人员状态是否为兼职,如果是兼职则设置人员状态为实习状态
                    if ("7".equals("" + personnelmap.get("personnel_state")))
                    {
                        personnel_state = "2";
                    }

                    wmsInveTransa.setSalesman_id(Integer.parseInt(personnelmap.get("personnel_id").toString()));
                    wmsInveTransa.setTrans_salesman_status(personnel_state);

                    Map<String, Object> renYuanXinXiMap = wmsInveTransaService.findPersonnelInformationByPersonnelId(Integer.parseInt(personnelmap.get("personnel_id")
                                                                                                                                                  .toString()),
                                                                                                                     Integer.parseInt(personnelmap.get("personnel_deptid")
                                                                                                                                                  .toString()));

                    // 人员信息
                    Map<String, Object> yeWuYuan = (Map<String, Object>) renYuanXinXiMap.get("yeWuYuan");
                    PmPersonnel buMenJingLi = (PmPersonnel) renYuanXinXiMap.get("buMenJingLi");
                    PmPersonnel zhongFenZong = (PmPersonnel) renYuanXinXiMap.get("zhongFenZong");
                    PmPersonnel fuZhong = (PmPersonnel) renYuanXinXiMap.get("fuZhong");
                    PmPersonnel zong = (PmPersonnel) renYuanXinXiMap.get("zong");

                    // 设置业务员相关信息
                    wmsInveTransa.setSalesman_city_code("" + personnelmap.get("personnel_regionnumber"));
                    wmsInveTransa.setSalesman_name("" + personnelmap.get("personnel_name"));
                    wmsInveTransa.setSalesman_dept_id(Integer.parseInt(personnelmap.get("personnel_deptid").toString()));
                    wmsInveTransa.setSalesman_shortcode("" + personnelmap.get("personnel_shortcode"));
                    wmsInveTransa.setBel_salesman_id_id(Integer.parseInt(yeWuYuan.get("personnel_id").toString()));
                    wmsInveTransa.setBel_salesman_dept_id(Integer.parseInt(yeWuYuan.get("personnel_deptId").toString()));

                    // 设置部门经理相关信息
                    wmsInveTransa.setDepartment_manager_id(buMenJingLi.getPersonnel_id());
                    wmsInveTransa.setDepartment_manager_city_code(buMenJingLi.getPersonnel_regionnumber());
                    wmsInveTransa.setDepartment_manager_dept_id(buMenJingLi.getPersonnel_deptid());
                    wmsInveTransa.setBel_department_manager_dept_id(buMenJingLi.getPersonnel_deptid());
                    wmsInveTransa.setBel_department_manager_id(buMenJingLi.getPersonnel_id());

                    // 设置中分总相关信息
                    wmsInveTransa.setCenter_manager_dept_id(zhongFenZong.getPersonnel_deptid());
                    wmsInveTransa.setCenter_manager_id(zhongFenZong.getPersonnel_id());
                    wmsInveTransa.setBel_center_manager_dept_id(zhongFenZong.getPersonnel_deptid());
                    wmsInveTransa.setBel_center_manager_id(zhongFenZong.getPersonnel_id());

                    // 设置副总相关信息
                    wmsInveTransa.setVice_general_manager_city_code(fuZhong.getPersonnel_regionnumber());
                    wmsInveTransa.setVice_general_manager_id(fuZhong.getPersonnel_id());
                    wmsInveTransa.setVice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
                    wmsInveTransa.setBel_vice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
                    wmsInveTransa.setBel_vice_general_manager_id(fuZhong.getPersonnel_id());

                    // 设置总的相关信息
                    wmsInveTransa.setGeneral_manager_city_code(zong.getPersonnel_regionnumber());
                    wmsInveTransa.setGeneral_manager_dept_id(zong.getPersonnel_deptid());
                    wmsInveTransa.setGeneral_manager_id(zong.getPersonnel_id());
                    wmsInveTransa.setBel_general_manager_dept_id(zong.getPersonnel_deptid());
                    wmsInveTransa.setBel_general_manager_id(zong.getPersonnel_id());
                }
            }

            wmsinvetransaDao.update(wmsInveTransa);

        }
        else
        // 新增上单信息
        {
            wmsInveTransa = wmsInveTransaService.setWmsInveTransaForAdd(wmsInveTransa, wmsInveCustomer, wmsInveTransaProd, saveFlag, sysTime, user);
            wmsInveTransa.setBill_source(0);// 单据来源 0上单
            // 处理兼职单和在职单
            // 根据user_id获取状态
            Map<String, Object> queryPersonnelParams = new HashMap<String, Object>();
            if (wmsInveTransa.getSalesman_id() != null)
            {
                queryPersonnelParams.put("personnel_id", wmsInveTransa.getSalesman_id());
            }
            if (wmsInveTransa.getSalesman_shortcode() != null)
            {
                queryPersonnelParams.put("personnel_shortCode", wmsInveTransa.getSalesman_shortcode());
            }

            Map<String, Object> personnelmap = pmPersonnelDao.getPersonnelInfos(queryPersonnelParams);

            if ((personnelmap != null && personnelmap.size() != 0) || "1".equals(saveFlag))
            {
                if (personnelmap == null || personnelmap.size() == 0)
                {
                    return "error9";
                }
                String personnel_state = "1";
                if ("7".equals("" + personnelmap.get("personnel_state")))
                {
                    personnel_state = "2";
                }

                wmsInveTransa.setSalesman_id(Integer.parseInt(personnelmap.get("personnel_id").toString()));
                wmsInveTransa.setTrans_salesman_status(personnel_state);

                Map<String, Object> renYuanXinXiMap = wmsInveTransaService.findPersonnelInformationByPersonnelId(Integer.parseInt(personnelmap.get("personnel_id")
                                                                                                                                              .toString()),
                                                                                                                 Integer.parseInt(personnelmap.get("personnel_deptid")
                                                                                                                                              .toString()));

                // 人员信息
                Map<String, Object> yeWuYuan = (Map<String, Object>) renYuanXinXiMap.get("yeWuYuan");
                PmPersonnel buMenJingLi = (PmPersonnel) renYuanXinXiMap.get("buMenJingLi");
                PmPersonnel zhongFenZong = (PmPersonnel) renYuanXinXiMap.get("zhongFenZong");
                PmPersonnel fuZhong = (PmPersonnel) renYuanXinXiMap.get("fuZhong");
                PmPersonnel zong = (PmPersonnel) renYuanXinXiMap.get("zong");

                // 设置业务员相关信息
                wmsInveTransa.setSalesman_city_code("" + personnelmap.get("personnel_regionnumber"));
                wmsInveTransa.setSalesman_name("" + personnelmap.get("personnel_name"));
                wmsInveTransa.setSalesman_dept_id(Integer.parseInt(personnelmap.get("personnel_deptid").toString()));
                wmsInveTransa.setSalesman_shortcode("" + personnelmap.get("personnel_shortcode"));
                wmsInveTransa.setBel_salesman_id_id(Integer.parseInt(yeWuYuan.get("personnel_id").toString()));
                wmsInveTransa.setBel_salesman_dept_id(Integer.parseInt(yeWuYuan.get("personnel_deptId").toString()));

                // 设置部门经理相关信息
                wmsInveTransa.setDepartment_manager_id(buMenJingLi.getPersonnel_id());
                wmsInveTransa.setDepartment_manager_city_code(buMenJingLi.getPersonnel_regionnumber());
                wmsInveTransa.setDepartment_manager_dept_id(buMenJingLi.getPersonnel_deptid());
                wmsInveTransa.setBel_department_manager_dept_id(buMenJingLi.getPersonnel_deptid());
                wmsInveTransa.setBel_department_manager_id(buMenJingLi.getPersonnel_id());

                // 设置中分总相关信息
                wmsInveTransa.setCenter_manager_dept_id(zhongFenZong.getPersonnel_deptid());
                wmsInveTransa.setCenter_manager_id(zhongFenZong.getPersonnel_id());
                wmsInveTransa.setBel_center_manager_dept_id(zhongFenZong.getPersonnel_deptid());
                wmsInveTransa.setBel_center_manager_id(zhongFenZong.getPersonnel_id());

                // 设置副总相关信息
                wmsInveTransa.setVice_general_manager_city_code(fuZhong.getPersonnel_regionnumber());
                wmsInveTransa.setVice_general_manager_id(fuZhong.getPersonnel_id());
                wmsInveTransa.setVice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
                wmsInveTransa.setBel_vice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
                wmsInveTransa.setBel_vice_general_manager_id(fuZhong.getPersonnel_id());

                // 设置总的相关信息
                wmsInveTransa.setGeneral_manager_city_code(zong.getPersonnel_regionnumber());
                wmsInveTransa.setGeneral_manager_dept_id(zong.getPersonnel_deptid());
                wmsInveTransa.setGeneral_manager_id(zong.getPersonnel_id());
                wmsInveTransa.setBel_general_manager_dept_id(zong.getPersonnel_deptid());
                wmsInveTransa.setBel_general_manager_id(zong.getPersonnel_id());


            }
            wmsinvetransaDao.save(wmsInveTransa);
        }
        
        // 修改上单产品信息
        if (wmsInveTransaProd.getWms_inve_transa_prod_id() != null)
        {
            wmsInveTransaProd = wmsInveTransaService.setWmsInveTransaProdForUpdate(wmsInveTransaProd, user, sysTime);
            
            //保存收益卡信息
            WmsInveCustomerCard wmsInveCustomerCard = new WmsInveCustomerCard();
            wmsInveCustomerCard.setBank_branch(wmsInveTransaProd.getBank_branch());
            wmsInveCustomerCard.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit());
            wmsInveCustomerCard.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city());
            wmsInveCustomerCard.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro());
            wmsInveCustomerCard.setCard_no(wmsInveTransaProd.getCard_no());
            wmsInveCustomerCard.setCard_owner_name(wmsInveTransaProd.getCard_owner_name());
            wmsInveCustomerCard.setId_card(wmsInveTransa.getId_card());
            wmsInveCustomerCard.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveCustomerCard.setCreate_user_id(user.getUserId());
            Map<String, Object> customerMap = wmsInveCustomerCardService.newAddIncomeCard(user, wmsInveCustomerCard);
            
            wmsInveTransaProd.setWms_inve_customer_card_id((Integer)customerMap.get("wms_inve_customer_card_id"));
            
            wmsInveTransaProdDao.update(wmsInveTransaProd);
        }
        else
        {
            wmsInveTransaProd = wmsInveTransaService.setWmsInveTransaProdForAdd(wmsInveTransaProd, wmsInveTransa, user, sysTime);
            
            //保存收益卡信息
            WmsInveCustomerCard wmsInveCustomerCard = new WmsInveCustomerCard();
            wmsInveCustomerCard.setBank_branch(wmsInveTransaProd.getBank_branch());
            wmsInveCustomerCard.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit());
            wmsInveCustomerCard.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city());
            wmsInveCustomerCard.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro());
            wmsInveCustomerCard.setCard_no(wmsInveTransaProd.getCard_no());
            wmsInveCustomerCard.setCard_owner_name(wmsInveTransaProd.getCard_owner_name());
            wmsInveCustomerCard.setId_card(wmsInveTransa.getId_card());
            wmsInveCustomerCard.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveCustomerCard.setCreate_user_id(user.getUserId());
            Map<String, Object> customerMap = wmsInveCustomerCardService.newAddIncomeCard(user, wmsInveCustomerCard);
            
            wmsInveTransaProd.setWms_inve_customer_card_id((Integer)customerMap.get("wms_inve_customer_card_id"));
            
            wmsInveTransaProdDao.save(wmsInveTransaProd);
        }


        if ("0".equals(saveFlag))
        {
            return wmsInveTransa.getWms_inve_transa_id() + ";" + wmsInveTransaProd.getWms_inve_transa_prod_id();
        }

        // 不为草稿状态，并且有上单表主键
        if (!bool && !"1".equals(beanVO.getLCtype()))
        {
            count = findClerkDataBySalemanCount(user);

            if ("".equals(beanVO.getLCtype()))
            {
                // 添加理财柜员业务信息
                WmsInveClerkData wmsInveClerkData = setWmsInveClerkData(wmsInveTransa, wmsInveTransaProd, wmsInveCustomer, user);
                wmsInveClerkDataDao.save(wmsInveClerkData);
            }

        }
        // 上单表主键为空
        if (bool)
        {
            
            count = findClerkDataBySalemanCount(user);

            // 添加理财柜员业务信息
            WmsInveClerkData wmsInveClerkData = setWmsInveClerkData(wmsInveTransa, wmsInveTransaProd, wmsInveCustomer, user);
            wmsInveClerkDataDao.save(wmsInveClerkData);
        }

        // 释放债权
        if ("12".equals(beanVO.getLCtype()) || "17".equals(beanVO.getLCtype()) || "19".equals(beanVO.getLCtype()) || "20".equals(beanVO.getLCtype()))
        {
            if (wmsInveTransa.getContract_signing_type() != null && "1".equals(wmsInveTransa.getContract_signing_type()))
            {
                Integer wms_inve_clerk_protocol_id = null;
                // 根据上单表主键获取柜员协议信息
                WmsInveClerkProtocol wmsInveClerkProtocol = wmsInveClerkProtocolDao.findWmsInveClerkProtocolByInveTransaId(wmsInveTransa.getWms_inve_transa_id());
                if (wmsInveClerkProtocol != null)
                {
                    wms_inve_clerk_protocol_id = wmsInveClerkProtocol.getWms_inve_clerk_protocol_id();
                }
                // 释放已匹配的债权信息
                CreditBusiness.getInstance().releaseMatchedCreditForTransaFlow(wmsInveTransa.getWms_inve_transa_id(), wms_inve_clerk_protocol_id,
                                                                               user);

            }
        }

        // 添加上单提交启动流程
        if ("1".equals(saveFlag) && "".equals(beanVO.getLCtype()))
        {
            // 调用流程(公用)
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            iWmsInveWorkFlowService.startFinancialWorkFlow(user.getUserId().toString(), wmsInveTransa.getWms_inve_transa_id().toString(),
                                                           wmsInveTransa.getSalesman_dept_id().toString(), wmsInveTransa.getSalesman_id().toString(),
                                                           WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        }

        String taskId = "";
        // 待修订（审核退回）
        if ("12".equals(beanVO.getLCtype()))
        {
            // 上单审核退回修订 更新柜员合同表客户银行卡信息
            Map<String, Object> cmap = new HashMap<>();
            cmap.put("wms_inve_transa_id", wmsInveTransaProd.getWms_inve_transa_id());
            cmap.put("a_bank_number", wmsInveTransaProd.getCard_no());
            cmap.put("bank_of_deposit_pro", wmsInveTransaProd.getBank_of_deposit_pro());
            cmap.put("bank_of_deposit_city", wmsInveTransaProd.getBank_of_deposit_city());
            cmap.put("bank_of_deposit", wmsInveTransaProd.getBank_of_deposit());
            cmap.put("bank_branch", wmsInveTransaProd.getBank_branch());
            wmsInveClerkProtocolDao.updateWmsinveclerkprotocolBankInfo(cmap);

            taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wmsInveTransa.getWms_inve_transa_id().toString(),
                                                                   WorkflowConstantHelp.FINANCIALSINGLEROCESS_SHTH);

            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("13");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setStatus(beanVO.getLCtype());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        // 待修订（支付退回）
        else if ("17".equals(beanVO.getLCtype()))
        {
            WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByTransaId(wmsInveTransa.getWms_inve_transa_id());
            wmsInveClerkData.setCategory_deadline(wmsInveTransaProd.getProduct_deadline());
            wmsInveClerkData.setCategory_name(wmsInveTransaProd.getCategory_name());
            wmsInveClerkData.setProduct_account(wmsInveTransaProd.getProduct_account());
            wmsInveClerkData.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            wmsInveClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveClerkData.setLast_update_user_id(user.getUserId());
            wmsInveClerkDataDao.update(wmsInveClerkData);

            // 重新计算一下排名信息
            count = findClerkDataBySalemanCount(user, wmsInveClerkData.getWms_inve_clerk_data_id());

            taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wmsInveTransa.getWms_inve_transa_id().toString(),
                                                                   WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZFTH);

            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("13");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setStatus(beanVO.getLCtype());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        // 待修订（签约退回）
        else if ("19".equals(beanVO.getLCtype()))
        {
            WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByTransaId(wmsInveTransa.getWms_inve_transa_id());
            wmsInveClerkData.setCategory_deadline(wmsInveTransaProd.getProduct_deadline());
            wmsInveClerkData.setCategory_name(wmsInveTransaProd.getCategory_name());
            wmsInveClerkData.setProduct_account(wmsInveTransaProd.getProduct_account());
            wmsInveClerkData.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            wmsInveClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveClerkData.setLast_update_user_id(user.getUserId());
            wmsInveClerkDataDao.update(wmsInveClerkData);
            // 重新计算一下排名信息
            count = findClerkDataBySalemanCount(user, wmsInveClerkData.getWms_inve_clerk_data_id());

            taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wmsInveTransa.getWms_inve_transa_id().toString(),
                                                                   WorkflowConstantHelp.FINANCIALSINGLEROCESS_QYTH);

            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("13");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setStatus(beanVO.getLCtype());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }
        // 待修订（主动撤单）
        else if ("20".equals(beanVO.getLCtype()))
        {
            WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByTransaId(wmsInveTransa.getWms_inve_transa_id());
            wmsInveClerkData.setCategory_deadline(wmsInveTransaProd.getProduct_deadline());
            wmsInveClerkData.setCategory_name(wmsInveTransaProd.getCategory_name());
            wmsInveClerkData.setProduct_account(wmsInveTransaProd.getProduct_account());
            wmsInveClerkData.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            wmsInveClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveClerkData.setLast_update_user_id(user.getUserId());
            wmsInveClerkDataDao.update(wmsInveClerkData);

            // 重新计算一下排名信息
            count = findClerkDataBySalemanCount(user, wmsInveClerkData.getWms_inve_clerk_data_id());
            taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wmsInveTransa.getWms_inve_transa_id().toString(),
                                                                   WorkflowConstantHelp.FINANCIALSINGLEROCESS_CD);

            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wmsInveTransa.getWms_inve_transa_id().toString());
            wDebtWorkFlowVO.setDebtkey("13");
            wDebtWorkFlowVO.setPass("true");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setStatus(beanVO.getLCtype());
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        }

        return "success," + count;
    }

    /**
     * @Title: setWmsInveClerkData
     * @Description: 设置理财柜员业务信息
     * @param wmsInveTransa
     * @param wmsInveTransaProd
     * @param wmsInveCustomer
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午11:44:53
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    private WmsInveClerkData setWmsInveClerkData(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsInveCustomer,
                                                 UserBean user)
    {
        PmPersonnel pm = pmPersonnelDao.get(user.getUserId());

        WmsInveClerkData wmsInveClerkData = new WmsInveClerkData();
        wmsInveClerkData.setData_type("1");
        wmsInveClerkData.setWms_inve_transa_id(wmsInveTransa.getWms_inve_transa_id());
        wmsInveClerkData.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        wmsInveClerkData.setCategory_name(wmsInveTransaProd.getCategory_name());
        wmsInveClerkData.setCategory_deadline(wmsInveTransaProd.getProduct_deadline());
        wmsInveClerkData.setProduct_account(wmsInveTransaProd.getProduct_account());
        wmsInveClerkData.setIs_order_extend("0");
        wmsInveClerkData.setOper_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setIs_finished("0");
        wmsInveClerkData.setPersonnel_regionnumber(pm.getPersonnel_regionnumber());
        wmsInveClerkData.setPersonnel_id(pm.getPersonnel_id());
        wmsInveClerkData.setIs_locked("0");
        wmsInveClerkData.setCreate_user_id(user.getUserId());
        wmsInveClerkData.setCreate_user_name(user.getRealname());
        wmsInveClerkData.setCreate_user_dept_id(user.getDeptId());
        wmsInveClerkData.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setEnable_flag("1");
        return wmsInveClerkData;
    }

    /**
     * @Title: getWmsInveTransaInfosByWmsInveTransaId
     * @Description: 根据上单表主键获取上单信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 下午1:11:24
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#getWmsInveTransaInfosByWmsInveTransaId(java.lang.String)
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveTransaInfosByWmsInveTransaId(String wms_inve_transa_id)
    {
        return wmsInveSignedApplicationDao.getWmsInveTransaInfosByWmsInveTransaId(wms_inve_transa_id);
    }

    /**
     * @Title: getWmsInveRenYuanInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午4:31:04
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#getWmsInveRenYuanInfo(java.lang.String)
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveRenYuanInfo(String wms_inve_transa_id)
    {
        Map<String, Object> resultMap = wmsInveSignedApplicationDao.getWmsInveRenYuanInfo(wms_inve_transa_id);
        return resultMap;
    }

    /**
     * @Title: getSigendProtocolList
     * @Description: 合同签订信息列表
     * @param queryInfo
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午5:52:46
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#getSigendProtocolList(com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getSigendProtocolList(WmsInveTransaCardSearchBeanVO queryInfo, UserBean user)
    {
        // 定义查询参数map集合
        Map<String, Object> paramsMap = wmsInveWorkFlowService.getWorkFlowToIdList(WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId()
                                                                                                                                   .toString(), "4");

        // 判断客户名称是否为空
        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
        {
            paramsMap.put("cus_name", queryInfo.getCus_name());
        }

        // 判断电话号码是否为空
        if (queryInfo.getMobile_phone() != null && !"".equals(queryInfo.getMobile_phone()))
        {
            paramsMap.put("mobile_phone", queryInfo.getMobile_phone());
        }

        // 判断产品名称是否为空
        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
        {
            paramsMap.put("category_name", queryInfo.getCategory_name());
        }

        // 判断客户经理是否为空
        if (queryInfo.getSalesman_name() != null && !"".equals(queryInfo.getSalesman_name()))
        {
            paramsMap.put("salesman_name", queryInfo.getSalesman_name());
        }

        // 判断单据提交开始时间是否为空
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramsMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }

        // 判断单据提交结束时间是否为空
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramsMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }

        // 设置当前登录的用户id
        paramsMap.put("user_id", user.getUserId());
        paramsMap.put("page", queryInfo.getOffset());
        paramsMap.put("page_size", queryInfo.getPagesize());

        List<Map<String, Object>> list = wmsInveSignedApplicationDao.getAmountConfirmInfos(paramsMap);
        list = wmsInveWorkFlowService.addTaskIdToList(list, (List<Integer>) paramsMap.get("idList"), (List<String>) paramsMap.get("taskIdList"),
                                                      (String) paramsMap.get("processDefinitionKey"));
        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveSignedApplicationDao.getAmountConfirmInfosCount(paramsMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 
     * @Title: sigendProd
     * @Description: 协议签订需要触发的流程
     * @param wms_inve_transa_id 单据id
     * @param taskId 任务id
     * @param user_id 用户id
     * @author: DongHao
     * @time:2017年2月13日 下午6:19:27
     * history:
     * 1、2017年2月13日 DongHao 创建方法
     */
    public String sigendProd(final String wms_inve_transa_id, String taskId, final String user_id)
    {
        String error = "success";
        try
        {
            if (taskId == null || "".equals(taskId))
            {
                taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id,
                                                                       WorkflowConstantHelp.FINANCIALSINGLEROCESS_QY);
            }
            final String finalTaskId = taskId;

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    // 为了流程历程排序，需要等待一秒
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        // e.printStackTrace();
                        throw new RuntimeException("线程等待出现错误！！！");
                    }
                    WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
                    wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
                    wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id);
                    wDebtWorkFlowVO.setDebtkey("3");
                    wDebtWorkFlowVO.setPass("true");
                    wDebtWorkFlowVO.setTaskId(finalTaskId);
                    wDebtWorkFlowVO.setUserID(user_id);
                    // 通过上单主键查询产品是否提供纸质合同
                    Map<String, Object> categoryMap = wmsinvetransaDao.getCategoryProtocolTypeByWmsInveTransaId(Integer.parseInt(wms_inve_transa_id));
                    if (categoryMap != null && "0".equals(categoryMap.get("has_paper_protocol")))
                    {
                        wDebtWorkFlowVO.setAdvice("系统自动审核");
                    }
                    wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
                }
            }).start();
        }
        catch (Exception e)
        {
            error = "error";
        }
        return error;
    }

    /**
     * @Title: withdrawSingle
     * @Description: 撤单
     * @param wms_inve_transa_id
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月13日 下午6:39:46
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#withdrawSingle(java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月13日 DongHao 创建方法
    */
    @Transactional
    @Override
    public Map<String, Object> withdrawSingle(String wms_inve_transa_id, String advice, String data_status, UserBean user)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            // 释放债权信息
            wmsInveTransaService.releaseMatchedCredit(Integer.parseInt(wms_inve_transa_id), user);

            String taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZF);
            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id);
            wDebtWorkFlowVO.setDebtkey("11");
            wDebtWorkFlowVO.setPass("is_cancel");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setAdvice(advice);
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            resultMap.put("error", "success");
        }
        catch (Exception e)
        {
            resultMap.put("error", "error");
        }

        return resultMap;
    }

    /**
     * @Title: invalidSingle
     * @Description: 作废
     * @param wms_inve_transa_id
     * @param advice
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午9:29:47
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#invalidSingle(java.lang.String, java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月14日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> invalidSingle(String wms_inve_transa_id, String advice, String data_status, UserBean user)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String taskId = "";
        try
        {
            if (!"1".equals(data_status))
            {
                // 释放债权信息
                wmsInveTransaService.releaseMatchedCredit(Integer.parseInt(wms_inve_transa_id), user);
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                paramsMap.put("wms_inve_transa_id", wms_inve_transa_id);

                WmsInveClerkProtocol wmsInveClerkProtocol = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByWmsInveTransaId(paramsMap);

                // 待修订（审核退回）
                if ("12".equals(data_status))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_SHTH);
                    if (wmsInveClerkProtocol != null)
                    {
                        CreditBusiness.getInstance().deleteCreditMatchHistory(Integer.parseInt(wms_inve_transa_id),
                                                                              wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
                    }

                }
                // 待修订（支付退回）
                else if ("17".equals(data_status))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZFTH);
                }
                // 待修订（签约退回）
                else if ("19".equals(data_status))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_QYTH);
                }
                // 待修订（主动撤单）
                else if ("20".equals(data_status))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_CD);
                }

                // 释放续期本金
                wmsInveTransaService.wmsInveTransaInvalid(Integer.parseInt(wms_inve_transa_id), user);

                // 作废上单单据需要同步更新柜员合同协议表设置该单据的状态enable_flag==0
                wmsInveSignedApplicationDao.updateWmsInveClerkProtocolByWmsInveTransaId(wms_inve_transa_id);

                WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
                wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
                wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id);
                wDebtWorkFlowVO.setDebtkey("12");
                wDebtWorkFlowVO.setPass("nullify");
                wDebtWorkFlowVO.setTaskId(taskId);
                wDebtWorkFlowVO.setUserID(user.getUserId().toString());
                wDebtWorkFlowVO.setAdvice(advice);

                if ("12".equals(data_status))
                {
                    WmsInveTransa wmsInveTransa = new WmsInveTransa();
                    wmsInveTransa.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
                    WmsInveTransaProd wmsInveTransaProd = wmsinvetransaDao.getWmsInveTransaProdByWmsInveTransaId(wms_inve_transa_id);
                    wmsInveTransaService.toSingleNullify(wmsInveTransa, wmsInveTransaProd, user, wDebtWorkFlowVO, "12");
                }
                else
                {
                    wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
                }
            }
            else
            {
                WmsInveTransa wmsInveTransa = new WmsInveTransa();
                wmsInveTransa.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
                wmsInveTransa.setData_status("7");
                wmsinvetransaDao.update(wmsInveTransa);
            }

            resultMap.put("error", "success");
        }
        catch (Exception e)
        {
            resultMap.put("error", "error");
        }

        return resultMap;
    }

    /**
     * @Title: backSingle
     * @Description: 退回操作流程
     * @param wms_inve_transa_id
     * @param advice
     * @param data_status
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月14日 上午11:13:22
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#backSingle(java.lang.String, java.lang.String, java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月14日 DongHao 创建方法
    */
    @Transactional
    @Override
    public Map<String, Object> backSingle(String wms_inve_transa_id, String advice, String data_status, String taskId, UserBean user)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try
        {
            // 待支付
            if ("2".equals(data_status))
            {
                if (taskId == null && "".equals(taskId))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZF);
                }

                // 释放债权信息
                wmsInveTransaService.releaseMatchedCredit(Integer.parseInt(wms_inve_transa_id), user);

                // 释放试用续期本金
                wmsInveTransaService.wmsInveTransaInvalid(Integer.parseInt(wms_inve_transa_id), user);
            }
            // 待签约
            else if ("13".equals(data_status))
            {
                if (taskId == null && "".equals(taskId))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_QY);
                }
            }
            // 待审核
            else if ("11".equals(data_status))
            {
                if (taskId == null && "".equals(taskId))
                {
                    taskId = wmsInveWorkFlowService.getSignedProcessTaskId(wms_inve_transa_id, WorkflowConstantHelp.FINANCIALSINGLEROCESS_ZGQR);
                }
            }

            WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
            wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
            wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id);
            wDebtWorkFlowVO.setDebtkey("14");
            wDebtWorkFlowVO.setPass("false");
            wDebtWorkFlowVO.setTaskId(taskId);
            wDebtWorkFlowVO.setUserID(user.getUserId().toString());
            wDebtWorkFlowVO.setAdvice(advice);
            wDebtWorkFlowVO.setStatus(data_status);
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            resultMap.put("error", "success");
        }
        catch (Exception e)
        {
            resultMap.put("error", "error");
        }

        return resultMap;
    }

    /**
     * @Title: findClerkDataBySalemanCount
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月27日 下午11:29:40
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#findClerkDataBySalemanCount(com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月27日 DongHao 创建方法
    */
    @Override
    public int findClerkDataBySalemanCount(UserBean user)
    {

        PmPersonnel personnel = pmPersonnelDao.get(user.getUserId());
        // 获取排队人数
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());

        int count = wmsInveClerkDataDao.findClerkDataBySalemanCount(paramsMap);

        return count;
    }

    private int findClerkDataBySalemanCount(UserBean user, Integer wms_inve_clerk_data_id)
    {

        PmPersonnel personnel = pmPersonnelDao.get(user.getUserId());
        // 获取排队人数
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("personnel_regionNumber", personnel.getPersonnel_regionnumber());
        paramsMap.put("wms_inve_clerk_data_id", wms_inve_clerk_data_id);

        int count = wmsInveClerkDataDao.findClerkDataBySalemanCount(paramsMap);

        return count;
    }

    /**
     * @Title: updateContractSigningType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_transa_id
     * @param act_of_date
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 上午1:44:03
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#updateContractSigningType(java.lang.String, java.lang.String)
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    @Override
    public boolean updateContractSigningType(String wms_inve_transa_id, String act_of_date, String origCategoryId, String newCategoryId,
                                          BigDecimal productAccount, UserBean user)
    {
        boolean flag = true;        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            // Date nowDate = new Date();
            //
            // String nowDateStr = format.format(nowDate);
            //
            // Date oneDate = format.parse(nowDateStr);
            // Date twoDate = format.parse(act_of_date);
            // if (twoDate.compareTo(oneDate) < 0)
            // {
            // wmsInveSignedApplicationDao.updateContractSigningType(wms_inve_transa_id);
            // }
            // if (twoDate.compareTo(oneDate) == 0)
            // {
            // wmsInveSignedApplicationDao.updateContractSigningTypeOnLine(wms_inve_transa_id);
            // }

            flag = CreditBusiness.getInstance().changeCategoryOrPaymentAccount(Integer.parseInt(origCategoryId), Integer.parseInt(newCategoryId),
                                                                        Integer.parseInt(wms_inve_transa_id), productAccount,
                                                                        format.parse(act_of_date), user);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return flag;

    }

    /**
     * 
     * @Title: newAddCostomerToCrmBackCostomerId
     * @Description: 新增用户调用crm接口设置用户信息
     * @param inveTransa
     * @return 返回提示信息和客户id以,号分割
     * @author: DongHao
     * @time:2017年2月17日 下午3:50:10
     * history:
     * 1、2017年2月17日 DongHao 创建方法
     */
    public Map<String, Object> newAddCostomerToCrmBackCostomerId(WmsInveTransa inveTransa)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 调用crm接口
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_PUTCustomerInformation"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        Map<String, Object> queryPersonnelParams = new HashMap<>();
        if (inveTransa.getSalesman_shortcode() != null)
        {
            queryPersonnelParams.put("personnel_shortCode", inveTransa.getSalesman_shortcode());
        }
        if (inveTransa.getSalesman_id() != null)
        {
            queryPersonnelParams.put("personnel_id", inveTransa.getSalesman_id());
        }

        Map<String, Object> pmPersonnel = pmPersonnelDao.getPersonnelInfos(queryPersonnelParams);

        nvps.add(new BasicNameValuePair("customer_name", inveTransa.getCus_name()));
        nvps.add(new BasicNameValuePair("contact_number", inveTransa.getMobile_phone()));
        nvps.add(new BasicNameValuePair("id_card_number", inveTransa.getId_card().toUpperCase()));
        nvps.add(new BasicNameValuePair("email_adress", inveTransa.getCustomer_email()));
        nvps.add(new BasicNameValuePair("domicile_place", inveTransa.getContact_address()));
        nvps.add(new BasicNameValuePair("personnel_id", pmPersonnel.get("personnel_id").toString()));

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

                int costomer_id = Integer.parseInt(obj.get("costomer_id").toString());
                String customer_num = obj.get("customer_num").toString();
                resMap.put("costomer_id", costomer_id);
                resMap.put("customer_num", customer_num);
                resMap.put("ret_code", obj.get("ret_code").toString());
            }
            else
            {
                resMap.put("costomer_id", 0);
                resMap.put("customer_num", "");
                resMap.put("ret_code", "error8");
                resMap.put("ret_msg", obj.get("ret_msg") + "");
            }

            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * @Title: saveSignedInfoPad
     * @Description: pad客户端进行签单
     * @param wmsInveTransa 上单信息对象
     * @param wmsInveTransaProd 上单产品信息对象
     * @param wmsCustomer 客户信息对象
     * @param beanVO 上单查询信息对象
     * @param user 当前登录的客户信息对象
     * @return 返回map集合信息提示
     * @author: DongHao
     * @time:2017年3月3日 下午10:42:49
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#saveSignedInfoPad(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd, com.zx.emanage.util.gen.entity.WmsInveCustomer, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月3日 DongHao 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Map<String, Object> saveSignedInfoPad(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsCustomer,
                                                 WmsInveTransaSearchBeanVO beanVO, UserBean user)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        //判断是上单信息表中的有效证件号是否为空,如果不为空则将有效证件中的字母转成大写
        if(wmsInveTransa.getId_card() != null && !"".equals(wmsInveTransa.getId_card()))
        {
            wmsInveTransa.setId_card(wmsInveTransa.getId_card().trim().toUpperCase());
        }
        
        //判断上单客户信息表中有效证件号是否为空,不为空则将有效证件中的小写字母转换成大写
        if(wmsCustomer.getId_card() != null && !"".equals(wmsCustomer.getId_card()))
        {
            wmsCustomer.setId_card(wmsCustomer.getId_card().trim().toUpperCase());
        }
        
        // 定义返回map集合信息
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 定义查询信息map集合
        Map<String, Object> paramMap = new HashMap<String, Object>();

        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        paramMap.put("id_card", wmsInveTransa.getId_card());
        paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());

        /**********设置签单信息***********/
        wmsInveTransa.setProduct_total_count_upper(digitUpperUtil.digitUppercase(((BigDecimal) wmsInveTransaProd.getProduct_account()).doubleValue(),
                                                                                 true).replaceAll("元", ""));// 设置上单中的投资金额大写
        // 设置产品的投资金额和产品期限
        wmsInveTransaProd.setProduct_deadline(wmsInvePruductCategory.getCategory_deadline());// 设置产品期限
        wmsInveTransaProd.setProduct_account(wmsInveTransaProd.getProduct_account().multiply(new BigDecimal(10000)));// 设置投资金额
        wmsInveTransaProd.setCategory_name(wmsInvePruductCategory.getCategory_name());// 设置产品名称
        wmsInveTransaProd.setProduct_interest(wmsInvePruductCategory.getCategory_return_rate().toString());

        wmsInveTransa.setProduct_total_count_lower(wmsInveTransaProd.getProduct_account());// 设置上单表中的投资金额小写
        
        //判断是否标记为赎回再签(pad端赎回再签默认为赎回再签)
        if(wmsInveTransa.getIs_extend_amount() == null || "".equals(wmsInveTransa.getIs_extend_amount()))
        {
            //判断该上单客户是否存在可以使用的续期本金的单据(根据当前客户的crm客户id)
            boolean isExistRenewalPrincipal = verifyIsExistRenewalPrincipal(wmsInveTransa.getCostomer_id(), wmsInveTransaProd.getProduct_account());
              
            //存在可以使用的续期本金单据需要更新上单表中的是否使用续期本金的
            if(isExistRenewalPrincipal)
            {
               //更新上单表中的使用续期本金的字段
               wmsInveTransa.setIs_extend_amount("1");
            }
            else
            {
               wmsInveTransa.setIs_extend_amount("0");
            }
        }
        

        if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
        {// 是追单
            if (wmsInvePruductCategory.getCategory_additional_monery_min() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_additional_monery_min().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "100");
                resultMap.put("ret_msg", "追单金额不能低于最小值");
                return resultMap;
            }
            if (wmsInvePruductCategory.getCategory_additional_monery_max() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_additional_monery_max().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "101");
                resultMap.put("ret_msg", "追单金额不能超过最大值");
                return resultMap;
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
                                                                                                          : product.get("product_account").toString());
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                    && sumZdje > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
                {
                    resultMap.put("ret_code", "102");
                    resultMap.put("ret_msg", "追单金额总和不能超过产品最大持有金额 ");
                    return resultMap;
                }
            }
        }
        else
        {// 是上单
            if (wmsInvePruductCategory.getCategory_investment_money_min() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_investment_money_min().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "103");
                resultMap.put("ret_msg", "上单金额不能低于最小值 ");
                return resultMap;
            }
            if (wmsInvePruductCategory.getCategory_investment_money_max() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_investment_money_max().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "104");
                resultMap.put("ret_msg", "上单金额不能超过最大值 ");
                return resultMap;
            }
            if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "106");
                resultMap.put("ret_msg", "上单金额不能超过产品最大持有金额 ");
                return resultMap;
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
            if (sumCategoryInveTransaPayaccount >= wmsInvePruductCategory.getCategory_maximum_amount().doubleValue() * 10000)
            {
                WmsInvePruductCategory disableWmsInveProductCategory = new WmsInvePruductCategory();
                disableWmsInveProductCategory.setWms_inve_pruduct_category_id(wmsInvePruductCategory.getWms_inve_pruduct_category_id());
                disableWmsInveProductCategory.setIs_forbidden("1");
                wmsInvePruductCategoryDao.update(disableWmsInveProductCategory);
            }
        }
        

        Timestamp sysTime = new Timestamp(System.currentTimeMillis());

        // 根据身份证号嘛获取客户的性别和出生日期,判断身份证号18位
        if (wmsInveTransa.getId_card() != null && wmsInveTransa.getId_card().length() == 18)
        {
            // 判断客户性别
            String sex = wmsInveTransa.getId_card().substring(16, 17);
            if (Integer.parseInt(sex) % 2 == 0)
            {
                wmsInveTransa.setCus_gender("0");
            }
            else
            {
                wmsInveTransa.setCus_gender("1");
            }
            // 获取身份证号出生日期
            String birth = wmsInveTransa.getId_card().substring(6, 14);
            String birthday = birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6, 8);
            try
            {
                Date date = format.parse(birthday);
                wmsInveTransa.setCus_birthday(new java.sql.Date(date.getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

        }
        else if (wmsInveTransa.getId_card() != null && wmsInveTransa.getId_card().length() == 15)
        {
            // 判断身份证号15位
            // 判断客户性别
            String sex = wmsInveTransa.getId_card().substring(14, 15);
            if (Integer.parseInt(sex) % 2 == 0)
            {
                wmsInveTransa.setCus_gender("0");
            }
            else
            {
                wmsInveTransa.setCus_gender("1");
            }
            // 获取身份证号出生日期
            String birth = wmsInveTransa.getId_card().substring(6, 12);
            String birthday = "19" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-" + birth.substring(4, 6);
            try
            {
                Date date = format.parse(birthday);
                wmsInveTransa.setCus_birthday(new java.sql.Date(date.getTime()));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }

        // 检查客户表里是否存在记录
        WmsInveCustomer wmsInveCustomer = null;
        String id_card = wmsCustomer.getId_card();
        paramMap = new HashMap<>();
        if (id_card != null && !"".equals(id_card))
        {
            paramMap.put("id_card", id_card);
            // 根据身份证号获取客户信息
            wmsInveCustomer = wmsInveCustomerDao.getByEntity(paramMap);
        }

        // 判断如果客户信息不存在
        if (wmsInveCustomer == null)
        {
            // 判断客户id是否存在
            if (wmsInveTransa.getCostomer_id() == null || wmsInveTransa.getCostomer_id().equals(0))
            {
                wmsInveTransa.setSalesman_id(user.getUserId());
                Map<String, Object> resMap = newAddCostomerToCrmBackCostomerId(wmsInveTransa);

                if (!"000".equals(resMap.get("ret_code").toString()))
                {
                    resultMap.put("ret_code", "108");
                    resultMap.put("ret_msg", resMap.get("ret_msg").toString());
                    return resultMap;
                }
                else
                {
                    wmsInveTransa.setCostomer_id((Integer) resMap.get("costomer_id"));
                    wmsInveTransa.setCustomer_num((String) resMap.get("customer_num"));
                    wmsInveTransa.setCustomer_source(1);
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    // 设置crm创建时间
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    wmsCustomer.setCostomer_id((Integer) resMap.get("costomer_id"));
                    wmsCustomer.setCustomer_num((String) resMap.get("customer_num"));
                }
            }
            else
            {
                wmsCustomer.setCostomer_id(wmsInveTransa.getCostomer_id());
            }
            wmsCustomer.setCustomer_source("1");
            wmsInveTransa.setCustomer_source(1);
            wmsInveCustomer = wmsInveTransaService.setWmsInveCustomer(wmsCustomer, wmsInveTransa, user, sysTime);// 保存客户信息
            wmsInveCustomer.setCostomer_id(wmsCustomer.getCostomer_id());// CRM关联信息表主键
            
            wmsInveCustomerDao.save(wmsInveCustomer);// 保存客户信息
        }
        else
        {
            // 判断客户id是否存在
            if (wmsInveTransa.getCostomer_id() == null || wmsInveTransa.getCostomer_id().equals(0))
            {
                wmsInveTransa.setSalesman_id(user.getUserId());
                Map<String, Object> resMap = newAddCostomerToCrmBackCostomerId(wmsInveTransa);

                if (!"000".equals(resMap.get("ret_code").toString()))
                {
                    resultMap.put("ret_code", "108");
                    resultMap.put("ret_msg", resMap.get("ret_msg").toString());
                    return resultMap;
                }
                else
                {
                    wmsInveTransa.setCostomer_id((Integer) resMap.get("costomer_id"));
                    wmsInveTransa.setCustomer_num((String) resMap.get("customer_num"));
                    wmsInveTransa.setCustomer_source(1);
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    // 设置crm创建时间
                    wmsInveTransa.setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    wmsCustomer.setCostomer_id((Integer) resMap.get("costomer_id"));
                    wmsCustomer.setCustomer_num((String) resMap.get("customer_num"));
                }
            }
            
            wmsCustomer.setCustomer_source("1");
            wmsInveTransa.setCustomer_source(1);
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

        wmsInveTransa = wmsInveTransaService.setWmsInveTransaForAdd(wmsInveTransa, wmsInveCustomer, wmsInveTransaProd, "1", sysTime, user);
        wmsInveTransa.setBill_source(0);
        wmsInveTransa.setContract_signing_type("2");

        // 定义查询人员信息参数map集合
        Map<String, Object> queryPersonnelParams = new HashMap<String, Object>();

        queryPersonnelParams.put("personnel_id", user.getUserId());

        // 根据条件查询获取人员信息
        Map<String, Object> personnelmap = pmPersonnelDao.getPersonnelInfos(queryPersonnelParams);

        String personnel_state = "1";

        // 判断人员状态
        if ("7".equals("" + personnelmap.get("personnel_state")))
        {
            personnel_state = "2";
        }

        wmsInveTransa.setSalesman_id(Integer.parseInt(personnelmap.get("personnel_id").toString()));
        wmsInveTransa.setTrans_salesman_status(personnel_state);

        // 获取上单业务员的归属信息
        Map<String, Object> renYuanXinXiMap = wmsInveTransaService.findPersonnelInformationByPersonnelId(Integer.parseInt(personnelmap.get("personnel_id")
                                                                                                                                      .toString()),
                                                                                                         Integer.parseInt(personnelmap.get("personnel_deptid")
                                                                                                                                      .toString()));

        // 人员信息
        Map<String, Object> yeWuYuan = (Map<String, Object>) renYuanXinXiMap.get("yeWuYuan");
        PmPersonnel buMenJingLi = (PmPersonnel) renYuanXinXiMap.get("buMenJingLi");
        PmPersonnel zhongFenZong = (PmPersonnel) renYuanXinXiMap.get("zhongFenZong");
        PmPersonnel fuZhong = (PmPersonnel) renYuanXinXiMap.get("fuZhong");
        PmPersonnel zong = (PmPersonnel) renYuanXinXiMap.get("zong");

        // 设置业务员相关信息
        wmsInveTransa.setSalesman_city_code("" + personnelmap.get("personnel_regionnumber"));
        wmsInveTransa.setSalesman_name("" + personnelmap.get("personnel_name"));
        wmsInveTransa.setSalesman_dept_id(Integer.parseInt(personnelmap.get("personnel_deptid").toString()));
        wmsInveTransa.setSalesman_shortcode("" + personnelmap.get("personnel_shortcode"));
        wmsInveTransa.setBel_salesman_id_id(Integer.parseInt(yeWuYuan.get("personnel_id").toString()));
        wmsInveTransa.setBel_salesman_dept_id(Integer.parseInt(yeWuYuan.get("personnel_deptId").toString()));

        // 设置部门经理相关信息
        wmsInveTransa.setDepartment_manager_id(buMenJingLi.getPersonnel_id());
        wmsInveTransa.setDepartment_manager_city_code(buMenJingLi.getPersonnel_regionnumber());
        wmsInveTransa.setDepartment_manager_dept_id(buMenJingLi.getPersonnel_deptid());
        wmsInveTransa.setBel_department_manager_dept_id(buMenJingLi.getPersonnel_deptid());
        wmsInveTransa.setBel_department_manager_id(buMenJingLi.getPersonnel_id());

        // 设置中分总相关信息
        wmsInveTransa.setCenter_manager_dept_id(zhongFenZong.getPersonnel_deptid());
        wmsInveTransa.setCenter_manager_id(zhongFenZong.getPersonnel_id());
        wmsInveTransa.setBel_center_manager_dept_id(zhongFenZong.getPersonnel_deptid());
        wmsInveTransa.setBel_center_manager_id(zhongFenZong.getPersonnel_id());

        // 设置副总相关信息
        wmsInveTransa.setVice_general_manager_city_code(fuZhong.getPersonnel_regionnumber());
        wmsInveTransa.setVice_general_manager_id(fuZhong.getPersonnel_id());
        wmsInveTransa.setVice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
        wmsInveTransa.setBel_vice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
        wmsInveTransa.setBel_vice_general_manager_id(fuZhong.getPersonnel_id());

        // 设置总的相关信息
        wmsInveTransa.setGeneral_manager_city_code(zong.getPersonnel_regionnumber());
        wmsInveTransa.setGeneral_manager_dept_id(zong.getPersonnel_deptid());
        wmsInveTransa.setGeneral_manager_id(zong.getPersonnel_id());
        wmsInveTransa.setBel_general_manager_dept_id(zong.getPersonnel_deptid());
        wmsInveTransa.setBel_general_manager_id(zong.getPersonnel_id());

        // 保存上单信息
        wmsinvetransaDao.save(wmsInveTransa);
        
        //保存收益卡信息
        WmsInveCustomerCard wmsInveCustomerCard = new WmsInveCustomerCard();
        wmsInveCustomerCard.setBank_branch(wmsInveTransaProd.getBank_branch());
        wmsInveCustomerCard.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit());
        wmsInveCustomerCard.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city());
        wmsInveCustomerCard.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro());
        wmsInveCustomerCard.setCard_no(wmsInveTransaProd.getCard_no());
        wmsInveCustomerCard.setCard_owner_name(wmsInveTransaProd.getCard_owner_name());
        wmsInveCustomerCard.setId_card(wmsInveTransa.getId_card());
        wmsInveCustomerCard.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveCustomerCard.setCreate_user_id(user.getUserId());
        Map<String, Object> customerMap = wmsInveCustomerCardService.newAddIncomeCard(user, wmsInveCustomerCard);
        
        // 保存上单产品信息
        wmsInveTransaProd = wmsInveTransaService.setWmsInveTransaProdForAdd(wmsInveTransaProd, wmsInveTransa, user, sysTime);
        wmsInveTransaProd.setWms_inve_customer_card_id((Integer)customerMap.get("wms_inve_customer_card_id"));
        
        wmsInveTransaProdDao.save(wmsInveTransaProd);
        int count = findClerkDataBySalemanCount(user);
        WmsInveClerkData wmsInveClerkData = setWmsInveClerkData(wmsInveTransa, wmsInveTransaProd, wmsInveCustomer, user);
        wmsInveClerkDataDao.save(wmsInveClerkData);


        // 调用流程(公用)
        WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        iWmsInveWorkFlowService.startFinancialWorkFlow(user.getUserId().toString(), wmsInveTransa.getWms_inve_transa_id().toString(),
                                                       wmsInveTransa.getSalesman_dept_id().toString(), wmsInveTransa.getSalesman_id().toString(),
                                                       WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        
        resultMap.put("ret_code", "000");
        resultMap.put("ret_msg", "尊敬的客户您好:您的前方正有" + count + "个客户等待办理业务,请关注窗口信息,过号则会重排");
        return resultMap;
    }

    /**
     * @Title: verificationCustomerInfo
     * @Description: 对签单的客户信息进行二次验证
     * @param info 客户信息
     * @param personnel_shortCode 业务员短工号 
     * @return 返回map集合
     * @author: DongHao
     * @time:2017年2月23日 下午2:51:00
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#verificationCustomerInfo(com.zx.emanage.util.gen.entity.CrmCustomerInfo)
     * history:
     * 1、2017年2月23日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> verificationCustomerInfo(CrmCustomerInfo info, String personnel_shortCode)
    {

        // 定义返回map参数
        Map<String, Object> resMap = new HashMap<String, Object>();

        /**********(1)本地对身份证号进行验证**************/

        resMap = localVerificationIdCard(info);

        // 判断本地验证有效证件重复是否通过, 如果有效证件验证重复通过, 则调用crm进行联系电话验证
        if ("000".equals((String) resMap.get("ret_code")))
        {

            /**********(2)调用crm联系电话进行验证************/
            resMap = toCrmVerificationContactNumber(info, personnel_shortCode);
        }
        return resMap;
    }


    /**
     * @Title: toCrmVerificationContactNumber
     * @Description: 调用crm系统进行验证联系电话是否可用
     * @param info 客户信息
     * @param personnel_shortCode 业务员短工号
     * @return  返回map提示信息 
     * @author: DongHao
     * @time:2017年12月21日 上午6:13:42
     * history:
     * 1、2017年12月21日 DongHao 创建方法
    */
    private Map<String, Object> toCrmVerificationContactNumber(CrmCustomerInfo info, String personnel_shortCode)
    {
        // 定义返回结果集合
        Map<String, Object> res_map = new HashMap<String, Object>();

        // 调用crm接口
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_JudgeContactNumber"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        nvps.add(new BasicNameValuePair("costomer_id", info.getCostomer_id() != null ? info.getCostomer_id().toString() : null));
        nvps.add(new BasicNameValuePair("contact_number", info.getContact_number()));
        nvps.add(new BasicNameValuePair("id_card_number", info.getId_card_number()));

        String personnel_id = "";
        // 判断业务员id为空时(新增客户时有可能为空)
        if (info.getPersonnel_id() == null)
        {
            PmPersonnel pmPersonnel = pmPersonnelDao.getPersonnelByShortCode(personnel_shortCode);
            personnel_id = pmPersonnel != null ? pmPersonnel.getPersonnel_id().toString() : "";
        }
        else
        {
            personnel_id = info.getPersonnel_id() != null ? info.getPersonnel_id().toString() : "";
        }
        nvps.add(new BasicNameValuePair("personnel_id", personnel_id));

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
                res_map.put("ret_code", "000");
                res_map.put("ret_msg", "成功");
            }
            else
            {
                res_map.put("ret_code", "001");// 提示信息：请使用该手机号重新查询，获取客户信息。
                res_map.put("ret_msg", "请使用该联系电话重新查询，获取客户信息。");
            }

            response.close();
            httpclient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res_map;
    }

    /**
     * @Title: localVerificationIdCard
     * @Description: 在本系统中对签单客户的有效证件号进行验证
     * @param info
     * @return 
     * @author: DongHao
     * @time:2017年12月21日 上午5:19:44
     * history:
     * 1、2017年12月21日 DongHao 创建方法
    */
    @SuppressWarnings("null")
    private Map<String, Object> localVerificationIdCard(CrmCustomerInfo info)
    {
        // 定义返回信息提示的map集合
        Map<String, Object> res_map = new HashMap<String, Object>();

        // 1 根据crm客户id进行本地查询
        List<Map<String, Object>> crmLis = wmsInveSignedApplicationDao.getLocalCustomerInfosByCostomerId(info.getCostomer_id());

        // 判断通过客户id进行获取客户的签单信息是否存在,不存在则通过客户有效证件号进行查询
        if (crmLis == null || crmLis.size() == 0)
        {

            /* (1) 通过crm客户id没有获取客户的签单信息,则使用客户的有效证件号进行查询*/
            crmLis = wmsInveSignedApplicationDao.getLocalCustomerInfosByIdCard(info.getId_card_number().trim());

            // 判断通过客户有效证件号没有获取到客户签单信息, 否则对获取的客户签单信息进行遍历
            if (crmLis != null || crmLis.size() != 0)
            {

                /*定义boolean类型的标记变量, bool == true 说明存在crm客户id与获取的客户id不一致, bool == false 说明不存在客户id不一致的数据*/
                boolean bool = false;

                // 对获取的客户信息进行遍历
                for (Map<String, Object> map : crmLis)
                {

                    Integer costomer_id = (Integer) map.get("costomer_id");

                    String data_status = (String) map.get("data_status");

                    // 判断客户id(crm客户id)是否一致
                    if (!costomer_id.equals(info.getCostomer_id()))
                    {
                        // 判断单据状态是否为待支付、待签约、待审核、待修订状态
                        if (data_status.equals("2") || data_status.equals("11") || data_status.equals("12") || data_status.equals("13")
                            || data_status.equals("17") || data_status.equals("19") || data_status.equals("20"))
                        {
                            res_map.put("ret_code", "001");
                            res_map.put("ret_msg", "该有效证件所指客户签单进行中,因此无法使用该有效证件号,绑定其他客户,请知悉");
                            break;
                        }
                        else
                        {
                            bool = true;// 存在不一致的情况设置bool为true
                            break;
                        }
                    }
                }

                // 判断bool == true 说明存在客户id不一致的数据
                if (bool)
                {
                    res_map.put("ret_code", "001");
                    res_map.put("ret_msg", "请使用有效证件重新查询,获取客户信息");
                }

                // 判断bool为false时说明不存在不一致的数据, res_map的size==0时说明不存在不一致的数据
                if (!bool && res_map.size() == 0)
                {
                    res_map.put("ret_code", "000");
                    res_map.put("ret_msg", "验证通过");
                }
            }
            else
            {
                res_map.put("ret_code", "000");
                res_map.put("ret_msg", "验证通过");
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

                // 判断单据状态是否为空
                if (map.get("data_status") != null && !"".equals((String) map.get("data_status")))
                {

                    // 判断客户有效证件是否存在不一致的情况
                    if (!id_card.toLowerCase().equals(info.getId_card_number().toLowerCase()))
                    {
                        bool = true;// 存在不一致的情况设置bool为true
                        break;
                    }
                }
            }

            // 判断bool == true 说明存在有效证件不一致的数据
            if (bool)
            {
                res_map.put("ret_code", "001");
                res_map.put("ret_msg", "请使用有效证件重新查询,获取客户信息");
            }

            // 判断bool为false时说明不存在不一致的数据, res_map的size==0时说明不存在不一致的数据
            if (!bool && res_map.size() == 0)
            {
                res_map.put("ret_code", "000");
                res_map.put("ret_msg", "验证通过");
            }
        }

        return res_map;
    }

    /**
     * @Title: creditHandler
     * @Description: 待修订状态的单据债权处理
     * @param wmsInveTransa 上单对象
     * @param wmsInveTransaProd 上单产品对象
     * @param beanVO 页面参数信息对象
     * @param user 用户对象
     * @return 返回错误处理信息
     * @author: DongHao
     * @throws Exception 
     * @time:2017年2月24日 下午6:09:37
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#creditHandler(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO)
     * history:
     * 1、2017年2月24日 DongHao 创建方法
    */
    @Override
    public String creditHandler(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveTransaSearchBeanVO beanVO, UserBean user)
                                                                                                                                                  throws Exception
    {
        // 定义时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 根据上单表主键获取单据信息
        Map<String, Object> newWmsInveTransaMap = wmsInveSignedApplicationDao.getWmsInveTransaInfosByWmsInveTransaId(wmsInveTransa.getWms_inve_transa_id()
                                                                                                                                  .toString());
        // 根据上单表主键获取柜员协议表中的单据信息
        WmsInveClerkProtocol protocol = wmsInveClerkProtocolDao.findWmsInveClerkProtocolByInveTransaId(wmsInveTransa.getWms_inve_transa_id());

        // 定义柜员协议表主键id等于空
        Integer wmsInveClerkProtocolId = null;

        // 判断获取的柜员协议表信息是否为空
        if (protocol != null)
        {
            // 柜员协议表信息不为空,则为定义的柜员协议表主键进行赋值
            wmsInveClerkProtocolId = protocol.getWms_inve_clerk_protocol_id();
        }

        // 释放单据的债权信息(根据上单表主键、柜员协议表主键、当前登录的用户)
        CreditBusiness.getInstance().releaseMatchedCreditForTransaFlow(wmsInveTransa.getWms_inve_transa_id(), wmsInveClerkProtocolId, user);

        // 定义实际支付时间
        Date actDate = null;

        // 判断单据信息中实际支付日期是否为空
        if (newWmsInveTransaMap.get("date_of_act") != null)
        {
            // 单据已经存在实际支付日期则为定义的实际支付时间进行赋值
            actDate = format.parse(newWmsInveTransaMap.get("date_of_act").toString());

        }
        else
        {
            // 如果实际支付日期为空,则获取当前系统时间
            actDate = new Date();
        }

        // 对单据进行债权匹配
        boolean bool = CreditBusiness.getInstance().matchHold(wmsInveTransa.getWms_inve_transa_id(),
                                                              wmsInveTransaProd.getWms_inve_pruduct_category_id(),
                                                              wmsInveTransaProd.getProduct_account(), actDate, user);

        // 生效债权
        CreditBusiness.getInstance().effectMatchedCredit(wmsInveTransa.getWms_inve_transa_id(), user);

        // 判断债权匹配是否成功,债权匹配成功bool==true否则bool==false
        if (bool)
        {
            return "success";
        }
        else
        {
            return "error";
        }
    }

    /**
     * @Title: verificationSignedInfo
     * @Description: 验证pad客户端提交的签单信息是否正确
     * @param wmsInveTransa 上单信息
     * @param wmsInveTransaProd 上单产品信息
     * @param wmsCustomer 上单客户信息
     * @param beanVO 上单信息
     * @return 返回Map集合验证结果信息
     * @author: DongHao
     * @time:2017年3月1日 上午10:56:58
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#verificationSignedInfo(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd, com.zx.emanage.util.gen.entity.WmsInveCustomer, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO)
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> verificationSignedInfo(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd, WmsInveCustomer wmsCustomer,
                                                      WmsInveTransaSearchBeanVO beanVO)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 判断上单对象是否为空
        if (wmsInveTransa != null)
        {
            // 判断客户姓名是否存在
            if (wmsInveTransa.getCus_name() == null || "".equals(wmsInveTransa.getCus_name()))
            {
                resMap.put("ret_code", "109");
                resMap.put("ret_msg", "客户姓名不允许为空!");
                return resMap;
            }
            // 判断客户电话是否为空
            if (wmsInveTransa.getMobile_phone() == null || "".equals(wmsInveTransa.getMobile_phone()))
            {
                resMap.put("ret_code", "110");
                resMap.put("ret_msg", "客户联系电话不允许为空!");
                return resMap;
            }
            // 判断设置电邮地址为默认地址
            if ("1".equals(wmsInveTransa.getBill_send_mode()))
            {
                // 判断电邮地址是否为空
                if (wmsInveTransa.getCustomer_email() == null || "".equals(wmsInveTransa.getCustomer_email()))
                {
                    resMap.put("ret_code", "122");
                    resMap.put("ret_msg", "电邮地址已设为默认发送地址,电邮地址不允许为空!");
                    return resMap;
                }
            }
            // 判断客户有效证件是否为空
            if (wmsInveTransa.getId_card() == null || "".equals(wmsInveTransa.getId_card()))
            {
                resMap.put("ret_code", "111");
                resMap.put("ret_msg", "客户有效证件不允许为空!");
                return resMap;
            }
            // 判断客户通讯地址是否为空
            if (wmsInveTransa.getContact_address() == null || "".equals(wmsInveTransa.getContact_address()))
            {
                resMap.put("ret_code", "112");
                resMap.put("ret_msg", "客户通讯地址不允许为空!");
                return resMap;
            }
        }
        else
        {
            resMap.put("ret_code", "121");
            resMap.put("ret_msg", "请完善签单信息!");
            return resMap;
        }
        if (wmsInveTransaProd != null)
        {
            // 判断上单产品投资金额是否为空
            if (wmsInveTransaProd.getProduct_account() == null || wmsInveTransaProd.getProduct_account().compareTo(BigDecimal.ZERO) == 0)
            {
                resMap.put("ret_code", "113");
                resMap.put("ret_msg", "投资金额不允许为空或者为0!");
                return resMap;
            }
            // 判断产品id是否为空
            if (wmsInveTransaProd.getWms_inve_pruduct_category_id() == null)
            {
                resMap.put("ret_code", "114");
                resMap.put("ret_msg", "请选择上单产品!");
                return resMap;
            }
            // 判断收益卡卡主姓名是否为空
            if (wmsInveTransaProd.getCard_owner_name() == null || "".equals(wmsInveTransaProd.getCard_owner_name()))
            {
                resMap.put("ret_code", "115");
                resMap.put("ret_msg", "客户收益卡卡主姓名不允许为空!");
                return resMap;
            }
            // 判断收益卡卡号是否为空
            if (wmsInveTransaProd.getCard_no() == null || "".equals(wmsInveTransaProd.getCard_no()))
            {
                resMap.put("ret_code", "116");
                resMap.put("ret_msg", "客户收益卡卡号不允许为空!");
                return resMap;
            }
            // 判断收益卡银行是否为空
            if (wmsInveTransaProd.getBank_of_deposit_code() == null || "".equals(wmsInveTransaProd.getBank_of_deposit_code()))
            {
                resMap.put("ret_code", "117");
                resMap.put("ret_msg", "请选择客户收益卡所属银行!");
                return resMap;
            }
            else
            {
                String str = "邮政储蓄";
                boolean flag = wmsInveTransaProd.getBank_of_deposit_code().contains(str);
                if (flag)
                {
                    resMap.put("ret_code", "117");
                    resMap.put("ret_msg", "暂不支持邮政储蓄银行卡,请更换其它银行卡!");
                    return resMap;
                }
            }
            // 判断收益卡所属省是否为空
            if (wmsInveTransaProd.getBank_of_deposit_pro_code() == null || "".equals(wmsInveTransaProd.getBank_of_deposit_pro_code()))
            {
                resMap.put("ret_code", "118");
                resMap.put("ret_msg", "请选择客户收益卡所属省份!");
                return resMap;
            }
            // 判断收益卡所属市是否为空
            if (wmsInveTransaProd.getBank_of_deposit_city_code() == null || "".equals(wmsInveTransaProd.getBank_of_deposit_city_code()))
            {
                resMap.put("ret_code", "119");
                resMap.put("ret_msg", "请选择客户收益卡所属市!");
                return resMap;
            }
            // 判断收益卡所属支行是否为空
            if (wmsInveTransaProd.getBank_branch() == null || "".equals(wmsInveTransaProd.getBank_branch()))
            {
                resMap.put("ret_code", "120");
                resMap.put("ret_msg", "客户收益卡所属支行信息不允许为空!");
                return resMap;
            }
        }
        else
        {
            resMap.put("ret_code", "121");
            resMap.put("ret_msg", "请完善签单信息!");
            return resMap;
        }
        // 判断是否存在验证信息
        if (resMap.size() == 0)
        {
            resMap.put("ret_code", "000");
            resMap.put("ret_msg", "验证通过!");
            return resMap;
        }
        return resMap;
    }
    

    
    // /**
    // * @Title: saveShareHolderSignedApplicationInfo
    // * @Description: 保存股东签单信息
    // * @param transa 签单信息
    // * @param transaProd 签单产品信息
    // * @param customer 客户信息
    // * @param salesman_commission_coefficient 客户经理提点系数
    // * @param dept_manager_commission_coefficient 部门经理提点系数
    // * @param bean 签单查询信息
    // * @param user 登录用户信息
    // * @return 成功或失败标志
    // * @author: jinzhm
    // * @time:2017年6月5日 下午3:14:47
    // * @see
    // com.zx.emanage.inve.service.IWmsInveSignedApplicationService#saveShareHolderSignedApplicationInfo(com.zx.emanage.util.gen.entity.WmsInveTransa,
    // com.zx.emanage.util.gen.entity.WmsInveTransaProd,
    // com.zx.emanage.util.gen.entity.WmsInveCustomer, java.math.BigDecimal,
    // java.math.BigDecimal, com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO,
    // com.zx.sframe.util.vo.UserBean)
    // * history:
    // * 1、2017年6月5日 jinzhm 创建方法
    // */
    // @Transactional
    // @Override
    // public String saveShareHolderSignedApplicationInfo(WmsInveTransa transa,
    // WmsInveTransaProd transaProd,
    // WmsInveCustomer customer,
    // BigDecimal salesman_commission_coefficient,
    // BigDecimal dept_manager_commission_coefficient,
    // WmsInveTransaSearchBeanVO bean, UserBean user)
    // {
    // // 封装签单数据
    // SignTransaData transaData = new SignTransaData();
    // transaData.setTransa(transa);
    // transaData.setTransaProd(transaProd);
    // transaData.setCustomer(customer);
    // transaData.setTransaBeanVO(bean);
    // transaData.setUser(user);
    // transaData.setTransa_type(SignHelper.SHARE_HOLDER_TRANSA);
    // transaData.setSalesman_commission_coefficient(salesman_commission_coefficient
    // == null ? BigDecimal.ZERO
    // : salesman_commission_coefficient);
    // transaData.setDept_manager_commission_coefficient(dept_manager_commission_coefficient
    // == null ? BigDecimal.ZERO
    // : dept_manager_commission_coefficient);
    // // 签单
    // return
    // SignTransaFactory.getSignApplication(SignHelper.SHARE_HOLDER_TRANSA).transa(transaData);
    // }

    /**
     * @Title: generatePTPTransa
     * @Description: 生成ptp单据信息
     * @param param 投资信息
     * @return 生成ptp单据后返回ptp和wms的单据对应情况集合
     * @author: jinzhm
     * @time:2017年6月20日 上午10:33:00
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#generatePTPTransa(java.lang.String, java.math.BigDecimal, java.util.Date, java.lang.String)
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
    */
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Map<String, Object>> generatePTPTransa(String param)
    {
        // 定义调用生成签单所需要的签单数据对象集合
        List<SignTransaData> transaDataList = new ArrayList<SignTransaData>();
        
        // 将传过来的签单信息字符串转成list结构
        List<Map<String, Object>> paramList = new Gson().fromJson(param, ArrayList.class);
        
        if(!paramList.isEmpty())
        {
            // 设置默认为null的签单数据对象
            SignTransaData transaData = null;

            // 循环要生成单据信息的所有ptp签单信息
            for (Map<String, Object> map : paramList)
            {
                // 投资日期
                Date dateOfPayment = map.get("date_of_payment") == null ? new Date()
                                                                       : DateUtil.strDate(String.valueOf(map.get("date_of_payment")),
                                                                                          null);
                // 理财产品id
                Integer wmsInveProductId = new Double(String.valueOf(map.get("wms_inve_product_id"))).intValue();
                // 产品利率
                BigDecimal productInterest = new BigDecimal(String.valueOf(map.get("product_interest")));

                transaData = new SignTransaData();
                // 单据类型为ptp单据
                transaData.setTransa_type(SignHelper.PTP_TRANSA);
                // ptp投资主键
                transaData.setPtp_user_invest_id(new Double(String.valueOf(map.get("ptp_user_invest_id"))).intValue());

                // 设置单据信息
                transaData.setTransa(new WmsInveTransa());
                // 身份证号
                transaData.getTransa().setId_card(String.valueOf(map.get("id_card")).toUpperCase());
                // 设置归属业务员id
                transaData.getTransa()
                          .setBel_salesman_id_id(new Double(String.valueOf(map.get("bel_salesman_id"))).intValue());
                // 设置投资金额
                transaData.getTransa()
                          .setProduct_total_count_lower(new BigDecimal(String.valueOf(map.get("product_account"))));
                // 设置签单时间
                transaData.getTransa().setDate_of_payment(new java.sql.Date(dateOfPayment.getTime()));

                // 设置上单产品对象
                transaData.setTransaProd(new WmsInveTransaProd());
                // 设置购买产品主键
                transaData.getTransaProd().setWms_inve_pruduct_category_id(wmsInveProductId);
                // 设置产品年华收益率
                transaData.getTransaProd().setProduct_interest(productInterest.toString());
                // 设置产品投资金额
                transaData.getTransaProd()
                          .setProduct_account(new BigDecimal(String.valueOf(map.get("product_account"))));
                // 设置产品原始投资金额
                transaData.getTransaProd()
                          .setOrg_product_account(new BigDecimal(String.valueOf(map.get("product_account"))));

                // 设置登录用户id
                transaData.setUser(new UserBean());
                transaData.getUser().setUserId(new Double(String.valueOf(map.get("bel_salesman_id"))).intValue());

                // 保存集合中
                transaDataList.add(transaData);
            }
        }
        
        List<Map<String, Object>> resMapList = SignTransaFactory.getSignApplication(SignHelper.PTP_TRANSA)
                                                                .transa(transaDataList);

        // if (true)
        // {
        // throw new RuntimeException("回滚");
        // }

        return resMapList;
    }

    /**
     * 
     * @Title: verifyIsExistRenewalPrincipal
     * @Description: 验证客户是否存在可用的续期本金的单据
     * @param costomerId 客户id
     * @param product_account 投资金额
     * @return 返回布尔类型的值(存在可以使用的续期单据返回true,否则返回false)
     * @author: DongHao
     * @time:2017年4月6日 下午3:52:14
     * history:
     * 1、2017年4月6日 DongHao 创建方法
     */
    public boolean verifyIsExistRenewalPrincipal(Integer costomerId, BigDecimal product_account)
    {
        //定义查询参数集合
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        
        //定必布尔类型的变量用于标记该上单客户是否存可使用的续期本金单据(存在并且可以用的续期本金的金额大于投资金额返回true否则返回false)
        boolean bool = true;
        
        //判断传入的crm客户id是否为空
        if(costomerId != null)
        {
            //根据crm客户id获取wms_inve_customer_id
            Integer wms_inve_customer_id = wmsinvetransaDao.findWmsInveCustomerIdByCostomerId(costomerId);
            
            //判断获取的wms_inve_customer_id是否为空
            if(wms_inve_customer_id != null)
            {
                paramsMap.put("wms_inve_customer_id", wms_inve_customer_id);
                
                /**
                 * 根据查询条件获取当前客户的续期本金使用情况
                 * (情况分为三种:1. 当前客户所具备的全部续期本金金额, 2. 待支付状态并且标记为续期本金单据的使用的续期本金金额, 3. 已占用的续期本金金额)
                 */
                Map<String, Object> customerExtendInfo = wmsinvetransaDao.getCustomerExtendBillInfo(paramsMap);
                
                //1.当前客户所具备的前部续期本金金额
                BigDecimal total_principal_amount = ((BigDecimal)customerExtendInfo.get("total_principal_amount")).abs();
                
                //2.当前客户已经占用的续期本金
                BigDecimal used_income_amount = ((BigDecimal)customerExtendInfo.get("used_income_amount")).abs();
                
                //3.待支付状态的已标记为使用续期的本金的金额
                BigDecimal extend_amount = ((BigDecimal)customerExtendInfo.get("extend_amount")).abs();
                
                //4.使用总的续期本金金额 - 已占用的续期本金 - 待支付状态的使用的续期本金金额 = 可以使用的续期本金
                BigDecimal value = total_principal_amount.subtract(used_income_amount).subtract(extend_amount);
                
                //判断得到的可使用的续期本金金额小于等于0
                if(value.compareTo(BigDecimal.ZERO) != 1)
                {
                	bool = false;//可以使用的续期本金小于等于0则设置成false
                }
                
            }
            else
            {
                bool = false;//wms_inve_customer_id不存在时设置不存在可以使用的续期本金单据
            }
        }
        else
        {
            bool = false;//当crm客户id为空时,设置不存在续期本金单据
        }
        return bool;
    }
    
    /**
     * 
     * @Title: isVerifyCategory
     * @Description: 验证购买的产品是否满足要求
     * @param wmsInveTransa 上单信息对象
     * @param wmsInveTransaProd 上单产品对象
     * @return 返回错误提示信息
     * @author: DongHao
     * @time:2017年9月20日 下午1:26:31
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#isVerifyCategory(com.zx.emanage.util.gen.entity.WmsInveTransa, com.zx.emanage.util.gen.entity.WmsInveTransaProd)
     * history:
     * 1、2017年9月20日 DongHao 创建方法
     */
    public Map<String, Object> isVerifyCategory(WmsInveTransa wmsInveTransa, WmsInveTransaProd wmsInveTransaProd)
    {
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        paramMap.put("id_card", wmsInveTransa.getId_card());
        paramMap.put("wms_inve_pruduct_category_id", wmsInveTransaProd.getWms_inve_pruduct_category_id());

        /**********设置签单信息***********/
        wmsInveTransa.setProduct_total_count_upper(digitUpperUtil.digitUppercase(((BigDecimal) wmsInveTransaProd.getProduct_account()).doubleValue(),
                                                                                 true).replaceAll("元", ""));// 设置上单中的投资金额大写
        // 设置产品的投资金额和产品期限
        wmsInveTransaProd.setProduct_deadline(wmsInvePruductCategory.getCategory_deadline());// 设置产品期限
        wmsInveTransaProd.setProduct_account(wmsInveTransaProd.getProduct_account().multiply(new BigDecimal(10000)));// 设置投资金额
        wmsInveTransaProd.setCategory_name(wmsInvePruductCategory.getCategory_name());// 设置产品名称
        wmsInveTransaProd.setProduct_interest(wmsInvePruductCategory.getCategory_return_rate().toString());
        wmsInveTransa.setProduct_total_count_lower(wmsInveTransaProd.getProduct_account());// 设置上单表中的投资金额小写

        if (wmsInveTransaProdDao.calRecordNum(paramMap) > 0)
        {// 是追单
            if (wmsInvePruductCategory.getCategory_additional_monery_min() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_additional_monery_min().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "100");
                resultMap.put("ret_msg", "追单金额不能低于最小值");
            }
            if (wmsInvePruductCategory.getCategory_additional_monery_max() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_additional_monery_max().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "101");
                resultMap.put("ret_msg", "追单金额不能超过最大值");
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
                                                                                                          : product.get("product_account").toString());
                }
                if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                    && sumZdje > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
                {
                    resultMap.put("ret_code", "102");
                    resultMap.put("ret_msg", "追单金额总和不能超过产品最大持有金额 ");
                }
            }
        }
        else
        {// 是上单
            if (wmsInvePruductCategory.getCategory_investment_money_min() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() < wmsInvePruductCategory.getCategory_investment_money_min().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "103");
                resultMap.put("ret_msg", "上单金额不能低于最小值 ");
            }
            if (wmsInvePruductCategory.getCategory_investment_money_max() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getCategory_investment_money_max().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "104");
                resultMap.put("ret_msg", "上单金额不能超过最大值 ");
            }
            if (wmsInvePruductCategory.getMaximum_holding_amount() != null
                && wmsInveTransaProd.getProduct_account().doubleValue() > wmsInvePruductCategory.getMaximum_holding_amount().doubleValue() * 10000)
            {
                resultMap.put("ret_code", "106");
                resultMap.put("ret_msg", "上单金额不能超过产品最大持有金额 ");
            }
        }
        
        if(resultMap.size() == 0)
        {
            resultMap.put("ret_code", "000");
            resultMap.put("ret_msg", "验证通过");
        }
        
        return resultMap;
    }

    /**
     * 
     * @Title: verifyCustomerIsBuy
     * @Description: 验证客户多购买的产品是否瞒住购买的要求
     * @param vo 客户购买的产品信息对象
     * @return 允许购买返回map集合信息对象
     * @author: DongHao
     * @time:2017年7月18日 下午1:54:07
     * @see com.zx.emanage.inve.service.IWmsInveSignedApplicationService#verifyCustomerIsBuy(com.zx.emanage.inve.vo.WmsInveVerifyCustomerSignedVo)
     * history:
     * 1、2017年7月18日 DongHao 创建方法
     */
    @Override
    public Map<String, Object> verifyCustomerIsBuy(WmsInveVerifyCustomerSignedVo vo)
    {
        
        //自定义map结果集合
        Map<String, Object> res_map = new HashMap<String, Object>();
        
        //判断验证客户购买产品信息对象是否为空
        if(vo != null)
        {
            //定义id集合
            List<String> ids = new ArrayList<String>();
            
            //判断产品id是否为空
            if(vo.getWms_inve_pruduct_category_id() != null && !"".equals(vo.getWms_inve_pruduct_category_id()))
            {
                ids = Arrays.asList(vo.getWms_inve_pruduct_category_id().split(","));
            }
            
            //根据产品表主键id集合进行获取产品
            List<WmsInvePruductCategory> categoryLis = wmsInveSignedApplicationDao.getWmsInvePruductCategoryByIds(ids);
            
            //定义变量用于存放不满足购买要求的产品名称
            String category_name = "";
            
            //便利获取到的产品
            for(WmsInvePruductCategory category : categoryLis)
            {
                    
                //定义查询客户存在的单据查询条件
                Map<String, Object> paramsMap = new HashMap<String, Object>();
                
                //定义变量来表示客户满足条件的单据存在的数量
                int count = 0;
                
                //判断验证对象中的crm客户id是否为空,如果不为空则根据crm客户id进行验证,否则根据客户的身份证号进行验证
                // if(vo.getCostomer_id() != null)
                // {
                // paramsMap.put("costomer_id", vo.getCostomer_id());
                // }
                // else
                // {
                paramsMap.put("id_card_number", vo.getId_card_number());
                // }
                
                //根据产品表主键到限制可以购买的新产品的表中进行查询,查询到数据则需要进行限制,否则不需要进行限制
                int categoryCount = wmsInveSignedApplicationDao.getNewCategory(category.getWms_inve_pruduct_category_id());
                
                //判断获取到的记录数量是否大于0,大于0该产品需要进行限制验证
                if(categoryCount > 0)
                {
                    paramsMap.put("wms_inve_pruduct_category_id", category.getWms_inve_pruduct_category_id());
                    count = wmsInveSignedApplicationDao.getNewCategoryLimitCustomer(paramsMap);
                }
                else
                {
                    count = 1;
                }
                
                //判断客户在系统中存在的满足条件的记录数位0是则不允许购买该产品
                if(count == 0)
                {
                    category_name += category.getCategory_name() + ",";
                }
            }
            
            //判断不满足要求的产品名称是否为空
            if("".equals(category_name))
            {
                res_map.put("ret_code", "000");
                res_map.put("ret_msg", "操作成功");
                res_map.put("is_buy", 1);
            }
            else
            {
                category_name = category_name.substring(0, category_name.length() - 1);
                res_map.put("ret_code", "000");
                res_map.put("ret_msg", "对不起，您不符合购买"+category_name+"产品的条件，请更换其他产品购买");
                res_map.put("is_buy", 0);
            }
            
        }
        return res_map;
    }
}
