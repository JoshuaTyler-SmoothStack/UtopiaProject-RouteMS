package com.utopia;

import java.util.List;

import com.utopia.exeptions.RouteAlreadyExistsException;
import com.utopia.exeptions.RouteDoesNotExistException;
import com.utopia.models.Route;
import com.utopia.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes")
public class RouteController {
	
	@Autowired
	RouteService routeService;
	
	@GetMapping
	public ResponseEntity<List<Route>> findAllRoutes(){
		List<Route> routeList = routeService.findAllRoutes();
		if(routeList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(routeList, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Route> findRouteById(@PathVariable Integer id){
		Route theRoute =  routeService.findRouteById(id);
		if(theRoute == null ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(theRoute, HttpStatus.OK);
	}
	
	@GetMapping("/dest/{destination}")
	public ResponseEntity<List<Route>> findRouteByDestination(@PathVariable String destination){
		List<Route>routeList =  routeService.findRoutesByDestination(destination);
		if(routeList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(routeList, HttpStatus.OK);
	}
	
	@GetMapping("/orig/{origin}")
	public ResponseEntity<List<Route>> findRouteByOrigin(@PathVariable String origin){
		List<Route>routeList =  routeService.findRoutesByOrigin(origin);
		if(routeList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(routeList, HttpStatus.OK);
	}
	
	@GetMapping("/dest/{destination}/orig/{origin}")
	public ResponseEntity<Route> findRouteByDestinationAndOrigin(@PathVariable String destination, @PathVariable String origin){
		Route theRoute =  routeService.findRouteByDestinationAndOrigin(destination, origin);
		if(theRoute == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(theRoute, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Route> insert(@RequestBody Route route) {
		try {
			Route newRoute = routeService.insert(route);
			return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
		} catch(RouteAlreadyExistsException err) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Route> delete(@PathVariable Integer id) {
		try {
			routeService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch(RouteDoesNotExistException err) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}