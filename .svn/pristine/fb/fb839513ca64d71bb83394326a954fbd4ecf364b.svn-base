package com.zx.emanage.inve.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.service.IWmsInveTransaCrepkgService;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveTransaCrepkgServiceImpl
 * 模块名称：单据债权关联信息service接口实现类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
@Service("wmsInveTransaCrepkgService")
public class WmsInveTransaCrepkgServiceImpl implements IWmsInveTransaCrepkgService
{

    @Autowired
    private WmsInveTransaCrepkgDao wmsInveTransaCrepkgDao;

    @Autowired
    private WmsInveClerkProtocolDao wmsInveClerkProtocolDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    /**
     * @Title: getAllTransaCrepkg
     * @Description: 根据上单主键和协议主键查找所有关联的债权信息
     * @param transaId 上单主键
     * @param protocolId 协议主键
     * @return 债权集合
     * @author: jinzhm
     * @time:2017年2月11日 上午10:36:15
     * @see com.zx.emanage.inve.service.IWmsInveTransaCrepkgService#getAllTransaCrepkg(java.lang.Integer, java.lang.Integer)
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getAllTransaCrepkg(Integer transaId, Integer protocolId)
    {
        // 如果传入了合同主键
        if (protocolId != null)
        {
            // 封装查询条件
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transaId", transaId);
            paramMap.put("protocolId", protocolId);

            // 查询关联债权信息
            List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgDao.queryAllTransaCrepkg(paramMap);

            // 封装返回结果格式
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("Rows", transaCrepkgList);

            return resMap;
        }
        else
        {
            // 查询上单产品表中的理财金额
            WmsInveTransaProd prod = new WmsInveTransaProd();
            prod.setWms_inve_transa_id(transaId);
            prod.setEnable_flag("1");
            prod = wmsInveTransaProdDao.getListByEntity(prod).get(0);

            // 根据上单主键及金额查询柜员合同信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("transaId", transaId);
            paramMap.put("productAccount", prod.getProduct_account());
            List<WmsInveClerkProtocol> clerkProtocolList = wmsInveClerkProtocolDao.getWmsInveClerkProtocolByTransaIdAndAccount(paramMap);

            if (clerkProtocolList.isEmpty())
            {
                paramMap = new HashMap<String, Object>();
                paramMap.put("transaId", transaId);
                // 查询关联债权信息
                List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgDao.queryAllTransaCrepkg(paramMap);

                // 封装返回结果格式
                Map<String, Object> resMap = new HashMap<String, Object>();
                resMap.put("Rows", transaCrepkgList);

                return resMap;
            }
            else
            {
                // 封装查询条件
                paramMap = new HashMap<String, Object>();
                paramMap.put("transaId", transaId);
                paramMap.put("protocolId", clerkProtocolList.get(0).getWms_inve_clerk_protocol_id());

                // 查询关联债权信息
                List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgDao.queryAllTransaCrepkg(paramMap);

                // 封装返回结果格式
                Map<String, Object> resMap = new HashMap<String, Object>();
                resMap.put("Rows", transaCrepkgList);

                return resMap;
            }
        }
    }

    /**
     * 
     * @Title: getAllTransaCrepkgByGroup
     * @Description: 根据上单id和协议id获得所有匹配的债权包信息
     * @param transaId
     * @param protocolId
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月11日 下午1:57:29
     * @see com.zx.emanage.inve.service.IWmsInveTransaCrepkgService#getAllTransaCrepkgByGroup(java.lang.Integer, java.lang.Integer)
     * history:
     * 1、2017年2月11日 Administrator 创建方法
     */
    @Override
    public List<Map<String, Object>> getAllTransaCrepkgByGroup(WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO)
    {
        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", wmsInveClerkProtocolSearchBeanVO.getWms_inve_transa_id());
        paramMap.put("protocolId", wmsInveClerkProtocolSearchBeanVO.getWms_inve_clerk_protocol_id());
        paramMap.put("transaCrepkgId", wmsInveClerkProtocolSearchBeanVO.getWms_inve_clerk_protocol_transa_crepkg_id());

        // 查询关联债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgDao.queryAllProtocolTransaCrepkg(paramMap);

        Map<String, List<Map<String, Object>>> map = groupTransaCrepkgList(transaCrepkgList);

        return transaCrepkgList;
    }

    /**
     * @Title: groupTransaCrepkgList
     * @Description: 将list按照他项人id进行分组（rele_per_id） 打印内部转让合同需要根据不同他项人 打印多张合同
     * @param transaCrepkgList 
     * @author: zhangyunfei
     * @time:2017年2月11日 下午2:07:29
     * history:
     * 1、2017年2月11日 Administrator 创建方法
     */
    public Map<String, List<Map<String, Object>>> groupTransaCrepkgList(List<Map<String, Object>> transaCrepkgList)
    {
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

        for (Map<String, Object> crepkg : transaCrepkgList)
        {
            String rele_per_name = crepkg.get("rele_per_name").toString();
            if (map.containsKey(rele_per_name))
            {
                map.get(rele_per_name).add(crepkg);
            }
            else
            {
                List<Map<String, Object>> crepkgs = new ArrayList<Map<String, Object>>();
                crepkgs.add(crepkg);
                map.put(rele_per_name, crepkgs);
            }
        }
        return map;
    }

    /**
     * @Title: queryCrepkgByPk
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wms_inve_credit_package_id
     * @return 
     * @author: Guanxu
     * @time:2017年2月21日 下午6:25:43
     * @see com.zx.emanage.inve.service.IWmsInveTransaCrepkgService#queryCrepkgByPk(java.lang.Integer)
     * history:
     * 1、2017年2月21日 Guanxu 创建方法
    */
    @Override
    public Map<String, Object> queryCrepkgByPk(Integer wms_inve_credit_package_id, String disacl_type)
    {
        // 封装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (disacl_type != null && disacl_type.equals("0"))
        {
            paramMap.put("disacl_type", disacl_type);
        }
        paramMap.put("wms_inve_credit_package_id", wms_inve_credit_package_id);

        // 查询关联债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgDao.queryCrepkgByPk(paramMap);

        // 封装返回结果格式
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", transaCrepkgList);

        return resMap;
    }
}
