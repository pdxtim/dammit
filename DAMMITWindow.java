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
    //public DAMMITCOMM DC;
    //private SerialPortHandler sph;

    public DAMMITWindow() {
        //DC = dc;
        initUI();
	relay0on = false;
	relay1on = false;
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

       JButton relay1Button = new JButton("Relay 1");
       relay1Button.setBounds(110, 70, 80, 30);
       
       quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });
       
       startButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });
       
       stopButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });
       
       relay1Button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
		DAMMIT.relay1Toggle(relay1on);
		if (relay1on) {relay1on = false;}
		else {relay1on = true;}
          }
       });

       panel.add(quitButton);
       panel.add(startButton);
       panel.add(stopButton);
       panel.add(relay1Button);

       setTitle("DAMMIT");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
