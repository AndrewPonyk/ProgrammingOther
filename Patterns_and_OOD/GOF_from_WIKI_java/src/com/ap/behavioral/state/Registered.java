package com.ap.behavioral.state;

public class Registered implements IOrderState
{
    private  OrderState _Parent;
    public void NewOrderPlaced() throws Exception
    {
        throw new Exception("OrderState has already been placed");
    }
    public Registered(OrderState OrderState)
    {
        _Parent = OrderState;
        this.Register();
    }
    public void Dispatch() throws Exception
    {
        throw new Exception("OrderState has not been registered yet");
    }
    public void Register()
    {
    	System.out.println("Registered");
    }
    public void Approve()
    {
        _Parent._CurrentState = new Approved(_Parent);
    }
}
