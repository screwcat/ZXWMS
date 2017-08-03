package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaBackApply;

@MyBatisRepository
public interface WmsInveTransaBackApplyDao extends BaseDao<WmsInveTransaBackApply> {
    
    /**
     * Description :上单信息表 上单产品信息表联合查询
     * @param paramMap
     * @return int
     * @author wangyihan
     * @date 2015年12月15日
     */
    public List<Map<String, Object>> searchForTransaAndProdList(Map<String, Object> paramMap);
    
    /**
     * Description :上单信息表 上单产品信息表联合查询(查询数量)
     * @param paramMap
     * @return int
     * @author wangyihan
     * @date 2015年12月15日
     */
    public int findTransaAndProdCount(Map<String, Object> paramMap);
    
}