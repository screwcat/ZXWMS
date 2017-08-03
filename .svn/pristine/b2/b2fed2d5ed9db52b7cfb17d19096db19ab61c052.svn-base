package com.zx.emanage.inve.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveTransaUpdateLogSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaUpdateLog;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaUpdateLogService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaUpdateLogSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key 
     * @param primary key 
     * @return WmsInveTransaUpdateLogVO
     * @author auto_generator
     */
    public WmsInveTransaUpdateLog getInfoByPK(Integer wms_inve_transa_update_log_id);

    /**
     * Description :add method
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaUpdateLog bean, UserBean user, Integer product_deadlinel);

    /**
     * @Title: wmsInveTransaUpdateLogValidate
     * @Description: 验证数据处理修改支付时间是否合法
     * @param bean 数据处理日志对象
     * @return 是否可以修改，可以修改返回success，不能修改不返回
     * @author: jinzhm
     * @time:2017年1月5日 下午5:28:24
     * history:
     * 1、2017年1月5日 jinzhm 创建方法
     */
    public String wmsInveTransaUpdateLogValidate(WmsInveTransaUpdateLog bean);

    /**
     * Description :update method
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransaUpdateLog bean, UserBean user);

    /**
     * @Title: getInfo 
     * @Description: 条件查询
     * @param bean
     * @param user
     * @return    
     * @return WmsInveTransaUpdateLog    
     * @throws
     * @author lvtu 
     * @date 2015年8月19日 下午2:43:39
     */
    public List<WmsInveTransaUpdateLog> getInfo(WmsInveTransaUpdateLog bean, UserBean user);
}