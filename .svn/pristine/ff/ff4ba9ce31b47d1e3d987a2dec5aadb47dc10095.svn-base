package com.zx.emanage.inve.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaPruductUser;

@MyBatisRepository
public interface WmsInveTransaPruductUserDao extends BaseDao<WmsInveTransaPruductUser> {
	
    /**
     * 根据主键删除
     */
    public void deleteByPK(@Param("wms_inve_transa_pruduct_user_id")Integer wms_inve_transa_pruduct_user_id);
    /**
     * @Title: getidList 
     * @Description: 根据人员获取当前人对应的产品
     * @param wms_inve_pruduct_category_id  
     * @return List<Integer>    
     * @throws
     * @author baisong 
     * @date 2015年12月15日 下午6:44:28
     */
	public List<Integer> getidList(Integer user_id);

}