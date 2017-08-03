package com.zx.emanage.cremanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsCreCreditHeadDiffPhone;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsCreCreditHeadDiffPhoneDao extends BaseDao<WmsCreCreditHeadDiffPhone>
{

    List<WmsCreCreditHeadDiffPhone> getListByMid(Map<String, Object> parameters);

    void deleteByMap(Map<String, Object> map);
}
