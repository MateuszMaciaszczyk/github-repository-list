package org.example.githubrepositorylist.model;

import java.util.List;

public class RepositoryDetails {
    private String name;
    private String ownerLogin;
    private List<BranchDetails> branches;

    // Constructors, getters and setters

    public RepositoryDetails(String name, String ownerLogin, List<BranchDetails> branches) {
        this.name = name;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<BranchDetails> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDetails> branches) {
        this.branches = branches;
    }
}
