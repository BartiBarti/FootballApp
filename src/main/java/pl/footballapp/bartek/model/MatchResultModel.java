package pl.footballapp.bartek.model;

import pl.footballapp.bartek.enums.MatchStatus;

import java.time.LocalDateTime;

public class MatchResultModel {

    public static final String TABLE_NAME = "MATCH_RESULT";
    public static final String MATCH_ID_COL = "MATCH_ID";
    public static final String MATCHWEEK_ID_COL = "MATCHWEEK_ID";
    public static final String SEASON_ID_COL = "SEASON_ID";
    public static final String GUEST_ID_COL = "GUEST_ID";
    public static final String HOST_ID_COL = "HOST_ID";
    public static final String GUEST_GOALS_COL = "GUEST_GOALS";
    public static final String HOST_GOALS_COL = "HOST_GOALS";
    public static final String MATCH_DATE_COL = "MATCH_DATE";
    public static final String MATCH_STATUS_COL = "MATCH_STATUS";

    private int matchId;

    private int matchweekId;

    private int seasonId;

    private int guestId;

    private int hostId;

    private Integer guestGoals;

    private Integer hostGoals;

    private LocalDateTime matchDate;

    private MatchStatus matchStatus;

    public int getMatchweekId() {
        return matchweekId;
    }

    public void setMatchweekId(int matchweekId) {
        this.matchweekId = matchweekId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public Integer getGuestGoals() {
        return guestGoals;
    }

    public void setGuestGoals(Integer guestGoals) {
        this.guestGoals = guestGoals;
    }

    public Integer getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(Integer hostGoals) {
        this.hostGoals = hostGoals;
    }

}


