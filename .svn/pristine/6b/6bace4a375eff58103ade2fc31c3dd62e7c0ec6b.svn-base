package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveExtendInfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveExtendInfoDao extends BaseDao<WmsInveExtendInfo> {

    /**
     * @Title: getWmsInveExtendInfoByWmsInveTransaId
     * @Description: 根据单据
     * @param wms_inve_transa_id
     * @return 
     * @author: DongHao
     * @time:2017年2月27日 下午10:38:11
     * history:
     * 1、2017年2月27日 DongHao 创建方法
    */
    WmsInveExtendInfo getWmsInveExtendInfoByWmsInveTransaId(Integer wms_inve_transa_id);

    /**
     * @Title: getCategoryTypeByWmsInveTransaProdId
     * @Description: 根据上单产品表主键id获取对应的产品类型
     * @param wms_inve_transa_prod_id 上单产品表主键
     * @return 返回产品相关信息map集合
     * @author: DongHao
     * @time:2017年3月13日 下午4:16:14
     * history:
     * 1、2017年3月13日 DongHao 创建方法
    */
    Map<String, Object> getCategoryTypeByWmsInveTransaProdId(String wms_inve_transa_prod_id);
	
}