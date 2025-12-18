package pl.footballapp.bartek.model;

import pl.footballapp.bartek.enums.SeasonStatus;

public class SeasonModel {

    public static final String SEASON_ID_COL = "SEASON_ID";
    public static final String START_SEASON_YEAR_COL = "START_SEASON_YEAR";
    public static final String END_SEASON_YEAR_COL = "END_SEASON_YEAR";
    public static final String SEASON_STATUS_COL = "SEASON_STATUS";


    private int seasonId;

    private int startSeasonYear;

    private int endSeasonYear;

    private SeasonStatus seasonStatus;

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getStartSeasonYear() {
        return startSeasonYear;
    }

    public void setStartSeasonYear(int startSeasonYear) {
        this.startSeasonYear = startSeasonYear;
    }

    public int getEndSeasonYear() {
        return endSeasonYear;
    }

    public void setEndSeasonYear(int endSeasonYear) {
        this.endSeasonYear = endSeasonYear;
    }

    public SeasonStatus getSeasonStatus() {
        return seasonStatus;
    }

    public void setSeasonStatus(SeasonStatus seasonStatus) {
        this.seasonStatus = seasonStatus;
    }

    @Override
    public String toString() {
        return startSeasonYear + "/" + endSeasonYear;
    }
}
