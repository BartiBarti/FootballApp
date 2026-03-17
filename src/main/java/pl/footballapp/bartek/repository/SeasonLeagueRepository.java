package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.SeasonLeagueModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.footballapp.bartek.model.SeasonLeagueModel.*;

public class SeasonLeagueRepository implements Repository {

    public boolean save(SeasonLeagueModel season) {
        try {
            Statement statement = connection.createStatement();
            String query = "Insert Into SEASON_LEAGUE (" + TEAM_ID_COL + ", "
                    + POINTS_COL + ", "
                    + MATCHES_COL + ", "
                    + MATCHES_WIN_COL + ", "
                    + MATCHES_DRAW_COL + ", "
                    + MATCHES_LOSS_COL + ", "
                    + GOALS_SCORED_COL + ", "
                    + GOALS_LOST_COL + ", "
                    + GOALS_DIFFERENCE_COL + ", "
                    + SEASON_ID_COL + ") "
                    + "values (%d, %d, %d, %d, %d, %d, %d, %d, %d, %d);";
            String filledQuery = String.format(query, season.getTeamId(),
                    season.getPoints(), season.getMatches(), season.getMatchesWin(),
                    season.getMatchesDraw(), season.getMatchesLoss(), season.getGoalsScored(),
                    season.getGoalsLost(), season.getGoalsDifference(), season.getSeasonId());
            statement.executeUpdate(filledQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<SeasonLeagueModel> findAllBySeasonIdOrderByFootballRules(int seasonId) {
        List<SeasonLeagueModel> seasonLeagueList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from SEASON_LEAGUE where "
                    + SEASON_ID_COL
                    + " = "
                    + seasonId
                    + " order by POINTS desc, GOALS_DIFFERENCE desc, "
                    + "GOALS_SCORED desc, GOALS_LOST asc;");

            while (resultSet.next()) {
                SeasonLeagueModel seasonLeague = getSeasonLeaugeFromResultSet(resultSet);
                seasonLeagueList.add(seasonLeague);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonLeagueList;
    }

    public List<Integer> findAllTeamIdsFromSeason(Integer seasonId) {
        List<SeasonLeagueModel> seasonLeagueList = findAllBySeasonIdOrderByFootballRules(seasonId);
        List<Integer> teamIds = new ArrayList<>();
        for (SeasonLeagueModel seasonLeague : seasonLeagueList) {
            teamIds.add(seasonLeague.getTeamId());
        }
        return teamIds;
    }

    public void deleteTeam(int teamId){
        try {
            Statement statement = connection.createStatement();
            String query = "delete from SEASON_LEAGUE where TEAM_ID = %d";
            String filledQuery = String.format(query, teamId);
            statement.executeUpdate(filledQuery);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private SeasonLeagueModel getSeasonLeaugeFromResultSet(ResultSet resultSet) throws SQLException {
        SeasonLeagueModel seasonLeague = new SeasonLeagueModel();
        seasonLeague.setSeasonLeagueId(resultSet.getInt(SEASON_LEAGUE_ID_COL));
        seasonLeague.setTeamId(resultSet.getInt(TEAM_ID_COL));
        seasonLeague.setPoints(resultSet.getInt(POINTS_COL));
        seasonLeague.setMatches(resultSet.getInt(MATCHES_COL));
        seasonLeague.setMatchesWin(resultSet.getInt(MATCHES_WIN_COL));
        seasonLeague.setMatchesDraw(resultSet.getInt(MATCHES_DRAW_COL));
        seasonLeague.setMatchesLoss(resultSet.getInt(MATCHES_LOSS_COL));
        seasonLeague.setGoalsScored(resultSet.getInt(GOALS_SCORED_COL));
        seasonLeague.setGoalsLost(resultSet.getInt(GOALS_LOST_COL));
        seasonLeague.setGoalsDifference(resultSet.getInt(GOALS_DIFFERENCE_COL));
        seasonLeague.setSeasonId(resultSet.getInt(SEASON_ID_COL));

        return seasonLeague;
    }


}
