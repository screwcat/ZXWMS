package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveRedeemRecordVO;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveRedeemDao extends BaseDao<WmsInveRedeem>
{

    List<Map<String, Object>> selectRedeemList(Map<String, Object> paramMap);

    /**
     * 导出excel--回款
     * 
     * @param paramMap
     * @return baisong
     */
    List<Map<String, Object>> selectRedeemListforExcel(Map<String, Object> paramMap);

    int selectRedeemListCount(Map<String, Object> paramMaps);

    void updateRecord(Map<String, Object> statusMap);

    List<Map<String, Object>> searchForQuerySH(Map<String, Object> paramMap);

    int findCountForQuerySH(Map<String, Object> paramMaps);

    List<Map<String, Object>> checkReturnList(Map<String, Object> paramMap);

    int findCountCheckReturn(Map<String, Object> paramMaps);

    int updateSJSH(WmsInveRedeem wmsInveRedeem);
    
    /**
     * 通过传递当前操作者部门ID和终结点部门ID,判断当前是否在理财财务中心架构中
     */
    String checkReDept(Map<String, Object> paramMap);
    /**
     * 获取审批数据 给手机赎回审批
     */
    List<Map<String, Object>> searchforphone(Map<String, Object> paramMap);
    /**
     * 获取审批数据 给手机赎回审批--根据主键获取当前单据信息
     */
   Map<String, Object> getforphone(Integer wms_inve_redeem_id);
/**
 * 理财赎回特批列表显示
 * @param paramMap
 * @return
 */
	List<Map<String, Object>> searchSpecialRedemptionList(
			Map<String, Object> paramMap);
	/**
	 * 理财赎回特批显示数量
	 * @param paramMap
	 * @return
	 */
	int searchSpecialRedemptionCount(Map<String, Object> paramMap);
	/**
	 * 理财赎回修订清空审批清空
	 * @param paramMap
	 * @return
	 */
	int updateToNull(WmsInveRedeem wmsInveRedeem);
	
	/**
	 * 理财赎回查询详细
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getRedeemInfoDetailList(Map<String, Object> paramMap);

    /**
     * @Title: searchforphone_ys
     * @Description:ZSH查询审批列表中的已审批单据
     * @param paramMap_ys
     * @return
     * @author: Guanxu
     * @time:2016年10月18日 下午2:34:54 history: 1、2016年10月18日 Guanxu 创建方法
     */
    List<Map<String, Object>> searchforphone_ys(Map<String, Object> paramMap_ys);


    /**
     * @Title: queryRedeemByBillCode
     * @Description: 根据billcode查询赎回主键和赎回时间
     * @param paramMap billcode
     * @return 赎回主键和赎回时间
     * @author: jinzhiming
     * @time:2017年5月17日 下午4:49:16
     * history:
     * 1、2017年5月17日 jinzhiming 创建方法
     */
    public Map<String, Object> queryRedeemByBillCode(Map<String, Object> paramMap);

    /**
     * @Title: getRedeemInfoDetailListMoa
     * @Description: 根据主键查询赎回单据的详细信息
     * @param wms_inve_redeem_id
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月7日 下午2:07:51
     * history:
     * 1、2016年12月7日 Administrator 创建方法
    */
    Map<String, Object> getRedeemInfoDetailListMoa(Integer wms_inve_redeem_id);


    /**
     * @Title: phoneGetRedeemByQueryInfo
     * @Description: 根据查询条件信息获取对应的赎回单据信息
     * @param paramMap 查询参数集合
     * @return 
     * @author: DongHao
     * @time:2016年12月7日 上午9:42:55
     * history:
     * 1、2016年12月7日 DongHao 创建方法
    */
    List<Map<String, Object>> phoneGetRedeemByQueryInfo(Map<String, Object> paramMap);

    /**
     * @Title: searchForPhoneAppData
     * @Description: 查询赎回单据信息
     * @param paramsMap 参数map集合
     * @return 返回list集合
     * @author: DongHao
     * @time:2016年12月7日 下午3:15:06
     * history:
     * 1、2016年12月7日 DongHao 创建方法
    */
    List<Map<String, Object>> searchForPhoneAppData(Map<String, Object> paramsMap);

    /**
     * @Title: searchForPhoneAppYsData
     * @Description: 查询已审的赎回单据
     * @param paramMap 参数集合
     * @return 
     * @author: DongHao
     * @time:2016年12月8日 下午1:09:35
     * history:
     * 1、2016年12月8日 DongHao 创建方法
    */
    List<Map<String, Object>> searchForPhoneAppYsData(Map<String, Object> paramMap);

    /**
     * @Title: getRedeemInfo
     * @Description: 根据赎回单据表主键id进行获取赎回单据信息
     * @param map
     * @return
     * @author: DongHao
     * @time:2016年12月9日 上午9:56:08
     * history:
     * 1、2016年12月9日 DongHao 创建方法
    */
    Map<String, Object> getRedeemInfo(Map<String, Object> map);

    /**
     * @Title: getWmsInveRedeemInfoByWmsInveRedeemDetailId
     * @Description: 根据赎回详细信息主键获对应的赎回信息
     * @param wmsInveRedeemDetailId
     * @return 
     * @author: DongHao
     * @time:2016年12月28日 下午5:25:31
     * history:
     * 1、2016年12月28日 DongHao 创建方法
    */
    Map<String, Object> getWmsInveRedeemInfoByWmsInveRedeemDetailId(int wmsInveRedeemDetailId);

    /**
     * @Title: searchFinancialRedeemListForExport
     * @Description: 根据查询条件查询理财主管导出赎回报表数据
     * @param paramMap 查询条件
     * @return 理财主管导出赎回报表数据
     * @author: jinzhm
     * @time:2017年5月10日 下午5:07:23
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchFinancialRedeemListForExport(Map<String, Object> paramMap);

    /**
     * @Title: searchRedeemListForExport
     * @Description: 根据查询条件查询理财财务主管导出赎回报表数据
     * @param paramMap 查询条件
     * @return 财务主管导出赎回报表数据
     * @author: jinzhm
     * @time:2017年5月11日 下午4:42:17
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public List<Map<String, Object>> searchRedeemListForExport(Map<String, Object> paramMap);

    /**
     * @Title: saveWmsInveRedeemPTP
     * @Description: 保存ptp赎回表信息
     * @param wmsInveRedeemRecordVO 
     * @author: zhangyunfei
     * @time:2017年6月23日 下午2:43:44
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    public void saveWmsInveRedeemPTP(WmsInveRedeemRecordVO wmsInveRedeemRecordVO);
}