// Still determining the copyright and License of this tutorial code.. Will update shortly
//

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.Exception;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.*;

import org.apache.commons.io.IOUtils;

public class SerialPortHandler {
    private SerialPort serialPort;
    private OutputStream outStream;
    private InputStream inStream;
 
    public void connect(String portName) throws IOException {
        try {
            // Obtain a CommPortIdentifier object for the port you want to open
            CommPortIdentifier portId =
                    CommPortIdentifier.getPortIdentifier(portName);
 
            // Get the port's ownership
            serialPort =
                    (SerialPort) portId.open("DAMMIT", 5000);
 
            // Set the parameters of the connection.
            setSerialPortParameters();
 
            // Open the input and output streams for the connection. If they won't
            // open, close the port before throwing an exception.
            outStream = serialPort.getOutputStream();
            inStream = serialPort.getInputStream();
	    serialPort.addEventListener(new SerialReader(inStream));
	    serialPort.notifyOnDataAvailable(true);
        } catch (NoSuchPortException e) {
            throw new IOException(e.getMessage());
        } catch (PortInUseException e) {
            throw new IOException(e.getMessage());
        } catch (TooManyListenersException e) {
            throw new IOException(e.getMessage());
        } catch (IOException e) {
	    System.out.println("Exception - Closing port ");
            serialPort.close();
            throw e;
        }
    }
 
    /**
     * Get the serial port input stream
     * @return The serial port input stream
     */
    //public InputStream getSerialInputStream() {
    public InputStream getSerialInputStream() throws IOException {
 	return inStream;
    }
 
    /**
     * Get the serial port output stream
     * @return The serial port output stream
     */
    public OutputStream getSerialOutputStream() {
    //public String getSerialOutputStream() {
        //convertStreamToStringOut(outStream);
	return outStream;
    }
 
    /**
     * Sets the serial port parameters
     */
    private void setSerialPortParameters() throws IOException {
        int baudRate = 9600; // 57600bps
 
        try {
            // Set serial port to 57600bps-8N1..my favourite
            serialPort.setSerialPortParams(
                    baudRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
 
            serialPort.setFlowControlMode(
                    SerialPort.FLOWCONTROL_NONE);
        } catch (UnsupportedCommOperationException ex) {
            throw new IOException("Unsupported serial port parameter");
        }
    }

    public void writeSerial(String msg) throws IOException {
	try{
 	    outStream.write(msg.getBytes());
        } catch (IOException e) {
	    System.out.println("Exception - Closing port ");
            serialPort.close();
            throw e;
        }
    }
/*
    public String readSerial() throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	try{
	    int data;
	    byte[] buffer = new byte[1024];
	    int len = 0;
	    String msg = "";
 	    //String msg = inStream.read(buffer);

                len = -1;
                while ( ( len = inStream.read(buffer)) > -1 )
               //while ((msg = reader.readLine()) != null) 
                {
                    //if ( data == '\n' ) {
                    //    break;
                    //}
            	   //System.out.println("Line with " + msg.length() + " characters: \"" + msg + "\"");
                    //buffer[len++] = (byte) data;
               // }
              System.out.print(new String(buffer,0,len));
	        }
	    return msg;
        } catch (IOException e) {
	    System.out.println("Exception - Closing port ");
            serialPort.close();
            throw e
        }
    }*/

    /**
     * Handles the input coming from the serial port. A new line character
     * is treated as the end of a block in this example. 
     */
    public static class SerialReader implements SerialPortEventListener 
    {
        private InputStream inStream;
        private byte[] buffer = new byte[1024];
        
        public SerialReader ( InputStream inStream )
        {
            this.inStream = inStream;
        }
        
        public void serialEvent(SerialPortEvent arg0) {
            int data;
          
            try
            {
                int len = 0;
                while ( ( data = inStream.read()) > -1 )
                {
                    if ( data == '\n' ) {
                        break;
                    }
                    buffer[len++] = (byte) data;
                }
                System.out.print(new String(buffer,0,len));
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                //System.exit(-1);
            }             
        }

    }


    public void disconnect() throws IOException {
        try {
            outStream.close();
            inStream.close();
            serialPort.close();
        } catch (IOException e) {
	    System.out.println("Exception - Closing port ");
            serialPort.close();
            throw e;
        }
    }

    public static String convertStreamToStringIn(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	System.out.println("Made it here!!");
        return s.hasNext() ? s.next() : "";
    }

    //public static String convertStreamToStringOut(java.io.OutputStream is) {
    //    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    //    return s.hasNext() ? s.next() : "";
    //}
}


