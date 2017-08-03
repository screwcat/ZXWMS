package com.zx.emanage.inve.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveChangManagementService;
import com.zx.emanage.inve.vo.WmsInveChangManagementBeanVO;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveChangManagementController
 * 模块名称：变更申请
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年4月11日
 * @version V3.5
 * history:
 * 1、2017年4月11日 zhangmingjian 创建文件
 */
@Controller
public class WmsInveChangManagementController
{
    @Autowired
    private IWmsInveChangManagementService wmsInveChangManagementService;
    
    /**
     * 
     * @Title: initPageInfo
     * @Description: 初始化页面信息
     * @param request
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 上午10:11:52
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/initPageInfo.do")
    @ResponseBody
    public Map<String, Object> initPageInfo(HttpServletRequest request)
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String date = sd.format(new Date());
        
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        // 登陆人信息
        map.put("user", user);
        // 查询单据编号
        map.put("bill_number", wmsInveChangManagementService.getBillNumber());
        // 保存当前日期
        map.put("nowDate", date);
        return map;
    }

    /**
     * 
     * @Title: selectBillInfo
     * @Description: 查询单据列表
     * @param request
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月11日 下午1:20:54
     * history:
     * 1、2017年4月11日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/selectBillInfo.do")
    @ResponseBody
    public Map<String, Object> selectBillInfo(HttpServletRequest request, WmsInveChangManagementBeanVO bean)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        bean.setPersonnel_id(user.getUserId());
        if (StringUtils.isNotBlank(bean.getId()))
        {
            bean.setIds(bean.getId().split(","));
        }

        Map<String, Object> map = new HashMap<>();
        // 分页处理
        if (bean.getPage() != null && bean.getPagesize() != null)
        {
            bean.setPage((bean.getPage() - 1) * bean.getPagesize());
        }
        if (StringUtils.isBlank(bean.getCus_name()))
        {
            bean.setCus_name(null);
        }
        if (StringUtils.isBlank(bean.getId_card()))
        {
            bean.setId_card(null);
        }
        map.put("Rows", wmsInveChangManagementService.selectBillInfo(bean));
        return  map;
    }

    /**
     * 
     * @Title: saveChangeAppInfo
     * @Description: 保存变更申请单
     * @param request
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月12日 下午2:51:05
     * history:
     * 1、2017年4月12日 zhangmingjian 创建方法
     */
    @RequestMapping(value = "/inve/saveChangeAppInfo.do")
    @ResponseBody
    public String saveChangeAppInfo(HttpServletRequest request, WmsInveChangManagementBeanVO bean, String jsonstr)
    {

        ObjectMapper mapper = new ObjectMapper();

        // 获取当前时间
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = sd.format(new Date().getTime());
        // 获取当前登陆人信息
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        // 创建人
        bean.setCreate_user_id(user.getUserId());
        // 最后修改人
        bean.setLast_update_user_id(user.getUserId());
        // 最后创建时间
        bean.setCreate_timestamp(nowtime);
        // 最后修改时间
        bean.setLast_update_timestamp(nowtime);

        try
        {
            // 处理附件信息
            List<Map<String, Object>> attinfo = mapper.readValue(bean.getAttinfo(), new TypeReference<List<Map<String, Object>>>()
            {
            });
            // 处理选中的单据
            List<Map<String, Object>> griddata = mapper.readValue(bean.getGriddata(), new TypeReference<List<Map<String, Object>>>()
            {
            });
            bean.setAttrinfo(attinfo);
            bean.setDatagrid(griddata);
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

        return wmsInveChangManagementService.saveChangeAppInfo(bean);
    }



    /**
     * 
     * @Title: selectDictInfo
     * @Description: 查询数据字典
     * @param dict_id
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 上午9:31:45
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */

    @RequestMapping(value = "/inve/selectDictInfo.do")
    @ResponseBody
    public List<Map<String, Object>> selectDictInfo(String dict_id)
    {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap();
        map.put("value_meaning", "-请选择-");
        map.put("value_code", "def");
        map.put("value", "def");
        list.add(map);
        list.addAll(wmsInveChangManagementService.selectDictInfo(dict_id));
        return list;
    };

    /**
     * 
     * @Title: selectChangeAppList
     * @Description: 查询变更申请列表
     * @param request
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月14日 下午3:14:43
     * history:
     * 1、2017年4月14日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectChangeAppList.do")
    @ResponseBody
    public Map<String, Object> selectChangeAppList(HttpServletRequest request,WmsInveChangManagementBeanVO bean)
    {
        UserBean user = (UserBean) request.getSession().getAttribute(GlobalVal.USER_SESSION);
        bean.setPersonnel_id(user.getUserId());
        bean.setPage((bean.getPage() - 1) * bean.getPagesize());

        return wmsInveChangManagementService.selectChangeAppList(bean);
    }

    /**
     * 
     * @Title: selectChangeAppList
     * @Description: 根据变更单据id查询变更单据信息
     * @param request
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年4月16日 上午10:29:43
     * history:
     * 1、2017年4月16日 zhangmingjian 创建方法
     */
    @RequestMapping(value="/inve/selectChangeAppInfoById.do")
    @ResponseBody
    public Map<String, Object> selectChangeAppInfoById(HttpServletRequest request,WmsInveChangManagementBeanVO bean)
    {
        return wmsInveChangManagementService.selectChangeAppInfoById(bean);
    }
    
    
}
