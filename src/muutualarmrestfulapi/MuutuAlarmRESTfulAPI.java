/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muutualarmrestfulapi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



/**
 *
 * @author anonymouseaggle
 */
public class MuutuAlarmRESTfulAPI {
    
    
    
    public static void main(String args[]){
        MuutuAlarmRESTfulAPI m = new MuutuAlarmRESTfulAPI();
        
        String add = "http://demo.inax.ch:8080/rest/v3.1/AlarmPoint/add";
        
        String delete = "http://demo.inax.ch:8080/rest/v3.1/AlarmPoint/delete?alarmId=12345&sysId=321";
        
        String status = "http://demo.inax.ch:8080/rest/v3.1/AlarmPoint/status?alarmId=000002&sysId=office";
        
        String status2 = "http://demo.inax.ch:8080/rest/v3.1/AlarmPoint/getCameOnAlarms";
        
        
        String addAlarm = "http://demo.inax.ch/rest/v3.1/AlarmEvent/alarm";
        
        
        
        String addXml = "<alarmPoint>\n" +
                                "<apid>12345</apid>\n" +
                                "<sysid>321</sysid>\n" +
                                "<alarmPlan>alarmPlan</alarmPlan>\n" +
                                "<localAcknowledgeTime>5</localAcknowledgeTime>\n" +
                                "<text>asdadads</text>\n" +
                                "<description>example</description>\n" +
                                "<name>Tahir</name>\n" +
                                "<confirmPriority>1</confirmPriority>\n" +
                                "<speechText>speechText</speechText>\n" +
                                "<alarmJobs/>\n" +
                                "<acknowledgeMode>MEDIA</acknowledgeMode>\n" +
                                "<mediaProcessingType>BY_RANK</mediaProcessingType>\n" +
                                "<triggering>WHEN_ON</triggering>\n" +
                                "<mediaUsageType>ALL</mediaUsageType>\n" +
                           "</alarmPoint>";
        
        String addAlarmXml = "<msg>\n" +
                                "<apid>1234</apid>\n" +
                                "<sysid>4321</sysid>\n" +
                                "<event>K</event>\n" +
                                "<prio>2</prio>\n" +
                                "<callerid>1</callerid>\n" +
                                "<family><![CDATA[vqwleuify]]></family>\n" +
                                "<text>help me (tahir)</text>\n" +
                                "<childtarget>true</childtarget>\n" +
                              "</msg>";
        
        
        //m.add(add, addXml);
        //m.delete(delete);
        //m.status(status2);
        m.add(addAlarm, addAlarmXml);
        
    }
    
    
    
    public void add(String ur, String xml){
        try {
            
            URL url = new URL(ur);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("Authorization","Basic "+ Base64.getEncoder().encodeToString("Muutu:Mu12rTq12".getBytes()));

            http.setDoInput(true);
            http.setDoOutput(true);    
            http.setConnectTimeout(2000);
            http.setReadTimeout(2000); 
            http.setUseCaches(false);
            http.setDefaultUseCaches(false); 
            http.setRequestProperty("Content-Type", "application/xml");
           
            try (OutputStreamWriter writer = new OutputStreamWriter(http.getOutputStream())) {
                writer.write(xml);
                writer.flush();
            }
            int i = http.getResponseCode();
            System.out.println(i);
            
            
            
            
            //Reading the response
            InputStreamReader reader = new InputStreamReader(http.getInputStream());
            
            StringBuilder buf = new StringBuilder();
            char[] cbuf = new char[2048];
            int num;
            
            while(-1 != (num=reader.read(cbuf))){
                buf.append(cbuf, 0, num);
            }
            
            String result = buf.toString();
            System.err.println("\nResponse from server after POST:\n" + result);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String ur){
    
        try {
            URL url = new URL(ur);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("Authorization","Basic "+ Base64.getEncoder().encodeToString("Muutu:Mu12rTq12".getBytes()));
            
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/xml" );
            http.setRequestMethod("DELETE");
            http.connect();
            
            int i = http.getResponseCode();
            System.out.println(i);
            
            //Reading the response
            InputStreamReader reader = new InputStreamReader(http.getInputStream());
            
            StringBuilder buf = new StringBuilder();
            char[] cbuf = new char[2048];
            int num;
            
            while(-1 != (num=reader.read(cbuf))){
                buf.append(cbuf, 0, num);
            }
            
            String result = buf.toString();
            System.err.println("\nResponse from server after POST:\n" + result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void status(String ur){
    
        try {
            URL url = new URL(ur);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("Authorization","Basic "+ Base64.getEncoder().encodeToString("Muutu:Mu12rTq12".getBytes()));
            
            
            int i = http.getResponseCode();
            System.out.println(i);
            
            //response
            InputStreamReader reader = new InputStreamReader(http.getInputStream());
            
            StringBuilder buf = new StringBuilder();
            char[] cbuf = new char[2048];
            int num;
            
            while(-1 != (num=reader.read(cbuf))){
                buf.append(cbuf, 0, num);
            }
            
            String result = buf.toString();
            System.err.println("\nResponse from server after POST:\n" + result);
             
            http.disconnect();
        
        } catch (IOException e) {
            
        }
    }
}
