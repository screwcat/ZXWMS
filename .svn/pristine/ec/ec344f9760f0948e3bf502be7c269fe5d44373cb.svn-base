package com.zx.emanage.inve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.emanage.inve.persist.WmsInveExpiredCustomerDao;
import com.zx.emanage.inve.service.IWmsInveExpiredCustomerService;
import com.zx.emanage.inve.vo.WmsInveExpiredCustomerVo;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName WmsInveExpiredCustomerServiceImpl
 * @Description 内容摘要: 到期客户数据查询的逻辑实现类
 * @author DongHao
 * @date 2016年11月24日
 * @version V1.0
 * @history 1. 2016年11月24日 DongHao 创建文件
 */
@Service("wmsInveExpiredCustomerServiceImpl")
public class WmsInveExpiredCustomerServiceImpl implements
		IWmsInveExpiredCustomerService {

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(WmsInveExpiredCustomerServiceImpl.class);
	
	@Autowired
	private WmsInveExpiredCustomerDao wmsInveExpiredCustomerDao;

	@Autowired
    private SysRoleDao sysroleDao_m;
	
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Autowired
    private SysSpecialUserDao specialUserDao;
	
	/**
	 * @Title findExpiredCustomer
	 * @Description 内容摘要: 根据条件获取到期客户的数据
	 * @param user
	 *            登录用户对象
	 * @param wmsInveExpiredCustomerVo
	 *            查询条件对象
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月24日
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	@Transactional
	@Override
	public Map<String, Object> findExpiredCustomer(UserBean user, WmsInveExpiredCustomerVo queryInfo) {
		Map<String, Object> paramMap = getParamsMap(user, queryInfo, false);
        paramMap.put("offset", queryInfo.getOffset());
        paramMap.put("pagesize", queryInfo.getPagesize());
       
        List<Map<String, Object>> resultList = wmsInveExpiredCustomerDao.findExpiredCustomer(paramMap);
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        resultList = SysTools.setListView(resultList, user, specialUsers);
		for (Map<String, Object> map : resultList) {
			map.remove("mobile_phone");
			map.remove("id_card");
			map.put("user_id", user.getUserId());
		}
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmsInveExpiredCustomerDao.findExpiredCustomerCount(paramMap), resultList);
		return bean.getGridData();
	}

	/**
	 * @Title findExportExpiredCustomerExcel
	 * @Description 内容摘要: 根据条件获取到期客户的数据
	 * @param user
	 *            用户对象
	 * @param wmsInveExpiredCustomerVo
	 *            查询条件对象
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月24日
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	@Transactional
	@Override
	public Map<String, Object> findExportExpiredCustomerExcel(UserBean user, WmsInveExpiredCustomerVo queryInfo) {
		Map<String, Object> paramMap = getParamsMap(user, queryInfo,false);
		Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = wmsInveExpiredCustomerDao.findExpiredCustomer(paramMap);
        SysSpecialUser specialUser = new SysSpecialUser();
        specialUser.setEnable_flag("1");
        List<SysSpecialUser> specialUsers = specialUserDao.getListByEntity(specialUser);
        resultList = SysTools.setListView(resultList, user, specialUsers);
		for (Map<String, Object> map : resultList) {
			map.remove("mobile_phone");
			map.remove("id_card");
			map.put("user_id", user.getUserId());
		}
        resultMap.put("Rows", resultList);
		return resultMap;
	}
	
	/**
	 * @Title getParamsMap
	 * @Description 根据条件封装成map集合参数
	 * @param user
	 *            用户对象
	 * @param queryInfo
	 *            查询条件对象
	 * @param invisible
	 *            当该用户为理财业务部主管时，去除客户手机与身份证信息并添加用户ID
	 * @return 返回map集合
	 * @author DongHao
	 * @date 2016年11月24日
	 * @history 1. 2016年11月24日 DongHao 创建文件
	 */
	private Map<String, Object> getParamsMap(UserBean user, WmsInveExpiredCustomerVo queryInfo, boolean invisible){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
		if (roleList == null || roleList.size() == 0) {
			paramMap.put("bel_salesman_id_id", user.getUserId());
		}
        for (String role : roleList)
        {
        	if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("bel_salesman_id_id", user.getUserId());
            }
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysDeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                invisible = true;
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务部总监"))
            {
            	paramMap.put("super_user", 1);
            }
            if (role.equals("理财业务部副总"))
            {
            	//根据数据权限去获取部门
            	paramMap.put("bel_salesman_id_id", user.getUserId());
                paramMap.put("deptIds_menu", queryInfo.getMenuId());
                paramMap.put("deptIds_user_id", user.getUserId());
            }
            if (role.equals("理财财务专员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("超级用户"))
            {
                // 可以看见所有数据
                paramMap.put("super_user", 1);
            }
            if(role.equals("财务柜员"))
            {
            	// 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if(role.equals("理财财务主管"))
            {
            	// 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
        }
        if(queryInfo.getExpiredStartDate() != null){
        	paramMap.put("expiredStartDate", queryInfo.getExpiredStartDate());
        }
        if(queryInfo.getExpiredEndDate() != null){
        	paramMap.put("expiredEndDate", queryInfo.getExpiredEndDate());
        }
        if(StringUtils.isNotBlank(queryInfo.getCusName())){
        	paramMap.put("cusName", queryInfo.getCusName());
        }
		paramMap.put("userid", user.getUserId());
        if(StringUtils.isNotBlank(queryInfo.getAccountManager())){
        	if(StringUtils.isNumeric(queryInfo.getAccountManager())){
        		paramMap.put("personnelShortCode", queryInfo.getAccountManager());
            }else{
            	paramMap.put("accountManager", queryInfo.getAccountManager());
            }
        }
        paramMap.put("invisible", invisible);
        return paramMap;
	}
}
