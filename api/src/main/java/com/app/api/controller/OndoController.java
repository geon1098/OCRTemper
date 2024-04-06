package com.app.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.dto.OndoDTO;
import com.app.api.dto.ResultDTO;
import com.app.api.service.OndoService;

@RestController
@CrossOrigin(origins = "http://localhost:8085")
@RequestMapping("/api")
public class OndoController {

    @Autowired
    OndoService oService;

    // 조회
    @PostMapping("/findAll")
    public ResultDTO findAll() {
        return oService.findAll();
    }

    // 수정
    @PostMapping("/editById")
    public ResultDTO editById(@RequestBody OndoDTO oDto) {
        return oService.editById(oDto);
    }

    // 삭제
    @DeleteMapping("/delete")
    public ResultDTO delete(@RequestParam("id") long id) {
        return oService.delete(id);
    }

    // 저장
    @PutMapping("/save")
    public ResultDTO save(@RequestBody OndoDTO oDto) {
        return oService.save(oDto);
    }

}
