package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.loanappro.vo.WmsCreCreditApproModelScoreVO;
import com.zx.emanage.util.gen.entity.WmsSysDictData;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsSysDictDataDao extends BaseDao<WmsSysDictData>
{
    /**
     * 通过字典ID查询字典数据
     * 
     * @param wms_sys_dict_id 字典ID
     * @return 字典数据集合
     */
	List<WmsSysDictData> getDictDataByDictId(Integer wms_sys_dict_id);

    /**
     * 通过字典ID查询字典数据可怕排除一些值
     * 
     * @param wms_sys_dict_id 字典ID 
     * @return 字典数据集合
     */
    List<WmsSysDictData> getDictDataByDictIdNotVal(Map<String, Object> map);

	/**
	 * 通过字典ID查询字典数据
	 * 
	 * @param wms_sys_dict_id
	 *            字典ID
	 * @return 字典数据集合
	 */
	List<WmsSysDictData> getDictDataByDictIdAll(String wms_sys_dict_id);

    /**
     * 通过字典ID查询字典数据
     * 
     * @param wms_sys_dict_id 字典ID
     * @return 字典数据集合
     */
    List<WmsSysDictData> getDictDataByDictIdP(Map<String, Object> parMap);
    /**
     * 通过字典ID查询字典数据
     * 
     * @param wms_sys_dict_id 字典ID
     * @return 字典数据集合
     */
    List<WmsSysDictData> getDictDataCode(Map<String, Object> parMap);
    
    /**
     * @return 字典数据集合
     */
    List<WmsSysDictData> getDictData(Map<String, Object> paramMap);


    /**
     * 通过id查询字典数据表数据
     * 
     * @param wms_sys_dict_data_id 数据表ID
     */
    List<WmsCreCreditApproModelScoreVO> getDictDataById(int wms_cre_credit_head_id);

    WmsSysDictData getDictById(int wms_sys_dict_data_id);

    /**
     * 通过字典表ID和产品类型查找相对应的流程状态
     * 
     * @param parMap 包含 wms_sys_dict_id and cre_type
     * @return
     */
    List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdAndCreType(Map<String, Object> parMap);
    
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
    public List<WmsSysDictData> getValMeaningBycodes(Map<String, Object> paramMap);
    
    /**
     * 通过字典ID查询字典数据
     * 
     * @param wms_sys_dict_id 字典ID
     * @return 字典数据集合
     */
    List<WmsSysDictData> getDictDataByCode(Map<String, Object> parMap);
	 /**
	  * 根据主键 只返回value_code
	  * @param wms_sys_dict_id
	  * @return List<String>
	  * @author baisong
	  * @date 2016-10-11
	  */
    public List<String> getCodeByDictId(Integer wms_sys_dict_id);

    /**
     * @Title: getDictDataByDictIdCombination
     * @Description:  根据需要获取贷款产品 并判断是否是组合贷
     * @param map
     * @return 
     * @author: baisong
     * @time:2017年5月12日 上午11:25:50
     * history:
     * 1、2017年5月12日 baisong 创建方法
    */
    List<com.zx.emanage.util.gen.entity.WmsSysDictData> getDictDataByDictIdCombination(Map<String, Object> map);
}
