package com.zx.emanage.loancheck.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineBankStream;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditLineBankStreamDao extends BaseDao<WmsCreCreditLineBankStream>
{

    List<WmsCreCreditLineBankStream> queryListByEntity(WmsCreCreditLineBankStream t);

}