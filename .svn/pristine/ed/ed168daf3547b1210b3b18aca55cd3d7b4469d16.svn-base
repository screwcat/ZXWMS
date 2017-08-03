package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerVehicle;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCustomerVehicleDao extends BaseDao<WmsCreCreditLineCustomerVehicle>
{
    /**
     * 根据贷款ID查询车辆信息
     * 
     * @param wms_cre_credit_head_id 贷款ID
     * @return 车辆信息集合
     */
    public List<Map<String, Object>> searchByFK(Integer wms_cre_credit_head_id);
}