package com.crystalpizaa.api.service.translators;

import java.util.ArrayList;
import java.util.List;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.core.AddOnType;

public class AddOnTranslator {

  public static AddOn toServiceModel(com.crystalpizaa.api.dao.entities.AddOn daoObj) {
    if(daoObj == null) {
      return null;
    }
    AddOn addOn = new AddOn();
    addOn.setId(daoObj.getId());
    addOn.setName(daoObj.getName());
    addOn.setDescription(daoObj.getDescription());
    addOn.setPrice(daoObj.getPrice());
    addOn.setType(toServiceModel(daoObj.getType()));
    addOn.setSize(CommonTranslator.toServiceModel(daoObj.getSize()));

    return addOn;
  }

  public static List<AddOn> toServiceModel(List<com.crystalpizaa.api.dao.entities.AddOn> daoObjs) {

    List<AddOn> results = new ArrayList<AddOn>();

    for (com.crystalpizaa.api.dao.entities.AddOn daoObj : daoObjs) {
      results.add(toServiceModel(daoObj));
    }
    return results;
  }

  public static com.crystalpizaa.api.dao.entities.AddOn toDaoModel(AddOn serviceObj) {
    com.crystalpizaa.api.dao.entities.AddOn addOn = new com.crystalpizaa.api.dao.entities.AddOn();
    addOn.setId(serviceObj.getId());
    addOn.setName(serviceObj.getName());
    addOn.setDescription(serviceObj.getDescription());
    addOn.setPrice(serviceObj.getPrice());
    addOn.setType(toDaoModel(serviceObj.getType()));
    addOn.setSize(CommonTranslator.toDaoModel(serviceObj.getSize()));
    return addOn;
  }


  private static AddOnType toServiceModel(com.crystalpizaa.api.dao.entities.AddOnType daoObj) {
    switch (daoObj) {
      case Beverage:
        return AddOnType.Beverage;
      case Desert:
        return AddOnType.Desert;
    }
    return AddOnType.Desert;
  }

  private static com.crystalpizaa.api.dao.entities.AddOnType toDaoModel(AddOnType serviceObj) {
    switch (serviceObj) {
      case Beverage:
        return com.crystalpizaa.api.dao.entities.AddOnType.Beverage;
      case Desert:
        return com.crystalpizaa.api.dao.entities.AddOnType.Desert;
    }
    return com.crystalpizaa.api.dao.entities.AddOnType.Desert;
  }

}
