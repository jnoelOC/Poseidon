package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.impl.CurvePointService;
import com.nnk.springboot.services.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CurvePointController {

    public static final Logger logger = LogManager.getLogger(CurvePointController.class);

    @Autowired
    private CurvePointService curvePointService;

    @GetMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> curvePoints = curvePointService.findAllCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        logger.info("Requête de Liste de CurvePoint");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCPForm() {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        try {
            if (curvePoint.getCurveId() <= 0 || curvePoint == null) {
                model.addAttribute("errorMsg", "doit ne pas être nul");
                return "curvePoint/add";
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            model.addAttribute("errorMsg", "doit ne pas être nul");
            return "curvePoint/add";
        }
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            logger.info("Requête de Create de CurvePoint");
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointService.findById(id);
        logger.info("Get CurvePoint Update");
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCP(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        try{
            if(curvePoint.getCurveId() <= 0 || curvePoint == null) {
                logger.error("CurvePoint <= 0 OR null");
                model.addAttribute("errorMsg", "doit ne pas être nul");
                return "curvePoint/update";
            }
        }catch(NullPointerException ex){
            logger.error(ex.getMessage());
            model.addAttribute("errorMsg", "doit ne pas être nul");
            return "curvePoint/update";
        }

        if (result.hasErrors()) {
            logger.error(result.getFieldErrors());
            return "curvePoint/update";
        }
        curvePoint.setId(id);

        CurvePoint curvePoint1 = curvePointService.saveCurvePoint(curvePoint);
        if(curvePoint1 == null) {
            logger.error("curvePoint variable is null");
            return "curvePoint/update";
        }

        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        logger.info("Requête de Update de CurvePoint");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCP(@PathVariable("id") Integer id, Model model) {

            CurvePoint curvePoint = curvePointService.findById(id);
            curvePointService.deleteCurvePoint(curvePoint);
            logger.info("Requête de Delete de CurvePoint");
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        return "redirect:/curvePoint/list";
    }
}
