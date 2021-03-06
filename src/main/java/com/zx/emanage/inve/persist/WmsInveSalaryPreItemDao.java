package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveSalaryPreItemSearchBeanVO;
import com.zx.emanage.inve.vo.WmsInveSalaryPreTotalSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreItem;
import com.zx.emanage.util.gen.entity.WmsInveSalaryPreTotal;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveSalaryPreItemDao extends BaseDao<WmsInveSalaryPreItem> {

    /**
     * @Title: getPerformanceSalaryPreItemsByDeptId
     * @Description: 根据static_month 和部门id 获取工资前提配置子表信息
     * @param queryInfo
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月11日 上午10:05:28
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    List<Map<String, Object>> getPerformanceSalaryPreItemsByDeptId(WmsInveSalaryPreItemSearchBeanVO queryInfo);

    /**
     * @Title: updatePerformanceSalaryPreItemsById
     * @Description: 根据主键批量更新工资前提配置表item表（更新是否开资和绩效工资）
     * @param wmsInveSalaryPreItemList 
     * @author: zhangyunfei
     * @time:2017年1月11日 下午2:10:28
     * history:
     * 1、2017年1月11日 Administrator 创建方法
    */
    void updatePerformanceSalaryPreItemsById(List<WmsInveSalaryPreItem> wmsInveSalaryPreItemList);

    /**
     * @Title: getDeptPayNum
     * @Description: 通过static_month查询当前月份下 各个部门的开资人数
     * @param wmsInveSalaryPreTotalVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年1月3日 下午2:17:04
     * history:
     * 1、2017年1月3日 Administrator 创建方法
    */
    List<WmsInveSalaryPreTotal> getDeptPayNum(WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO);

    /**
     * @Title: updateViceGeneralPerformanceSalary
     * @Description: 总经理审批不通过 并且单据是由部门经理开始 需要清空副总项所对应的绩效
     * @param paramMap (包括statics_month和deptids)
     * @author: zhangyunfei
     * @time:2017年4月2日 上午1:23:29
     * history:
     * 1、2017年4月2日 Administrator 创建方法
    */
    public void updateViceGeneralPerformanceSalary(Map<String, Object> paramMap);

    /**
     * @Title: getWmsInveSalaryPreItemByDeptIdsAndMonth
     * @Description: 根据部门ids和statics_month查询工资前提配置子表集合
     * @param wmsInveSalaryPreTotalVO
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月24日 下午3:14:57
     * history:
     * 1、2017年4月24日 Administrator 创建方法
    */
    public List<WmsInveSalaryPreItem> getWmsInveSalaryPreItemByDeptIdsAndMonth(WmsInveSalaryPreTotalSearchBeanVO wmsInveSalaryPreTotalVO);

    /**
     * @Title: updateWmsInveSalaryPreItemByDeptsAndMonth
     * @Description: 根据部门ids和statics_month更新工资前提配置子表对应的副总或总审批的字段
     * @param paramMap 
     * @author: zhangyunfei
     * @time:2017年4月24日 下午4:18:07
     * history:
     * 1、2017年4月24日 Administrator 创建方法
    */
    public void updateWmsInveSalaryPreItemByDeptsAndMonth(Map<String, Object> paramMap);

}