package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCustomer;
import com.zx.emanage.util.gen.entity.WmsInveTransa;

@MyBatisRepository
public interface WmsInveCustomerDao extends BaseDao<WmsInveCustomer>
{
    /**
     * 根据某些条件查询客户实体
     * 
     * @param paramMap 条件Map
     * @return 客户实体类
     */
    WmsInveCustomer getByEntity(Map<String, Object> paramMap);

    /**
     * @Title: updateCostomerIdByWmsInveTransaId
     * @Description: 上单审核通过之后通过上单表主键进行获取上单时的crm客户id进行更新客户信息表中的crmid
     * @param transa 上单表对象
     * @author: DongHao
     * @time:2017年10月7日 上午1:55:45
     * history:
     * 1、2017年10月7日 DongHao 创建方法
    */
    Integer updateCostomerIdByWmsInveTransaId(WmsInveTransa transa);
}