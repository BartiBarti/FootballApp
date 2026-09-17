package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.MatchResultModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MatchResultRepository implements Repository {

    @Override
    public String getTableName() {
        return MatchResultModel.TABLE_NAME;
    }

    public int countBySeason(int seasonId) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT count(*) FROM " + getTableName() + " WHERE "
                    + MatchResultModel.SEASON_ID_COL + " = %d";
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

