package com.oac.project.system.distribution.vo;

import lombok.Data;


@Data
public class DistributionVO {

    private Long orderId;
    private String addressName;

    private String customerName;

    private String orderName;

    private String carpentryName;

    private String painterName;

    private String primerName;

    private String created;
}
