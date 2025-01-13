package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArmadaMapper {

    @Select("SELECT * FROM armada")
    List<Armada> getAllArmada();

    @Select("SELECT * FROM armada WHERE tipe_kendaraan LIKE CONCAT('%', #{filter}, '%')")
    List<Armada> getAllArmadaByFilter(@Param("filter") String filter);

    @Insert("INSERT INTO armada (nomor_kendaraan, tipe_kendaraan, kapasitas) VALUES (#{nomorKendaraan}, #{tipeKendaraan}, #{kapasitas})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addArmada(Armada armada); // Mengembalikan jumlah baris yang ditambahkan

    @Update("UPDATE armada SET nomor_kendaraan = #{nomorKendaraan}, tipe_kendaraan = #{tipeKendaraan}, kapasitas = #{kapasitas} WHERE id = #{id}")
    int updateArmada(Armada armada); // Mengembalikan jumlah baris yang diupdate

    @Delete("DELETE FROM armada WHERE id = #{id}")
    int deleteArmada(@Param("id") int id); // Mengembalikan jumlah baris yang dihapus
}
