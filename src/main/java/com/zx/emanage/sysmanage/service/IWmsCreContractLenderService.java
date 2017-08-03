package com.zx.emanage.sysmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreContractLender;

/*
 * @version 2.0
 */

public interface IWmsCreContractLenderService
{
    public List<WmsCreContractLender> getWmsCreContractLender();
    List<Map<String,Object>> getWmsCreContractLenderCards(Map<String,String> params);
}