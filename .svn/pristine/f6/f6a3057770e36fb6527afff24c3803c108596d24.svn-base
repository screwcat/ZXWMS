package com.zx.emanage.inve.persist;
import java.util.List;
import java.util.Map;


import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruduct;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductRules;

@MyBatisRepository
public interface WmsInveTransaPruductDao extends BaseDao<WmsInveTransaPruduct> {
	/**
     * Description :保存多条
     * @param list
     * @return int
     * @author baisong
     * @date 2015年12月15日
     */
	public int saveList(List<WmsInveTransaPruduct> list);
	/**
     * Description :删除多条
     * @param list
     * @return int
     * @author baisong
     * @date 2015年12月15日
     */
	public int deleteList(List<String> list);
    /**
     * Description :根据主表id删除记录
     * @param paramMap
     * @return int
     * @author wangyihan
     * @date 2015年12月15日
     */
    public void deleteByWmsInveTransaPruductUserId(Map<String, Object> paramMap);
    
	/**
     * Description :删除多条
     * @param list
     * @return int
     * @author baisong
     * @date 2015年12月15日
     */
	public List <Map<String,Object>> ischeck();
    

}