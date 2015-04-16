package com.example.drey.testforpromua.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.drey.testforpromua.dataobjects.Item;
import com.example.drey.testforpromua.dataobjects.Order;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

  private static final String TAG = DatabaseHelper.class.getSimpleName();

  //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
  private static final String DATABASE_NAME ="promua_test.db";
   
  //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
   private static final int DATABASE_VERSION = 5;
   
   //ссылки на DAO соответсвующие сущностям, хранимым в БД
   private OrderDAO orderDao = null;
   private ItemDAO itemDao = null;
   
   public DatabaseHelper(Context context){
       super(context,DATABASE_NAME, null, DATABASE_VERSION);
   }

   //Выполняется, когда файл с БД не найден на устройстве
   @Override
   public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
       try
       {
           TableUtils.createTable(connectionSource, Order.class);
           TableUtils.createTable(connectionSource, Item.class);
       }
       catch (SQLException e){
           Log.e(TAG, "error creating DB " + DATABASE_NAME);
           throw new RuntimeException(e);
       }
   }

   //Выполняется, когда БД имеет версию отличную от текущей
   @Override
   public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
           int newVer){
       try{
        //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
           TableUtils.dropTable(connectionSource, Item.class, true);
           TableUtils.dropTable(connectionSource, Order.class, true);
           onCreate(db, connectionSource);
       }
       catch (SQLException e){
           Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
           throw new RuntimeException(e);
       }
   }
   
   public OrderDAO getOrderDAO(){
       if(orderDao == null){
           try {
               orderDao = new OrderDAO(getConnectionSource(), Order.class);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return orderDao;
   }
   public ItemDAO getItemDAO(){
       if(itemDao == null){
           try {
               itemDao = new ItemDAO(getConnectionSource(), Item.class);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return itemDao;
   }
   
   //выполняется при закрытии приложения
   @Override
   public void close(){
       super.close();
       itemDao = null;
       orderDao = null;
   }
}