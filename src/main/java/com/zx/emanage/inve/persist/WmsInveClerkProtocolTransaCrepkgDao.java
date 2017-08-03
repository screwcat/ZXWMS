package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveClerkProtocolTransaCrepkg;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveClerkProtocolTransaCrepkgDao
 * 模块名称：债权匹配历史数据dao类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月14日
 * @version V1.0
 * history:
 * 1、2017年2月14日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveClerkProtocolTransaCrepkgDao extends BaseDao<WmsInveClerkProtocolTransaCrepkg>
{
    /**
     * @Title: updateProtocolTransaCrepkg
     * @Description: 根据上单主键和合同主键逻辑删除债权匹配历史记录
     * @param paramMap 修改条件
     * @author: jinzhm
     * @time:2017年2月14日 下午5:39:41
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void updateProtocolTransaCrepkg(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveClerkProtocolTransaCrepkg
     * @Description:  根据上单主键和柜员合同主键查询 债权匹配历史表主键
     * @param paramMap
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月20日 下午2:39:27
     * history:
     * 1、2017年7月20日 zhangyunfei 创建方法
    */
    public List<WmsInveClerkProtocolTransaCrepkg> getWmsInveClerkProtocolTransaCrepkg(Map<String, Object> paramMap);
}
