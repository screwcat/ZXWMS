package com.zx.emanage.cusmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.cusmanage.vo.WmsCusCustomerHeadSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCusCustomerHead;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCusCustomerHeadDao extends BaseDao<WmsCusCustomerHead>
{

    List<Map<String, Object>> getListWithoutPaging(Map<String, Object> resMap);

    /**
     * 通过客户信息主键删除相关联所有数据
     * 
     * @param wms_cus_customer_id
     * @return
     */
    int deleteByPK(Integer wms_cus_customer_id);

}
