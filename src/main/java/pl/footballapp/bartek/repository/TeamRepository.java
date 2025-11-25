package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.TeamModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.footballapp.bartek.model.TeamModel.TEAM_ID_COL;
import static pl.footballapp.bartek.model.TeamModel.TEAM_NAME_COL;

public class TeamRepository implements Repository {

    public List<TeamModel> findAll() {

        List<TeamModel> teamList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from TEAMS;");
            while (resultSet.next()) {
                TeamModel team = getTeamFromResultSet(resultSet);
                teamList.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return teamList;
    }

    public TeamModel findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from TEAMS where " + TEAM_ID_COL + " = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TeamModel team = getTeamFromResultSet(resultSet);
                return team;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private TeamModel getTeamFromResultSet(ResultSet resultSet) throws SQLException {
        TeamModel team = new TeamModel();
        team.setTeamId(resultSet.getInt(TEAM_ID_COL));
        team.setTeamName(resultSet.getString(TEAM_NAME_COL));

        return team;
    }

}
