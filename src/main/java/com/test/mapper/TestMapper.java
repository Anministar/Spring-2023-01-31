package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.test.dto.TestDto;

public interface TestMapper {

	// COUNT

	// XML
	public int countXML();

	// At
	@Select("Select COUNT(*) from tbl_a")
	public int countAt();

	// SELECT

	// XML
	public TestDto selectXML(int id);

	// At
	@Select("Select * from tbl_a where id=#{id}")
	public TestDto selectAt(int id);

	// SELECT2

	// XML
	public List<TestDto> selectXML2(int id); // select 결과값이 하나이상일 수도 있기 때문에 List로 받아줌.
	// At

	@Select("Select * from tbl_a where id>=#{id}")
	public List<TestDto> selectAT2(int id);

	// INSERT

	// XML
	public int insertXML(TestDto dto);

	// At
	@Insert("Insert into tbl_a values(#{id}, #{name})")
	public int insertAT(TestDto dto);

	public int insertPARAM(@Param("id") int i, @Param("name") String n);

	public int insertKeyBeforeXML(TestDto dto);

	public int insertKeyAfterXML(TestDto dto);
	
	@SelectKey(before=true, keyProperty="id", resultType=int.class, statement={"select max(id) from tbl_a"})
	@Insert("Insert into tbl_a values(null, #{name})")
	public int insertKeyBeforeAT(TestDto dto);

	@SelectKey(before=false, keyProperty="id", resultType=int.class, statement= {"select max(id) from tbl_a"})
	@Insert("Insert into tbl_a values(null, #{name})")
	public int insertKeyAfterAT(TestDto dto);
	
	int updateXML(TestDto dto);
	int deleteXML(int id);
	
	@Update("Update tbl_a SET name=#{name} where id=#{id}")
	int updateAT(TestDto dto);
	
	@Delete("Delete from tbl_a where id=#{id}")
	int deleteAT(int id);
	
	int insertXMLHashmap(Map<String, Object> map); 

	int insertXMLHashmap2(Map<String, Object> map);

	List<Map<String, Object>> selectXMLHashMap();
	
	List<Map<String, Object>> selectXMLHashMapIf(Map<String, Object> map);
	
	List<Map<String, Object>> selectXMLhashMapChoose(Map<String, Object> map);
}