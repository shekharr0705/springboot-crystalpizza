package com.crystalpizaa.api.service.translators;


import com.crystalpizaa.api.dao.entities.ComponentType;
import com.crystalpizaa.api.service.models.core.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class OrderTranslator {

  public static List<com.crystalpizaa.api.dao.entities.OrderItem> toDaoEntity(
      List<OrderItem> serviceObjs,
      ComponentType type) {
    List<com.crystalpizaa.api.dao.entities.OrderItem> daoItems = new ArrayList<>();

    for (OrderItem item : serviceObjs) {
      com.crystalpizaa.api.dao.entities.OrderItem daoItem = new com.crystalpizaa.api.dao.entities.OrderItem();
      daoItem.setComponentId(item.getId());
      daoItem.setQuantity(item.getQuantity());
      daoItem.setType(type);
      daoItems.add(daoItem);
    }

    return daoItems;
  }
}
