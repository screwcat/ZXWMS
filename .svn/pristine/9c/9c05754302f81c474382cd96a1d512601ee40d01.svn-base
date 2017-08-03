package com.zx.emanage.loanfina.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsFinaCreLoanApp;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * @ClassName: WmsFinaCreLoanAppServiceImpl
 * @Description: 内容摘要：放款申请相关Dao
 * @author wangyihan
 * @date 2016年12月23日
 * @version V1.0
 * history:
 * 1、2016年12月23日 wangyihan 创建文件
 */
@MyBatisRepository
public interface WmsFinaCreLoanAppDao extends BaseDao<WmsFinaCreLoanApp>
{

    /**
     * 根据wms_cre_credit_head_id 查询 WmsFinaCreLoanApp对象
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    WmsFinaCreLoanApp getWmsFinaCreLoanAppByFk(Integer wms_cre_credit_head_id);

    /**
     * 修改放款申请时间
     * 
     * @param date
     * @param wms_cre_credit_head_id
     */
    void updateLoanDate(Map<String, Object> paramMap);

    /**
     * 根据贷款主表ID删除贷款申请记录
     * 
     * @param wms_cre_credit_head_id 贷款主表ID
     */
    void deleteByFk(Integer wms_cre_credit_head_id);

	void updateWmsFinaCreLoanApp(WmsFinaCreLoanApp bean);
	
	/**
	 * 根据单据主键wms_cre_credit_head_id逻辑删除
	 * @param paramMap
	 */
	void deleteByWmsCreCreditHeadId(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: initCombineLoanInfo
	 * @Description: discription: 放款申请组合贷初始化信息
	 * @param paramMap
	 * @return 
	 * @author: wangyihan
	 * @time:2016年12月23日 下午4:19:22
	 * history:
	 * 1、2016年12月23日 wangyihan 创建方法
	 */
	List<Map<String, Object>> initCombineLoanInfo(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: deleteByMap
	 * @Description: TODO(删除数据)
	 * @param paramMap 
	 * @author: wangyihan
	 * @time:2016年12月27日 上午9:01:31
	 * history:
	 * 1、2016年12月27日 wangyihan 创建方法
	 */
	void deleteByMap(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: searchCombineLoanList
	 * @Description: 查询组合贷单据
	 * @param paramMap
	 * @return 
	 * @author: wangyihan
	 * @time:2017年1月10日 下午2:03:30
	 * history:
	 * 1、2017年1月10日 wangyihan 创建方法
	 */
	List<Map<String, Object>> searchCombineLoanList(Map<String, Object> paramMap);

    /**
     * @Title: updateInfo
     * @Description: TODO(根据headid修改放款申请表信息)
     * @param app 
     * @author: jiaodelong
     * @time:2017年2月28日 下午2:33:45
     * history:
     * 1、2017年2月28日 jiaodelong 创建方法
    */
    void updateInfo(WmsFinaCreLoanApp app);

    /**
     * @Title: updateForHeadId
     * @Description: TODO(根据headid修改放款申请表信息)
     * @param app 
     * @author: jiaodelong
     * @time:2017年3月9日 下午3:14:29
     * history:
     * 1、2017年3月9日 jiaodelong 创建方法
    */
    void updateForHeadId(WmsFinaCreLoanApp app);
	
	
	
}