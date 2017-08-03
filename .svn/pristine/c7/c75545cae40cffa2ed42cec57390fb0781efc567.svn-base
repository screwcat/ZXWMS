/**   
 * Project Name:WMS   
 * File Name:UserCityUtil.java   
 * Package Name:com.zx.emanage.sysmanage.util   
 * Date:2014年6月26日下午6:31:09   
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.   
 *   
 */

package com.zx.emanage.sysmanage.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.emanage.sysmanage.persist.WmsSysDictDataDao;
import com.zx.emanage.util.gen.entity.WmsSysDictData;
import com.zx.sframe.util.GlobalVal;

/**
 * ClassName:UserCityUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年6月26日 下午6:31:09 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 * @see
 */
public class UserCityUtil
{
    public static Map<String, String> PROVCITYMAP = new HashMap<>();

    private static Integer WMS_SYS_DICT_ID = 25;
    static
    {
        WmsSysDictDataDao wmsSysDictDataDao = (WmsSysDictDataDao) GlobalVal.ctx.getBean("wmsSysDictDataDao");
        List<WmsSysDictData> list = wmsSysDictDataDao.getDictDataByDictId(WMS_SYS_DICT_ID);
        PROVCITYMAP = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++)
        {
            WmsSysDictData data = list.get(i);
            PROVCITYMAP.put(data.getValue_code(), data.getValue_meaning());
        }
    }

    public static String getUserCity(String regionNumber)
    {
        String val = PROVCITYMAP.get(regionNumber);
        if (StringUtil.isBlank(val))
        {
            return "";
        }
        return val.substring(val.indexOf("省") + 1, val.length() - 1);
    }

    public static String getUserProv(String regionNumber)
    {
        String val = PROVCITYMAP.get(regionNumber);
        if (StringUtil.isBlank(val))
        {
            return "";
        }
        return val.substring(0, val.indexOf("省"));
    }
}
