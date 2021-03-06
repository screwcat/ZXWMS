package com.zx.emanage.loanappro.persist;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.emanage.loanappro.vo.WmsCreApproBorrowProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreApproBorrowProtocolDao
 * 模块名称：终审合同
 * @Description: 内容摘要：合同dao
 * @author baisong
 * @date 2016年12月29日
 * @version V1.0
 * history:
 * 1、2016年12月29日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreApproBorrowProtocolDao extends BaseDao<WmsCreApproBorrowProtocol>
{
    /**
     * 根据wms_cre_credit_head_id查询合同是否存在
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreApproBorrowProtocol
     */
    WmsCreApproBorrowProtocol getBorrowProtocolByFK(int wms_cre_credit_head_id);
    /**
     * 根据wms_cre_credit_head_id查询合同是否存在
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreApproBorrowProtocol
     */
    int getNumberByFK(int wms_cre_credit_head_id);
    /**
     * saveforhouse:在数据库中insert一条记录. 用于房产签合同
     * 
     * @author baisong
     */
    Integer saveforhouse(WmsCreApproBorrowProtocol bean);

    /**
     * updateforhouse:在数据库中update一条记录. 用于房产签合同
     * 
     * @author baisong
     */
    void updateforhouse(WmsCreApproBorrowProtocol bean);

    /**
     * 用于合同查询
     * 
     * @author baisong
     */
    Map<String, Object> getobjforhouse(Integer wms_cre_credit_head_id);
    /**
     * 放款申请
     * 
     * @author baisong
     */
    Map<String, Object> getobjforcar(Integer wms_cre_credit_head_id);
    /**
     * 通过客户姓名 签订日期  联系电话 确认合同编号
     * @param param
     * @return
     */
	List<String> getProtocolId(Map<String, Object> param);
	
	/**
	 * 保存合同表
	 * @param vo
	 * @return
	 */
	Integer perfectContractSave(WmsCreApproBorrowProtocolSearchBeanVO vo);
	/**
	 * 判断是否保存过
	 * @param wms_cre_credit_head_id
	 * @return Integer
	 * jiaodelong
	 */
	Integer getPerfectContractCount(Integer wms_cre_credit_head_id);
	
	/**
	 * 判断是否保存过
	 * @param wms_cre_credit_head_id
	 * @return Integer
	 * jiaodelong
	 */
	Map<String, Object> getPerfectContract(Integer wms_cre_credit_head_id);
	/**
	 * 
	 * 根据id 查询合同完善信息
	 * @param wms_cre_credit_head_id
	 * @return Map<String, Object>
	 * jiaodelong
	 */
	Map<String, Object> getPerfectContractInfo(Integer wms_cre_credit_head_id);
	/**
	 * 
	 * 修改合同表信息
	 * @param vo
	 * @return
	 * jiaodelong
	 */
	Integer updatePerfectContract(WmsCreApproBorrowProtocolSearchBeanVO vo);
	
	/**
	 * 获取保存的数据 以供给ireport导出合同pdf
	 * @param wms_cre_credit_head_id
	 * @return
	 * @author baisong
	 * @date 20160830
	 */
	Map<String, Object> getInfoForHouseIReport(Integer wms_cre_credit_head_id);
	
	/**
	 * 查询贷款额度
	 */
	BigDecimal searchApproLimit(Integer _wms_cre_credit_head_id);
    /**
     * @Title: getGroupSaveInfo
     * @Description: TODO(根据headIdList去合同表获取保存过合同的信息)
     * @param headIdListMap
     * @return 
     * @author: jiaodelong
     * @time:2017年1月4日 下午1:07:56
     * history:
     * 1、2017年1月4日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getGroupSaveInfo(Map<String, Object> headIdListMap);
    /**
     * @Title: getProtocolByHeadIdMap
     * @Description: TODO(根据headidList查出全部组合贷单据)
     * @param headIdListMap
     * @return 
     * @author: jiaodelong
     * @time:2017年1月4日 下午7:15:56
     * history:
     * 1、2017年1月4日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getProtocolByHeadIdMap(Map<String, Object> headIdListMap);
    /**
     * @Title: getheadListByProtocolMap
     * @Description: TODO(根据headidList查询所有的签合同信息)
     * @param headIdListMap
     * @return 
     * @author: jiaodelong
     * @time:2017年1月5日 上午9:17:54
     * history:
     * 1、2017年1月5日 jiaodelong 创建方法
    */
    List<Map<String, Object>> getheadListByProtocolMap(Map<String, Object> headIdListMap);
    /**
     * @Title: selectZSJE
     * @Description: TODO(查询终审金额)
     * @param integer
     * @return 
     * @author: jiaodelong
     * @time:2017年1月13日 下午7:20:09
     * history:
     * 1、2017年1月13日 jiaodelong 创建方法
    */
    WmsCreApproBorrowProtocol selectZSJE(Integer integer);
    
    /**
	 * 获取合同列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getProtocolList(Map<String,Object> map);
	/**
	 * 获取合同数目
	 * @param map
	 * @return
	 */
	int findProtocolCount(Map<String,Object> paramMap);
	/**
	 * 获取日流水列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findDailyFlowList(Map<String,Object> map);
	/**
	 * 获取日刷卡列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findDailyCardList(Map<String,Object> map);
	/**
	 * 获取还款提醒列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findRefundRemindList(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: getBorrowProtocolDetails
	 * @Description: TODO(根据单据主表id获取合同信息)
	 * @param wms_cre_credit_head_id
	 * @return 
	 * @author: handongchun
	 * @time:2017年3月27日 下午2:23:51
	 * history:
	 * 1、2017年3月27日 handongchun 创建方法
	 */
	Map<String, Object> getBorrowProtocolDetails(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: findPreviousBorrowProtocol
	 * @Description: TODO(根据手动输入的还款用户名和债务人身份证号匹配合同)
	 * @param map
	 * @return 
	 * @author: handongchun
	 * @time:2017年5月23日 下午1:24:11
	 * history:
	 * 1、2017年5月23日 handongchun 创建方法
	 */
	Map<String, String> findPreviousBorrowProtocol(Map<String,String> map);
    /**
     * @Title: validateProtocolIdYearNum
     * @Description: TODO(验证合同编号是否重复)
     * @param protocol_id_year_num
     * @return 
     * @author: handongchun
     * @param bill_status 
     * @time:2017年7月20日 上午11:15:56
     * history:
     * 1、2017年7月20日 handongchun 创建方法
    */
    int validateProtocolIdYearNum(@Param(value="wms_cre_credit_head_id") int wms_cre_credit_head_id, 
                                  @Param(value="protocol_id_year_num") String protocol_id_year_num, 
                                  @Param(value="bill_status") String bill_status);
}
