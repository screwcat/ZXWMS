package com.zx.emanage.remind.service.impl;



import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.remind.persist.WmsCreCreditNotarySetDao;
import com.zx.emanage.remind.service.IWmsCreCreditNotarySetService;
import com.zx.emanage.remind.vo.WmsCreCreditNotarySetSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotarySet;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @ClassName: WmsCreCreditNotarySetServiceImpl
 * @Description: 内容摘要：公证提醒设置表
 * @author baisong
 * @date 2016年11月22日
 * @version V1.0
 * history:
 * 1、2016年11月22日 baisong 创建文件
 */

@Service("wmsCreCreditNotarySetService")
public class WmsCreCreditNotarySetServiceImpl implements IWmsCreCreditNotarySetService {
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditNotarySetServiceImpl.class);

    @Autowired
    private WmsCreCreditNotarySetDao wmscrecreditnotarysetDao;

    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditnotarysetDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditnotarysetDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = 
            new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarysetDao.findCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotarySet
     * @author administrator
     */
    @Override
    public WmsCreCreditNotarySet getInfoByPK(Integer wms_cre_credit_notary_set_id) {
        return wmscrecreditnotarysetDao.get(wms_cre_credit_notary_set_id);
    }

    /**
     * Description :新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @Override
    @Transactional
    public String save(WmsCreCreditNotarySet bean, UserBean user) {
    	WmsCreCreditNotarySet updateBean = new WmsCreCreditNotarySet();
    	updateBean.setEnable_flag("0");
    	wmscrecreditnotarysetDao.updateEnable(updateBean);
        String resStr = "success";
        int ret = 0;
        bean.setEnable_flag("1");
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        ret = wmscrecreditnotarysetDao.save(bean);
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description : 修改
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @Override
    @Transactional
    public String update(WmsCreCreditNotarySet bean, UserBean user) {
        String resStr = "success";
        int ret = 0;
        ret = wmscrecreditnotarysetDao.update(bean); // update a record replace columns,
                                                   // nonsupport null val
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }

	@Override
	public WmsCreCreditNotarySet getWmsCreCreditNotarySetInfo() {
		return wmscrecreditnotarysetDao.getWmsCreCreditNotarySetInfo();
	}
}
