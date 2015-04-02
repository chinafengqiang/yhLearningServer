package com.smlearning.api;
import java.util.HashMap;
import java.util.List;

public interface IApi {
    public List<HashMap<String, Object>> getBookCategory(int gradeId);
    
    public List<HashMap<String, Object>> getBookPart(int gradeId,int categoryId);
    
}
