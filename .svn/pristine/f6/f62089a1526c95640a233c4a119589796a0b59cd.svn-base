package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.WmsSysDictDataSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDictData;
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;

/*
 * @version 2.0
 */

public interface IWmsSysDictDataDao
{

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithoutPaging(WmsSysDictDataSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public List<Map<String, Object>> getListWithPaging(WmsSysDictDataSearchBeanVO queryInfo);

    /**
     * Description :get record list count num by vo queryInfo
     * 
     * @param queryInfo
     * @return records count num
     * @author auto_generator
     */
    public int getListCountNum(WmsSysDictDataSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysDictDataVO
     * @author auto_generator
     */
    public WmsSysDictDataVO getInfoByPK(Integer wms_sys_dict_data_id);

    /**
     * Description :insert a record, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int addNewRecord(WmsSysDictData bean);

    /**
     * Description :update a record replace all, need primary key, support null
     * val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordAll(WmsSysDictData bean);

    /**
     * Description :update a record replace columns, need primary key,
     * nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int updateRecordCols(WmsSysDictData bean);

    /**
     * Description :delete record by primary key
     * 
     * @param pk
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByPK(Integer wms_sys_dict_data_id);

    /**
     * Description :delete record by domain bean, nonsupport null val
     * 
     * @param bean
     * @return success num or 0
     * @author auto_generator
     */
    public int deleteRecordByDomain(WmsSysDictData bean);

    /**
     * Description :get list by "and" method, need new WmsSysDictData() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsSysDictData list
     * @author auto_generator
     */
    public List<WmsSysDictData> getSingleTableListByAndMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag);

    /**
     * Description :get list by "or" method, need new WmsSysDictData() include
     * query-params
     * 
     * @param queryInfo
     * @param isExludePKFlag, true is exclude primary key, false is include
     *            primary key
     * @return WmsSysDictData list
     * @author auto_generator
     */
    public List<WmsSysDictData> getSingleTableListByOrMethod(WmsSysDictData queryInfo, Boolean isExcludePKFlag);

    public List<WmsSysDictDataVO> getDictDataByDictId(Integer wms_sys_dict_id);

    @Deprecated
    public List<WmsSysDictDataVO> getDictDataByDictCode(String dict_code);
}