// Still determining the copyright and License of this tutorial code.. Will update shortly
//

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
        } catch (NoSuchPortException e) {
            throw new IOException(e.getMessage());
        } catch (PortInUseException e) {
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
    public InputStream getSerialInputStream() {
        return inStream;
    }
 
    /**
     * Get the serial port output stream
     * @return The serial port output stream
     */
    public OutputStream getSerialOutputStream() {
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
}
