package com.zx.emanage.inve.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveCreditEmailAttDao;
import com.zx.emanage.inve.persist.WmsInveCreditEmailDao;
import com.zx.emanage.inve.persist.WmsInveExtendInfoDao;
import com.zx.emanage.inve.persist.WmsInveProtocolRenewalDao;
import com.zx.emanage.inve.persist.WmsInveProtocolRenewalLogDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveCustomerCardService;
import com.zx.emanage.inve.service.IWmsInveExtendService;
import com.zx.emanage.inve.service.IWmsInveRedeemService;
import com.zx.emanage.inve.service.IWmsInveTransaProtocolService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveCreditEmail;
import com.zx.emanage.util.gen.entity.WmsInveCreditEmailAtt;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInveExtendInfo;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewal;
import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewalLog;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveExtendServiceImpl
 * 模块名称：理财续期
 * @Description: 内容摘要：理财续期的相关功能
 * @author Guanxu
 * @date 2016年11月27日
 * @version V1.0
 * history:
 * 1、2016年11月27日 Guanxu 创建文件
 */
@Service("wmsInveExtendService")
public class WmsInveExtendServiceImpl implements IWmsInveExtendService
{

    @Autowired
    private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsInveTransaCardDao wmsInveTransaCardDao;

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;

    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;

    @Autowired
    private WmsInveTransaIncomeDao wmsInveTransaIncomeDao;

    @Autowired
    private WmsInveTransaLogDao wmsInveTransaLogDao;

    @Autowired
    private WmsInvePruductYearPaySpecialDao wmsInvePruductYearPaySpecialDao;

    @Autowired
    private WmsInveProtocolRenewalDao wmsinveprotocolrenewalDao;

    @Autowired
    private WmsInveProtocolRenewalLogDao wmsInveProtocolRenewalLogDao;

    @Autowired
    private WmsInveRedeemDao wmsinveredeemDao;

    @Autowired
    private WmsInveRedeemDetailDao wmsinveredeemdetailDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private IWmsInveTransaProtocolService wmsInveTransaProtocolService;

    @Autowired
    private WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao;

    @Autowired
    private WmsInveClerkProtocolDao wmsInveClerkProtocolDao;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;

    @Autowired
    private WmsInveExtendInfoDao wmsInveExtendInfoDao;

    @Autowired
    private WmsInveCreditEmailDao wmsInveCreditEmailDao;

    @Autowired
    private WmsInveCreditEmailAttDao wmsInveCreditEmailAttDao;
    
    @Autowired
    private IWmsInveCustomerCardService wmsInveCustomerCardService;

    /**
     * 赎回service
     */
    @Autowired
    private IWmsInveRedeemService WmsInveRedeemServiceImpl;

    @Autowired
    private IWmsInveClerkProtocolService wmsInveClerkProtocolService;

    /**
      * @Title inveInveExtendSave
      * @Description 内容摘要:添加续期信息
      * @param wms_inve_transa_id 单据id
      * @param date_of_payment 续期时间
      * @param is_order_redeem 是否预约赎回
      * @param order_financial_bill_code 预约续期合同编号
      * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
      * @param protocol_type 债权协议类型(1表示字纸债权;2表示为电子债权)
      * @param new_customer_email 邮箱地址
      * @param user 用户信息
      * @param wmsInveCustomerCard 收益卡信息表
      * @author DongHao
      * @Time 2016年11月14日14:23:26
      * history 1. 2016年11月14日14:23:31 DongHao 修改方法(修改方法添加是否预约赎回参数)
      * 2. 2016年12月28日16:03:33 DongHao 修改方法    在方法中增加String类型的参数 bill_type
      */
    @Override
    @Transactional
    public void inveInveExtendSave(WmsInveTransaProd wmsInveTransaProd, Integer wms_inve_transa_id, Date date_of_payment, Date date_of_end,
                                   Integer is_order_redeem, String bill_type, String protocol_type, String new_customer_email, UserBean user,
                                   WmsInveCustomerCard wmsInveCustomerCard)
    {

        // 通过原始单据进行验证是否为股东单据(股东单据不生成台账),验证单据不是股东单据返回true,是股东单据返回false
        boolean isShareholderBill = verifyIsShareholderBill(wms_inve_transa_id);

        // 柜员协议对象
        WmsInveClerkProtocol wmsInveClerkProtocol = null;
        // 柜员业务对象
        WmsInveClerkData wmsInveClerkData = null;

        if (wmsInveTransaProd != null)
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
            
            // 续期之前需要更新enable_flag = 0
            upateWmsInveExtendInfoByWmsInveTransaId(wms_inve_transa_id, user);
            
            //收益卡信息表不为空时进行添加收益卡信息
            if(wmsInveCustomerCard != null)
            {
              //判断银行卡所属银行是否为空
                if(wmsInveCustomerCard.getBank_of_deposit() != null && !"".equals(wmsInveCustomerCard.getBank_of_deposit()))
                {
                    if(wmsInveCustomerCard.getBank_of_deposit().contains("广东发展银行"))
                    {
                        wmsInveCustomerCard.setBank_of_deposit("广发银行");
                    }
                    else if(wmsInveCustomerCard.getBank_of_deposit().contains("中国光大银行"))
                    {
                        wmsInveCustomerCard.setBank_of_deposit("光大银行");
                    }
                }
                wmsInveCustomerCardService.newAddIncomeCard(user, wmsInveCustomerCard);
            }
            
            // 添加续期收益卡信息
            WmsInveExtendInfo info = setWmsInveExtendInfo(wmsInveTransaProd, wms_inve_transa_id, protocol_type, user);
            
            wmsInveExtendInfoDao.save(info);
                
            //wmsInveTransaProdDao.update(wmsInveTransaProd);
        }

        // 判断是否是预约赎回的单据,如果是预约赎回的单据并且is_order_redeem==1 则调用财务作废赎回的方法
        if (is_order_redeem == 1)
        {
            WmsInveDebtWorkFlowVO vo = new WmsInveDebtWorkFlowVO();
            String redeem_id = wmsInveTransaDao.getRedeemDataId(wms_inve_transa_id);
            vo.setBusinessId(redeem_id);
            vo.setWms_inve_redeem_id(new Integer(redeem_id));
            vo.setAdvice("预约预期作废");
            WmsInveRedeemServiceImpl.cancelWmsInveRedeemByCw(user, vo);
            wmsInveTransaDao.updateIsOrderRedeemByWmsInveTransaId(wms_inve_transa_id);
        }
        // 符合要求的协议失效 后台自动变成失效标识 wms_inve_redeem_id=-5
        WmsInveTransaProtocol wProtocol = new WmsInveTransaProtocol();
        Map<String, Object> map = new HashMap<>();
        map.put("wms_inve_transa_id", wms_inve_transa_id);
        map.put("data_status", "4");
        Map<String, Object> transaMap = wmsInveTransaDao.getProtocolRenewalnew(map);
        // 原始协议表主键
        if (transaMap == null)
        {
            System.out.print("===========" + wms_inve_transa_id);
            return;
        }
        Integer wms_inve_transa_protocol_id = (Integer) transaMap.get("wms_inve_transa_protocol_id");
        // 原始客户信息表主键
        Integer wms_inve_customer_id = (Integer) transaMap.get("wms_inve_customer_id");
        // 原始产品表主键
        Integer wms_inve_pruduct_category_id = (Integer) transaMap.get("wms_inve_pruduct_category_id");
        // 原始上单产品表主键
        Integer wms_inve_transa_prod_id = (Integer) transaMap.get("wms_inve_transa_prod_id");

        // 原始协议到期时间 --把原到期日期+上1天作为下期的开始日期
        java.sql.Date date = setDatebyCalendarAddday(date_of_payment, 0);

        /*wProtocol.setWms_inve_transa_protocol_id(wms_inve_transa_protocol_id);
        wProtocol.setWms_inve_redeem_id(-5);
        wmsInveTransaProtocolDao.update(wProtocol);*/
        // 获取当前合同信息
        wProtocol = wmsInveTransaProtocolDao.get(wms_inve_transa_protocol_id);
        // 先生成上单产品表信息，避免在生成赎回表时获取不到产品的投资金额
        WmsInveTransaProd wmsInveTransaProd_old = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);

        // 判断续期日期是否大于当前系统时间,大于当前系统时间则做预约续期处理
        if (isRenewalDateCompareToNowDate(date_of_payment) > 0)
        {
            WmsInveTransa wmsInveTransa = new WmsInveTransa();

            if ("2".equals(protocol_type))
            {
                if (new_customer_email != null && !"".equals(new_customer_email))
                {
                    wmsInveTransa.setCustomer_email(new_customer_email);
                }
            }

            wmsInveTransa.setWms_inve_transa_id(wms_inve_transa_id);
            wmsInveTransa.setIs_order_extend("1");
            wmsInveTransaDao.updateDataStatus(wmsInveTransa);

            // 判断进行预约的单据是否是新单据
            if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
            {
                CountIncomeFactory.getCountIncome(wProtocol).handleTransaIncomeForNewExtend(wms_inve_transa_id, user);
            }

            // 判断单据是否是属于股东的单据,股东的单据不生成台账
            if (isShareholderBill)
            {
                /**********************生成新的协议*******************************/
                wmsInveClerkProtocol = setWmsInveClerkProtocol(wmsInveTransa.getWms_inve_transa_id(), null, user, 1, date_of_payment, date_of_end,
                                                               wmsInveTransaProd_old.getProduct_deadline());
                /**********************生成新的柜员业务数据**************************/

                wmsInveClerkData = setWmsInveClerkData(wmsInveTransa.getWms_inve_transa_id(), null, user, 1);

                // 插入协议信息
                if (wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() == null)
                {
                    wmsInveClerkProtocolDao.save(wmsInveClerkProtocol);
                }
                else
                {
                    wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
                }

                // 插入柜员业务信息
                if (wmsInveClerkData.getWms_inve_clerk_data_id() == null)
                {
                    wmsInveClerkDataDao.save(wmsInveClerkData);
                }
                else
                {
                    wmsInveClerkDataDao.update(wmsInveClerkData);
                }
            }

        }
        // 续期日期小于等于当前系统时间,则做续期处理
        else
        {

            // 把原始上单信息变成已完成状态
            WmsInveTransa wmsInveTransa = new WmsInveTransa();

            wmsInveTransa.setWms_inve_transa_id(wms_inve_transa_id);
            wmsInveTransa.setData_status("6");
            wmsInveTransa.setIs_order_extend("0");
            wmsInveTransaDao.updateDataStatus(wmsInveTransa);

            boolean flag = true;
            // 新生成一张上单信息
            WmsInveTransa wInveTransa2 = CopyWmsInveTransaMethod(wms_inve_transa_id, wms_inve_transa_prod_id, wms_inve_pruduct_category_id,
                                                                 wms_inve_customer_id, date, bill_type, user);

            // 新生成一张上单产品信息
            if (user == null)
            {
                PmPersonnel person = pmPersonnelDao.get(wInveTransa2.getCreate_user_id());
                user = new UserBean();
                user.setDeptId(person.getPersonnel_deptid());
                user.setUserId(person.getPersonnel_id());
                user.setRealname(person.getPersonnel_name());
            }
            WmsInveTransaProd wTransaProd2 = CopyWmsInveTransaPordMethod(wInveTransa2.getWms_inve_transa_id(), wms_inve_transa_prod_id, wms_inve_pruduct_category_id, wms_inve_customer_id, flag, user);

            // 判断单据是否是属于股东的单据,股东的单据不生成台账
            if (isShareholderBill)
            {
                /**********************生成新的协议*******************************/
                wmsInveClerkProtocol = setWmsInveClerkProtocol(wmsInveTransa.getWms_inve_transa_id(), wInveTransa2.getWms_inve_transa_id(), user, 2,
                                                               date_of_payment, date_of_end, wTransaProd2.getProduct_deadline());
                /**********************生成新的柜员业务数据**************************/
                wmsInveClerkData = setWmsInveClerkData(wmsInveTransa.getWms_inve_transa_id(), wInveTransa2.getWms_inve_transa_id(), user, 2);
            }

            
            // 进行债权匹配(全部批信贷债权)
            // macth=autoTransaMatch(wTransaProd2.getWms_inve_transa_prod_id(),product_account);
            int wmsInveRedeemDetailId = 0;
            if (bill_type != null && !"".equals(bill_type) && "0".equals(bill_type))
            {
                String macth = initProtocol(wTransaProd2, wInveTransa2, wProtocol, date, bill_type);
                if (macth.equals("success"))
                {
                    // 证明匹配成功，把信息存储到歇息续期表中，无论成功匹配与否 都存储到协议续期日志表中
                    WmsInveProtocolRenewal wProtocolRenewal = new WmsInveProtocolRenewal();
                    wProtocolRenewal.setWms_inve_transa_id(wInveTransa2.getWms_inve_transa_id());
                    wProtocolRenewal.setWms_inve_transa_prod_id(wTransaProd2.getWms_inve_transa_prod_id());
                    wProtocolRenewal.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);
                    wProtocolRenewal.setWms_inve_customer_id(wms_inve_customer_id);
                    wProtocolRenewal.setBill_code(wInveTransa2.getBill_code());
                    wProtocolRenewal.setCus_name(wInveTransa2.getCus_name());
                    wProtocolRenewal.setId_card(wInveTransa2.getId_card());
                    wProtocolRenewal.setCategory_name(wTransaProd2.getCategory_name());
                    wProtocolRenewal.setProduct_account(wTransaProd2.getProduct_account());
                    wProtocolRenewal.setProduct_deadline(wTransaProd2.getProduct_deadline());
                    wProtocolRenewal.setAtic_renewal_date(date);
                    wProtocolRenewal.setCategory_type(Integer.valueOf(wTransaProd2.getCategory_type()));
                    wProtocolRenewal.setEnable_flag("1");
                    wmsinveprotocolrenewalDao.save(wProtocolRenewal);
                    WmsInveProtocolRenewalLog inveProtocolRenewalLog = new WmsInveProtocolRenewalLog();
                    inveProtocolRenewalLog.setWms_inve_transa_id(wms_inve_transa_id);
                    inveProtocolRenewalLog.setWms_inve_transa_prod_id(wms_inve_transa_prod_id);
                    inveProtocolRenewalLog.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);
                    inveProtocolRenewalLog.setMatching_renewal_result("1");
                    inveProtocolRenewalLog.setMatching_renewal_remark("成功续期");
                    inveProtocolRenewalLog.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    wmsInveProtocolRenewalLogDao.save(inveProtocolRenewalLog);
                    // 生成赎回表和赎回明细表
                    wmsInveRedeemDetailId = saveWmsInveRedeem(wInveTransa2, wmsInveTransaProd_old, bill_type, user, date);
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            else
            {
                try
                {
                    // 证明匹配成功，把信息存储到歇息续期表中，无论成功匹配与否 都存储到协议续期日志表中
                    WmsInveProtocolRenewal wProtocolRenewal = new WmsInveProtocolRenewal();
                    wProtocolRenewal.setWms_inve_transa_id(wInveTransa2.getWms_inve_transa_id());
                    wProtocolRenewal.setWms_inve_transa_prod_id(wTransaProd2.getWms_inve_transa_prod_id());
                    wProtocolRenewal.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);
                    wProtocolRenewal.setWms_inve_customer_id(wms_inve_customer_id);
                    wProtocolRenewal.setBill_code(wInveTransa2.getBill_code());
                    wProtocolRenewal.setCus_name(wInveTransa2.getCus_name());
                    wProtocolRenewal.setId_card(wInveTransa2.getId_card());
                    wProtocolRenewal.setCategory_name(wTransaProd2.getCategory_name());
                    wProtocolRenewal.setProduct_account(wTransaProd2.getProduct_account());
                    wProtocolRenewal.setProduct_deadline(wTransaProd2.getProduct_deadline());
                    wProtocolRenewal.setAtic_renewal_date(date);
                    wProtocolRenewal.setCategory_type(Integer.valueOf(wTransaProd2.getCategory_type()));
                    wProtocolRenewal.setEnable_flag("1");
                    wmsinveprotocolrenewalDao.save(wProtocolRenewal);
                    WmsInveProtocolRenewalLog inveProtocolRenewalLog = new WmsInveProtocolRenewalLog();
                    inveProtocolRenewalLog.setWms_inve_transa_id(wms_inve_transa_id);
                    inveProtocolRenewalLog.setWms_inve_transa_prod_id(wms_inve_transa_prod_id);
                    inveProtocolRenewalLog.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);
                    inveProtocolRenewalLog.setMatching_renewal_result("1");
                    inveProtocolRenewalLog.setMatching_renewal_remark("成功续期");
                    inveProtocolRenewalLog.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
                    wmsInveProtocolRenewalLogDao.save(inveProtocolRenewalLog);
                    // 生成赎回表和赎回明细表
                    wmsInveRedeemDetailId = saveWmsInveRedeem(wInveTransa2, wmsInveTransaProd_old, bill_type, user, date);

                    initProtocol(wTransaProd2, wInveTransa2, wProtocol, date, bill_type, wmsInveRedeemDetailId);
                }
                catch (Exception e)
                {
                    throw new RuntimeException();
                }
            }
            if (isShareholderBill)
            {
                /**********************释放原单据债权**************************/
                // 插入协议信息
                if (wmsInveClerkProtocol.getWms_inve_clerk_protocol_id() == null)
                {
                    wmsInveClerkProtocolDao.save(wmsInveClerkProtocol);
                }
                else
                {
                    wmsInveClerkProtocolDao.update(wmsInveClerkProtocol);
                    // 根据合同主键查询柜员合同
                    WmsInveClerkProtocol wmsInveClerkProtocolNew = wmsInveClerkProtocolDao.get(wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
                    // prot_code存在时 去更新上单表合同编码
                    if (wmsInveClerkProtocolNew.getProt_code() != null && !"".equals(wmsInveClerkProtocolNew.getProt_code()))
                    {
                        WmsInveTransa wmsInveTransaNew = new WmsInveTransa();
                        wmsInveTransaNew.setWms_inve_transa_id(wmsInveClerkProtocol.getWms_inve_transa_id());
                        wmsInveTransaNew.setFinancial_bill_code(wmsInveClerkProtocol.getProt_code());
                        // 根据主键更新单据编号
                        wmsInveTransaDao.update(wmsInveTransaNew);
                    }

                }

                // 插入柜员业务信息
                if (wmsInveClerkData.getWms_inve_clerk_data_id() == null)
                {
                    wmsInveClerkDataDao.save(wmsInveClerkData);
                }
                else
                {
                    wmsInveClerkDataDao.update(wmsInveClerkData);
                }


                // 根据上单表主键获取获取老单据的协议信息
                WmsInveClerkProtocol oldWmsInveClerkProtocol = wmsInveClerkProtocolDao.getOldWmsInveClerkProtocolByWmsInveTransaId(wms_inve_transa_id);

                // 判断获取的老单据的协议信息对象是否为空
                if (oldWmsInveClerkProtocol != null)
                {
                    // 更新原合同实际到期日
                    oldWmsInveClerkProtocol.setAct_end_of_date(com.zx.sframe.util.DateUtil.strTimestamp(transaMap.get("end_of_date").toString(), null));
                    wmsInveClerkProtocolDao.update(oldWmsInveClerkProtocol);
                    // 释放原单据债权
                    CreditBusiness.getInstance().releaseMatchedCreditForExtendFlow(wms_inve_transa_id,
                                                                                   oldWmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
                }

                /**********************新单据进行债权匹配***********************/
                CreditBusiness.getInstance().matchForExtendFlow(wInveTransa2.getWms_inve_transa_id(),
                                                                wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), wms_inve_pruduct_category_id,
                                                                wTransaProd2.getProduct_account(), date_of_payment, user);

                /**********************(预约续期并且是可拆分类产品并且选择了债权协议为电子债权方式进行发送邮件信息)生成发送的邮件信息************************/
                WmsInveExtendInfo info = wmsInveExtendInfoDao.getWmsInveExtendInfoByWmsInveTransaId(wms_inve_transa_id);

                // 判断续期信息是否为空
                if (info != null)
                {
                    // 判断续期选择的债权方式是债权方式为电子债权则进行发送邮件
                    if ("2".equals(info.getGet_credit_type()))
                    {
                        saveWmsInveCreditEmailInfo(wms_inve_transa_id, wInveTransa2.getWms_inve_transa_id(), wmsInveClerkProtocol, user);
                    }
                }

            }


            int wmsInveRedeemPrincipalDetailId = 0;
            // 判断bill_type是否是新单据,如果是新单据则需要生成一条赎回本金记录
            if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
            {
                wmsInveRedeemPrincipalDetailId = saveWmsInveRedeemPrincipalDetail(wmsInveRedeemDetailId, wmsInveTransaProd_old.getWms_inve_transa_id());
            }
            // 新生成一张客户支付信息
            CopyWmsInveTransaCard(wInveTransa2, wTransaProd2, bill_type, wmsInveRedeemPrincipalDetailId);

            // 预约续期的历史记录
            Map<String, Object> wmsInveTransaExtendHisMap = new HashMap<String, Object>();
            wmsInveTransaExtendHisMap.put("wms_inve_transa_id", wms_inve_transa_id);
            wmsInveTransaExtendHisMap.put("end_of_date", date_of_payment);
            wmsInveTransaDao.saveWmsInveTransaExtendHis(wmsInveTransaExtendHisMap);
        }
    }

    /**
     * 
     * @Title: setWmsInveClerkProtocol
     * @Description: 生产合同信息
     * @param old_wms_inve_transa_id
     * @param new_wms_inve_transa_id
     * @param user
     * @param type
     * @param date_of_payment
     * @param date_of_end
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 上午11:43:38
     * history:
     * 1、2017年2月16日 DongHao 创建方法
     */
    private WmsInveClerkProtocol setWmsInveClerkProtocol(Integer old_wms_inve_transa_id, Integer new_wms_inve_transa_id, UserBean user, int type,
                                                         Date date_of_payment, Date date_of_end, Integer product_deadline)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();

        try
        {
            if (date_of_end == null)
            {
                if (date_of_payment.compareTo(df.parse(CountIncome.MAGIC_DATE_OF_PAYMENT_STR)) >= 0)
                {
                    java.util.Date date = DateUtil.changeDay(DateUtil.sqlDate2UtilDate(setDatebyCalendar(date_of_payment, product_deadline)), -1);
                    date_of_end = new java.sql.Date(date.getTime());
                }
                else
                {
                    date_of_end = setDatebyCalendar(date_of_payment, product_deadline);
                }
            }
        }
        catch (Exception e)
        {
            Log.error("续期获取生成合同计算date_of_end报错内容：" + e.getMessage());
        }

        // 判断是否是预约续期
        if (type == 1)
        {
            // 根据单据主键获取单据对应的信息
            wmsInveClerkProtocol = getWmsInveClerkProtocolByWmsInveTransaId(old_wms_inve_transa_id, user, type, date_of_payment, date_of_end);
            wmsInveClerkProtocol.setIs_order_extend("1");
        }
        else
        {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("wms_inve_transa_id", old_wms_inve_transa_id);
            paramsMap.put("is_order_extend", 1);

            // 根据原单据的上单主键查询柜员协议表中预约续期的协议
            wmsInveClerkProtocol = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByWmsInveTransaIdAndIsOrderExtend(paramsMap);

            if (wmsInveClerkProtocol == null)
            {
                wmsInveClerkProtocol = getWmsInveClerkProtocolByWmsInveTransaId(new_wms_inve_transa_id, user, type, date_of_payment, date_of_end);
                wmsInveClerkProtocol.setIs_order_extend("0");
            }
            else
            {
                wmsInveClerkProtocol.setWms_inve_transa_id(new_wms_inve_transa_id);
                wmsInveClerkProtocol.setIs_order_extend("0");
                // wmsInveClerkProtocol.setAct_end_of_date(new
                // Timestamp(date_of_end.getTime()));
            }
            wmsInveClerkProtocol.setFinnished_type("1");
        }
        wmsInveClerkProtocol.setCustomer_signature_path(null);
        return wmsInveClerkProtocol;
    }
    
    /**
     * 
     * @Title: getWmsInveClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取
     * @param wms_inve_transa_id
     * @param user
     * @param type
     * @param date_of_payment
     * @param date_of_end
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 上午11:16:46
     * history:
     * 1、2017年2月16日 DongHao 创建方法
     */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id, UserBean user, int type, Date date_of_payment,
                                                                         Date date_of_end)
    {
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        // 根据单据主键获取单据对应的信息
        wmsInveClerkProtocol = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByTransaIdWithoutCode(wms_inve_transa_id);

        wmsInveClerkProtocol.setBegin_of_date(new Timestamp(date_of_payment.getTime()));// 合同生效日期

        wmsInveClerkProtocol.setEnd_of_date(new Timestamp(com.zx.sframe.util.DateUtil.getDatePlusMonths(wmsInveClerkProtocol.getBegin_of_date(), Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())).getTime()));
        // 判断是否是预约续期
        if (type == 1)
        {
            wmsInveClerkProtocol.setAct_end_of_date(null);// 合同实际到期日
        }
        else
        {
            wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(date_of_end.getTime()));
        }
        // 续期时生成不带合同类型的合同
        wmsInveClerkProtocol.setProtocol_type(null);
        wmsInveClerkProtocol.setCreate_user_id(user.getUserId());// 创建人
        wmsInveClerkProtocol.setCreate_user_name(user.getRealname());// 创建人姓名
        wmsInveClerkProtocol.setCreate_user_dept_id(user.getDeptId());// 创建人所在部门
        wmsInveClerkProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));// 创建时间
        wmsInveClerkProtocol.setEnable_flag("1");
        // 续期单据未打印合同 没有合同版本号信息 将实体类中版本号内容清空
        wmsInveClerkProtocol.setMain_protocol_version(null);
        wmsInveClerkProtocol.setSub_protocol_version(null);
        wmsInveClerkProtocol.setInter_protocol_version(null);
        wmsInveClerkProtocol.setM2m_protocol_version(null);

        return wmsInveClerkProtocol;
    }

    /**
     * 
     * @Title: setWmsInveClerkData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param old_wms_inve_transa_id
     * @param new_wms_inve_transa_id
     * @param user
     * @param type
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 上午11:43:29
     * history:
     * 1、2017年2月16日 DongHao 创建方法
     */
    private WmsInveClerkData setWmsInveClerkData(Integer old_wms_inve_transa_id, Integer new_wms_inve_transa_id, UserBean user, int type)
    {
        WmsInveClerkData wmsInveClerkData = new WmsInveClerkData();

        // 判断是否是预约续期
        if (type == 1)
        {
            wmsInveClerkData = getWmsInveClerkDataByWmsInveTransaId(old_wms_inve_transa_id, user);
            wmsInveClerkData.setIs_order_extend("1");
        }
        else
        {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("wms_inve_transa_id", old_wms_inve_transa_id);
            paramsMap.put("is_order_extend", 1);

            wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByWmsInveTransaIdAndDataType(paramsMap);

            if (wmsInveClerkData == null)
            {
                wmsInveClerkData = getWmsInveClerkDataByWmsInveTransaId(new_wms_inve_transa_id, user);
                wmsInveClerkData.setIs_order_extend("0");
            }
            else
            {
                wmsInveClerkData.setWms_inve_transa_id(new_wms_inve_transa_id);
                wmsInveClerkData.setIs_order_extend("0");
            }
        }
        return wmsInveClerkData;
    }

    /**
     * 
     * @Title: getWmsInveClerkDataByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员业务表
     * @param wms_inve_transa_id
     * @param user
     * @return 
     * @author: DongHao
     * @time:2017年2月16日 上午11:51:12
     * history:
     * 1、2017年2月16日 DongHao 创建方法
     */
    private WmsInveClerkData getWmsInveClerkDataByWmsInveTransaId(Integer wms_inve_transa_id, UserBean user)
    {
        WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByWmsInveTransaId(wms_inve_transa_id);

        wmsInveClerkData.setData_type("2");
        wmsInveClerkData.setOper_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setIs_finished("0");
        wmsInveClerkData.setCreate_user_id(user.getUserId());
        wmsInveClerkData.setCreate_user_name(user.getRealname());
        wmsInveClerkData.setCreate_user_dept_id(user.getDeptId());
        wmsInveClerkData.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setEnable_flag("1");
        return wmsInveClerkData;
    }

    /**
     * @Title: saveWmsInveRedeemPrincipalDetail
     * @Description: 生成续期本金
     * @param wmsInveRedeemDetailId 赎回详细信息id
     * @param wmsInveTransaId 老单子的上单表主键
     * @author: DongHao
     * @time:2016年12月28日 下午5:06:15
     * history:
     * 1、2016年12月28日 DongHao 创建方法
    */
    private int saveWmsInveRedeemPrincipalDetail(int wmsInveRedeemDetailId, int wmsInveTransaId)
    {
        WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail = null;
        
        //Map<String, Object> oldWmsInveTransaIncomeMap = wmsInveTransaDao.getWmsInveTransaIncomeByWmsInveTransaId(wmsInveTransaId);
        
        Map<String, Object> wmsRedeemMap = wmsinveredeemDao.getWmsInveRedeemInfoByWmsInveRedeemDetailId(wmsInveRedeemDetailId);
        
        if (wmsRedeemMap != null)
        {
            wmsInveRedeemPrincipalDetail = new WmsInveRedeemPrincipalDetail();
            wmsInveRedeemPrincipalDetail.setWms_inve_redeem_detail_id(wmsInveRedeemDetailId);
            wmsInveRedeemPrincipalDetail.setPrincipal_type("2");
            wmsInveRedeemPrincipalDetail.setPrincipal_amount(new BigDecimal(wmsRedeemMap.get("redeem_amount") != null ? wmsRedeemMap.get("redeem_amount").toString() : "0"));// 本金
            wmsInveRedeemPrincipalDetail.setIncome_amount(new BigDecimal("0"));// 基本收益
            wmsInveRedeemPrincipalDetail.setExtend_amount(new BigDecimal("0"));// 公益收益
            wmsInveRedeemPrincipalDetail.setPaid_income_amount(new BigDecimal("0"));// 已付收益
            wmsInveRedeemPrincipalDetail.setUsed_income_amount(new BigDecimal(wmsRedeemMap.get("redeem_amount") != null ? wmsRedeemMap.get("redeem_amount").toString() : "0"));// 已使用本金金额
            wmsInveRedeemPrincipalDetail.setReward_income(new BigDecimal("0"));// 奖励收益
            wmsInveRedeemPrincipalDetail.setTotal_management_fee(new BigDecimal("0"));// 服务金额
            wmsInveRedeemPrincipalDetailDao.save(wmsInveRedeemPrincipalDetail);
        }
        return wmsInveRedeemPrincipalDetail.getWms_inve_redeem_principal_detail_id();
    }

    /**
      * @Title isRenewalDateCompareToNowDate
      * @Description 内容摘要: 判断续期时间是否在当前系统时间之前
      * @param date_of_payment
      * @return 返回0说明续期时间与当前系统时间相同,返回小于0的数说明在系统时间之前,返回大于0的数说明在当前系统时间之后
      * @author DongHao
      * @Time 2016年11月15日13:10:22
      * history 1. 2016年11月15日13:10:27 DongHao 创建方法
      */
    public int isRenewalDateCompareToNowDate(Date date_of_payment)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastDateStr = format.format(date_of_payment);
        String nowDateStr = format.format(new Date());

        try
        {
            Date lastDate = format.parse(lastDateStr);
            Date nowDate = format.parse(nowDateStr);
            return lastDate.compareTo(nowDate);

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 设置日期 参数int i是为了方法的公共使用  主要的目的就是计算的天数上有差异对天数进行加减
     * @param wmsFinaCreLoanApp
     * @return
     */
    private java.sql.Date setDatebyCalendarAddday(Date sDate, int i)
    {

        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        calendar.add(Calendar.DAY_OF_MONTH, +i);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }

    /**
     * 新生成一张上单信息
     * @param wms_inve_transa_id 原始上单信息表主键
     * @param wms_inve_transa_prod_id 原始上单产品信息表主键
     * @param wms_inve_pruduct_category_id 判断获得
     * @param wms_inve_customer_id 原始客户ID
     * @param personnel_state 
     * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
     * @param user 
     * @param order_financial_bill_code续期编号
     * @return
     * history:
     *  1. 2016年12月28日16:07:52 DongHao 修改方法  在方法中 增加String类型的bill_type参数
     */
    private WmsInveTransa CopyWmsInveTransaMethod(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id, Integer wms_inve_pruduct_category_id,
                                                  Integer wms_inve_customer_id, java.sql.Date date, String bill_type, UserBean user)
    {
        WmsInveTransa wTransa_old = wmsInveTransaDao.get(wms_inve_transa_id);
        WmsInveTransaProd wmsInveTransaProd_old = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        Integer sales_man_id = wTransa_old.getBel_salesman_id_id();
        // 根据user_id获取状态
        PmPersonnel pmPersonnel = pmPersonnelDao.get(sales_man_id);
        String personnel_state = "1";
        if ("7".equals(pmPersonnel.getPersonnel_state()))
        {
            personnel_state = "2";
        }
        String wTransa_old_str = new Gson().toJson(wTransa_old);
        WmsInveTransa wTransa_new = new Gson().fromJson(wTransa_old_str, WmsInveTransa.class);
        // 上单信息表

        wTransa_new.setBill_code(CodeNoUtil.getEnviNoteCode());
        wTransa_new.setData_status("4");// 单据状态4收益中
        wTransa_new.setAccount_status("3");// 金额状态 2已支付3已到账

        // 判断单据类型为新单据,如果为新单据则设置单据的支付类型为续期本经和设置单据来源为上单单据
        // 否则设置支付类型为单据续期和单据来源设置为续期生成的单据
        if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
        {
            wTransa_new.setPay_type(1);
            wTransa_new.setBill_source(0);// 设置单据来源为上单单据
        }
        else
        {
            wTransa_new.setPay_type(0);// 1代表现金5续期支付
            wTransa_new.setBill_source(1);// 单据来源 1标示续期生产的单据
            if (wTransa_old.getBill_source() != null && 1 == wTransa_old.getBill_source())
            {
                wTransa_new.setOld_date_of_payment(wTransa_old.getOld_date_of_payment());// 原单据的上单支付时间
            }
            else
            {
                wTransa_new.setOld_date_of_payment(wTransa_old.getDate_of_payment());// 原单据的上单支付时间
            }
        }

        wTransa_new.setDate_of_payment(date);//
        wTransa_new.setDate_of_act(date);// 到账时间
        wTransa_new.setPay_account(wmsInveTransaProd_old.getProduct_account());// 支付金额合计
        wTransa_new.setAct_account(wmsInveTransaProd_old.getProduct_account());// 到账金额合计==上单信息表中的理财金额
        wTransa_new.setEnable_flag("1");// 是否有效
        wTransa_new.setCreate_timestamp(new Timestamp(date.getTime()));// 创建时间
        wTransa_new.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));

        if (bill_type != null && !"".equals(bill_type) && "0".equals(bill_type))
        {
            // 判断老单据中是否有做过续期的原始id,如果存在则将原始id赋值给old_wms_inve_transa_id,否则将老单子的id进行赋值
            if (wTransa_old.getOld_wms_inve_transa_id() == null)
            {
                wTransa_new.setOld_wms_inve_transa_id(wms_inve_transa_id);// 原上单单据id
            }
            else
            {
                wTransa_new.setOld_wms_inve_transa_id(wTransa_old.getOld_wms_inve_transa_id());// 原上单单据id
            }
            wTransa_new.setOld_last_wms_inve_transa_id(wms_inve_transa_id);
        }
        wTransa_new.setTrans_salesman_status(personnel_state);// 兼职单还是在职单
        //查询柜员合同表对应的单据的合同编号
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramsMap.put("is_order_extend", 1);

        // 根据原单据的上单主键查询柜员协议表中预约续期的协议
        WmsInveClerkProtocol wmsInveClerkProtocol = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByWmsInveTransaIdAndIsOrderExtend(paramsMap);
        
        //判断获取的对应单据的合同信息是否为空
        if(wmsInveClerkProtocol != null)
        {
            
            //判断合同编号是否为空,如果不为空则对单据进行赋值
            if(wmsInveClerkProtocol.getProt_code() != null && !"".equals(wmsInveClerkProtocol.getProt_code()))
            {
                //为上单表中的财务合同进行赋值
                wTransa_new.setFinancial_bill_code(wmsInveClerkProtocol.getProt_code());
            }
            
        }
        /*
        //WmsInveTransa wTransa_new = new WmsInveTransa();
        wTransa_new.setWms_inve_customer_id(wms_inve_customer_id);
        wTransa_new.setBill_code(CodeNoUtil.getEnviNoteCode());
        wTransa_new.setId_card(wTransa_old.getId_card());//身份证号
        wTransa_new.setCus_name(wTransa_old.getCus_name());//客户姓名
        wTransa_new.setCus_birthday(wTransa_old.getCus_birthday());//出生日期
        wTransa_new.setCus_gender(wTransa_old.getCus_gender());//性别
        wTransa_new.setCus_address(wTransa_old.getCus_address());//居住地址
        wTransa_new.setPost_code(wTransa_old.getPost_code());//邮政编码
        wTransa_new.setAddress_phone(wTransa_old.getAddress_phone());//居住电话
        wTransa_new.setWork_phone(wTransa_old.getWork_phone());//办公室电话
        wTransa_new.setCus_fax(wTransa_old.getCus_fax());//传真号码
        wTransa_new.setContact_address(wTransa_old.getContact_address());//通讯地址
        wTransa_new.setCustomer_email(wTransa_old.getCustomer_email());//电邮地址
        wTransa_new.setBill_send_mode(wTransa_old.getBill_send_mode());//账单默认发送方式
        wTransa_new.setInve_transa_type(wTransa_old.getInve_transa_type());//理财单类型
        wTransa_new.setProduct_total_count_lower(wTransa_old.getProduct_total_count_lower());//合计投资金额（小写）单位元
        wTransa_new.setProduct_total_count_upper(wTransa_old.getProduct_total_count_upper());//合计投资金额（大写）
        wTransa_new.setData_status("4");//单据状态4收益中
        wTransa_new.setAccount_status("3");//金额状态 2已支付3已到账
        wTransa_new.setPay_type("5");//1代表现金
        //支付时间为协议到期日期
        wTransa_new.setDate_of_payment(date);//支付时间
        wTransa_new.setPay_account(wTransa_old.getPay_account());//支付金额合计
        wTransa_new.setCash_pay_name(wTransa_old.getCash_pay_name());//现金支付人姓名
        wTransa_new.setAct_account_usr_id(wTransa_old.getAct_account_usr_id());//到账操作人主键
        //到账时间
        wTransa_new.setPay_usr_id(wTransa_old.getPay_usr_id());//支付操作人主键
        wTransa_new.setDate_of_act(date);//到账时间
        wTransa_new.setAct_account(wmsInveTransaProd_old.getProduct_account());//到账金额合计==上单信息表中的理财金额
        wTransa_new.setFee_account(new BigDecimal(0));//手续费（合计金额与到账金额差）
        wTransa_new.setSalesman_name(sales_man_name);//业务员的名字
        wTransa_new.setSalesman_id(sales_man_id);//业务员主键
        wTransa_new.setSalesman_city(wTransa_old.getSalesman_city());//业务员城市
        wTransa_new.setSalesman_shortcode(pmPersonnel.getPersonnel_shortcode());//业务员
        wTransa_new.setSalesman_city_code(pmPersonnel.getPersonnel_regionnumber());//业务员城市编码
        wTransa_new.setSalesman_dept_id(new Integer(pmPersonnel.getPersonnel_deptid()));//业务员所在部门ID
        wTransa_new.setEnable_flag("1");//是否有效
        wTransa_new.setMobile_phone(wTransa_old.getMobile_phone());//移动电话
        wTransa_new.setDepartment_manager_id(wTransa_old.getDepartment_manager_id());//部门经理ID
        wTransa_new.setDepartment_manager_dept_id(wTransa_old.getDepartment_manager_dept_id());//部门经理城市编码
        wTransa_new.setDepartment_manager_city_code(wTransa_old.getDepartment_manager_city_code());//部门经理部门ID
        wTransa_new.setVice_general_manager_dept_id(wTransa_old.getVice_general_manager_dept_id());//副总经理ID
        wTransa_new.setVice_general_manager_id(wTransa_old.getVice_general_manager_id());//副总经理城市编码
        wTransa_new.setVice_general_manager_city_code(wTransa_old.getVice_general_manager_city_code());//副总经理部门ID
        wTransa_new.setGeneral_manager_id(wTransa_old.getGeneral_manager_id());//总经理ID
        wTransa_new.setGeneral_manager_dept_id(wTransa_old.getGeneral_manager_dept_id());//总经理城市编码
        wTransa_new.setGeneral_manager_city_code(wTransa_old.getGeneral_manager_city_code());//总经理部门ID
        wTransa_new.setCostomer_id(wTransa_old.getCostomer_id());//CRM关联信息表主键
        wTransa_new.setCustomer_source(wTransa_old.getCustomer_source());//客户来源
        wTransa_new.setCustomer_num(wTransa_old.getCustomer_num());//客户编号
        wTransa_new.setCreate_user_id(user.getUserId());//创建人
        wTransa_new.setCreate_user_name(user.getRealname());//创建人姓名
        wTransa_new.setCreate_user_dept_id(user.getDeptId());//创建人所在部门
        wTransa_new.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));//创建时间
        wTransa_new.setLast_update_user_id(user.getUserId());//最后修改人
        wTransa_new.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));//最后修改时间
        wTransa_new.setOld_wms_inve_transa_id(wms_inve_transa_id);//原上单单据id
        wTransa_new.setBill_source(1);//单据来源 1标示续期生产的单据
        //wTransa_new.setBel_salesman_id_id(wTransa_old.getBel_salesman_id_id());
        wTransa_new.setTrans_salesman_status(personnel_state);//兼职单还是在职单
        wTransa_new.setBel_center_manager_dept_id(wTransa_old.getBel_center_manager_dept_id());
        wTransa_new.setBel_center_manager_id(wTransa_old.getBel_center_manager_id());
        if(wTransa_old.getBill_source()!=null&&1==wTransa_old.getBill_source()){
        	wTransa_new.setOld_date_of_payment(wTransa_old.getOld_date_of_payment());//原单据的上单支付时间
        }else{
        	wTransa_new.setOld_date_of_payment(wTransa_old.getDate_of_payment());//原单据的上单支付时间
        }*/
        wmsInveTransaDao.save(wTransa_new);
        return wTransa_new;
    }

    /**
     * 新生成一张上单产品信息
     * @param wms_inve_transa_id 新理财上单信息ID
     * @param wms_inve_transa_prod_id 原始上单产品信息
     * @param wms_inve_pruduct_category_id 判断新的产品ID
     * @param wms_inve_customer_id 客户ID
     * @return
     */
    private WmsInveTransaProd CopyWmsInveTransaPordMethod(Integer wms_inve_transa_id, Integer wms_inve_transa_prod_id, Integer wms_inve_pruduct_category_id, Integer wms_inve_customer_id, boolean flag, UserBean user)
    {
        WmsInveTransaProd wmsInveTransaProd_old = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        // 上单产品信息表
        WmsInveTransaProd wmsInveTransaProd_new = new WmsInveTransaProd();
        wmsInveTransaProd_new.setWms_inve_transa_id(wms_inve_transa_id);// 上单信息表主键
        wmsInveTransaProd_new.setWms_inve_pruduct_category_id(wms_inve_pruduct_category_id);// 产品表主键
        // 根据主键获取当前产品
        WmsInvePruductCategory wCategory = wmsInvePruductCategoryDao.get(wms_inve_pruduct_category_id);
        wmsInveTransaProd_new.setCategory_name(wCategory.getCategory_name());// 产品名称
        wmsInveTransaProd_new.setProduct_deadline(wCategory.getCategory_deadline());// 理财期限（月）
        // 区分是否是换了产品
        if (flag)
        {
            wmsInveTransaProd_new.setProduct_interest(wmsInveTransaProd_old.getProduct_interest());// 年化收益率（%）
        }
        else
        {
            wmsInveTransaProd_new.setProduct_interest(wCategory.getCategory_return_rate().toString());// 年化收益率（%）
        }
        wmsInveTransaProd_new.setProduct_account(wmsInveTransaProd_old.getProduct_account());// 理财金额
        wmsInveTransaProd_new.setIs_finish("1");// 债权匹配是否完成
        wmsInveTransaProd_new.setCreate_user_id(user.getUserId());// 创建人
        wmsInveTransaProd_new.setCreate_user_name(user.getRealname());// 创建人姓名

        wmsInveTransaProd_new.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));// 创建时间

        wmsInveTransaProd_new.setEnable_flag("1");// 数据状态
        wmsInveTransaProd_new.setProduct_deadline_name(wCategory.getCategory_deadline() + "期");// 理财期限

        if (flag)
        {
            wmsInveTransaProd_new.setReward_interest(wmsInveTransaProd_old.getReward_interest());// 奖励利率(%)
            wmsInveTransaProd_new.setExpect_interest_account(wmsInveTransaProd_old.getExpect_interest_account());// 预期收益（元）
            wmsInveTransaProd_new.setWms_inve_pruduct_deadline_id(wmsInveTransaProd_old.getWms_inve_pruduct_deadline_id());// 产品限制表主键
            wmsInveTransaProd_new.setOrg_product_account(wmsInveTransaProd_old.getProduct_account());// 原始上单/追单金额
        }
        else
        {

        }

        if (flag)
        {
            wmsInveTransaProd_new.setCategory_code(wmsInveTransaProd_old.getCategory_code());// 产品表编号
            wmsInveTransaProd_new.setCategory_type(wmsInveTransaProd_old.getCategory_type());// 理财产品类型
        }
        else
        {
            wmsInveTransaProd_new.setCategory_code(wCategory.getCategory_code());
            wmsInveTransaProd_new.setCategory_type(wCategory.getCategory_type().toString());
        }
        wmsInveTransaProd_new.setIs_allopatry(wmsInveTransaProd_old.getIs_allopatry());// 是否匹配异地债权
        wmsInveTransaProd_new.setIs_customer_confirmation(wmsInveTransaProd_old.getIs_customer_confirmation());// 是否客户确认
        wmsInveTransaProd_new.setLast_update_user_id(user.getUserId());// 最后修改人
        wmsInveTransaProd_new.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));// 最后修改时间
        
        wmsInveTransaProd_new.setCard_owner_name(wmsInveTransaProd_old.getCard_owner_name());// 卡主姓名
        wmsInveTransaProd_new.setBank_of_deposit(wmsInveTransaProd_old.getBank_of_deposit());// 开户行
        wmsInveTransaProd_new.setCard_no(wmsInveTransaProd_old.getCard_no());// 银行卡号
        wmsInveTransaProd_new.setBank_of_deposit_pro(wmsInveTransaProd_old.getBank_of_deposit_pro());// 开户行(省)
        wmsInveTransaProd_new.setBank_of_deposit_city(wmsInveTransaProd_old.getBank_of_deposit_city());// 开户行(市)
        wmsInveTransaProd_new.setBank_branch(wmsInveTransaProd_old.getBank_branch());// 开户行所属支行
        wmsInveTransaProd_new.setWms_inve_customer_card_id(wmsInveTransaProd_old.getWms_inve_customer_card_id());
        
        /****************************************收益卡信息*******************************************************/
        //根据上单表主键获取收益卡信息
        Map<String, Object> res_map = wmsInveTransaProdDao.getIncomeCardInfo(wms_inve_transa_prod_id);
        
        if(res_map != null && res_map.size() > 0)
        {
            wmsInveTransaProd_new.setCard_owner_name((String)res_map.get("card_owner_name"));// 卡主姓名
            wmsInveTransaProd_new.setBank_of_deposit((String)res_map.get("bank_of_deposit"));// 开户行
            wmsInveTransaProd_new.setCard_no((String)res_map.get("card_no"));// 银行卡号
            wmsInveTransaProd_new.setBank_of_deposit_pro((String)res_map.get("bank_of_deposit_pro"));// 开户行(省)
            wmsInveTransaProd_new.setBank_of_deposit_city((String)res_map.get("bank_of_deposit_city"));// 开户行(市)
            wmsInveTransaProd_new.setBank_branch((String)res_map.get("bank_branch"));// 开户行所属支行
            wmsInveTransaProd_new.setWms_inve_customer_card_id((Integer)res_map.get("wms_inve_customer_card_id"));
        }
        
        wmsInveTransaProdDao.save(wmsInveTransaProd_new);
        wmsInveTransaProdDao.updateRedeem(wmsInveTransaProd_old);
        
        return wmsInveTransaProd_new;
    }

    /**
     * 新生成一张客户支付信息
     * @param wms_inve_transa_id 新上单信息表主键
     * @param wms_inve_transa_id_old 原始单信息表主键
     * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
     * @param wmsInveRedeemPrincipalDetailId 续期本金表主键
     * @return
     * history:
     * 1. 2016年12月28日17:12:02 DongHao 修改方法   方法中增加两个参数分别为：String类型的bill_type, int类型的wmsInveRedeemPrincipalDetailId
     */
    private void CopyWmsInveTransaCard(WmsInveTransa wInveTransa2, WmsInveTransaProd wTransaProd2, String bill_type, int wmsInveRedeemPrincipalDetailId)
    {
        // 自动续期是现金支付 需要添加一条支付现金支付信息
        WmsInveTransaCard w = new WmsInveTransaCard();
        w.setWms_inve_transa_id(wInveTransa2.getWms_inve_transa_id());// 上单信息表主键
        w.setCard_owner_name(wInveTransa2.getCus_name());// 卡主姓名

        w.setPay_account(wTransaProd2.getProduct_account());// 实际支付金额
        w.setIs_finish("1");// 是否已到账
        w.setAct_account(wTransaProd2.getProduct_account());// 到账金额
        w.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));// 创建时间
        w.setEnable_flag("1");// 是否有效
        if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
        {
            w.setPay_type("3");// 支付方式为续期本金
            w.setWms_inve_redeem_principal_detail_id(wmsInveRedeemPrincipalDetailId);// 设置使用的续期本金的id
            w.setIs_valid("1");
        }
        else
        {
            w.setPay_type("5");// 支付方式1 现金 2银行卡3现金+银行卡4产品转换5单据续期
        }
        w.setShould_pay_account(wTransaProd2.getProduct_account());// 应支付金额
        wmsInveTransaCardDao.save(w);
    }

    /**
    * 因当前流程中不匹配债权和打印协议 但是代码中是有的 所以需要重新初始化当前表信息 以保证数据正常使用
    * 初始化合同和债权 
    * @return
    * history:
    * 1.2016年12月29日10:06:40 DongHao 修改方法  在方法参数列表中增加String类型的bill_type参数(bill_type等于0表示老单据1表示为新单据)
    */
    public String initProtocol(WmsInveTransaProd wmsTransaProd, WmsInveTransa wmsInveTransa, WmsInveTransaProtocol wProtocol, java.sql.Date nowDate, String bill_type, Integer... wmsInveRedeemDetailId)
    {
        int wms_inve_redeem_id = 0;
        if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
        {
            if (wmsInveRedeemDetailId != null && wmsInveRedeemDetailId.length > 0)
            {
                int wms_inve_redeem_detail_id = wmsInveRedeemDetailId[0];
                Map<String, Object> resultMap = wmsinveredeemDao.getWmsInveRedeemInfoByWmsInveRedeemDetailId(wms_inve_redeem_detail_id);
                wms_inve_redeem_id = Integer.parseInt(resultMap.get("wms_inve_redeem_id").toString());
                wProtocol.setWms_inve_redeem_id(wms_inve_redeem_id);
            }
        }
        java.sql.Date end_of_date = null;
        Date startDate = null;
        if (wmsInveTransa.getOld_date_of_payment() == null)
        {
            startDate = wmsInveTransa.getDate_of_payment();
        }
        else
        {
            startDate = wmsInveTransa.getOld_date_of_payment();
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            if (startDate.compareTo(df.parse(CountIncome.MAGIC_DATE_OF_PAYMENT_STR)) >= 0)
            {
                java.util.Date date = DateUtil.changeDay(DateUtil.sqlDate2UtilDate(setDatebyCalendar(wmsInveTransa.getDate_of_payment(), wmsTransaProd.getProduct_deadline())), -1);
                end_of_date = new java.sql.Date(date.getTime());
            }
            else
            {
                end_of_date = setDatebyCalendar(wmsInveTransa.getDate_of_payment(), wmsTransaProd.getProduct_deadline());
            }
        }
        catch (ParseException e)
        {
        }
        // 保存协议签订信息
        String result = "success";
        WmsInveTransaProtocol wInveTransaProtocol = new WmsInveTransaProtocol();
        wInveTransaProtocol.setWms_inve_transa_id(wmsTransaProd.getWms_inve_transa_id());// 上单信息表主键
        wInveTransaProtocol.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());// 上单产品表主键
        wInveTransaProtocol.setProt_code(CodeNoUtil.getEnviProCode());// 获取协议编号
        wInveTransaProtocol.setA_name(wProtocol.getA_name());// 甲方姓名
        wInveTransaProtocol.setA_id_card(wProtocol.getA_id_card());// 甲方身份证号
        wInveTransaProtocol.setProduct_account(wmsTransaProd.getProduct_account());
        wInveTransaProtocol.setDate_of_payment(nowDate);// 当前期的开始日期
        wInveTransaProtocol.setA_bank(wProtocol.getA_bank());
        wInveTransaProtocol.setA_number(wProtocol.getA_number());
        wInveTransaProtocol.setB_name(wProtocol.getB_name());// 客户姓名
        wInveTransaProtocol.setB_id_card(wProtocol.getB_id_card());// 客户身份证号
        wInveTransaProtocol.setCategory_name(wmsTransaProd.getCategory_name());
        wInveTransaProtocol.setProduct_deadline(wmsTransaProd.getProduct_deadline());
        wInveTransaProtocol.setSign_place(wProtocol.getSign_place());
        wInveTransaProtocol.setB_data(wProtocol.getB_data());
        wInveTransaProtocol.setEnd_of_date(end_of_date);// 结束日期
        wInveTransaProtocol.setCreate_user_id(wProtocol.getCreate_user_id());
        wInveTransaProtocol.setCreate_user_name(wProtocol.getCreate_user_name());
        wInveTransaProtocol.setCreate_timestamp(new Timestamp(nowDate.getTime()));
        wInveTransaProtocol.setLast_update_user_id(wProtocol.getCreate_user_id());
        wInveTransaProtocol.setLast_update_timestamp(new Timestamp(nowDate.getTime()));
        wInveTransaProtocol.setEnable_flag("1");
        wInveTransaProtocol.setContact_address(wProtocol.getContact_address());
        wInveTransaProtocol.setPost_code(wProtocol.getPost_code());
        wInveTransaProtocol.setWms_inve_redeem_id(0);
        wmsInveTransaProtocolDao.save(wInveTransaProtocol);

        // 直接做一条测试据存储到债权表中
        WmsInveTransaMatch inveTransaMatch = new WmsInveTransaMatch();
        inveTransaMatch.setWms_inve_transa_prod_id(wmsTransaProd.getWms_inve_transa_prod_id());
        inveTransaMatch.setWms_fina_cre_repay_id(0);
        inveTransaMatch.setCredit_name("测试1");
        inveTransaMatch.setCredit_id_card("111111111111111111");
        inveTransaMatch.setAssign_account(wmsTransaProd.getProduct_account());
        inveTransaMatch.setDate_of_assign(new java.sql.Date(System.currentTimeMillis()));
        inveTransaMatch.setRepay_begin_date(new java.sql.Date(System.currentTimeMillis()));
        inveTransaMatch.setRepay_end_date(new java.sql.Date(System.currentTimeMillis()));
        inveTransaMatch.setProduct_interest_month(new BigDecimal(1));
        inveTransaMatch.setCreate_user_id(wProtocol.getCreate_user_id());
        inveTransaMatch.setCreate_timestamp(new Timestamp(nowDate.getTime()));
        inveTransaMatch.setLast_update_user_id(wProtocol.getCreate_user_id());
        inveTransaMatch.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        inveTransaMatch.setEnable_flag("1");
        inveTransaMatch.setWms_inve_redeem_id(0);
        inveTransaMatch.setWms_inve_transa_protocol_id(wInveTransaProtocol.getWms_inve_transa_protocol_id());
        wmsInveTransaMatchDao.save(inveTransaMatch);
        // 创建一个人 直接拿签订合同那个人来创建收益
        UserBean user = new UserBean();
        user.setUserId(wProtocol.getCreate_user_id());
        user.setRealname(wProtocol.getCreate_user_name());
        // try
        // {
            // 计算收益和日志
            // result =handleIncomeAndLogInfo(wInveTransaProtocol, user);
            // jinzhiming修改，统一调用协议service中的方法
            // wmsInveTransaProtocolService.SethandleIncomeAndLogInfo(wInveTransaProtocol,
            // user);
            // 判断单据类型
            if (bill_type != null && !"".equals(bill_type) && "0".equals(bill_type))
            {
                CountIncomeFactory.getCountIncome(wInveTransaProtocol).getIncomeAndLogForAutoExtend(wInveTransaProtocol, wProtocol, new Date(), user);
            }
            else if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
            {
                CountIncomeFactory.getCountIncome(wInveTransaProtocol).generateIncomeAndLogForNewExtend(wInveTransaProtocol, wProtocol, new Date(), user);
            }
        // }
        // catch (Exception e)
        // {
        // result = "error";
        // return result;
        // }
        return result;
    }

    /**
     * 设置日期 参数int i是为了方法的公共使用  主要的目的就是计算的月份上有差异对月份进行加减
     * @param wmsFinaCreLoanApp
     * @return
     */
    private java.sql.Date setDatebyCalendar(Date sDate, int i)
    {

        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        calendar.add(Calendar.MONTH, +i);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }

    /**
     * @Title: handleIncomeAndLogInfo 
     * @Description: 处理收益和日志信息
     * @param bean
     * @param user
     * @return String 
     * @throws
     * @author lvtu 
     * @date 2015年8月28日 上午9:12:39
     */
    private String handleIncomeAndLogInfo(WmsInveTransaProtocol bean, UserBean user)
    {
        CountIncomeFactory.getCountIncome(bean).getIncomeAndLog(bean, user);
        return "success";
    }

    /**
     * 
     * @Title: saveWmsInveRedeem
     * @Description: 生成赎回单据
     * @param wInveTransa2
     * @param wmsInveTransaProd_old
     * @param bill_type 单据类型(0表示为老单据,1表示为新单据)
     * @param user
     * @param date
     * @return 
     * @author: DongHao
     * @time:2016年12月28日 下午4:29:02
     * history:
     * 1、2016年12月28日 DongHao 修改方法  方法参数中新增String类型的bill_type参数
     */
    private int saveWmsInveRedeem(WmsInveTransa wInveTransa2, WmsInveTransaProd wmsInveTransaProd_old, String bill_type, UserBean user, java.sql.Date date)
    {
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        WmsInveRedeem bean = new WmsInveRedeem();
        bean.setRedeem_date(date);// 赎回日期
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setLast_update_user_id(user.getUserId());
        bean.setCreate_user_dept_id(user.getDeptId());
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_timestamp(sysTime);
        bean.setBill_code(CodeNoUtil.getReturnRedeemCode());
        bean.setTotal_invest_amount(wmsInveTransaProd_old.getOrg_product_account());
        bean.setWms_inve_customer_id(wInveTransa2.getWms_inve_customer_id());
        WmsInveTransa wTransa_old = wmsInveTransaDao.get(wmsInveTransaProd_old.getWms_inve_transa_id());
        if (wTransa_old.getBel_salesman_id_id() != null)
        {
            bean.setSalesman_id(wTransa_old.getBel_salesman_id_id());
        }
        else
        {
            bean.setSalesman_id(wTransa_old.getSalesman_id());
        }
        bean.setSalesman_dept_id(wTransa_old.getSalesman_dept_id());
        bean.setId_card(wTransa_old.getId_card());
        bean.setCus_name(wTransa_old.getCus_name());
        bean.setRedeem_apply_total_amount(wmsInveTransaProd_old.getProduct_account());
        bean.setRedeem_reality_total_amount(wmsInveTransaProd_old.getProduct_account());
        bean.setIs_special_approval("0");
        bean.setIs_payback("1");
        // 判断单据类型为新单据类型,则设置赎回类型为到期赎回
        if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
        {
            bean.setRedeem_type("4");// 到期赎回
            bean.setRedeem_reason("到期赎回");// 赎回事由
            bean.setRedeem_reality_total_amount(new BigDecimal("0"));
            bean.setRedeem_reality_total_amount_upper("零");
            bean.setTotal_due_income(new BigDecimal("0"));
            bean.setTotal_deduct_tax_point(new BigDecimal("0"));
        }
        else
        {
            bean.setRedeem_type("2");// 续期赎回
            bean.setRedeem_reason("续期赎回");// 赎回事由
        }
        bean.setEnable_flag("1");
        bean.setIs_take_off_damages("0");// 不收违约金
        bean.setRedeem_company_reason("3");// 协议到期
        bean.setIs_fully_redeemed("1");
        bean.setIs_finish("2");
        bean.setIs_protocol_finish("2");
        bean.setData_status("6");// 单据状态为已完成
        wmsinveredeemDao.save(bean);
        WmsInveRedeemDetail wmsInveRedeemDetail = new WmsInveRedeemDetail();
        wmsInveRedeemDetail.setCreate_user_id(user.getUserId());
        wmsInveRedeemDetail.setLast_update_user_id(user.getUserId());
        wmsInveRedeemDetail.setCreate_user_dept_id(user.getDeptId());
        wmsInveRedeemDetail.setCreate_timestamp(sysTime);
        wmsInveRedeemDetail.setLast_update_timestamp(sysTime);
        wmsInveRedeemDetail.setWms_inve_redeem_id(bean.getWms_inve_redeem_id());
        wmsInveRedeemDetail.setIs_protocol_finish("0");
        wmsInveRedeemDetail.setEnable_flag("1");
        wmsInveRedeemDetail.setDeduct_money(new BigDecimal(0));
        wmsInveRedeemDetail.setDeduct_tax_point(new BigDecimal(0));
        wmsInveRedeemDetail.setWms_inve_transa_id(wmsInveTransaProd_old.getWms_inve_transa_id());
        wmsInveRedeemDetail.setWms_inve_transa_prod_id(wmsInveTransaProd_old.getWms_inve_transa_prod_id());
        wmsInveRedeemDetail.setRedeem_amount(wmsInveTransaProd_old.getProduct_account());
        wmsInveRedeemDetail.setProduct_account(wmsInveTransaProd_old.getProduct_account());
        wmsInveRedeemDetail.setCategory_name(wmsInveTransaProd_old.getCategory_name());
        wmsInveRedeemDetail.setIs_fully_redeemed("1");

        if (bill_type != null && !"".equals(bill_type) && "1".equals(bill_type))
        {
            wmsInveRedeemDetail.setDue_income(new BigDecimal("0"));
            wmsInveRedeemDetail.setDeduct_tax_point(new BigDecimal("0"));
            wmsInveRedeemDetail.setPaid_income(new BigDecimal("0"));
            wmsInveRedeemDetail.setExtend_income(new BigDecimal("0"));
        }
        // 保存赎回明细表信息
        wmsinveredeemdetailDao.save(wmsInveRedeemDetail);
        int wmsInveRedeemDetailId = wmsInveRedeemDetail.getWms_inve_redeem_detail_id();
        // 更改原单据更新理财中的金额
        WmsInveTransaProd prod = new WmsInveTransaProd();
        prod.setWms_inve_transa_prod_id(wmsInveTransaProd_old.getWms_inve_transa_prod_id());
        prod.setProduct_account(BigDecimal.ZERO);
        wmsInveTransaDao.updateTransaProdAmount(prod);
        return wmsInveRedeemDetailId;
    }

    @Override
    public void autoInveExtendSave(String is_new_data)
    {
        // 查询2015-12-14（含）及之前的数据进行自动的续期处理
        // inveInveExtendSave(Integer wms_inve_transa_id,Date date_of_payment,
        // null)
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("magic_date", "2015-12-14");
        paramMap.put("is_new_data", is_new_data);

        List<Map<String, Object>> list = wmsInveTransaDao.getAutoExtendTransa(paramMap);
        if (list == null)
        {
            return;
        }
        for (Map<String, Object> map : list)
        {
            inveInveExtendSave(null, (Integer) map.get("wms_inve_transa_id"), (Date) map.get("pay_of_date"), null, 0, "0", "", "", null, null);
        }
        // inveInveExtendSave(5144,
        // com.zx.sframe.util.DateUtil.strDate("2016-11-11", null), null);
    }

    /**
     * @Title: inveReservationRenewalAutoHandler
     * @Description: 将预约续期的单据进行续期操作 
     * @param nowDate 续期时间
     * @return 返回结果 success 或者 error
     * @author: DongHao
     * @time:2016年11月18日 下午5:06:21
     * @see com.zx.emanage.inve.service.IWmsInveExtendService#inveReservationRenewalAutoHandler()
     * history:
     * 1、2016年11月18日 DongHao 创建方法
    */
    @Override
    public String inveReservationRenewalAutoHandler(String is_new_data)
    {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = formate.format(new Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("nowDate", nowDate);
        paramMap.put("is_new_data", is_new_data);
        List<Map<String, Object>> list = wmsInveTransaDao.getInveRenewalByNowDateAndIsOrderExtend(paramMap);

        for (Map<String, Object> map : list)
        {
            inveInveExtendSave(null, (Integer) map.get("wms_inve_transa_id"), (Date) map.get("end_of_date"), null, 0,
                               map.get("bill_type").toString(), "", "", null,null);
        }
        return "success";
    }

    /**
     * 
     * @Title: setWmsInveExtendInfo
     * @Description: 生成续期单据信息表对象
     * @param wmsInveTransaProd 上单产品信息对象
     * @param wms_inve_transa_id 上单表主键ID
     * @param protocol_type 债权协议类型(1表示字纸债权,2表示电子债权)
     * @param user 当前登录的用户信息对象
     * @return 返回续期单据信息表对象
     * @author: DongHao
     * @time:2017年2月27日 下午10:12:27
     * history:
     * 1、2017年2月27日 DongHao 创建方法
     */
    public WmsInveExtendInfo setWmsInveExtendInfo(WmsInveTransaProd wmsInveTransaProd, Integer wms_inve_transa_id, String protocol_type, UserBean user)
    {
        WmsInveExtendInfo info = new WmsInveExtendInfo();
        info.setWms_inve_transa_id(wms_inve_transa_id);
        info.setBank_of_deposit_pro(wmsInveTransaProd.getBank_of_deposit_pro());// 省
        info.setBank_of_deposit_city(wmsInveTransaProd.getBank_of_deposit_city());// 市
        info.setBank_branch(wmsInveTransaProd.getBank_branch());// 支行
        info.setBank_of_deposit(wmsInveTransaProd.getBank_of_deposit());// 开户行
        info.setCard_owner_name(wmsInveTransaProd.getCard_owner_name());// 客户姓名
        info.setCard_no(wmsInveTransaProd.getCard_no());// 卡号
        info.setCreate_user_id(user.getUserId());
        info.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        info.setLast_update_user_id(user.getUserId());
        info.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        info.setEnable_flag("1");
        info.setGet_credit_type(protocol_type);
        return info;
    }

    /**
     * 
     * @Title: upateWmsInveExtendInfoByWmsInveTransaId
     * @Description: 根据单据表主键获取当前续期单据的收益卡信息
     * @param wms_inve_transa_id
     * @param user 
     * @author: DongHao
     * @time:2017年2月27日 下午10:36:23
     * history:
     * 1、2017年2月27日 DongHao 创建方法
     */
    public void upateWmsInveExtendInfoByWmsInveTransaId(Integer wms_inve_transa_id, UserBean user)
    {
        WmsInveExtendInfo wmsInveExtendInfo = wmsInveExtendInfoDao.getWmsInveExtendInfoByWmsInveTransaId(wms_inve_transa_id);

        if (wmsInveExtendInfo != null)
        {
            wmsInveExtendInfo.setEnable_flag("0");
            wmsInveExtendInfoDao.update(wmsInveExtendInfo);
        }

    }

    /**
     * @Title: verifyRenewalBill
     * @Description: 验证所要续期的单据是否是预约续期并且是可拆分类的产品
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param date_of_payment 续期开始时间
     * @return 返回boolean类型的值,如果所要续期的单据是预约续期并且是可拆分类的产品返回true,否则返回false
     * @author: DongHao
     * @time:2017年3月13日 下午3:59:49
     * @see com.zx.emanage.inve.service.IWmsInveExtendService#verifyRenewalBill(java.lang.String, java.lang.String)
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    @Override
    public boolean verifyRenewalBill(String wms_inve_transa_id, String wms_inve_transa_prod_id, Date date_of_payment)
    {

        // 定义返回结果的boolean类型
        boolean bool = false;

        // 判断续期单据是否是预约续期
        if (isRenewalDateCompareToNowDate(date_of_payment) > 0)
        {
            // 根据上单产品表主键获取对应的产品类型
            Map<String, Object> categoryMap = wmsInveExtendInfoDao.getCategoryTypeByWmsInveTransaProdId(wms_inve_transa_prod_id);

            Integer categoryType = categoryMap != null && categoryMap.size() != 0 ? (Integer) categoryMap.get("category_type") : null;

            // 判断产品类型为可拆分类型
            if (categoryType != null && categoryType == 4)
            {
                bool = true;
            }
        }
        return bool;
    }

    /**
     * 
     * @Title: saveWmsInveCreditEmailInfo
     * @Description: 生成债权邮件信息
     * @param old_wms_inve_transa_id 原单据上单表主键ID
     * @param new_wms_inve_transa_id 新单据上单表主键ID
     * @param oldWmsInveClerkProtocol 合同信息
     * @author: DongHao
     * @time:2017年3月16日 下午5:24:55
     * history:
     * 1、2017年3月16日 DongHao 创建方法
     */
    private Integer saveWmsInveCreditEmailInfo(Integer old_wms_inve_transa_id, Integer new_wms_inve_transa_id,
                                               WmsInveClerkProtocol oldWmsInveClerkProtocol, UserBean user)
    {

        // 定义邮件信息表中的主键id
        Integer wms_inve_credit_email_id = null;

        // 根据上单表主键获取续期信息
        WmsInveExtendInfo info = wmsInveExtendInfoDao.getWmsInveExtendInfoByWmsInveTransaId(old_wms_inve_transa_id);

        // 判断续期信息是否为空
        if (info != null)
        {
            // 判断债权选择方式不为空并且债权方式选择了电子债权的方式,添加发送邮件信息
            if (info.getGet_credit_type() != null && !"".equals(info.getGet_credit_type()) && "2".equals(info.getGet_credit_type()))
            {
                // 定义邮件对象
                WmsInveCreditEmail wmsInveCreditEmail = new WmsInveCreditEmail();

                // 设置续期生成的新单据的上单表主键
                wmsInveCreditEmail.setWms_inve_transa_id(new_wms_inve_transa_id);

                // 设置邮件标题
                wmsInveCreditEmail.setEmail_subject("卓信金控《债权转让及受让协议》已发送，请查收_【自动发送，请勿回复】");

                // 判断柜员协议信息是否为空
                if (oldWmsInveClerkProtocol != null)
                {
                    // 设置柜员协议表主键
                    wmsInveCreditEmail.setWms_inve_clerk_protocol_id(oldWmsInveClerkProtocol.getWms_inve_clerk_protocol_id());

                    // 设置邮件内容
                    wmsInveCreditEmail.setEmail_content("尊敬的客户，您好！您所持有的【" + oldWmsInveClerkProtocol.getProt_code()
                                                        + "】理财合同所对应的《债权转让及受让协议》和《内部债权转让及受让协议》已匹配完成，请注意查收！谢谢！");
                }

                // 设置邮件发送状态为未发送状态
                wmsInveCreditEmail.setSend_status("0");

                // 设置邮件是否有效标识
                wmsInveCreditEmail.setEnable_flag("1");

                // 设置类型
                wmsInveCreditEmail.setData_type("1");

                // 设置创建时间
                wmsInveCreditEmail.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

                // 设置创建人
                wmsInveCreditEmail.setCreate_user_id(user.getUserId());

                // 保存邮件发送信息
                wmsInveCreditEmailDao.save(wmsInveCreditEmail);

                // 保存邮件信息之后获取返回的邮件id
                wms_inve_credit_email_id = wmsInveCreditEmail.getWms_inve_credit_email_id();
            }

        }

        return wms_inve_credit_email_id;
    }

    /**
     * 
     * @Title: saveWmsInveCreditEmailAttInfo
     * @Description: 保存邮件附件信息并生成债权合同信息
     * @param wms_inve_credit_email_id 邮件信息表主键ID
     * @author: DongHao
     * @time:2017年3月16日 下午5:31:23
     * history:
     * 1、2017年3月16日 DongHao 创建方法
     */
    private void saveWmsInveCreditEmailAttInfo(Integer wms_inve_credit_email_id, UserBean user)
    {
        // 判断邮件id是否为空
        if (wms_inve_credit_email_id != null)
        {

            // 根据邮件id获取邮件信息
            Map<String, Object> emailInfo = wmsInveCreditEmailDao.getCreditEmailByEmailId(wms_inve_credit_email_id);

            if (emailInfo != null)
            {
                // 定义生成合同对象
                WmsInveClerkProtocolSearchBeanVO bean = new WmsInveClerkProtocolSearchBeanVO();

                // 设置预约续期生成新单据id
                bean.setWms_inve_transa_id(Integer.valueOf(emailInfo.get("wms_inve_transa_id").toString()));

                // 设置合同id
                bean.setWms_inve_clerk_protocol_id(Integer.valueOf(emailInfo.get("wms_inve_clerk_protocol_id").toString()));

                // 设置单据编号
                bean.setBill_code(emailInfo.get("bill_code").toString());

                List<Map<String, String>> res_file_map = wmsInveClerkProtocolService.exportProtocolpdf(bean);

                for(Map<String, String> map : res_file_map)
                {
                 // 生成发送邮件附件信息
                    WmsInveCreditEmailAtt att = new WmsInveCreditEmailAtt();

                    // 设置邮件id
                    att.setWms_inve_credit_email_id(wms_inve_credit_email_id);

                    // 设置附件名称
                    att.setAtt_name(map.get("file_name").toString());

                    // 设置附件地址
                    att.setAtt_dir(map.get("file_url").toString());

                    // 设置是否有效标识
                    att.setEnable_flag("1");
                    
                    // 设置创建时间
                    att.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

                    // 设置创建人
                    att.setCreate_user_id(user.getUserId());

                    // 保存邮件附件
                    wmsInveCreditEmailAttDao.save(att);
                }
            }
        }
    }

    /**
     * @Title: generateCreditProtocol
     * @Description: 生成预约续期的债权合同(条件:1. 邮件状态处于未发送状态,2. 合同类型为线上合同)
     * @return 返回map集合提示信息
     * @author: DongHao
     * @time:2017年3月20日 上午9:16:10
     * @see com.zx.emanage.inve.service.IWmsInveExtendService#generateCreditProtocol()
     * history:
     * 1、2017年3月20日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> generateCreditProtocol()
    {

        // 定义返回结果信息
        Map<String, Object> res_map = new HashMap<String, Object>();

        // 获取未发送的邮件并且合同类型为线上合同
        List<Map<String, Object>> list = wmsInveCreditEmailDao.getWmsInveCreditEmailInfoAll();

        // 循环遍历未发送的邮件,并且生成债权合同
        for (Map<String, Object> map : list)
        {
            // 根据上单表中的创建人id获取用户信息
            PmPersonnel person = pmPersonnelDao.get((Integer) map.get("create_user_id"));
            UserBean user = new UserBean();
            user.setDeptId(person.getPersonnel_deptid());
            user.setUserId(person.getPersonnel_id());
            user.setRealname(person.getPersonnel_name());

            // 更新邮件内容中的内容
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("wms_inve_credit_email_id", (Integer) map.get("wms_inve_credit_email_id"));
            params.put("email_content", "尊敬的客户，您好！您所持有的【" + (String) map.get("prot_code") + "】理财合同所对应的《债权转让及受让协议》和《内部债权转让及受让协议》已匹配完成，请注意查收！谢谢！");
            wmsInveCreditEmailDao.updateCreditEmailById(params);

            // 生成合同
            saveWmsInveCreditEmailAttInfo((Integer) map.get("wms_inve_credit_email_id"), user);

        }

        // 发送邮件
        sendEmailInfo();

        res_map.put("ret_code", 000);

        return res_map;
    }

    /**
     * 
     * @Title: sendEmailInfo
     * @Description: 发送邮件
     * @author: DongHao
     * @time:2017年3月20日 上午9:41:53
     * history:
     * 1、2017年3月20日 DongHao 创建方法
     */
    @SuppressWarnings("unchecked")
    private void sendEmailInfo()
    {
        // 定义map参数用于更新邮件信息状态
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        // 获取全部需要发送的邮件信息
        List<Map<String, Object>> list = wmsInveCreditEmailDao.getSendEmailInfos();

        // 循环遍历发送邮件信息
        for (Map<String, Object> map : list)
        {
            try
            {
                // 对邮件附件的名称和邮件的地址进行处理
                Map<String, String> attachFileMap = new HashMap<String, String>();

                String[] att_names = map.get("att_name").toString().split(",");
                String[] att_dirs = map.get("att_dir").toString().split(",");

                for (int i = 0; i < att_names.length; i++)
                {
                    attachFileMap.put(att_names[i], att_dirs[i]);
                }

                // 设置调用接口参数
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("interface_num", "MAIL_OUT_003"));
                nvps.add(new BasicNameValuePair("sys_num", "WMS"));
                nvps.add(new BasicNameValuePair("toAddress", map.get("customer_email").toString()));
                nvps.add(new BasicNameValuePair("content", map.get("email_content").toString()));
                nvps.add(new BasicNameValuePair("subject", map.get("email_subject").toString()));
                nvps.add(new BasicNameValuePair("attachFiles", new Gson().toJson(attachFileMap)));

                // 设置邮件表主键,为了更新邮件发送状态
                paramsMap.put("wms_inve_credit_email_id", (Integer) map.get("wms_inve_credit_email_id"));

                HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpPost);

                InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
                BufferedReader rd = new BufferedReader(reader);
                String result_str = rd.readLine();

                Gson gson = new Gson();
                Map<String, Object> res_map = gson.fromJson(result_str, Map.class);

                // 判断获取的结果集是否为空
                if (res_map != null)
                {
                    // 判断返回的结果集ret_code是否是000(000表示发送邮件成功)
                    if (res_map.get("flag") != null && (boolean) res_map.get("flag"))
                    {
                        paramsMap.put("send_status", "1");// 发送成功
                    }
                    else
                    {
                        paramsMap.put("send_status", "2");// 发送失败
                    }
                }

                // 更新邮件状态
                wmsInveCreditEmailDao.updateWmsInveCreditEmailStatusById(paramsMap);

                response.close();
                httpclient.close();
            }
            catch (Exception e)
            {
                paramsMap.put("send_status", "2");// 发送失败
                wmsInveCreditEmailDao.updateWmsInveCreditEmailStatusById(paramsMap);

                e.printStackTrace();
            }
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
    private boolean verifyIsShareholderBill(Integer wms_inve_transa_id)
    {
        // 定义boolean类型的变量,用来标记是否存在股东单据,存在bool为false,否则为true
        boolean bool = true;

        // 根据原始上单单据id获取股东单据
        Integer shareholderBillCount = wmsInveTransaDao.getShareholderBillsByWmsInveTransaId(wms_inve_transa_id);

        // 判断根据单据id获取的股东单据记录数是否大于0,大于0说明存在股东单据所以设置bool为false
        if (shareholderBillCount > 0)
        {
            bool = false;
        }

        return bool;
    }

}
