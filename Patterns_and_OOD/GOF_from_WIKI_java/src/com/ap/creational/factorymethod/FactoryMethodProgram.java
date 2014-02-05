package com.ap.creational.factorymethod;

public class FactoryMethodProgram {

	public static void main(String[] args) {	
		
		//int providerType = GetTypeOfLoggingProviderFromConfigFile(); 
		int providerType = 1; // hardcode
		 ILogger logger = LoggerProviderFactory.GetLoggingProvider(providerType); 

		 logger.LogMessage("Hello Factory Method Design Pattern."); 
	}

}
