package com.ap.behavioral.state;

public class Approved implements IOrderState
{
    private  OrderState _Parent;
    public Approved(OrderState OrderState)
    {
        _Parent = OrderState;
        this.Approve();
    }
    public void NewOrderPlaced() throws Exception
    {
        throw new Exception("OrderState has already been placed");
    }
    public void Dispatch()
    {
        _Parent._CurrentState = new Dispatched(_Parent);
    }
    public void Register() throws Exception
    {
        throw new Exception("OrderState has already been registered");
    }
    public void Approve()
    {
    	System.out.println("Approved");
    }
}
