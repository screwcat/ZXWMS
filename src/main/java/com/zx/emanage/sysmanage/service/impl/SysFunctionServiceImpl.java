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
import com.zx.emanage.sysmanage.persist.ISysFunctionDao;
import com.zx.emanage.sysmanage.service.ISysFunctionService;
import com.zx.emanage.sysmanage.vo.SysFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysFunction;
import com.zx.emanage.util.gen.vo.SysFunctionVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysfunctionService")
public class SysFunctionServiceImpl implements ISysFunctionService
{
    private static Logger log = LoggerFactory.getLogger(SysFunctionServiceImpl.class);

    @Autowired
    private ISysFunctionDao sysfunctionDao;

    @Override
    public List<Map<String, Object>> getComboxList(SysFunctionSearchBeanVO queryInfo)
    {
        return sysfunctionDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", sysfunctionDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysFunctionSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysfunctionDao.getListCountNum(queryInfo),
                                             sysfunctionDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public SysFunctionVO getInfoByPK(Integer id)
    {
        return sysfunctionDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(SysFunction bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysfunctionDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysFunction bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysfunctionDao.updateRecordAll(bean); // update a record replace
                                                    // all, support null val
        // ret = sysfunctionDao.updateRecordCols(bean); // update a record
        // replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(SysFunction bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = sysfunctionDao.deleteRecordByDomain(bean); // nonsupport null
                                                             // val
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
     * SysFunction() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysFunction queryInfo, Boolean isExcludePKFlag)
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
        List<SysFunction> beanList = sysfunctionDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysFunction() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysFunction queryInfo, Boolean isExcludePKFlag)
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
        List<SysFunction> beanList = sysfunctionDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
