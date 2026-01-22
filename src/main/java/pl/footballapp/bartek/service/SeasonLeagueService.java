package pl.footballapp.bartek.service;

import pl.footballapp.bartek.model.SeasonLeagueModel;
import pl.footballapp.bartek.repository.SeasonLeagueRepository;

public class SeasonLeagueService {

    private SeasonLeagueRepository seasonLeagueRepository = new SeasonLeagueRepository();

    public boolean addTeamToSeasonLeague(int teamId, int seasonId){
        SeasonLeagueModel seasonLeagueModel = new SeasonLeagueModel();
        seasonLeagueModel.setTeamId(teamId);
        seasonLeagueModel.setSeasonId(seasonId);
        seasonLeagueModel.setPoints(0);
        seasonLeagueModel.setMatches(0);
        seasonLeagueModel.setMatchesWin(0);
        seasonLeagueModel.setMatchesDraw(0);
        seasonLeagueModel.setMatchesLoss(0);
        seasonLeagueModel.setGoalsScored(0);
        seasonLeagueModel.setGoalsLost(0);
        seasonLeagueModel.setGoalsDifference(0);
        return seasonLeagueRepository.save(seasonLeagueModel);
    }

}
