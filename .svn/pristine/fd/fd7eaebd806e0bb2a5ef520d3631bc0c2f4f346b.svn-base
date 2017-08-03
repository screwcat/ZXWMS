package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveRedeemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveRedeemVO;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemAtt;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveRedeemService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPagingForQuerySH(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @param user
     * @return record list
     * @author hancd
     */
    public Map<String, Object> getListWithPaging(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :赎回查询分页数据
     * 
     * @param queryInfo
     * @param user
     * @return record list
     * @author ry
     */
    public Map<String, Object> getListWithPagingForQuerySH(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveRedeemVO
     * @author auto_generator
     */
    public WmsInveRedeem getInfoByPK(Integer wms_inve_redeem_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveRedeem bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveRedeem bean, UserBean user);

    public Map<String, Object> getWmsInveredeemList(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    public List<WmsInveTransaCard> getCardListForSelect(Integer wms_inve_redeem_id);

    /**
     * 保存赎回申请信息
     * 
     * @param queryInfo
     * @return
     * @author 朱博
     * @param is_update 
     */
    public String saveWmsInveRedeem(WmsInveRedeem bean, UserBean user, WmsInveRedeemDetail wmsInveRedeemDetail, WmsInveRedeemVO wmsInveRedeemVO, Integer... type);

    /**
     * 获取赎回修订列表
     * 
     * @param queryInfo
     * @param user
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> checkReturnList(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * 导出赎回修订列表
     * 
     * @param queryInfo
     * @param user
     * @return record list
     * @author zhubo
     */
    public Map<String, Object> checkReturnListWithout(WmsInveRedeemSearchBeanVO queryInfo, UserBean user);

    /**
     * 保存回款信息
     * 
     * @param queryInfo
     * @return
     * @author 张风山
     */
    public String saveRedeemPayBackInfo(WmsInveRedeem bean, UserBean user, String fileArr,
    									WmsInveDebtWorkFlowVO wmsinvedebtworkflowvo);

    /**
     * 保存特批信息
     * 
     * @param queryInfo
     * @return
     * @author 张风山
     */
    public String saveRedeemspecialapprovalInfo(WmsInveRedeem bean, UserBean user,
    											WmsInveDebtWorkFlowVO wmsinvedebtworkflowvo);

    /**
     * 修改赎回申请信息
     * 
     * @param queryInfo
     * @return
     * @author 朱博
     */
    public String updateWmsInveRedeem(WmsInveRedeem bean, UserBean user,WmsInveRedeemDetail wmsInveRedeemDetail,String redeemGridData, String taskId,
                                      String cxdeptid);

    /**
     * 取消赎回申请信息
     * 
     * @param queryInfo
     * @return
     * @author 朱博
     * @param  
     */
    public String cancelWmsInveRedeem(UserBean user, WmsInveDebtWorkFlowVO vo);

    /**
     * 导出excel 回款 债权匹配 签订协议列表
     * 
     * @param queryInfo
     * @param user
     * @return baisong
     */
	public void getWmsInveRedeemListforexl(
			WmsInveRedeemSearchBeanVO queryInfo, UserBean user,
			HttpServletResponse response);

    /**
     * 获取赎回附件信息
     * 
     * @param queryInfo
     * @return
     * @author 朱博
     */
    public List<WmsInveRedeemAtt> getAttList(Integer wms_inve_redeem_id);
    
    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param user_id
     * @param user
     * @return record list
     * @author baisong
     */
    public Map<String, Object> phoneGetBackInfo(String personnel_shortcode,String searchInfo);
    /**
     * Description :获取赎回信息 和赎回审批历史
     * 
     * @param user_id
     * @param user
     * @return record list
     * @author baisong
     */
    public Map<String, Object> phoneGetBackInfoAndHistory(String wms_inve_redeem_id);
/**
 * 
 * 理财赎回特批列表显示
 * @param queryInfo
 * @param user
 * @return
 * @author jiaodelong
 */
	public Map<String, Object> getSpecialRedemptionList(
			WmsInveRedeemSearchBeanVO queryInfo, UserBean user);
	
	    /**
     * @Title: getRedeemApplyinfoByPk
     * @Description:  通过上单主键和赎回主键查询赎回相信信息
     * @param wms_inve_transa_id
     * @param wms_inve_redeem_id
     * @param is_return    是否是审核退回修订进行的查询
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月3日 下午3:02:30
     * history:
     * 1、2017年5月3日 Administrator 创建方法
     */
    public Map<String, Object> getRedeemApplyinfoByPk(Integer wms_inve_transa_id, Integer wms_inve_redeem_id, String is_return);

    /**
     * @Title: cancelWmsInveRedeemByCw
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param user
     * @param vo
     * @return 
     * @author: Guanxu
     * @time:2016年11月7日 上午11:06:21
     * history:
     * 1、2016年11月7日 Guanxu 创建方法
    */
    String cancelWmsInveRedeemByCw(UserBean user, WmsInveDebtWorkFlowVO vo);

    /**
     * @Title: getRedeemApplyInfoByPkMoa
     * @Description: 通过主键查询单据的赎回详细信息
     * @param wms_inve_redeem_id
     * @param personnel_id 当前登录MOA系统的人员id
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月7日 上午11:28:37
     * history:
     * 1、2016年12月7日 Administrator 创建方法
     * 2、 2016年12月15日 DongHao 修改方法  在方法中添加当前登录MOA系统的人员id
    */
    Map<String, Object> getRedeemApplyInfoByPkMoa(Integer wms_inve_redeem_id, Integer personnel_id);

    /**
     * @Title: phoneGetPendingApprovalInfo
     * @Description: app首页查询代办事项
     * @param personnel_shortCode app端登录人员的短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午3:43:45
     * history:
     * 1、2016年12月6日 DongHao 创建方法
    */
    public Map<String, Object> phoneGetPendingApprovalInfo(String personnel_shortCode, String searchInfo, String version);

    /**
     * @Title: phoneGetRedeemByQueryInfo
     * @Description: 根据条件进行查询赎回单据信息
     * @param personnel_shortCode app端登录的用户短工号
     * @param searchInfo 查询参数包括(客户姓名/客户经理姓名/客户经理短工号)
     * @param query_type 查询的类型
     * @param page
     * @param page_size
     * @return 
     * @author: DongHao
     * @time:2016年12月6日 下午5:33:53
     * history:
     * 1、2016年12月6日 DongHao 创建方法
    */
    public Map<String, Object> phoneGetRedeemByQueryInfo(String personnel_shortCode, String searchInfo, String query_type, int page, int page_size, String version);

    /**
     * @Title: wmsPhoneQueryType
     * @Description: 根据员工的短工号进行获取对应的信息
     * @param personnel_shortCode
     * @return 
     * @author: DongHao
     * @time:2016年12月8日 下午3:00:30
     * history:
     * 1、2016年12月8日 DongHao 创建方法
    */
    public Map<String, Object> wmsPhoneQueryType(String personnel_shortCode);

    /**
     * @Title: phoneGetPendingApprovalInfoCountWMS
     * @Description: 获取代办任务数量
     * @param personnel_shortCode
     * @param searchInfo
     * @return 
     * @author: DongHao
     * @time:2016年12月14日 上午8:33:06
     * history:
     * 1、2016年12月14日 DongHao 创建方法
    */
    public Map<String, Object> phoneGetPendingApprovalInfoCountWMS(String personnel_shortCode, String searchInfo);

    /**
     * @Title: exportFinancialRedeem
     * @Description: 财务主管导出赎回报表
     * @param queryInfo 查询条件对象
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月10日 下午5:11:48
     * history:
     * 1、2017年5月10日 jinzhm 创建方法
     */
    public void exportFinancialRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response);

    /**
     * @Title: exportRedeem
     * @Description: 柜员主管导出赎回报表
     * @param queryInfo 查询条件对象
     * @param response 响应信息
     * @author: jinzhm
     * @time:2017年5月11日 下午4:09:54
     * history:
     * 1、2017年5月11日 jinzhm 创建方法
     */
    public void exportRedeem(WmsInveRedeemSearchBeanVO queryInfo, HttpServletResponse response);

    /**
     * @Title: saveWmsInveRedeemInfoPTP
     * @Description: 定时任务生成PTP赎回单据相关信息 
     * @return 
     * @author: zhangyunfei
     * @time:2017年6月23日 上午9:50:47
     * history:
     * 1、2017年6月23日 zhangyunfei 创建方法
    */
    public void saveWmsInveRedeemInfoPTP();
}