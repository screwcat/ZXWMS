package com.zx.emanage.loancheck.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineCallList;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineCallListDao extends BaseDao<WmsCreCreditLineCallList>
{

    List<Map<String, Object>> searchInfoByFK(Integer wms_cre_credit_head_id);

}