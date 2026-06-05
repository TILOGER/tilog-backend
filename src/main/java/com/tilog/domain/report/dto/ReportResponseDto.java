package com.tilog.domain.report.dto;

import com.tilog.domain.feedback.entity.Status;
import com.tilog.domain.report.entity.ReasonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportResponseDto {
    private Long reportId;
    private ReasonType reasonType;
    private String reasonDetail;
    private Status status;
}
