package com.zx.emanage.inve.persist;

import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCreditLimit;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveCreditLimitDao extends BaseDao<WmsInveCreditLimit> {

    /**
     * @Title: getLocationRegionCreditMinAccountByUserRegion
     * @Description: 根据当前登录人id获取人员所在区域(然后获取所在区域的债权限制金额)
     * @param userId 当前登录人id
     * @return 返回债权限制金额
     * @author: DongHao
     * @time:2017年4月7日 上午11:06:35
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    public Map<String, Object> getLocationRegionCreditMinAccountByUserRegion(Integer userId);

    /**
     * @Title: updateCreditLimitDataByLocalNum
     * @Description: 修改债权匹配限制中各地区的限制信息
     * @param paramMap 修改用户及地区信息
     * @author: jinzhm
     * @time:2017年4月8日 下午1:13:08
     * history:
     * 1、2017年4月8日 jinzhm 创建方法
     */
    public void updateCreditLimitDataByLocalNum(Map<String, Object> paramMap);
	
}