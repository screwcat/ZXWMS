package com.zx.emanage.loanreview.vo;

import java.util.List;

import com.zx.platform.syscontext.vo.GridParamBean;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineCustomerChangeHead;
import com.zx.emanage.util.gen.entity.WmsCreCustomerChangeLineContact;

/*
 * @version 2.0
 */

public class WmsCreRevPhoneCustomerContactVO extends GridParamBean
{

    private static final long serialVersionUID = 1L;

    private WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead;

    private List<WmsCreCustomerChangeLineContact> wmsCreCustomerChangeLineContactList;

    public WmsCreCreditLineCustomerChangeHead getWmsCreCreditLineCustomerChangeHead()
    {
        return wmsCreCreditLineCustomerChangeHead;
    }

    public void setWmsCreCreditLineCustomerChangeHead(WmsCreCreditLineCustomerChangeHead wmsCreCreditLineCustomerChangeHead)
    {
        this.wmsCreCreditLineCustomerChangeHead = wmsCreCreditLineCustomerChangeHead;
    }

    public List<WmsCreCustomerChangeLineContact> getWmsCreCustomerChangeLineContactList()
    {
        return wmsCreCustomerChangeLineContactList;
    }

    public void setWmsCreCustomerChangeLineContactList(List<WmsCreCustomerChangeLineContact> wmsCreCustomerChangeLineContactList)
    {
        this.wmsCreCustomerChangeLineContactList = wmsCreCustomerChangeLineContactList;
    }

}