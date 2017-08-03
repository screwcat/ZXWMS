package com.zx.emanage.inve.persist;

import com.zx.emanage.util.gen.entity.WmsInveCustomerWx;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCustomerWxDao extends BaseDao<WmsInveCustomerWx> {

    /**
     * 
     * @Title: getWmsInveCustomerWxByPhoneNumber
     * @Description: 根据验证的手机号获取对应的客户信息
     * @param phone_number 手机号
     * @return 返回对应的手机号的客户信息
     * @author: DongHao
     * @time:2017年7月20日 上午11:19:18
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    WmsInveCustomerWx getWmsInveCustomerWxByPhoneNumber(String phone_number);

    /**
     * 
     * @Title: getWmsInveTransaByPhoneNumber
     * @Description: 根据手机号获取对应的客户是否购买过不提供纸质合同的产品
     * @param phone_number 手机号
     * @return 返回记录的数量
     * @author: DongHao
     * @time:2017年7月20日 上午11:38:44
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    int getWmsInveTransaByPhoneNumber(String phone_number);

	
}