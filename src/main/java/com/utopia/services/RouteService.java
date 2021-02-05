package com.utopia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Route> findRoutesByDestination(Integer id) {
		return routeRepository.findRoutesByDestination(id);
	}

	public List<Route> findRoutesByOrigin(Integer id) {
		return routeRepository.findRoutesByOrigin(id);
	}
	
	public List<Route> findRoutesByDestinationAndOrigin(Integer destinationId, Integer originId) {
		return routeRepository.findRoutesByDestinationAndOrigin(destinationId, originId);
	}
}