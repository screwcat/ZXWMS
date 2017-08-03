package com.zx.emanage.inve.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.exception.InveTransSysException;
import com.zx.emanage.inve.exception.InveTransaConcurrentException;
import com.zx.emanage.inve.persist.WmsInveDebtHeadDao;
import com.zx.emanage.inve.persist.WmsInveDebtOlnclaimsDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchBackupsDao;
import com.zx.emanage.inve.persist.WmsInveTransaMatchDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.service.IWmsInveDebtHeadService;
import com.zx.emanage.inve.service.IWmsInveDebtOlnclaimsService;
import com.zx.emanage.inve.service.IWmsInveTransaMatchService;
import com.zx.emanage.inve.service.IWmsInveWorkFlowService;
import com.zx.emanage.inve.vo.WmsInveDebtOlnclaimsSearchBeanVO;
import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsInveDebtHead;
import com.zx.emanage.util.gen.entity.WmsInveDebtOlnclaims;
import com.zx.emanage.util.gen.entity.WmsInveTransaMatch;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvedebtolnclaimsService")
public class WmsInveDebtOlnclaimsServiceImpl implements IWmsInveDebtOlnclaimsService {
	private static Logger log = LoggerFactory.getLogger(WmsInveDebtOlnclaimsServiceImpl.class);

	@Autowired
	private WmsInveDebtOlnclaimsDao wmsinvedebtolnclaimsDao;
	
	@Autowired
	private IWmsInveTransaMatchService wmsInveTransaMatchService;
	
	@Autowired
	private WmsInveTransaMatchDao wmsInveTransaMatchDao;
	
	@Autowired
	private WmsInveTransaMatchBackupsDao wmsInveTransaMatchBackupsDao;
	
    @Autowired
    private WmsFinaCreRepayDao wmsFinaCreRepayDao;
    
    @Autowired
    private WmsInveDebtOlnclaimsDao wmsInveDebtOlnclaimsDao;
    
    @Autowired
    private WmsInveDebtHeadDao wmsinvedebtheadDao;

    @Autowired
    private IWmsInveWorkFlowService approveInveWorkFlowService;
    
    @Autowired
    private WmsInveTransaProtocolDao wmsInveTransaProtocolDao;

	@Autowired
	private IWmsInveWorkFlowService wmsInveWorkFlowService;
	
	@Autowired
	private WmsInveTransaDao wmsInveTransaDao;
	
	@Autowired
	private IWmsInveDebtHeadService iwmsInveDebtHeadService;
	
	@Autowired
    private SysSpecialUserDao sysSpecialUserDao;
	
	@Autowired
    private WmsCreApproBorrowProtocolDao wmsCreApproBorrowProtocolDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsinvedebtolnclaimsDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsInveDebtOlnclaimsSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsinvedebtolnclaimsDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsinvedebtolnclaimsDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsInveDebtOlnclaims getInfoByPK(Integer wms_inve_debt_olnclaims_id) {
		return wmsinvedebtolnclaimsDao.get(wms_inve_debt_olnclaims_id);
	}

	@Override	
	@Transactional
	public String save(WmsInveDebtOlnclaims bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvedebtolnclaimsDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsInveDebtOlnclaims bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsinvedebtolnclaimsDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsInveDebtOlnclaims() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsInveDebtOlnclaims> getListByEntity(WmsInveDebtOlnclaims queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsInveDebtOlnclaims> beanList = wmsinvedebtolnclaimsDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * 债权变更 - 债权变更申请
	 * 保存前台传来的原债权信息和债权调整申请信息
	 * @param String
	 * @author yangqiyu
	 */
	@Override
	@Transactional
	public String savelist(List<WmsInveDebtOlnclaims> list, UserBean user) {
		String resStr ="success";
		int ret =0;
		//接受前台传递的list并解析成map
		for(WmsInveDebtOlnclaims wmsInveDebtOlnclaims:list){
			wmsInveDebtOlnclaims.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
			ret=wmsinvedebtolnclaimsDao.save(wmsInveDebtOlnclaims);
			if(ret == 0){
				return "error";
			}
		}
		return resStr;
	}
	
	/**
	 * 债权调整保存
	 */
	@Override
	@Transactional
	/**
     * @Title: changeCreditorTempSave 
     * @Description: 债权调整保存
     * @param String transaMatch, String transaMatchNew, Integer wms_inve_debt_head_id, String taskId, String surplus,
              String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user
     * @return String 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
	public String changeCreditorSave(String transaMatch, String transMatchOld, String transaMatchNew, 
            Integer wms_inve_debt_head_id, String taskId, String surplus,
            String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user) throws InveTransaConcurrentException, InveTransSysException{
	    log.debug("--------------------债权调整保存处理开始--------------------");
	    
	    //修改前还款信息表主键list
        List<Integer> wms_fina_cre_repay_id_old = new ArrayList<Integer>();
        //修改后还款信息表主键list
        List<Integer> wms_fina_cre_repay_id_new = new ArrayList<Integer>();
        
        /*//原债权匹配记录(此操作在打印协议时完成)
        List<WmsInveTransaMatch> matchList = JsonUtil.jsonArrayToList(transaMatch, WmsInveTransaMatch.class);
        for(WmsInveTransaMatch match : matchList) {
            //修改还款信息表可匹配的债权(matching_creditor), 加上原有债权的本次转让额度
            WmsFinaCreRepay wmsFinaCreRepay = wmsFinaCreRepayDao.get(match.getWms_fina_cre_repay_id());
            wmsFinaCreRepay.setMatching_creditor(wmsFinaCreRepay.getMatching_creditor().add(match.getAssign_account()));
            wmsFinaCreRepayDao.update(wmsFinaCreRepay);
            //删除原有匹配信息
            wmsInveTransaMatchDao.deleteById(match.getWms_inve_transa_match());
            //备份
            wmsInveTransaMatchBackupsDao.save(match);
            //原债权匹配包含的还款信息表主键id
            wms_fina_cre_repay_id_old.add(match.getWms_fina_cre_repay_id());
        }*/
        
        List<WmsInveTransaMatch> matchOldList = new ArrayList<WmsInveTransaMatch>();
        if(StringUtils.isNotBlank(transMatchOld)){
            matchOldList = JsonUtil.jsonArrayToList(transMatchOld, WmsInveTransaMatch.class);
        }
        for(WmsInveTransaMatch matchOld : matchOldList) {
            wms_fina_cre_repay_id_old.add(matchOld.getWms_fina_cre_repay_id());
        }
        
        //根据债权调整申请主表id删除原有的债权调整申请债权信息
        wmsInveDebtOlnclaimsDao.deleteOlnclaimsRecordsByWmsInveDebtHeadId(wms_inve_debt_head_id);
        
        List<WmsInveTransaMatch> matchNewList = JsonUtil.jsonArrayToList(transaMatchNew, WmsInveTransaMatch.class);
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        
        WmsFinaCreRepay wmsFinaCreRepay = null;
        WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = null;
        
        //插入新的债权调整申请债权信息
        Map<Integer, BigDecimal> validMap = new HashMap<Integer, BigDecimal>();
        for(WmsInveTransaMatch matchNew : matchNewList) {
            validMap.put(matchNew.getWms_fina_cre_repay_id(), matchNew.getAssign_account());
            
            wmsFinaCreRepay = wmsFinaCreRepayDao.get(matchNew.getWms_fina_cre_repay_id());//还款信息bean
            wmsCreApproBorrowProtocol = wmsCreApproBorrowProtocolDao.get(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());//合同信息bean
            
            WmsInveDebtOlnclaims wmsInveDebtOlnclaims = new WmsInveDebtOlnclaims();
            wmsInveDebtOlnclaims.setWms_inve_debt_head_id(wms_inve_debt_head_id);
            wmsInveDebtOlnclaims.setWms_inve_transa_match_id(matchNew.getWms_inve_transa_match());
            wmsInveDebtOlnclaims.setWms_inve_transa_prod_id(matchNew.getWms_inve_transa_prod_id());
            wmsInveDebtOlnclaims.setWms_fina_cre_repay_id(matchNew.getWms_fina_cre_repay_id());
            
            wms_fina_cre_repay_id_new.add(matchNew.getWms_fina_cre_repay_id());
            
            wmsInveDebtOlnclaims.setCredit_name(matchNew.getCredit_name());
            wmsInveDebtOlnclaims.setCredit_id_card(wmsCreApproBorrowProtocol.getDebtor_identity_id());//重新查询身份证号(不带*号的)
            wmsInveDebtOlnclaims.setProtocol_code(matchNew.getProtocol_code());
            wmsInveDebtOlnclaims.setAssign_account(matchNew.getAssign_account());
            wmsInveDebtOlnclaims.setDate_of_assign(matchNew.getDate_of_assign());
            wmsInveDebtOlnclaims.setRepay_begin_date(matchNew.getRepay_begin_date());
            wmsInveDebtOlnclaims.setRepay_end_date(matchNew.getRepay_end_date());
            wmsInveDebtOlnclaims.setProduct_interest_month(matchNew.getProduct_interest_month());
            wmsInveDebtOlnclaims.setCreate_timestamp(nowTime);
            wmsInveDebtOlnclaims.setCreate_user_id(user.getUserId());
            wmsInveDebtOlnclaims.setEnable_flag("1");
            wmsInveDebtOlnclaims.setOlnclaims("2");
            wmsInveDebtOlnclaimsDao.save(wmsInveDebtOlnclaims);
        }
        
        //比较修改前与修改后的还款信息表主键id的list
        List<Integer> addList = getDiffList(wms_fina_cre_repay_id_old, wms_fina_cre_repay_id_new);
        List<Integer> reduceList = getDiffList(wms_fina_cre_repay_id_new, wms_fina_cre_repay_id_old);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(addList.size() > 0){//将还款信息表的占用情况改为1(代表被占用)
            for(Integer id : addList){
                wmsFinaCreRepay = wmsFinaCreRepayDao.get(id);
                if(wmsFinaCreRepay.getIs_occupy() != null && wmsFinaCreRepay.getIs_occupy() == 1){
                    throw new InveTransaConcurrentException();
                }
                if(wmsFinaCreRepay.getMatching_creditor() != null && 
                        wmsFinaCreRepay.getMatching_creditor().compareTo(validMap.get(id)) < 0){//判断当前匹配的债权额度是否大于需要匹配的额度
                    throw new InveTransSysException();
                }
                paramMap.put("wms_fina_pay_id", id);
                paramMap.put("status", 1);
                paramMap.put("type", 1);//1代表WMS 2代表PTP
                wmsInveDebtOlnclaimsDao.updateWmsFinaCreRepayStatus(paramMap);
            }
        }
        if(reduceList.size() > 0){//将还款信息表的占用情况改为0(代表未被占用)
            for(Integer id : reduceList){
                paramMap.put("wms_fina_pay_id", id);
                paramMap.put("status", 0);
                paramMap.put("type", null);//1代表WMS 2代表PTP
                wmsInveDebtOlnclaimsDao.updateWmsFinaCreRepayStatus(paramMap);
            }
        }
        
		//surplus剩余未匹配的债权
		if(surplus != null && !"".equals(surplus)) {
			wmsInveTransaMatchService.autoTransaMatchNew(Integer.parseInt(wms_inve_transa_prod_id), new BigDecimal(surplus), wms_inve_debt_head_id, user);
		}
		
		WmsInveDebtHead bean = new WmsInveDebtHead();
		bean.setWms_inve_debt_head_id(wms_inve_debt_head_id);
		bean.setIs_debtmatch_finish("1");
		wmsinvedebtheadDao.update(bean);
		
		//将原始合同(协议)变为失效
//		wmsInveTransaProtocolDao.setTransaProtocolFailure(Integer.parseInt(wms_inve_transa_id));
		
		//查询最新的
		WmsInveDebtHead wmsInveDebtHead = wmsinvedebtheadDao.get(wms_inve_debt_head_id);
		iwmsInveDebtHeadService.approvalSave(null,wmsInveDebtHead, user, "zqtz", taskId);
		
		log.debug("--------------------债权调整保存处理结束--------------------");
		return "success";
	}

	/**
     * @Title: selectOlnclaimsByWmsInveDebtHeadId 
     * @Description: 初始化根据债权调整主表id查询债权调整表信息
     * @param paramMap
     * @return List<Map<String, Object>> 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    public Map<String, Object> selectOlnclaimsByWmsInveDebtHeadId(WmsInveDebtOlnclaimsSearchBeanVO queryInfo, UserBean user) {
        log.debug("--------------------初始化根据债权调整主表id查询债权调整表信息处理开始--------------------");
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_inve_debt_head_id", queryInfo.getWms_inve_debt_head_id());
        paramMap.put("olnclaims", 2);
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String,Object>>  list = wmsinvedebtolnclaimsDao.selectOlnclaimsByWmsInveDebtHeadId(paramMap);
        
        SysSpecialUser specilaUser = new SysSpecialUser();
        specilaUser.setEnable_flag("1");
        boolean isSpecialUserFlag = SysTools.isSpecialUser(sysSpecialUserDao.getListByEntity(new SysSpecialUser()), user);
        
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        resMap.put("isSpecialUserFlag", isSpecialUserFlag);//是否为特批人标识
        
        log.debug("--------------------初始化根据债权调整主表id查询债权调整表信息处理结束--------------------");
        return resMap;      
    }
	
	/**
     * @Title: changeCreditorTempSave 
     * @Description: 债权调整暂存
     * @param String transaMatch, String transaMatchNew, Integer wms_inve_debt_head_id, String taskId, String surplus,
              String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user
     * @return String 
     * @throws
     * @author wangihan 
     * @date 2015年11月26日
     */
    @Transactional
	public String changeCreditorTempSave(String transaMatch, String transMatchOld, String transaMatchNew, 
	        Integer wms_inve_debt_head_id, String taskId, String surplus,
            String wms_inve_transa_id, String wms_inve_transa_prod_id, UserBean user) throws InveTransaConcurrentException, InveTransSysException{
	    log.debug("--------------------债权调整暂存处理开始--------------------");
	    
	    //修改前还款信息表主键list
        List<Integer> wms_fina_cre_repay_id_old = new ArrayList<Integer>();
	    //修改后还款信息表主键list
        List<Integer> wms_fina_cre_repay_id_new = new ArrayList<Integer>();
	    
        List<WmsInveTransaMatch> matchOldList = new ArrayList<WmsInveTransaMatch>();
        if(StringUtils.isNotBlank(transMatchOld)){
            matchOldList = JsonUtil.jsonArrayToList(transMatchOld, WmsInveTransaMatch.class);
        }
        for(WmsInveTransaMatch matchOld : matchOldList) {
            wms_fina_cre_repay_id_old.add(matchOld.getWms_fina_cre_repay_id());
        }
        
	    //根据债权调整申请主表id删除原有的债权调整申请债权信息
	    wmsInveDebtOlnclaimsDao.deleteOlnclaimsRecordsByWmsInveDebtHeadId(wms_inve_debt_head_id);
	    
	    List<WmsInveTransaMatch> matchNewList = JsonUtil.jsonArrayToList(transaMatchNew, WmsInveTransaMatch.class);
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	    
	    WmsFinaCreRepay wmsFinaCreRepay = null;
	    WmsCreApproBorrowProtocol wmsCreApproBorrowProtocol = null;
	    
	    //插入新的债权调整申请债权信息
	    Map<Integer, BigDecimal> validMap = new HashMap<Integer, BigDecimal>();
        for(WmsInveTransaMatch matchNew : matchNewList) {
            validMap.put(matchNew.getWms_fina_cre_repay_id(), matchNew.getAssign_account());
            
            wmsFinaCreRepay = wmsFinaCreRepayDao.get(matchNew.getWms_fina_cre_repay_id());//还款信息bean
            wmsCreApproBorrowProtocol = wmsCreApproBorrowProtocolDao.get(wmsFinaCreRepay.getWms_cre_appro_borrow_protocol_id());//合同信息bean
            
            WmsInveDebtOlnclaims wmsInveDebtOlnclaims = new WmsInveDebtOlnclaims();
            wmsInveDebtOlnclaims.setWms_inve_debt_head_id(wms_inve_debt_head_id);
            wmsInveDebtOlnclaims.setWms_inve_transa_match_id(matchNew.getWms_inve_transa_match());
            wmsInveDebtOlnclaims.setWms_inve_transa_prod_id(matchNew.getWms_inve_transa_prod_id());
            wmsInveDebtOlnclaims.setWms_fina_cre_repay_id(matchNew.getWms_fina_cre_repay_id());
            
            wms_fina_cre_repay_id_new.add(matchNew.getWms_fina_cre_repay_id());
            
            wmsInveDebtOlnclaims.setCredit_name(matchNew.getCredit_name());
            wmsInveDebtOlnclaims.setCredit_id_card(wmsCreApproBorrowProtocol.getDebtor_identity_id());//重新查询身份证号(不带*号的)
            wmsInveDebtOlnclaims.setProtocol_code(matchNew.getProtocol_code());
            wmsInveDebtOlnclaims.setAssign_account(matchNew.getAssign_account());
            wmsInveDebtOlnclaims.setDate_of_assign(matchNew.getDate_of_assign());
            wmsInveDebtOlnclaims.setRepay_begin_date(matchNew.getRepay_begin_date());
            wmsInveDebtOlnclaims.setRepay_end_date(matchNew.getRepay_end_date());
            wmsInveDebtOlnclaims.setProduct_interest_month(matchNew.getProduct_interest_month());
            wmsInveDebtOlnclaims.setCreate_timestamp(nowTime);
            wmsInveDebtOlnclaims.setCreate_user_id(user.getUserId());
            wmsInveDebtOlnclaims.setEnable_flag("1");
            wmsInveDebtOlnclaims.setOlnclaims("2");
            wmsInveDebtOlnclaimsDao.save(wmsInveDebtOlnclaims);
        }
        
        //比较修改前与修改后的还款信息表主键id的list
        List<Integer> addList = getDiffList(wms_fina_cre_repay_id_old, wms_fina_cre_repay_id_new);
        List<Integer> reduceList = getDiffList(wms_fina_cre_repay_id_new, wms_fina_cre_repay_id_old);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(addList.size() > 0){//将还款信息表的占用情况改为1(代表被占用)
            for(Integer id : addList){
                wmsFinaCreRepay = wmsFinaCreRepayDao.get(id);
                if(wmsFinaCreRepay.getIs_occupy() != null && wmsFinaCreRepay.getIs_occupy() == 1){
                    throw new InveTransaConcurrentException();
                }
                if(wmsFinaCreRepay.getMatching_creditor() != null && 
                        wmsFinaCreRepay.getMatching_creditor().compareTo(validMap.get(id)) < 0){//判断当前匹配的债权额度是否大于需要匹配的额度
                    throw new InveTransSysException();
                }
                paramMap.put("wms_fina_pay_id", id);
                paramMap.put("status", 1);
                paramMap.put("type", 1);//1代表WMS 2代表PTP
                wmsInveDebtOlnclaimsDao.updateWmsFinaCreRepayStatus(paramMap);
            }
        }
        if(reduceList.size() > 0){//将还款信息表的占用情况改为0(代表未被占用)
            for(Integer id : reduceList){
                paramMap.put("wms_fina_pay_id", id);
                paramMap.put("status", 0);
                paramMap.put("type", null);//1代表WMS 2代表PTP
                wmsInveDebtOlnclaimsDao.updateWmsFinaCreRepayStatus(paramMap);
            }
        }
        
	    log.debug("--------------------债权调整暂存处理结束--------------------");
	    return "success";
	}
	
	/**
	 * 获取list1比list2少的元素
	 */
	public List<Integer> getDiffList(List<Integer> list1, List<Integer> list2) {
	    List<Integer> list = new ArrayList<Integer>();
	    for(Integer a : list2){
	        if(!list1.contains(a)){
	            list.add(a);
	        }
	    }
	    return list;
	}
	
}
