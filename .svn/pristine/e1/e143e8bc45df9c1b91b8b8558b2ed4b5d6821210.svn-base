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
import com.zx.emanage.sysmanage.persist.ISysRoleMenuFunctionDao;
import com.zx.emanage.sysmanage.service.ISysRoleMenuFunctionService;
import com.zx.emanage.sysmanage.vo.SysRoleMenuFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysrolemenufunctionService")
public class SysRoleMenuFunctionServiceImpl implements ISysRoleMenuFunctionService
{
    private static Logger log = LoggerFactory.getLogger(SysRoleMenuFunctionServiceImpl.class);

    @Autowired
    private ISysRoleMenuFunctionDao sysrolemenufunctionDao;

    @Override
    public List<Map<String, Object>> getComboxList(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        return sysrolemenufunctionDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", sysrolemenufunctionDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysRoleMenuFunctionSearchBeanVO queryInfo)
    {
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), sysrolemenufunctionDao.getListCountNum(queryInfo),
                                             sysrolemenufunctionDao.getListWithPaging(queryInfo));
        return bean.getGridData();
    }

    @Override
    public SysRoleMenuFunctionVO getInfoByPK(Integer id)
    {
        return sysrolemenufunctionDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(SysRoleMenuFunction bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysrolemenufunctionDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysRoleMenuFunction bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysrolemenufunctionDao.updateRecordAll(bean); // update a record
                                                            // replace all,
                                                            // support null val
        // ret = sysrolemenufunctionDao.updateRecordCols(bean); // update a
        // record replace columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(SysRoleMenuFunction bean)
    {
        String resStr = "success";
        int ret = 0;
        try
        {
            ret = sysrolemenufunctionDao.deleteRecordByDomain(bean); // nonsupport
                                                                     // null val
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
     * SysRoleMenuFunction() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysRoleMenuFunction queryInfo, Boolean isExcludePKFlag)
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
        List<SysRoleMenuFunction> beanList = sysrolemenufunctionDao.getSingleTableListByAndMethod(queryInfo,
                                                                                                  isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysRoleMenuFunction() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysRoleMenuFunction queryInfo, Boolean isExcludePKFlag)
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
        List<SysRoleMenuFunction> beanList = sysrolemenufunctionDao.getSingleTableListByOrMethod(queryInfo,
                                                                                                 isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
