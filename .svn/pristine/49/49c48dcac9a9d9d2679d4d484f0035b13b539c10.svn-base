package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.SysMenuSearchBeanVO;
import com.zx.emanage.util.gen.domain.SysMenu;
import com.zx.emanage.util.gen.vo.SysMenuVO;

/*
 * @version 2.0
 */

public interface ISysMenuDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(SysMenuSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(SysMenuSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(SysMenuSearchBeanVO queryInfo);

    /**
     * Description :get single-table information vo by primary key
     * 
     * @param primary key
     * @return SysMenuVO
     * @author auto_generator
     */
    public SysMenuVO getInfoByPK(Integer id);

    /**
     * Description :get single-table information domain by primary key
     * 
     * @param primary key
     * @return SysMenu
     * @author auto_generator
     */
    public SysMenu getDomainByPK(Integer id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(SysMenu bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(SysMenu bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(SysMenu bean);

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
    public int deleteRecordByDomain(SysMenu bean);

    /**
     * Description :get list by "and" method, need new SysMenu() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysMenu list
     * @author auto_generator
     */
    public List<SysMenu> getSingleTableListByAndMethod(SysMenu queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new SysMenu() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return SysMenu list
     * @author auto_generator
     */
    public List<SysMenu> getSingleTableListByOrMethod(SysMenu queryInfo, Boolean isExcludePKFlag);
}