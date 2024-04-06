package com.app.api.dao;

import java.util.List;

import com.app.api.dto.OndoDTO;
import com.app.api.dto.ResultDTO;

public interface OndoDao {

    public List<OndoDTO> findAll();

    public long editById(OndoDTO oDto);

    public long delete(long id);

    public long save(OndoDTO oDto);
}
