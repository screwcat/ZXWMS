package com.zx.emanage.inve.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveClerkProtocolService;
import com.zx.emanage.inve.service.IWmsInveTransaCrepkgService;
import com.zx.emanage.inve.service.IWmsInveTransaIncomeService;
import com.zx.emanage.inve.vo.WmsInveClerkProtocolSearchBeanVO;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.FileUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.JasperUtil;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveClerkProtocolController
{
    private static Logger log = LoggerFactory.getLogger(WmsInveClerkProtocolController.class);

    @Autowired
    private IWmsInveClerkProtocolService wmsinveclerkprotocolService;

    @Autowired
    private IWmsInveTransaCrepkgService wmsInveTransaCrepkgService;

    @Autowired
    private IWmsInveTransaIncomeService wmsInveTransaIncomeService;
    /**
     * @Title: getWmsInveClerkProtocolList
     * @Description: 客户合同查询
     * @param queryInfo
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午2:55:01
     * history:
     * 1、2017年2月14日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClerkProtocolList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveClerkProtocolList(WmsInveClerkProtocolSearchBeanVO queryInfo,
                                                           HttpServletRequest request)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        return wmsinveclerkprotocolService.getWmsInveClerkProtocolList(queryInfo, user);
    }

    /**
     * @Title: getWmsInveClerkProtocolList
     * @Description: 客户合同查询 不分页
     * @param queryInfo
     * @param request
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月14日 下午2:55:01
     * history:
     * 1、2017年2月14日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClerkProtocolWithoutpagingList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveClerkProtocolWithoutpagingList(WmsInveClerkProtocolSearchBeanVO queryInfo, HttpServletRequest request)
    {
        return wmsinveclerkprotocolService.getWmsInveClerkProtocolWithoutpagingList(queryInfo);
    }
    /** 
     * @Title: previewOrprint
     * @Description: 打印合同入口
     * @param request
     * @param response
     * @param wmsInveClerkProtocolSearchBeanVO 
     * @author: zhangyunfei
     * @time:2017年2月11日 下午2:56:10
     * history:
     * 1、2017年2月11日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/printProtocolpdf.pdf", method = { RequestMethod.GET, RequestMethod.POST })
    public void printProtocolpdf(HttpServletRequest request, HttpServletResponse response,
                                 WmsInveClerkProtocolSearchBeanVO wmsInveClerkProtocolSearchBeanVO, String procotolIds)
    {
        if (procotolIds == null)
        {
            return;
        }

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);

        List<String> listUrl = new ArrayList<String>();

        Map<String, Object> jasperMap = new HashMap<String, Object>();
        jasperMap.put("1", "per-lendAdiviceAndServiceProtocol");// 个人出借咨询与服务协议
        jasperMap.put("2", "entrust-withHoldAuthority.jasper");// 委托代扣款授权书
        jasperMap.put("3", "creditPackageSignDeclare.jasper");// 债权包签收声明
        jasperMap.put("4", "sub-lendAdviceAndServiceProtocol");// 个人出借咨询与服务补充协议
        jasperMap.put("5", "inter-creditTransferProtocol");// 内部转让债权协议
        jasperMap.put("6", "m-m_creditTransferProtocol");// 多对多债权转让及受让协议

        String[] procotolArr = procotolIds.split(",");
        for (int i = 0; i < procotolArr.length; i++)
        {
            listUrl.add((String) jasperMap.get(procotolArr[i]));
        }

        String baseDir = WmsInveClerkProtocolController.class.getResource("/").getPath();
        String header1Dir = baseDir + "jasper/header1.png";
        String imagesDir = baseDir + "jasper/合同首页.png";
        String logoDir = baseDir + "jasper/logo_zx.jpg";
        
        // pMap 存放人出借咨询与服务协议、委托代扣款授权书、债权包签收声明、个人出借咨询与服务补充协议所需参数
        // wmsInveClerkProtocolSearchBeanVO.setWms_inve_transa_id(5);
        Map<String, Object> pMap = wmsinveclerkprotocolService.getWmsInveClerkProtocolById(wmsInveClerkProtocolSearchBeanVO);

        // 主合同版本号 不为""时 需要拼接下划线 格式(文件名_版本号)
        String main_protocol_version = ("".equals(pMap.get("main_protocol_version").toString())) ? "" : "_" + pMap.get("main_protocol_version").toString();
        // 补充协议版本号
        String sub_protocol_version = ("".equals(pMap.get("sub_protocol_version").toString())) ? "" : "_" + pMap.get("sub_protocol_version").toString();
        // 内部转让协议版本号
        String inter_protocol_version = ("".equals(pMap.get("inter_protocol_version").toString())) ? "" : "_" + pMap.get("inter_protocol_version").toString();
        // 内部转让协议合并版本号 1
        String inter_protocol_merge_version = ("".equals(pMap.get("inter_protocol_merge_version").toString())) ? "" : "_" + pMap.get("inter_protocol_merge_version").toString();
        // 多对多协议版本号
        String m2m_protocol_version = ("".equals(pMap.get("m2m_protocol_version").toString())) ? "" : "_" + pMap.get("m2m_protocol_version").toString();

        //拼接页脚字符串
        String footInfo = "Add：" + pMap.get("r_b_area") + "  TEL：400 000 2555  Web：www.zxptp.com";
        // 审核人
        pMap.put("printman_name", user.getRealname());
        pMap.put("printman_shortCode", user.getUserCode());
        // zMap 存放内部转让债权协议所需参数
        Map<String, Object> zMap = new HashMap<String, Object>();
        // cMap 存放债权转让受让协议所需参数
        Map<String, Object> cMap = new HashMap<String, Object>();

        // 根据上单主键和协议主键查找所有关联的债权信息
        List<Map<String, Object>> transaCrepkgList = wmsInveTransaCrepkgService.getAllTransaCrepkgByGroup(wmsInveClerkProtocolSearchBeanVO);
        Map<String, List<Map<String, Object>>> crepkgMap = wmsInveTransaCrepkgService.groupTransaCrepkgList(transaCrepkgList);
        // 客户收益计划map
        int wms_inve_pruduct_category_id = Integer.parseInt(pMap.get("wms_inve_pruduct_category_id").toString());
        int product_account = Integer.parseInt(pMap.get("bas_product_account").toString());
        Date begin_of_date = DateUtil.strToSqlDate(pMap.get("begin_of_date").toString(), null);
        Map<String, Object> cIMap = wmsInveTransaIncomeService.computeCategoryIncome(wms_inve_pruduct_category_id, product_account, begin_of_date);
        // 赎回所生成的合同 筛选cIMap中日期大于赎回日期的集合
        if (pMap.get("wms_inve_redeem_id") != null)
        {
            Date redeem_date = DateUtil.strToSqlDate(pMap.get("begin_of_date").toString(), null);
            List<Map<String, Object>> tList = wmsInveTransaIncomeService.getIncomeListGtRedeemDate((List<Map<String, Object>>) cIMap.get("incomeList"), redeem_date);
            cIMap.put("incomeList", tList);
        }
        wmsInveTransaIncomeService.calTotal_Income(cIMap);
        pMap.put("imagesDir", imagesDir);
        pMap.put("header1Dir", header1Dir);
        pMap.put("logoDir", logoDir);
        pMap.put("right_dir", baseDir + "jasper/right.png");
        pMap.put("error_dir", baseDir + "jasper/error.png");
        // 原合同模板路径
        String fileurl = "";
        // 他项人不为赵燕国或卓信小额贷时 合并其他他项人的内部转让协议模板
        String fileMergeurl = "";
        List<Map<String, Object>> tcList = new ArrayList<Map<String, Object>>();
        // sdMap 存放原债权人签字
        Map<String, Object> sdMap = new HashMap<String, Object>();
        Map<String, Object> relMap = new HashMap<String, Object>();
        List<Map<String, Object>> sdList = new ArrayList<Map<String, Object>>();
        BigDecimal sum_cre_pledge_mon = BigDecimal.ZERO;

        List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        JasperPrint jasperPrint = null;

        try
        {
            // 客户签字路径
            String customer_signature_path = pMap.get("customer_signature_path").toString();
            // new一个URL对象
            URL url = new URL(GlobalVal.SERVER_UPLOAD_URL + "/upload" + customer_signature_path);
            // 存在客户签字并且非赎回改签/续签所生成的合同才能有客户签字（改签续签都没有客户签字）
            if (!"".equals(customer_signature_path))
            {
                // 打开链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 设置请求方式为"GET"
                conn.setRequestMethod("GET");
                // 超时响应时间为5秒
                conn.setConnectTimeout(5 * 1000);
                // 通过输入流获取图片数据
                // 客户签字(主合同)
                byte[] byt = FileUtil.toByteArray(conn.getInputStream());
                pMap.put("client_sign_main", new ByteArrayInputStream(byt));
                // 客户签字(补充协议)
                pMap.put("client_sign_sub", new ByteArrayInputStream(byt));
                // 客户签字(外部转让)
                cMap.put("client_sign_m2m", new ByteArrayInputStream(byt));
            }

            for (int i = 0; i < listUrl.size(); i++)
            {
                // 当所打印的合同为 主合同 补充协议 内部转让协议 多对多协议时需要拼接合同版本号
                if("per-lendAdiviceAndServiceProtocol".equals(listUrl.get(i))){

                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + main_protocol_version + ".jasper";
                }
                else if ("sub-lendAdviceAndServiceProtocol".equals(listUrl.get(i)))
                {
                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + sub_protocol_version + ".jasper";

                }else if("inter-creditTransferProtocol".equals(listUrl.get(i))){
                    // 原内部转让合同/卓信小额贷模板路径
                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + inter_protocol_version + ".jasper";
                    // 项人不为赵燕国或卓信小额贷时 合并其他他项人的内部转让协议模板路径
                    if (!"".equals(inter_protocol_merge_version))
                    {
                        fileMergeurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/inter-creditTransferProtocol-merge" + inter_protocol_merge_version + ".jasper";
                    }

                }else if("m-m_creditTransferProtocol".equals(listUrl.get(i))){

                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i) + m2m_protocol_version + ".jasper";

                }else{

                    fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + listUrl.get(i);
                }
                // 原合同模板的JasperReport
                File file = new File(fileurl);
                InputStream jr = new FileInputStream(file);
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jr);
                jasperReport.getFields();

                // 内部转让协议需要根据他项人id多次封装jasperPrint(打印多张合同)
                if ("inter-creditTransferProtocol".equals(listUrl.get(i)))
                {
                    // 转让协议编号
                    zMap.put("trans_code", pMap.get("trans_code"));
                    zMap.put("header1Dir", header1Dir);
                    zMap.put("b_address", pMap.get("b_address"));
                    zMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
                    zMap.put("intran_b_name", pMap.get("intran_name"));
                    zMap.put("intran_b_address", pMap.get("intran_address"));
                    zMap.put("intran_b_id", pMap.get("intran_id"));
                    zMap.put("prot_code", pMap.get("prot_code"));
                    zMap.put("logoDir", logoDir);
                    zMap.put("sign_date", pMap.get("sign_date"));
                    zMap.put("signDir_b", baseDir + "jasper/" + pMap.get("intran_name") + ".png");

                    // 经办人-----业务员
                    zMap.put("salesman_name", pMap.get("salesman_name"));
                    zMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));
                    // 经办人-----业务员
                    zMap.put("printman_name", user.getRealname());
                    zMap.put("printman_shortCode", user.getUserCode());
                    // 备注
                    if (pMap.get("prot_type") == null)
                    {
                        zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                    }
                    else
                    {
                        zMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        if ("1".equals(pMap.get("prot_type").toString()))
                        {
                            zMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                        }
                        else if ("2".equals(pMap.get("prot_type").toString()))
                        {
                            zMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
                        }
                    }
                    // 页脚说明
                    zMap.put("footInfo", footInfo);

                    for (Map.Entry<String, List<Map<String, Object>>> entry : crepkgMap.entrySet())
                    {
                        // 当他项人为赵燕国时 不签内转内合同
                        if (!"赵燕国".equals(entry.getKey()))
                        {
                            // cList为债权集合
                            String intran_a_name = (String) entry.getValue().get(0).get("intran_a_name");

                            // inter_protocol_merge_version为空说明是老单据 没有合并合同版本号
                            // 老单据和卓信小额贷是按原始版本打印多个协议
                            if ("".equals(inter_protocol_merge_version) || "卓信小额贷".equals(entry.getKey()))
                            {
                                zMap.put("tList", entry.getValue());
                                zMap.put("intran_a_name", entry.getValue().get(0).get("intran_a_name"));
                                zMap.put("intran_a_id", entry.getValue().get(0).get("intran_a_id"));
                                zMap.put("signDir", baseDir + "jasper/" + intran_a_name + ".png");
                                // 他项人为组织 甲方取组织名称
                                zMap.put("a_company_name", entry.getValue().get(0).get("a_company_name"));
                                // 组织标示 0非组织 1组织
                                zMap.put("is_company", entry.getValue().get(0).get("is_company"));

                                jasperPrint = JasperFillManager.fillReport(jasperReport, zMap, new JREmptyDataSource());
                                int page_size = jasperPrint.getPages().size();
                                jasperPrintList.add(jasperPrint);
                                // 添加空白页
                                if (page_size % 2 == 1)
                                {
                                    String blank_fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "blank.jasper";
                                    File blank_file = new File(blank_fileurl);
                                    InputStream blank_jr = new FileInputStream(blank_file);
                                    JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                                    JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                                    jasperPrintList.add(blank_jasperPrint);
                                }
                            }
                            else
                            {
                                tcList.addAll(entry.getValue());
                                if (relMap.get("intran_a_name") == null)
                                {
                                    relMap.put(intran_a_name, baseDir + "jasper/" + intran_a_name + ".png");
                                }
                                // 计算总和
                                for (int k = 0; k < entry.getValue().size(); k++)
                                {
                                    sum_cre_pledge_mon = sum_cre_pledge_mon.add((BigDecimal) entry.getValue().get(k).get("acl_mon"));
                                }
                            }
                        }
                    }
                    // 当存在内部转让协议合并版本号 并且他项人不为赵燕国或卓信小额贷的list不为空
                    if (!"".equals(inter_protocol_merge_version) && tcList.size() > 0)
                    {
                        // 内部转让协议他项人不是赵燕国和卓信小额贷时所打印合并其他所有他项人的合同模板
                        file = new File(fileMergeurl);
                        InputStream jrm = new FileInputStream(file);
                        JasperReport jasperReportm = (JasperReport) JRLoader.loadObject(jrm);
                        jasperReportm.getFields();

                        zMap.put("intran_a_name", "详见转让债权明细列表");
                        zMap.put("intran_a_id", "详见转让债权明细列表");
                        zMap.put("is_merge_version", "1");
                        int j = 1;
                        for (Map.Entry<String, Object> entry : relMap.entrySet())
                        {
                            if (j % 2 == 0)
                            {
                                sdMap.put("signDir2", entry.getValue());
                                sdList.add(sdMap);
                                sdMap = new HashMap<String, Object>();
                            }
                            else
                            {
                                sdMap.put("signDir1", entry.getValue());
                                if (relMap.size() % 2 == 1 && j == relMap.size())
                                {
                                    sdList.add(sdMap);
                                }
                            }
                            j++;
                        }
                        // 计算合计 并格式化
                        DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
                        String sum_cre_pledge_mon_str = fmt.format(sum_cre_pledge_mon);
                        zMap.put("sum_cre_pledge_mon", sum_cre_pledge_mon_str);

                        zMap.put("tList", tcList);
                        zMap.put("sdList", sdList);
                        jasperPrint = JasperFillManager.fillReport(jasperReportm, zMap, new JREmptyDataSource());
                        int page_size = jasperPrint.getPages().size();
                        jasperPrintList.add(jasperPrint);
                        // 添加空白页
                        if (page_size % 2 == 1)
                        {
                            String blank_fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "blank.jasper";
                            File blank_file = new File(blank_fileurl);
                            InputStream blank_jr = new FileInputStream(blank_file);
                            JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                            JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                            jasperPrintList.add(blank_jasperPrint);
                        }
                    }
                }
                // 封装多对多债权转让受让协议所需参数
                else if ("m-m_creditTransferProtocol".equals(listUrl.get(i)))
                {
                    // 转让协议编号
                    cMap.put("sign_place_postcode", pMap.get("sign_place_postcode"));
                    cMap.put("a_contact_address", pMap.get("a_contact_address"));
                    cMap.put("a_id_card", pMap.get("a_id_card"));
                    cMap.put("a_name", pMap.get("a_name"));
                    cMap.put("intran_id", pMap.get("intran_id"));
                    cMap.put("intran_name", pMap.get("intran_name"));
                    cMap.put("prot_code", pMap.get("prot_code"));
                    cMap.put("logoDir", logoDir);

                    cMap.put("category_name", pMap.get("category_name"));
                    cMap.put("trans_code", pMap.get("trans_code"));
                    cMap.put("begin_of_date", pMap.get("begin_of_date"));
                    cMap.put("card_no", pMap.get("card_no"));
                    cMap.put("bank_info", pMap.get("bank_info"));
                    cMap.put("sum_cre_pledge_mon", pMap.get("sum_cre_pledge_mon"));
                    cMap.put("sum_cre_pledge_mon_upper", pMap.get("sum_cre_pledge_mon_upper"));

                    cMap.put("signDir_b", baseDir + "jasper/" + pMap.get("intran_name") + ".png");
                    // 经办人-----业务员
                    cMap.put("salesman_name", pMap.get("salesman_name"));
                    cMap.put("salesman_shortCode", pMap.get("salesman_shortCode"));

                    // 审核人-----当前登录人 打印人
                    cMap.put("printman_name", user.getRealname());
                    cMap.put("printman_shortCode", user.getUserCode());
                    // 备注
                    if (pMap.get("prot_type") == null)
                    {
                        cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                    }
                    else
                    {
                        cMap.put("backup1", "备注：1. 非本债权人持本债权无效。");
                        if ("1".equals(pMap.get("prot_type").toString()))
                        {
                            cMap.put("backup2", "2. 本协议有效期至" + pMap.get("end_of_date") + "，请在此日期后办理“债权转让手续”或签署新的《债权转让及受让协议》。");
                        }
                        else if ("2".equals(pMap.get("prot_type").toString()))
                        {
                            cMap.put("backup2", "2. 客户在本协议期间的收益，将在单据赎回时按当月央行活期存款基准利率统一结算。");
                        }
                    }
                    // 页脚说明   
                    cMap.put("footInfo", footInfo);
                    // 3.1/6.1/6.2项文字说明
                    cMap.put("3.1Info", "       3.1 转让价款：乙方向甲方支付人民币(大写)" + pMap.get("sum_cre_pledge_mon_upper") + " 万元整（人民币小写" + pMap.get("sum_cre_pledge_mon") + "元）作为上述债权转让的对价。");
                    cMap.put("6.1Info", "       6.1双方同意" + pMap.get("r_b_name") + "作为本协议签订的见证人。");
                    cMap.put("6.2Info", "       6.2乙方同意委托" + pMap.get("r_b_name") + "对其受让债权进行信用咨询和管理服务。乙方与" + pMap.get("r_b_name") + "另行签订《个人出借与咨询服务协议》。");
                    // 证明人
                    cMap.put("r_b_name", pMap.get("r_b_name"));
                    cMap.put("header1Dir", header1Dir);
                    cMap.put("tList", transaCrepkgList);

                    jasperPrint = JasperFillManager.fillReport(jasperReport, cMap, new JREmptyDataSource());

                    int page_size = jasperPrint.getPages().size();
                    jasperPrintList.add(jasperPrint);

                    // 添加空白页
                    if (page_size % 2 == 1)
                    {
                        String blank_fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "blank.jasper";
                        File blank_file = new File(blank_fileurl);
                        InputStream blank_jr = new FileInputStream(blank_file);
                        JasperReport blank_jasperReport = (JasperReport) JRLoader.loadObject(blank_jr);
                        JasperPrint blank_jasperPrint = JasperFillManager.fillReport(blank_jasperReport, zMap, new JREmptyDataSource());
                        jasperPrintList.add(blank_jasperPrint);
                    }
                }
                else if ("per-lendAdiviceAndServiceProtocol".equals(listUrl.get(i)))
                {
                    // 打印两份主合同
                    jasperPrint = JasperFillManager.fillReport(jasperReport, pMap, new JREmptyDataSource());
                    // 第一份主合同
                    jasperPrintList.add(jasperPrint);
                    // 第二份主合同(非ZSH做的操作打印两份主合同)
                    jasperPrintList.add(jasperPrint);
                    // 客户收益计划书(在第二份主合同后面打印)
                    cIMap.put("prot_code", pMap.get("prot_code"));
                    cIMap.put("logoDir", logoDir);
                    cIMap.put("category_name", pMap.get("category_name"));
                    cIMap.put("product_deadline_info", pMap.get("product_deadline") + "个月");
                    cIMap.put("expect_interest", pMap.get("expect_interest"));
                    cIMap.put("product_account", pMap.get("bas_product_account") + "万元");
                    cIMap.put("category_interest_pay_method_name", pMap.get("category_interest_pay_method_name"));
                    cIMap.put("begin_of_date", pMap.get("begin_of_date"));

                    if (Integer.parseInt(pMap.get("is_reward_product").toString()) > 0)
                    {
                        fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "customerIncomePlan1.jasper";
                    }
                    else
                    {
                        fileurl = JasperUtil.class.getClassLoader().getResource("/").getPath() + "/jasper/" + "customerIncomePlan2.jasper";
                    }
                    file = new File(fileurl);
                    jr = new FileInputStream(file);
                    jasperReport = (JasperReport) JRLoader.loadObject(jr);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, cIMap, new JREmptyDataSource());
                    jasperPrintList.add(jasperPrint);
                }
                else if ("sub-lendAdviceAndServiceProtocol".equals(listUrl.get(i)))
                {
                    // 打印两份补充协议
                    jasperPrint = JasperFillManager.fillReport(jasperReport, pMap, new JREmptyDataSource());
                    // 第一份补充协议
                    jasperPrintList.add(jasperPrint);
                    // 第二份补充协议
                    jasperPrintList.add(jasperPrint);
                }
                // 其他合同
                else
                {
                    // 债权包签收声明合同(拼接标题和第一项文字说明)
                    pMap.put("creditPackageTitle", "致" + pMap.get("r_b_name") + "：");
                    pMap.put("creditPackageInfo1", "没按价赔偿的，" + pMap.get("r_b_name") + "有权从代投资人收取的投资收益中扣除。");

                    jasperPrint = JasperFillManager.fillReport(jasperReport, pMap, new JREmptyDataSource());
                    jasperPrintList.add(jasperPrint);
                }
            }
            JasperUtil.doprint(jasperPrintList, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @Title: changeProtocolCredit
     * @Description: 更换债权包信息
     * @param request 请求信息
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param categoryId 产品主键
     * @return 债权更换结果
     *  1表示匹配成功
     *  2表示产品不是可拆分类型
     *  3表示没有要到期的债权包
     *  -1表示债权匹配失败
     * @author: jinzhm
     * @time:2017年2月15日 上午11:22:14
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/changeProtocolCredit.do", method = { RequestMethod.POST })
    @ResponseBody
    public int changeProtocolCredit(HttpServletRequest request, int transaId, int protocolId, int categoryId)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            return wmsinveclerkprotocolService.changeProtocolCredit(transaId, protocolId, categoryId, user);
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    /**
     * @Title: autoChangeCreditForTransaWithoutPaperProtocol
     * @Description: 给不提供纸质合同的单据自动更换到期债权信息
     * @author: jinzhm
     * @time:2017年7月18日 下午1:47:02
     * history:
     * 1、2017年7月18日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/autoChangeCreditForTransaWithoutPaperProtocol.dos", method = { RequestMethod.POST })
    @ResponseBody
    public void autoChangeCreditForTransaWithoutPaperProtocol()
    {
        wmsinveclerkprotocolService.autoChangeCreditForTransaWithoutPaperProtocol();
    }

    /**
     * 
     * @Title: doSave
     * @Description: 上单时调用合同打印
     * @param request
     * @param begin_of_date
     * @return 
     * @author: Guanxu
     * @time:2017年2月14日 上午10:43:16
     * history:
     * 1、2017年2月14日 Guanxu 创建方法
     */
    @RequestMapping(value = "/inve/wmsInveClerkProtocolsave.do", method = { RequestMethod.POST })
    @ResponseBody
    public String doSave(HttpServletRequest request, String begin_of_date, Integer wms_inve_transa_id,
                         String protocol_type, String taskId, String prot_code)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveclerkprotocolService.save(begin_of_date, wms_inve_transa_id, user, protocol_type, taskId,
                                                      prot_code);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    
    /**
     * @Title: wmsInveRedeemMatchForIsOrderRedeem
     * @Description: 查询到达预约赎回日的柜员协议集合 进行债权匹配
     * @return 成功标志
     * @author: zhangyunfei
     * @time:2017年2月17日 下午2:01:30
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/wmsInveRedeemMatchForIsOrderRedeem.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    protected Map<String, Object> wmsInveRedeemMatchForIsOrderRedeem(String date)
    {
        String resultStr = wmsinveclerkprotocolService.matchForOrderRedeemClerkProtocol(date);

        Map<String, Object> map = new HashMap<>();
        map.put("resultStr", resultStr);
        return map;
    }

    /**
     * @Title: saveRedeemProtocol
     * @Description: 合同改签签订
     * @param request 请求信息
     * @param transaId 上单主键
     * @return 成功标志
     * @author: jinzhm
     * @time:2017年2月17日 下午2:01:30
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/saveRedeemProtocol.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveRedeemProtocol(HttpServletRequest request, Integer transaId, BigDecimal productAccount)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            return wmsinveclerkprotocolService.saveRedeemProtocol(transaId, productAccount, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * @Title: saveExtendProtocol
     * @Description: 保存合同续签数据
     * @param request 请求信息
     * @param transaId 上单主键
     * @return 成功标志
     * @author: jinzhm
     * @time:2017年2月17日 下午3:56:24
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/saveExtendProtocol.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveExtendProtocol(HttpServletRequest request, Integer transaId)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            return wmsinveclerkprotocolService.saveExtendProtocol(transaId, user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * @Title: saveExtendOrRedeemOfflineProtocol
     * @Description: 合同续签和合同改签线下合同补录
     * @param request 请求信息
     * @param transaId 上单主键
     * @param protocolCode 合同编号
     * @param type 业务类型 2：续期单据；3：赎回单据
     * @return 操作成功标志
     * @author: jinzhm
     * @time:2017年2月17日 上午8:37:07
     * history:
     * 1、2017年2月17日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/saveExtendOrRedeemOfflineProtocol.do", method = { RequestMethod.POST })
    @ResponseBody
    public String saveExtendOrRedeemOfflineProtocol(HttpServletRequest request, Integer transaId,
                                                    BigDecimal productAccount, String protocolCode,
                                                    String type)
    {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try
        {
            result = wmsinveclerkprotocolService.saveExtendOrRedeemOfflineProtocol(transaId, productAccount, protocolCode, type,
                                                                          user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * 
     * @Title: getWmsInveClaimsInfos
     * @Description: 根据上单表主键和柜员协议表主键获取债权匹配信息
     * @param wms_inve_transa_id 上单表主键
     * @param wms_inve_clerk_protocol_id 柜员协议表主键
     * @return 
     * @author: DongHao
     * @time:2017年2月17日 下午6:32:56
     * history:
     * 1、2017年2月17日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClaimsInfos.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getWmsInveClaimsInfos(String wms_inve_transa_id, String wms_inve_clerk_protocol_id)
    {
        return wmsinveclerkprotocolService.getWmsInveClaimsInfos(wms_inve_transa_id, wms_inve_clerk_protocol_id);
    }

    /**
     * 
     * @Title: getWmsInveClaimsInfosWithoutZHAO
     * @Description: 如果该匹配历史他项人全部为赵燕国 返回false 否则返回true
     * @param wms_inve_transa_id
     * @param wms_inve_clerk_protocol_id
     * @param wms_inve_clerk_protocol_transa_crepkg_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月19日 下午3:28:23
     * history:
     * 1、2017年5月19日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClaimsInfosWithoutZHAO.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, String> getWmsInveClaimsInfosWithoutZHAO(String wms_inve_transa_id, String wms_inve_clerk_protocol_id, String wms_inve_clerk_protocol_transa_crepkg_id)
    {
        return wmsinveclerkprotocolService.getWmsInveClaimsInfosWithoutZHAO(wms_inve_transa_id, wms_inve_clerk_protocol_id, wms_inve_clerk_protocol_transa_crepkg_id);
    }

    /**
     * @Title: getWmsInveClerkProtocolId
     * @Description: 获得柜员协议表id
     * @param transaId 上单主键
     * @param type 数据类型： 2续期单据，3赎回单据
     * @return 获得协议主键
     * @author: jinzhm
     * @time:2017年2月20日 上午8:21:45
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClerkProtocolId.do", method = { RequestMethod.GET })
    @ResponseBody
    public Integer getWmsInveClerkProtocolId(Integer transaId, BigDecimal productAccount, int type)
    {
        return wmsinveclerkprotocolService.getWmsInveClerkProtocolId(transaId, productAccount, type);
    }

    /**
     * 
     * @Title: getClerkProtocolByWmsInveTransaId
     * @Description: 根据上单表主键获取柜员协议信息
     * @param wms_inve_transa_id 上单表主键
     * @return 返回柜员协议信息
     * @author: DongHao
     * @time:2017年3月22日 上午10:03:48
     * history:
     * 1、2017年3月22日 DongHao 创建方法
     */
    @RequestMapping(value = "/inve/getClerkProtocolByWmsInveTransaId.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getClerkProtocolByWmsInveTransaId(Integer wms_inve_transa_id)
    {
        return wmsinveclerkprotocolService.getClerkProtocolByWmsInveTransaId(wms_inve_transa_id);
    }

    /**
     * 
     * @Title: savePublicOrCurrentProtocol
     * @Description: 定时生成柜员公益合同或活期合同
     * @param 
     * @return 
     * @author: zhangyunfei
     * @time:2017年5月16日 下午6:19:18
     * history:
     * 1、2017年5月16日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/savePublicOrCurrentProtocol.dos", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void savePublicOrCurrentProtocol()
    {
        wmsinveclerkprotocolService.savePublicOrCurrentProtocol();
    }

    /**
     * @Title: checkWmsInveClerkProtocolOper
     * @Description: 检查是否可以对柜员合同进行操作(23-1不允许操作无纸质合同 内部/外部 /更换债权)
     * @param wms_inve_pruduct_category_id
     * @return 
     * @author: zhangyunfei
     * @time:2017年7月21日 上午8:18:35
     * history:
     * 1、2017年7月21日 admin 创建方法
     */
    @RequestMapping(value = "/inve/checkWmsInveClerkProtocolOper.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String checkWmsInveClerkProtocolOper(Integer wms_inve_pruduct_category_id)
    {
        return wmsinveclerkprotocolService.checkWmsInveClerkProtocolOper(wms_inve_pruduct_category_id);
    }
}