package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.MatchweekModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchweekRepository implements Repository {
    @Override
    public String getTableName() {
        return MatchweekModel.TABLE_NAME;
    }

    public int countBySeason(int seasonId) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT count(*) FROM " + getTableName() + " WHERE "
                    + MatchweekModel.SEASON_ID_COL + " = %d;";
            String filledQuery = String.format(query, seasonId);
            ResultSet resultSet = statement.executeQuery(filledQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
