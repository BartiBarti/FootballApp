package pl.footballapp.bartek.service;


import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.repository.SeasonLeagueRepository;
import pl.footballapp.bartek.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private TeamRepository teamRepository = new TeamRepository();

    private SeasonLeagueRepository seasonLeagueRepository = new SeasonLeagueRepository();

    public boolean teamExist(String teamName){
        return teamRepository.teamExist(teamName);
    }

    public int createTeam(String teamName){
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamName(teamName);
        return teamRepository.save(teamModel);
    }

    public List<TeamModel> findAllTeamsWithoutCurrentAdded(List<TeamModel> teamsAdded) {
        List<TeamModel> filteredTeams = new ArrayList<>();
        List<TeamModel> allTeams = teamRepository.findAll();
        for (TeamModel team : allTeams) {
            if(!teamsAdded.contains(team)){
                filteredTeams.add(team);

            }
        }
        return filteredTeams;
    }

    public List<TeamModel> findAllTeamsCurrentlyAddedToSeason(Integer seasonId) {
        List<Integer> teamIds = seasonLeagueRepository.findAllTeamIdsFromSeason(seasonId);
        List<TeamModel> teamsAddedToSeason = teamRepository.findAllByIds(teamIds);
        return teamsAddedToSeason;
    }

    public TeamModel findTeam(Integer teamId){
        return teamRepository.findById(teamId);
    }

    public TeamModel findTeamByName(String teamName){
        return teamRepository.findTeamByTeamName(teamName);
    }
}
