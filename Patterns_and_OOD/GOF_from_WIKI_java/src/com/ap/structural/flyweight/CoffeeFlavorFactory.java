package com.ap.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

//FlyweightFactory object
class CoffeeFlavorFactory {
  private Map<String, CoffeeFlavor> flavors = new HashMap<String, CoffeeFlavor>();

  public CoffeeFlavor getCoffeeFlavor(String flavorName) {
      CoffeeFlavor flavor = flavors.get(flavorName);
      if (flavor == null) {
          flavor = new CoffeeFlavor(flavorName);
          flavors.put(flavorName, flavor);
      }
      return flavor;
  }

  public int getTotalCoffeeFlavorsMade() {
      return flavors.size();
  }
}
