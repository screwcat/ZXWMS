package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysDeptDao extends BaseDao<SysDept>
{
    /**
     * 根据父部门ID删除部门
     * 
     * @param p_dept_id 父部门ID
     */
    void deleteByP_dept_id(Integer p_dept_id);

    /**
     * 根据dept_id获得子部门dept_id
     * 
     * @param dept_id
     * @return
     */
    List<Integer> getDeptId(Integer dept_id);
    
    List<SysDept> getListByEntityCity(SysDept sysDept);
    
    /**
     * @Title: getFatherDeptInfo 
     * @Description: 根据部门ID查询上级部门信息
     * @param dept_id
     * @return SysDept 
     * @throws
     * @author lvtu 
     * @date 2015年9月7日 下午1:33:37
     */
    public SysDept getFatherDeptInfo(Integer dept_id);

    /**
     * 根据部门id获取对应的部门信息
     * 
     * @param dept_id
     * @return
     */
	SysDept getDeptById(int dept_id);

    /**
     * @Title: getDeptInfoForSendInfo
     * @Description: <!-- 给发送消息获取部门信息的sql勿动 -->
     * @param deptMap
     * @return 
     * @author: baisong
     * @time:2017年5月23日 下午3:30:58
     * history:
     * 1、2017年5月23日 baisong 创建方法
    */
    String getDeptInfoForSendInfo(Map<String, Object> deptMap);
    
}
