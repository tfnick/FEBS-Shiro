package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Process;
import cc.mrbird.febs.approve.service.IProcessService;
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
 * @date 2019-09-25 23:37:42
 */
@Slf4j
@Validated
@Controller
public class ProcessController extends BaseController {

    @Autowired
    private IProcessService processService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "process")
    @RequiresPermissions("process:list")
    public String processIndex(){
        return FebsUtil.view("process/process");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "process/view/{id}")
    @RequiresPermissions("process:view")
    public String processView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Process process = this.processService.getById(id);
        resolveModel(process, model, true);
        return FebsUtil.view("process/processView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "process/update/{id}")
    @RequiresPermissions("process:update")
    public String processUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Process process = this.processService.getById(id);
        resolveModel(process, model, false);
        return FebsUtil.view("process/processUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "process/add")
    @RequiresPermissions("process:add")
    public String processAdd(){
        return FebsUtil.view("process/processAdd");
    }



    @GetMapping("process")
    @ResponseBody
    @RequiresPermissions("process:list")
    public FebsResponse getAllProcesss(Process process) {
        return new FebsResponse().success().data(processService.findProcesss(process));
    }

    @GetMapping("process/list")
    @ResponseBody
    @RequiresPermissions("process:list")
    public FebsResponse processList(QueryRequest request, Process process) {
        Map<String, Object> dataTable = getDataTable(this.processService.findProcesss(request, process));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Process")
    @PostMapping("process")
    @ResponseBody
    @RequiresPermissions("process:add")
    public FebsResponse addProcess(@Valid Process process) throws FebsException {
        try {
            this.processService.createProcess(process);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Process失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Process")
    @PostMapping("process/delete")
    @ResponseBody
    @RequiresPermissions("process:delete")
    public FebsResponse deleteProcess(Process process) throws FebsException {
        try {
            this.processService.deleteProcess(process);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Process失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("批量删除Process")
    @PostMapping("process/batchDelete")
    @ResponseBody
    @RequiresPermissions("process:delete")
    public FebsResponse deleteProcesss(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.processService.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Process失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Process")
    @PostMapping("process/update")
    @ResponseBody
    @RequiresPermissions("process:update")
    public FebsResponse updateProcess(Process process) throws FebsException {
        try {
            this.processService.updateProcess(process);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Process失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("process/excel")
    @ResponseBody
    @RequiresPermissions("process:export")
    public void export(QueryRequest queryRequest, Process process, HttpServletResponse response) throws FebsException {
        try {
            List<Process> processs = this.processService.findProcesss(queryRequest, process).getRecords();
            ExcelKit.$Export(Process.class, response).downXlsx(processs, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("process/{id}")
    @ResponseBody
    @RequiresPermissions("process:view")
    public Process getProcess(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.processService.getById(id);
    }




    private void resolveModel(Process process,Model model, Boolean transform) {
        model.addAttribute("process", process);
        if (transform && process != null) {
            process.transformViewFields();
        }
    }
}
