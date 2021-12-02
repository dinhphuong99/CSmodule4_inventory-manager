package com.cg.controller.api;

import com.cg.model.*;
import com.cg.model.dto.*;
import com.cg.service.item.ItemService;
import com.cg.service.report_problem.ReportProblemService;
import com.cg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<?>> getList() {

        List<ReportProblemDTO> reportProblems = reportProblemService.findAllReportProblemDTO();

        if (reportProblems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reportProblems, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ReportProblemDTO getByIdDTO(@PathVariable Long id) {

        ReportProblemDTO reportProblemDTO = reportProblemService.findReportProblemDTOById(id);
        return reportProblemDTO;
    }

//    .setItem(item.toItem())
//    .setUser(user.toUser());

    @PutMapping("/update")
    public ReportProblemDTO edit(@RequestBody ReportProblemDTO reportProblemDTO) {

        ReportProblem reportProblem = reportProblemDTO.toReportProblem();

        ItemDTO itemDTO = reportProblemDTO.getItem();
        Item itemSaved = itemService.save(itemDTO.toItem());
        reportProblem.setItem(itemSaved);

        UserDTO userDTO = itemDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        reportProblem.setUser(userSaved);

        reportProblemService.save(reportProblem);
        return getByIdDTO(reportProblem.getId());
    }

    @PostMapping("/create")
    public ReportProblem create(@RequestBody ReportProblemDTO reportProblemDTO) {
        ReportProblem reportProblem = reportProblemDTO.toReportProblem();

        ItemDTO itemDTO = reportProblemDTO.getItem();
        Item itemSaved = itemService.save(itemDTO.toItem());
        reportProblem.setItem(itemSaved);

        UserDTO userDTO = itemDTO.getUser();
        User userSaved = userService.save(userDTO.toUser());
        reportProblem.setUser(userSaved);

        ReportProblem reportProblemCreated = reportProblemService.save(reportProblem);
        return reportProblemCreated;
    }

    @PostMapping("/update")
    public ReportProblem update(@RequestBody ReportProblem reportProblem) {

        ReportProblem reportProblemUpdated = reportProblemService.save(reportProblem);

        return reportProblemUpdated;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        reportProblemService.remove(id);
        Optional<ReportProblem> reportProblem = reportProblemService.findById(id);

        if (reportProblem.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
