/*
 * Copyright (c) 2013 Tim Fleck
 *
 * This program is licensed under the MIT License
 * Please see the LICENSE file for complete license details
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.Exception;

import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gnu.io.*;

public class DAMMITCOMM {

    //public SerialPortHandler sph;
    public String port;
    public boolean portOpen;

    //DAMMITCOMM(){
    //};

    public void startDammit(String Port) throws InterruptedException {
	//sph = Sph;
	port = Port;
	try{
	    DAMMIT.sph.connect(port);
	    portOpen = true;
	} catch (IOException e) {
	    System.out.println("Port opening failed: ");
	    System.out.println(e.getMessage());
	}
		if (portOpen){
		try{
		    DAMMIT.sph.writeSerial("relay on 0\n\r");
		    Thread.sleep(2000);
		    DAMMIT.sph.writeSerial("relay off 0\n\r");
		    //DAMMIT.sph.disconnect();
		} catch (IOException e) {
		    System.out.println("Port operations failed: ");
		    System.out.println(e.getMessage());
		}
	}

	
    }

	public void toggleRelay0on (boolean relay0on) {
		System.out.println(relay0on);
		//sph.connect(port);                
		try{
		//sph.connect(port);        
		System.out.println("Relay 0 turning on? ");
		DAMMIT.sph.writeSerial("relay on 0\n\r");
		//dammit.relay1on = true;
		} catch (IOException e) {
		    System.out.println("Relay 0 operation failed: ");
		    System.out.println(e.getMessage());
		}
	}

	public void toggleRelay0off (boolean relay0on) {
		System.out.println(relay0on);
		try{
		//sph.connect(port);        
		System.out.println("Relay 0 turning off? ");
		DAMMIT.sph.writeSerial("relay off 0\n\r");
		//dammit.relay1on = false;
		} catch (IOException e) {
		    System.out.println("Relay 0 operation failed: ");
		    System.out.println(e.getMessage());
		}
	}

	public int adcRead (int channel) {
		//System.out.println(relay0on);
		int input = 0;
		String callString;

		switch(channel){
		    case 0: callString = "adc read 0\n\r";
			    break;
		    case 1: callString = "adc read 1\n\r";
			    break;
		    case 2: callString = "adc read 2\n\r";
			    break;
		    default: callString = "adc read 3\n\r";
			    break;
		}
		try{
		//sph.connect(port);        
		//System.out.println(callString);
		//Thread.sleep(1);
		DAMMIT.sph.writeSerial(callString);
		while (!DAMMIT.sph.checkForData()){
		    Thread.sleep(100);
		}
		input = DAMMIT.sph.getData();
		//System.out.println("asked and got data - " + input);
		return input;
		//System.out.println("Sent read request! ");
		//input = DAMMIT.sph.readSerial();
		//System.out.println(input);
		//dammit.relay1on = false;
		} catch (IOException e) {
		    System.out.println("Relay 0 operation failed: ");
		    System.out.println(e.getMessage());
		} catch (InterruptedException e) {
		    System.out.println("Relay 0 operation failed: ");
		    System.out.println(e.getMessage());
		}
		return input;
	}
}

