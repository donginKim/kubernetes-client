package com.service.kubernetesclient.dto;

public class Requested {
    private String cpu;
    private String memory;
    private String pods;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPods() {
        return pods;
    }

    public void setPods(String pods) {
        this.pods = pods;
    }
}
