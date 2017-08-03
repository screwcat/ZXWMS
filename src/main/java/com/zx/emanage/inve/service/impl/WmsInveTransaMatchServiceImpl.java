package com.zx.emanage.inve.service.impl;
/**
 * 版权所有：版权所有(C) 2014， 沈阳新融金融信息服务有限公司
 * 文件名称: WmsInveTransaMatchServiceImpl.java
 * 系统名称：WMS财务管理系统
 * 模块名称：财务管理
 * 完成日期：2014-8-20
 * 作    者   ：hancd
 * 内容摘要：  
 * 
 * 
 * 
 */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.exception.InveTransException;
import com.zx.emanage.inve.exception.InveTransSysException;
import com.zx.emanage.inve.exception.InveTransaConcurrentException;
import com.zx.emanage.inve.persist.WmsInveDebtOlnclaimsDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchBackupsDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.service.IWmsInveTransaMatchService;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.emanage.inve.vo.WmsInveTransaMatchSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaMatchVo;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransamatchService")
public class WmsInveTransaMatchServiceImpl implements IWmsInveTransaMatchService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveTransaMatchServiceImpl.class);

    @Autowired
    private WmsInveTransaMatchDao wmsinvetransamatchDao;

    @Autowired
    private WmsInveTransaDao wmsinvetransaDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;

    @Autowired
    private WmsInveRedeemDetailDao wmsInveRedeemDetailDao;

    @Autowired
    private WmsInveRedeemDao wmsInveRedeemDao;

    @Autowired
    private WmsSysPropertyDao wmsSysPropertyDao;

    @Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;

    @Autowired
    private WmsInveTransaMatchBackupsDao wmsInveTransaMatchBackupsDao;
    
    @Autowired
    private WmsInvePruductCategoryDao wmsInvePruductCategoryDao;
    
    @Autowired
    private WmsFinaCreRepayDao wmsCreRepayDao;
    
    @Autowired
    private WmsInveDebtOlnclaimsDao wmsinvedebtolnclaimsDao;
    
    @Autowired
    private WmsInveDebtOlnclaimsDao wmsInveDebtOlnclaimsDao;
    
    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveTransaMatchSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransamatchDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveTransaMatchSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransamatchDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvetransamatchDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveTransaMatch getInfoByPK(Integer wms_inve_transa_match)
    {
        return wmsinvetransamatchDao.get(wms_inve_transa_match);
    }

    @Override
    @Transactional
    public String save(WmsInveTransaMatch bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransamatchDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInveTransaMatch bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvetransamatchDao.update(bean); // update a record replace
                                                  // columns, nonsupport null
                                                  // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveTransaMatch() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveTransaMatch> getListByEntity(WmsInveTransaMatch queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveTransaMatch> beanList = wmsinvetransamatchDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * 保存理财债权匹配信息
     * 
     * 说明：匹配债权信息表中wms_inve_redeem_id设置为0 代表债权已被匹配
     * 
     * @param String transaMatch 选择房贷/车贷债权数据
     * @param WmsInveTransaProd wmsInveTransaProd 上单产品信息
     * @param category_type 产品类型
     * @param String wms_inve_redeem_id 赎回ID
     * @param UserBean user 当前登录人信息
     * @return "success" or "repeat"
     * @author 张风山
     * @author 韩昶东 二次修改 2015-08-20
     */
    @Override
    @Transactional
    public String saveMatch(String transaMatch, WmsInveTransaProd wmsInveTransaProd, String wms_inve_redeem_id,UserBean user,String category_type)
    {
    	//修改上单产品信息表 债权已经匹配完成
        wmsInveTransaProd.setIs_finish("1");
        wmsInveTransaProdDao.update(wmsInveTransaProd);
        //获取抵押类产品 匹配的债权信息
        List<WmsInveTransaMatch> witpList = JsonUtil.jsonArrayToList(transaMatch, WmsInveTransaMatch.class);
        //初始化投资金额为0
        BigDecimal tzje = new BigDecimal(0);
        //判断手动选择匹配的抵押类产品  如何category_type!=1并且witpList债权集合不为null，有数据 说明就是抵押类产品
        if (witpList != null && witpList.size() > 0)
        {
            for (int j = 0; j < witpList.size(); j++)
            {
                WmsInveTransaMatch witp = witpList.get(j);
                witp.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
                witp.setCreate_user_id(user.getUserId());
                witp.setEnable_flag("1");
                witp.setCreate_timestamp(new Timestamp(new Date().getTime()));
                witp.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                witp.setWms_inve_redeem_id(0);
                // 向债权匹配表中存入数据
                wmsinvetransamatchDao.save(witp);
                tzje = tzje.add(witp.getAssign_account());
                //本次分配的债权值
                WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
                wmsFinaCreRepay.setWms_fina_cre_pay_id(witpList.get(j).getWms_fina_cre_repay_id());
                wmsFinaCreRepay.setMatching_creditor(witpList.get(j).getAssign_account());
                //根据当前还款信息表主键，获取现在此时该单据债权的情况
                WmsFinaCreRepay wmsFinaCreRepaySelect = wmsFinaCreRepayDao.get(witp.getWms_fina_cre_repay_id());
                if (wmsFinaCreRepaySelect.getMatching_creditor() != null
                    && (wmsFinaCreRepaySelect.getMatching_creditor().compareTo(witp.getAssign_account()) < 0))
                {
                    throw new InveTransException();
                }
                if(wmsFinaCreRepaySelect.getIs_occupy() != null && wmsFinaCreRepaySelect.getIs_occupy() == 1){
                    throw new InveTransaConcurrentException();//当前债权已被占用
                }
                wmsFinaCreRepay.setIs_occupy(1);//将is_occupy改为"占用"状态
                wmsFinaCreRepay.setOccupants(1);//1代表WMS 2代表PTP
                wmsFinaCreRepayDao.updateMatch(wmsFinaCreRepay);
            }
            if(wmsInveTransaProd.getProduct_account().subtract(tzje).compareTo(new BigDecimal(0))>0){
            	//如果上面的抵押类债权不能足够此次投资金额  剩余部分用信贷类债权填充
            	this.autoTransaMatch(wmsInveTransaProd.getWms_inve_transa_prod_id(), wmsInveTransaProd.getProduct_account().subtract(tzje), user);            	
            }
        }
        //信贷类产品
        else
        {
        	//如何是信贷类产品  全部拿信贷债权匹配 
            this.autoTransaMatch(wmsInveTransaProd.getWms_inve_transa_prod_id(),wmsInveTransaProd.getProduct_account(), user);
        }
        return "success";
    }

    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    public List<Map<String, Object>> getMapForLc(Integer wms_inve_transa_prod_id, Integer wms_inve_redeem_id)
    {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("wms_inve_transa_prod_id", wms_inve_transa_prod_id);
        hashMap.put("wms_inve_redeem_id", wms_inve_redeem_id);
        BigDecimal money_sum = new BigDecimal(0);
        List<Map<String, Object>> list = wmsinvetransamatchDao.searchforlc(hashMap);
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> map = list.get(i);
            BigDecimal bd = (BigDecimal) map.get("assign_account");
            map.put("assign_account", getDecimalFormat(bd));
            money_sum = money_sum.add(bd);
            if (i == list.size() - 1)
            {
                map.put("money_sum", getDecimalFormat(money_sum));// 设置债权金额
            }
	       /* if(bd.compareTo(new BigDecimal(0))==0){
            	list.remove(i);
            	i--;
            }*/
        }
        return list;
    }
    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    public List<Map<String, Object>> getMapForHeadid(Integer wms_inve_debt_head_id)
    {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("wms_inve_debt_head_id", wms_inve_debt_head_id);
        hashMap.put("olnclaims", 2);
        hashMap.put("sortname", "");
        hashMap.put("sortorder", "");
        BigDecimal money_sum = new BigDecimal(0);
        List<Map<String, Object>> list = wmsinvedebtolnclaimsDao.selectOlnclaimsByWmsInveDebtHeadId(hashMap);
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> map = list.get(i);
            BigDecimal bd = (BigDecimal) map.get("assign_account");
            map.put("assign_account", getDecimalFormat(bd));
            money_sum = money_sum.add(bd);
            if (i == list.size() - 1)
            {
                map.put("money_sum", getDecimalFormat(money_sum));// 设置债权金额
            }
	       /* if(bd.compareTo(new BigDecimal(0))==0){
            	list.remove(i);
            	i--;
            }*/
        }
        return list;
    }
    /**
     * Description :理财 债权转让及受让协议 后台查询数据 查询了很多表--赎回
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    public List<Map<String, Object>> getMapForLcSh(Integer wms_inve_transa_prod_id)
    {
        List<Map<String, Object>> list = wmsinvetransamatchDao.searchforlcsh(wms_inve_transa_prod_id);
        for (int i = 0; i < list.size(); i++)
        {
            Map<String, Object> map = list.get(i);
            BigDecimal bd = (BigDecimal) map.get("assign_account");
            map.put("assign_account", getDecimalFormat(bd));
            /*if(bd.compareTo(new BigDecimal(0))==0){
            	list.remove(i);
            	i--;
            }*/
        }
        return list;
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

    public Map<String, Object> getInveTransaMatchList(Integer wms_inve_redeem_id)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        WmsInveRedeemDetail wmsInveRedeemDetail = new WmsInveRedeemDetail();
        wmsInveRedeemDetail.setWms_inve_redeem_id(wms_inve_redeem_id);
        wmsInveRedeemDetail.setIs_fully_redeemed("0");
        List<WmsInveRedeemDetail> wmsInveRedeemDetailList = wmsInveRedeemDetailDao.getListByEntity(wmsInveRedeemDetail);
        paramMap.put("wmsInveRedeemDetail", wmsInveRedeemDetailList);
        return paramMap;
    }
    /**
     * 理财功能-财务管理-签署协议 --债权调整功能
     * @param tramsaMatch 抵押匹配的债权
     * @param wms_inve_redeem_id 赎回主键
     * @return user 登录用户
     * @author hancd
     */
    @Override
    @Transactional
    public String saveAllMatch(String transaMatch, String wms_inve_redeem_id, UserBean user)
    {
        List<WmsInveTransaMatchVo> witpList = JsonUtil.jsonArrayToList(transaMatch, WmsInveTransaMatchVo.class);
        for (int i = 0; i < witpList.size(); i++)
        {
            WmsInveTransaMatchVo wmsInveTransaMatchVo = witpList.get(i);
            WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wmsInveTransaMatchVo.getWms_inve_transa_prod_id());
            List<WmsInveTransaMatch> matchList = JsonUtil.jsonArrayToList(wmsInveTransaMatchVo.getTransaMatch(),WmsInveTransaMatch.class);
            // 更新匹配表使原来的匹配数据失效
            WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
            wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wmsInveTransaMatchVo.getWms_inve_transa_prod_id());
            wmsInveTransaMatchSearch.setWms_inve_redeem_id(Integer.parseInt(wms_inve_redeem_id));
            wmsinvetransamatchDao.updateRedeem(wmsInveTransaMatchSearch);
            // 把失效的债权 释放回收
            WmsInveTransaMatch wMatch = new WmsInveTransaMatch();
            wMatch.setWms_inve_transa_prod_id(wmsInveTransaMatchVo.getWms_inve_transa_prod_id());
            wMatch.setWms_inve_redeem_id(0);
            //wMatch.setWms_inve_transa_protocol_id(wmsInveTransaMatchVo.getWms_inve_transa_protocol_id());//协议id
            List<WmsInveTransaMatch> WmsInveTransaMatchlist=wmsinvetransamatchDao.getListByEntity(wMatch);
            for(WmsInveTransaMatch w:WmsInveTransaMatchlist){
            	WmsFinaCreRepay wCreRepay = new WmsFinaCreRepay();
            	wCreRepay.setWms_fina_cre_pay_id(w.getWms_fina_cre_repay_id());
            	wCreRepay.setMatching_creditor(w.getAssign_account());
            	wmsCreRepayDao.updateRedeem(wCreRepay);
            }
           BigDecimal tzje = new BigDecimal(0);
           WmsInvePruductCategory wCategory= wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
            //抵押类产品
            if (matchList != null && matchList.size() > 0)
            {   
                for (int j = 0; j < matchList.size(); j++){
                    WmsInveTransaMatch wmsInveTransaMatch = matchList.get(j);
                    WmsFinaCreRepay wmsFinaCreRepaySelect = wmsFinaCreRepayDao.get(wmsInveTransaMatch.getWms_fina_cre_repay_id());
                    //当选择的抵押类债权不足或者被其他占用,前台提示需要重选选择抵押类债权
                    if (wmsFinaCreRepaySelect.getMatching_creditor() != null 
                        && (wmsFinaCreRepaySelect.getMatching_creditor().compareTo(wmsInveTransaMatch.getAssign_account()) < 0)){
                        throw new InveTransException();
                    }
                    //如果充足，就占用相对应的抵押类债权
                    wmsInveTransaMatch.setWms_inve_transa_prod_id(wmsInveTransaMatchVo.getWms_inve_transa_prod_id());
                    wmsInveTransaMatch.setCreate_user_id(user.getUserId());
                    wmsInveTransaMatch.setEnable_flag("1");
                    wmsInveTransaMatch.setCreate_timestamp(new Timestamp(new Date().getTime()));
                    wmsInveTransaMatch.setLast_update_timestamp(new Timestamp(new Date().getTime()));
                    wmsInveTransaMatch.setWms_inve_redeem_id(0);
                    wmsinvetransamatchDao.save(wmsInveTransaMatch);
                    //把占用的债权在原始的债权上扣除
                    tzje = tzje.add(wmsInveTransaMatch.getAssign_account());
                    WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
                    wmsFinaCreRepay.setWms_fina_cre_pay_id(wmsInveTransaMatch.getWms_fina_cre_repay_id());
                    wmsFinaCreRepay.setMatching_creditor(wmsInveTransaMatch.getAssign_account());
                    wmsFinaCreRepayDao.updateMatch(wmsFinaCreRepay);
                }
                if(wmsInveTransaProd.getProduct_account().subtract(tzje).compareTo(new BigDecimal(0))>0){
                	//剩余不足的债权，用信用债权匹配
                	this.autoTransaMatch(wmsInveTransaMatchVo.getWms_inve_transa_prod_id(), wmsInveTransaProd.getProduct_account().subtract(tzje), user);                	
                }
            }
            //信用类产品
            else
            {
            	//信用全部用信用类债权匹配
                this.autoTransaMatch(wmsInveTransaMatchVo.getWms_inve_transa_prod_id(), wmsInveTransaProd.getProduct_account(), user);
            }
        }
        //赎回信息表标记已经赎回完成
        WmsInveRedeem wmsInveRedeem = new WmsInveRedeem();
        wmsInveRedeem.setWms_inve_redeem_id(Integer.parseInt(wms_inve_redeem_id));
        wmsInveRedeem.setIs_finish("1");
        wmsInveRedeemDao.update(wmsInveRedeem);
        return "success";
    }
    /**
     * 保存债权匹配规则并重新匹配  债权自动匹配
     * 
     * 实现系统中现有可匹配债权统一按照事先选择的方式进行自动匹配
     * 
     * 1.按照债权包总额
     * 
     * 
     * 
     * 2.按照债权包实时余额  (现在已经进行处理)
     * 
     * 
     * 
     * @param queryInfo
     * @return record list
     * @author 张风山
     */
    @Override
    @Transactional
    public String saveMatchRule(String match_rule, String match_ratio, UserBean user)
    {

        // 更改属性表中债权匹配系数和债权匹配规则
        WmsSysProperty wmsSysProperty = new WmsSysProperty();
        wmsSysProperty.setWms_sys_property_id(23);
        wmsSysProperty.setProperty_value(match_ratio);
        wmsSysPropertyDao.update(wmsSysProperty);
        wmsSysProperty.setWms_sys_property_id(36);
        wmsSysProperty.setProperty_value(match_rule);
        wmsSysPropertyDao.update(wmsSysProperty);

        // 更改贷款还款表中的可匹配债权 
        BigDecimal ratio = new BigDecimal(match_ratio);
        Map<String, Object> params = new HashMap<>();
        params.put("ratio", ratio);
//        if (match_rule.equals("1"))
//        {
//            wmsFinaCreRepayDao.changeMatchRuleToTotal(params);
//        }
//        else if (match_rule.equals("2"))
//        {
//            wmsFinaCreRepayDao.changeMatchRuleToSurplus(params);
//        }
        //查询所有已经匹配过债权总额 和 还款信息表主键
        WmsInveTransaMatch wMatch = new WmsInveTransaMatch();
        wMatch.setWms_inve_redeem_id(0);
        wMatch.setEnable_flag("1");
        List<Map<String,Object>> wmsInveTransaMatchs = wmsinvetransamatchDao.getListByMacthRepay(wMatch);
        
	    if (match_rule.equals("1"))
	    {
	          for(int i=0;i<wmsInveTransaMatchs.size();i++){
	        	  WmsFinaCreRepay wFinaCreRepay = wmsFinaCreRepayDao.get(Integer.valueOf(wmsInveTransaMatchs.get(i).get("wms_fina_cre_repay_id").toString()));
	        	  //总合同金额-减去PTP占用的金额
	        	  BigDecimal protocol_amountsy = wFinaCreRepay.getProtocol_amount().subtract(wFinaCreRepay.getAct_creditor()==null?new BigDecimal(0):new BigDecimal(0));
	        	  BigDecimal matching_creditor = new BigDecimal(0);
	        	  if(wFinaCreRepay.getCre_type().equals("1")){
	        		  matching_creditor = protocol_amountsy.multiply(ratio).subtract(new BigDecimal(wmsInveTransaMatchs.get(i).get("sumassign_account").toString()));	        		  
	        	  }
	        	  else if(wFinaCreRepay.getCre_type().equals("2")||wFinaCreRepay.getCre_type().equals("3")){
	        		  matching_creditor = protocol_amountsy.subtract(new BigDecimal(wmsInveTransaMatchs.get(i).get("sumassign_account").toString()));	        		  
	        	  }
	        	  WmsFinaCreRepay w = new WmsFinaCreRepay();
	        	  w.setWms_fina_cre_pay_id(Integer.valueOf(wmsInveTransaMatchs.get(i).get("wms_fina_cre_repay_id").toString()));
	        	  w.setMatching_creditor(matching_creditor);
	        	  wmsFinaCreRepayDao.update(w);
	          }
	    }
	    else if (match_rule.equals("2"))
	    {
	         
	    }
//        // 查询所有已经匹配的理财产品
//        List<Integer> inveTransaProdIdList = wmsinvetransamatchDao.selectAllProdForMatch();
//        
//        if(inveTransaProdIdList.size() !=0 && inveTransaProdIdList !=null)
//        {
//            for (int i = 0 ; i < inveTransaProdIdList.size(); i++)
//            {
//                //上单产品主键
//                int wms_inve_transa_prod_id = inveTransaProdIdList.get(i);
//                //根据上单产品主键获取相应上单产品详细信息
//                WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
//                //查询相对应的产品表信息
//                WmsInvePruductCategory wmsInvePruductCategory =wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
//                // 判断理财产品类型
//                if(wmsInvePruductCategory.getCategory_type().equals("1")){//信贷类理财产品
//                	deleteMatchInfo(wms_inve_transa_prod_id);
//                    autoTransaMatch(wms_inve_transa_prod_id, wmsInveTransaProd.getProduct_account(), user);
//                }else if(wmsInvePruductCategory.getCategory_type().equals("2")){//房贷类理财抵押产品
//                	//获取房贷类理财产品用过的房贷债权
//                	 List<WmsInveTransaMatch> wmsInveTransaMatchHouseList = wmsinvetransamatchDao.getMatchForHouse(wms_inve_transa_prod_id);
//                	 deleteMatchInfo(wms_inve_transa_prod_id);
//                	 BigDecimal ppje = new BigDecimal(0);// 房贷匹配金额的总和
//                	 for (int j = 0; j < wmsInveTransaMatchHouseList.size(); j++)
//                     {
//                         WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchHouseList.get(j);
//                         ppje = ppje.add(wmsInveTransaMatch.getAssign_account());
//                         wmsinvetransamatchDao.save(wmsInveTransaMatch);
//                     }
//                     autoTransaMatch(wms_inve_transa_prod_id, wmsInveTransaProd.getProduct_account().subtract(ppje), user);
//                }else if(wmsInvePruductCategory.getCategory_type().equals("3")){//车贷类理财抵押产品
//                	 //获取房贷类理财产品用过的车贷债权
//               	     List<WmsInveTransaMatch> wmsInveTransaMatchCarList = wmsinvetransamatchDao.getMatchForCar(wms_inve_transa_prod_id);
//               	     deleteMatchInfo(wms_inve_transa_prod_id);
//               	     BigDecimal ppje = new BigDecimal(0);// 房贷匹配金额的总和
//	               	 for (int j = 0; j < wmsInveTransaMatchCarList.size(); j++)
//	                  {
//	                      WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchCarList.get(j);
//	                      ppje = ppje.add(wmsInveTransaMatch.getAssign_account());
//	                      wmsinvetransamatchDao.save(wmsInveTransaMatch);
//	                  }
//                  autoTransaMatch(wms_inve_transa_prod_id, wmsInveTransaProd.getProduct_account().subtract(ppje), user);
//                }
//            }
//        }
        return "success";
    }
    
    /**
     * Description :自动债权匹配
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param tzje 剩余的投资金额
     * @param user 当前登录信息
     * @return "success" or "error" or user defined
     * @author 张风山
     * 
     * @author hancd  2015-8-26
     */
    @Override
    @Transactional
    public String autoTransaMatch(Integer wms_inve_transa_prod_id, BigDecimal tzje, UserBean user)
    {
        Calendar nowcalendar = Calendar.getInstance();
        int year = nowcalendar.get(Calendar.YEAR);
        int month = nowcalendar.get(Calendar.MONTH) + 1;
        int day = nowcalendar.get(Calendar.DATE);
        String today = "" + year + "-" + month + "-" + day + "";
        // 系统当前日期
        java.sql.Date nowDate = java.sql.Date.valueOf(today);
        //上单产品表数据
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        //上单信息表数据
        WmsInveTransa wmsinvetransa = wmsinvetransaDao.get(wmsInveTransaProd.getWms_inve_transa_id());
        //获取产品配置表对应产品信息
        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        
        Date sDate = setDatebyCalendar(wmsinvetransa.getDate_of_payment(), wmsInveTransaProd.getProduct_deadline());
        
        //查询还款信息表中最终还款日期与上单支付时间相近的时间
        WmsFinaCreRepay wmsFinaCreRepaySearch = new WmsFinaCreRepay();
        wmsFinaCreRepaySearch.setRepay_end_date(new java.sql.Date(sDate.getTime()));
        
        // 获取债权匹配系数
        WmsSysProperty wmsSysPropertyForMatchFactor = wmsSysPropertyDao.get(23);
        String matchFactor = wmsSysPropertyForMatchFactor.getProperty_value();
       
        // 获取债权匹配规则
        WmsSysProperty wmsSysPropertyForMatchRule = wmsSysPropertyDao.get(36);
        String matchRule = wmsSysPropertyForMatchRule.getProperty_value();
        
        // 按照匹配规则 选择债权包 符合规则的债权包优先     
        //1,与支付时间最近的债权
        List<WmsFinaCreRepay> wmsFinaCreRepayFirstList = wmsFinaCreRepayDao.getListForFirstMatch(wmsFinaCreRepaySearch);
        //2,与支付时间远的债权
        List<WmsFinaCreRepay> wmsFinaCreRepayOtherList = wmsFinaCreRepayDao.getListForOtherMatch(wmsFinaCreRepaySearch);
        //合并          
        wmsFinaCreRepayFirstList.addAll(wmsFinaCreRepayOtherList);
        
        BigDecimal tzjeFlag= new BigDecimal(0);
        String flag="";
        //新规定：以万元为单位取值  当投资金额小于30000时,就直接匹，不用符合33规则
        if(tzje.compareTo(new BigDecimal(30000))<0){
        	flag="1";
        	matchSelectMethod(tzjeFlag,tzje,wmsFinaCreRepayFirstList,matchFactor,matchRule,
            		nowDate,wmsInvePruductCategory,wmsInveTransaProd,user,flag);
        }else{
        	flag="2";
        	matchSelectMethod(tzjeFlag,tzje,wmsFinaCreRepayFirstList,matchFactor,matchRule,
            		nowDate,wmsInvePruductCategory,wmsInveTransaProd,user,flag);
        }
        wmsInveTransaProd.setIs_finish("1");
        wmsInveTransaProdDao.update(wmsInveTransaProd);
        return "success";
    }
    
    
    /**
     * Description :自动债权匹配
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @param tzje 剩余的投资金额
     * @param user 当前登录信息
     * @return "success" or "error" or user defined
     * @author wangyihan 2015-11-30
     */
    @Transactional
    public String autoTransaMatchNew(Integer wms_inve_transa_prod_id, BigDecimal tzje, Integer wms_inve_debt_head_id, UserBean user)
    {
        Calendar nowcalendar = Calendar.getInstance();
        int year = nowcalendar.get(Calendar.YEAR);
        int month = nowcalendar.get(Calendar.MONTH) + 1;
        int day = nowcalendar.get(Calendar.DATE);
        String today = "" + year + "-" + month + "-" + day + "";
        //系统当前日期
        java.sql.Date nowDate = java.sql.Date.valueOf(today);
        //上单产品表数据
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        //上单信息表数据
        WmsInveTransa wmsinvetransa = wmsinvetransaDao.get(wmsInveTransaProd.getWms_inve_transa_id());
        //获取产品配置表对应产品信息
        WmsInvePruductCategory wmsInvePruductCategory = wmsInvePruductCategoryDao.get(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        
        Date sDate = setDatebyCalendar(wmsinvetransa.getDate_of_payment(), wmsInveTransaProd.getProduct_deadline());
        
        //查询还款信息表中最终还款日期与上单支付时间相近的时间
        WmsFinaCreRepay wmsFinaCreRepaySearch = new WmsFinaCreRepay();
        wmsFinaCreRepaySearch.setRepay_end_date(new java.sql.Date(sDate.getTime()));
        
        //获取债权匹配系数
        WmsSysProperty wmsSysPropertyForMatchFactor = wmsSysPropertyDao.get(23);
        String matchFactor = wmsSysPropertyForMatchFactor.getProperty_value();
       
        //获取债权匹配规则
        WmsSysProperty wmsSysPropertyForMatchRule = wmsSysPropertyDao.get(36);
        String matchRule = wmsSysPropertyForMatchRule.getProperty_value();
        
        //按照匹配规则选择债权包,符合规则的债权包优先     
        //1:与支付时间最近的债权
        List<WmsFinaCreRepay> wmsFinaCreRepayFirstList = wmsFinaCreRepayDao.getListForFirstMatch(wmsFinaCreRepaySearch);
        //2:与支付时间远的债权
        List<WmsFinaCreRepay> wmsFinaCreRepayOtherList = wmsFinaCreRepayDao.getListForOtherMatch(wmsFinaCreRepaySearch);
        //合并
        wmsFinaCreRepayFirstList.addAll(wmsFinaCreRepayOtherList);
        
        BigDecimal tzjeFlag = new BigDecimal(0);
        String flag = "";
        //新规定：以万元为单位取值,当投资金额小于30000时,就直接匹,不用符合33规则
        if(tzje.compareTo(new BigDecimal(30000)) < 0){
            flag = "1";
            matchSelectMethodNew(tzjeFlag, tzje, wmsFinaCreRepayFirstList, matchFactor, matchRule,
                    nowDate, wmsInvePruductCategory, wmsInveTransaProd, user, flag, wms_inve_debt_head_id);
        }else{
            flag = "2";
            matchSelectMethodNew(tzjeFlag, tzje, wmsFinaCreRepayFirstList, matchFactor, matchRule,
                    nowDate, wmsInvePruductCategory, wmsInveTransaProd, user, flag, wms_inve_debt_head_id);
        }
        wmsInveTransaProd.setIs_finish("1");
        wmsInveTransaProdDao.update(wmsInveTransaProd);
        return "success";
    }
    
    
    /**
     * 
     * @param tzjeFlag
     * @param tzje
     * @param wmsFinaCreRepayFirstList
     * @param matchFactor
     * @param matchRule
     * @param nowDate
     * @param wmsInvePruductCategory
     * @param wmsInveTransaProd
     * @param flag=1 代表符合小于30000债权  flag=2 代表符合大于等于30000债权
     */
    private void matchSelectMethod(BigDecimal tzjeFlag,BigDecimal tzje,List<WmsFinaCreRepay> wmsFinaCreRepayFirstList,String matchFactor,String matchRule,
    		java.sql.Date nowDate,WmsInvePruductCategory wmsInvePruductCategory,WmsInveTransaProd wmsInveTransaProd,UserBean user, String flag){
    	if(flag.equals("1")){
    		tzjeFlag = tzje;
    	}else if(flag.equals("2")){
    		// 投资金额的三分之一，为了能确保最少匹配到3个债权包
        	tzjeFlag = ((tzje.divide(new BigDecimal(10000))).divide(new BigDecimal(3), 0 , BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
    	}
    	// 单轮匹配的债权金额
        BigDecimal zqje = new BigDecimal(0);
        int transaFlag = 1;
        while (tzje.compareTo(new BigDecimal(0)) > 0)
        {
        	//说明系统中没有可用的债权
            if (wmsFinaCreRepayFirstList.size() < transaFlag)
            {
                throw new InveTransSysException();
            }
            WmsFinaCreRepay wmsFinaCreRepay = wmsFinaCreRepayFirstList.get(transaFlag - 1);
            WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = wmsCreApproBorrowProtocolDao.get(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());
            // 按债权总值匹配 flag=2 债权总金额=(合同金额*匹配系数)/3
            // 按债权总值匹配 flag=1 债权总金额=(合同金额*匹配系数)
            if (matchRule.equals("1"))
            {
                  //zqje = (wmsFinaCreRepay.getProtocol_amount().multiply(new BigDecimal(matchFactor))).divide(new BigDecimal(3), 0 ,BigDecimal.ROUND_DOWN);
            	if(flag.equals("1")){
            		 //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = (wmsFinaCreRepay.getMatching_creditor().divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
            	}else if(flag.equals("2")){
            		//获取的可用债权变成万元取到整万 在乘以10000变成元
                	zqje = ((wmsFinaCreRepay.getMatching_creditor().divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR))
                    		  .divide(new BigDecimal(3),0,BigDecimal.ROUND_DOWN))
                    		  .multiply(new BigDecimal(10000));
            	}
            	
            }
            // 按债权剩余价值匹配 flag=1 债权总金额=((剩余本金+剩余利息)*匹配系数)  flag=2 债权总金额=((剩余本金+剩余利息)*匹配系数)/3
            else if (matchRule.equals("2"))
            {
            	if(flag.equals("1")){
            		 //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = (((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(matchFactor))).
                  		  divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
            	}else if(flag.equals("2")){
            		 //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = ((((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(matchFactor))).divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR))
                  		  .divide(new BigDecimal(3), 0 ,BigDecimal.ROUND_DOWN))
                  		  .multiply(new BigDecimal(10000));
            	}
            }
            // 本次匹配额度
            BigDecimal matchNow = new BigDecimal(0);
            // 将债权总额的三分之一与理财金额的三分之一做比较，较少的一个作为本次匹配额度
            if (zqje.compareTo(tzjeFlag) >= 0) 
            {
                if (tzjeFlag.compareTo(tzje) >= 0) 
                { 
                    matchNow = tzje;
                }
                else
                {
                    matchNow = tzjeFlag;
                }
            }
            //将债权总额的三分之一与理财金额的三分之一做比较，如果债权总额小于理财金额 把债权总额的三分之一做本次匹配的债权  这样做：只能满足轮换到小金额债权也能匹，但是上面的匹配过程就没有多少作用
            if(zqje.compareTo(tzjeFlag) < 0 && zqje.compareTo(new BigDecimal(0))>0){
            	if(tzje.compareTo(zqje)>=0){
            		matchNow=zqje;            		
            	}else{
            		matchNow=tzje;
            	}
            }
            if(matchNow.compareTo(new BigDecimal(1))>=0 && tzje.compareTo(new BigDecimal(0))>0){
         	   tzje = tzje.subtract(matchNow);
         	   matchRepayMethod(wmsFinaCreRepay,wmsCreApproBorrowProtocol,
         			   matchNow,nowDate,wmsInvePruductCategory,wmsInveTransaProd,user);
            }
           transaFlag++;
        }
    }
    
    
    /**
     * 不修改还款信息表的额度,只将状态改为"被占用"
     * @param tzjeFlag
     * @param tzje
     * @param wmsFinaCreRepayFirstList
     * @param matchFactor
     * @param matchRule
     * @param nowDate
     * @param wmsInvePruductCategory
     * @param wmsInveTransaProd
     * @param flag=1 代表符合小于30000债权  flag=2 代表符合大于等于30000债权
     */
    private void matchSelectMethodNew(BigDecimal tzjeFlag, BigDecimal tzje, List<WmsFinaCreRepay> wmsFinaCreRepayFirstList, 
            String matchFactor, String matchRule, java.sql.Date nowDate, WmsInvePruductCategory wmsInvePruductCategory, 
            WmsInveTransaProd wmsInveTransaProd, UserBean user, String flag, Integer wms_inve_debt_head_id){
        if(flag.equals("1")){
            tzjeFlag = tzje;
        }else if(flag.equals("2")){
            // 投资金额的三分之一，为了能确保最少匹配到3个债权包 
            tzjeFlag = ((tzje.divide(new BigDecimal(10000))).divide(new BigDecimal(3), 0, BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
        }
        // 单轮匹配的债权金额
        BigDecimal zqje = new BigDecimal(0);
        int transaFlag = 1;
        while (tzje.compareTo(new BigDecimal(0)) > 0)
        {
            //说明系统中没有可用的债权
            if(wmsFinaCreRepayFirstList.size() < transaFlag){
                throw new InveTransSysException();
            }
            WmsFinaCreRepay wmsFinaCreRepay = wmsFinaCreRepayFirstList.get(transaFlag - 1);
            WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = wmsCreApproBorrowProtocolDao.get(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());
            // 按债权总值匹配 flag=2 债权总金额=(合同金额*匹配系数)/3
            // 按债权总值匹配 flag=1 债权总金额=(合同金额*匹配系数)
            if(matchRule.equals("1")){
                  //zqje = (wmsFinaCreRepay.getProtocol_amount().multiply(new BigDecimal(matchFactor))).divide(new BigDecimal(3), 0 ,BigDecimal.ROUND_DOWN);
                if(flag.equals("1")){
                     //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = (wmsFinaCreRepay.getMatching_creditor().divide(new BigDecimal(10000), 0, BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
                }else if(flag.equals("2")){
                    //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = ((wmsFinaCreRepay.getMatching_creditor().divide(new BigDecimal(10000), 0, BigDecimal.ROUND_FLOOR))
                              .divide(new BigDecimal(3),0,BigDecimal.ROUND_DOWN))
                              .multiply(new BigDecimal(10000));
                }
                
            }
            // 按债权剩余价值匹配 flag=1 债权总金额=((剩余本金+剩余利息)*匹配系数)  flag=2 债权总金额=((剩余本金+剩余利息)*匹配系数)/3
            else if(matchRule.equals("2")){
                if(flag.equals("1")){
                     //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = (((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(matchFactor))).
                          divide(new BigDecimal(10000),0,BigDecimal.ROUND_FLOOR)).multiply(new BigDecimal(10000));
                }else if(flag.equals("2")){
                     //获取的可用债权变成万元取到整万 在乘以10000变成元
                    zqje = ((((wmsFinaCreRepay.getUn_pay_principal().add(wmsFinaCreRepay.getUn_pay_interest())).multiply(new BigDecimal(matchFactor))).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_FLOOR))
                          .divide(new BigDecimal(3), 0, BigDecimal.ROUND_DOWN))
                          .multiply(new BigDecimal(10000));
                }
            }
            // 本次匹配额度
            BigDecimal matchNow = new BigDecimal(0);
           /* // 将债权总额的三分之一与理财金额的三分之一做比较，较少的一个作为本次匹配额度
            if(zqje.compareTo(tzjeFlag) >= 0){
                if(tzjeFlag.compareTo(tzje) >= 0){ 
                    matchNow = tzje;
                }
                else{
                    matchNow = tzjeFlag;
                }
            }
            else{
                //至少要满足1万的匹给它
                if(zqje.compareTo(new BigDecimal(10000)) >= 0){
                    matchNow = zqje;
                }
            }
            if(matchNow.compareTo(new BigDecimal(1)) >= 0){
               tzje = tzje.subtract(matchNow);
               matchRepayMethodNew(wmsFinaCreRepay, wmsCreApproBorrowProtocol,
                       matchNow, nowDate, wmsInvePruductCategory, wmsInveTransaProd, wms_inve_debt_head_id, user);
            }*/
            // 将债权总额的三分之一与理财金额的三分之一做比较，较少的一个作为本次匹配额度
            if (zqje.compareTo(tzjeFlag) >= 0) 
            {
                if (tzjeFlag.compareTo(tzje) >= 0) 
                { 
                    matchNow = tzje;
                }
                else
                {
                    matchNow = tzjeFlag;
                }
            }
            //将债权总额的三分之一与理财金额的三分之一做比较，如果债权总额小于理财金额 把债权总额的三分之一做本次匹配的债权  这样做：只能满足轮换到小金额债权也能匹，但是上面的匹配过程就没有多少作用
            if(zqje.compareTo(tzjeFlag) < 0 && zqje.compareTo(new BigDecimal(0))>0){
            	if(tzje.compareTo(zqje)>=0){
            		matchNow=zqje;            		
            	}else{
            		matchNow=tzje;
            	}
            }
            if(matchNow.compareTo(new BigDecimal(1))>=0 && tzje.compareTo(new BigDecimal(0))>0){
         	   tzje = tzje.subtract(matchNow);
         	   matchRepayMethodNew(wmsFinaCreRepay,wmsCreApproBorrowProtocol,
         			   matchNow, nowDate, wmsInvePruductCategory, wmsInveTransaProd, wms_inve_debt_head_id, user);
            }
            transaFlag++;
        }
    }
    
    
    /**
     * 更新每次债权匹配信息和还款信息表中债权的使用情况进行更新  
     */
    private void matchRepayMethod(WmsFinaCreRepay wmsFinaCreRepay,WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol,
    		BigDecimal matchNow,java.sql.Date nowDate,WmsInvePruductCategory wmsInvePruductCategory,WmsInveTransaProd wmsInveTransaProd,UserBean user){
    	 WmsInveTransaMatch witp = new WmsInveTransaMatch();
         witp.setWms_fina_cre_repay_id(wmsFinaCreRepay.getWms_fina_cre_pay_id());
         witp.setProtocol_code(wmsFinaCreRepay.getProtocol_code());
         witp.setCredit_name(wmsCreApproBorrowProtocol.getDebtor_name());
         witp.setCredit_id_card(wmsCreApproBorrowProtocol.getDebtor_identity_id());
         witp.setAssign_account(matchNow);
         witp.setDate_of_assign(nowDate);
         witp.setRepay_begin_date(wmsFinaCreRepay.getRepay_begin_date());
         witp.setRepay_end_date(wmsFinaCreRepay.getRepay_end_date());
         //月利率 用对应产品表中的年化收益率
         witp.setProduct_interest_month(wmsInvePruductCategory.getCategory_return_rate().divide(new BigDecimal(12),3,BigDecimal.ROUND_DOWN));
         
         witp.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id()); 
         witp.setEnable_flag("1");
         witp.setCreate_user_id(user.getUserId());
         witp.setCreate_timestamp(new Timestamp(new Date().getTime()));
         witp.setLast_update_timestamp(new Timestamp(new Date().getTime()));
         witp.setWms_inve_redeem_id(0);
         wmsinvetransamatchDao.save(witp);
         WmsFinaCreRepay wmsFinaCreRepayAfterMatch = new WmsFinaCreRepay();
         wmsFinaCreRepayAfterMatch.setWms_fina_cre_pay_id(witp.getWms_fina_cre_repay_id());
         wmsFinaCreRepayAfterMatch.setMatching_creditor(matchNow);
         WmsFinaCreRepay wmsFinaCreRepaySelect = wmsFinaCreRepayDao.get(witp.getWms_fina_cre_repay_id());
         if (wmsFinaCreRepaySelect.getMatching_creditor() != null && (wmsFinaCreRepaySelect.getMatching_creditor().compareTo(witp.getAssign_account()) < 0)){
             throw new InveTransException();
         }
         if(wmsFinaCreRepaySelect.getIs_occupy() != null && wmsFinaCreRepaySelect.getIs_occupy() == 1){
             throw new InveTransaConcurrentException();//当前债权已被占用
         }
         wmsFinaCreRepayDao.updateMatch(wmsFinaCreRepayAfterMatch);
    }
    
    
    /**
     * 更新每次债权匹配信息和还款信息表中债权的使用情况进行更新  
     */
    private void matchRepayMethodNew(WmsFinaCreRepay wmsFinaCreRepay, WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol,
            BigDecimal matchNow, java.sql.Date nowDate, WmsInvePruductCategory wmsInvePruductCategory, WmsInveTransaProd wmsInveTransaProd,
            Integer wms_inve_debt_head_id,
            UserBean user){
         WmsInveTransaMatch witp = new WmsInveTransaMatch();
         witp.setWms_fina_cre_repay_id(wmsFinaCreRepay.getWms_fina_cre_pay_id());
         witp.setProtocol_code(wmsFinaCreRepay.getProtocol_code());
         witp.setCredit_name(wmsCreApproBorrowProtocol.getDebtor_name());
         witp.setCredit_id_card(wmsCreApproBorrowProtocol.getDebtor_identity_id());
         witp.setAssign_account(matchNow);
         witp.setDate_of_assign(nowDate);
         witp.setRepay_begin_date(wmsFinaCreRepay.getRepay_begin_date());
         witp.setRepay_end_date(wmsFinaCreRepay.getRepay_end_date());
         //月利率 用对应产品表中的年化收益率
         witp.setProduct_interest_month(wmsInvePruductCategory.getCategory_return_rate().divide(new BigDecimal(12), 3, BigDecimal.ROUND_DOWN));
         
         witp.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id()); 
         witp.setEnable_flag("1");
         witp.setCreate_user_id(user.getUserId());
         witp.setCreate_timestamp(new Timestamp(new Date().getTime()));
         witp.setLast_update_timestamp(new Timestamp(new Date().getTime()));
         witp.setWms_inve_redeem_id(0);
         /** 此处不做还款信息表的"可匹配的合同金额债权字段"的更新,只做占用处理(WMS占用) */
         /*wmsinvetransamatchDao.save(witp);
         WmsFinaCreRepay wmsFinaCreRepayAfterMatch = new WmsFinaCreRepay();
         wmsFinaCreRepayAfterMatch.setWms_fina_cre_pay_id(witp.getWms_fina_cre_repay_id());
         wmsFinaCreRepayAfterMatch.setMatching_creditor(matchNow);*/
         WmsFinaCreRepay wmsFinaCreRepaySelect = wmsFinaCreRepayDao.get(witp.getWms_fina_cre_repay_id());
         
         wmsFinaCreRepaySelect.setIs_occupy(1);//1代表占用   0或null代表未占用
         wmsFinaCreRepaySelect.setOccupants(1);//1代表WMS 2代表PTP
         wmsFinaCreRepaySelect.setMatching_creditor(null);
         
         if(wmsFinaCreRepaySelect.getIs_occupy() != null && wmsFinaCreRepaySelect.getIs_occupy() == 1){
             throw new InveTransaConcurrentException();//当前债权已被占用
         }
         if (wmsFinaCreRepaySelect.getMatching_creditor() != null && 
                 (wmsFinaCreRepaySelect.getMatching_creditor().compareTo(witp.getAssign_account()) < 0)){
             throw new InveTransException();
         }
         wmsFinaCreRepayDao.updateMatch(wmsFinaCreRepaySelect);
         
         //将新匹配的还款信息插入到债权变更副表
         WmsInveDebtOlnclaims wmsInveDebtOlnclaims = new WmsInveDebtOlnclaims();
         wmsInveDebtOlnclaims.setWms_inve_debt_head_id(wms_inve_debt_head_id);
         wmsInveDebtOlnclaims.setWms_inve_transa_prod_id(wmsInveTransaProd.getWms_inve_transa_prod_id());
         wmsInveDebtOlnclaims.setWms_fina_cre_repay_id(wmsFinaCreRepay.getWms_fina_cre_pay_id());
         wmsInveDebtOlnclaims.setCredit_name(wmsCreApproBorrowProtocol.getDebtor_name());
         wmsInveDebtOlnclaims.setCredit_id_card(wmsCreApproBorrowProtocol.getDebtor_identity_id());
         wmsInveDebtOlnclaims.setProtocol_code(wmsFinaCreRepaySelect.getProtocol_code());
         wmsInveDebtOlnclaims.setAssign_account(matchNow);
         wmsInveDebtOlnclaims.setDate_of_assign(nowDate);
         wmsInveDebtOlnclaims.setRepay_begin_date(wmsFinaCreRepaySelect.getRepay_begin_date());
         wmsInveDebtOlnclaims.setRepay_end_date(wmsFinaCreRepaySelect.getRepay_end_date());
         wmsInveDebtOlnclaims.setProduct_interest_month(wmsInvePruductCategory.getCategory_return_rate().divide(new BigDecimal(12), 3, BigDecimal.ROUND_DOWN));
         wmsInveDebtOlnclaims.setCreate_timestamp(new Timestamp(new Date().getTime()));
         wmsInveDebtOlnclaims.setCreate_user_id(user.getUserId());
         wmsInveDebtOlnclaims.setEnable_flag("1");
         wmsInveDebtOlnclaims.setOlnclaims("2");
         wmsInveDebtOlnclaimsDao.save(wmsInveDebtOlnclaims);
         
    }
    
    
    /**
     * 删除失效的债权匹配关系，并恢复债权包可用匹配金额
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author 张风山
     */
    @Transactional
    public String deleteMatchInfo(Integer wms_inve_transa_prod_id)
    {

        WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
        wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wms_inve_transa_prod_id);
        wmsInveTransaMatchSearch.setWms_inve_redeem_id(0);
        wmsInveTransaMatchSearch.setEnable_flag("1");
        List<WmsInveTransaMatch> wmsInveTransaMatchList = wmsinvetransamatchDao.getListByEntity(wmsInveTransaMatchSearch);
        // 遍历失效的债权匹配关系，删除前保存在备份表中，并恢复债权包可用匹配金额
        if (wmsInveTransaMatchList.size() != 0 && wmsInveTransaMatchList != null)
        {
            for (int i = 0; i < wmsInveTransaMatchList.size(); i++)
            {
                WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchList.get(i);
                wmsInveTransaMatchBackupsDao.save(wmsInveTransaMatch);
                WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
				wmsFinaCreRepay.setWms_fina_cre_pay_id(wmsInveTransaMatch.getWms_fina_cre_repay_id());
				wmsFinaCreRepay.setMatching_creditor(wmsInveTransaMatch.getAssign_account());
				wmsFinaCreRepayDao.updateRedeem(wmsFinaCreRepay);
            }
            wmsinvetransamatchDao.deleteForMatchRuleChange(wms_inve_transa_prod_id);
        }

        return "success";
    }
    /**
     * 设置日期 参数int i是为了方法的公共使用 
     * 主要的目的就是计算的月份上有差异对月份进行加减
     * @param wmsFinaCreLoanApp
     * @return
     */
    private Date setDatebyCalendar(Date sDate, int i)
    {
    	  java.sql.Date  date1;
          Calendar  calendar=new GregorianCalendar();   
          calendar.setTime(sDate);
          calendar.add(Calendar.MONTH, +i);
          java.util.Date date_=calendar.getTime();
          date1=new java.sql.Date(date_.getTime()); 
          return date1;
    }
    /**
     * 债权匹配表中根据债权调整的对应协议表id查询对应信息
     * @date 2015-12-3
     * @param wms_inve_transa_protocol_id  
     * @return record list
     * @exception 
     * @author baisong
     */
	@Override
	public List<Map<String, Object>> getMapByProtocolid(Integer wms_inve_transa_protocol_id) {
		 Map<String, Object> hashMap = new HashMap<String, Object>();
	        hashMap.put("wms_inve_transa_protocol_id", wms_inve_transa_protocol_id);
	        hashMap.put("olnclaims", 2);
	        hashMap.put("sortname", "");
	        hashMap.put("sortorder", "");
	        BigDecimal money_sum = new BigDecimal(0);
	        List<Map<String, Object>> list = wmsinvetransamatchDao.getMapByProtocolid(hashMap);
	        for (int i = 0; i < list.size(); i++)
	        {
	            Map<String, Object> map = list.get(i);
	            BigDecimal bd = (BigDecimal) map.get("assign_account");
	            map.put("assign_account", getDecimalFormat(bd));
	            money_sum = money_sum.add(bd);
	            if (i == list.size() - 1)
	            {
	                map.put("money_sum", getDecimalFormat(money_sum));// 设置债权金额
	            }
	        }
	     return list;
	}
}
