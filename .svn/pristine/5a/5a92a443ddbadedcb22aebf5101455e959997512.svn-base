package com.zx.emanage.creditRightManager.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.invoke.empty.Empty;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.zx.emanage.creditRightManager.service.WmsInveAutoCreditPackageService;
import com.zx.emanage.creditRightManager.vo.WmsInveAutoCreditPackageVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.XlsUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveAutoCreditPackageController
 * 模块名称：自动采集
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年7月18日
 * @version V3.5
 * history:
 * 1、2017年7月18日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveAutoCreditPackageController
{
       @ Autowired
       private WmsInveAutoCreditPackageService wmsInveAutoCreditPackageServiceImpl;
       /**
        * 
        * @Title: selectAutoCreditPackageInfo
        * @Description: 查询抵押包相关信息
        * @return 
        * @author: zhangmingjian
        * @time:2017年7月18日 下午2:41:55
        * history:
        * 1、2017年7月18日 zhangmingjian 创建方法
        */
       @RequestMapping(value="/creditRightManager/selectAutoCreditPackageInfo.do")
       @ResponseBody
        public Map<String,Object> selectAutoCreditPackageInfo(WmsInveAutoCreditPackageVO bean){
            
            return wmsInveAutoCreditPackageServiceImpl.selectAutoCreditPackageInfo(bean);
        };   
        
        
        
        /**
         * 
         * @Title: selectAutoCreditPackageInfo
         * @Description: 抵押包类型设置
         * @return 
         * @author: zhangmingjian
         * @time:2017年7月18日 下午2:41:55
         * history:
         * 1、2017年7月18日 zhangmingjian 创建方法
         */
        @RequestMapping(value="/creditRightManager/updateAutoCreditPackageType.do")
        @ResponseBody
         public String updateAutoCreditPackageType(HttpServletRequest request, WmsInveAutoCreditPackageVO bean){
            Map<String,Object> map = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION); 
            //转换类型
            map.put("numFlag", bean.getNumFlag());
            //创建人id
            map.put("create_user_id", user.getUserId());
            //最后修改人id
            map.put("last_update_user_id", user.getUserId());
            
            
             List<WmsInveAutoCreditPackageVO> list = null;
             
                 try
                {
                     if(StringUtils.isNotBlank(bean.getRowsDate())){
                        list = mapper.readValue(bean.getRowsDate(), new TypeReference< List<Map<String,Object>>>(){
                                                    });
                     map.put("listData", list);
                     return  wmsInveAutoCreditPackageServiceImpl.updateAutoCreditPackageType(map);
                     }
                }
                catch (Exception e){
                    e.printStackTrace();
                    return "error";
                }
                 return "error";
         }
        
        /**
         * 
         * @Title: exportAutoCreditorPackage
         * @Description: 导出报表
         * @param request
         * @param response
         * @param bean 
         * @author: zhangmingjian
         * @time:2017年7月20日 下午2:52:22
         * history:
         * 1、2017年7月20日 zhangmingjian 创建方法
         */
        @RequestMapping(value="/creditRightManager/exportAutoCreditorPackage.do")
        public void exportAutoCreditorPackage(HttpServletRequest request, HttpServletResponse response,WmsInveAutoCreditPackageVO bean){
            String params =request.getParameter("params");
            ObjectMapper objectMapper=new ObjectMapper();
            try
            {
//                参数类型转换
                bean=objectMapper.readValue(params, WmsInveAutoCreditPackageVO.class);
            }
            catch (JsonParseException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (JsonMappingException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (IOException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            bean.setSortname("cre_pkg_code");
            bean.setSortorder("asc");
            bean.setExportFlag(1);
//            抵押包数据
         List<Map<String,Object>> list =  (List<Map<String, Object>>) wmsInveAutoCreditPackageServiceImpl.selectAutoCreditPackageInfo(bean).get("Rows");
         String  columnStr = "{'cre_pkg_code':'抵押包编号','cre_per_name':'抵押包姓名','protocol_id_year_num':'合同编号','cre_per_card_id':'身份证号','cre_pledge_mon':'抵押金额(万元)','house_size':'平米数','crepg_start_date':'起始日期','crepg_end_date':'终止日期','rele_per_name':'他项人','loca_num_name':'归属地','cre_type_name':'抵押类型','create_timestamp':'创建时间'}";
         String sheet_name = "sheet1";
         String out_file_name = "自动采集抵押包数据";
//          创建导出报表
          org.apache.poi.ss.usermodel.Workbook wb = XlsUtil.createExcel(sheet_name, list, JSON.parseObject(columnStr, LinkedHashMap.class), null, false,0,null);
          try
          {
//              导出数据
              XlsUtil.exportExcel(out_file_name, response, wb);
          }
          catch (IOException e)
          {
              e.printStackTrace();
          }
          
        }
        /**
         * 
         * @Title: iverificationContract
         * @Description: 验证合同编号
         * @return 
         * @author: zhangmingjian
         * @time:2017年7月28日 下午3:04:26
         * history:
         * 1、2017年7月28日 zhangmingjian 创建方法
         */
        @RequestMapping(value="/creditRightManager/iverificationContract.do")
        @ResponseBody
        public List<String> iverificationContract(String code ,String enable_flag){
            List<Map<String,Object>> list = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                list = mapper.readValue(code,new TypeReference<List<Map<String,Object>>>(){});
            }
            catch (JsonParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (JsonMappingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return wmsInveAutoCreditPackageServiceImpl.iverificationContract(list,enable_flag);
        }
        
        
}
