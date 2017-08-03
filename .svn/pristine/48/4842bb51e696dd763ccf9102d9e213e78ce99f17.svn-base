package com.zx.emanage.sysmanage.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.sysmanage.service.IWmsCreContractLenderService;
import com.zx.emanage.util.gen.entity.WmsCreContractLender;

/*
 * @version 2.0
 */

@Service("iwmscrecontractlenderservice")
public class WmsCreContractLenderServiceImpl implements IWmsCreContractLenderService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreContractLenderServiceImpl.class);

    @Autowired
    private com.zx.emanage.cremanage.persist.WmsCreContractLenderDao WmsCreContractLenderDao;


	@Override
	public List<WmsCreContractLender> getWmsCreContractLender() {
		
		 List<com.zx.emanage.util.gen.entity.WmsCreContractLender> list = WmsCreContractLenderDao.getWmsCreContractLenderList();
	      
	        return list;
	}


    /**
     * @Title: getWmsCreContractLenderCards
     * @Description: TODO(还款用户名获取相应还款账号、还款银行列表；还款用户名、还款银行获取相应还款账号列表 )
     * @param params
     * @return 
     * @author: handongchun
     * @time:2017年5月23日 上午9:51:55
     * @see com.zx.emanage.sysmanage.service.IWmsCreContractLenderService#getWmsCreContractLenderCards(java.util.Map)
     * history:
     * 1、2017年5月23日 handongchun 创建方法
    */
    @Override
    public List<Map<String, Object>> getWmsCreContractLenderCards(Map<String, String> params)
    {
        List<Map<String, Object>> wmsCreContractLenderCardsList = WmsCreContractLenderDao.getWmsCreContractLenderCards(params);
        return wmsCreContractLenderCardsList;
    }
   
}
