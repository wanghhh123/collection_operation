package com.yjxxt.service;

import com.yjxxt.pojo.NoteType;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;

import java.util.Map;


/**
 * 云记类别管理
 *    云记类别遍历
 *    云记类别添加
 *    云记类别更新
 *    云记类别删除
 */
public class NoteTypeService {

    private Map<Integer,NoteType> noteTypeMap;
    private UserService userService=new UserService();
    private NoteService noteService;


    public NoteTypeService() {
        noteTypeMap= new HashMap<Integer, NoteType>();
        noteTypeMap.put(1,new NoteType(1,"java",1));
        noteTypeMap.put(2,new NoteType(2,"php",1));
        noteTypeMap.put(3,new NoteType(3,"scala",2));
    }

    public void addNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空 用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         * 2.当前用户下类别名称不可重复
         * 3.执行添加
         */
       /* UserService userService = new UserService();

        if(null==noteType){
            throw new RuntimeException("添加内容不能为空！");
        }
        if(StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("类别名不能为空!");
        }

        if(userService.findUserByUserId(noteType.getUserId())==null){
            throw new RuntimeException("userid不存在");
        }
        for(Map.Entry<Integer,NoteType> integerNoteTypeEntry:noteTypeMap.entrySet()){
            if(integerNoteTypeEntry.getValue().getId()==noteType.getId()){
                throw new RuntimeException("id已重复");
            }
        }
        for(Map.Entry<Integer,NoteType> integerNoteTypeEntry:noteTypeMap.entrySet()){
            if(integerNoteTypeEntry.getValue().getTypeName()==noteType.getTypeName()){
                throw new RuntimeException("typename已重复");
            }
        }
        int id=noteTypeMap.size();
        noteTypeMap.put(id,noteType);
*/
        if(null == noteType){
            throw  new RuntimeException("云记记录不能为空!");
        }
        if(StringUtils.isBlank(noteType.getTypeName())){
            throw  new RuntimeException("云记类别名称不能为空!");
        }
        if(null==noteType.getUserId() && null==userService.findUserByUserId(noteType.getUserId())){
            throw new RuntimeException("用户记录不存在！");

        }

        Boolean flag = checkNoteTypeNameUnique(noteType.getTypeName(),noteType.getUserId());
        if(!flag){
            throw  new RuntimeException("云记类别名称不能重复!");
        }

        noteTypeMap.put(noteType.getId(),noteType);
    }

    private Boolean checkNoteTypeNameUnique(String typeName, Integer userId) {
        for (NoteType noteType : noteTypeMap.values()) {
            if(noteType.getUserId()==userId && noteType.getTypeName().equals(typeName)){
                return  false;
            }
        }
        return true;
    }


    /**
     * 根据登录用户查询云记类别
     * @param userId
     */
    public void listNoteType(Integer userId){
//        Collection<NoteType> values = noteTypeMap.values();
//        for (NoteType value : values) {
//            if(value.getUserId().equals(userId)){
//                System.out.println(value.getTypeName());
//            }
//        }
        /*for(Map.Entry<Integer,NoteType> integerNoteTypeEntry: noteTypeMap.entrySet()){
            if(integerNoteTypeEntry.getValue().getUserId().equals(userId)){
                System.out.println(integerNoteTypeEntry);
            }
        }*/
        noteTypeMap.values().stream()
                .filter(u->u.getUserId().equals(userId))
                .forEach(u-> System.out.println(u));
    }


    public void updateNoteType(NoteType noteType){
        /**
         * 1.参数校验
         *    类别名 不能为空
         *    用户id 必须存在(UserService->List<User> 必须存在对应用户记录)
         *    云记类别id 必须存在
         * 2.当前用户下类别名称不可重复
         * 3.执行更新
         */
        if(null==noteType){
            throw new RuntimeException("云记记录不能为空！");
        }
        if(StringUtils.isBlank(noteType.getTypeName())){
            throw new RuntimeException("类别名不能为空！");
        }
        if(null==noteType.getUserId() ||null ==userService.findUserByUserId(noteType.getUserId())){
            throw new RuntimeException("用户id不存在！");
        }
        if(!noteTypeMap.containsKey(noteType.getId())){
            throw new RuntimeException("云记类别不存在！");
        }

        //类别名唯一校验
        for (Map.Entry<Integer, NoteType> noteTypeEntry : noteTypeMap.entrySet()) {
            if(noteTypeEntry.getValue().getTypeName()==noteType.getTypeName()
                && !(noteTypeEntry.getValue().getId()==noteType.getId())
                && noteTypeEntry.getValue().getUserId().equals((noteType.getUserId())) ){
                throw new RuntimeException("类别名称不可重复！");
            }
        }
        //更新
        for (Map.Entry<Integer, NoteType> typeEntry : noteTypeMap.entrySet()) {
            if(typeEntry.getValue().getId().equals(noteType.getId())){
                noteTypeMap.replace(typeEntry.getKey(),noteType);
            }
        }




    }

    public void delNoteType(Integer noteTypeId){
        /**
         * 1.参数校验
         *    云记类别id 必须存在
         * 2.如果类别下存在云记记录 不允许删除
         * 3.执行删除
         */
        if(!noteTypeMap.containsKey(noteTypeId)){
            throw new RuntimeException("待删除记录不存在！");
        }
        Integer count = noteService.countNoteByNoteTypeId(noteTypeId);
        if(count>0){
            throw new RuntimeException("存在云记记录，暂不允许删除！");
        }
        noteTypeMap.remove(noteTypeId);
    }

}
