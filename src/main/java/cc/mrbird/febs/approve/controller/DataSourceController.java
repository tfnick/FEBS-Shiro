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
 * @date 2019-09-24 18:05:37
 */
@Slf4j
@Validated
@Controller
public class DataSourceController extends BaseController {

    @Autowired
    private IDataSourceService dataSourceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataSource")
    public String dataSourceIndex(){
        return FebsUtil.view("dataSource/dataSource");
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
    @GetMapping("dataSource/delete")
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

    @PostMapping("dataSource/excel")
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
}
