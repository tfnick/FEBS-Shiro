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
 * @date 2019-09-27 11:47:02
 */
@Slf4j
@Validated
@Controller
public class DatasetController extends BaseController {

    @Autowired
    private IDatasetService datasetService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset")
    @RequiresPermissions("dataset:list")
    public String datasetIndex(){
        return FebsUtil.view("dataset/dataset");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset/view/{id}")
    @RequiresPermissions("dataset:view")
    public String datasetView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Dataset dataset = this.datasetService.getById(id);
        resolveModel(dataset, model, true);
        return FebsUtil.view("dataset/datasetView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset/update/{id}")
    @RequiresPermissions("dataset:update")
    public String datasetUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Dataset dataset = this.datasetService.getById(id);
        resolveModel(dataset, model, false);
        return FebsUtil.view("dataset/datasetUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataset/add")
    @RequiresPermissions("dataset:add")
    public String datasetAdd(){
        return FebsUtil.view("dataset/datasetAdd");
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
    @PostMapping("dataset/delete")
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

    @Log("批量删除Dataset")
    @PostMapping("dataset/batchDelete")
    @ResponseBody
    @RequiresPermissions("dataset:delete")
    public FebsResponse deleteDatasets(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.datasetService.removeByIds(Arrays.asList(toDels));
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

    @GetMapping("dataset/excel")
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

    @GetMapping("dataset/{id}")
    @ResponseBody
    @RequiresPermissions("dataset:view")
    public Dataset getDataset(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.datasetService.getById(id);
    }




    private void resolveModel(Dataset dataset,Model model, Boolean transform) {
        model.addAttribute("dataset", dataset);
        if (transform && dataset != null) {
            dataset.transformViewFields();
        }
    }
}
