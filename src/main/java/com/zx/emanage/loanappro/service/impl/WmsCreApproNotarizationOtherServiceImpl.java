package com.zx.emanage.loanappro.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.typeconverter.Convert;
import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.creditRightManager.service.WmsInveAutoCreditPackageService;
import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.loanappro.persist.WmsCreNotarizationOtherDao;
import com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService;
import com.zx.emanage.loanappro.vo.WmsCreNotarizationOtherSearchBeanVO;
import com.zx.emanage.sysmanage.persist.SysPushErrorInfoDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.SysPushErrorInfo;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2017，卓信金控 系统名称：财富管理平台
 * 
 * @ClassName: WmsCreApproNotarizationOtherServiceImpl 模块名称：
 * @Description: 内容摘要：
 * @author ZhangWei
 * @date 2017年7月6日
 * @version V1.0 history: 1、2017年7月6日 ZhangWei 创建文件
 */
@Service("wmsCreApproNotarizationOtherService")
public class WmsCreApproNotarizationOtherServiceImpl implements IWmsCreApproNotarizationOtherService {

	@Autowired
	private WmsCreNotarizationOtherDao wmsCreNotarizationOtherDao;

	@Autowired
	private IWmsCreCreditHeadService wmsCreCreditHeadService;

	@Autowired
	private SysPushErrorInfoDao sysPushErrorInfoDao;
	
	@Autowired
    private WmsInveAutoCreditPackageService wmsInveAutoCreditPackageServiceImpl;

	/**
	 * @Title: getNotarizationOtherListWithPaging
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param queryInfo
	 * @param user
	 * @return
	 * @author: ZhangWei
	 * @time:2017年7月6日 下午4:20:19
	 * @see com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService#getNotarizationOtherListWithPaging(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO,
	 *      com.zx.sframe.util.vo.UserBean) history: 1、2017年7月6日 ZhangWei 创建方法
	 */
	@Override
	public Map<String, Object> getNotarizationOtherListWithPaging(WmsCreNotarizationOtherSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<>();

		// 客户姓名
		if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
			paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
		}
		// 单据编号
		if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
			paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
		}
		// 业务员/工号
		if (StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
			paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
			paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
		}
		// 申请时间区间
		if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
			paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
		}
		if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
			paramMap.put("create_timestamp_end", Convert.toDate(new StringBuffer(queryInfo.getCreate_timestamp_end()).append(" 23:59:59.9")));
		}
		// 办件状态
		if (StringUtil.isNotBlank(queryInfo.getOffice_status())) {
			paramMap.put("office_status", queryInfo.getOffice_status());
			switch (queryInfo.getOffice_status()) {
			case "0": {
				paramMap.put("notary_is_finish", 0);
				paramMap.put("he_is_finish", 0);
				break;
			}
			case "1": {
				paramMap.put("notary_is_finish", 0);
				paramMap.put("he_is_finish", 1);
				break;
			}
			case "2": {
				paramMap.put("notary_is_finish", 1);
				paramMap.put("he_is_finish", 0);
				break;
			}
			default: {
				paramMap.put("notary_is_finish", 1);
				paramMap.put("he_is_finish", 1);
				break;
			}
			}

		}
		// 抵押状态
		if (StringUtil.isNotBlank(queryInfo.getMort_flag())) {
			paramMap.put("mort_flag", queryInfo.getMort_flag());
		}

		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());
		paramMap.put("offset", queryInfo.getOffset());
		paramMap.put("pagesize", queryInfo.getPagesize());

		// 开发模式 1为开发模式 其他为正常权限模式
		if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode"))) {
			paramMap.put("salesman_id", user.getUserId());// 登陆人id
			paramMap.put("menu_url", WmsHelp.MENU_URL_GZTX_LIST);
			paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
		}
		paramMap.put("SystemStartDate", WmsHelp.SYSTEM_START_DATE);

		List<Map<String, Object>> list = wmsCreNotarizationOtherDao.getNotarizationOtherListWithPaging(paramMap);
		GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsCreNotarizationOtherDao.getNotarizationOtherCount(paramMap), list);
		return bean.getGridData();
	}

	/**
	 * @Title: getNotarizationOtherListWithoutPaging
	 * @Description: 导出excel
	 * @param queryInfo
	 * @param user
	 * @return
	 * @author: ZhangWei
	 * @time:2017年7月10日 上午10:48:08
	 * @see com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService#getNotarizationOtherListWithoutPaging(com.zx.emanage.loanappro.vo.WmsCreNotarizationOtherSearchBeanVO,
	 *      com.zx.sframe.util.vo.UserBean) history: 1、2017年7月10日 ZhangWei 创建方法
	 */
	@Override
	public Map<String, Object> getNotarizationOtherListWithoutPaging(WmsCreNotarizationOtherSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<>();

		// 客户姓名
		if (StringUtil.isNotBlank(queryInfo.getCustomer_name())) {
			paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
		}
		// 单据编号
		if (StringUtil.isNotBlank(queryInfo.getBill_code())) {
			paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
		}
		// 业务员/工号
		if (StringUtil.isNotBlank(queryInfo.getSalesman_name())) {
			paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
			paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
		}
		// 申请时间区间
		if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_begin())) {
			paramMap.put("create_timestamp_begin", Convert.toDate(queryInfo.getCreate_timestamp_begin()));
			;
		}
		if (StringUtil.isNotBlank(queryInfo.getCreate_timestamp_end())) {
			paramMap.put("create_timestamp_end", Convert.toDate(new StringBuffer(queryInfo.getCreate_timestamp_end()).append(" 23:59:59.9")));
		}
		// 办件状态
		if (StringUtil.isNotBlank(queryInfo.getOffice_status())) {
			paramMap.put("office_status", queryInfo.getOffice_status());
			switch (queryInfo.getOffice_status()) {
			case "0": {
				paramMap.put("notary_is_finish", 0);
				paramMap.put("he_is_finish", 0);
				break;
			}
			case "1": {
				paramMap.put("notary_is_finish", 0);
				paramMap.put("he_is_finish", 1);
				break;
			}
			case "2": {
				paramMap.put("notary_is_finish", 1);
				paramMap.put("he_is_finish", 0);
				break;
			}
			default: {
				paramMap.put("notary_is_finish", 1);
				paramMap.put("he_is_finish", 1);
				break;
			}
			}

		}
		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());

		// 开发模式 1为开发模式 其他为正常权限模式
		if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode"))) {
			paramMap.put("salesman_id", user.getUserId());// 登陆人id
			paramMap.put("menu_url", WmsHelp.MENU_URL_GZTX_LIST);
			paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
		}
		paramMap.put("SystemStartDate", WmsHelp.SYSTEM_START_DATE);

		paramMap.put("Rows", wmsCreNotarizationOtherDao.getNotarizationOtherListWithoutPaging(paramMap));

		return paramMap;
	}

	/**
	 * @Title: notaryConfirm
	 * @Description: 公证确认
	 * @param queryInfo
	 * @param user
	 * @return
	 * @author: ZhangWei
	 * @time:2017年7月7日 下午2:43:48
	 * @see com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService#notaryConfirm(com.zx.emanage.loanappro.vo.WmsCreNotarizationOtherSearchBeanVO,
	 *      com.zx.sframe.util.vo.UserBean) history: 1、2017年7月7日 ZhangWei 创建方法
	 */
	@Override
	@Transactional
	public String updateNotarizationOther(WmsCreNotarizationOtherSearchBeanVO queryInfo, UserBean user) {

		String resStr = "success";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		int ret = 0;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());// 主表ID
		if (StringUtil.isNotBlank(queryInfo.getNotary_is_finish())) {// 公证是否完成
			paramMap.put("notary_is_finish", queryInfo.getNotary_is_finish());
			paramMap.put("notary_is_finish_time", sdf.format(new Date()));// 公证完成时间
		}
		if (StringUtil.isNotBlank(queryInfo.getHe_is_finish())) {// 他项是否完成
			paramMap.put("he_is_finish", queryInfo.getHe_is_finish());
			paramMap.put("he_is_finish_time", sdf.format(new Date())); // 他项完成时间
		}
		if (StringUtil.isNotBlank(queryInfo.getHe_is_amount())) {// 他项证金额
			paramMap.put("he_is_amount", queryInfo.getHe_is_amount());
		}
		ret = wmsCreNotarizationOtherDao.updateNotarizationOther(paramMap);
		if (ret == 0) {
			resStr = "error";
		}
		paramMap.clear();
		// 推送债权包信息
		paramMap.put("wms_cre_credit_head_id", queryInfo.getWms_cre_credit_head_id());
		paramMap.put("type_code", WmsHelp.ERROR_TYPE_CLAIMS_PACKAGE_SYS);
		List<Map<String, Object>> list = wmsCreNotarizationOtherDao.getClaimsPackageInfo(paramMap);

		// 判断是否为空
		if (list != null && list.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 调用理财债权处理方法
			map = wmsInveAutoCreditPackageServiceImpl.saveNotSetCreditPackageInfo(list, user);
			if (map != null) {
				/*
				 * map的key如果是000 代表保存数据成功 map的key如果是001 代表数据异常 map的key如果是002
				 * 代表系统异常
				 */
				String message1 = "001";
				String message2 = "002";
				// 更新掉原纪录--系统异常
				SysPushErrorInfo sysPushErrorInfosys = new SysPushErrorInfo();
				sysPushErrorInfosys.setType_code(WmsHelp.ERROR_TYPE_CLAIMS_PACKAGE_SYS);
				sysPushErrorInfosys.setEnable_flag("0");
				// 更新掉上次系统异常数据
				sysPushErrorInfoDao.update(sysPushErrorInfosys);
                if (map.get(message2) != null && !"".equals(map.get(message2)))
                {
					sysPushErrorInfosys.setType_remark(WmsHelp.ERROR_TYPE_CLAIMS_PACKAGE_SYS_INFO);
					sysPushErrorInfosys.setEnable_flag("1");
					sysPushErrorInfosys.setError_value(map.get(message2).toString());
					sysPushErrorInfosys.setLast_update_user_id(user.getUserId());
					sysPushErrorInfosys.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
					sysPushErrorInfoDao.save(sysPushErrorInfosys);
				}
				if (map.get(message1) != null && !"".equals(map.get(message1))) {
					// 更新掉原纪录--数据异常
					SysPushErrorInfo sysPushErrorInfodata = new SysPushErrorInfo();
					sysPushErrorInfodata.setType_code(WmsHelp.ERROR_TYPE_CLAIMS_PACKAGE_DATA);
					sysPushErrorInfodata.setType_remark(WmsHelp.ERROR_TYPE_CLAIMS_PACKAGE_DATA_INFO);
					sysPushErrorInfodata.setEnable_flag("1");
					sysPushErrorInfodata.setError_value(map.get(message1).toString());
					sysPushErrorInfodata.setLast_update_user_id(user.getUserId());
					sysPushErrorInfodata.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
					sysPushErrorInfoDao.save(sysPushErrorInfodata);
				}
			}
		}

		return resStr;
	}

	/**
	 * @Title: GetFinanceLoanAppInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param wms_cre_credit_head_id
	 * @return
	 * @author: ZhangWei
	 * @time:2017年7月10日 下午1:12:45
	 * @see com.zx.emanage.loanappro.service.IWmsCreApproNotarizationOtherService#GetFinanceLoanAppInfo(java.lang.Integer)
	 *      history: 1、2017年7月10日 ZhangWei 创建方法
	 */
	@Override
	public Map<String, Object> GetFinanceLoanAppInfo(Integer wms_cre_credit_head_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
		List<Map<String, Object>> listAssess = wmsCreNotarizationOtherDao.GetFinanceLoanAppInfo(map);
		if (listAssess != null && listAssess.size() > 0) {
			map.put("notary_is_finish", listAssess.get(0).get("notary_is_finish"));
			map.put("he_is_finish", listAssess.get(0).get("he_is_finish"));
			map.put("he_is_amount", listAssess.get(0).get("he_is_amount"));
		}
		return map;
	}

}
