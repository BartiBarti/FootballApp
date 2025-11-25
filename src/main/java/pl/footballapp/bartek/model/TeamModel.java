package pl.footballapp.bartek.model;

public class TeamModel {

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

}
