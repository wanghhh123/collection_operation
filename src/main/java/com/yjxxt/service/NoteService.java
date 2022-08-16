package com.yjxxt.service;

import java.util.*;

public class NoteService {
    private List<Map<String,Object>> noteList=null;

    public NoteService() {
        noteList =new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        map.put("title","hello");
        map.put("content","hello");
        map.put("typeId",1);
        map.put("pubTime",new Date());
        noteList.add(map);

    }

    public Integer countNoteByNoteTypeId(Integer noteTypeId) {
        Integer count=-0;
        for (Map<String, Object> map : noteList) {
            if(map.get("typeId").equals(noteTypeId)){
                count=count+1;
            }
        }
        return count;
    }
}
