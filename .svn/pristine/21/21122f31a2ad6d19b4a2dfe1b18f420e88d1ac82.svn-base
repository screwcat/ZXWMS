package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaUser;

@MyBatisRepository
public interface WmsInveTransaUserDao extends BaseDao<WmsInveTransaUser> {
	
    /**
     * Description :保存多条
     * @param list
     * @return int
     * @author wangyihan
     * @date 2015年12月25日
     */
    public int saveList(List<WmsInveTransaUser> list);
    
    /**
     * Description :根据主表id删除记录
     * @param paramMap
     * @return int
     * @author wangyihan
     * @date 2015年12月15日
     */
    public void deleteByWmsInveTransaPruductUserId(Map<String, Object> paramMap);
    
    /**
     * Description :查询重复人员记录
     * @param paramMap
     * @return int
     * @author wangyihan
     * @date 2015年12月28日
     */
    public List<Map<String, Object>> searchSameUser();
    
}