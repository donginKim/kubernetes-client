package com.service.kubernetesclient.dto;

public class Provision {
    public String service_id;
    public String plan_id;
    public Object context;
    public String organization_guid;
    public String space_guid;
    public Object parameters;
    public MaintenanceInfo maintenance_info;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public String getOrganization_guid() {
        return organization_guid;
    }

    public void setOrganization_guid(String organization_guid) {
        this.organization_guid = organization_guid;
    }

    public String getSpace_guid() {
        return space_guid;
    }

    public void setSpace_guid(String space_guid) {
        this.space_guid = space_guid;
    }

    public Object getParameters() {
        return parameters;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    public MaintenanceInfo getMaintenance_info() {
        return maintenance_info;
    }

    public void setMaintenance_info(MaintenanceInfo maintenance_info) {
        this.maintenance_info = maintenance_info;
    }
}
