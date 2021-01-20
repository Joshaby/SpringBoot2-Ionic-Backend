package com.nelioalves.cursomc.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcesUtils {

    public static String ParamDecoder(String param) {
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }
    public static List<Integer> ParamToList(String param) {
        return Arrays.asList(param.split(","))
            .stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
