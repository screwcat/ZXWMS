package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.ISysLogDao;
import com.zx.emanage.sysmanage.vo.SysLogSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysLog;
import com.zx.emanage.util.gen.vo.SysLogVO;

/*
 * @version 2.0
 */

@Repository("syslogDao_m")
public class SysLogDaoImpl extends AbstractSimpleDao implements ISysLogDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_LOG_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysLogSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getUser_code()))
        {
            paramMap.put("user_code", SysTools.getSqlLikeParam(queryInfo.getUser_code()));
        }

        if (StringUtil.isNotBlank(queryInfo.getUser_name()))
        {
            paramMap.put("user_name", SysTools.getSqlLikeParam(queryInfo.getUser_name()));
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_LOG_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysLogSearchBeanVO queryInfo)
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
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_LOG_LIST, paramMap);
    }

    @Override
    public SysLogVO getInfoByPK(Integer id)
    {
        return SysLog.getRecordVOByPK(this, id);
    }

    @Override
    public SysLog getDomainByPK(Integer id)
    {
        return SysLog.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysLog bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysLog bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysLog bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysLog.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysLog bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysLog> getSingleTableListByAndMethod(SysLog queryInfo, Boolean isExcludePKFlag)
    {
        return SysLog.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysLog> getSingleTableListByOrMethod(SysLog queryInfo, Boolean isExcludePKFlag)
    {
        return SysLog.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}