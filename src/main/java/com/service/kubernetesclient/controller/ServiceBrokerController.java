package com.service.kubernetesclient.controller;

import com.service.kubernetesclient.dto.Catalog;
import com.service.kubernetesclient.dto.Provision;
import com.service.kubernetesclient.service.ServiceBrokerService;
import io.kubernetes.client.openapi.ApiException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v2")
public class ServiceBrokerController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceBrokerController.class);

    @Autowired
    private ServiceBrokerService serviceBrokerService;

    @ApiOperation(value = "service catalog list")
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public Catalog catalog(){
        return serviceBrokerService.catalog();
    }

    @RequestMapping(value = "/service_instances/{instance_id}", method = RequestMethod.GET)
    public void provision(@PathVariable(value = "instance_id") String instance_id)
            throws Exception {
        try {
            serviceBrokerService.provisionResult(instance_id);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

}
