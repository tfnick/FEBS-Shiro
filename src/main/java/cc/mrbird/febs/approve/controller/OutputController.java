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
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2019-09-24 18:05:41
 */
@Slf4j
@Validated
@Controller
public class OutputController extends BaseController {

    @Autowired
    private IOutputService outputService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "output")
    public String outputIndex(){
        return FebsUtil.view("output/output");
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
    @GetMapping("output/delete")
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

    @PostMapping("output/excel")
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
}
