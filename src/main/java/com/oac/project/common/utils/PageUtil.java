package com.oac.project.common.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageUtil<T> {

    private int code = 0;
    private String msg = "";
    private Long count;
    private List<T> data = new ArrayList<>();

}
