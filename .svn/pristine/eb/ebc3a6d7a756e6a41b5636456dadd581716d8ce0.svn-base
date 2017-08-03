package com.zx.emanage.loanfina.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.loanfina.persist.WmsFinaCreMortgageListDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractAttDao;
import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractDao;
import com.zx.emanage.loanfina.persist.WmsFinaTerminationContractMortgageListDao;
import com.zx.emanage.loanfina.service.IWmsFinaTerminationContractService;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractAtt;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsfinaterminationcontractService")
public class WmsFinaTerminationContractServiceImpl implements IWmsFinaTerminationContractService {
	private static Logger log = LoggerFactory.getLogger(WmsFinaTerminationContractServiceImpl.class);

	@Autowired
	private WmsFinaTerminationContractDao wmsfinaterminationcontractDao;
	@Autowired
	private WmsFinaTerminationContractMortgageListDao wmsFinaTerminationContractMortgageListDao;//抵押物详情
	@Autowired
	private WmsFinaTerminationContractAttDao wmsFinaTerminationContractAttDao;//终止合同附件
	@Autowired
	private WmsFinaCreMortgageListDao wmsFinaCreMortgageListDao;//财务——还款抵押物清单wms_fina_cre_mortgage_list
    @Autowired
    private WmsFinaCreRepayDao wmsfinacrerepayDao;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmsfinaterminationcontractDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmsfinaterminationcontractDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmsfinaterminationcontractDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsFinaTerminationContract getInfoByPK(Integer wms_fina_termination_contract_id) {
		return wmsfinaterminationcontractDao.get(wms_fina_termination_contract_id);
	}

	@Override	
	@Transactional
	public String save(WmsFinaTerminationContract bean, UserBean user,WmsFinaTerminationContractSearchBeanVO beanvo) {
		String resStr = "success";
		int ret = 0;
		bean.setOperator_id(user.getUserId());//终止合同操作人ID
		bean.setCreate_datetime(new Timestamp(System.currentTimeMillis()));//创建时间
		bean.setEnable_flag("1");//'数据有效：1代表有效  0代表无效'
		ret = wmsfinaterminationcontractDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		WmsFinaCreRepay creRepay=new WmsFinaCreRepay();
		creRepay.setWms_fina_cre_pay_id(beanvo.getWms_fina_cre_pay_id());
		creRepay.setClean_up_date(new java.sql.Date(System.currentTimeMillis()));
		creRepay.setRepay_status("5");//5、存档
	    ret = wmsfinacrerepayDao.update(creRepay); 
	    if (ret == 0) {
			resStr = "error";
		}
		//抵押物详情
		List<WmsFinaTerminationContractMortgageList> list=JsonUtil.jsonArrayToList(beanvo.getWms_fina_termination_contract_mortgage_list(), WmsFinaTerminationContractMortgageList.class);
		for(WmsFinaTerminationContractMortgageList gagelist :list){
			gagelist.setWms_fina_termination_contract_id(bean.getWms_fina_termination_contract_id());
			gagelist.setEnable_flag("1");
			wmsFinaTerminationContractMortgageListDao.save(gagelist);
			WmsFinaCreMortgageList mlist=new WmsFinaCreMortgageList();
			mlist.setWms_cre_credit_head_id(beanvo.getWms_cre_credit_head_id());
			mlist.setWms_fina_cre_pay_id(beanvo.getWms_fina_cre_pay_id());
			mlist.setWms_cre_appro_id(beanvo.getWms_cre_appro_borrow_protocol_id());
			mlist.setMortgage_code(CodeNoUtil.getFinaDYCode());//抵押物编号
			if(gagelist.getCollateral_name()!=null){
				mlist.setMortgage_name(gagelist.getCollateral_name());//抵押物名称	
			}
			mlist.setMortgage_date(new java.sql.Date(System.currentTimeMillis()));//抵押时间
			if(gagelist.getCollateral_accout()!=null){
				mlist.setMortgage_count(new Integer(gagelist.getCollateral_accout().toString()));//抵押数量
			}
			if(gagelist.getCollateral_estimated_value()!=null){
				mlist.setShould_mortgage_price(gagelist.getCollateral_estimated_value());//预估价值
			}
			if(gagelist.getCollateral_actual_value()!=null){
				mlist.setReal_mortgage_price(gagelist.getCollateral_actual_value());//实际价值
			}
			if(gagelist.getCollateral_estimated_value()!=null&&gagelist.getCollateral_actual_value()!=null){
				mlist.setD_value(gagelist.getCollateral_estimated_value().subtract(gagelist.getCollateral_actual_value()));//差值
			}
			mlist.setAuditor_id(user.getUserId());//审核人ID
			mlist.setAuditor_name(user.getRealname());//审核人Name
			mlist.setAuditor_result("1");//审核结果
			mlist.setAuditor_datetime(new Timestamp(System.currentTimeMillis()));//审核时间time
			mlist.setCreate_user_id(user.getUserId());//创建人ID
			mlist.setCreate_user_name(user.getRealname());//创建name
			mlist.setLast_update_datetime(new Timestamp(System.currentTimeMillis()));//审核时间time
			mlist.setMortgage_status("1");//抵押状态
			mlist.setStrike_balance_status(1);//冲账状态
			mlist.setEnable_flag("1");//数据有效性
			wmsFinaCreMortgageListDao.save(mlist);
		}
		//抵押物详情
		List<WmsFinaTerminationContractAtt> listatt=JsonUtil.jsonArrayToList(beanvo.getFileArr(), WmsFinaTerminationContractAtt.class);
		for(WmsFinaTerminationContractAtt attlist :listatt){
			attlist.setWms_fina_termination_contract_id(bean.getWms_fina_termination_contract_id());
			attlist.setEnable_flag("1");
			attlist.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
			attlist.setCreate_user_id(user.getUserId());
			attlist.setCreate_user_name(user.getRealname());
			attlist.setData_type("0");//  `data_type` char(1) DEFAULT NULL COMMENT '默认0',
			wmsFinaTerminationContractAttDao.save(attlist);
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsFinaTerminationContract bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmsfinaterminationcontractDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsFinaTerminationContract() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsFinaTerminationContract> getListByEntity(WmsFinaTerminationContract queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsFinaTerminationContract> beanList = wmsfinaterminationcontractDao.getListByEntity(queryInfo);
		return beanList;
	}
}
