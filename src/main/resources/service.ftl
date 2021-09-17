package ${basePackage}.service;
import com.github.pagehelper.PageInfo;
import ${basePackage}.model.entity.${modelNameUpperCamel};
import com.demo.common.mybatis.Service;
import ${basePackage}.model.vo.${modelNameUpperCamel}VO;
import java.util.List;

/**
 * Created by ${author} on ${date}.
 */
public interface I${modelNameUpperCamel}Service extends Service<${modelNameUpperCamel}> {

    int save(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO);

    int deleteByModel(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO);

    int update(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO);

    int updateDelModel(String[] ids);

    ${modelNameUpperCamel} findByModel(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO);

    List<${modelNameUpperCamel}> findModelList(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO);

    PageInfo<${modelNameUpperCamel}> selectByPage(${modelNameUpperCamel}VO ${modelNameLowerCamel}VO, Integer page, Integer size);
}
