package cc.mrbird.febs.approve.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class DtoSelectOption {

    String name;
    String value;


    List<DtoSelectOption> children;

}
