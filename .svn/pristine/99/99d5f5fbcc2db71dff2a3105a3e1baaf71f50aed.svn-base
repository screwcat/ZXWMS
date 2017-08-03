package com.zx.emanage.inve.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveTransaApprovalInfoDao;
import com.zx.emanage.inve.persist.WmsInveTransaBackApplyDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.service.IWmsInveTransaBackApplyService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaBackApplySearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveTransaSearchBeanVO;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.emanage.util.gen.entity.WmsInveTransaBackApply;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.workflow.util.WorkflowConstantHelp;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvetransabackapplyService")
public class WmsInveTransaBackApplyServiceImpl implements IWmsInveTransaBackApplyService {
	private static Logger log = LoggerFactory.getLogger(WmsInveTransaBackApplyServiceImpl.class);

	@Autowired
	private WmsInveTransaBackApplyDao wmsinvetransabackapplyDao;
	
	@Autowired
	private WmsInveTransaDao wmsinvetransaDao; 
	
	@Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;
	
	@Autowired
	private WmsInveTransaCardDao wmsInveTransaCardDao;
	
	@Autowired
	private SysSpecialUserDao specialUserDao;
	
	@Autowired
	private PmPersonnelDao pmPersonnelDao;
	
	@Autowired
	private IWmsInveWorkFlowService iWmsInveWorkFlowService;
	
	@Autowired
	private WmsInveTransaApprovalInfoDao wmsInveTransaApprovalInfoDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveTransaBackApplySearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvetransabackapplyDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveTransaBackApplySearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvetransabackapplyDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvetransabackapplyDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveTransaBackApply getInfoByPK(Integer wms_inve_transa_back_apply_id) {
		return wmsinvetransabackapplyDao.get(wms_inve_transa_back_apply_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveTransaBackApply bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransabackapplyDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveTransaBackApply bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvetransabackapplyDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveTransaBackApply() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	@SuppressWarnings("unused")
    private List<WmsInveTransaBackApply> getListByEntity(WmsInveTransaBackApply queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveTransaBackApply> beanList = wmsinvetransabackapplyDao.getListByEntity(queryInfo);
		return beanList;
	}
	
	/**
     * Description: 退单申请页面初始化
     * @param paramMap user
     * @return Map
     * @author wangyihan
     */
    public Map<String, Object> singleApplicationDisp(Map<String, Object> paramMap, UserBean user) {
        log.debug("--------------------退单申请页面初始化处理开始--------------------");
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        paramMap.put("sortname", "wms_inve_transa_id");
        List<Map<String, Object>> list = wmsinvetransabackapplyDao.searchForTransaAndProdList(paramMap);
        if(list != null && list.size() > 0){
            if(list.size() == 1){
                Map<String, Object> transaAndProdBean = new HashMap<String, Object>();
                transaAndProdBean = list.get(0);
                SysSpecialUser specilaUser = new SysSpecialUser();
                specilaUser.setEnable_flag("1");
                //判断当前用户是否包含特批人
                boolean isSpecialUserFlag = SysTools.isSpecialUser(specialUserDao.getListByEntity(new SysSpecialUser()), user);
                String id_card = transaAndProdBean.get("id_card").toString();
                if(isSpecialUserFlag){
                    transaAndProdBean.put("id_card_show", id_card);
                }else{
                    transaAndProdBean.put("id_card_show", id_card);
                    if(!id_card.equals("") && id_card.length() == 18){
                        transaAndProdBean.put("id_card_show", id_card.substring(0, 3) + "********" + id_card.substring(15, id_card.length()));                                     
                    }else if(!id_card.equals("")){
                        transaAndProdBean.put("id_card_show", "********");
                    }
                }
                resultMap.put("transaAndProdBean", transaAndProdBean);
                
                //查询支付卡信息
                Integer wms_inve_transa_id = Integer.parseInt(transaAndProdBean.get("wms_inve_transa_id").toString());
                Map<String, Object> searchForCardMap = new HashMap<String, Object>();
                searchForCardMap.put("wms_inve_transa_id", wms_inve_transa_id);//上单信息表id
                searchForCardMap.put("pay_type", 2);//支付方式为银行卡
                searchForCardMap.put("sortname", "wms_inve_transa_card_id");
                List<Map<String, Object>> wmsInveTransaCardList = wmsInveTransaCardDao.search(searchForCardMap);
                if(wmsInveTransaCardList != null && wmsInveTransaCardList.size() > 0){
                    resultMap.put("wmsInveTransaCardBean", wmsInveTransaCardList.get(0));
                }else{
                    resultMap.put("wmsInveTransaCardBean", new HashMap<String, Object>());
                }
                
            }else{
                log.error("查出两条数据！");
            }
        }
        
        log.debug("--------------------退单申请页面初始化处理结束--------------------");
        return resultMap;
    }
	
    /**
     * Description: 退单申请页面保存
     * @param bean taskId user
     * @return String
     * @author wangyihan
     */
    @Override
    @Transactional
    public String singleApplicationSave(WmsInveTransaBackApply bean, String taskId, UserBean user) {
        log.debug("--------------------退单申请页面保存处理开始--------------------");
        
        //获取上单信息表id
        Integer wms_inve_transa_id = bean.getWms_inve_transa_id();
        //获取上单产品表id
        Integer wms_inve_transa_prod_id = bean.getWms_inve_transa_prod_id();
        
        //查询上单信息表信息
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wms_inve_transa_id);
        //查询上单产品表信息
        WmsInveTransaProd wmsInveTransaProd = wmsInveTransaProdDao.get(wms_inve_transa_prod_id);
        //查询部门经理信息
        PmPersonnel department_manager = pmPersonnelDao.get(wmsInveTransa.getDepartment_manager_id());
        //查询副总经理信息
        PmPersonnel vice_general_manager = pmPersonnelDao.get(wmsInveTransa.getVice_general_manager_id());
        
        bean.setWms_inve_pruduct_category_id(wmsInveTransaProd.getWms_inve_pruduct_category_id());
        bean.setBill_code(wmsInveTransa.getBill_code());
        bean.setCus_name(wmsInveTransa.getCus_name());
        bean.setId_card(wmsInveTransa.getId_card());
        bean.setMobile_phone(wmsInveTransa.getMobile_phone());
        bean.setCategory_name(wmsInveTransaProd.getCategory_name());
        bean.setProduct_account(wmsInveTransaProd.getProduct_account());
        
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        bean.setEnable_flag("1");
        bean.setSalesman_id(wmsInveTransa.getSalesman_id());
        bean.setSalesman_name(wmsInveTransa.getSalesman_name());
        bean.setSalesman_dept_id(wmsInveTransa.getSalesman_dept_id());
        bean.setSalesman_city_code(wmsInveTransa.getSalesman_city_code());
        
        bean.setDepartment_manager_id(wmsInveTransa.getDepartment_manager_id());
        bean.setDepartment_manager_name(department_manager.getPersonnel_name());
        bean.setDepartment_manager_dept_id(wmsInveTransa.getDepartment_manager_dept_id());
        
        bean.setVice_general_manager_id(wmsInveTransa.getVice_general_manager_id());
        bean.setVice_general_manager_name(vice_general_manager.getPersonnel_name());
        bean.setVice_general_manager_dept_id(wmsInveTransa.getVice_general_manager_dept_id());
        
        wmsinvetransabackapplyDao.save(bean);
        
        //调用流程(公用)
        WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
        wDebtWorkFlowVO.setTaskId(taskId);
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id.toString());//上单信息表id
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        String data_status = wmsInveTransa.getData_status();//当前单据状态
        if(data_status.equals("13")){//协议签订
            wDebtWorkFlowVO.setPass("false");
            wDebtWorkFlowVO.setDebtkey("4");
        }else if(data_status.equals("14")){//客户确认
            wDebtWorkFlowVO.setPass("false");
            wDebtWorkFlowVO.setDebtkey("5");
        }else if(data_status.equals("15")){//退单退回
            wDebtWorkFlowVO.setDebtkey("9");
        }
        iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        
        log.debug("--------------------退单申请页面保存处理结束--------------------");
        return "success";
    }
    
	/**
     * 获取退单确认列表数据
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    @Override
    public Map<String, Object> searchSingleConfirmationList(WmsInveTransaSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------获取退单确认列表数据处理开始--------------------");
        
        Map<String, Object> paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(
                WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId().toString(), "9");//待退单确认
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
            paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
        }
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())){
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        } 
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        
        paramMap.put("data_status_in", "('16')");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransabackapplyDao.searchForTransaAndProdList(paramMap);
        
        //加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(
                list, 
                (List<Integer>) paramMap.get("idList"), 
                (List<String>) paramMap.get("taskIdList"), 
                (String)paramMap.get("processDefinitionKey"));
        
        //实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        
        
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), 
                wmsinvetransabackapplyDao.findTransaAndProdCount(paramMap), list);
        
        log.debug("--------------------获取退单确认列表数据处理结束--------------------");
        return bean.getGridData();
    }
    
    /**
     * 退单确认列表数据导出
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> searchSingleConfirmationListForExport(WmsInveTransaSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------获取退单确认列表导出数据处理开始--------------------");
        
        Map<String, Object> paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(
                WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId().toString(), "9");//待退单确认
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())){
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        } 
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        
        paramMap.put("data_status_in", "('16')");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransabackapplyDao.searchForTransaAndProdList(paramMap);
        
        //加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(
                list, 
                (List<Integer>) paramMap.get("idList"), 
                (List<String>) paramMap.get("taskIdList"), 
                (String)paramMap.get("processDefinitionKey"));
        
        //实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), 
                wmsinvetransabackapplyDao.findTransaAndProdCount(paramMap), list);
        
        log.debug("--------------------获取退单确认列表数据导出处理结束--------------------");
        return bean.getGridData();
    }
    
    /**
     * 退单确认页面获取退单详情数据(与退单申请为同一页面但数据来源不同)
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    @Override
    public Map<String, Object> getBackApplyDataForConfirm(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------退单确认页面获取退单详情数据处理开始--------------------");
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        //获取上单信息表id
        Integer wms_inve_transa_id = queryInfo.getWms_inve_transa_id();
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramMap.put("enable_flag", "1");
        paramMap.put("sortname", "wms_inve_transa_id");
        
        List<Map<String, Object>> wmsInveTransaBackApplyList = wmsinvetransabackapplyDao.search(paramMap);
        if(wmsInveTransaBackApplyList != null && wmsInveTransaBackApplyList.size() > 0){
            Map<String, Object> bean = wmsInveTransaBackApplyList.get(wmsInveTransaBackApplyList.size() - 1);//取最新一条
            resultMap.put("wmsInveTransaBackApplyBean", bean);
            
            SysSpecialUser specilaUser = new SysSpecialUser();
            specilaUser.setEnable_flag("1");
            //判断当前用户是否包含特批人
            boolean isSpecialUserFlag = SysTools.isSpecialUser(specialUserDao.getListByEntity(new SysSpecialUser()), user);
            String id_card = bean.get("id_card").toString();
            if(isSpecialUserFlag){
                bean.put("id_card_show", id_card);
            }else{
                bean.put("id_card_show", id_card);
                if(!id_card.equals("") && id_card.length() == 18){
                    bean.put("id_card_show", id_card.substring(0, 3) + "********" + id_card.substring(15, id_card.length()));                                     
                }else if(!id_card.equals("")){
                    bean.put("id_card_show", "********");
                }
            }
        }
        
        log.debug("--------------------退单确认页面获取退单详情数据处理结束--------------------");
        return resultMap;
    }
	
    /**
     * 退单确认页面撤销退单
     * 
     * @param queryInfo user
     * @return String
     * @author wangyihan
     */
    @Override
    @Transactional
    public String revocationTransaBackApply(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------退单确认页面撤销退单处理开始--------------------");
        
        //获取上单信息表id
        Integer wms_inve_transa_id = queryInfo.getWms_inve_transa_id();
        //获取上单产品表id
        Integer wms_inve_transa_prod_id = queryInfo.getWms_inve_transa_prod_id();
        //获取taskId
        String taskId = queryInfo.getTaskId();
        //查询上单信息表信息
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wms_inve_transa_id);
        
        WmsInveTransaApprovalInfo approvalBean = new WmsInveTransaApprovalInfo();
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramMap.put("sortname", "wms_inve_transa_approval_info_id");
        List<Map<String, Object>> wmsInveTransaApprovalList = wmsInveTransaApprovalInfoDao.search(paramMap);
        if(wmsInveTransaApprovalList.size() > 0){
            if(wmsInveTransaApprovalList.size() == 1){
                approvalBean.setWms_inve_transa_approval_info_id(
                        Integer.parseInt(wmsInveTransaApprovalList.get(0).get("wms_inve_transa_approval_info_id").toString()));
            }else{
                //若为多条取最新一条
                approvalBean.setWms_inve_transa_approval_info_id(
                        Integer.parseInt(wmsInveTransaApprovalList.get(wmsInveTransaApprovalList.size() - 1).get("wms_inve_transa_approval_info_id").toString()));
            }
        }
        
        approvalBean.setWms_inve_transa_id(wms_inve_transa_id);
        approvalBean.setWms_inve_transa_prod_id(wms_inve_transa_prod_id);
        approvalBean.setBill_code(wmsInveTransa.getBill_code());
        approvalBean.setBack_apply_cancel_id(user.getUserId());
        approvalBean.setBack_apply_cancel_name(user.getRealname());
        approvalBean.setBack_apply_cancel_time(new Timestamp(System.currentTimeMillis()));
        approvalBean.setEnable_flag("1");
        
        if(approvalBean.getWms_inve_transa_approval_info_id() != null){//主键存在更新
            wmsInveTransaApprovalInfoDao.update(approvalBean);
        }else{//主键不存在新增
            wmsInveTransaApprovalInfoDao.save(approvalBean);
        }
        
        //调用流程(公用)
        WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
        wDebtWorkFlowVO.setTaskId(taskId);
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id.toString());//上单信息表id
        wDebtWorkFlowVO.setDebtkey("10");
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        
        log.debug("--------------------退单确认页面撤销退单处理结束--------------------");
        return "success";
    }
    
    /**
     * 退单确认页面审核
     * 
     * @param queryInfo user
     * @return String
     * @author wangyihan
     */
    @Override
    @Transactional
    public String approvalTransaApply(WmsInveTransaBackApplySearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------退单确认页面审核处理开始--------------------");
        
        //获取上单信息表id
        Integer wms_inve_transa_id = queryInfo.getWms_inve_transa_id();
        //获取上单产品表id
        Integer wms_inve_transa_prod_id = queryInfo.getWms_inve_transa_prod_id();
        //获取taskId
        String taskId = queryInfo.getTaskId();
        //查询上单信息表信息
        WmsInveTransa wmsInveTransa = wmsinvetransaDao.get(wms_inve_transa_id);
        
        WmsInveTransaApprovalInfo approvalBean = new WmsInveTransaApprovalInfo();
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_transa_id", wms_inve_transa_id);
        paramMap.put("sortname", "wms_inve_transa_approval_info_id");
        List<Map<String, Object>> wmsInveTransaApprovalList = wmsInveTransaApprovalInfoDao.search(paramMap);
        if(wmsInveTransaApprovalList.size() > 0){
            if(wmsInveTransaApprovalList.size() == 1){
                approvalBean.setWms_inve_transa_approval_info_id(
                        Integer.parseInt(wmsInveTransaApprovalList.get(0).get("wms_inve_transa_approval_info_id").toString()));
            }else{
                //若为多条取最新一条
                approvalBean.setWms_inve_transa_approval_info_id(
                        Integer.parseInt(wmsInveTransaApprovalList.get(wmsInveTransaApprovalList.size() - 1).get("wms_inve_transa_approval_info_id").toString()));
            }
        }
        
        approvalBean.setWms_inve_transa_id(wms_inve_transa_id);
        approvalBean.setWms_inve_transa_prod_id(wms_inve_transa_prod_id);
        approvalBean.setBill_code(wmsInveTransa.getBill_code());
        approvalBean.setBack_apply_id(user.getUserId());
        approvalBean.setBack_apply_name(user.getRealname());
        approvalBean.setBack_apply_time(new Timestamp(System.currentTimeMillis()));
        approvalBean.setBack_apply_result(queryInfo.getBack_apply_result());
        approvalBean.setBack_apply_advice(queryInfo.getBack_apply_advice());
        approvalBean.setEnable_flag("1");
        
        if(approvalBean.getWms_inve_transa_approval_info_id() != null){//主键存在更新
            wmsInveTransaApprovalInfoDao.update(approvalBean);
        }else{//主键不存在新增
            wmsInveTransaApprovalInfoDao.save(approvalBean);
        }
        
        //调用流程(公用)
        WmsInveDebtWorkFlowVO wDebtWorkFlowVO = new WmsInveDebtWorkFlowVO();
        wDebtWorkFlowVO.setTaskId(taskId);
        wDebtWorkFlowVO.setProcessDefinitionKey(WorkflowConstantHelp.FINANCIALSINGLEROCESS);
        wDebtWorkFlowVO.setBusinessId(wms_inve_transa_id.toString());//上单信息表id
        wDebtWorkFlowVO.setDebtkey("10");
        wDebtWorkFlowVO.setUserID(user.getUserId().toString());
        wDebtWorkFlowVO.setAdvice(queryInfo.getBack_apply_advice());
        if(queryInfo.getBack_apply_result().intValue() == 1){//通过
            wDebtWorkFlowVO.setPass("true");
        }else if(queryInfo.getBack_apply_result().intValue() == 0){//不通过
            wDebtWorkFlowVO.setPass("false");
        }
        iWmsInveWorkFlowService.publicApproval(wDebtWorkFlowVO);
        
        log.debug("--------------------退单确认页面审核处理结束--------------------");
        return "success";
    }
    
    /**
     * 获取退单退回列表数据
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    @Override
    public Map<String, Object> searchTransaBackReturnList(WmsInveTransaSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------获取退单退回列表数据处理开始--------------------");
        
        Map<String, Object> paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(
                WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId().toString(), "8");//退单退回
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())){
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        } 
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        
        paramMap.put("data_status_in", "('15')");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvetransabackapplyDao.searchForTransaAndProdList(paramMap);
        
        //加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(
                list, 
                (List<Integer>) paramMap.get("idList"), 
                (List<String>) paramMap.get("taskIdList"), 
                (String)paramMap.get("processDefinitionKey"));
        
        //实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), 
                wmsinvetransabackapplyDao.findTransaAndProdCount(paramMap), list);
        
        log.debug("--------------------获取退单退回列表数据处理结束--------------------");
        return bean.getGridData();
    }
    
    /**
     * 退单退回列表数据导出
     * 
     * @param queryInfo user
     * @return Map
     * @author wangyihan
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> searchTransaBackReturnListForExport(WmsInveTransaSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------获取退单退回列表导出数据处理开始--------------------");
        
        Map<String, Object> paramMap = iWmsInveWorkFlowService.getWorkFlowToIdList(
                WorkflowConstantHelp.FINANCIALSINGLEROCESS, user.getUserId().toString(), "8");//退单退回
        if(StringUtil.isNotBlank(queryInfo.getCus_name())){
            paramMap.put("cus_name", "%" + queryInfo.getCus_name() + "%");
        }
        if(StringUtil.isNotBlank(queryInfo.getCategory_name())){
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        } 
        if(StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }
        if(StringUtil.isNotBlank(queryInfo.getId_card())){
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        
        paramMap.put("data_status_in", "('15')");
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvetransabackapplyDao.searchForTransaAndProdList(paramMap);
        
        //加流程id
        list = iWmsInveWorkFlowService.addTaskIdToList(
                list, 
                (List<Integer>) paramMap.get("idList"), 
                (List<String>) paramMap.get("taskIdList"), 
                (String)paramMap.get("processDefinitionKey"));
        
        //实现对数据信息屏蔽效果
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specilaUsers = specialUserDao.getListByEntity(specialUser);
        list = SysTools.setListView(list, user, specilaUsers);
        
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), 
                wmsinvetransabackapplyDao.findTransaAndProdCount(paramMap), list);
        
        log.debug("--------------------获取退单退回列表导出数据处理结束--------------------");
        return bean.getGridData();
    }
    
}
