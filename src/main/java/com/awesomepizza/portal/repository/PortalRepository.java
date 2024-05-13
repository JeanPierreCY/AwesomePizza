package com.awesomepizza.portal.repository;

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.awesomepizza.portal.model.AwesomePizzaOrder;

@Repository
public interface PortalRepository extends JpaRepository<AwesomePizzaOrder, BigInteger>{
	
	/*
	 * get all AwesomePizzaOrders which status is not COMPLETED
	 * @return List of AwesomePizzaOrders
	 */
	@Query("SELECT p FROM AwesomePizzaOrder p WHERE p.status <> 'COMPLETED' ")
	public ArrayList<AwesomePizzaOrder> findAllNotCompletedOrders();

	
}
