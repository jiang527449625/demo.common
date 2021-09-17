package ${basePackage}.controller;
import com.demo.common.mybatis.Result;
import com.demo.common.mybatis.ResultGenerator;
import ${basePackage}.model.entity.${modelNameUpperCamel};
import ${basePackage}.model.vo.${modelNameUpperCamel}VO;
import ${basePackage}.service.I${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.annotation.Resource;
import java.util.List;
import io.swagger.annotations.Api;

/**
* Created by ${author} on ${date}.
*/
@Api(tags={"${modelNameUpperCamel}"})
@RestController
@RequestMapping("/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private I${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "${modelNameUpperCamel}-新增", notes = "/${baseRequestMapping}/insert")
    @PostMapping("/insert")
    public Result add(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        Result result = ResultGenerator.genFailResult();
        int count = ${modelNameLowerCamel}Service.save(${modelNameLowerCamel}VO);
        if(count > 0){
            result = ResultGenerator.genSuccessResult();
        }
        return result;
    }

    @ApiOperation(value = "${modelNameUpperCamel}-删除", notes = "/${baseRequestMapping}/delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        Result result = ResultGenerator.genFailResult();
        int count = ${modelNameLowerCamel}Service.deleteByModel(${modelNameLowerCamel}VO);
        if(count > 0){
            result = ResultGenerator.genSuccessResult();
        }
        return result;
    }

    @ApiOperation(value = "${modelNameUpperCamel}-修改", notes = "/${baseRequestMapping}/update")
    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        Result result = ResultGenerator.genFailResult();
        int count = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel}VO);
        if(count > 0){
            result = ResultGenerator.genSuccessResult();
        }
        return result;
    }

    @ApiOperation(value = "${modelNameUpperCamel}-批量假删", notes = "/${baseRequestMapping}/deleteByids")
    @PostMapping("/deleteByids")
    public Result deleteByids(@RequestBody String[] ids) {
        Result result = ResultGenerator.genFailResult();
        int count = ${modelNameLowerCamel}Service.updateDelModel(ids);
        if(count == ids.length){
            result = ResultGenerator.genSuccessResult();
        }
        return result;
    }

    @ApiOperation(value = "${modelNameUpperCamel}-查询实体", notes = "/${baseRequestMapping}/getModel")
    @PostMapping("/getModel")
    public Result getModel(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findByModel(${modelNameLowerCamel}VO);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @ApiOperation(value = "${modelNameUpperCamel}-查询集合", notes = "/${baseRequestMapping}/getList")
    @PostMapping("/getList")
    public Result getList(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findModelList(${modelNameLowerCamel}VO);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation(value = "${modelNameUpperCamel}-分页查询", notes = "/${baseRequestMapping}/pageList")
    @PostMapping("/pageList")
    public Result pageList(@RequestBody ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO) {
        Integer page=${modelNameLowerCamel}VO.getPageNum();
        Integer size=${modelNameLowerCamel}VO.getPageSize();
        PageInfo<${modelNameUpperCamel}> pageInfo = ${modelNameLowerCamel}Service.selectByPage(${modelNameLowerCamel}VO, page,  size);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}
