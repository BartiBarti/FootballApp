package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.SeasonLeagueModel;

import java.sql.SQLException;
import java.sql.Statement;

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

}
