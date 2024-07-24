package org.example.githubrepositorylist.service;

import org.example.githubrepositorylist.exception.UserNotFoundException;
import org.example.githubrepositorylist.model.BranchDetails;
import org.example.githubrepositorylist.model.RepositoryDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com/users/%s/repos";
    private static final String GITHUB_BRANCHES_URL = "https://api.github.com/repos/%s/%s/branches";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<RepositoryDetails> getRepositories(String username) {
        String url = String.format(GITHUB_API_URL, username);
        List<RepositoryDetails> repositories = new ArrayList<>();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode root = objectMapper.readTree(response.getBody());
            for (JsonNode repo : root) {
                if (!repo.get("fork").asBoolean()) {
                    String repoName = repo.get("name").asText();
                    String ownerLogin = repo.get("owner").get("login").asText();
                    List<BranchDetails> branches = getBranches(ownerLogin, repoName);
                    repositories.add(new RepositoryDetails(repoName, ownerLogin, branches));
                }
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found");
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return repositories;
    }

    private List<BranchDetails> getBranches(String ownerLogin, String repoName) {
        String url = String.format(GITHUB_BRANCHES_URL, ownerLogin, repoName);
        List<BranchDetails> branches = new ArrayList<>();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JsonNode root = objectMapper.readTree(response.getBody());
            for (JsonNode branch : root) {
                String branchName = branch.get("name").asText();
                String commitSha = branch.get("commit").get("sha").asText();
                branches.add(new BranchDetails(branchName, commitSha));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return branches;
    }
}
