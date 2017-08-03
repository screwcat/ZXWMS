package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.ISysUserDao;
import com.zx.emanage.sysmanage.vo.SysUserSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.emanage.util.gen.vo.SysUserVO;

/*
 * @version 2.0
 */

@Repository("sysuserDao")
public class SysUserDaoImpl extends AbstractSimpleDao implements ISysUserDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtil.isNotBlank(queryInfo.getUser_code()))
        {
            paramMap.put("user_code", SysTools.getSqlLikeParam(queryInfo.getUser_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getUser_realname()))
        {
            paramMap.put("user_realname", SysTools.getSqlLikeParam(queryInfo.getUser_realname()));
        }

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysUserSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getUser_code()))
        {
            paramMap.put("user_code", SysTools.getSqlLikeParam(queryInfo.getUser_code()));
        }
        if (StringUtil.isNotBlank(queryInfo.getUser_realname()))
        {
            paramMap.put("user_realname", SysTools.getSqlLikeParam(queryInfo.getUser_realname()));
        }

        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_LIST, paramMap);
    }

    @Override
    public SysUserVO getInfoByPK(Integer id)
    {
        return SysUser.getRecordVOByPK(this, id);
    }

    @Override
    public SysUser getDomainByPK(Integer id)
    {
        return SysUser.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysUser bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysUser bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysUser bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysUser.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysUser bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysUser> getSingleTableListByAndMethod(SysUser queryInfo, Boolean isExcludePKFlag)
    {
        return SysUser.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysUser> getSingleTableListByOrMethod(SysUser queryInfo, Boolean isExcludePKFlag)
    {
        return SysUser.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }
}