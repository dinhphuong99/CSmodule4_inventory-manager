package com.cg.repository;

import com.cg.model.ReportProblem;
import com.cg.model.dto.ReportProblemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportProblemRepository extends JpaRepository<ReportProblem, Long> {

    @Query("SELECT NEW com.cg.model.dto.ReportProblemDTO(u.id, u.createAt, u.compensationFee, u.description, u.item, u.createBy) FROM ReportProblem u")
    List<ReportProblemDTO> findAllReportProblemDTO();

    @Query("SELECT NEW com.cg.model.dto.ReportProblemDTO(u.id, u.createAt, u.compensationFee, u.description, u.item, u.createBy) FROM ReportProblem u WHERE u.id = :id")
    ReportProblemDTO findReportProblemDTOById(@Param("id") Long id);
}