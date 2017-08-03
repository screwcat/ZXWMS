package com.zx.emanage.loanappro.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsSysPropertyDao extends BaseDao<WmsSysProperty>
{
    List<WmsSysProperty> searchforhouse(List<Integer> ids);// 属性值查询 forhouse签合同

    /**
     * 传入cre_type,cre_loan_type,region_number 的值要获取的属性表条件 贷款类型 贷款具体类型 地区编码
     * 
     * @param map
     * @return Map<String,String> baisong
     */
    List<WmsSysProperty> getforNewProtocol(Map<String, Object> map);
    
    
    /**
     * 获取产品基础信息
     * 
     * @param map
     * @return Map<String,String> baisong
     */
    List<Map<String, Object>> getprotocolProperty(Map<String, Object> map);
    
    /**
     * 获取产品基础信息 <!-- 贷款申请使用 -->
     * 
     * @param map
     * @return Map<String,String> baisong
     */
    List<Map<String, Object>> getprotocolPropertyApply(Map<String, Object> map);

    /**
     * @Title: getPaymentContractType
     * @Description: TODO(查询还款方式)
     * @param cre_loan_type 
     * @author: jiaodelong
     * @time:2016年12月29日 下午2:31:31
     * history:
     * 1、2016年12月29日 jiaodelong 创建方法
    */
    String getPaymentContractType(Integer cre_loan_type);
    
}