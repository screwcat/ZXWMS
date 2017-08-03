package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysUserMenuFuncSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysUserMenuFunc;
import com.zx.emanage.util.gen.vo.SysUserMenuFuncVO;

/*
 * @version 2.0
 */

public interface ISysUserMenuFuncDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysUserMenuFuncSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysUserMenuFuncVO
     * @author auto_generator
     */
    public SysUserMenuFuncVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysUserMenuFunc
     * @author auto_generator
     */
    public SysUserMenuFunc getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysUserMenuFunc bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysUserMenuFunc bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysUserMenuFunc bean);

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
    public int deleteRecordByDomain(SysUserMenuFunc bean);

    /**
     * Description :get list by "and" method, need new SysUserMenuFunc() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysUserMenuFunc list
     * @author auto_generator
     */
    public List<SysUserMenuFunc> getSingleTableListByAndMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysUserMenuFunc() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysUserMenuFunc list
     * @author auto_generator
     */
    public List<SysUserMenuFunc> getSingleTableListByOrMethod(SysUserMenuFunc queryInfo, Boolean isExcludePKFlag);

    public List<Map<String, Object>> getSysUserMenuCheck(Integer user_id);

    public List<Map<String, Object>> getSysUserFuncCheck(Integer user_id);

    public void insertBatch(List<SysUserMenuFunc> userMenuList);
}