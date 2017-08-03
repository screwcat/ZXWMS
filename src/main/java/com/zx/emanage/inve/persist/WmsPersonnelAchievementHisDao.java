package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsPersonnelAchievementHisSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsPersonnelAchievementHis;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsPersonnelAchievementHisDao extends BaseDao<WmsPersonnelAchievementHis> {

    /**
     * @Title: wmspersonnelachievementhisDao
     * @Description: 通过人员Id和static_month查询员工本月 上季度 本季度业绩
     * @param queryInfo 
     * @author: zhangyunfei
     * @return 
     * @time:2017年1月12日 下午2:56:24
     * history:
     * 1、2017年1月12日 Administrator 创建方法
    */
    Map<String, Object> getPersonnelAchievementByPid(WmsPersonnelAchievementHisSearchBeanVO queryInfo);
}