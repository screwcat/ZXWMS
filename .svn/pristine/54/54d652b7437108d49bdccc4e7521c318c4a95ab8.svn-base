package com.zx.emanage.cremanage.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.cremanage.persist.WmsCreCreditAttDao;
import com.zx.emanage.cremanage.service.IWmsCreCreditAttService;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;
import com.zx.emanage.cremanage.vo.WmsCreCreditAttSearchBeanVO;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmscrecreditattService")
public class WmsCreCreditAttServiceImpl implements IWmsCreCreditAttService
{
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditAttServiceImpl.class);

    @Autowired
    private WmsCreCreditAttDao wmscrecreditattDao;

    /**
     * 面签附件存储功能
     */
    @Override
    public List<WmsCreCreditAtt> getInfoByFK(Integer wms_cre_credit_head_id, String data_type)
    {
        WmsCreCreditAtt wAtt = new WmsCreCreditAtt();
        wAtt.setWms_cre_credit_head_id(wms_cre_credit_head_id);
        wAtt.setData_type(data_type);
        wAtt.setEnable_flag("1");
        List<WmsCreCreditAtt> beanList = wmscrecreditattDao.getListByEntity(wAtt);
        return beanList;
    }
}
