package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysDeptSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysDept;
import com.zx.emanage.util.gen.vo.SysDeptVO;

/*
 * @version 2.0
 */

public interface ISysDeptDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysDeptSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysDeptSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysDeptSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysDeptVO
     * @author auto_generator
     */
    public SysDeptVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysDept
     * @author auto_generator
     */
    public SysDept getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysDept bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysDept bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysDept bean);

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
    public int deleteRecordByDomain(SysDept bean);

    /**
     * Description :get list by "and" method, need new SysDept() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysDept list
     * @author auto_generator
     */
    public List<SysDept> getSingleTableListByAndMethod(SysDept queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysDept() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysDept list
     * @author auto_generator
     */
    public List<SysDept> getSingleTableListByOrMethod(SysDept queryInfo, Boolean isExcludePKFlag);
}