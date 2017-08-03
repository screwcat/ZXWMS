package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.SysWebUserDao;
import com.zx.emanage.sysmanage.service.ISysWebUserService;
import com.zx.emanage.util.gen.entity.SysWebUser;
import com.zx.emanage.sysmanage.vo.SysWebUserSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("syswebuserService")
public class SysWebUserServiceImpl implements ISysWebUserService
{
    private static Logger log = LoggerFactory.getLogger(SysWebUserServiceImpl.class);

    @Autowired
    private SysWebUserDao syswebuserDao;

    @Override
    public Map<String, Object> getListWithoutPaging(SysWebUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = syswebuserDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysWebUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = syswebuserDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       syswebuserDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    @Transactional
    public String save(SysWebUser bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = syswebuserDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysWebUser bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysWebUser() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<SysWebUser> getListByEntity(SysWebUser queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<SysWebUser> beanList = syswebuserDao.getListByEntity(queryInfo);
        return beanList;
    }
}
