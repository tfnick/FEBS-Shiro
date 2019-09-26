package cc.mrbird.febs.approve.controller;

import cc.mrbird.febs.approve.entity.DtoSelectOption;
import cc.mrbird.febs.common.entity.FebsResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ProcessDefinitionController {

    @GetMapping("processDefinition")
    @ResponseBody
    public List<DtoSelectOption> getAllProcessDefinition(DtoSelectOption dtoProcessDefinition) {
        List<DtoSelectOption> dtos = Lists.newArrayList();

        DtoSelectOption process1 = DtoSelectOption.builder().name("audit_process_1").value("audit_process_1").children(new ArrayList<>()).build();
        DtoSelectOption process2 = DtoSelectOption.builder().name("audit_process_2").value("audit_process_2").children(new ArrayList<>()).build();

        process1.getChildren().add(DtoSelectOption.builder().name("1").value("audit_process_1__1").children(new ArrayList<>()).build());
        process1.getChildren().add(DtoSelectOption.builder().name("2").value("audit_process_1__2").children(new ArrayList<>()).build());

        process2.getChildren().add(DtoSelectOption.builder().name("1").value("audit_process_2__1").children(new ArrayList<>()).build());

        dtos.add(process1);
        dtos.add(process2);
        return dtos;
    }

}
