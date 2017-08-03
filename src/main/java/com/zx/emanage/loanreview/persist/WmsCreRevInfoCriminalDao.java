package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevInfoCriminal;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevInfoCriminalDao extends BaseDao<WmsCreRevInfoCriminal>
{
    // 删除做操
    void deleteInfo(Map<String, Object> zancunMap);

}