package com.zx.emanage.loanreview.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreRevAtt;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreRevAttDao
 * 模块名称：
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 baisong 创建文件
 */
@MyBatisRepository
public interface WmsCreRevAttDao extends BaseDao<WmsCreRevAtt>
{
    /**
     * 根据一定的条件删除记录
     * 
     * @param params 条件Map
     */
    void deleteRecords(Map<String, Object> params);

    // 点击暂存，删除原始对应数据
    void deleteInfo(Map<String, Object> zancunMap);

    /**
    * 
    * @Title: getCopyInfo
    * @Description: TODO(查询当前表的所有信息--用于复制)
    * @param id
    * @return 
    * @author: baisong
    * @time:2016年12月23日 下午5:18:59
    * history:
    * 1、2016年12月23日 baisong 创建方法
    */
    List<WmsCreRevAtt> getCopyInfo(Integer id);

    /**
     * @Title: saveBatch
     * @Description: TODO(批量保存) 
     * @author: baisong
     * @time:2016年12月26日 上午11:41:53
     * history:
     * 1、2016年12月26日 baisong 创建方法
    */
    void saveBatch(List<WmsCreRevAtt> list);
}