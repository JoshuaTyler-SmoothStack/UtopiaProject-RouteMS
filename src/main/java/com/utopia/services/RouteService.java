package com.utopia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utopia.exeptions.RouteAlreadyExistsException;
import com.utopia.exeptions.RouteDoesNotExistException;
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

	public Route insert(Route route) throws RouteAlreadyExistsException {
		try {
			return routeRepository.save(route);
		} catch(Exception err) {
			throw new RouteAlreadyExistsException("This route already exists");
		}
	}
	
	public void delete(Integer id) throws RouteDoesNotExistException {
		try {
			routeRepository.deleteById(id);
		} catch(Exception err) {
			throw new RouteDoesNotExistException("This route does not exist.");
		}
	}
	
}