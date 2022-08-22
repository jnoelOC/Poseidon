package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService implements ICurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> findAllCurvePoints() { return curvePointRepository.findAll(); }

    public CurvePoint findById(Integer id) {return curvePointRepository.findById(id).get(); }

    public CurvePoint saveCurvePoint(CurvePoint curvePoint){ return curvePointRepository.save(curvePoint); }

    public void deleteCurvePoint(CurvePoint curvePoint) { curvePointRepository.delete(curvePoint);}
}
