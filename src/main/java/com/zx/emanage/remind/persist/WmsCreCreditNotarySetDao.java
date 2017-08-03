package com.zx.emanage.remind.persist;

import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotarySet;

@MyBatisRepository
public interface WmsCreCreditNotarySetDao extends BaseDao <WmsCreCreditNotarySet> {
/**
 * 作废数据
 * @param updateBean
 * @return
 */
	Integer updateEnable(WmsCreCreditNotarySet updateBean);
/**
 * 数据初始化
 * @return WmsCreCreditNotarySet
 */
WmsCreCreditNotarySet getWmsCreCreditNotarySetInfo();

}