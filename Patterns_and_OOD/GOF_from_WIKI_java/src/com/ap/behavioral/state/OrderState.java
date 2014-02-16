package com.ap.behavioral.state;

public class OrderState
{
    public IOrderState _CurrentState;
    public OrderState(){
        _CurrentState = new NewOrder(this);
    }
    public void Dispatch() throws Exception{
        _CurrentState.Dispatch();
    }
    public void Register() throws Exception{
        _CurrentState.Register();
    }
    public void Approve() throws Exception{
        _CurrentState.Approve();
    }
} 