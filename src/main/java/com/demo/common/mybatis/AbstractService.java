//package com.demo.common.mybatis;
//
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.demo.common.model.enums.EntityDataAopEnum;
//import com.demo.common.service.ModelMethodChannelService;
//import com.demo.common.service.ModelMethodFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.exceptions.TooManyResultsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import tk.mybatis.mapper.entity.Condition;
//import tk.mybatis.mapper.entity.Example;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 基于通用MyBatis Mapper插件的Service接口的实现
// */
//@Slf4j
//public abstract class AbstractService<T> implements Service<T> {
//
//    @Autowired
//    protected Mapper<T> mapper;
//
//    private Class<T> modelClass;    // 当前泛型真实类型的Class
//
//    public AbstractService() {
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
//    }
//    @Override
//    public int save(T model) {
//        return mapper.insertSelective(updateModelFile(model,EntityDataAopEnum.TYPE_INSERT.getCode()));
//    }
//
//    @Override
//    public int save(List<T> models) {
//        List<T> listModels = new ArrayList<>();
//        models.stream().forEach(model -> {
//            listModels.add(updateModelFile(model,EntityDataAopEnum.TYPE_INSERT.getCode()));
//        });
//        return mapper.insertList(models);
//    }
//
//    @Override
//    public int deleteById(String id) {
//        return mapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int deleteByModel(T model) {
//        return mapper.delete(model);
//    }
//
//    @Override
//    public int deleteByIds(String ids) {
//        return mapper.deleteByIds(ids);
//    }
//
//    @Override
//    public void deleteByIds(Class<T> clazz, List<String> values, String idName) {
//        Example example = new Example(clazz);
//        example.createCriteria().andIn(idName, values);
//        mapper.deleteByExample(example);
//    }
//
//    @Override
//    public int update(T model) {
//        return mapper.updateByPrimaryKeySelective(updateModelFile(model,EntityDataAopEnum.TYPE_UPDATE.getCode()));
//    }
//
//    @Override
//    public int updateByPrimaryKey(T model){
//        return mapper.updateByPrimaryKey(updateModelFile(model,EntityDataAopEnum.TYPE_UPDATE.getCode()));
//    }
//    @Override
//    public int updateByCondition(T model,Object obj){
//        return mapper.updateByCondition(model,obj);
//    }
//    @Override
//    public int updateByConditionSelective(T model,Object obj){
//        return mapper.updateByConditionSelective(model,obj);
//    }
//    @Override
//    public int updateByExample(T model,Object obj){
//        return mapper.updateByExample(model,obj);
//    }
//    @Override
//    public int updateByExampleSelective(T model,Object obj){
//        return mapper.updateByExampleSelective(model,obj);
//    }
//
//    @Override
//    public T findById(String id) {
//        return mapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public PageInfo<T> selectPage(T parameter,Integer pageNum,Integer pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<T> list = mapper.select(updateModelFile(parameter,EntityDataAopEnum.TYPE_SELECT.getCode()));
//        PageInfo<T> pageInfo = new PageInfo<T>(list);
//        return pageInfo;
//    }
//
//    @Override
//    public PageInfo<T> selectByCondition(Example example,Integer pageNum,Integer pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<T> list =   mapper.selectByExample(example);
//        PageInfo<T> pageInfo = new PageInfo<T>(list);
//        return pageInfo;
//    }
//
//    @Override
//    public T findBy(String fieldName, Object value) throws TooManyResultsException {
//        try {
//            T model = modelClass.newInstance();
//            Field field = modelClass.getDeclaredField(fieldName);
//            field.setAccessible(true);
//            field.set(model, value);
//            return mapper.selectOne(updateModelFile(model,EntityDataAopEnum.TYPE_SELECT.getCode()));
//        } catch (ReflectiveOperationException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public T findByModel(T model) throws TooManyResultsException {
//        return mapper.selectOne(updateModelFile(model,EntityDataAopEnum.TYPE_SELECT.getCode()));
//    }
//
//    @Override
//    public List<T> findByIds(String ids) {
//        return mapper.selectByIds(ids);
//    }
//
//    @Override
//    public List<T> findByCondition(Condition condition) {
//        return mapper.selectByCondition(condition);
//    }
//
//    @Override
//    public List<T> findByCondition(Example example) {
//        return mapper.selectByExample(example);
//    }
//
//    @Override
//    public List<T> findAll() {
//        return mapper.selectAll();
//    }
//
//    @Override
//    public  List<T> findModelList(T model){
//        return  mapper.select(updateModelFile(model,EntityDataAopEnum.TYPE_SELECT.getCode()));
//    }
//
//    private T updateModelFile(T model,String type){
//        try{
//            Class<?> tClass = model.getClass();
//            //得到所有属性
//            Field[] fields = tClass.getDeclaredFields();
//
//            for(Field field : fields){
//                field.setAccessible(true);
//                String name = field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase());
//                Method method = tClass.getMethod("set"+name,new Class[]{field.getType()});
//                Method getMethod = tClass.getMethod("get"+name);
//                Object valObj = getMethod.invoke(model);
//                if(valObj == null){
//                    ModelMethodFactory modelMethodFactory = new ModelMethodFactory();
//                    ModelMethodChannelService channelService =  modelMethodFactory.buildService(field.getName(),type);
//                    if(channelService != null){
//                        channelService.executionMethod(method,model);
//                    }
//                }
//            }
//        }catch(Exception e){
//            log.info("AbstractService - 没有这个属性");
//        }
//        return model;
//    }
//}
