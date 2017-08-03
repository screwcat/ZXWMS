package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductReturn;

@MyBatisRepository
public interface WmsInvePruductReturnDao extends BaseDao<WmsInvePruductReturn>
{

    /**
     * 理财产品 复制 初始化限制表 赎回申请 获取信息
     * 
     * @param wms_inve_pruduct_category_id
     * @return map baisong
     */
    public List<Map<String, Object>> getListforlc(Integer wms_inve_pruduct_category_id);

    /**
     * 赎回申请 获取信息
     * 
     * @param wms_inve_pruduct_category_id
     * @return WmsInvePruductReturn zhubo
     */
    public List<WmsInvePruductReturn> getReturnInfo(Integer wms_inve_pruduct_category_id);

    /**
     * 赎回申请 获取限制最大天数与最小天数
     * 
     * @param wms_inve_pruduct_category_id
     * @return WmsInvePruductReturn zhubo
     */
    public WmsInvePruductReturn getBeginAndEnd(Integer wms_inve_pruduct_category_id);
    /**
     * 根据产品主键删除对应信息
     * @param wms_inve_pruduct_category_id
     * baisong
     */
    void delete(Integer wms_inve_pruduct_category_id);
}