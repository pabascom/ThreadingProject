package phil.homework.threadingproject.thread

import android.os.AsyncTask
import android.util.Log
import phil.homework.threadingproject.manager.TaskManager

class MyAsyncTask(val callback:(Int)->Unit): AsyncTask<Int, Int, String>() {

    override fun onPreExecute() {
        Log.d(MyAsyncTask::class.java.simpleName, "onPreExecute: ${Thread.currentThread()}")

        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Int?): String {
        TaskManager.startTask(MyAsyncTask::class.java.simpleName, params[0]?:0){
            publishProgress(it)
        }

        return "Complete"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        callback.invoke(values[0]?:0)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        Log.d(MyAsyncTask::class.java.simpleName, "onPostExecute: ${Thread.currentThread()}")
    }
}