package com.service.kubernetesclient.dto;

import java.util.List;

public class Catalog {
    private String name;
    private String id;
    private String description;
    private String[] tags;
    private boolean bindable;
    private boolean instances_retrievable;
    private boolean bindings_retrievable;
    private boolean allow_context_updates;
    private Metadata metadata;
    private boolean plan_updateable;
    private List<Plans> plans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public boolean isBindable() {
        return bindable;
    }

    public void setBindable(boolean bindable) {
        this.bindable = bindable;
    }

    public boolean isInstances_retrievable() {
        return instances_retrievable;
    }

    public void setInstances_retrievable(boolean instances_retrievable) {
        this.instances_retrievable = instances_retrievable;
    }

    public boolean isBindings_retrievable() {
        return bindings_retrievable;
    }

    public void setBindings_retrievable(boolean bindings_retrievable) {
        this.bindings_retrievable = bindings_retrievable;
    }

    public boolean isAllow_context_updates() {
        return allow_context_updates;
    }

    public void setAllow_context_updates(boolean allow_context_updates) {
        this.allow_context_updates = allow_context_updates;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public boolean isPlan_updateable() {
        return plan_updateable;
    }

    public void setPlan_updateable(boolean plan_updateable) {
        this.plan_updateable = plan_updateable;
    }

    public List<Plans> getPlans() {
        return plans;
    }

    public void setPlans(List<Plans> plans) {
        this.plans = plans;
    }
}
