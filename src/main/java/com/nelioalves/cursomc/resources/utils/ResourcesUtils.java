package com.nelioalves.cursomc.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe com métodos úteis estáticos usado na camada de resource
 * @author José Henrique
 */
public class ResourcesUtils {

    /**
     * Decodifica um parâmetro de URL para UTF-8
     * @param param parâmetro de um URL
     * @return parâmetro decodificado
     */
    public static String ParamDecoder(String param) {
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }

    /**
     * Converte uma lista de parâmetros de String para List<String>
     * @param param lista de parâmetros em String
     * @return parâmetros em um List<String>
     */
    public static List<Integer> ParamToList(String param) {
        return Arrays.asList(param.split(","))
            .stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
