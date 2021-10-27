package univ.lille.gl.sra1.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import univ.lille.gl.sra1.dao.OrderDao;
import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository
        extends OrderDao, CrudRepository<Order, Long>
{
 @Query("select o from Order o where o.customerId = ?1 order by o.createdOn DESC")
 public List<Order> findAllByCustomerIdOrderByCreatedOnDesc(String customerId);

 @Query("select o from Order o where o.currentStatus = ?1 and o.createdOn between ?2 and ?3")
 public List<Order> findOrdersByCurrentStatusAndCreatedOnBetween(String status, Date createdOnStart, Date createdOnEnd);

 @Query("select count(o) from Order o where o.currentStatus = ?1 and o.createdOn between ?2 and ?3")
 public int countOrdersByCurrentStatusAndCreatedOnBetween(String status, Date createdOnStart, Date createdOnEnd);

 @Query("select o from Order o where o.customerId = ?1 and o.currentStatus = ?2 order by o.createdOn ASC")
 public List<Order> findOrderToDeliver(String customerId, Status currentStatus);

 public int countAllByCurrentStatusAndCreatedOnAndHourDelivered(Status currentStatus, Date createdOn ,int hourDelivered);

 public List<Order> findAllByCurrentStatusAndCreatedOnAndHourDelivered(Status currentStatus, Date createdOn ,int hourDelivered);
 
 
 
 // Cherche les orders ayant un certain status
 
 public List<Order> findAllByCurrentStatus(Status currentStatus);
 
 // Cherche une order par id
 
 public Order findById(long id);

}
