package com.ape.fgengine.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum KeyMappings {
    LEFT(65), DOWN(83), RIGHT(68), UP(-255),
    LP(85), MP(73), HP(79),
    LK(74), MK(75), HK(76);

    private static Map<Integer, KeyMappings> map = new HashMap<>();

    private Integer charCode;

    KeyMappings(Integer charCode) {
        this.charCode = charCode;
    }

    static {
        Arrays.stream(KeyMappings.values()).forEach(keyMapping -> KeyMappings.map.put(keyMapping.charCode, keyMapping));
    }
}
