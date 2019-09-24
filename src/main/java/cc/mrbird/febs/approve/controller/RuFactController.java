package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.RuFact;
import cc.mrbird.febs.approve.service.IRuFactService;
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
 * @date 2019-09-24 18:00:42
 */
@Slf4j
@Validated
@Controller
public class RuFactController extends BaseController {

    @Autowired
    private IRuFactService ruFactService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "ruFact")
    public String ruFactIndex(){
        return FebsUtil.view("ruFact/ruFact");
    }

    @GetMapping("ruFact")
    @ResponseBody
    @RequiresPermissions("ruFact:list")
    public FebsResponse getAllRuFacts(RuFact ruFact) {
        return new FebsResponse().success().data(ruFactService.findRuFacts(ruFact));
    }

    @GetMapping("ruFact/list")
    @ResponseBody
    @RequiresPermissions("ruFact:list")
    public FebsResponse ruFactList(QueryRequest request, RuFact ruFact) {
        Map<String, Object> dataTable = getDataTable(this.ruFactService.findRuFacts(request, ruFact));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增RuFact")
    @PostMapping("ruFact")
    @ResponseBody
    @RequiresPermissions("ruFact:add")
    public FebsResponse addRuFact(@Valid RuFact ruFact) throws FebsException {
        try {
            this.ruFactService.createRuFact(ruFact);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增RuFact失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除RuFact")
    @GetMapping("ruFact/delete")
    @ResponseBody
    @RequiresPermissions("ruFact:delete")
    public FebsResponse deleteRuFact(RuFact ruFact) throws FebsException {
        try {
            this.ruFactService.deleteRuFact(ruFact);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除RuFact失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改RuFact")
    @PostMapping("ruFact/update")
    @ResponseBody
    @RequiresPermissions("ruFact:update")
    public FebsResponse updateRuFact(RuFact ruFact) throws FebsException {
        try {
            this.ruFactService.updateRuFact(ruFact);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改RuFact失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("ruFact/excel")
    @ResponseBody
    @RequiresPermissions("ruFact:export")
    public void export(QueryRequest queryRequest, RuFact ruFact, HttpServletResponse response) throws FebsException {
        try {
            List<RuFact> ruFacts = this.ruFactService.findRuFacts(queryRequest, ruFact).getRecords();
            ExcelKit.$Export(RuFact.class, response).downXlsx(ruFacts, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
