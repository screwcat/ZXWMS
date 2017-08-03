package com.zx.emanage.sysmanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.sysmanage.persist.SysPtpinfoLogDao;
import com.zx.emanage.sysmanage.service.ISysPtpinfoLogService;
import com.zx.emanage.util.gen.entity.SysPtpinfoLog;
import com.zx.emanage.sysmanage.vo.SysPtpinfoLogSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("sysptpinfologService")
public class SysPtpinfoLogServiceImpl implements ISysPtpinfoLogService
{
    private static Logger log = LoggerFactory.getLogger(SysPtpinfoLogServiceImpl.class);

    @Autowired
    private SysPtpinfoLogDao sysptpinfologDao;

    @Override
    public Map<String, Object> getListWithoutPaging(SysPtpinfoLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = sysptpinfologDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    @Override
    public Map<String, Object> getListWithPaging(SysPtpinfoLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = sysptpinfologDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       sysptpinfologDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    @Override
    public SysPtpinfoLog getInfoByPK(Integer sys_ptpinfo_log_id)
    {
        return sysptpinfologDao.get(sys_ptpinfo_log_id);
    }

    @Override
    @Transactional
    public String save(SysPtpinfoLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysptpinfologDao.save(bean);
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    @Override
    @Transactional
    public String update(SysPtpinfoLog bean, UserBean user)
    {
        String resStr = "success";
        int ret = 0;
        ret = sysptpinfologDao.update(bean); // update a record replace columns,
                                             // nonsupport null val
        if (ret == 0)
        {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description :check repeat by "and" method, union checking, need new
     * SysPtpinfoLog() include check-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return "success" or "repeat"
     * @author auto_generator
     */
    private List<SysPtpinfoLog> getListByEntity(SysPtpinfoLog queryInfo, Boolean isExcludePKFlag)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isExcludePKFlag", isExcludePKFlag);
        String resStr = "success";
        List<SysPtpinfoLog> beanList = sysptpinfologDao.getListByEntity(queryInfo);
        return beanList;
    }
}
