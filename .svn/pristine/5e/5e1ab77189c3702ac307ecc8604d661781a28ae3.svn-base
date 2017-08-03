package com.zx.emanage.loanappro.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditHeadDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditFinalApplDao;
import com.zx.emanage.loanappro.persist.WmsCreCreditVisaApplDao;
import com.zx.emanage.loanappro.service.IWmsCreCreditFinalApplService;
import com.zx.emanage.loanappro.vo.WmsCreCreditFinalApplSearchBeanVO;
import com.zx.emanage.loanreview.persist.WmsCreHousingTrialAssessmentDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditFinalAppl;
import com.zx.emanage.util.gen.entity.WmsCreCreditHead;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditfinalapplService")
public class WmsCreCreditFinalApplServiceImpl implements IWmsCreCreditFinalApplService {
	private static Logger log = LoggerFactory.getLogger(WmsCreCreditFinalApplServiceImpl.class);

	@Autowired
	private WmsCreCreditFinalApplDao wmscrecreditfinalapplDao;

    @Autowired
    private WmsCreCreditHeadDao wmscrecreditheadDao;

    @Autowired
    private WmsSysDictDataDao wmssysdictdataDao;

    @Autowired
    private WmsCreCreditVisaApplDao wmsCreCreditVisaApplDao;// 终审面签

    @Autowired
    private WmsCreHousingTrialAssessmentDao wmscrehousingtrialassessmentdao;// 初评表

	@Override
	public Map<String, Object> getListWithoutPaging(WmsCreCreditFinalApplSearchBeanVO queryInfo) {
	 	Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("sortname", queryInfo.getSortname());
	    paramMap.put("sortorder", queryInfo.getSortorder());
	    List<Map<String,Object>>  list = wmscrecreditfinalapplDao.search(paramMap);
	    Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("Rows",list);
		return resMap;		
	}

	@Override
	public Map<String, Object> getListWithPaging(WmsCreCreditFinalApplSearchBeanVO queryInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String,Object>> list = wmscrecreditfinalapplDao.search(paramMap); 
        GridDataBean<Map<String,Object>> bean = new 
                GridDataBean<Map<String,Object>>(queryInfo.getPage(), wmscrecreditfinalapplDao.findCount(paramMap),list);
		return bean.getGridData();			
	}

	@Override
	public WmsCreCreditFinalAppl getInfoByPK(Integer wms_cre_credit_final_appl_id) {
		return wmscrecreditfinalapplDao.get(wms_cre_credit_final_appl_id);
	}

	@Override	
	@Transactional
	public String save(WmsCreCreditFinalAppl bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditfinalapplDao.save(bean);
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}

	@Override
	@Transactional
	public String update(WmsCreCreditFinalAppl bean, UserBean user) {
		String resStr = "success";
		int ret = 0;
		ret = wmscrecreditfinalapplDao.update(bean); // update a record replace columns, nonsupport null val
		if (ret == 0) {
			resStr = "error";
		}
		return resStr;
	}	
	
	/**
	 * Description :check repeat by "and" method, union checking, need new WmsCreCreditFinalAppl() include check-params
	 * @param queryInfo
	 * @param isExludePKFlag, true is exclude primary key, false is include primary key
	 * @return "success" or "repeat"
	 * @author auto_generator
	 */
	private List<WmsCreCreditFinalAppl> getListByEntity(WmsCreCreditFinalAppl queryInfo, Boolean isExcludePKFlag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
		String resStr = "success";
		List<WmsCreCreditFinalAppl> beanList = wmscrecreditfinalapplDao.getListByEntity(queryInfo);
		return beanList;
	}

    /**
     * @Title: getWmsCusCreditFinalApplInfo
     * @Description: (查询终审信息)
     * @param wms_cre_credit_head_id
     * @return 
     * @author: baisong
     * @time:2017年2月20日 下午3:05:22
     * @see com.zx.emanage.cremanage.service.IWmsCreCreditHeadService#getWmsCusCreditFinalApplInfo(java.lang.Integer)
     * history:
     * 1、2017年2月20日 baisong 创建方法
    */
    @Override
    public Map<String, Object> getWmsCusCreditFinalApplInfo(UserBean user,Integer wms_cre_credit_head_id)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        // 获取终审审批信息
        List<Map<String,Object>> listAppl = wmscrecreditfinalapplDao.search(map);
        // 获取初评表
        List<Map<String, Object>> listAssess = wmscrehousingtrialassessmentdao.search(map);
        // 贷款主表
        WmsCreCreditHead wmscrecredithead = wmscrecreditheadDao.get(wms_cre_credit_head_id);

        if (listAppl != null && listAppl.size() > 0)
        {
            map = listAppl.get(0);
        }else{
            map.put("appro_limit", wmscrecredithead.getCredit_limit());// 贷款申请金额
            /* // 判断面签默认值
             List<String> codeList = wmssysdictdataDao.getCodeByDictId(120);// 120为数据库字典表id
             // 沈阳、长春默认为是，其他外阜默认为否。选“是”时，需先进行“面签”再流转到签合同，选“否”时则跳过面签环节直接流转至签合同。
             if (codeList.contains(wmscrecredithead.getSalesman_city_code()))
             {
                 map.put("is_visa", "1");// 是否面前
             }
             else
             {
                 map.put("is_visa", "0");// 是否面前
             }*/
        }
        if (listAssess != null && listAssess.size() > 0)
        {
            map.put("old_appro_limit", listAssess.get(0).get("old_appro_limit"));// 曾终审金额
        }
        if (wmscrecredithead != null)
        {
            map.put("is_guarantee", wmscrecredithead.getIs_guarantee());// 担保
            map.put("credit_limit", wmscrecredithead.getCredit_limit());// 贷款申请金额
        }
        return map;
    }

    /**
     * @Title: getWmsCusCreditFinalVisaInfo
     * @Description: TODO(终审面签-房贷)
     * @param user
     * @param wms_cre_credit_head_id
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午2:09:14
     * @see com.zx.emanage.loanappro.service.IWmsCreCreditFinalApplService#getWmsCusCreditFinalVisaInfo(com.zx.sframe.util.vo.UserBean, java.lang.Integer)
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    @Override
    public Map<String, Object> getWmsCusCreditFinalVisaInfo(UserBean user, Integer wms_cre_credit_head_id)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("wms_cre_credit_head_id", wms_cre_credit_head_id);
        map.put("sortname", "last_update_timestamp");
        map.put("sortorder", "desc");
        // 获取终审面签信息
        List<Map<String, Object>> listAppl = wmsCreCreditVisaApplDao.search(map);
        // 贷款主表
        WmsCreCreditHead wmscrecredithead = wmscrecreditheadDao.get(wms_cre_credit_head_id);
        String visa_remark = "";
        if (listAppl != null && listAppl.size() > 0)
        {
            map = listAppl.get(0);
            visa_remark = listAppl.get((listAppl.size() - 1)).get("visa_remark").toString();
        }
        if (wmscrecredithead != null)
        {
            map.put("appro_limit", wmscrecredithead.getAppro_limit());// 贷款终审金额
            map.put("visa_remark", visa_remark);
        }

        return map;
    }
}
