package com.test.mapper;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {
	
	//XML
	public int countXML();
	//At
	@Select("Select COUNT(*) from tbl_a")
	public int countAt();
}
