package com.cg.service.report_problem;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ReportProblemServiceImpl implements ReportProblemService {

    @Autowired
    private ReportProblemRepository reportProblemRepository;

    @Override
    public List<ReportProblem> findAll() {
        return reportProblemRepository.findAll();
    }

    @Override
    public List<ReportProblemDTO> findAllReportProblemDTO() {
        return reportProblemRepository.findAllReportProblemDTO();
    }

    @Override
    public ReportProblemDTO findReportProblemDTOById(Long id) {
        return reportProblemRepository.findReportProblemDTOById(id);
    }

    @Override
    public Optional<ReportProblem> findById(Long id) {
        return reportProblemRepository.findById(id);
    }

    @Override
    public ReportProblem save(ReportProblem reportProblem) {
        return reportProblemRepository.save(reportProblem);
    }

    @Override
    public void remove(Long id) {
        reportProblemRepository.deleteById(id);
    }
}
