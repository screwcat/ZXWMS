package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveTransaCard;
import com.zx.emanage.inve.vo.WmsInveTransaCardSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaCardService
{
	/**
     * @Title:getListWithoutPaging
     * Description :【财务管理】【理财上单】【金额到账】 列表显示
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    public Map<String, Object> getListWithoutPaging(WmsInveTransaCardSearchBeanVO queryInfo,UserBean user);

    /**
     * Description :通过上单信息主键获取数据
     * 
     * @param queryInfo
     * @return record list
     * @author ry
     */
    public Map<String, Object> getListWithoutPagingForJEDZ(WmsInveTransaCardSearchBeanVO queryInfo);

    /**
     * @Title:getListWithPaging
     * Description :【财务管理】【理财上单】【金额到账】 列表导出
     * 
     * @param queryInfo
     * @return record list
     * @author hancd
     * @date 2015年12月17日 15:16
     */
    public Map<String, Object> getListWithPaging(WmsInveTransaCardSearchBeanVO queryInfo,UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveTransaCardVO
     * @author auto_generator
     */
    public WmsInveTransaCard getInfoByPK(Integer wms_inve_transa_card_id);

    /**
     * 通过上单信息表ID获取上单支付表信息
     * 
     * @param wms_inve_transa__id
     * @return WmsInveTransaCardVO
     * @author zhubo
     */
    public Map<String, Object> getInfoByFK(Integer wms_inve_transa_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveTransaCard bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveTransaCard bean, UserBean user);

    /**
     * Description :金额到账
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author ry
     */
    public String updateForJEDZ(UserBean user, String wms_inve_transa_id, String itcardJSON, String tzje,
                                String payTypeStr);
    /**
     * @Deprecated 根据上单信息表主键获取对应的单据的支付信息
     * @param wms_inve_transa_id
     * @return
     * @author donghao
     * @date 2016年9月1日10:32:46
     */
	public Map<String, Object> findZfInfoByWmsInveTransaId(Integer wms_inve_transa_id);
	
	/**
	 * 1.该方法主要是通过NumberFormat将列表中的pay_type_binary格式化成二进制的形式(如:111)
	 *   二进制形式的数据从左到右依次表示为 现金--银行卡--续单本金,如果二进制形式的数据表示为000则为单据续期
	 * 2.将格式化完的二进制数据转换成char[]数组。
	 * 3.对char[]数组中的每一位数据进行判断,判断的顺序遵循上面的二进制数据的规则
	 *   从左到右依次判断每一位数据是否==1如果==1就设置成对应的文字信息,如果从左到右依次都为0的情况,就表示是单据续期
	 * @param list
	 */
	public List<Map<String, Object>> setPayName(List<Map<String, Object>> list);
}