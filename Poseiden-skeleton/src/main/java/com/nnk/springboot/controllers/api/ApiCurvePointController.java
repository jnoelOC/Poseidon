package com.nnk.springboot.controllers.api;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.ICurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiCurvePointController {
    public static final Logger logger = LogManager.getLogger(ApiCurvePointController.class);

    @Autowired
    ICurvePointService cpService;

    @GetMapping("api/cp/list")
    public List<CurvePoint> findAllCurvePoints() {

        List<CurvePoint> lcp = cpService.findAllCurvePoints();

        if (lcp.isEmpty()) {
            logger.error("Find all curvePoint : status Non trouvé.");
            return null;
        }
        logger.info("curve points trouvés.");
        return lcp;
    }

    @PutMapping("api/cp/update")
    public ResponseEntity<CurvePoint> updateCurvePoint(@RequestBody CurvePoint curvePoint) {

        CurvePoint cp = cpService.saveCurvePoint(curvePoint);
        if (cp == null) {
            logger.error("update CurvePoint : status Non trouvé.");
            return new ResponseEntity<>(cp, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Update CurvePoint : status Ok.");
            return new ResponseEntity<>(cp, HttpStatus.OK);
        }
    }

    @DeleteMapping("api/cp/delete")
    public ResponseEntity<Boolean> deleteCurvePoint(@RequestParam Integer id) {

        try {
            CurvePoint cp = cpService.findById(id);
            cpService.deleteCurvePoint(cp);
        } catch (Exception ex) {
            logger.error("Error delete CurvePoint : "+ ex.getMessage());
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
        }

        logger.info("Delete CurvePoint : status Trouvé.");
        return new ResponseEntity<>(true, HttpStatus.FOUND);

    }

    @PostMapping("api/cp/create")
    public ResponseEntity<CurvePoint> createCurvePointWithBodyParam(@RequestBody CurvePoint curvePoint) {

        CurvePoint cp = cpService.saveCurvePoint(curvePoint);
        if (cp == null) {
            logger.error("create CurvePoint : status Non trouvé.");
            return new ResponseEntity<>(cp, HttpStatus.NOT_FOUND);
            // throw new PAlreadyCreatedException();
        } else {
            logger.info("Create CurvePoint : status Créé.");
            return new ResponseEntity<>(cp, HttpStatus.CREATED);
        }

    }


    @GetMapping("api/cp/getOne")
    public ResponseEntity<CurvePoint> findOneCurvePoint(@RequestParam Integer id) {

        CurvePoint cp = cpService.findById(id);
        if (cp == null) {
            logger.error("FindOneCurvePoint : status Non trouvé.");
            return new ResponseEntity<>(cp, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status CurvePoint trouvé.");
            return new ResponseEntity<>(cp, HttpStatus.FOUND);
        }
    }
}
