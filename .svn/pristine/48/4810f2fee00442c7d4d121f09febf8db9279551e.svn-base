package com.zx.emanage.inve.util.transa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zx.emanage.inve.util.CountIncomeFactory;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaSalesmanHis;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.digitUpperUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SignPTPTransa
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月5日
 * @version V1.0
 * history:
 * 1、2017年6月5日 jinzhm 创建文件
 */
public class SignPTPTransa extends SignAbstract
{

    private static final Logger log = LoggerFactory.getLogger(SignPTPTransa.class);

    /**
     * ptp生成单据关联收益卡主键的property配置表中主键
     */
    public static final Integer PROPERTY_ID = 5007;

    /**
     * @Title: sign
     * @Description: ptp签单都是批量签单，此方法不做任何实现
     * @param transaData
     * @return 
     * @author: jinzhm
     * @time:2017年6月20日 上午10:37:18
     * @see com.zx.emanage.inve.util.transa.SignAbstract#sign(com.zx.emanage.inve.util.transa.SignTransaData)
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
    */
    @Override
    protected Map<String, Object> sign(SignTransaData transaData)
    {
        return new HashMap<String, Object>();
    }

    /**
     * @Title: transa
     * @Description: 为ptp系统生成ptp单据信息
     * @param transaDataList
     * @return 
     * @author: jinzhm
     * @time:2017年6月20日 上午10:42:04
     * @see com.zx.emanage.inve.util.transa.SignTransa#transa(java.util.List)
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> transa(List<SignTransaData> transaDataList)
    {
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // 最后要返回的结果
        List<Map<String, Object>> resMapList = new ArrayList<Map<String, Object>>();
        // 每条ptp单据生成结果
        Map<String, Object> resMap = null;

        for (SignTransaData transaData : transaDataList)
        {
            resMap = new HashMap<String, Object>();
            // 设置产品信息
            transaData.setCategory(SignHelper.getWmsInvePruductCategoryDao()
                                             .get(transaData.getTransaProd().getWms_inve_pruduct_category_id()));

            // 获得crm客户信息
            Map<String, Object> cusData = getCustomerInfoByIdCard(transaData.getTransa().getId_card(),
                                                                  transaData.getTransa().getBel_salesman_id_id()
                                                                            .toString());

            // 设置业务员等信息
            setSalesmanInfo(cusData, transaData);
            
            // 设置登录人id及登录人姓名
            transaData.getUser().setUserId(transaData.getTransa().getSalesman_id());
            transaData.getUser().setRealname(transaData.getTransa().getSalesman_name());

            // 生成wms客户信息
            generateCustomerData(cusData, transaData, time);
            // 生成上单信息
            generateTransaData(transaData, time);
            // 设置客户性别及生日
            transaData.getCustomer().setCus_birthday(transaData.getTransa().getCus_birthday());
            transaData.getCustomer().setCus_gender(transaData.getTransa().getCus_gender());

            // 保存客户信息
            if (transaData.getCustomer().getWms_inve_customer_id() == null)
            {
                SignHelper.getWmsInveCustomerDao().save(transaData.getCustomer());
            }
            else
            {
                SignHelper.getWmsInveCustomerDao().update(transaData.getCustomer());
            }

            // 设置单据信息中的wms客户主键
            transaData.getTransa().setWms_inve_customer_id(transaData.getCustomer().getWms_inve_customer_id());
            // 保存上单信息
            SignHelper.getWmsInveTransaDao().save(transaData.getTransa());

            // 设置收益卡信息
            generateCustomerCardData(transaData);

            // 设置上单产品信息
            generateTransaProdData(transaData, time);
            // 保存上单产品信息
            SignHelper.getWmsInveTransaProdDao().save(transaData.getTransaProd());

            // 生成支付信息保存
            SignHelper.getWmsInveTransaCardDao().save(generateTransaCardData(transaData, time));

            // 生成并保存理财协议信息
            WmsInveTransaProtocol protocol = generateTransaProtocol(transaData, time);
            SignHelper.getWmsInveTransaProtocolDao().save(protocol);

            // 生成并保存信息
            SignHelper.getWmsInveTransaDao().saveWmsInveTransaSalesmanHis(generateTransaSalesmanHis(transaData, time));

            // 生成交易日志
            CountIncomeFactory.getCountIncome(SignHelper.PTP_CATEGORY_ID).getIncomeAndLog(protocol,
                                                                                          transaData.getUser());
            // 设置ptp系统投资主键
            resMap.put("ptp_user_invest_id", transaData.getPtp_user_invest_id());
            // 设置wms上单主键
            resMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
            // 保存在返回的集合中
            resMapList.add(resMap);
        }
        return resMapList;
    }

    /**
     * @Title: generateTransaSalesmanHis
     * @Description: 生成理财上单业务员历史数据
     * @param transaData
     * @param time
     * @return 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:39:20
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
     */
    private WmsInveTransaSalesmanHis generateTransaSalesmanHis(SignTransaData transaData, Timestamp time)
    {
        WmsInveTransaSalesmanHis transaSalesmanHis = new WmsInveTransaSalesmanHis();

        transaSalesmanHis.setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());

        transaSalesmanHis.setSalesman_id(transaData.getTransa().getSalesman_id());
        transaSalesmanHis.setSalesman_dept_id(transaData.getTransa().getSalesman_dept_id());

        transaSalesmanHis.setDepartment_manager_dept_id(transaData.getTransa().getDepartment_manager_dept_id());
        transaSalesmanHis.setDepartment_manager_id(transaData.getTransa().getDepartment_manager_id());

        transaSalesmanHis.setVice_general_manager_dept_id(transaData.getTransa().getVice_general_manager_dept_id());
        transaSalesmanHis.setVice_general_manager_id(transaData.getTransa().getVice_general_manager_id());

        transaSalesmanHis.setGeneral_manager_dept_id(transaData.getTransa().getGeneral_manager_dept_id());
        transaSalesmanHis.setGeneral_manager_id(transaData.getTransa().getGeneral_manager_id());

        transaSalesmanHis.setCenter_manager_dept_id(transaData.getTransa().getCenter_manager_dept_id());
        transaSalesmanHis.setCenter_manager_id(transaData.getTransa().getCenter_manager_id());
        transaSalesmanHis.setCreate_user_id(transaData.getUser().getUserId());
        transaSalesmanHis.setCreate_timestamp(time);

        transaSalesmanHis.setRemark("");

        return transaSalesmanHis;
    }

    /**
     * @Title: generateTransaProtocol
     * @Description: 生成签单老协议信息
     * @param transaData 签单数据
     * @param time 系统时间
     * @return 签单老协议
     * @author: jinzhm
     * @time:2017年6月7日 上午8:40:20
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    private WmsInveTransaProtocol generateTransaProtocol(SignTransaData transaData, Timestamp time)
    {
        WmsInveTransaProtocol transaProtocol = new WmsInveTransaProtocol();

        // 设置上单主键，上单产品主键及赎回主键
        transaProtocol.setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
        transaProtocol.setWms_inve_transa_prod_id(transaData.getTransaProd().getWms_inve_transa_prod_id());
        transaProtocol.setWms_inve_redeem_id(0);
        // 设置协议编号
        transaProtocol.setProt_code(CodeNoUtil.getEnviProCode());
        // 甲方姓名
        transaProtocol.setA_name("测试甲");
        // 甲方身份证号
        transaProtocol.setA_id_card("111111111111111111");
        // 上单金额
        transaProtocol.setProduct_account(transaData.getTransaProd().getProduct_account());
        // 支付日期
        transaProtocol.setDate_of_payment(transaData.getTransa().getDate_of_payment());
        // 甲方开户银行及甲方账号
        transaProtocol.setA_bank("111111111111111111");
        transaProtocol.setA_number("111111111111111111");
        // 乙方姓名及乙方身份证号
        transaProtocol.setB_name(transaData.getTransa().getCus_name());
        transaProtocol.setB_id_card(transaData.getTransa().getId_card());
        // 产品名称，产品期限
        transaProtocol.setCategory_name(transaData.getTransaProd().getCategory_name());
        transaProtocol.setProduct_deadline(transaData.getTransaProd().getProduct_deadline());
        // 协议签署地及乙方资料
        transaProtocol.setSign_place("111111111111111111");
        transaProtocol.setB_data("111111111111111111");
        // 协议到期时间
        transaProtocol.setEnd_of_date(new Date(SignHelper.computeEndOfDate(null,
                                                                           transaData.getTransa().getDate_of_payment(),
                                                                           transaData.getTransaProd()
                                                                                     .getProduct_deadline()).getTime()));
        // 创建数据及更新数据
        transaProtocol.setCreate_user_id(transaData.getUser().getUserId());
        transaProtocol.setCreate_user_name(transaData.getUser().getRealname());
        transaProtocol.setCreate_timestamp(time);
        transaProtocol.setLast_update_user_id(transaData.getUser().getUserId());
        transaProtocol.setLast_update_timestamp(time);
        // 是否有效，通讯地址，邮政编码
        transaProtocol.setEnable_flag("1");
        transaProtocol.setContact_address("111111111111111111");
        transaProtocol.setPost_code("111111");
        return transaProtocol;
    }

    private WmsInveTransaCard generateTransaCardData(SignTransaData transaData, Timestamp time)
    {
        WmsInveTransaCard transaCard = new WmsInveTransaCard();

        // 单据主键
        transaCard.setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
        // pos机信息主键直接设置成0
        transaCard.setWms_inve_pos_id("0");
        // 支付金额
        transaCard.setPay_account(transaData.getTransaProd().getProduct_account());
        // 是否已到账
        transaCard.setIs_finish("1");
        // 到账金额
        transaCard.setAct_account(transaData.getTransaProd().getProduct_account());
        // 创建人及创建时间
        transaCard.setCreate_user_id(transaData.getUser().getUserId());
        transaCard.setCreate_user_name(transaData.getUser().getRealname());
        transaCard.setCreate_timestamp(time);
        // 是否有效
        transaCard.setEnable_flag("1");
        // 支付方式设置成现金支付
        transaCard.setPay_type("1");
        // 应支付金额
        transaCard.setShould_pay_account(transaData.getTransaProd().getProduct_account());
        // 是否生效
        transaCard.setIs_valid("1");

        return transaCard;
    }

    /**
     * @Title: generateTransaProdData
     * @Description: 生成上单产品信息
     * @param transaData 签单数据对象
     * @param time 当前时间
     * @author: jinzhm
     * @time:2017年6月21日 上午9:54:46
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
     */
    private void generateTransaProdData(SignTransaData transaData, Timestamp time)
    {
        // 设置单据主键
        transaData.getTransaProd().setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
        // 设置产品名称
        transaData.getTransaProd().setCategory_name(transaData.getCategory().getCategory_name());
        // 设置产品期限
        transaData.getTransaProd().setProduct_deadline(transaData.getCategory().getCategory_deadline());
        // 是否完成
        transaData.getTransaProd().setIs_finish("0");
        // 创建人，创建时间，创建人姓名
        transaData.getTransaProd().setCreate_user_id(transaData.getTransa().getCreate_user_id());
        transaData.getTransaProd().setCreate_user_name(transaData.getTransa().getCreate_user_name());
        transaData.getTransaProd().setCreate_timestamp(time);
        // 修改人，修改时间
        transaData.getTransaProd().setLast_update_user_id(transaData.getTransa().getCreate_user_id());
        transaData.getTransaProd().setLast_update_timestamp(time);
        // 有效标志
        transaData.getTransaProd().setEnable_flag("1");
        // 卡主姓名
        transaData.getTransaProd().setCard_owner_name(transaData.getCustomerCard().getCard_owner_name());
        // 银行名称
        transaData.getTransaProd().setBank_of_deposit(transaData.getCustomerCard().getBank_of_deposit());
        // 银行卡号
        transaData.getTransaProd().setCard_no(transaData.getCustomerCard().getCard_no());
        // 奖励利率
        transaData.getTransaProd().setReward_interest(BigDecimal.ZERO);
        // 理财期限
        if (transaData.getTransaProd().getProduct_deadline() != null)
        {
            transaData.getTransaProd().setProduct_deadline_name(transaData.getTransaProd().getProduct_deadline() + "期");
        }
        // 预期收益
        transaData.getTransaProd()
                  .setExpect_interest_account(transaData.getTransaProd()
                                                        .getProduct_account()
                                                        .multiply(new BigDecimal(transaData.getTransaProd()
                                                                                           .getProduct_interest()).divide(new BigDecimal(
                                                                                                                                         100)))
                                                        .multiply(new BigDecimal(transaData.getTransaProd()
                                                                                           .getProduct_deadline()))
                                                        .divide(new BigDecimal(12)));
        // 开户行省
        transaData.getTransaProd().setBank_of_deposit_pro(transaData.getCustomerCard().getBank_of_deposit_pro());
        // 开户行市
        transaData.getTransaProd().setBank_of_deposit_city(transaData.getCustomerCard().getBank_of_deposit_city());
        // 开户行支行
        transaData.getTransaProd().setBank_branch(transaData.getCustomerCard().getBank_branch());
        // 收益卡主键
        transaData.getTransaProd().setWms_inve_customer_card_id(transaData.getCustomerCard()
                                                                          .getWms_inve_customer_card_id());
    }

    /**
     * @Title: generateCustomerCardData
     * @Description: 查询ptp关联单据对应的收益卡信息
     * @param transaData 签单数据对象
     * @author: jinzhm
     * @time:2017年6月21日 上午11:22:40
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
     */
    private void generateCustomerCardData(SignTransaData transaData)
    {
        transaData.setCustomerCard(SignHelper.getWmsInveCustomerCardDao()
                                             .get(Integer.parseInt(SignHelper.getWmsSysPropertyDao()
                                                                                     .get(PROPERTY_ID)
                                                                             .getProperty_value())));
    }

    /**
     * @Title: setSalesmanInfo
     * @Description: 设置单据业务员等信息
     * @param cusData 客户数据
     * @param transaData 签单数据对象
     * @author: jinzhm
     * @time:2017年6月21日 上午9:46:11
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
     */
    private void setSalesmanInfo(Map<String, Object> cusData, SignTransaData transaData)
    {
        PmPersonnel personnel = (PmPersonnel) cusData.get("salesman_info");
        transaData.getTransa().setSalesman_id(personnel.getPersonnel_id());
        transaData.getTransa().setSalesman_shortcode(personnel.getPersonnel_shortcode());
        transaData.getTransa().setSalesman_dept_id(personnel.getPersonnel_deptid());

        // 校验并设置业务员信息
        String result = checkSalesmanInfo(transaData);
        if (result == SignHelper.ERROR9)
        {
            log.error("归属业务员信息输入有误！");
            throw new RuntimeException("归属业务员信息输入有误！");
        }
    }

    /**
     * @Title: generateCustomerData
     * @Description: 设置wms客户信息
     * @param cusData crm接口调用返回数据
     * @param transaData 签单数据对象
     * @param time 当前时间
     * @author: jinzhm
     * @time:2017年6月20日 下午3:59:42
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
     */
    private void generateCustomerData(Map<String, Object> cusData, SignTransaData transaData, Timestamp time)
    {
        // 通过客户身份证号查询wms客户信息
        WmsInveCustomer customer = new WmsInveCustomer();
        customer.setId_card(transaData.getTransa().getId_card());
        List<WmsInveCustomer> customerList = SignHelper.getWmsInveCustomerDao().getListByEntity(customer);
        if (!customerList.isEmpty())
        {
            customer = customerList.get(0);
            customer.setCostomer_id((Integer) cusData.get("costomer_id"));
            // 客户来源写死是CRM
            customer.setCustomer_source("1");
            customer.setLast_update_timestamp(time);
            customer.setLast_update_user_id(transaData.getUser().getUserId());
            customer.setCrm_create_timestamp((String) cusData.get("create_timestamp"));
        }
        else
        {
            // 客户编码
            customer.setCus_code(CodeNoUtil.getEnviCustomerCode());
            // 客户姓名
            customer.setCus_name(((String) cusData.get("customer_name")) != null
                                 && !"".equals(((String) cusData.get("customer_name"))) ? ((String) cusData.get("customer_name")).trim()
                                                                                       : "");
            // 客户生日
            customer.setCus_birthday(cusData.get("customer_birthday") == null ? null
                                                                             : new Date(
                                                                                        DateUtil.strDate(String.valueOf(cusData.get("customer_birthday")),
                                                                                                         null)
                                                                                                .getTime()));
            // 性别
            customer.setCus_gender(((String) cusData.get("customer_gender")) != null
                                   && !"".equals(((String) cusData.get("customer_gender"))) ? ((String) cusData.get("customer_gender")).trim()
                                                                                           : "");
            // 居住地址
            customer.setCus_address(((String) cusData.get("present_address")) != null
                                    && !"".equals(((String) cusData.get("present_address"))) ? ((String) cusData.get("present_address")).trim()
                                                                                            : "");
            // 邮政编码
            // customer.setPost_code("");
            // 居住电话
            // customer.setAddress_phone("");
            // 移动电话
            customer.setMobile_phone(((String) cusData.get("contact_number")) != null
                                     && !"".equals(((String) cusData.get("contact_number"))) ? ((String) cusData.get("contact_number")).trim()
                                                                                            : "");
            // 办公室电话
            // customer.setWork_phone("");
            // 传真号码
            // customer.setCus_fax("");
            // 通讯地址
            customer.setContact_address(((String) cusData.get("domicile_place")) != null
                                        && !"".equals(((String) cusData.get("domicile_place"))) ? ((String) cusData.get("domicile_place")).trim()
                                                                                               : "");
            // 电邮地址
            customer.setCustomer_email(((String) cusData.get("email_adress")) != null
                                       && !"".equals(((String) cusData.get("email_adress"))) ? ((String) cusData.get("email_adress")).trim()
                                                                                            : "");
            // 业务员姓名
            customer.setSalesman_name(transaData.getTransa().getSalesman_name());
            // 业务员id
            customer.setSalesman_id(transaData.getTransa().getSalesman_id());
            // 业务员城市编码
            customer.setSalesman_city_code(transaData.getTransa().getSalesman_city_code());
            // 业务员所在城市
            customer.setSalesman_city(transaData.getTransa().getSalesman_city());
            // 业务员所在部门
            customer.setSalesman_dept_id(transaData.getTransa().getSalesman_dept_id());
            // 创建人id
            customer.setCreate_user_id(transaData.getTransa().getSalesman_id());
            // 创建人姓名
            customer.setCreate_user_name(transaData.getTransa().getSalesman_name());
            // 创建时间
            customer.setCreate_timestamp(time);
            // 设置crm创建时间
            customer.setCrm_create_timestamp((String) cusData.get("create_timestamp"));
            // 修改人id
            customer.setLast_update_user_id(transaData.getTransa().getSalesman_id());
            // 修改时间
            customer.setLast_update_timestamp(time);
            // 有效标志
            customer.setEnable_flag("1");
            // 客户编号
            customer.setCustomer_num((String) cusData.get("customer_num"));
            // 证件类型（表示id_card_number是1139（身份证）、1140（军官证）、1141（护照））
            customer.setCertificate_type((Integer) cusData.get("certificate_type"));
            // 客户来源 客户来源写死是crm
            customer.setCustomer_source("1");
            // 客户crm主键
            customer.setCostomer_id((Integer) cusData.get("costomer_id"));

            // 存储部门经理
            customer.setDepartment_manager_id(transaData.getTransa().getDepartment_manager_id());
            customer.setDepartment_manager_city_code(transaData.getTransa().getDepartment_manager_city_code());
            customer.setDepartment_manager_dept_id(transaData.getTransa().getDepartment_manager_dept_id());
            // 存储副总经理
            customer.setVice_general_manager_id(transaData.getTransa().getVice_general_manager_id());
            customer.setVice_general_manager_city_code(transaData.getTransa().getVice_general_manager_city_code());
            customer.setVice_general_manager_dept_id(transaData.getTransa().getVice_general_manager_dept_id());
            // 存储总经理
            customer.setGeneral_manager_id(transaData.getTransa().getGeneral_manager_id());
            customer.setGeneral_manager_city_code(transaData.getTransa().getGeneral_manager_city_code());
            customer.setGeneral_manager_dept_id(transaData.getTransa().getGeneral_manager_dept_id());
        }
        transaData.setCustomer(customer);
    }

    /**
     * @Title: generateTransaData
     * @Description: 生成
     * @param transaData 签单数据对象
     * @param time 当前时间
     * @author: jinzhm
     * @time:2017年6月20日 下午4:00:11
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
     */
    private void generateTransaData(SignTransaData transaData, Timestamp time)
    {
        transaData.getTransa().setBill_code(CodeNoUtil.getEnviNoteCode());
        transaData.getTransa().setId_card(transaData.getCustomer().getId_card());
        transaData.getTransa().setCus_name(transaData.getCustomer().getCus_name());
        // 设置性别及生日
        setCustomerGenderAndBirthDate(transaData);
        transaData.getTransa().setCus_address(transaData.getCustomer().getCus_address());
        transaData.getTransa().setMobile_phone(transaData.getCustomer().getMobile_phone());
        transaData.getTransa().setContact_address(transaData.getCustomer().getContact_address());
        transaData.getTransa().setCustomer_email(transaData.getCustomer().getCustomer_email());
        // 账单默认发送方式 1电邮地址 2居住地址 3通讯地址 设置为通讯地址
        transaData.getTransa().setBill_send_mode("3");

        // 查找该客户是否之前投资过该产品
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_card", transaData.getTransa().getId_card());
        paramMap.put("wms_inve_pruduct_category_id", transaData.getTransaProd().getWms_inve_pruduct_category_id());
        // 投资过该产品设置为追单
        if (SignHelper.getWmsInveTransaProdDao().calRecordNum(paramMap) > 0)
        {
            transaData.getTransa().setInve_transa_type("2");
        }
        // 未投资过该产品设置为上单
        else
        {
            transaData.getTransa().setInve_transa_type("1");
        }
        // 投资金额大写
        transaData.getTransa()
                  .setProduct_total_count_upper(digitUpperUtil.digitUppercase(transaData.getTransa()
                                                                                        .getProduct_total_count_lower()
                                                                                        .doubleValue(), false));
        // ptp单据直接为收益中
        transaData.getTransa().setData_status("4");
        // 支付状态设置成已到账
        transaData.getTransa().setAccount_status("3");
        // 支付操作人主键
        transaData.getTransa().setPay_usr_id(transaData.getTransa().getSalesman_id());
        // 支付金额合计
        transaData.getTransa().setPay_account(transaData.getTransa().getProduct_total_count_lower());
        // 现金支付人姓名为客户姓名
        transaData.getTransa().setCash_pay_name(transaData.getTransa().getCus_name());
        // 到账操作人主键
        transaData.getTransa().setAct_account_usr_id(transaData.getTransa().getSalesman_id());
        // 到账时间
        transaData.getTransa().setDate_of_act(transaData.getTransa().getDate_of_payment());
        // 到账金额合计
        transaData.getTransa().setAct_account(transaData.getTransa().getProduct_total_count_lower());
        // 手续费
        transaData.getTransa().setFee_account(BigDecimal.ZERO);
        // 创建用户主键
        transaData.getTransa().setCreate_user_id(transaData.getTransa().getSalesman_id());
        // 创建时间
        transaData.getTransa()
                  .setCreate_timestamp(new Timestamp(transaData.getTransa().getDate_of_payment().getTime()));
        // 创建用户姓名
        transaData.getTransa().setCreate_user_name(transaData.getTransa().getSalesman_name());
        // 创建用户部门id
        transaData.getTransa().setCreate_user_dept_id(transaData.getTransa().getSalesman_dept_id());
        // 修改用户主键
        transaData.getTransa().setLast_update_user_id(transaData.getTransa().getSalesman_id());
        // 修改时间
        transaData.getTransa().setLast_update_timestamp(time);
        // 有效标志
        transaData.getTransa().setEnable_flag("1");
        // 支付方式 ptp设置支付方式为现金
        transaData.getTransa().setPay_type(4);
        // crm客户主键
        transaData.getTransa().setCostomer_id(transaData.getCustomer().getCostomer_id());
        // 客户来源
        transaData.getTransa()
                  .setCustomer_source(StringUtils.isNotEmpty(transaData.getCustomer().getCustomer_source()) ? Integer.parseInt(transaData.getCustomer()
                                                                                                                                         .getCustomer_source())
                                                                                                           : null);
        // 客户编号
        transaData.getTransa().setCustomer_num(transaData.getCustomer().getCustomer_num());
        // 单据来源 空表示是上单单据
        transaData.getTransa().setBill_source(0);
        // 是否预约赎回
        transaData.getTransa().setIs_order_redeem("0");
        // 预约的财务合同编号和理财财务合同编号在ptp单据中都没有
        transaData.getTransa().setOrder_financial_bill_code(null);
        transaData.getTransa().setFinancial_bill_code(null);
        // 是否预约续期
        transaData.getTransa().setIs_order_extend("0");
        // 收益到账提醒
        transaData.getTransa().setIncome_account("0");
        // 理财到期提醒
        transaData.getTransa().setExpiration_reminder("0");
        // 债权到期提醒
        transaData.getTransa().setDebt_expires("0");
        // 线上/线下合同设置成线下合同
        transaData.getTransa().setContract_signing_type("1");
        // 查询crm客户信息设置，ptp单据都是身份证查询
        transaData.getTransa().setCrm_name_query_type("0");
        transaData.getTransa().setCrm_mobile_query_type("0");
        transaData.getTransa().setCrm_id_card_query_type("0");
        transaData.getTransa().setCrm_create_timestamp(DateUtil.strTimestamp(transaData.getCustomer()
                                                                                       .getCrm_create_timestamp(),
                                                                             "yyyy-MM-dd HH:mm:ss"));
        // 是否为续期本金必须单 设置为否
        transaData.getTransa().setIs_extend_amount("0");
        // 单据类型，设置成ptp产品
        transaData.getTransa().setTransa_type(SignHelper.PTP_TRANSA);
    }

    /**
     * @Title: getCustomerInfoByIdCard
     * @Description: 根据身份证号调用crm端获得客户经理信息
     * @param idCard 身份证号
     * @param bel_salesman_id 客户经理id
     * @return 客户信息
     * @throws ClientProtocolException
     * @throws IOException 
     * @author: jinzhm
     * @time:2017年6月20日 下午1:54:24
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getCustomerInfoByIdCard(String idCard, String bel_salesman_id)
    {
        // 客户信息
        Map<String, Object> cusData = null;
        try
        {

            // 设置调用接口信息
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_001"));
            nvps.add(new BasicNameValuePair("sys_num", "WMS"));
            nvps.add(new BasicNameValuePair("user_id", "0"));

            // 联系电话
            nvps.add(new BasicNameValuePair("contact_number", ""));
            // 客户姓名
            nvps.add(new BasicNameValuePair("customer_name", ""));
            // 身份证号码
            nvps.add(new BasicNameValuePair("id_card_number", idCard.toUpperCase()));

            // 封装调用crm发送请求信息
            HttpPost httpPost = new HttpPost(GlobalVal.EKP_LOGIN_URL + "/modi/restful/simple");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);

            // 读取crm返回的响应信息
            InputStreamReader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
            BufferedReader rd = new BufferedReader(reader);
            String result_str = rd.readLine();
            JSONObject obj = JSONObject.parseObject(result_str);

            // 获得crm客户信息集合
            List<Map<String, Object>> crmList = (List<Map<String, Object>>) obj.get("result");

            // 如果客户信息集合不为null
            if (crmList != null)
            {
                // 客户信息集合长度为1
                if (crmList.size() == 1)
                {
                    // 根据返回的客户经理id查找客户经理信息封装后返回
                    Map<String, Object> crmData = crmList.get(0);
                    PmPersonnel pmPersonnel = SignHelper.getPmPersonnelDao().get((Integer) crmData.get("personnel_id"));
                    crmData.put("salesman_info", pmPersonnel);
                    cusData = crmData;
                }
                // 如果有多条记录
                else
                {
                    // 循环客户信息集合
                    for (Map<String, Object> crmData : crmList)
                    {
                        // 如果crm返回的客户信息集合中的客户经理id等于ptp传入的客户经理id
                        if (bel_salesman_id.equals(crmData.get("personnel_id")))
                        {
                            // 获得客户经理信息，封装客户经理信息后返回
                            PmPersonnel pmPersonnel = SignHelper.getPmPersonnelDao()
                                                                .get((Integer) crmData.get("personnel_id"));
                            crmData.put("salesman_info", pmPersonnel);
                            cusData = crmData;
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("调用crm获取客户信息出现错误！", e);
            throw new RuntimeException("调用crm获取客户信息出现错误！");
        }
        // 如果调用crm后获得的客户信息为null
        if (cusData == null)
        {
            log.error("调用crm没有获取到客户信息！");
            throw new RuntimeException("调用crm没有获取到客户信息！");
        }
        return cusData;
    }

}
