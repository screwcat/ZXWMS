package com.zx.emanage.inve.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsInveCreditSearchBeanVO extends GridParamBean {

	private static final long serialVersionUID = 1L;

    /**
     * 拆分配置表主键
     */
    private Integer wms_inve_credit_split_spec_id;

    /**
     * @return the wms_inve_credit_split_spec_id
     */
    public Integer getWms_inve_credit_split_spec_id()
    {
        return wms_inve_credit_split_spec_id;
    }

    /**
     * @param wms_inve_credit_split_spec_id the wms_inve_credit_split_spec_id to set
     */
    public void setWms_inve_credit_split_spec_id(Integer wms_inve_credit_split_spec_id)
    {
        this.wms_inve_credit_split_spec_id = wms_inve_credit_split_spec_id;
    }

}