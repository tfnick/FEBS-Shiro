package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.RuHitRule;
import cc.mrbird.febs.approve.service.IRuHitRuleService;
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
 * @date 2019-09-24 18:08:33
 */
@Slf4j
@Validated
@Controller
public class RuHitRuleController extends BaseController {

    @Autowired
    private IRuHitRuleService ruHitRuleService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "ruHitRule")
    public String ruHitRuleIndex(){
        return FebsUtil.view("ruHitRule/ruHitRule");
    }

    @GetMapping("ruHitRule")
    @ResponseBody
    @RequiresPermissions("ruHitRule:list")
    public FebsResponse getAllRuHitRules(RuHitRule ruHitRule) {
        return new FebsResponse().success().data(ruHitRuleService.findRuHitRules(ruHitRule));
    }

    @GetMapping("ruHitRule/list")
    @ResponseBody
    @RequiresPermissions("ruHitRule:list")
    public FebsResponse ruHitRuleList(QueryRequest request, RuHitRule ruHitRule) {
        Map<String, Object> dataTable = getDataTable(this.ruHitRuleService.findRuHitRules(request, ruHitRule));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增RuHitRule")
    @PostMapping("ruHitRule")
    @ResponseBody
    @RequiresPermissions("ruHitRule:add")
    public FebsResponse addRuHitRule(@Valid RuHitRule ruHitRule) throws FebsException {
        try {
            this.ruHitRuleService.createRuHitRule(ruHitRule);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增RuHitRule失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除RuHitRule")
    @GetMapping("ruHitRule/delete")
    @ResponseBody
    @RequiresPermissions("ruHitRule:delete")
    public FebsResponse deleteRuHitRule(RuHitRule ruHitRule) throws FebsException {
        try {
            this.ruHitRuleService.deleteRuHitRule(ruHitRule);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除RuHitRule失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改RuHitRule")
    @PostMapping("ruHitRule/update")
    @ResponseBody
    @RequiresPermissions("ruHitRule:update")
    public FebsResponse updateRuHitRule(RuHitRule ruHitRule) throws FebsException {
        try {
            this.ruHitRuleService.updateRuHitRule(ruHitRule);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改RuHitRule失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("ruHitRule/excel")
    @ResponseBody
    @RequiresPermissions("ruHitRule:export")
    public void export(QueryRequest queryRequest, RuHitRule ruHitRule, HttpServletResponse response) throws FebsException {
        try {
            List<RuHitRule> ruHitRules = this.ruHitRuleService.findRuHitRules(queryRequest, ruHitRule).getRecords();
            ExcelKit.$Export(RuHitRule.class, response).downXlsx(ruHitRules, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
