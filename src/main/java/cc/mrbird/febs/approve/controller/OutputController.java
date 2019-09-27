package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Output;
import cc.mrbird.febs.approve.service.IOutputService;
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
 *  Controller
 *
 * @author YangXiao
 * @date 2019-09-27 23:59:10
 */
@Slf4j
@Validated
@Controller
public class OutputController extends BaseController {

    @Autowired
    private IOutputService outputService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "output")
    @RequiresPermissions("output:list")
    public String outputIndex(){
        return FebsUtil.view("output/output");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "output/view/{id}")
    @RequiresPermissions("output:view")
    public String outputView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Output output = this.outputService.getById(id);
        resolveModel(output, model, true);
        return FebsUtil.view("output/outputView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "output/update/{id}")
    @RequiresPermissions("output:update")
    public String outputUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Output output = this.outputService.getById(id);
        resolveModel(output, model, false);
        return FebsUtil.view("output/outputUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "output/add")
    @RequiresPermissions("output:add")
    public String outputAdd(){
        return FebsUtil.view("output/outputAdd");
    }



    @GetMapping("output")
    @ResponseBody
    @RequiresPermissions("output:list")
    public FebsResponse getAllOutputs(Output output) {
        return new FebsResponse().success().data(outputService.findOutputs(output));
    }

    @GetMapping("output/list")
    @ResponseBody
    @RequiresPermissions("output:list")
    public FebsResponse outputList(QueryRequest request, Output output) {
        Map<String, Object> dataTable = getDataTable(this.outputService.findOutputs(request, output));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Output")
    @PostMapping("output")
    @ResponseBody
    @RequiresPermissions("output:add")
    public FebsResponse addOutput(@Valid Output output) throws FebsException {
        try {
            this.outputService.createOutput(output);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Output失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Output")
    @PostMapping("output/delete")
    @ResponseBody
    @RequiresPermissions("output:delete")
    public FebsResponse deleteOutput(Output output) throws FebsException {
        try {
            this.outputService.deleteOutput(output);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Output失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("批量删除Output")
    @PostMapping("output/batchDelete")
    @ResponseBody
    @RequiresPermissions("output:delete")
    public FebsResponse deleteOutputs(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.outputService.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Output失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Output")
    @PostMapping("output/update")
    @ResponseBody
    @RequiresPermissions("output:update")
    public FebsResponse updateOutput(Output output) throws FebsException {
        try {
            this.outputService.updateOutput(output);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Output失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("output/excel")
    @ResponseBody
    @RequiresPermissions("output:export")
    public void export(QueryRequest queryRequest, Output output, HttpServletResponse response) throws FebsException {
        try {
            List<Output> outputs = this.outputService.findOutputs(queryRequest, output).getRecords();
            ExcelKit.$Export(Output.class, response).downXlsx(outputs, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("output/{id}")
    @ResponseBody
    @RequiresPermissions("output:view")
    public Output getOutput(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.outputService.getById(id);
    }




    private void resolveModel(Output output,Model model, Boolean transform) {
        model.addAttribute("output", output);
        if (transform && output != null) {
            output.transformViewFields();
        }
    }
}
