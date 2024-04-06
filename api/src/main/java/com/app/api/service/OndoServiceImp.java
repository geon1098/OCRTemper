package com.app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.app.api.dao.OndoDao;
import com.app.api.dto.OndoDTO;
import com.app.api.dto.ResultDTO;

@Service
public class OndoServiceImp implements OndoService {

    private ResultDTO rDto;

    @Autowired
    OndoDao oDao;

    @Override
    public ResultDTO findAll() {
        rDto = new ResultDTO();
        List<OndoDTO> resulList = oDao.findAll();
        if (resulList != null) {
            rDto.setState(true);
            rDto.setResult(resulList);
        } else {
            rDto.setState(false);
        }
        return rDto;
    }

    @Override
    public ResultDTO editById(OndoDTO oDto) {
        rDto = new ResultDTO();
        long state = oDao.editById(oDto);
        if (state == 1) {
            rDto.setState(true);
            rDto.setMessage("온도수정성공");
        } else {
            rDto.setState(false);
            rDto.setMessage("온도수정실패");
        }
        return rDto;
    }

    @Override
    public ResultDTO delete(long id) {
        rDto = new ResultDTO();
        long state = oDao.delete(id);
        if(state == 1){
            rDto.setState(true);
            rDto.setMessage("온도삭제성공");
        }else{
            rDto.setState(false);
            rDto.setMessage("온도삭제실패");
        }
        return rDto;
    }

    @Override
    public ResultDTO save(OndoDTO oDto) {
        rDto = new ResultDTO();
        long state = oDao.save(oDto);
        if (state == 1) {
            rDto.setState(true);
            rDto.setMessage("온도생성성공");
        } else {
            rDto.setState(false);
            rDto.setMessage("온도생성실패");
        }
        return rDto;
    }
}
