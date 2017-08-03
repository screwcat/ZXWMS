package com.zx.emanage.sysmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.sysmanage.vo.PmPersonnelSearchBeanVO;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IPmPersonnelService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(PmPersonnelSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(PmPersonnelSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return PmPersonnelVO
     * @author auto_generator
     */
    public PmPersonnel getInfoByPK(Integer personnel_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(PmPersonnel bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(PmPersonnel bean, UserBean user);

    public Map<String, Object> getListByEntity(PmPersonnel queryInfo);
    
    /**
     * @Title: getPmPersonnelByIds 
     * @Description: 根据人员id集合查询人员
     * @param personnelIds
     * @return List<Map<String,Object>> 
     * @throws
     * @author lvtu 
     * @date 2015年9月17日 下午5:11:05
     */
	public List<Map<String, Object>> getPmPersonnelByIds(String personnelIds);

    /**
     * @Title: getPmPersonnelByShortcode
     * @Description: 查询人员信息
     * @param pmPersonnel
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月9日 下午5:15:14
     * history:
     * 1、2016年12月9日 Administrator 创建方法
    */
    public List<PmPersonnel> getPmPersonnelListByEntity(PmPersonnel pmPersonnel);

}