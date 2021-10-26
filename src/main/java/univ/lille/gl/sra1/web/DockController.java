package univ.lille.gl.sra1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.lille.gl.sra1.dao.Status;
import univ.lille.gl.sra1.model.Dock;
import univ.lille.gl.sra1.model.Order;
import univ.lille.gl.sra1.repository.DockRepository;
import univ.lille.gl.sra1.repository.OrderRepository;
import univ.lille.gl.sra1.web.dto.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path="/docks")
public class DockController {
	@Autowired
	DockRepository dockRep;


	@ResponseBody
	@GetMapping(path = "/list.json")
	public List<Dock> getDocks(Model model, HttpServletRequest req, HttpServletResponse res) {

		try {
			List<Dock> docks = (List<Dock>) dockRep.findAll();
			res.setStatus(200);
			return docks;
		} catch (Exception e) {

			res.setStatus(500);
			return null;
		}


	}

	@GetMapping(path = "/list.html")
	public String listDocks(Model model, HttpServletRequest req, HttpServletResponse res) {

		try {
			List<Dock> docks = (List<Dock>) dockRep.findAll();
			model.addAttribute("docks", docks);
			res.setStatus(200);
			return "docks";
		} catch (Exception e) {

			res.setStatus(500);
			return "internal error";
		}


	}


	@ResponseBody
	@PostMapping(path = "/post.json", consumes = "application/json")
	public SimpleResponse createDock(Model model, HttpServletRequest req, HttpServletResponse resp, @RequestBody Dock dock) {

		SimpleResponse res = new SimpleResponse();

		try {
			if (dock.getNumDocker() < 1 || dock.getNumDocker() > 30 || dockRep.existsByNumDocker(dock.getNumDocker())) {
				res.status = SimpleResponse.Status.ERROR;
				res.message = "Internal error";
				resp.setStatus(400);

			} else {
				dockRep.save(dock);
				res.status = SimpleResponse.Status.OK;
				res.message = "crée avec succes ;) ;) ;)";
				resp.setStatus(200);
			}

		} catch (Exception e) {

			res.status = SimpleResponse.Status.ERROR;
			res.message = "Internal error";
			resp.setStatus(400);
		}

		return res;
	}


	@ResponseBody
	@PutMapping(path="/update.json",consumes="application/json")
	public SimpleResponse updateDock(Model model, HttpServletRequest req , HttpServletResponse resp, @RequestBody Dock dock) {

		SimpleResponse res = new SimpleResponse();

		try{
			if(dock.getNumDocker()<1 || dock.getNumDocker()>30 || !dockRep.existsById(dock.getId())|| dockRep.existsByNumDocker(dock.getNumDocker())){
				res.status = SimpleResponse.Status.ERROR;
				res.message = "Internal error";
				resp.setStatus(400);

			}
			else{
				dockRep.save(dock);
				res.status = SimpleResponse.Status.OK;
				res.message = "modifié avec succes =====)";
				resp.setStatus(200);
			}

		}catch (Exception e){

			res.status = SimpleResponse.Status.ERROR;
			res.message = "Internal error";
			resp.setStatus(400);
		}

		return res;
	}

	@ResponseBody
	@DeleteMapping(path="/remove/{id}.json")
	public SimpleResponse deleteDock(Model model, HttpServletResponse resp, @PathVariable Integer id) {

		SimpleResponse res = new SimpleResponse();
		try{
			if(dockRep.existsById(id)){
				dockRep.delete(id);
				res.status = SimpleResponse.Status.OK;
				res.message = "Dock supprimé bro ;)";
				resp.setStatus(200);
			}
			else {

				res.status = SimpleResponse.Status.ERROR;
				res.message = "Dock n'existe pas";
				resp.setStatus(404);
			}

		}catch (Exception e){
			res.status = SimpleResponse.Status.ERROR;
			res.message = "internal error!";
			resp.setStatus(500);
		}
		return res;

	}



}
