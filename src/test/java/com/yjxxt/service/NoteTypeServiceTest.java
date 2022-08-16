package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import org.junit.Before;
import org.junit.Test;

public class NoteTypeServiceTest {
    private NoteTypeService noteTypeService=null;

    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        noteTypeService=new NoteTypeService();
    }

    @Test
    public void listNoteType(){
        noteTypeService.listNoteType(1);

    }
    @Test
    public void  addNoteType(){
        System.out.println("添加前");
        noteTypeService.listNoteType(1);
        noteTypeService.addNoteType(new NoteType(5,"java1",1));
        System.out.println("添加后");
        noteTypeService.listNoteType(1);
    }
    @Test
    public void updateNoteType(){
        noteTypeService.updateNoteType(new NoteType(2,"html",1));
        noteTypeService.listNoteType(1);
    }
}
