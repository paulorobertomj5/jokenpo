package com.springboot.jokenpo.controller;

import com.springboot.jokenpo.data.vo.PlayVO;
import com.springboot.jokenpo.service.PlayService;
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


@Api(value = "Play API", description = "Play API description", tags = "Play")
@RestController
@RequestMapping("/play")
public class PlayController {
    @Autowired
    private PlayService service;

    @ApiOperation(value = "findById")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        PlayVO playVO = service.findById(id);

        if (playVO != null) {
            playVO.add(linkTo(methodOn(PlayController.class).findById(id)).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(playVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "findByAll")
    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> findByAll() {

        List<PlayVO> playVOs = service.findByAll();

        if (playVOs != null && playVOs.size() > 0) {
            playVOs.stream().forEach(p -> p.add(linkTo(methodOn(PlayController.class).findById(p.getKey())).withSelfRel()));
            return ResponseEntity.status(HttpStatus.OK).body(playVOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "create")
    @PostMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> create(@Valid @RequestBody PlayVO play) {

        PlayVO playVO = service.create(play);

        if (playVO != null) {
            playVO.add(linkTo(methodOn(PlayController.class).findById(playVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(playVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "update")
    @PutMapping(produces = {"application/json", "application/x-yaml"}, consumes = {"application/json", "application/x-yaml"})
    public ResponseEntity<Object> update(@Valid @RequestBody PlayVO play) {
        PlayVO playVO = service.update(play);
        if (playVO != null) {
            playVO.add(linkTo(methodOn(PlayController.class).findById(playVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(playVO);
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
