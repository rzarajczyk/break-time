package pl.rzarajczyk.breaktime;

public class BreakCalculator {
    
    private static boolean STARTS_WITH_BREAK = false;

    public static boolean isBreakActive(long time, Break b) {
        long period = b.getDuration() / 1000 + b.getInterval() / 1000;
        long fullPeriodsAlreadyPassed = time / period;
        long currentPeriod = time - fullPeriodsAlreadyPassed * period;
        if ( !STARTS_WITH_BREAK ) {
            return currentPeriod > b.getInterval() / 1000;
        } else {
            return currentPeriod > b.getDuration() / 1000;
        }
    }
    
    public static int getTotalTimeSec(long time, Break b) {
        if ( isBreakActive(time, b) ) {
            return b.getDuration() / 1000;
        } else {
            return b.getInterval() / 1000;
        }
    }
    
    public static int getRemainingTimeSec(long time, Break b) {
        boolean active = isBreakActive(time, b);
        long period = b.getDuration() / 1000 + b.getInterval() / 1000;
        long fullPeriodsAlreadyPassed = time / period;
        long currentPeriod = time - fullPeriodsAlreadyPassed * period;
        
        if ( active && !STARTS_WITH_BREAK ) {
            return (int) currentPeriod - b.getInterval() / 1000;
        } else if ( active && STARTS_WITH_BREAK ) {
            return (int) currentPeriod;
        } else if ( !active && !STARTS_WITH_BREAK ) {
            return (int) currentPeriod;
        } else {
            return (int) currentPeriod - b.getDuration() / 1000;
        }
    }
            
}
