package pl.footballapp.bartek.service;

import pl.footballapp.bartek.enums.ParameterName;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleGeneratorService {

    private ParameterService parameterService = new ParameterService();
    private SeasonLeagueService seasonLeagueService = new SeasonLeagueService();

    public void generateSchedule(int seasonId) {
        LocalDate startSeasonDate = parameterService.findParameterByName(ParameterName.START_SEASON_DATE).getParameterDateValue();
        LocalDate endSeasonDate = parameterService.findParameterByName(ParameterName.END_SEASON_DATE).getParameterDateValue();
        LocalDate startWinterBreak = parameterService.findParameterByName(ParameterName.START_WINTER_BREAK).getParameterDateValue();
        LocalDate endWinterBreak = parameterService.findParameterByName(ParameterName.END_WINTER_BREAK).getParameterDateValue();

        List<Integer> teamIds = seasonLeagueService.findAllTeamIdsFromSeason(seasonId);
        int roundsPerLeg = teamIds.size() - 1; // Liczba kolejek w rundzie ( jesiennej, lub wiosennej)

        List<LocalDate> autumnSundays = getSundays(startSeasonDate, startWinterBreak);
        List<LocalDate> springSundays = getSundays(endWinterBreak, endSeasonDate);

        validateAvailableSundays(autumnSundays, roundsPerLeg, "jesiennej");
        validateAvailableSundays(springSundays, roundsPerLeg, "wiosennej");
    }

    private void validateAvailableSundays(List<LocalDate> sundays, int requiredRounds, String roundName) {
        if (sundays.size() < requiredRounds) {
            JOptionPane.showMessageDialog(null, "Niewystarczająca liczba niedziel w rundzie "
                            + roundName + ". Wymagane: " + requiredRounds + ", dostępne: " + sundays.size(),
                    "Błąd walidacji!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<LocalDate> getSundays(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> sundays = new ArrayList<>();
        LocalDate firstSunday = startDate;

        while (firstSunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            firstSunday = firstSunday.plusDays(1);
        }

        for (LocalDate date = firstSunday; !date.isAfter(endDate); date = date.plusWeeks(1)) {
            sundays.add(date);
        }
        return sundays;
    }
}
