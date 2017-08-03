package com.zx.emanage.loancheck.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCreditLinePersonCredit;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLinePersonCreditDao extends BaseDao<WmsCreCreditLinePersonCredit>
{

    List<WmsCreCreditLinePersonCredit> queryListByEntity(WmsCreCreditLinePersonCredit t);

}