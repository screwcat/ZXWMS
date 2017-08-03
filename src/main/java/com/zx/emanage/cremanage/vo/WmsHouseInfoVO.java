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
 * ClassName:WmsHouseInfoVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 新增贷款单据修改客户信息代码转换需要 <br/>
 * Date: 2014年4月30日 下午3:01:49 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 * @see
 */
public class WmsHouseInfoVO
{
    private String housestr;
    
    private String house_situation;

    private Integer wms_cus_customer_id;

    public String getHousestr()
    {
        return housestr;
    }

    public void setHousestr(String housestr)
    {
        this.housestr = housestr;
    }

    public Integer getWms_cus_customer_id()
    {
        return wms_cus_customer_id;
    }

    public void setWms_cus_customer_id(Integer wms_cus_customer_id)
    {
        this.wms_cus_customer_id = wms_cus_customer_id;
    }

	public String getHouse_situation() {
		return house_situation;
	}

	public void setHouse_situation(String house_situation) {
		this.house_situation = house_situation;
	}
}
