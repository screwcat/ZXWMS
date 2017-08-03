package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCourtCaseStatus;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCourtCaseStatusDao extends BaseDao<WmsCreCreditLineCourtCaseStatus>
{
    public List<Map<String, Object>> searchByFK(Integer wms_cre_credit_head_id);
}