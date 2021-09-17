//package com.demo.common.mybatis;
//
//import com.github.pagehelper.PageInfo;
//import org.apache.ibatis.exceptions.TooManyResultsException;
//import tk.mybatis.mapper.entity.Condition;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//
///**
// * Service 层 基础接口，其他Service 接口 请继承该接口
// */
//public interface Service<T> {
//    int save(T model);//持久化
//
//    int save(List<T> models);//批量持久化
//
//    int deleteById(String id);//通过主鍵刪除
//
//    int deleteByModel(T model);//通过T model
//
//    int deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”
//
//    void deleteByIds(Class<T> clazz, List<String> values, String idName);
//
//    int update(T model);//更新
//
//    int updateByPrimaryKey(T model);
//
//    int updateByCondition(T model, Object obj);
//
//    int updateByConditionSelective(T model, Object obj);
//
//    int updateByExample(T model, Object obj);
//
//    int updateByExampleSelective(T model, Object obj);
//
//    T findById(String id);//通过ID查找
//
//    T findByModel(T model);//通过model查找
//
//    T findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
//
//    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
//
//    List<T> findModelList(T model);//通过model查找
//
//    List<T> findByCondition(Condition condition);//根据条件查找
//
//    List<T> findByCondition(Example example);//根据条件查找
//
//    List<T> findAll();//获取所有
//
//    PageInfo<T> selectPage(T parameter, Integer pageNum, Integer pageSize);
//
//    PageInfo<T> selectByCondition(Example example, Integer pageNum, Integer pageSize);
//}
