package univ.lille.gl.sra1.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import univ.lille.gl.sra1.model.Employee;
import univ.lille.gl.sra1.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Qualifier("OrderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    EntityManager em;

    public List<Order> find(Integer customerId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("customerId"), customerId));
        TypedQuery<Order> typed = em.createQuery(criteriaQuery);
        try {
            return typed.getResultList();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public Order findWaiting(){
        //TODO
        return new Order();
    }

    public void validateOrder(){
        //TODO
        return;
    }

    @Override
    public int getNbDeliveredOrder() {
        //TODO
        return 0;
    }

    @Override
    public int getNbDeliveredOrder(Employee e) {
        //TODO
        return 0;
    }

    @Override
    public void init() {
        Order o1 = new Order();
        o1.setAmount(300);
        o1.setCurrentStatus(Status.ORDERED);
        o1.setCustomerId("12331");
        o1.setId(1);
        em.persist(o1);
        Order o = new Order();
        o.setAmount(300);
        o.setCurrentStatus(Status.READY_TO_DELIVER);
        o.setCustomerId("12332");
        o.setId(1);
        em.persist(o);
        return;
    }
}




