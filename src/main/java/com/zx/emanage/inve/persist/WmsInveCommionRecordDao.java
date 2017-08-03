package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveCommionRecord;

@MyBatisRepository
public interface WmsInveCommionRecordDao extends BaseDao<WmsInveCommionRecord> {
	/**
	 * Description :调整金额
	 * @param WmsInveCommionRecord
	 * @return "success" or "error" or user defined
	 * @author zhangyunfei
	 */		
	String updateWmsInveCommionRecordAmount(HttpServletRequest request, WmsInveCommionRecord bean);
	
	/**
	 * Description :条件查询实发金额总数
	 * @param paramMap
	 * @return 实发金额总数
	 * @author zhangyunfei
	 */		
	String findwmsinvepruductcategorySumMoney(Map<String, Object> paramMap);
	
	
	/**
	 * Description :佣金计算
	 * @param paramMap
	 * @return 佣金计算列表
	 * @author zhangyunfei
	 */	
    List<Map<String,Object>> getWmsInveTaxationByCondition(Map<String, Object> paramMap);

    /**
	 * Description :存量奖
	 * @param paramMap
	 * @return 佣金计算列表
	 * @author zhangyunfei
	 */	
    List<Map<String,Object>> getWmsInveStockByCondition(Map<String, Object> paramMap);	
    
    /**
   	 * Description :管理提成奖
   	 * @param paramMap
   	 * @return 佣金计算列表
   	 * @author zhangyunfei
   	 */	
    List<Map<String,Object>> getWmsInveCommionByCondition(Map<String, Object> paramMap);
    
    /**
   	 * Description :打款
   	 * @param paramMap
   	 * @return 佣金计算列表
   	 * @author zhangyunfei
   	 */	
    List<Map<String,Object>> getWmsInvePaymentByCondition(Map<String, Object> paramMap);	
}