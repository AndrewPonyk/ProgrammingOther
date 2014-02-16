package com.ap.behavioral.state;

public class Dispatched implements IOrderState
{
    private  OrderState _Parent;
    public void NewOrderPlaced() throws Exception
    {
        throw new Exception("OrderState has already been placed");
    }
    public Dispatched(OrderState OrderState)
    {
        _Parent = OrderState;
        this.Dispatch();
    }
    public void Dispatch()
    {
    	System.out.println("dispatched");
    }
    public void Register() throws Exception
    {
        throw new Exception("OrderState has already been registered");
    }
    public void Approve()
    {
        _Parent._CurrentState = new Approved(_Parent);
    }
} 
