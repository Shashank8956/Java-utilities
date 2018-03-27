package arduinotest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;


public class SerialCommunicator{
    static SerialPort serialPort;
    static  StringBuilder sb = new StringBuilder();
    static  String ch, rxData="";
    static  String[] c;
    static  int cnt=0;
            
    public static void main(String[] args) {
        serialPort = new SerialPort("COM3");                                    //Create new SerialPort object to a particular port
        try {
            serialPort.openPort();                                              //Open port
            serialPort.setParams(9600, 8, 1, 0);                                //Default params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR; //Prepare default mask
            serialPort.setEventsMask(mask);                                     //Set mask
            serialPort.addEventListener(new SerialPortReader());                //Add SerialPortEventListener
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    static class SerialPortReader implements SerialPortEventListener {          //Create static nexted class for eventListener
        
        private StringBuilder message = new StringBuilder();
        private Boolean receivingMessage = false;
        
        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){                  //event.isRXCHAR checks if there is an input available
                try {                                                           //event.getEventValue checks if the input size > 0
                    byte buffer[] = serialPort.readBytes();                     //ReadBytes of n length from the serail port
                    for (byte b: buffer) {                                      //Iterate through all the elements of buffer[].
                        if ( (b == '\r' || b == '\n') && message.length() > 0) {//If Carriage return (\r) or newline(\n) found
                            String finalMessage = message.toString();           //Convert StringBuilder to string
                            System.out.println(finalMessage);                   //Display String
                            message.setLength(0);                               //Clear StringBuilder
                        }else {
                            message.append((char)b);                            //Else append the current char in b to StringBuilder
                        }
                    }                
                }catch (SerialPortException ex) {
                    System.out.println(ex);
                    System.out.println("serialEvent");
                }
            }
        }
    }
}
