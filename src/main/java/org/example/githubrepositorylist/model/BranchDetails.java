package org.example.githubrepositorylist.model;

public class BranchDetails {
    private String name;
    private String commitSha;

    // Constructors, getters and setters

    public BranchDetails(String name, String commitSha) {
        this.name = name;
        this.commitSha = commitSha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }
}
