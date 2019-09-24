package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.RuApply;
import cc.mrbird.febs.approve.service.IRuApplyService;
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
 * @date 2019-09-24 18:00:32
 */
@Slf4j
@Validated
@Controller
public class RuApplyController extends BaseController {

    @Autowired
    private IRuApplyService ruApplyService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "ruApply")
    public String ruApplyIndex(){
        return FebsUtil.view("ruApply/ruApply");
    }

    @GetMapping("ruApply")
    @ResponseBody
    @RequiresPermissions("ruApply:list")
    public FebsResponse getAllRuApplys(RuApply ruApply) {
        return new FebsResponse().success().data(ruApplyService.findRuApplys(ruApply));
    }

    @GetMapping("ruApply/list")
    @ResponseBody
    @RequiresPermissions("ruApply:list")
    public FebsResponse ruApplyList(QueryRequest request, RuApply ruApply) {
        Map<String, Object> dataTable = getDataTable(this.ruApplyService.findRuApplys(request, ruApply));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增RuApply")
    @PostMapping("ruApply")
    @ResponseBody
    @RequiresPermissions("ruApply:add")
    public FebsResponse addRuApply(@Valid RuApply ruApply) throws FebsException {
        try {
            this.ruApplyService.createRuApply(ruApply);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增RuApply失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除RuApply")
    @GetMapping("ruApply/delete")
    @ResponseBody
    @RequiresPermissions("ruApply:delete")
    public FebsResponse deleteRuApply(RuApply ruApply) throws FebsException {
        try {
            this.ruApplyService.deleteRuApply(ruApply);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除RuApply失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改RuApply")
    @PostMapping("ruApply/update")
    @ResponseBody
    @RequiresPermissions("ruApply:update")
    public FebsResponse updateRuApply(RuApply ruApply) throws FebsException {
        try {
            this.ruApplyService.updateRuApply(ruApply);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改RuApply失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("ruApply/excel")
    @ResponseBody
    @RequiresPermissions("ruApply:export")
    public void export(QueryRequest queryRequest, RuApply ruApply, HttpServletResponse response) throws FebsException {
        try {
            List<RuApply> ruApplys = this.ruApplyService.findRuApplys(queryRequest, ruApply).getRecords();
            ExcelKit.$Export(RuApply.class, response).downXlsx(ruApplys, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
