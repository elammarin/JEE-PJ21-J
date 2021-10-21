package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import univ.lille.gl.sra1.dao.OrderDao;
import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.OrderRepository;

import java.util.List;


@Controller
@RequestMapping(path="/order")
public class OrderController {


    @Autowired
    OrderRepository repoOrder;

    @PostMapping(path="ordermanager.html")
    public String createRole(@RequestParam("customerID") String valueOne){
        String customerId = valueOne;
        System.out.println(customerId);
        return "redirect:/ofCustomer/"+customerId+".html";
    }

    @GetMapping(path = "/welcome.html", produces = "text/html")
    public String initOrders(){
        Order o1 = new Order();
        o1.setAmount(300);
        o1.setCurrentStatus(Status.ORDERED);
        o1.setCustomerId("12331");
        repoOrder.save(o1);
        Order o = new Order();
        o.setAmount(400);
        o.setCurrentStatus(Status.READY_TO_DELIVER);
        o.setCustomerId("12332");
        repoOrder.save(o);
        return "welcome";
    }

    @GetMapping(path="/ofCustomer/{custId}.html", produces = "text/html")
    public String list(@PathVariable String custId, Model model) {
        List<Order> orders = repoOrder.findAllByCustomerIdOrderByCreatedOnDesc(custId);
        model.addAttribute("orders", orders);
        return "order_list";
    }
    }
