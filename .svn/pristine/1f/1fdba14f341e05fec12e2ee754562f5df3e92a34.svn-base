package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveRedeemAtt;

@MyBatisRepository
public interface WmsInveRedeemAttDao extends BaseDao<WmsInveRedeemAtt>
{

    public int delete(int wms_inve_redeem_id);

    /**
     * 获取赎回附件信息
     * 
     * @param wms_inve_redeem_id
     * @return
     */
    List<WmsInveRedeemAtt> getAttList(int wms_inve_redeem_id);
}