package hu.szacskesz.mobile.tasklist.framework.db.dao

import androidx.room.*
import hu.szacskesz.mobile.tasklist.framework.db.entity.TaskEntity
import hu.szacskesz.mobile.tasklist.framework.db.entity.TaskWithTaskListEntity


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(task: TaskEntity)

    @Transaction
    @Query("""
        SELECT * FROM `task` WHERE
            (:listId IS NUll OR `task`.list_id = :listId) AND
            (:isFinished IS NULL OR `task`.done = :isFinished)
    """)
    suspend fun read(listId: Int?, isFinished: Boolean?) : List<TaskEntity>

    @Transaction
    @Query("""
        SELECT * FROM `task` WHERE
            (:listId IS NUll OR `task`.list_id = :listId) AND
            (:isFinished IS NULL OR `task`.done = :isFinished)
    """)
    suspend fun readWithTaskList(listId: Int?, isFinished: Boolean?) : List<TaskWithTaskListEntity>

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}
