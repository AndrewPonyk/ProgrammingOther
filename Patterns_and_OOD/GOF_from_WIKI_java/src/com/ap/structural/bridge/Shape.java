package com.ap.structural.bridge;

/** "Abstraction" */
abstract class Shape {
   protected DrawingAPI drawingAPI;
 
   protected Shape(DrawingAPI drawingAPI){
      this.drawingAPI = drawingAPI;
   }
 
   public abstract void draw();                             // low-level
   public abstract void resizeByPercentage(double pct);     // high-level
}