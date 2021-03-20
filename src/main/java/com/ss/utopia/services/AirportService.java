package com.ss.utopia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.utopia.exceptions.AirportNotFoundException;
import com.ss.utopia.models.Airport;
import com.ss.utopia.repositories.AirportRepository;

@Service
public class AirportService {

	@Autowired
	private AirportRepository airportRepository;

	public Airport findByIataId(String airportIataId) throws AirportNotFoundException {
		Optional<Airport> optionalAirpot = airportRepository.findById(airportIataId);
		if(!optionalAirpot.isPresent()) {
			throw new AirportNotFoundException("No airport with IATA code: " + airportIataId + " exist!");
		}
		return optionalAirpot.get();
	}
}