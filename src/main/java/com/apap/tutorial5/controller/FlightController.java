package com.apap.tutorial5.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		ArrayList<FlightModel> flightList = new ArrayList<>();
		pilot.setPilotFlight(flightList);
		flightList.add(new FlightModel());
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"save"})
	private String addFligthSubmit(@ModelAttribute PilotModel pilot, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for(FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(archive);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(PilotModel pilot, BindingResult bindingResult, Model model) {
		if (pilot.getPilotFlight() ==  null) {
			pilot.setPilotFlight(new ArrayList<FlightModel>());
		}
		pilot.getPilotFlight().add(new FlightModel());
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
	
	@RequestMapping(value="/flight/delete/", method=RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value="/flight/update/{id}", method=RequestMethod.GET)
	private String updateFlight(@PathVariable(value="id") Long id, Model model) {
		FlightModel flight = flightService.getFlight(id);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flight.getPilot().getLicenseNumber());
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value="/flight/update", method=RequestMethod.POST)
	private String suksesUpdate(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight, flight.getId());
		return "suksesUpdate";
	}
	
	@RequestMapping (value = "/flight/view")
	private String viewFlight (@RequestParam ("flightNumber") String flightNumber, Model model) {
		List <FlightModel> Flights = new ArrayList();
		List <FlightModel> allFlights = flightService.allFlight();
		
		for (FlightModel fli: allFlights) {
			if (fli.getFlightNumber().equals(flightNumber)) {
				Flights.add(fli);
			}
		}
		if (Flights.size() == 0){
			return "error";
		}
		model.addAttribute("flightNumber", flightNumber);
		model.addAttribute("flights", Flights);
		return "viewFlight";		
	}
}
