package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.impl.CurvePointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointService curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    @Test
    @DisplayName("find all curvepoints")
    void FindAllCurvePoints_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        List<CurvePoint> curvePoints = new ArrayList<>();
        CurvePoint cp1 = new CurvePoint(1, 10.0,10.0);
        CurvePoint cp2 = new CurvePoint(2, 22.0,22.0);
        curvePoints.add(cp1);
        curvePoints.add(cp2);

        when(curvePointRepository.findAll()).thenReturn(curvePoints);
        // Act
        List<CurvePoint> allCps = curvePointService.findAllCurvePoints();
        if (allCps.isEmpty()) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find curvepoint by id")
    void FindCurvePointById_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Optional<CurvePoint> cp3 = Optional.of(new CurvePoint(3, 33.0, 33.0));
        cp3.ifPresent( cpValue ->
                when(curvePointRepository.findById(1)).thenReturn(cp3)
        );
        // Act
        CurvePoint cp2 = curvePointService.findById(1);
        if (cp2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("save curvepoint")
    void SaveCurvePoint_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        CurvePoint cp1 = new CurvePoint(1, 11.0, 11.0);
        CurvePoint cp3 = new CurvePoint(2, 33.0, 33.0);

        when(curvePointRepository.save(cp3)).thenReturn(cp1);
        // Act
        CurvePoint cp2 = curvePointService.saveCurvePoint(cp3);
        if (cp2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("delete curvepoint")
    void DeleteCurvePoint_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        CurvePoint cp3 = new CurvePoint(1, 22.0, 33.0);
        curvePointRepository.delete(Mockito.any(CurvePoint.class));
        // Act
        curvePointService.deleteCurvePoint(cp3);
        // Assert
        assertTrue(ret);
    }
}
