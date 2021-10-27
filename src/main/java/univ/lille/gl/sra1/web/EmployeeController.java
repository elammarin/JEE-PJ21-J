package univ.lille.gl.sra1.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Employee;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.EmployeeRepository;
import univ.lille.gl.sra1.repository.OrderRepository;

@Controller
@Scope("session")
@RequestMapping(path="/employee")
//@SessionAttributes(value="employeeXX", types={Employee.class})
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private OrderRepository orderRepository;

	private Employee employee;
	
	
	
	@GetMapping(value="/init.html", produces="text/html")
	public String initEmployees() {
		// Ajout de l'employé 1
		
		Employee e1 = new Employee();
		e1.setFirstname("Rayane");
		e1.setLastname("Hamani");
		e1.setPassword("RayaneHamani");
		employeeRepository.save(e1);
		
		
		// Ajout de l'employé 2
		
		Employee e2 = new Employee();
		e2.setFirstname("Razine");
		e2.setLastname("Hallil");
		e2.setPassword("RazineHallil");
		employeeRepository.save(e2);
		
		
		// Ajout de l'employé 3
		
		Employee e3 = new Employee();
		e3.setFirstname("Nordine");
		e3.setLastname("ElAmmari");
		e3.setPassword("NordineElAmmari");
		employeeRepository.save(e3);
		
		
		// Ajout de l'employé 4
		
		Employee e4 = new Employee();
		e4.setFirstname("Cyria");
		e4.setLastname("Amara");
		e4.setPassword("CyriaAmara");
		employeeRepository.save(e4);
		
		return "employee/init";
	}
	
	

	
	
	@GetMapping(value="/connect.html")
	public String getUser(Model model) {
		List<Employee> employees = employeeRepository.findAllEmployees();
		
		model.addAttribute("employees", employees);
		
		return "employee/connect";
	}
	
	
	
	@PostMapping(value="/connect.html")
	public String postUser(Model model, @ModelAttribute("employeeId") long employeeId, HttpServletRequest r) {
		employee = employeeRepository.findById(employeeId);
		
		System.out.println(employee.getLastname() + " " + employee.getFirstname());
		
		return getReadyOrders(model, r);
	}
	
	
	
	@GetMapping(value="/ready_orders.html")
	public String getReadyOrders(Model model, HttpServletRequest r) {
		// Retourne à la page de connexion si la session n'existe pas
		
//		System.out.println(employee.getLastname() + " " + employee.getFirstname());
		
		if(employee == null)
			return getUser(model);
		
		
		// Initialise la liste des commandes
		
		//List<Order> orders = orderRepository.findAllByCurrentStatus(Status.READY_TO_DELIVER);
		List<Long> ids = (List<Long>) r.getSession().getAttribute("dock");
		List<Order> orders = new ArrayList<>();
		for (Long elmt : ids){
			if (elmt.intValue()!=-1)
			orders.add(orderRepository.findById(elmt));
		}
		
		// Ajoute les deux listes au modèle
		
		model.addAttribute("orders", orders);
		
		
		return "employee/ready_orders";
	}
	
	
	
	@Transactional
	@PostMapping(value="/ready_orders.html")
	public String deliverReadyOrders(Model model, @ModelAttribute("orderId") long orderId, HttpServletRequest req) {
		// Retourne à la page de connexion si la session n'existe pas
		
		if(employee == null)
			return getUser(model);
				
		
		// Récupère l'employé et la commande sélectionnés
		
		Order order = orderRepository.findById(orderId);
		
		List<Long> l = (List<Long>) req.getSession().getAttribute("dock");
		int indexToDelete = l.indexOf(orderId);
		l.set(indexToDelete, Long.valueOf(-1));
		req.getSession().setAttribute("dock", l);
		// Récupère la date actuelle

		int hourDelivered = Math.toIntExact(((new Date().getTime() / 1000 / 60 / 60) + 2) % 24);
		

		// Met à jour les informations de la commande
		
		order.setCurrentStatus(Status.DELIVERED);
		order.setHourDelivered(hourDelivered);
		order.setDeliveredBy(employee.getId());
		
		orderRepository.save(order);
		
				
		return getReadyOrders(model, req);
	}
	
	
	@PostMapping(value="/disconnect.html")
	public String disconnect(Model model) {
		employee = null;
		
		return "employee/disconnect";
	}
	
}
