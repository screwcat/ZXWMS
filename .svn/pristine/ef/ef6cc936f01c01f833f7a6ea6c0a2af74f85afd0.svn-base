package com.zx.emanage.sysmanage.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zx.emanage.sysmanage.persist.SysUserDao;
import com.zx.sframe.util.GlobalVal;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CodeNoUtil
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
public class CodeNoUtil
{

    private static String CUS_CODE = "WMS_CUS_CUSTOMER_HEAD_CUSTOMER_CODE";

    private static String CUS_CRE_CODE = "WMS_CUS_REC_CODE";

    private static String WMS_CUS_HOUSE_REC_CODE = "WMS_CUS_HOUSE_REC_CODE";

    private static String WMS_PROTOCOL_NEWCODE = "WMS_PROTOCOL_NEWCODE";

    private static String WMS_ENVI_PROT_CODE = "WMS_ENVI_PROT_CODE";

    private static String WMS_ENVI_NOTE_CODE = "WMS_ENVI_NOTE_CODE";

    private static String ENVI_CUS_CODE = "ENVI_WMS_CUS_CUSTOMER_HEAD_CUSTOMER_CODE";

    private static String WMS_INVE_RETURN_REDEEM_CODE = "WMS_INVE_RETURN_REDEEM_CODE";
    
    private static String WMS_FINA_HK_CODE ="WMS_FINA_HK_CODE";//财务还款流水编码
    
    private static String WMS_FINA_DY_CODE ="WMS_FINA_DY_CODE";//财务抵押物编码
    
    private static String WMS_POST_DUNNING_CJ_CODE = "WMS_POST_DUNNING_CJ_CODE";//单后催缴单编号
    
    private static String WMS_CUS_CAR_REC_CODE ="WMS_CUS_HOUSE_REC_CODE";//车贷贷款单据编号
    
    private static String WMS_CUS_LC_REC_CODE ="WMS_CUS_LC_REC_CODE";//理财产品编码
    
    private static String WMS_CUS_BG_REC_CODE ="WMS_CUS_BG_REC_CODE";//理财产品变更编码
    
    private static String WMS_INVE_DEBT_CODE ="WMS_INVE_DEBT_CODE";//债权变更申请单编码
    
    private static String WMS_INVE_SALARY_RULES_CODE ="WMS_INVE_SALARY_RULES_CODE";//理财薪资规则编号
    /**
     * 债权编码
     */
    private static String WMS_INVE_CREDIT_CODE = "WMS_INVE_CREDIT_CODE";

    /**
     * 债权拆分规则编码
     */
    private static String WMS_INVE_CREDIT_SPLIT_SPEC_CODE = "WMS_INVE_CREDIT_SPLIT_SPEC_CODE";

    /**组合贷编号*/
    private static String WMS_CUS_GROUP_CODE = "WMS_CUS_GROUP_CODE";

    /**公正他项还款提醒组合贷编号*/
    private static String WMS_WARN_GROUP_CODE = "WMS_WARN_GROUP_CODE";

    /**
     * 获取贷款客户编码
     * 
     * @return
     */
    public static String getCustomerCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", CUS_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNoMonth(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMM").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("KH").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("00000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 获取理财客户编码
     * 
     * @return
     */
    public static String getEnviCustomerCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", ENVI_CUS_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNoMonth(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMM").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("LC").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("00000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 获取理财单编号
     * 
     * @return
     */
    public static String getEnviNoteCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_ENVI_NOTE_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("LC").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("000000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 获取信贷单编码
     * 
     * @return
     */
    public static String getCreCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", CUS_CRE_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "XD" + dateStr + fmt.format(codeNo);
    }

    /**
     * 获取房贷单据编码
     * 
     * @return
     */
    public static String getHouseCreCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_CUS_HOUSE_REC_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "FD" + dateStr + fmt.format(codeNo);
    }

    /**
     * 获取贷款贷款合同编码
     * 
     * @return
     */
    public static String getProCreCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_PROTOCOL_NEWCODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyy年第MMdd").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append(dateStr);
        if (codeNo < 10)
        {
            reStr.append("0" + codeNo);
        }
        else
        {
            reStr.append(codeNo);
        }
        reStr.append("号");
        return reStr.toString();
    }

    /**
     * 获取理财协议编码
     * 
     * @return
     */
    public static String getEnviProCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_ENVI_PROT_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("ZX").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("0000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 获取赎回单据编码
     * 
     * @return
     */
    public static String getReturnRedeemCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_INVE_RETURN_REDEEM_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "SH" + dateStr + fmt.format(codeNo);
    }
    /**
     * 获取财务还款流水编号 
     * 样式：HK20150518000001
     * @return
     * @author hancd
     */
    public static String getFinaHKCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_FINA_HK_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
    	Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "HK" +dateStr + fmt.format(codeNo);
    }
    /**
     * 获取财务抵押物编号
     * 样式：DY20150518000001
     * @return
     * @author hancd
     */
    public static String getFinaDYCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_FINA_DY_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
    	Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "DY" +dateStr + fmt.format(codeNo);
    }
    /**
     * 获取贷后催缴单编号
     */
    public static String getPostDunningCJCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_POST_DUNNING_CJ_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
    	Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "CJ" +dateStr + fmt.format(codeNo);
    }
     /**
     * 获取车贷单据编码
     * 
     * @return
     */
    public static String getCarCreCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_CUS_CAR_REC_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "CD" + dateStr + fmt.format(codeNo);
    }
    
    /**
     * 获取理财产品编码
     * 
     * @return
     */
    public static String getLCCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_CUS_LC_REC_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "CP" + dateStr + fmt.format(codeNo);
    }
    
    /**
     * 获取理财产品变更编码
     * 
     * @return string
     * @author baisong
     */
    public static String getBGCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_CUS_BG_REC_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "BG" + dateStr + fmt.format(codeNo);
    }
    
    /**
     * 获取债权变更申请编码
     * 
     * @return String
     * @author yangqiyu
     */
    public static String getDebtCode(){
    	SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_INVE_DEBT_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "TZ" + dateStr + fmt.format(codeNo);
    }
    
    /**
     * 获取理财薪资规则编码
     * 
     * @return String
     * @author wangyihan
     */
    public static String getSalaryRulesCode(){
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_INVE_SALARY_RULES_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "XZGZ" + dateStr + fmt.format(codeNo);
    }

    /**
     * 
     * @Title: getWmsInveCreditCode
     * @Description: 获取债权包编码
     * @return 
     * @author: Guanxu
     * @time:2016年12月14日 下午4:24:05
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
     */
    public static String getWmsInveCreditCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_INVE_CREDIT_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("000000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 
     * @Title: getWmsInveCreditSplitSpecCode
     * @Description: 获取债权拆分编码
     * @return 
     * @author: Guanxu
     * @time:2016年12月14日 下午4:24:25
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
     */
    public static String getWmsInveCreditSplitSpecCode()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_INVE_CREDIT_SPLIT_SPEC_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(now);
        StringBuffer reStr = new StringBuffer();
        reStr.append("").append(dateStr);
        DecimalFormat fmt = new DecimalFormat("000000");
        reStr.append(fmt.format(codeNo));
        return reStr.toString();
    }

    /**
     * 
     * @Title: getGroup_code
     * @Description: TODO(贷款端组合贷编码)
     * @return 
     * @author: baisong
     * @time:2017年1月5日 下午2:05:55
     * history:
     * 1、2017年1月5日 baisong 创建方法
     */
    public static String getGroup_code()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_CUS_GROUP_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "ZH" + dateStr + fmt.format(codeNo);
    }

    /**
     * @Title: getWARN_Group_code
     * @Description: TODO(公正他项还款提醒组合贷编码)
     * @return 
     * @author: baisong
     * @time:2017年1月5日 下午2:06:19
     * history:
     * 1、2017年1月5日 baisong 创建方法
     */
    public static String getWARN_Group_code()
    {
        SysUserDao sysUserDao = (SysUserDao) GlobalVal.ctx.getBean("sysUserDao");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", WMS_WARN_GROUP_CODE);
        paramMap.put("org_no", 0);
        Long codeNo = sysUserDao.getRepeatCodeNo(paramMap);
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyMMdd").format(now);
        DecimalFormat fmt = new DecimalFormat("000000");
        return "ZH" + dateStr + fmt.format(codeNo);
    }
}
