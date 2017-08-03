package com.zx.emanage.sysmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmPersonnelDao extends BaseDao<PmPersonnel>
{

    List<Map<String, Object>> searchforlcjl(Map<String, Object> parameters);

    List<Map<String, Object>> searchforlczjl(Map<String, Object> parameters);

    List<Map<String, Object>> searchforlcfzjl(Map<String, Object> parameters);

    /**
     * 根据提供的短工号，查询相应是否存在部门
     */
    List<Map<String, Object>> searchforNodept(Map<String, Object> parameters);
    /**
     * 根据用户id查询用户职务信息===  职务编码=催缴主管的 CJZG
     */
    List<Map<String, Object>> getPostInfo(Integer personnel_id);
    
    List<Map<String, Object>> getListByEntity2(PmPersonnel queryInfo);
    /*
     * 获取部门上级信息
     */
    List<PmPersonnel> getByPost(Map<String, Object> parameters);

    /**
     * @Title: getPmPersonnelByIds 
     * @Description: 根据人员id集合查询人员
     * @param list
     * @return List<Map<String,Object>> 
     * @throws
     * @author lvtu 
     * @date 2015年9月17日 下午5:32:27
     */
	List<Map<String, Object>> getPmPersonnelByIds(List<Integer> list);
	/**
	 * 查询符合见习团队经理
	 * @param parameters
	 * @return
	 */
	List<Map<String, Object>> searchforlcjxjl(Map<String, Object> parameters);

	/**
	 * @Description 根据人员信息id查询对应的人员信息
	 * @param personne_id
	 * @return
	 * @author donghao
	 * @date 2016年8月10日15:21:18
	 */
	Map<String, Object> getPersonnelByPersonnelId(int personne_id);

	/**
	 * 
	 * @param map
	 * @return
	 */
	PmPersonnel getPersonnelByPersonnelId2(int personnel_id);
	
	/**
	 * @Deprecated 根据部门id获取对应的中分总的人员id
	 * @param map
	 * @return
	 * @author donghao
	 * @date 2016年8月15日14:26:12
	 */
	PmPersonnel getZhongFenZongByDeptId(Map<String, Object> map);
	
	/**
	 * @Deprecated 根据部门id获取对应的副总的人员id
	 * @param map
	 * @return
	 * @author donghao
	 * @date 2016年8月15日14:26:12
	 */
	PmPersonnel getFuZongByDeptId(Map<String, Object> map);
	
	/**
	 * @Deprecated 根据部门id获取对应的部门经理的人员id
	 * @param map
	 * @return
	 * @author donghao
	 * @date 2016年8月15日14:26:12
	 */
	PmPersonnel getBuMenJingLiByDeptId(Map<String, Object> map);
	
	/**
	 * @Deprecated 验证当前人员id所在部门id是否是兼职
	 * @param paramsMap
	 * @return
	 */
	int getSysConcurrentPost(Map<String, Integer> paramsMap);

	/**
	 * @Deprecated 根据人员信息id查询人员信息
	 * @param queryInfo
	 * @return
	 * @author donghao
	 * @date 2016年8月18日17:27:18
	 */
	Map<String, Object> getPersonnelByPersonnelIdAndDeptId(PmPersonnel queryInfo);

	/**
	 * @Deprecated 根据参数获取对应的总的部门id
	 * @param paramMap
	 * @return
	 * @author donghao
	 * @date 2016年8月19日10:43:57
	 */
	Integer getDeptId(Map<String, Object> paramMap);

	/**
	 * @Deprecated 获取总的信息
	 * @param map 
	 * @return
	 * @author donghao
	 * @date 2016年8月23日16:00:46
	 */
	PmPersonnel getZongByPersonnelInfo(Map<String, Object> map);

    /**
     * @Title: getPersonnelInfoById
     * @Description: 根据业务员id查找业务员信息
     * @param paramMap 查询条件
     * @return 业务员信息
     * @author: jinzhm
     * @time:2017年1月3日 下午4:02:26
     * history:
     * 1、2017年1月3日 jinzhm 创建方法
     */
    public Map<String, Object> getPersonnelInfoById(Map<String, Object> paramMap);

    /**
     * @Title: getPersonnelInfos
     * @Description: 根据查询条件获取对应的人员信息
     * @param queryPersonnelParams
     * @return 
     * @author: DongHao
     * @time:2017年2月11日 上午10:23:00
     * history:
     * 1、2017年2月11日 DongHao 创建方法
    */
    Map<String, Object> getPersonnelInfos(Map<String, Object> queryPersonnelParams);

    /**
     * @Title: getPersonnelByShortCode
     * @Description: 根据人员短工号进行获取人员信息
     * @param 
     * @return 
     * @author: DongHao
     * @time:2017年3月1日 下午10:52:42
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    PmPersonnel getPersonnelByShortCode(String string);

    /**
     * @Title: getPersonnelByPersonnelRegionnumber
     * @Description: 根据归属地获取人员信息
     * @param personnel_regionnumber 人员归属地编号
     * @return 返回该人员归属地人员信息集合
     * @author: DongHao
     * @time:2017年3月1日 下午11:39:22
     * history:
     * 1、2017年3月1日 DongHao 创建方法
    */
    List<PmPersonnel> getPersonnelByPersonnelRegionnumber(String personnel_regionnumber);

    /**
     * @Title: getJurisdictionInfo
     * @Description: TODO(查询当前用户的菜单权限)
     * @param personnel_id
     * @return 
     * @author: baisong
     * @time:2017年2月22日 下午3:51:08
     * history:
     * 1、2017年2月22日baisong 创建方法
     */
    List<String> getJurisdictionInfo(Integer personnel_id);
}