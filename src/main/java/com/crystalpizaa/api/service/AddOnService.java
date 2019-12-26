package com.crystalpizaa.api.service;

import java.util.List;
import com.crystalpizaa.api.service.models.core.AddOn;

public interface AddOnService {

  AddOn save(AddOn addOn);

  List<AddOn> getAll();

  AddOn get(int id);

  boolean remove(int id);

}
