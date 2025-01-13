package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PemesanMapper {

    // Mendapatkan semua data pemesan
    @Select("SELECT * FROM pemesan")
    List<Pemesan> getAllPemesan();

    // Menambahkan data pemesan baru
    @Insert("INSERT INTO pemesan (nama, alamat, jenis_sampah) VALUES (#{nama}, #{alamat}, #{jenisSampah})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addPemesan(Pemesan pemesan);

    // Mengupdate data pemesan
    @Update("UPDATE pemesan SET nama = #{nama}, alamat = #{alamat}, jenis_sampah = #{jenisSampah} WHERE id = #{id}")
    int updatePemesan(Pemesan pemesan);

    // Menghapus data pemesan
    @Delete("DELETE FROM pemesan WHERE id = #{id}")
    int deletePemesan(int id);
}
