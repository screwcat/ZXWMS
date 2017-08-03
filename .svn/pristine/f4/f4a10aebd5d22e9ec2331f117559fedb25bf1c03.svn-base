package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCredit;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCreditDao extends BaseDao<WmsInveCredit> {

    /**
     * @Title: savebatch
     * @Description: 批量插入债权数据
     * @param creditList 
     * @author: Guanxu
     * @time:2016年12月14日 下午6:33:45
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
    */
    void savebatch(List<WmsInveCredit> creditList);

    /**
     * @Title: searchSpec
     * @Description: 查询拆分规则
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午5:26:54
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    List<Map<String, Object>> searchSpec(Map<String, Object> paramMap);

    /**
     * @Title: searchSplitData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午6:27:12
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    List<Map<String, Object>> searchSplitData(Map<String, Object> paramMap);

    /**
     * @Title: searchSplitDataSum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param paramMap
     * @return 
     * @author: Guanxu
     * @time:2016年12月15日 下午6:27:17
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
    */
    List<Map<String, Object>> searchSplitDataSum(Map<String, Object> paramMap);
	
}