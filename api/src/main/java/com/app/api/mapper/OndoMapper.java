package com.app.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.app.api.dto.OndoDTO;

@Mapper
public interface OndoMapper {

    @Select("select * from ondos")
    public List<OndoDTO> findAll();

    @Update("UPDATE ondos SET temperature09 = #{temperature09}, temperature13 = #{temperature13}, temperature17 = #{temperature17}, name = #{name} WHERE id = #{id}")
    public long editById(OndoDTO oDto);

    @Delete("DELETE FROM ondos WHERE id = #{id}")
    public long delete(long id);

    @Insert("insert into ondos (name, temperature09, temperature13, temperature17) values (#{name}, #{temperature09}, #{temperature13}, #{temperature17})")
    public long save(OndoDTO oDto);
}
