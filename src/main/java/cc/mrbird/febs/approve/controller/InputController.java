package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.approve.entity.Dataset;
import cc.mrbird.febs.approve.service.IDatasetService;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Input;
import cc.mrbird.febs.approve.service.IInputService;
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
 * @date 2019-09-27 23:59:06
 */
@Slf4j
@Validated
@Controller
public class InputController extends BaseController {

    @Autowired
    private IInputService inputService;
    @Autowired
    private IDatasetService datasetService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset/{datasetId}/input")
    @RequiresPermissions("input:list")
    public String inputIndex(@NotBlank(message = "{required}") @PathVariable String datasetId, Model model){
        Dataset dataset = this.datasetService.getById(datasetId);
        if (dataset == null) {
            return FebsUtil.view("error/500");
        }
        model.addAttribute("datasetId", datasetId);
        return FebsUtil.view("input/input");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "input/view/{id}")
    @RequiresPermissions("input:view")
    public String inputView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Input input = this.inputService.getById(id);
        resolveModel(input, model, true);
        return FebsUtil.view("input/inputView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "input/update/{id}")
    @RequiresPermissions("input:update")
    public String inputUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Input input = this.inputService.getById(id);
        resolveModel(input, model, false);
        return FebsUtil.view("input/inputUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "input/add")
    @RequiresPermissions("input:add")
    public String inputAdd(){
        return FebsUtil.view("input/inputAdd");
    }



    @GetMapping("input")
    @ResponseBody
    @RequiresPermissions("input:list")
    public FebsResponse getAllInputs(Input input) {
        return new FebsResponse().success().data(inputService.findInputs(input));
    }

    @GetMapping("input/list")
    @ResponseBody
    @RequiresPermissions("input:list")
    public FebsResponse inputList(QueryRequest request, Input input) {
        Map<String, Object> dataTable = getDataTable(this.inputService.findInputs(request, input));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Input")
    @PostMapping("input")
    @ResponseBody
    @RequiresPermissions("input:add")
    public FebsResponse addInput(@Valid Input input) throws FebsException {
        try {
            this.inputService.createInput(input);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Input失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Input")
    @PostMapping("input/delete")
    @ResponseBody
    @RequiresPermissions("input:delete")
    public FebsResponse deleteInput(Input input) throws FebsException {
        try {
            this.inputService.deleteInput(input);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Input失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("批量删除Input")
    @PostMapping("input/batchDelete")
    @ResponseBody
    @RequiresPermissions("input:delete")
    public FebsResponse deleteInputs(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.inputService.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Input失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Input")
    @PostMapping("input/update")
    @ResponseBody
    @RequiresPermissions("input:update")
    public FebsResponse updateInput(Input input) throws FebsException {
        try {
            this.inputService.updateInput(input);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Input失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("input/excel")
    @ResponseBody
    @RequiresPermissions("input:export")
    public void export(QueryRequest queryRequest, Input input, HttpServletResponse response) throws FebsException {
        try {
            List<Input> inputs = this.inputService.findInputs(queryRequest, input).getRecords();
            ExcelKit.$Export(Input.class, response).downXlsx(inputs, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("input/{id}")
    @ResponseBody
    @RequiresPermissions("input:view")
    public Input getInput(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.inputService.getById(id);
    }




    private void resolveModel(Input input,Model model, Boolean transform) {
        model.addAttribute("input", input);
        if (transform && input != null) {
            input.transformViewFields();
        }
    }
}
