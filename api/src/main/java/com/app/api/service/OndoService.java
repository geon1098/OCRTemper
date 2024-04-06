package com.app.api.service;

import com.app.api.dto.OndoDTO;
import com.app.api.dto.ResultDTO;

public interface OndoService {

    public ResultDTO findAll();

    public ResultDTO editById(OndoDTO oDto);

    public ResultDTO save(OndoDTO oDto);

    public ResultDTO delete(long id);
}
