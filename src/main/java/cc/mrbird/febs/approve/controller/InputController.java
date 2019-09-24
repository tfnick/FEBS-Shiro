package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Input;
import cc.mrbird.febs.approve.service.IInputService;
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
 * @date 2019-09-24 18:05:43
 */
@Slf4j
@Validated
@Controller
public class InputController extends BaseController {

    @Autowired
    private IInputService inputService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "input")
    public String inputIndex(){
        return FebsUtil.view("input/input");
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
    @GetMapping("input/delete")
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

    @PostMapping("input/excel")
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
}
