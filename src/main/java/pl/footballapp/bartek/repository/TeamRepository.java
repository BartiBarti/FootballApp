package pl.footballapp.bartek.repository;

import org.apache.commons.lang3.StringUtils;
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

    public boolean teamExist(String teamName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from TEAMS where " + TEAM_NAME_COL + " = ? ");
            preparedStatement.setString(1, teamName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public int save(TeamModel team) {

        try {
            Statement statement = connection.createStatement();
            String query = "Insert Into TEAMS (" + TEAM_NAME_COL + ") values ('%s');";
            String filledQuery = String.format(query, team.getTeamName());
            statement.executeUpdate(filledQuery);
            ResultSet generatedKeyResultSet = statement.getGeneratedKeys();
            if (generatedKeyResultSet.next()){
                return generatedKeyResultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public void delete(int teamId) {

        try {
            Statement statement = connection.createStatement();
            String query = "delete from TEAMS where TEAM_ID = %d";
            String filledQuery = String.format(query, teamId);
            statement.executeUpdate(filledQuery);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean update(TeamModel team) {

        try {
            Statement statement = connection.createStatement();
            String query = "update TEAMS set ";
            if (StringUtils.isNotBlank(team.getTeamName())) {
                query = query + "TEAM_NAME = '" + team.getTeamName() + "' ";
            }
            query = query + "where TEAM_ID = " + team.getTeamId();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private TeamModel getTeamFromResultSet(ResultSet resultSet) throws SQLException {
        TeamModel team = new TeamModel();
        team.setTeamId(resultSet.getInt(TEAM_ID_COL));
        team.setTeamName(resultSet.getString(TEAM_NAME_COL));

        return team;
    }


}
