package com.zx.emanage.inve.util.transa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SignAbstract
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月5日
 * @version V1.0
 * history:
 * 1、2017年6月5日 jinzhm 创建文件
 */
public abstract class SignAbstract implements SignTransa
{

    /**
     * @Title: sign
     * @Description: 签单
     * @param transaData 签单数据
     * @return 成功或失败标志，成功返回success
     * @author: jinzhm
     * @time:2017年6月5日 下午3:32:07
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected abstract Map<String, Object> sign(SignTransaData transaData);

    /**
     * @Title: transa
     * @Description: 签单
     * @param transaData 签单数据
     * @return 成功或失败标志，成功返回success
     * @author: jinzhm
     * @time:2017年6月5日 下午3:28:32
     * @see com.zx.emanage.inve.util.transa.SignTransa#transa(com.zx.emanage.inve.util.transa.SignTransaData)
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> transa(SignTransaData transaData)
    {
        initTransaData(transaData);
        return sign(transaData);
    }

    /**
     * @Title: transa
     * @Description: 默认实现返回空集合的批量签单方法，具体实现在子类中继承并重写
     * @param transaData 
     * @return 
     * @author: jinzhm
     * @time:2017年6月20日 上午11:27:00
     * @see com.zx.emanage.inve.util.transa.SignTransa#transa(java.util.List)
     * history:
     * 1、2017年6月20日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> transa(List<SignTransaData> transaData)
    {
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * @Title: initTransaData
     * @Description: 初始化一些处理时需要用到的数据
     * @param transaData 单据数据
     * @author: jinzhm
     * @time:2017年6月5日 下午4:10:24
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected void initTransaData(SignTransaData transaData)
    {
        // 设置产品信息
        transaData.setCategory(SignHelper.getWmsInvePruductCategoryDao()
                                         .get(transaData.getTransaProd().getWms_inve_pruduct_category_id()));
    }

    /**
     * @Title: setCustomerGenderAndBirthDate
     * @Description: 根据身份证号设置单据中的客户性别及生日
     * @param transaData 签单数据
     * @author: jinzhm
     * @time:2017年6月5日 下午4:29:10
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected void setCustomerGenderAndBirthDate(SignTransaData transaData)
    {
        // 根据身份证号嘛获取客户的性别和出生日期,判断身份证号18位
        if (transaData.getTransa().getId_card() != null && transaData.getTransa().getId_card().length() == 18)
        {
            // 判断客户性别,偶数为女奇数为男
            String sex = transaData.getTransa().getId_card().substring(16, 17);
            transaData.getTransa().setCus_gender(Integer.parseInt(sex) % 2 == 0 ? "0" : "1");
            // 获取身份证号出生日期
            String birth = transaData.getTransa().getId_card().substring(6, 14);
            Date birthDate = DateUtil.strDate(birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-"
                                                      + birth.substring(6, 8), null);
            transaData.getTransa().setCus_birthday(new java.sql.Date(birthDate.getTime()));

        }
        else if (transaData.getTransa().getId_card() != null && transaData.getTransa().getId_card().length() == 15)
        {
            // 判断身份证号15位
            // 判断客户性别
            String sex = transaData.getTransa().getId_card().substring(14, 15);
            transaData.getTransa().setCus_gender(Integer.parseInt(sex) % 2 == 0 ? "0" : "1");
            // 获取身份证号出生日期
            String birth = transaData.getTransa().getId_card().substring(6, 12);
            Date birthDate = DateUtil.strDate("19" + birth.substring(0, 2) + "-" + birth.substring(2, 4) + "-"
                                                      + birth.substring(4, 6), null);
            transaData.getTransa().setCus_birthday(new java.sql.Date(birthDate.getTime()));
        }
    }

    /**
     * @Title: checkTransaCatoryInvestment
     * @Description: 检验客户最单金额是否符合条件及上单金额是否满足条件
     * @param transaData
     * @return 
     * @author: jinzhm
     * @time:2017年6月5日 下午4:13:25
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected String checkTransaCatoryInvestment(SignTransaData transaData)
    {
        // 查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("id_card", transaData.getTransa().getId_card());
        paramMap.put("wms_inve_pruduct_category_id", transaData.getTransaProd().getWms_inve_pruduct_category_id());
        // 判断当前客户上单是否为追单
        if (SignHelper.getWmsInveTransaProdDao().calRecordNum(paramMap) > 0)
        {
            // 判断客户追单金额不能低于最小值
            if (transaData.getCategory().getCategory_additional_monery_min() != null
                && transaData.getTransaProd().getProduct_account().doubleValue() < transaData.getCategory()
                                                                                             .getCategory_additional_monery_min()
                                                                                             .doubleValue() * 10000)
            {
                return "error1";
            }
            // 判断追单金额不能超过最大值
            if (transaData.getCategory().getCategory_additional_monery_max() != null
                && transaData.getTransaProd().getProduct_account().doubleValue() > transaData.getCategory()
                                                                                             .getCategory_additional_monery_max()
                                                                                             .doubleValue() * 10000)
            {
                return "error2";
            }

            // 判断追单金额总和不能超过产品最大持有金额
            if (transaData.getCategory().getMaximum_holding_amount() != null)
            {
                paramMap.put("data_status1", "4");
                paramMap.put("data_status2", "5");
                List<Map<String, Object>> products = SignHelper.getWmsInveTransaProdDao().search(paramMap);
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
                if (transaData.getCategory().getMaximum_holding_amount() != null
                    && sumZdje > transaData.getCategory().getMaximum_holding_amount().doubleValue() * 10000)
                {
                    return "error5";
                }
            }
        }
        else
        {
            // 判断上单金额不能低于最小值
            if (transaData.getCategory().getCategory_investment_money_min() != null
                && transaData.getTransaProd().getProduct_account().doubleValue() < transaData.getCategory()
                                                                                             .getCategory_investment_money_min()
                                                                                             .doubleValue() * 10000)
            {
                return "error3";
            }
            // 判断上单金额不能超过最大值
            if (transaData.getCategory().getCategory_investment_money_max() != null
                && transaData.getTransaProd().getProduct_account().doubleValue() > transaData.getCategory()
                                                                                             .getCategory_investment_money_max()
                                                                                             .doubleValue() * 10000)
            {
                return "error4";
            }
            // 判断上单金额不能超过产品最大持有金额
            if (transaData.getCategory().getMaximum_holding_amount() != null
                && transaData.getTransaProd().getProduct_account().doubleValue() > transaData.getCategory()
                                                                                             .getMaximum_holding_amount()
                                                                                             .doubleValue() * 10000)
            {
                return "error6";
            }
        }

        // 判断当前上单产品所有金额是否大于等于当前产品的最大限制金额
        // (1) 如果当前产品的上单金额已经大于等于当前产品的最大限制金额,需要将当前产品设置成禁用
        if (transaData.getCategory().getCategory_maximum_amount() != null)
        {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("wms_inve_pruduct_category_id", transaData.getCategory().getWms_inve_pruduct_category_id());

            int sumCategoryInveTransaPayaccount = SignHelper.getWmsInveTransaDao()
                                                            .getSumCategoryInveTransaPayaccountByCategoryId(paramsMap);

            sumCategoryInveTransaPayaccount += transaData.getTransaProd().getProduct_account().doubleValue();
            // (2) 判断当前上单记录所上单的金额加上已经上单的总金额大于等于当前产品的最大限制金额则将当前产品禁用
            if (sumCategoryInveTransaPayaccount >= transaData.getCategory().getCategory_maximum_amount().doubleValue() * 10000)
            {
                WmsInvePruductCategory disableWmsInveProductCategory = new WmsInvePruductCategory();
                disableWmsInveProductCategory.setWms_inve_pruduct_category_id(transaData.getCategory()
                                                                                        .getWms_inve_pruduct_category_id());
                disableWmsInveProductCategory.setIs_forbidden("1");
                SignHelper.getWmsInvePruductCategoryDao().update(disableWmsInveProductCategory);
            }
        }
        return SignHelper.SUCCESS;
    }

    /**
     * @Title: checkSalesmanInfo
     * @Description: 校验业务员信息是否存在并设置各个总信息
     * @param transaData 签单数据
     * @return 检验是否成功标志
     * @author: jinzhm
     * @time:2017年6月5日 下午5:29:16
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    @SuppressWarnings("unchecked")
    protected String checkSalesmanInfo(SignTransaData transaData)
    {
        // 封装查询业务员信息数据
        Map<String, Object> queryPersonnelParams = new HashMap<String, Object>();
        if (transaData.getTransa().getSalesman_id() != null)
        {
            queryPersonnelParams.put("personnel_id", transaData.getTransa().getSalesman_id());
        }
        if (transaData.getTransa().getSalesman_shortcode() != null)
        {
            queryPersonnelParams.put("personnel_shortCode", transaData.getTransa().getSalesman_shortcode());
        }

        Map<String, Object> personnelmap = SignHelper.getPmPersonnelDao().getPersonnelInfos(queryPersonnelParams);
        if (personnelmap == null || personnelmap.size() == 0)
        {
            return SignHelper.ERROR9;
        }
        // 业务员状态
        String personnel_state = "1";
        if ("7".equals("" + personnelmap.get("personnel_state")))
        {
            personnel_state = "2";
        }

        // 设置业务员id及业务员状态
        transaData.getTransa().setSalesman_id(Integer.parseInt(personnelmap.get("personnel_id").toString()));
        transaData.getTransa().setTrans_salesman_status(personnel_state);

        // 查找人员信息
        Map<String, Object> renYuanXinXiMap = findPersonnelInformationByPersonnelId(Integer.parseInt(personnelmap.get("personnel_id")
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
        transaData.getTransa().setSalesman_city_code("" + personnelmap.get("personnel_regionnumber"));
        if (StringUtil.isNotBlank(transaData.getTransa().getSalesman_city_code()))
        {
            transaData.getTransa().setSalesman_city(UserCityUtil.getUserCity(transaData.getTransa()
                                                                                       .getSalesman_city_code()));
        }
        transaData.getTransa().setSalesman_name("" + personnelmap.get("personnel_name"));
        transaData.getTransa().setSalesman_dept_id(Integer.parseInt(personnelmap.get("personnel_deptid").toString()));
        transaData.getTransa().setSalesman_shortcode("" + personnelmap.get("personnel_shortcode"));
        transaData.getTransa().setBel_salesman_id_id(Integer.parseInt(yeWuYuan.get("personnel_id").toString()));
        transaData.getTransa().setBel_salesman_dept_id(Integer.parseInt(yeWuYuan.get("personnel_deptId").toString()));

        // 设置部门经理相关信息
        transaData.getTransa().setDepartment_manager_id(buMenJingLi.getPersonnel_id());
        transaData.getTransa().setDepartment_manager_city_code(buMenJingLi.getPersonnel_regionnumber());
        transaData.getTransa().setDepartment_manager_dept_id(buMenJingLi.getPersonnel_deptid());
        transaData.getTransa().setBel_department_manager_dept_id(buMenJingLi.getPersonnel_deptid());
        transaData.getTransa().setBel_department_manager_id(buMenJingLi.getPersonnel_id());

        // 设置中分总相关信息
        transaData.getTransa().setCenter_manager_dept_id(zhongFenZong.getPersonnel_deptid());
        transaData.getTransa().setCenter_manager_id(zhongFenZong.getPersonnel_id());
        transaData.getTransa().setBel_center_manager_dept_id(zhongFenZong.getPersonnel_deptid());
        transaData.getTransa().setBel_center_manager_id(zhongFenZong.getPersonnel_id());

        // 设置副总相关信息
        transaData.getTransa().setVice_general_manager_city_code(fuZhong.getPersonnel_regionnumber());
        transaData.getTransa().setVice_general_manager_id(fuZhong.getPersonnel_id());
        transaData.getTransa().setVice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
        transaData.getTransa().setBel_vice_general_manager_dept_id(fuZhong.getPersonnel_deptid());
        transaData.getTransa().setBel_vice_general_manager_id(fuZhong.getPersonnel_id());

        // 设置总的相关信息
        transaData.getTransa().setGeneral_manager_city_code(zong.getPersonnel_regionnumber());
        transaData.getTransa().setGeneral_manager_dept_id(zong.getPersonnel_deptid());
        transaData.getTransa().setGeneral_manager_id(zong.getPersonnel_id());
        transaData.getTransa().setBel_general_manager_dept_id(zong.getPersonnel_deptid());
        transaData.getTransa().setBel_general_manager_id(zong.getPersonnel_id());
        return SignHelper.SUCCESS;
    }

    /**
     * @Title: findPersonnelInformationByPersonnelId
     * @Description: 根据业务员id及职务信息查询各个总信息
     * @param personne_id 业务员信息
     * @param concurrent_post_deptid 职务部门信息
     * @return 各个总信息
     * @author: jinzhm
     * @time:2017年6月5日 下午5:25:24
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected Map<String, Object> findPersonnelInformationByPersonnelId(int personne_id, int concurrent_post_deptid)
    {
        Map<String, Object> personnelMap = (Map<String, Object>) SignHelper.getPmPersonnelDao()
                                                                           .getPersonnelByPersonnelId(personne_id);
        Map<String, Object> map = new HashMap<String, Object>();
        PmPersonnel queryInfo = new PmPersonnel();
        queryInfo.setPersonnel_id(Integer.parseInt("" + personnelMap.get("personnel_id")));
        // queryInfo.setPersonnel_deptid(concurrent_post_deptid);
        Map<String, Object> mapYeWuYuan = (Map<String, Object>) SignHelper.getPmPersonnelDao()
                                                                          .getPersonnelByPersonnelIdAndDeptId(queryInfo);
        mapYeWuYuan.put("personnel_deptId", concurrent_post_deptid);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("personnel_id", Integer.parseInt("" + mapYeWuYuan.get("personnel_id")));
        paramsMap.put("personnel_deptid", concurrent_post_deptid);
        PmPersonnel buMenJingLi = (PmPersonnel) SignHelper.getPmPersonnelDao().getBuMenJingLiByDeptId(paramsMap);
        PmPersonnel zhongFenZong = (PmPersonnel) SignHelper.getPmPersonnelDao().getZhongFenZongByDeptId(paramsMap);
        PmPersonnel fuZhong = (PmPersonnel) SignHelper.getPmPersonnelDao().getFuZongByDeptId(paramsMap);
        PmPersonnel zong = (PmPersonnel) SignHelper.getPmPersonnelDao().getZongByPersonnelInfo(paramsMap);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("personnel_id", personne_id);
        paramMap.put("personnel_deptid", concurrent_post_deptid);

        if (buMenJingLi == null)
        {
            buMenJingLi = new PmPersonnel();
        }
        paramMap.put("type", 1);
        buMenJingLi.setPersonnel_deptid(SignHelper.getPmPersonnelDao().getDeptId(paramMap));
        if (zhongFenZong == null)
        {
            zhongFenZong = new PmPersonnel();
        }
        paramMap.put("type", 2);
        zhongFenZong.setPersonnel_deptid(SignHelper.getPmPersonnelDao().getDeptId(paramMap));
        if (fuZhong == null)
        {
            fuZhong = new PmPersonnel();
        }
        paramMap.put("type", 3);
        fuZhong.setPersonnel_deptid(SignHelper.getPmPersonnelDao().getDeptId(paramMap));
        map.put("yeWuYuan", mapYeWuYuan);
        map.put("buMenJingLi", buMenJingLi);
        map.put("zhongFenZong", zhongFenZong);
        map.put("fuZhong", fuZhong);
        map.put("zong", zong);
        return map;
    }

    /**
     * @Title: checkCustomerInfo
     * @Description: 检验客户信息
     *      1. 通过传入的客户身份证号查询wms系统中是否存在该客户信息
     *      2. 如果wms系统中不存在该客户信息，检验是否传入了crm系统的客户主键
     *      3. 如果传入了该客户主键给单据信息中的wms系统客户主键设置成该主键
     *      4. 如果没有传入crm系统的客户主键，根据传入的客户信息调用crm接口创建客户
     *      5. 调用crm接口创建客户失败，返回失败信息
     *      6. 调用crm接口创建客户成功，给单据信息和客户信息设置客户相关信息
     * @param transaData
     * @return 
     * @author: jinzhm
     * @time:2017年6月5日 下午4:52:24
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected String checkCustomerInfo(SignTransaData transaData)
    {
        // 检查客户表里是否存在记录
        WmsInveCustomer wmsInveCustomer = null;
        String id_card = transaData.getCustomer().getId_card();
        Map<String, Object> paramMap = new HashMap<>();
        if (id_card != null && !"".equals(id_card))
        {
            paramMap.put("id_card", id_card);
            wmsInveCustomer = SignHelper.getWmsInveCustomerDao().getByEntity(paramMap);
        }

        // 如果客户信息不存在
        if (wmsInveCustomer == null)
        {
            // 判断客户id是否为空
            if (transaData.getTransa().getCostomer_id() == null)
            {

                // 客户id如果为空,则进行新增客户信息,需要将客户信息发送到crm,并且crm给返回客户id和客户编号
                Map<String, Object> resMap = newAddCostomerToCrmBackCostomerId(transaData.getTransa());

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
                    transaData.getTransa().setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置上单表中客户编号
                    transaData.getTransa().setCustomer_num((String) resMap.get("customer_num"));
                    // 设置上单表中客户来源
                    transaData.getTransa().setCustomer_source(1);
                    // 设置crm创建时间
                    transaData.getTransa().setCrm_create_timestamp(new java.sql.Timestamp(System.currentTimeMillis()));
                    // 设置客户表中的客户id
                    transaData.getCustomer().setCostomer_id((Integer) resMap.get("costomer_id"));
                    // 设置客户表中客户编号
                    transaData.getCustomer().setCustomer_num((String) resMap.get("customer_num"));
                    // 设置客户表中客户来源
                    transaData.getCustomer().setCustomer_source("1");
                }
            }
            // 如果传入的单据信息中costomerId不为空
            else
            {
                transaData.getCustomer().setWms_inve_customer_id(transaData.getTransa().getCostomer_id());
            }
        }
        return SignHelper.SUCCESS;
    }

    /**
     * @Title: newAddCostomerToCrmBackCostomerId
     * @Description: 调用crm接口进行新增客户信息
     * @param transa 签单数据
     * @return 新增客户结果信息
     * @author: jinzhm
     * @time:2017年6月5日 下午4:45:34
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected Map<String, Object> newAddCostomerToCrmBackCostomerId(WmsInveTransa transa)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 调用crm接口
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "CRM_OUT_PUTCustomerInformation"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        nvps.add(new BasicNameValuePair("user_id", "0"));

        Map<String, Object> queryPersonnelParams = new HashMap<>();
        if (transa.getSalesman_shortcode() != null)
        {
            queryPersonnelParams.put("personnel_shortCode", transa.getSalesman_shortcode());
        }
        if (transa.getSalesman_id() != null)
        {
            queryPersonnelParams.put("personnel_id", transa.getSalesman_id());
        }

        Map<String, Object> pmPersonnel = SignHelper.getPmPersonnelDao().getPersonnelInfos(queryPersonnelParams);

        nvps.add(new BasicNameValuePair("customer_name", transa.getCus_name()));
        nvps.add(new BasicNameValuePair("contact_number", transa.getMobile_phone()));
        nvps.add(new BasicNameValuePair("id_card_number", transa.getId_card()));
        nvps.add(new BasicNameValuePair("email_adress", transa.getCustomer_email()));
        nvps.add(new BasicNameValuePair("domicile_place", transa.getContact_address()));
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
     * @Title: setWmsInveTransaData
     * @Description: 设置单据信息
     * @param transaData 签单数据
     * @param time 系统时间
     * @author: jinzhm
     * @time:2017年6月5日 下午5:39:37
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    protected void setWmsInveTransaData(SignTransaData transaData, Timestamp time)
    {
        if (transaData.getCustomer() != null)
        {
            transaData.getTransa().setWms_inve_customer_id(transaData.getCustomer().getWms_inve_customer_id());
        }
        transaData.getTransa().setBill_code(CodeNoUtil.getEnviNoteCode());
        transaData.getTransa().setData_status("2");
        transaData.getTransa().setAccount_status("1");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_card", transaData.getTransa().getId_card());
        paramMap.put("wms_inve_pruduct_category_id", transaData.getTransaProd().getWms_inve_pruduct_category_id());
        if (SignHelper.getWmsInveTransaProdDao().calRecordNum(paramMap) > 0)
        {
            transaData.getTransa().setInve_transa_type("2");
        }
        else
        {
            transaData.getTransa().setInve_transa_type("1");
        }
        if (StringUtil.isNotBlank(transaData.getTransa().getSalesman_city_code()))
        {
            transaData.getTransa().setSalesman_city(UserCityUtil.getUserCity(transaData.getTransa()
                                                                                       .getSalesman_city_code()));
        }
        transaData.getTransa().setCreate_user_id(transaData.getUser().getUserId());
        transaData.getTransa().setCreate_user_name(transaData.getUser().getRealname());
        transaData.getTransa().setCreate_user_dept_id(transaData.getUser().getDeptId());
        transaData.getTransa().setCreate_timestamp(time);
        transaData.getTransa().setLast_update_user_id(transaData.getUser().getUserId());
        transaData.getTransa().setLast_update_timestamp(time);
        transaData.getTransa().setEnable_flag("1");
    }

    /**
     * @Title: setWmsInveTransaProdInfo
     * @Description: 设置签单产品信息
     * @param transaData 签单数据
     * @param time 系统时间
     * @author: jinzhm
     * @time:2017年6月6日 下午1:18:05
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    protected void setWmsInveTransaProdInfo(SignTransaData transaData, Timestamp time)
    {
        transaData.getTransaProd().setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
        transaData.getTransaProd().setIs_finish("0");
        transaData.getTransaProd().setCreate_user_id(transaData.getUser().getUserId());
        transaData.getTransaProd().setCreate_user_name(transaData.getUser().getRealname());
        transaData.getTransaProd().setCreate_timestamp(time);
        transaData.getTransaProd().setLast_update_user_id(transaData.getUser().getUserId());
        transaData.getTransaProd().setLast_update_timestamp(time);
        transaData.getTransaProd().setEnable_flag("1");
        transaData.getTransaProd().setOrg_product_account(transaData.getTransaProd().getProduct_account());
        if (transaData.getTransaProd().getProduct_deadline() != null)
        {
            transaData.getTransaProd().setProduct_deadline_name(transaData.getTransaProd().getProduct_deadline() + "期");
        }

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_pruduct_category_id", transaData.getTransaProd().getWms_inve_pruduct_category_id());
        paramsMap.put("product_account", transaData.getTransaProd().getProduct_account());
        paramsMap.put("product_dealine", transaData.getTransaProd().getProduct_deadline());

        Map<String, Object> map = SignHelper.getWmsInveTransaDao().getCategoryIncomeByCategoryId(paramsMap);
        if (map != null && map.size() > 0)
        {
            if (map.get("reward_interest") != null)
            {
                transaData.getTransaProd().setReward_interest(new BigDecimal(map.get("reward_interest").toString()));
            }
            if (map.get("expect_interest_account") != null)
            {
                transaData.getTransaProd().setExpect_interest_account(new BigDecimal(map.get("expect_interest_account")
                                                                                        .toString()));
            }
        }
    }

    /**
     * @Title: setCustomerInfo
     * @Description: 设置客户信息
     * @param transaData 签单数据
     * @param time 当前时间
     * @author: jinzhm
     * @time:2017年6月6日 上午9:06:09
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    protected void setCustomerInfo(SignTransaData transaData, Timestamp time)
    {
        transaData.getCustomer().setId_card(transaData.getTransa().getId_card());
        transaData.getCustomer().setCus_code(CodeNoUtil.getEnviCustomerCode());
        transaData.getCustomer().setCus_name(transaData.getTransa().getCus_name());
        transaData.getCustomer().setCus_birthday(transaData.getTransa().getCus_birthday());
        transaData.getCustomer().setCus_gender(transaData.getTransa().getCus_gender());
        transaData.getCustomer().setCus_address(transaData.getTransa().getCus_address());
        transaData.getCustomer().setPost_code(transaData.getTransa().getPost_code());
        transaData.getCustomer().setAddress_phone(transaData.getTransa().getAddress_phone());
        transaData.getCustomer().setWork_phone(transaData.getTransa().getWork_phone());
        transaData.getCustomer().setCus_fax(transaData.getTransa().getCus_fax());
        transaData.getCustomer().setContact_address(transaData.getTransa().getContact_address());
        transaData.getCustomer().setCustomer_email(transaData.getTransa().getCustomer_email());
        transaData.getCustomer().setSalesman_name(transaData.getTransa().getSalesman_name());
        transaData.getCustomer().setSalesman_id(transaData.getTransa().getSalesman_id());
        transaData.getCustomer().setSalesman_city_code(transaData.getTransa().getSalesman_city_code());
        transaData.getCustomer().setSalesman_city(transaData.getUser().getUser_city());
        transaData.getCustomer().setSalesman_dept_id(transaData.getTransa().getSalesman_dept_id());
        transaData.getCustomer().setCreate_user_dept_id(transaData.getUser().getDeptId());
        transaData.getCustomer().setCreate_user_id(transaData.getUser().getUserId());
        transaData.getCustomer().setCreate_user_name(transaData.getUser().getRealname());
        transaData.getCustomer().setCreate_timestamp(time);
        transaData.getCustomer().setLast_update_user_id(transaData.getUser().getUserId());
        transaData.getCustomer().setLast_update_timestamp(time);
        transaData.getCustomer().setEnable_flag("1");
        transaData.getCustomer().setMobile_phone(transaData.getTransa().getMobile_phone());
    }

    /**
     * @Title: newAddIncomeCard
     * @Description: 保存收益卡信息
     * @param transaData
     * @param time
     * @author: jinzhm
     * @time:2017年6月6日 下午1:24:46
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    protected void newAddIncomeCard(SignTransaData transaData, Timestamp time)
    {
        // 保存收益卡信息
        WmsInveCustomerCard wmsInveCustomerCard = new WmsInveCustomerCard();
        wmsInveCustomerCard.setBank_branch(transaData.getTransaProd().getBank_branch());
        wmsInveCustomerCard.setBank_of_deposit(transaData.getTransaProd().getBank_of_deposit());
        wmsInveCustomerCard.setBank_of_deposit_city(transaData.getTransaProd().getBank_of_deposit_city());
        wmsInveCustomerCard.setBank_of_deposit_pro(transaData.getTransaProd().getBank_of_deposit_pro());
        wmsInveCustomerCard.setCard_no(transaData.getTransaProd().getCard_no());
        wmsInveCustomerCard.setCard_owner_name(transaData.getTransaProd().getCard_owner_name());
        wmsInveCustomerCard.setId_card(transaData.getTransa().getId_card());
        // 设置创建时间
        wmsInveCustomerCard.setCreate_timestamp(time);
        // 设置创建人
        wmsInveCustomerCard.setCreate_user_id(transaData.getUser().getUserId());
        // 设置是否有效标识
        wmsInveCustomerCard.setEnable_flag("1");

        // 根据收益卡信息获取收益卡信息是否存在重复的数据
        List<WmsInveCustomerCard> list = SignHelper.getWmsInveCustomerCardDao()
                                                   .getIncomeCardRepeat(wmsInveCustomerCard);

        // 定义收益卡信息表主键变量
        Integer wms_inve_customer_card_id = 0;

        // 判断是否存在重复的数据
        if (list != null && list.size() > 0)
        {

            // 遍历收益卡集合,更新收益卡信息
            for (WmsInveCustomerCard card : list)
            {
                card.setBank_branch(wmsInveCustomerCard.getBank_branch());
                card.setCard_no(wmsInveCustomerCard.getCard_no());
                card.setId_card(wmsInveCustomerCard.getId_card());
                card.setCard_owner_name(wmsInveCustomerCard.getCard_owner_name());
                card.setBank_of_deposit(wmsInveCustomerCard.getBank_of_deposit());
                card.setBank_of_deposit_city(wmsInveCustomerCard.getBank_of_deposit_city());
                card.setBank_of_deposit_pro(wmsInveCustomerCard.getBank_of_deposit_pro());

                // 更新收益卡信息
                SignHelper.getWmsInveCustomerCardDao().update(card);

            }
            wms_inve_customer_card_id = list.get(0).getWms_inve_customer_card_id();
        }
        else
        {
            // 保存收益卡信息
            SignHelper.getWmsInveCustomerCardDao().save(wmsInveCustomerCard);

            // 得到保存收益卡信息表主键
            wms_inve_customer_card_id = wmsInveCustomerCard.getWms_inve_customer_card_id();
        }

        transaData.getTransaProd().setWms_inve_customer_card_id(wms_inve_customer_card_id);
    }
}
