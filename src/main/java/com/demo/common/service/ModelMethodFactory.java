package com.demo.common.service;

import com.demo.common.model.enums.DeleteStatusEnum;
import com.demo.common.model.enums.EnableStatusEnum;
import com.demo.common.model.enums.EntityDataAopEnum;
import com.demo.common.model.vo.Constants;
import com.demo.common.utils.SnowflakeIdUtil;
import com.demo.common.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelMethodFactory {
    private Map<String, Map<String,ModelMethodChannelService>> typeServiceMap;
    private Map<String,ModelMethodChannelService> insertServiceMap;
    private Map<String,ModelMethodChannelService> updateServiceMap;
    private Map<String,ModelMethodChannelService> selectServiceMap;

    public ModelMethodFactory(){
        typeServiceMap = new HashMap<>();

        insertServiceMap = new HashMap<>();
        insertServiceMap.put(EntityDataAopEnum.UUID.getCode(), new ModelUuidMethodChannelServiceImpl());
        insertServiceMap.put(EntityDataAopEnum.CREATOR.getCode(), new ModelCreateUserMethodChannelServiceImpl());
        insertServiceMap.put(EntityDataAopEnum.CREATE_TIME.getCode(), new ModelCreateDateMethodChannelServiceImpl());
        insertServiceMap.put(EntityDataAopEnum.ORG_UUID.getCode(), new ModelOrgMethodChannelServiceImpl());
        insertServiceMap.put(EntityDataAopEnum.DEL_FLAG.getCode(), new ModelIsDelMethodChannelServiceImpl());
        insertServiceMap.put(EntityDataAopEnum.WHETHER_ENABLE.getCode(), new ModelIsEnableMethodChannelServiceImpl());
        typeServiceMap.put(EntityDataAopEnum.TYPE_INSERT.getCode(), insertServiceMap);

        updateServiceMap = new HashMap<>();
        updateServiceMap.put(EntityDataAopEnum.UPDATOR.getCode(), new ModelUpdateUserMethodChannelServiceImpl());
        updateServiceMap.put(EntityDataAopEnum.UPDATE_TIME.getCode(), new ModelUpdateDateMethodChannelServiceImpl());
        typeServiceMap.put(EntityDataAopEnum.TYPE_UPDATE.getCode(), updateServiceMap);

        selectServiceMap = new HashMap<>();
        selectServiceMap.put(EntityDataAopEnum.DEL_FLAG.getCode(), new ModelIsDelMethodChannelServiceImpl());
//      ???????????????uuid???????????????????????????????????????????????????????????????uuid????????????
//        selectServiceMap.put(EntityDataAopEnum.ORG_UUID.getCode(), new ModelOrgMethodChannelServiceImpl());
        typeServiceMap.put(EntityDataAopEnum.TYPE_SELECT.getCode(), selectServiceMap);
    }

    /**
     * ????????????
     */
    class ModelCreateDateMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method, Object model) throws InvocationTargetException, IllegalAccessException {
//            method.invoke(model, new Date());
            field.set(model, new Date());
        }
    }

    /**
     * ?????????
     */
    class ModelCreateUserMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userId = request.getAttribute(Constants.USERID) == null ? null : String.valueOf(request.getAttribute(Constants.USERID));
//            method.invoke(model, userId);
            field.set(model, userId);
        }
    }

    /**
     * ???????????????????????????
     */
    class ModelIsDelMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
//            method.invoke(model, DeleteStatusEnum.NO_ENUM.getCode());
            field.set(model, DeleteStatusEnum.NO_ENUM.getCode());
        }
    }

    /**
     * ????????????????????????
     */
    class ModelIsEnableMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
//            method.invoke(model, EnableStatusEnum.YES_ENUM.getCode());
            field.set(model, EnableStatusEnum.YES_ENUM.getCode());
        }
    }

    /**
     * ????????????
     */
    class ModelOrgMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String orgUuid = request.getAttribute(Constants.ORGUUID) == null ? null : String.valueOf(request.getAttribute(Constants.ORGUUID));
//            method.invoke(model, orgUuid);
            field.set(model, orgUuid);
        }
    }

    /**
     * ????????????
     */
    class ModelUpdateDateMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
//            method.invoke(model, new Date());
            field.set(model, new Date());
        }
    }

    /**
     * ?????????
     */
    class ModelUpdateUserMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userId = request.getAttribute(Constants.USERID) == null ? null : String.valueOf(request.getAttribute(Constants.USERID));
//            method.invoke(model, userId);
            field.set(model, userId);
        }
    }

    /**
     * ??????
     */
    class ModelUuidMethodChannelServiceImpl implements ModelMethodChannelService{
        @Override
        public void executionMethod(Field field, Method method,Object model) throws InvocationTargetException, IllegalAccessException {
//            method.invoke(model, SnowflakeIdUtil.newStringId());
            field.set(model, SnowflakeIdUtil.newStringId());
        }
    }

    //???????????????????????????????????????Service
    public ModelMethodChannelService buildService(String channelType, String type){
        return typeServiceMap.get(type).get(channelType);
    }
}
