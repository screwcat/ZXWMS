package com.zx.emanage.loanreview.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevWaterPrivPassbook;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreRevWaterPrivPassbookDao extends BaseDao<WmsCreRevWaterPrivPassbook>
{
    /**
     * 按一定条件删除记录
     * 
     * @param paramMap 条件Map
     */
    void deleteRecords(Map<String, Object> paramMap);

}