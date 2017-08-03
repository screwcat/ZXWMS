package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCarpAssessment;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCarpAssessmentDao extends BaseDao<WmsCreCarpAssessment> {
	 public Map<String, Object> getByHK(Integer wms_cre_credit_head_id);
}