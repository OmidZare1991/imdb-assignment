package com.imdb.main.mapper;

import com.imdb.main.util.Utility;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseReaderMapper {
    @Named("getValues")
    default List<String> getValues(String filedValue) {
        return Utility.removeNullItem(filedValue);
    }

    @Named("parsedData")
    default String getParsedData(String data) {
        return Utility.parseMissingValues(data);
    }

    @Named("parsedDataBoolean")
    default Boolean getBooleanValue(String data) {
        return "1".equals(Utility.parseMissingValues(data));
    }
}
