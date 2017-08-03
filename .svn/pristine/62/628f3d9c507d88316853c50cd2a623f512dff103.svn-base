package com.zx.emanage.sysmanage.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCreditLineCustomerChangeHeadDao;
import com.zx.emanage.cremanage.persist.WmsCreCustomerChangeLineHouseinfoDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.WmsCreHousingOperationLogDao;
import com.zx.emanage.sysmanage.service.IWmsCreHousingOperationLogService;
import com.zx.emanage.sysmanage.vo.WmsCreHousingOperationLogSearchBeanVO;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.emanage.util.gen.entity.WmsCreHousingOperationLog;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrehousingoperationlogService")
public class WmsCreHousingOperationLogServiceImpl implements IWmsCreHousingOperationLogService {
	private static Logger log = LoggerFactory.getLogger(WmsCreHousingOperationLogServiceImpl.class);

	@Autowired
	private WmsCreHousingOperationLogDao wmscrehousingoperationlogDao;

	@Autowired
	private WmsCreCustomerChangeLineHouseinfoDao wmsCreCustomerChangeLineHouseinfoDao;

    @Autowired
    private WmsCreCreditHeadDao wmsCreCreditHeadDao;

    @Autowired
    private WmsCreCreditLineCustomerChangeHeadDao wmsCreCreditLineCustomerChangeHeadDao;

    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreHousingOperationLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		List<Map<String, Object>> list = wmscrehousingoperationlogDao.search(paramMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows", list);
		return resMap;
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreHousingOperationLogSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("pagesize", queryInfo.getPagesize());
		paramMap.put("offset", queryInfo.getOffset());
		List<Map<String, Object>> list = wmscrehousingoperationlogDao.search(paramMap);
		GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrehousingoperationlogDao.findCount(paramMap), list);
		return bean.getGridData();
	}

	@Override
	public WmsCreHousingOperationLog getInfoByPK(Integer wms_cre_housing_operation_log_id) {
		return wmscrehousingoperationlogDao.get(wms_cre_housing_operation_log_id);
	}

	@Override
	@Transactional
	public String save(WmsCreHousingOperationLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingoperationlogDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreHousingOperationLog bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrehousingoperationlogDao.update(bean); // update a record
															// replace columns,
															// nonsupport null
															// val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	/**
	 * Description :check repeat by "and" method, union checking, need new
	 * WmsCreHousingOperationLog() include check-params
	 * 
	 * @param queryInfo
	 * @param isExludePKFlag,
	 *            true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreHousingOperationLog> getListByEntity(WmsCreHousingOperationLog queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreHousingOperationLog> beanList = wmscrehousingoperationlogDao.getListByEntity(queryInfo);
		return beanList;
	}

	/**
	 * 
	 * @Title: ClaimOperUp
	 * @Description: 发送房产核查领用状态
	 * @param bean
	 * @return
	 * @author: ZhangWei
	 * @time:2017年6月1日 下午1:09:18
	 * @see com.zx.emanage.cremanage.service.IWmsCreCreditHeadService#ClaimOperUp(com.zx.emanage.cremanage.vo.WmsMoaHouseInfoVO)
	 *      history: 1、2017年6月1日 ZhangWei 创建方法
	 */
	public Map<String, Object> ClaimOperUp(WmsCreHousingOperationLog bean) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if ("1".equals(bean.getOperation_type()) && wmsCreCustomerChangeLineHouseinfoDao.checkIsClaim(bean.getWms_cre_credit_head_id()) == 1) {
			resMap.put("ret_code", "100");
			resMap.put("ret_msg", "该单据已经被其他用户认领。");
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());// 贷款主表id
			if ("1".equals(bean.getOperation_type())) { // 认领
				paramMap.put("is_claim", "1"); // 核查是否认领 1是 0否
				paramMap.put("claim_user_id", bean.getOperation_user_id());// 操作人id
			} else { // 撤销
				paramMap.put("is_claim", "0"); // 核查是否认领 1是 0否
				paramMap.put("claim_user_id", null);// 操作人id
				WmsCreCreditHead wmsCreCreditHead = wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
				wmsCreCreditHead.setHouse_appro_user_id(null);
				wmsCreCreditHeadDao.updateApproUserId(wmsCreCreditHead);
			}
			wmsCreCustomerChangeLineHouseinfoDao.ClaimOperUp(paramMap);
			WmsCreHousingOperationLog wmsCreHousingOperationLog = new WmsCreHousingOperationLog();
			wmsCreHousingOperationLog.setOperation_module("1");
			wmsCreHousingOperationLog.setOperation_type(bean.getOperation_type());
			wmsCreHousingOperationLog.setOperation_reason(bean.getOperation_reason());// 操作原因
			Date date = new Date();
			wmsCreHousingOperationLog.setOperation_time(new Timestamp(date.getTime()));// 操作时间
			wmsCreHousingOperationLog.setOperation_user_id(bean.getOperation_user_id());// 操作人id
			wmsCreHousingOperationLog.setOperation_user_deptid(bean.getOperation_user_deptid());// 操作人部门id
			wmsCreHousingOperationLog.setEnable_flag("1");// 数据状态1有效0无效
			wmsCreHousingOperationLog.setWms_cre_credit_head_id(bean.getWms_cre_credit_head_id());// 贷款主表id
			wmscrehousingoperationlogDao.save(wmsCreHousingOperationLog);
			resMap.put("ret_code", "000");
			resMap.put("ret_msg", "请求成功！");
		}
        // operation_type'操作类型 1认领 2撤销',
        if ("2".equals(bean.getOperation_type()))
        {
            // 调用发送信息的接口出现异常 不会影响正常流程--业务部
            try
            {
                // 并发校验(单据状态)
                WmsCreCreditHead wmsCreCreditHead = this.wmsCreCreditHeadDao.get(bean.getWms_cre_credit_head_id());
                // 查询客户姓名
                Map<String, Object> customerChangeParamMap = new HashMap<String, Object>();
                customerChangeParamMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
                customerChangeParamMap.put("is_major", "1");
                customerChangeParamMap.put("enable_flag", "1");
                List<Map<String, Object>> customerChangeList = this.wmsCreCreditLineCustomerChangeHeadDao.search(customerChangeParamMap);
                PmPersonnel pmPersonnel = new PmPersonnel();
                pmPersonnel.setPersonnel_id(bean.getOperation_user_id());
                List<PmPersonnel> listp = pmPersonnelDao.getListByEntity(pmPersonnel);
                pmPersonnel = listp.get(0);
                // 调用方法map
                Map<String, Object> sendMap = new HashMap<String, Object>();
                // 参数map
                Map<String, String> paramMap = new HashMap<String, String>();
                // 参数map
                Map<String, String> msg_extras = new HashMap<String, String>();

                sendMap.put("user_id", pmPersonnel.getPersonnel_id());
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());

                // 消息中心使用
                sendMap.put("personnel_id", pmPersonnel.getPersonnel_id().toString());
                sendMap.put("personnel_shortCode", pmPersonnel.getPersonnel_shortcode());
                sendMap.put("personnel_name", pmPersonnel.getPersonnel_name());

                // 极光推送需要的数据参数
                sendMap.put("quart_message", "1");// 消息附加参数
                paramMap.put("salesman_name_code", pmPersonnel.getPersonnel_name() + pmPersonnel.getPersonnel_shortcode());
                paramMap.put("advice", bean.getOperation_reason());// 审核意见
                msg_extras.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());// 添加参数
                sendMap.put("msg_extras", msg_extras);// 消息附加参数
                // 如果是消息中心
                sendMap.put("message_center", "1");
                // 提交人
                paramMap.put("bill_code", wmsCreCreditHead.getBill_code());
                paramMap.put("customer_name", customerChangeList.get(0).get("customer_name").toString());
                paramMap.put("wms_cre_credit_head_id", wmsCreCreditHead.getWms_cre_credit_head_id().toString());
                paramMap.put("bill_status", wmsCreCreditHead.getBill_status());
                paramMap.put("create_user_id", pmPersonnel.getPersonnel_id().toString());
                paramMap.put("create_user_name", pmPersonnel.getPersonnel_name());
                paramMap.put("city", wmsCreCreditHead.getCity());
                paramMap.put("app_name", WmsHelp.APP_NAME_MIF);
                sendMap.put("msg_map", paramMap);// 极光推送的消息内容参数
                sendMap.put("role_value", "1");// 判断获取人
                sendMap.put("role_name", WmsHelp.FCHC_ZG_ROLE_NAME);// 判断获取人
                sendMap.put("msg_code", "20013");// 消息编码
                sendMap.put("msg_code_role", "20013");// 消息编码
                sendMap.put("app_name", WmsHelp.APP_NAME_MIF);
                // 线程发送消息
                this.wmsCreCreditHeadService.sendInfoAsynchronous(sendMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

		return resMap;
	}
}
