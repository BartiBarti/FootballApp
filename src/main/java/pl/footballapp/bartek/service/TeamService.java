package pl.footballapp.bartek.service;


import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.repository.TeamRepository;

public class TeamService {
    private TeamRepository teamRepository = new TeamRepository();

    public boolean teamExist(String teamName){
        return teamRepository.teamExist(teamName);
    }

    public int createTeam(String teamName){
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamName(teamName);
        return teamRepository.save(teamModel);
    }
}
