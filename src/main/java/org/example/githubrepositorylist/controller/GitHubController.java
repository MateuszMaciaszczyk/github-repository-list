package org.example.githubrepositorylist.controller;

import org.example.githubrepositorylist.model.RepositoryDetails;
import org.example.githubrepositorylist.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/repos")
    public String getRepos(@RequestParam(name = "username") String username, Model model) {
        List<RepositoryDetails> repos = gitHubService.getRepositories(username);
        model.addAttribute("repos", repos);
        model.addAttribute("username", username);
        model.addAttribute("jsonUrl", "/repos/json?username=" + username);
        return "index";
    }

    @GetMapping("/repos/json")
    @ResponseBody
    public ResponseEntity<List<RepositoryDetails>> getReposJson(@RequestParam(name = "username") String username) {
        List<RepositoryDetails> repos = gitHubService.getRepositories(username);
        return ResponseEntity.ok(repos);
    }
}
