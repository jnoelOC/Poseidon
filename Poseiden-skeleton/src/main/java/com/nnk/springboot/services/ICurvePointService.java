package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICurvePointService {
    List<CurvePoint> findAllCurvePoints();
    CurvePoint findById(Integer id);

    CurvePoint saveCurvePoint(CurvePoint curvePoint);

    void deleteCurvePoint(CurvePoint curvePoint);
}
