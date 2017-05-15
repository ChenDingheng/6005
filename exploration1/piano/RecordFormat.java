package piano;

import midi.*;
import music.*;

/**
 * Created by dell on 2017/5/15.
 */
public class RecordFormat {
    private Pitch[] pitches;
    private Instrument[] instruments;
    private long[] currentTimes;

    private int index;
    public RecordFormat(){
        pitches=new Pitch[1000];
        instruments=new Instrument[1000];
        currentTimes=new long[1000];
        index=0;
    }
    public int getIndex(){
        return this.index;
    }

    public Pitch getPitch(int index){
        return pitches[index];
    }

    public long getCurrentTime(int index){
        return currentTimes[index];
    }

    public Instrument getInstuments(int index){
        return instruments[index];
    }

    public void setPitches(Pitch pitch){
        pitches[index]=pitch;
    }
    public void setInstruments(Instrument instrument){
        instruments[index]=instrument;
    }
    public void setCurrentTimes(){
        currentTimes[index]=System.currentTimeMillis();
    }
    public void incrementIndex(){
        index++;
    }
}
