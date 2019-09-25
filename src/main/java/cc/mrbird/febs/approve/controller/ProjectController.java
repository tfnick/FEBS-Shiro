package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.approve.entity.Project;
import cc.mrbird.febs.approve.service.IProjectService;
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
 * @author MrBird
 * @date 2019-09-25 11:04:12
 */
@Slf4j
@Validated
@Controller
public class ProjectController extends BaseController {

    @Autowired
    private IProjectService projectService;

    @GetMapping("project")
    @ResponseBody
    @RequiresPermissions("project:list")
    public FebsResponse getAllProjects(Project project) {
        return new FebsResponse().success().data(projectService.findProjects(project));
    }

    @GetMapping("project/list")
    @ResponseBody
    @RequiresPermissions("project:list")
    public FebsResponse projectList(QueryRequest request, Project project) {
        Map<String, Object> dataTable = getDataTable(this.projectService.findProjects(request, project));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Project")
    @PostMapping("project")
    @ResponseBody
    @RequiresPermissions("project:add")
    public FebsResponse addProject(@Valid Project project) throws FebsException {
        try {
            this.projectService.createProject(project);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Project失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Project")
    @PostMapping("project/delete")
    @ResponseBody
    @RequiresPermissions("project:delete")
    public FebsResponse deleteProject(Project project) throws FebsException {
        try {
            this.projectService.deleteProject(project);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Project失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Project")
    @PostMapping("project/batchDelete")
    @ResponseBody
    @RequiresPermissions("project:delete")
    public FebsResponse deleteProjects(@NotBlank(message = "{required}") String ids) throws FebsException {
        try {
            String[] toDels = ids.split(StringPool.COMMA);
            if (toDels.length > 200) {
                throw new RuntimeException("禁止单次删除超过200个");
            }
            this.projectService.removeByIds(Arrays.asList(toDels));
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Project失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Project")
    @PostMapping("project/update")
    @ResponseBody
    @RequiresPermissions("project:update")
    public FebsResponse updateProject(Project project) throws FebsException {
        try {
            this.projectService.updateProject(project);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Project失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("project/excel")
    @ResponseBody
    @RequiresPermissions("project:export")
    public void export(QueryRequest queryRequest, Project project, HttpServletResponse response) throws FebsException {
        try {
            List<Project> projects = this.projectService.findProjects(queryRequest, project).getRecords();
            ExcelKit.$Export(Project.class, response).downXlsx(projects, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    //<!-- 模板新增内容 -->

    @GetMapping("project/{id}")
    @ResponseBody
    @RequiresPermissions("project:view")
    public Project getProject(@NotBlank(message = "{required}") @PathVariable String id) {
        return this.projectService.getById(id);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "project")
    @RequiresPermissions("project:list")
    public String projectIndex(){
        return FebsUtil.view("project/project");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "project/view/{id}")
    @RequiresPermissions("project:view")
    public String projectView(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Project project = this.projectService.getById(id);
        resolveModel(project, model, true);
        return FebsUtil.view("project/projectView");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "project/update/{id}")
    @RequiresPermissions("project:update")
    public String projectUpdate(@NotBlank(message = "{required}") @PathVariable String id,Model model){
        Project project = this.projectService.getById(id);
        resolveModel(project, model, false);
        return FebsUtil.view("project/projectUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "project/add")
    @RequiresPermissions("project:add")
    public String projectAdd(){
        return FebsUtil.view("project/projectAdd");
    }


    private void resolveModel(Project project,Model model, Boolean transform) {
        model.addAttribute("project", project);
        if (transform && project != null) {
            project.transformViewFields();
        }
    }
}
