package com.zx.sframe.util.spring;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <P>
 * FileName: ExtendsWebBinding.java
 * 
 * @author Administrator
 *         <P>
 *         CreateTime: Nov 8, 2011 9:34:54 AM
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 * @param
 */
public class ExtendsWebBinding implements WebBindingInitializer
{

    /**
     * <P>
     * Function: initBinder
     * <P>
     * Description:
     * <P>
     * Others:
     * 
     * @author: Administrator
     * @CreateTime: Nov 8, 2011 9:35:01 AM
     * @param binder
     * @param request
     */
    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        // 1. 使用spring自带的CustomDateEditor
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // binder.registerCustomEditor(Date.class, new
        // CustomDateEditor(dateFormat, true));

        // 2. 自定义的PropertyEditorSupport
        binder.registerCustomEditor(java.util.Date.class, new DateConvertEditor());
        binder.registerCustomEditor(java.sql.Date.class, new SqlDateConvertEditor());
        binder.registerCustomEditor(java.sql.Timestamp.class, new TimeStampConvertEditor());
    }

}
