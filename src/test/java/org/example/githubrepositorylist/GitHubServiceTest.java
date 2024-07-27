package org.example.githubrepositorylist;

import org.example.githubrepositorylist.exception.UserNotFoundException;
import org.example.githubrepositorylist.model.BranchDetails;
import org.example.githubrepositorylist.model.RepositoryDetails;
import org.example.githubrepositorylist.service.GitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GitHubServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GitHubService gitHubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRepositoriesSuccess() throws Exception {
        String username = "MateuszMaciaszczyk";
        List<RepositoryDetails> repositories = gitHubService.getRepositories(username);

        assertNotNull(repositories);
        assertFalse(repositories.isEmpty());
        assertEquals(10, repositories.size());
        assertEquals("CRUD-Application", repositories.get(0).name());
        assertEquals("MateuszMaciaszczyk", repositories.get(0).ownerLogin());
        assertEquals(2, repositories.get(0).branches().size());
        assertEquals("main", repositories.get(0).branches().get(0).name());
    }

    @Test
    void testGetRepositoriesUserNotFound() {
        String username = "MateuszMaciaszczykk";

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            gitHubService.getRepositories(username);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
