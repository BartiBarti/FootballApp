package pl.footballapp.bartek.service;

import pl.footballapp.bartek.repository.MatchResultRepository;

public class MatchResultService {

    private MatchResultRepository matchResultRepository = new MatchResultRepository();

    public int countBySeason(int seasonId) {
        return matchResultRepository.countBySeason(seasonId);
    }
}
