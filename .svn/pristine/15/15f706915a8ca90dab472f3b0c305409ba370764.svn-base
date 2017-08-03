package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysUserRoleSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUserRole;
import com.zx.emanage.util.gen.vo.SysUserRoleVO;

/*
 * @version 2.0
 */

public interface ISysUserRoleDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysUserRoleSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysUserRoleVO
     * @author auto_generator
     */
    public SysUserRoleVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysUserRole
     * @author auto_generator
     */
    public SysUserRole getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysUserRole bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysUserRole bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysUserRole bean);

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
    public int deleteRecordByDomain(SysUserRole bean);

    /**
     * Description :get list by "and" method, need new SysUserRole() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysUserRole list
     * @author auto_generator
     */
    public List<SysUserRole> getSingleTableListByAndMethod(SysUserRole queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysUserRole() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysUserRole list
     * @author auto_generator
     */
    public List<SysUserRole> getSingleTableListByOrMethod(SysUserRole queryInfo, Boolean isExcludePKFlag);

    public void insertBeath(List<SysUserRole> list);
}