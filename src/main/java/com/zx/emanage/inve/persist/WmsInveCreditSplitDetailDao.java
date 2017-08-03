package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCreditSplitDetail;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCreditSplitDetailDao extends BaseDao<WmsInveCreditSplitDetail> {

    /**
     * @Title: savebatch
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param detailList 
     * @author: Guanxu
     * @time:2016年12月14日 下午8:20:48
     * history:
     * 1、2016年12月14日 Guanxu 创建方法
    */
    void savebatch(List<WmsInveCreditSplitDetail> detailList);
	
}