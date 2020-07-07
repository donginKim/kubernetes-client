package com.service.kubernetesclient.dto;

import java.util.List;

public class Plans {
    public String name;
    public String id;
    public String description;
    public boolean free;
    public Object metadata;
    public Object schemas;

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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public Object getSchemas() {
        return schemas;
    }

    public void setSchemas(Object schemas) {
        this.schemas = schemas;
    }
}
