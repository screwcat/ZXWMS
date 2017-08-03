package com.zx.emanage.loanappro.vo;

import com.zx.platform.syscontext.vo.GridParamBean;

/*
 * @version 2.0
 */

public class WmsSysPropertySearchBeanVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;
    
    /** 出借人姓名 **/
    private String lender_name;

    public String getLender_name() {
        return lender_name;
    }

    public void setLender_name(String lender_name) {
        this.lender_name = lender_name;
    }
    
    

}