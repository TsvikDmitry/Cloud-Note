package com.tsvikdima.cloudnote.dropbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.dropbox.sync.android.DbxDatastore;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFields;
import com.dropbox.sync.android.DbxList;
import com.dropbox.sync.android.DbxRecord;
import com.dropbox.sync.android.DbxTable;

public class Table {
    static DbxDatastore mDatastore;
    static DbxTable mTableNote;
    static DbxTable mTableTask;
   // static DbxTable mTableList;
    static DbxTable mTableListIndex;
    
    public class Note {
    	
    	
        private DbxRecord mRecord;
        public Note(DbxRecord record) {
            mRecord = record;
        }

        public String getId() {
            return mRecord.getId();
        }

        public String getImage() {
            return mRecord.getString("image");
        }
        
        public void setImage(String image) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("image", image);
            mDatastore.sync();
        }
        public void setName(String name) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("name", name);
            mDatastore.sync();
        }
        public String getName() {
            return mRecord.getString("name");
        }
        
        public String getBasket() {
            return mRecord.getString("basket");
        }
        
        public boolean isFavorite() {
            return mRecord.getBoolean("favorite");
        }
       
        public Date getDatas() {
            return mRecord.getDate("data_create");
        }
        public Date getDatasMod() {
            return mRecord.getDate("data_mod");
        }
        
        public void toFavorite() throws DbxException {
            mRecord.set("favorite", !isFavorite());
            mDatastore.sync();
        }
       
        
        public void delete() throws DbxException {
            mRecord.deleteRecord();
            mDatastore.sync();
        }
   
    }

    public class Task {
        private DbxRecord mRecord;

        public Task(DbxRecord record) {
            mRecord = record;
        }
        public String getId() {
            return mRecord.getId();
        }

        /***************NoteID*************/
        public String getNoteID() {
            return mRecord.getString("id_note");
        }
        public void setNoteID(String id_note) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("id_note", id_note);
            mDatastore.sync();
        }
        
        /***************setName*************/
        public String getName() {
            return mRecord.getString("name");
        }
        public void setName(String name) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("name", name);
            mDatastore.sync();
        }
        
        /***************setText*************/
        public String getText() {
            return mRecord.getString("text");
        }
        public void setText(String text) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("text", text);
            mDatastore.sync();
        }
        
        /***************isFavorite*************/
        public boolean isFavorite() {
            return mRecord.getBoolean("favorite");
        }
        
        public void toFavorite() throws DbxException {
            mRecord.set("favorite", !isFavorite());
            mDatastore.sync();
        }
        
        public void setFavorite(boolean favorite) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("favorite", favorite);
            mDatastore.sync();
        }
        /*************** Date *************/

        public Date getDatas() {
            return mRecord.getDate("data_create");
        }
        public Date getDatasMod() {
            return mRecord.getDate("data_mod");
        }
        
        /*************** delete *************/
        
        public boolean isBasket() {
            return mRecord.getBoolean("basket");
        }
        
        public void toBasket() throws DbxException {
            mRecord.set("basket", !isBasket());
            mDatastore.sync();
        }
        
        /*************** delete *************/
        public void delete() throws DbxException {
            mRecord.deleteRecord();
            mDatastore.sync();
        }

        public boolean isList() {
            return mRecord.getBoolean("list");
        }

    }
    
    public class ListItem {
        private DbxRecord mRecord;

        public ListItem(DbxRecord record) {
            mRecord = record;
        }
        public String getId() {
            return mRecord.getId();
        }

        /***************NoteID*************/
        public String getListID() {
            return mRecord.getString("id_list");
        }
        public void setListID(String id_note) throws DbxException {
        	mRecord.set("data_mod", new Date());
        	mRecord.set("id_note", id_note);
            mDatastore.sync();
        }
        
        public Date getDatas() {
            return mRecord.getDate("data_create");
        }
        public Date getDatasMod() {
            return mRecord.getDate("data_mod");
        }
        
        public String getText() {
        	return mRecord.getString("text");
        }
        public void setText(String text) throws DbxException {
        	//mRecord.set("data_mod", new Date());
        	mRecord.set("text", text);
            mDatastore.sync();
        }
        
        public boolean isCheck() {
            return mRecord.getBoolean("check");
        }
        
        public void toCheck() throws DbxException {
            mRecord.set("check", !isCheck());
            mDatastore.sync();
        }
        
        public void delete() throws DbxException {
            mRecord.deleteRecord();
            mDatastore.sync();
        }
    }
     
    public Table(DbxDatastore datastore) {
        mDatastore = datastore;
        mTableNote = datastore.getTable("Note");
        mTableTask = datastore.getTable("Task");
       // mTableList = datastore.getTable("List");
        mTableListIndex = datastore.getTable("ListIndex");
    }
    
    
    
    /******************* Note *****************************/
    public void CreateNote(String name, String image,boolean favorite,String color, boolean password_test, String password) throws DbxException {
	
		DbxFields noteFields = new DbxFields()
      			.set("image", image)
    			.set("name", name)
    			.set("basket", false)
    			.set("favorite", favorite)
    			.set("color", color)
				.set("password_test", password_test)
				.set("password", password)
				.set("data_create", new Date())
				.set("data_mod", new Date());
    	
    	mTableNote.insert(noteFields);
        mDatastore.sync();
    }

    
    public List<Note> getNoteTableAll() throws DbxException {
        List<Note> resultList = new ArrayList<Note>();
        for (DbxRecord result : mTableNote.query()) {
            resultList.add(new Note(result));
        }        

        return sort(resultList,1);
   }
    	//сортировка
    public List<Note> sort(List<Note> resultList, int i) throws DbxException {
       if (i==1){
       	Collections.sort(resultList, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        }); 
       }
       if (i==2){
         	Collections.sort(resultList, new Comparator<Note>() {
                @Override
                public int compare(Note o1, Note o2) {
                    return o1.getDatasMod().compareTo(o2.getDatasMod());
                }
            });    
       }
        return resultList;
        
    }
    
    public List<Note> getNoteTableId(String id) throws DbxException {
        List<Note> resultList = new ArrayList<Note>();
          DbxRecord result = mTableNote.get(id);
            resultList.add(new Note(result));
        	Log("resultList = "+resultList);
            return resultList;
    }

    

    public static DbxRecord TaskNameID(String id ) throws DbxException {
        DbxRecord result =  mTableNote.get(id);
        return result;
    }

    public static DbxList NoteNameID(String id ) throws DbxException {
        DbxList result =  mTableNote.get(id).getList("name");
        return result;
    }
    

    public static void NoteDeleteID(String id ) throws DbxException {
        DbxRecord result =  mTableNote.get(id);  
        result.deleteRecord();

        mDatastore.sync();
    } 
    
    /******************* Task *****************************/
    public void CreateTask(String id_note,String name, String text, boolean list,  boolean favorite, boolean password_test, String password,String color) throws DbxException {
    	DbxFields taskFields = new DbxFields()
    		.set("id_note", id_note)
    		.set("list", list)
 			.set("index", "")
 			.set("name", name)
 			.set("text", text)
 			.set("basket", false)
 			.set("favorite", favorite)
			.set("color", color)
			.set("data_create", new Date())
			.set("data_mod", new Date());
      
    	mTableTask.insert(taskFields);
        mDatastore.sync();
        
    }
    // получение всех заметок,  при условии basket = false
    public List<Task> getTableTaskORList(boolean list) throws DbxException {
        List<Task> resultList = new ArrayList<Task>();  //isList
        for (DbxRecord result :  mTableTask.query(new DbxFields().set("basket", false).set("list", list))){
            resultList.add(new Task(result));
        }
        Collections.sort(resultList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
            	return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });      
        return resultList;
    }
    
    
    // получение всех заметок,  при условии basket = false
    public List<Task> getTableTaskAndList() throws DbxException {
        List<Task> resultList = new ArrayList<Task>();  //isList
        for (DbxRecord result :  mTableTask.query(new DbxFields().set("basket", false))){
            resultList.add(new Task(result));
        }
        Collections.sort(resultList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
            	return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });      
        return resultList;
    }

    // получение всех заметок,  при условии basket = true
    public List<Task> getTaskTableBasket() throws DbxException {

    	List<Task> resultList = new ArrayList<Task>();
        for (DbxRecord result : mTableTask.query(new DbxFields().set("basket", true))) {
            resultList.add(new Task(result));
        }
        Collections.sort(resultList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
            	return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });  
        return resultList;
        
    }
    
    
    // получение последего созданной заметки по дате, для создания листа
    public List<Task> getTaskIdList() throws DbxException {
        List<Task> resultList = new ArrayList<Task>();
        for (DbxRecord result :  mTableTask.query(new DbxFields())){
            resultList.add(new Task(result));
        }
        Collections.sort(resultList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
            	return o1.getDatas().compareTo(o2.getDatas());
            }
        });      
        return resultList;
    }
    
    // получение всех заметок,  при условии basket = false, id_note = id
    public List<Task> getTableTaskIdNote(String id ) throws DbxException {
    	List<Task> resultList = new ArrayList<Task>();
        for (DbxRecord result : mTableTask.query(new DbxFields().set("basket", false).set("id_note", id))) {
            resultList.add(new Task(result));
        }
        Collections.sort(resultList, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
            	return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });  
        return resultList;
        
    }

    
    
    public List<Task> getTaskTableId(String id) throws DbxException {
        List<Task> resultList = new ArrayList<Task>();
          DbxRecord result = mTableTask.get(id);
          	//result.set("basket", false);
            resultList.add(new Task(result));
        	Log("resultList = "+resultList);
            return resultList;
    }
    
    public List<Task> getTaskTableId2(String id) throws DbxException {
        List<Task> resultList = new ArrayList<Task>();
          DbxRecord result = mTableTask.get(id);
            resultList.add(new Task(result));
        	Log("resultList = "+resultList);
            return resultList;
    }
    
    
    
    public static void TaskDeleteID(String id ) throws DbxException {
        DbxRecord result =  mTableTask.get(id);
        result.deleteRecord();
        mDatastore.sync();
    }
 
  
    
    
    public static void TaskBasket(String id,boolean basket) throws DbxException {
        DbxRecord result =  mTableTask.get(id);
        result.set("basket", basket);
       // result.deleteRecord();
        mDatastore.sync();
    }
    

    public void CreateListItem(String id_task, String text) throws DbxException {
    	DbxFields taskFields = new DbxFields()
    		.set("id_task", id_task)
			.set("text", text)
      		.set("check", false)
      		.set("data_create", new Date())
			.set("data_mod", new Date())
			.set("position", 0);

    mTableListIndex.insert(taskFields);
    mDatastore.sync();
    
}

    public List<ListItem> getListItem() throws DbxException {
        List<ListItem> resultList = new ArrayList<ListItem>();
        for (DbxRecord result : mTableListIndex.query()) {
            resultList.add(new ListItem(result));
        }        
        
        Collections.sort(resultList, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });   
        return resultList;
    }

    public List<ListItem> getListItemId(String id_task) throws DbxException {
        List<ListItem> resultList = new ArrayList<ListItem>();
        for (DbxRecord result : mTableListIndex.query(new DbxFields()
        	.set("id_task", id_task)
        		)) {
            resultList.add(new ListItem(result));
        }        
        
        Collections.sort(resultList, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });   
        return resultList;
    }

    

    
    public List<ListItem> getListItemId(String id_task, boolean check) throws DbxException {
        List<ListItem> resultList = new ArrayList<ListItem>();
        for (DbxRecord result : mTableListIndex.query(new DbxFields()
        	.set("id_task", id_task).set("check", check)
        		)) {
            resultList.add(new ListItem(result));
        }        
        
        Collections.sort(resultList, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getDatasMod().compareTo(o1.getDatasMod());
            }
        });   
        return resultList;
    }
    
    /******************* *****************************/

    
    




    
	static void Log(String s2) {
		String TAG = "Note";
		String s1 = "Table";
		Log.d(TAG, s1 + " - " + s2);

	}

  
}
