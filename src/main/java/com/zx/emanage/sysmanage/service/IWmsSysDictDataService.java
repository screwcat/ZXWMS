package com.zx.emanage.sysmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.WmsSysDictDataSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDictData;
import com.zx.emanage.util.gen.entity.SysDeptDataBean;
import com.zx.emanage.util.gen.vo.WmsSysDictDataVO;
import com.zx.platform.syscontext.vo.TreeBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsSysDictDataService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsSysDictDataSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsSysDictDataSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysDictDataVO
     * @author auto_generator
     */
    public WmsSysDictDataVO getInfoByPK(Integer wms_sys_dict_data_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsSysDictData bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsSysDictData bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsSysDictData bean);

    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictId(Integer wms_sys_dict_id,
                                                                                   String isEmpty, String isAll);

    @Deprecated
    public List<WmsSysDictDataVO> getDictDataByDictCode(String dict_code);

    public List<TreeBean> getDictDataTreeBean(Integer p_wms_sys_dict_id, Integer wms_sys_dict_id, String... isnotval);

    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id,                  
  	        	String isEmpty, String isAll);

    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyCombination(Integer wms_sys_dict_id, String isEmpty, String isAll, String isCombin);

	public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyAll(
			String wms_sys_dict_id, String isEmpty, String isAll);
	
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmpty(Integer wms_sys_dict_id,Integer p_wms_sys_dict_data_id,
            String isEmpty, String isAll);
    
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdEmptyP(Integer wms_sys_dict_id,Integer p_wms_sys_dict_data_id,
            String isEmpty, String isAll);
    
    public Map<String, Object> getDictDataCode(Integer wms_sys_dict_id,String value_code,
            String isEmpty, String isAll);

    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAmount(Integer wms_sys_dict_id,
                                                                                         String isEmpty);

    /**
     * 根据提供字典表ID，是否请选择 产品类型
     * 
     * @param wms_sys_dict_id
     * @param isEmpty
     * @param cre_type
     * @return
     */
    public List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAndCreType(Integer wms_sys_dict_id,
                                                                                             String isEmpty,
                                                                                             String cre_type);
    
    /**
     * @Title: getValMeaningBycodes 
     * @Description: 
     * @param paramMap
     * @return    
     * @return List<WmsSysDictData>    
     * @throws
     * @author lvtu 
     * @date 2015年7月24日 下午5:10:07
     */
    public String getValMeaningBycodes(Integer wms_sys_dict_id, String valCodes);
    /**
     * @Title getAllSysStoresWithList
     * @description:
     * @param isEmpty
     * @param deptpId
     * @return List<SysDeptDataBean>
     * @author yangqiyu
     * @date 2015年9月30日 上午10:15
     */
	public List<SysDeptDataBean> getAllSysStoresWithList(String isEmpty,
			Integer deptpId);
	  /**
	   * 通过valuecode 获取对应的值
	   * @param wms_sys_dict_id
	   * @param value_code
	   * @return
	   */
    public Map<String, Object> getDictDataByCode(Integer wms_sys_dict_id,String value_code);

    /**
     * @Title: getDictionaryDataPad
     * @Description: pad请求获取数据字典详细内容
     * @param wms_sys_dict_id
     * @param p_wms_sys_dict_data_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月22日 下午6:02:41
     * history:
     * 1、2017年2月22日 Administrator 创建方法
    */
    public Map<String, Object> getDictionaryDataPad(String wms_sys_dict_id, String p_wms_sys_dict_data_id);
}