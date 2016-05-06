package com.capitalone.dashboard.collector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Bean to hold settings specific to the Gogs collector.
 */
@Component
@ConfigurationProperties(prefix = "gogs")
public class GogsSettings {

    private String cron;
//    private String host;
//    private String key;
    private int commitThresholdDays;

//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
    public int getCommitThresholdDays() {
        return commitThresholdDays;
    }

    public void setCommitThresholdDays(int commitThresholdDays) {
        this.commitThresholdDays = commitThresholdDays;
    }
}
