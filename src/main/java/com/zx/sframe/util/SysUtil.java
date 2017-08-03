package com.zx.sframe.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.zx.emanage.util.gen.domain.SysUser;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.vo.UserBean;

public class SysUtil
{
    /**
     * 获取List中指定属性的值为propertyVal的列表
     * 
     * @param List
     * @param propertyName
     * @param propertyVal
     */
    public static List<Map<String, Object>> getSamePropertyList(List<Map<String, Object>> list, String propertyName,
                                                                String propertyVal)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list)
        {
            String val = map.get(propertyName).toString();
            if (val == null)
            {
                continue;
            }
            if (val.equals(propertyVal))
            {
                resultList.add(map);
            }
        }
        if (resultList.size() == 0)
        {
            return null;
        }
        return resultList;
    }

    /**
     * 根据传入的属性名称、遍历list返回属性值与propertyVal相等的记录
     * 
     * @param list
     * @param propertyName
     * @param propertyVal
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> getSamePropertyListBean(Collection<T> list, String propertyName, Object propertyVal)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }
        List<T> resultList = new ArrayList<T>();
        for (T bean : list)
        {
            Object val = null;
            try
            {
                if (bean instanceof java.util.Map)
                {
                    val = ((java.util.Map) bean).get(propertyName);
                }
                else
                {
                    PropertyDescriptor p = new PropertyDescriptor(propertyName, bean.getClass());
                    val = p.getReadMethod().invoke(bean);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (val == null)
            {
                continue;
            }
            if (val.equals(propertyVal))
            {
                resultList.add(bean);
            }
        }
        if (resultList.size() == 0)
        {
            return null;
        }
        return resultList;
    }

    /**
     * 返回与传入属性propertyName值相同的一个对象
     * 
     * @param list
     * @param propertyName
     * @param propertyVal
     * @return
     */
    public static <T> T getSamePropertyBean(Collection<T> list, String propertyName, Object propertyVal)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }
        for (T bean : list)
        {
            Object val = null;
            try
            {
                if (bean instanceof java.util.Map)
                {
                    val = ((java.util.Map) bean).get(propertyName);
                }
                else
                {
                    PropertyDescriptor p = new PropertyDescriptor(propertyName, bean.getClass());
                    val = p.getReadMethod().invoke(bean);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (val == null)
            {
                continue;
            }
            if (val.equals(propertyVal))
            {
                return bean;
            }
        }
        return null;
    }

    /**
     * 获取浏览器的ip地址
     * 
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip))
        {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip))
        {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取用户对象
     * 
     * @param request
     * @return
     */
    public static UserBean getUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return user;
    }

    /**
     * 获取浏览器类型
     * 
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request)
    {
        String agent = request.getHeader("User-Agent");
        return agent;
    }

    private static boolean checkIP(String ip)
    {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split("\\.").length != 4)
        {
            return false;
        }
        return true;
    }

    /**
     * 模糊匹配时将传入的参数的特殊字符(%\_)进行转义，并将前后增加通配符%
     * 
     * @param sqlParam
     * @return
     */
    public static String getSqlLikeParam(String sqlParam)
    {
        sqlParam = sqlParam.replace("\\", "\\\\");
        sqlParam = sqlParam.replace("%", "\\%");
        sqlParam = sqlParam.replace("_", "\\_");
        return "%" + sqlParam + "%";
    }

    public static String ajaxFileTextEncoding(String srcStr, HttpServletRequest request)
    {
        String res;
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("firefox") > 0 || agent.indexOf("msie 10") > 0 || agent.indexOf("msie 9") > 0
            || agent.indexOf("chrome") > 0)
        {
            try
            {
                res = new String(srcStr.getBytes("UTF-8"), "ISO8859-1");
                return res;
            }
            catch (UnsupportedEncodingException e)
            {
                // log.error(e.getMessage());
            }
        }
        else if (agent.indexOf("msie 8") > 0)
        {
            return srcStr;
        }

        return srcStr;
    }

    /**
     * 比较传入的两个javaBean内容是否一致，不比较last_update_user_id,last_update_timestamp
     * 
     * @param obj1
     * @param obj2
     * @return
     */
    public static <T> String checkBean(T obj1, T obj2)
    {
        Class clazz = obj1.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, String> map = new HashMap<String, String>();
        for (Field field : fields)
        {
            try
            {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();// 获得get方法
                Object o1 = getMethod.invoke(obj1);
                Object o2 = getMethod.invoke(obj2);
                if ((o1 == null && o2 == null))
                {
                    continue;
                }
                if ((o1 == null || o2 == null))
                {
                    map.put(field.getName(), ":" + o1 + "--》" + o2);
                    continue;
                }
                if (o1 instanceof String)
                {
                    if (!o1.equals(o2))
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
                else if (o1 instanceof BigDecimal)
                {
                    if (((BigDecimal) o1).compareTo((BigDecimal) o2) != 0)
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
                else if (o1 instanceof Timestamp)
                {
                    if (((Timestamp) o1).getTime() != ((Timestamp) o2).getTime())
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
                else if (o1 instanceof Date)
                {
                    if (((Date) o1).getTime() != ((Date) o2).getTime())
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
                else if (o1 instanceof Integer)
                {
                    if (((Integer) o1).intValue() == ((Integer) o2).intValue())
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
                else
                {
                    if (!o1.toString().equals(o2.toString()))
                    {
                        map.put(field.getName(), ":" + o1 + "--》" + o2);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        if (map.size() > 0)
        {
            return new Gson().toJson(map);
        }
        else
        {
            return "";
        }
    }

    public static void main(String args[])
    {
        SysUser user = new SysUser();
        user.setDept_id(1);
        user.setLast_update_timestamp(new Timestamp(new Date().getTime()));
        // System.out.println(SysUtil.checkBean(user, user2));
    }
    /**
     * 发送短息 --此方法存在乱码问题
     * */
    public static String sendInfo(Map<String, String> map){
    	String result ="success";
    	MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("interface_num", "SMS_OUT_001");//短信调用接口编码
		form.add("sys_num", "WMS");//发送短信的系统
		//form.add("user_id", "0");
		form.add("mobile", map.get("tel").toString());//发送短信手机号码
		//需要发送的信息内容
        String srcStr = "您有待审批的单据,请登录WMS财富管理系统审批或登录手机版，" + "网站地址 ：" + PlatformGlobalVar.SYS_PROPERTIES.get("zshUrl");
		try {
			form.add("content", new String(srcStr.getBytes("gbk"), "utf-8"));//GBK //UTF-8//GB2312
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		form.add("num", "");
		RestTemplate restTemplate = new RestTemplate();
		Map<String,Object> temp = restTemplate
				.postForObject(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url")+"/modi/restful/simple",
						form, Map.class);
    	return result;
    }

    /**
     * 发送短信息   现在正在使用的方法2016-6-8
     * @param map
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void sendMsg(Map<String, String> map) throws ClientProtocolException, IOException {
    	//http://localhost:4080/modi/restful/simple
        HttpPost httpPost = new HttpPost(PlatformGlobalVar.SYS_PROPERTIES.get("ekp_login_url") + "/modi/restful/simple");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("interface_num", "SMS_OUT_003"));
        nvps.add(new BasicNameValuePair("sys_num", "WMS"));
        if(!map.containsKey("user_id")) {
            nvps.add(new BasicNameValuePair("user_id", "0"));
        } else {
            nvps.add(new BasicNameValuePair("user_id", map.get("user_id")));
        }
        nvps.add(new BasicNameValuePair("tpl_id", map.get("tpl_id").toString()));
        nvps.add(new BasicNameValuePair("mobile", map.get("tel").toString()));
        nvps.add(new BasicNameValuePair("tpl_value", map.get("paramJson").toString()));
        
        nvps.add(new BasicNameValuePair("num", ""));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            // do something useful with the response body
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        } finally {
            response.close();
            httpclient.close();
        }
    }    
    
    /**
     * 根据map里的key替换形如【#need_replace_value#】参数(need_replace_value)的值
     */
    public static String replaceParamValueByMap(String oldString, Map<String, String> paramMap) {
        if(paramMap == null) {
            return oldString;
        }
        for(String key : paramMap.keySet()) {
            if(paramMap.get(key) != null) {
                oldString = oldString.replace("#" + key + "#", paramMap.get(key));
            }
        }
        return oldString;
    }
    
}
