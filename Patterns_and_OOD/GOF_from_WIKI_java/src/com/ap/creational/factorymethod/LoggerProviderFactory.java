package com.ap.creational.factorymethod;

public class LoggerProviderFactory {
	
	public final static int Enterprise = 0;
	public final static int Log4Net = 1;
	
	// parametrised factory method
	public static ILogger GetLoggingProvider(int logProviders){
		switch (logProviders){

		case LoggerProviderFactory.Enterprise:
			return new EnterpriseLogger();
			
		case LoggerProviderFactory.Log4Net:
			return new Log4NetLogger();

		default:
			return new EnterpriseLogger();
		}
	}
}
