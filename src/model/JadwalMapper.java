package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface JadwalMapper {

    @Select("SELECT * FROM jadwal")
    List<Jadwal> getAllJadwal();

    @Insert("INSERT INTO jadwal (nama, tanggal, lokasi) VALUES (#{nama}, #{tanggal}, #{lokasi})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addJadwal(Jadwal jadwal);

    @Update("UPDATE jadwal SET nama = #{nama}, tanggal = #{tanggal}, lokasi = #{lokasi} WHERE id = #{id}")
    int updateJadwal(Jadwal jadwal);

    @Delete("DELETE FROM jadwal WHERE id = #{id}")
    int deleteJadwal(int id);
}
