package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.emanage.sysmanage.persist.ISysUserRoleDao;
import com.zx.emanage.sysmanage.vo.SysUserRoleSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.vo.SysUserRoleVO;

/*
 * @version 2.0
 */

@Repository("sysuserroleDao")
public class SysUserRoleDaoImpl extends AbstractSimpleDao implements ISysUserRoleDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("user_id", queryInfo.getUser_id());
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_ROLE_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysUserRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_ROLE_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysUserRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName", "%"+queryInfo.getSearchName()+"%"); }
         */
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_USER_ROLE_LIST, paramMap);
    }

    @Override
    public SysUserRoleVO getInfoByPK(Integer id)
    {
        return SysUserRole.getRecordVOByPK(this, id);
    }

    @Override
    public SysUserRole getDomainByPK(Integer id)
    {
        return SysUserRole.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysUserRole bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysUserRole bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysUserRole bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysUserRole.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysUserRole bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysUserRole> getSingleTableListByAndMethod(SysUserRole queryInfo, Boolean isExcludePKFlag)
    {
        return SysUserRole.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysUserRole> getSingleTableListByOrMethod(SysUserRole queryInfo, Boolean isExcludePKFlag)
    {
        return SysUserRole.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public void insertBeath(List<SysUserRole> list)
    {
        this.insertBatch(list, SqlString.AUTO2_SYS_USER_ROLE_INSERT);
    }
}