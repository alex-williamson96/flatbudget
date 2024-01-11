package com.caliber.flatbudget.dtos.account;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountOverviewDto {
    private Long id;
    private String name;
    private Integer dollar;
    private Integer cents;
    private Boolean onBudget;
    private Integer orderPosition;
}
