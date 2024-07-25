package org.example.githubrepositorylist;

import org.example.githubrepositorylist.controller.GitHubController;
import org.example.githubrepositorylist.model.RepositoryDetails;
import org.example.githubrepositorylist.service.GitHubService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GitHubControllerTest {

    @Mock
    private GitHubService gitHubService;

    @Mock
    private Model model;

    @InjectMocks
    private GitHubController gitHubController;

    public GitHubControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRepos() {
        String username = "testuser";
        List<RepositoryDetails> repos = Collections.emptyList();

        when(gitHubService.getRepositories(username)).thenReturn(repos);

        String viewName = gitHubController.getRepos(username, model);

        verify(gitHubService, times(1)).getRepositories(username);
        verify(model, times(1)).addAttribute("repos", repos);
        verify(model, times(1)).addAttribute("username", username);
        verify(model, times(1)).addAttribute("jsonUrl", "/repos/json?username=" + username);

        assertEquals("index", viewName);
    }

    @Test
    public void testGetReposJson() {
        String username = "testuser";
        List<RepositoryDetails> repos = Collections.emptyList();

        when(gitHubService.getRepositories(username)).thenReturn(repos);

        ResponseEntity<List<RepositoryDetails>> responseEntity = gitHubController.getReposJson(username);

        verify(gitHubService, times(1)).getRepositories(username);

        assertEquals(ResponseEntity.ok(repos), responseEntity);
    }
}
