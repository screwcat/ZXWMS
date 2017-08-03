package com.zx.emanage.loanreview.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCarpApproInfoDao;
import com.zx.emanage.cremanage.persist.WmsCreCarpHousingAttDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.service.IWmsCarLoanWorkFlowService;
import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.loanreview.persist.WmsCreCarpAssessmentDao;
import com.zx.emanage.loanreview.service.IWmsCreCarpAssessmentService;
import com.zx.emanage.sysmanage.service.IWmsSysDictDataService;
import com.zx.emanage.util.gen.entity.WmsCreCarpApproInfo;
import com.zx.emanage.util.gen.entity.WmsCreCarpAssessment;
import com.zx.emanage.util.gen.entity.WmsCreCarpHousingAtt;
import com.zx.emanage.loanreview.vo.WmsCreCarpAssessmentSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecarpassessmentService")
public class WmsCreCarpAssessmentServiceImpl implements IWmsCreCarpAssessmentService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCarpAssessmentServiceImpl.class);

	@Autowired
	private WmsCreCarpAssessmentDao wmscrecarpassessmentDao;
	@Autowired
	private WmsCreCarpHousingAttDao wmsCreCarpHousingAttDao;
	@Autowired
	private WmsCreCreditHeadDao wmscrecreditheaddao;
	@Autowired
	private IWmsSysDictDataService iWmsSysDictDataService;
	@Autowired
	private IWmsCarLoanWorkFlowService carLoanWorkFlowService;//流程
	@Autowired
	private WmsCreCarpApproInfoDao wmscrecarpapproinfoDao;
	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecarpassessmentDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecarpassessmentDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecarpassessmentDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCarpAssessment getInfoByPK(Integer wms_cre_carp_assessment_id) {
		return wmscrecarpassessmentDao.get(wms_cre_carp_assessment_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCarpAssessment bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpassessmentDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCarpAssessment bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecarpassessmentDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCarpAssessment() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCarpAssessment> getListByEntity(WmsCreCarpAssessment queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCarpAssessment> beanList = wmscrecarpassessmentDao.getListByEntity(queryInfo);
		return beanList;
	}
	/**
	 * Description :add method--评估单保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	@Override	
	@Transactional
	public String saveUpdate(WmsCreCarpAssessment bean, UserBean user,WmsCreCarpAssessmentSearchBeanVO beanvo) {
		String resStr = "success";
		int ret = 0;
        if (beanvo.getPass() != null)
        {
            resStr = "success";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("assessment_id", user.getUserId());
            params.put("assessment_datetime", new Timestamp(System.currentTimeMillis()));
            params.put("assessment_advice", beanvo.getAdvice());
            params.put("assessment_result", "true".equals(beanvo.getPass()) ? "1" : "0");// 当流做出来后需要修改此值为pass的值
            params.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            wmscrecreditheaddao.updateRecord(params);
        }
            
		List<WmsCreCarpHousingAtt> attachment = JsonUtil.jsonArrayToList(beanvo.getFileArr(), WmsCreCarpHousingAtt.class); // 前台json附件路径数据转换为list
		if(bean.getWms_cre_carp_assessment_id()!=null&&!"".equals(bean.getWms_cre_carp_assessment_id())){//如果有主键则更新
			bean.setLast_update_user_id(user.getUserId());
			bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
			ret = wmscrecarpassessmentDao.update(bean);
			if (ret == 0) {
				resStr = "error";
			}
		}else{
			bean.setCreate_user_id(user.getUserId());
			bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
			bean.setLast_update_user_id(user.getUserId());
			bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
			bean.setEnable_flag("1");
			ret = wmscrecarpassessmentDao.save(bean);
			if (ret == 0) {
				resStr = "error";
			}
		}
		 Map<String, Object> paramsMap = new HashMap<>();
	        paramsMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
	        paramsMap.put("data_type", "4");//3.代表 车贷审核-房产办件 验房信息-验房评估信息-验房照片信息附件
	        wmsCreCarpHousingAttDao.deleteRecords(paramsMap);
	        for (int i = 0; i < attachment.size(); i++)
	        {
	        	WmsCreCarpHousingAtt mplm = attachment.get(i);
	            mplm.setData_type("4");// 4.代表 车贷审核-评估审核-车产评估-评估单-车验照片信息附件
	            mplm.setWms_cre_credit_head_id(Integer.valueOf(bean.getWms_cre_credit_head_id()));
	            mplm.setCreate_user_id(user.getUserId()); // 创建人id
	            mplm.setCreate_user_name(user.getRealname());// 创建人名字
	            mplm.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));// 创建时间
	            mplm.setEnable_flag("1");// 是否有效
	            wmsCreCarpHousingAttDao.save(mplm);
	        }
	        if("1".equals(beanvo.getSaveVal())){//提交
				 WmsCarLoanWorkFlowVO wVo=new WmsCarLoanWorkFlowVO();//流程
				 //给流程赋值
		         wVo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		         wVo.setAdvice(beanvo.getAdvice());//意见
		         wVo.setUserId(String.valueOf(user.getUserId()));//操作人id
		         wVo.setTaskId(beanvo.getTaskId());//流程节点id
		         if("true".equals(beanvo.getPass())&&"true".equals(beanvo.getInspection_pass())){//
		        	 wVo.setPass("pgbj");////待贷前审核
		         }else if("true".equals(beanvo.getPass())&&"false".equals(beanvo.getInspection_pass())){
		        	 wVo.setPass("pgzs");////待贷前审核
		         }else if("false".equals(beanvo.getPass())){
		        	 wVo.setPass("pgno");////单据终止
		         }
		        //carkey: 1:车贷复核  2:复核退回  3:评估审核  4:验点审核  5:贷前退件 6:终审审批  7:合同签订  8:放款申请  9:放款审批 10:放款确认
		         carLoanWorkFlowService.carLoanApprovalProcess(wVo,"3");
		        
		        //车贷——审核环节结果意见存储wms_cre_carp_appro_info
		        Timestamp system =new Timestamp(System.currentTimeMillis());
		        WmsCreCarpApproInfo  wmscrecarpapproinfo=new WmsCreCarpApproInfo();
		        wmscrecarpapproinfo.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());
		        wmscrecarpapproinfo.setCarp_appro_type(1);//车贷审核环节1为评估2为办件3为终审
		        List<WmsCreCarpApproInfo> list=wmscrecarpapproinfoDao.getListByEntity(wmscrecarpapproinfo);
		        wmscrecarpapproinfo.setCarp_appro_type(1);//车贷审核环节1为评估2为办件3为终审
		        wmscrecarpapproinfo.setIs_yd("true".equals(beanvo.getInspection_pass())?1:0);//是否验点
		        wmscrecarpapproinfo.setCarp_appro_pass("true".equals(beanvo.getPass())?1:0);//审核结果
		        wmscrecarpapproinfo.setCarp_appro_advice(beanvo.getAdvice());//审核意见
		        wmscrecarpapproinfo.setEnable_flag("1");
		        if(list!=null&&list.size()>0){
		        	wmscrecarpapproinfo.setLast_update_user_id(user.getUserId());
			        wmscrecarpapproinfo.setLast_update_timestamp(system);
		        	wmscrecarpapproinfo.setWms_cre_carp_appro_info_id(list.get(0).getWms_cre_carp_appro_info_id());
		        	wmscrecarpapproinfoDao.update(wmscrecarpapproinfo);
		        }else{
			        wmscrecarpapproinfo.setCreate_user_id(user.getUserId());//创建人
			        wmscrecarpapproinfo.setCreate_timestamp(system);//创建人
			     	wmscrecarpapproinfo.setLast_update_user_id(user.getUserId());
			        wmscrecarpapproinfo.setLast_update_timestamp(system);
		        	wmscrecarpapproinfoDao.save(wmscrecarpapproinfo);
		        }
	        }
		return resStr;
	}
	/**
	 * Description :get single-table information by primary key --获取数据评估单
	 * @param primary key 
	 * @return WmsCreCarpAssessmentVO
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id) {
		Map<String, Object> map =	wmscrecarpassessmentDao.getByHK(wms_cre_credit_head_id);
		map.put("vi_type_info_val", map.get("vi_type_info").toString());//转换保险信息
		map.put("vi_type_info", iWmsSysDictDataService.getValMeaningBycodes(74, map.get("vi_type_info").toString()));//转换保险信息
		return map;
	}
}
