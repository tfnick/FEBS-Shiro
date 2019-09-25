package ${basePackage}.${controllerPackage};

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.ui.Model;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Validated
@Controller
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;

    @GetMapping(FebsConstant.VIEW_PREFIX + "${className?uncap_first}")
    @RequiresPermissions("${className?uncap_first}:list")
    public String ${className?uncap_first}Index(){
        return FebsUtil.view("${className?uncap_first}/${className?uncap_first}");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "${className?uncap_first}/view/{id}")
    @RequiresPermissions("${className?uncap_first}:view")
    public String ${className?uncap_first}View(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        ${className} ${className?uncap_first} = this.${className?uncap_first}Service.getById(id);
        resolveModel(${className?uncap_first}, model, true);
        return FebsUtil.view("${className?uncap_first}/${className?uncap_first}View");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "${className?uncap_first}/update/{id}")
    @RequiresPermissions("${className?uncap_first}:update")
    public String ${className?uncap_first}Update(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        ${className} ${className?uncap_first} = this.${className?uncap_first}Service.getById(id);
        resolveModel(${className?uncap_first}, model, false);
        return FebsUtil.view("${className?uncap_first}/${className?uncap_first}Update");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "${className?uncap_first}/add")
    @RequiresPermissions("${className?uncap_first}:add")
    public String ${className?uncap_first}Add(){
        return FebsUtil.view("${className?uncap_first}/${className?uncap_first}Add");
    }



    @GetMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public FebsResponse getAll${className}s(${className} ${className?uncap_first}) {
        return new FebsResponse().success().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
    }

    @GetMapping("${className?uncap_first}/list")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public FebsResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
        Map<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增${className}")
    @PostMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:add")
    public FebsResponse add${className}(@Valid ${className} ${className?uncap_first}) throws FebsException {
        try {
            this.${className?uncap_first}Service.create${className}(${className?uncap_first});
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增${className}失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除${className}")
    @PostMapping("${className?uncap_first}/delete")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:delete")
    public FebsResponse delete${className}(${className} ${className?uncap_first}) throws FebsException {
        try {
            this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除${className}失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("批量删除${className}")
    @PostMapping("${className?uncap_first}/batchDelete")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:delete")
    public FebsResponse delete${className}s(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.${className?uncap_first}Service.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除${className}失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改${className}")
    @PostMapping("${className?uncap_first}/update")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:update")
    public FebsResponse update${className}(${className} ${className?uncap_first}) throws FebsException {
        try {
            this.${className?uncap_first}Service.update${className}(${className?uncap_first});
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改${className}失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("${className?uncap_first}/excel")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:export")
    public void export(QueryRequest queryRequest, ${className} ${className?uncap_first}, HttpServletResponse response) throws FebsException {
        try {
            List<${className}> ${className?uncap_first}s = this.${className?uncap_first}Service.find${className}s(queryRequest, ${className?uncap_first}).getRecords();
            ExcelKit.$Export(${className}.class, response).downXlsx(${className?uncap_first}s, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("${className?uncap_first}/{id}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:view")
    public ${className} get${className}(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.${className?uncap_first}Service.getById(id);
    }




    private void resolveModel(${className} ${className?uncap_first},Model model, Boolean transform) {
        model.addAttribute("${className?uncap_first}", ${className?uncap_first});
        if (transform && ${className?uncap_first} != null) {
            ${className?uncap_first}.transformViewFields();
        }
    }
}
