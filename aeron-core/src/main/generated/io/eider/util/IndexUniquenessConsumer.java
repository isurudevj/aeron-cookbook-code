// EIDER HELPER GENERATED BY EIDER AT 2020-06-21T15:05:28.452982Z. DO NOT MODIFY
package io.eider.util;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface IndexUniquenessConsumer<T> {
  /**
   * Accepts a type<T> value and checks that the index is valid
   */
  boolean isUnique(T t);
}
