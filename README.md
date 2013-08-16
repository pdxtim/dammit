Data Acquisition Module Monitoring Indicator Tool (DAMMIT) 
----------------------------------------------------------

Developer contact information:  
Name: Tim Fleck  
email: tfleck@cs.pdx.edu  


Data Acquisition Module Monitoring Indicator Tool (DAMMIT)


DAMMIT is a JAVA Swing based application to monitor the voltage inputs on a Numato Lab's USB GPIO module and display them. It will also provide the ability to turn on and off the voltage supply to the monitored components and respond to a high voltage condition. 

More specifically:
Starting the application opens a JAVA Swing window that will display a number of empty display bars and a start monitoring button. Pressing the start button will contact the relay board and initiate voltage via the relay. The switching of the relay applies voltage to the external circuit and subsequently provides active input to the GPIO module. The application then starts polling the GPIO module, and displays the voltage for each channel in the display bars. If a voltage exceeds a set-point the application turns off the relay, securing the voltage supply to the GPIO input circuit. Additionally the start button when pressed changes to a stop button which also secures power to the GPIO input circuit and resets to a ready to start condition. 


Equipment used for development:  
GPIO:  
Numato Lab 8 Channel USB GPIO Module with Analog Inputs  
Ver: 1.04  
Batch: NLHA01  

# Build Instructions

## Dependencies
This application uses the RXTX library. The files for use with 64bit Linux are included as they were the ones for the development. If building for another system you can get the required version of the library at http://rxtx.qbang.org/wiki/index.php/Download.

The ant build should work if you place the associated librxtxSerial.so file in the dist folder and the RXTXcomm.jar file in the lib folder in place of the included ones. 

There are some oddities with RXTX so if you have issues there are other alternatives for RXTX installation on your system, please see http://rxtx.qbang.org for more information.

## Building and Running
Building is automated using "Ant jar" as the command.
Because of the use of the USB port running the program is via a command like "sudo java -jar dist/dammit.jar" assuming you are at a command line in the dammit folder.
 
The current set up assumes a symbolic link from /dev/ttyS80 to /dev/ttyACM0. This was based on a suggestion due to some other conflicts with directly using ttyACM0.  I have a goal of working on this to eliminate the need and to enumerate the devices and improve this dependency in hard code. 

## Comments
On starting there will be two warning you may see. One is a file mismatch, this is due to the use of a per-release version of RXTX that supports 64 Bit Linux. This should go away of you are on a 32 bit system and use those files. Also it may not happen with the 64 Bit windows version.
The other is a stale lock warning. This is due to a bug with RXTX that can cause the close port to hang so for this point in development I am being less than optimum and not properly closing the port until I can find a way around the bug.
