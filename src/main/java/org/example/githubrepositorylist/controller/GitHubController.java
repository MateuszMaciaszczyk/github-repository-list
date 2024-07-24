package org.example.githubrepositorylist.controller;

import org.example.githubrepositorylist.model.RepositoryDetails;
import org.example.githubrepositorylist.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "index";
    }
}
