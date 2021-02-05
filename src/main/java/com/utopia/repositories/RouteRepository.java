package com.utopia.repositories;

import java.util.List;

import com.utopia.models.Route;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

	@Query(value = "SELECT * FROM route", nativeQuery = true)
	List<Route> findAllRoutes();

	@Query(value = "SELECT * FROM route WHERE id = ?1", nativeQuery = true)
	Route findRouteById(Integer id);

	@Query(value = "SELECT * FROM route WHERE destination_id = ?1", nativeQuery = true)
	List<Route> findRoutesByDestination(Integer id);

	@Query(value = "SELECT * FROM route WHERE origin_id = ?1", nativeQuery = true)
	List<Route> findRoutesByOrigin(Integer id);

	@Query(value = "SELECT * FROM route WHERE destination_id = ?1 AND origin_id = ?2", nativeQuery = true)
	List<Route> findRoutesByDestinationAndOrigin(Integer destinationId, Integer originId);
}