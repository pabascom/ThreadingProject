package phil.homework.threadingproject.ui.activity.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import phil.homework.threadingproject.data.repository.GithubMetaRepository
import phil.homework.week3test_gitcrawler.model.repo.Repository

class GithubViewModel: ViewModel() {

    //lateinit var repoData: LiveData<List<Repository>>
    private val githubMetaRepository = GithubMetaRepository()
    val repoData = githubMetaRepository.getRepoLiveData()

    fun getRepoData(username: String) {
        githubMetaRepository.getRepoList(username)
    }

}