package com.crystalpizaa.api.service.interfaces;

import java.util.List;
import com.crystalpizaa.api.service.models.core.AddOn;

public interface AddOnService {

  AddOn Save(AddOn addOn);

  List<AddOn> GetAll();

  AddOn Get(int id);

  boolean Remove(int id);

}
