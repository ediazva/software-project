package org.unsa.model.exceptions;

public class ItemNotFoundException extends IllegalArgumentException {
  public ItemNotFoundException(String msg) {
    super(msg);
  }
}
