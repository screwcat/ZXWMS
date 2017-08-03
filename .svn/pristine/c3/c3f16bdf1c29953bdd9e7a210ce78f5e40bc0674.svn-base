package com.zx.emanage.loanappro.persist;

import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreApproServiceProtocolDao extends BaseDao<WmsCreApproServiceProtocol>
{
    /**
     * saveforhouse:在数据库中insert一条记录. 用于房产签合同
     * 
     * @author baisong
     */
    Integer saveforhouse(WmsCreApproServiceProtocol bean);

    /**
     * updateforhouse:在数据库中update一条记录. 用于房产签合同
     * 
     * @author baisong
     */
    void updateforhouse(WmsCreApproServiceProtocol bean);

    /**
     * 根据wms_cre_credit_head_id查询出对应的记录 放款确认 使用
     * 
     * @author baisong
     */
    WmsCreApproServiceProtocol getbypk(Integer wms_cre_credit_head_id);
}