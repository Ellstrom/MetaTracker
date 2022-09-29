package com.dota2.metatracker;

import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.service.MetaDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MetaTrackerController {

    private final MetaDataService metaDataService;

    public MetaTrackerController(MetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }

    @GetMapping(value = "/matchups/vs-and-with/heroes")
    public List<CounterData> findHeroMatchupsWithAndVsHeroes(@RequestParam(required = false) List<Hero> vsHeroes,
                                                             @RequestParam(required = false) List<Hero> withHeroes,
                                                             @RequestParam(required = false) List<Hero> heroesToInclude,
                                                             @RequestParam(defaultValue = "0") int minimumAmountOfGamesForMatchup) throws IOException {

        return metaDataService.getCounterData(vsHeroes, withHeroes, heroesToInclude, minimumAmountOfGamesForMatchup);
    }

}
