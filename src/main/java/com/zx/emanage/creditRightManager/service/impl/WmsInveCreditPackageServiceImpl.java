package com.zx.emanage.creditRightManager.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.zx.emanage.creditRightManager.persist.WmsInveCreditCRPackageDao;
import com.zx.emanage.creditRightManager.persist.WmsInveMulticreInfoDao;
import com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService;
import com.zx.emanage.creditRightManager.vo.WmsCredRightNotifyCustomerSearchBeanVo;
import com.zx.emanage.creditRightManager.vo.WmsInveCreditPackageSearchBeanVO;
import com.zx.emanage.inve.persist.WmsInveClerkDataDao;
import com.zx.emanage.inve.persist.WmsInveClerkProtocolDao;
import com.zx.emanage.inve.persist.WmsInveClerkRegionDao;
import com.zx.emanage.inve.persist.WmsInveCreditPackageDao;
import com.zx.emanage.inve.persist.WmsInveSetSalesLimitDao;
import com.zx.emanage.inve.persist.WmsInveTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.util.credit.CreditMatchInterface;
import com.zx.emanage.inve.util.credit.CreditUtils;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.sysmanage.persist.SysDeptDao;
import com.zx.emanage.sysmanage.persist.SysRoleDao;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.entity.ColumnInfo;
import com.zx.emanage.util.gen.entity.PmPersonnel;
import com.zx.emanage.util.gen.entity.WmsInveClerkProtocol;
import com.zx.emanage.util.gen.entity.WmsInveClerkRegion;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsSysDictData;
import com.zx.emanage.util.gen.vo.WmsInveClerkProtocolTransaCrepkgTemp;
import com.zx.emanage.util.gen.vo.WmsInveOfflineCreditImportTemp;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.SysUtil;
import com.zx.sframe.util.digitUpperUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

@Service("wmsinvecreditpackageService")
public class WmsInveCreditPackageServiceImpl implements IWmsInveCreditPackageService
{
    private static Logger log = LoggerFactory.getLogger(WmsInveCreditPackageServiceImpl.class);

    @Autowired
    private WmsInveCreditCRPackageDao wmsInveCreditCRPackageDao;

    @Autowired
    private WmsInveCreditPackageDao wmsInveCreditPackageDao;

    @Autowired
    private WmsInveTransaCrepkgDao wmsInveTransaCrepkgDao;

    @Autowired
    private WmsInveMulticreInfoDao wmsInveMulticreInfoDao;

    @Autowired
    private SysRoleDao sysroleDao_m;

    @Autowired
    private SysDeptDao sysdeptDao;

    @Autowired
    private WmsInveTransaDao wmsInveTransaDao;

    @Autowired
    private WmsInveTransaProdDao wmsInveTransaProdDao;

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @Autowired
    private WmsInveClerkProtocolDao wmsInveClerkProtocolDao;

    @Autowired
    private PmPersonnelDao pmPersonnelDao;

    @Autowired
    private WmsInveClerkRegionDao wmsInveClerkRegionDao;

    @Autowired
    private WmsSysDictDataDao wmsSysDictDataDao;

    @Autowired
    private WmsInveClerkDataDao wmsInveClerkDataDao;
    
    @Autowired
    private WmsInveSetSalesLimitDao wmsInveSetSalesLimitDao;
    /**
     * @Title: getListWithPaging
     * @Description: 债权包列表
     * @param queryInfo
     * @return 
     * @author: WangShuai
     * @time:2017年2月8日 下午1:38:40
     * history:
     * 1、2017年2月8日 WangShuai 创建方法
    */
    @Override
    public Map<String, Object> getListWithPaging(WmsInveCreditPackageSearchBeanVO queryInfo)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sortname", queryInfo.getSortname());
        paramMap.put("sortorder", queryInfo.getSortorder());
        paramMap.put("pagesize", queryInfo.getPagesize());
        paramMap.put("offset", queryInfo.getOffset());
        if (StringUtil.isNotBlank(queryInfo.getCre_type()))
        {
            paramMap.put("cre_type", queryInfo.getCre_type());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_per_name()))
        {
            paramMap.put("cre_per_name", "%" + queryInfo.getCre_per_name() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", "%" + queryInfo.getProtocol_id_year_num() + "%");
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_per_card_id()))
        {
            paramMap.put("cre_per_card_id", "%" + queryInfo.getCre_per_card_id() + "%");
        }

        if (StringUtil.isNotBlank(queryInfo.getCre_pledge_mon_tt_low()))
        {
            paramMap.put("cre_pledge_mon_tt_low", queryInfo.getCre_pledge_mon_tt_low());
        }
        if (StringUtil.isNotBlank(queryInfo.getCre_pledge_mon_tt_high()))
        {
            paramMap.put("cre_pledge_mon_tt_high", queryInfo.getCre_pledge_mon_tt_high());
        }
        if (StringUtil.isNotBlank(queryInfo.getRele_per_id()))
        {
            paramMap.put("rele_per_id", queryInfo.getRele_per_id());
        }
        if (StringUtil.isNotBlank(queryInfo.getLoca_num()))
        {
            paramMap.put("loca_num", queryInfo.getLoca_num());
        }
        if (StringUtil.isNotBlank(queryInfo.getCrepackage_state()))
        {
            paramMap.put("crepackage_state", queryInfo.getCrepackage_state());
        }

        List<Map<String, Object>> list = wmsInveCreditCRPackageDao.search(paramMap);
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       queryInfo.getPage(),
                                                                                       wmsInveCreditCRPackageDao.findCount(paramMap),
                                                                                       list);
        return bean.getGridData();
    }

    /**
     * @Title: getAllLocalNumList
     * @Description: 获取地区下拉框
     * @return 
     * @author: WangShuai
     * @time:2017年2月20日 下午4:42:45
     * history:
     * 1、2017年2月20日 WangShuai 创建方法
    */
    @Override
    public List<Map<String, Object>> getAllLocalNumList()
    {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("region_name", "---请选择---");
        temp.put("loca_num", "");
        list.add(0, temp);

        // 获取地区列表
        List<Map<String, Object>> tplist = new ArrayList<Map<String, Object>>();
        tplist = wmsInveCreditCRPackageDao.getAllLocalNumList();

        list.addAll(tplist);
        return list;
    }

    /**
     * @Title: getAllRelePerList
     * @Description: 获取他项人下拉框
     * @return 
     * @author: WangShuai
     * @time:2017年2月20日 下午4:42:55
     * history:
     * 1、2017年2月20日 WangShuai 创建方法
    */
    @Override
    public List<Map<String, Object>> getAllRelePerList()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("rele_per_id", "");
        temp.put("personnel_name", "---请选择---");
        list.add(0, temp);

        // 获取他项人列表
        List<Map<String, Object>> tplist = wmsInveCreditCRPackageDao.getAllRelePerList();

        list.addAll(tplist);
        return list;
    }

    /**
     * @Title: update
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @param user
     * @return 
     * @author: Guanxu
     * @time:2017年2月21日 下午7:29:38
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#update(com.zx.emanage.util.gen.entity.WmsInveCreditPackage, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年2月21日 Guanxu 创建方法
    */
    @Override
    public String update(WmsInveCreditPackage bean, UserBean user)
    {
        bean.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        bean.setLast_update_user_id(user.getUserId());
        wmsInveCreditPackageDao.update(bean);
        return "success";
    }

    /**
     * @Title: getNotifyCustomerForCreditEnd
     * @Description: 查询抵押包到期未更换债权的单据信息
     * @param searchBeanVo 查询条件
     * @param user 登录用户
     * @return 抵押包到期未更换债权的单据信息集合
     * @author: jinzhm
     * @time:2017年3月13日 上午10:40:57
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getNotifyCustomerForCreditEnd(com.zx.emanage.creditRightManager.vo.WmsCredRightNotifyCustomerSearchBeanVo)
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
    */
    @Override
    public Map<String, Object> getNotifyCustomerForCreditEnd(WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo,
                                                             UserBean user)
    {
        // 格式化查询条件，转成map解构;包括数据权限
        Map<String, Object> paramMap = formatSearchCondition(searchBeanVo, user);
        // 分页信息
        paramMap.put("offset", searchBeanVo.getOffset());
        paramMap.put("pageSize", searchBeanVo.getPagesize());
        List<Map<String, Object>> resultMapList = wmsInveCreditCRPackageDao.getAllNeedNotifyCustomerForCreditEndList(paramMap);
        // 分页数据转换
        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(
                                                                                       searchBeanVo.getPage(),
                                                                                       wmsInveCreditCRPackageDao.getAllNeedNotifyCustomerForCreditEndListCount(paramMap),
                                                                                       resultMapList);
        return bean.getGridData();
    }

    /**
     * @Title: getNotifyCustomerForCreditEndWithoutPaging
     * @Description: 无分页查询抵押包到期的客户单据信息
     * @param request 请求信息
     * @param searchBeanVo 查询条件
     * @return 抵押包到期的客户单据信息集合
     * @author: jinzhm
     * @time:2017年6月28日 下午2:30:04
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getNotifyCustomerForCreditEndWithoutPaging(com.zx.emanage.creditRightManager.vo.WmsCredRightNotifyCustomerSearchBeanVo, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月28日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> getNotifyCustomerForCreditEndWithoutPaging(WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo,
                                                                                UserBean user)
    {
        // 格式化查询条件，转成map解构;包括数据权限
        Map<String, Object> paramMap = formatSearchCondition(searchBeanVo, user);
        return wmsInveCreditCRPackageDao.getAllNeedNotifyCustomerForCreditEndListWhitoutPaging(paramMap);
    }

    /**
     * @Title: formatSearchCondition
     * @Description: 将查询条件格式化成map解构
     * @param searchBeanVo 查询条件
     * @return 查询条件map
     * @author: jinzhm
     * @time:2017年3月13日 上午11:12:49
     * history:
     * 1、2017年3月13日 jinzhm 创建方法
     */
    private Map<String, Object> formatSearchCondition(WmsCredRightNotifyCustomerSearchBeanVo searchBeanVo, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 抵押包姓名
        if (!StringUtils.isEmpty(searchBeanVo.getCre_per_name()))
        {
            paramMap.put("cre_per_name", searchBeanVo.getCre_per_name());
        }
        // 抵押合同编号
        if (!StringUtils.isEmpty(searchBeanVo.getProtocol_id_year_num()))
        {
            paramMap.put("protocol_id_year_num", searchBeanVo.getProtocol_id_year_num());
        }
        // 身份证号
        if (!StringUtils.isEmpty(searchBeanVo.getCre_per_card_id()))
        {
            paramMap.put("cre_per_card_id", searchBeanVo.getCre_per_card_id());
        }
        // 实际终止开始日期
        if (searchBeanVo.getCrepg_end_date_begin() != null)
        {
            paramMap.put("crepg_end_date_begin", searchBeanVo.getCrepg_end_date_begin());
        }
        // 实际终止结束日期
        if (searchBeanVo.getCrepg_end_date_end() != null)
        {
            paramMap.put("crepg_end_date_end", searchBeanVo.getCrepg_end_date_end());
        }
        if (!StringUtils.isEmpty(searchBeanVo.getEnd_method()) && !"-1".equals(searchBeanVo.getEnd_method()))
        {
            paramMap.put("end_method", searchBeanVo.getEnd_method());
        }
        if (!StringUtils.isEmpty(searchBeanVo.getCus_name()))
        {
            paramMap.put("cus_name", searchBeanVo.getCus_name());
        }
        if (!StringUtils.isEmpty(searchBeanVo.getSalesman_name()))
        {
            paramMap.put("salesman_name", searchBeanVo.getSalesman_name());
        }
        if (!StringUtils.isEmpty(searchBeanVo.getIs_notified()) && !"-1".equals(searchBeanVo.getIs_notified()))
        {
            paramMap.put("is_notified", searchBeanVo.getIs_notified());
        }
        if (!StringUtils.isEmpty(searchBeanVo.getIs_config_notification())
            && !"-1".equals(searchBeanVo.getIs_config_notification()))
        {
            paramMap.put("is_config_notification", searchBeanVo.getIs_config_notification());
        }

        // 权限信息
        List<String> roleList = sysroleDao_m.getUserRoleNameList(user.getUserId());
        for (String role : roleList)
        {
            // 判断该用户的角色
            if (role.equals("理财业务接待专员"))
            {
                // 只能看见自己创建的单据
                paramMap.put("create_user_id", user.getUserId());
            }
            if (role.equals("理财业务接待部主管"))
            {
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务专员"))
            {
                // 可以看见自己是业务员的单据
                paramMap.put("salesman_id", user.getUserId());
            }
            if (role.equals("理财业务部主管"))
            {
                // 可以看见自己部门所有业务员的单据
                // 根据部门ID获得子部门ID
                List<Integer> deptIds = sysdeptDao.getDeptId(user.getDeptId());
                deptIds.add(user.getDeptId());
                // 可以看见子部门的所有业务员单据
                paramMap.put("deptIds", deptIds);
            }
            if (role.equals("理财业务部总监"))
            {
                paramMap.put("super_user", 1);
            }
            if (role.equals("理财业务部副总"))
            {
                // 根据数据权限去获取部门
                paramMap.put("salesman_id", user.getUserId());
                paramMap.put("deptIds_menu", searchBeanVo.getMenu_id());
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
            if (role.equals("财务柜员"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("理财财务主管"))
            {
                // 可以看见所有数据
                paramMap.put("financial_services", 1);
            }
            if (role.equals("抵押包专员"))
            {
                paramMap.put("credit_services", 1);
            }
        }
        paramMap.put("user_id", user.getUserId());
        return paramMap;
    }

    /**
     * @Title: notifyCustomerForCreditEnd
     * @Description: 解析导入excel中数据，发送债权到期提醒
     * @param request 请求信息
     * @param response 响应信息
     * @param user 登录用户
     * @return 
     * @author: jinzhm
     * @time:2017年6月8日 下午4:23:30
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#notifyCustomerForCreditEnd(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    @Override
    public Map<String, Object> notifyCustomerForCreditEnd(HttpServletRequest request, HttpServletResponse response,
                                                          UserBean user)
    {
        String result = "success";
        Boolean flag = true;
        
        List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();

        if (this.multipartResolver != null && this.multipartResolver.isMultipart(request))
        {
            if (request instanceof MultipartHttpServletRequest)
            {
                MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = mRequest.getFileMap();

                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
                {
                    MultipartFile mf = entity.getValue();
                    long fileSize = mf.getSize();
                    if (fileSize > 60 * 1024 * 1024)
                    {
                        result = "上传附件不能超过60M！";
                        flag = false;
                        break;
                    }
                    String srcFileName = mf.getOriginalFilename();// 获取文件名称
                    String postfix = "";
                    if (srcFileName.lastIndexOf(".") > -1)
                    {
                        postfix = srcFileName.substring(srcFileName.lastIndexOf(".") + 1).toLowerCase();
                        if (!("xls".equals(postfix) || "xlsx".equals(postfix)))
                        {
                            result = "不允许上传模板以外的格式文件！";
                            flag = false;
                            break;
                        }
                    }

                    // Excel 2007获取方法
                    Workbook book = null;

                    try
                    {
                        if ("xls".equals(postfix))
                        {
                            book = new HSSFWorkbook(mf.getInputStream());

                        }
                        else if ("xlsx".equals(postfix))
                        {
                            book = new XSSFWorkbook(mf.getInputStream());
                        }
                    }
                    catch (IOException e1)
                    {
                        result = e1.getMessage();
                        e1.printStackTrace();
                    }

                    // 读取表格的第一个sheet页
                    Sheet sheet = book.getSheetAt(0);
                    Row row = null;
                    // 总行
                    int totalRows = sheet.getLastRowNum();

                    Map<String, Object> dataMap = null;

                    for (int i = 0; i <= totalRows; i++)
                    {
                        row = sheet.getRow(i);
                        dataMap = new HashMap<String, Object>();
                        // 如果下一行单据编号为空则返回
                        if ("".equals(getValueFromCell(row, 0)) || null == getValueFromCell(row, 0))
                        {
                            continue;
                        }

                        dataMap.put("cusName", row.getCell(0).toString().trim());
                        dataMap.put("cusId", row.getCell(1).toString().trim());
                        dataMap.put("cusPhone", row.getCell(2).toString().trim());
                        dataMap.put("protCode", row.getCell(3).toString().trim());
                        dataMap.put("endCreditId", row.getCell(4).toString().trim());
                        dataMap.put("transaId", row.getCell(5).toString().trim());

                        dataMapList.add(dataMap);
                    }
                }
            }
        }

        if (!dataMapList.isEmpty())
        {
            List<Map<String, Object>> newDataMapList = formatNotificationDataMapList(dataMapList);
            for (int i = 0; i < newDataMapList.size(); i++)
            {
                try
                {
                    sendMessageToCustomerForCreditEnd(newDataMapList.get(i), user);
                }
                catch (Exception e)
                {
                    log.error("抵押包到期通知客户发送短信失败", e);
                    // e.printStackTrace();
                }
            }
        }

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        resMap.put("flag", flag.toString());
        return resMap;
    }

    // 获取对应单元格的值
    private String getValueFromCell(Row row, int num)
    {
        if (row == null)
        {
            return null;
        }
        if (row.getCell(num) == null)
        {
            return null;
        }
        else
        {
            if ("".equals(row.getCell(num).toString()))
            {
                return "";
            }
        }
        return row.getCell(num).toString();
    }

    /**
     * @Title: formatNotificationDataMapList
     * @Description: 格式化需要短信通知的数据为发送短信需要的数据
     * @param dataMapList 需要短信通知的信息集合
     * @return 格式化后数据集合
     * @author: jinzhm
     * @time:2017年6月8日 下午6:03:44
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    private List<Map<String, Object>> formatNotificationDataMapList(List<Map<String, Object>> dataMapList)
    {
        // 格式化后数据集合
        List<Map<String, Object>> newDataMapList = new ArrayList<Map<String, Object>>();
        // 当前正在处理的客户id
        String currentCusId = "";

        // 循环所有数据
        for (int i = 0; i < dataMapList.size(); i++)
        {
            Map<String, Object> dataMap = getDataMapFromList(dataMapList, i);

            String cusId = String.valueOf(dataMap.get("cusId"));
            // 如果客户id是已处理的客户id直接跳过
            if (currentCusId.equals(cusId))
            {
                continue;
            }
            // 更新当前处理的客户id
            currentCusId = cusId;
            // 封装数据
            Map<String, Object> newDataMap = new HashMap<String, Object>();
            newDataMap.put("cusName", String.valueOf(dataMap.get("cusName")));
            newDataMap.put("cusId", String.valueOf(dataMap.get("cusId")));
            newDataMap.put("cusPhone", String.valueOf(dataMap.get("cusPhone")));
            // 处理计数
            int count = 0;

            String protCodeList = "";
            String endCreditIdList = "";
            String transaIdList = "";

            // 循环继续向后去值判断，如客户id相同，继续处理
            do
            {
                // 如果已经存在相同的合同编号不继续添加
                if (("," + protCodeList).indexOf("," + getDataMapFromList(dataMapList, i + count).get("protCode") + ",") < 0)
                {
                    protCodeList += getDataMapFromList(dataMapList, i + count).get("protCode") + ",";
                }
                // 如果已经存在相同的抵押包编号不继续添加
                if (("," + endCreditIdList).indexOf("," + getDataMapFromList(dataMapList, i + count).get("endCreditId")
                                                    + ",") < 0)
                {
                    endCreditIdList += getDataMapFromList(dataMapList, i + count).get("endCreditId") + ",";
                }
                // 如果已经存在相同的单据主键不继续添加
                if (("," + transaIdList).indexOf("," + getDataMapFromList(dataMapList, i + count).get("transaId") + ",") < 0)
                {
                    transaIdList += getDataMapFromList(dataMapList, i + count).get("transaId") + ",";
                }
                // 处理计数加1
                count++;
                // 如果下一个客户id和当前处理的客户id相同继续处理
            } while (cusId.equals(String.valueOf(getDataMapFromList(dataMapList, i + count).get("cusId"))));

            // 去掉最后一个逗号，封装数据
            newDataMap.put("protCodeList", protCodeList.substring(0, protCodeList.length() - 1));
            newDataMap.put("endCreditIdList", endCreditIdList.substring(0, endCreditIdList.length() - 1));
            newDataMap.put("transaIdList", transaIdList.substring(0, transaIdList.length() - 1));

            newDataMapList.add(newDataMap);
        }

        return newDataMapList;
    }

    /**
     * @Title: getDataMapFromList
     * @Description: 获得list集合中的元素，如果要获得的元素的下标超过集合长度返回空map集合
     * @param dataList list集合
     * @param i 要获得的元素下标
     * @return 元素，如果下标超过list长度返回空map集合
     * @author: jinzhm
     * @time:2017年6月8日 下午6:03:10
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    private Map<String, Object> getDataMapFromList(List<Map<String, Object>> dataList, int i)
    {
        if (i > dataList.size() - 1)
        {
            return new HashMap<String, Object>();
        }
        return dataList.get(i);
    }

    /**
     * @Title: sendMessageToCustomerForCreditEnd
     * @Description: 发送短信提醒客户债权到期
     * @param data 客户及合同编号信息
     *      [
     *          {
     *              cusName: '金志明',
     *              cusId: 90382, //crm客户id
     *              cusPhone: 18600000000,
     *              protCodeList: "111, 222", // 合同编号 （字符串逗号隔开）
     *              endCreditIdList: 1,2,3, // 到期的债权id （字符串逗号隔开）
     *              transaIdList: 1,2,3 // 受影响的单据 （字符串逗号隔开）
     *          }
     *      ]
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年3月14日 上午10:01:59
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#sendMessageToCustomerForCreditEnd(java.lang.String, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void sendMessageToCustomerForCreditEnd(String data, UserBean user)
    {

        Gson gson = new Gson();
        List<LinkedTreeMap> cusList = new Gson().fromJson(data, ArrayList.class);
        for (LinkedTreeMap treeMap : cusList)
        {
            try
            {
                sendMessageToCustomerForCreditEnd(treeMap, user);
            }
            catch (Exception e)
            {
                log.error("抵押包到期通知客户发送短信失败", e);
                // e.printStackTrace();
            }
        }
    }

    /**
     * @Title: sendMessageToCustomerForCreditEnd
     * @Description: 发送债权到期短信提醒并记录日志信息
     * @param treeMap 数据
     * @param user 登录用户
     * @author: jinzhm
     * @time:2017年6月8日 下午6:02:35
     * history:
     * 1、2017年6月8日 jinzhm 创建方法
     */
    private void sendMessageToCustomerForCreditEnd(Map<String, Object> treeMap, UserBean user)
    {
        // 发送短信参数集合
        Map<String, String> sendMap = null;
        // 短信内容参数集合
        Map<String, Object> contentMap = null;
        // 更新上次提醒时间参数集合
        Map<String, Object> paramMap = null;

        // 设置发送信息
        sendMap = new HashMap<String, String>();
        sendMap.put("tpl_id", "2284");// 设置模板id
        sendMap.put("tel", String.valueOf(treeMap.get("cusPhone")));// 设置电话

        // 设置发送模板中参数信息
        contentMap = new HashMap<String, Object>();
        contentMap.put("protocol_codes", String.valueOf(treeMap.get("protCodeList")));

        Gson gson = new Gson();
        sendMap.put("paramJson", gson.toJson(contentMap));

        try
        {
            // 发送短信通知
            SysUtil.sendMsg(sendMap);
        }
        catch (ClientProtocolException e)
        {
            log.error("抵押包到期通知客户发送短信失败", e);
        }
        catch (IOException e)
        {
            log.error("抵押包到期通知客户发送短信失败", e);
        }

        // 更新最后一次提醒客户时间
        paramMap = new HashMap<String, Object>();
        paramMap.put("transaIds", treeMap.get("transaIdList"));
        paramMap.put("endCreditIds", treeMap.get("endCreditIdList"));
        paramMap.put("userId", user.getUserId());
        wmsInveCreditCRPackageDao.updateCreditEndNotifyTime(paramMap);

        // 保存发送短信日志信息
        saveNotificationLog(gson.toJson(sendMap), 3,
                            "上单主键：" + treeMap.get("transaIdList") + ";到期抵押包主键：" + treeMap.get("endCreditIdList")
                                    + ";通知用户主键及姓名：" + user.getUserId() + "/" + user.getRealname());
    }

    /**
     * @Title: saveNotificationLog
     * @Description: 记录短信通知日志
     * @param content 报文内容
     * @param type 通知类型
     * @param remark 备注
     * @author: jinzhm
     * @time:2017年1月24日 下午1:03:20
     * history:
     * 1、2017年1月24日 jinzhm 创建方法
     */
    private void saveNotificationLog(String content, int type, String remark)
    {
        Map<String, Object> logMap = new HashMap<String, Object>();
        logMap.put("content", content);
        logMap.put("send_time", new Timestamp(System.currentTimeMillis()));
        logMap.put("notification_type", type);
        logMap.put("remark", remark);
        wmsInveTransaDao.saveWmsInveNotificationLog(logMap);
    }

    /**
     * @Title: destroyCreditPackage
     * @Description: 作废某个抵押包信息
     * @param creditPackageId 抵押包主键
     * @param reason 作废原因
     * @param user 登录用户信息
     * @return 成功或错误信息
     * @author: jinzhm
     * @time:2017年3月14日 下午3:51:20
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#destroyCreditPackage(int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年3月14日 jinzhm 创建方法
    */
    @Override
    @Transactional
    public String destroyCreditPackage(int creditPackageId, String reason, UserBean user)
    {
        // 获得抵押包信息
        WmsInveCreditPackage creditPackage = wmsInveCreditPackageDao.get(creditPackageId);

        // 如果抵押包是失效状态的话，直接返回-1提示已经是失效状态
        if ("0".equals(creditPackage.getEnable_flag()) && !StringUtils.isEmpty(creditPackage.getDestroy_reason()))
        {
            return "-1";
        }

        // 抵押包已使用金额不等于0，说明已经使用了（占用或生效）
        if (creditPackage.getMatched_product_account().compareTo(BigDecimal.ZERO) != 0)
        {
            // 下面需要分辨是占用还是生效，直接看是否有生效的债权单据关联数据
            // 查询该抵押包和单据生效关联的数据
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("creditPackageId", creditPackageId);
            paramMap.put("state", CreditMatchInterface.ACL_STATE_ENABLE);
            List<WmsInveTransaCrepkg> transaCrepkgList = wmsInveTransaCrepkgDao.queryCreditPackageInState(paramMap);

            // 如果该集合不是空集合，说明已经有生效的债权单据关联数据
            if (!transaCrepkgList.isEmpty())
            {
                return "1";
            }
            // 如果该集合为空，说明没有生效的，但是因为已使用金额不等于0说明是有占用数据
            else
            {
                return "2";
            }
        }

        // 作废抵押包
        WmsInveCreditPackage updCreditPakcage = new WmsInveCreditPackage();
        updCreditPakcage.setWms_inve_credit_package_id(creditPackageId);
        // 抵押包设置成失效状态
        updCreditPakcage.setEnable_flag("0");
        // 设置修改人和修改时间和作废原因
        updCreditPakcage.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        updCreditPakcage.setLast_update_user_id(user.getUserId());
        updCreditPakcage.setDestroy_reason(reason);
        wmsInveCreditPackageDao.update(updCreditPakcage);

        return "success";
    }
    /**
     * @Title: getCompanyRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 全集团剩余债权额度、今日到期抵押包额度
     * @author: zhangyunfei
     * @time:2017年4月5日 下午4:35:55
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getCompanyRemainCreditPackage()
     * history:
     * 1、2017年4月5日 Administrator 创建方法
    */
    @Override
    public List<Map<String, Object>> getCompanyRemainCreditPackage()
    {
        return wmsInveCreditPackageDao.getCompanyRemainCreditPackage();
    }

    /**
     * @Title: getGroupRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各组已售额度、剩余债权额度
     * @return 
     * @author: Administrator
     * @time:2017年4月19日 下午2:55:45
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getGroupRemainCreditPackage()
     * history:
     * 1、2017年4月19日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getGroupRemainCreditPackage()
    {
        List<Map<String, Object>> list = wmsInveCreditPackageDao.getGroupRemainCreditPackage();
        // 将集合封装成前台页面grid所需的格式（行---->列转换）
        Map<String, Object> map = getGroupRemainCreditPackageGridMap(list);

        return map;
    }

    /**
     * 
     * @Title: getGroupRemainCreditPackageGridMap
     * @Description: 各组债权匹配情况 格式转换(行---->列转换)
     * @param list
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月10日 上午10:43:50
     * history:
     * 1、2017年4月10日 Administrator 创建方法
     */
    private Map<String, Object> getGroupRemainCreditPackageGridMap(List<Map<String, Object>> list)
    {
        // 存放column的集合
        LinkedList<ColumnInfo> columnList = new LinkedList<ColumnInfo>();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName("NO1");
        columnInfo.setWidth(180);
        columnInfo.setFrozen(true);
        // column标题列第一列
        columnList.addFirst(columnInfo);

        List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
        // 存放封装之后的column和grid
        Map<String, Object> map = new HashMap<String, Object>();
        // 存放已售额度信息
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 存放 剩余额度信息
        Map<String, Object> map2 = new HashMap<String, Object>();
        // gird第一列显示内容
        map1.put("NO1", "已售额度");
        map2.put("NO1", "剩余额度 ");
        // 遍历集合 将组名等属性封装放到list中 并且将grid第一行已售额度和grid第二行放到map中
        for (int i = 0; i < list.size(); i++)
        {
            // 封装column 设置各属性
            columnInfo = new ColumnInfo();
            columnInfo.setName(list.get(i).get("group_id").toString());
            columnInfo.setDisplay(list.get(i).get("group_name").toString());
            columnInfo.setWidth(120);
            columnList.add(columnInfo);
            // 封装grid第一行 已售额度
            map1.put(list.get(i).get("group_id").toString(), ((BigDecimal) (list.get(i).get("used_mon"))).intValue());
            // 封装grid第二行 剩余额度
            map2.put(list.get(i).get("group_id").toString(), ((BigDecimal) (list.get(i).get("remain_mon"))).intValue());
        }
        gridList.add(map1);
        gridList.add(map2);
        // map存放column 和 rows
        map.put("columns", columnList);
        map.put("Rows", gridList);

        return map;
    }

    /**
     * @Title: getRegionRemainCreditPackage
     * @Description: 实时债权匹配情况-----查询 各地区销售限额、已售额度、剩余额度
     * @return 
     * @author: zhangyufnei
     * @time:2017年4月7日 下午4:36:37
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getRegionRemainCreditPackage()
     * history:
     * 1、2017年4月7日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getRegionRemainCreditPackage()
    {
        List<Map<String, Object>> list = wmsInveCreditPackageDao.getRegionRemainCreditPackage();
        // 将各地区债权匹配情况集合封装成前台页面grid所需的格式（行---->列转换）
        Map<String, Object> map = getRegionRemainCreditPackageGridMap(list);

        return map;
    }

    /**
     * 
     * @Title: getRegionRemainCreditPackageGridMap
     * @Description: 各组债权匹配情况 格式转换(行---->列转换)
     * @param list
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月10日 上午10:45:50
     * history:
     * 1、2017年4月10日 Administrator 创建方法
     */
    private Map<String, Object> getRegionRemainCreditPackageGridMap(List<Map<String, Object>> list)
    {
        // 存放column的集合
        LinkedList<ColumnInfo> columnList = new LinkedList<ColumnInfo>();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName("NO1");
        columnInfo.setWidth(250);
        columnInfo.setFrozen(true);
        // column标题列第一列
        columnList.addFirst(columnInfo);

        List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
        // 存放封装之后的column和grid
        Map<String, Object> map = new HashMap<String, Object>();
        // 存放今日销售限额
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 存放 今日已售额度
        Map<String, Object> map2 = new HashMap<String, Object>();
        // 存放 今日剩余额度
        Map<String, Object> map3 = new HashMap<String, Object>();
        // gird第一列显示内容
        map1.put("NO1", "今日销售限额");
        map2.put("NO1", "今日已售额度");
        map3.put("NO1", "今日剩余额度");

        // 遍历集合 将组名等属性封装放到list中 并且将grid第一行已售额度和grid第二行放到map中
        for (int i = 0; i < list.size(); i++)
        {
            // 封装column 设置各属性
            columnInfo = new ColumnInfo();
            columnInfo.setName(list.get(i).get("region_number").toString());
            columnInfo.setDisplay(list.get(i).get("region_name").toString());
            columnInfo.setWidth(230);
            columnList.add(columnInfo);
            // 封装grid第一行 销售限额
            map1.put(list.get(i).get("region_number").toString(), (list.get(i).get("cur_limit_mon")).toString());
            // 封装grid第二行 已售额度
            map2.put(list.get(i).get("region_number").toString(), ((BigDecimal) (list.get(i).get("used_mon"))).intValue());
            // 封装grid第三行 剩余额度
            if ((!list.get(i).get("remain_mon").toString().equals("无限额")) && list.get(i).get("cur_warn_mon") != null && (Integer.parseInt(list.get(i).get("remain_mon").toString()) - Integer.parseInt(list.get(i).get("cur_warn_mon").toString()) <= 0))
            {
                map3.put(list.get(i).get("region_number").toString(), "<span style='color:red'>" + (list.get(i).get("remain_mon")).toString() + "</span>");
            }else{
                map3.put(list.get(i).get("region_number").toString(), "<span>" + (list.get(i).get("remain_mon")).toString() + "</span>");
            }
        }
        gridList.add(map1);
        gridList.add(map2);
        gridList.add(map3);
        // map存放column 和 rows
        map.put("columns", columnList);
        map.put("Rows", gridList);

        return map;
    }

    /**
     * @Title: getAllGroupSaleAmountInfo
     * @Description: 实时债权匹配情况-----查询 全集团销售情况 线上、线下(全新支付/赎回再签)
     * @return 
     * @author: Administrator
     * @time:2017年5月10日 上午9:53:14
     * @see com.zx.emanage.creditRightManager.service.IWmsInveCreditPackageService#getAllGroupSaleAmountInfo()
     * history:
     * 1、2017年5月10日 Administrator 创建方法
    */
    @Override
    public Map<String, Object> getAllGroupSaleAmountInfo()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("date", DateUtil.getDate10(new Date()));
        // 调用其他开发人员的方法参数名固定
        paramMap.put("create_timestamp", DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<Map<String, Object>> list = wmsInveClerkDataDao.getAllGroupSaleAmountInfo(paramMap);       
        // 将各地区债权匹配情况集合封装成前台页面grid所需的格式（行---->列转换）
        Map<String, Object> map = getAllGroupSaleAmountInfoGridMap(list);
        //今日【线上-全新支付】销售限额
        Map<String, Object> wmsInveSetSalesLimitMap = wmsInveSetSalesLimitDao.selectSalesLimitInfo(paramMap);
        if (wmsInveSetSalesLimitMap == null)
        {
            map.put("limit_amount", "无限额");
        }
        else
        {
            if (wmsInveSetSalesLimitMap.get("limit_amount") == null || "".equals(wmsInveSetSalesLimitMap.get("limit_amount")))
            {
                map.put("limit_amount", "无限额");
            }
            else
            {
                map.put("limit_amount", wmsInveSetSalesLimitMap.get("limit_amount").toString());
            }
        }

        return map;
    }

    /**
     * 
     * @Title: getRegionRemainCreditPackageGridMap
     * @Description:  全集团销售情况 线上、线下格式转换(行---->列转换)
     * @param list
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月10日 上午10:45:50
     * history:
     * 1、2017年4月10日 Administrator 创建方法
     */
    private Map<String, Object> getAllGroupSaleAmountInfoGridMap(List<Map<String, Object>> list)
    {
        String contract_signing_type = "";
        int totalTransaNewAccountPaid = 0;
        // 存放column的集合
        LinkedList<ColumnInfo> columnList = new LinkedList<ColumnInfo>();
        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.setName("NO1");
        columnInfo.setDisplay("<div style='width: 100%;text-align:left;padding-left: 10px;'> 今日【全途径】已销售金额<div>");
        columnInfo.setWidth(820);
        // column标题列第一列
        columnList.addFirst(columnInfo);

        List<Map<String, Object>> gridList = new ArrayList<Map<String, Object>>();
        // 存放封装之后的column和grid
        Map<String, Object> map = new HashMap<String, Object>();
        // 存放 线上之和
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 存放 线上全新支付
        Map<String, Object> map2 = new HashMap<String, Object>();
        // 存放线上 赎回再签
        Map<String, Object> map3 = new HashMap<String, Object>();
        // 存放 线下之和
        Map<String, Object> map4 = new HashMap<String, Object>();
        // 存放 线下全新支付
        Map<String, Object> map5 = new HashMap<String, Object>();
        // 存放线下 赎回再签
        Map<String, Object> map6 = new HashMap<String, Object>();
        // gird第一列显示内容
        map1.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 10px;'>其中，线上<div>");
        map2.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 20px;'>【全新支付】（含赎回再签中额外加钱部分）<div>");
        map3.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 20px;'>【赎回再签】<div>");
        map4.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 10px;'>其中，线下<div>");
        map5.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 20px;'>【全新支付】（含赎回再签中额外加钱部分）<div>");
        map6.put("NO1", "<div style='width: 100%;text-align:left;padding-left: 20px;'>【赎回再签】<div>");
        
        map1.put("NO2", "0");
        map2.put("NO2", "0");
        map3.put("NO2", "0");
        map4.put("NO2", "0");
        map5.put("NO2", "0");
        map6.put("NO2", "0");

        // 遍历集合 将组名等属性封装放到list中 并且将grid第一行已售额度和grid第二行放到map中
        for (int i = 0; i < list.size(); i++)
        {

            contract_signing_type = list.get(i).get("contract_signing_type").toString();
            totalTransaNewAccountPaid += ((BigDecimal) (list.get(i).get("totalTransaNewAccountPaid"))).add(((BigDecimal) (list.get(i).get("totalTransaRedeemExtendAccountPaid")))).intValue();
            // 线上
            if ("2".equals(contract_signing_type))
            {
                // 封装grid第二行 线上总和
                map1.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaNewAccountPaid"))).add(((BigDecimal) (list.get(i).get("totalTransaRedeemExtendAccountPaid")))).intValue() + "");
                // 封装grid第三行 全新支付
                map2.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaNewAccountPaid"))).intValue() + "");
                // 封装grid第四行 赎回再签
                map3.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaRedeemExtendAccountPaid"))).intValue() + "");

            }
            // 线下
            else
            {
                // 封装grid第五行 线上总和
                map4.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaNewAccountPaid"))).add(((BigDecimal) (list.get(i).get("totalTransaRedeemExtendAccountPaid")))).intValue() + "");
                // 封装grid第六行 全新支付
                map5.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaNewAccountPaid"))).intValue() + "");
                // 封装grid第七行 赎回再签
                map6.put("NO2", ((BigDecimal) (list.get(i).get("totalTransaRedeemExtendAccountPaid"))).intValue() + "");
            }
        }
        // 封装column 设置各属性
        columnInfo = new ColumnInfo();
        columnInfo.setName("NO2");
        columnInfo.setDisplay(totalTransaNewAccountPaid + "");
        columnInfo.setWidth(835);
        columnList.add(columnInfo);

        gridList.add(map1);
        gridList.add(map2);
        gridList.add(map3);
        gridList.add(map4);
        gridList.add(map5);
        gridList.add(map6);

        // map存放column 和 rows
        map.put("columns", columnList);
        map.put("Rows", gridList);

        return map;
    }

    /**
     * @Title: handleOfflineImportCredit
     * @Description: 处理一条线下债权匹配数据
     * @param offlineCredit 线下债权匹配临时数据对象
     * @param wmsInveMulticreInfoId 债权匹配主键
     * @param user 登录用户信息
     * @return 是否使用库中抵押包。使用苦衷抵押包返回true
     * @author: jinzhm
     * @time:2017年4月11日 上午9:44:39
     * history:
     * 1、2017年4月11日 jinzhm 创建方法
     */
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public int handleOfflineImportCredit(WmsInveOfflineCreditImportTemp offlineCredit, Integer wmsInveMulticreInfoId,
                                          UserBean user)
    {
        // 是否使用库中抵押包标记，1表示使用库中抵押包
        int flag = 1;
        // 根据抵押包合同编号获得抵押包信息
        WmsInveCreditPackage creditPackage = wmsInveCreditCRPackageDao.getCreditPackageByProtocolIdYearNum(offlineCredit.getProtocol_id_year_num());

        // 如果系统内没有该抵押包信息，先创建一个保存
        if (creditPackage == null)
        {
            // 抵押包信息验证完毕，没有问题保存抵押包信息
            creditPackage = new WmsInveCreditPackage();
            // 设置抵押包批次号
            creditPackage.setWms_inve_multicre_info_id(wmsInveMulticreInfoId);
            // 抵押包类型设置为可拆分类型
            creditPackage.setCre_type(CreditUtils.CATEGORY_TYPE_SPLIT);
            // 设置抵押包姓名
            creditPackage.setCre_per_name(offlineCredit.getCre_per_name());
            // 设置抵押包合同编号
            creditPackage.setProtocol_id_year_num(offlineCredit.getProtocol_id_year_num());
            // 设置身份证号
            creditPackage.setCre_per_card_id(offlineCredit.getCre_per_card_id());
            // 设置抵押金额
            creditPackage.setCre_pledge_mon(offlineCredit.getCre_pledge_mon().multiply(new BigDecimal("10000")));
            // 设置平米数
            creditPackage.setHouse_size(offlineCredit.getHouse_size());
            // 设置开始还款日期
            creditPackage.setCrepg_start_date(offlineCredit.getCrepg_start_date());
            // 设置还款终止日期
            creditPackage.setCrepg_end_date(offlineCredit.getCrepg_end_date());
            // 设置他项人id
            creditPackage.setRele_per_id(offlineCredit.getRele_per_id());
            // 设置他项人名称
            creditPackage.setRele_per_name(offlineCredit.getRele_per_name());
            // 设置抵押包所在地区编号
            creditPackage.setLoca_num(offlineCredit.getLocal_num());
            // 设置抵押包匹配状态，该字段不使用，直接设置成0
            creditPackage.setCrepackage_state("0");
            // 设置创建人
            creditPackage.setCreate_user_id(user.getUserId());
            // 设置创建时间
            creditPackage.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
            // 设置有效状态
            creditPackage.setEnable_flag("1");
            // 创建时设置抵押包已匹配金额为0
            creditPackage.setMatched_product_account(BigDecimal.ZERO);
            // 保存抵押包信息
            wmsInveCreditCRPackageDao.save(creditPackage);
            // 是否使用库中抵押包设置成false，表示导入抵押包信息
            flag = 0;
        }
        // 如果查到抵押包
        else
        {
            // 抵押包是失效状态
            if("0".equals(creditPackage.getEnable_flag()))
            {
                // 如果不是作废抵押包
                if(StringUtils.isEmpty(creditPackage.getDestroy_reason()))
                {
                    // 保存错误信息
                    WmsInveOfflineCreditImportTemp updOfflineCredit = new WmsInveOfflineCreditImportTemp();
                    updOfflineCredit.setId(offlineCredit.getId());
                    updOfflineCredit.setRemark("该抵押包在库中为草稿状态");
                    wmsInveCreditCRPackageDao.updateOfflineCredit(updOfflineCredit);
                    return -1;
                }
                else
                {
                    // 保存错误信息
                    WmsInveOfflineCreditImportTemp updOfflineCredit = new WmsInveOfflineCreditImportTemp();
                    updOfflineCredit.setId(offlineCredit.getId());
                    updOfflineCredit.setRemark("该抵押包在库中为已失效状态");
                    wmsInveCreditCRPackageDao.updateOfflineCredit(updOfflineCredit);
                    return -2;
                }
            }
        }

        // 根据理财单据编号查找单据信息
        WmsInveTransa transa = new WmsInveTransa();
        transa.setBill_code(offlineCredit.getBill_code());
        transa = wmsInveTransaDao.getListByEntity(transa).get(0);

        /**
         * 1. 检查该单据时候有柜员合同数据；如有获取主键；没有生成柜员合同数据
         * 2. 单据债权关联表中插入关联数据
         * 3. 保存单据债权关联临时表
         * 4. 修改抵押包当前已匹配金额
         * 5. 如果excel中有合同编号更新上单表理财合同编号
         */
        WmsInveClerkProtocol clerkProtocol = getTransaClerkProtocolData(transa, offlineCredit, user);
        WmsInveTransaCrepkg transaCrepkg = persistWmsInveTransaCrepkg(transa, clerkProtocol, creditPackage,
                                                                      offlineCredit, user);
        // 保存单据债权关联临时表
        persistTransaCreditMatchTempData(transaCrepkg);
        // 更新抵押包的当前已匹配金额
        WmsInveCreditPackage updCreditPackage = new WmsInveCreditPackage();
        updCreditPackage.setWms_inve_credit_package_id(creditPackage.getWms_inve_credit_package_id());
        updCreditPackage.setMatched_product_account(creditPackage.getMatched_product_account()
                                                                 .add(transaCrepkg.getAcl_mon()));
        updCreditPackage.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        updCreditPackage.setLast_update_user_id(user.getUserId());
        wmsInveCreditPackageDao.update(updCreditPackage);
        // 更新线下债权匹配数据已处理标记
        WmsInveOfflineCreditImportTemp updOfflineCredit = new WmsInveOfflineCreditImportTemp();
        updOfflineCredit.setId(offlineCredit.getId());
        updOfflineCredit.setEnable_flag("0");
        wmsInveCreditCRPackageDao.updateOfflineCredit(updOfflineCredit);
        return flag;
    }

    /**
     * @Title: persistTransaCreditMatchTempData
     * @Description: 将单据债权关联信息保存到库中
     * @param transaCrepkg 单据债权关联对象
     * @author: jinzhm
     * @time:2017年4月8日 下午2:30:24
     * history:
     * 1、2017年4月8日 jinzhm 创建方法
     */
    private void persistTransaCreditMatchTempData(WmsInveTransaCrepkg transaCrepkg)
    {
        WmsInveClerkProtocolTransaCrepkgTemp temp = new WmsInveClerkProtocolTransaCrepkgTemp();
        // 设置上单主键，柜员合同主键，单据债权关联主键
        temp.setWms_inve_transa_id(transaCrepkg.getWms_inve_transa_id());
        temp.setWms_inve_clerk_protocol_id(transaCrepkg.getWms_inve_clerk_protocol_id());
        temp.setWms_inve_transa_crepkg_id(transaCrepkg.getWms_inve_transa_crepkg_id());
        // 设置创建用户，时间，有效标志
        temp.setCreate_user_id(transaCrepkg.getCreate_user_id());
        temp.setCreate_timestamp(transaCrepkg.getCreate_timestamp());
        temp.setEnable_flag("1");
        wmsInveTransaCrepkgDao.saveProtocolTransaCrepkgTempData(temp);
    }

    /**
     * @Title: persistWmsInveTransaCrepkg
     * @Description: 将单据债权关联信息保存到数据库中
     * @param transa 上单信息
     * @param clerkProtocol 柜员合同信息
     * @param creditPackage 抵押包信息
     * @param dataMap excel中一行数据集合
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年4月8日 上午10:43:52
     * history:
     * 1、2017年4月8日 jinzhm 创建方法
     */
    private WmsInveTransaCrepkg persistWmsInveTransaCrepkg(WmsInveTransa transa, WmsInveClerkProtocol clerkProtocol,
                                                           WmsInveCreditPackage creditPackage,
                                                           WmsInveOfflineCreditImportTemp offlineCredit, UserBean user)
    {
        // 获得上单产品信息
        WmsInveTransaProd prod = getWmsInveTransaProdByTransaId(transa.getWms_inve_transa_id());
        // 创建单据债权关联信息
        WmsInveTransaCrepkg transaCrepkg = new WmsInveTransaCrepkg();
        // 设置上单主键及柜员合同主键
        transaCrepkg.setWms_inve_transa_id(transa.getWms_inve_transa_id());
        transaCrepkg.setWms_inve_clerk_protocol_id(clerkProtocol.getWms_inve_clerk_protocol_id());
        // 匹配类型设置为可拆分类型
        transaCrepkg.setCre_type(CreditUtils.CATEGORY_TYPE_SPLIT);
        // 抵押包主键
        transaCrepkg.setWms_inve_credit_package_id(creditPackage.getWms_inve_credit_package_id());
        // 产品主键
        transaCrepkg.setWms_inve_pruduct_category_id(prod.getWms_inve_pruduct_category_id());
        // 设置匹配金额及匹配时间
        transaCrepkg.setAcl_mon(offlineCredit.getMatch_product_account().multiply(new BigDecimal("10000")));
        transaCrepkg.setAcl_date(offlineCredit.getDate_of_payment());
        // 设置匹配状态为生效中
        transaCrepkg.setAcl_state(CreditMatchInterface.ACL_STATE_ENABLE);
        // 设置创建用户及创建时间和有效状态
        transaCrepkg.setCreate_user_id(user.getUserId());
        transaCrepkg.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        transaCrepkg.setEnable_flag("1");
        wmsInveTransaCrepkgDao.save(transaCrepkg);
        return transaCrepkg;
    }

    /**
     * @Title: getWmsInveTransaProdByTransaId
     * @Description: 根据上单主键获得上单产品信息
     * @param transaId 上单主键
     * @return 上单产品信息
     * @author: jinzhm
     * @time:2017年4月7日 下午5:58:10
     * history:
     * 1、2017年4月7日 jinzhm 创建方法
     */
    private WmsInveTransaProd getWmsInveTransaProdByTransaId(int transaId)
    {
        WmsInveTransaProd prod = new WmsInveTransaProd();
        prod.setWms_inve_transa_id(transaId);
        prod.setEnable_flag("1");
        prod = wmsInveTransaProdDao.getListByEntity(prod).get(0);
        return prod;
    }

    /**
     * @Title: getTransaClerkProtocolData
     * @Description: 获得柜员合同数据
     *      根据上单主键查找柜员合同表，如果有记录，直接返回；没有记录生成一个并返回
     * @param transa 上单表数据
     * @param offlineCredit 一条要导入的线下债权匹配数据对象
     * @param user 登录用户
     * @return 柜员合同数据
     * @author: jinzhm
     * @time:2017年4月7日 下午5:53:07
     * history:
     * 1、2017年4月7日 jinzhm 创建方法
     */
    private WmsInveClerkProtocol getTransaClerkProtocolData(WmsInveTransa transa,
                                                            WmsInveOfflineCreditImportTemp offlineCredit, UserBean user)
    {
        // 获得柜员合同信息
        WmsInveClerkProtocol clerkProtocol = wmsInveClerkProtocolDao.getNewestWmsInveClerkProtocolByTransaId(transa.getWms_inve_transa_id());
        // 如果根据上单主键能找到柜员合同数据，直接返回
        if (clerkProtocol != null)
        {
            return clerkProtocol;
        }

        // 根据上单主键获得上单产品表信息
        WmsInveTransaProd prod = getWmsInveTransaProdByTransaId(transa.getWms_inve_transa_id());

        // 获得银行相关信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", 72);
        paramMap.put("value_code", prod.getBank_of_deposit_pro());
        // 获取省
        WmsSysDictData dictDataPro = wmsSysDictDataDao.getDictDataCode(paramMap).get(0);
        paramMap = new HashMap<String, Object>();
        paramMap.put("wms_sys_dict_id", 73);
        paramMap.put("value_code", prod.getBank_of_deposit_city());
        // 获取市
        WmsSysDictData dictDataCity = wmsSysDictDataDao.getDictDataCode(paramMap).get(0);

        // 获得归属业务员人员信息
        PmPersonnel personnel = pmPersonnelDao.get(transa.getBel_salesman_id_id());
        // 根据人员地区获得柜员地区信息
        WmsInveClerkRegion clerkRegion = wmsInveClerkRegionDao.getWmsInveClerkRegionByRegion(personnel.getPersonnel_regionnumber());

        // 根据上单主键找不到柜员合同数据，需要新生成一个柜员合同数据
        clerkProtocol = new WmsInveClerkProtocol();
        // 设置上单主键
        clerkProtocol.setWms_inve_transa_id(transa.getWms_inve_transa_id());
        // 设置理财合同编号
        clerkProtocol.setProt_code(String.valueOf(offlineCredit.getFinancial_bill_code()));
        // 合同类型设置成线上合同
        clerkProtocol.setProtocol_type("2");
        // 设置协议签署地
        clerkProtocol.setSign_place(clerkRegion.getRegion_protocol_area());
        // 设置协议签署编码
        clerkProtocol.setSign_place_postcode(clerkRegion.getRegion_postcode());
        // 设置甲方姓名
        clerkProtocol.setA_name(transa.getCus_name());
        // 设置甲方身份证号
        clerkProtocol.setA_id_card(transa.getId_card());
        // 设置甲方地址
        clerkProtocol.setA_contact_address(transa.getContact_address());
        // 设置甲方银行账号
        clerkProtocol.setA_bank_number(prod.getCard_no());
        // 设置甲方银行卡号
        clerkProtocol.setA_bank_info(prod.getBank_of_deposit() + " " + dictDataPro.getValue_meaning() + ""
                                     + dictDataCity.getValue_meaning() + " " + prod.getBank_branch());
        // 设置出借金额
        clerkProtocol.setProduct_account(prod.getProduct_account());
        // 设置出借金额（大）
        clerkProtocol.setProduct_account_upper(digitUpperUtil.digitUppercase(((BigDecimal) prod.getProduct_account()).doubleValue(),
                                                                             true).replaceAll("万元", ""));
        // 设置产品名称
        clerkProtocol.setCategory_name(prod.getCategory_name());
        // 设置产品期限
        clerkProtocol.setProduct_deadline(prod.getProduct_deadline() + "");
        // 设置具体资料
        clerkProtocol.setA_data("身份证，银行卡，金额");
        // 设置乙方地址
        clerkProtocol.setB_address(clerkRegion.getRegion_protocol_b_area());
        // 设置乙方名称
        clerkProtocol.setB_name(transa.getCreate_user_name());

        Date dateOfPayment = offlineCredit.getDate_of_payment();
        // 设置合同生效时间
        clerkProtocol.setBegin_of_date(new Timestamp(dateOfPayment.getTime()));
        // 设置合同到期日期
        clerkProtocol.setEnd_of_date(new Timestamp(
                                                   com.zx.sframe.util.DateUtil.getDateAddDays(com.zx.sframe.util.DateUtil.getDatePlusMonths(clerkProtocol.getBegin_of_date(),
                                                                                                                                            Integer.parseInt(clerkProtocol.getProduct_deadline())),
                                                                                              -1).getTime()));
        // 设置预计年华收益
        clerkProtocol.setExpect_interest(prod.getProduct_interest());
        // 设置创建用户，创建用户姓名，创建时间，有效状态
        clerkProtocol.setCreate_user_id(user.getUserId());
        clerkProtocol.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        clerkProtocol.setCreate_user_name(user.getRealname());
        clerkProtocol.setEnable_flag("1");

        wmsInveClerkProtocolDao.save(clerkProtocol);

        return clerkProtocol;
    }

}
