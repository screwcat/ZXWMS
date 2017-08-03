package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysRoleSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRole;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleVO;

/*
 * @version 2.0
 */

public interface ISysRoleDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysRoleSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysRoleVO
     * @author auto_generator
     */
    public SysRoleVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysRole
     * @author auto_generator
     */
    public SysRole getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysRole bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysRole bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysRole bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(SysRole bean);

    /**
     * Description :get list by "and" method, need new SysRole() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysRole list
     * @author auto_generator
     */
    public List<SysRole> getSingleTableListByAndMethod(SysRole queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysRole() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysRole list
     * @author auto_generator
     */
    public List<SysRole> getSingleTableListByOrMethod(SysRole queryInfo, Boolean isExcludePKFlag);

    public List<Map<String, Object>> getSysRoleMenuCheck(Integer modifyRoleId);

    public List<Map<String, Object>> getSysRoleFuncCheck(Integer modifyRoleId);

    public void insertBatchSysRoleFunc(List<SysRoleMenuFunction> roleMenuList);

    public int addNewRecordWithId(SysRole sysRole);
}