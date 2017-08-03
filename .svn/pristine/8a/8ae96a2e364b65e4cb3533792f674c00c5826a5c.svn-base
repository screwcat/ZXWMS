package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevInfoInsurance;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevInfoInsuranceDao extends BaseDao<WmsCreRevInfoInsurance>
{

    // 实现对保险公积金医疗信息的查找
    WmsCreRevInfoInsurance getByFK(Map<String, Object> parmMap);

    // 实现根据相应条件删除相应数据
    void deleteInfo(Map<String, Object> zancunMap);

}