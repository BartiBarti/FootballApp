package pl.footballapp.bartek.model;

import pl.footballapp.bartek.enums.MatchweekStatus;

public class MatchweekModel {

    public static final String TABLE_NAME = "MATCHWEEKS";
    public static final String MATCHWEEK_ID_COL = "MATCHWEEK_ID";
    public static final String MATCHWEEK_NUMBER_COL = "MATCHWEEK_NUMBER";
    public static final String MATCHWEEK_STATUS_COL = "MATCHWEEK_STATUS";
    public static final String SEASON_ID_COL = "SEASON_ID";

    private int matchweekId;

    private int matchweekNumber;

    private MatchweekStatus matchweekStatus;

    private int seasonId;

    public int getMatchweekId() {
        return matchweekId;
    }

    public void setMatchweekId(int matchweekId) {
        this.matchweekId = matchweekId;
    }

    public int getMatchweekNumber() {
        return matchweekNumber;
    }

    public void setMatchweekNumber(int matchweekNumber) {
        this.matchweekNumber = matchweekNumber;
    }

    public MatchweekStatus getMatchweekStatus() {
        return matchweekStatus;
    }

    public void setMatchweekStatus(MatchweekStatus matchweekStatus) {
        this.matchweekStatus = matchweekStatus;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
}
