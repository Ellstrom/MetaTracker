package com.dota2.metatracker.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum Hero {

    ANTI_MAGE((short) 1, "Anti-Mage"),
    AXE((short) 2, "Axe"),
    BANE((short) 3, "Bane"),
    BLOODSEEKER((short) 4, "Bloodseeker"),
    CRYSTAL_MAIDEN((short) 5, "Crystal Maiden"),
    DROW_RANGER((short) 6, "Drow Ranger"),
    EARTHSHAKER((short) 7, "Earthshaker"),
    JUGGERNAUT((short) 8, "Juggernaut"),
    MIRANA((short) 9, "Mirana"),
    MORPHLING((short) 10, "Morphling"),
    SHADOW_FIEND((short) 11, "Shadow Fiend"),
    PHANTOM_LANCER((short) 12, "Phantom Lancer"),
    PUCK((short) 13, "Puck"),
    PUDGE((short) 14, "Pudge"),
    RAZOR((short) 15, "Razor"),
    SAND_KING((short) 16, "Sand King"),
    STORM_SPIRIT((short) 17, "Storm Spirit"),
    SVEN((short) 18, "Sven"),
    TINY((short) 19, "Tiny"),
    VENGEFUL_SPIRIT((short) 20, "Vengeful Spirit"),
    WINDRANGER((short) 21, "Windranger"),
    ZEUS((short) 22, "Zeus"),
    KUNKKA((short) 23, "Kunkka"),
    LINA((short) 25, "Lina"),
    LION((short) 26, "Lion"),
    SHADOW_SHAMAN((short) 27, "Shadow Shaman"),
    SLARDAR((short) 28, "Slardar"),
    TIDEHUNTER((short) 29, "Tidehunter"),
    WITCH_DOCTOR((short) 30, "Witch Doctor"),
    LICH((short) 31, "Lich"),
    RIKI((short) 32, "Riki"),
    ENIGMA((short) 33, "Enigma"),
    TINKER((short) 34, "Tinker"),
    SNIPER((short) 35, "Sniper"),
    NECROPHOS((short) 36, "Necrophos"),
    WARLOCK((short) 37, "Warlock"),
    BEASTMASTER((short) 38, "Beastmaster"),
    QUEEN_OF_PAIN((short) 39, "Queen of Pain"),
    VENOMANCER((short) 40, "Venomancer"),
    FACELESS_VOID((short) 41, "Faceless Void"),
    WRAITH_KING((short) 42, "Wraith King"),
    DEATH_PROPHET((short) 43, "Death Prophet"),
    PHANTOM_ASSASSIN((short) 44, "Phantom Assassin"),
    PUGNA((short) 45, "Pugna"),
    TEMPLAR_ASSASSIN((short) 46, "Templar Assassin"),
    VIPER((short) 47, "Viper"),
    LUNA((short) 48, "Luna"),
    DRAGON_KNIGHT((short) 49, "Dragon Knight"),
    DAZZLE((short) 50, "Dazzle"),
    CLOCKWERK((short) 51, "Clockwerk"),
    LESHRAC((short) 52, "Leshrac"),
    NATURES_PROPHET((short) 53, "Nature's Prophet"),
    LIFESTEALER((short) 54, "Lifestealer"),
    DARK_SEER((short) 55, "Dark Seer"),
    CLINKZ((short) 56, "Clinkz"),
    OMNIKNIGHT((short) 57, "Omniknight"),
    ENCHANTRESS((short) 58, "Enchantress"),
    HUSKAR((short) 59, "Huskar"),
    NIGHT_STALKER((short) 60, "Night Stalker"),
    BROODMOTHER((short) 61, "Broodmother"),
    BOUNTY_HUNTER((short) 62, "Bounty Hunter"),
    WEAVER((short) 63, "Weaver"),
    JAKIRO((short) 64, "Jakiro"),
    BATRIDER((short) 65, "Batrider"),
    CHEN((short) 66, "Chen"),
    SPECTRE((short) 67, "Spectre"),
    ANCIENT_APPARITION((short) 68, "Ancient Apparition"),
    DOOM((short) 69, "Doom"),
    URSA((short) 70, "Ursa"),
    SPIRIT_BREAKER((short) 71, "Spirit Breaker"),
    GYROCOPTER((short) 72, "Gyrocopter"),
    ALCHEMIST((short) 73, "Alchemist"),
    INVOKER((short) 74, "Invoker"),
    SILENCER((short) 75, "Silencer"),
    OUTWORLD_DESTROYER((short) 76, "Outworld Destroyer"),
    LYCAN((short) 77, "Lycan"),
    BREWMASTER((short) 78, "Brewmaster"),
    SHADOW_DEMON((short) 79, "Shadow Demon"),
    LONE_DRUID((short) 80, "Lone Druid"),
    CHAOS_KNIGHT((short) 81, "Chaos Knight"),
    MEEPO((short) 82, "Meepo"),
    TREANT_PROTECTOR((short) 83, "Treant Protector"),
    OGRE_MAGI((short) 84, "Ogre Magi"),
    UNDYING((short) 85, "Undying"),
    RUBICK((short) 86, "Rubick"),
    DISRUPTOR((short) 87, "Disruptor"),
    NYX_ASSASSIN((short) 88, "Nyx Assassin"),
    NAGA_SIREN((short) 89, "Naga Siren"),
    KEEPER_OF_THE_LIGHT((short) 90, "Keeper of the Light"),
    IO((short) 91, "Io"),
    VISAGE((short) 92, "Visage"),
    SLARK((short) 93, "Slark"),
    MEDUSA((short) 94, "Medusa"),
    TROLL_WARLORD((short) 95, "Troll Warlord"),
    CENTAUR_WARRUNNER((short) 96, "Centaur Warrunner"),
    MAGNUS((short) 97, "Magnus"),
    TIMBERSAW((short) 98, "Timbersaw"),
    BRISTLEBACK((short) 99, "Bristleback"),
    TUSK((short) 100, "Tusk"),
    SKYWRATH_MAGE((short) 101, "Skywrath Mage"),
    ABADDON((short) 102, "Abaddon"),
    ELDER_TITAN((short) 103, "Elder Titan"),
    LEGION_COMMANDER((short) 104, "Legion Commander"),
    TECHIES((short) 105, "Techies"),
    EMBER_SPIRIT((short) 106, "Ember Spirit"),
    EARTH_SPIRIT((short) 107, "Earth Spirit"),
    UNDERLORD((short) 108, "Underlord"),
    TERRORBLADE((short) 109, "Terrorblade"),
    PHOENIX((short) 110, "Phoenix"),
    ORACLE((short) 111, "Oracle"),
    WINTER_WYVERRN((short) 112, "Winter Wyvern"),
    ARC_WARDEN((short) 113, "Arc Warden"),
    MONKEY_KING((short) 114, "Monkey King"),
    DARK_WILLOW((short) 119, "Dark Willow"),
    PANGOLIER((short) 120, "Pangolier"),
    GRIMSTROKE((short) 121, "Grimstroke"),
    HOODWINK((short) 123, "Hoodwink"),
    VOID_SPIRIT((short) 126, "Void Spirit"),
    SNAPFIRE((short) 128, "Snapfire"),
    MARS((short) 129, "Mars"),
    DAWNBREAKER((short) 135, "Dawnbreaker"),
    MARCI((short) 136, "Marci"),
    PRIMAL_BEAST((short) 137, "Primal Beast");

    private final short id;
    private final String name;

    Hero(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Hero findById(short id) {
        return Arrays.stream(Hero.values())
                .filter(hero -> id == hero.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Hero with id %s not found.", id)));
    }

    public static Hero findByName(String name) {
        return Arrays.stream(Hero.values())
                .filter(hero -> StringUtils.equalsIgnoreCase(name, hero.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Hero with name %s not found.", name)));
    }

}
