package com.ap.behavioral.visitor;

interface IVisitor{
	void Visit(OfficeBuilding building);
	void Visit(Floor floor);
	void Visit(Room room);
}
