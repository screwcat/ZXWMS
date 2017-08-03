package com.zx.emanage.cremanage.service;

import java.util.List;
import com.zx.emanage.util.gen.entity.WmsCreCreditAtt;

/*
 * @version 2.0
 */
public interface IWmsCreCreditAttService
{
    /**
     * 根据提供的贷款主键外键以及上传附件类型查询其所有附件信息
     * 
     * @param wms_cre_credit_head_id
     * @param data_type=5 代表面签环节上传的附件数据类型标识
     * @return
     */
    public List<WmsCreCreditAtt> getInfoByFK(Integer wms_cre_credit_head_id, String data_type);
}