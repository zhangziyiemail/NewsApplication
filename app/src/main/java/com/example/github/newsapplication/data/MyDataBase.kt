package com.example.github.newsapplication.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.data.db.MyNewsData

/**
 *   Created by zhangziyi on 9/14/20 18:21
 */
@Database(entities = [MyNewsData::class],version = 1,exportSchema = false)
abstract class MyDataBase :RoomDatabase(){
    abstract fun newsArticleCacheDao() : NewsArticleCacheDao
}

object MyDatabaseUtils{
    private const val DB_NAME ="my_db"

    private val instance: MyDataBase by lazy {
        Room.databaseBuilder(MyApplication.instance,MyDataBase::class.java,DB_NAME)
            .addCallback(
                object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                    }
                }
            ).build()
    }

    val newsArticleCacheDao = instance.newsArticleCacheDao()

}