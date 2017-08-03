package com.zx.sframe.util.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.platform.syscontext.util.StringUtil;
import com.zx.platform.syscontext.vo.GridDataBean;

/**
 * 该类用于对表格进行相应的修饰
 * 
 * @author
 */
public class GridModify
{
    public static String RED = "#FF6666";

    public static String ORANGE = "#FF9933";

    public static String BLUE = "#5C7DFE";

    public static String GREEN = "#00FF66";

    public static String ROW_FONT_COLOR = "#ffffff";

    /*
     * 实现对表格行的底色、字体颜色、粗体的变换
     */
    public static void modifyRow(GridDataBean grid, String bgColor, boolean isBolt, String fontColor,
                                 IGridRowCondition condition)
    {

        List<Map<String, Object>> rows = grid.getRows();
        for (Map<String, Object> row : rows)
        {
            if (condition.isRight(row))
            {
                if (StringUtil.isNotBlank(bgColor))
                {
                    row.put("_rowbgcolor", bgColor);
                }
                if (isBolt)
                {
                    row.put("_rowfontbolt", true);
                }
                if (StringUtil.isNotBlank(fontColor))
                {
                    row.put("_rowfontcolor", fontColor);
                }
            }
        }
    }

    /**
     * 为采集数据定制的变色方法
     * 
     * @param grid 表格结果集
     * @param boltId 需要字体加粗的主键
     */
    public static void modifyRow(GridDataBean grid, Integer boltId)
    {
        List<Map<String, Object>> rows = grid.getRows();
        for (Map<String, Object> row : rows)
        {

            String color = (String) row.get("sum_result");
            Integer id = (Integer) row.get("id");

            if ("2".equals(color) || "3".equals(color))
            {
                row.put("_rowbgcolor", BLUE);
                row.put("_rowfontcolor", ROW_FONT_COLOR);
            }
            else if ("4".equals(color))
            {
                row.put("_rowbgcolor", ORANGE);
                row.put("_rowfontcolor", ROW_FONT_COLOR);
            }
            else if ("5".equals(color))
            {
                row.put("_rowbgcolor", RED);
                row.put("_rowfontcolor", ROW_FONT_COLOR);
            }
            if (boltId != null && boltId.equals(id))
            {
                row.put("_rowfontbolt", true);
            }
        }
    }

    /*
     * 实现删除表格指定列的重复数据，在视觉上达到通栏的效果
     */
    public static void mergeRowData(GridDataBean grid, String... columns)
    {
        List<Map<String, Object>> rows = grid.getRows();
        if (rows == null || rows.size() == 0 || columns == null || columns.length == 0)
        {
            return;
        }

        boolean isStart = true;
        HashMap<String, Object> colMap = new HashMap<String, Object>();
        Object value;
        for (Map<String, Object> row : rows)
        {
            if (isStart)
            {
                isStart = false;
                for (String columnName : columns)
                {
                    colMap.put(columnName, row.get(columnName));
                }
            }
            else
            {
                for (String columnName : columns)
                {
                    value = colMap.get(columnName);
                    if (value != null && value.equals(row.get(columnName)))
                    {
                        row.put(columnName, "");
                    }
                    else
                    {
                        colMap.put(columnName, row.get(columnName));
                    }
                }
            }

        }
    }
}
