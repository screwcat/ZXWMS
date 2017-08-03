package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSaleLimitVo;
import com.zx.emanage.util.gen.entity.WmsInveSaleLimit;
import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSaleLimitDao
 * 模块名称：理财限制每日销售额dao
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年4月6日
 * @version V1.0
 * history:
 * 1、2017年4月6日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveSaleLimitDao
{
    /**
     * @Title: save
     * @Description: 保存理财销售每日限额数据
     * @param saleLimit 销售每日限额数据
     * @return 受影响记录数
     * @author: jinzhm
     * @time:2017年4月6日 下午3:42:10
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public int save(WmsInveSaleLimit saleLimit);
    
    /**
     * @Title: update
     * @Description: 更新理财销售每日限额数据
     * @param saleLimit 销售每日限额数据
     * @return 受影响记录数
     * @author: jinzhm
     * @time:2017年4月6日 下午3:42:37
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public int update(WmsInveSaleLimit saleLimit);
    
    /**
     * @Title: getSaleLimit
     * @Description: 查询符合条件的销售每日限额数据
     * @param paramMap 查询条件
     * @return 销售每日限额数据集合
     * @author: jinzhm
     * @time:2017年4月6日 下午3:44:26
     * history:
     * 1、2017年4月6日 jinzhm 创建方法
     */
    public List<WmsInveSaleLimit> getSaleLimit(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveSaleLimitVoList
     * @Description: 查询WmsInveSaleLimit集合
     * @return 
     * @author: zhangyunfei
     * @param paramMap 
     * @time:2017年4月20日 上午11:32:27
     * history:
     * 1、2017年4月20日 Administrator 创建方法
    */
    public List<WmsInveSaleLimitVo> getWmsInveSaleLimitVoList(Map<String, Object> paramMap);

    /**
     * @Title: saveWmsInveSaleLimitPatch
     * @Description: 批量保存WmsInveSaleLimit
     * @param paramMap 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午2:50:24
     * history:
     * 1、2017年4月7日 Administrator 创建方法
    */
    public void saveWmsInveSaleLimitBatch(Map<String, Object> paramMap);

    /**
     * @Title: updateWmsInveSaleLimitBatch
     * @Description: 批量更新WmsInveSaleLimit
     * @param paramMap 
     * @author: zhangyunfei
     * @time:2017年4月7日 下午3:48:18
     * history:
     * 1、2017年4月7日 Administrator 创建方法
    */
    public void updateWmsInveSaleLimitBatch(Map<String, Object> paramMap);
}
