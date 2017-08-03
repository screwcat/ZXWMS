package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCarpHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreCarpHouseAssessmentDao;
import com.zx.emanage.loanreview.persist.WmsCreCarpHouseCheckDao;
import com.zx.emanage.loanreview.service.IWmsCreCarpHouseCheckService;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseCheck;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseCheckSearchBeanVO;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarphousecheckService")
public class WmsCreCarpHouseCheckServiceImpl implements IWmsCreCarpHouseCheckService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpHouseCheckServiceImpl.class);

	@Autowired
	private WmsCreCarpHouseCheckDao wmscrecarphousecheckDao;
	@Autowired
	private WmsCreCarpHouseAssessmentDao wmsCreCarpHouseAssessmentDao; 
	@Autowired
	private WmsCreCreditHeadDao wmscrecreditheaddao;
	@Autowired
	private WmsCreCarpHousingAttDao wmsCreCarpHousingAttDao;
	@Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;
	/**
	 * Description :get record list  车贷办件查询列表导出excel
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo, UserBean user) {
		//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
		Map<String, Object> paramMap =  carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),"4");
		if (paramMap.get("idList") == null)
		{
			paramMap.put("Rows","");
			return paramMap;
		}
		if (StringUtil.isNotBlank(queryInfo.getBill_code()))
		{
		paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
		}
		if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
		{
		paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
		}
		if (StringUtil.isNotBlank(queryInfo.getId_card()))
		{
		paramMap.put("id_card", queryInfo.getId_card());
		}
		if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
		{
		paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
		}
		paramMap.put("create_user_city_code", user.getUser_regionNumber());
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list= wmscrecarphousecheckDao.searchForCdCardForAdd(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}
	/**
	 * Description :--车贷办件--查询列表
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseCheckSearchBeanVO queryInfo, UserBean user) {
		//carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
		 Map<String, Object> paramMap = carLoanWorkFlowService.getIdListWorkFlow(String.valueOf(user.getUserId()),"4");
			if (paramMap.get("idList") == null)
			{
				return new HashMap<String, Object>();
			}
			if (StringUtil.isNotBlank(queryInfo.getBill_code()))
			{
			paramMap.put("bill_code", "%" + queryInfo.getBill_code() + "%");
			}
			if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
			{
			paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
			}
			if (StringUtil.isNotBlank(queryInfo.getId_card()))
			{
			paramMap.put("id_card", queryInfo.getId_card());
			}
			if (StringUtil.isNotBlank(queryInfo.getMobile_telephone()))
			{
			paramMap.put("mobile_telephone", "%" + queryInfo.getMobile_telephone() + "%");
			}
			paramMap.put("create_user_city_code", user.getUser_regionNumber());
			paramMap.put("sortname", queryInfo.getSortname());
			paramMap.put("sortorder", queryInfo.getSortorder());
			paramMap.put("offset", queryInfo.getOffset());
			paramMap.put("pagesize", queryInfo.getPagesize());
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			int count = 0;
			list = wmscrecarphousecheckDao.searchForCdCardForAdd(paramMap); //流程上添加操作
			list = carLoanWorkFlowService.setWorkFlowTaskID(list, (List<Integer>) paramMap.get("idList"),(List<String>) paramMap.get("taskIdList"), (List<String>)paramMap.get("approvesGroups"), (List<String>)paramMap.get("approveAdvices"), (List<String>)paramMap.get("approveTimes"));
			
			GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarphousecheckDao.findCountForCdCardForAdd(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpHouseCheck getInfoByPK(Integer wms_cre_carp_house_check_id) {
		return wmscrecarphousecheckDao.get(wms_cre_carp_house_check_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpHouseCheck bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousecheckDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpHouseCheck bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarphousecheckDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpHouseCheck() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpHouseCheck> getListByEntity(WmsCreCarpHouseCheck queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpHouseCheck> beanList = wmscrecarphousecheckDao.getListByEntity(queryInfo);
		return beanList;
	}
	/***
	 * 车贷办件保存更新
	 * baisong
	 */
	@Override
    @Transactional
    public String save(WmsCreCarpHouseCheck bean, WmsCreCarpHouseAssessment wmsCreCarpHouseAssessment, UserBean user,
    		WmsCarLoanWorkFlowVO wVo, String fileArr)
    {
        String resStr = "temOK";
        int ret = 0;
        Timestamp sysTime = new Timestamp(System.currentTimeMillis());
        List<WmsCreCarpHousingAtt> attachment = JsonUtil.jsonArrayToList(fileArr, WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
        wmscrecarphousecheckDao.deleteRecord(bean.getWms_cre_credit_head_id());
        bean.setCreate_user_id(user.getUserId()); // 传入创建人id
        bean.setCreate_user_name(user.getRealname());// 传入创建人名称
        bean.setCreate_timestamp(sysTime);
        bean.setLast_update_user_id(user.getUserId());
        bean.setLast_update_timestamp(sysTime);
        bean.setEnable_flag("1"); // 是否有效
        ret = wmscrecarphousecheckDao.save(bean);
        wmsCreCarpHouseAssessmentDao.deleteForId(wmsCreCarpHouseAssessment.getWms_cre_credit_head_id());
        wmsCreCarpHouseAssessment.setCommunity_name(wmsCreCarpHouseAssessment.getCommunity_name1());
        wmsCreCarpHouseAssessment.setHouse_address(wmsCreCarpHouseAssessment.getHouse_address1());
        wmsCreCarpHouseAssessment.setHouse_building_area(wmsCreCarpHouseAssessment.getHouse_building_area1());
        ret = wmsCreCarpHouseAssessmentDao.save(wmsCreCarpHouseAssessment);
        if (wVo.getPass() != null)
        {
            resStr = "success";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("house_appro_user_id", user.getUserId());
            params.put("house_appro_user_name", user.getRealname());
            params.put("house_appro_timestamp", sysTime);
            params.put("house_appro_advice", wVo.getAdvice());
            params.put("house_appro_result", "true".equals(wVo.getPass()) ? "1" : "0");// 当流做出来后需要修改此值为pass的值
            params.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
            wVo.setUserId(String.valueOf(user.getUserId()));
			 //给流程赋值
	        wVo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
	        wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
	        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
	         resStr =  carLoanWorkFlowService.carLoanApprovalProcess(wVo,"4");
	        if("supply".equals(resStr)){
	        	return resStr;
	        }
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
        paramsMap.put("data_type", "3");//3.代表 车贷审核-房产办件 验房信息-验房评估信息-验房照片信息附件
        wmsCreCarpHousingAttDao.deleteRecords(paramsMap);
        for (int i = 0; i < attachment.size(); i++)
        {
        	WmsCreCarpHousingAtt mplm = attachment.get(i);
            mplm.setData_type("3");//3为办件附件
            mplm.setWms_cre_credit_head_id(Integer.valueOf(wVo.getWms_cre_credit_head_id()));
            mplm.setCreate_user_id(user.getUserId()); // 创建人id
            mplm.setCreate_user_name(user.getRealname());// 创建人名字
            mplm.setCreate_timestamp(sysTime);// 创建时间
            mplm.setEnable_flag("1");// 是否有效
            wmsCreCarpHousingAttDao.save(mplm);
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }
	 @Override
    public WmsCreCarpHouseCheck getInfo(Integer wms_cre_credit_head_id)
    {
        return wmscrecarphousecheckDao.get(wms_cre_credit_head_id);
    }
}
