package com.service.kubernetesclient.controller;

import com.service.kubernetesclient.dto.Catalog;
import com.service.kubernetesclient.service.ServiceBrokerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2")
public class ServiceBrokerController {

    @Autowired
    private ServiceBrokerService serviceBrokerService;

    @ApiOperation(value = "service catalog list")
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public Catalog catalog(){
        return serviceBrokerService.catalog();
    }

    @ApiOperation(value = "service provision call")
    @RequestMapping(value = "/service_instances/{instance_id}", method = RequestMethod.PUT)
    public void provision(@PathVariable(value = "instance_id") String instance_id) throws Exception {
            serviceBrokerService.provisionResult(instance_id);
    }

}
