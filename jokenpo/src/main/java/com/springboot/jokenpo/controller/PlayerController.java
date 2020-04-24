package com.springboot.jokenpo.controller;

import com.springboot.jokenpo.data.vo.PlayerVO;
import com.springboot.jokenpo.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Api(value = "Player API", description = "Player API description", tags = "Player")
@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService service;

    @ApiOperation(value = "findById")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        PlayerVO playerVO = service.findById(id);

        if (playerVO != null) {
            playerVO.add(linkTo(methodOn(PlayerController.class).findById(id)).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(playerVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "findByAll")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<Object> findByAll() {

        List<PlayerVO> playerVOs = service.findByAll();

        if (playerVOs != null && playerVOs.size() > 0) {
            playerVOs.stream().forEach(p -> p.add(linkTo(methodOn(PlayerController.class).findById(p.getKey())).withSelfRel()));
            return ResponseEntity.status(HttpStatus.OK).body(playerVOs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "create")
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<Object> create(@RequestBody PlayerVO player) {

        PlayerVO playerVO = service.create(player);

        if (playerVO != null) {
            playerVO.add(linkTo(methodOn(PlayerController.class).findById(playerVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.CREATED).body(playerVO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "update")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<Object> update(@RequestBody PlayerVO player) {
        PlayerVO playerVO = service.update(player);
        if (playerVO != null) {
            playerVO.add(linkTo(methodOn(PlayerController.class).findById(playerVO.getKey())).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(playerVO);
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
