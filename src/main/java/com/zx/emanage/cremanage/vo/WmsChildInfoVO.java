/**   
 * Project Name:WMS   
 * File Name:WmsHouseInfoVO.java   
 * Package Name:com.zx.emanage.cremanage.vo   
 * Date:2014年4月30日下午3:01:49   
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.   
 *   
 */

package com.zx.emanage.cremanage.vo;

/**
 * ClassName:WmsChildInfoVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 新增贷款单据修改客户信息代码转换需要 <br/>
 * Date: 2014年4月30日 下午3:01:49 <br/>
 * 
 * @author ry
 * @version
 * @see
 */
public class WmsChildInfoVO
{
    private String cusChild;

    private Integer wms_cus_customer_id;

    public String getCusChild()
    {
        return cusChild;
    }

    public void setCusChild(String cusChild)
    {
        this.cusChild = cusChild;
    }

    public Integer getWms_cus_customer_id()
    {
        return wms_cus_customer_id;
    }

    public void setWms_cus_customer_id(Integer wms_cus_customer_id)
    {
        this.wms_cus_customer_id = wms_cus_customer_id;
    }
}
