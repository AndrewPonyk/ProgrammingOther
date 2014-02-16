package com.ap.behavioral.state;

public class NewOrder implements IOrderState
{
    private  OrderState _Parent;
    public NewOrder(OrderState OrderState)
    {
        _Parent = OrderState;
        this.NewOrderPlaced();
       
    }
    public boolean IsDispatched;

    public boolean isIsDispatched() {
		return false;
	}
	public void NewOrderPlaced()
    {
    	System.out.println("NewOrderPlaced");
    }
    public void Dispatch()
    {
        _Parent._CurrentState = new Dispatched(_Parent);
    }
    public void Register()
    {
        _Parent._CurrentState = new Registered(_Parent);
    }
    public void Approve()
    {
        _Parent._CurrentState = new Approved(_Parent);
    }
}
