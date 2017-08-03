package com.zx.emanage.cremanage.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsCreHousingAttSearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_head_id;
    
    private Integer[] wms_cre_credit_head_id_arr;

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer wms_cre_credit_head_id)
    {
        this.wms_cre_credit_head_id = wms_cre_credit_head_id;
    }

    public Integer[] getWms_cre_credit_head_id_arr()
    {
        return wms_cre_credit_head_id_arr;
    }

    public void setWms_cre_credit_head_id_arr(Integer[] wms_cre_credit_head_id_arr)
    {
        this.wms_cre_credit_head_id_arr = wms_cre_credit_head_id_arr;
    }

    
    
}