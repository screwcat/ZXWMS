package com.zx.emanage.loanreview.persist;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;

@MyBatisRepository
public interface WmsCreCarpHouseAssessmentDao extends BaseDao<WmsCreCarpHouseAssessment> {
	WmsCreCarpHouseAssessment getInfoByFK(Integer wms_cre_credit_head_id);

    void deleteForId(Integer wms_cre_credit_head_id);
}