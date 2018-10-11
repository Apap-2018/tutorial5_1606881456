package com.apap.tutorial5.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;

	@Override
	public void addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.save(flight);
		
	}

	@Override
	public void deleteFlight(Long flightId) {
		// TODO Auto-generated method stub
		flightDb.deleteById(flightId);
	}

	@Override
	public void updateFlight(FlightModel flight, long id) {
		// TODO Auto-generated method stub
		FlightModel newFlight = flightDb.findFlightById(id);
		newFlight.setDestination(flight.getDestination());
		newFlight.setOrigin(flight.getOrigin());
		newFlight.setFlightNumber(flight.getFlightNumber());
		newFlight.setTime(flight.getTime());
		
	}

	@Override
	public FlightModel findFlightByFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return flightDb.findFlightByFlightNumber(flightNumber);
	}

	@Override
	public FlightModel getFlight(long id) {
		// TODO Auto-generated method stub
		return flightDb.findFlightById(id);
	}

	@Override
	public List<FlightModel> allFlight() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}
}