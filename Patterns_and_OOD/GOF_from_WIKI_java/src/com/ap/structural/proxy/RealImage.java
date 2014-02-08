package com.ap.structural.proxy;

//on System A 
class RealImage implements Image {

  private String filename = null;
  /**
   * Constructor
   * @param FILENAME
   */
  public RealImage(final String FILENAME) { 
      filename = FILENAME;  //TO DO real image can be a singleton
      loadImageFromDisk();
  }

  /**
   * Loads the image from the disk
   */
  private void loadImageFromDisk() {
      System.out.println("Loading   " + filename);
  }

  /**
   * Displays the image
   */
  public void displayImage() { 
      System.out.println("Displaying " + filename); 
  }

}