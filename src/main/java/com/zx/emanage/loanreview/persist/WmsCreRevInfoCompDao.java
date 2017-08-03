package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevInfoComp;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevInfoCompDao extends BaseDao<WmsCreRevInfoComp>
{
    // 删除相应数据
    void deleteInfo(Map<String, Object> zancunMap);

}