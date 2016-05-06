package com.capitalone.dashboard.model;


import java.util.Date;

/**
 * CollectorItem extension to store the Gogs repo url and branch.
 */
public class GogsRepo extends CollectorItem {
    private static final String REPO_URL = "repoUrl"; // https://try.gogs.io/jack/somejavacode
    private static final String BRANCH = "branch"; // master, development etc.
    private static final String USER_ID = "userID";
    private static final String PASSWORD = "password";
    private static final String LAST_UPDATE_TIME = "lastUpdate";

    public String getUserId() {
        return (String) getOptions().get(USER_ID);
    }

    public void setUserId(String userId) {
        getOptions().put(USER_ID, userId);
    }

    public String getPassword() {
        return (String) getOptions().get(PASSWORD);
    }

    public void setPassword(String password) {
        getOptions().put(PASSWORD, password);
    }


    public String getRepoUrl() {
        return (String) getOptions().get(REPO_URL);
    }

    public void setRepoUrl(String instanceUrl) {
        getOptions().put(REPO_URL, instanceUrl);
    }

    public String getBranch() {
        return (String) getOptions().get(BRANCH);
    }

    public void setBranch(String branch) {
        getOptions().put(BRANCH, branch);
    }


    public Date getLastUpdateTime() {
        Object latest = getOptions().get(LAST_UPDATE_TIME);
        return (Date) latest;
    }

    public void setLastUpdateTime(Date date) {
        getOptions().put(LAST_UPDATE_TIME, date);
    }

    public void removeLastUpdateDate() {
        getOptions().remove(LAST_UPDATE_TIME);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }

        GogsRepo gogsRepo = (GogsRepo) o;

        return getRepoUrl().equals(gogsRepo.getRepoUrl()) & getBranch().equals(gogsRepo.getBranch());
    }

    @Override
    public int hashCode() {
        return getRepoUrl().hashCode();
    }

}
