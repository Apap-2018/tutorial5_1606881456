package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	//method untuk delete flight
	void deleteFlight(Long flightId);
	//method untuk update flight
	void updateFlight (FlightModel flight, long id);
	FlightModel findFlightByFlightNumber (String flightNumber);
	FlightModel getFlight (long id);
	//method untuk mengambil semua flight
	List<FlightModel> allFlight();
}
