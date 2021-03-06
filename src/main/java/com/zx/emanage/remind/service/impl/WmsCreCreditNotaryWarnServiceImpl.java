package com.zx.emanage.remind.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zx.emanage.cremanage.service.IWmsCreCreditHeadService;
import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.remind.persist.WmsCreCreditNotaryWarnDao;
import com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService;
import com.zx.emanage.remind.vo.CombinedLoanBeanVO;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.sysmanage.util.CodeNoUtil;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.WmsHelp;
import com.zx.emanage.util.gen.entity.SysDept;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.emanage.util.gen.entity.WmsSysDictData;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.JsonUtil;
import com.zx.sframe.util.ValidFormat;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * @Title: WmsCreCreditNotaryWarnServiceImpl
 * @Description: (公证和还款提醒模块)
 * @time:2016年11月15日 下午1:30:40 
 * history: 
 * 1、2016年11月18日 baisong 修改还款
 * 2、2016年11月22日 添加异常
 * 3、2016年11月29日 添加查询项
 */

@Service("wmsCreCreditNotaryWarnService")
public class WmsCreCreditNotaryWarnServiceImpl implements IWmsCreCreditNotaryWarnService {
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditNotaryWarnServiceImpl.class);

    @Autowired
    private WmsCreCreditNotaryWarnDao wmscrecreditnotarywarnDao;

    @Autowired
	private CommonsMultipartResolver multipartResolver;
    
    @Autowired
    private SysDeptDao sysdeptDao;
    
    @Autowired
    private IWmsCreCreditHeadService wmsCreCreditHeadService;
    
    @Autowired
    private WmsSysDictDataDao wmsSysDictDataDao;
    
    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @Override
    public Map<String, Object> getListWithoutPaging(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.search(paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("Rows", list);
        return resMap;
    }

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @Override
    public Map<String, Object> getListWithPaging(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = 
            new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.findCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotaryWarn
     * @author administrator
     */
    @Override
    public WmsCreCreditNotaryWarn getInfoByPK(Integer wms_cre_credit_notary_warn_id, UserBean user) {
        return wmscrecreditnotarywarnDao.get(wms_cre_credit_notary_warn_id);
    }

    /**
     * Description :新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @Override
    @Transactional
    public String save(WmsCreCreditNotaryWarn bean, UserBean user) {
        String resStr = "success";
        int ret = 0;
        
        long now_time_long = System.currentTimeMillis();
        
        // 单据编号根据申请日期生成
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("appl_date", bean.getAppl_date());
        
        bean.setBill_code(this.wmscrecreditnotarywarnDao.getBillCodeForNotaryWarn(paramMap));
        bean.setCreate_user_id(user.getUserId());
        bean.setCreate_user_name(user.getRealname());
        bean.setCreate_timestamp(new java.sql.Timestamp(now_time_long));
        bean.setData_sources("1");// 页面新增
        bean.setIs_weekend_not_remind(0);
        bean.setAlreadyse_send_message_number(0);
        bean.setEnable_flag("1");
        bean.setCre_type("2");
        ret = wmscrecreditnotarywarnDao.save(bean);
        if (ret == 0) {
            resStr = "error";
        }
        return resStr;
    }

    /**
     * Description : 修改
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @Override
    @Transactional
    public String update(WmsCreCreditNotaryWarn bean, UserBean user) {
        String resStr = "success";
        try
        {
            long now_time_long = System.currentTimeMillis();
            
            // 借款期限为空默认为0
            if(bean.getBorrow_deadline() == null) {
                bean.setBorrow_deadline(0);
            }
            bean.setAlreadyse_send_message_number(0);
            bean.setIs_weekend_not_remind(0);
            bean.setLast_update_user_id(user.getUserId());
            bean.setLast_update_timestamp(new java.sql.Timestamp(now_time_long));
            
            wmscrecreditnotarywarnDao.update(bean);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            resStr = "error";
        }
        
        return resStr;
    }

    /**
     * Description : 查询公证到期提醒列表(带分页：使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchVNotaryWarnList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(queryInfo.getBill_code())) {
    		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
    		paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_begin())) {
    		paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_end())) {
    		paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
    		paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
    		paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_begin())) {
    		paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_end())) {
    		paramMap.put("end_time_end", queryInfo.getEnd_time_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status())) {
    		paramMap.put("bill_status", queryInfo.getBill_status());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status_ne())) {
    		paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
    	}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_name())) {
            paramMap.put("category_name", queryInfo.getCategory_name());
        }
		if (StringUtils.isNotBlank(queryInfo.getEnd_time())) {
            paramMap.put("end_time", queryInfo.getEnd_time());
        }
        // 营业部
        if (StringUtils.isNotBlank(queryInfo.getSales_epartment_name()))
        {
            paramMap.put("sales_epartment_name", queryInfo.getSales_epartment_name());
        }
        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_GZDQTX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

    	paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag()) && queryInfo.getNeed_paging_flag().equals("0")) {
        	paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
        	paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
    	
    	List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchVNotaryWarnList(paramMap);
    	
    	GridDataBean<Map<String, Object>> bean =  
    			new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchVNotaryWarnCount(paramMap), list);
        return bean.getGridData();
    }
    
    /**
     * Description : 查询公证到期查询列表(带分页：使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchVNotarySearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(queryInfo.getBill_code())) {
    		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
    		paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_begin())) {
    		paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_end())) {
    		paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
    		paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
    		paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_begin())) {
    		paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_end())) {
    		paramMap.put("end_time_end", queryInfo.getEnd_time_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status())) {
    		paramMap.put("bill_status", queryInfo.getBill_status());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status_ne())) {
    		paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
    	}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_GZDQCX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
		
    	paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag()) && queryInfo.getNeed_paging_flag().equals("0")) {
        	paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
        	paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
    	
    	List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchVNotaryWarnList(paramMap);
    	
    	GridDataBean<Map<String, Object>> bean =  
    			new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchVNotaryWarnCount(paramMap), list);
        return bean.getGridData();
    }
    
    /**
     * Description : 批量导入数据
     * 
     * @param queryInfo
     * @return String
     * @author administrator
     */
    @Override
    @Transactional
    public WmsCreCreditNotaryWarnSearchBeanVO importWmsCreCreditNotaryWarnData(HttpServletRequest request, HttpServletResponse response, 
    		WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
    	String result = "";
    	int err_num = 0;
    	Boolean flag = true;
    	List<WmsCreCreditNotaryWarn> list = new ArrayList<WmsCreCreditNotaryWarn>();
    	if (this.multipartResolver != null && this.multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				
				SysDept sDept = new SysDept();
                sDept.setDept_code(WmsHelp.TOP_DEPT_CODE);// 产品管理部的部门编码
		        List<SysDept> systopdeptList = sysdeptDao.getListByEntity(sDept);
		        sDept.setDept_code(null);
		        sDept.setDept_pid(systopdeptList.get(0).getDept_id());
		        List<SysDept> sysdeptList = sysdeptDao.getListByEntityCity(sDept);
		        boolean dept_name_flag = false;
		        
		        List<WmsSysDictData> wmsSysDictDataList = wmsSysDictDataDao.getDictDataByDictId(27);
		        List<WmsSysDictData> billTypeList = wmsSysDictDataDao.getDictDataByDictId(124);
		        boolean product_flag = false;
		        boolean bill_type_flag = false;
		        
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					MultipartFile mf = entity.getValue();
					long fileSize = mf.getSize();
					if (fileSize > 60 * 1024 * 1024) {
                        result = "上传附件不能超过60M！";
						flag = false;
						break;
					}
                    String srcFileName = mf.getOriginalFilename();// 获取文件名称
					String postfix = "";
					if (srcFileName.lastIndexOf(".") > -1) {
						postfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1).toLowerCase();
						if (!("xls".equals(postfix) || "xlsx".equals(postfix))) {
                            result = "不允许上传模板以外的格式文件！";
							flag = false;
							break;
						}
					}
                    // Excel 2007获取方法
					Workbook book = null;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
			    	long now_time_long = System.currentTimeMillis();
					java.sql.Timestamp now_time_timestamp = new java.sql.Timestamp(now_time_long);
			    	
					try {
						book = new XSSFWorkbook(mf.getInputStream());
					} catch (IOException e1) {
						result = e1.getMessage();
						e1.printStackTrace();
					}
                    // 读取表格的第一个sheet页
					Sheet sheet = book.getSheetAt(0);
					Row row;
                    // 总行
					int totalRows = sheet.getLastRowNum();
					WmsCreCreditNotaryWarn bean = new WmsCreCreditNotaryWarn();
					
					String mobile_telephone = "";
					String com_debtor_tel = "";
					String salesman_shortcode = "";
					String the_number = "";
					for(int i = 1; i <= totalRows; i++) {
						row = sheet.getRow(i);
						
						if(row == null) {
						    if(i == 1) {
						        result = "数据为空！";
	                            flag = false;
						    }
						    break;
						}
						
						bean = new WmsCreCreditNotaryWarn();
						
						err_num = i + 1;

                        // 申请日期必填校验
						if(cellValueIsNull(row, 0)) {
                            result = "第" + (i + 1) + "行申请日期不能为空！";
							flag = false;
							break;
						}
                        // 申请日期格式校验
						try {
                            bean.setAppl_date(new java.sql.Date(format.parse(getCellValueDate(row, 0)).getTime()));// 申请日期(yyyy-MM-dd)
						} catch (ParseException e) {
						    try {
	                            bean.setAppl_date(new java.sql.Date(format1.parse(getCellValueDate(row, 0)).getTime()));// 申请日期(yyyy/MM/dd)
	                        } catch (ParseException e1) {
	                            result = "第" + (i + 1) + "行申请日期格式不正确！";
	                            flag = false;
	                            break;
	                        }
						}
						// 业务状态必填校验
                        if(cellValueIsNull(row, 1)) {
                            result = "第" + (i + 1) + "行业务状态不能为空！";
                            flag = false;
                            break;
                        }
                        // 业务状态范围校验
                        if(!cellValueIsNull(row, 1)) {
                            bill_type_flag = false;
                            for(WmsSysDictData wmsSysDictData : billTypeList) {
                                if(wmsSysDictData.getValue_code().toString().equals(getValueFromCell(row, 1))) {
                                    bill_type_flag = true;
                                    break;
                                }
                            }
                            if(!bill_type_flag) {
                                result = "第" + (i + 1) + "行业务状态填写错误！";
                                flag = false;
                                break;
                            }
                        }
                        // 业务状态不为01(新增)则次数为必填(2016-12-19 10:01 zhigang.wang 需求变更)
                        if(!getValueFromCell(row, 1).equals("01") && cellValueIsNull(row, 2)) {
                            result = "第" + (i + 1) + "行业务状态非新增时则次数不能为空！";
                            flag = false;
                            break;
                        }
                        // 次数格式校验
                        if(!cellValueIsNull(row, 2)) {
                            if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {//非文本格式
                                if(!ValidFormat.isPositiveDecimal(row.getCell(2).toString())) {
                                    result = "第" + (i + 1) + "行次数格式不正确！";
                                    flag = false;
                                    break;
                                } else {
                                    the_number = getValueFromCell(row, 2).split("\\.")[0];
                                }
                            } else if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {//文本格式
                                if(!ValidFormat.isPositiveInteger(row.getCell(2).toString())) {
                                    result = "第" + (i + 1) + "行次数格式不正确！";
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        // 客户姓名必填校验
						if(cellValueIsNull(row, 3)) {
                            result = "第" + (i + 1) + "行客户姓名不能为空！";
							flag = false;
							break;
						}
                        // 身份证必填校验
						if(cellValueIsNull(row, 4)) {
                            result = "第" + (i + 1) + "行身份证不能为空！";
							flag = false;
							break;
						}
                        // 身份证格式校验
						try {
							if(!ValidFormat.isIdCard(getValueFromCell(row, 4))) {
                                result = "第" + (i + 1) + "行身份证格式不正确！";
								flag = false;
								break;
							}
						} catch (ParseException e) {
                            result = "第" + (i + 1) + "行身份证格式不正确！";
							flag = false;
							e.printStackTrace();
						}
                        // 联系电话必填校验
						if(cellValueIsNull(row, 5)) {
                            result = "第" + (i + 1) + "行联系电话不能为空！";
							flag = false;
							break;
						}
                        // 联系电话格式校验
						if(!cellValueIsNull(row, 5)) {
						    mobile_telephone = row.getCell(5).toString();
						    if(mobile_telephone.contains(".")) {
						        //非文本格式电话号码
						        mobile_telephone = new DecimalFormat("#").format(row.getCell(5).getNumericCellValue());
						        if(!ValidFormat.isNumWithLine(mobile_telephone)) {
						            result = "第" + (i + 1) + "行联系电话格式不正确！";
		                            flag = false;
		                            break;
	                            }
						    } else {//文本格式电话号码
						        if(!ValidFormat.isNumWithLine(mobile_telephone)) {
						            result = "第" + (i + 1) + "行联系电话格式不正确！";
		                            flag = false;
		                            break;
						        }
						    }
						}
                        // 共贷人身份证格式校验
						try {
							if(!cellValueIsNull(row, 7) && !ValidFormat.isIdCard(getValueFromCell(row, 7))) {
                                result = "第" + (i + 1) + "行共贷人身份证格式不正确！";
								flag = false;
								break;
							}
						} catch (ParseException e) {
                            result = "第" + (i + 1) + "行共贷人身份证格式不正确！";
							flag = false;
							e.printStackTrace();
						}
                        // 共贷人联系电话格式校验
						if(!cellValueIsNull(row, 8)) {
						    com_debtor_tel = row.getCell(8).toString();
						    if(com_debtor_tel.contains(".")) {
                                //非文本格式电话号码
						        com_debtor_tel = new DecimalFormat("#").format(row.getCell(8).getNumericCellValue());
                                if(!ValidFormat.isNumWithLine(com_debtor_tel)) {
                                    result = "第" + (i + 1) + "行共贷人联系电话格式不正确！";
                                    flag = false;
                                    break;
                                }
                            } else {//文本格式电话号码
                                if(!ValidFormat.isNumWithLine(com_debtor_tel)) {
                                    result = "第" + (i + 1) + "行共贷人联系电话格式不正确！";
                                    flag = false;
                                    break;
                                }
                            }
						}
                        // 固定电话格式校验
						if(!cellValueIsNull(row, 10) && (!ValidFormat.isPhone(getValueFromCell(row, 10)) && !ValidFormat.isNumWithLine(getValueFromCell(row, 10)))) {
                            result = "第" + (i + 1) + "行固定电话格式不正确！";
							flag = false;
							break;
						}
                        // 合同签订日期必填校验
						if(cellValueIsNull(row, 11)) {
                            result = "第" + (i + 1) + "行合同签订日期不能为空！";
							flag = false;
							break;
						}
                        // 合同签订日期格式校验
						try {
							bean.setProtocol_date(new java.sql.Date(format.parse(getCellValueDate(row, 11)).getTime()));
						} catch (ParseException e) {
                            result = "第" + (i + 1) + "行合同签订日期格式不正确！";
							flag = false;
							break;
						}
                        // 终止还款日期必填校验
						if(cellValueIsNull(row, 12)) {
                            result = "第" + (i + 1) + "行终止还款日期不能为空！";
							flag = false;
							break;
						}
                        // 终止还款日期格式校验
						try {
                            bean.setBorrow_end_date(new java.sql.Date(format.parse(getCellValueDate(row, 12)).getTime()));// 终止还款日期
						} catch (ParseException e) {
                            result = "第" + (i + 1) + "行终止还款日期格式不正确！";
							flag = false;
							break;
						}
                        // 产品必填校验
						if(cellValueIsNull(row, 14)) {
                            result = "第" + (i + 1) + "行产品不能为空！";
							flag = false;
							break;
						}
                        // 产品范围校验
						if(!cellValueIsNull(row, 14)) {
						    String product = getValueFromCell(row, 14);
						    if(product.contains(".")) {
						        product = product.split("\\.")[0];
						    }
							product_flag = false;
							for(WmsSysDictData wmsSysDictData : wmsSysDictDataList) {
								if(wmsSysDictData.getWms_sys_dict_data_id().toString().equals(product)) {
									product_flag = true;
									break;
								}
							}
							if(!product_flag) {
                                result = "第" + (i + 1) + "行产品填写错误！";
								flag = false;
								break;
							}
						}
                        // 利息必填校验
						if(cellValueIsNull(row, 15)) {
                            result = "第" + (i + 1) + "行利息不能为空！";
							flag = false;
							break;
						}
                        // 利息格式校验
						if(!ValidFormat.isPositiveDecimal(getValueFromCell(row, 15))) {
                            result = "第" + (i + 1) + "行利息格式不正确！";
							flag = false;
							break;
						}
                        // 借款期数必填校验
						if(cellValueIsNull(row, 16)) {
                            result = "第" + (i + 1) + "行借款期数不能为空！";
							flag = false;
							break;
						}
                        // 借款期数格式校验
						if(!ValidFormat.isPositiveInteger(getValueFromCell(row, 16)) &&
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 16))) {
                            result = "第" + (i + 1) + "行借款期数格式不正确！";
							flag = false;
							break;
						}
                        // 借款金额必填校验
						if(cellValueIsNull(row, 17)) {
                            result = "第" + (i + 1) + "行借款金额不能为空！";
							flag = false;
							break;
						}
                        // 借款金额格式校验
						if(!ValidFormat.isPositiveInteger(getValueFromCell(row, 17)) && 
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 17))) {
                            result = "第" + (i + 1) + "行借款金额格式不正确！";
							flag = false;
							break;
						}
                        // 打卡金额格式校验
						if(!cellValueIsNull(row, 18) && 
					        !ValidFormat.isPositiveInteger(getValueFromCell(row, 18)) &&
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 18))) {
                            result = "第" + (i + 1) + "行打卡金额格式不正确！";
							flag = false;
							break;
						}
                        // 平台费格式校验
						if(!cellValueIsNull(row, 19) && 
					        !ValidFormat.isPositiveInteger(getValueFromCell(row, 19)) &&
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 19))) {
                            result = "第" + (i + 1) + "行平台费格式不正确！";
							flag = false;
							break;
						}
                        // 公证费格式校验
						if(!cellValueIsNull(row, 20) && 
					        !ValidFormat.isPositiveInteger(getValueFromCell(row, 20)) &&
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 20))) {
                            result = "第" + (i + 1) + "行公证费格式不正确！";
							flag = false;
							break;
						}
                        // 合同金额必填校验
						if(cellValueIsNull(row, 21)) {
                            result = "第" + (i + 1) + "行合同金额不能为空！";
							flag = false;
							break;
						}
                        // 合同金额格式校验
						if(!ValidFormat.isPositiveInteger(getValueFromCell(row, 21)) && 
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 21))) {
                            result = "第" + (i + 1) + "行合同金额格式不正确！";
							flag = false;
							break;
						}
                        // 月还款金额必填校验
						if(cellValueIsNull(row, 22)) {
                            result = "第" + (i + 1) + "行月还款金额不能为空！";
							flag = false;
							break;
						}
                        // 月还款金额格式校验
						if(!ValidFormat.isPositiveInteger(getValueFromCell(row, 22)) && 
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 22))) {
                            result = "第" + (i + 1) + "行月还款金额格式不正确！";
							flag = false;
							break;
						}
                        // 开户银行必填校验
						if(cellValueIsNull(row, 23)) {
                            result = "第" + (i + 1) + "行开户银行不能为空！";
							flag = false;
							break;
						}
                        // 银行卡号必填校验
						if(cellValueIsNull(row, 24)) {
                            result = "第" + (i + 1) + "行银行卡号不能为空！";
							flag = false;
							break;
						}
                        // 银行卡号格式校验
						if(!ValidFormat.isCardNo(getValueFromCell(row, 24))) {
                            result = "第" + (i + 1) + "行银行卡号格式不正确！";
							flag = false;
							break;
						}
                        // 已还款期数必填校验
						if(cellValueIsNull(row, 25)) {
                            result = "第" + (i + 1) + "行已还款期数不能为空！";
							flag = false;
							break;
						}
                        // 已还款期数格式校验(可以为零)
						if(!getValueFromCell(row, 25).equals("0") && 
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 25)) &&
					        !ValidFormat.isPositiveInteger(getValueFromCell(row, 25))) {
                            result = "第" + (i + 1) + "行已还款期数格式不正确！";
							flag = false;
							break;
						}
                        // 还款日期必填校验
						if(cellValueIsNull(row, 26)) {
                            result = "第" + (i + 1) + "行还款日期不能为空！";
							flag = false;
							break;
						}
                        // 还款日期格式校验
						try {
							bean.setCurrent_repay_date(new java.sql.Date(format.parse(getCellValueDate(row, 26)).getTime()));
						} catch (ParseException e) {
                            result = "第" + (i + 1) + "行还款日期格式不正确！";
							flag = false;
							break;
						}
                        // 剩余还款金额格式校验
						if(!cellValueIsNull(row, 27) && 
					        !ValidFormat.isPositiveInteger(getValueFromCell(row, 27)) &&
					        !ValidFormat.isPositiveDecimal(getValueFromCell(row, 27))) {
                            result = "第" + (i + 1) + "行剩余还款金额格式不正确！";
							flag = false;
							break;
						}
                        // 工号格式校验
						if(!cellValueIsNull(row, 29)) {
						    salesman_shortcode = getValueFromCell(row, 29);
						    if(salesman_shortcode.contains(".")) {
						        salesman_shortcode = salesman_shortcode.split("\\.")[0];
						    }
						    if((salesman_shortcode.length() != 6) && 
								!ValidFormat.isPositiveInteger(salesman_shortcode)) {
                                result = "第" + (i + 1) + "行工号格式不正确！";
    							flag = false;
    							break;
						    }
						}
                        // 公证截止日期必填校验
						/*if(cellValueIsNull(row, 29)) {
                            result = "第" + (i + 1) + "行公证截止日期不能为空！";
							flag = false;
							break;
						}*/
						if(!cellValueIsNull(row, 31)) {
                            // 公证截止日期格式校验
    						try {
    							bean.setEnd_time(new java.sql.Date(format.parse(getCellValueDate(row, 31)).getTime()));
    						} catch (ParseException e) {
                                result = "第" + (i + 1) + "行公证截止日期格式不正确！";
    							flag = false;
    							break;
    						}
						}
                        // 所属营业部必填校验
						if(cellValueIsNull(row, 33)) {
                            result = "第" + (i + 1) + "行所属营业部不能为空！";
							flag = false;
							break;
						}
                        // 所属营业部范围校验(只能从指定部门里选择)
						if(!cellValueIsNull(row, 33)) {
					        dept_name_flag = false;
					        for(SysDept sysdept : sysdeptList) {
					            if(getValueFromCell(row, 33).equals(sysdept.getDept_code())) {
					            	dept_name_flag = true;
					            	break;
					            }
					        }
					        if(!dept_name_flag) {
                                result = "第" + (i + 1) + "行无此营业部！";
								flag = false;
								break;
					        }
						}

						bean.setBill_type(getValueFromCell(row, 1));// 业务状态
						bean.setThe_number(the_number);// 次数
                        bean.setCustomer_name(getValueFromCell(row, 3));// 客户姓名
                        bean.setId_card(getValueFromCell(row, 4));// 身份证
                        bean.setMobile_telephone(mobile_telephone);// 联系电话
                        bean.setCom_debtor_name(getValueFromCell(row, 6));// 共贷人姓名
                        bean.setCom_debtor_identity_id(getValueFromCell(row, 7));// 共贷人身份证
                        bean.setCom_debtor_tel(com_debtor_tel);// 共贷人电话
                        bean.setCurrent_address_more(getValueFromCell(row, 9));// 共贷人地址
                        bean.setDebtor_fixed_line(getValueFromCell(row, 10));// 共贷人固话
                        bean.setProtocol_id_year_num(getValueFromCell(row, 13));// 合同号
                        bean.setCategory_name(getValueFromCell(row, 14));// 产品
                        bean.setBorrow_interest(getValueFromCell(row, 15));// 利息
                        bean.setBorrow_deadline((new Double(getValueFromCell(row, 16))).intValue());// 借款期数
                        bean.setRefund_bank(getValueFromCell(row, 23));// 开户行
                        bean.setRefund_number(getValueFromCell(row, 24));// 银行卡号
                        bean.setRepay_period((new Double(getValueFromCell(row, 25))).intValue());// 已还款期数

                        bean.setSalesman_name(getValueFromCell(row, 28));// 业务员
                        bean.setSalesman_shortcode(salesman_shortcode);// 工号
                        bean.setTeam_manager_name(getValueFromCell(row, 30));// 团队经理
                        bean.setEnd_reason(getValueFromCell(row, 32));// 到期原因
                        bean.setSales_epartment_code(getValueFromCell(row, 33));// 所属营业部
                        bean.setWarn_remak(getValueFromCell(row, 34));// 备注
						
                        bean.setCre_type("2");// 贷款产品1信贷2房贷3车贷
						bean.setCreate_user_id(user.getUserId());
						bean.setCreate_user_name(user.getRealname());
						bean.setCreate_timestamp(now_time_timestamp);
						bean.setLast_update_user_id(user.getUserId());
						bean.setLast_update_timestamp(now_time_timestamp);
                        bean.setData_sources("1");// excel导入
						bean.setIs_weekend_not_remind(0);
						bean.setAlreadyse_send_message_number(0);
						bean.setEnable_flag("1");
                        // 以下字段保留两位小数
                        bean.setAppro_limit(getRoundingByTwo(row, 17));// 借款金额
                        bean.setLoan_amount(getRoundingByTwo(row, 18));// 打卡金额
                        bean.setPlatform_fee(getRoundingByTwo(row, 19));// 平台费
                        bean.setNotarial_fee(getRoundingByTwo(row, 20));// 公证费
                        bean.setPrincipal_lower(getRoundingByTwo(row, 21));// 合同金额
                        bean.setRefund_limit_month(getRoundingByTwo(row, 22));// 月还款金额
						if(!cellValueIsNull(row, 27)) {
                            bean.setUn_pay_principal(getRoundingByTwoReturnBigDecimal(row, 27));// 剩余还款金额
						}
						bean.setRepay_status("1");
						bean.setBill_status("1");
						
						list.add(bean);
					}
				} 
			}
		}
    	if(flag) {
    		Map<String, Object> paramMap = new HashMap<String, Object>();
    		for(WmsCreCreditNotaryWarn bean : list) {
    			paramMap.put("appl_date", bean.getAppl_date());
				bean.setBill_code(this.wmscrecreditnotarywarnDao.getBillCodeForNotaryWarn(paramMap));
    			wmscrecreditnotarywarnDao.save(bean);
    		}
    	}
    	queryInfo.setResult(result);
    	queryInfo.setErr_num(err_num);
    	queryInfo.setFlag(flag.toString());
    	return queryInfo;
    }
    
    private String getValueFromCell(Row row, int num) {
    	if(row.getCell(num) == null) {
    		return null;
    	} else {
    		if("".equals(row.getCell(num).toString())) {
    			return "";
    		}
    	}
    	return row.getCell(num).toString();
    }
    
    // 判断单元格是否为空
    private boolean cellValueIsNull(Row row, int num) {
    	if(row.getCell(num) == null) {
    		return true;
    	} else {
    		if("".equals(row.getCell(num).toString())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // 保留两位小数
    private String getRoundingByTwo(Row row, int num) {
    	if(row.getCell(num) == null) {
    		return null;
    	} else {
    		if("".equals(row.getCell(num).toString())) {
    			return "";
    		}
    	}
    	String value = row.getCell(num).toString();
    	return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    // 保留两位小数
    private BigDecimal getRoundingByTwoReturnBigDecimal(Row row, int num) {
    	if(row.getCell(num) == null) {
    		return null;
    	} else {
    		if("".equals(row.getCell(num).toString())) {
    			return null;
    		}
    	}
    	String value = row.getCell(num).toString();
    	return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    // 日期格式化
    private String getCellValueDate(Row row, Integer i) {
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String value = "";
		try {
			value = formatter.format(format.parse(row.getCell(i).toString()));
		} catch (ParseException e) {
		    try {
	            value = formatter.format(format1.parse(row.getCell(i).toString()));
	        } catch (ParseException e1) {
	            e.printStackTrace();
	        }
		}
		return value;
	}

	@Override
	@Transactional
	public String deleteInfo(Integer wms_cre_credit_notary_warn_id) {
		String res = "success";
		Integer n = wmscrecreditnotarywarnDao.deleteInfo(wms_cre_credit_notary_warn_id);
		if(n == 0){
			res = "error";
		}
		return res;
	}
    
	                                                                                /**
    * Description : 查询还款提醒列表(使用视图)
    * 
    * @param queryInfo
    * @return record list
    * @author administrator
    */
    public Map<String, Object> searchRepaymentReminderList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(queryInfo.getBill_code())) {
    		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
    		paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_begin())) {
    		paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_end())) {
    		paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
    		paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
    		paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_begin())) {
    		paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_end())) {
    		paramMap.put("end_time_end", queryInfo.getEnd_time_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status())) {
    		paramMap.put("bill_status", queryInfo.getBill_status());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status_ne())) {
    		paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
    	}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		if (StringUtils.isNotBlank(queryInfo.getCurrent_repay_date())) {
			paramMap.put("current_repay_date_reminder", queryInfo.getCurrent_repay_date());
		}
		if (StringUtils.isNotBlank(queryInfo.getBill_status_arr())) {
			paramMap.put("bill_status_arr", SysTools.getSqlInParam(queryInfo.getBill_status_arr()));
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_name())) {
            paramMap.put("category_name", queryInfo.getCategory_name());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_id())) {
            paramMap.put("category_id", queryInfo.getCategory_id());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());// 还款状态
        }
		if (StringUtils.isNotBlank(queryInfo.getRepay_status_arr())) {
            paramMap.put("repay_status_arr", SysTools.getSqlInParam(queryInfo.getRepay_status_arr()));
        }
        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_HKTX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

    	paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag())) {
        	paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
        	paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
    	
    	List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchRepaymentList(paramMap);
    	
    	GridDataBean<Map<String, Object>> bean =  
    			new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchRepaymentCount(paramMap), list);
        return bean.getGridData();
    }
    
    /**
     * Description : 查询还款查询列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    public Map<String, Object> searchRepaymentSearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(queryInfo.getBill_code())) {
    		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
    		paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_begin())) {
    		paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_end())) {
    		paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
    		paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
    		paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_begin())) {
    		paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_end())) {
    		paramMap.put("end_time_end", queryInfo.getEnd_time_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status())) {
    		paramMap.put("bill_status", queryInfo.getBill_status());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status_ne())) {
    		paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
    	}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		if (StringUtils.isNotBlank(queryInfo.getCurrent_repay_date())) {
			paramMap.put("current_repay_date", queryInfo.getCurrent_repay_date());
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_name())) {
            paramMap.put("category_name", queryInfo.getCategory_name());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_id())) {
            paramMap.put("category_id", queryInfo.getCategory_id());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());// 还款状态
        }
		if (StringUtils.isNotBlank(queryInfo.getRepay_status_arr())) {
            paramMap.put("repay_status_arr", SysTools.getSqlInParam(queryInfo.getRepay_status_arr()));
        }
        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_HKCX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

    	paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag())) {
        	paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
        	paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
    	
    	List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchRepaymentList(paramMap);
    	
    	GridDataBean<Map<String, Object>> bean =  
    			new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchRepaymentCount(paramMap), list);
        return bean.getGridData();
    }
	
    /**
     * 
     * @Title: searchBirthdayReminderList
     * @Description: (查询生日提醒列表 视图)
     * @param request
     * @param queryInfo
     * @return
     * @author: baisong
     * @time:2016年11月15日 上午10:59:39 history: 1、2016年11月15日 baisong 创建方法
     */
	public Map<String, Object> searchBirthdayReminderList(
			WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(queryInfo.getBill_code())) {
			paramMap.put("bill_code",
					SysTools.getSqlLikeParam(queryInfo.getBill_code()));
		}
		if (StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
			paramMap.put("salesman_name_str",
					SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
		}
		if (StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
			paramMap.put("customer_name",
					SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
		}
		if (StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
			paramMap.put("mobile_telephone",
					SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
		}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		if (StringUtils.isNotBlank(queryInfo.getBirthday_date())) {
            paramMap.put("birthday_date", queryInfo.getBirthday_date());// 生日日期
		}
		if (StringUtils.isNotBlank(queryInfo.getRepay_status_ne())) {
            paramMap.put("repay_status_ne", queryInfo.getRepay_status_ne());// 还款状态
		}
        // 还款状态
        if (StringUtils.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
		if (StringUtils.isNotBlank(queryInfo.getCategory_name())) {
            paramMap.put("category_name", queryInfo.getCategory_name());// 产品
		}
        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_SRTX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

		paramMap.put("sortname", queryInfo.getSortname());
		paramMap.put("sortorder", queryInfo.getSortorder());

		if (StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag())
				&& queryInfo.getNeed_paging_flag().equals("0")) {
			paramMap.put("pagesize", null);
			paramMap.put("offset", null);
		} else {
			paramMap.put("pagesize", queryInfo.getPagesize());
			paramMap.put("offset", queryInfo.getOffset());
		}

		List<Map<String, Object>> list = wmscrecreditnotarywarnDao
				.searchBirthdayVNotaryWarnList(paramMap);

		GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
				queryInfo.getPage(),
				wmscrecreditnotarywarnDao
						.searchBirthdayVNotaryWarnCount(paramMap),
				list);
		// bean.getGridData().put("now_date",
        // new java.sql.Date(System.currentTimeMillis()));// 当前时间
		return bean.getGridData();
	}
	
    /**
     * 
     * @Title: sendMessageBirthday
     * @Description: (获取信息放款数据同步到还款提醒模块)
     * @param request
     * @param bean
     * @return String
     * @author: baisong
     * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
     */
	@Override
	@Transactional
	public String getCreditMessageToRepayWarn(
			CreditMessageToRepayWarnBeanVO bean, UserBean user) {
		Map<String, Object> paramMap =new HashMap<>();
		String result="success";
		int ret=0;
        // 修改 02：捕捉异常 当报错的时候不会影响其他流程继续
		try{
    		paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
    		paramMap.put("create_user_id", user.getUserId());//id
            paramMap.put("create_user_name", user.getRealname());// 姓名
            // 贷款类型是否为空 如果为空则默认为信贷 1
    		if(bean.getCre_type()==null||"".equals(bean.getCre_type())){
                // 贷款类型
    			paramMap.put("cre_type", "1");
                // 贷款类型
    			paramMap.put("bill_status", "7");
    		}
    		else
    		{
                // 贷款类型
    			paramMap.put("cre_type", bean.getCre_type());
    		}
    		List<WmsCreCreditNotaryWarn> listMap=	wmscrecreditnotarywarnDao.getCreditMessageToRepayWarn(paramMap);
            // 如果是房贷 重新初始化单据编号 需求暂定自动带过去
    		/*if("2".equals(bean.getCre_type())){
    		    Map<String, Object> paMap = new HashMap<String, Object>();
                for(WmsCreCreditNotaryWarn warnbean : listMap) {
                    paramMap.put("appl_date", warnbean.getAppl_date());
                    warnbean.setBill_code(this.wmscrecreditnotarywarnDao.getBillCodeForNotaryWarn(paMap));  
                }
    		}*/
            // 如果list为空或者长度为0则返回没有数据
            if (listMap == null || listMap.size() == 0)
            {
                result = "listNull";
                return result;
            }
            else
            {
                ret = wmscrecreditnotarywarnDao.saveBatch(listMap);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = "exceptionError";
		}
		if(ret==0){
			result="error";
		}
		return result;
	}
	  
    /**
     * 
     * @Title: searchRepaymentConfirmationList
     * @Description: TODO(查询还款确认列表(使用视图))
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2016年11月24日 下午3:37:02
     * history:
     * 1、2016年11月24日 jiaodelong 创建方法
     */
	@Override
	public Map<String, Object> searchRepaymentConfirmationList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(queryInfo.getBill_code())) {
    		paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getSalesman_name_str())) {
    		paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_begin())) {
    		paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getAppl_date_end())) {
    		paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getCustomer_name())) {
    		paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getMobile_telephone())) {
    		paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_begin())) {
    		paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getEnd_time_end())) {
    		paramMap.put("end_time_end", queryInfo.getEnd_time_end());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status())) {
    		paramMap.put("bill_status", queryInfo.getBill_status());
    	}
    	if(StringUtils.isNotBlank(queryInfo.getBill_status_ne())) {
    		paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
    	}
		if (StringUtils.isNotBlank(queryInfo.getIs_show_now())) {
			paramMap.put("is_show_now", queryInfo.getIs_show_now());
		}
		if (StringUtils.isNotBlank(queryInfo.getCurrent_repay_date())) {
            paramMap.put("current_repay_date_reminder", queryInfo.getCurrent_repay_date());
		}
		if (StringUtils.isNotBlank(queryInfo.getBill_status_arr())) {
			paramMap.put("bill_status_arr", SysTools.getSqlInParam(queryInfo.getBill_status_arr()));
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_name())) {
            paramMap.put("category_name", queryInfo.getCategory_name());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getCategory_id())) {
            paramMap.put("category_id", queryInfo.getCategory_id());// 产品
		}
		if (StringUtils.isNotBlank(queryInfo.getRepay_status())) {
            paramMap.put("repay_status", queryInfo.getRepay_status());// 还款状态
        }
		if (StringUtils.isNotBlank(queryInfo.getRepay_status_arr())) {
            paramMap.put("repay_status_arr", SysTools.getSqlInParam(queryInfo.getRepay_status_arr()));
        }
        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_HKQR_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

    	paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag())) {
        	paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
        	paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
    	
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchRepaymentConfirmationList(paramMap);
    	
    	GridDataBean<Map<String, Object>> bean =  
 new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchRepaymentConfirmationCount(paramMap), list);
        return bean.getGridData();
    }

    /**
    * @Title: searchloansSearchList
    * @Description: TODO(贷款数据查询裂变 视图)
    * @param queryInfo
    * @param user
    * @return Map<String, Object>
    * @author: jiaodelong
    * @time:2016年11月23日 上午9:49:04
    * @see com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService#searchloansSearchList(com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO, com.zx.sframe.util.vo.UserBean)
    * history:
    * 1、2016年11月23日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> searchloansSearchList(WmsCreCreditNotaryWarnSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (StringUtils.isNotBlank(queryInfo.getSalesman_name_str()))
        {
            paramMap.put("salesman_name_str", SysTools.getSqlLikeParam(queryInfo.getSalesman_name_str()));
        }
        if (StringUtils.isNotBlank(queryInfo.getAppl_date_begin()))
        {
            paramMap.put("appl_date_begin", queryInfo.getAppl_date_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getAppl_date_end()))
        {
            paramMap.put("appl_date_end", queryInfo.getAppl_date_end());
        }
        if (StringUtils.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtils.isNotBlank(queryInfo.getCustomer_name_all()))
        {
            paramMap.put("customer_name_all", SysTools.getSqlLikeParam(queryInfo.getCustomer_name_all()));
        }
        if (StringUtils.isNotBlank(queryInfo.getMobile_telephone()))
        {
            paramMap.put("mobile_telephone", SysTools.getSqlLikeParam(queryInfo.getMobile_telephone()));
        }
        if (StringUtils.isNotBlank(queryInfo.getEnd_time_begin()))
        {
            paramMap.put("end_time_begin", queryInfo.getEnd_time_begin());
        }
        if (StringUtils.isNotBlank(queryInfo.getEnd_time_end()))
        {
            paramMap.put("end_time_end", queryInfo.getEnd_time_end());
        }
        if (StringUtils.isNotBlank(queryInfo.getBill_status()))
        {
            paramMap.put("bill_status", queryInfo.getBill_status());
        }
        if (StringUtils.isNotBlank(queryInfo.getBill_status_ne()))
        {
            paramMap.put("bill_status_ne", queryInfo.getBill_status_ne());
        }
        if (StringUtils.isNotBlank(queryInfo.getIs_show_now()))
        {
            paramMap.put("is_show_now", queryInfo.getIs_show_now());
        }
        if (StringUtils.isNotBlank(queryInfo.getCurrent_repay_date()))
        {
            paramMap.put("current_repay_date", queryInfo.getCurrent_repay_date());
        }
        if (StringUtils.isNotBlank(queryInfo.getCategory_name()))
        {
            paramMap.put("category_name", queryInfo.getCategory_name());// 产品
        }
        if (StringUtils.isNotBlank(queryInfo.getCategory_id()))
        {
            paramMap.put("category_id", queryInfo.getCategory_id());// 产品
        }
        if (StringUtils.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());// 还款状态
        }
        if (StringUtils.isNotBlank(queryInfo.getRepay_status_arr()))
        {
            paramMap.put("repay_status_arr", SysTools.getSqlInParam(queryInfo.getRepay_status_arr()));
        }
        // 身份证号
        if (StringUtils.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        // 身份证号/共贷人身份证号
        if (StringUtils.isNotBlank(queryInfo.getId_card_all()))
        {
            paramMap.put("id_card_all", SysTools.getSqlLikeParam(queryInfo.getId_card_all()));
        }
        // 团队经理
        if (StringUtils.isNotBlank(queryInfo.getTeam_manager_name()))
        {
            paramMap.put("team_manager_name", queryInfo.getTeam_manager_name());
        }

        // 借款金额(开始)
        if (queryInfo.getAppro_limit_str() != null)
        {
            paramMap.put("appro_limit_str", queryInfo.getAppro_limit_str());
        }
        // 借款金额(结束)
        if (queryInfo.getAppro_limit_end() != null)
        {
            paramMap.put("appro_limit_end", queryInfo.getAppro_limit_end());
        }
        // 所属营业部
        if (StringUtils.isNotBlank(queryInfo.getSales_epartment_name()))
        {
            paramMap.put("sales_epartment_name", queryInfo.getSales_epartment_name());
        }
        // 合同日期(开始)
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_begin()))
        {
            paramMap.put("protocol_date_begin", queryInfo.getProtocol_date_begin());
        }
        // 合同日期(结束)
        if (StringUtils.isNotBlank(queryInfo.getProtocol_date_end()))
        {
            paramMap.put("protocol_date_end", queryInfo.getProtocol_date_end());
        }
        // 终止还款日期(开始)
        if (StringUtils.isNotBlank(queryInfo.getBorrow_end_date_begin()))
        {
            paramMap.put("borrow_end_date_begin", queryInfo.getBorrow_end_date_begin());
        }
        // 终止还款日期(结束)
        if (StringUtils.isNotBlank(queryInfo.getBorrow_end_date_end()))
        {
            paramMap.put("borrow_end_date_end", queryInfo.getBorrow_end_date_end());
        }
        // 月还款(开始)
        if (StringUtils.isNotBlank(queryInfo.getRefund_limit_month_str()))
        {
            paramMap.put("refund_limit_month_str", queryInfo.getRefund_limit_month_str());
        }
        // 月还款(结束)
        if (StringUtils.isNotBlank(queryInfo.getRefund_limit_month_end()))
        {
            paramMap.put("refund_limit_month_end", queryInfo.getRefund_limit_month_end());
        }
        // 打卡金额(开始)
        if (StringUtils.isNotBlank(queryInfo.getLoan_amount_str()))
        {
            paramMap.put("loan_amount_str", queryInfo.getLoan_amount_str());
        }
        // 打卡金额(结束)
        if (StringUtils.isNotBlank(queryInfo.getLoan_amount_end()))
        {
            paramMap.put("loan_amount_end", queryInfo.getLoan_amount_end());
        }
        // 期数
        if (StringUtils.isNotBlank(queryInfo.getBorrow_deadline()))
        {
            paramMap.put("borrow_deadline", queryInfo.getBorrow_deadline());
        }
        // 合同号
        if (StringUtils.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", SysTools.getSqlLikeParam(queryInfo.getProtocol_id_year_num()));
        }
        // 次数
        if (StringUtils.isNotBlank(queryInfo.getThe_number()))
        {
            paramMap.put("the_number", queryInfo.getThe_number());
        }
        // 业务状态
        if (StringUtils.isNotBlank(queryInfo.getBill_type()))
        {
            paramMap.put("bill_type", queryInfo.getBill_type());
        }
        // 合同金额(开始)
        if (StringUtils.isNotBlank(queryInfo.getPrincipal_lower_str()))
        {
            paramMap.put("principal_lower_str", queryInfo.getPrincipal_lower_str());
        }
        // 合同金额(结束)
        if (StringUtils.isNotBlank(queryInfo.getPrincipal_lower_end()))
        {
            paramMap.put("principal_lower_end", queryInfo.getPrincipal_lower_end());
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }

        // 权限控制
        paramMap.put("salesman_id", user.getUserId());// 登陆人id
        paramMap.put("menu_url", WmsHelp.MENU_URL_DKSJCX_LIST);
        paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门

        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());

        if (StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag()))
        {
            paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        }
        else
        {
            paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
        // 数据查询
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.searchloansSearchList(paramMap);
        // 条数查询+装载数据
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(), wmscrecreditnotarywarnDao.searchloansSearchCount(paramMap), list);
        return bean.getGridData();
    }

    /**
     * @Title: getTeamManagerName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @param user
     * @return 
     * @author: admin
     * @time:2016年12月7日 下午5:26:35
     * @see com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService#getTeamManagerName(com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2016年12月7日 admin 创建方法
    */
    @Override
    public Map<String, Object> getTeamManagerName(WmsCreCreditNotaryWarnSearchBeanVO bean, UserBean user)
    {
        Map<String, Object> map=new HashMap<>();
        // 姓名
        if (StringUtils.isNotBlank(bean.getTeam_manager_name()))
        {
            map.put("team_manager_name", SysTools.getSqlLikeParam(bean.getTeam_manager_name()));
        }
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.getTeamManagerName(map);
        // 判断是否为空
        if (list != null && list.size() > 0)
        {
            map.put("result", list);
        }
        else
        {
            map.put("result", "error");
        }
        return map;
    }

    /**
     * @Title: combinedLoan
     * @Description: (公证提醒还款提醒组合贷)
     * @param queryInfo
     * @param user
     * @return 
     * @author: baisong
     * @time:2017年1月5日 下午1:28:37
     * @see com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService#combinedLoan(com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月5日 baisong 创建方法
    */
    @Override
    @Transactional
    public Map<String, Object> combinedLoan(CombinedLoanBeanVO queryInfo, UserBean user)
    {
        Map<String,Object> retMap=new HashMap<>();

        List<String> idList = JsonUtil.jsonArrayToList(queryInfo.getCheckedIds(), String.class);
        // 组合贷主键为空
        if (idList == null || idList.size() == 0)
        {
            retMap.put("result", "iderror");
            return retMap;
        }
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("list", idList);
        List<WmsCreCreditNotaryWarn> notaryWarnList = wmscrecreditnotarywarnDao.getList(paraMap);
        String bill_code_group = null;
        String id_card = "";
        boolean is_name = false;
        int num_group = 0;
        String category_name = "";
        for (WmsCreCreditNotaryWarn warn : notaryWarnList)
        {
            // 组合贷编号是否为空
            if (warn.getBill_code_group() != null)
            {
                bill_code_group = warn.getBill_code_group();
                num_group++;
            }
            // 身份证号码是否为空
            if (warn.getId_card() != null && !"".equals(warn.getId_card()))
            {
                // 原始身份证号码不为空 并且单据身份证号码不为空
                if ("".equals(id_card) && warn.getId_card() != null)
                {
                    id_card = warn.getId_card();
                }
                // 身份证号码是否相同
                else if (warn.getId_card() != null && !id_card.equals(warn.getId_card()))
                {
                    retMap.put("result", "idcarderror");
                    return retMap;
                }
            }
            else
            {
                is_name = true;
            }
        }
        if (num_group > 1)
        {
            retMap.put("result", "codeerror");
            return retMap;
        }
        // 是否需要判断姓名
        if (is_name)
        {
            for (WmsCreCreditNotaryWarn warn : notaryWarnList)
            {
                // 姓名是否为空
                if (warn.getCategory_name() != null && !"".equals(warn.getCategory_name()))
                {
                    // 原始姓名 不为空 并且单据 姓名不为空
                    if ("".equals(category_name) && warn.getCategory_name() != null)
                    {
                        category_name = warn.getCategory_name();
                    }
                    // 姓名是否相同
                    else if (warn.getCategory_name() != null && !category_name.equals(warn.getCategory_name()))
                    {
                        retMap.put("result", "categorynameerror");
                        return retMap;
                    }
                }
                else
                {
                    retMap.put("result", "categorynamenull");
                    return retMap;
                }
            }
        }
        WmsCreCreditNotaryWarn wNotaryWarn = new WmsCreCreditNotaryWarn();
        // 判断组合贷
        if ("".equals(bill_code_group) || bill_code_group == null)
        {
            wNotaryWarn.setBill_code_group(CodeNoUtil.getGroup_code());
        }
        else
        {
            wNotaryWarn.setBill_code_group(bill_code_group);
        }

        wNotaryWarn.setGroup_date(new Timestamp(System.currentTimeMillis()));
        for (String id : idList)
        {
            wNotaryWarn.setWms_cre_credit_notary_warn_id(Integer.valueOf(id));
            wmscrecreditnotarywarnDao.update(wNotaryWarn);
        }
        // 判断组合贷编号
        if (wNotaryWarn.getBill_code_group() != null)
        {
            paraMap.put("bill_code_group", wNotaryWarn.getBill_code_group());
            List<Map<String, Object>> notaryWarnGroupList = wmscrecreditnotarywarnDao.getGroupALlList(paraMap);
            if (notaryWarnGroupList != null && notaryWarnGroupList.size() > 0)
            {
                retMap.put("result", "success");
            }
            else
            {
                // 缺少组合贷 等额本息产品
                retMap.put("result", "creloantypeerror");
                throw new RuntimeException();
            }
        }
        else
        {
            // 组合贷编号错误
            retMap.put("result", "billcodegrouperror");
            throw new RuntimeException();
        }
        return retMap;
    }

    /**
     * @Title: getAgreeInfoListForZQ
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param queryInfo
     * @param user
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 上午9:20:53
     * @see com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService#getAgreeInfoListForZQ(com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
    */
    @Override
    public Map<String, Object> getAgreeInfoListForZQ(WmsCreApproSearchBeanVO queryInfo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtil.isNotBlank(queryInfo.getBill_type()))
        {
            if(queryInfo.getBill_type().equals("02")){
                paramMap.put("bill_type", 2);
            }
            else if(queryInfo.getBill_type().equals("03")){
                paramMap.put("bill_type", 3);
            }
            
        }
        if (StringUtil.isNotBlank(queryInfo.getRepay_status()))
        {
            paramMap.put("repay_status", queryInfo.getRepay_status());
        }
        
        if (StringUtil.isNotBlank(queryInfo.getBill_code()))
        {
            paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
        }
        if (queryInfo.getCreate_timestamp_begin() != null && !"".equals(queryInfo.getCreate_timestamp_begin()))
        {
            paramMap.put("create_timestamp_begin", queryInfo.getCreate_timestamp_begin());
        }
        if (queryInfo.getCreate_timestamp_end() != null && !"".equals(queryInfo.getCreate_timestamp_end()))
        {
            paramMap.put("create_timestamp_end", queryInfo.getCreate_timestamp_end());
        }         
        if (StringUtil.isNotBlank(queryInfo.getSalesman_name()))
        {
            paramMap.put("salesman_name", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
            paramMap.put("salesman_shortcode", SysTools.getSqlLikeParam(queryInfo.getSalesman_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getId_card()))
        {
            paramMap.put("id_card", SysTools.getSqlLikeParam(queryInfo.getId_card()));
        }
        if (StringUtil.isNotBlank(queryInfo.getCustomer_name()))
        {
            paramMap.put("customer_name", SysTools.getSqlLikeParam(queryInfo.getCustomer_name()));
        }
        if (StringUtil.isNotBlank(queryInfo.getMort_flag()))
        {
            paramMap.put("mort_flag", queryInfo.getMort_flag());// 抵押状态
        }
        // 开发模式 1为开发模式 其他为正常权限模式
        if (!"1".equals(PlatformGlobalVar.SYS_PROPERTIES.get("isDeveloperMode")))
        {
            paramMap.put("salesman_id", user.getUserId());// 登陆人id
            paramMap.put("menu_url", WmsHelp.MENU_URL_QDHT_LIST);
            paramMap.put("childrendept", wmsCreCreditHeadService.queryChildrenDeptInfo(paramMap)); // 获取可查看的部门
        }
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        if(StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag()) && queryInfo.getNeed_paging_flag().equals("0")) {
            paramMap.put("pagesize", null);
            paramMap.put("offset", null);
        } else {
            paramMap.put("pagesize", queryInfo.getPagesize());
            paramMap.put("offset", queryInfo.getOffset());
        }
        List<Map<String, Object>> list = wmscrecreditnotarywarnDao.getAgreeInfoListForZQ(paramMap);
        // 判断是否分页
        if (StringUtils.isNotEmpty(queryInfo.getNeed_paging_flag()) && queryInfo.getNeed_paging_flag().equals("0"))
        {
            Map<String, Object> map = new HashMap<>();
            map.put("Rows", list);
            return map;
        }
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmscrecreditnotarywarnDao.findCountAgreeInfoListForZQ(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * 
     * @Title: sendMessageBirthday
     * @Description: (获取信息放款数据同步到还款提醒模块)
     * @param request
     * @param bean
     * @return String
     * @author: baisong
     * @time:2016年11月15日 下午1:30:40 history: 1、2016年11月15日 baisong 创建方法
     */
    @Override
    public String getHouseCreditMessageToRepayWarn(CreditMessageToRepayWarnBeanVO bean, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<>();
        String result = "success";
        Integer wms_cre_credit_notary_warn_id = null;
        int ret = 0;
        // 修改 02：捕捉异常 当报错的时候不会影响其他流程继续
        try
        {
            paramMap.put("wms_cre_credit_head_id", bean.getWms_cre_credit_head_id());
            paramMap.put("create_user_id", user.getUserId());// id
            paramMap.put("create_user_name", user.getRealname());// 姓名
            // 贷款类型
            paramMap.put("cre_type", bean.getCre_type());
            List<WmsCreCreditNotaryWarn> listMap = wmscrecreditnotarywarnDao.getHosueCreditMessageToRepayWarn(paramMap);
            // 如果list为空或者长度为0则返回没有数据
            if (listMap == null || listMap.size() == 0)
            {
                result = "listNull";
                return result;
            }
            else
            {
                ret = wmscrecreditnotarywarnDao.save(listMap.get(0));
                wms_cre_credit_notary_warn_id = listMap.get(0).getWms_cre_credit_notary_warn_id();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = "exceptionError";
        }
        if (ret == 0)
        {
            result = "error";
        }
        else
        {
            result = String.valueOf(wms_cre_credit_notary_warn_id);
        }
        return result;
    }
}
