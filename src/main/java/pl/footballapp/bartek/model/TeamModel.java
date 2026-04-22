package pl.footballapp.bartek.model;

import java.util.Objects;

public class TeamModel {

    public static final String TABLE_NAME = "TEAMS";

    public static final String TEAM_ID_COL = "TEAM_ID";

    public static final String TEAM_NAME_COL = "TEAM_NAME";

    private int teamId;

    private String teamName;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamModel teamModel = (TeamModel) o;
        return teamId == teamModel.teamId && Objects.equals(teamName, teamModel.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName);
    }

}
