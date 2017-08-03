package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevInfoModel;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevInfoModelDao extends BaseDao<WmsCreRevInfoModel>
{

    void deleteInfo(Map<String, Object> wmsCreRevInfoDemoMap);

    WmsCreRevInfoModel getInfoByFK(Integer wms_cre_credit_head_id);

}