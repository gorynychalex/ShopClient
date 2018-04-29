package ru.popovich.shopclient.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Converter
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
