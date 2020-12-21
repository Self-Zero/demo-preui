package com.oac.project.system.logistics.mapper;

import com.oac.project.system.logistics.domain.Logistics;
import com.oac.project.system.logistics.domain.LogisticsCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsCompanyMapper {

    /**
     * 查询全部的物流公司信息
     *
     * @return
     */
    List<LogisticsCompany> getAllLogisticsCompany();

}
