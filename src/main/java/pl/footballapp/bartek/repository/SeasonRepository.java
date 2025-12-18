package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.enums.SeasonStatus;
import pl.footballapp.bartek.model.SeasonModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.footballapp.bartek.model.SeasonModel.*;

public class SeasonRepository implements Repository {
    public List<SeasonModel> findAllOrderByDesc() {

        List<SeasonModel> seasonList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from SEASONS order by START_SEASON_YEAR desc;");
            while (resultSet.next()) {
                SeasonModel season = getSeasonFromResultSet(resultSet);
                seasonList.add(season);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }
//     todo - Dopisać metodę - findById

    public boolean save(SeasonModel season) {

        try {
            Statement statement = connection.createStatement();
            String query = "Insert Into SEASONS ("
                    + START_SEASON_YEAR_COL + ","
                    + END_SEASON_YEAR_COL + ","
                    + SEASON_STATUS_COL
                    + ") values (%d, %d, '%s');";
            String filledQuery = String.format(query, season.getStartSeasonYear(), season.getEndSeasonYear(), season.getSeasonStatus().toString());
            statement.executeUpdate(filledQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private SeasonModel getSeasonFromResultSet(ResultSet resultSet) throws SQLException {
        SeasonModel season = new SeasonModel();
        season.setSeasonId(resultSet.getInt(SEASON_ID_COL));
        season.setStartSeasonYear(resultSet.getInt(START_SEASON_YEAR_COL));
        season.setEndSeasonYear(resultSet.getInt(END_SEASON_YEAR_COL));
        season.setSeasonStatus(SeasonStatus.valueOf(resultSet.getString(SEASON_STATUS_COL)));

        return season;
    }

}
