package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveTransaProdSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaProdService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaProdSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaProdSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaProdVO
     * @author auto_generator
     */
    public WmsInveTransaProd getInfoByPK(Integer wms_inve_transa_prod_id);

    /**
     * Description :通过上单信息主键获取上单产品信息
     * 
     * @param primary key
     * @return WmsInveTransaProdVO
     * @author ry
     */
    public WmsInveTransaProd getInfoByPKForJEGL(Integer wms_inve_transa_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaProd bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransaProd bean, UserBean user);

    public Map<String, Object> getListByID(String wms_inve_transa_prod_id, UserBean user);
    
    /**
     * Description :修改收益卡信息
     * 
     * @param bean
     * @author jiaodelong
     */
    public String updateIncomeCard(UserBean user,WmsInveTransaProd wmsTransaProd);

    /**
     * @Title: getWmsInveTransaProdInfos
     * @Description: 根据客户姓名和身份证号获取对应的收益卡信息
     * @param customer_name 客户姓名
     * @param id_card_number 身份证号
     * @return 返回Map集合
     * @author: DongHao
     * @time:2016年12月16日 上午11:01:15
     * history:
     * 1、2016年12月16日 DongHao 创建方法
    */
    public Map<String, Object> getWmsInveTransaProdInfos(String customer_name, String id_card_number);
    
    
}