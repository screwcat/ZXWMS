package com.zx.emanage.sysmanage.persist;

import java.util.List;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.SysPost;

@MyBatisRepository
public interface SysPostDao extends BaseDao<SysPost> {

	/**
	 * @Deprecated 根据业务员部门id获取该部门的信息
	 * @param post_deptid
	 * @return
	 * @author donghao
	 * @date 2016年8月17日17:48:25
	 */
	List<SysPost> getSysPostBySalesManDeptId(int post_deptid);
	
}