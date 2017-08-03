package com.zx.emanage.cusmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCusCustomerLineHouseinfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCusCustomerLineHouseinfoDao extends BaseDao<WmsCusCustomerLineHouseinfo>
{

    List<WmsCusCustomerLineHouseinfo> getSingleTableListByAndMethod(WmsCusCustomerLineHouseinfo qryinfo);

    List<WmsCusCustomerLineHouseinfo> getList(Integer wms_cus_customer_id);

    /**
     * 删除客户房产信息
     * 
     * @param wms_cus_customer_id
     * @return
     */
    int deleteByPK(Integer wms_cus_customer_id);
    
    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>
     * 
     * @author Administrator
     * @param parameters
     * @return
     * @since JDK 1.6
     */
    List<Map<String, Object>> searchInfo(Map<String, Object> parameters);
}
