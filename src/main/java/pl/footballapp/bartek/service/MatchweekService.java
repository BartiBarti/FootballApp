package pl.footballapp.bartek.service;

import pl.footballapp.bartek.repository.MatchweekRepository;

public class MatchweekService {

    private MatchweekRepository matchweekRepository = new MatchweekRepository();

    public int countBySeason(int seasonId) {
        return matchweekRepository.countBySeason(seasonId);
    }
}
