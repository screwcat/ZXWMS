package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInvePruductReturnDao;
import com.zx.emanage.inve.service.IWmsInvePruductReturnService;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;
import com.zx.emanage.inve.vo.WmsInvePruductReturnSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvepruductreturnService")
public class WmsInvePruductReturnServiceImpl implements IWmsInvePruductReturnService
{
    private static Logger log = LoggerFactory.getLogger(WmsInvePruductReturnServiceImpl.class);

    @Autowired
    private WmsInvePruductReturnDao wmsinvepruductreturnDao;

    @Override
    public Map<String, Object> getListWithoutPaging(WmsInvePruductReturnSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmsinvepruductreturnDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(WmsInvePruductReturnSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmsinvepruductreturnDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsinvepruductreturnDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public WmsInvePruductReturn getInfoByPK(Integer wms_inve_pruduct_return_id)
    {
        return wmsinvepruductreturnDao.get(wms_inve_pruduct_return_id);
    }

    @Override
    @Transactional
    public String save(WmsInvePruductReturn bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductreturnDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(WmsInvePruductReturn bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = wmsinvepruductreturnDao.update(bean); // update a record replace
                                                    // columns, nonsupport null
                                                    // val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * WmsInvePruductReturn() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<WmsInvePruductReturn> getListByEntity(WmsInvePruductReturn queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<WmsInvePruductReturn> beanList = wmsinvepruductreturnDao.getListByEntity(queryInfo);
        return beanList;
    }

    /**
     * Description :理财产品 期限表
     * 
     * @param queryInfo
     * @return Map
     * @author baisong
     */
    @Override
    public Map<String, Object> getListforlc(Integer wms_inve_pruduct_category_id)
    {
        List<Map<String, Object>> list = wmsinvepruductreturnDao.getListforlc(wms_inve_pruduct_category_id);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if(list.size()==0){
	    	Map<String, Object> resp = new HashMap<String, Object>();
	    	resp.put("deadline_begin_date", "");
	    	resp.put("deadline_end_date", "");
	    	resp.put("management_fee", "");
	    	resp.put("product_interest", "");
	    	list.add(resp);
	    }
        resMap.put("Rows", list);
        return resMap;
    }
}
