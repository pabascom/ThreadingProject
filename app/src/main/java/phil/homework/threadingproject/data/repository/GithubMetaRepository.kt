package phil.homework.threadingproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import phil.homework.threadingproject.network.RemoteDataSource
import phil.homework.week3test_gitcrawler.model.repo.Repository

class GithubMetaRepository {

    private val repoData = MutableLiveData<List<Repository>>()
    private val remoteDataSource = RemoteDataSource()

    fun getRepoLiveData(): LiveData<List<Repository>>{
        return repoData
    }

    fun getRepoList(username: String) {
        remoteDataSource.getRepoList(username){
            repoData.value = it
        }
    }

}