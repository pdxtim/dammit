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

Relay Module:  
Numato Lab 2 Channel USB Powered Relay Module  
Ver: 1.02  
Batch: NLAA02  
