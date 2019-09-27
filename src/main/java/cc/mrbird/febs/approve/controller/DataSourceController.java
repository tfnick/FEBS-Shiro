package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.DataSource;
import cc.mrbird.febs.approve.service.IDataSourceService;
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
 * @date 2019-09-27 11:47:09
 */
@Slf4j
@Validated
@Controller
public class DataSourceController extends BaseController {

    @Autowired
    private IDataSourceService dataSourceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataSource")
    @RequiresPermissions("dataSource:list")
    public String dataSourceIndex(){
        return FebsUtil.view("dataSource/dataSource");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataSource/view/{id}")
    @RequiresPermissions("dataSource:view")
    public String dataSourceView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        DataSource dataSource = this.dataSourceService.getById(id);
        resolveModel(dataSource, model, true);
        return FebsUtil.view("dataSource/dataSourceView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataSource/update/{id}")
    @RequiresPermissions("dataSource:update")
    public String dataSourceUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        DataSource dataSource = this.dataSourceService.getById(id);
        resolveModel(dataSource, model, false);
        return FebsUtil.view("dataSource/dataSourceUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataSource/add")
    @RequiresPermissions("dataSource:add")
    public String dataSourceAdd(){
        return FebsUtil.view("dataSource/dataSourceAdd");
    }



    @GetMapping("dataSource")
    @ResponseBody
    @RequiresPermissions("dataSource:list")
    public FebsResponse getAllDataSources(DataSource dataSource) {
        return new FebsResponse().success().data(dataSourceService.findDataSources(dataSource));
    }

    @GetMapping("dataSource/list")
    @ResponseBody
    @RequiresPermissions("dataSource:list")
    public FebsResponse dataSourceList(QueryRequest request, DataSource dataSource) {
        Map<String, Object> dataTable = getDataTable(this.dataSourceService.findDataSources(request, dataSource));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增DataSource")
    @PostMapping("dataSource")
    @ResponseBody
    @RequiresPermissions("dataSource:add")
    public FebsResponse addDataSource(@Valid DataSource dataSource) throws FebsException {
        try {
            this.dataSourceService.createDataSource(dataSource);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增DataSource失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除DataSource")
    @PostMapping("dataSource/delete")
    @ResponseBody
    @RequiresPermissions("dataSource:delete")
    public FebsResponse deleteDataSource(DataSource dataSource) throws FebsException {
        try {
            this.dataSourceService.deleteDataSource(dataSource);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除DataSource失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("批量删除DataSource")
    @PostMapping("dataSource/batchDelete")
    @ResponseBody
    @RequiresPermissions("dataSource:delete")
    public FebsResponse deleteDataSources(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.dataSourceService.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除DataSource失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改DataSource")
    @PostMapping("dataSource/update")
    @ResponseBody
    @RequiresPermissions("dataSource:update")
    public FebsResponse updateDataSource(DataSource dataSource) throws FebsException {
        try {
            this.dataSourceService.updateDataSource(dataSource);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改DataSource失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("dataSource/excel")
    @ResponseBody
    @RequiresPermissions("dataSource:export")
    public void export(QueryRequest queryRequest, DataSource dataSource, HttpServletResponse response) throws FebsException {
        try {
            List<DataSource> dataSources = this.dataSourceService.findDataSources(queryRequest, dataSource).getRecords();
            ExcelKit.$Export(DataSource.class, response).downXlsx(dataSources, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("dataSource/{id}")
    @ResponseBody
    @RequiresPermissions("dataSource:view")
    public DataSource getDataSource(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.dataSourceService.getById(id);
    }




    private void resolveModel(DataSource dataSource,Model model, Boolean transform) {
        model.addAttribute("dataSource", dataSource);
        if (transform && dataSource != null) {
            dataSource.transformViewFields();
        }
    }
}
