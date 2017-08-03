package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditAutoExpire;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditAutoExpireDao extends BaseDao <WmsCreCreditAutoExpire> {

    /**
     * 
     * @Title: updateForMany
     * @Description: 根据条件更新
     * @param paramMap 
     * @author: wangyihan
     * @time:2017年2月21日 上午9:19:09
     * history:
     * 1、2017年2月21日 wangyihan 创建方法
     */
    void updateForMany(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: searchForTable
     * @Description: 查询单表数据
     * @param paramMap
     * @return 
     * @author: wangyihan
     * @time:2017年2月21日 上午9:29:32
     * history:
     * 1、2017年2月21日 wangyihan 创建方法
     */
    List<Map<String, Object>> searchForTable(Map<String, Object> paramMap);
    
    
    
}