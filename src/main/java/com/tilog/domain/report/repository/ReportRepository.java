package com.tilog.domain.report.repository;

import com.tilog.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findTop4ByOrderByCreatedAtDesc();  // 전체 신고 내역 중 생성일(createdAt) 기준 최신순(내림차순)으로 4개만
}
