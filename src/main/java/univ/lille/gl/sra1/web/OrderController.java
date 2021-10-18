package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.lille.gl.sra1.dao.OrderDao;
import univ.lille.gl.sra1.repository.OrderRepository;


@Controller
@RequestMapping(path="/order")
public class OrderController {


    @Autowired
    OrderDao repo;

    @RequestMapping(path="/ofCustomer/{custId}.html")
    public String list(@PathVariable String custId, Model model) {

        // use repo to get orders of a customer
        // assign in model as "orders"
        // return order list view

        return "";
    }

}
