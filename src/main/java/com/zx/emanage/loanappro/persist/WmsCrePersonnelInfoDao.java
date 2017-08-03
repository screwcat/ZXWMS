package com.zx.emanage.loanappro.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCrePersonnelInfo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCrePersonnelInfoDao extends BaseDao<WmsCrePersonnelInfo>
{
    // 借款合同查询抵押人保证人
    List<WmsCrePersonnelInfo> searchforhouse(Integer wms_cre_appro_borrow_protocol);

	Integer updateWmsCrePersonnelInfo(WmsCrePersonnelInfo personnel);
}