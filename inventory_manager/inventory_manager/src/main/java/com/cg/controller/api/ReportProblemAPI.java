package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.item.ItemService;
import com.cg.service.report_problem.ReportProblemService;
import com.cg.service.user.UserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/report_problems")
public class ReportProblemAPI {

    @Autowired
    private ReportProblemService reportProblemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<?>> getList() {
        try {

            List<ReportProblemDTO> reportProblems = reportProblemService.findAllReportProblemDTO();

            if (reportProblems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reportProblems, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportProblemDTO> getByIdDTO(@PathVariable Long id) {

        try {
            ReportProblemDTO reportProblemDTO = reportProblemService.findReportProblemDTOById(id);
            return new ResponseEntity<>(reportProblemDTO, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ReportProblemDTO reportProblemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {

            ReportProblem reportProblem = reportProblemDTO.toReportProblem();

            ItemDTO itemDTO = reportProblemDTO.getItem();
            Item itemSaved = itemService.save(itemDTO.toItem());
            reportProblem.setItem(itemSaved);

            UserDTO userDTO = reportProblemDTO.getCreateBy();
            User userSaved = userService.save(userDTO.toUser());
            reportProblem.setCreateBy(userSaved);
            return new ResponseEntity<>(reportProblem, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ReportProblemDTO reportProblemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        try {

            ReportProblem reportProblem = reportProblemDTO.toReportProblem();

            ItemDTO itemDTO = reportProblemDTO.getItem();
            Item itemSaved = itemService.save(itemDTO.toItem());
            reportProblem.setItem(itemSaved);

            UserDTO userDTO = reportProblemDTO.getCreateBy();
            User userSaved = userService.save(userDTO.toUser());
            reportProblem.setCreateBy(userSaved);
            return new ResponseEntity<>(reportProblem, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Data invalid");
        }
    }

    @PostMapping("/update")
    public ReportProblem update(@RequestBody ReportProblem reportProblem) {

        ReportProblem reportProblemUpdated = reportProblemService.save(reportProblem);

        return reportProblemUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        try {
            reportProblemService.remove(id);
            Optional<ReportProblem> reportProblem = reportProblemService.findById(id);

            if (reportProblem.isPresent()) {
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
