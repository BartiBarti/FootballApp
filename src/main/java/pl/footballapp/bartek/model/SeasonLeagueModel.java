package pl.footballapp.bartek.model;

public class SeasonLeagueModel {

    public static final String SEASON_LEAGUE_ID_COL = "SEASON_LEAGUE_ID";

    public static final String TEAM_ID_COL = "TEAM_ID";

    public static final String POINTS_COL = "POINTS";

    public static final String MATCHES_COL = "MATCHES";

    public static final String MATCHES_WIN_COL = "MATCHES_WIN";

    public static final String MATCHES_DRAW_COL = "MATCHES_DRAW";

    public static final String MATCHES_LOSS_COL = "MATCHES_LOSS";

    public static final String GOALS_SCORED_COL = "GOALS_SCORED";

    public static final String GOALS_LOST_COL = "GOALS_LOST";

    public static final String GOALS_DIFFERENCE_COL = "GOALS_DIFFERENCE";

    public static final String SEASON_ID_COL = "SEASON_ID";



    private int matches;

    private int matchesWin;

    private int matchesDraw;

    private int matchesLoss;

    private int seasonLeagueId;

    private int teamId;

    private int points;

    private int goalsScored;

    private int goalsLost;

    private int goalsDifference;

    private int seasonId;

    public int getSeasonLeagueId() {
        return seasonLeagueId;
    }

    public void setSeasonLeagueId(int seasonLeagueId) {
        this.seasonLeagueId = seasonLeagueId;
    }

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

    public void setMatchesWin(int matchesWin) {
        this.matchesWin = matchesWin;
    }

    public int getMatchesDraw() {
        return matchesDraw;
    }

    public void setMatchesDraw(int matchesDraw) {
        this.matchesDraw = matchesDraw;
    }

    public int getMatchesLoss() {
        return matchesLoss;
    }

    public void setMatchesLoss(int matchesLoss) {
        this.matchesLoss = matchesLoss;
    }

    public int getMatchesWin() {
        return matchesWin;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "SeasonLeagueModel{" +
                "matches=" + matches +
                ", matchesWin=" + matchesWin +
                ", matchesDraw=" + matchesDraw +
                ", matchesLoss=" + matchesLoss +
                ", seasonLeagueId=" + seasonLeagueId +
                ", teamId=" + teamId +
                ", points=" + points +
                ", goalsScored=" + goalsScored +
                ", goalsLost=" + goalsLost +
                ", goalsDifference=" + goalsDifference +
                ", seasonId=" + seasonId +
                '}';
    }
}


