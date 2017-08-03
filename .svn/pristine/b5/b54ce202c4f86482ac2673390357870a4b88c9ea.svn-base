package com.zx.emanage.sysmanage.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zx.emanage.loanappro.persist.WmsCreApproBorrowProtocolDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.loanfina.persist.WmsFinaCrePeriodRepayDao;
import com.zx.emanage.loanfina.persist.WmsFinaCreRepayDao;
import com.zx.emanage.sysmanage.service.IWmsSysDictService;
import com.zx.emanage.sysmanage.vo.WmsDataRevisionBean;
import com.zx.emanage.sysmanage.vo.WmsSysDictSearchBeanVO;
import com.zx.emanage.util.gen.domain.WmsSysDict;
import com.zx.emanage.util.gen.entity.WmsCreApproBorrowProtocol;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepay;
import com.zx.emanage.util.gen.entity.WmsSysProperty;
import com.zx.emanage.util.gen.vo.WmsSysDictVO;
import com.zx.sframe.util.GlobalFileUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsSysDictController {
	private static Logger log = LoggerFactory
			.getLogger(WmsSysDictController.class);

	@Autowired
	private IWmsSysDictService wmssysdictService;
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictwithoutpaginglist.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(
			WmsSysDictSearchBeanVO queryInfo) {
		return wmssysdictService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * 
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictwithpaginglist.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getListWithPaging(
			WmsSysDictSearchBeanVO queryInfo) {
		return wmssysdictService.getListWithPaging(queryInfo);
	}

	/**
	 * Description :get single-table information by primary key
	 * 
	 * @param primary
	 *            key
	 * @return WmsSysDictVO
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictinfobypk.do", method = { RequestMethod.GET })
	@ResponseBody
	public WmsSysDictVO getInfoByPK(Integer wms_sys_dict_id) {
		return wmssysdictService.getInfoByPK(wms_sys_dict_id);
	}

	/**
	 * Description :add method
	 * 
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictsave.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsSysDict bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmssysdictService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		 * // record log if("success".equals(result)){ String msg =
		 * "log content"; SysTools.saveLog(request, msg); // record log method }
		 */
		return result;
	}

	/**
	 * Description :update method
	 * 
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictupdate.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsSysDict bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmssysdictService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		 * // record log if("success".equals(result)){ String msg =
		 * "log content"; SysTools.saveLog(request, msg); // record log method }
		 */
		return result;
	}

	/**
	 * Description :delete method
	 * 
	 * @param pk
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	@RequestMapping(value = "/sysmanage/wmssysdictdelete.do", method = { RequestMethod.POST })
	@ResponseBody
	public String doDelete(HttpServletRequest request, WmsSysDict bean) {
		String result = wmssysdictService.delete(bean);
		/*
		 * // record log if("success".equals(result)){ String msg =
		 * "log content"; SysTools.saveLog(request, msg); // record log method }
		 */
		return result;
	}

	/**
	 * 实现excel2007数据的导入
	 * @param request
	 * @param session
	 * @param response
	 * @return success or error
	 * @author hancd
	 */
	@RequestMapping(value = "/sysmanage/fileforexcel.do", method = {RequestMethod.POST})
	@ResponseBody
	public String fileforexcel(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		
		return wmssysdictService.fileforexcel(request,session,response);
	}
	
	/**
	 * 导入excel并解析--合同他项公正等提醒
	 * @param request
	 * @param session
	 * @param response
	 * @return String
	 * @date 2016-10-20
	 * @author baisong
	 */
	@RequestMapping(value = "/sysmanage/excelFileToMysql.do", method = {RequestMethod.POST})
	@ResponseBody
	public String excelFileToMysql(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		return wmssysdictService.excelFileToMysql(request,session,response);
	}
}
