package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.OrderRepository;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //extraire l'heure à partir d'une date 
    public int dateToHour(Date date) {
    	int hour;
    	hour = Math.toIntExact((date.getTime() / 1000 / 60 / 60) % 24) + 2;
    	if(hour==24) {
    		hour = 0;
    	}else if(hour==25){
    		hour = 1;
    	}
    	
    	return hour; 	
    }
    
    
    @GetMapping(value = "/init.html", produces = "text/html")
    public String initOrders(){
        Order o1 = new Order();
        Date d1 = new Date();
        o1.setCreatedOn(d1);
        int hourD1 = dateToHour(d1);
        System.out.println(d1);
        o1.setHourDelivered(hourD1);
        o1.setAmount(300);
        o1.setCustomerId("12331");
        o1.setDeliveredBy(Math.toIntExact(1));
        o1.setCurrentStatus(Status.ORDERED);
        repoOrder.save(o1);
        
        Order o = new Order();
        Date d = new Date();
        o.setCreatedOn(d);
        int hourD = dateToHour(d);
        System.out.println(d);
        o.setHourDelivered(hourD);
        o.setAmount(400);
        o.setCurrentStatus(Status.READY_TO_DELIVER);
        o.setCustomerId("123");
        o.setDeliveredBy(Math.toIntExact(1));
        repoOrder.save(o);

        Order o10 = new Order();
        Date d10 = new Date();
        o10.setCreatedOn(d10);
        int hourD10 = dateToHour(d10);
        System.out.println(d10);
        o10.setHourDelivered(hourD10);
        o10.setAmount(400);
        o10.setCurrentStatus(Status.READY_TO_DELIVER);
        o10.setCustomerId("1234");
        o10.setDeliveredBy(Math.toIntExact(1));
        repoOrder.save(o10);
        
        Order o2 = new Order();
        Date d2 = new Date();
		o2.setCreatedOn(d2);
		int hourD2 = dateToHour(d2);
		System.out.println(hourD2);
		o2.setHourDelivered(hourD2);
		o2.setAmount(400);
        o2.setCurrentStatus(Status.DELIVERED);
        o2.setCustomerId("123");
        o2.setDeliveredBy(Math.toIntExact(1));
        repoOrder.save(o2);
        
        Order o3 = new Order();
        Date d3 = new Date();
        o3.setCreatedOn(d3);
        int hourD3 = dateToHour(d3);
        System.out.println(hourD3);
        o3.setHourDelivered(hourD3);
        o3.setAmount(400);
        o3.setCurrentStatus(Status.DELIVERED);
        o3.setCustomerId("123");
        o3.setDeliveredBy(Math.toIntExact(1));
        repoOrder.save(o3);
      
        Order o4 = new Order();
        Date d4 = new Date();
        o4.setCreatedOn(d4);
        int hourD4 = dateToHour(d4);
        System.out.println(hourD4);
        o4.setHourDelivered(hourD4);
        o4.setAmount(400);
        o4.setCurrentStatus(Status.DELIVERED);
        o4.setCustomerId("123");
        o4.setDeliveredBy(Math.toIntExact(0));
        repoOrder.save(o4);
     
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
    
    
    
    @GetMapping(path = "/welcomeAdmin.html", produces = "text/html")
    public String welcomeAdmin(){
        return "welcomeAdmin";
    }
    
    
    @PostMapping(path="orderDeliveredAdmin.html")
    public String createRoleAdmin(@RequestParam("dateChoisie") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateC,Model model){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateVoulu = dateFormat.format(dateC);
        
        List<Integer> listHour = new ArrayList<Integer>();
        List<List<Integer>> listNbrOrderDeliveredByAllEmployees = new ArrayList<List<Integer>>();
        List<Integer> listNbrOrderDeliveredByAnEmployee = new ArrayList<Integer>();
        List<Order> listOrder = new ArrayList<Order>();
        List<Order> listOrderEmployee = new ArrayList<Order>();
        int nombreTotalCommande = 0; 
        int montantTotal = 0;
        int nbrOrderDeliveredByAnEmployee = 0;
        
        for(int i= 0 ; i<24; i++ ) {
        	listHour.add(repoOrder.countAllByCurrentStatusAndCreatedOnAndHourDelivered(Status.DELIVERED ,dateC, i));
        	nombreTotalCommande = nombreTotalCommande + listHour.get(i);
   
        }
        
        for(int i= 0 ; i<24; i++ ) {
        	listOrder = repoOrder.findAllByCurrentStatusAndCreatedOnAndHourDelivered(Status.DELIVERED ,dateC, i);
        	for(int j=0; j<listOrder.size();j++) {
        		montantTotal = montantTotal + listOrder.get(j).getAmount();
        	}
        }
        
        for(int i= 0 ; i<24; i++ ) {
        	listOrderEmployee = repoOrder.findAllByCurrentStatusAndCreatedOnAndHourDelivered(Status.DELIVERED ,dateC, i);
        	for(int x = 0; x < 5; x++){
        		for(int j=0; j<listOrderEmployee.size();j++) {
        			if(listOrderEmployee.get(j).getDeliveredBy() == x) {
            			nbrOrderDeliveredByAnEmployee++;
            		}
        		}
        	
        		listNbrOrderDeliveredByAnEmployee.add(nbrOrderDeliveredByAnEmployee);
        		nbrOrderDeliveredByAnEmployee = 0 ;
                
        	}
        	listNbrOrderDeliveredByAllEmployees.add(listNbrOrderDeliveredByAnEmployee);
        	listNbrOrderDeliveredByAnEmployee =  new ArrayList<Integer>();
        }
        
        System.out.println("listNbrOrderDeliveredByAllEmployees     "+listNbrOrderDeliveredByAllEmployees.size());
        
        if(nombreTotalCommande>0) {
        	model.addAttribute("listHour", listHour);	
        }else {
        	listHour = null;
        	model.addAttribute("listHour", listHour);
        }
        	
        
    	
    	model.addAttribute("listNbrOrderDeliveredByAllEmployees", listNbrOrderDeliveredByAllEmployees);
    	model.addAttribute("nombreTotalCommande", nombreTotalCommande);
    	model.addAttribute("montantTotal", montantTotal);
        model.addAttribute("dateVoulu", dateVoulu);
   
        return "order_delivered";
    }
    
}
