package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.SysConcurrentPost;

@MyBatisRepository
public interface SysConcurrentPostDao extends BaseDao<SysConcurrentPost> {
	//批量更新 2016-8-4 baisong
	void  updateBatch(List<SysConcurrentPost> sysConcurrentPostlist);
	//批量保存 2016-8-4 baisong
	int  saveBatch(List<SysConcurrentPost> sysConcurrentPostlist);
	/**
	 * 删除所有信息
	 */
	void deleteF();
	/**
	 * 获取当前人 的兼职业务部门的职务
	 * @param sysConcurrentPostlist
	 */
	List<Map<String, Object>>  searchList(Map<String, Object> map);
}