package com.caliber.flatbudget.models.internal.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {
    private String year;
    private String month;
    private String name;
    private Integer mainOrder;
}
