package com.zx.emanage.loanfina.persist;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsFinaCreRepayDao extends BaseDao<WmsFinaCreRepay>
{
    public WmsFinaCrePeriodRepaySearchBeanVO getbyfk(Integer wms_cre_credit_head_id);// 逾期还款确认信息获取
    public WmsFinaCrePeriodRepaySearchBeanVO getbyfk2(Integer wms_cre_credit_head_id);// 逾期还款确认信息 获取逾期还款确认 查询清单  重新写的 上面的可能被其他地方使用

    public Map<String,Object> getbyfkfornor(Integer wms_cre_credit_head_id);// 正常还款确认信息获取

    public WmsFinaCreRepay updateInfo(WmsFinaCrePeriodRepaySearchBeanVO bean);

    public WmsFinaCreRepay getbyid(Integer wms_cre_credit_head_id);// 根据
                                                                   // wms_cre_credit_head_id获取期还款台账表

    /**
     * 根据主键查询申请提前还款信息
     * 
     * @param wms_fina_cre_pay_id
     * @return 提前还款信息Map
     */
    Map<String, Object> getInfoByPK(Integer wms_fina_cre_pay_id);

    /**
     * 根据贷款还款信息中的属性来修改贷款还款信息
     * 
     * @param wmsFinaCreRepay 贷款还款信息实体类
     */
    void updateByEntity(WmsFinaCreRepay wmsFinaCreRepay);

    /**
     * 查询需要审核的记录的条数 提前还款查询列表
     * 
     * @param params
     * @return 条数
     */
    Integer findCount2(Map<String, Object> params);

    /**
     * 查询需要审核的记录 提前还款查询列表
     * 
     * @param params
     * @return 需要审核的记录
     */
    List<Map<String, Object>> search2(Map<String, Object> params);

    /**
     * 查询的条数 逾期查询列表
     * 
     * @param params
     * @return 条数
     */
    Integer findCountforyuqi(Map<String, Object> params);

    /**
     * 查询 逾期查询列表
     * 
     * @param params
     * @return 需要审核的记录
     */
    List<Map<String, Object>> searchforyuqi(Map<String, Object> params);
    /**
     * 查询 逾期查询列表
     * 
     * @param params
     * @return 需要审核的记录
     */
    List<Map<String, Object>> searchforyuqiexcel(Map<String, Object> params);
    /**
     * 查询条数 正常查询列表
     * 
     * @param params
     * @return 条数
     */
    Integer findCountfornormal(Map<String, Object> params);

    /**
     * 查询 正常查询列表
     * 
     * @param params
     * @return 需要审核的记录
     */
    List<Map<String, Object>> searchfornormal(Map<String, Object> params);

    /**
     * 查询 结清管理数据列表
     * 
     * @param paramMap
     * @return 需要返回的结清记录
     */
    List<Map<String, Object>> searchforClear(Map<String, Object> paramMap);

    /**
     * 查询结清管理数据列表
     * 
     * @param paramMap
     * @return 需要结清的记录数
     */
    int findCountforClear(Map<String, Object> paramMap);

    /**
     * 根据wms_cre_creidt_head_id 查询合同表、放款申请表
     * 
     * @param wms_cre_credit_head_id
     * @return
     */
    Map<String, Object> getBorrowAndLoanAppByPK(Integer wms_cre_credit_head_id);

    /**
     * 债权匹配后更新可匹配债权数值
     * 
     * @param
     * @return
     */
    int updateMatch(WmsFinaCreRepay wmsFinaCreRepay);

    /**
     * 赎回后后更新可匹配债权数值
     * 
     * @param
     * @return
     */
    int updateRedeem(WmsFinaCreRepay wmsFinaCreRepay);

    /**
     * 获取优先等级的债权信息
     * 
     * @param
     * @return
     */
    List<WmsFinaCreRepay> getListForFirstMatch(WmsFinaCreRepay wmsFinaCreRepay);

    /**
     * 获取非优先等级的债权信息
     * 
     * @param
     * @return
     */
    List<WmsFinaCreRepay> getListForOtherMatch(WmsFinaCreRepay wmsFinaCreRepay);

    /**
     * 当债权匹配规则变更为按债权包总额时更改债权包价值
     * 
     * @param
     * @return
     */
    int changeMatchRuleToTotal(Map<String, Object> params);

    /**
     * 当债权匹配规则变更为按债权包实时余额时更改债权包价值
     * 
     * @param
     * @return
     */
    int changeMatchRuleToSurplus(Map<String, Object> params);
    /**
     * 服务管理--贷后管理--贷后查询列表信息--贷后专员分配-列表
     * @param paramMap
     * @return
     * @author baisong
     */
    List<Map<String, Object>> searchforPostLoanAllocation(Map<String, Object> paramMap);
    /**
     * 贷后专员查询贷款单据--贷后专员分配--导出excel--催缴分配
     * 
     * @param queryInfo
     * @return record list
     * @author baisong
     */
    List<Map<String, Object>> getListWithoutAllocation(Map<String, Object> paramMap);
    /**
     * 服务管理--贷后管理--贷后查询--数据数--贷后专员分配-列表
     * @param paramMap
     * @return
     * @author baisong
     */
    int findCountforPostLoanAllocation(Map<String, Object> paramMap);
    /**
     * 根据查询条件查询相应信息
     * @param paramMap
     * @return
     */
    Map<String, Object> getInfo(Map<String,Object> paramMap);
    
    /**
     * 服务管理--贷后管理--贷后查询列表信息--贷后专员查询--电话催缴
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforPostLoanCommissioner(Map<String, Object> paramMap);
    /**
     * 服务管理--贷后管理--贷后查询--数据数--贷后专员查询--电话催缴
     * @param paramMap
     * @return
     */
    int findCountCommissioner(Map<String, Object> paramMap);
    /**
     * 放款确认-催缴人员分配
     * @param paramMap
     * @return
     * baisong
     */
    Map<String, Object> seachCount(Map<String, Object> map);
    
	 /**
     * 清空数据库贷款还款表中的 贷后专员信息 id  name  deptid
     * @param wms_cre_credit_head_id
     * @return
     * baisong
     */
    int udpatenull(WmsFinaCreRepay wmsFinaCreRepay);
    
    /**
     * 服务管理--贷后管理--贷后查询列表信息--导出excel--电话催缴
     * @param paramMap
     * @return
     * baisong
     */
    List<Map<String, Object>> searchforPostLoanexcel(Map<String, Object> paramMap);
    /**
     * 新增获取信贷债权详情接口
     * @param paramMap
     * @return
     * baisong
     */
    List<Map<String, Object>> getInfoforPTP(Map<String, Object> paramMap);
    /**
     * 新增获取房贷债权详情接口
     * @param paramMap
     * @return
     * baisong
     */
    List<Map<String, Object>> getInfoforPTPhosue(Map<String, Object> paramMap);
    
    /**
     * 查询条数 列表---终止合同审核
     * 
     * @param params
     * @return 条数
     */
    Integer findCountforstop(Map<String, Object> params);

    /**
     * 查询查询列表---终止合同审核
     * 
     * @param params
     *baisong
     */
    List<Map<String, Object>> searchforstop(Map<String, Object> params);
  
    /**
     * 终止合同审核信息
     * 
     * @param params
     * @return 
     * baisong
     */
    public  Map<String,Object> getinfostop(Map<String, Object> params);
    
    
    /**
     * 查询条数 列表---终止合同确认
     * 
     * @param params
     * @return 条数
     */
    Integer findCountforaffirm(Map<String, Object> params);

    /**
     * 查询查询列表---终止合同确认
     * 
     * @param params
     *baisong
     */
    List<Map<String, Object>> searchforaffirm(Map<String, Object> params);
    /**
     * 服务管理--贷后管理--贷后查询列表信息--贷后专员查询--上门催缴
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> searchforPostLoanVisit(Map<String, Object> paramMap);
    /**
     * 服务管理--贷后管理--贷后查询--数据数--贷后专员查询--上门催缴
     * @param paramMap
     * @return
     */
    int findCountVisit(Map<String, Object> paramMap);
	/**
	 * 更新还款信息表数据 (更正逾期数据使用)
	 * @param paramMap
	 * @author hancd
	 */
    public void updateforrepay(Map<String, Object> paramMap);
	/**
	 * 实现把所有结清的数据结清 (更正逾期数据使用)
	 */
    public void updateforclearstatus();
    /**
     * 结清证明打印数据显示
     * @param param
     * @return
     */
	public Map<String, Object> getWmsPostforClearById(Map<String, Object> param);
}