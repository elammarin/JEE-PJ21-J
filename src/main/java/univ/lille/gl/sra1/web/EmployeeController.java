package univ.lille.gl.sra1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String deliverReadyOrders(Model model) {
		List<Employee> employees = null;
		List<Order> orders = null;
		
		employees = employeeRepository.findAllEmployees();
		orders    = orderRepository.findAllByCurrentStatus(Status.READY_TO_DELIVER);
		
		model.addAttribute("employees", employees);
		model.addAttribute("orders"   , orders);
		
		return "employee/ready_orders";
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
