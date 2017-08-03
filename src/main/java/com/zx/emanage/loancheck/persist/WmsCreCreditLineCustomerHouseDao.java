package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerHouse;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCustomerHouseDao extends BaseDao<WmsCreCreditLineCustomerHouse>
{
    /**
     * 根据贷款ID查询房产信息
     * 
     * @param wms_cre_credit_head_id 贷款信息ID
     * @return 房产信息集合
     */
    public List<Map<String, Object>> searchByFK(Integer wms_cre_credit_head_id);
}