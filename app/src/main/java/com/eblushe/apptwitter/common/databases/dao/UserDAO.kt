package com.eblushe.apptwitter.common.databases.dao

import androidx.room.*
import com.eblushe.apptwitter.common.models.User
import io.reactivex.Single

@Dao
interface UserDAO {
    @Insert
    fun save(user: User): Long

    @Update
    fun update(user: User) : Int

    @Delete
    fun delete(user: User) : Int

    @Query("select * from User where id = :id")
    fun findById(id: Long) : Single<User>

    @Query("select * from User where name = :name")
    fun findByName(name: String): Single<User>
}