package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveUserTodo;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface WmsInveUserTodoDao extends BaseDao<WmsInveUserTodo> {

	/**
	 * @Deprecated 根据用户id获取对应的用户待办事项表的信息
	 * @param user_id
	 * @return
	 * @author donghao
	 * @date 2016年9月8日16:20:32
	 */
	public int findWmsInveUserToByUserId(Integer user_id);
	
	/**
	 * 根据用户id查找用户需要处理的代办事项
	 * @param user_id
	 * @return
	 */
	public List<WmsInveUserTodo> getWmsInveUserTodoByUserId(Integer user_id);
	
	/**
	 * 根据用户id查询代办事项表中的remark及pm_personnel表中shortCode
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> getWmsInveUserTodoAndPersonByUserId(Integer user_id);
	
}