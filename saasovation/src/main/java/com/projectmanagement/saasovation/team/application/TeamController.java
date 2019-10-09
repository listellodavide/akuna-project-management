package com.projectmanagement.saasovation.team.application;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Team;
import com.projectmanagement.saasovation.team.infrustructure.repositories.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @Autowired
    TeamRepository temTeamRepository;

    @Autowired
    ProjectRepository projectRepository;


    @PostMapping("/newTeam/{id}")
    public String createTeam(@PathVariable("id") long id, @RequestParam("teamName") String teamName){

        Project project = projectRepository.findProjectById(id);
        Team team = new Team(teamName, project);
        temTeamRepository.saveTeam(team);
        return "redirect:/projects/"+id;
    }
}
