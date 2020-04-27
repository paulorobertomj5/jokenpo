package com.springboot.jokenpo.controller;

import com.springboot.jokenpo.data.vo.MatchVO;
import com.springboot.jokenpo.service.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Api(value = "Match API", description = "Match API description", tags = "Match")
@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService service;

    @ApiOperation(value = "findById")
    @GetMapping(value = "/", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        MatchVO matchVO = service.findById(id);

        if (matchVO != null) {
            matchVO.add(linkTo(methodOn(MatchController.class).findById(id)).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(matchVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "Result Match")
    @GetMapping(value = "/result/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findResultMatchById(@PathVariable("id") Long id) {

        String resultMatch = service.findResultMatchById(id);

        if (resultMatch != null && !resultMatch.equals("")) {
            MatchVO matchVO = new MatchVO();
            matchVO.setMessage(resultMatch);
            return ResponseEntity.status(HttpStatus.OK).body(matchVO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @ApiOperation(value = "findByAll")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findByAll() {

        List<MatchVO> matchVOs = service.findByAll();

        if (matchVOs != null && matchVOs.size() > 0) {
            matchVOs.stream().forEach(p -> p.add(linkTo(methodOn(MatchController.class).findById(p.getKey())).withSelfRel()));
            return ResponseEntity.status(HttpStatus.OK).body(matchVOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "create")
    @PostMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> create(@Valid @RequestBody MatchVO match) {

        MatchVO matchVO = service.create(match);

        if (matchVO != null) {
            matchVO.add(linkTo(methodOn(MatchController.class).findById(matchVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(matchVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "update")
    @PutMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> update(@Valid @RequestBody MatchVO match) {
        MatchVO matchVO = service.update(match);
        if (matchVO != null) {
            matchVO.add(linkTo(methodOn(MatchController.class).findById(matchVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(matchVO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @ApiOperation(value = "delete")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
