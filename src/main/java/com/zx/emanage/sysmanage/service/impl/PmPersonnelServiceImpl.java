package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.service.IPmPersonnelService;
import com.zx.emanage.sysmanage.util.UserCityUtil;
import com.zx.emanage.sysmanage.vo.PmPersonnelSearchBeanVO;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("pmpersonnelService")
public class PmPersonnelServiceImpl implements IPmPersonnelService
{
    private static Logger log = LoggerFactory.getLogger(PmPersonnelServiceImpl.class);

    @Autowired
    private PmPersonnelDao pmpersonnelDao;

    @Override
    public Map<String, Object> getListWithoutPaging(PmPersonnelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = pmpersonnelDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(PmPersonnelSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = pmpersonnelDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       pmpersonnelDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public PmPersonnel getInfoByPK(Integer personnel_id)
    {
        return pmpersonnelDao.get(personnel_id);
    }

    @Override
    @Transactional
    public String save(PmPersonnel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = pmpersonnelDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(PmPersonnel bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = pmpersonnelDao.update(bean); // update a record replace columns,
                                           // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * PmPersonnel() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    @Override
    public Map<String, Object> getListByEntity(PmPersonnel queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String resStr = "success";
        List<Map<String, Object>> beanList = pmpersonnelDao.getListByEntity2(queryInfo);
        //新增城市编码替换
        for(Map<String, Object> map : beanList) {
        	map.put("city", UserCityUtil.getUserCity(map.get("personnel_regionnumber").toString()));
        }
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", beanList);
        return resMap;
    }
    
    /**
     * 根据人员id集合查询人员
     */
	@Override
	public List<Map<String, Object>> getPmPersonnelByIds(String personnelIds) {
		
		List<Integer> list = JsonUtil.jsonArrayToList(personnelIds, Integer.class);
		
		return pmpersonnelDao.getPmPersonnelByIds(list);
	}

    /**
     * 
     * @Title: getPmPersonnelByShortcode
     * @Description: 通过短工号查询人员
     * @param personnelIds
     * @return 
     * @author: Administrator
     * @time:2016年12月9日 下午5:12:59
     * history:
     * 1、2016年12月9日 Administrator 创建方法
     */
    @Override
    public List<PmPersonnel> getPmPersonnelListByEntity(PmPersonnel pmPersonnel)
    {
        return pmpersonnelDao.getListByEntity(pmPersonnel);
    }
}
