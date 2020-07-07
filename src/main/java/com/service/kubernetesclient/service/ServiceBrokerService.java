package com.service.kubernetesclient.service;


import com.service.kubernetesclient.dto.*;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ServiceBrokerService{

    @Value("${k8s.url:}") private String url;
    @Value("${k8s.cluster.path:}") private String clusterPath;
    @Value("${k8s.validateSSL:}") private Boolean validateSSL;
    @Value("${rancher.api.token:}") private String rancherApiToken;

    private ApiClient customApiClient(String cluster){
        ApiClient client = Config.fromToken(String.format("%s/%s/%s", url, clusterPath, cluster), rancherApiToken, validateSSL);
        client.setDebugging(true);
        Configuration.setDefaultApiClient(client);
        return client;
    }

    public void provisionResult(String instance_id) throws Exception {
        customApiClient(instance_id);
        AppsV1Api api = new AppsV1Api();

        V1Deployment deployment = new V1Deployment();

        V1ObjectMeta objectMeta = new V1ObjectMeta();
        V1LabelSelector labelSelector = new V1LabelSelector();
        V1PodTemplateSpec podTemplateSpec = new V1PodTemplateSpec();
        V1PodSpec podSpec = new V1PodSpec();
        List<V1Container> containers = new ArrayList<V1Container>();
        List<V1ContainerPort> containerPorts = new ArrayList<V1ContainerPort>();
        V1Container container = new V1Container();
        V1ContainerPort port = new V1ContainerPort();
        V1DeploymentSpec deploymentSpec = new V1DeploymentSpec();

        port.setContainerPort(80);
        containerPorts.add(port);

        container.setName("nginx");
        container.setImage("nginx:1.14.2");
        container.setPorts(containerPorts);

        containers.add(container);

        podSpec.setContainers(containers);

        Map<String, String> labels = new HashMap<>();
        labels.put("app", "nginx");

        labelSelector.setMatchLabels(labels);

        objectMeta.setName("nginx-deployment");
        objectMeta.setLabels(labels);

        podTemplateSpec.setSpec(podSpec);
        podTemplateSpec.setMetadata(objectMeta);

        deploymentSpec.setReplicas(3);
        deploymentSpec.setSelector(labelSelector);
        deploymentSpec.setTemplate(podTemplateSpec);

        deployment.setApiVersion("apps/v1");
        deployment.setKind("Deployment");
        deployment.setMetadata(objectMeta);
        deployment.setSpec(deploymentSpec);

        api.createNamespacedDeployment("default", deployment, null, null, null);

    }

    public Catalog catalog(){
        String[] tags_1 = {"no-sql", "relational"};

        return addCatalog_1(tags_1);
    }

    public Catalog addCatalog_1(String[] tags){
        List<Plans> plans = new ArrayList<Plans>();
        plans.add(addPlans_1());
        plans.add(addPlans_2());
        Catalog catalog = new Catalog();
        catalog.setName("fake-service");
        catalog.setBindable(true);
        catalog.setId("acb56d7c-XXXX-XXXX-XXXX-feb140a59a66");
        catalog.setDescription("A fake service.");
        catalog.setTags(tags);
        catalog.setInstances_retrievable(true);
        catalog.setBindings_retrievable(true);
        catalog.setAllow_context_updates(true);
        catalog.setMetadata(addMetadata());
        catalog.setPlan_updateable(true);
        catalog.setPlans(plans);

        return catalog;
    }

    public Metadata addMetadata(){
        Metadata metadata = new Metadata();
        JSONObject provider = new JSONObject();
        JSONObject listing = new JSONObject();
        provider.put("name", "The name");
        listing.put("imageUrl", "http://example.com/cat.gif");
        listing.put("blurb", "Add a blurb here");
        listing.put("longDescription", "A long time ago, in a galaxy far far away...");
        metadata.setProvider(provider);
        metadata.setListing(listing);
        metadata.setDisplayName("The Fake Service Broker");

        return metadata;
    }

    public Plans addPlans_1(){

        String[] bullets = {"Shared fake server", "5 TB storage", "40 concurrent connections"};

        JSONObject metadata = new JSONObject();
        JSONArray costs = new JSONArray();

        costs.add(setCost(99.0, "MONTHLY"));
        costs.add(setCost(0.99, "1GB of messages over 20GB"));

        metadata.put("max_storage_tb", 5);
        metadata.put("costs", costs);
        metadata.put("bullets", bullets);

        JSONObject schemas = new JSONObject();
        JSONObject service_instance = new JSONObject();
        JSONObject service_binding = new JSONObject();
        JSONObject maintenance_info = new JSONObject();

        service_instance.put("create", setCreate("http://json-schema.org/draft-04/schema#", "Billing account number used to charge use of shared fake server."));
        service_instance.put("update", setUpdate("http://json-schema.org/draft-05/schema#", "Billing account number used to charge use of shared fake server."));
        service_binding.put("create", setCreate("http://json-schema.org/draft-06/schema#", "Billing account number used to charge use of shared fake server."));

        maintenance_info.put("version","2.1.1+abcdef");
        maintenance_info.put("description","OS image update.\nExpect downtime.");

        schemas.put("service_instance", service_instance);
        schemas.put("service_binding", service_binding);
        schemas.put("maintenance_info", maintenance_info);

        Plans plan_1 = new Plans();
        plan_1.setName("fake-plan-1");
        plan_1.setId("d3031751-XXXX-XXXX-XXXX-a42377d3320e");
        plan_1.setDescription("Shared fake Server, 5tb persistent disk, 40 max concurrent connections.");
        plan_1.setFree(false);
        plan_1.setMetadata(metadata);
        plan_1.setSchemas(schemas);

        return plan_1;
    }

    public Plans addPlans_2(){

        String[] bullets = { "40 concurrent connections"};

        JSONObject metadata = new JSONObject();
        JSONArray costs = new JSONArray();

        costs.add(setCost(199.0, "MONTHLY"));
        costs.add(setCost(0.99, "1GB of messages over 20GB"));

        metadata.put("max_storage_tb", 5);
        metadata.put("costs", costs);
        metadata.put("bullets", bullets);

        Plans plan_2 = new Plans();
        plan_2.setName("fake-plan-2");
        plan_2.setId("0f4008b5-XXXX-XXXX-XXXX-dace631cd648");
        plan_2.setDescription("Shared fake Server, 5tb persistent disk, 40 max concurrent connections. 100 async.");
        plan_2.setFree(false);
        plan_2.setMetadata(metadata);

        return plan_2;
    }

    public JSONObject setCost(Double amounts, String unit){
        JSONObject cost = new JSONObject();
        JSONObject amount = new JSONObject();
        amount.put("usd", amounts);
        cost.put("amount", amount);
        cost.put("unit", unit);

        return cost;
    }

    public JSONObject setCreate(String schema, String description){
        JSONObject creater = new JSONObject();
        JSONObject parameters = new JSONObject();
        JSONObject properties = new JSONObject();
        JSONObject billing_account = new JSONObject();

        billing_account.put("description", description);
        billing_account.put("type","string");
        parameters.put("$schema", schema);
        parameters.put("type", "object");
        parameters.put("properties", properties);
        properties.put("billing-account", billing_account);
        creater.put("parameters", parameters);

        return creater;
    }

    public JSONObject setUpdate(String schema, String description){
        JSONObject updater = new JSONObject();
        JSONObject parameters = new JSONObject();
        JSONObject properties = new JSONObject();
        JSONObject billing_account = new JSONObject();

        billing_account.put("description",description);
        billing_account.put("type","string");
        parameters.put("$schema", schema);
        parameters.put("type", "object");
        parameters.put("properties", properties);
        properties.put("billing-account", billing_account);
        updater.put("parameters", parameters);

        return updater;
    }

}
