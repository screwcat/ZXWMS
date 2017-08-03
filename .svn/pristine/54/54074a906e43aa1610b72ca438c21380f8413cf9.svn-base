package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysRoleMenuFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysRoleMenuFunction;
import com.zx.emanage.util.gen.vo.SysRoleMenuFunctionVO;

/*
 * @version 2.0
 */

public interface ISysRoleMenuFunctionDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysRoleMenuFunctionSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysRoleMenuFunctionVO
     * @author auto_generator
     */
    public SysRoleMenuFunctionVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysRoleMenuFunction
     * @author auto_generator
     */
    public SysRoleMenuFunction getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysRoleMenuFunction bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysRoleMenuFunction bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysRoleMenuFunction bean);

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
    public int deleteRecordByDomain(SysRoleMenuFunction bean);

    /**
     * Description :get list by "and" method, need new SysRoleMenuFunction()
     * include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysRoleMenuFunction list
     * @author auto_generator
     */
    public List<SysRoleMenuFunction> getSingleTableListByAndMethod(SysRoleMenuFunction queryInfo,
                                                                   Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysRoleMenuFunction()
     * include query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysRoleMenuFunction list
     * @author auto_generator
     */
    public List<SysRoleMenuFunction> getSingleTableListByOrMethod(SysRoleMenuFunction queryInfo, Boolean isExcludePKFlag);
}