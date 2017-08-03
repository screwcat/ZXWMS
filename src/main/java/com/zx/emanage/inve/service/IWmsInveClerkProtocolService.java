package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocolTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveClerkProtocolService {

    /**
     * @Title: getWmsInveClerkProtocolId
     * @Description: 获得柜员协议表id
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param type 数据类型： 2续期单据，3赎回单据
     * @return 获得协议主键
     * @author: jinzhm
     * @time:2017年2月20日 上午8:23:28
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    public Integer getWmsInveClerkProtocolId(Integer transaId, BigDecimal productAccount, int type);

    /**
     * @Title: getWmsInveClerkProtocolById
     * @Description: 通过主键查询合同相关信息
     * @param WmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月9日 下午2:59:27
     * history:
     * 1、2017年2月9日 Administrator 创建方法
    */
    public Map<String, Object> getWmsInveClerkProtocolById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: changeProtocolCredit
     * @Description: 更换债权包信息
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param categoryId 产品主键
     * @param user 登录用户信息
     * @return 债权更换结果
     *  1表示匹配成功
     *  2表示产品不是可拆分类型
     *  3表示没有要到期的债权包
     *  -1表示债权匹配失败
     * @author: jinzhm
     * @time:2017年2月15日 上午11:24:07
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public int changeProtocolCredit(int transaId, int protocolId, int categoryId, UserBean user);

    /**
     * @Title: autoChangeCreditForTransaWithoutPaperProtocol
     * @Description: 为不提供纸质合同的单据自动更换到期债权信息 
     * @author: jinzhm
     * @time:2017年7月18日 下午1:49:24
     * history:
     * 1、2017年7月18日 jinzhm 创建方法
     */
    public void autoChangeCreditForTransaWithoutPaperProtocol();

    /**
     * @Title: save
     * @Description: 上单时合同打印调用
     * @param begin_of_date
     * @param user
     * @return 
     * @author: Guanxu
     * @param wms_inve_transa_id 
     * @time:2017年2月14日 上午10:44:24
     * history:
     * 1、2017年2月14日 Guanxu 创建方法
    */
    String save(String begin_of_date, Integer wms_inve_transa_id, UserBean user, String protocol_type, String taskId, String prot_code);

    /**
     * @Title: getWmsInveClerkProtocolList
     * @Description: 客户合同查询
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午2:57:28
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    public Map<String, Object> getWmsInveClerkProtocolList(WmsInveClerkProtocolSearchBeanVO queryInfo, UserBean user);

    /**
     * @Title: updateProtocolActEndOfDateById
     * @Description: 根据合同表主键更新柜员合同表实际到期日为当前时间
     * @param wms_inve_clerk_protocol_id 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午12:59:19
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    public void updateProtocolActEndOfDateById(Integer wms_inve_clerk_protocol_id);

    /**
     * @Title: getWmsInveClerkProtocolByEntity
     * @Description: 根据上单主键和出借金额查询柜员合同
     * @param wmsInveClerkProtocol
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午1:33:09
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByEntity(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: saveExtendOrRedeemOfflineProtocol
     * @Description: 保存续签和改签线下补录合同信息
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param protocolCode 合同编号
     * @param type 业务类型 2：续期单据；3：赎回单据
     * @param user 登录用户信息
     * @author: jinzhm
     * @return 
     * @time:2017年2月17日 上午9:03:15
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public String saveExtendOrRedeemOfflineProtocol(Integer transaId, BigDecimal productAccount, String protocolCode,
                                                  String type, UserBean user);

    /**
     * @Title: saveWmsInveClerkProtocolRedeem
     * @Description: 预约部分赎回生成柜员合同
     * @param WmsInveRedeem  WmsInveRedeemDetail user
     * @author: zhangyunfei
     * @time:2017年2月16日 下午1:25:17
     * history:
     * 1、2017年2月16日 Administrator 创建方法
    */
    public void saveWmsInveClerkProtocolRedeem(WmsInveRedeem wmsInveRedeem, WmsInveRedeemDetail wmsInveRedeemDetail, UserBean user);

    /**
     * @Title: updateWmsInveClerkProtocolRedeem
     * @Description: 根据主键  更新柜员协议表金额和enable_flag
     * @param wmsInveRedeem
     * @param wmsInveRedeemDetail
     * @param user 
     * @author: zhangyunfei
     * @param wmsInveRedeemDetail 
     * @param bean 
     * @time:2017年2月16日 下午6:15:48
     * history:
     * 1、2017年2月16日 Administrator 创建方法
    */
    public void updateWmsInveClerkProtocolRedeem(WmsInveClerkProtocol protocol, WmsInveRedeem bean, WmsInveRedeemDetail wmsInveRedeemDetail, UserBean user);

    /**
     * @Title: saveRedeemProtocol
     * @Description: 保存合同改签数据
     * @param transaId 上单主键
     * @param productAccount 投资金额（赎回时，如果没有打印合同的情况下多次进行赎回时，需要根据投资金额分辨合同）
     * @param user 登录用户信息
     * @return 合同主键
     * @author: jinzhm
     * @time:2017年2月17日 下午2:09:48
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public String saveRedeemProtocol(Integer transaId, BigDecimal productAccount, UserBean user);

    /**
     * @Title: saveExtendProtocol
     * @Description: 保存合同续签数据
     * @param transaId 上单主键
     * @param user 登录用户信息
     * @return 合同主键
     * @author: jinzhm
     * @time:2017年2月17日 下午3:31:40
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public String saveExtendProtocol(Integer transaId, UserBean user);

    /**
     * @Title: disableWmsInveClerkProtocol
     * @Description: 失效柜员协议表单据 和 柜员业务表单据
     * @param wms_inve_redeem_id
     * @author: zhangyunfei
     * @time:2017年2月17日 上午9:19:21
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public void disableWmsInveClerkProtocol(Integer wms_inve_redeem_id);

    /**
     * @Title: matchForOrderRedeemClerkProtocol
     * @Description:  查询到达预约赎回日的柜员协议集合并匹配债权
     * @param date 
     * @author: zhangyunfei
     * @return 
     * @time:2017年2月17日 下午4:37:07
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public String matchForOrderRedeemClerkProtocol(String date);

    /**
     * @Title: updatePreWmsInveClerkProtocolEndOfDate
     * @Description: 更新原单据到期日  释放原合同债权
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年2月17日 下午6:09:32
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public void updatePreWmsInveClerkProtocolEndOfDate(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: getWmsInveClaimsInfos
     * @Description: 根据上单表主键和柜员协议表主键获取债权匹配信息
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_clerk_protocol_id 柜员协议表主键
     * @return 
     * @author: DongHao
     * @time:2017年2月17日 下午6:33:17
     * history:
     * 1、2017年2月17日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveClaimsInfos(String wms_inve_transa_id, String wms_inve_clerk_protocol_id);

    /**
     * @Title: deleteClerkDataAndClerkProtocol
     * @Description: 赎回操作时需要删除掉预约续期的协议表和柜员业务表数据
     * @param wms_inve_transa_id 
     * @author: zhangyunfei
     * @time:2017年2月20日 上午9:26:44
     * history:
     * 1、2017年2月20日 Administrator 创建方法
    */
    public void deleteClerkDataAndClerkProtocol(Integer wms_inve_transa_id);

    /**
     * @Title: saveWmsInveClerkData
     * @Description:  赎回退回修订时保存柜员业务单据
     * @param protocol
     * @param wmsInveRedeem
     * @param wmsInveRedeemDetail
     * @param user 
     * @author: zhangyunfei
     * @time:2017年2月22日 上午10:20:18
     * history:
     * 1、2017年2月22日 Administrator 创建方法
    */
    void saveWmsInveClerkData(WmsInveClerkProtocol protocol, WmsInveRedeem wmsInveRedeem, WmsInveRedeemDetail wmsInveRedeemDetail, UserBean user);

    /**
     * @Title: getWmsInveClerkProtocolWithoutpagingList
     * @Description: 客户合同查询 不分页
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月22日 下午3:23:42
     * history:
     * 1、2017年2月22日 Administrator 创建方法
    */
    public Map<String, Object> getWmsInveClerkProtocolWithoutpagingList(WmsInveClerkProtocolSearchBeanVO queryInfo);

    /**
     * @Title: exportProtocolpdf
     * @Description: 预约部分赎回/预约续期  定时任务下载合同
     * @param wmsInveClerkProtocolSearchBeanVO 
     * @author: zhangyunfei
     * @time:2017年3月10日 下午5:18:45
     * history:
     * 1、2017年3月10日 Administrator 创建方法
     */
    public List<Map<String, String>> exportProtocolpdf(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: getClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回柜员协议信息
     * @author: DongHao
     * @time:2017年3月22日 上午10:04:38
     * history:
     * 1、2017年3月22日 DongHao 创建方法
    */
    public Map<String, Object> getClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: savePublicOrCurrentProtocol
     * @Description: 根据当前柜员合同状态 生成公益合同或活期合同
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月17日 下午6:20:42
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public void savePublicOrCurrentProtocol();

    /**
     * @Title: saveWmsInveClerkProtocol
     * @Description: 生成柜员合同(公益/活期) 并保存债权匹配关系
     * @param paramsMap
     * @param wmsInveClerkPublicProtocol 
     * @author: zhangyunfei
     * @time:2017年5月17日 上午9:41:44
     * history:
     * 1、2017年5月16日 Administrator 创建方法
    */
    public void saveWmsInveClerkProtocol(Map<String, Object> paramsMap, WmsInveClerkProtocol wmsInveClerkPublicProtocol);

    /**
     * @Title: getWithoutPaperProtocolTransaProtocolInfoList
     * @Description: 根据手机号查询所有购买无纸质合同的单据信息集合
     * @param 手机号
     * @return 购买无纸质合同的单据信息集合
     * @author: jinzhm
     * @time:2017年7月20日 下午5:45:59
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getWithoutPaperProtocolTransaProtocolInfoList(String mobile_phone);

    /**
     * @Title: getWmsInveClaimsInfosWithoutZHAO
     * @Description: 如果该匹配历史他项人全部为赵燕国 返回false 否则返回true
     * @param wms_inve_transa_id
     * @param wms_inve_clerk_protocol_id
     * @param wms_inve_clerk_protocol_transa_crepkg_id 
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月19日 下午3:08:40
     * history:
     * 1、2017年5月19日 Administrator 创建方法
    */
    public Map<String, String> getWmsInveClaimsInfosWithoutZHAO(String wms_inve_transa_id, String wms_inve_clerk_protocol_id, String wms_inve_clerk_protocol_transa_crepkg_id);
    /**
     * @Title: getWmsInveClerkProtocolByTransaIdForPhone
     * @Description: 手持查询端查询柜员协议
     * @param transa_id 
     * @author: zhangyunfei
     * @return 
     * @time:2017年7月20日 下午2:05:25
     * history:
     * 1、2017年7月20日 zhangyunfei 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByTransaIdForPhone(String transa_id, String product_account);

    /**
     * @Title: getWmInveClerkProtocolTransaCrepkg
     * @Description: 根据上单主键和柜员合同主键查询 债权匹配历史表主键
     * @param wms_inve_transa_id
     * @param wms_inve_clerk_protocol_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午2:37:23
     * history:
     * 1、2017年7月20日 admin 创建方法
    */
    public WmsInveClerkProtocolTransaCrepkg getWmInveClerkProtocolTransaCrepkg(Integer wms_inve_transa_id, Integer wms_inve_clerk_protocol_id);

    /**
     * @Title: getWmsInveClerkProtocolCreateUserById
     * @Description: 查询合同的原始创建人
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午4:18:57
     * history:
     * 1、2017年7月21日 zhangyunfei 创建方法
    */
    public UserBean getWmsInveClerkProtocolCreateUserById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: getLastWmsInveClaimsInfo
     * @Description: 根据上单主键和合同主键查询债权匹配历史 按倒叙排序
     * @param transa_id
     * @param protocol_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午5:02:40
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    public List<Map<String, Object>> getLastWmsInveClaimsInfo(String transa_id, String protocol_id);

    /**
     * @Title: checkWmsInveClerkProtocolOper
     * @Description: 检查是否可以对柜员合同进行操作(23-1不允许操作 内部/外部 /更换债权)
     * @param wms_inve_pruduct_category_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午7:28:56
     * history:
     * 1、2017年7月21日 zhangyunfei 创建方法
    */
    public String checkWmsInveClerkProtocolOper(Integer wms_inve_pruduct_category_id);

    /**
     * @Title: phoneGetWmsCusProtocol
     * @Description: 微信公众号ZSH调用 通过上单主键 协议编号 协议主键查询主合同和补充协议相关信息
     * @param wmsInveClerkProtocolSearchBeanVO 
     * @author: zhangyunfei
     * @return 
     * @time:2017年7月24日 上午9:09:35
     * history:
     * 1、2017年7月24日 zhangyunfei 创建方法
    */
    public Map<String, Object> phoneGetWmsCusProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: phoneGetWmsCusInterProtocolInfo
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询内部转让协议相关信息
     * @param wmsInveClerkProtocolSearchBeanVO
     * @author: zhangyunfei
     * @time:2017年7月24日 上午11:21:45
     * history:
     * 1、2017年7月24日 zhangyunfei 创建方法
    */
    public Map<String, Object> phoneGetWmsCusInterProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: phoneGetWmsCusM2MProtocolInfo
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询外部转让协议相关信息
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @time:2017年7月24日 上午11:27:35
     * history:
     * 1、2017年7月24日 admin 创建方法
    */
    public Map<String, Object> phoneGetWmsCusM2MProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: phoneGetWmsCusMainProtocolInfo
     * @Description: 微信公众号ZSH调用 通过上单主键 协议主键查询主议相关信息
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月24日 下午1:44:20
     * history:
     * 1、2017年7月24日 admin 创建方法
    */
    public Map<String, Object> phoneGetWmsCusMainProtocolInfo(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);
}