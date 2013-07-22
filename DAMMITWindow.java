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


public class DAMMITWindow extends JFrame {

    public boolean relay0on;
    public boolean relay1on;
    public boolean quitFlag;
    public int adc0;
    public int adc1;
    public int adc2;
    public int adc3;
    public int cnt;

    //public DAMMITCOMM DC;
    //private SerialPortHandler sph;

    public DAMMITWindow() {
        //DC = dc;
        initUI();
	relay0on = false;
	relay1on = false;
	quitFlag = false;
	adc0 = 0;
	adc1 = 0;
	adc2 = 0;
	adc3 = 0;
	cnt = 0;
    }

    //public DAMMITWindow(SerialPortHandler Sph) {
    //    
	//sph = Sph;        
	//initUI();
	//relay0on = false;
	//relay1on = false;
    //}
    private void initUI() {

       JPanel panel = new JPanel();
       getContentPane().add(panel);

       String  defaultPort = "/dev/ttyACM0";

       panel.setLayout(null);

       JButton quitButton = new JButton("Quit");
       quitButton.setBounds(210, 130, 80, 30);

       JButton startButton = new JButton("Start");
       startButton.setBounds(10, 130, 80, 30);

       JButton stopButton = new JButton("Stop");
       stopButton.setBounds(110, 130, 80, 30);

       JButton relay0Button = new JButton("Relay 0");
       relay0Button.setBounds(100, 70, 100, 30);

       JButton readAdc0 = new JButton("ADC 0");
       readAdc0.setBounds(100, 10, 100, 30);
       
       quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });
       
       startButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               DAMMIT.relay0ToggleOn(relay0on);
	       if (!relay0on) {relay0on = true;}
          }
       });
       
       stopButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               	DAMMIT.relay0ToggleOff(relay0on);
		if (relay0on) {relay0on = false;}
          }
       });
       
       readAdc0.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
		DAMMIT.readFromAdc0 ();
          }
       });
       
       relay0Button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
		if (relay0on) 
		{
		    DAMMIT.relay0ToggleOff(relay0on);
		    relay0on = false;
		}
		else 
		{
		    DAMMIT.relay0ToggleOn(relay0on);
		    relay0on = true;
		}
          }
       });

       panel.add(quitButton);
       panel.add(startButton);
       panel.add(stopButton);
       panel.add(readAdc0);
       panel.add(relay0Button);

       setTitle("DAMMIT");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
