package com.crystalpizaa.api.service.translators;

import com.crystalpizaa.api.service.models.core.Size;

public class CommonTranslator {

  public static Size toServiceModel(com.crystalpizaa.api.dao.entities.Size daoObj)
  {
    switch (daoObj)
    {
      case Medium:
        return Size.Medium;
      case Large:
        return Size.Large;
    }
    return Size.Small;
  }

  public static com.crystalpizaa.api.dao.entities.Size toDaoModel(Size serviceObj)
  {
    switch (serviceObj)
    {
      case Medium:
        return com.crystalpizaa.api.dao.entities.Size.Medium;
      case Large:
        return com.crystalpizaa.api.dao.entities.Size.Large;
    }
    return com.crystalpizaa.api.dao.entities.Size.Small;
  }
}
