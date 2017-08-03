package com.zx.emanage.sysmanage.service;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.zx.emanage.sysmanage.vo.WmsSysDictSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDict;
import com.zx.emanage.util.gen.vo.WmsSysDictVO;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsSysDictService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsSysDictSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsSysDictSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsSysDictVO
     * @author auto_generator
     */
    public WmsSysDictVO getInfoByPK(Integer wms_sys_dict_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsSysDict bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsSysDict bean, UserBean user);

    /**
     * Description :delete method
     * 
     * @param pk
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String delete(WmsSysDict bean);

	public String fileforexcel(HttpServletRequest request, HttpSession session,
			HttpServletResponse response);
	/**
	 * 导入excel并解析--合同他项公正等提醒
	 * @param request
	 * @param session
	 * @param response
	 * @return String
	 * @date 2016-10-20
	 * @author baisong
	 */
	public String excelFileToMysql(HttpServletRequest request, HttpSession session,
			HttpServletResponse response);

}