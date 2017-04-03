package com.codegans.jdbc.check;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 02/04/2017 16:40
 */
public interface Check<V> {
    String run(V value) throws Exception;
}
