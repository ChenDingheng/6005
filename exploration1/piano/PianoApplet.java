/**
 * Author: dnj
 * Date: August 29, 2008
 * 6.005 Elements of Software Construction
 * (c) 2008, MIT and Daniel Jackson
 */
package piano;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.applet.*;
import midi.*;
import music.*;

import static java.lang.Math.abs;

/**
 * A skeletal applet that shows how to bind methods to key events
 */
public class PianoApplet extends Applet {

    private boolean recordFlag=false;
    //private Pitch[] pitches;

    private RecordFormat recordFormat;

	public void init() {

		final Midi midi;
		try {
			midi = new Midi();
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
			return;
		}


		setLayout(new FlowLayout());
		setSize(1000,1000);

		//JButton currentInstrument=new JButton();
		//add(currentInstrument);
		//currentInstrument.setText(String.valueOf(Midi.DEFAULT_INSTRUMENT));


		
		// this is a standard pattern for associating method calls with GUI events
		// the call to the constructor of KeyAdapter creates an object of an
		// anonymous subclass of KeyAdapter, whose keyPressed method is called
		// when a key is pressed in the GUI

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char key = (char) e.getKeyCode();
				switch (key) {
					case '1':
			        	midi.beginNote(new Pitch('C').transpose(1).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.red);
			        	return;
			        case '2':
						midi.beginNote(new Pitch('C').transpose(2).toMidiFrequency(),midi.getInstrument());
			        	//setBackground(Color.green);
						return;
			        case '3':
			        	midi.beginNote(new Pitch('C').transpose(3).toMidiFrequency(),midi.getInstrument());
			        	//setBackground(Color.blue);
			        	return;
			        case '4':
						midi.beginNote(new Pitch('C').transpose(4).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.red);
						return;
					case '5':
						midi.beginNote(new Pitch('C').transpose(5).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.green);
						return;
					case '6':
						midi.beginNote(new Pitch('C').transpose(6).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.blue);
						return;
					case '7':
						midi.beginNote(new Pitch('C').transpose(7).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.red);
						return;
					case '8':
						midi.beginNote(new Pitch('C').transpose(8).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.green);
						return;
					case '9':
						midi.beginNote(new Pitch('C').transpose(9).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.blue);
						return;
					case '0':
						midi.beginNote(new Pitch('C').transpose(10).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.red);
						return;
					case '-':
						midi.beginNote(new Pitch('C').transpose(11).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.green);
						return;
					case '=':
						midi.beginNote(new Pitch('C').transpose(12).toMidiFrequency(),midi.getInstrument());
						//setBackground(Color.blue);
						return;
					case 'I':
						midi.setInstrument(midi.getInstrument().next());
						//currentInstrument.setText(String.valueOf(midi.getInstrument()));
						return;

                    case 'R':
                        if(recordFlag==false){
                            recordFlag=true;
                            setBackground(Color.red);
                            recordFormat=new RecordFormat();
                            startRecord(recordFormat,midi);
                        }
                        else{
                            recordFlag=false;
                            setBackground(Color.green);
                        }
                        return;
                    case 'P':
                        playRecord(recordFormat);

                    //case 'A':
                        //midi.play(new Pitch('C').transpose(12).toMidiFrequency(),10000,midi.getInstrument());

				}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char key = (char) e.getKeyCode();
				switch (key) {
					case '1':
			        	midi.endNote(new Pitch('C').transpose(1).toMidiFrequency(),midi.getInstrument());
			        	return;
					case '2':
			        	midi.endNote(new Pitch('C').transpose(2).toMidiFrequency(),midi.getInstrument());
			        	return;
					case '3':
						midi.endNote(new Pitch('C').transpose(3).toMidiFrequency(),midi.getInstrument());
			        	return;
					case '4':
						midi.endNote(new Pitch('C').transpose(4).toMidiFrequency(),midi.getInstrument());
						return;
					case '5':
						midi.endNote(new Pitch('C').transpose(5).toMidiFrequency(),midi.getInstrument());
						return;
					case '6':
						midi.endNote(new Pitch('C').transpose(6).toMidiFrequency(),midi.getInstrument());
						return;
					case '7':
						midi.endNote(new Pitch('C').transpose(7).toMidiFrequency(),midi.getInstrument());
						return;
					case '8':
						midi.endNote(new Pitch('C').transpose(8).toMidiFrequency(),midi.getInstrument());
						return;
					case '9':
						midi.endNote(new Pitch('C').transpose(9).toMidiFrequency(),midi.getInstrument());
						return;
					case '0':
						midi.endNote(new Pitch('C').transpose(10).toMidiFrequency(),midi.getInstrument());
						return;
					case '-':
						midi.endNote(new Pitch('C').transpose(11).toMidiFrequency(),midi.getInstrument());
						return;
					case '=':
						midi.endNote(new Pitch('C').transpose(12).toMidiFrequency(),midi.getInstrument());
						return;
				}
			}
		});


	}

	public void playRecord(RecordFormat recordFormat){
	    Midi midi;
        try {
            midi = new Midi();
        } catch (MidiUnavailableException e1) {
            e1.printStackTrace();
            return;
        }
        int i=0;
        while(i<recordFormat.getIndex()-1){
            //while(true){
                //if
            //}
            midi.play(recordFormat.getPitch(i).toMidiFrequency(),(int)abs(recordFormat.getCurrentTime(i)-recordFormat.getCurrentTime(i+1)),recordFormat.getInstuments(i));
            System.out.println(i);
            //System.out.println((int)(recordFormat.getCurrentTime(i)-recordFormat.getCurrentTime(i+1)));
            i++;
        }
        midi.beginNote(recordFormat.getPitch(i).toMidiFrequency(),recordFormat.getInstuments(i));
        //midi.endNote(recordFormat.getPitch(i).toMidiFrequency(),recordFormat.getInstuments(i));
    }


	public void startRecord(RecordFormat recordFormat,Midi midi){
	    //recordFormat=new RecordFormat();
	    addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char key = (char) e.getKeyCode();
                switch (key) {
                    case '1':
                        recordFormat.setPitches(new Pitch('C').transpose(1));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(1).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '2':
                        recordFormat.setPitches(new Pitch('C').transpose(2));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(2).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '3':
                        recordFormat.setPitches(new Pitch('C').transpose(3));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(3).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '4':
                        recordFormat.setPitches(new Pitch('C').transpose(4));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(4).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '5':
                        recordFormat.setPitches(new Pitch('C').transpose(5));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(5).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '6':
                        recordFormat.setPitches(new Pitch('C').transpose(6));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(6).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '7':
                        recordFormat.setPitches(new Pitch('C').transpose(7));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(7).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '8':
                        recordFormat.setPitches(new Pitch('C').transpose(8));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(8).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '9':
                        recordFormat.setPitches(new Pitch('C').transpose(9));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(9).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '0':
                        recordFormat.setPitches(new Pitch('C').transpose(10));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(10).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '-':
                        recordFormat.setPitches(new Pitch('C').transpose(11));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(11).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '=':
                        recordFormat.setPitches(new Pitch('C').transpose(12));
                        recordFormat.setInstruments(midi.getInstrument());
                        recordFormat.setCurrentTimes();
                        recordFormat.incrementIndex();
                        midi.beginNote(new Pitch('C').transpose(12).toMidiFrequency(),midi.getInstrument());
                        return;
                }
            }
/*
            public void keyReleased(KeyEvent e) {
                char key = (char) e.getKeyCode();
                switch (key) {
                    case '1':
                        midi.endNote(new Pitch('C').transpose(1).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '2':
                        midi.endNote(new Pitch('C').transpose(2).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '3':
                        midi.endNote(new Pitch('C').transpose(3).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '4':
                        midi.endNote(new Pitch('C').transpose(4).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '5':
                        midi.endNote(new Pitch('C').transpose(5).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '6':
                        midi.endNote(new Pitch('C').transpose(6).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '7':
                        midi.endNote(new Pitch('C').transpose(7).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '8':
                        midi.endNote(new Pitch('C').transpose(8).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '9':
                        midi.endNote(new Pitch('C').transpose(9).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '0':
                        midi.endNote(new Pitch('C').transpose(10).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '-':
                        midi.endNote(new Pitch('C').transpose(11).toMidiFrequency(),midi.getInstrument());
                        return;
                    case '=':
                        midi.endNote(new Pitch('C').transpose(12).toMidiFrequency(),midi.getInstrument());
                        return;
                }
            }
            */
        });
    }

}