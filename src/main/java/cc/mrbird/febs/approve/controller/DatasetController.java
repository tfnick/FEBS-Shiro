package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Dataset;
import cc.mrbird.febs.approve.service.IDatasetService;
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
 * @date 2019-09-24 18:05:39
 */
@Slf4j
@Validated
@Controller
public class DatasetController extends BaseController {

    @Autowired
    private IDatasetService datasetService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset")
    public String datasetIndex(){
        return FebsUtil.view("dataset/dataset");
    }

    @GetMapping("dataset")
    @ResponseBody
    @RequiresPermissions("dataset:list")
    public FebsResponse getAllDatasets(Dataset dataset) {
        return new FebsResponse().success().data(datasetService.findDatasets(dataset));
    }

    @GetMapping("dataset/list")
    @ResponseBody
    @RequiresPermissions("dataset:list")
    public FebsResponse datasetList(QueryRequest request, Dataset dataset) {
        Map<String, Object> dataTable = getDataTable(this.datasetService.findDatasets(request, dataset));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Dataset")
    @PostMapping("dataset")
    @ResponseBody
    @RequiresPermissions("dataset:add")
    public FebsResponse addDataset(@Valid Dataset dataset) throws FebsException {
        try {
            this.datasetService.createDataset(dataset);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Dataset失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Dataset")
    @GetMapping("dataset/delete")
    @ResponseBody
    @RequiresPermissions("dataset:delete")
    public FebsResponse deleteDataset(Dataset dataset) throws FebsException {
        try {
            this.datasetService.deleteDataset(dataset);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Dataset失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Dataset")
    @PostMapping("dataset/update")
    @ResponseBody
    @RequiresPermissions("dataset:update")
    public FebsResponse updateDataset(Dataset dataset) throws FebsException {
        try {
            this.datasetService.updateDataset(dataset);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Dataset失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("dataset/excel")
    @ResponseBody
    @RequiresPermissions("dataset:export")
    public void export(QueryRequest queryRequest, Dataset dataset, HttpServletResponse response) throws FebsException {
        try {
            List<Dataset> datasets = this.datasetService.findDatasets(queryRequest, dataset).getRecords();
            ExcelKit.$Export(Dataset.class, response).downXlsx(datasets, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
