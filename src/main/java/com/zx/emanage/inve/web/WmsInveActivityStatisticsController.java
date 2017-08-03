package com.zx.emanage.inve.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zx.emanage.inve.service.IWmsInveActivityStatisticsService;
import com.zx.emanage.inve.vo.WmsInveActivityStatisticsSearchBeanVO;
import com.zx.sframe.util.ExpertUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveActivityStatisticsController
 * 模块名称：活动量统计
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月23日
 * @version V3.5
 * history:
 * 1、2017年3月23日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveActivityStatisticsController
{
    @Autowired
    private IWmsInveActivityStatisticsService wmsInveActivityStatisticsServiceImpl;

    /**
     * 
     * @Title: selectActivityStatisticsList
     * @Description: 活动量统计列表信息
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月23日 下午3:18:01
     * history:
     * 1、2017年3月23日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/selectActivityStatisticsList.do")
    @ResponseBody
    public Map<String, Object> selectActivityStatisticsList(HttpServletRequest request, WmsInveActivityStatisticsSearchBeanVO bean)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);    
        return wmsInveActivityStatisticsServiceImpl.selectActivityStatisticsList(bean, user);
    }

    /**
     * 
     * @Title: selectActivityStatisticsList
     * @Description: 活动量报表导出
     * @param request
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月31日 上午10:03:47
     * history:
     * 1、2017年3月31日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/selectActivityStatisticsImportExcel.do")
    @ResponseBody
    public void selectActivityStatisticsImportExcel(HttpServletRequest request, HttpServletResponse response, String parameter)
    {
        Map<String, Object> map = new HashMap<>();
        // 报表时间
        JSONObject json = JSONObject.parseObject(parameter);
        map = json;
        Calendar cal = Calendar.getInstance();
        String[] backup_month = null;
        if(map.get("backup_month")!=null){
             backup_month =map.get("backup_month").toString().split("-");
        }
        SimpleDateFormat sd =  new SimpleDateFormat("yyyy年MM月");
            cal.set(Calendar.MONTH,Integer.parseInt(backup_month[1]));
            cal.set(Calendar.YEAR,Integer.parseInt(backup_month[0]));
             cal.add(Calendar.MONTH, -1);
         String datestr = sd.format(cal.getTime());
         
        String out_file_name = datestr + "业务员&部门考核信息统计表（活动量）_平台版本.xls";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        Map<String, Object> mapData = null;

        if (map.get("personnel_state").equals(""))
        {
            map.put("personnel_state", null);
        }
        // 导出数据
        mapData = wmsInveActivityStatisticsServiceImpl.selectActivityStatisticsImportExcel(map, user);

        Map<String, Object> listMap = new HashMap<String, Object>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar   cale = Calendar.getInstance();
        cale.set(Calendar.MONTH,Integer.parseInt(backup_month[1]));
        cale.set(Calendar.YEAR,Integer.parseInt(backup_month[0]));
      
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String  datestr1 = format.format(cale.getTime());
        Map<String, Object> titleMap = new HashMap<String, Object>();
        titleMap.put("个人考核", "数据有效性：截止" + datestr1 + "财富管理平台&CRM平台数据");
        titleMap.put("部门考核", "数据有效性：截止" + datestr1 + "财富管理平台&CRM平台数据");

        listMap.put("titles", titleMap);

        listMap.put("个人考核", mapData.get("personnel_data"));
        listMap.put("部门考核", mapData.get("team_data"));
        ExpertUtil eu = new ExpertUtil();
        eu.expertExcel("业务员&部门考核信息统计表(活动量)模板.xls", listMap, out_file_name, "titles", 4, response);
    }

    /**
     * 
     * @Title: jsonToObject
     * @Description: jsonObject 转 map
     * @param jsonObj
     * @return
     * @throws Exception 
     * @author: zhangmingjian
     * @time:2017年4月7日 下午3:16:50
     * history:
     * 1、2017年4月7日 zhangmingjian 创建方法
     */
    private Map<String, Object> jsonToObject(JSONObject jsonObj) throws Exception
    {

        Iterator<String> nameItr = (Iterator<String>) jsonObj.keySet();
        String name;
        Map<String, Object> outMap = new HashMap<String, Object>();
        while (nameItr.hasNext())
        {
            name = nameItr.next();
            outMap.put(name, jsonObj.getString(name));
        }
        return outMap;
    }

}
