package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysFunctionSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysFunction;
import com.zx.emanage.util.gen.vo.SysFunctionVO;

/*
 * @version 2.0
 */

public interface ISysFunctionDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysFunctionSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysFunctionSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysFunctionVO
     * @author auto_generator
     */
    public SysFunctionVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysFunction
     * @author auto_generator
     */
    public SysFunction getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysFunction bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysFunction bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysFunction bean);

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
    public int deleteRecordByDomain(SysFunction bean);

    /**
     * Description :get list by "and" method, need new SysFunction() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysFunction list
     * @author auto_generator
     */
    public List<SysFunction> getSingleTableListByAndMethod(SysFunction queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysFunction() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysFunction list
     * @author auto_generator
     */
    public List<SysFunction> getSingleTableListByOrMethod(SysFunction queryInfo, Boolean isExcludePKFlag);
}