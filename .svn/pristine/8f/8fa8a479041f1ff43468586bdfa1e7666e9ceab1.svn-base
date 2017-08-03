package com.zx.emanage.inve.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveCreditEmailDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaUpdateLogDao;
import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveSignedApplicationService;
import com.zx.emanage.inve.service.IWmsInveTransaCrepkgService;
import com.zx.emanage.inve.service.IWmsInveTransaIncomeService;
import com.zx.emanage.inve.service.IWmsInveTransaService;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.util.credit.CreditUtils;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolVO;
import com.zx.emanage.inve.web.WmsInveClerkProtocolController;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveClerkData;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocolTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveCreditEmail;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.JasperUtil;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveclerkprotocolService")
public class WmsInveClerkProtocolServiceImpl implements IWmsInveClerkProtocolService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveClerkProtocolServiceImpl.class);

    @Autowired
    private WmsInveClerkProtocolDao wmsinveclerkprotocolDao;

    @Autowired
    private IWmsInveSignedApplicationService signedApplicationService;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;

    @Autowired
    private WmsInveTransaCrepkgDao wmsInveTransaCrepkgDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao; // 上单产品信息表

    @Autowired
    private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveTransaUpdateLogDao wmsinvetransaupdatelogDao;

    @Autowired
    private SysSpecialUserDao specialUserDao;

    @Autowired
    private IWmsInveTransaCrepkgService wmsInveTransaCrepkgService;

    @Autowired
    private WmsInveCreditEmailDao wmsInveCreditEmailDao;

    @Autowired
    private IWmsInveTransaService wmsInveTransaService;

    @Autowired
    private WmsSysPropertyDao wmsSysPropertyDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysDeptDao sysdeptDao;

    @Autowired
    private WmsInveRedeemDao wmsinveredeemDao;

    @Autowired
    private WmsInveClerkProtocolTransaCrepkgDao wmsInveClerkProtocolTransaCrepkgdDao;

    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;

    @Autowired
    private IWmsInveTransaIncomeService wmsInveTransaIncomeService;
    /**
     * @Title: getWmsInveClerkProtocolId
     * @Description: 获得柜员协议表id
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param type 数据类型： 2续期单据，3赎回单据
     * @return 获得协议主键
     * @author: jinzhm
     * @time:2017年2月20日 上午8:23:46
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolId(java.lang.Integer, int)
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
    */
    @Override
    public Integer getWmsInveClerkProtocolId(Integer transaId, BigDecimal productAccount, int type)
    {
        // 如果是续期单据，查找续期单据协议信息返回主键
        if (type == 2)
        {
            return wmsinveclerkprotocolDao.getWmsInveExtendProtocolByTransaId(transaId).get(0)
                                          .getWms_inve_clerk_protocol_id();
        }
        // 如果是赎回单据，查找赎回单据协议信息返回主键
        else if (type == 3)
        {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transaId", transaId);
            paramMap.put("productAccount", productAccount);
            return wmsinveclerkprotocolDao.getWmsInveRedeemProtocolByTransaId(paramMap).get(0)
                                          .getWms_inve_clerk_protocol_id();
        }
        return -1;
    }

    /**
     * @Title: changeProtocolCredit
     * @Description: 更换债权包信息
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param categoryId 产品主键
     * @param user 登录用户信息
     * @return 债权更换结果
     *  1表示匹配成功
     *  2表示产品不是可拆分类型
     *  3表示没有要到期的债权包
     *  -1表示债权匹配失败
     * @author: jinzhm
     * @time:2017年2月15日 上午11:26:39
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#changeProtocolCredit(int, int, int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
    */
    @Override
    @Transactional
    public int changeProtocolCredit(int transaId, int protocolId, int categoryId, UserBean user)
    {
        return CreditBusiness.getInstance().changeMatchedCredit(transaId, protocolId, categoryId, user);
    }

    /**
     * @Title: autoChangeCreditForTransaWithoutPaperProtocol
     * @Description: 为不提供纸质合同的单据自动更换到期债权信息 
     * @author: jinzhm
     * @time:2017年7月18日 下午1:51:18
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#autoChangeCreditForTransaWithoutPaperProtocol()
     * history:
     * 1、2017年7月18日 jinzhm 创建方法
    */
    @Override
    public void autoChangeCreditForTransaWithoutPaperProtocol()
    {
        // 查询所有无纸质合同单据
        List<Map<String, Object>> transaProtocolInfoList = wmsinveclerkprotocolDao.getWithoutPaperProtocolTransaProtocolInfoList();
        // 循环所有无纸质合同单据，进行更换债权操作
        for (int i = 0; i < transaProtocolInfoList.size(); i++)
        {
            // 上单主键
            int transaId = Integer.parseInt(String.valueOf(transaProtocolInfoList.get(i).get("wms_inve_transa_id")));
            // 柜员协议主键
            int protocolId = Integer.parseInt(String.valueOf(transaProtocolInfoList.get(i)
                                                                                   .get("wms_inve_clerk_protocol_id")));
            // 产品主键
            int categoryId = Integer.parseInt(String.valueOf(transaProtocolInfoList.get(i)
                                                                                   .get("wms_inve_pruduct_category_id")));
            // 设置登录人默认为马越
            UserBean user = new UserBean();
            user.setRealname("马越");
            user.setUserId(125);

            try
            {
                // 更换债权
                int result = CreditBusiness.getInstance().changeMatchedCredit(transaId, protocolId, categoryId, user);
                // 根据返回条件进行具体的日志输出
                switch (result)
                {
                // 不是可拆分产品
                    case 2:
                        log.debug("上单主键：" + transaId + "；柜员合同主键：" + protocolId + "；产品主键：" + categoryId
                                  + "；不是可拆分类产品单据，没有进行债权更换！");
                        break;
                    // 没有到期债权
                    case 3:
                        log.debug("上单主键：" + transaId + "；柜员合同主键：" + protocolId + "；产品主键：" + categoryId + "；没有到期债权需要更换！");
                        break;
                }
            }
            catch (Exception e)
            {
                // 判断是不是债权不足报错， 是的话进行债权不足日志
                if ("债权更换时，债权不足！".equals(e.getMessage()))
                {
                    log.debug("上单主键：" + transaId + "；柜员合同主键：" + protocolId + "；产品主键：" + categoryId + "；债权更换时，债权不足！");
                }
                // 判断不是债权不足报错，说明是其他错误，进行日志
                else
                {
                    log.debug("上单主键：" + transaId + "；柜员合同主键：" + protocolId + "；产品主键：" + categoryId + "；", e);
                }
            }
        }
    }

    @Override
    public Map<String, Object> getWmsInveClerkProtocolById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        // 通过传入的合同id 查询合同相关信息
        Map<String, Object> map = wmsinveclerkprotocolDao.getWmsInveClerkProtocolById(wmsInveClerkProtocolSearchBeanVO);
        // 将小写数字转成大写放到map中
        if (map.get("sum_cre_pledge_mon_lower") != null)
        {
            map.put("sum_cre_pledge_mon_upper", digitUpperUtil.digitUppercase(((BigDecimal) map.get("sum_cre_pledge_mon_lower")).doubleValue(), true).replaceAll("万元", ""));
        }

        return map;
    }

    /**
     * @Title: save
     * @Description: 上单时合同打印调用
     * 1、更新上单表的date_of_payment和合同编码
     * 2、生成柜员合同表数据、更新债权匹配历史表数据、更新债权历史表的合同编号
     * 3、调用流程保存方法
     * 4、更新柜员单据表是否完成为已完成状态
     * @param begin_of_date
     * @param user
     * @return 
     * @author: Guanxu
     * @time:2017年2月14日 上午10:44:49
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#save(java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月14日 Guanxu 创建方法
    */
    @Override
    @Transactional
    public String save(String begin_of_date, Integer wms_inve_transa_id, UserBean user, String protocol_type,
                       String taskId, String prot_code)
    {

        // 判断合同生效日期>=实际支付日期
        WmsInveTransa wmsInveTransa = wmsInveTransaDao.get(wms_inve_transa_id);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            String result = wmsInveTransaUpdateLogValidate(df.parse(begin_of_date));
            if ("error".equals(result))
            {
                return "date_error_check";
            }
            // 合同签订时，合同生效时间设置往后修改限制片段
            String days = wmsSysPropertyDao.get(CreditUtils.DAY_RANGE_PROPERTY_ID).getProperty_value();
            if (df.parse(begin_of_date).compareTo(DateUtil.getDateAddDays(wmsInveTransa.getDate_of_act(),
                                                                          Integer.parseInt(days))) > 0)
            {
                return "date_error_check";
            }
            if (df.parse(begin_of_date).compareTo(wmsInveTransa.getDate_of_act()) < 0)
            {
                return "date_error";
            }
            //验证线下合同编号是否有重复
            if (isProtCodedup(prot_code))
            {
                return "prot_code_error";
            }
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 1生成柜员合同表数据 2线下合同释放债权
        saveWmsInveClerkProtocol(begin_of_date, wms_inve_transa_id, protocol_type, prot_code, user);
        // 更新date_of_payment和合同编码
        updateWmsInvetransaDateOfPaymentById(begin_of_date, wms_inve_transa_id);
        // 更新柜员单据表状态为已完成
        updateWmsInveClerkDataStatus(wms_inve_transa_id);
        // 更新上单表中合同签订情况类型（2为线上合同，1为线下合同） jinzhm 2017-03-14添加
        if ("2".equals(protocol_type))
        {
            updateWmsInveTransaSigningType(wms_inve_transa_id, "1");
        }
        // TODO Auto-generated method stub
        String resultStr = signedApplicationService.sigendProd(wms_inve_transa_id.toString(), taskId, user.getUserId()
                                                                                                          .toString());

        return resultStr;
    }

    /**
     * @Title: updateWmsInveTransaSigningType
     * @Description: 更改单据的协议签订方式
     * @param transaId 单据主键
     * @param signingType 1表示线下合同，2表示线上合同
     * @author: jinzhm
     * @time:2017年3月14日 下午1:37:16
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
     */
    private void updateWmsInveTransaSigningType(int transaId, String signingType)
    {
        WmsInveTransa transa = new WmsInveTransa();
        transa.setWms_inve_transa_id(transaId);
        transa.setContract_signing_type(signingType);
        wmsInveTransaDao.update(transa);
    }

    private String wmsInveTransaUpdateLogValidate(Date changeDate)
    {
        // 获得支付时间
        Date nowDate = new java.sql.Date(DateUtil.getDate10(new java.util.Date()).getTime());

        // 查找要修改到日期的结账时间配置信息
        Map<String, Object> changeDateRuleMap = wmsinvetransaupdatelogDao.getTransaJobTime(DateUtil.date2String(changeDate, "yyyy-MM"));

        // 如果要修改到的日期月份有结账配置信息
        if (changeDateRuleMap != null)
        {
            // 获得结账日期
            Date ruleDate = (Date) changeDateRuleMap.get("job_date");
            // 如果当前时间和上单时间小于等于结账时间
            if (nowDate.compareTo(ruleDate) > 0)
            {
                return "error";
            }
        }

        return "success";
    }


    /**
     * @Title: getWmsInveClerkProtocolList
     * @Description: 客户合同查询
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午2:59:00
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolList(com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO)
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveClerkProtocolList(WmsInveClerkProtocolSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            // 客户经理
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }

            // 部门经理
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }

            // 总
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }

            // 副总
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", 110);
                paramMap.put("deptIds_user_id", user.getUserId());
            }

            // 柜员
            if (role.equals("财务柜员"))
            {
                // 可以查看自己所在区域的单据(传入当前柜员的id,根据当前柜员id进行获取区域)
                paramMap.put("personnel_id", user.getUserId());
            }

            // 合同专员
            if (role.equals("理财合同专员"))
            {
                // 可以查看自己所在区域的单据(传入当前柜员的id,根据当前柜员id进行获取区域)
                paramMap.put("personnel_id", user.getUserId());
            }
        }

        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", queryInfo.getMobile_phone());
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getProt_code()))
        {
            paramMap.put("prot_code", queryInfo.getProt_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getBegin_of_date_begin()))
        {
            paramMap.put("begin_of_date_begin", queryInfo.getBegin_of_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getBegin_of_date_end()))
        {
            paramMap.put("begin_of_date_end", queryInfo.getBegin_of_date_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getEnd_of_date_begin()))
        {
            paramMap.put("end_of_date_begin", queryInfo.getEnd_of_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getEnd_of_date_end()))
        {
            paramMap.put("end_of_date_end", queryInfo.getEnd_of_date_end());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveclerkprotocolDao.getWmsInveClerkProtocolList(paramMap);

        // 实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveclerkprotocolDao.findCountProtocol(paramMap), list);
        return bean.getGridData();
    }
    
    /**
     * @Title: getWmsInveClerkProtocolWithoutpagingList
     * @Description: 客户合同查询 不分页
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午2:59:00
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolList(com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO)
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveClerkProtocolWithoutpagingList(WmsInveClerkProtocolSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", queryInfo.getBill_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", queryInfo.getId_card());
        }
        if (StringUtil.isNotBlank(queryInfo.getCus_name()))
        {
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMobile_phone()))
        {
            paramMap.put("mobile_phone", queryInfo.getMobile_phone());
        }
        if (StringUtil.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getProt_code()))
        {
            paramMap.put("prot_code", queryInfo.getProt_code());
        }
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", queryInfo.getSalesman_name());
        }
        if (StringUtil.isNotBlank(queryInfo.getBegin_of_date_begin()))
        {
            paramMap.put("begin_of_date_begin", queryInfo.getBegin_of_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getBegin_of_date_end()))
        {
            paramMap.put("begin_of_date_end", queryInfo.getBegin_of_date_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getEnd_of_date_begin()))
        {
            paramMap.put("end_of_date_begin", queryInfo.getEnd_of_date_begin());
        }
        if (StringUtil.isNotBlank(queryInfo.getEnd_of_date_end()))
        {
            paramMap.put("end_of_date_end", queryInfo.getEnd_of_date_end());
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinveclerkprotocolDao.getWmsInveClerkProtocolList(paramMap);
        // 加密id_card
        for (Map<String, Object> map : list)
        {
            String idCard = (String) map.get("id_card");
            if (idCard != null)
            {
                if (!idCard.equals("") && idCard.length() == 18)
                {
                    map.put("id_card", idCard.substring(0, 3) + "********" + idCard.substring(15, idCard.length()));
                }
                else if (!idCard.equals(""))
                {
                    map.put("id_card", "********");
                }
            }
        }

        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsinveclerkprotocolDao.findCountProtocol(paramMap), list);
        return bean.getGridData();
    }

    /**
     * @Title: updateProtocolActEndOfDateById
     * @Description: 根据合同表主键更新柜员合同表实际到期日为当前时间
     * @param zhangyunfei 
     * @author: Administrator
     * @time:2017年2月15日 下午1:05:50
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#updateWmsInveClerkProtocol(com.zx.emanage.util.gen.entity.WmsInveClerkProtocol)
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    @Override
    public void updateProtocolActEndOfDateById(Integer wms_inve_clerk_protocol_id)
    {
        wmsinveclerkprotocolDao.updateProtocolActEndOfDateById(wms_inve_clerk_protocol_id);
    }

    /**
     * @Title: getWmsInveClerkProtocolByEntity
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveClerkProtocol
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午1:33:57
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolByEntity(com.zx.emanage.util.gen.entity.WmsInveClerkProtocol)
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    @Override
    public WmsInveClerkProtocol getWmsInveClerkProtocolByEntity(WmsInveClerkProtocol wmsInveClerkProtocol)
    {
        return wmsinveclerkprotocolDao.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
    }

    /**
     * @Title: updateWmsInveClerkDataStatus
     * @Description: 更新柜员业务表状态为已完成
     * @param wms_inve_transa_id 
     * @author: zhangyunfei
     * @time:2017年2月16日 上午9:15:51
     * history:
     * 1、2017年2月16日 Administrator 创建方法
     */
    private void updateWmsInveClerkDataStatus(Integer wms_inve_transa_id)
    {
        WmsInveClerkData wmsInveClerkData = new WmsInveClerkData();
        wmsInveClerkData.setWms_inve_transa_id(wms_inve_transa_id);
        // 单据状态为1 上单单据
        wmsInveClerkData.setIs_finished("1");
        wmsInveClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        // jinzhm 想2017-03-23修改
        wmsInveClerkDataDao.updateByTransaId(wmsInveClerkData);
        // wmsInveClerkDataDao.update(wmsInveClerkData);
    }

    /**
     * @Title: updateWmsInvetransaDateOfPaymentById
     * @Description: 根据上单主键更新date_of_payment和合同编码
     * @param begin_of_date
     * @param wms_inve_transa_id 
     * @author: zhangyunfei
     * @time:2017年2月16日 上午9:09:58
     * history:
     * 1、2017年2月16日 Administrator 创建方法
     */
    private void updateWmsInvetransaDateOfPaymentById(String begin_of_date, Integer wms_inve_transa_id)
    {
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        wmsInveClerkProtocol.setWms_inve_transa_id(wms_inve_transa_id);
        wmsInveClerkProtocol = wmsinveclerkprotocolDao.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
        WmsInveTransa wmsInveTransa = new WmsInveTransa();
        wmsInveTransa.setWms_inve_transa_id(wms_inve_transa_id);
        wmsInveTransa.setDate_of_payment(DateUtil.strToSqlDate(begin_of_date, null));
        if (wmsInveClerkProtocol != null)
        {
            wmsInveTransa.setFinancial_bill_code(wmsInveClerkProtocol.getProt_code());
        }
        wmsInveTransaDao.update(wmsInveTransa);

    }

    /**
     * @Title: SaveWmsInveClerkProtocol
     * @Description: 生成柜员合同  线下合同需要释放债权
     * @param wms_inve_transa_id
     * @param user 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午7:14:50
     * history:
     * 1、2017年2月15日 Administrator 创建方法
     */
    private void saveWmsInveClerkProtocol(String begin_of_date, Integer wms_inve_transa_id, String protocol_type, String prot_code,
                                          UserBean user)
    {
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();

        // 线下合同释放债权
        if ("2".equals(protocol_type))
        {
            // 根据上单表主键查询返回柜员合同实体类 不带合同编号
            wmsInveClerkProtocol = wmsinveclerkprotocolDao.getWmsInveClerkProtocolByTransaIdWithoutCode(wms_inve_transa_id);
            // 线下合同协议编号
            wmsInveClerkProtocol.setProt_code(prot_code);
            // 是线下合同释放债权
            CreditBusiness.getInstance()
                          .releaseMatchedCreditForTransaFlow(wmsInveClerkProtocol.getWms_inve_transa_id(),
                                                             wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);
        }
        else
        {
            // 根据上单表主键查询返回柜员合同实体类 带合同编号
            wmsInveClerkProtocol = wmsinveclerkprotocolDao.getWmsInveClerkProtocolByTransaIdWithCode(wms_inve_transa_id);
        }
        // 1线上合同 2 线下合同  签约时把合同类型设置为null
        wmsInveClerkProtocol.setProtocol_type(protocol_type);
        wmsInveClerkProtocol.setBegin_of_date(DateUtil.strTimestamp(begin_of_date, null));
        // 补充协议合同到期日:合同签署日期+期限-1
        wmsInveClerkProtocol.setEnd_of_date(new Timestamp(DateUtil.getDateAddDays(DateUtil.getDatePlusMonths(wmsInveClerkProtocol.getBegin_of_date(), Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())), -1).getTime()));
        /*
         * wmsInveClerkProtocol.setAct_end_of_date(new Timestamp(DateUtil.getDateAddDays(DateUtil.getDatePlusMonths(wmsInveClerkProtocol.getBegin_of_date(), Integer.parseInt(wmsInveClerkProtocol.getProduct_deadline())), -1).getTime()));
        */
        wmsInveClerkProtocol.setCreate_user_id(user.getUserId());
        wmsInveClerkProtocol.setEnable_flag("1");
        wmsInveClerkProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsinveclerkprotocolDao.save(wmsInveClerkProtocol);
        
        // 线上合同，更新债权匹配关系表的合同表主键、插入债权匹配历史表数据
        if ("1".equals(protocol_type))
        {
            Integer wms_inve_clerk_protocol_id = wmsInveClerkProtocol.getWms_inve_clerk_protocol_id();
            // 更新债权匹配关系表的合同表主键
            WmsInveTransaCrepkg crepkg = new WmsInveTransaCrepkg();
            crepkg.setWms_inve_transa_id(wms_inve_transa_id);
            crepkg.setWms_inve_clerk_protocol_id(wms_inve_clerk_protocol_id);
            // jinzhm 2017-03-21 添加， 生成合同同时，更新债权转让日期
            crepkg.setAcl_date(new java.sql.Date(DateUtil.strDate(begin_of_date, null).getTime()));
            int count = wmsInveTransaCrepkgDao.updateProtocol(crepkg);
            if (count > 0)
            {
                CreditBusiness.getInstance().recordHistory(wms_inve_transa_id, wms_inve_clerk_protocol_id, user);
            }
        }

    }

    /**
     * @Title: saveRedeemProtocol
     * @Description: 保存合同改签数据
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param user 登录用户
     * @return 合同主键
     * @author: jinzhm
     * @time:2017年2月17日 下午2:10:52
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#saveRedeemProtocol(java.lang.Integer, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
    */
    @Override
    @Transactional
    public String saveRedeemProtocol(Integer transaId, BigDecimal productAccount, UserBean user)
    {
        // 查询单据
        WmsInveTransa transa = wmsInveTransaDao.get(transaId);
        // 查询创建单据的人员信息
        PmPersonnel person = pmPersonnelDao.get(transa.getCreate_user_id());

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("productAccount", productAccount);
        // 查询协议信息
        WmsInveClerkProtocol protocol = wmsinveclerkprotocolDao.getWmsInveRedeemProtocolByTransaId(paramMap).get(0);

        // 更新合同编号 和 合同版本号 经办人id
        paramMap = new HashMap<String, Object>();
        paramMap.put("protocolId", protocol.getWms_inve_clerk_protocol_id());
        paramMap.put("personnel_regionNumber", person.getPersonnel_regionnumber());
        paramMap.put("protocol_type", "1");
        paramMap.put("userId", user.getUserId());
        paramMap.put("lastUpdateTime", new Timestamp(System.currentTimeMillis()));
        paramMap.put("main_protocol_version", protocol.getMain_protocol_version());
        paramMap.put("sub_protocol_version", protocol.getSub_protocol_version());
        paramMap.put("inter_protocol_version", protocol.getInter_protocol_version());
        paramMap.put("inter_protocol_merge_version", protocol.getInter_protocol_merge_version());
        paramMap.put("m2m_protocol_version", protocol.getM2m_protocol_version());
        wmsinveclerkprotocolDao.updateProtocolCode(paramMap);

        WmsInveRedeem wmsInveRedeem = wmsinveredeemDao.get(protocol.getWms_inve_redeem_id());
        // 赎回单据状态为已完成的 需要去更新上单表合同编码
        if (wmsInveRedeem.getData_status().equals("6"))
        {
            // 更新上单表合同编码
            updateWmsInveTransaBillCode(transaId, protocol.getWms_inve_clerk_protocol_id());
        }
        // 更新柜员业务数据
        updateClerkDataFinished(transaId, protocol.getWms_inve_redeem_id(), user);

        return protocol.getWms_inve_clerk_protocol_id().toString();
    }

    /**
     * @Title: saveExtendProtocol
     * @Description: 保存合同续签数据
     * @param transaId 上单主键
     * @param user 登录用户信息
     * @return 合同主键
     * @author: jinzhm
     * @time:2017年2月17日 下午3:32:12
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#saveExtendProtocol(java.lang.Integer, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    @Override
    @Transactional
    public String saveExtendProtocol(Integer transaId, UserBean user)
    {
        // 查询单据
        WmsInveTransa transa = wmsInveTransaDao.get(transaId);
        // 查询创建单据的人员信息
        PmPersonnel person = pmPersonnelDao.get(transa.getCreate_user_id());

        // 查询协议信息
        WmsInveClerkProtocol protocol = wmsinveclerkprotocolDao.getWmsInveExtendProtocolByTransaId(transaId).get(0);

        // 更新合同编号
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("protocolId", protocol.getWms_inve_clerk_protocol_id());
        paramMap.put("personnel_regionNumber", person.getPersonnel_regionnumber());
        paramMap.put("last_update_user_id", user.getUserId());
        paramMap.put("last_update_timestamp", new Timestamp(System.currentTimeMillis()));
        paramMap.put("protocol_type", "1");
        paramMap.put("main_protocol_version", protocol.getMain_protocol_version());
        paramMap.put("sub_protocol_version", protocol.getSub_protocol_version());
        paramMap.put("inter_protocol_version", protocol.getInter_protocol_version());
        paramMap.put("inter_protocol_merge_version", protocol.getInter_protocol_merge_version());
        paramMap.put("m2m_protocol_version", protocol.getM2m_protocol_version());
        wmsinveclerkprotocolDao.updateProtocolCode(paramMap);

        // 获取柜员业务表中该transaid最新的一条数据
        WmsInveClerkData clerkData = new WmsInveClerkData();
        clerkData.setWms_inve_transa_id(transaId);
        // 业务类型是续期单据
        clerkData.setData_type("2");
        clerkData = wmsInveClerkDataDao.getListByEntity(clerkData).get(0);
        // 修改柜员业务数据为已完成
        WmsInveClerkData updClerkData = new WmsInveClerkData();
        updClerkData.setWms_inve_clerk_data_id(clerkData.getWms_inve_clerk_data_id());
        updClerkData.setIs_finished("1");
        updClerkData.setLast_update_user_id(user.getUserId());
        updClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkDataDao.update(updClerkData);
        
        // 非处于预约续期中的单据 打印合同时需要同步更新上单表单据编号
        if (!"1".equals(transa.getIs_order_extend()))
        {
            updateWmsInveTransaBillCode(transaId, protocol.getWms_inve_clerk_protocol_id());
        }

        return protocol.getWms_inve_clerk_protocol_id().toString();
    }

    /**
     * @Title: updateClerkDataFinished
     * @Description: 更新柜员业务表数据为已完成
     * @param transaId 上单主键
     * @param redeemId 赎回主键
     * @param isOrderExtend 是否为预约续期
     * @author: jinzhm
     * @time:2017年2月17日 下午3:24:08
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    private void updateClerkDataFinished(Integer transaId, Integer redeemId, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("redeemId", redeemId);
        paramMap.put("is_finished", "1");
        paramMap.put("enable_flag", "1");
        paramMap.put("userId", user.getUserId());
        paramMap.put("lastUpdateTime", new Timestamp(System.currentTimeMillis()));
        wmsInveClerkDataDao.updateClerkDataFinished(paramMap);
    }

    /**
     * @Title: saveExtendOrRedeemOfflineProtocol
     * @Description: 保存续签和改签线下补录合同信息
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param protocolCode 合同编号
     * @param type 业务类型 2：续期单据；3：赎回单据
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月17日 上午9:04:41
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#saveExtendOrRedeemOfflineProtocol(java.lang.Integer, java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
    */
    @Override
    @Transactional
    public String saveExtendOrRedeemOfflineProtocol(Integer transaId, BigDecimal productAccount, String protocolCode,
                                                  String type, UserBean user)
    {
        // 验证线下合同编号是否有重复
        if (isProtCodedup(protocolCode))
        {
            return "prot_code_error";
        }
        // 如果是续期单据
        if ("2".equals(type))
        {
            // 根据上单主键获得协议主键，续期时有可能有相同上单主键的协议信息
            WmsInveClerkProtocol protocol = wmsinveclerkprotocolDao.getWmsInveExtendProtocolByTransaId(transaId).get(0);

            // 更新合同编号
            WmsInveClerkProtocol updProtocol = new WmsInveClerkProtocol();
            updProtocol.setWms_inve_clerk_protocol_id(protocol.getWms_inve_clerk_protocol_id());
            updProtocol.setProt_code(protocolCode);
            // 设置为线下合同
            updProtocol.setProtocol_type("2");
            updProtocol.setLast_update_user_id(user.getUserId());
            updProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 合同版本号 和 经办人
            updProtocol.setMain_protocol_version(protocol.getMain_protocol_version());
            updProtocol.setSub_protocol_version(protocol.getSub_protocol_version());
            updProtocol.setInter_protocol_version(protocol.getInter_protocol_version());
            updProtocol.setInter_protocol_merge_version(protocol.getInter_protocol_merge_version());
            updProtocol.setM2m_protocol_version(protocol.getM2m_protocol_version());
            // 修改合同信息
            wmsinveclerkprotocolDao.update(updProtocol);

            // 获取柜员业务表中该transaid最新的一条数据
            WmsInveClerkData clerkData = new WmsInveClerkData();
            clerkData.setWms_inve_transa_id(transaId);
            // 业务类型是续期单据
            clerkData.setData_type("2");
            clerkData = wmsInveClerkDataDao.getListByEntity(clerkData).get(0);
            // 修改柜员业务数据为已完成
            WmsInveClerkData updClerkData = new WmsInveClerkData();
            updClerkData.setWms_inve_clerk_data_id(clerkData.getWms_inve_clerk_data_id());
            updClerkData.setIs_finished("1");
            updClerkData.setLast_update_user_id(user.getUserId());
            updClerkData.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            wmsInveClerkDataDao.update(updClerkData);
         
            // 非处于预约续期中的单据 打印合同时需要同步更新上单表单据编号
            WmsInveTransa transa = wmsInveTransaDao.get(transaId);
            if (!"1".equals(transa.getIs_order_extend()))
            {
                updateWmsInveTransaBillCode(transaId, protocol.getWms_inve_clerk_protocol_id());
            }
            
            // 释放债权
            CreditBusiness.getInstance().disableMatchedCreditForExtendFlow(protocol.getWms_inve_transa_id(),
                                                                           protocol.getWms_inve_clerk_protocol_id(),
                                                                           user);
            // 删除匹配历史
            CreditBusiness.getInstance().deleteCreditMatchHistory(protocol.getWms_inve_transa_id(),
                                                                  protocol.getWms_inve_clerk_protocol_id(), user);
        }
        // 如果是赎回单据
        else
        {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transaId", transaId);
            paramMap.put("productAccount", productAccount);
            WmsInveClerkProtocol protocol = wmsinveclerkprotocolDao.getWmsInveRedeemProtocolByTransaId(paramMap).get(0);
            // 更新合同编号
            WmsInveClerkProtocol updProtocol = new WmsInveClerkProtocol();
            updProtocol.setWms_inve_clerk_protocol_id(protocol.getWms_inve_clerk_protocol_id());
            updProtocol.setProt_code(protocolCode);
            // 设置为线下合同
            updProtocol.setProtocol_type("2");
            updProtocol.setLast_update_user_id(user.getUserId());
            updProtocol.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 合同版本号 和 经办人
            updProtocol.setMain_protocol_version(protocol.getMain_protocol_version());
            updProtocol.setSub_protocol_version(protocol.getSub_protocol_version());
            updProtocol.setInter_protocol_version(protocol.getInter_protocol_version());
            updProtocol.setInter_protocol_merge_version(protocol.getInter_protocol_merge_version());
            updProtocol.setM2m_protocol_version(protocol.getM2m_protocol_version());
            // 修改合同信息
            wmsinveclerkprotocolDao.update(updProtocol);

            WmsInveRedeem wmsInveRedeem = wmsinveredeemDao.get(protocol.getWms_inve_redeem_id());
            // 赎回单据状态为已完成的 需要去更新上单表合同编码
            if (wmsInveRedeem.getData_status().equals("6"))
            {
                // 更新上单表合同编码
                updateWmsInveTransaBillCode(transaId, protocol.getWms_inve_clerk_protocol_id());
            }

            // 修改柜员业务数据为已完成
            updateClerkDataFinished(transaId, protocol.getWms_inve_redeem_id(), user);
            // 释放债权
            CreditBusiness.getInstance().disableMatchedCreditForRedeemFlow(protocol.getWms_inve_transa_id(),
                                                                           protocol.getWms_inve_clerk_protocol_id(),
                                                                           user);
            // 删除匹配历史
            CreditBusiness.getInstance().deleteCreditMatchHistory(protocol.getWms_inve_transa_id(),
                                                                  protocol.getWms_inve_clerk_protocol_id(), user);
        }

        return "success";
    }

    /**
     * @Title: saveWmsInveClerkProtocolRedeem
     * @Description: 预约部分赎回 生成柜员合同
     * @param wms_inve_clerk_protocol_id 
     * @author: zhangyunfei
     * @time:2017年2月16日 下午1:21:40
     * history:
     * 1、2017年2月16日 Administrator 创建方法
     */
    @Override
    public void saveWmsInveClerkProtocolRedeem(WmsInveRedeem wmsInveRedeem, WmsInveRedeemDetail wmsInveRedeemDetail,
                                               UserBean user)
    {
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
        List<WmsInveClerkProtocol> wmsinveclerkprotocolList = wmsinveclerkprotocolDao.getListByWmsInveClerkProtocol(wmsInveClerkProtocol);
        // 柜员协议表为空 需要从新生成合同
        if (wmsinveclerkprotocolList.size() > 0)
        {
            wmsInveClerkProtocol = wmsinveclerkprotocolList.get(0);
        }
        else
        {
            // 当柜员协议表中不存在时 查询原协议表 取出end_of_date
            WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
            protocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
            List<WmsInveTransaProtocol> protocolList = wmsInveTransaProtocolDao.getListByEntity(protocol);
            // 生成不带合同编号的 合同实体类
            wmsInveClerkProtocol = wmsinveclerkprotocolDao.getWmsInveClerkProtocolByTransaIdWithoutCode(wmsInveRedeemDetail.getWms_inve_transa_id());
            wmsInveClerkProtocol.setEnd_of_date(new Timestamp(protocolList.get(0).getEnd_of_date().getTime()));// 结束日期
        }

        // 赎回生成新合同 合同主键设置为null
        wmsInveClerkProtocol.setAct_end_of_date(null);
        wmsInveClerkProtocol.setWms_inve_clerk_protocol_id(null);
        wmsInveClerkProtocol.setProduct_account(wmsInveClerkProtocol.getProduct_account()
                                                                    .subtract(wmsInveRedeemDetail.getRedeem_amount()));
        // 赎回未打合同 这时合同没有版本号 清空实体类中的合同版本号信息
        wmsInveClerkProtocol.setMain_protocol_version(null);
        wmsInveClerkProtocol.setSub_protocol_version(null);
        wmsInveClerkProtocol.setInter_protocol_version(null);
        wmsInveClerkProtocol.setInter_protocol_merge_version(null);
        wmsInveClerkProtocol.setM2m_protocol_version(null);
        wmsInveClerkProtocol.setCustomer_signature_path(null);
        // 投资金额小写转大写
        wmsInveClerkProtocol.setProduct_account_upper(digitUpperUtil.digitUppercase(((BigDecimal) wmsInveClerkProtocol.getProduct_account()).doubleValue(), true).replaceAll("万元", ""));
        wmsInveClerkProtocol.setWms_inve_redeem_id(wmsInveRedeem.getWms_inve_redeem_id());
        wmsInveClerkProtocol.setCreate_user_id(user.getUserId());
        wmsInveClerkProtocol.setCreate_user_name(user.getRealname());
        wmsInveClerkProtocol.setCreate_timestamp(new Timestamp(new Date().getTime()));
        wmsInveClerkProtocol.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // 赎回时生成不带协议编号的合同
        wmsInveClerkProtocol.setProt_code(null);
        wmsInveClerkProtocol.setProtocol_type(null);
        wmsInveClerkProtocol.setEnable_flag("1");
        wmsinveclerkprotocolDao.save(wmsInveClerkProtocol);
        // 预约部分赎回匹配债权
        // WmsInveTransaProd wmsInveTransaProd =
        // wmsInveTransaProdDao.get(wmsInveRedeemDetail.getWms_inve_transa_prod_id());

        // 封装柜员业务表实体类
        WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByWmsInveTransaId(wmsInveRedeemDetail.getWms_inve_transa_id());
        wmsInveClerkData.setWms_inve_redeem_id(wmsInveRedeem.getWms_inve_redeem_id());
        wmsInveClerkData.setData_type("3");
        wmsInveClerkData.setProduct_account(wmsInveClerkProtocol.getProduct_account());
        wmsInveClerkData.setOper_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setIs_finished("0");
        wmsInveClerkData.setCreate_user_id(user.getUserId());
        wmsInveClerkData.setCreate_user_name(user.getRealname());
        wmsInveClerkData.setCreate_user_dept_id(user.getDeptId());
        wmsInveClerkData.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

        wmsInveClerkData.setEnable_flag("1");

        wmsInveClerkDataDao.save(wmsInveClerkData);

    }

    /**
     * @Title: updateWmsInveClerkProtocolRedeem
     * @Description: 赎回退回时  修订 需要更新柜员协议表金额和enable_flag
     * @param wmsInveRedeem
     * @param wmsInveRedeemDetail
     * @param user 
     * @author: zhangyunfei
     * @time:2017年2月16日 下午6:11:01
     * history:
     * 1、2017年2月16日 Administrator 创建方法
     */
    @Override
    public void updateWmsInveClerkProtocolRedeem(WmsInveClerkProtocol protocol, WmsInveRedeem wmsInveRedeem, WmsInveRedeemDetail wmsInveRedeemDetail,
                                                 UserBean user)
    {
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        // 更新柜员协议表单据状态和金额
        wmsInveClerkProtocol.setProduct_account(protocol.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount()));
        wmsInveClerkProtocol.setProduct_account_upper(digitUpperUtil.digitUppercase(((BigDecimal) wmsInveClerkProtocol.getProduct_account()).doubleValue(), true).replaceAll("万元", ""));

        wmsInveClerkProtocol.setEnable_flag("1");
        wmsInveClerkProtocol.setWms_inve_transa_id(wmsInveRedeemDetail.getWms_inve_transa_id());
        wmsInveClerkProtocol.setWms_inve_redeem_id(wmsInveRedeem.getWms_inve_redeem_id());
        wmsinveclerkprotocolDao.disableWmsinveclerkprotocol(wmsInveClerkProtocol);

    }

    /**
     * 
     * @Title: saveWmsInveClerkData
     * @Description: 赎回退回修订时保存柜员业务单据
     * @param protocol
     * @param wmsInveRedeem
     * @param wmsInveRedeemDetail
     * @param user 
     * @author: zhangyunfei
     * @time:2017年2月22日 上午10:19:42
     * history:
     * 1、2017年2月22日 Administrator 创建方法
     */
    @Override
    public void saveWmsInveClerkData(WmsInveClerkProtocol protocol, WmsInveRedeem wmsInveRedeem, WmsInveRedeemDetail wmsInveRedeemDetail, UserBean user)
    {
        // 封装柜员业务表实体类
        // 生成柜员业务单据
        WmsInveClerkData wmsInveClerkData = wmsInveClerkDataDao.getWmsInveClerkDataByWmsInveTransaId(wmsInveRedeemDetail.getWms_inve_transa_id());
        wmsInveClerkData.setWms_inve_redeem_id(wmsInveRedeem.getWms_inve_redeem_id());

        // 3是赎回单据
        wmsInveClerkData.setData_type("3");
        wmsInveClerkData.setProduct_account(protocol.getProduct_account().subtract(wmsInveRedeemDetail.getRedeem_amount()));

        wmsInveClerkData.setOper_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setSort_timestamp(new Timestamp(System.currentTimeMillis()));
        wmsInveClerkData.setIs_finished("0");
        wmsInveClerkData.setCreate_user_id(user.getUserId());
        wmsInveClerkData.setCreate_user_name(user.getRealname());
        wmsInveClerkData.setCreate_user_dept_id(user.getDeptId());
        wmsInveClerkData.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

        wmsInveClerkData.setEnable_flag("1");

        wmsInveClerkDataDao.save(wmsInveClerkData);
    }

    /**
     * 
     * @Title: enableWmsInveClerkProtocolRedeem
     * @Description: 失效柜员协议 和柜员业务单据
     * @author: zhangyunfei
     * @time:2017年2月17日 上午9:03:48
     * history:
     * 1、2017年2月17日 Administrator 创建方法
     */
    public void disableWmsInveClerkProtocol(Integer wms_inve_redeem_id)
    {
        // 失效柜员协议表单据
        WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
        wmsInveClerkProtocol.setWms_inve_redeem_id(wms_inve_redeem_id);
        // 预约赎回 获取主键最大的原单据 wmsinveclerkprotocol.get(0)
        wmsInveClerkProtocol.setEnable_flag("0");
        wmsinveclerkprotocolDao.disableWmsinveclerkprotocol(wmsInveClerkProtocol);

        // 失效柜员业务表单据
        WmsInveClerkData wmsInveClerkData = new WmsInveClerkData();
        wmsInveClerkData.setEnable_flag("0");
        wmsInveClerkData.setWms_inve_redeem_id(wms_inve_redeem_id);
        wmsInveClerkDataDao.disableWmsInveClerkData(wmsInveClerkData);

    }


    /**
     * @Title: getOrderRedeemClerkProtocol
     * @Description:  查询到达预约赎回日的柜员协议集合并匹配债权
     * @param date
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月17日 下午4:39:23
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getOrderRedeemClerkProtocol(java.lang.String)
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    @Override
    public String matchForOrderRedeemClerkProtocol(String date)
    {
        UserBean user = new UserBean();
        user.setUserId(113);

        // 查询到达预约赎回日的柜员协议集合 遍历匹配债权(预约部分赎回的单据)
        List<WmsInveClerkProtocolVO> WmsInveClerkProtocolVoList = wmsInveClerkDataDao.getOrderRedeemClerkProtocol(date);
        for (int i = 0; i < WmsInveClerkProtocolVoList.size(); i++)
        {
            // 非股东单据(匹配债权 发送邮件)
            if (wmsInveTransaService.verifyIsShareholderBill(WmsInveClerkProtocolVoList.get(i).getWms_inve_transa_id()))
            {
                CreditBusiness.getInstance().matchForRedeemFlow(WmsInveClerkProtocolVoList.get(i).getWms_inve_transa_id(), WmsInveClerkProtocolVoList.get(i).getWms_inve_clerk_protocol_id(), WmsInveClerkProtocolVoList.get(i).getWms_inve_pruduct_category_id(), WmsInveClerkProtocolVoList.get(i).getProduct_account(), WmsInveClerkProtocolVoList.get(i).getRedeem_date(), user);
                // 预约部分赎回选择电子债权则进行发送邮件
                if ("2".equals(WmsInveClerkProtocolVoList.get(i).getGet_credit_type()))
                {
                    saveWmsInveCreditEmailInfo(WmsInveClerkProtocolVoList.get(i).getWms_inve_transa_id(), WmsInveClerkProtocolVoList.get(i).getWms_inve_clerk_protocol_id(), WmsInveClerkProtocolVoList.get(i).getProt_code(), user);
                }
            }
        }
        return "success";
    }

    /**
     * 
     * @Title: saveWmsInveCreditEmailInfo
     * @Description: 生成债权邮件信息
     * @param old_wms_inve_transa_id
     * @param new_wms_inve_transa_id
     * @param oldWmsInveClerkProtocol
     * @param user
     * @return 
     * @author: zhangyunfei
     * @time:2017年3月22日 上午10:40:44
     * history:
     * 1、2017年3月22日 Administrator 创建方法
     */
    private void saveWmsInveCreditEmailInfo(Integer wms_inve_transa_id, Integer wms_inve_clerk_protocol_id, String prot_code, UserBean user)
    {

        // 定义邮件对象
        WmsInveCreditEmail wmsInveCreditEmail = new WmsInveCreditEmail();

        // 设置续期生成的新单据的上单表主键
        wmsInveCreditEmail.setWms_inve_transa_id(wms_inve_transa_id);

        // 设置邮件标题
        wmsInveCreditEmail.setEmail_subject("卓信金控《债权转让及受让协议》已发送，请查收_【自动发送，请勿回复】");

        // 设置柜员协议表主键
        wmsInveCreditEmail.setWms_inve_clerk_protocol_id(wms_inve_clerk_protocol_id);

        // 设置邮件内容
        wmsInveCreditEmail.setEmail_content("尊敬的客户，您好！您所持有的【" + prot_code + "】理财合同所对应的《债权转让及受让协议》和《内部债权转让及受让协议》已匹配完成，请注意查收！谢谢！");

        // 设置邮件发送状态为未发送状态
        wmsInveCreditEmail.setSend_status("0");

        // 设置邮件是否有效标识
        wmsInveCreditEmail.setEnable_flag("1");

        // 设置类型
        wmsInveCreditEmail.setData_type("2");

        // 设置创建时间
        wmsInveCreditEmail.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));

        // 设置创建人
        wmsInveCreditEmail.setCreate_user_id(user.getUserId());

        // 保存邮件发送信息
        wmsInveCreditEmailDao.save(wmsInveCreditEmail);

    }

    /**
     * @Title: updatePreWmsInveClerkProtocolEndOfDate
     * @Description: 更新原单据到期日 释放原合同债权
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年2月17日 下午6:10:22
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#updatePreWmsInveClerkProtocolEndOfDate(com.zx.emanage.util.gen.entity.WmsInveClerkProtocol)
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    @Override
    public void updatePreWmsInveClerkProtocolEndOfDate(WmsInveClerkProtocol wmsInveClerkProtocol)
    {
        wmsinveclerkprotocolDao.updatePreWmsInveClerkProtocolEndOfDate(wmsInveClerkProtocol);
    }

    /**
     * @Title: getWmsInveClaimsInfos
     * @Description: 根据上单表主键和柜员协议表主键获取债权匹配信息
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_clerk_protocol_id 柜员协议表主键
     * @return 
     * @author: DongHao
     * @time:2017年2月17日 下午6:33:52
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClaimsInfos(java.lang.String, java.lang.String)
     * history:
     * 1、2017年2月17日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getWmsInveClaimsInfos(String wms_inve_transa_id, String wms_inve_clerk_protocol_id)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramsMap.put("wms_inve_clerk_protocol_id", wms_inve_clerk_protocol_id);

        List<Map<String, Object>> list = wmsinveclerkprotocolDao.getWmsInveClaimsInfos(paramsMap);

        resultMap.put("Rows", list);
        return resultMap;
    }

    /**
     * @Title: getWmsInveClaimsInfosWithoutZHAO
     * @Description: 如果该匹配历史他项人全部为赵燕国 返回false 否则返回true  
     * @param wms_inve_transa_id 
     * @param wms_inve_clerk_protocol_id 
     * @param wms_inve_clerk_protocol_transa_crepkg_id 
     * @author: zhangyunfei
     * @time:2017年5月19日 下午2:12:26
     * history:
     * 1、2017年5月19日 Administrator 创建方法
     */
    public Map<String, String> getWmsInveClaimsInfosWithoutZHAO(String wms_inve_transa_id, String wms_inve_clerk_protocol_id, String wms_inve_clerk_protocol_transa_crepkg_id)
    {
        String result = "true";
        WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO = new WmsInveClerkProtocolSearchBeanVO();
        List<Map<String, Object>> crepkgList = new ArrayList<Map<String, Object>>();
        HashMap<String, String> map = new HashMap<String, String>();
        // 查询关联债权信息
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_transa_id(Integer.parseInt(wms_inve_transa_id));
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_id(Integer.parseInt(wms_inve_clerk_protocol_id));
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_transa_crepkg_id(Integer.parseInt(wms_inve_clerk_protocol_transa_crepkg_id));
        // 查询该匹配历史所对应的所有债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgService.getAllTransaCrepkgByGroup(wmsInveClerkProtocolSearchBeanVO);
        // 遍历债权信息 把他项人不是赵燕国的数据放到list中
        for (Map<String, Object> crepkg : transaCrepkgList)
        {
            String rele_per_name = crepkg.get("rele_per_name").toString();
            if (!rele_per_name.equals("赵燕国"))
            {
                crepkgList.add(crepkg);
            }
        }
        // 如果list==0 说明他项人全是赵燕国 返回false
        if (transaCrepkgList.size() > 0 && crepkgList.size() == 0)
        {
            result = "false";
        }
        map.put("result", result);
        return map;
    }
    /**
     * 
     * @Title: deleteClerkDataAndClerkProtocol
     * @Description: 赎回操作时需要删除掉预约续期的协议表和柜员业务表数据
     * @param wms_inve_transa_id 
     * @author: DongHao
     * @time:2017年2月17日 下午5:41:26
     * history:
     * 1、2017年2月17日 DongHao 创建方法
     */
    public void deleteClerkDataAndClerkProtocol(Integer wms_inve_transa_id)
    {

        WmsInveClerkData data = new WmsInveClerkData();
        data.setWms_inve_transa_id(wms_inve_transa_id);
        data.setData_type("2");
        data.setEnable_flag("0");
        wmsInveClerkDataDao.deleteClerkDataByWmsInveTransaIdAndDataType(data);

        WmsInveClerkProtocol protocol = new WmsInveClerkProtocol();
        protocol.setWms_inve_transa_id(wms_inve_transa_id);
        protocol.setIs_order_extend("1");
        protocol.setEnable_flag("0");
        wmsinveclerkprotocolDao.deleteClerkProtocolByWmsInveTransaIdAndIsOrderExtend(protocol);
    }

    /**
     * @Title: exportProtocolpdf
     * @Description: 预约部分赎回/预约续期  定时任务下载合同
     * @param wmsInveClerkProtocolSearchBeanVO 
     * @author: zhangyunfei
     * @time:2017年3月10日 下午5:01:38
     * history:
     * 1、2017年3月10日 Administrator 创建方法
     */
    public List<Map<String, String>> exportProtocolpdf(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {

        List<Map<String, String>> res_list = new ArrayList<Map<String, String>>();

        List<String> listUrl = new ArrayList<String>();
        listUrl.add("inter-creditTransferProtocol");
        listUrl.add("m-m_creditTransferProtocol");

        String baseDir = WmsInveClerkProtocolController.class.getResource("/").getPath();
        String header1Dir = baseDir + "jasper/header1.png";
        String logoDir = baseDir + "jasper/logo_zx.jpg";

        // pMap 存放人出借咨询与服务协议、委托代扣款授权书、债权包签收声明、个人出借咨询与服务补充协议所需参数
        // wmsInveClerkProtocolSearchBeanVO.setWms_inve_transa_id(5);
        Map<String, Object> pMap = getWmsInveClerkProtocolById(wmsInveClerkProtocolSearchBeanVO);

        // 内部转让协议版本号
        String inter_protocol_version = ("".equals(pMap.get("inter_protocol_version").toString())) ? "" : "_" + pMap.get("inter_protocol_version").toString();
        // 内部转让协议合并版本号 1
        String inter_protocol_merge_version = ("".equals(pMap.get("inter_protocol_merge_version").toString())) ? "" : "_" + pMap.get("inter_protocol_merge_version").toString();
        // 多对多协议版本号
        String m2m_protocol_version = ("".equals(pMap.get("m2m_protocol_version").toString())) ? "" : "_" + pMap.get("m2m_protocol_version").toString();

        // 拼接页脚字符串
        String footInfo = "Add：" + pMap.get("r_b_area") + "  TEL：400 000 2555  Web：www.zxptp.com";
        // zMap 存放内部转让债权协议所需参数
        Map<String, Object> zMap = new HashMap<String, Object>();
        // cMap 存放债权转让受让协议所需参数
        Map<String, Object> cMap = new HashMap<String, Object>();

        // 根据上单主键和协议主键查找所有关联的债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgService.getAllTransaCrepkgByGroup(wmsInveClerkProtocolSearchBeanVO);
        Map<String, List<Map<String, Object>>> crepkgMap = wmsInveTransaCrepkgService.groupTransaCrepkgList(transaCrepkgList);

        String fileurl = "";
        // 他项人不为赵燕国或卓信小额贷时 合并其他他项人的内部转让协议模板
        String fileMergeurl = "";
        List<Map<String, Object>> tcList = new ArrayList<Map<String, Object>>();
        // sdMap 存放原债权人签字
        Map<String, Object> sdMap = new HashMap<String, Object>();
        Map<String, Object> relMap = new HashMap<String, Object>();
        List<Map<String, Object>> sdList = new ArrayList<Map<String, Object>>();
        BigDecimal sum_cre_pledge_mon = BigDecimal.ZERO;

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        List<JasperPrint> jasperPrintList_out = new ArrayList<JasperPrint>();
        JasperPrint jasperPrint = null;
        try
        {
            for (int i = 0; i < listUrl.size(); i++)
            {
                if ("inter-creditTransferProtocol".equals(listUrl.get(i)))
                {
                    // 原内部转让合同/卓信小额贷模板路径
                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + inter_protocol_version + ".jasper";
                    // 项人不为赵燕国或卓信小额贷时 合并其他他项人的内部转让协议模板路径
                    if (!"".equals(inter_protocol_merge_version))
                    {
                        fileMergeurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/inter-creditTransferProtocol-merge" + inter_protocol_merge_version + ".jasper";
                    }
                }else if("m-m_creditTransferProtocol".equals(listUrl.get(i))){

                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + m2m_protocol_version + ".jasper";

                }else{

                    fileurl = JasperUtil.class.getClassLoader().getResource("").getPath() + "/jasper/" + listUrl.get(i);
                }
                File file = new File(fileurl);
                InputStream jr = new FileInputStream(file);
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jr);
                jasperReport.getFields();
                // 内部转让协议需要根据他项人id多次封装jasperPrint(打印多张合同)
                if ("inter-creditTransferProtocol".equals(listUrl.get(i)))
                {
                    // 转让协议编号
                    zMap.put("trans_code", pMap.get("trans_code"));
                    zMap.put("header1Dir", header1Dir);
                    zMap.put("b_address", pMap.get("b_address"));
                    zMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
                    zMap.put("intran_b_name", pMap.get("intran_name"));
                    zMap.put("intran_b_address", pMap.get("intran_address"));
                    zMap.put("intran_b_id", pMap.get("intran_id"));
                    zMap.put("prot_code", pMap.get("prot_code"));
                    zMap.put("logoDir", logoDir);
                    zMap.put("sign_date", pMap.get("sign_date"));
                    zMap.put("signDir_b", baseDir + "jasper/" + pMap.get("intran_name") + ".png");
                    // 经办人-----业务员
                    zMap.put("salesman_name", pMap.get("salesman_name"));
                    zMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));
                    // 经办人-----业务员
                    zMap.put("printman_shortCode", pMap.get("create_user_shortCode"));
                    // 备注
                    if (pMap.get("prot_type") == null)
                    {
                        zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                    }
                    else
                    {
                        zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        if ("1".equals(pMap.get("prot_type").toString()))
                        {
                            zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                        }
                        else if ("2".equals(pMap.get("prot_type").toString()))
                        {
                            zMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
                        }
                    }
                    // 页脚说明
                    zMap.put("footInfo", footInfo);
                    for (Map.Entry<String, List<Map<String, Object>>> entry : crepkgMap.entrySet())
                    {
                        // 当他项人为赵燕国时 不签内转内合同
                        if (!"赵燕国".equals(entry.getKey()))
                        {
                            // cList为债权集合
                            String intran_a_name = (String) entry.getValue().get(0).get("intran_a_name");
                            // 老单据和卓信小额贷是按原始版本打印多个协议
                            if ("".equals(inter_protocol_merge_version) || "卓信小额贷".equals(entry.getKey()))
                            {
                                zMap.put("tList", entry.getValue());
                                zMap.put("intran_a_name", entry.getValue().get(0).get("intran_a_name"));
                                zMap.put("intran_a_id", entry.getValue().get(0).get("intran_a_id"));
                                zMap.put("signDir", baseDir + "jasper/" + intran_a_name + ".png");
                                // 他项人为组织 甲方取组织名称
                                zMap.put("a_company_name", entry.getValue().get(0).get("a_company_name"));
                                // 组织标示 0非组织 1组织
                                zMap.put("is_company", entry.getValue().get(0).get("is_company"));

                                jasperPrint = JasperFillManager.fillReport(jasperReport, zMap, new JREmptyDataSource());
                                int page_size = jasperPrint.getPages().size();
                                jasperPrintList.add(jasperPrint);

                                // 添加空白页
                                if (page_size % 2 == 1)
                                {
                                    String blank_fileurl = JasperUtil.class.getClassLoader().getResource("").getPath() + "/jasper/" + "blank.jasper";
                                    File blank_file = new File(blank_fileurl);
                                    InputStream blank_jr = new FileInputStream(blank_file);
                                    JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                                    JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                                    jasperPrintList.add(blank_jasperPrint);
                                }
                            }
                            else
                            {
                                tcList.addAll(entry.getValue());
                                if (relMap.get("intran_a_name") == null)
                                {
                                    relMap.put(intran_a_name, baseDir + "jasper/" + intran_a_name + ".png");
                                }
                                // 计算总和
                                for (int k = 0; k < entry.getValue().size(); k++)
                                {
                                    sum_cre_pledge_mon = sum_cre_pledge_mon.add((BigDecimal) entry.getValue().get(k).get("acl_mon"));
                                }
                            }
                        }
                    }
                    // 当存在内部转让协议合并版本号 并且他项人不为赵燕国或卓信小额贷的list不为空
                    if (!"".equals(inter_protocol_merge_version) && tcList.size() > 0)
                    {
                        // 内部转让协议他项人不是赵燕国和卓信小额贷时所打印合并其他所有他项人的合同模板
                        file = new File(fileMergeurl);
                        InputStream jrm = new FileInputStream(file);
                        JasperReport jasperReportm = (JasperReport) JRLoader.loadObject(jrm);
                        jasperReportm.getFields();

                        zMap.put("intran_a_name", "详见转让债权明细列表");
                        zMap.put("intran_a_id", "详见转让债权明细列表");
                        zMap.put("is_merge_version", "1");
                        int j = 1;
                        for (Map.Entry<String, Object> entry : relMap.entrySet())
                        {
                            if (j % 2 == 0)
                            {
                                sdMap.put("signDir2", entry.getValue());
                                sdList.add(sdMap);
                                sdMap = new HashMap<String, Object>();
                            }
                            else
                            {
                                sdMap.put("signDir1", entry.getValue());
                                if (relMap.size() % 2 == 1 && j == relMap.size())
                                {
                                    sdList.add(sdMap);
                                }
                            }
                            j++;
                        }
                        // 计算合计 并格式化
                        DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
                        String sum_cre_pledge_mon_str = fmt.format(sum_cre_pledge_mon);
                        zMap.put("sum_cre_pledge_mon", sum_cre_pledge_mon_str);

                        zMap.put("tList", tcList);
                        zMap.put("sdList", sdList);
                        jasperPrint = JasperFillManager.fillReport(jasperReportm, zMap, new JREmptyDataSource());
                        int page_size = jasperPrint.getPages().size();
                        jasperPrintList.add(jasperPrint);
                        // 添加空白页
                        if (page_size % 2 == 1)
                        {
                            String blank_fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "blank.jasper";
                            File blank_file = new File(blank_fileurl);
                            InputStream blank_jr = new FileInputStream(blank_file);
                            JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                            JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                            jasperPrintList.add(blank_jasperPrint);
                        }
                    }
                }
                // 封装多对多债权转让受让协议所需参数
                else if ("m-m_creditTransferProtocol".equals(listUrl.get(i)))
                {
                    // 转让协议编号
                    cMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
                    cMap.put("a_contact_address", pMap.get("a_contact_address"));
                    cMap.put("a_id_card", pMap.get("a_id_card"));
                    cMap.put("a_name", pMap.get("a_name"));
                    cMap.put("intran_id", pMap.get("intran_id"));
                    cMap.put("intran_name", pMap.get("intran_name"));
                    cMap.put("prot_code", pMap.get("prot_code"));
                    cMap.put("logoDir", logoDir);

                    cMap.put("category_name", pMap.get("category_name"));
                    cMap.put("trans_code", pMap.get("trans_code"));
                    cMap.put("begin_of_date", pMap.get("begin_of_date"));
                    cMap.put("card_no", pMap.get("card_no"));
                    cMap.put("bank_info", pMap.get("bank_info"));
                    cMap.put("sum_cre_pledge_mon", pMap.get("sum_cre_pledge_mon"));
                    cMap.put("sum_cre_pledge_mon_upper", pMap.get("sum_cre_pledge_mon_upper"));

                    cMap.put("signDir_b", baseDir + "jasper/" + pMap.get("intran_name") + ".png");
                    // 经办人-----业务员
                    cMap.put("salesman_name", pMap.get("salesman_name"));
                    cMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));
                    // 审核人-----当前登录人 打印人
                    cMap.put("printman_shortCode", pMap.get("create_user_shortCode"));
                    // 备注
                    if (pMap.get("prot_type") == null)
                    {
                        cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                    }
                    else
                    {
                        cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        if ("1".equals(pMap.get("prot_type").toString()))
                        {
                            cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                        }
                        else if ("2".equals(pMap.get("prot_type").toString()))
                        {
                            cMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
                        }
                    }
                    // 页脚说明
                    cMap.put("footInfo", footInfo);
                    // 3.1/6.1/6.2项文字说明
                    cMap.put("3.1Info", "       3.1 转让价款：乙方向甲方支付人民币(大写)" + pMap.get("sum_cre_pledge_mon_upper") + " 元整（人民币小写" + pMap.get("sum_cre_pledge_mon") + "元）作为上述债权转让的对价。");
                    cMap.put("6.1Info", "       6.1双方同意" + pMap.get("r_b_name") + "作为本协议签订的见证人。");
                    cMap.put("6.2Info", "       6.2乙方同意委托" + pMap.get("r_b_name") + "对其受让债权进行信用咨询和管理服务。乙方与" + pMap.get("r_b_name") + "另行签订《个人出借与咨询服务协议》。");
                    // 证明人
                    cMap.put("r_b_name", pMap.get("r_b_name"));
                    cMap.put("header1Dir", header1Dir);
                    cMap.put("tList", transaCrepkgList);

                    jasperPrint = JasperFillManager.fillReport(jasperReport, cMap, new JREmptyDataSource());

                    int page_size = jasperPrint.getPages().size();
                    jasperPrintList_out.add(jasperPrint);

                    // 添加空白页
                    if (page_size % 2 == 1)
                    {
                        String blank_fileurl = JasperUtil.class.getClassLoader().getResource("").getPath() + "/jasper/" + "blank.jasper";
                        File blank_file = new File(blank_fileurl);
                        InputStream blank_jr = new FileInputStream(blank_file);
                        JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                        JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                        jasperPrintList_out.add(blank_jasperPrint);
                    }
                }
            }
            // 导出pdf格式合同
            Map<String, String> res_file_map1 = JasperUtil.doExport(jasperPrintList, "内部转让债权协议.pdf", wmsInveClerkProtocolSearchBeanVO.getBill_code());
            res_list.add(res_file_map1);
            Map<String, String> res_file_map2 = JasperUtil.doExport(jasperPrintList_out, "债权转让及受让协议.pdf",
                                                                    wmsInveClerkProtocolSearchBeanVO.getBill_code());
            res_list.add(res_file_map2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res_list;
    }

    /**
     * @Title: getClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回柜员协议信息
     * @author: DongHao
     * @time:2017年3月22日 上午10:05:08
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getClerkProtocolByWmsInveTransaId(java.lang.Integer)
     * history:
     * 1、2017年3月22日 DongHao 创建方法
    */
    @Override
    public Map<String, Object> getClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id)
    {
        return wmsinveclerkprotocolDao.getClerkProtocolByWmsInveTransaIdToWmsInveTransa(wms_inve_transa_id);
    }

    /**
     * @Title: updateWmsInveTransaBillCode
     * @Description: 根据主键更新上单表合同编码
     * @param transaId
     * @param wms_inve_clerk_protocol_id 
     * @author: zhangyunfei
     * @time:2017年4月3日 上午3:47:04
     * history:
     * 1、2017年4月3日 Administrator 创建方法
     */
    private void updateWmsInveTransaBillCode(Integer transaId, Integer wms_inve_clerk_protocol_id)
    {
        WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO = new WmsInveClerkProtocolSearchBeanVO();
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_id(wms_inve_clerk_protocol_id);
        Map<String, Object> map = wmsinveclerkprotocolDao.getWmsInveClerkProtocolById(wmsInveClerkProtocolSearchBeanVO);
        WmsInveTransa wmsInveTransa = new WmsInveTransa();
        wmsInveTransa.setWms_inve_transa_id(transaId);
        wmsInveTransa.setFinancial_bill_code(map.get("prot_code").toString());
        // 根据主键更新单据编号
        wmsInveTransaDao.update(wmsInveTransa);
    }

    /**
     * @Title: isProtCodedup
     * @Description: 验证合同编码是否重复
     * @return 返回布尔类型  重复返回true
     * @author: zhangyunfei
     * @time:2017年4月30日 上午10:14:13
     * history:
     * 1、2017年4月30日 Administrator 创建方法
     */
    private boolean isProtCodedup(String prot_code)
    {
        // 验证线下合同编号是否有重复
        int count = wmsinveclerkprotocolDao.getClerkProtocolProtCodeCount(prot_code);
        // count>0 说明有重复 返回错误信息
        if (count > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * @Title: savePublicOrCurrentProtocol
     * @Description: 根据当前柜员合同状态 生成公益合同或活期合同
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午6:21:25
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#savePublicOrCurrentProtocol()
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    @Override
    public void savePublicOrCurrentProtocol()
    {
        // 获取可以生成公益合同或活期合同的柜员合同(柜员合同主键、上单主键、合同到日期) 再根据合同到期日期+1天与当前时间比较
        List<WmsInveClerkProtocol> canBePublicOrCurrentProtocolList = wmsinveclerkprotocolDao.getCanBePublicOrCurrentProtocolList();
        String end_of_date = "";
        // 到期日+一天
        Date end_date_addDay = null;
        // 到期日+一个月+一天
        Date end_date_addMonth = null;
        // 当前时间
        Date nowDate = DateUtil.getDate10(new Date());

        // 活期合同开始日期
        Date current_begin_of_date = DateUtil.getDate10(new Date());

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        WmsInveTransa wmsInveTransa = new WmsInveTransa();

        for (int i = 0; i < canBePublicOrCurrentProtocolList.size(); i++)
        {
            // 合同到期日期
            end_of_date = canBePublicOrCurrentProtocolList.get(i).getEnd_of_date_str();

            // 获得到期日期+1天(生成公益合同)
            end_date_addDay = DateUtil.getDate10(DateUtil.getDateAddDays(DateUtil.strDate(end_of_date, null), 1));
            // 活期到期日+一个月+一天后的日期(生成活期合同)
            end_date_addMonth = DateUtil.getDate10(DateUtil.getDateAddDays(DateUtil.getDatePlusMonths(DateUtil.strDate(end_of_date, null), 1), 1));
            // 如果end_date_addDay等于当前时间 生成公益合同
            if (end_date_addDay.compareTo(nowDate) == 0)
            {
                // 合同类型为公益合同
                canBePublicOrCurrentProtocolList.get(i).setProt_code(canBePublicOrCurrentProtocolList.get(i).getProt_code());
                canBePublicOrCurrentProtocolList.get(i).setProt_type("1");
                canBePublicOrCurrentProtocolList.get(i).setCategory_name("公益6号");
                canBePublicOrCurrentProtocolList.get(i).setExpect_interest("6");
                canBePublicOrCurrentProtocolList.get(i).setCreate_timestamp(new Timestamp(DateUtil.getDatePlusMonths(canBePublicOrCurrentProtocolList.get(i).getBegin_of_date(), Integer.parseInt(canBePublicOrCurrentProtocolList.get(i).getProduct_deadline())).getTime()));
                canBePublicOrCurrentProtocolList.get(i).setBegin_of_date(new Timestamp(DateUtil.getDatePlusMonths(canBePublicOrCurrentProtocolList.get(i).getBegin_of_date(), Integer.parseInt(canBePublicOrCurrentProtocolList.get(i).getProduct_deadline())).getTime()));
                canBePublicOrCurrentProtocolList.get(i).setEnd_of_date(new Timestamp(DateUtil.getDatePlusMonths(DateUtil.strDate(end_of_date, null), 1).getTime()));
                canBePublicOrCurrentProtocolList.get(i).setProduct_deadline("1");
                canBePublicOrCurrentProtocolList.get(i).setEnable_flag("1");
                canBePublicOrCurrentProtocolList.get(i).setCreate_user_id(115);
                // 通过上单主键和柜员合同主键查询出当前匹配生效的债权
                paramsMap.put("wms_inve_transa_id", canBePublicOrCurrentProtocolList.get(i).getWms_inve_transa_id());
                paramsMap.put("wms_inve_clerk_protocol_id", canBePublicOrCurrentProtocolList.get(i).getWms_inve_clerk_protocol_id());
                // 将柜员合同主键设置成null
                canBePublicOrCurrentProtocolList.get(i).setWms_inve_clerk_protocol_id(null);
                // 保存柜员合同(公益/活期)
                saveWmsInveClerkProtocol(paramsMap, canBePublicOrCurrentProtocolList.get(i));
                // 更新原合同实际到期日
                paramsMap.put("act_end_of_date", new Timestamp(DateUtil.strDate(end_of_date, null).getTime()));
                wmsinveclerkprotocolDao.updateProtocolActEndOfDateByParams(paramsMap);
            }
            // 如果end_date_addMonth = 当前时间 生成活期合同
            else if (end_date_addMonth.compareTo(nowDate) == 0)
            {
                /**获取原始上单时间 
                   *如果是2016-7-1之前活期合同开始时间公益合同到期日+一天 
                   *如果是2016-7-1之前活期合同开始时间公益合同到期日
                **/
                wmsInveTransa = wmsInveTransaDao.getOldDateOfPaymentById(canBePublicOrCurrentProtocolList.get(i).getWms_inve_transa_id());
                // 传参 1原始上单时间 2原柜员合同到期日期+一个月(公益合同到期日期)
                current_begin_of_date = getCurrentProtocolStartDate(wmsInveTransa.getOld_date_of_payment(), DateUtil.getDatePlusMonths(DateUtil.strDate(end_of_date, null), 1));
                // 合同类型为活期合同
                canBePublicOrCurrentProtocolList.get(i).setProt_code(canBePublicOrCurrentProtocolList.get(i).getProt_code());
                canBePublicOrCurrentProtocolList.get(i).setBegin_of_date(new Timestamp(current_begin_of_date.getTime()));
                canBePublicOrCurrentProtocolList.get(i).setProt_type("2");
                canBePublicOrCurrentProtocolList.get(i).setProduct_deadline(null);
                canBePublicOrCurrentProtocolList.get(i).setEnable_flag("1");
                canBePublicOrCurrentProtocolList.get(i).setCreate_user_id(115);
                // 创建时间取合同起始时间 而不是当前时间 打印合同时合同编号需要通过创建时间获得
                canBePublicOrCurrentProtocolList.get(i).setCreate_timestamp(new Timestamp(current_begin_of_date.getTime()));

                // 通过上单主键和柜员合同主键查询出当前匹配生效的债权
                paramsMap.put("wms_inve_transa_id", canBePublicOrCurrentProtocolList.get(i).getWms_inve_transa_id());
                paramsMap.put("wms_inve_clerk_protocol_id", canBePublicOrCurrentProtocolList.get(i).getWms_inve_clerk_protocol_id());
                // 将柜员合同主键设置成null
                canBePublicOrCurrentProtocolList.get(i).setWms_inve_clerk_protocol_id(null);
                // 保存柜员合同(公益/活期)
                saveWmsInveClerkProtocol(paramsMap, canBePublicOrCurrentProtocolList.get(i));
                // 更新公益合同实际到期日
                paramsMap.put("act_end_of_date", new Timestamp(DateUtil.getDatePlusMonths(DateUtil.strDate(end_of_date, null), 1).getTime()));
                wmsinveclerkprotocolDao.updatePubProtocolActEndOfDateById(paramsMap);
            }
        }
    }

    /**
     * @Title: getWithoutPaperProtocolTransaProtocolInfoList
     * @Description: 根据手机号获取无纸质合同的单据信息集合
     * @param mobile_phone 手机号
     * @return 无纸质合同的单据信息集合
     * @author: jinzhm
     * @time:2017年7月20日 下午5:46:28
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWithoutPaperProtocolTransaProtocolInfoList(java.lang.String)
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> getWithoutPaperProtocolTransaProtocolInfoList(String mobile_phone)
    {
        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mobile_phone", mobile_phone);
        // 查询购买无纸质合同的单据信息集合
        List<Map<String, Object>> transaInfoList = wmsinveclerkprotocolDao.getAllCusProtocolWithoutPaperTransaInfoList(paramMap);

        // 返回数据
        List<Map<String, Object>> retMapList = new ArrayList<Map<String, Object>>();

        // 封装数据使用临时对象
        Map<String, Object> prodDataMap = null;
        Map<String, Object> transaDataMap = null;
        Integer currentCategoryId = -1;

        // 循环所有单据信息集合
        for (int i = 0; i < transaInfoList.size(); i++)
        {
            Map<String, Object> transaInfoMap = transaInfoList.get(i);
            // 如果正在循环的单据和当前正在处理的产品id不相同
            if (currentCategoryId.compareTo(Integer.parseInt(String.valueOf(transaInfoMap.get("wms_inve_pruduct_category_id")))) != 0)
            {
                // 处理的是新产品的时候，创建新map对象封装产品信息
                prodDataMap = new HashMap<String, Object>();
                // 产品名称
                prodDataMap.put("prod_name", String.valueOf(transaInfoMap.get("prod_name")));
                // 利率
                prodDataMap.put("product_interest", String.valueOf(transaInfoMap.get("product_interest")));
                // 产品期限
                prodDataMap.put("product_deadline", String.valueOf(transaInfoMap.get("product_deadline")));
                // 单据集合
                prodDataMap.put("transa_list", new ArrayList<Map<String, Object>>());
                // 设置当前处理的产品id
                currentCategoryId = new Double(String.valueOf(transaInfoMap.get("wms_inve_pruduct_category_id"))).intValue();
                // 保存返回数据集合中
                retMapList.add(prodDataMap);
            }

            // 封装单据信息
            transaDataMap = new HashMap<String, Object>();
            // 单据主键
            transaDataMap.put("transa_id", String.valueOf(transaInfoMap.get("transa_id")));
            // 支付日期
            transaDataMap.put("date_of_payment_str", String.valueOf(transaInfoMap.get("date_of_payment_str")));
            // 投资金额
            transaDataMap.put("product_account", String.valueOf(transaInfoMap.get("product_account")));
            // 状态
            transaDataMap.put("status", String.valueOf(transaInfoMap.get("status")));
            
            // 保存单据信息
            ((List<Map<String, Object>>) prodDataMap.get("transa_list")).add(transaDataMap);
        }

        return retMapList;
    }


    /**
     * @Title: saveWmsInveClerkProtocol
     * @Description: 生成柜员合同(公益/活期) 并保存债权匹配关系
     * @param paramsMap
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年5月15日 下午2:12:26
     * history:
     * 1、2017年5月15日 Administrator 创建方法
    */
    public void saveWmsInveClerkProtocol(Map<String, Object> paramsMap, WmsInveClerkProtocol wmsInveClerkProtocol)
    {
        WmsInveClerkProtocolTransaCrepkg wmsInveClerkProtocolTransaCrepkg = new WmsInveClerkProtocolTransaCrepkg();
        // 债权匹配关联信息
        List<WmsInveTransaCrepkg> WmsInveTransaCrepkgList = wmsInveTransaCrepkgDao.getWmsInveTransaCrepkgList(paramsMap);
        // 保存当前公益合同 返回主键
        wmsinveclerkprotocolDao.saveWmsInveClerkProtocol(wmsInveClerkProtocol);

        for (int i = 0; i < WmsInveTransaCrepkgList.size(); i++)
        {
            WmsInveTransaCrepkgList.get(i).setWms_inve_transa_id(wmsInveClerkProtocol.getWms_inve_transa_id());
            WmsInveTransaCrepkgList.get(i).setWms_inve_clerk_protocol_id(wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
            WmsInveTransaCrepkgList.get(i).setCreate_user_id(115);
            WmsInveTransaCrepkgList.get(i).setCreate_timestamp(wmsInveClerkProtocol.getBegin_of_date());
            WmsInveTransaCrepkgList.get(i).setEnable_flag("1");

            WmsInveTransaCrepkgList.get(i).setAcl_date(new java.sql.Date(wmsInveClerkProtocol.getBegin_of_date().getTime()));
        }
        // 更新原合同enable_flag为失效
        wmsInveTransaCrepkgDao.disableWmsInveTransaCrepkg(paramsMap);
        // 批量保存匹配关系
        if (WmsInveTransaCrepkgList.size() > 0)
        {
            wmsInveTransaCrepkgDao.saveWmsInveTransaCrepkgBatch(WmsInveTransaCrepkgList);
        }
        // 此处从新定义一个map 不改变paramsMap中的值
        Map<String, Object> pMap = new HashMap<String, Object>();
        pMap.put("wms_inve_clerk_protocol_id", wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
        pMap.put("wms_inve_transa_id", wmsInveClerkProtocol.getWms_inve_transa_id());
        // 根据新生成的柜员合同去生成新的匹配历史
        String wms_inve_transa_crepkg_ids = wmsInveTransaCrepkgDao.getWmsInveTransaCrepkgIds(pMap);
        // 当存在匹配历史的时候 给当前公益合同生成债权匹配历史关系
        if (wms_inve_transa_crepkg_ids != null && !"".equals(wms_inve_transa_crepkg_ids.trim()))
        {
            wmsInveClerkProtocolTransaCrepkg.setWms_inve_transa_id(wmsInveClerkProtocol.getWms_inve_transa_id());
            wmsInveClerkProtocolTransaCrepkg.setWms_inve_clerk_protocol_id(wmsInveClerkProtocol.getWms_inve_clerk_protocol_id());
            wmsInveClerkProtocolTransaCrepkg.setWms_inve_transa_crepkg_ids(wms_inve_transa_crepkg_ids);
            wmsInveClerkProtocolTransaCrepkg.setCreate_user_id(115);
            wmsInveClerkProtocolTransaCrepkg.setEnable_flag("1");
            wmsInveClerkProtocolTransaCrepkg.setCreate_timestamp(wmsInveClerkProtocol.getBegin_of_date());
            wmsInveClerkProtocolTransaCrepkgdDao.save(wmsInveClerkProtocolTransaCrepkg);
        }
    }

    // 判断支付日期如果在2016年7月1号之后 活期合同开始日期为公益合同到期日期+一天
    // 2016年7月1号之前 活期合同开始日期为公益合同到期日期
    private Date getCurrentProtocolStartDate(Date old_date_of_payment, Date public_end_of_date)
    {
        Date magic_date = DateUtil.strDate("2016-07-01", null);
        if (!DateUtil.before(old_date_of_payment, magic_date))
        {
            return DateUtil.getDateAddDays(public_end_of_date, 1);
        }
        return public_end_of_date;
    }

    /**
     * @Title: getWmsInveClerkProtocolByTransaIdForPhone
     * @Description: 手持查询端查询柜员协议
     * @param transa_id 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午2:05:49
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolByTransaIdForPhone(java.lang.String)
     * history:
     * 1、2017年7月20日 zhangyunfei 创建方法
    */
    @Override
    public WmsInveClerkProtocol getWmsInveClerkProtocolByTransaIdForPhone(String transa_id, String product_account)
    {
        // 查询柜员合同表对应的单据的合同编号
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_transa_id", transa_id);
        paramsMap.put("product_account", product_account);

        // 通过上单主键查询生效的柜员合同 倒序排序
        List<WmsInveClerkProtocol> wmsInveClerkProtocolList = wmsinveclerkprotocolDao.getWmsInveClerkProtocolByTransaIdForPhone(paramsMap);
        // 返回主键最大的一条 （最新）
        return wmsInveClerkProtocolList.get(0);
    }

    /**
     * @Title: getWmInveClerkProtocolTransaCrepkg
     * @Description: 根据上单主键和柜员合同主键查询 债权匹配历史表主键
     * @param wms_inve_transa_id
     * @param wms_inve_clerk_protocol_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午2:37:42
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmInveClerkProtocolTransaCrepkg(java.lang.Integer, java.lang.Integer)
     * history:
     * 1、2017年7月20日 admin 创建方法
    */
    @Override
    public WmsInveClerkProtocolTransaCrepkg getWmInveClerkProtocolTransaCrepkg(Integer wms_inve_transa_id, Integer wms_inve_clerk_protocol_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 上单主键
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        // 柜员合同主键
        paramMap.put("wms_inve_clerk_protocol_id", wms_inve_clerk_protocol_id);
        // 取list中的第一个 最新的一个
        return wmsInveClerkProtocolTransaCrepkgdDao.getWmsInveClerkProtocolTransaCrepkg(paramMap).get(0);
    }

    /**
     * @Title: getWmsInveClerkProtocolCreateUserById
     * @Description: 查询合同的原始创建人
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午4:19:31
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getWmsInveClerkProtocolCreateUserById(com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO)
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    @Override
    public UserBean getWmsInveClerkProtocolCreateUserById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        return wmsinveclerkprotocolDao.getWmsInveClerkProtocolCreateUserById(wmsInveClerkProtocolSearchBeanVO);
    }

    /**
     * @Title: getLastWmsInveClaimsInfo
     * @Description:  根据上单主键和合同主键  查询出最新的债权匹配记录
     * @param transa_id
     * @param protocol_id
     * @return 
     * @author: admin
     * @time:2017年7月21日 上午5:17:44
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#getLastWmsInveClaimsInfo(java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    @Override
    public List<Map<String, Object>> getLastWmsInveClaimsInfo(String transa_id, String protocol_id)
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("wms_inve_transa_id", transa_id);
        paramsMap.put("wms_inve_clerk_protocol_id", protocol_id);

        return wmsinveclerkprotocolDao.getLastWmsInveClaimsInfo(paramsMap);
    }

    /**
     * @Title: checkWmsInveClerkProtocolOper
     * @Description:  检查是否可以对柜员合同进行操作(23-1不允许操作无纸质合同 内部/外部 /更换债权)
     * @param wms_inve_pruduct_category_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午7:30:04
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#checkWmsInveClerkProtocolOper(java.lang.Integer)
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    @Override
    public String checkWmsInveClerkProtocolOper(Integer wms_inve_pruduct_category_id)
    {
        // 通过产品主键查询产品
        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wms_inve_pruduct_category_id);
        // 产品不提供纸质合同
        if (!"1".equals(wmsInvePruductCategory.getHas_paper_protocol()))
        {
            // 5008配置为禁止操作合同所设置的时间
            WmsSysProperty wmsSysProperty = wmsSysPropertyDao.get(5008);
            // 当前时间小时的数字
            int nowHourInt = Integer.parseInt(DateUtil.date2String(new Date(), "HH"));
            if (wmsSysProperty != null)
            {
                // 获得配置的限制时间段
                String[] value = wmsSysProperty.getProperty_value().split("-");
                // 将当前时间和限制时间段比较，如果当前时间在限制时间段内，则返回不能查看
                if ((Integer.parseInt(value[0]) > Integer.parseInt(value[1]) && (Integer.parseInt(value[0]) <= nowHourInt || nowHourInt < Integer.parseInt(value[1]))) || (Integer.parseInt(value[0]) < Integer.parseInt(value[1]) && (Integer.parseInt(value[0]) <= nowHourInt && nowHourInt < Integer.parseInt(value[1]))))
                {
                    return "false";
                }
            }

        }
        return "true";
    }

    /**
     * @Title: phoneGetWmsCusProtocol
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询主协议信息
     * @param transa_id
     * @param procotolIds
     * @param protocol_id 
     * @author: zhangyunfei
     * @time:2017年7月24日 上午9:10:12
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#phoneGetWmsCusProtocol(java.lang.String, java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月24日 zhangyunfei 创建方法
    */
    @Override
    public Map<String, Object> phoneGetWmsCusProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        return phonePreviewWmsCusProtocolWX(wmsInveClerkProtocolSearchBeanVO);
    }

    /**
     * @Title: phoneGetWmsCusMainProtocolInfo
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询主协议相关信息
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: admin
     * @time:2017年7月24日 下午1:44:50
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#phoneGetWmsCusMainProtocolInfo(com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO)
     * history:
     * 1、2017年7月24日 admin 创建方法
    */
    @Override
    public Map<String, Object> phoneGetWmsCusMainProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> pMap = phonePreviewWmsCusProtocolWX(wmsInveClerkProtocolSearchBeanVO);
        // 客户收益计划
        int wms_inve_pruduct_category_id = Integer.parseInt(pMap.get("wms_inve_pruduct_category_id").toString());
        int product_account = Integer.parseInt(pMap.get("bas_product_account").toString());
        Date begin_of_date = DateUtil.strToSqlDate(pMap.get("begin_of_date").toString(), null);
        Map<String, Object> cIMap = wmsInveTransaIncomeService.computeCategoryIncome(wms_inve_pruduct_category_id, product_account, begin_of_date);
        // 赎回所生成的合同 筛选cIMap中日期大于赎回日期的集合
        if (pMap.get("wms_inve_redeem_id") != null)
        {
            Date redeem_date = DateUtil.strToSqlDate(pMap.get("begin_of_date").toString(), null);
            List<Map<String, Object>> tList = wmsInveTransaIncomeService.getIncomeListGtRedeemDate((List<Map<String, Object>>) cIMap.get("incomeList"), redeem_date);
            cIMap.put("incomeList", tList);
        }
        wmsInveTransaIncomeService.calTotal_Income(cIMap);
        cIMap.put("prot_code", pMap.get("prot_code"));
        // cIMap.put("logoDir", logoDir);
        cIMap.put("category_name", pMap.get("category_name"));
        cIMap.put("product_deadline_info", pMap.get("product_deadline") + "个月");
        cIMap.put("expect_interest", pMap.get("expect_interest"));
        cIMap.put("product_account", pMap.get("bas_product_account") + "万元");
        cIMap.put("category_interest_pay_method_name", pMap.get("category_interest_pay_method_name"));
        cIMap.put("begin_of_date", pMap.get("begin_of_date"));
        // 主协议参数
        resMap.put("pMap", pMap);
        // 收益计划书参数
        resMap.put("cIMap", cIMap);

        return resMap;
    }

    /**
     * @Title: phoneGetWmsCusInterProtocolInfo
     * @Description:  微信公众号ZSH调用 通过上单主键 协议主键查询内部转让协议相关信息
     * @param transa_id
     * @param protocol_id 
     * @author: admin
     * @time:2017年7月24日 上午11:23:04
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#phoneGetWmsCusInterProtocolInfo(java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月24日 admin 创建方法
    */
    @Override
    public Map<String, Object> phoneGetWmsCusInterProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        // 获取单据原始业务员
        UserBean user = getWmsInveClerkProtocolCreateUserById(wmsInveClerkProtocolSearchBeanVO);
        Map<String, Object> pMap = phonePreviewWmsCusProtocolWX(wmsInveClerkProtocolSearchBeanVO);
        // zMap 存放内部转让债权协议所需参数
        Map<String, Object> zMap = new HashMap<String, Object>();
        // sdMap 存放原债权人签字
        Map<String, Object> sdMap = new HashMap<String, Object>();
        Map<String, Object> relMap = new HashMap<String, Object>();
        // 存放签字map
        List<Map<String, Object>> sdList = new ArrayList<Map<String, Object>>();
        Map<String, Object> claimMap = getLastWmsInveClaimsInfo(wmsInveClerkProtocolSearchBeanVO.getWms_inve_transa_id().toString(), wmsInveClerkProtocolSearchBeanVO.getWms_inve_clerk_protocol_id().toString()).get(0);
        // 债权匹配历史表主键
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_transa_crepkg_id(Integer.parseInt(claimMap.get("wms_inve_clerk_protocol_transa_crepkg_id").toString()));
        // 根据上单主键和协议主键查找所有关联的债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgService.getAllTransaCrepkgByGroup(wmsInveClerkProtocolSearchBeanVO);
        Map<String, List<Map<String, Object>>> crepkgMap = wmsInveTransaCrepkgService.groupTransaCrepkgList(transaCrepkgList);

        // 拼接页脚字符串
        String footInfo = "Add：" + pMap.get("r_b_area") + "  TEL：400 000 2555  Web：www.zxptp.com";
        // 转让协议编号
        zMap.put("trans_code", pMap.get("trans_code"));
        // zMap.put("header1Dir", header1Dir);
        zMap.put("b_address", pMap.get("b_address"));
        zMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
        zMap.put("intran_b_name", pMap.get("intran_name"));
        zMap.put("intran_b_address", pMap.get("intran_address"));
        zMap.put("intran_b_id", pMap.get("intran_id"));
        zMap.put("prot_code", pMap.get("prot_code"));
        // zMap.put("logoDir", logoDir);
        zMap.put("sign_date", pMap.get("sign_date"));

        // 经办人-----业务员
        zMap.put("salesman_name", pMap.get("salesman_name"));
        zMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));
        // 经办人-----业务员
        zMap.put("printman_name", user.getRealname());
        zMap.put("printman_shortCode", user.getUserCode());
        // 备注
        if (pMap.get("prot_type") == null)
        {
            zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
        }
        else
        {
            zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            if ("1".equals(pMap.get("prot_type").toString()))
            {
                zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
            }
            else if ("2".equals(pMap.get("prot_type").toString()))
            {
                zMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
            }
        }
        // 页脚说明
        zMap.put("footInfo", footInfo);
        
        // 转让协议编号
        zMap.put("trans_code", pMap.get("trans_code"));
        zMap.put("b_address", pMap.get("b_address"));
        zMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
        zMap.put("intran_b_name", pMap.get("intran_name"));
        zMap.put("intran_b_address", pMap.get("intran_address"));
        zMap.put("intran_b_id", pMap.get("intran_id"));
        zMap.put("prot_code", pMap.get("prot_code"));
        zMap.put("sign_date", pMap.get("sign_date"));

        // 经办人-----业务员
        zMap.put("salesman_name", pMap.get("salesman_name"));
        zMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));
        // 经办人-----业务员
        zMap.put("printman_name", user.getRealname());
        zMap.put("printman_shortCode", user.getUserCode());
        // 备注
        if (pMap.get("prot_type") == null)
        {
            zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
        }
        else
        {
            zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            if ("1".equals(pMap.get("prot_type").toString()))
            {
                zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
            }
            else if ("2".equals(pMap.get("prot_type").toString()))
            {
                zMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
            }
        }
        // 页脚说明
        zMap.put("footInfo", footInfo);

        // 他项人不是卓信小额贷的list
        List<Map<String, Object>> tcList = new ArrayList<Map<String, Object>>();
        // 合计
        BigDecimal sum_cre_pledge_mon = BigDecimal.ZERO;
        // 遍历map 将map按照他项人分类 卓信小额贷是一类 其他是另一类(查看合并版本的合同)
        for (Map.Entry<String, List<Map<String, Object>>> entry : crepkgMap.entrySet())
        {
            // 当他项人为赵燕国时 不签内转内合同
            if (!"赵燕国".equals(entry.getKey()))
            {
                String intran_a_name = (String) entry.getValue().get(0).get("intran_a_name");
                // 卓信小额贷是按原始版本打印多个协议
                if ("卓信小额贷".equals(entry.getKey()))
                {
                    zMap.put("tList", entry.getValue());
                    zMap.put("intran_a_name", entry.getValue().get(0).get("intran_a_name"));
                    zMap.put("intran_a_company_id", entry.getValue().get(0).get("intran_a_id"));
                    // 他项人为组织 甲方取组织名称
                    zMap.put("a_company_name", entry.getValue().get(0).get("a_company_name"));
                    // 组织标示 0非组织 1组织
                    zMap.put("is_company", entry.getValue().get(0).get("is_company"));
                }
                else
                {
                    tcList.addAll(entry.getValue());
                    if (relMap.get("intran_a_name") == null)
                    {
                        relMap.put(intran_a_name, intran_a_name);
                    }
                    // 计算总和
                    for (int k = 0; k < entry.getValue().size(); k++)
                    {
                        sum_cre_pledge_mon = sum_cre_pledge_mon.add((BigDecimal) entry.getValue().get(k).get("acl_mon"));
                    }
                }
            }
        }
        // 当存在内部转让协议合并版本号 并且他项人不为赵燕国或卓信小额贷的list不为空
        if (tcList.size() > 0)
        {
            // 内部转让协议他项人不是赵燕国和卓信小额贷时所打印合并其他所有他项人的合同模板
            zMap.put("intran_a_name", "详见转让债权明细列表");
            zMap.put("intran_a_id", "详见转让债权明细列表");
            int j = 1;
            for (Map.Entry<String, Object> entry : relMap.entrySet())
            {
                if (j % 2 == 0)
                {
                    sdMap.put("signDir2", entry.getValue());
                    sdList.add(sdMap);
                    sdMap = new HashMap<String, Object>();
                }
                else
                {
                    sdMap.put("signDir1", entry.getValue());
                    if (relMap.size() % 2 == 1 && j == relMap.size())
                    {
                        sdList.add(sdMap);
                    }
                }
                j++;
            }
            // 计算合计 并格式化
            DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
            String sum_cre_pledge_mon_str = fmt.format(sum_cre_pledge_mon);
            // 转让额度总和
            zMap.put("sum_cre_pledge_mon", sum_cre_pledge_mon_str);
            // 甲方签字集合
            zMap.put("sdList", sdList);
            // 债权明细列表
            zMap.put("tcList", tcList);
        }
        return zMap;
    }

    /**
     * @Title: phoneGetWmsCusM2MProtocolInfo
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询外部转让协议相关信息
     * @param transa_id
     * @param protocol_id 
     * @author: admin
     * @time:2017年7月24日 上午11:28:04
     * @see com.zx.emanage.inve.service.IWmsInveClerkProtocolService#phoneGetWmsCusM2MProtocolInfo(java.lang.String, java.lang.String)
     * history:
     * 1、2017年7月24日 admin 创建方法
    */
    @Override
    public Map<String, Object> phoneGetWmsCusM2MProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        UserBean user = getWmsInveClerkProtocolCreateUserById(wmsInveClerkProtocolSearchBeanVO);
        // cMap 存放债权转让受让协议所需参数
        Map<String, Object> cMap = new HashMap<String, Object>();
        
        Map<String, Object> claimMap = getLastWmsInveClaimsInfo(wmsInveClerkProtocolSearchBeanVO.getWms_inve_transa_id().toString(), wmsInveClerkProtocolSearchBeanVO.getWms_inve_clerk_protocol_id().toString()).get(0);
        // 债权匹配历史表主键
        wmsInveClerkProtocolSearchBeanVO.setWms_inve_clerk_protocol_transa_crepkg_id(Integer.parseInt(claimMap.get("wms_inve_clerk_protocol_transa_crepkg_id").toString()));
        // 根据上单主键和协议主键查找所有关联的债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgService.getAllTransaCrepkgByGroup(wmsInveClerkProtocolSearchBeanVO);
        Map<String, List<Map<String, Object>>> crepkgMap = wmsInveTransaCrepkgService.groupTransaCrepkgList(transaCrepkgList);
        Map<String, Object> pMap = phonePreviewWmsCusProtocolWX(wmsInveClerkProtocolSearchBeanVO);
        // 拼接页脚字符串
        String footInfo = "Add：" + pMap.get("r_b_area") + "  TEL：400 000 2555  Web：www.zxptp.com";
        cMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
        cMap.put("a_contact_address", pMap.get("a_contact_address"));
        cMap.put("a_id_card", pMap.get("a_id_card"));
        cMap.put("a_name", pMap.get("a_name"));
        cMap.put("intran_id", pMap.get("intran_id"));
        cMap.put("intran_name", pMap.get("intran_name"));
        cMap.put("prot_code", pMap.get("prot_code"));
        // cMap.put("logoDir", logoDir);

        cMap.put("category_name", pMap.get("category_name"));
        cMap.put("trans_code", pMap.get("trans_code"));
        cMap.put("begin_of_date", pMap.get("begin_of_date"));
        cMap.put("card_no", pMap.get("card_no"));
        cMap.put("bank_info", pMap.get("bank_info"));
        cMap.put("sum_cre_pledge_mon", pMap.get("sum_cre_pledge_mon"));
        cMap.put("sum_cre_pledge_mon_upper", pMap.get("sum_cre_pledge_mon_upper"));
        cMap.put("signDir_b", pMap.get("intran_name"));
        // 经办人-----业务员
        cMap.put("salesman_name", pMap.get("salesman_name"));
        cMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));

        // 审核人-----当前登录人 打印人
        cMap.put("printman_name", user.getRealname());
        cMap.put("printman_shortCode", user.getUserCode());
        // 客户签字(外部转让)
        cMap.put("client_sign_m2m", pMap.get("client_sign_main"));
        // 备注
        if (pMap.get("prot_type") == null)
        {
            cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
        }
        else
        {
            cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
            if ("1".equals(pMap.get("prot_type").toString()))
            {
                cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
            }
            else if ("2".equals(pMap.get("prot_type").toString()))
            {
                cMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
            }
        }
        // 页脚说明
        cMap.put("footInfo", footInfo);
        // 3.1/6.1/6.2项文字说明
        cMap.put("info31", "       3.1 转让价款：乙方向甲方支付人民币(大写)" + pMap.get("sum_cre_pledge_mon_upper") + " 万元整（人民币小写" + pMap.get("sum_cre_pledge_mon") + "元）作为上述债权转让的对价。");
        cMap.put("info61", "       6.1双方同意" + pMap.get("r_b_name") + "作为本协议签订的见证人。");
        cMap.put("info62", "       6.2乙方同意委托" + pMap.get("r_b_name") + "对其受让债权进行信用咨询和管理服务。乙方与" + pMap.get("r_b_name") + "另行签订《个人出借与咨询服务协议》。");
        // 证明人
        cMap.put("r_b_name", pMap.get("r_b_name"));
        // cMap.put("header1Dir", header1Dir);
        cMap.put("tList", transaCrepkgList);

        return cMap;
    }

    /**
     * 
     * @Title: phonePreviewWmsCusProtocolWX
     * @Description: 根据上单主键和合同主键查询协议所需的参数 返回map
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月26日 下午5:24:13
     * history:
     * 1、2017年7月26日 admin 创建方法
     */
    private Map<String, Object> phonePreviewWmsCusProtocolWX(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        // 获取单据原始柜员
        UserBean user = getWmsInveClerkProtocolCreateUserById(wmsInveClerkProtocolSearchBeanVO);
        //返回 存放人出借咨询与服务协议、委托代扣款授权书、债权包签收声明、个人出借咨询与服务补充协议所需参数map
        Map<String, Object> pMap = getWmsInveClerkProtocolById(wmsInveClerkProtocolSearchBeanVO);
        // 审核人
        pMap.put("printman_name", user.getRealname());
        pMap.put("printman_shortCode", user.getUserCode());
        // 客户签字路径
        String customer_signature_path = pMap.get("customer_signature_path").toString();
        // pad客户签字 打印机打签字 未签字不打印
        if (customer_signature_path != null && !"".equals(customer_signature_path))
        {
            pMap.put("client_sign_main", pMap.get("a_name"));
            // 客户签字(补充协议)
            pMap.put("client_sign_sub", pMap.get("a_name"));
        }
        else
        {
            pMap.put("client_sign_main", "");
            // 客户签字(补充协议)
            pMap.put("client_sign_sub", "");
        }
        return pMap;
    }
}
