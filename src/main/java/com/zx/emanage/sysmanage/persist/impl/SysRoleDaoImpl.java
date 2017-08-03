package com.zx.emanage.sysmanage.persist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.platform.database.AbstractSimpleDao;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.ISysRoleDao;
import com.zx.emanage.sysmanage.vo.SysRoleSearchBeanVO;
import com.zx.emanage.util.gen.SqlString;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.domain.SysRole;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleVO;

/*
 * @version 2.0
 */

@Repository("sysroleDao")
public class SysRoleDaoImpl extends AbstractSimpleDao implements ISysRoleDao
{

    @Override
    public List<Map<String, Object>> getListWithoutPaging(SysRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        /*
         * if(StringUtil.isNotBlank(queryInfo.getSearchName())){
         * paramMap.put("searchName",
         * SysTools.getSqlLikeParam(queryInfo.getSearchName())); }
         */
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.queryForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_LIST, paramMap);
    }

    @Override
    public List<Map<String, Object>> getListWithPaging(SysRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getRole_name()))
        {
            paramMap.put("role_name", SysTools.getSqlLikeParam(queryInfo.getRole_name()));
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        return this.pagingForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_LIST, queryInfo.getPagesize(),
                                            queryInfo.getPage(), paramMap);
    }

    @Override
    public int getListCountNum(SysRoleSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(queryInfo.getRole_name()))
        {
            paramMap.put("role_name", SysTools.getSqlLikeParam(queryInfo.getRole_name()));
        }
        return this.recordNumberForListByTemplate(SqlString.AUTOSINGLE2_SYS_ROLE_LIST, paramMap);
    }

    @Override
    public SysRoleVO getInfoByPK(Integer id)
    {
        return SysRole.getRecordVOByPK(this, id);
    }

    @Override
    public SysRole getDomainByPK(Integer id)
    {
        return SysRole.getRecordDomainByPK(this, id);
    }

    @Override
    public int addNewRecord(SysRole bean)
    {
        return bean.insertRecord(this);
    }

    @Override
    public int updateRecordAll(SysRole bean)
    {
        return bean.updateRecordAll(this);
    }

    @Override
    public int updateRecordCols(SysRole bean)
    {
        return bean.updateRecordColumn(this);
    }

    @Override
    public int deleteRecordByPK(Integer id)
    {
        return SysRole.deleteRecordsByPK(this, id);
    }

    @Override
    public int deleteRecordByDomain(SysRole bean)
    {
        return bean.deleteRecordsByDomain(this);
    }

    @Override
    public List<SysRole> getSingleTableListByAndMethod(SysRole queryInfo, Boolean isExcludePKFlag)
    {
        return SysRole.getSingleTableListByAndMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<SysRole> getSingleTableListByOrMethod(SysRole queryInfo, Boolean isExcludePKFlag)
    {
        return SysRole.getSingleTableListByOrMethod(this, queryInfo, isExcludePKFlag);
    }

    @Override
    public List<Map<String, Object>> getSysRoleMenuCheck(Integer modifyRoleId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (modifyRoleId != null)
        {
            paramMap.put("current_role_id", modifyRoleId);
        }
        else
        {
            paramMap.put("no_current_role_id", "true");
        }
        return this.queryForListByTemplate(SqlString.SYSMANAGE_SYSROLE_SYS_MENU_GET_USER_MENU_CHECK, paramMap);
    }

    @Override
    public List<Map<String, Object>> getSysRoleFuncCheck(Integer modifyRoleId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (modifyRoleId != null)
        {
            paramMap.put("current_role_id", modifyRoleId);
        }
        else
        {
            paramMap.put("no_current_role_id", "true");
        }
        return this.queryForListByTemplate(SqlString.SYSMANAGE_SYSROLE_SYS_FUNC_ROLE_GET_USER_FUNC_CHECK, paramMap);
    }

    @Override
    public void insertBatchSysRoleFunc(List<SysRoleMenuFunction> roleMenuList)
    {
        this.insertBatch(roleMenuList, SqlString.SYSMANAGE_SYSROLE_SYS_ROLE_MENU_FUNCTION_INSERT);
    }

    @Override
    public int addNewRecordWithId(SysRole sysRole)
    {
        return this.insert(sysRole, "sys_role", "id");
    }
}