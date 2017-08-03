package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevInfoMain;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevInfoMainDao extends BaseDao<WmsCreRevInfoMain>
{

    // 实现设置信调总体表中的企业法院网的有无
    int updateQyFyw(WmsCreRevInfoMain wmsCreRevInfoMain);

    // 查询个人信息
    WmsCreRevInfoMain getByFK(Map<String, Object> parmMap);

    // 当用户点击暂存操作的时候先删除相应数据
    void deleteInfo(Map<String, Object> zancunMap);

}