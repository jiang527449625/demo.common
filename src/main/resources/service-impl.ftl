package ${basePackage}.service.impl;

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.entity.${modelNameUpperCamel};
import ${basePackage}.service.I${modelNameUpperCamel}Service;
import ${basePackage}.model.vo.${modelNameUpperCamel}VO;
import com.demo.common.model.enums.DeleteStatusEnum;
import com.demo.common.mybatis.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import java.util.List;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements I${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public int save(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        return super.save(${modelNameLowerCamel});
    }

    @Override
    public int deleteByModel(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        return super.deleteByModel(${modelNameLowerCamel});
    }

    @Override
    public int update(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        return super.update(${modelNameLowerCamel});
    }

    @Override
    public int updateDelModel(String[] ids) {
        int count = 0;
        if(ids.length > 0){
            for(String id : ids){
                ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
                ${modelNameLowerCamel}.setId(id);
                ${modelNameLowerCamel}.setDelFlag(DeleteStatusEnum.YES_ENUM.getCode());
                count += super.update(${modelNameLowerCamel});
            }
        }
        return count;
    }

    @Override
    public ${modelNameUpperCamel} findByModel(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        return super.findByModel(${modelNameLowerCamel});
    }

    @Override
    public List<${modelNameUpperCamel}> findModelList(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        return super.findModelList(${modelNameLowerCamel});
    }

    @Override
    public PageInfo<${modelNameUpperCamel}> selectByPage(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}VO,${modelNameLowerCamel});
        List<${modelNameUpperCamel}> list = super.findModelList(${modelNameLowerCamel});
        return new PageInfo<${modelNameUpperCamel}>(list);
    }
}
