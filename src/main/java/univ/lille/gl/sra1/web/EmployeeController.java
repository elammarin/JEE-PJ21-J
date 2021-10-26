package univ.lille.gl.sra1.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path="/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	
	
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
	
	
	
	@GetMapping(value="/ready_orders.html")
	public String getReadyOrders(Model model) {
		// Initialise la liste des employés et la liste des commandes
		
		List<Employee> employees = employeeRepository.findAllEmployees();
		List<Order> orders = orderRepository.findAllByCurrentStatus(Status.READY_TO_DELIVER);;
		
		
		// Ajoutes les deux listes au modèle
		
		model.addAttribute("employees", employees);
		model.addAttribute("orders"   , orders);
		
		
		return "employee/ready_orders";
	}
	
	
	
	@Transactional
	@PostMapping(value="/ready_orders.html")
	public String deliverReadyOrders(Model model, @ModelAttribute("employeeId") long employeeId, @ModelAttribute("orderId") long orderId) {
		// Récupère l'employé et la commande sélectionnés
		
		Order order = orderRepository.findById(orderId);
		Employee employee = employeeRepository.findById(employeeId);
		
		
		// Récupère la date actuelle
		
		int hourDelivered = Math.toIntExact(((new Date().getTime() / 1000 / 60 / 60) + 2) % 24);
		

		// Met à jour les informations de la commande
		
		order.setCurrentStatus(Status.DELIVERED);
		order.setHourDelivered(hourDelivered);
		order.setDeliveredBy(employee.getId());
		
		orderRepository.save(order);
		
				
		return getReadyOrders(model);
	}
	
	
	
	public String disconnect(Model model) {
		// TODO quand un employé se déconnecte,
		//      les orders qu'il devait livrer
		//      peuvent être livré par d'autres
		//      employés.
		
		// TODO créer "Employee deliveredBy" dans Order
		//      créer "List<Order> toDeliver" dans Employee
		//      linker les deux avec une relation
		
		return "";
	}
	
}
