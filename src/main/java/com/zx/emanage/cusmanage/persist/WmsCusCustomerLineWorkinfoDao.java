package com.zx.emanage.cusmanage.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCusCustomerLineWorkinfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCusCustomerLineWorkinfoDao extends BaseDao<WmsCusCustomerLineWorkinfo>
{

    List<WmsCusCustomerLineWorkinfo> getSingleTableListByAndMethod(WmsCusCustomerLineWorkinfo wmsCusCustomerLineWorkinfo);

    /**
     * 删除客户工作信息
     * 
     * @param wms_cus_customer_id
     * @return
     */
    int deleteByPK(Integer wms_cus_customer_id);
}
