package com.lux.ge.stat.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lux.ge.stat.model.StatData;
import com.lux.ge.stat.model.TimeseriesData;
import com.lux.ge.stat.model.TimeseriesType;
import com.lux.ge.stat.repository.StatDataRepository;

@Service
public class StatServiceImpl implements StatService {
	
	@Autowired
	private StatDataRepository statDataRepository;

	@Override
	public long calculateMessagesCount(Iterable<TimeseriesData> data, String fileName) {
		Long count = StreamSupport.stream(data.spliterator(), false)
		.filter(ts -> ts.getFileName().equals(fileName)).count();
		return count;
	}

	@Override
	public int calculateMaxTemperature(Iterable<TimeseriesData> data) {
		TimeseriesData tsData = StreamSupport.stream(data.spliterator(), false)
				.filter(ts -> ts.getType().equals(TimeseriesType.TEMPERATURE.getValue()))
				.max(Comparator.comparingDouble(TimeseriesData::getValue)).get();
		return tsData.getValue().intValue();
	}

	@Override
	public float calculateAverageVoltage(Iterable<TimeseriesData> data) {
		Double average = StreamSupport.stream(data.spliterator(), false)
				.filter(ts -> ts.getType().equals(TimeseriesType.VOLTAGE.getValue())).mapToDouble(ts -> ts.getValue())
				.average().getAsDouble();
		return average.floatValue();
	}

	@Override
	public StatData save(StatData data) {
		return statDataRepository.save(data);
	}

	@Override
	public List<StatData> findAll() {
		Iterable<StatData> statData = statDataRepository.findAll();
		List<StatData> list = StreamSupport.stream(statData.spliterator(), false).collect(Collectors.toList());
		System.err.println("#### list = " + list.toString());
		return list;
	}

}
