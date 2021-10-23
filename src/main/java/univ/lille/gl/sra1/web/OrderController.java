package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.OrderRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
        return "redirect:/order/ofCustomer/"+customerId+".html";
    }

    @GetMapping(value = "/init.html", produces = "text/html")
    public String initOrders(){
        Order o1 = new Order();
        o1.setCreatedOn(new Date());
        o1.setAmount(300);
        o1.setCustomerId("12331");
        o1.setCurrentStatus(Status.ORDERED);
        repoOrder.save(o1);
        Order o = new Order();
        o.setCreatedOn(new Date());
        o.setAmount(400);
        o.setCurrentStatus(Status.READY_TO_DELIVER);
        o.setCustomerId("12332");
        repoOrder.save(o);
        return "init";
    }

    @GetMapping(path = "/welcome.html", produces = "text/html")
    public String welcome(){
        return "welcome";
    }

    @GetMapping(path="/ofCustomer/{custId}.html", produces = "text/html")
    public String list(@PathVariable String custId, Model model) {
        //List<Order> orders = repoOrder.findAllByCustomerIdOrderByCreatedOnDesc(custId);
        Order order = null;
        try {
            order = repoOrder.findOrderToDeliver(custId, Status.READY_TO_DELIVER).get(0);
        }
        catch (Exception e){
            System.out.println("no order found !");
        }
        model.addAttribute("order", order);
        return "order_list";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "{id}/ticket.html", produces = "text/html")
    public String getTicket(@PathVariable("id") long id, Model model, HttpServletRequest r){
        int size;
        List<Long> dock;
        int ticket;
        // si les quais n'existent pas je crée des quais par default
        if (r.getSession().getAttribute("dockSize")==null) {
            size = 30;
            dock = new ArrayList<Long>();
            r.getSession().setAttribute("dockSize", size);
        }
        //sinon je récuper les quais
        else {
            size = (int) r.getSession().getAttribute("dockSize");
            dock = (List<Long>) r.getSession().getAttribute("dock");
        }
        //si les quais sont pleins on ne peut pas donner de numéro de quai au client
        if (size == dock.size()){
            ticket = -1;
        }
        //sinon on peut ajouter donner un numero d'un quai dispo
        else{
            //si un quai ne contient pas deja la commande
            if (!dock.contains(id)){
                //si un quai entre plusieurs quais occupés contient un emplacement vide.
                // L'employé devra remplacé l'id de la commande par -1 quand il aura delivré une commande
                // depuis ce nuero de quai
                if (dock.contains(-1)){
                    int index = dock.indexOf(-1);
                    dock.add(index, id);
                    ticket = index;
                }
                //sinon on met la commande dans un quai à la suite de ceux qui sont deja occupés
                else
                {
                ticket = dock.size();
                dock.add(id);
                }
            }
            //si une commande est déja a récuperer dans un quai on redonne ce même numéro de quai au client;
            else{
                ticket = dock.indexOf(id);
            }
        }
        //ajout de la liste des quais à travers la session, l'employé et l'admin pourront visualiser
        // cette attribut pour confirmer une commande et liberer un quai pout l'employé
        // et ajouter modifier ou supprimer des quais pour l'admin
        r.getSession().setAttribute("dock", dock);
        // je renvoi à travers le modèle l'attribut ticket qui contient le numero de quai
        model.addAttribute("ticket", ticket);
        return "ticket";
    }
    }
