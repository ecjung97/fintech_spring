package net.daum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.daum.vo.ZipCodeVO;

public interface ZipCodeRepository extends JpaRepository<ZipCodeVO, Integer> {

	@Query("select z from ZipCodeVO z where z.dong like ?1 and z.no>0 order by z.no desc") // JPQL
	public List<ZipCodeVO> findByDong(String dong);
}
