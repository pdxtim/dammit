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

public class DAMMIT {

    public static SerialPortHandler sph;
    public static DAMMITCOMM DC;
    public static DAMMITWindow dammitWin;

    public static void relay1Toggle(boolean relay1on){
	DC.toggleRelay1(relay1on);
    }
 
    public static void main(String[] args) throws InterruptedException {
	sph = new SerialPortHandler();

	String port = "/dev/ttyS80";

	DC = new DAMMITCOMM();
	DC.startDammit(port);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dammitWin = new DAMMITWindow();
                dammitWin.setVisible(true);
            }
        });

    }
}

