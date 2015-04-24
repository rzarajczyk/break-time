package pl.rzarajczyk.breaktime;

/**
 * Objects of this class keep information about the current state of the application.
 * 
 */
public class State {
    private boolean breakActive;
    private boolean paused;
    private int remainingTimeSec;
    private int totalTimeSec;

    State() {
    }
    
    public State clone() {
        State result = new State();
        result.breakActive = this.breakActive;
        result.paused = this.paused;
        result.remainingTimeSec = this.remainingTimeSec;
        result.totalTimeSec = this.totalTimeSec;
        return result;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isBreakActive() {
        return breakActive;
    }

    void setBreakActive(boolean breakActive) {
        this.breakActive = breakActive;
    }

    public int getRemainingTimeSec() {
        return remainingTimeSec;
    }

    void setRemainingTimeSec(int remainingTimeSec) {
        this.remainingTimeSec = remainingTimeSec;
    }

    public int getTotalTimeSec() {
        return totalTimeSec;
    }

    void setTotalTimeSec(int totalTimeSec) {
        this.totalTimeSec = totalTimeSec;
    }
    
    public int getElapsedTimeSec() {
        return this.totalTimeSec - this.remainingTimeSec;
    }
    
    void setElapsedTimeSec(int elapsedTimeSec) {
        this.remainingTimeSec = this.totalTimeSec - elapsedTimeSec;
    }
    
    public float getProgress() {
        return (float) getElapsedTimeSec() / getTotalTimeSec();
    }
    
    void setProgress(float progress) {
        int elapsed = Math.round(progress * getTotalTimeSec());
        setElapsedTimeSec(elapsed);
    }
    
    public boolean hasDisplayStateChanged(State previous) {
        if ( previous == null ) {
            return true;
        }
        return previous.isBreakActive() != isBreakActive();
    }
    
    public boolean hasPausedStateChanged(State previous) {
        if ( previous == null ) {
            return true;
        }
        return previous.isPaused() != isPaused();
    }

    @Override
    public String toString() {
        return "State{" + "breakActive=" + breakActive + ", paused=" + paused + ", remainingTimeSec=" + remainingTimeSec + ", totalTimeSec=" + totalTimeSec + '}';
    }


    
    
}
