package com.ap.creational.factorymethod;

public class EnterpriseLogger implements ILogger{

	@Override
	public void LogMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("Enterprise logger : " + message);
	}

	@Override
	public void LogError(String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void LogVerboseInformation(String message) {
		// TODO Auto-generated method stub
	}
}
