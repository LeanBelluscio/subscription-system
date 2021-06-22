package com.adidas.email.util;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
    


    public static class Deserialize extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(com.fasterxml.jackson.core.JsonParser jp, DeserializationContext ctxt) throws IOException {
            try {
                String dateAsString = jp.getText();
                if (dateAsString==null) {
                    return null;
                } else {
                    return sdf1.parse(dateAsString);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
