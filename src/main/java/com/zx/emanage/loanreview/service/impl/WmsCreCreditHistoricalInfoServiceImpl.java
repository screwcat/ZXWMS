package com.zx.emanage.loanreview.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.loanreview.persist.WmsCreCreditHistoricalInfoDao;
import com.zx.emanage.loanreview.service.IWmsCreCreditHistoricalInfoService;
import com.zx.emanage.loanreview.vo.WmsCreCreditHistoricalInfoSearchBeanVO;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.WmsCreCreditHistoricalInfo;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecredithistoricalinfoService")
public class WmsCreCreditHistoricalInfoServiceImpl implements IWmsCreCreditHistoricalInfoService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditHistoricalInfoServiceImpl.class);

	@Autowired
	private WmsCreCreditHistoricalInfoDao wmscrecredithistoricalinfoDao;

    @Autowired
    private IWmsCreCreditHeadService wmscrecreditheadService;

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCreditHistoricalInfoSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecredithistoricalinfoDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

    /**
     * @Title: getListWithPaging
     * @Description: 历史单据查询
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年6月19日 上午10:31:41
     * @see com.zx.emanage.loanreview.service.IWmsCreCreditHistoricalInfoService#getListWithPaging(com.zx.emanage.loanreview.vo.WmsCreCreditHistoricalInfoSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月19日 baisong 创建方法
     */
	@Override
    public Map<String, Object> getListWithPaging(WmsCreCreditHistoricalInfoSearchBeanVO queryInfo, UserBean user)
    {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 客户名称
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", "%" + queryInfo.getCustomer_name() + "%");
        }
        // 房产地址
        if (StringUtil.isNotBlank(queryInfo.getHouse_address()))
        {
            paramMap.put("house_address", "%" + queryInfo.getHouse_address() + "%");
        }
        // 业务员
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name_str()))
        {
            paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
        }
        // 申请时间从
        if (StringUtil.isNotBlank(queryInfo.getApply_date_start()))
        {
            paramMap.put("apply_date_start", queryInfo.getApply_date_start());
        }
        // 申请时间到
        if (StringUtil.isNotBlank(queryInfo.getApply_date_end()))
        {
            paramMap.put("apply_date_end", queryInfo.getApply_date_end());
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        /*// 开发模式 1为开发模式 其他为正常权限模式
            if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
            {
                paramMap.put("salesman_id", user.getUserId());// 登陆人id
                paramMap.put("menu_url", WmsHelp.MENU_URL_CSPG_LIST);
                paramMap.put("childrendept", wmscrecreditheadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }*/
        if (!"0".equals(queryInfo.getIs_need_paging()))
        {
            paramMap.put("offset", queryInfo.getOffset());
            paramMap.put("pagesize", queryInfo.getPagesize());
        }
        List<Map<String,Object>> list = wmscrecredithistoricalinfoDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecredithistoricalinfoDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditHistoricalInfo getInfoByPK(Integer wms_cre_credit_historical_info_id) {
		return wmscrecredithistoricalinfoDao.get(wms_cre_credit_historical_info_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditHistoricalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecredithistoricalinfoDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

    /**
     * 
     * @Title: update
     * @Description: 更改信息
     * @param bean
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年6月19日 下午1:20:50
     * @see com.zx.emanage.loanreview.service.IWmsCreCreditHistoricalInfoService#update(com.zx.emanage.util.gen.entity.WmsCreCreditHistoricalInfo, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月19日 baisong 创建方法
     */
	@Override
	@Transactional
	public String update(WmsCreCreditHistoricalInfo bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
        bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        bean.setLast_update_user_id(user.getUserId());
		ret = wmscrecredithistoricalinfoDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditHistoricalInfo() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditHistoricalInfo> getListByEntity(WmsCreCreditHistoricalInfo queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditHistoricalInfo> beanList = wmscrecredithistoricalinfoDao.getListByEntity(queryInfo);
		return beanList;
	}
}
