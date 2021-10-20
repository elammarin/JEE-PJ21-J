package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.lille.gl.sra1.dao.OrderDao;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.OrderRepository;

import java.util.List;


@Controller
@RequestMapping(path="/order")
public class OrderController {


    @Autowired
    OrderRepository repoOrder;


    @RequestMapping(path="/ofCustomer/{custId}.html")
    public String list(@PathVariable String custId, Model model) {
        List<Order> orders = repoOrder.findAllByCustomerIdOrderByCreatedOnDesc(custId);
        model.addAttribute("orders", orders);
        return "order_list";
    }

    @RequestMapping("/")
    public String welcome(){
        //jsp welcome need to redirect to /order/ofCustomer/{custId}.html given the customer id in the field of the form.
        return "welcome";
    }


    }
