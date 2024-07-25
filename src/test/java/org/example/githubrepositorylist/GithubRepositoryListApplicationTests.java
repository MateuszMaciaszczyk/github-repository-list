package org.example.githubrepositorylist;

import org.example.githubrepositorylist.controller.GitHubController;
import org.example.githubrepositorylist.service.GitHubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubRepositoryListApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GitHubController gitHubController;

    @Autowired
    private GitHubService gitHubService;

    @Test
    public void contextLoads() {
        assert gitHubController != null;
        assert gitHubService != null;
    }

    @Test
    public void testGetReposHtml() throws Exception {
        mockMvc.perform(get("/repos")
                        .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("repos"))
                .andExpect(model().attributeExists("username"));
    }

    @Test
    public void testGetReposJson() throws Exception {
        mockMvc.perform(get("/repos/json")
                        .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
