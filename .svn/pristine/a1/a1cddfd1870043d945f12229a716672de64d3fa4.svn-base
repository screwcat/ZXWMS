package com.zx.emanage.inve.web;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveClerkRegionService;
import com.zx.emanage.inve.vo.WmsInveSaleLimitVo;
import com.zx.emanage.util.gen.entity.ColumnInfo;

/*
 * @version 2.0
 * @author
 */

@Controller
public class WmsInveClerkRegionController {
	private static Logger log = LoggerFactory.getLogger(WmsInveClerkRegionController.class);
	
	@Autowired
    private IWmsInveClerkRegionService wmsinveclerkregionService;

    /**
     * 
     * @Title: getWmsInveClerkRegionList
     * @Description: 查询柜员区域表(Column)list
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月20日 上午11:09:33
     * history:
     * 1、2017年2月20日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClerkRegionColumnList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<ColumnInfo> getWmsInveClerkRegionColumnList()
    {
        LinkedList<ColumnInfo> list = wmsinveclerkregionService.getWmsInveClerkRegionColumnList();
        list.addFirst(new ColumnInfo());
        return list;
    }

    /**
     * 
     * @Title: getWmsInveClerkRegionList
     * @Description: 查询柜员区域表list
     * @return 
     * @author: zhangyunfei
     * @time:2017年4月20日 上午9:37:26
     * history:
     * 1、2017年4月20日 Administrator 创建方法
     */
    @RequestMapping(value = "/inve/getWmsInveClerkRegionList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<WmsInveSaleLimitVo> getWmsInveClerkRegionList(String limit_type)
    {
        return wmsinveclerkregionService.getWmsInveClerkRegionList(limit_type);
    }
}