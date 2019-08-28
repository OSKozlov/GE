package com.lux.ge.stat.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToTimestampConverter implements Converter<java.util.Date, java.sql.Timestamp> {

	@Override
	public Timestamp convert(Date source) {
		return source == null ? null : new java.sql.Timestamp(source.getTime());
	}

}