package com.ap.behavioral.state;

public interface IOrderState{
    void NewOrderPlaced() throws Exception;
    void Register() throws Exception;
    void Dispatch() throws Exception;
    void Approve() throws Exception;
}