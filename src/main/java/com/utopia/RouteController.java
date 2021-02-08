package com.utopia;

import java.util.List;

import com.utopia.exeptions.AirportDoesNotExistException;
import com.utopia.exeptions.RouteAlreadyExistsException;
import com.utopia.exeptions.RouteDoesNotExistException;
import com.utopia.models.Route;
import com.utopia.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes", produces = { "application/json", "application/xml", "text/xml"}, consumes = MediaType.ALL_VALUE)
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
	
	@GetMapping("/id")
	public ResponseEntity<Route> findRouteById(@RequestParam Integer id){
		Route theRoute =  routeService.findRouteById(id);
		if(theRoute == null ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(theRoute, HttpStatus.OK);
	}
	
	@GetMapping("/destination")
	public ResponseEntity<List<Route>> findRoutesByDestination(@RequestParam String dest){
		List<Route>routeList =  routeService.findRoutesByDestination(dest);
		if(routeList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(routeList, HttpStatus.OK);
	}
	
	@GetMapping("/origin")
	public ResponseEntity<List<Route>> findRoutesByOrigin(@RequestParam String orig){
		List<Route>routeList =  routeService.findRoutesByOrigin(orig);
		if(routeList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(routeList, HttpStatus.OK);
	}
	
	@GetMapping("/destOrig")
	public ResponseEntity<Route> findRouteByDestinationAndOrigin(@RequestParam String dest, @RequestParam String orig){
		Route theRoute =  routeService.findRouteByDestinationAndOrigin(dest, orig);
		if(theRoute == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(theRoute, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Route> insert(@RequestBody Route route) {
		try {
			Route newRoute = routeService.insert(route);
			return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
		}	catch (AirportDoesNotExistException err) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	catch(RouteAlreadyExistsException err) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		} 
	}
	
	@DeleteMapping("/del")
	public ResponseEntity<Route> delete(@RequestParam Integer id) {
		try {
			routeService.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch(RouteDoesNotExistException err) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Route> update(@RequestBody Route route) {
		try {
			Route newRoute = routeService.update(route);
			return new ResponseEntity<>(newRoute, HttpStatus.OK);
		}	catch (AirportDoesNotExistException err) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} 	catch (RouteDoesNotExistException err) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}