package com.app.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.api.dto.OndoDTO;
import com.app.api.mapper.OndoMapper;

@Repository
public class OndoDaoImp implements OndoDao {

    @Autowired
    OndoMapper oMapper;

    public List<OndoDTO> findAll() {
        return oMapper.findAll();
    }

    @Override
    public long delete(long id) {
        return oMapper.delete(id);
    }

    public long editById(OndoDTO oDto) {
        return oMapper.editById(oDto);
    }

    public long save(OndoDTO oDto) {
        return oMapper.save(oDto);
    }
}
