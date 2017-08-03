package com.zx.emanage.telUserLoanInfo.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.telUserLoanInfo.persist.TelUserLoanInfoDao;
import com.zx.emanage.telUserLoanInfo.service.ItelUserLoanInfoService;
import com.zx.emanage.telUserLoanInfo.vo.TelUserInfoBeanVO;




@Service("teluserloaninfoService")
public class TelUserLoanInfoServiceImpl implements ItelUserLoanInfoService {
	private static Logger log = LoggerFactory.getLogger(TelUserLoanInfoServiceImpl.class);

	@Autowired
	private TelUserLoanInfoDao teluserloaninfoDao;
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--单据具体情况
	 * @param map
	 * @return Map
	 * baisong
	 */
	@Override
	public Map<String, Object> getInfo(TelUserInfoBeanVO telUserInfoBeanVO) {
		Map<String, Object> returnmap=new HashMap<String, Object>();
		Map<String, Object> prammap=new HashMap<String, Object>();
		prammap.put("customer_name", telUserInfoBeanVO.getCustomer_name());
		prammap.put("id_card", telUserInfoBeanVO.getId_card());
		prammap.put("mobile_telephone1", telUserInfoBeanVO.getMobile_telephone1());
		List<Map<String, Object>> list=	teluserloaninfoDao.searchinfoloan(prammap);
		returnmap.put("resultval", list);
		if(list!=null&&list.size()>0){
			returnmap.put("result", "success");	
		}else{
			returnmap.put("result", "error");
		}
		return returnmap;
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--借贷金额
	 * @param map
	 * @return Map
	 * baisong
	 */
	@Override
	public Map<String, Object> searchisum(TelUserInfoBeanVO telUserInfoBeanVO) {
		Map<String, Object> returnmap=new HashMap<String, Object>();
		Map<String, Object> prammap=new HashMap<String, Object>();
		prammap.put("customer_name", telUserInfoBeanVO.getCustomer_name());
		prammap.put("id_card", telUserInfoBeanVO.getId_card());
		prammap.put("mobile_telephone1", telUserInfoBeanVO.getMobile_telephone1());
		Map<String, Object> list=	teluserloaninfoDao.searchisum(prammap);
		int number=	teluserloaninfoDao.searchnum(prammap);
		returnmap.put("map", list);
		returnmap.put("number", number);
		return returnmap;
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--还款中单据数量
	 * @param map
	 * @return Map
	 * baisong
	 */
	@Override
	public Map<String, Object> searchnum(TelUserInfoBeanVO telUserInfoBeanVO) {
		Map<String, Object> returnmap=new HashMap<String, Object>();
		Map<String, Object> prammap=new HashMap<String, Object>();
		prammap.put("customer_name", telUserInfoBeanVO.getCustomer_name());
		prammap.put("id_card", telUserInfoBeanVO.getId_card());
		prammap.put("mobile_telephone1", telUserInfoBeanVO.getMobile_telephone1());
		int number=	teluserloaninfoDao.searchnum(prammap);
		returnmap.put("result", number);
		return returnmap;
	}
	/***
	 * 根据客户名称 身份证 联系电话 查询借贷情况--期还款台账
	 * @param map
	 * @return Map
	 * baisong
	 */
	@Override
	public Map<String, Object> searchPeriodRepay(TelUserInfoBeanVO telUserInfoBeanVO) {
		Map<String, Object> returnmap=new HashMap<String, Object>();
		Map<String, Object> prammap=new HashMap<String, Object>();
		prammap.put("wms_cre_credit_head_id", telUserInfoBeanVO.getWms_cre_credit_head_id());
		List<Map<String, Object>> list=	teluserloaninfoDao.searchPeriodRepay(prammap);
		returnmap.put("result", list);
		return returnmap;
	}
}
