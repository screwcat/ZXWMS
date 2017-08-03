package com.zx.emanage.remind.service;



import java.util.Map;

import com.zx.emanage.remind.vo.WmsCreCreditNotarySetSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotarySet;
import com.zx.sframe.util.vo.UserBean;

/*
 * WmsCreCreditNotarySetService
 */
public interface IWmsCreCreditNotarySetService {

    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo);

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditNotarySetSearchBeanVO queryInfo);

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotarySet
     * @author administrator
     */
    public WmsCreCreditNotarySet getInfoByPK(Integer wms_cre_credit_notary_set_id);

    /**
     * Description : 新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    public String save(WmsCreCreditNotarySet bean, UserBean user);

    /**
     * Description : 修改
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author administrator
     */
    public String update(WmsCreCreditNotarySet bean, UserBean user);
/**
 * 数据初始化
 * @return
 */
	public WmsCreCreditNotarySet getWmsCreCreditNotarySetInfo();
    
    
    
}
