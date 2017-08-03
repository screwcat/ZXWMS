package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePos;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.inve.vo.WmsInvePosSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePosService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInvePosSearchBeanVO queryInfo);

    /**
     * @Title: getListWithPaging 
     * @Description: 理财POS机查询（分页）
     * @param queryInfo
     * @return    
     * @return Map<String,Object>    
     * @throws
     * @author lvtu 
     * @date 2015年6月29日 下午3:56:28
     */
    public Map<String, Object> getListWithPaging(WmsInvePosSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePosVO
     * @author auto_generator
     */
    public WmsInvePos getInfoByPK(Integer wms_inve_pos_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInvePos bean, UserBean user);

    /**
     * Description :更改pos机信息
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author ry
     */
    public String doChangePos(String bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInvePos bean, UserBean user);

    /**
     * Description :查询所有的POS机信息
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    public List<WmsInvePos> getAllInvePosInfo();
}