package com.zx.sframe.util.mybatis;

import java.util.List;
import java.util.Map;

/**
 * ClassName: BaseDao <br/>
 * Function: 该类为所有mybatis dao接口的父类，所有接口需继承该类 <br/>
 * date: 2014年5月6日 下午3:10:24 <br/>
 * 
 * @author Administrator
 * @version @param <T>
 * @since JDK 1.6
 */
@MyBatisRepository
public interface BaseDao<T extends BaseEntity>
{
    /**
     * get:根据主键获取实体类. <br/>
     * 
     * @author Administrator
     * @param id
     * @return
     * @since JDK 1.6
     */
    T get(Integer id);

    /**
     * search:根据传人的条件动态生成sql语句，如需分页需要在sql中加入offset、pagesize变量 <br/>
     * 
     * @author Administrator
     * @param parameters
     * @return
     * @since JDK 1.6
     */
    List<Map<String, Object>> search(Map<String, Object> parameters);

    /**
     * save:在数据库中insert一条记录. <br/>
     * 
     * @author Administrator
     * @param t
     * @return
     * @since JDK 1.6
     */
    Integer save(T t);

    /**
     * delete:删除数据库记录，该方法请慎用，WMS系统中为逻辑删除. <br/>
     * 
     * @author Administrator
     * @param id
     * @since JDK 1.6
     */
    void delete(Integer[] id);

    /**
     * update:更新传人的实体类. <br/>
     * 
     * @author Administrator
     * @param t
     * @since JDK 1.6
     */
    int update(T t);

    /**
     * findCount:根据查询条件返回记录总条数. <br/>
     * 
     * @author Administrator
     * @param paramMaps
     * @return
     * @since JDK 1.6
     */
    int findCount(Map<String, Object> paramMaps);

    List<T> getListByEntity(T t);
}
