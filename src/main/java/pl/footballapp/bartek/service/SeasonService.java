package pl.footballapp.bartek.service;

import pl.footballapp.bartek.enums.SeasonStatus;
import pl.footballapp.bartek.model.SeasonModel;
import pl.footballapp.bartek.repository.SeasonRepository;

import java.time.LocalDate;
import java.util.List;

public class SeasonService {

    private SeasonRepository seasonRepository = new SeasonRepository();

    public List<SeasonModel> findAllSeasonsWithCurrent(){
        List<SeasonModel> seasonsInDb = seasonRepository.findAllOrderByDesc();
        if (seasonsInDb.isEmpty()) {
            LocalDate currentDate = LocalDate.now();
            int startSeasonYear = currentDate.getYear();
            int endSeasonYear = startSeasonYear + 1;
            SeasonModel currentSeason = new SeasonModel();
            currentSeason.setStartSeasonYear(startSeasonYear);
            currentSeason.setEndSeasonYear(endSeasonYear);
            currentSeason.setSeasonStatus(SeasonStatus.OPEN);
            seasonRepository.save(currentSeason);
            return seasonRepository.findAllOrderByDesc();
        }
        return seasonsInDb;
    }
}
