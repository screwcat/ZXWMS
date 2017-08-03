package com.zx.emanage.cusmanage.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCusCustomerChild;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCusCustomerChildDao extends BaseDao<WmsCusCustomerChild>
{
    List<WmsCusCustomerChild> getSingleTableListByAndMethod(WmsCusCustomerChild qryinfo);

    /**
     * 删除客户信息子女信息
     * 
     * @param wms_cus_customer_id
     * @return
     */
    int deleteByPK(Integer wms_cus_customer_id);
}