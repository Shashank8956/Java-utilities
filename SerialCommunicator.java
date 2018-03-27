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
        serialPort = new SerialPort("COM3"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    static class SerialPortReader implements SerialPortEventListener {
        
        private StringBuilder message = new StringBuilder();
        private Boolean receivingMessage = false;
        
        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    byte buffer[] = serialPort.readBytes();
                    for (byte b: buffer) {
                        if ( (b == '\r' || b == '\n') && message.length() > 0) {
                            String toProcess = message.toString();
                            System.out.println(toProcess);
                            message.setLength(0);
                        }else {
                            message.append((char)b);
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
