package com.utopia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utopia.exeptions.AirportDoesNotExistException;
import com.utopia.exeptions.RouteAlreadyExistsException;
import com.utopia.exeptions.RouteDoesNotExistException;
import com.utopia.models.Airport;
import com.utopia.models.Route;
import com.utopia.repositories.RouteRepository;


@Service
public class RouteService {
	
	@Autowired
	private RouteRepository routeRepository;

	public List<Route> findAllRoutes() {
		return routeRepository.findAllRoutes();
	}
	
	public Route findRouteById(Integer id) {
		return routeRepository.findRouteById(id);
	}
	
	public List<Route> findRoutesByDestination(String destination) {
		return routeRepository.findRoutesByDestination(destination);
	}

	public List<Route> findRoutesByOrigin(String destination) {
		return routeRepository.findRoutesByOrigin(destination);
	}
	
	public Route findRouteByDestinationAndOrigin(String destination, String origin) {
		return routeRepository.findRouteByDestinationAndOrigin(destination, origin);
	}

	public Route insert(Route route) throws RouteAlreadyExistsException, AirportDoesNotExistException {
		Airport dest = routeRepository.findAirportById(route.getDestination().getAiportCode());
		Airport orig = routeRepository.findAirportById(route.getOrigin().getAiportCode());
			
		if(dest == null || orig == null)
			throw new AirportDoesNotExistException("Airport(s) does not exist");
		
		route.setDestination(dest);
		route.setOrigin(orig);
		
		try {
			return routeRepository.save(route);
		}
		catch(Exception err) {
			throw new RouteAlreadyExistsException("This route already exists");
		}
	}

	public void deleteById(Integer id) throws RouteDoesNotExistException {
		if(findRouteById(id) == null) 
			throw new RouteDoesNotExistException("This route does not exist.");
		routeRepository.deleteById(id);
		
	}

}