package univ.lille.gl.sra1.dao;

import univ.lille.gl.sra1.model.Employee;
import univ.lille.gl.sra1.model.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> find(Integer customerId);
    public Order findWaiting();
    public void validateOrder();
    public int getNbDeliveredOrder();
    public int getNbDeliveredOrder(Employee e);
    public void init();
}