package com.dota2.metatracker.model;

import java.util.List;

public class HeroStatsDto {

    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {

        private HeroStats heroStats;

        public HeroStats getHeroStats() {
            return heroStats;
        }

        public static class HeroStats {

            private List<MatchUp> matchUp;
            private List<Stat> stats;

            public List<MatchUp> getMatchUp() {
                return matchUp;
            }

            public List<Stat> getStats() {
                return stats;
            }

            public static class MatchUp {

                private List<Vs> vs;
                private List<With> with;


                public List<Vs> getVs() {
                    return vs;
                }

                public List<With> getWith() {
                    return with;
                }

                public static class Vs {
                    private short heroId2;
                    private long count;
                    private double synergy;
                    private double winRateHeroId1;

                    public short getHeroId2() {
                        return heroId2;
                    }

                    public long getCount() {
                        return count;
                    }

                    public double getSynergy() {
                        return synergy;
                    }

                    public double getWinRateHeroId1() {
                        return winRateHeroId1;
                    }
                }

                public static class With {
                    private short heroId2;
                    private long count;
                    private double synergy;
                    private double winRateHeroId1;

                    public short getHeroId2() {
                        return heroId2;
                    }

                    public long getCount() {
                        return count;
                    }

                    public double getSynergy() {
                        return synergy;
                    }

                    public double getWinRateHeroId1() {
                        return winRateHeroId1;
                    }
                }
            }

            public static class Stat {

                private List<Event> events;

                public List<Event> getEvents() {
                    return events;
                }

                public static class Event {
                    private long matchCount;
                    private int time;
                    private double networth;
                    private double physicalDamage;

                    public long getMatchCount() {
                        return matchCount;
                    }

                    public int getTime() {
                        return time;
                    }

                    public double getNetworth() {
                        return networth;
                    }

                    public double getPhysicalDamage() {
                        return physicalDamage;
                    }
                }

            }
        }

    }

}
