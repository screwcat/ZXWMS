package com.zx.emanage.inve.persist;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.vo.UserBean;

@MyBatisRepository
public interface WmsInveClerkProtocolDao extends BaseDao<WmsInveClerkProtocol> {

    /**
     * @Title: getWmsInveClerkProtocolById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月9日 下午3:14:29
     * history:
     * 1、2017年2月9日 Administrator 创建方法
    */
    Map<String, Object> getWmsInveClerkProtocolById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: getWmsInveClerkProtocolList
     * @Description: 查询合同列表
     * @param paramMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午3:28:31
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    public List<Map<String, Object>> getWmsInveClerkProtocolList(Map<String, Object> paramMap);

    /**
     * @Title: findCountProtocol
     * @Description: 根据查询条件返回理财查询记录总条数
     * @param paramMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午3:38:51
     * history:
     * 1、2017年2月14日 Administrator 创建方法
    */
    public int findCountProtocol(Map<String, Object> paramMap);

    /**
     * @Title: updateProtocolActEndOfDateById
     * @Description: 根据合同表主键更新柜员合同表实际到期日
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午1:08:36
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    public void updateProtocolActEndOfDateById(Integer wms_inve_clerk_protocol_id);

    /**
     * @Title: getWmsInveClerkProtocolByEntity
     * @Description: 查询柜员合同
     * @param wmsInveClerkProtocol
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午1:40:45
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByEntity(WmsInveClerkProtocol wmsInveClerkProtocol);
    
    /**
     * @Title: getListByWmsInveClerkProtocol
     * @Description: 通过主键查询柜员协议集合  按主键倒序排序
     * @param wmsInveClerkProtocol
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月16日 下午2:11:24
     * history:
     * 1、2017年2月16日 Administrator 创建方法
    */
    public List<WmsInveClerkProtocol> getListByWmsInveClerkProtocol(WmsInveClerkProtocol wmsInveClerkProtocol);


    /**
     * @Title: getWmsInveClerkProtocolByTransaId
     * @Description: 通过上单表主键查询封装柜员协议表实体类
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月15日 下午5:29:06
     * history:
     * 1、2017年2月15日 Administrator 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByTransaIdWithCode(Integer wms_inve_transa_id);

    /**
     * @Title: getWmsInveRedeemProtocolByTransaId
     * @Description: 通过上单主键查询合同改签协议
     * @param transaId 上单主键
     * @return 协议信息集合
     * @author: jinzhm
     * @time:2017年2月17日 下午2:49:31
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public List<WmsInveClerkProtocol> getWmsInveRedeemProtocolByTransaId(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveExtendProtocolByTransaId
     * @Description: 根据上单主键查询合同续签协议
     * @param transaId 上单主键
     * @return 协议信息集合
     * @author: jinzhm
     * @time:2017年2月18日 下午12:19:20
     * history:
     * 1、2017年2月18日 jinzhm 创建方法
     */
    public List<WmsInveClerkProtocol> getWmsInveExtendProtocolByTransaId(Integer transaId);

    /**
     * @Title: updateProtocolCode
     * @Description: 更新合同合同编号
     * @param paramMap 
     * @author: jinzhm
     * @time:2017年2月17日 下午3:04:42
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    public void updateProtocolCode(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveClerkProtocolByWmsInveTransaIdAndIsOrderExtend
     * @Description: 根据原单据的上单主键查询柜员协议表中预约续期的协议  带合同编号
     * @param paramsMap
     * @return WmsInveClerkProtocol对象
     * @author: DongHao
     * @time:2017年2月16日 上午11:21:07
     * history:
     * 1、2017年2月16日 DongHao 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByWmsInveTransaIdAndIsOrderExtend(Map<String, Object> paramsMap);

    /**
     * @Title: disableWmsinveclerkprotocol
     * @Description: 失效（生效）柜员协议表单据
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年2月17日 上午10:09:56
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public void disableWmsinveclerkprotocol(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: updatePreWmsInveClerkProtocolEndOfDate
     * @Description: 更新原合同实际到期日
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年2月17日 下午6:17:58
     * history:
     * 1、2017年2月17日 Administrator 创建方法
    */
    public void updatePreWmsInveClerkProtocolEndOfDate(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: deleteClerkProtocolByWmsInveTransaIdAndIsOrderExtend
     * @Description: 根据上单表主键和是否预约续期进行更新enable_flag=0
     * @param protocol 
     * @author: DongHao
     * @time:2017年2月17日 下午5:46:42
     * history:
     * 1、2017年2月17日 DongHao 创建方法
    */
    void deleteClerkProtocolByWmsInveTransaIdAndIsOrderExtend(WmsInveClerkProtocol protocol);

    /**
     * @Title: getWmsInveClaimsInfos
     * @Description: 根据上单表主键和柜员协议表主键获取债权匹配信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月17日 下午6:35:48
     * history:
     * 1、2017年2月17日 DongHao 创建方法
    */
    LinkedList<Map<String, Object>> getWmsInveClaimsInfos(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveClerkProtocolByTransaIdWithoutCode
     * @Description: 根据原单据的上单主键查询柜员协议表中预约续期的协议 不带合同编号
     * @param wms_inve_transa_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月20日 下午5:01:21
     * history:
     * 1、2017年2月20日 Administrator 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkProtocolByTransaIdWithoutCode(Integer wms_inve_transa_id);

    /**
     * @Title: getWmsInveClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取合同表信息
     * @param paramsMap
     * @return 
     * @author: DongHao
     * @time:2017年2月28日 下午9:20:29
     * history:
     * 1、2017年2月28日 DongHao 创建方法
    */
    WmsInveClerkProtocol getWmsInveClerkProtocolByWmsInveTransaId(Map<String, Object> paramsMap);

    /**
     * @Title: getOldWmsInveClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年3月1日 上午5:59:05
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    WmsInveClerkProtocol getOldWmsInveClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: findWmsInveClerkProtocolByInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回柜员协议信息
     * @author: DongHao
     * @time:2017年2月24日 下午3:49:10
     * history:
     * 1、2017年2月24日 DongHao 创建方法
    */
    WmsInveClerkProtocol findWmsInveClerkProtocolByInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回柜员协议信息
     * @author: DongHao
     * @time:2017年3月22日 上午10:07:03
     * history:
     * 1、2017年3月22日 DongHao 创建方法
    */
    WmsInveClerkProtocol getClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getWmsInveClerkProtocolByTransaIdAndAccount
     * @Description: 根据上单主键和金额查找对应的柜员合同记录
     * @param paramMap 条件
     * @return 柜员协议数据
     * @author: jinzhm
     * @time:2017年3月28日 上午10:09:22
     * history:
     * 1、2017年3月28日 jinzhm 创建方法
     */
    List<WmsInveClerkProtocol> getWmsInveClerkProtocolByTransaIdAndAccount(Map<String, Object> paramMap);

    /**
     * @Title: getNewestWmsInveClerkProtocolByTransaId
     * @Description: 根据上单主键获取一条最新的柜员合同信息
     * @param transaId 上单主键
     * @return 柜员合同信息
     * @author: jinzhm
     * @time:2017年4月7日 下午4:57:49
     * history:
     * 1、2017年4月7日 jinzhm 创建方法
     */
    WmsInveClerkProtocol getNewestWmsInveClerkProtocolByTransaId(int transaId);

    /**
     * @Title: updateWmsinveclerkprotocolBankInfo
     * @Description: 上单审核退回 修订收益卡 同步更新柜员合同表客户收益卡信息
     * @param cmap 
     * @author: zhangyunfei
     * @time:2017年4月13日 下午1:12:12
     * history:
     * 1、2017年4月13日 Administrator 创建方法
    */
    public void updateWmsinveclerkprotocolBankInfo(Map<String, Object> cmap);

    /**
     * @Title: getClerkProtocolByWmsInveTransaIdToWmsInveTransa
     * @Description: 根据上单表主键获取合同编号
     * @param wms_inve_transa_id 上单表主键
     * @return 返回合同信息
     * @author: DongHao
     * @time:2017年4月24日 上午12:52:28
     * history:
     * 1、2017年4月24日 DongHao 创建方法
    */
    Map<String, Object> getClerkProtocolByWmsInveTransaIdToWmsInveTransa(Integer wms_inve_transa_id);

    /**
     * @Title: getClerkProtocolProtCodeCount
     * @Description: 查询是否存在当前合同编号的柜员合同 返回数量
     * @param prot_code 
     * @author: zhangyunfei
     * @time:2017年5月11日 上午9:12:10
     * history:
     * 1、2017年5月11日 Administrator 创建方法
    */
    public int getClerkProtocolProtCodeCount(String prot_code);

    /**
     * @Title: getWmsInveClerkPublicProtocol
     * @Description: 根据上单主键和赎回主键查询合同信息  生成一条公益合同记录 
     * @param wmsInveClerkProtocol
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月19日 下午5:13:25
     * history:
     * 1、2017年5月19日 Administrator 创建方法
    */
    public WmsInveClerkProtocol getWmsInveClerkPublicProtocol(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: getCanBePublicOrCurrentProtocolList
     * @Description: 获取可以生成公益合同或活期合同的柜员合同(柜员合同主键、上单主键、合同到期日期)
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月17日 上午9:02:18
     * history:
     * 1、2017年5月17日 Administrator 创建方法
    */
    public List<WmsInveClerkProtocol> getCanBePublicOrCurrentProtocolList();

    /**
     * @Title: updateProtocolActEndOfDateByParams
     * @Description: 更新原合同实际到期日
     * @param paramsMap 
     * @author: zhangyunfei
     * @time:2017年5月17日 上午9:03:59
     * history:
     * 1、2017年5月17日 Administrator 创建方法
    */
    public void updateProtocolActEndOfDateByParams(Map<String, Object> paramsMap);

    /**
     * @Title: updatePubProtocolActEndOfDateById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param paramsMap 
     * @author: zhangyunfei
     * @time:2017年5月17日 上午9:04:40
     * history:
     * 1、2017年5月17日 Administrator 创建方法
    */
    public void updatePubProtocolActEndOfDateById(Map<String, Object> paramsMap);

    /**
     * @Title: saveWmsInveClerkProtocol
     * @Description: 保存柜员合同 并且柜员返回合同主键
     * @param wmsInveClerkProtocol 
     * @author: zhangyunfei
     * @time:2017年5月17日 上午9:05:31
     * history:
     * 1、2017年5月17日 Administrator 创建方法
    */
    public void saveWmsInveClerkProtocol(WmsInveClerkProtocol wmsInveClerkProtocol);

    /**
     * @Title: getAllCusProtocolWithoutPaperTransaInfoList
     * @Description: 根据手机号查询所有购买无纸质合同的单据信息集合
     * @return 购买无纸质合同的单据信息集合
     * @author: jinzhm
     * @time:2017年7月20日 下午5:43:40
     * history:
     * 1、2017年7月20日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getAllCusProtocolWithoutPaperTransaInfoList(Map<String, Object> paramMap);

    /**
     * @Title: getWithoutPaperProtocolTransaProtocolInfoList
     * @Description: 查询无纸质合同的单据合同信息
     * @return 单据合同产品主键集合
     * @author: jinzhm
     * @time:2017年7月18日 下午2:30:10
     * history:
     * 1、2017年7月18日 jinzhm 创建方法
     */
    public List<Map<String, Object>> getWithoutPaperProtocolTransaProtocolInfoList();

    /**
     * @Title: getWmsInveClerkProtocolByTransaIdForPhone
     * @Description: 手持查询端查询柜员协议
     * @param paramsMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午2:08:33
     * history:
     * 1、2017年7月20日 zhangyunfei 创建方法
    */
    public List<WmsInveClerkProtocol> getWmsInveClerkProtocolByTransaIdForPhone(Map<String, Object> paramsMap);

    /**
     * @Title: getWmsInveClerkProtocolCreateUserById
     * @Description: 查询合同的原始创建人
     * @param wmsInveClerkProtocolSearchBeanVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午4:20:26
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    public UserBean getWmsInveClerkProtocolCreateUserById(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: getLastWmsInveClaimsInfo
     * @Description: 根据上单主键和合同主键  查询出最新的债权匹配记录
     * @param paramsMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午5:19:31
     * history:
     * 1、2017年7月21日 admin 创建方法
    */
    public List<Map<String, Object>> getLastWmsInveClaimsInfo(Map<String, Object> paramsMap);

}