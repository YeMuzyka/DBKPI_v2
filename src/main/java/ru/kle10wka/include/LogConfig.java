package ru.kle10wka.include;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

public class LogConfig {
	private static Properties logProperty = new Properties();
	private String logFile;
	
	public LogConfig(String logFile){
		this.logFile = logFile;
		try{
			logProperty.load(new FileInputStream(this.logFile));
			PropertyConfigurator.configure(logProperty);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}