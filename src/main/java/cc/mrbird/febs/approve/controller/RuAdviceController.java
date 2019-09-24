package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.RuAdvice;
import cc.mrbird.febs.approve.service.IRuAdviceService;
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
 * @date 2019-09-24 18:00:30
 */
@Slf4j
@Validated
@Controller
public class RuAdviceController extends BaseController {

    @Autowired
    private IRuAdviceService ruAdviceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "ruAdvice")
    public String ruAdviceIndex(){
        return FebsUtil.view("ruAdvice/ruAdvice");
    }

    @GetMapping("ruAdvice")
    @ResponseBody
    @RequiresPermissions("ruAdvice:list")
    public FebsResponse getAllRuAdvices(RuAdvice ruAdvice) {
        return new FebsResponse().success().data(ruAdviceService.findRuAdvices(ruAdvice));
    }

    @GetMapping("ruAdvice/list")
    @ResponseBody
    @RequiresPermissions("ruAdvice:list")
    public FebsResponse ruAdviceList(QueryRequest request, RuAdvice ruAdvice) {
        Map<String, Object> dataTable = getDataTable(this.ruAdviceService.findRuAdvices(request, ruAdvice));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增RuAdvice")
    @PostMapping("ruAdvice")
    @ResponseBody
    @RequiresPermissions("ruAdvice:add")
    public FebsResponse addRuAdvice(@Valid RuAdvice ruAdvice) throws FebsException {
        try {
            this.ruAdviceService.createRuAdvice(ruAdvice);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增RuAdvice失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除RuAdvice")
    @GetMapping("ruAdvice/delete")
    @ResponseBody
    @RequiresPermissions("ruAdvice:delete")
    public FebsResponse deleteRuAdvice(RuAdvice ruAdvice) throws FebsException {
        try {
            this.ruAdviceService.deleteRuAdvice(ruAdvice);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除RuAdvice失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改RuAdvice")
    @PostMapping("ruAdvice/update")
    @ResponseBody
    @RequiresPermissions("ruAdvice:update")
    public FebsResponse updateRuAdvice(RuAdvice ruAdvice) throws FebsException {
        try {
            this.ruAdviceService.updateRuAdvice(ruAdvice);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改RuAdvice失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("ruAdvice/excel")
    @ResponseBody
    @RequiresPermissions("ruAdvice:export")
    public void export(QueryRequest queryRequest, RuAdvice ruAdvice, HttpServletResponse response) throws FebsException {
        try {
            List<RuAdvice> ruAdvices = this.ruAdviceService.findRuAdvices(queryRequest, ruAdvice).getRecords();
            ExcelKit.$Export(RuAdvice.class, response).downXlsx(ruAdvices, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
