package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.impl.CurvePointService;
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
    // TODO: Inject Curve Point service
    @Autowired
    private CurvePointService curvePointService;

    @GetMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        List<CurvePoint> curvePoints = curvePointService.findAllCurvePoints();
        model.addAttribute("curvePoints", curvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCPForm() {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model, RedirectAttributes ra) {
        // TODO: check data valid and save to db, after saving return Curve list
        if(curvePoint.getCurveId() <= 0) {
            ra.addFlashAttribute("errorMsg", "must not be null");
            return "curvePoint/add";
        }

        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointService.findById(id);  //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCP(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model, RedirectAttributes ra) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list

        if(curvePoint.getCurveId() <= 0) {
            ra.addFlashAttribute("errorMsg", "must not be null");
            return "curvePoint/update";
        }

        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setId(id);

        CurvePoint curvePoint1 = curvePointService.saveCurvePoint(curvePoint);
        if(curvePoint1 == null) {
            return "curvePoint/update";
        }

        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCP(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        try {
            CurvePoint curvePoint = curvePointService.findById(id);
            //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            curvePointService.deleteCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());
        }
        catch(Exception ex){

        }
        return "redirect:/curvePoint/list";
    }
}
