package com.cg.service.report_problem;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ReportProblemService extends IGeneralService<ReportProblem> {

    List<ReportProblemDTO> findAllReportProblemDTO();
    ReportProblemDTO findReportProblemDTOById(Long id);
}
