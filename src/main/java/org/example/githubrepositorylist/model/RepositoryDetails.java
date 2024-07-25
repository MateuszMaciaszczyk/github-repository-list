package org.example.githubrepositorylist.model;

import java.util.List;

public record RepositoryDetails (String name, String ownerLogin, List<BranchDetails> branches) {}
