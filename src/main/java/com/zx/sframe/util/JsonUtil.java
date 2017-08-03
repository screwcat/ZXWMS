package com.zx.sframe.util;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.zx.emanage.util.gen.domain.WmsCreCustomerChangeLineHouseinfo;
import com.zx.platform.syscontext.util.StringUtil;

public class JsonUtil
{

    /**
     * 根据传入的json数组，返回指定包含类型的集合
     * 
     * @param jsonStr [{id:1,name:gx},{id:2,name:wxm}]
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonArrayToList(String jsonStr, Class<T> clazz)
    {
        if (jsonStr == null || jsonStr.trim().equals(""))
        {
            return null;
        }
        List<T> resultList = new ArrayList<T>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(java.lang.Double.class, new DoubleAdapter());
        builder.registerTypeAdapter(java.lang.Integer.class, new IntegerAdapter());
        builder.registerTypeAdapter(java.math.BigInteger.class, new BigIntegerAdapter());
        builder.registerTypeAdapter(java.lang.Long.class, new LongAdapter());
        builder.registerTypeAdapter(java.sql.Date.class, new SqlDateAdapter("yyyy-MM-dd"));
        builder.registerTypeAdapter(java.sql.Timestamp.class, new SqlTimestampAdapter("yyyy-MM-dd HH:mm:ss"));
        builder.registerTypeAdapter(java.math.BigDecimal.class, new BigDecimalAdapter());
        Gson gson = builder.create();
        // Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonStr); // 将json字符串转换成JsonElement
        JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
        Iterator<JsonElement> it = jsonArray.iterator(); // Iterator处理
        while (it.hasNext())
        { // 循环
            jsonElement = (JsonElement) it.next(); // 提取JsonElement
            String menu = jsonElement.toString(); // JsonElement转换成String
            if (StringUtil.isBlank(menu))
            {
                menu = null;
            }
            T bean = gson.fromJson(menu, clazz); // String转化成JavaBean
            resultList.add(bean); // 加入List
        }
        return resultList;
    }
    
    /**
     * 将json字符串转为bean
     * 
     * @param jsonStr [{id:1,name:gx},{id:2,name:wxm}]
     * @param clazz
     * @return 
     * @return
     */
    public static <T> T jsonStringToBean(String jsonStr, Class<T> clazz) {
        if (jsonStr == null || jsonStr.trim().equals("")) {
            return null;
        }
        
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(java.lang.Double.class, new DoubleAdapter());
        builder.registerTypeAdapter(java.lang.Integer.class, new IntegerAdapter());
        builder.registerTypeAdapter(java.math.BigInteger.class, new BigIntegerAdapter());
        builder.registerTypeAdapter(java.lang.Long.class, new LongAdapter());
        builder.registerTypeAdapter(java.sql.Date.class, new SqlDateAdapter("yyyy-MM-dd"));
        builder.registerTypeAdapter(java.sql.Timestamp.class, new SqlTimestampAdapter("yyyy-MM-dd HH:mm:ss"));
        builder.registerTypeAdapter(java.math.BigDecimal.class, new BigDecimalAdapter());
        Gson gson = builder.create();
        
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonStr);
        String menu = jsonElement.toString(); // JsonElement转换成String
        if (StringUtil.isBlank(menu)) {
            menu = null;
        }
        T bean = gson.fromJson(menu, clazz);
        
        return bean;
    }

    public static void main(String args[])
    {
        String str = "[{'create_timestamp':'2014-05-06 10:00:00','house_buy_money':'120.00','house_buy_date':'2014-05-06','wms_cre_customer_change_line_houseinfo_id':'1'},{'create_timestamp':'','house_buy_money':'','house_buy_date':''}]";
        List<WmsCreCustomerChangeLineHouseinfo> list = jsonArrayToList(str, WmsCreCustomerChangeLineHouseinfo.class);
        // System.out.println(list.size());
    }

    /**
     * 将传入的对象转为json字符串，支持不固定参数 使用示例：allToJson(1,2,3)，allToJson()，
     * allToJson(null)， allToJson(new TreeBean(), "aaa")
     * 
     * @author Allen
     * @return json字符串
     */
    public static String allToJson(Object... objArray)
    {
        Gson gson = new Gson();
        if (objArray != null)
        {
            return gson.toJson(objArray);
        }
        return gson.toJson(new Object[0]);
    }

    public static class DoubleAdapter implements JsonDeserializer<java.lang.Double>
    {

        public DoubleAdapter()
        {

        }

        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                     throws JsonParseException
        {
            String doubleStr = json.getAsString();
            if (StringUtil.isBlank(doubleStr))
            {
                return null;
            }
            else
            {
                return new Double(doubleStr);
            }
        }

    }

    public static class IntegerAdapter implements JsonDeserializer<java.lang.Integer>
    {

        public IntegerAdapter()
        {

        }

        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                      throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return new Integer(str);
            }
        }

    }

    public static class BigIntegerAdapter implements JsonDeserializer<java.math.BigInteger>
    {

        public BigIntegerAdapter()
        {

        }

        @Override
        public BigInteger deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                         throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return new BigInteger(str);
            }
        }

    }

    public static class LongAdapter implements JsonDeserializer<java.lang.Long>
    {

        public LongAdapter()
        {

        }

        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                   throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return new Long(str);
            }
        }

    }

    public static class BigDecimalAdapter implements JsonDeserializer<java.math.BigDecimal>
    {
        public BigDecimalAdapter()
        {

        }

        @Override
        public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                         throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return new BigDecimal(str);
            }
        }
    }

    public static class SqlDateAdapter implements JsonDeserializer<java.sql.Date>
    {

        private String dateFormat;

        public SqlDateAdapter()
        {

        }

        public SqlDateAdapter(String dateFormat)
        {
            this.dateFormat = dateFormat;
        }

        @Override
        public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                            throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return DateUtil.strToSqlDate(str, dateFormat);
            }
        }

    }

    public static class SqlTimestampAdapter implements JsonDeserializer<java.sql.Timestamp>
    {

        private String dateFormat;

        public SqlTimestampAdapter()
        {

        }

        public SqlTimestampAdapter(String dateFormat)
        {
            this.dateFormat = dateFormat;
        }

        @Override
        public java.sql.Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                                                                                                 throws JsonParseException
        {
            String str = json.getAsString();
            if (StringUtil.isBlank(str))
            {
                return null;
            }
            else
            {
                return DateUtil.strTimestamp(str, dateFormat);
            }
        }

    }
}
