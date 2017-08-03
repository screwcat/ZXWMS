//package com.zx.emanage.inve.util.transa;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.time.DateUtils;
//
//import com.zx.emanage.inve.util.CountIncomeFactory;
//import com.zx.emanage.sysmanage.util.CodeNoUtil;
//import com.zx.emanage.sysmanage.util.UserCityUtil;
//import com.zx.emanage.util.gen.entity.PmPersonnel;
//import com.zx.emanage.util.gen.entity.WmsInveCommissionPerformanceHis;
//import com.zx.emanage.util.gen.entity.WmsInveCustomer;
//import com.zx.emanage.util.gen.entity.WmsInveTransa;
//import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
//import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
//import com.zx.platform.syscontext.util.StringUtil;
//import com.zx.sframe.util.DateUtil;
//import com.zx.sframe.util.vo.UserBean;
//
///**
// * 版权所有：版权所有(C) 2016，卓信金控
// * 系统名称：财富管理平台
// * @ClassName: SignShareHolderTransa
// * 模块名称：
// * @Description: 内容摘要：
// * @author jinzhm
// * @date 2017年6月5日
// * @version V1.0
// * history:
// * 1、2017年6月5日 jinzhm 创建文件
// */
//public class SignShareHolderTransa extends SignAbstract
//{
//
//    /**
//     * @Title: beforeSign
//     * @Description: 签单股东单据前前验证或数据处理
//     * @param transaData 签单数据
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月5日 下午3:51:26
//     * @see com.zx.emanage.inve.util.transa.SignAbstract#beforeSign(com.zx.emanage.inve.util.transa.SignTransaData)
//     * history:
//     * 1、2017年6月5日 jinzhm 创建方法
//    */
//    @Override
//    protected String beforeSign(SignTransaData transaData)
//    {
//        // 检验投资金额是否满足产品设置的追单和上单限制条件
//        String result = checkTransaCatoryInvestment(transaData);
//
//        // 如果投资金额有不满足产品设置的追单和上单限制信息直接返回
//        if (!SignHelper.SUCCESS.equals(result))
//        {
//            return result;
//        }
//
//        // 给单据设置客户性别及生日
//        setCustomerGenderAndBirthDate(transaData);
//
//        // 校验业务员信息
//        result = checkSalesmanInfo(transaData);
//        // 如果业务员信息不存在直接返回提示
//        if (!SignHelper.SUCCESS.equals(result))
//        {
//            return result;
//        }
//
//        return SignHelper.SUCCESS;
//    }
//
//    /**
//     * @Title: sign
//     * @Description: 股东签单
//     *  1. 修改客户信息
//     *  2. 保存单据信息
//     *  3. 保存单据产品信息
//     *  4. 保存收益卡信息
//     *  5. 保存支付信息
//     *  6. 保存单据协议信息
//     *  7. 保存股东新佣金系数
//     *  8. 保存股东自动续期信息
//     *  9. 保存股东不打收益信息
//     *  10. 生成收益信息
//     *  11. 根据到期时间判断，如需要续期则续期，且同时生成收益信息
//     *  12. 续期全部完成后进行佣金his表是否需要插入数据判断，如需要则插入（入股时间（续期后的）月份已结账，往当前要结账的月份的上个月插入）
//     * @param transaData 签单数据
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月5日 下午3:51:26
//     * @see com.zx.emanage.inve.util.transa.SignAbstract#sign(com.zx.emanage.inve.util.transa.SignTransaData)
//     * history:
//     * 1、2017年6月5日 jinzhm 创建方法
//    */
//    @Override
//    protected String sign(SignTransaData transaData)
//    {
//        // 定义当前系统时间变量
//        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//
//        // 检查客户表里是否存在记录
//        WmsInveCustomer wmsInveCustomer = null;
//        String id_card = transaData.getCustomer().getId_card();
//        Map<String, Object> paramMap = new HashMap<>();
//        if (id_card != null && !"".equals(id_card))
//        {
//            paramMap.put("id_card", id_card);
//            wmsInveCustomer = SignHelper.getWmsInveCustomerDao().getByEntity(paramMap);
//        }
//
//        // 如果客户信息不存在
//        if (wmsInveCustomer == null)
//        {
//            // 判断客户id是否为空
//            if (transaData.getTransa().getCostomer_id() == null)
//            {
//                /**
//                 *  wms客户信息表中不存在通过传入的身份证号查询的客户信息
//                 *  传入的签单数据中也没有crm客户主键
//                 *  说明存在问题，返回错误
//                 */
//                // return SignHelper.ERROR11;
//                throw new IllegalArgumentException("通过传入身份证号没有找到wms系统客户信息，且也没有传入crm客户主键！！！");
//            }
//            // 如果传入的单据信息中costomerId不为空
//            else
//            {
//                transaData.getCustomer().setWms_inve_customer_id(transaData.getTransa().getCostomer_id());
//            }
//            // 设置wms系统客户信息
//            setCustomerInfo(transaData, currentTime);
//            // 新增客户信息
//            SignHelper.getWmsInveCustomerDao().save(transaData.getCustomer());
//        }
//        else
//        {
//            // 如果通过id_card获取到客户信息,则设置更新客户信息时间
//            transaData.getCustomer().setLast_update_timestamp(currentTime);
//            // 设置更新客户信息操作人
//            transaData.getCustomer().setLast_update_user_id(transaData.getUser().getUserId());
//            // 更行客户信息
//            SignHelper.getWmsInveCustomerDao().update(transaData.getCustomer());
//            // 定义map类型的参数变量
//            paramMap = new HashMap<>();
//
//            // 判断有效证件号是否为空
//            if (id_card != null && !"".equals(id_card))
//            {
//                // 根据有效证件号进行获取客户信息
//                paramMap.put("id_card", id_card);
//                wmsInveCustomer = SignHelper.getWmsInveCustomerDao().getByEntity(paramMap);
//                transaData.setCustomer(wmsInveCustomer);
//            }
//        }
//
//        // 封装单据信息
//        setWmsInveTransaData(transaData, currentTime);
//        // 保存单据信息
//        SignHelper.getWmsInveTransaDao().save(transaData.getTransa());
//
//        // 保存收益卡信息
//        newAddIncomeCard(transaData, currentTime);
//
//        // 封装签单产品信息
//        setWmsInveTransaProdInfo(transaData, currentTime);
//        // 保存签单产品信息
//        SignHelper.getWmsInveTransaProdDao().save(transaData.getTransaProd());
//
//        // 生成支付信息
//        WmsInveTransaCard transaCard = generateTransaCardInfo(transaData, currentTime);
//        SignHelper.getWmsInveTransaCardDao().save(transaCard);
//
//        // 保存单据老协议信息
//        WmsInveTransaProtocol protocol = generateTransaProtocol(transaData, currentTime);
//        SignHelper.getWmsInveTransaProtocolDao().save(protocol);
//
//        // 保存股东信用金系数表数据
//        generateShareHolderCommissionSpecial(transaData, currentTime);
//
//        // 保存股东单据自动续期信息
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("bill_code", transaData.getTransa().getBill_code());
//        SignHelper.getWmsInveTransaDao().insertShareHolderTransaAutoExtend(dataMap);
//
//        // 保存股东单据不打收益信息
//        dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("bill_code", transaData.getTransa().getBill_code());
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_timestamp", currentTime);
//        SignHelper.getWmsInveTransaIncomeDao().insertShareHolderTransaIncome(dataMap);
//
//        // 生成股东单据收益信息
//        generateTransaIncomeAndLog(protocol, transaData.getUser());
//
//        // 续期
//        int transaId = generateExtendTransa(transaData, protocol, currentTime);
//
//        // 佣金计算his表
//        generateCommissionPerformanceHis(transaData, transaId);
//
//        return SignHelper.SUCCESS;
//    }
//
//    /**
//     * @Title: generateCommissionPerformanceHis
//     * @Description: 生成佣金his数据 <b>次方法没有完全实现，需求修改，如重新使用需要实现</b>
//     *      需要判断结账日期
//     *      6月4日结账5月份佣金
//     *      6月4日-6月30日
//     *          6月份上单不插入his数据
//     *          <=5月份上单插入5月份数据
//     *      6月1日-6月3日
//     *          上5月份单据不插入his数据
//     *          <=4月份上单那插入4月数据
//     * @param transaData
//     * @param transaId 
//     * @author: jinzhm
//     * @time:2017年6月7日 下午4:03:17
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//     */
//    private void generateCommissionPerformanceHis(SignTransaData transaData, int transaId)
//    {
//        WmsInveTransa transa = SignHelper.getWmsInveTransaDao().get(transaId);
//        // 查找结账信息
//        Map<String, Object> jobRule = SignHelper.getWmsInveTransaUpdateLogDao()
//                                                             .getTransaJobTime(DateUtil.date2String(transa.getDate_of_payment(),
//                                                                                                    "yyyy-MM"));
//
//        if (jobRule != null)
//        {
//            // 获得结账日期
//            Date ruleDate = (Date) jobRule.get("job_date");
//            // 当前日期
//            Date nowDate = new Date(DateUtil.getDate10(new java.util.Date()).getTime());
//            // 当前日期大于结账日期说明当月已经结账
//            if (nowDate.compareTo(ruleDate) > 0)
//            {
//                // 如果入股当月佣金已经结账需要往当前月份的上个月插入
//                WmsInveCommissionPerformanceHis cph = new WmsInveCommissionPerformanceHis();
//                cph.setSalesman_name(transa.getSalesman_name());
//                cph.setStatics_month(DateUtil.date2String(DateUtils.addMonths(nowDate, -1), "yyyy-MM"));
//
//                cph.setTax_point(BigDecimal.ZERO);
//                cph.setData_status("6");
//                cph.setEnable_flag("1");
//
//                // cph.setTeam_personnel_id(obj);
//
//                cph.setWms_inve_pruduct_category_id(transaData.getCategory().getWms_inve_pruduct_category_id());
//
//                PmPersonnel personnel = SignHelper.getPmPersonnelDao().get(transa.getBel_salesman_id_id());
//                cph.setTrans_salesman_status("7".equals(personnel.getPersonnel_state()) ? "2" : "1");
//
//                cph.setBel_salesman_id_id(transa.getBel_salesman_id_id());
//                cph.setBel_salesman_dept_id(transa.getBel_salesman_dept_id());
//                cph.setBel_department_manager_id(transa.getBel_center_manager_id());
//                cph.setBel_department_manager_dept_id(transa.getBel_center_manager_dept_id());
//                cph.setBel_vice_general_manager_id(transa.getBel_vice_general_manager_id());
//                cph.setBel_vice_general_manager_dept_id(transa.getBel_vice_general_manager_dept_id());
//                cph.setBel_center_manager_id(transa.getBel_center_manager_id());
//                cph.setBel_center_manager_dept_id(transa.getBel_center_manager_dept_id());
//                cph.setBel_general_manager_id(transa.getBel_general_manager_id());
//                cph.setBel_general_manager_dept_id(transa.getBel_general_manager_dept_id());
//                cph.setCenter_manager_id(transa.getCenter_manager_id());
//                cph.setCenter_manager_dept_id(transa.getCenter_manager_dept_id());
//                // 设置为特殊产品
//                cph.setIs_special_rules("1");
//            }
//        }
//    }
//
//    /**
//     * @Title: generateExtendTransa
//     * @Description: 生成续期单据
//     *      当股东上单的单据的到期时间在小于当前时间时需要给股东单据进行续期，直到当前新单据不是超期状态为止
//     * @param transaData 签单数据
//     * @param protocol 协议信息
//     * @param time 系统时间
//     * @return 最新上单主键
//     * @author: jinzhm
//     * @time:2017年6月7日 下午3:31:17
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//     */
//    private int generateExtendTransa(SignTransaData transaData, WmsInveTransaProtocol protocol, Timestamp time)
//    {
//        // 获得到期日期和当前日期
//        Date endOfDate = protocol.getEnd_of_date();
//        Date nowDate = new Date(DateUtil.getDate10(new java.util.Date()).getTime());
//
//        int transaId = transaData.getTransa().getWms_inve_transa_id();
//        java.util.Date dateOfPayment = DateUtils.addMonths(transaData.getTransa().getDate_of_payment(), 12);
//        /**
//         * 不用判断是否是2016-07-01之前还是之后上单，所有单据都是当前系统日期大于到期日期时才续期。
//         */
//        // 判断当前日期是否大于理财到期日期，大于的话需要续期
//        while (nowDate.compareTo(endOfDate) > 0)
//        {
//            // 调用续期方法
//            SignHelper.getWmsInveExtendService().inveInveExtendSave(null, transaId, dateOfPayment, null, 0, "1", "",
//                                                                    "", transaData.getUser(), null);
//            // 获得新的单据信息
//            WmsInveTransa transa = SignHelper.getWmsInveTransaDao().getTransaByOldLastTransaId(transaId);
//
//            // 更新单据主键
//            transaId = transa.getWms_inve_transa_id();
//            dateOfPayment = DateUtils.addMonths(transa.getDate_of_payment(), 12);
//
//            // 获得协议信息，更新协议到期日期
//            WmsInveTransaProtocol transaProtocol = SignHelper.getTransaProtocol(transaId);
//            endOfDate = transaProtocol.getEnd_of_date();
//        }
//        return transaId;
//    }
//
//    /**
//     * @Title: generateTransaIncomeAndLog
//     * @Description: 股东单据生成收益及交易日志信息
//     * @param protocol 协议对象
//     * @param user 登录用户
//     * @author: jinzhm
//     * @time:2017年6月7日 下午2:10:32
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//     */
//    private void generateTransaIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user)
//    {
//        CountIncomeFactory.getCountIncome(protocol).getIncomeAndLog(protocol, user);
//    }
//
//    /**
//     * @Title: generateShareHolderCommissionSpecial
//     * @Description: 生成股东单据计算拥挤提点系数数据
//     * @param transaData 签单数据
//     * @param time 系统时间
//     * @author: jinzhm
//     * @time:2017年6月7日 上午9:50:01
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//     */
//    private void generateShareHolderCommissionSpecial(SignTransaData transaData, Timestamp time)
//    {
//        // 客户经理提点系数
//        BigDecimal scc = transaData.getSalesman_commission_coefficient();
//        // 部门经理提点系数
//        BigDecimal dmcc = transaData.getDept_manager_commission_coefficient();
//        // 如果是否计算佣金设置为不计算，则将获得的提点系数设置成0
//        if ("0".equals(transaData.getTransa().getIs_compute_commission()))
//        {
//            scc = BigDecimal.ZERO;
//            dmcc = BigDecimal.ZERO;
//        }
//        else
//        {
//            scc = scc.divide(new BigDecimal(100));
//            dmcc = dmcc.divide(new BigDecimal(100));
//        }
//
//        // 封装股东单据提点系数
//        List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        // 新增奖
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("comm_rate", scc);
//        dataMap.put("wms_inve_comm_item_id", 1);
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_datetime", time);
//        dataMap.put("enable_flag", "1");
//        dataMapList.add(dataMap);
//        // 个人存量将
//        dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("comm_rate", scc);
//        dataMap.put("wms_inve_comm_item_id", 2);
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_datetime", time);
//        dataMap.put("enable_flag", "1");
//        dataMapList.add(dataMap);
//        // 部门存量将
//        dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("comm_rate", dmcc);
//        dataMap.put("wms_inve_comm_item_id", 3);
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_datetime", time);
//        dataMap.put("enable_flag", "1");
//        dataMapList.add(dataMap);
//        // 经理存量将
//        dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("comm_rate", dmcc);
//        dataMap.put("wms_inve_comm_item_id", 4);
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_datetime", time);
//        dataMap.put("enable_flag", "1");
//        dataMapList.add(dataMap);
//        // 后线领导管理奖
//        dataMap = new HashMap<String, Object>();
//        dataMap.put("wms_inve_transa_id", transaData.getTransa().getWms_inve_transa_id());
//        dataMap.put("comm_rate", dmcc);
//        dataMap.put("wms_inve_comm_item_id", 5);
//        dataMap.put("create_user_id", transaData.getUser().getUserId());
//        dataMap.put("create_datetime", time);
//        dataMap.put("enable_flag", "1");
//        dataMapList.add(dataMap);
//        // 批量插入提点系数信息
//        SignHelper.getWmsInveAchievementReportDao().batchInsertShareHolderCommission(dataMapList);
//    }
//
//    /**
//     * @Title: generateTransaProtocol
//     * @Description: 生成签单老协议信息
//     * @param transaData 签单数据
//     * @param time 系统时间
//     * @return 签单老协议
//     * @author: jinzhm
//     * @time:2017年6月7日 上午8:40:20
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//     */
//    private WmsInveTransaProtocol generateTransaProtocol(SignTransaData transaData, Timestamp time)
//    {
//        WmsInveTransaProtocol transaProtocol = new WmsInveTransaProtocol();
//
//        // 设置上单主键，上单产品主键及赎回主键
//        transaProtocol.setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
//        transaProtocol.setWms_inve_transa_prod_id(transaData.getTransaProd().getWms_inve_transa_prod_id());
//        transaProtocol.setWms_inve_redeem_id(0);
//        // 设置协议编号
//        transaProtocol.setProt_code(CodeNoUtil.getEnviProCode());
//        // 甲方姓名
//        transaProtocol.setA_name("测试甲");
//        // 甲方身份证号
//        transaProtocol.setA_id_card("111111111111111111");
//        // 上单金额
//        transaProtocol.setProduct_account(transaData.getTransaProd().getProduct_account());
//        // 支付日期
//        transaProtocol.setDate_of_payment(transaData.getTransa().getDate_of_payment());
//        // 甲方开户银行及甲方账号
//        transaProtocol.setA_bank("111111111111111111");
//        transaProtocol.setA_number("111111111111111111");
//        // 乙方姓名及乙方身份证号
//        transaProtocol.setB_name(transaData.getTransa().getCus_name());
//        transaProtocol.setB_id_card(transaData.getTransa().getId_card());
//        // 产品名称，产品期限
//        transaProtocol.setCategory_name(transaData.getTransaProd().getCategory_name());
//        transaProtocol.setProduct_deadline(transaData.getTransaProd().getProduct_deadline());
//        // 协议签署地及乙方资料
//        transaProtocol.setSign_place("111111111111111111");
//        transaProtocol.setB_data("111111111111111111");
//        // 协议到期时间
//        transaProtocol.setEnd_of_date(new Date(SignHelper.computeEndOfDate(null,
//                                                                           transaData.getTransa().getDate_of_payment(),
//                                                                           transaData.getTransaProd()
//                                                                                     .getProduct_deadline()).getTime()));
//        // 创建数据及更新数据
//        transaProtocol.setCreate_user_id(transaData.getUser().getUserId());
//        transaProtocol.setCreate_user_name(transaData.getUser().getRealname());
//        transaProtocol.setCreate_timestamp(time);
//        transaProtocol.setLast_update_user_id(transaData.getUser().getUserId());
//        transaProtocol.setLast_update_timestamp(time);
//        // 是否有效，通讯地址，邮政编码
//        transaProtocol.setEnable_flag("1");
//        transaProtocol.setContact_address("111111111111111111");
//        transaProtocol.setPost_code("111111");
//        return transaProtocol;
//    }
//
//    /**
//     * @Title: generateTransaCardInfo
//     * @Description: 股东单据生成现金支付信息
//     * @param transaData 签单数据
//     * @param time 系统时间
//     * @return 支付信息
//     * @author: jinzhm
//     * @time:2017年6月6日 下午5:01:05
//     * history:
//     * 1、2017年6月6日 jinzhm 创建方法
//     */
//    private WmsInveTransaCard generateTransaCardInfo(SignTransaData transaData, Timestamp time)
//    {
//        WmsInveTransaCard transaCard = new WmsInveTransaCard();
//        // 单据主键
//        transaCard.setWms_inve_transa_id(transaData.getTransa().getWms_inve_transa_id());
//        // pos机信息主键直接设置成0
//        transaCard.setWms_inve_pos_id("0");
//        // 支付金额
//        transaCard.setPay_account(transaData.getTransaProd().getProduct_account());
//        // 是否已到账
//        transaCard.setIs_finish("1");
//        // 到账金额
//        transaCard.setAct_account(transaData.getTransaProd().getProduct_account());
//        // 创建人及创建时间
//        transaCard.setCreate_user_id(transaData.getUser().getUserId());
//        transaCard.setCreate_user_name(transaData.getUser().getRealname());
//        transaCard.setCreate_timestamp(time);
//        // 是否有效
//        transaCard.setEnable_flag("1");
//        // 支付方式设置成现金支付
//        transaCard.setPay_type("1");
//        // 应支付金额
//        transaCard.setShould_pay_account(transaData.getTransaProd().getProduct_account());
//        // 是否生效
//        transaCard.setIs_valid("1");
//        return transaCard;
//    }
//
//    /**
//     * @Title: setCustomerInfo
//     * @Description: 设置客户信息
//     * @param transaData 签单数据
//     * @param time 当前时间
//     * @author: jinzhm
//     * @time:2017年6月6日 上午9:59:36
//     * @see com.zx.emanage.inve.util.transa.SignAbstract#setCustomerInfo(com.zx.emanage.inve.util.transa.SignTransaData, java.sql.Timestamp)
//     * history:
//     * 1、2017年6月6日 jinzhm 创建方法
//    */
//    @Override
//    protected void setCustomerInfo(SignTransaData transaData, Timestamp time)
//    {
//        super.setCustomerInfo(transaData, time);
//    }
//
//    /**
//     * @Title: setWmsInveTransaData
//     * @Description: 设置单据信息
//     * @param transaData 签单数据
//     * @param time 系统时间
//     * @author: jinzhm
//     * @time:2017年6月6日 上午8:46:49
//     * @see com.zx.emanage.inve.util.transa.SignAbstract#setWmsInveTransaData(com.zx.emanage.inve.util.transa.SignTransaData, java.sql.Timestamp)
//     * history:
//     * 1、2017年6月6日 jinzhm 创建方法
//    */
//    @Override
//    protected void setWmsInveTransaData(SignTransaData transaData, Timestamp time)
//    {
//        if (transaData.getCustomer() != null)
//        {
//            transaData.getTransa().setWms_inve_customer_id(transaData.getCustomer().getWms_inve_customer_id());
//        }
//        // 是否使用续单本金金额设置为不使用
//        transaData.getTransa().setIs_extend_amount("0");
//        // 单据来源 0上单
//        transaData.getTransa().setBill_source(0);
//        // 设置理财单据编号
//        transaData.getTransa().setBill_code(CodeNoUtil.getEnviNoteCode());
//        // 股东单据上单时单据状态直接设置成收益中
//        transaData.getTransa().setData_status("4");
//        // 股东单据上单时支付操作人设置为当前登录人
//        transaData.getTransa().setPay_usr_id(transaData.getUser().getUserId());
//        // 股东单据上单时支付状态直接设置成已到账
//        transaData.getTransa().setAccount_status("3");
//        // 支付金额合计
//        transaData.getTransa().setPay_account(transaData.getTransaProd().getProduct_account());
//        // 到账操作人主键
//        transaData.getTransa().setAct_account_usr_id(transaData.getUser().getUserId());
//        // 实际到账时间
//        transaData.getTransa().setDate_of_act(transaData.getTransa().getDate_of_payment());
//        // 实际到账金额
//        transaData.getTransa().setAct_account(transaData.getTransaProd().getProduct_account());
//        // 手续费
//        transaData.getTransa().setFee_account(BigDecimal.ZERO);
//        // 支付方式:股东单据支付方式都设置为现金
//        transaData.getTransa().setPay_type(4);
//        // 发送方式设置为居住地址
//        transaData.getTransa().setBill_send_mode("2");
//
//        // 发送提醒通知设置
//        transaData.getTransa().setIncome_account("0");
//        transaData.getTransa().setExpiration_reminder("0");
//        transaData.getTransa().setDebt_expires("0");
//        // 股东单据设置为线下合同
//        transaData.getTransa().setContract_signing_type("1");
//
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id_card", transaData.getTransa().getId_card());
//        paramMap.put("wms_inve_pruduct_category_id", transaData.getTransaProd().getWms_inve_pruduct_category_id());
//
//        if (SignHelper.getWmsInveTransaProdDao().calRecordNum(paramMap) > 0)
//        {
//            transaData.getTransa().setInve_transa_type("2");
//        }
//        else
//        {
//            transaData.getTransa().setInve_transa_type("1");
//        }
//
//        if (StringUtil.isNotBlank(transaData.getTransa().getSalesman_city_code()))
//        {
//            transaData.getTransa().setSalesman_city(UserCityUtil.getUserCity(transaData.getTransa()
//                                                                                       .getSalesman_city_code()));
//        }
//        transaData.getTransa().setCreate_user_id(transaData.getUser().getUserId());
//        transaData.getTransa().setCreate_user_name(transaData.getUser().getRealname());
//        transaData.getTransa().setCreate_user_dept_id(transaData.getUser().getDeptId());
//        transaData.getTransa().setCreate_timestamp(time);
//        transaData.getTransa().setLast_update_user_id(transaData.getUser().getUserId());
//        transaData.getTransa().setLast_update_timestamp(time);
//        transaData.getTransa().setEnable_flag("1");
//    }
//
//}
