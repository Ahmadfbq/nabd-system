package com.example.analysis_service.model;

import com.example.analysis_service.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}

