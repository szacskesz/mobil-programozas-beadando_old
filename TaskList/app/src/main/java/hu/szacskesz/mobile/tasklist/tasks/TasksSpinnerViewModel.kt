package hu.szacskesz.mobile.tasklist.tasks

import android.app.Application
import androidx.lifecycle.MutableLiveData
import hu.szacskesz.mobile.tasklist.common.CommonViewModel
import hu.szacskesz.mobile.tasklist.core.domain.TaskList
import hu.szacskesz.mobile.tasklist.framework.Interactors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TasksSpinnerViewModel(application: Application, interactors: Interactors) : CommonViewModel(application, interactors) {

    val taskLists: MutableLiveData<List<TaskList>> = MutableLiveData()

    fun read() {
        GlobalScope.launch {
            val docs = interactors.readTaskLists()
            taskLists.postValue(docs)
        }
    }

    fun create(taskList: TaskList) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interactors.createTaskList(taskList)
            }
            read()
        }
    }

    fun update(taskList: TaskList) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interactors.updateTaskList(taskList)
            }
            read()
        }
    }

    fun delete(taskList: TaskList) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interactors.deleteTaskList(taskList)
            }
            read()
        }
    }
}