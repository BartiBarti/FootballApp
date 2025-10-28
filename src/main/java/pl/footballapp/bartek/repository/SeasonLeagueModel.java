package pl.footballapp.bartek.repository;

public class SeasonLeagueModel {

    private int seasonId;

    private int teamId;

    private int points;

    private int goalsScored;

    private int goalsLost;

    private int goalsDifference;

    private int startSeasonYear;

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsLost() {
        return goalsLost;
    }

    public void setGoalsLost(int goalsLost) {
        this.goalsLost = goalsLost;
    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        this.goalsDifference = goalsDifference;
    }

    public int getStartSeasonYear() {
        return startSeasonYear;
    }

    public void setStartSeasonYear(int startSeasonYear) {
        this.startSeasonYear = startSeasonYear;
    }

    @Override
    public String toString() {
        return "SeasonLeagueModel{" +
                "seasonId=" + seasonId +
                ", teamId=" + teamId +
                ", points=" + points +
                ", goalsScored=" + goalsScored +
                ", goalsLost=" + goalsLost +
                ", goalsDifference=" + goalsDifference +
                ", startSeasonYear=" + startSeasonYear +
                '}';
    }
}


