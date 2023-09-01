package com.imdb.main.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Utility {

    private Utility() {
    }
    public static String parseMissingValues(String endYear) {
        if (endYear.equals("\\N")) {
            return StringUtils.EMPTY;
        } else {
            return endYear;
        }
    }

    public static List<String> removeNullItem(String token)
    {
       return Arrays.stream(token.split(",")).filter(item->!item.equals("\\N")).toList();
    }
}
