package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IWmsInveTransaCrepkgService
 * 模块名称：债权单据关联信息service接口
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
public interface IWmsInveTransaCrepkgService
{

    /**
     * @Title: getAllTransaCrepkg
     * @Description: 根据上单id和协议id获得所有匹配的债权包信息
     * @param transaId 上单主键
     * @param protocolId 协议表主键
     * @return 关联的债权包集合
     * @author: jinzhm
     * @time:2017年2月11日 上午10:34:55
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public Map<String, Object> getAllTransaCrepkg(Integer transaId, Integer protocolId);

    /**
     * @Title: getAllTransaCrepkgByGroup
     * @Description: 根据上单id和协议id获得所有匹配的债权包信息
     * @param transaId
     * @param protocolId
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月11日 下午1:49:43
     * history:
     * 1、2017年2月11日 Administrator 创建方法
    */
    public List<Map<String, Object>> getAllTransaCrepkgByGroup(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO);

    /**
     * @Title: groupTransaCrepkgList
     * @Description: 将list按照他项人id进行分组（rele_per_id） 打印内部转让合同需要根据不同他项人 打印多张合同
     * @param transaCrepkgList
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月13日 下午3:48:11
     * history:
     * 1、2017年2月13日 Administrator 创建方法
    */
    public Map<String, List<Map<String, Object>>> groupTransaCrepkgList(List<Map<String, Object>> transaCrepkgList);

    /**
     * @Title: queryCrepkgByPk
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_credit_package_id
     * @return 
     * @author: Guanxu
     * @time:2017年2月21日 下午6:25:26
     * history:
     * 1、2017年2月21日 Guanxu 创建方法
    */
    public Map<String, Object> queryCrepkgByPk(Integer wms_inve_credit_package_id, String disacl_type);
}
