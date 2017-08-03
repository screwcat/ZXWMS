package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.sysmanage.persist.ISysMenuDao;
import com.zx.emanage.sysmanage.service.ISysMenuService;
import com.zx.emanage.sysmanage.vo.SysMenuSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysMenu;
import com.zx.emanage.util.gen.vo.SysMenuVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysmenuService")
public class SysMenuServiceImpl implements ISysMenuService
{
    private static Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private ISysMenuDao sysmenuDao;

    @Override
    public List<Map<String, Object>> getComboxList(SysMenuSearchBeanVO queryInfo)
    {
        return sysmenuDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysMenuSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", sysmenuDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysMenuSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysmenuDao.getListCountNum(queryInfo),
                                             sysmenuDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public SysMenuVO getInfoByPK(Integer id)
    {
        return sysmenuDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(SysMenu bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysmenuDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysMenu bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysmenuDao.updateRecordAll(bean); // update a record replace all,
                                                // support null val
        // ret = sysmenuDao.updateRecordCols(bean); // update a record replace
        // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(SysMenu bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = sysmenuDao.deleteRecordByDomain(bean); // nonsupport null val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysMenu() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysMenu queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getId() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<SysMenu> beanList = sysmenuDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysMenu() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysMenu queryInfo, Boolean isExcludePKFlag)
    {
        if (isExcludePKFlag)
        {
            Boolean setAllPk = true;
            if (queryInfo.getId() == null)
            {
                setAllPk = false;
            }
            if (!setAllPk)
            {
                log.info("**************please set pk into the queryInfo.**************");
                return "error";
            }
        }
        String resStr = "success";
        List<SysMenu> beanList = sysmenuDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
