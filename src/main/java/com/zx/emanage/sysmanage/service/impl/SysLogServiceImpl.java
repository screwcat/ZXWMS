package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.emanage.sysmanage.persist.ISysLogDao;
import com.zx.emanage.sysmanage.persist.SysLogDao;
import com.zx.emanage.sysmanage.service.ISysLogService;
import com.zx.emanage.sysmanage.vo.SysLogSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysLog;
import com.zx.emanage.util.gen.vo.SysLogVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("syslogService")
public class SysLogServiceImpl implements ISysLogService
{
    private static Logger log = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    private ISysLogDao syslogDao;

    @Autowired
    private SysLogDao syslogDao_m;

    @Override
    public List<Map<String, Object>> getComboxList(SysLogSearchBeanVO queryInfo)
    {
        return syslogDao.getListWithoutPaging(queryInfo);
    }

    @Override
    public Map<String, Object> getListWithoutPaging(SysLogSearchBeanVO queryInfo)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", syslogDao.getListWithoutPaging(queryInfo));
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getUser_code()))
        {
            paramMap.put("user_code", "%" + queryInfo.getUser_code() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getUser_name()))
        {
            paramMap.put("user_name", "%" + queryInfo.getUser_name() + "%");
        }
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        GridDataBean bean = new GridDataBean(queryInfo.getPage(), syslogDao_m.findCount(paramMap),
                                             syslogDao_m.search(paramMap));
        return bean.getGridData();
    }

    @Override
    public SysLogVO getInfoByPK(Integer id)
    {
        return syslogDao.getInfoByPK(id);
    }

    @Override
    @Transactional
    public String save(SysLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = syslogDao.addNewRecord(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = syslogDao.updateRecordAll(bean); // update a record replace all,
                                               // support null val
        // ret = syslogDao.updateRecordCols(bean); // update a record replace
        // columns, nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String delete(Integer id)
    {
        String resStr = "success";
        try
        {
            syslogDao_m.delete(new Integer[] { id }); // nonsupport null val
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "fkerror"; // default foreign key error
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysLog() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByAndMethod(SysLog queryInfo, Boolean isExcludePKFlag)
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
        List<SysLog> beanList = syslogDao.getSingleTableListByAndMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "or" method, each checking, need new
     * SysLog() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private String checkParamsRepeatByOrMethod(SysLog queryInfo, Boolean isExcludePKFlag)
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
        List<SysLog> beanList = syslogDao.getSingleTableListByOrMethod(queryInfo, isExcludePKFlag);
        if (beanList != null && beanList.size() > 0)
        {
            resStr = "repeat";
        }
        return resStr;
    }
}
