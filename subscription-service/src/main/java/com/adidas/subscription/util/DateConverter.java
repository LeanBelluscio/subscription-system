package com.adidas.subscription.util;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.springframework.boot.jackson.JsonComponent;

/**
 * @author lbelluscio
 * Custom Date converter, it handle the Dates from the json reques/response and parse or convert to text the 
 * dates according to the configure pattern yyyy-MM-dd
 */
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
