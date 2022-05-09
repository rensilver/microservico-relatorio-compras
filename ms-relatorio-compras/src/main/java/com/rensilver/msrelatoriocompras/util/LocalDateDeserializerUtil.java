package com.rensilver.msrelatoriocompras.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializerUtil extends StdDeserializer<LocalDate> {

    public LocalDateDeserializerUtil() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(jsonParser.readValueAs(String.class), format);
    }
}
