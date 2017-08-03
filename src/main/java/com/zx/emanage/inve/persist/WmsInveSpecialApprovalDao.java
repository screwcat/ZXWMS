package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveSpecialApproval;

@MyBatisRepository
public interface WmsInveSpecialApprovalDao extends BaseDao<WmsInveSpecialApproval>
{
    /**
     * 获取所有特批领导
     * 
     * @return list
     */
    List<WmsInveSpecialApproval> getAllInveSpecialapprova();
}