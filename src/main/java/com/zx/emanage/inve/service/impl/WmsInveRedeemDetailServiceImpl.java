package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveRedeemPrincipalDetailDao;
import com.zx.emanage.inve.persist.WmsInveSpecialApprovalDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveRedeemDetailService;
import com.zx.emanage.inve.service.IWmsInveRedeemService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.util.credit.CreditBusiness;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemDetailSearchBeanVO;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.util.gen.entity.WmsInveSpecialApproval;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinveredeemdetailService")
public class WmsInveRedeemDetailServiceImpl implements IWmsInveRedeemDetailService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveRedeemDetailServiceImpl.class);

    @Autowired
    private WmsInveRedeemDetailDao wmsinveredeemdetailDao;

    @Autowired
    private IWmsInveWorkFlowService wmsInveWorkFlowService;

    @Autowired
    private WmsInveRedeemDao wmsInveRedeemDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private WmsInveTransaMatchDao wmsInveTransaMatchDao;

    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;
    
    @Autowired
    private   PmPersonnelDao pmPersonnelDao;
    @Autowired
    private  IWmsInveRedeemService wmsInveRedeemServiceImpl;
    
    @Autowired
    private  WmsInvePruductCategoryDao wmsInvePruductCategoryDao;
    
    @Autowired
    private WmsInvePruductRebateWayDao wmsInvePruductRebateWayDao;
    
    @Autowired
    private WmsInveTransaProtocolDao wmsinvetransaprotocolDao;
    @Autowired
    private  WmsInveSpecialApprovalDao wmsInveSpecialApprovalDao;
    @Autowired
	private WmsInveRedeemPrincipalDetailDao wmsInveRedeemPrincipalDetailDao;// 赎回本金表
    
    @Autowired
    private IWmsInveClerkProtocolService wmsInveClerkProtocolService; // 柜员合同表

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinveredeemdetailDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithoutPagingBywiri(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if (queryInfo.getWms_inve_redeem_id() != null)
        {
            paramMap.put("wms_inve_redeem_id", queryInfo.getWms_inve_redeem_id());
        }
        List<Map<String, Object>> list = wmsinveredeemdetailDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInveRedeemDetailSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinveredeemdetailDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinveredeemdetailDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInveRedeemDetail getInfoByPK(Integer wms_inve_redeem_detail_id)
    {
        return wmsinveredeemdetailDao.get(wms_inve_redeem_detail_id);
    }

    @Override
    @Transactional
    public String save(WmsInveRedeemDetail bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinveredeemdetailDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
    /**
     * @title:doUpdate
     * 
     * Description :理财赎回三级审批
     * 
     * @param request
     * 
     * @param wms_inve_redeem_detail 赎回明细表
     * 
     * @param aInveWorkFlowVO 赎回流程
     * 
     * @param wms_inve_redeem_data 赎回主表
     * 
     * @return "success" or "error" or user defined
     * 
     * @author hancd
     */
    @Override
    @Transactional
    public String update(WmsInveRedeemDetail wms_inve_redeem_detail, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
                         WmsInveRedeem wInveRedeem,String redeemGridData)
    {
        
        String resStr = "success";
        int ret = 0;
        if (wDebtWorkFlowVO.getStatus().equals("1"))// 经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wInveRedeem.getWms_inve_redeem_id().toString());
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("1");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO, wms_inve_redeem_detail.getWms_inve_transa_id());
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("2"))// 副总经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wInveRedeem.getWms_inve_redeem_id().toString());
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("2");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO, wms_inve_redeem_detail.getWms_inve_transa_id());
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("3"))// 总经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wInveRedeem.getWms_inve_redeem_id().toString());
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("3");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            // 当pass=true方可对数据进行修改否则不允许进行任何修改
            if (wDebtWorkFlowVO.getPass().equals("true"))
            {
                // 变更总赎回金额 应得收益 应付管理费
                wmsInveRedeemDao.update(wInveRedeem);
          
                // 更新理财赎回明細表中的年化利率 应付管理费率 剩余利息天数 应收管理费 个人收益
                wmsinveredeemdetailDao.update(wms_inve_redeem_detail);
                
                // 上单产品表
                WmsInveTransaProd wInveTransaProd = new WmsInveTransaProd();
                wInveTransaProd.setWms_inve_transa_prod_id(wms_inve_redeem_detail.getWms_inve_transa_prod_id());
                wInveTransaProd.setProduct_account(wms_inve_redeem_detail.getRedeem_amount());
                // 修改上单产品表理财金额
                // 2016-11-14总经理审批后不更新上单表的产品金额--by guanx
                /*wmsInveTransaProdDao.updateRedeem(wInveTransaProd);*/
                WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wInveTransaProd.getWms_inve_transa_prod_id());
                // 查找复合要求的匹配债权
                List<WmsInveTransaMatch> wmsInveTransaMatchList = wmsInveTransaMatchDao.getListByEntity(wmsInveTransaMatchSearch);
                for (int j = 0; j < wmsInveTransaMatchList.size(); j++)
                {
                    WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchList.get(j);
                    WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
                    wmsFinaCreRepay.setWms_fina_cre_pay_id(wmsInveTransaMatch.getWms_fina_cre_repay_id());
                    wmsFinaCreRepay.setMatching_creditor(wmsInveTransaMatch.getAssign_account());
                    wmsFinaCreRepayDao.updateRedeem(wmsFinaCreRepay);
                }
                //修改赎回wms_inve_redeem_principal_detail表
                try {
                    /*	wmsInveRedeemPrincipalDetailDao
                    			.deleteByDetailId(wms_inve_redeem_detail
                    					.getWms_inve_redeem_detail_id());
                     */
    				List<WmsInveRedeemPrincipalDetail> listBean = JsonUtil
    						.jsonArrayToList(redeemGridData,
    								WmsInveRedeemPrincipalDetail.class);
                    // 批量更新赎回本金表
                    wmsInveRedeemPrincipalDetailDao.updateBatch(listBean);
                    /*// 保存赎回本金表
                    for (WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail : listBean) {
                    	wmsInveRedeemPrincipalDetail
                    			.setWms_inve_redeem_detail_id(wms_inve_redeem_detail
                    					.getWms_inve_redeem_detail_id());
                    	wmsInveRedeemPrincipalDetail
                    			.setUsed_income_amount(new BigDecimal(0));
                    	// 保存赎回明细表信息
                    	wmsInveRedeemPrincipalDetailDao
                    			.save(wmsInveRedeemPrincipalDetail);
                    }*/
    			} catch (Exception e) {
    				return "error";
    			}
                
            }
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        
        //审批不通过时  需要失效柜员业务表单据  和柜员协议表单据
        if (wDebtWorkFlowVO.getPass().equals("false"))
        {
            WmsInveClerkProtocol wmsInveClerkProtocol = new WmsInveClerkProtocol();
            wmsInveClerkProtocol.setWms_inve_redeem_id(wInveRedeem.getWms_inve_redeem_id());
            wmsInveClerkProtocol = wmsInveClerkProtocolService.getWmsInveClerkProtocolByEntity(wmsInveClerkProtocol);
            // null 说明已经释放过
            if (wmsInveClerkProtocol != null)
            {
                // 失效新合同
                wmsInveClerkProtocolService.disableWmsInveClerkProtocol(wInveRedeem.getWms_inve_redeem_id());
                // 释放债权
                CreditBusiness.getInstance()
                              .disableMatchedCreditForRedeemFlow(wmsInveClerkProtocol.getWms_inve_transa_id(),
                                                                 wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(),
                                                                 user);
                // 释放债权匹配关系历史表
                CreditBusiness.getInstance().deleteCreditMatchHistory(wmsInveClerkProtocol.getWms_inve_transa_id(), wmsInveClerkProtocol.getWms_inve_clerk_protocol_id(), user);

            }
        }

        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInveRedeemDetail() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInveRedeemDetail> getListByEntity(WmsInveRedeemDetail queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInveRedeemDetail> beanList = wmsinveredeemdetailDao.getListByEntity(queryInfo);
        return beanList;
    }

    @Override
    public List<WmsInveRedeemDetail> getListByPK(Integer wms_inve_redeem_id)
    {
        return wmsinveredeemdetailDao.getListbypk(wms_inve_redeem_id);

    }

    @Override
    public List<Map<String, Object>> getRedeemAllInfo(Integer wms_inve_redeem_id)
    {
        // 通过赎回单据主键查询出赎回明细表与单据表信息
    	List<Map<String, Object>> list= wmsinveredeemdetailDao.getRedeemAllInfo(wms_inve_redeem_id);
    	if(list!=null&&list.size()>0){
    		for(int i=0;i<list.size();i++){
    			BigDecimal bonus_rate=new BigDecimal(0);//奖励利率
    			Map<String, Object> map=list.get(i);
		    	//处理奖励利率问题
		        if("1".equals(map.get("category_interest_pay_method").toString())){//月付
					 //俩个时间月份差
					 int monthNum=0;
					 try{
						//monthNum=Integer.valueOf(map.get("month_num").t);
					 	monthNum=DateUtil.getMonthNum(map.get("date_of_payment_str").toString(),map.get("now_time").toString());
					 }catch (Exception e){
						e.printStackTrace();
					 }
					 if("1".equals(map.get("category_rebate_way").toString())){//返利方式--满月
						WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
						rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
						rebateWay.setEnable_flag("1");
						List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
						for(WmsInvePruductRebateWay rebate: rebateWays){
							if(rebate.getFull_month()==monthNum){
								bonus_rate=rebate.getBonus_rate();
							}
						}
					 }else  if("2".equals(map.get("category_rebate_way").toString())){//返利方式--满月存量
							Map<String, Object> map1 = new HashMap<String, Object>();
							map.put("b_id_card",map.get("id_card"));
							map.put("wms_inve_pruduct_category_id",Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
							//获取所有该用户该产品的正常单据
							List<WmsInveTransaProtocol> wmsInveTransaProtocols = wmsinvetransaprotocolDao.getAllProtocolForCategoryAndIDCard(map);
							//该客户该产品的总投资金额
							BigDecimal totalMoney = new BigDecimal(0);
							for(WmsInveTransaProtocol protocol : wmsInveTransaProtocols) {
								totalMoney = totalMoney.add(protocol.getProduct_account());
							}
							WmsInvePruductRebateWay rebateWay = new WmsInvePruductRebateWay();
							rebateWay.setWms_inve_pruduct_category_id(Integer.valueOf(map.get("wms_inve_pruduct_category_id").toString()));
							rebateWay.setEnable_flag("1");
							List<WmsInvePruductRebateWay> rebateWays = wmsInvePruductRebateWayDao.getListByEntity(rebateWay);
							//判断累积存量
							for(WmsInvePruductRebateWay wmsInvePruductRebateWay : rebateWays) {
								//数据库为万元，要乘10000
								BigDecimal start = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_begin()*10000);
								BigDecimal end = new BigDecimal(wmsInvePruductRebateWay.getCustomer_stock_end()*10000);
								if(totalMoney.compareTo(start) >= 0 && totalMoney.compareTo(end) <=0&&wmsInvePruductRebateWay.getFull_month()==monthNum) {
									bonus_rate=wmsInvePruductRebateWay.getBonus_rate();
								}
							}
					 }
		        }
		        map.put("bonus_rate", bonus_rate);//奖励利率
    		}
 		}
    	return list;
    }
    /**
     * Description :doUpdateProduct 领导审核修改--产品变更
     * 
     * @param request
     * @param wms_inve_redeem_detail 赎回明细表
     * @param aInveWorkFlowVO 赎回流程
     * @param wms_inve_redeem_data 赎回主表
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    @Transactional
    public String updateProduct(String wms_inve_redeem_detail, UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
                         WmsInveRedeem wInveRedeem)
    {
        String resStr = "success";
        int ret = 0;
        if (wDebtWorkFlowVO.getStatus().equals("1"))
        {// 经理审批
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey(wDebtWorkFlowVO.getStatus());
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("2"))
        {// 副总经理审批
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey(wDebtWorkFlowVO.getStatus());
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("3"))
        {// 总经理审批
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey(wDebtWorkFlowVO.getStatus());
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(String.valueOf(user.getUserId()));
            WmsInveRedeemDetail wmsInveRedeemDetail=new WmsInveRedeemDetail();
            wmsInveRedeemDetail.setWms_inve_redeem_id(wInveRedeem.getWms_inve_redeem_id());
            List<WmsInveRedeemDetail> wms_inve_redeem_detailArr = wmsinveredeemdetailDao.getListByEntity(wmsInveRedeemDetail);
            // 理財金額=每条投资金额-赎回金额
            for (int i = 0; i < wms_inve_redeem_detailArr.size(); i++)
            {
                // 每一条上单明细
                WmsInveRedeemDetail wDetail = wms_inve_redeem_detailArr.get(i);
                // 上单产品表
                WmsInveTransaProd wInveTransaProd = new WmsInveTransaProd();
                wInveTransaProd.setWms_inve_transa_prod_id(wDetail.getWms_inve_transa_prod_id());
                wInveTransaProd.setProduct_account(wDetail.getRedeem_amount());
                // 修改上单产品表理财金额
                wmsInveTransaProdDao.updateRedeem(wInveTransaProd);
                WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
                wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wInveTransaProd.getWms_inve_transa_prod_id());
                // 查找复合要求的匹配债权
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
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
    
    /**
     * @title:doUpdateforphone
     * Description :理财赎回三级审批-给手机赎回审批使用
     * @param personnel_shortcode 
     * @param aInveWorkFlowVO 赎回流程
     * @param wms_inve_redeem_id 赎回表主键
     * @return "success" or "error" or user defined
     * @author baisong
     */
    @Override
    @Transactional
    public Map<String,Object> doUpdateforphone(String personnel_shortcode, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,String wms_inve_redeem_id)
    {
    	Map<String,Object> map=new HashMap<>();
    	PmPersonnel pmPersonnel=new PmPersonnel();
    	pmPersonnel.setPersonnel_shortcode(personnel_shortcode);
    	List<PmPersonnel> listp=	pmPersonnelDao.getListByEntity(pmPersonnel);
    	if(listp==null||listp.size()==0){
    		map.put("result", "error");
	        map.put("message", "获取审批人调用失败！");
	        map.put("flag", true);
	        return map;
    	}

    	
	    if("true".equals(wDebtWorkFlowVO.getPass())){
            if (StringUtil.isBlank(wDebtWorkFlowVO.getAdvice()))
            {
                wDebtWorkFlowVO.setAdvice("同意");
            }
	    }else if("false".equals(wDebtWorkFlowVO.getPass())){
            if (StringUtil.isBlank(wDebtWorkFlowVO.getAdvice()))
            {
                wDebtWorkFlowVO.setAdvice("不同意");
            }
	    }
        String resStr = "success";
        int ret = 0;
    	if(wms_inve_redeem_id!=null&&!"".equals(wms_inve_redeem_id)){
    		WmsInveRedeem	wmsInveRedeem=wmsInveRedeemDao.get(Integer.valueOf(wms_inve_redeem_id));
    		if(wmsInveRedeem!=null){
    			//状态为3 有特批领导则是特批单据 走特批流程
    			if(wDebtWorkFlowVO.getStatus().equals("3")&&wmsInveRedeem.getSpecial_approval_leader_id()!=null&&!"".equals(wmsInveRedeem.getSpecial_approval_leader_id())){
    				UserBean user=new UserBean();
    				user.setUserId(listp.get(0).getPersonnel_id());//人员id
    				if("true".equals(wDebtWorkFlowVO.getPass())){
    					wmsInveRedeem.setSpecial_approval_result("1");	//同意
    					wmsInveRedeem.setSpecial_approval_advice("同意");
    					wmsInveRedeem.setSpecial_approval_leader_name(listp.get(0).getPersonnel_name());
    					wmsInveRedeem.setSpecial_approval_leader_id(listp.get(0).getPersonnel_id());
    					//转换为特批表中的id
    					WmsInveSpecialApproval app=new WmsInveSpecialApproval();
    					app.setSpecial_approval_leader_pmid(listp.get(0).getPersonnel_id());
    					app.setDef_asc(0);//归属
    					List<WmsInveSpecialApproval>list=wmsInveSpecialApprovalDao.getListByEntity(app);
    					if(list!=null&&list.size()>0){
    						wmsInveRedeem.setSpecial_approval_leader_id(list.get(0).getSpecial_approval_leader_id());
    					}
    					wmsInveRedeem.setManager_advice(wmsInveRedeem.getSpecial_approval_advice());//总经理审批意见
    					wmsInveRedeem.setManager_result(wmsInveRedeem.getSpecial_approval_result());//总经理审批结果
    					wmsInveRedeem.setManager_date(new java.sql.Date(System.currentTimeMillis()));
    				}else if("false".equals(wDebtWorkFlowVO.getPass())){
    					wmsInveRedeem.setSpecial_approval_result("0");	//不同意
    					wmsInveRedeem.setSpecial_approval_advice("不同意");
    					wmsInveRedeem.setSpecial_approval_leader_name(listp.get(0).getPersonnel_name());
    					wmsInveRedeem.setSpecial_approval_leader_id(listp.get(0).getPersonnel_id());
    					//转换为特批表中的id
    					WmsInveSpecialApproval app=new WmsInveSpecialApproval();
    					app.setSpecial_approval_leader_pmid(listp.get(0).getPersonnel_id());
    					app.setDef_asc(0);//归属
    					List<WmsInveSpecialApproval>list=wmsInveSpecialApprovalDao.getListByEntity(app);
    					if(list!=null&&list.size()>0){
    						wmsInveRedeem.setSpecial_approval_leader_id(list.get(0).getSpecial_approval_leader_id());
    					}
    					wmsInveRedeem.setManager_advice(wmsInveRedeem.getSpecial_approval_advice());//总经理审批意见
    					wmsInveRedeem.setManager_result(wmsInveRedeem.getSpecial_approval_result());//总经理审批结果
    					wmsInveRedeem.setManager_date(new java.sql.Date(System.currentTimeMillis()));
    				}
    				wmsInveRedeemDao.update(wmsInveRedeem);//更新
    				wmsInveRedeemServiceImpl.saveRedeemspecialapprovalInfo(wmsInveRedeem,user,wDebtWorkFlowVO);
    				map.put("result", resStr);
    		        map.put("message", "特批审批调用成功");
    		        map.put("flag", true);
    		        return map;
    			}
    		}
    	}
        if (wDebtWorkFlowVO.getStatus().equals("1"))//经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wms_inve_redeem_id);
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("1");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(listp.get(0).getPersonnel_id().toString());
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("2"))// 副总经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wms_inve_redeem_id);
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("2");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(listp.get(0).getPersonnel_id().toString());
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        else if (wDebtWorkFlowVO.getStatus().equals("3"))// 总经理审批
        {
        	//流程实例Key
        	wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        	//赎回单据ID
        	wDebtWorkFlowVO.setBusinessId(wms_inve_redeem_id);
            // 设置赎回Invekey,完成经理审批
        	wDebtWorkFlowVO.setDebtkey("3");
            // 设置当前用户登录ID,完成任务办理
        	wDebtWorkFlowVO.setUserID(listp.get(0).getPersonnel_id().toString());
            // 调用具体完成方法
            wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
            ret = 1;
        }
        if (ret == 0)
        {
            resStr = "error";
            map.put("result", resStr);
	        map.put("message", "三级审批调用失败");
	        map.put("flag", false);
        }else{
        	map.put("result", resStr);
 	        map.put("message", "三级审批调用成功");
        	map.put("flag", true);
        }
        return map;
    }

	@Override
	public String specialRedemptionUpdate(WmsInveRedeemDetail wms_inve_redeem_detail,
			UserBean user, WmsInveDebtWorkFlowVO wDebtWorkFlowVO,
			WmsInveRedeem wInveRedeem,String redeemGridData) {
		
		String resStr = "success";
		int ret = 0;
		// 当pass=true方可对数据进行修改否则不允许进行任何修改
		if (wDebtWorkFlowVO.getPass().equals("true")) {
			// 变更总赎回金额 应得收益 应付管理费
			wInveRedeem.setSpecial_approval_advice(wDebtWorkFlowVO.getAdvice());
			wInveRedeem.setSpecial_approval_date(new java.sql.Date(System.currentTimeMillis()));
			wInveRedeem.setSpecial_approval_leader_id(user.getUserId());
			wInveRedeem.setSpecial_approval_leader_name(user.getRealname());
			wInveRedeem.setSpecial_approval_operator_id(user.getUserId());
			//转换为特批表中的id
			WmsInveSpecialApproval app=new WmsInveSpecialApproval();
			app.setSpecial_approval_leader_pmid(user.getUserId());
			app.setDef_asc(0);//归属
			List<WmsInveSpecialApproval>list=wmsInveSpecialApprovalDao.getListByEntity(app);
			if(list!=null&&list.size()>0){
				wInveRedeem.setSpecial_approval_leader_id(list.get(0).getSpecial_approval_leader_id());
			}
			wInveRedeem.setSpecial_approval_result("1");//同意
			wInveRedeem.setManager_advice(wInveRedeem.getSpecial_approval_advice());//总经理审批意见
			wInveRedeem.setManager_result(wInveRedeem.getSpecial_approval_result());//总经理审批结果
			wInveRedeem.setManager_date(new java.sql.Date(System.currentTimeMillis()));
			wmsInveRedeemDao.update(wInveRedeem);
			
			// 更新理财赎回明細表中的年化利率 应付管理费率 剩余利息天数 应收管理费 个人收益
			wmsinveredeemdetailDao.update(wms_inve_redeem_detail);
	
			// 上单产品表
            WmsInveTransaProd wInveTransaProd = new WmsInveTransaProd();
            wInveTransaProd.setWms_inve_transa_prod_id(wms_inve_redeem_detail
            		.getWms_inve_transa_prod_id());
            wInveTransaProd.setProduct_account(wms_inve_redeem_detail
            		.getRedeem_amount());
            // 修改上单产品表理财金额
            /* wmsInveTransaProdDao.updateRedeem(wInveTransaProd);*/

            WmsInveTransaMatch wmsInveTransaMatchSearch = new WmsInveTransaMatch();
            wmsInveTransaMatchSearch.setWms_inve_transa_prod_id(wInveTransaProd.getWms_inve_transa_prod_id());
			// 查找复合要求的匹配债权
			List<WmsInveTransaMatch> wmsInveTransaMatchList = wmsInveTransaMatchDao
					.getListByEntity(wmsInveTransaMatchSearch);
			for (int j = 0; j < wmsInveTransaMatchList.size(); j++) {
				WmsInveTransaMatch wmsInveTransaMatch = wmsInveTransaMatchList
						.get(j);
				WmsFinaCreRepay wmsFinaCreRepay = new WmsFinaCreRepay();
				wmsFinaCreRepay
						.setWms_fina_cre_pay_id(wmsInveTransaMatch
								.getWms_fina_cre_repay_id());
				wmsFinaCreRepay.setMatching_creditor(wmsInveTransaMatch
						.getAssign_account());
				wmsFinaCreRepayDao.updateRedeem(wmsFinaCreRepay);
			}
			try {
                /*wmsInveRedeemPrincipalDetailDao
                		.deleteByDetailId(wms_inve_redeem_detail
                				.getWms_inve_redeem_detail_id());
                */
				List<WmsInveRedeemPrincipalDetail> listBean = JsonUtil
						.jsonArrayToList(redeemGridData,
								WmsInveRedeemPrincipalDetail.class);

                wmsInveRedeemPrincipalDetailDao.updateBatch(listBean);

                /*
                // 保存赎回本金表
                for (WmsInveRedeemPrincipalDetail wmsInveRedeemPrincipalDetail : listBean) {
                	wmsInveRedeemPrincipalDetail
                			.setWms_inve_redeem_detail_id(wms_inve_redeem_detail
                					.getWms_inve_redeem_detail_id());
                	wmsInveRedeemPrincipalDetail
                			.setUsed_income_amount(new BigDecimal(0));
                	// 保存赎回明细表信息
                	wmsInveRedeemPrincipalDetailDao
                			.save(wmsInveRedeemPrincipalDetail);
                }*/
			} catch (Exception e) {
				return "error";
			}
		}else{
            wInveRedeem.setSpecial_approval_result("0");// 不同意
		}
		//调用特批方法并执行流程
		wmsInveRedeemServiceImpl.saveRedeemspecialapprovalInfo(wInveRedeem, user, wDebtWorkFlowVO);
	 	/*wDebtWorkFlowVO.setDebtkey("6");
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        wDebtWorkFlowVO.setAdvice(wInveRedeem.getSpecial_approval_advice());
        wDebtWorkFlowVO.setBusinessId(wInveRedeem.getWms_inve_redeem_id().toString());
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALREDEMPTIONPROCESS);
        wmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);*/
		
		ret = 1;
		
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}
}
