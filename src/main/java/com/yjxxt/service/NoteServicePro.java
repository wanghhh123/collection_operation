package com.yjxxt.service;


import com.yjxxt.pojo.Note;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 云记管理
 * 云记添加
 * 云记列表展示
 * 云记更新
 * 云记删除
 */
public class NoteServicePro {

    private List<Note> noteList ;

    public NoteServicePro() {

            noteList =new ArrayList<Note>();
            try {
                noteList.add(new Note(1,"java","java",1, DateUtils.parseDate("2022-06-01","yyyy-MM-dd"),99));
                noteList.add(new Note(2,"php","php",3,DateUtils.parseDate("2022-06-10","yyyy-MM-dd"),30));
                noteList.add(new Note(4,"scala","scala",1,DateUtils.parseDate("2022-06-05","yyyy-MM-dd"),40));
                noteList.add(new Note(6,"nodejs","nodejs",2, DateUtils.parseDate("2022-06-01","yyyy-MM-dd"),40));
                noteList.add(new Note(7,"html","html",1,DateUtils.parseDate("2022-06-20","yyyy-MM-dd"),70));
                noteList.add(new Note(10,"css","css",6,DateUtils.parseDate("2022-06-20","yyyy-MM-dd"),88));
                noteList.add(new Note(12,"aa","aa",1,DateUtils.parseDate("2022-06-21","yyyy-MM-dd"),90));
                noteList.add(new Note(15,"cc","cc",1,DateUtils.parseDate("2022-06-30","yyyy-MM-dd"),100));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    public void listNote(){
        noteList.forEach(n->{
            System.out.println(n);
        });
    }


    /**
     * 查询类别id =1 的所有云记的标题
     *    select title from t_note where type_id=1
     */
    /*public void query01(){
        List<String> titleList = noteList.stream()
                .filter(n -> n.getTypeId() == 1)
                .map(Note::getTitle)
                .collect(Collectors.toList());
        titleList.forEach(title->{
            System.out.println(title);
        });
    }
*/
    public void query01(){
        noteList.stream().filter(n->n.getTypeId()==1)
                .map(Note::getTitle)
                .collect(Collectors.toList())
                .forEach(title-> System.out.println(title));
    }
    /**
     *  查询点击量超过50的所有云记记录
     *    select * from t_note where click>50
     */
   /* public void query02(){
        List<Note> noteList = this.noteList.stream().filter(n -> n.getClick() > 50).collect(Collectors.toList());
        noteList.forEach(n->{
            System.out.println(n);
        });
    }*/
    public void query02(){
        noteList.stream().filter(n->n.getClick()>50)
                .collect(Collectors.toList())
                .forEach(u-> System.out.println(u));
    }


    /**
     * 查询类别id=2 点击量超过50 所有的云记标题
     *    select title from t_note where type_id=2 and click>50
     */
   /* public void query03(){
        List<String> titleList = noteList.stream().filter(n -> n.getTypeId() == 1)
                .filter(n -> n.getClick() > 50)
                .map(n -> n.getTitle())
                .collect(Collectors.toList());
        titleList.forEach(title->{
            System.out.println(title);
        });
    }*/
    public void query03(){
        noteList.stream()
                .filter(n->n.getTypeId()==2)
                .filter(n->n.getClick()>50)
                .map(Note::getTitle)
                .collect(Collectors.toList())
                .forEach(u-> System.out.println(u));
    }


    /**
     * 查询类别id=1 点击量超过50 云记标题 前两条记录
     *   select title from t_note where type_id=2 and click>50 limit 2
     */
  /*  public void query04(){
        List<String> titleList = noteList.stream().filter(n -> n.getTypeId() == 1)
                .filter(n -> n.getClick() > 50)
                .map(n -> n.getTitle())
                .limit(3)
                .collect(Collectors.toList());
        titleList.forEach(title->{
            System.out.println(title);
        });
    }*/
    public void query04(){
        noteList.stream()
                .filter(u->u.getTypeId()==1)
                .filter(u->u.getClick()>50)
                .map(Note::getTitle).limit(2)
                .collect(Collectors.toList())
                .forEach(title-> System.out.println(title));
    }



    /**
     * 查询类别id=1 点击量超过50 云记标题 (分页查找-每页2条  查询第二页记录)
     *   select title from t_note where type_id=2 and click>50 limit (page-1)*pageSize,2
     */

    public void query05(){
        noteList.stream().filter(u->u.getTypeId()==1)
                .filter(u->u.getClick()>50)
                .map(Note::getTitle)
                .skip(2)
                .limit(4)
                .collect(Collectors.toList())
                .forEach(title-> System.out.println(title));
    }


    /**
     * 分组统计每个类别下云记数量
     *    select type_id,count(id) from t_note group by type_id
     */
    public void query06(){
       noteList.stream().collect(Collectors.groupingBy((Note::getTypeId))).forEach((k,v)->{
           System.out.println("类别id："+k);
           v.forEach(n-> System.out.println(n));
           System.out.println("-------------------");
       });

    }
    /**
     * 根据类别id 统计每个类别下云记的数量
     */

    public void query07(){
        noteList.stream()
                .collect(Collectors.groupingBy((Note::getTypeId),Collectors.counting()))
                .forEach((k,v)-> System.out.println("类别id:"+k+"-----"+"数量:"+v));
    }

    /**
     * 根据类别发布日期 统计每个类别下云记的数量
     */

    public void query08(){
        noteList.stream()
                .collect(Collectors.groupingBy((Note::getPubTime),Collectors.counting()))
                .forEach((k,v)-> System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(k) +"---"+v));
    }
    /**
     * 查询云记记录点击量最低的点击量值
     */
    public void query09(){
        System.out.println(noteList.stream()
                .map(Note::getClick)
                .reduce(Integer::min)
                .get());


    }

    /**
     * 查询出云记记录点击量的总和
     */
    public void query10(){
        System.out.println(noteList.stream().map(Note::getClick)
                .reduce(Integer::sum).get());
    }




    public static void main(String[] args) {
        NoteServicePro noteServicePro =new NoteServicePro();
        noteServicePro.query10();
    }




}
