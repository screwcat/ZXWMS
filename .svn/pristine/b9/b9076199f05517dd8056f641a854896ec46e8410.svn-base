package com.zx.emanage.cusmanage.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCusCustomerLineCompany;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCusCustomerLineCompanyDao extends BaseDao<WmsCusCustomerLineCompany>
{
    List<WmsCusCustomerLineCompany> getSingleTableListByAndMethod(WmsCusCustomerLineCompany qryinfo);

    /**
     * 删除客户公司信息
     * 
     * @param wms_cus_customer_id
     * @return
     */
    int deleteByPK(Integer wms_cus_customer_id);
}