package com.zx.emanage.inve.persist;

import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePadPower;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInvePadPowerDao extends BaseDao<WmsInvePadPower> {

    /**
     * @Title: updateEndTime
     * @Description: 强退根据设备序列号 和人员id更改失效时间
     * @param wmsInvePadPower 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:50:16
     * history:
     * 1、2017年2月23日 Administrator 创建方法
    */
    public void updateEndTime(WmsInvePadPower wmsInvePadPower);

    /**
     * @Title: getWmsInvePadAuthorList
     * @Description: 根据人员短工号和pad编号 并且当前时间在有效时间内  查询list（当前人员有操作pad的权限）
     * @param wmsInvePadPower
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午5:48:35
     * history:
     * 1、2017年2月23日 Administrator 创建方法
    */
    public List<WmsInvePadPower> getWmsInvePadAuthorList(WmsInvePadPower wmsInvePadPower);
	
}